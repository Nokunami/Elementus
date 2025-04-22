package net.nokunami.elementus.common.entity.ai.goal.steelGolem;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Enemy;
import net.nokunami.elementus.common.entity.living.SteelGolem;
import org.jetbrains.annotations.NotNull;

import java.util.EnumSet;

public class SteelGolemAttackGoal extends MeleeAttackGoal {
    private final SteelGolem steelGolem;
    protected final int attackDelay = 10;
    protected int ticksUntilNextAttack = 20;
    protected int ticksUntilAoeAttack;
    protected boolean shouldCountTillNextAttack = false;
    protected boolean brokenChassis;
    protected boolean isAttacking;
    protected boolean isAoeAttacking;

    public SteelGolemAttackGoal(SteelGolem pMob, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
        super(pMob, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        this.steelGolem = pMob;
        this.ticksUntilAoeAttack = pMob.getAoeAttackTick();
        this.brokenChassis = pMob.isChassisBroken();
        this.isAttacking = pMob.isAttacking();
        this.isAoeAttacking = pMob.isAoeAttacking();
    }

    @Override
    protected void checkAndPerformAttack(@NotNull LivingEntity enemy, double distToEnemySqr) {
        if (isEnemyWithinAttackDistance(enemy, distToEnemySqr)) {
            shouldCountTillNextAttack = true;

            if(isTimeToStartAttackAnimation()) {
                steelGolem.setAttacking(true);
            }
            if(isTimeToAttack()) {
                this.mob.getLookControl().setLookAt(enemy.getX(), enemy.getEyeY(), enemy.getZ());
                performAttack(enemy);
            }

            if(isTimeToStartAoeAttackAnimation()) {
                steelGolem.setAoeAttacking(true);
            }
            if(isTimeToAoeAttack()) {
                this.mob.getLookControl().setLookAt(enemy.getX(), enemy.getEyeY(), enemy.getZ());
                performAoeAttack(enemy);
            }

        } else {
            resetAttackCooldown();
            shouldCountTillNextAttack = false;
            steelGolem.setAttacking(false);
            steelGolem.attackAnimTimeout = 0;
        }
    }

    boolean isEnemyWithinAttackDistance(LivingEntity enemy, double distToEnemySqr) {
        return distToEnemySqr <= this.getAttackReachSqr(enemy) * 2.25;
    }

    protected void resetAttackCooldown() {
        this.ticksUntilNextAttack = this.adjustedTickDelay(attackDelay * 2);
    }

    protected boolean isTimeToAttack() {
        return this.ticksUntilNextAttack <= 0;
    }

    protected boolean isTimeToStartAttackAnimation() {
        return this.ticksUntilNextAttack <= attackDelay;
    }

    protected int getTicksUntilNextAttack() {
        return this.ticksUntilNextAttack;
    }

    protected void performAttack(LivingEntity enemy) {
        this.resetAttackCooldown();
        this.mob.swing(InteractionHand.MAIN_HAND);
        this.mob.doHurtTarget(enemy);
    }

    protected void resetAoeAttackCooldown() {
        this.ticksUntilNextAttack = this.adjustedTickDelay(attackDelay * 2);
    }

    protected boolean isTimeToAoeAttack() {
        return this.ticksUntilAoeAttack <= 0;
    }

    protected boolean isTimeToStartAoeAttackAnimation() {
        return this.ticksUntilAoeAttack <= 20;
    }

    protected void performAoeAttack(LivingEntity enemy) {
        this.resetAoeAttackCooldown();
        this.mob.swing(InteractionHand.MAIN_HAND);
        this.groundAttack(enemy);
    }

    @Override
    public void tick() {
        super.tick();
        if(shouldCountTillNextAttack && !isTimeToStartAoeAttackAnimation()) {
            this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);
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
        return !brokenChassis && super.canUse();
    }

    private void groundAttack(LivingEntity livingEntity) {
        if (this.steelGolem.onGround()) {
            this.steelGolem.playSound(SoundEvents.GENERIC_EXPLODE, 1.4F, 1.4F);
            this.steelGolem.playSound(SoundEvents.IRON_GOLEM_ATTACK, 1.0F, 1.0F);
            for (Entity entity : this.steelGolem.level().getEntitiesOfClass(LivingEntity.class, this.steelGolem.getBoundingBox().inflate(8.0D)/*, iMobTarget*/)) {
                if (!(entity instanceof Creeper) && (entity instanceof Enemy || (livingEntity != null && livingEntity == entity))) {
                    if (entity.onGround()) {
                        boolean flag = entity.hurt(this.steelGolem.damageSources().mobAttack(this.steelGolem), (float) this.steelGolem.getAttributeValue(Attributes.ATTACK_DAMAGE) * 0.75F);

                        if (flag) {
                            entity.getDeltaMovement().add(0.0D, 0.4000000059604645D, 0.0D);

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
        entity.push(d0 / d2 * 4.0D, 0.2D, d1 / d2 * 4.0D);
    }
}