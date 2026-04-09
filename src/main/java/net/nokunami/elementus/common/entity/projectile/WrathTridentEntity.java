package net.nokunami.elementus.common.entity.projectile;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.nokunami.elementus.Elementus;
import net.nokunami.elementus.common.item.unique.WrathTridentItem;
import net.nokunami.elementus.common.registry.EEntityTypes;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.UUID;

public class WrathTridentEntity extends AbstractArrow {
    private static final EntityDataAccessor<ItemStack> ITEM_STACK = SynchedEntityData.defineId(WrathTridentEntity.class, EntityDataSerializers.ITEM_STACK);
    private static final EntityDataAccessor<Byte> ID_LOYALTY = SynchedEntityData.defineId(WrathTridentEntity.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Integer> ID_SLOT = SynchedEntityData.defineId(WrathTridentEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> DROPPED = SynchedEntityData.defineId(WrathTridentEntity.class, EntityDataSerializers.BOOLEAN);
    private boolean dealtDamage;
    public int clientSideReturnTridentTickCount;
    public static float bbWidth = 0.5F;
    public static float bbHeight = 0.5F;

    public WrathTridentEntity(EntityType<? extends WrathTridentEntity> entityType, Level level) {
        super(entityType, level);
    }

    public WrathTridentEntity(Level level, LivingEntity entity, ItemStack stack) {
        super(EEntityTypes.WRATH_TRIDENT.get(), entity, level);
        setTridentItem(stack.copy());
        entityData.set(ID_LOYALTY, (byte)EnchantmentHelper.getLoyalty(stack));
        setSlotId(WrathTridentItem.getSlotId(stack));
    }

    public static WrathTridentEntity dropped(Level level, Entity entity, ItemStack stack) {
        WrathTridentEntity trident = new WrathTridentEntity(EEntityTypes.WRATH_TRIDENT.get(), level);
        Vec3 position = entity.position();
        trident.setDropped(true);
        trident.setPos(position);
        trident.setDeltaMovement(entity.getDeltaMovement().scale(1.5F));
        trident.setOwner(level.getPlayerByUUID(UUID.fromString(WrathTridentItem.getOwnerTag(stack))));
        trident.setTridentItem(stack);
        trident.pickup = Pickup.ALLOWED;
        trident.setSlotId(WrathTridentItem.getSlotId(stack));
        trident.setLoyalty((byte)EnchantmentHelper.getLoyalty(stack));

        return trident;
    }

    @Override
    protected void onHitBlock(@NotNull BlockHitResult hitResult) {
        super.onHitBlock(hitResult);
        setSoundEvent(SoundEvents.TRIDENT_HIT_GROUND);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(ITEM_STACK, ItemStack.EMPTY);
        entityData.define(ID_LOYALTY, (byte)0);
        entityData.define(ID_SLOT, 0);
        entityData.define(DROPPED, false);
    }

    public void tick() {
        if (inGroundTime > 4) {
            dealtDamage = true;
        }

        Entity entity = getOwner();
        int i = getLoyalty();
        if (i > 0 && (dealtDamage || isNoPhysics()) && entity != null || isBelowWorld()) {
            if (!isAcceptableReturnOwner()) {
//                if (!level().isClientSide && pickup == AbstractArrow.Pickup.ALLOWED) {
//                    spawnAtLocation(getPickupItem(), 0.1F);
//                }
//                discard();
                if (isBelowWorld()) setDeltaMovement(Vec3.ZERO);
                else setDeltaMovement(getDeltaMovement().add(0, -1, 0));
            } else if (isAcceptableReturnOwner()) {
                setNoPhysics(true);
                Vec3 vec3 = entity.getEyePosition().subtract(position());
                setPosRaw(getX(), getY() + vec3.y * 0.015D * (double)i, getZ());
                if (level().isClientSide) yOld = getY();
                float distanceMulti = Mth.clampedLerp(distanceTo(entity) / 5, 1, 4);
                double d0 = (0.05D * (double)i) * distanceMulti;
                setDeltaMovement(getDeltaMovement().scale(Mth.clamp(distanceTo(entity)/10, 0.95, 0.99)).add(vec3.normalize().scale(d0)));
                getBoundingBox().inflate(5);
                if (clientSideReturnTridentTickCount == 0) {
                    playSound(SoundEvents.TRIDENT_RETURN, 10.0F, 1.0F);
                }
                ++clientSideReturnTridentTickCount;
            }
        }

        if (getOwner() != null) {

        }

        super.tick();
    }

    public boolean isBelowWorld() {
        return (getY() < (double) (level().getMinBuildHeight() - 64));
    }

    @Override protected void onBelowWorld() {
        setNoPhysics(true);
    }

    public boolean isAcceptableReturnOwner() {
        return getOwner() != null && getOwner().isAlive() && !(getOwner() instanceof ServerPlayer || getOwner().isSpectator());
    }

    public ItemStack getTridentItem() { return entityData.get(ITEM_STACK); }
    public void setTridentItem(ItemStack stack) { entityData.set(ITEM_STACK, stack); }

    public byte getLoyalty() { return entityData.get(ID_LOYALTY); }
    public void setLoyalty(byte b) { entityData.set(ID_LOYALTY, b); }

    public int getSlotId() { return entityData.get(ID_SLOT); }
    public void setSlotId(int i) { entityData.set(ID_SLOT, i); }

    public boolean isDropped() { return entityData.get(DROPPED); }
    public void setDropped(boolean b) { entityData.set(DROPPED, b); }

    public @NotNull ItemStack getPickupItem() { return getTridentItem().copy(); }

    @Nullable
    protected EntityHitResult findHitEntity(@NotNull Vec3 startVec, @NotNull Vec3 endVec) {
        return dealtDamage ? null : super.findHitEntity(startVec, endVec);
    }

    protected void onHitEntity(EntityHitResult result) {
        Entity entity = result.getEntity();
        float f = 8.0F;
        if (entity instanceof LivingEntity livingentity)
            f += EnchantmentHelper.getDamageBonus(getTridentItem(), livingentity.getMobType());

        Entity owner = getOwner();
        DamageSource damagesource = damageSources().trident(this, owner == null ? this : owner);
        dealtDamage = true;
        SoundEvent soundevent = SoundEvents.TRIDENT_HIT;
        if (entity.hurt(damagesource, f)) {
            if (entity instanceof LivingEntity livingEntity) {
                if (owner != null) {
                    EnchantmentHelper.doPostHurtEffects(livingEntity, owner);
                    EnchantmentHelper.doPostDamageEffects((LivingEntity) owner, livingEntity);
                }
                doPostHurtEffects(livingEntity);
            }
        }

        setDeltaMovement(getDeltaMovement().multiply(-0.01D, -0.1D, -0.01D));
        float f1 = 1.0F;
        if (level() instanceof ServerLevel /*&& level().isThundering()*/ && isChanneling()) {
            BlockPos blockpos = entity.blockPosition();
            LightningBolt lightningbolt = EntityType.LIGHTNING_BOLT.create(level());
            if (lightningbolt != null) {
                lightningbolt.moveTo(Vec3.atBottomCenterOf(blockpos));
                lightningbolt.setCause(owner instanceof ServerPlayer ? (ServerPlayer) owner : null);
                level().addFreshEntity(lightningbolt);
                soundevent = SoundEvents.TRIDENT_THUNDER;
                f1 = 5.0F;
            }
        }
        playSound(soundevent, f1, 1.0F);
    }

    public boolean isChanneling() {
        return EnchantmentHelper.hasChanneling(getTridentItem());
    }

    protected boolean tryPickup(@NotNull Player player) {
        return checkPickup(player) || isNoPhysics() && ownedBy(player) && player.getInventory().add(getPickupItem());
    }

    private boolean checkPickup(Player player) {
        Inventory inv = player.getInventory();
        return switch (pickup) {
            case ALLOWED -> inv.getItem(getSlotId()).isEmpty() ? inv.add(getSlotId(), getPickupItem()) : inv.add(getPickupItem());
            case CREATIVE_ONLY -> player.getAbilities().instabuild;
            default -> false;
        };
    }

    protected @NotNull SoundEvent getDefaultHitGroundSoundEvent() {
        return SoundEvents.TRIDENT_HIT_GROUND;
    }

    @Override
    public void playerTouch(@NotNull Player player) {
        if (ownedBy(player) || getOwner() == null) {
            super.playerTouch(player);
        }
    }

    public void readAdditionalSaveData(@NotNull CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        CompoundTag stack = tag.getCompound("Trident");
        if (!stack.isEmpty()) {
            ItemStack itemStack = ItemStack.of(stack);
            if (itemStack.isEmpty()) Elementus.LOGGER.warn("Unable to load Trident from: {}", stack);
            setTridentItem(itemStack);
        }

        dealtDamage = tag.getBoolean("DealtDamage");
        entityData.set(ID_LOYALTY, (byte)EnchantmentHelper.getLoyalty(getTridentItem()));
        setSlotId(tag.getInt("Slot"));
        setDropped(tag.getBoolean("Dropped"));
    }

    public void addAdditionalSaveData(@NotNull CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        if (!getTridentItem().isEmpty())
            tag.put("Trident", getTridentItem().save(new CompoundTag()));
        tag.putBoolean("DealtDamage", dealtDamage);
        tag.putInt("Slot", getSlotId());
        tag.putBoolean("Dropped", isDropped());
    }

    public void tickDespawn() {
        int i = getLoyalty();
        if (pickup != AbstractArrow.Pickup.ALLOWED || i <= 0) {
            super.tickDespawn();
        }

    }

    protected float getWaterInertia() {
        return 0.99F;
    }

    public boolean shouldRender(double pX, double pY, double pZ) {
        return true;
    }
}
