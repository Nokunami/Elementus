package net.nokunami.elementus.common.entity.ai.goal.steelGolem;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.nokunami.elementus.common.entity.living.TamableGolem;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class GolemLookGoal extends Goal {
    private final TamableGolem mob;
    private final boolean lookAtEntity;
    private double relX;
    private double relZ;

    private int lookTime;

    public static final float DEFAULT_PROBABILITY = 0.02F;
    @Nullable
    protected Entity lookAt;
    protected final float lookDistance;
    protected final float probability;
    private final boolean onlyHorizontal;
    protected final Class<? extends LivingEntity> lookAtType;
    protected final TargetingConditions lookAtContext;

    public GolemLookGoal(TamableGolem golem) {
        this(golem, new goalInfo());
    }

    public GolemLookGoal(TamableGolem golem, goalInfo info) {
        mob = golem;
        lookAtEntity = info.lookAtEntity;

        lookAtType = info.lookAtType;
        lookDistance = info.lookDistance;
        probability = info.probability;
        onlyHorizontal = goalInfo.onlyHorizontal;
        setFlags(EnumSet.of(Goal.Flag.LOOK));
        if (info.lookAtType == Player.class) {
            lookAtContext = TargetingConditions.forNonCombat().range(info.lookDistance).selector((p_25531_) -> EntitySelector.notRiding(golem).test(p_25531_));
        } else {
            lookAtContext = TargetingConditions.forNonCombat().range(info.lookDistance);
        }
    }

    @Override
    public boolean canUse() {
        if (mob.isOrderedToSit() || mob.isChassisBroken()) return false;
        else if (!lookAtEntity) {
            return mob.getRandom().nextFloat() < DEFAULT_PROBABILITY;
        } else {
            if (mob.getRandom().nextFloat() >= probability) {
                return false;
            } else {
                if (mob.getTarget() != null) {
                    lookAt = mob.getTarget();
                }

                if (lookAtType == Player.class) {
                    lookAt = mob.level().getNearestPlayer(lookAtContext, mob, mob.getX(), mob.getEyeY(), mob.getZ());
                } else {
                    lookAt = mob.level().getNearestEntity(mob.level().getEntitiesOfClass(lookAtType, mob.getBoundingBox()
                            .inflate(lookDistance, 3.0D, lookDistance), (p_148124_) -> true), lookAtContext, mob, mob.getX(), mob.getEyeY(), mob.getZ());
                }

                return lookAt != null;
            }
        }
    }

    @Override
    public boolean canContinueToUse() {
        if (lookAtEntity) {
            if (!lookAt.isAlive()) {
                return false;
            } else if (mob.distanceToSqr(lookAt) > (double)(lookDistance * lookDistance)) {
                return false;
            } else {
                return lookTime > 0;
            }
        }
        return super.canContinueToUse();
    }

    @Override
    public void start() {
        if (!lookAtEntity) {
            double d0 = (Math.PI * 2D) * mob.getRandom().nextDouble();
            relX = Math.cos(d0);
            relZ = Math.sin(d0);
            lookTime = 20 + mob.getRandom().nextInt(20);
        } else {
            lookTime = adjustedTickDelay(40 + mob.getRandom().nextInt(40));
        }
    }

    @Override
    public void stop() {
        if (lookAtEntity) lookAt = null;
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return !lookAtEntity;
    }

    @Override
    public void tick() {
        if (!lookAtEntity) {
            --lookTime;
            mob.getLookControl().setLookAt(mob.getX() + relX, mob.getEyeY(), mob.getZ() + relZ);
        } else {
            if (lookAt.isAlive()) {
                double d0 = onlyHorizontal ? mob.getEyeY() : lookAt.getEyeY();
                mob.getLookControl().setLookAt(lookAt.getX(), d0, lookAt.getZ());
                --lookTime;
            }
        }
    }

    public static class goalInfo {
        boolean lookAtEntity = false;
        float lookDistance;
        float probability = DEFAULT_PROBABILITY;
        static boolean onlyHorizontal;
        Class<? extends LivingEntity> lookAtType;

        public goalInfo lookAt(Class<? extends LivingEntity> lookAtType, float lookDist) {
            return lookAt(lookAtType, lookDist, DEFAULT_PROBABILITY);
        }

        public goalInfo lookAt(Class<? extends LivingEntity> lookAtType, float lookDist, float probability) {
            return lookAt(lookAtType, lookDist, probability, false);
        }

        public goalInfo lookAt(Class<? extends LivingEntity> lookAtType, float lookDist, float probability, boolean onlyHorizontal) {
            lookAtEntity = true;
            this.lookAtType = lookAtType;
            lookDistance = lookDist;
            this.probability = probability;
            goalInfo.onlyHorizontal = onlyHorizontal;
            return this;
        }
    }
}
