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
public class InitialBurstParticle extends HugeExplosionParticle {
    private final float rotSpeed;
    private final SpriteSet spritesSet;

    public InitialBurstParticle(ClientLevel pLevel, double pX, double pY, double pZ, double pQuadSizeMultiplier, SpriteSet sprites) {
        super(pLevel, pX, pY, pZ, pQuadSizeMultiplier, sprites);
        spritesSet = sprites;
        lifetime = 20;
        quadSize = 3F;
        setSpriteFromAge(sprites);
        rotSpeed = 0.05F + ((float) Math.min(0.05F, Math.random() * 0.05F));
        roll = (float)Math.random() * ((float)Math.PI * 0.5F);
    }

    @Override
    public void tick() {
        super.tick();
        setSpriteFromAge(spritesSet);
//        alpha -= 0.05F;
        oRoll = roll;
        roll += (float)Math.PI * rotSpeed * 2.0F;
    }

    @OnlyIn(Dist.CLIENT)
        public record Provider(SpriteSet sprites) implements ParticleProvider<SimpleParticleType> {

        public Particle createParticle(@NotNull SimpleParticleType pType, @NotNull ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
                InitialBurstParticle particle = new InitialBurstParticle(pLevel, pX, pY, pZ, pXSpeed, sprites);
                particle.rCol = 1;
                particle.gCol = 1;
                particle.bCol = 1;
                return particle;
            }
        }
}
