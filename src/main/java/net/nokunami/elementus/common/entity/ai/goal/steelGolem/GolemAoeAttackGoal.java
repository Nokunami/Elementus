package net.nokunami.elementus.common.entity.ai.goal.steelGolem;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Enemy;

import java.util.EnumSet;
import java.util.function.Predicate;

public class GolemAoeAttackGoal<T extends Mob & GolemAoeAttackGoal.ISwingAttack> extends Goal  {
    private static final Predicate<Entity> iMobTarget = (Entity -> (!(Entity instanceof IronGolem)));

    private final T mobEntity;

    private int attackingTimer;

    private int cooldownTimer = -1;

    public GolemAoeAttackGoal(T mobEntity) {
        this.mobEntity = mobEntity;
        setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (this.cooldownTimer > 0) {
            this.cooldownTimer--;
        }
        LivingEntity livingEntity = this.mobEntity.getTarget();
        if (livingEntity != null && livingEntity.isAlive() &&
                this.cooldownTimer <= 0) {
            if (livingEntity.distanceToSqr(this.mobEntity) < 32.0D && livingEntity.onGround()) {
                this.cooldownTimer = 100 + this.mobEntity.getRandom().nextInt(100);
                return true;
            }

        }
        return false;
    }

    @Override
    public boolean canContinueToUse() {
        return this.attackingTimer < 20 * 1.25F;
    }

    public void start() {
        super.start();
        this.mobEntity.setSwingAttack(true);
        this.attackingTimer = 0;
        this.mobEntity.level().broadcastEntityEvent(this.mobEntity, (byte) 64);
    }

    public void stop() {
        super.stop();
        this.mobEntity.setSwingAttack(false);
    }

    public void tick() {
        super.tick();
        this.attackingTimer++;
        if (this.attackingTimer == 20 * 0.25F) {
            groundAttack();
        }
    }

    private void groundAttack() {
        LivingEntity livingEntity = this.mobEntity.getTarget();
        if (this.mobEntity.onGround()) {
            this.mobEntity.playSound(SoundEvents.GENERIC_EXPLODE, 1.4F, 1.4F);
            this.mobEntity.playSound(SoundEvents.IRON_GOLEM_ATTACK, 1.0F, 1.0F);
            for (Entity entity : this.mobEntity.level().getEntitiesOfClass(LivingEntity.class, this.mobEntity.getBoundingBox().inflate(8.0D)/*, iMobTarget*/)) {
                if (!(entity instanceof Creeper) && (entity instanceof Enemy || (livingEntity != null && livingEntity == entity))) {
                    if (entity.onGround()) {
                        boolean flag = entity.hurt(this.mobEntity.damageSources().mobAttack(this.mobEntity), (float) this.mobEntity.getAttributeValue(Attributes.ATTACK_DAMAGE) * 0.75F);

                        if (flag) {
                            entity.getDeltaMovement().add(0.0D, 0.4000000059604645D, 0.0D);

                        }
                    }
                    launch(entity);
                }
            }
        }
    }

    private void launch(Entity p_33340_) {
        double d0 = p_33340_.getX() - this.mobEntity.getX();
        double d1 = p_33340_.getZ() - this.mobEntity.getZ();
        double d2 = Math.max(d0 * d0 + d1 * d1, 0.001D);
        p_33340_.push(d0 / d2 * 4.0D, 0.2D, d1 / d2 * 4.0D);
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    public interface ISwingAttack {
        void setSwingAttack(boolean paramBoolean);

        boolean isSwingAttack();

        AnimationState slamAnimationState();
    }

}
