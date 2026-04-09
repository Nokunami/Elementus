package net.nokunami.elementus.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.NoRenderParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.nokunami.elementus.common.registry.EParticles;
import org.jetbrains.annotations.NotNull;

public class BurstEmitterParticle extends NoRenderParticle {
    private int life;
    private int particleTimer;
    private int altTimer;
    ParticleOptions boomParticle;
    ParticleOptions burstParticle;

    protected BurstEmitterParticle(ClientLevel pLevel, double pX, double pY, double pZ, ParticleOptions particle1, ParticleOptions particle2) {
        super(pLevel, pX, pY, pZ);
        boomParticle = particle1;
        burstParticle = particle2;
    }

    public void tick() {
        int lifeTime = 10;

        if (particleTimer < 1) {
            ++particleTimer;
        } else particleTimer = 0;
        if (particleTimer == 1) {
            double d0 = x + (random.nextDouble() - random.nextDouble()) * 2;
            double d1 = y + (random.nextDouble() - random.nextDouble()) * 2;
            double d2 = z + (random.nextDouble() - random.nextDouble()) * 2;
            if (altTimer < 1) {
                level.addParticle(boomParticle, d0, d1, d2, (float) life / (float) lifeTime, 0, 0);
                ++altTimer;
            } else {
                level.addParticle(burstParticle, d0, d1, d2, (float) life / (float) lifeTime, 0, 0);
                --altTimer;
            }
        }

        ++life;
        if (life == lifeTime) {
            remove();
        }

    }

    public static class normalProvider implements ParticleProvider<SimpleParticleType> {
        public Particle createParticle(@NotNull SimpleParticleType pType, @NotNull ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
            return new BurstEmitterParticle(pLevel, pX, pY, pZ, ParticleTypes.SONIC_BOOM, EParticles.SONIC_BURST.get());
        }
    }

    public static class sacrificeProvider implements ParticleProvider<SimpleParticleType> {
        public Particle createParticle(@NotNull SimpleParticleType pType, @NotNull ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
            return new BurstEmitterParticle(pLevel, pX, pY, pZ, EParticles.SONIC_BOOM_SACRIFICE.get(), EParticles.SONIC_BURST_SACRIFICE.get());
        }
    }
}
