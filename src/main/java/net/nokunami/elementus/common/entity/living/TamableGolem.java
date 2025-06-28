package net.nokunami.elementus.common.entity.living;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.*;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.DismountHelper;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.wrapper.InvWrapper;
import net.minecraftforge.network.NetworkHooks;
import net.nokunami.elementus.common.registry.ModSoundEvents;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public abstract class TamableGolem extends TamableAnimal implements ContainerListener, HasCustomInventoryScreen, MenuProvider, PlayerRideableJumping, Saddleable, RiderShieldingMount {
    private static final EntityDataAccessor<Byte> DATA_SADDLED_ID = SynchedEntityData.defineId(TamableGolem.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Boolean> DATA_ID_CHEST = SynchedEntityData.defineId(TamableGolem.class, EntityDataSerializers.BOOLEAN);
    public static final int EQUIPMENT_SLOT_OFFSET = 400;
    public static final int CHEST_SLOT_OFFSET = 499;
    public static final int INVENTORY_SLOT_OFFSET = 500;
    public static final int SADDLE_FLAG_ID = 1;
    public static final int INV_SLOT_SADDLE = 0;
    public static final int INV_SLOT_ARMOR = 1;
    public static final int INV_BASE_COUNT = 2;
    public int sprintCounter;
    protected boolean isJumping;
    public SimpleContainer inventory;

    protected TamableGolem(EntityType<? extends TamableGolem> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.createInventory();
    }

    public boolean isAlliedTo(@NotNull Entity pEntity) {
        if (this.isTame()) {
            LivingEntity livingentity = this.getOwner();
            if (pEntity == livingentity) {
                return true;
            }

            if (livingentity != null) {
                return livingentity.isAlliedTo(pEntity);
            }
        }

        return super.isAlliedTo(pEntity);
    }

    // Ridable Stuff
    @Override
    public boolean dismountsUnderwater() {
        return false;
    }

    @Override
    public double getRiderShieldingHeight() {
        return 1.25D;
    }

    @Override
    public @NotNull SoundEvent getSaddleSoundEvent() {
        return ModSoundEvents.STEEL_GOLEM_SADDLED.get();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.getEntityData().define(DATA_SADDLED_ID, (byte) 0);
        this.entityData.define(DATA_ID_CHEST, false);
    }

    protected boolean getSaddleFlag() {
        return (this.entityData.get(DATA_SADDLED_ID) & SADDLE_FLAG_ID) != 0;
    }

    protected void setSaddleFlag(boolean pValue) {
        byte b0 = this.entityData.get(DATA_SADDLED_ID);
        if (pValue) {
            this.entityData.set(DATA_SADDLED_ID, (byte)(b0 | SADDLE_FLAG_ID));
        } else {
            this.entityData.set(DATA_SADDLED_ID, (byte)(b0 & ~SADDLE_FLAG_ID));
        }
    }

    public boolean hasChest() {
        return this.entityData.get(DATA_ID_CHEST);
    }

    public void setChest(boolean pChested) {
        this.entityData.set(DATA_ID_CHEST, pChested);
    }

    public boolean isJumping() {
        return this.isJumping;
    }

    public void setIsJumping(boolean pJumping) {
        this.isJumping = pJumping;
    }

    public boolean isSaddleable() {
        return this.isAlive() && this.isTame();
    }

    public void equipSaddle(@Nullable SoundSource pSource) {
        this.inventory.setItem(INV_SLOT_SADDLE, new ItemStack(Items.SADDLE));
    }

    public void equipArmor(@NotNull Player pPlayer, @NotNull ItemStack pArmor) {
        if (this.isArmor(pArmor)) {
            this.inventory.setItem(1, pArmor.copyWithCount(1));
            if (!pPlayer.getAbilities().instabuild) {
                pArmor.shrink(1);
            }
        }
    }

    public boolean isSaddled() {
//        return this.getSaddleFlag();
        return !this.getItemBySlot(EquipmentSlot.HEAD).isEmpty();
    }

    public boolean isPushable() {
        return !this.isVehicle();
    }

    @Override
    public boolean causeFallDamage(float pFallDistance, float pMultiplier, @NotNull DamageSource pSource) {
        return false;
    }

    protected int getInventorySize() {
        return INV_BASE_COUNT;
    }

    protected void createInventory() {
        SimpleContainer simplecontainer = this.inventory;
        this.inventory = new SimpleContainer(this.getInventorySize());
        if (simplecontainer != null) {
            simplecontainer.removeListener(this);
            int i = Math.min(simplecontainer.getContainerSize(), this.inventory.getContainerSize());

            for(int j = 0; j < i; ++j) {
                ItemStack itemstack = simplecontainer.getItem(j);
                if (!itemstack.isEmpty()) {
                    this.inventory.setItem(j, itemstack.copy());
                }
            }
        }

        this.inventory.addListener(this);
        this.updateContainerEquipment();
        this.itemHandler = LazyOptional.of(() -> new InvWrapper(this.inventory));
    }

    protected void updateContainerEquipment() {
        if (!this.level().isClientSide) {
            this.setSaddle(this.inventory.getItem(0));
//            this.setSaddleFlag(!this.inventory.getItem(0).isEmpty());
        }
    }

    @Override
    public void containerChanged(@NotNull Container pContainer) {
        boolean flag = this.isSaddled();
        this.updateContainerEquipment();
        if (this.tickCount > 20 && !flag && this.isSaddled()) {
            this.playSound(this.getSaddleSoundEvent(), 0.5F, 1.0F);
        }
    }

    private void setSaddle(ItemStack stack) {
        this.setItemSlot(EquipmentSlot.HEAD, stack);
        this.setDropChance(EquipmentSlot.HEAD, 0.0F);
    }

    public void openCustomInventoryScreen(@NotNull Player pPlayer) {
    }

    public InteractionResult openInventory(Player player) {
        if (player instanceof ServerPlayer serverPlayer) {
            NetworkHooks.openScreen(serverPlayer, this);
        }
        openCustomInventoryScreen(player);
        if (hasChest()) this.level().playSound(null, this.blockPosition(), SoundEvents.CHEST_OPEN, SoundSource.NEUTRAL, 0.5F, 1);
        return InteractionResult.sidedSuccess(this.level().isClientSide);
    }

    protected InteractionResult doPlayerRide(Player pPlayer) {
        this.setOrderedToSit(false);
        this.setInSittingPose(false);
        if (!this.level().isClientSide) {
            pPlayer.setYRot(this.getYRot());
            pPlayer.setXRot(this.getXRot());
            pPlayer.startRiding(this);
        }
        return InteractionResult.SUCCESS;
    }

    protected void dropEquipment() {
        super.dropEquipment();
        if (this.inventory != null) {
            for(int i = 0; i < this.inventory.getContainerSize(); ++i) {
                ItemStack itemstack = this.inventory.getItem(i);
                if (!itemstack.isEmpty() && !EnchantmentHelper.hasVanishingCurse(itemstack)) {
                    this.spawnAtLocation(itemstack);
                }
            }

        }
    }

//    public @NotNull InteractionResult mobInteract(@NotNull Player player, @NotNull InteractionHand hand) {
//        ItemStack itemStack = player.getItemInHand(hand);
//        InteractionResult itemInteract = itemStack.interactLivingEntity(player, this, hand);
//
////        if (!isVehicle()) {
////            if (isTame() && player.isSecondaryUseActive() && (!itemStack.isEmpty() && !itemInteract.consumesAction())) {
////                Elementus.LOGGER.debug("tamableGolemOpenInv");
////                return openInventory(player);
////            } else {
////                if (!itemStack.isEmpty()) {
////                    if (itemInteract.consumesAction()) {
////                        Elementus.LOGGER.debug("tamableGolemItem");
////                        return itemInteract;
////                    }
////                    if (this.canWearArmor() && isArmor(itemStack) && !isWearingArmor()) {
////                        this.equipArmor(player, itemStack);
////                        Elementus.LOGGER.debug("tamableGolemArmor");
////                        return InteractionResult.sidedSuccess(this.level().isClientSide);
////                    }
////                }
////                Elementus.LOGGER.debug("tamableGolemEmptyStack");
////                return InteractionResult.sidedSuccess(this.level().isClientSide);
////            }
////        } else {
////            Elementus.LOGGER.debug("tamableGolemSuperMobInt");
////            return super.mobInteract(player, hand);
////        }
//        Elementus.LOGGER.debug("tamableGolemSuperMobInt");
//        return super.mobInteract(player, hand);
//    }

    protected void tickRidden(@NotNull Player pPlayer, @NotNull Vec3 pTravelVector) {
        super.tickRidden(pPlayer, pTravelVector);
        Vec2 vec2 = this.getRiddenRotation(pPlayer);
        this.setRot(vec2.y, vec2.x);
        this.yRotO = this.yBodyRot = this.yHeadRot = this.getYRot();
//        if (this.isControlledByLocalInstance()) {
//            if (this.onGround()) {
//                this.setIsJumping(false);
//                if (this.playerJumpPendingScale > 0.0F && !this.isJumping()) {
//                    this.executeRidersJump(this.playerJumpPendingScale, pTravelVector);
//                }
//
//                this.playerJumpPendingScale = 0.0F;
//            }
//        }
    }

    @Override
    public boolean canBeLeashed(Player pPlayer) {
        return super.canBeLeashed(pPlayer);
    }

    protected Vec2 getRiddenRotation(LivingEntity pEntity) {
        return new Vec2(pEntity.getXRot() * 0.25F, pEntity.getYRot());
    }

    protected @NotNull Vec3 getRiddenInput(Player pPlayer, @NotNull Vec3 pTravelVector) {
        float f = pPlayer.xxa * 0.5F;
        float f1 = pPlayer.zza;
        if (f1 <= 0.0F) {
            f1 *= 0.25F;
        }
        return new Vec3(f, 0.0D, f1);
    }

    @Override
    protected float getRiddenSpeed(@NotNull Player pPlayer) {
        return super.getRiddenSpeed(pPlayer);
    }

    protected void executeRidersJump(float pPlayerJumpPendingScale, Vec3 pTravelVector) {
        double d0 = 0.9 * (double)pPlayerJumpPendingScale * (double)this.getBlockJumpFactor();
        double d1 = d0 + (double)this.getJumpBoostPower();
        Vec3 vec3 = this.getDeltaMovement();
        this.setDeltaMovement(vec3.x, d1, vec3.z);
        this.setIsJumping(true);
        this.hasImpulse = true;
        net.minecraftforge.common.ForgeHooks.onLivingJump(this);
        if (pTravelVector.z > 0.0D) {
            float f = Mth.sin(this.getYRot() * ((float)Math.PI / 180F));
            float f1 = Mth.cos(this.getYRot() * ((float)Math.PI / 180F));
            this.setDeltaMovement(this.getDeltaMovement().add(-0.4F * f * pPlayerJumpPendingScale, 0.0D, 0.4F * f1 * pPlayerJumpPendingScale));
        }
    }

    public void addAdditionalSaveData(@NotNull CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        if (!this.inventory.getItem(0).isEmpty()) {
            tag.put("SaddleItem", this.inventory.getItem(0).save(new CompoundTag()));
        }
    }

    public void readAdditionalSaveData(@NotNull CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains("SaddleItem", 10)) {
            ItemStack itemStack = ItemStack.of(tag.getCompound("SaddleItem"));
            if (itemStack.is(Items.SADDLE)) {
                this.inventory.setItem(0, itemStack);
            }
        }
        this.updateContainerEquipment();
    }

    @Override
    public void onPlayerJump(int pJumpPower) {
    }

    @Override
    public boolean canJump() {
        return false;
    }

    @Override
    public void handleStartJump(int pJumpPower) {
    }

    @Override
    public void handleStopJump() {
    }

    protected int getMaxPassengers() {
        return 1;
    }

    public boolean canWearArmor() {
        return false;
    }

    public boolean isWearingArmor() {
        return !this.getItemBySlot(EquipmentSlot.CHEST).isEmpty();
    }

    public boolean isArmor(@NotNull ItemStack pStack) {
        return false;
    }

    protected SlotAccess createEquipmentSlotAccess(final int pSlot, final Predicate<ItemStack> pStackFilter) {
        return new SlotAccess() {
            public @NotNull ItemStack get() {
                return TamableGolem.this.inventory.getItem(pSlot);
            }

            public boolean set(@NotNull ItemStack stack) {
                if (!pStackFilter.test(stack)) {
                    return false;
                } else {
                    TamableGolem.this.inventory.setItem(pSlot, stack);
                    TamableGolem.this.updateContainerEquipment();
                    return true;
                }
            }
        };
    }

    public @NotNull SlotAccess getSlot(int pSlot) {
        int i = pSlot - EQUIPMENT_SLOT_OFFSET;
        if (i >= INV_SLOT_SADDLE && i < INV_BASE_COUNT && i < this.inventory.getContainerSize()) {
            if (i == INV_SLOT_SADDLE) {
                return this.createEquipmentSlotAccess(i, (stack) -> stack.isEmpty() || stack.is(Items.SADDLE));
            }
            if (i == INV_SLOT_ARMOR) {
                if (!this.canWearArmor()) {
                    return SlotAccess.NULL;
                }
                return this.createEquipmentSlotAccess(i, (p_149516_) -> p_149516_.isEmpty() || this.isArmor(p_149516_));
            }
        }
        int j = pSlot - INVENTORY_SLOT_OFFSET + 2;
        return j >= 2 && j < this.inventory.getContainerSize() ? SlotAccess.forContainer(this.inventory, j) : super.getSlot(pSlot);
    }

    @Nullable
    public LivingEntity getControllingPassenger() {
        Entity entity = this.getFirstPassenger();
        if (!(entity instanceof Mob)) {
            if (this.isSaddled()) {
                entity = this.getFirstPassenger();
                if (entity instanceof Player player && this.isOwnedBy(player)) {
                    return player;
                }
            }

        }
        return null;
    }

    @Nullable
    private Vec3 getDismountLocationInDirection(Vec3 pDirection, LivingEntity passenger) {
        double d0 = this.getX() + pDirection.x;
        double d1 = this.getBoundingBox().minY;
        double d2 = this.getZ() + pDirection.z;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for(Pose pose : passenger.getDismountPoses()) {
            blockpos$mutableblockpos.set(d0, d1, d2);
            double d3 = this.getBoundingBox().maxY + 0.75D;

            while(true) {
                double d4 = this.level().getBlockFloorHeight(blockpos$mutableblockpos);
                if ((double)blockpos$mutableblockpos.getY() + d4 > d3) {
                    break;
                }

                if (DismountHelper.isBlockFloorValid(d4)) {
                    AABB aabb = passenger.getLocalBoundsForPose(pose);
                    Vec3 vec3 = new Vec3(d0, (double)blockpos$mutableblockpos.getY() + d4, d2);
                    if (DismountHelper.canDismountTo(this.level(), passenger, aabb.move(vec3))) {
                        passenger.setPose(pose);
                        return vec3;
                    }
                }

                blockpos$mutableblockpos.move(Direction.UP);
                if (!((double)blockpos$mutableblockpos.getY() < d3)) {
                    break;
                }
            }
        }

        return null;
    }

    public @NotNull Vec3 getDismountLocationForPassenger(LivingEntity pLivingEntity) {
        Vec3 vec3 = getCollisionHorizontalEscapeVector(this.getBbWidth(), pLivingEntity.getBbWidth(), this.getYRot() + (pLivingEntity.getMainArm() == HumanoidArm.RIGHT ? 90.0F : -90.0F));
        Vec3 vec31 = this.getDismountLocationInDirection(vec3, pLivingEntity);
        if (vec31 != null) {
            return vec31;
        } else {
            Vec3 vec32 = getCollisionHorizontalEscapeVector(this.getBbWidth(), pLivingEntity.getBbWidth(), this.getYRot() + (pLivingEntity.getMainArm() == HumanoidArm.LEFT ? 90.0F : -90.0F));
            Vec3 vec33 = this.getDismountLocationInDirection(vec32, pLivingEntity);
            return vec33 != null ? vec33 : this.position();
        }
    }

    protected LazyOptional<?> itemHandler = null;

    @Override
    public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction facing) {
        if (this.isAlive() && capability == ForgeCapabilities.ITEM_HANDLER && itemHandler != null)
            return itemHandler.cast();
        return super.getCapability(capability, facing);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        if (itemHandler != null) {
            LazyOptional<?> oldHandler = itemHandler;
            itemHandler = null;
            oldHandler.invalidate();
        }
    }

    public boolean hasInventoryChanged(@NotNull Container pInventory) {
        return this.inventory != pInventory;
    }

    /// No
    @Override
    public @Nullable AgeableMob getBreedOffspring(@NotNull ServerLevel pLevel, @NotNull AgeableMob pOtherParent) {
        return null;
    }

    protected int decreaseAirSupply(int pAir) {
        return pAir;
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int pContainerId, @NotNull Inventory pPlayerInventory, @NotNull Player pPlayer) {
        return null;
    }
}
