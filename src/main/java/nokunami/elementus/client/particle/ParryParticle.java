package nokunami.elementus.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.HugeExplosionParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class ParryParticle extends HugeExplosionParticle {

    public ParryParticle(ClientLevel pLevel, double pX, double pY, double pZ, double pQuadSizeMultiplier, SpriteSet pSprites) {
        super(pLevel, pX, pY, pZ, pQuadSizeMultiplier, pSprites);
        this.lifetime = 7;
        this.quadSize = 0.75F;
        this.setSpriteFromAge(pSprites);
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet pSprites) {
            this.sprites = pSprites;
        }

        public Particle createParticle(@NotNull SimpleParticleType pType, @NotNull ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
            ParryParticle particle = new ParryParticle(pLevel, pX, pY, pZ, pXSpeed, this.sprites);
            particle.setAlpha(1);
            particle.rCol = 1;
            particle.gCol = 1;
            particle.bCol = 1;
            return particle;
        }
    }
}
