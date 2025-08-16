package net.nokunami.elementus.common.entity.projectile;

import com.simibubi.create.AllEntityTypes;
import com.simibubi.create.content.logistics.box.PackageEntity;
import com.simibubi.create.content.logistics.chute.ChuteBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.nokunami.elementus.Elementus;
import net.nokunami.elementus.common.registry.ModEntityType;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;

public class TestTridentEntity extends AbstractArrow {
    private static final EntityDataAccessor<ItemStack> ITEM_STACK = SynchedEntityData.defineId(TestTridentEntity.class, EntityDataSerializers.ITEM_STACK);
//    private static final EntityDataAccessor<Optional<UUID>> TRUE_OWNER = SynchedEntityData.defineId(TestTridentEntity.class, EntityDataSerializers.OPTIONAL_UUID);
    private static final EntityDataAccessor<Byte> ID_LOYALTY = SynchedEntityData.defineId(TestTridentEntity.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Boolean> ID_FOIL = SynchedEntityData.defineId(TestTridentEntity.class, EntityDataSerializers.BOOLEAN);
    private boolean dealtDamage;
    public int clientSideReturnTridentTickCount;
    public static float bbWidth = 0.5F;
    public static float bbHeight = 0.5F;

    public TestTridentEntity(EntityType<? extends TestTridentEntity> entityType, Level level) {
        super(entityType, level);
    }

    public TestTridentEntity(Level level, LivingEntity entity, ItemStack stack) {
        super(ModEntityType.TEST_TRIDENT.get(), entity, level);
        this.setTridentItem(stack.copy());
//        this.setOwnerUUID(entity.getUUID());
        this.entityData.set(ID_LOYALTY, (byte)EnchantmentHelper.getLoyalty(stack));
        this.entityData.set(ID_FOIL, stack.hasFoil());
    }

    public TestTridentEntity(Level level, Entity entity, ItemStack stack) {
        super(ModEntityType.TEST_TRIDENT.get(), level);
        this.setTridentItem(stack.copy());
        this.entityData.set(ID_LOYALTY, (byte)EnchantmentHelper.getLoyalty(stack));
        this.entityData.set(ID_FOIL, stack.hasFoil());
    }

    @Override
    protected void onHitBlock(@NotNull BlockHitResult hitResult) {
        super.onHitBlock(hitResult);
        this.setSoundEvent(SoundEvents.TRIDENT_HIT);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
//        this.entityData.define(TRUE_OWNER, Optional.empty());
        this.entityData.define(ITEM_STACK, ItemStack.EMPTY);
        this.entityData.define(ID_LOYALTY, (byte)0);
        this.entityData.define(ID_FOIL, false);
    }

    public void tick() {
        if (this.inGroundTime > 4) {
            this.dealtDamage = true;
        }

//        Entity entity = this.level().getPlayerByUUID(this.getOwnerUUID());
        Entity entity = this.getOwner();
        int i = this.entityData.get(ID_LOYALTY);
        if (i > 0 && (this.dealtDamage || this.isNoPhysics()) && entity != null) {
            if (!this.isAcceptibleReturnOwner()) {
                if (!this.level().isClientSide && this.pickup == AbstractArrow.Pickup.ALLOWED) {
                    this.spawnAtLocation(this.getPickupItem(), 0.1F);
                }

                this.discard();
            } else {
                this.setNoPhysics(true);
                Vec3 vec3 = entity.getEyePosition().subtract(this.position());
                this.setPosRaw(this.getX(), this.getY() + vec3.y * 0.015D * (double)i, this.getZ());
                if (this.level().isClientSide) {
                    this.yOld = this.getY();
                }

                double d0 = 0.05D * (double)i;
                this.setDeltaMovement(this.getDeltaMovement().scale(0.95D).add(vec3.normalize().scale(d0)));
                if (this.clientSideReturnTridentTickCount == 0) {
                    this.playSound(SoundEvents.TRIDENT_RETURN, 10.0F, 1.0F);
                }

                ++this.clientSideReturnTridentTickCount;
            }
        }

        super.tick();
    }

    private boolean isAcceptibleReturnOwner() {
        Entity entity = this.getOwner();
        if (entity != null && entity.isAlive()) {
            return !(entity instanceof ServerPlayer) || !entity.isSpectator();
        } else {
            return false;
        }
    }

    public ItemStack getTridentItem() {
        return this.entityData.get(ITEM_STACK);
    }

    public void setTridentItem(ItemStack stack) {
        this.entityData.set(ITEM_STACK, stack);
    }

//    public UUID getOwnerUUID() {
//        return this.entityData.get(TRUE_OWNER).orElse(null);
//    }

//    public void setOwnerUUID(UUID uuid) {
//        this.entityData.set(TRUE_OWNER, Optional.ofNullable(uuid));
//    }

//    public Entity getTrueOwner() {
//        if (this.getOwnerUUID() != null) {
//            return this.level().getPlayerByUUID(this.getOwnerUUID());
//        } /*else if (this.getOwnerUUID() != null && this.level() instanceof ServerLevel) {
//            this.cachedOwner = ((ServerLevel)this.level()).getEntity(this.getOwnerUUID());
//            return this.cachedOwner;
//        } */else {
//            return null;
//        }
//    }

    public @NotNull ItemStack getPickupItem() {
        return this.getTridentItem().copy();
    }

    @Nullable
    protected EntityHitResult findHitEntity(@NotNull Vec3 startVec, @NotNull Vec3 endVec) {
        return this.dealtDamage ? null : super.findHitEntity(startVec, endVec);
    }

    protected void onHitEntity(EntityHitResult result) {
        Entity entity = result.getEntity();
        float f = 8.0F;
        if (entity instanceof LivingEntity livingentity)
            f += EnchantmentHelper.getDamageBonus(this.getTridentItem(), livingentity.getMobType());

//        LivingEntity owner = this.level().getPlayerByUUID(this.getOwnerUUID());
        Entity owner = this.getOwner();
        DamageSource damagesource = this.damageSources().trident(this, owner == null ? this : owner);
        this.dealtDamage = true;
        SoundEvent soundevent = SoundEvents.TRIDENT_HIT;
        if (entity.hurt(damagesource, f)) {
            if (entity.getType() == EntityType.ENDERMAN)
                return;

            if (entity instanceof LivingEntity livingEntity) {
                if (owner != null) {
                    EnchantmentHelper.doPostHurtEffects(livingEntity, owner);
                    EnchantmentHelper.doPostDamageEffects((LivingEntity) owner, livingEntity);
                }
                this.doPostHurtEffects(livingEntity);
            }
        }

        this.setDeltaMovement(this.getDeltaMovement().multiply(-0.01D, -0.1D, -0.01D));
        float f1 = 1.0F;
        if (this.level() instanceof ServerLevel && this.level().isThundering() && this.isChanneling()) {
            BlockPos blockpos = entity.blockPosition();
            if (this.level().canSeeSky(blockpos)) {
                LightningBolt lightningbolt = EntityType.LIGHTNING_BOLT.create(this.level());
                if (lightningbolt != null) {
                    lightningbolt.moveTo(Vec3.atBottomCenterOf(blockpos));
                    lightningbolt.setCause(owner instanceof ServerPlayer ? (ServerPlayer)owner : null);
                    this.level().addFreshEntity(lightningbolt);
                    soundevent = SoundEvents.TRIDENT_THUNDER;
                    f1 = 5.0F;
                }
            }
        }
        this.playSound(soundevent, f1, 1.0F);
    }

    public boolean isChanneling() {
        return EnchantmentHelper.hasChanneling(this.getTridentItem());
    }

    protected boolean tryPickup(@NotNull Player player) {
        return super.tryPickup(player) || this.isNoPhysics() && this.ownedBy(player) && player.getInventory().add(this.getPickupItem());
    }

    protected @NotNull SoundEvent getDefaultHitGroundSoundEvent() {
        return SoundEvents.TRIDENT_HIT_GROUND;
    }

    public void playerTouch(@NotNull Player player) {
        if (this.ownedBy(player) || this.getOwner() == null) {
            super.playerTouch(player);
        }

    }

    public void readAdditionalSaveData(@NotNull CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        CompoundTag stack = tag.getCompound("Trident");
        if (!stack.isEmpty()) {
            ItemStack itemStack = ItemStack.of(stack);
            if (itemStack.isEmpty()) Elementus.LOGGER.warn("Unable to load Trident from: {}", stack);
            this.setTridentItem(itemStack);
        }

        this.dealtDamage = tag.getBoolean("DealtDamage");
        this.entityData.set(ID_LOYALTY, (byte)EnchantmentHelper.getLoyalty(this.getTridentItem()));
    }

    public void addAdditionalSaveData(@NotNull CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        if (!this.getTridentItem().isEmpty())
            tag.put("Trident", this.getTridentItem().save(new CompoundTag()));
        tag.putBoolean("DealtDamage", this.dealtDamage);
    }

    public void tickDespawn() {
        int i = this.entityData.get(ID_LOYALTY);
        if (this.pickup != AbstractArrow.Pickup.ALLOWED || i <= 0) {
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
