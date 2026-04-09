package net.nokunami.elementus.common.entity.ai.goal.steelGolem;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.monster.Enemy;
import net.nokunami.elementus.common.entity.living.SteelGolem;
import org.jetbrains.annotations.NotNull;

import java.util.EnumSet;
import java.util.function.Predicate;

import static net.nokunami.elementus.common.entity.MobUtil.alliedAttacked;

public class SteelGolemAttackGoal extends MeleeAttackGoal {
    private SteelGolem golem;
    protected int attackDelay = 10;
    protected int ticksTilNextAttack = 20;
    protected int fastAttackDelay = 10;
    protected int ticksTilNextFastAttack = 10;
    protected int aoeAttackDelay = 20;
    protected int ticksTilNextAoeAttack = 15;
    protected boolean shouldCountTillNextAttack = false;
    protected LivingEntity golemOwner;
    private final Predicate<Entity> aoeFilter = (e -> (alliedAttacked(golem, e) || (golemOwner != null && alliedAttacked(golemOwner, e))));

    public SteelGolemAttackGoal(SteelGolem golem, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
        super(golem, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
        this.golem = golem;
        golemOwner = golem.getOwner() != null ? golem.getOwner() : null;
    }

    @Override
    protected void checkAndPerformAttack(@NotNull LivingEntity enemy, double distToEnemySqr) {
        if (distanceToStartAttackWindUp(enemy, distToEnemySqr)) {
            shouldCountTillNextAttack = true;

            if(isTimeToStartAttackAnimation()) {
                golem.setAttacking(true);
                setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
            }
            if(isTimeToAttack() && distanceToStartAttack(enemy, distToEnemySqr)) {
                mob.getLookControl().setLookAt(enemy.getX(), enemy.getEyeY(), enemy.getZ());
                performAttack(enemy);
            }

            if(isTimeToStartAoeAttackAnimation()) {
                golem.setAoeAttacking(true);
                golem.setAttackType(2);
            }
            if(isTimeToAoeAttack() && distanceToStartAttack(enemy, distToEnemySqr)) {
//                mob.getLookControl().setLookAt(enemy.getX(), enemy.getEyeY(), enemy.getZ());
                performAoeAttack(enemy);
            }

        } else {
            resetAttackCooldown();
            shouldCountTillNextAttack = false;
            golem.setAttacking(false);
            golem.setAoeAttacking(false);
            golem.attackAnimTimeout = 0;
            golem.aoeAttackAnimTimeout = 0;
        }
    }

    boolean distanceToStartAttackWindUp(LivingEntity enemy, double distToEnemySqr) {
        return distToEnemySqr <= getAttackReachSqr(enemy) * 2.5;
    }

    boolean distanceToStartAttack(LivingEntity enemy, double distToEnemySqr) {
        return distToEnemySqr <= getAttackReachSqr(enemy) * 2.25;
    }

    protected void resetAttackCooldown() {
        if (golem.getFastAttack()) {
            ticksTilNextFastAttack = adjustedTickDelay(fastAttackDelay * 2);
        } else ticksTilNextAttack = adjustedTickDelay(attackDelay * 2);
    }
    protected boolean isTimeToStartAttackAnimation() {
        if (golem.getAoeTimer() <= 0) {
            return false;
        } else {
            if (golem.getFastAttack()) {
                return ticksTilNextFastAttack <= fastAttackDelay;
            } else return ticksTilNextAttack <= attackDelay;
        }
    }
    protected boolean isTimeToAttack() {
        if (golem.getFastAttack()) {
            return ticksTilNextFastAttack <= 0;
        } else return ticksTilNextAttack <= 0;
    }
    protected int getTicksUntilNextAttack() {
        if (golem.getFastAttack()) {
            return ticksTilNextFastAttack;
        } else return ticksTilNextAttack;
    }
    protected void performAttack(LivingEntity enemy) {
        resetAttackCooldown();
        mob.swing(InteractionHand.MAIN_HAND);
        mob.doHurtTarget(enemy);
    }

    protected boolean initiateCritAttack() {
        return golem.fallDistance > 0.0F
                && !golem.onGround() && !golem.onClimbable()
                && !golem.isInWater() && !golem.hasEffect(MobEffects.BLINDNESS)
                && !golem.isPassenger();
    }
    protected boolean isTimeToCritAttack() {
        return false;
    }

    protected void resetAoeAttackCooldown() {
        ticksTilNextAoeAttack = adjustedTickDelay(aoeAttackDelay * 2);
        golem.setAoeTimer(golem.aoeAnimTimeout);
    }
    protected boolean isTimeToStartAoeAttackAnimation() {
        return ticksTilNextAoeAttack <= aoeAttackDelay && golem.getAoeTimer() <= 0;
    }
    protected boolean isTimeToAoeAttack() {
        return ticksTilNextAoeAttack <= 0 && golem.getAoeTimer() <= 0;
    }
    protected void performAoeAttack(LivingEntity enemy) {
        groundAttack(enemy);
        resetAoeAttackCooldown();
        golem.setAttackType(2);
        mob.swing(InteractionHand.MAIN_HAND);
        mob.getNavigation().stop();
        mob.doHurtTarget(enemy);
    }

    @Override
    public void tick() {
        super.tick();
        if (!golem.isChassisCompromised()) {
            if (golem.getAoeTimer() > 20) {
                if (shouldCountTillNextAttack) {
                    if (golem.getFastAttack())
                        ticksTilNextFastAttack = Math.max(ticksTilNextFastAttack - 1, 0);
                    else ticksTilNextAttack = Math.max(ticksTilNextAttack - 1, 0);
                }
            } else ticksTilNextAoeAttack = Math.max(ticksTilNextAoeAttack - 1,0);
        }
    }

    @Override
    public void stop() {
        super.stop();
        golem.setAttacking(false);
        golem.setAoeAttacking(false);
    }

    @Override
    public boolean canUse() {
        return !golem.isChassisCompromised() && super.canUse();
    }

    private void groundAttack(LivingEntity livingEntity) {
        if (golem.onGround()) {
            golem.playSound(SoundEvents.GENERIC_EXPLODE, 1.4F, 1.4F);
            golem.playSound(SoundEvents.IRON_GOLEM_ATTACK, 1.0F, 1.0F);
            for (Entity entity : golem.level().getEntitiesOfClass(LivingEntity.class, golem.getBoundingBox().inflate(6.0D), aoeFilter)) {
                if (entity instanceof Enemy || entity == golem.getTarget() || (livingEntity instanceof Mob target && (target.getTarget() == mob))) {
                    if (entity.onGround()) {
                        boolean flag = entity.hurt(golem.damageSources().mobAttack(golem),
                                (float) golem.getAttributeValue(Attributes.ATTACK_DAMAGE) * 0.75F);
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
        double d0 = entity.getX() - golem.getX();
        double d1 = entity.getZ() - golem.getZ();
        double d2 = Math.max(d0 * d0 + d1 * d1, 0.001D);
        entity.push(d0 / d2 * 1.25D, 0.2D, d1 / d2 * 1.25D);
    }
}