package net.nokunami.elementus.common.entity.ai.goal.steelGolem;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
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
import net.nokunami.elementus.common.entity.living.TamableGolem;

import java.util.EnumSet;

public class GolemFollowOwnerGoal extends Goal {
    private static final int MIN_HORIZONTAL_TP_DIST = 2;
    private static final int MAX_HORIZONTAL_DIST_TP = 5;
    private static final int MAX_VERTICAL_DIST_TP = 3;
    private final TamableGolem golem;
    private LivingEntity owner;
    private final LevelReader level;
    private final double speedModifier;
    private final double aggroSpeedModifier;
    private final PathNavigation navigation;
    private int timeToRecalcPath;
    private final float stopDistance;
    private final float aggroStopDistance;
    private final float startDist;
    private final float startDistAggro;
    private float oldWaterCost;
    private final boolean canFly;
    private final float tpDist;
    private final float tpDistAggro;
    private final boolean brokenChassis;

    public GolemFollowOwnerGoal(TamableGolem golem, goalInfo info) {
        this.golem = golem;
        this.level = golem.level();
        this.navigation = golem.getNavigation();
        this.speedModifier = info.speedModifier;
        this.aggroSpeedModifier = info.aggroSpeedModifier;
        this.startDist = info.startDistance;
        this.startDistAggro = info.aggroStartDistance;
        this.stopDistance = info.stopDistance;
        this.aggroStopDistance = info.aggroStopDistance;
        this.tpDist = info.teleportDistance;
        this.tpDistAggro = info.aggroTeleportDistance;
        this.canFly = info.canFly;
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
        LivingEntity livingentity = this.golem.getOwner();
        if (golem.isChassisBroken()) {
            return false;
        } else {
            if (livingentity == null) {
                return false;
            } else if (livingentity.isSpectator()) {
                return false;
            } else if (this.unableToMove()) {
                return false;
            } else if ((golem.distanceToSqr(livingentity) < Mth.square(startDist) && !golem.getAggroState()) || (golem.distanceToSqr(livingentity) < Mth.square(startDistAggro) && golem.getAggroState())) {
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
            if (golem.getAggroState()) {
                return !(this.golem.distanceToSqr(this.owner) <= (double)(this.aggroStopDistance * this.startDistAggro));
            } else {
                return !(this.golem.distanceToSqr(this.owner) <= (double)(this.stopDistance * this.stopDistance));
            }
        }
    }

    private boolean unableToMove() {
        return this.golem.isOrderedToSit() || this.golem.isPassenger() || this.golem.isLeashed() || this.brokenChassis;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void start() {
        this.timeToRecalcPath = 0;
        this.oldWaterCost = this.golem.getPathfindingMalus(BlockPathTypes.WATER);
        this.golem.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
    }

    public void stop() {
        this.owner = null;
        this.navigation.stop();
        this.golem.setPathfindingMalus(BlockPathTypes.WATER, this.oldWaterCost);
    }

    public void tick() {
        this.golem.getLookControl().setLookAt(this.owner, 10.0F, (float)this.golem.getMaxHeadXRot());
        if (golem.getTarget() == null) {
            if (--this.timeToRecalcPath <= 0) {
                this.timeToRecalcPath = this.adjustedTickDelay(10);
                if ((golem.getAggroState() && golem.distanceToSqr(owner) >= Mth.square(tpDistAggro)) || (!golem.getAggroState() && golem.distanceToSqr(owner) >= Mth.square(tpDist)))
                    teleportToOwner();
                else {
                    if (golem.getAggroState()) this.navigation.moveTo(this.owner, this.aggroSpeedModifier);
                    else this.navigation.moveTo(this.owner, this.speedModifier);
                }
            }
        }
    }

    private void teleportToOwner() {
        BlockPos blockpos = this.owner.blockPosition();
        for(int i = 0; i < 10; ++i) {
            int j = randomIntInclusive(-MAX_HORIZONTAL_DIST_TP, MAX_HORIZONTAL_DIST_TP);
            int k = randomIntInclusive(-MAX_VERTICAL_DIST_TP, MAX_VERTICAL_DIST_TP);
            int l = randomIntInclusive(-MAX_HORIZONTAL_DIST_TP, MAX_HORIZONTAL_DIST_TP);
            boolean bl = maybeTeleportTo(blockpos.getX() + j, blockpos.getY() + k, blockpos.getZ() + l);

            if(bl)
                return;
        }
    }

    private boolean maybeTeleportTo(int pX, int pY, int pZ) {
        if (Math.abs((double)pX - this.owner.getX()) < MIN_HORIZONTAL_TP_DIST && Math.abs((double)pZ - this.owner.getZ()) < MIN_HORIZONTAL_TP_DIST) {
            return false;
        } else if (!this.canTeleportTo(new BlockPos(pX, pY, pZ))) {
            return false;
        } else {
            this.golem.moveTo((double)pX + 0.5D, pY, (double)pZ + 0.5D, this.golem.getYRot(), this.golem.getXRot());
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
                BlockPos blockpos = pPos.subtract(this.golem.blockPosition());
                return this.level.noCollision(this.golem, this.golem.getBoundingBox().move(blockpos));
            }
        }
    }

    private int randomIntInclusive(int pMin, int pMax) {
        return this.golem.getRandom().nextInt(pMax - pMin + 1) + pMin;
    }

    public static class goalInfo {
        double speedModifier = 0;
        float startDistance = 0;
        float stopDistance = 0;
        int teleportDistance = 0;
        double aggroSpeedModifier = speedModifier;
        float aggroStartDistance = startDistance;
        float aggroStopDistance = stopDistance;
        int aggroTeleportDistance = teleportDistance;
        boolean canFly = false;

        public goalInfo speed(double amount) {
            this.speedModifier = amount;
            return this;
        }
        public goalInfo start(int amount) {
            this.startDistance = amount;
            return this;
        }
        public goalInfo stop(int amount) {
            this.stopDistance = amount;
            return this;
        }
        public goalInfo teleport(int amount) {
            this.teleportDistance = amount;
            return this;
        }

        public goalInfo speed(double amount1, double amount2) {
            this.speedModifier = amount1;
            this.aggroSpeedModifier = amount2;
            return this;
        }
        public goalInfo start(int amount1, int amount2) {
            this.startDistance = amount1;
            this.aggroStartDistance = amount2;
            return this;
        }
        public goalInfo stop(int amount1, int amount2) {
            this.stopDistance = amount1;
            this.aggroStopDistance = amount2;
            return this;
        }
        public goalInfo teleport(int amount1, int amount2) {
            this.teleportDistance = amount1;
            this.aggroTeleportDistance = amount2;
            return this;
        }

        public goalInfo canFly() {
            this.canFly = true;
            return this;
        }
    }
}