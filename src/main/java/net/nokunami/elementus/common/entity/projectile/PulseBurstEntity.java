package net.nokunami.elementus.common.entity.projectile;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.players.OldUsersConverter;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.server.ServerLifecycleHooks;
import net.nokunami.elementus.Elementus;
import net.nokunami.elementus.common.entity.MobUtil;
import net.nokunami.elementus.common.item.unique.BladeOfSurgingWinds;
import net.nokunami.elementus.common.registry.EEntityTypes;
import net.nokunami.elementus.common.registry.EParticles;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Predicate;

import static net.nokunami.elementus.common.item.EItemUtil.enchantedWith;
import static net.nokunami.elementus.common.registry.EEnchantments.SACRIFICE_CURSE;

public class PulseBurstEntity extends Projectile {
    protected static final EntityDataAccessor<Optional<UUID>> OWNER_UNIQUE_ID = SynchedEntityData.defineId(PulseBurstEntity.class, EntityDataSerializers.OPTIONAL_UUID);
    protected static final EntityDataAccessor<BlockPos> BLOCK_POS = SynchedEntityData.defineId(PulseBurstEntity.class, EntityDataSerializers.BLOCK_POS);
    protected static final EntityDataAccessor<Float> DISCARD_DISTANCE = SynchedEntityData.defineId(PulseBurstEntity.class, EntityDataSerializers.FLOAT);
    protected static final EntityDataAccessor<Boolean> FRIENDLY_FIRE = SynchedEntityData.defineId(PulseBurstEntity.class, EntityDataSerializers.BOOLEAN);
    protected static final EntityDataAccessor<ItemStack> WEAPON = SynchedEntityData.defineId(PulseBurstEntity.class, EntityDataSerializers.ITEM_STACK);
    private float damage;
    private int delay;
    public Predicate<? super Entity> REMOVE_ENTITIES_PREDICATE = (e -> MobUtil.allied(this.getTrueOwner(), e, this.getFriendlyFire()) || e.equals(this.getTrueOwner()));

    public PulseBurstEntity(EntityType<? extends Projectile> entityType, Level level) {
        super(entityType, level);
        this.damage = 7.5F;
        this.delay = 0;
    }

    protected PulseBurstEntity(EntityType<? extends Projectile> pEntityType, double pX, double pY, double pZ, Level pLevel) {
        this(pEntityType, pLevel);
        this.setPos(pX, pY, pZ);
    }

    protected PulseBurstEntity(EntityType<? extends Projectile> pEntityType, LivingEntity pShooter, Level pLevel) {
        this(pEntityType, pShooter.getX(), pShooter.getEyeY() - (double)0.1F, pShooter.getZ(), pLevel);
        this.setOwner(pShooter);
    }

    public PulseBurstEntity(Level pLevel, LivingEntity shooter) {
        this(EEntityTypes.PULSE_BURST.get(), shooter, pLevel);
        this.setOwnerId(shooter.getUUID());
        this.setBlockPos(shooter.blockPosition());
        this.setItemStack(shooter.getUseItem());
    }

    public void emitPulse(Entity caster, double velocity, double inaccuracy, double discardDist, double damage) {
        float pX = caster.getXRot();
        float pY = caster.getYRot();
        float pZ = 0;
        float f = -Mth.sin(pY * ((float) Math.PI / 180F)) * Mth.cos(pX * ((float) Math.PI / 180F));
        float f1 = -Mth.sin((pX + pZ) * ((float) Math.PI / 180F));
        float f2 = Mth.cos(pY * ((float) Math.PI / 180F)) * Mth.cos(pX * ((float) Math.PI / 180F));
        shoot(f, f1, f2, (float) velocity, (float) inaccuracy);
        setXRot(pX);
        setYRot(pY);
        setDiscardDistance((float) discardDist);
        setDamage((float) damage);
    }

    public float getDamage() {
        return this.damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    protected void defineSynchedData() {
        this.entityData.define(OWNER_UNIQUE_ID, Optional.empty());
        this.entityData.define(BLOCK_POS, BlockPos.ZERO);
        this.entityData.define(DISCARD_DISTANCE, 10F);
        this.entityData.define(FRIENDLY_FIRE, false);
        this.entityData.define(WEAPON, ItemStack.EMPTY);
    }

    public void readAdditionalSaveData(@NotNull CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        UUID uuid;
        if (compound.hasUUID("Owner")) {
            uuid = compound.getUUID("Owner");
        } else {
            String s = compound.getString("Owner");
            uuid = OldUsersConverter.convertMobOwnerIfNecessary(Objects.requireNonNull(this.getServer()), s);
        }

        if (uuid != null) {
            try {
                this.setOwnerId(uuid);
            } catch (Throwable ignored) {
            }
        }

        if (compound.contains("Damage")) {
            this.setDamage(compound.getInt("Damage"));
        }
        int x = compound.getInt("PosX");
        int y = compound.getInt("PosY");
        int z = compound.getInt("PosZ");
        this.setBlockPos(new BlockPos(x, y, z));
        this.setDiscardDistance(compound.getFloat("DiscardDistance"));
        this.setFriendlyFire(compound.getBoolean("FriendlyFire"));
        CompoundTag stack = compound.getCompound("ItemStack");
        if (!stack.isEmpty()) {
            ItemStack itemStack = ItemStack.of(stack);
            if (itemStack.isEmpty()) Elementus.LOGGER.warn("Unable to load ItemStack from: {}", stack);
            this.setItemStack(itemStack);
        }
    }

    public void addAdditionalSaveData(@NotNull CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        if (this.getOwnerId() != null) {
            compound.putUUID("Owner", this.getOwnerId());
        }
        compound.putFloat("Damage", this.getDamage());
        compound.putInt("PosX", getBlockPos().getX());
        compound.putInt("PosY", getBlockPos().getY());
        compound.putInt("PosZ", getBlockPos().getZ());
        compound.putFloat("DiscardDistance", this.getDiscardDistance());
        compound.putBoolean("FriendlyFire", this.getFriendlyFire());
        if (!this.getItemStack().isEmpty()) {
            compound.put("ItemStack", this.getItemStack().save(new CompoundTag()));
        }
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
    public void setOwnerId(@Nullable UUID uuid) {
        this.entityData.set(OWNER_UNIQUE_ID, Optional.ofNullable(uuid));
    }

    public BlockPos getBlockPos() {
        return this.entityData.get(BLOCK_POS);
    }
    public void setBlockPos(BlockPos blockPos) {
        this.entityData.set(BLOCK_POS, blockPos);
    }

    public float getDiscardDistance() {
        return this.entityData.get(DISCARD_DISTANCE);
    }
    public void setDiscardDistance(float v) {
        this.entityData.set(DISCARD_DISTANCE, v);
    }

    public boolean getFriendlyFire() {
        return this.entityData.get(FRIENDLY_FIRE);
    }
    public void setFriendlyFire(boolean b) {
        this.entityData.set(FRIENDLY_FIRE, b);
    }

    public ItemStack getItemStack() {
        return this.entityData.get(WEAPON);
    }
    public void setItemStack(ItemStack b) {
        this.entityData.set(WEAPON, b);
    }

    public void tick() {
        int totalDelay = 6;
        ParticleOptions burstEmitter = EParticles.INITIAL_BURST.get();
        ParticleOptions trail = EParticles.SONIC_BURST.get();
        if (!getItemStack().isEmpty()) {
            setFriendlyFire(BladeOfSurgingWinds.getFriendlyFire(getItemStack()));
            if (enchantedWith(getItemStack(), SACRIFICE_CURSE)) {
                burstEmitter = EParticles.INITIAL_BURST_SACRIFICE.get();
                trail = EParticles.SONIC_BURST_SACRIFICE.get();
            }
        }

        if (getTrueOwner() != null) {
            double blockPos = distanceToSqr(getBlockPos().getCenter());
            double discardDistance = Mth.square(getDiscardDistance());

            if (blockPos >= discardDistance) {
                discard();
            }

            if (delay < totalDelay){
                ++delay;
            } else {
                level().addParticle(burstEmitter, getX() + getDeltaMovement().x, getY() + getDeltaMovement().y, getZ() + getDeltaMovement().z, 0.0D, 0.0D, 0.0D);
                level().playLocalSound(getX(), getY(), getZ(), SoundEvents.WARDEN_SONIC_BOOM, getSoundSource(), 5.0F, 1.0F, false);
                delay = 0;
            }

//            Set<Entity> targets = new HashSet<>(level().getEntitiesOfClass(LivingEntity.class, getBoundingBox().inflate(5), (e) -> MobUtil.allied(getTrueOwner(), e, getFriendlyFire())));
            Set<Entity> targets = new HashSet<>(level().getEntitiesOfClass(LivingEntity.class, getBoundingBox().inflate(5), e -> MobUtil.allied(getTrueOwner(), e, getFriendlyFire())));
            float damage1 = getDamage();
            if (!targets.isEmpty()){
                for (Entity entity: targets){
                    if (entity instanceof LivingEntity living && entity != getTrueOwner() && delay == totalDelay/2) {
                        damage1 += EnchantmentHelper.getDamageBonus(getItemStack(), (living).getMobType());
                        living.hurt(entity.damageSources().sonicBoom(getTrueOwner()), damage1);
                        living.invulnerableTime = 0;
                    }
                }
            }

            if (delay == 1) level().addParticle(trail, getX(), getY(), getZ(), 0.0D, 0.0D, 0.0D);
        }

        setPos(position().add(getDeltaMovement()));
        Vec3 vec34 = getDeltaMovement();
//        setDeltaMovement(vec34.x + (vec34.x * 0.125), vec34.y + (vec34.y * 0.125), vec34.z + (vec34.z * 0.125));
        setDeltaMovement(vec34.x, vec34.y, vec34.z);
    }

    @Override
    public boolean canChangeDimensions() {
        return false;
    }

    public boolean isOnFire() {
        return false;
    }

    public boolean hurt(@NotNull DamageSource pSource, float pAmount) {
        return false;
    }

    @Override
    public @NotNull Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
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
}