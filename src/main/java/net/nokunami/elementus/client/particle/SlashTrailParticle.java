package net.nokunami.elementus.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class SlashTrailParticle extends HugeExplosionParticle {
    private float rotSpeed;
    private final SpriteSet sprites;
    private static final int totalLifetime = 20;

    public SlashTrailParticle(ClientLevel pLevel, double pX, double pY, double pZ, double pQuadSizeMultiplier, SpriteSet pSprites) {
        super(pLevel, pX, pY, pZ, pQuadSizeMultiplier, pSprites);
        this.sprites = pSprites;
        this.lifetime = totalLifetime;
        this.quadSize = 0.5F;
        this.setSpriteFromAge(pSprites);
        this.rotSpeed = 40;
    }

    @Override
    public void tick() {
        super.tick();
        this.setSpriteFromAge(this.sprites);
        this.oRoll = this.roll;
//        if (this.age >= totalLifetime/2) {
//            rotAlphaSize(-this.rotSpeed * 0.1F, -0.03F, -0.005F);
//        } else if (this.age >= totalLifetime/4) {
//            rotAlphaSize(-this.rotSpeed * 0.25F, -0.06F, 0.015F);
//        } else {
//            rotAlphaSize(-this.rotSpeed * 0.05F, -0.015F, 0.075F);
//        }
        rotSpeed -= rotSpeed * 0.01F;
        alpha -= 0.05F;

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
        public record Provider(SpriteSet sprites) implements ParticleProvider<SimpleParticleType> {

        public Particle createParticle(@NotNull SimpleParticleType pType, @NotNull ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
                return new SlashTrailParticle(pLevel, pX, pY, pZ, pXSpeed, this.sprites);
            }
        }
}
