package net.nokunami.elementus.common.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.ConduitBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class CatalystCoreRangedUtil {
    public LivingEntity destroyTarget;
    public UUID destroyTargetUUID;

    public void updateDestroyTarget(Level level, BlockPos pos, BlockState state, List<BlockPos> postions, double range) {
        LivingEntity livingentity = this.destroyTarget;
        int i = postions.size();
        if (i < 42) {
            this.destroyTarget = null;
        } else if (this.destroyTarget == null && this.destroyTargetUUID != null) {
            this.destroyTarget = findDestroyTarget(level, pos, this.destroyTargetUUID, range);
            this.destroyTargetUUID = null;
        } else if (this.destroyTarget == null) {
            List<LivingEntity> list = level.getEntitiesOfClass(LivingEntity.class, getDestroyRangeAABB(pos, range),
                    (entity) -> entity instanceof Enemy && entity.isInWaterOrRain());
            if (!list.isEmpty()) {
                this.destroyTarget = list.get(level.random.nextInt(list.size()));
            }
        } else if (!this.destroyTarget.isAlive() || !pos.closerThan(this.destroyTarget.blockPosition(), 8.0D)) {
            this.destroyTarget = null;
        }

        if (this.destroyTarget != null) {
            level.playSound(null, this.destroyTarget.getX(), this.destroyTarget.getY(), this.destroyTarget.getZ(), SoundEvents.CONDUIT_ATTACK_TARGET, SoundSource.BLOCKS, 1.0F, 1.0F);
            this.destroyTarget.hurt(level.damageSources().magic(), 4.0F);
        }

        if (livingentity != this.destroyTarget) {
            level.sendBlockUpdated(pos, state, state, 2);
        }

    }

    public void updateClientTarget(Level level, BlockPos pos, double range) {
        if (this.destroyTargetUUID == null) {
            this.destroyTarget = null;
        } else if (this.destroyTarget == null || !this.destroyTarget.getUUID().equals(this.destroyTargetUUID)) {
            this.destroyTarget = findDestroyTarget(level, pos, this.destroyTargetUUID, range);
            if (this.destroyTarget == null) {
                this.destroyTargetUUID = null;
            }
        }

    }

    public static AABB getDestroyRangeAABB(BlockPos pos, double range) {
        int posX = pos.getX();
        int posY = pos.getY();
        int posZ = pos.getZ();
        return (new AABB(posX, posY, posZ, posX + 1, posY + 1, posZ + 1)).inflate(range);
    }

    @Nullable
    public static LivingEntity findDestroyTarget(Level level, BlockPos pos, UUID targetId, double range) {
        List<LivingEntity> list = level.getEntitiesOfClass(LivingEntity.class, getDestroyRangeAABB(pos, range),
                (p_289510_) -> p_289510_.getUUID().equals(targetId));
        return list.size() == 1 ? list.get(0) : null;
    }

    public static void animationTick(ParticleOptions particle, Level level, BlockPos pos, List<BlockPos> positions, @Nullable Entity entity, int tick) {
        RandomSource randomsource = level.random;
        double d0 = Mth.sin((float)(tick + 35) * 0.1F) / 2.0F + 0.5F;
        d0 = (d0 * d0 + d0) * (double)0.3F;
        Vec3 vec3 = new Vec3((double)pos.getX() + 0.5D, (double)pos.getY() + 1.5D + d0, (double)pos.getZ() + 0.5D);

        for(BlockPos blockpos : positions) {
            if (randomsource.nextInt(50) == 0) {
                BlockPos blockpos1 = blockpos.subtract(pos);
                float f = -0.5F + randomsource.nextFloat() + (float)blockpos1.getX();
                float f1 = -2.0F + randomsource.nextFloat() + (float)blockpos1.getY();
                float f2 = -0.5F + randomsource.nextFloat() + (float)blockpos1.getZ();
                level.addParticle(particle, vec3.x, vec3.y, vec3.z, f, f1, f2);
            }
        }

        if (entity != null) {
            Vec3 vec31 = new Vec3(entity.getX(), entity.getEyeY(), entity.getZ());
            float f3 = (-0.5F + randomsource.nextFloat()) * (3.0F + entity.getBbWidth());
            float f4 = -1.0F + randomsource.nextFloat() * entity.getBbHeight();
            float f5 = (-0.5F + randomsource.nextFloat()) * (3.0F + entity.getBbWidth());
            Vec3 vec32 = new Vec3(f3, f4, f5);
            level.addParticle(particle, vec31.x, vec31.y, vec31.z, vec32.x, vec32.y, vec32.z);
        }

    }
}
