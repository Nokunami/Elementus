package net.nokunami.elementus.api.util;

import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ParticleUtil {
    Level level;
    ParticleOptions particleData;
    Vec3 pos;
    boolean alwaysRender = false;

    public static <T extends ParticleOptions> ParticleUtil particleUtil(Level level, RegistryObject<T> data, Vec3 pos) { return new ParticleUtil(level, data.get(), pos); }
    public static ParticleUtil particleUtil(Level level, Supplier<ParticleOptions> data, Vec3 pos) { return new ParticleUtil(level, data.get(), pos); }
    public static ParticleUtil particleUtil(Level level, ParticleOptions data, Vec3 pos) { return new ParticleUtil(level, data, pos); }

    public ParticleUtil(Level level, ParticleOptions data, Vec3 position) {
        this.level = level;
        particleData = data;
        pos = position;
    }

    public ParticleUtil alwaysRender() { alwaysRender = true; return this; }
    public ParticleUtil repos(Vec3 repos) { return repos(repos.x, repos.y, repos.z); }
    public ParticleUtil repos(double x, double y, double z) { pos = new Vec3(x, y, z); return this; }

    public void addParticle() { addParticle(Vec3.ZERO); }
    public void addParticle(Vec3 speed) { addParticle(speed.x, speed.y, speed.z); }
    public void addParticle(double xo, double yo, double zo) { level.addParticle(particleData, alwaysRender, pos.x, pos.y, pos.z, xo, yo, zo); }

    public void addAlwaysVisibleParticle() { addAlwaysVisibleParticle(Vec3.ZERO); }
    public void addAlwaysVisibleParticle(Vec3 speed) { level.addAlwaysVisibleParticle(particleData, alwaysRender, pos.x, pos.y, pos.z, speed.x, speed.y, speed.z); }

    public void sendParticle() { sendParticle(0, 0, 0); }
    public void sendParticle(Vec3 offset) { sendParticle(offset.x, offset.y, offset.z); }
    public void sendParticle(double xo, double yo, double zo) { sendParticle(0, xo, yo, zo); }
    public void sendParticle(int count, double xo, double yo, double zo) { sendParticle(count, xo, yo, zo, 1);}
    public void sendParticle(int count, double xo, double yo, double zo, double speed) { if (level instanceof ServerLevel serverLevel) serverLevel.sendParticles(particleData, pos.x, pos.y, pos.z, count, xo, yo, zo, speed); }

    public void spread(boolean serverSide, Entity entity, IntProvider intProvider) {
        spread(serverSide, entity, intProvider, () -> getRandomSpeedRanges(level.random), 0.55, 1, 0.25, 1);
    }
    public void spread(boolean serverSide, Entity entity, IntProvider intProvider, double random) {
        spread(serverSide, entity, intProvider, () -> getRandomSpeedRanges(level.random), random, 1, 0.25, 1);
    }
    public void spread(boolean serverSide, Entity entity, IntProvider intProvider, double random, double multi) {
        spread(serverSide, entity, intProvider, () -> getRandomSpeedRanges(level.random), random, multi, multi, multi);
    }
    public void spread(boolean serverSide, Entity entity, IntProvider intProvider, Supplier<Vec3> randomSpeed, double random, double multiX, double multiY, double multiZ) {
        for (Direction direction : Direction.values()) {
            int i = intProvider.sample(level.random);
            for(int j = 0; j < i; ++j) {
                int dX = direction.getStepX();
                int dY = direction.getStepY();
                int dZ = direction.getStepZ();
                double vX = pos.x() + (dX == 0 ? Mth.nextDouble(level.random, -entity.getBbWidth() * multiX, entity.getBbWidth() * 2) : (double)dX * random);
                double vY = pos.y() + (dY == 0 ? Mth.nextDouble(level.random, -entity.getBbHeight() * multiY, entity.getBbHeight()) : (double)dY * random);
                double vZ = pos.z() + (dZ == 0 ? Mth.nextDouble(level.random, -entity.getBbWidth() * multiZ, entity.getBbWidth() * 2) : (double)dZ * random);
                double x = dX == 0 ? randomSpeed.get().x() : 0;
                double z = dZ == 0 ? randomSpeed.get().z() : 0;
                double y = dY == 0 ? randomSpeed.get().y() : 0;
                if (serverSide && level instanceof ServerLevel serverLevel) serverLevel.sendParticles(particleData, vX, vY, vZ, 0, x, y, z, 1);
                else level.addParticle(particleData, vX, vY, vZ, x, y, z);
            }
        }
    }

//    public static void particleSpawner(Level level, Position pos, ParticleOptions particle, Entity entity, IntProvider intProvider) {
//        particleSpawner(level, pos, particle, entity, intProvider, () -> getRandomSpeedRanges(level.random), 0.55, 1, 0.25, 1);
//    }
//    public static void particleSpawner(Level level, Position pos, ParticleOptions particle, Entity entity, IntProvider intProvider, double random) {
//        particleSpawner(level, pos, particle, entity, intProvider, () -> getRandomSpeedRanges(level.random), random, 1, 0.25, 1);
//    }
//    public static void particleSpawner(Level level, Position pos, ParticleOptions particle, Entity entity, IntProvider intProvider, double random, double multi) {
//        particleSpawner(level, pos, particle, entity, intProvider, () -> getRandomSpeedRanges(level.random), random, multi, multi, multi);
//    }
//    public static void particleSpawner(Level level, Position pos, ParticleOptions particle, Entity entity, IntProvider intProvider, Supplier<Vec3> randomSpeed, double random, double multiX, double multiY, double multiZ) {
//        for(Direction direction : Direction.values()) spawnParticles(level, pos, particle, intProvider, direction, entity, randomSpeed, random, multiX, multiY, multiZ);
//    }

//    public static void spawnParticles(Level level, Position pos, ParticleOptions particle, IntProvider intProvider, Direction direction, Entity entity, Supplier<Vec3> speed, double random, double multiX, double multiY, double multiZ) {
//        int i = intProvider.sample(level.random);
//        for(int j = 0; j < i; ++j) randomParticleGen(level, pos, direction, entity, particle, speed.get(), random, multiX, multiY, multiZ);
//    }

    private static Vec3 getRandomSpeedRanges(RandomSource pRandom) {
        return new Vec3(Mth.nextDouble(pRandom, -0.5D, 0.5D), Mth.nextDouble(pRandom, -0.5D, 0.5D), Mth.nextDouble(pRandom, -0.5D, 0.5D));
    }

//    private static void randomParticleGen(Level level, Position blockPos, Direction direction, Entity entity, ParticleOptions particle, Vec3 vec3, double random, double multiX, double multiY, double multiZ) {
//        int dX = direction.getStepX();
//        int dY = direction.getStepY();
//        int dZ = direction.getStepZ();
//        double vX = blockPos.x() + (dX == 0 ? Mth.nextDouble(level.random, -entity.getBbWidth() * multiX, entity.getBbWidth() * 2) : (double)dX * random);
//        double vY = blockPos.y() + (dY == 0 ? Mth.nextDouble(level.random, -entity.getBbHeight() * multiY, entity.getBbHeight()) : (double)dY * random);
//        double vZ = blockPos.z() + (dZ == 0 ? Mth.nextDouble(level.random, -entity.getBbWidth() * multiZ, entity.getBbWidth() * 2) : (double)dZ * random);
//        double x = dX == 0 ? vec3.x() : 0;
//        double y = dY == 0 ? vec3.y() : 0;
//        double z = dZ == 0 ? vec3.z() : 0;
//        level.addParticle(particle, vX, vY, vZ, x, y, z);
//    }
}
