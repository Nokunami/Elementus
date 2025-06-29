package nokunami.elementus.common.entity.ai.goal.steelGolem;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import nokunami.elementus.common.entity.living.SteelGolem;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.function.Predicate;

public class SteelGolemNearestAttackableGoal<T extends LivingEntity> extends TargetGoal {
    private static final int DEFAULT_RANDOM_INTERVAL = 10;
    protected final Class<T> targetType;
    protected final int randomInterval;
    @Nullable
    protected LivingEntity target;
    /** This filter is applied to the Entity search. Only matching entities will be targeted. */
    protected TargetingConditions targetConditions;
    private final SteelGolem steelGolem;

//    public SteelGolemNearestAttackableGoal(SteelGolem golem, Class<T> pTargetType, boolean pMustSee) {
//        this(golem, pTargetType, DEFAULT_RANDOM_INTERVAL, pMustSee, false, null);
//    }
//
//    public SteelGolemNearestAttackableGoal(SteelGolem golem, Class<T> pTargetType, boolean pMustSee, Predicate<LivingEntity> pTargetPredicate) {
//        this(golem, pTargetType, DEFAULT_RANDOM_INTERVAL, pMustSee, false, pTargetPredicate);
//    }
//
//    public SteelGolemNearestAttackableGoal(SteelGolem golem, Class<T> pTargetType, boolean pMustSee, boolean pMustReach) {
//        this(golem, pTargetType, DEFAULT_RANDOM_INTERVAL, pMustSee, pMustReach, null);
//    }

    public SteelGolemNearestAttackableGoal(SteelGolem golem, Class<T> pTargetType, int pRandomInterval, boolean pMustSee, boolean pMustReach, @Nullable Predicate<LivingEntity> pTargetPredicate) {
        super(golem, pMustSee, pMustReach);
        this.targetType = pTargetType;
        this.randomInterval = reducedTickDelay(pRandomInterval);
        this.setFlags(EnumSet.of(Goal.Flag.TARGET));
        this.targetConditions = TargetingConditions.forCombat().range(this.getFollowDistance()).selector(pTargetPredicate);
        this.steelGolem = golem;
    }

    /**
     * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
     * method as well.
     */
    public boolean canUse() {
        if (!steelGolem.getAggroState()) {
            return false;
        } else if (this.randomInterval > 0 && this.steelGolem.getRandom().nextInt(this.randomInterval) != 0) {
            return false;
        } else {
            this.findTarget();
            return this.target != null;
        }
    }

    protected AABB getTargetSearchArea(double pTargetDistance) {
        return this.steelGolem.getBoundingBox().inflate(pTargetDistance, 4.0D, pTargetDistance);
    }

    protected void findTarget() {
        if (steelGolem.getAggroState()) {
            if (this.targetType != Player.class && this.targetType != ServerPlayer.class) {
                this.target = this.steelGolem.level().getNearestEntity(this.steelGolem.level()
                                .getEntitiesOfClass(this.targetType, this.getTargetSearchArea(this.getFollowDistance()), (living) -> true),
                        this.targetConditions, this.steelGolem, this.steelGolem.getX(), this.steelGolem.getEyeY(), this.steelGolem.getZ());
            } else {
                this.target = this.steelGolem.level().getNearestPlayer(this.targetConditions, this.steelGolem, this.steelGolem.getX(), this.steelGolem.getEyeY(), this.steelGolem.getZ());
            }
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void start() {
        if (steelGolem.getAggroState()) {
            this.steelGolem.setTarget(this.target);
            super.start();
        }
    }

    public void setTarget(@Nullable LivingEntity pTarget) {
        this.target = pTarget;
    }
}