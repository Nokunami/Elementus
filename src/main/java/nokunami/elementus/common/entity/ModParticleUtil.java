package nokunami.elementus.common.entity;

import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.function.Supplier;

public class ModParticleUtil {

    public static void spawnParticlesOnEntity(Level pLevel, Position pPos, ParticleOptions pParticle, Entity entity, IntProvider pCount) {
        for(Direction direction : Direction.values()) {
            spawnParticles(pLevel, pPos, pParticle, pCount, direction, entity, () -> getRandomSpeedRanges(pLevel.random), 0.55D);
        }
    }

    public static void spawnWideParticlesOnEntity(Level pLevel, Position pPos, ParticleOptions pParticle, Entity entity, IntProvider pCount) {
        for(Direction direction : Direction.values()) {
            spawnWideParticles(pLevel, pPos, pParticle, pCount, direction, entity, () -> getRandomSpeedRanges(pLevel.random), 0.55D);
        }
    }

    public static void spawnParticles(Level pLevel, Position pPos, ParticleOptions pParticle, IntProvider pCount, Direction pDirection, Entity entity, Supplier<Vec3> pSpeedSupplier, double p_216325_) {
        int i = pCount.sample(pLevel.random);

        for(int j = 0; j < i; ++j) {
            randomParticleGenerator(pLevel, pPos, pDirection, entity, pParticle, pSpeedSupplier.get(), p_216325_);
        }
    }

    public static void spawnWideParticles(Level pLevel, Position pPos, ParticleOptions pParticle, IntProvider pCount, Direction pDirection, Entity entity, Supplier<Vec3> pSpeedSupplier, double p_216325_) {
        int i = pCount.sample(pLevel.random);

        for(int j = 0; j < i; ++j) {
            wideRandomParticleGenerator(pLevel, pPos, pDirection, entity, pParticle, pSpeedSupplier.get(), p_216325_);
        }
    }

    private static Vec3 getRandomSpeedRanges(RandomSource pRandom) {
        return new Vec3(Mth.nextDouble(pRandom, -0.5D, 0.5D), Mth.nextDouble(pRandom, -0.5D, 0.5D), Mth.nextDouble(pRandom, -0.5D, 0.5D));
    }

    private static void randomParticleGenerator(Level level, Position blockPos, Direction direction, Entity entity, ParticleOptions particle, Vec3 vec3, double d) {
        int dX = direction.getStepX();
        int dY = direction.getStepY();
        int dZ = direction.getStepZ();
        double vX = blockPos.x() + (dX == 0 ? Mth.nextDouble(level.random, -entity.getBbWidth(), entity.getBbWidth()) : (double)dX * d);
        double vY = blockPos.y() + (dY == 0 ? Mth.nextDouble(level.random, -entity.getBbHeight()/4, entity.getBbHeight()) : (double)dY * d);
        double vZ = blockPos.z() + (dZ == 0 ? Mth.nextDouble(level.random, -entity.getBbWidth(), entity.getBbWidth()) : (double)dZ * d);
        double x = dX == 0 ? vec3.x() : 0.0D;
        double y = dY == 0 ? vec3.y() : 0.0D;
        double z = dZ == 0 ? vec3.z() : 0.0D;
        level.addParticle(particle, vX, vY, vZ, x, y, z);
    }

    private static void wideRandomParticleGenerator(Level level, Position blockPos, Direction direction, Entity entity, ParticleOptions particle, Vec3 vec3, double d) {
        int dX = direction.getStepX();
        int dY = direction.getStepY();
        int dZ = direction.getStepZ();
        double vX = blockPos.x() + (dX == 0 ? Mth.nextDouble(level.random, -entity.getBbWidth() * 2, entity.getBbWidth() * 2) : (double)dX * d);
        double vY = blockPos.y() + (dY == 0 ? Mth.nextDouble(level.random, -entity.getBbHeight()/4, entity.getBbHeight()) : (double)dY * d);
        double vZ = blockPos.z() + (dZ == 0 ? Mth.nextDouble(level.random, -entity.getBbWidth() * 2, entity.getBbWidth() * 2) : (double)dZ * d);
        double x = dX == 0 ? vec3.x() : 0.0D;
        double y = dY == 0 ? vec3.y() : 0.0D;
        double z = dZ == 0 ? vec3.z() : 0.0D;
        level.addParticle(particle, vX, vY, vZ, x, y, z);
    }
}
