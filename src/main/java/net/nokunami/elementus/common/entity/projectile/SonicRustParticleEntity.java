package net.nokunami.elementus.common.entity.projectile;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.players.OldUsersConverter;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.server.ServerLifecycleHooks;
import net.nokunami.elementus.common.registry.ModEntityType;
import net.nokunami.elementus.common.registry.ModItems;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;

public class SonicRustParticleEntity extends AbstractHurtingProjectile {
    protected static final EntityDataAccessor<Optional<UUID>> OWNER_UNIQUE_ID = SynchedEntityData.defineId(SonicRustParticleEntity.class, EntityDataSerializers.OPTIONAL_UUID);
    private ItemStack weapon = new ItemStack(ModItems.ElementusItems.ANTHEKTITE_CHARGE_BLADE.get());
    private float damage;
    private int lifespan;
    private int totallife;

    public SonicRustParticleEntity(EntityType<? extends AbstractHurtingProjectile> entityType, Level level) {
        super(entityType, level);
        this.damage = 7.5F;
        this.lifespan = 0;
        this.totallife = 120;
    }

    protected SonicRustParticleEntity(EntityType<? extends AbstractHurtingProjectile> pEntityType, double pX, double pY, double pZ, Level pLevel) {
        this(pEntityType, pLevel);
        this.setPos(pX, pY, pZ);
    }

    protected SonicRustParticleEntity(EntityType<? extends AbstractHurtingProjectile> pEntityType, LivingEntity pShooter, Level pLevel) {
        this(pEntityType, pShooter.getX(), pShooter.getEyeY() - (double)0.1F, pShooter.getZ(), pLevel);
        this.setOwner(pShooter);
    }

    public SonicRustParticleEntity(Level pLevel, LivingEntity pShooter) {
        this(ModEntityType.ANTHEKTITE_SLASH.get(), pShooter, pLevel);
        this.weapon = pShooter.getMainHandItem();
    }

    public float getDamage() {
        return this.damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public int getTotallife() {
        return totallife;
    }

    public void setTotallife(int totallife) {
        this.totallife = totallife;
    }

    public int getLifespan() {
        return lifespan;
    }

    public void setLifespan(int lifespan) {
        this.lifespan = lifespan;
    }

    protected void defineSynchedData() {
        this.entityData.define(OWNER_UNIQUE_ID, Optional.empty());
    }

    public void readAdditionalSaveData(@NotNull CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        UUID uuid;
        if (compound.hasUUID("Owner")) {
            uuid = compound.getUUID("Owner");
        } else {
            String s = compound.getString("Owner");
            uuid = OldUsersConverter.convertMobOwnerIfNecessary(this.getServer(), s);
        }

        if (uuid != null) {
            try {
                this.setOwnerId(uuid);
            } catch (Throwable ignored) {
            }
        }

        if (compound.contains("Damage")) {
            this.setLifespan(compound.getInt("Damage"));
        }
        if (compound.contains("Lifespan")) {
            this.setLifespan(compound.getInt("Lifespan"));
        }
        if (compound.contains("TotalLife")) {
            this.setTotallife(compound.getInt("TotalLife"));
        }

    }

    public void addAdditionalSaveData(@NotNull CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        if (this.getOwnerId() != null) {
            compound.putUUID("Owner", this.getOwnerId());
        }
        compound.putFloat("Damage", this.getDamage());
        compound.putInt("Lifespan", this.getLifespan());
        compound.putInt("TotalLife", this.getTotallife());
    }

    public LivingEntity getTrueOwner() {
        try {
            UUID uuid = this.getOwnerId();
            return uuid == null ? null : getLivingEntityByUuiD(uuid);
        } catch (IllegalArgumentException illegalargumentexception) {
            return null;
        }
    }

    @Nullable
    public UUID getOwnerId() {
        return this.entityData.get(OWNER_UNIQUE_ID).orElse(null);
    }

    public void setOwnerId(@Nullable UUID p_184754_1_) {
        this.entityData.set(OWNER_UNIQUE_ID, Optional.ofNullable(p_184754_1_));
    }

    public void tick() {
        super.tick();

        this.setPos(getTrueOwner().getPosition(tickCount));
    }

    @Override
    protected void onHit(@NotNull HitResult result) {
    }

    @Override
    protected void onHitEntity(@NotNull EntityHitResult result) {
    }

    @Override
    protected void onHitBlock(@NotNull BlockHitResult result) {
    }

    public boolean isOnFire() {
        return false;
    }

    public boolean isPickable() {
        return false;
    }

    public boolean hurt(@NotNull DamageSource pSource, float pAmount) {
        return false;
    }

    protected @NotNull ParticleOptions getTrailParticle() {
        return ParticleTypes.SONIC_BOOM;
    }

    @Override
    public @NotNull Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public static LivingEntity getLivingEntityByUuiD(Level level, UUID uuid) {
        return getLivingEntityByUuiD(level.getServer(), uuid);
    }

    public static LivingEntity getLivingEntityByUuiD(UUID uuid) {
        return getLivingEntityByUuiD(ServerLifecycleHooks.getCurrentServer(), uuid);
    }

    public static LivingEntity getLivingEntityByUuiD(MinecraftServer server, UUID uuid){
        if (uuid != null && server != null) {
            for (ServerLevel world : server.getAllLevels()) {
                Entity entity = world.getEntity(uuid);
                if (entity instanceof LivingEntity){
                    return (LivingEntity) entity;
                }
            }
        }
        return null;
    }

    public static boolean breakBlock(Level level, BlockPos blockPos, ItemStack itemStack, @Nullable Entity entity) {
        BlockState blockstate = level.getBlockState(blockPos);
        if (blockstate.isAir()) {
            return false;
        } else {
            FluidState fluidstate = level.getFluidState(blockPos);
            if (!(blockstate.getBlock() instanceof BaseFireBlock)) {
                level.levelEvent(2001, blockPos, Block.getId(blockstate));
            }

            BlockEntity blockentity = blockstate.hasBlockEntity() ? level.getBlockEntity(blockPos) : null;
            Block.dropResources(blockstate, level, blockPos, blockentity, entity, itemStack);

            boolean flag = level.setBlock(blockPos, fluidstate.createLegacyBlock(), 3, 512);
            if (flag) {
                level.gameEvent(GameEvent.BLOCK_DESTROY, blockPos, GameEvent.Context.of(entity, blockstate));
            }

            return flag;
        }
    }

    public static boolean areAllies(@Nullable Entity entity, @Nullable Entity entity1){
        if (entity != null && entity1 != null) {
            return entity.isAlliedTo(entity1) || entity1.isAlliedTo(entity) || entity == entity1;
        } else {
            return false;
        }
    }
}
