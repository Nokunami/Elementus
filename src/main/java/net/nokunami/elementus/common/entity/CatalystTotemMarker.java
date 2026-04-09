package net.nokunami.elementus.common.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;

public class CatalystTotemMarker extends Entity {
    protected static final EntityDataAccessor<Optional<UUID>> OWNER_UUID = SynchedEntityData.defineId(CatalystTotemMarker.class, EntityDataSerializers.OPTIONAL_UUID);
    protected static final EntityDataAccessor<Integer> DISTANCE = SynchedEntityData.defineId(CatalystTotemMarker.class, EntityDataSerializers.INT);
    protected static final EntityDataAccessor<Optional<UUID>> LATCHED = SynchedEntityData.defineId(CatalystTotemMarker.class, EntityDataSerializers.OPTIONAL_UUID);
    public float yBodyRot;
    public float yBodyRotO;

    public CatalystTotemMarker(EntityType<?> type, Level level) {
        super(type, level);
    }

    @Override
    protected void defineSynchedData() {
        entityData.define(OWNER_UUID, Optional.empty());
        entityData.define(DISTANCE, 10);
    }

    @Override
    protected void readAdditionalSaveData(@NotNull CompoundTag tag) {
        if (getOwnerUUID() != null) tag.putUUID("Owner", getOwnerUUID());
        tag.putInt("EffectiveRange", getMaxDistance());
        if (getLatchedUUID() != null) tag.putUUID("Latched", getLatchedUUID());

    }

    @Override
    protected void addAdditionalSaveData(@NotNull CompoundTag tag) {
        if (tag.hasUUID("Owner")) setOwnerUUID(tag.getUUID("Owner"));
        setMaxDistance(tag.getInt("EffectiveRange"));
        if (tag.hasUUID("Latched")) setLatchedUUID(tag.getUUID("Latched"));
    }

    @Nullable public UUID getOwnerUUID() { return entityData.get(OWNER_UUID).orElse(null); }
    public void setOwnerUUID(@Nullable UUID uuid) { entityData.set(OWNER_UUID, Optional.ofNullable(uuid)); }

    @Nullable
    public Entity getOwner() {
        Entity cachedOwner;
        if (getOwnerUUID() != null && level() instanceof ServerLevel) {
            cachedOwner = ((ServerLevel) level()).getEntity(getOwnerUUID());
            return cachedOwner;
        } else {
            return null;
        }
    }

    @Nullable public UUID getLatchedUUID() { return entityData.get(LATCHED).orElse(null); }
    public void setLatchedUUID(@Nullable UUID uuid) { entityData.set(LATCHED, Optional.ofNullable(uuid)); }

    @Nullable
    public Entity getLatched() {
        Entity cachedLatched;
        if (getLatchedUUID() != null && level() instanceof ServerLevel) {
            cachedLatched = ((ServerLevel) level()).getEntity(getLatchedUUID());
            return cachedLatched;
        } else {
            return null;
        }
    }

    public int getMaxDistance() { return entityData.get(DISTANCE); }
    public void setMaxDistance(int i) { entityData.set(DISTANCE, i); }

    @Override
    public void baseTick() {
        super.baseTick();

        yBodyRotO = yBodyRot;
    }

    @Override
    public void tick() {
        super.tick();

        if (getOwner() != null && distanceTo(getOwner()) > Mth.square(getMaxDistance()) || getLatched() == null) {
            outOfRange();
        }
        if (getLatched() != null) setPos(getLatched().getPosition(1));

        if (level().isClientSide) {
//            if (random.nextInt(24) == 0 && !isSilent()) {
//                level().playLocalSound(getX() + 0.5D, getY() + 0.5D, getZ() + 0.5D, SoundEvents.BLAZE_BURN, getSoundSource(), 1.0F + this.random.nextFloat(), this.random.nextFloat() * 0.7F + 0.3F, false);
//            }
            for(int i = 0; i < 2; ++i) {
                level().addParticle(ParticleTypes.ELECTRIC_SPARK, getRandomX(0.5), getRandomY(), getRandomZ(0.5), 0, 0, 0);
            }
        }
        float f1 = yBodyRot;

        while(yBodyRot - yBodyRotO < -180.0F) {
            yBodyRotO -= 360.0F;
        }

        while(yBodyRot - yBodyRotO >= 180.0F) {
            yBodyRotO += 360.0F;
        }
    }

    public void outOfRange() {
        playSound(SoundEvents.TOTEM_USE, 1, 0);
        discard();
    }
}
