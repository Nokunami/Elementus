package net.nokunami.elementus.common.catalystCore.ability.activeAbility.endShifter;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.nokunami.elementus.common.capability.cAbility.CAbility;
import net.nokunami.elementus.common.catalystCore.ability.AbilityType;
import net.nokunami.elementus.common.catalystCore.ability.activeAbility.AbstractActiveAbility;

import static net.nokunami.elementus.Elementus.modLoc;
import static net.nokunami.elementus.common.entity.MobUtil.POVHitResult;
import static net.nokunami.elementus.common.entity.MobUtil.teleportEntity;

public class TeleportAbility extends AbstractActiveAbility {
    int cooldown = 10;

    public TeleportAbility() {
        super(new AbilityProperties(AbilityType.TRIGGERED, 10));
    }

    @Override public int getCooldown() { return cooldown; }

    @Override
    public boolean castAbility(LivingEntity entity, Level level, CAbility ca) {
        if (!level.isClientSide()) {
            double max = 64;

            var hitResult = POVHitResult(level, entity, max, ClipContext.Fluid.NONE);
            BlockPos clickedPos = hitResult.getBlockPos();
            Direction direction = hitResult.getDirection();
            BlockState blockstate = level.getBlockState(clickedPos);
            BlockPos blockPos;

            if (blockstate.getCollisionShape(level, clickedPos).isEmpty()) {
                blockPos = clickedPos;
            } else blockPos = clickedPos.relative(direction);

            if (direction.getAxis().isHorizontal() || direction.equals(Direction.UP) || blockState(level, clickedPos.above()).isAir()) blockPos = clickedPos.above();
            if (blockState(level, blockPos.above()).isSolid()) blockPos = clickedPos.relative(direction);
            Vec3 tp = blockPos.getCenter().add(0, -0.5, 0);

            int d = (int) distanceTo(entity.getEyePosition(), tp);
            cooldown = Math.max(d, 10);
            if (entity.getVehicle() != null) entity.stopRiding();
            if (!entity.isFallFlying()) entity.resetFallDistance();
            teleportEntity(entity, tp);

            level.playSound(null, entity, SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1, 0.9F);

            if (level instanceof ServerLevel) ((ServerLevel) level).sendParticles(ParticleTypes.PORTAL, tp.x + 0.5, tp.y + 0.5, tp.z + 0.5, 5, 0.5, 0.5, 0.5, 0.1);
        }
        return true;
    }

    public float distanceTo(Vec3 start, Vec3 stop) {
        float f = (float) (stop.x() - start.x());
        float f1 = (float) (stop.y() - start.y());
        float f2 = (float) (stop.z() - start.z());
        return Mth.sqrt(f * f + f1 * f1 + f2 * f2);
    }

    protected BlockState blockState(Level level, BlockPos pos) { return level.getBlockState(pos); }

//    protected void teleportEntity(Entity entity, BlockPos vec3) { entity.teleportTo(vec3.getX(), vec3.getY(), vec3.getZ()); }
//    protected void teleportEntity(Entity entity, Vec3 vec3) { entity.teleportTo(vec3.x, vec3.y, vec3.z); }
//    protected void teleportEntity(Entity entity, double x, double y, double z) { entity.teleportTo(x, y, z); }
}
