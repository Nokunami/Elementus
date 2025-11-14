package net.nokunami.elementus.common.entity.living;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
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
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.wrapper.InvWrapper;
import net.minecraftforge.network.NetworkHooks;
import net.nokunami.elementus.common.registry.ESoundEvents;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.UUID;
import java.util.function.Predicate;

public abstract class TamableGolem extends TamableAnimal implements ContainerListener, HasCustomInventoryScreen, MenuProvider, PlayerRideableJumping, Saddleable, RiderShieldingMount, NeutralMob {
    protected static final EntityDataAccessor<Boolean> IS_PLAYER_CREATED = SynchedEntityData.defineId(TamableGolem.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IS_ATTACKING = SynchedEntityData.defineId(TamableGolem.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IS_AOE_ATTACKING = SynchedEntityData.defineId(TamableGolem.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> AOE_TIMER = SynchedEntityData.defineId(TamableGolem.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> ATTACK_TYPE = SynchedEntityData.defineId(TamableGolem.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> ATTACK_COOLDOWN = SynchedEntityData.defineId(TamableGolem.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> ATTACK_PERFORMED = SynchedEntityData.defineId(TamableGolem.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> AGGRO = SynchedEntityData.defineId(TamableGolem.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> CHASSIS_STATUS = SynchedEntityData.defineId(TamableGolem.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> CHASSIS_HEALTH = SynchedEntityData.defineId(TamableGolem.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> BROKEN_TICK = SynchedEntityData.defineId(TamableGolem.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Byte> DATA_SADDLED_ID = SynchedEntityData.defineId(TamableGolem.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Boolean> DATA_ID_CHEST = SynchedEntityData.defineId(TamableGolem.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> CHEST_OPEN = SynchedEntityData.defineId(TamableGolem.class, EntityDataSerializers.BOOLEAN);
    public final AnimationState sitFromStandAnimState = new AnimationState();
    public final AnimationState standFromSitAnimState = new AnimationState();
    public final AnimationState brokenAnim = new AnimationState();
    public final AnimationState repairedAnim = new AnimationState();
    public final AnimationState chestOpened = new AnimationState();
    public final AnimationState chestClosed = new AnimationState();
    public final AnimationState ridden = new AnimationState();
    public final AnimationState unRide = new AnimationState();

    private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
    private int remainingPersistentAngerTime;
    @Nullable
    private UUID persistentAngerTarget;

    public static final int EQUIPMENT_SLOT_OFFSET = 400;
    public static final int CHEST_SLOT_OFFSET = 499;
    public static final int INV_SLOT_OFFSET = 500;
    public static final int SADDLE_FLAG_ID = 1;
    public static final int INV_SLOT_SADDLE = 0;
    public static final int INV_SLOT_ARMOR = 1;
    public static final int INV_BASE_COUNT = 2;
    public int sprintCounter;
    protected boolean isJumping;
    public SimpleContainer inventory;
    private boolean canEquipChest;

    protected TamableGolem(EntityType<? extends TamableGolem> type, Level level) {
        super(type, level);
        this.createInventory();
    }

    ////////////////////////////////////// DATA START //////////////////////////////////////

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(IS_PLAYER_CREATED, false);
        entityData.define(IS_ATTACKING, false);
        entityData.define(IS_AOE_ATTACKING, false);
        entityData.define(AOE_TIMER, 100);
        entityData.define(ATTACK_TYPE, 0);
        entityData.define(ATTACK_COOLDOWN, 0);
        entityData.define(ATTACK_PERFORMED, false);
        entityData.define(AGGRO, true);
        entityData.define(CHASSIS_HEALTH, 5);
        entityData.define(CHASSIS_STATUS, false);
        entityData.define(BROKEN_TICK, 0);
        entityData.define(DATA_SADDLED_ID, (byte) 0);
        entityData.define(DATA_ID_CHEST, false);
        entityData.define(CHEST_OPEN, false);
    }

    public void addAdditionalSaveData(@NotNull CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("AoeTimer", getAoeTimer());
        tag.putInt("AttackType", getAttackType());
        tag.putInt("AttackCooldown", getAttackCooldown());
        tag.putBoolean("AttackPerformed", getAttackPerformed());
        tag.putBoolean("PlayerCreated", isPlayerCreated());
        if (!inventory.getItem(0).isEmpty())
            tag.put("SaddleItem", inventory.getItem(0).save(new CompoundTag()));
        tag.putBoolean("ChestOpened", chestOpened());
        tag.putBoolean("Chested", hasChest());
        if (hasChest()) {
            ListTag listtag = new ListTag();
            for(int i = 2; i < inventory.getContainerSize(); ++i) {
                ItemStack itemstack = inventory.getItem(i);
                if (!itemstack.isEmpty()) {
                    CompoundTag compoundtag = new CompoundTag();
                    compoundtag.putByte("Slot", (byte)i);
                    itemstack.save(compoundtag);
                    listtag.add(compoundtag);
                }
            }
            tag.put("Items", listtag);
        }
        tag.putBoolean("AggroState", getAggroState());
        tag.putInt("ChassisValue", getChassisHealth());
        tag.putBoolean("ChassisState", isChassisBroken());
        tag.putInt("BrokenTick", getBrokenTick());
        addPersistentAngerSaveData(tag);
    }
    public void readAdditionalSaveData(@NotNull CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        setPlayerCreated(tag.getBoolean("PlayerCreated"));
        setAoeTimer(tag.getInt("AoeTimer"));
        setAttackType(tag.getInt("AttackType"));
        setAttackCooldown(tag.getInt("AttackCooldown"));
        setAttackPerformed(tag.getBoolean("AttackPerformed"));
        if (tag.contains("SaddleItem", 10)) {
            ItemStack itemStack = ItemStack.of(tag.getCompound("SaddleItem"));
            if (itemStack.is(Items.SADDLE)) inventory.setItem(0, itemStack);
        }
        isChestOpened(tag.getBoolean("ChestOpened"));
        setChest(tag.getBoolean("Chested"));
        if (hasChest()) {
            ListTag listtag = tag.getList("Items", 10);
            for(int i = 0; i < listtag.size(); ++i) {
                CompoundTag compoundtag = listtag.getCompound(i);
                int j = compoundtag.getByte("Slot") & 255;
                if (j >= 2 && j < inventory.getContainerSize()) {
                    inventory.setItem(j, ItemStack.of(compoundtag));
                }
            }
        }
        updateContainerEquipment();
        setAggroState(tag.getBoolean("AggroState"));
        setChassisHealth(tag.getInt("ChassisValue"));
        setChassisState(tag.getBoolean("ChassisState"));
        setBrokenTick(tag.getInt("BrokenTick"));
        readPersistentAngerSaveData(level(), tag);
    }

    public boolean isPlayerCreated() { return entityData.get(IS_PLAYER_CREATED); }
    public void setPlayerCreated(boolean playerMade) { entityData.set(IS_PLAYER_CREATED, playerMade); }

    public boolean hasChest() { return entityData.get(DATA_ID_CHEST); }
    public void setChest(boolean pChested) { entityData.set(DATA_ID_CHEST, pChested); }

    public void setAttacking(boolean attacking) { entityData.set(IS_ATTACKING, attacking); }
    public boolean isAttacking() { return entityData.get(IS_ATTACKING); }
    
    public void setAoeAttacking(boolean b) { entityData.set(IS_AOE_ATTACKING, b); }
    public boolean isAoeAttacking() { return entityData.get(IS_AOE_ATTACKING); }

    public int getAoeTimer() { return entityData.get(AOE_TIMER); }
    public void setAoeTimer(int i) { entityData.set(AOE_TIMER, i); }
    public void resetAoeTimer() { setAoeTimer(320); }

    public void setAttackType(int type) { entityData.set(ATTACK_TYPE, type); }
    public int getAttackType() { return entityData.get(ATTACK_TYPE); }

    public void setAttackCooldown(int type) { entityData.set(ATTACK_COOLDOWN, type); }
    public int getAttackCooldown() { return entityData.get(ATTACK_COOLDOWN); }

    public void setAttackPerformed(boolean type) { entityData.set(ATTACK_PERFORMED, type); }
    public boolean getAttackPerformed() { return entityData.get(ATTACK_PERFORMED); }

    public void setAggroState(boolean state) { entityData.set(AGGRO, state); }
    public boolean getAggroState() { return entityData.get(AGGRO); }

    public boolean chestOpened() { return entityData.get(CHEST_OPEN); }
    public void isChestOpened(boolean open) { entityData.set(CHEST_OPEN, open); }

    public int getChassisHealth() { return entityData.get(CHASSIS_HEALTH); }
    public void setChassisHealth(int health) { entityData.set(CHASSIS_HEALTH, health); }

    public void setChassisState(boolean state) { entityData.set(CHASSIS_STATUS, state); }
    public boolean isChassisBroken() { return entityData.get(CHASSIS_STATUS); }

    public int getBrokenTick() { return entityData.get(BROKEN_TICK); }
    public void setBrokenTick(int i) { entityData.set(BROKEN_TICK, i); }

    ////////////////////////////////////// DATA END //////////////////////////////////////

    ////////////////////////////////////// INVENTORY START //////////////////////////////////////

    /// Returns the created inventory size
    protected int getInventorySize() {
        return hasChest() ? 17 : INV_BASE_COUNT;
    }

    public int getInventoryColumns() { return 5; }

    protected void playChestEquipsSound() {
        playSound(ESoundEvents.STEEL_GOLEM_CHESTED.get(), 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
    }

    public void equipChest(Player pPlayer, ItemStack pChestStack) {
        setChest(true);
        playChestEquipsSound();
        if (!pPlayer.getAbilities().instabuild)
            pChestStack.shrink(1);
        createInventory();
    }

    protected void createInventory() {
        SimpleContainer simplecontainer = inventory;
        inventory = new SimpleContainer(getInventorySize());
        if (simplecontainer != null) {
            simplecontainer.removeListener(this);
            int i = Math.min(simplecontainer.getContainerSize(), inventory.getContainerSize());

            for(int j = 0; j < i; ++j) {
                ItemStack itemstack = simplecontainer.getItem(j);
                if (!itemstack.isEmpty()) {
                    inventory.setItem(j, itemstack.copy());
                }
            }
        }

        inventory.addListener(this);
        updateContainerEquipment();
        itemHandler = LazyOptional.of(() -> new InvWrapper(inventory));
    }

    protected void updateContainerEquipment() {
        if (!level().isClientSide) {
            setSaddle(inventory.getItem(0));
        }
    }

    @Override
    public void containerChanged(@NotNull Container pContainer) {
        boolean flag = isSaddled();
        updateContainerEquipment();
        if (tickCount > 20 && !flag && isSaddled()) {
            playSound(getSaddleSoundEvent(), 0.5F, 1.0F);
        }
    }

    public boolean isSaddleable() { return isAlive() && isTame(); }

    public boolean isSaddled() { return !getItemBySlot(EquipmentSlot.HEAD).isEmpty(); }

    private void setSaddle(ItemStack stack) {
        setItemSlot(EquipmentSlot.HEAD, stack);
        setDropChance(EquipmentSlot.HEAD, 0.0F);
    }

    public void equipSaddle(@Nullable SoundSource source) {
        inventory.setItem(INV_SLOT_SADDLE, new ItemStack(Items.SADDLE));
        if (source != null) level().playSound(null, this, ESoundEvents.STEEL_GOLEM_SADDLED.get(), source, 0.5F, 1.0F);
    }

    public void equipArmor(@NotNull Player player, @NotNull ItemStack stack) {
        if (isArmor(stack)) {
            inventory.setItem(1, stack.copyWithCount(1));
            if (!player.getAbilities().instabuild) stack.shrink(1);
        }
    }

    public void openCustomInventoryScreen(@NotNull Player pPlayer) { }

    public InteractionResult openInventory(Player player) {
        if (player instanceof ServerPlayer serverPlayer) {
            NetworkHooks.openScreen(serverPlayer, this);
        }
        openCustomInventoryScreen(player);
        if (hasChest()) level().playSound(null, blockPosition(), SoundEvents.CHEST_OPEN, SoundSource.NEUTRAL, 0.5F, 1);
        return InteractionResult.sidedSuccess(level().isClientSide);
    }

    protected InteractionResult doPlayerRide(Player pPlayer) {
        setOrderedToSit(false);
        setInSittingPose(false);
        if (!level().isClientSide) {
            pPlayer.setYRot(getYRot());
            pPlayer.setXRot(getXRot());
            pPlayer.startRiding(this);
        }
        return InteractionResult.SUCCESS;
    }

    protected void dropEquipment() {
        super.dropEquipment();
        if (inventory != null) {
            for(int i = 0; i < inventory.getContainerSize(); ++i) {
                ItemStack itemstack = inventory.getItem(i);
                if (!itemstack.isEmpty() && !EnchantmentHelper.hasVanishingCurse(itemstack)) {
                    spawnAtLocation(itemstack);
                }
            }

        }
        if (hasChest()) {
            if (!level().isClientSide) {
                spawnAtLocation(Blocks.CHEST);
            }

            setChest(false);
        }
    }public boolean canWearArmor() {
        return false;
    }

    public boolean isWearingArmor() {
        return !getItemBySlot(EquipmentSlot.CHEST).isEmpty();
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

//    public @NotNull SlotAccess getSlot(int pSlot) {
//        return pSlot == CHEST_SLOT_OFFSET ? new SlotAccess() {
//            public @NotNull ItemStack get() {
//                return TamableGolem.this.hasChest() ? new ItemStack(Items.CHEST) : ItemStack.EMPTY;
//            }
//
//            public boolean set(@NotNull ItemStack stack) {
//                if (stack.isEmpty()) {
//                    if (TamableGolem.this.hasChest()) {
//                        TamableGolem.this.setChest(false);
//                        TamableGolem.this.createInventory();
//                    }
//                    return true;
//                } else if (stack.is(Items.CHEST)) {
//                    if (!TamableGolem.this.hasChest()) {
//                        TamableGolem.this.setChest(true);
//                        TamableGolem.this.createInventory();
//                    }
//                    return true;
//                } else return false;
//            }
//        } : this.getSlot1(pSlot);
//    }
//
//    public SlotAccess getSlot1(int slot) {
//        int i = slot - EQUIPMENT_SLOT_OFFSET;
//        if (i >= INV_SLOT_SADDLE && i < INV_BASE_COUNT && i < this.inventory.getContainerSize()) {
//            if (i == INV_SLOT_SADDLE) {
//                return this.createEquipmentSlotAccess(i, (stack) -> stack.isEmpty() || stack.is(Items.SADDLE));
//            }
//            if (!this.canWearArmor()) {
//                return SlotAccess.NULL;
//            }
//            return this.createEquipmentSlotAccess(i, (p_149516_) -> p_149516_.isEmpty() || this.isArmor(p_149516_));
//        }
//        int j = slot - INV_SLOT_OFFSET + 2;
//        return j >= 2 && j < this.inventory.getContainerSize() ? SlotAccess.forContainer(this.inventory, j) : super.getSlot(slot);
//    }

    public @NotNull SlotAccess getSlot(int slot) {
        int slot1 = slot - EQUIPMENT_SLOT_OFFSET;

        if (slot == CHEST_SLOT_OFFSET)
            return new SlotAccess() {
                @Override
                public @NotNull ItemStack get() {
                    return TamableGolem.this.hasChest() ? new ItemStack(Items.CHEST) : ItemStack.EMPTY;
                }
                @Override
                public boolean set(@NotNull ItemStack carried) {
                    if (carried.isEmpty()) {
                        if (TamableGolem.this.hasChest()) {
                            TamableGolem.this.setChest(false);
                            TamableGolem.this.createInventory();
                        }
                        return true;
                    } else if (carried.is(Items.CHEST)) {
                        if (!TamableGolem.this.hasChest()) {
                            TamableGolem.this.setChest(true);
                            TamableGolem.this.createInventory();
                        }
                        return true;
                    } else return false;
                }
            };
        else if (slot1 >= INV_SLOT_SADDLE && slot1 < INV_BASE_COUNT && slot1 < inventory.getContainerSize()) {
            if (slot1 == INV_SLOT_SADDLE) return createEquipmentSlotAccess(slot1, stack -> stack.isEmpty() || stack.is(Items.SADDLE));
            if (!canWearArmor()) return SlotAccess.NULL;
            return createEquipmentSlotAccess(slot1, stack -> stack.isEmpty() || isArmor(stack));
        }
        int invOffset = slot - INV_SLOT_OFFSET + INV_BASE_COUNT;
        return invOffset >= 2 && invOffset < inventory.getContainerSize() ? SlotAccess.forContainer(inventory, invOffset) : super.getSlot(slot);
    }

    public boolean canEquipChest() {
        return canEquipChest;
    }

    public void setCanEquipChest(boolean canEquipChest) {
        this.canEquipChest = canEquipChest;
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int pContainerId, @NotNull Inventory pPlayerInventory, @NotNull Player pPlayer) {
        return null;
    }

    public boolean hasInventoryChanged(@NotNull Container pInventory) { return inventory != pInventory; }

    ////////////////////////////////////// INVENTORY END //////////////////////////////////////

    ////////////////////////////////////// ENTITY LOGIC START //////////////////////////////////////

    public boolean isPushable() { return !isVehicle(); }

    @Override
    public boolean dismountsUnderwater() { return false; }

    /// <p>When the {@link LivingEntity#die(DamageSource)} triggers, it checks for this method.</p>
    /// <p>If it returns true the golem dies, otherwise it goes into a "broken" state.
    public boolean isChassisCompromised() { return false; }

    @Override
    public boolean causeFallDamage(float pFallDistance, float pMultiplier, @NotNull DamageSource pSource) { return false; }

    @Override
    public @Nullable AgeableMob getBreedOffspring(@NotNull ServerLevel pLevel, @NotNull AgeableMob pOtherParent) { return null; }

    protected int decreaseAirSupply(int pAir) { return pAir; }

    @Override
    public boolean isAffectedByPotions() { return !isChassisBroken() && super.isAffectedByPotions(); }

    @Override
    public boolean attackable() { return !isChassisBroken() && super.attackable(); }

    @Override
    public boolean canAttack(@NotNull LivingEntity pTarget) { return !isChassisBroken() && super.canAttack(pTarget); }

    @Override
    public boolean canBeLeashed(@NotNull Player player) { return !isLeashed() && !isAngry(); }

    @Override
    public boolean isAttackable() { return !isChassisBroken(); }

    @Override
    protected boolean isImmobile() {
        return isChassisBroken() || (isPlayerCreated() && !isTame()) || super.isImmobile();
    }

    public void startPersistentAngerTimer() { setRemainingPersistentAngerTime(PERSISTENT_ANGER_TIME.sample(random)); }

    public void setRemainingPersistentAngerTime(int time) { remainingPersistentAngerTime = time; }

    public int getRemainingPersistentAngerTime() { return remainingPersistentAngerTime; }

    public void setPersistentAngerTarget(@Nullable UUID target) { persistentAngerTarget = target; }

    @Nullable
    public UUID getPersistentAngerTarget() { return persistentAngerTarget; }

    public boolean canAttackType(@NotNull EntityType<?> type) {
        if (isPlayerCreated() && type == EntityType.PLAYER) return false;
        else return type != EntityType.CREEPER && super.canAttackType(type);
    }

    @Override
    public boolean isAngry() { return !isChassisBroken() && NeutralMob.super.isAngry(); }

    ////////////////////////////////////// ENTITY LOGIC END //////////////////////////////////////
    
    ////////////////////////////////////// RIDEABLE START //////////////////////////////////////

    protected void tickRidden(@NotNull Player pPlayer, @NotNull Vec3 pTravelVector) {
        super.tickRidden(pPlayer, pTravelVector);
        Vec2 vec2 = getRiddenRotation(pPlayer);
        setRot(vec2.y, vec2.x);
        yRotO = yBodyRot = yHeadRot = getYRot();
//        if (isControlledByLocalInstance()) {
//            if (onGround()) {
//                setIsJumping(false);
//                if (playerJumpPendingScale > 0.0F && !isJumping()) {
//                    executeRidersJump(playerJumpPendingScale, pTravelVector);
//                }
//
//                playerJumpPendingScale = 0.0F;
//            }
//        }
    }

    public boolean isJumping() {
        return isJumping;
    }

    public void setIsJumping(boolean pJumping) {
        isJumping = pJumping;
    }

    @Override
    public double getRiderShieldingHeight() {
        return 1.25D;
    }

    @Override
    public @NotNull SoundEvent getSaddleSoundEvent() {
        return ESoundEvents.STEEL_GOLEM_SADDLED.get();
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

    protected void executeRidersJump(float pPlayerJumpPendingScale, Vec3 pTravelVector) {
        double d0 = 0.9 * (double)pPlayerJumpPendingScale * (double)getBlockJumpFactor();
        double d1 = d0 + (double)getJumpBoostPower();
        Vec3 vec3 = getDeltaMovement();
        setDeltaMovement(vec3.x, d1, vec3.z);
        setIsJumping(true);
        hasImpulse = true;
        net.minecraftforge.common.ForgeHooks.onLivingJump(this);
        if (pTravelVector.z > 0.0D) {
            float f = Mth.sin(getYRot() * ((float)Math.PI / 180F));
            float f1 = Mth.cos(getYRot() * ((float)Math.PI / 180F));
            setDeltaMovement(getDeltaMovement().add(-0.4F * f * pPlayerJumpPendingScale, 0.0D, 0.4F * f1 * pPlayerJumpPendingScale));
        }
    }

    @Override
    public void onPlayerJump(int pJumpPower) { }

    @Override
    public boolean canJump() {
        return false;
    }

    @Override
    public void handleStartJump(int pJumpPower) { }

    @Override
    public void handleStopJump() { }

    protected int getMaxPassengers() {
        return 1;
    }

    @Nullable
    public LivingEntity getControllingPassenger() {
        Entity entity = getFirstPassenger();
        if (!(entity instanceof Mob)) {
            if (isSaddled()) {
                entity = getFirstPassenger();
                if (entity instanceof Player player && isOwnedBy(player)) {
                    return player;
                }
            }

        }
        return null;
    }

    @Nullable
    private Vec3 getDismountLocationInDirection(Vec3 pDirection, LivingEntity passenger) {
        double d0 = getX() + pDirection.x;
        double d1 = getBoundingBox().minY;
        double d2 = getZ() + pDirection.z;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for(Pose pose : passenger.getDismountPoses()) {
            blockpos$mutableblockpos.set(d0, d1, d2);
            double d3 = getBoundingBox().maxY + 0.75D;

            while(true) {
                double d4 = level().getBlockFloorHeight(blockpos$mutableblockpos);
                if ((double)blockpos$mutableblockpos.getY() + d4 > d3) {
                    break;
                }

                if (DismountHelper.isBlockFloorValid(d4)) {
                    AABB aabb = passenger.getLocalBoundsForPose(pose);
                    Vec3 vec3 = new Vec3(d0, (double)blockpos$mutableblockpos.getY() + d4, d2);
                    if (DismountHelper.canDismountTo(level(), passenger, aabb.move(vec3))) {
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
        Vec3 vec3 = getCollisionHorizontalEscapeVector(getBbWidth(), pLivingEntity.getBbWidth(), getYRot() + (pLivingEntity.getMainArm() == HumanoidArm.RIGHT ? 90.0F : -90.0F));
        Vec3 vec31 = getDismountLocationInDirection(vec3, pLivingEntity);
        if (vec31 != null) {
            return vec31;
        } else {
            Vec3 vec32 = getCollisionHorizontalEscapeVector(getBbWidth(), pLivingEntity.getBbWidth(), getYRot() + (pLivingEntity.getMainArm() == HumanoidArm.LEFT ? 90.0F : -90.0F));
            Vec3 vec33 = getDismountLocationInDirection(vec32, pLivingEntity);
            return vec33 != null ? vec33 : position();
        }
    }
    
    ////////////////////////////////////// RIDEABLE END //////////////////////////////////////

    ////////////////////////////////////// INTERACTION START //////////////////////////////////////

    @Override
    public @NotNull InteractionResult mobInteract(Player player, @NotNull InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (canEquipChest() && itemStack.is(Items.CHEST)) {
            equipChest(player, itemStack);
            return InteractionResult.SUCCESS;
        }
        if (isArmor(itemStack)) {
            equipArmor(player, itemStack);
            return InteractionResult.SUCCESS;
        }
        if (isSaddleable() && itemStack.is(Items.SADDLE)) equipSaddle(SoundSource.NEUTRAL);
        return super.mobInteract(player, hand);
    }

    ////////////////////////////////////// INTERACTION END //////////////////////////////////////

    ////////////////////////////////////// MISC //////////////////////////////////////

    public void groundSlamAttack() { }

    public void tameGolem(Player player) {
        if (!isPlayerCreated()) setPlayerCreated(true);
        tame(player);
        navigation.stop();
        setTarget(null);
        setOrderedToSit(false);
        setInSittingPose(false);
        level().broadcastEntityEvent(this, (byte) 7);
    }

    protected LazyOptional<?> itemHandler = null;

    @Override
    public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction facing) {
        if (isAlive() && capability == ForgeCapabilities.ITEM_HANDLER && itemHandler != null) return itemHandler.cast();
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
}