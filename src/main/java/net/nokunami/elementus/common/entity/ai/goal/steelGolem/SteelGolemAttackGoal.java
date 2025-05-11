package net.nokunami.elementus.common.entity.ai.goal.steelGolem;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Enemy;
import net.nokunami.elementus.common.entity.living.SteelGolem;
import org.jetbrains.annotations.NotNull;

import java.util.EnumSet;

import static net.nokunami.elementus.common.entity.ModParticleUtil.spawnWideParticlesOnEntity;

public class SteelGolemAttackGoal extends MeleeAttackGoal {
    private final SteelGolem steelGolem;
    protected final int attackDelay = 10;
    protected int ticksTilNextAttack = 20;
    protected final int aoeAttackDelay = 10;
    protected int ticksTilNextAoeAttack = 20;
    protected boolean shouldCountTillNextAttack = false;
    protected boolean brokenChassis;

    public SteelGolemAttackGoal(SteelGolem pMob, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
        super(pMob, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        this.steelGolem = pMob;
        this.brokenChassis = pMob.isChassisBroken();
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
            steelGolem.setAoeAttacking(false);
            steelGolem.attackAnimTimeout = 0;
            steelGolem.aoeAttackAnimTimeout = 0;
        }
    }

    boolean isEnemyWithinAttackDistance(LivingEntity enemy, double distToEnemySqr) {
        return distToEnemySqr <= this.getAttackReachSqr(enemy) * 2.25;
    }

    protected void resetAttackCooldown() {
        this.ticksTilNextAttack = this.adjustedTickDelay(attackDelay * 2);
    }

    protected boolean isTimeToStartAttackAnimation() {
        return this.ticksTilNextAttack <= attackDelay;
    }

    protected boolean isTimeToAttack() {
        return this.ticksTilNextAttack <= 0;
    }

    protected int getTicksUntilNextAttack() {
        return this.ticksTilNextAttack;
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
        this.mob.swing(InteractionHand.MAIN_HAND);
        this.mob.getNavigation().stop();
        this.mob.doHurtTarget(enemy);
    }

    @Override
    public void tick() {
        super.tick();
        if(shouldCountTillNextAttack && steelGolem.getAoeTimer() > 30) {
            this.ticksTilNextAttack = Math.max(this.ticksTilNextAttack - 1, 0);
        }
        if (steelGolem.getAoeTimer() <= 0) {
            this.ticksTilNextAoeAttack = Math.max(this.ticksTilNextAoeAttack - 1,0);
            resetAttackCooldown();
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
            for (Entity entity : this.steelGolem.level().getEntitiesOfClass(LivingEntity.class, this.steelGolem.getBoundingBox().inflate(6.0D))) {
//                if (!(entity instanceof Creeper) && (entity instanceof Enemy || (livingEntity != null && livingEntity == entity)) || !(steelGolem.getOwner() != null && entity.isAlliedTo(steelGolem.getOwner()))) {
                if (entity instanceof Enemy || entity == steelGolem.getTarget() || (livingEntity instanceof Mob target && (target.getTarget() == this.mob))/* || !(steelGolem.getOwner() != null && entity.isAlliedTo(steelGolem.getOwner())) || entity != this.steelGolem*/) {
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
        entity.push(d0 / d2 * 2.0D, 0.2D, d1 / d2 * 2.0D);
    }
}