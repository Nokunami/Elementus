package net.nokunami.elementus.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.HugeExplosionParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class AnthektiteSlashImpactParticle extends HugeExplosionParticle {
    private float rotSpeed;
    private final SpriteSet sprites;
    private static final int totalLifetime = 12;

    public AnthektiteSlashImpactParticle(ClientLevel pLevel, double pX, double pY, double pZ, double pQuadSizeMultiplier, SpriteSet pSprites) {
        super(pLevel, pX, pY, pZ, pQuadSizeMultiplier, pSprites);
        this.sprites = pSprites;
        this.lifetime = totalLifetime;
        this.quadSize = 1.5F;
        this.setSpriteFromAge(pSprites);
        this.rotSpeed = 40;
    }

    @Override
    public void tick() {
        super.tick();
        this.setSpriteFromAge(this.sprites);
        this.oRoll = this.roll;
        if (this.age >= totalLifetime/2) {
            this.rotSpeed = this.rotSpeed - this.rotSpeed * 0.5F;
        } else this.rotSpeed = this.rotSpeed - this.rotSpeed * 0.1F;
        this.roll += (float) (this.rotSpeed * (Math.PI * 0.01));
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet pSprites) {
            this.sprites = pSprites;
        }

        public Particle createParticle(@NotNull SimpleParticleType pType, @NotNull ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
            return new AnthektiteSlashImpactParticle(pLevel, pX, pY, pZ, pXSpeed, this.sprites);
        }
    }
}
