package net.nokunami.elementus.common.entity.ai.navigation;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.*;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class GolemNavigation extends GroundPathNavigation {

    public GolemNavigation(Mob pMob, Level pLevel) {
        super(pMob, pLevel);
    }

    @Override
    public @NotNull NodeEvaluator getNodeEvaluator() {
        return new CustomWalkNodeEvaluator();
    }

    protected @NotNull PathFinder createPathFinder(int pMaxVisitedNodes) {
        nodeEvaluator = new WalkNodeEvaluator();
        nodeEvaluator.setCanOpenDoors(true);
        nodeEvaluator.setCanPassDoors(true);
        nodeEvaluator.setCanFloat(false);
        nodeEvaluator.setCanWalkOverFences(true);
        return new PathFinder(nodeEvaluator, pMaxVisitedNodes);
    }

    /**
     * If on ground or swimming and can swim
     */
    protected boolean canUpdatePath() {
//        return mob.onGround() || isInLiquid() || mob.isPassenger();
        return true;
    }

    protected @NotNull Vec3 getTempMobPos() {
        return new Vec3(mob.getX(), getSurfaceY(), mob.getZ());
    }

    /**
     * Returns path to given BlockPos
     */
    public @NotNull Path createPath(@NotNull BlockPos pPos, int pAccuracy) {
        if (level.getBlockState(pPos).isAir()) {
            BlockPos blockpos;
            for(blockpos = pPos.below(); blockpos.getY() > level.getMinBuildHeight() && level.getBlockState(blockpos).isAir(); blockpos = blockpos.below()) {
            }

            if (blockpos.getY() > level.getMinBuildHeight()) {
                return super.createPath(blockpos.above(), pAccuracy);
            }

            while(blockpos.getY() < level.getMaxBuildHeight() && level.getBlockState(blockpos).isAir()) {
                blockpos = blockpos.above();
            }

            pPos = blockpos;
        }

        if (!level.getBlockState(pPos).isSolid()) {
            return super.createPath(pPos, pAccuracy);
        } else {
            BlockPos blockpos;
            for(blockpos = pPos.above(); blockpos.getY() < level.getMaxBuildHeight() && level.getBlockState(blockpos).isSolid(); blockpos = blockpos.above()) {
            }

            return super.createPath(blockpos, pAccuracy);
        }
    }

    /**
     * Returns a path to the given entity or null
     */
    public @NotNull Path createPath(Entity pEntity, int pAccuracy) {
        return createPath(pEntity.blockPosition(), pAccuracy);
    }

    /**
     * Gets the safe pathing Y position for the entity depending on if it can path swim or not
     */
    private int getSurfaceY() {
        if (mob.isInWater() && canFloat()) {
            int i = mob.getBlockY();
            BlockState blockstate = level.getBlockState(BlockPos.containing(mob.getX(), i, mob.getZ()));
            int j = 0;

            while(blockstate.is(Blocks.WATER)) {
                ++i;
                blockstate = level.getBlockState(BlockPos.containing(mob.getX(), i, mob.getZ()));
                ++j;
                if (j > 16) {
                    return mob.getBlockY();
                }
            }

            return i;
        } else {
            return Mth.floor(mob.getY() + 0.5D);
        }
    }

    /**
     * Trims path data from the end to the first sun covered block
     */
    protected void trimPath() {
        super.trimPath();
    }

    protected boolean hasValidPathType(@NotNull BlockPathTypes type) {
        if (type == BlockPathTypes.WATER || type == BlockPathTypes.LAVA) {
            return false;
        } else {
            return type != BlockPathTypes.OPEN;
        }
    }
}
