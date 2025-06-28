package net.nokunami.elementus.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.nokunami.elementus.common.registry.ModParticleTypes;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class SonicBurstEmitterParticle extends NoRenderParticle {
    private int life;
    private int particleTimer;
    private int particleType;

    protected SonicBurstEmitterParticle(ClientLevel pLevel, double pX, double pY, double pZ) {
        super(pLevel, pX, pY, pZ);
    }

    public void tick() {
        int lifeTime = 10;

        if (particleTimer < 1) {
            ++particleTimer;
        } else particleTimer = 0;
        if (particleTimer == 1) {
            double d0 = this.x + (this.random.nextDouble() - this.random.nextDouble()) * 2.0D;
            double d1 = this.y + (this.random.nextDouble() - this.random.nextDouble()) * 2.0D;
            double d2 = this.z + (this.random.nextDouble() - this.random.nextDouble()) * 2.0D;
            if (particleType < 1) {
                this.level.addParticle(ParticleTypes.SONIC_BOOM, d0, d1, d2, (float)this.life / (float) lifeTime, 0.0D, 0.0D);
                ++particleType;
            } else {
                this.level.addParticle(ModParticleTypes.SONIC_BURST.get(), d0, d1, d2, (float)this.life / (float) lifeTime, 0.0D, 0.0D);
                --particleType;
            }
        }

        ++this.life;
        if (this.life == lifeTime) {
            this.remove();
        }

    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        public Particle createParticle(@NotNull SimpleParticleType pType, @NotNull ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
            return new SonicBurstEmitterParticle(pLevel, pX, pY, pZ);
        }
    }
}
