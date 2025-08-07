package net.nokunami.elementus.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class RushTrailParticle extends HugeExplosionParticle {
    private float rotSpeed;
    private final SpriteSet sprites;
    private static final int totalLifetime = 20;

    public RushTrailParticle(ClientLevel pLevel, double pX, double pY, double pZ, double pQuadSizeMultiplier, SpriteSet pSprites) {
        super(pLevel, pX, pY, pZ, pQuadSizeMultiplier, pSprites);
        this.sprites = pSprites;
        this.lifetime = totalLifetime;
        this.quadSize = 1.5F;
        this.setSpriteFromAge(pSprites);
        this.rotSpeed = 60;
    }

    @Override
    public void tick() {
        super.tick();
        this.setSpriteFromAge(this.sprites);
        this.oRoll = this.roll;
        if (this.age >= totalLifetime * 0.75) {
            rotAlphaSize(-this.rotSpeed * 0.075F, -0.0075F, -0.005F);
        } else if (this.age >= totalLifetime * 0.5) {
            rotAlphaSize(-this.rotSpeed * 0.1F, -0.01F, 0.01F);
        } else if (this.age >= totalLifetime * 0.25) {
            rotAlphaSize(-this.rotSpeed * 0.3F, -0.015F, 0.015F);
        } else {
            rotAlphaSize(-this.rotSpeed * 0.05F, -0.005F, 0.075F);
        }

        this.roll += (float) (this.rotSpeed * (Math.PI * 0.01));
    }

    private void rotAlphaSize(float rotSpeed, float alpha, float size) {
        this.rotSpeed += rotSpeed;
        this.alpha += alpha;
        this.quadSize += size;
    }

    @Override
    public @NotNull ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet pSprites) {
            this.sprites = pSprites;
        }

        public Particle createParticle(@NotNull SimpleParticleType pType, @NotNull ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
            return new RushTrailParticle(pLevel, pX, pY, pZ, pXSpeed, this.sprites);
        }
    }
}
