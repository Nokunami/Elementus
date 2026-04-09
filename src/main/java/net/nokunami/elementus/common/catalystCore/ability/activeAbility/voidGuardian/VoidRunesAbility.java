package net.nokunami.elementus.common.catalystCore.ability.activeAbility.voidGuardian;

import com.github.L_Ender.cataclysm.config.CMCommonConfig;
import com.github.L_Ender.cataclysm.entity.projectile.Void_Rune_Entity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.nokunami.elementus.common.capability.cAbility.CAbility;
import net.nokunami.elementus.common.catalystCore.ability.AbilityType;
import net.nokunami.elementus.common.catalystCore.ability.activeAbility.AbstractActiveAbility;

import static net.nokunami.elementus.ModChecker.cataclysm;

public class VoidRunesAbility extends AbstractActiveAbility {

    public VoidRunesAbility() { super(new AbilityProperties(AbilityType.TRIGGERED, 40).setDefaultCharge(3)); }

    @Override
    public boolean castAbility(LivingEntity entity, Level level, CAbility ca) {
        if (cataclysm) {
            if (!level.isClientSide) {
                int standingOnY = Mth.floor(entity.getY()) - 1;
                double headY = entity.getY() + 1;
                float yawRadians = (float) Math.toRadians(90 + entity.getYRot());
                boolean hasSucceeded = false;
                if (entity.getXRot() > 70) {
                    for(int i = 0; i < 5; ++i) {
                        float mulPosedYaw = yawRadians + i * (float) Math.PI * 0.4F;
                        if (spawnFangs(entity.getX() + Mth.cos(mulPosedYaw) * 1.5, headY, entity.getZ() + Mth.sin(mulPosedYaw) * 1.5, standingOnY, mulPosedYaw, 0, level, entity)) {
                            hasSucceeded = true;
                        }
                    }

                    for(int k = 0; k < 8; ++k) {
                        float mulPosedYaw = yawRadians + k * (float) Math.PI * 2.0F / 8.0F + 1.2566371F;
                        if (spawnFangs(entity.getX() + Mth.cos(mulPosedYaw) * 2.5, headY, entity.getZ() + Mth.sin(mulPosedYaw) * 2.5, standingOnY, mulPosedYaw, 3, level, entity)) {
                            hasSucceeded = true;
                        }
                    }
                } else {
                    for(int l = 0; l < 10; ++l) {
                        double d2 = 1.25 * (l + 1);
                        if (spawnFangs(entity.getX() + Mth.cos(yawRadians) * d2, headY, entity.getZ() + Mth.sin(yawRadians) * d2, standingOnY, yawRadians, l, level, entity)) {
                            hasSucceeded = true;
                        }
                    }
                }
                return hasSucceeded;
            }
        }
        return false;
    }

    // Credits: Cataclysm: Void_cores.class
    private boolean spawnFangs(double x, double y, double z, int lowestYCheck, float yRot, int warmupDelayTicks, Level world, LivingEntity player) {
        if (cataclysm) {
            BlockPos blockpos = BlockPos.containing(x, y, z);
            boolean flag = false;
            double d0 = 0;

            do {
                BlockPos below = blockpos.below();
                BlockState state = world.getBlockState(below);
                if (state.isFaceSturdy(world, below, Direction.UP)) {
                    if (!world.isEmptyBlock(blockpos)) {
                        BlockState blockstate1 = world.getBlockState(blockpos);
                        VoxelShape voxelshape = blockstate1.getCollisionShape(world, blockpos);
                        if (!voxelshape.isEmpty()) {
                            d0 = voxelshape.max(Direction.Axis.Y);
                        }
                    }
                    flag = true;
                    break;
                }
                blockpos = blockpos.below();
            } while (blockpos.getY() >= lowestYCheck);

            if (flag) {
                world.addFreshEntity(new Void_Rune_Entity(world, x, (double) blockpos.getY() + d0, z, yRot, warmupDelayTicks, (float) CMCommonConfig.VoidCore.runeDamage, player));
                return true;
            } else return false;
        }
        return false;
    }
}