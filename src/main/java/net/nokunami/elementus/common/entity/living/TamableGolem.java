package net.nokunami.elementus.common.entity.living;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.DismountHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.UUID;

public abstract class TamableGolem extends TamableAnimal implements /*ContainerListener, HasCustomInventoryScreen,*/ PlayerRideableJumping, Saddleable, RiderShieldingMount {
    public static final int EQUIPMENT_SLOT_OFFSET = 400;
    public static final int CHEST_SLOT_OFFSET = 499;
    public static final int INVENTORY_SLOT_OFFSET = 500;
//    private static final EntityDataAccessor<Byte> DATA_FLAGS_ID = SynchedEntityData.defineId(TamableGolem.class, EntityDataSerializers.BYTE);
//    protected static final EntityDataAccessor<Optional<UUID>> DATA_OWNERUUID_ID = SynchedEntityData.defineId(TamableGolem.class, EntityDataSerializers.OPTIONAL_UUID);
//    private static final int FLAG_SADDLE = 4;
    public static final int INV_SLOT_SADDLE = 0;
    public static final int INV_SLOT_ARMOR = 1;
    public static final int INV_BASE_COUNT = 2;
    public int sprintCounter;
    protected boolean isJumping;
    protected SimpleContainer inventory;

    protected TamableGolem(EntityType<? extends TamableGolem> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
//        this.createInventory();
        this.reassessTameGoals();
    }

    // Ai Related

    // Entity Data
    public void addAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);

//        if (!this.inventory.getItem(0).isEmpty()) {
//            pCompound.put("SaddleItem", this.inventory.getItem(0).save(new CompoundTag()));
//        }
    }

    public void readAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
//        if (pCompound.contains("SaddleItem", 10)) {
//            ItemStack itemstack = ItemStack.of(pCompound.getCompound("SaddleItem"));
//            if (itemstack.is(Items.SADDLE)) {
//                this.inventory.setItem(0, itemstack);
//            }
//        }

//        this.updateContainerEquipment();
    }


//    public void handleEntityEvent(byte pId) {
//        if (pId == 7) {
//            this.spawnTamingParticles(true);
//        } else if (pId == 6) {
//            this.spawnTamingParticles(false);
//        } else {
//            super.handleEntityEvent(pId);
//        }
//    }

//    protected boolean getFlag(int pFlagId) {
//        return (this.entityData.get(DATA_FLAGS_ID) & pFlagId) != 0;
//    }
//
//    protected void setFlag(int pFlagId, boolean pValue) {
//        byte b0 = this.entityData.get(DATA_FLAGS_ID);
//        if (pValue) {
//            this.entityData.set(DATA_FLAGS_ID, (byte)(b0 | pFlagId));
//        } else {
//            this.entityData.set(DATA_FLAGS_ID, (byte)(b0 & ~pFlagId));
//        }
//    }

    // Tame Related Stuff
    @Nullable
    public UUID getOwnerUUID() {
        return this.entityData.get(DATA_OWNERUUID_ID).orElse(null);
    }

    // Movement Related
    public boolean isJumping() {
        return this.isJumping;
    }

    public void setIsJumping(boolean pJumping) {
        this.isJumping = pJumping;
    }

    // Combat Related
    /**
     * Returns whether this Entity is on the same team as the given Entity.
     */
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
    protected void positionRider(@NotNull Entity pPassenger, @NotNull MoveFunction pCallback) {
        super.positionRider(pPassenger, pCallback);
        float f = Mth.sin(this.yBodyRot * ((float)Math.PI / 180F));
        float f1 = Mth.cos(this.yBodyRot * ((float)Math.PI / 180F));
        float f2 = 0.5F /** this.standAnimO*/;
        float f3 = 0.15F /** this.standAnimO*/;
        pCallback.accept(pPassenger,
                this.getX() + (double)(f2 * f),
                this.getY() + this.getPassengersRidingOffset() + pPassenger.getMyRidingOffset()/* + (double)f3*/,
                this.getZ() - (double)(f2 * f1));
        if (pPassenger instanceof LivingEntity) {
            ((LivingEntity)pPassenger).yBodyRot = this.yBodyRot;
        }
    }

    @Override
    public boolean dismountsUnderwater() {
        return false;
    }

    @Override
    public boolean causeFallDamage(float pFallDistance, float pMultiplier, @NotNull DamageSource pSource) {
        return false;
    }

    @Override
    public double getRiderShieldingHeight() {
        return 1.25D;
    }

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

    protected Vec2 getRiddenRotation(LivingEntity pEntity) {
        return new Vec2(pEntity.getXRot() * 0.5F, pEntity.getYRot());
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
            this.setDeltaMovement(this.getDeltaMovement().add((double)(-0.4F * f * pPlayerJumpPendingScale), 0.0D, (double)(0.4F * f1 * pPlayerJumpPendingScale)));
        }

    }

    protected void doPlayerRide(Player pPlayer) {
//        Elementus.LOGGER.debug("test ride");
        this.setOrderedToSit(false);
        this.setInSittingPose(false);
        if (!this.level().isClientSide) {
            pPlayer.setYRot(this.getYRot());
            pPlayer.setXRot(this.getXRot());
            pPlayer.startRiding(this);
        }
    }

    @Nullable
    public LivingEntity getControllingPassenger() {
        Entity entity = this.getFirstPassenger();
        if (entity instanceof Mob) {
            return (Mob)entity;
        } else {
            if (this.isSaddled()) {
                entity = this.getFirstPassenger();
                if (entity instanceof Player player && this.isOwnedBy(player)) {
                    return player;
                }
            }

            return null;
        }
    }

    @Nullable
    private Vec3 getDismountLocationInDirection(Vec3 pDirection, LivingEntity pPassenger) {
        double d0 = this.getX() + pDirection.x;
        double d1 = this.getBoundingBox().minY;
        double d2 = this.getZ() + pDirection.z;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for(Pose pose : pPassenger.getDismountPoses()) {
            blockpos$mutableblockpos.set(d0, d1, d2);
            double d3 = this.getBoundingBox().maxY + 0.75D;

            while(true) {
                double d4 = this.level().getBlockFloorHeight(blockpos$mutableblockpos);
                if ((double)blockpos$mutableblockpos.getY() + d4 > d3) {
                    break;
                }

                if (DismountHelper.isBlockFloorValid(d4)) {
                    AABB aabb = pPassenger.getLocalBoundsForPose(pose);
                    Vec3 vec3 = new Vec3(d0, (double)blockpos$mutableblockpos.getY() + d4, d2);
                    if (DismountHelper.canDismountTo(this.level(), pPassenger, aabb.move(vec3))) {
                        pPassenger.setPose(pose);
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
        Vec3 vec3 = getCollisionHorizontalEscapeVector((double)this.getBbWidth(), (double)pLivingEntity.getBbWidth(), this.getYRot() + (pLivingEntity.getMainArm() == HumanoidArm.RIGHT ? 90.0F : -90.0F));
        Vec3 vec31 = this.getDismountLocationInDirection(vec3, pLivingEntity);
        if (vec31 != null) {
            return vec31;
        } else {
            Vec3 vec32 = getCollisionHorizontalEscapeVector((double)this.getBbWidth(), (double)pLivingEntity.getBbWidth(), this.getYRot() + (pLivingEntity.getMainArm() == HumanoidArm.LEFT ? 90.0F : -90.0F));
            Vec3 vec33 = this.getDismountLocationInDirection(vec32, pLivingEntity);
            return vec33 != null ? vec33 : this.position();
        }
    }

    public boolean isSaddleable() {
        return this.isAlive() && this.isTame();
    }

    public void equipSaddle(@javax.annotation.Nullable SoundSource pSource) {
        this.inventory.setItem(0, new ItemStack(Items.SADDLE));
    }

//    public boolean isSaddled() {
//        return this.getFlag(FLAG_SADDLE);
//    }

    @Override
    public void onPlayerJump(int pJumpPower) {
    }

    public void equipArmor(@NotNull Player pPlayer, @NotNull ItemStack pArmor) {
        if (this.isArmor(pArmor)) {
            this.inventory.setItem(1, pArmor.copyWithCount(1));
            if (!pPlayer.getAbilities().instabuild) {
                pArmor.shrink(1);
            }
        }
    }

    public boolean isPushable() {
        return !this.isVehicle();
    }

    // Inventory
//    protected int getInventorySize() {
//        return INV_BASE_COUNT;
//    }
//
//    protected void createInventory() {
//        SimpleContainer simplecontainer = this.inventory;
//        this.inventory = new SimpleContainer(this.getInventorySize());
//        if (simplecontainer != null) {
//            simplecontainer.removeListener(this);
//            int i = Math.min(simplecontainer.getContainerSize(), this.inventory.getContainerSize());
//
//            for(int j = 0; j < i; ++j) {
//                ItemStack itemstack = simplecontainer.getItem(j);
//                if (!itemstack.isEmpty()) {
//                    this.inventory.setItem(j, itemstack.copy());
//                }
//            }
//        }
//
//        this.inventory.addListener(this);
//        this.updateContainerEquipment();
//        this.itemHandler = net.minecraftforge.common.util.LazyOptional.of(() -> new net.minecraftforge.items.wrapper.InvWrapper(this.inventory));
//    }
//
//    protected void updateContainerEquipment() {
//        if (!this.level().isClientSide) {
//            this.setFlag(4, !this.inventory.getItem(0).isEmpty());
//        }
//    }
//
//    @Override
//    public void containerChanged(@NotNull Container pContainer) {
//        boolean flag = this.isSaddled();
//        this.updateContainerEquipment();
//        if (this.tickCount > 20 && !flag && this.isSaddled()) {
//            this.playSound(this.getSaddleSoundEvent(), 0.5F, 1.0F);
//        }
//    }

//    public void openInventory(Player player) {
//        if (!this.level().isClientSide)
//            NetworkHooks.openScreen((ServerPlayer) player, getMenuProvider());
//        Elementus.PROXY.setReferencedMob(this);
//    }

//    public MenuProvider getMenuProvider() {
//        return new SimpleMenuProvider((containerId, playerInventory, player) ->
//                new GolemInventoryMenu(containerId, playerInventory, this.inventory, this), this.getDisplayName());
//    }

//    public void openCustomInventoryScreen(@NotNull Player player) {
//        if (!this.level().isClientSide && (!this.isVehicle() || this.hasPassenger(player)) && this.isTame()) {
//            openInventory(player);
//        }
//    }


    @Override
    public @NotNull SoundEvent getSaddleSoundEvent() {
        return SoundEvents.HORSE_SADDLE;
    }

    public boolean isWearingArmor() {
        return !this.getItemBySlot(EquipmentSlot.CHEST).isEmpty();
    }

    public boolean canWearArmor() {
        return false;
    }

    public boolean isArmor(@NotNull ItemStack pStack) {
        return false;
    }

    public boolean hasInventoryChanged(@NotNull Container pInventory) {
        return this.inventory != pInventory;
    }

    private final net.minecraftforge.common.util.LazyOptional<?> itemHandler = null;

    @Override
    public <T> net.minecraftforge.common.util.@NotNull LazyOptional<T> getCapability(net.minecraftforge.common.capabilities.@NotNull Capability<T> capability, @Nullable net.minecraft.core.Direction facing) {
        if (this.isAlive() && capability == net.minecraftforge.common.capabilities.ForgeCapabilities.ITEM_HANDLER && itemHandler != null)
            return itemHandler.cast();
        return super.getCapability(capability, facing);
    }

//    public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
//        if (!this.isVehicle() && !this.isBaby()) {
//            if (this.isTame() && pPlayer.isSecondaryUseActive()) {
//                this.openCustomInventoryScreen(pPlayer);
//                return InteractionResult.sidedSuccess(this.level().isClientSide);
//            } else {
//                ItemStack itemstack = pPlayer.getItemInHand(pHand);
//                if (!itemstack.isEmpty()) {
//                    InteractionResult interactionresult = itemstack.interactLivingEntity(pPlayer, this, pHand);
//                    if (interactionresult.consumesAction()) {
//                        return interactionresult;
//                    }
//
//                    if (this.canWearArmor() && this.isArmor(itemstack) && !this.isWearingArmor()) {
//                        this.equipArmor(pPlayer, itemstack);
//                        return InteractionResult.sidedSuccess(this.level().isClientSide);
//                    }
//                }
//
//                this.doPlayerRide(pPlayer);
//                return InteractionResult.sidedSuccess(this.level().isClientSide);
//            }
//        } else {
//            return super.mobInteract(pPlayer, pHand);
//        }
//    }

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

}
