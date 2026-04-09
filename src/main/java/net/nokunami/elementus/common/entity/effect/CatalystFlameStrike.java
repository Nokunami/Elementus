package net.nokunami.elementus.common.entity.effect;

import com.github.L_Ender.cataclysm.entity.effect.Flame_Strike_Entity;
import com.github.L_Ender.cataclysm.init.ModEntities;
import com.github.L_Ender.cataclysm.util.CustomExplosion.IgnisExplosion;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.nokunami.elementus.common.registry.tempCompat.CompatEntityTypes;

public class CatalystFlameStrike extends Flame_Strike_Entity {
    int warmupDelay;

    public CatalystFlameStrike(EntityType<? extends Flame_Strike_Entity> type, Level level) {
        super(type, level);
    }

    public CatalystFlameStrike(Level level, double x, double y, double z, float p_i47276_8_, int duration, int wait, int delay, float radius, float damage, float Hpdamage, boolean soul, LivingEntity casterIn) {
        this(CompatEntityTypes.CataclysmEntities.CATALYST_FLAME_STRIKE.get(), level);
        setOwner(casterIn);
        setDuration(duration);
        setWaitTime(wait);
//        warmupDelayTicks = delay;
        warmupDelay = delay;
        setRadius(radius);
        setDamage(damage);
        setHpDamage(Hpdamage);
        setSoul(soul);
        setYRot(p_i47276_8_ * (180F / (float)Math.PI));
        setPos(x, y, z);
    }

    @Override
    public void tick() {
        boolean flag = isWaiting();
        float f = getRadius();
        if (level().isClientSide) {
            if (flag && random.nextBoolean()) return;

            ParticleOptions particleoptions = isSoul() ? ParticleTypes.SOUL_FIRE_FLAME : ParticleTypes.FLAME;
            float f1 = flag ? 0.2F : f;
            double spread = (Math.PI * 2D);
            int arcLen = Mth.ceil((double) getRadius() * spread);
            if (!flag) {
                if (tickCount % 2 == 0) {
                    for(int j = 0; j < arcLen; ++j) {
                        float f2 = random.nextFloat() * ((float)Math.PI * 2F);
                        double d0 = getX() + (Mth.cos(f2) * f1) * 0.9;
                        double d2 = getY();
                        double d4 = getZ() + (Mth.sin(f2) * f1) * 0.9;
                        level().addParticle(particleoptions, d0, d2, d4, random.nextGaussian() * 0.07, 0.125 * getRadius() + 0.4, random.nextGaussian() * 0.07);
                    }
                }

                if (random.nextInt(24) == 0) {
                    level().playLocalSound(getX() + 0.5, getY() + 0.5, getZ() + 0.5, SoundEvents.BLAZE_BURN, getSoundSource(), 1 + random.nextFloat(), random.nextFloat() * 0.7F + 0.3F, false);
                }
            }
        } else {
            if (tickCount >= getWaitTime() + getDuration() + warmupDelay) {
                if (getRadius() > 0.0F) {
                    setRadius(getRadius() - 0.1F);
                } else {
                    int explosionradius = getOwner() instanceof Player ? 1 : 2;
                    IgnisExplosion explosion = new IgnisExplosion(level(), getOwner(), null, null, getX(), getY(), getZ(), (float) explosionradius, false, Explosion.BlockInteraction.KEEP);
                    explosion.explode();
                    explosion.finalizeExplosion(0, 0);

                    level().broadcastEntityEvent(this, (byte) 4);
                    discard();
                }
            }

            if (tickCount >= warmupDelay) setSee(true);

            boolean flag1 = tickCount < getWaitTime() + warmupDelay;
            if (flag != flag1) setWaiting(flag1);

            if (flag1) return;
        }
        if (!flag && tickCount % 5 == 0) for(LivingEntity entity : level().getEntitiesOfClass(LivingEntity.class, getBoundingBox())) damage(entity);

        super.tick();
    }
}
