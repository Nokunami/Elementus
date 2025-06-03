package net.nokunami.elementus.common.entity.ai.goal.steelGolem;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;
import net.nokunami.elementus.common.entity.living.SteelGolem;

import java.util.EnumSet;

public class SteelGolemFollowOwnerGoal extends Goal {
    private static final int MIN_HORIZONTAL_DISTANCE_WHEN_TELEPORTING = 2;
    private static final int MAX_HORIZONTAL_DISTANCE_WHEN_TELEPORTING = 5;
    private static final int MAX_VERTICAL_DISTANCE_WHEN_TELEPORTING = 3;
    private final SteelGolem steelGolem;
    private LivingEntity owner;
    private final LevelReader level;
    private final double speedModifier;
    private final double aggroSpeedModifier;
    private final PathNavigation navigation;
    private int timeToRecalcPath;
    private final float stopDistance;
    private final float aggroStopDistance;
    private final float startDistance;
    private final float aggroStartDistance;
    private float oldWaterCost;
    private final boolean canFly;
    private final float teleportDistance;
    private final float aggroTeleportDistance;
    private final boolean brokenChassis;

    public SteelGolemFollowOwnerGoal(SteelGolem golem, double normalSpeed, double aggroSpeed, float startDistance, float aggroStartDistance, float stopDistance, float aggroStopDistance, boolean canFly, int teleportDistance, int aggroTeleportDistance) {
        this.steelGolem = golem;
        this.level = golem.level();
        this.navigation = golem.getNavigation();
        this.speedModifier = normalSpeed;
        this.aggroSpeedModifier = aggroSpeed;
        this.startDistance = startDistance;
        this.aggroStartDistance = aggroStartDistance;
        this.stopDistance = stopDistance;
        this.aggroStopDistance = aggroStopDistance;
        this.teleportDistance = teleportDistance;
        this.aggroTeleportDistance = aggroTeleportDistance;
        this.canFly = canFly;
        this.brokenChassis = golem.isChassisBroken();
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        if (!(golem.getNavigation() instanceof GroundPathNavigation) && !(golem.getNavigation() instanceof FlyingPathNavigation)) {
            throw new IllegalArgumentException("Unsupported mob type for FollowOwnerGoal");
        }
    }

    /**
     * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
     * method as well.
     */
    public boolean canUse() {
        LivingEntity livingentity = this.steelGolem.getOwner();
        if (steelGolem.isChassisBroken()) {
            return false;
        } else {
            if (livingentity == null) {
                return false;
            } else if (livingentity.isSpectator()) {
                return false;
            } else if (this.unableToMove()) {
                return false;
            } else if (this.steelGolem.distanceToSqr(livingentity) < (double)(this.startDistance * this.startDistance) && !steelGolem.getAggroState()) {
                return false;
            } else if (this.steelGolem.distanceToSqr(livingentity) < (double)(this.aggroStartDistance * this.aggroStartDistance) && steelGolem.getAggroState()) {
                return false;
            } else {
                this.owner = livingentity;
                return true;
            }
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean canContinueToUse() {
        if (this.navigation.isDone()) {
            return false;
        } else if (this.unableToMove()) {
            return false;
        } else {
            if (steelGolem.getAggroState()) {
                return !(this.steelGolem.distanceToSqr(this.owner) <= (double)(this.aggroStopDistance * this.aggroStartDistance));
            } else {
                return !(this.steelGolem.distanceToSqr(this.owner) <= (double)(this.stopDistance * this.stopDistance));
            }
        }
    }

    private boolean unableToMove() {
        return this.steelGolem.isOrderedToSit() || this.steelGolem.isPassenger() || this.steelGolem.isLeashed() || this.brokenChassis;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void start() {
        this.timeToRecalcPath = 0;
        this.oldWaterCost = this.steelGolem.getPathfindingMalus(BlockPathTypes.WATER);
        this.steelGolem.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
    }

    public void stop() {
        this.owner = null;
        this.navigation.stop();
        this.steelGolem.setPathfindingMalus(BlockPathTypes.WATER, this.oldWaterCost);
    }

    public void tick() {
        this.steelGolem.getLookControl().setLookAt(this.owner, 10.0F, (float)this.steelGolem.getMaxHeadXRot());
        if (steelGolem.getTarget() == null) {
            if (--this.timeToRecalcPath <= 0) {
                this.timeToRecalcPath = this.adjustedTickDelay(10);
                if (this.steelGolem.distanceToSqr(this.owner) >= (aggroTeleportDistance * aggroTeleportDistance) && steelGolem.getAggroState()) {
                    this.teleportToOwner();
                } else if (this.steelGolem.distanceToSqr(this.owner) >= (teleportDistance * teleportDistance) && !steelGolem.getAggroState()) {
                    this.teleportToOwner();
                } else {
                    if (steelGolem.getAggroState()) {
                        this.navigation.moveTo(this.owner, this.aggroSpeedModifier);
                    } else {
                        this.navigation.moveTo(this.owner, this.speedModifier);
                    }
//                    this.navigation.moveTo(this.owner, aggro ? this.aggroSpeedModifier : this.speedModifier);
                }
            }
        }
    }

    private void teleportToOwner() {
        BlockPos blockpos = this.owner.blockPosition();
        for(int i = 0; i < 10; ++i) {
            int j = randomIntInclusive(-MAX_HORIZONTAL_DISTANCE_WHEN_TELEPORTING, MAX_HORIZONTAL_DISTANCE_WHEN_TELEPORTING);
            int k = randomIntInclusive(-MAX_VERTICAL_DISTANCE_WHEN_TELEPORTING, MAX_VERTICAL_DISTANCE_WHEN_TELEPORTING);
            int l = randomIntInclusive(-MAX_HORIZONTAL_DISTANCE_WHEN_TELEPORTING, MAX_HORIZONTAL_DISTANCE_WHEN_TELEPORTING);
            boolean bl = maybeTeleportTo(blockpos.getX() + j, blockpos.getY() + k, blockpos.getZ() + l);

            if(bl)
                return;
        }
    }

    private boolean maybeTeleportTo(int pX, int pY, int pZ) {
        if (Math.abs((double)pX - this.owner.getX()) < MIN_HORIZONTAL_DISTANCE_WHEN_TELEPORTING && Math.abs((double)pZ - this.owner.getZ()) < MIN_HORIZONTAL_DISTANCE_WHEN_TELEPORTING) {
            return false;
        } else if (!this.canTeleportTo(new BlockPos(pX, pY, pZ))) {
            return false;
        } else {
            this.steelGolem.moveTo((double)pX + 0.5D, pY, (double)pZ + 0.5D, this.steelGolem.getYRot(), this.steelGolem.getXRot());
            this.navigation.stop();
            return true;
        }
    }

    private boolean canTeleportTo(BlockPos pPos) {
        BlockPathTypes blockpathtypes = WalkNodeEvaluator.getBlockPathTypeStatic(this.level, pPos.mutable());
        if (blockpathtypes != BlockPathTypes.WALKABLE) {
            return false;
        } else {
            BlockState blockstate = this.level.getBlockState(pPos.below());
            if (!this.canFly && blockstate.getBlock() instanceof LeavesBlock) {
                return false;
            } else {
                BlockPos blockpos = pPos.subtract(this.steelGolem.blockPosition());
                return this.level.noCollision(this.steelGolem, this.steelGolem.getBoundingBox().move(blockpos));
            }
        }
    }

    private int randomIntInclusive(int pMin, int pMax) {
        return this.steelGolem.getRandom().nextInt(pMax - pMin + 1) + pMin;
    }
}
