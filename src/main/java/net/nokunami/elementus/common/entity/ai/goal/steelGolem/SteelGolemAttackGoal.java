package net.nokunami.elementus.common.entity.ai.goal.steelGolem;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.monster.Enemy;
import net.nokunami.elementus.common.entity.living.SteelGolem;
import org.jetbrains.annotations.NotNull;

import java.util.EnumSet;
import java.util.function.Predicate;

import static net.nokunami.elementus.common.entity.MobUtil.alliedAttacked;

public class SteelGolemAttackGoal extends MeleeAttackGoal {
    private SteelGolem steelGolem;
    protected int attackDelay = 10;
    protected int ticksTilNextAttack = 20;
    protected int fastAttackDelay = 10;
    protected int ticksTilNextFastAttack = 10;
    protected int aoeAttackDelay = 20;
    protected int ticksTilNextAoeAttack = 15;
    protected boolean shouldCountTillNextAttack = false;
    protected LivingEntity golemOwner;
    private final Predicate<Entity> aoeFilter = (e -> (alliedAttacked(steelGolem, e) || (golemOwner != null && alliedAttacked(golemOwner, e))));

    public SteelGolemAttackGoal(SteelGolem golem, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
        super(golem, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
//        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        this.steelGolem = golem;
        this.golemOwner = golem.getOwner() != null ? golem.getOwner() : null;
    }

    @Override
    protected void checkAndPerformAttack(@NotNull LivingEntity enemy, double distToEnemySqr) {
        if (isEnemyWithinAttackDistance(enemy, distToEnemySqr)) {
            shouldCountTillNextAttack = true;

            if(isTimeToStartAttackAnimation()) {
                steelGolem.setAttacking(true);
                this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
            }
            if(isTimeToAttack()) {
                this.mob.getLookControl().setLookAt(enemy.getX(), enemy.getEyeY(), enemy.getZ());
                performAttack(enemy);
            }

            if(isTimeToStartAoeAttackAnimation()) {
                steelGolem.setAoeAttacking(true);
                steelGolem.setAttackType(2);
            }
            if(isTimeToAoeAttack()) {
                this.mob.getLookControl().setLookAt(enemy.getX(), enemy.getEyeY(), enemy.getZ());
                performAoeAttack(enemy);
            }

        } else {
            resetAttackCooldown();
            shouldCountTillNextAttack = false;
            steelGolem.setAttacking(false);
            steelGolem.setAoeAttacking(false);
            steelGolem.attackAnimTimeout = 0;
            steelGolem.aoeAttackAnimTimeout = 0;
        }
    }

    boolean isEnemyWithinAttackDistance(LivingEntity enemy, double distToEnemySqr) {
        return distToEnemySqr <= this.getAttackReachSqr(enemy) * 2.25;
    }

    protected void resetAttackCooldown() {
        if (steelGolem.getFastAttack()) {
            this.ticksTilNextFastAttack = this.adjustedTickDelay(fastAttackDelay * 2);
        } else this.ticksTilNextAttack = this.adjustedTickDelay(attackDelay * 2);
    }

    protected boolean isTimeToStartAttackAnimation() {
        if (steelGolem.getAoeTimer() <= 0) {
            return false;
        } else {
            if (steelGolem.getFastAttack()) {
                return this.ticksTilNextFastAttack <= fastAttackDelay;
            } else return this.ticksTilNextAttack <= attackDelay;
        }
    }

    protected boolean isTimeToAttack() {
        if (steelGolem.getFastAttack()) {
            return this.ticksTilNextFastAttack <= 0;
        } else return this.ticksTilNextAttack <= 0;
    }

    protected int getTicksUntilNextAttack() {
        if (steelGolem.getFastAttack()) {
            return this.ticksTilNextFastAttack;
        } else return this.ticksTilNextAttack;
    }

    protected void performAttack(LivingEntity enemy) {
        this.resetAttackCooldown();
        this.mob.swing(InteractionHand.MAIN_HAND);
        this.mob.doHurtTarget(enemy);
    }

    protected void resetAoeAttackCooldown() {
        this.ticksTilNextAoeAttack = this.adjustedTickDelay(aoeAttackDelay * 2);
        this.steelGolem.setAoeTimer(steelGolem.aoeAnimTimeout);
    }

    protected boolean isTimeToStartAoeAttackAnimation() {
        return ticksTilNextAoeAttack <= aoeAttackDelay && this.steelGolem.getAoeTimer() <= 0;
    }

    protected boolean isTimeToAoeAttack() {
        return ticksTilNextAoeAttack <= 0 && steelGolem.getAoeTimer() <= 0;
    }

    protected void performAoeAttack(LivingEntity enemy) {
        this.groundAttack(enemy);
        this.resetAoeAttackCooldown();
        this.steelGolem.setAttackType(2);
        this.mob.swing(InteractionHand.MAIN_HAND);
        this.mob.getNavigation().stop();
        this.mob.doHurtTarget(enemy);
    }

    @Override
    public void tick() {
        super.tick();
        if (!steelGolem.isChassisCompromised()) {
            if (steelGolem.getAoeTimer() > 20) {
                if (shouldCountTillNextAttack) {
                    if (steelGolem.getFastAttack()) {
                        this.ticksTilNextFastAttack = Math.max(this.ticksTilNextFastAttack - 1, 0);
                    } else {
                        this.ticksTilNextAttack = Math.max(this.ticksTilNextAttack - 1, 0);
                    }
                }
            } else {
                this.ticksTilNextAoeAttack = Math.max(this.ticksTilNextAoeAttack - 1,0);
            }
        }
    }

    @Override
    public void stop() {
        super.stop();
        steelGolem.setAttacking(false);
        steelGolem.setAoeAttacking(false);
    }

    @Override
    public boolean canUse() {
        return !steelGolem.isChassisCompromised() && super.canUse();
    }

    private void groundAttack(LivingEntity livingEntity) {
        if (this.steelGolem.onGround()) {
            this.steelGolem.playSound(SoundEvents.GENERIC_EXPLODE, 1.4F, 1.4F);
            this.steelGolem.playSound(SoundEvents.IRON_GOLEM_ATTACK, 1.0F, 1.0F);
            for (Entity entity : this.steelGolem.level().getEntitiesOfClass(LivingEntity.class, this.steelGolem.getBoundingBox().inflate(6.0D), aoeFilter)) {
                if (entity instanceof Enemy || entity == steelGolem.getTarget() || (livingEntity instanceof Mob target && (target.getTarget() == this.mob))) {
                    if (entity.onGround()) {
                        boolean flag = entity.hurt(this.steelGolem.damageSources().mobAttack(this.steelGolem),
                                (float) this.steelGolem.getAttributeValue(Attributes.ATTACK_DAMAGE) * 0.75F);
                        if (flag) {
                            entity.getDeltaMovement().add(0.0D, 0.2D, 0.0D);
                        }
                    }
                    launch(entity);
                }
            }
        }
    }

    private void launch(Entity entity) {
        double d0 = entity.getX() - this.steelGolem.getX();
        double d1 = entity.getZ() - this.steelGolem.getZ();
        double d2 = Math.max(d0 * d0 + d1 * d1, 0.001D);
        entity.push(d0 / d2 * 1.25D, 0.2D, d1 / d2 * 1.25D);
    }
}