package net.nokunami.elementus.common.entity.living;

import com.google.common.collect.ImmutableList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.BodyRotationControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.IForgeShearable;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.items.wrapper.InvWrapper;
import net.minecraftforge.network.PacketDistributor;
import net.nokunami.elementus.common.Etags;
import net.nokunami.elementus.common.config.EntityConfig;
import net.nokunami.elementus.common.entity.ai.control.TestBodyControl;
import net.nokunami.elementus.common.entity.ai.goal.steelGolem.GolemFollowOwnerGoal;
import net.nokunami.elementus.common.entity.ai.goal.steelGolem.SteelGolemAttackGoal;
import net.nokunami.elementus.common.entity.ai.goal.steelGolem.SteelGolemOwnerHurtByTargetGoal;
import net.nokunami.elementus.common.entity.ai.goal.steelGolem.SteelGolemOwnerHurtTargetGoal;
import net.nokunami.elementus.common.entity.ai.navigation.TestGroundNavigation;
import net.nokunami.elementus.common.inventory.SteelGolemInventoryMenu;
import net.nokunami.elementus.common.item.SteelGolemUpgradeItem;
import net.nokunami.elementus.common.network.ModNetwork;
import net.nokunami.elementus.common.network.SteelGolemInventoryPacket;
import net.nokunami.elementus.common.registry.ModEntityType;
import net.nokunami.elementus.common.registry.ModSoundEvents;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static net.nokunami.elementus.Elementus.modLoc;
import static net.nokunami.elementus.common.entity.ModParticleUtil.spawnParticlesOnEntity;
import static net.nokunami.elementus.common.entity.ModParticleUtil.spawnWideParticlesOnEntity;

@SuppressWarnings("deprecation")
public class SteelGolem extends TamableChestedGolem implements NeutralMob, Shearable, IForgeShearable {
    private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
    private int remainingPersistentAngerTime;
    @javax.annotation.Nullable
    private UUID persistentAngerTarget;
    protected static final EntityDataAccessor<Byte> DATA_FLAGS_ID = SynchedEntityData.defineId(SteelGolem.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Boolean> IS_ATTACKING = SynchedEntityData.defineId(SteelGolem.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IS_AOE_ATTACKING = SynchedEntityData.defineId(SteelGolem.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> AOE_TIMER = SynchedEntityData.defineId(SteelGolem.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> CHASSIS_STATUS = SynchedEntityData.defineId(SteelGolem.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> AGGRO = SynchedEntityData.defineId(SteelGolem.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IS_WAXED = SynchedEntityData.defineId(SteelGolem.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> CHASSIS_HEALTH = SynchedEntityData.defineId(SteelGolem.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> ATTACK_TYPE = SynchedEntityData.defineId(SteelGolem.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> MOSS_TIMER = SynchedEntityData.defineId(SteelGolem.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> MOSS_STAGE = SynchedEntityData.defineId(SteelGolem.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> BROKEN_TICK = SynchedEntityData.defineId(SteelGolem.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> SIT_TICK = SynchedEntityData.defineId(SteelGolem.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> CHEST_OPEN = SynchedEntityData.defineId(SteelGolem.class, EntityDataSerializers.BOOLEAN);
    public final AnimationState leftAttackAnimationState = new AnimationState();
    public final AnimationState leftAttackEndAnimState = new AnimationState();
    public final AnimationState rightAttackAnimationState = new AnimationState();
    public final AnimationState rightAttackEndAnimState = new AnimationState();
    public final AnimationState upswingAttackAnimState = new AnimationState();
    public final AnimationState upswingAttackEndAnimState = new AnimationState();
    public final AnimationState sitFromStandAnimState = new AnimationState();
    public final AnimationState standFromSitAnimState = new AnimationState();
    public final AnimationState brokenAnim = new AnimationState();
    public final AnimationState repairedAnim = new AnimationState();
    public final AnimationState ridden = new AnimationState();
    public final AnimationState unRide = new AnimationState();
    public final AnimationState chestOpened = new AnimationState();
    public final AnimationState chestClosed = new AnimationState();
    private static final EntityDimensions SITTING_DIMENSIONS = EntityDimensions.scalable(ModEntityType.STEEL_GOLEM.get().getWidth(), ModEntityType.STEEL_GOLEM.get().getHeight() - 0.5F);
    private static final EntityDimensions CROUCHING_DIMENSIONS = EntityDimensions.scalable(ModEntityType.STEEL_GOLEM.get().getWidth(), ModEntityType.STEEL_GOLEM.get().getHeight() - 1F);
    public int attackAnimTimeout = 0;
    public int aoeAttackAnimTimeout = 0;
    public int aoeAnimTimeout = 160;
    private static final UUID ARMOR_MODIFIER_UUID = UUID.fromString("556E1665-8B10-40C8-8F9D-CF9B1667F295");
    private static final UUID TOUGHNESS_MODIFIER_UUID = UUID.fromString("0e936964-c9bb-4eb7-a718-ed69de8a4e8f");

    public SteelGolem(EntityType<? extends TamableChestedGolem> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        createInventory();
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_FLAGS_ID, (byte)0);
        this.entityData.define(IS_ATTACKING, false);
        this.entityData.define(CHASSIS_HEALTH, 5);
        this.entityData.define(CHASSIS_STATUS, false);
        this.entityData.define(AGGRO, true);
        this.entityData.define(IS_WAXED, false);
        this.entityData.define(ATTACK_TYPE, 0);
        this.entityData.define(MOSS_TIMER, 0);
        this.entityData.define(MOSS_STAGE, 0);
        this.entityData.define(BROKEN_TICK, 0);
        this.entityData.define(SIT_TICK, 0);
        this.entityData.define(AOE_TIMER, 100);
        this.entityData.define(IS_AOE_ATTACKING, false);
        this.entityData.define(CHEST_OPEN, false);
    }

    public void addAdditionalSaveData(@NotNull CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        this.addPersistentAngerSaveData(tag);
        tag.putBoolean("PlayerCreated", this.isPlayerCreated());
        tag.putInt("ChassisValue", this.getChassisHealth());
        tag.putBoolean("ChassisState", this.isChassisBroken());
        tag.putBoolean("AggroState", this.getAggroState());
        tag.putBoolean("Waxed", this.isWaxed());
        tag.putInt("AttackType", this.getAttackType());
        tag.putInt("MossTimer", this.getMossTimer());
        tag.putInt("MossStage", this.getMossStage());
        tag.putBoolean("Chested", this.hasChest());
        if (!this.inventory.getItem(1).isEmpty()) {
            tag.put("ArmorItem", this.inventory.getItem(1).save(new CompoundTag()));
        }
        if (!this.inventory.getItem(2).isEmpty()) {
            tag.put("LeavesDecoration", this.inventory.getItem(2).save(new CompoundTag()));
        }
        if (!this.inventory.getItem(3).isEmpty()) {
            tag.put("DecorItem", this.inventory.getItem(3).save(new CompoundTag()));
        }
        tag.putInt("BrokenTick", this.getBrokenTick());
        tag.putInt("SitTick", this.getSitTick());
        tag.putInt("SitTick", this.getSitTick());
        tag.putInt("AoeTimer", this.getAoeTimer());
        tag.putBoolean("ChestOpened", this.chestOpened());
    }

    public ItemStack getArmor() {
        return this.getItemBySlot(EquipmentSlot.CHEST);
    }

    private void setArmor(ItemStack stack) {
        this.setItemSlot(EquipmentSlot.CHEST, stack);
        this.setDropChance(EquipmentSlot.CHEST, 0.0F);
    }

    public ItemStack isCamouflaged() {
        return this.getItemBySlot(EquipmentSlot.LEGS);
    }

    private void wearCamouflaged(ItemStack stack) {
        this.setItemSlot(EquipmentSlot.LEGS, stack);
        this.setDropChance(EquipmentSlot.LEGS, 0.0F);
    }

    public ItemStack getDripCarpet() {
        return this.getItemBySlot(EquipmentSlot.FEET);
    }

    private void wearDripCarpet(ItemStack stack) {
        this.setItemSlot(EquipmentSlot.FEET, stack);
        this.setDropChance(EquipmentSlot.FEET, 0.0F);
    }

    public boolean chestOpened() {
        return this.entityData.get(CHEST_OPEN);
    }

    public void isChestOpened(boolean open) {
        this.entityData.set(CHEST_OPEN, open);
    }

    public void readAdditionalSaveData(@NotNull CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setPlayerCreated(tag.getBoolean("PlayerCreated"));
        this.readPersistentAngerSaveData(this.level(), tag);
        this.setChassisHealth(tag.getInt("ChassisValue"));
        this.setChassisState(tag.getBoolean("ChassisState"));
        this.setAggroState(tag.getBoolean("AggroState"));
        this.setWaxed(tag.getBoolean("Waxed"));
        this.setAttackType(tag.getInt("AttackType"));
        this.setMossTimer(tag.getInt("MossTimer"));
        this.setMossStage(tag.getInt("MossStage"));
        if (tag.contains("ArmorItem", 10)) {
            ItemStack armorItem = ItemStack.of(tag.getCompound("ArmorItem"));
            if (!armorItem.isEmpty() && this.isArmor(armorItem)) {
                this.inventory.setItem(1, armorItem);
            }
        }
        if (tag.contains("LeavesDecoration", 10)) {
            ItemStack leaves = ItemStack.of(tag.getCompound("LeavesDecoration"));
            if (leaves.is(Etags.Items.STEEL_GOLEM_LEAVES_DECORATION)) {
                this.inventory.setItem(2, leaves);
            }
        }
        if (tag.contains("DecorItem", 10)) {
            this.inventory.setItem(3, ItemStack.of(tag.getCompound("DecorItem")));
        }
        this.updateContainerEquipment();
        this.setBrokenTick(tag.getInt("BrokenTick"));
        this.setSitTick(tag.getInt("SitTick"));
        this.setAoeTimer(tag.getInt("AoeTimer"));
        this.isChestOpened(tag.getBoolean("ChestOpened"));
    }

    @Override
    protected void updateContainerEquipment() {
        if (!this.level().isClientSide) {
            super.updateContainerEquipment();
            this.setArmorEquipment(this.inventory.getItem(1));
            this.setDropChance(EquipmentSlot.CHEST, 0.0F);
            this.wearCamouflaged(this.inventory.getItem(2));
            this.setDropChance(EquipmentSlot.LEGS, 0.0F);
            this.wearDripCarpet(this.inventory.getItem(3));
            this.setDropChance(EquipmentSlot.FEET, 0.0F);
        }
    }

    private void setArmorEquipment(ItemStack pStack) {
        this.setArmor(pStack);
        if (!this.level().isClientSide) {
            this.getAttribute(Attributes.ARMOR).removeModifier(ARMOR_MODIFIER_UUID);
            this.getAttribute(Attributes.ARMOR_TOUGHNESS).removeModifier(TOUGHNESS_MODIFIER_UUID);
            if (this.isArmor(pStack)) {
                int armor = ((SteelGolemUpgradeItem)pStack.getItem()).getProtection();
                int toughness = ((SteelGolemUpgradeItem)pStack.getItem()).getProtection();
                if (armor != 0) {
                    this.getAttribute(Attributes.ARMOR).addTransientModifier(new AttributeModifier(ARMOR_MODIFIER_UUID, "Armor bonus", armor, AttributeModifier.Operation.ADDITION));
                }
                if (toughness != 0) {
                    this.getAttribute(Attributes.ARMOR_TOUGHNESS).addTransientModifier(new AttributeModifier(TOUGHNESS_MODIFIER_UUID, "Toughness", toughness, AttributeModifier.Operation.ADDITION));
                }
            }
        }
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

    @Override
    public void containerChanged(@NotNull Container container) {
        ItemStack armor = this.getArmor();
        ItemStack leaves = this.isCamouflaged();
        ItemStack carpet = this.getDripCarpet();
        super.containerChanged(container);
        ItemStack armor1 = this.getArmor();
        ItemStack leaves1 = this.isCamouflaged();
        ItemStack carpet1 = this.getDripCarpet();
        if (this.tickCount > 20 && this.isArmor(armor1) && armor != armor1) {
            this.playSound(ModSoundEvents.STEEL_GOLEM_ARMORED.get(), 0.5F, 1.0F);
        }
        if (this.tickCount > 20 && leaves != leaves1) {
            this.playSound(ModSoundEvents.STEEL_GOLEM_LEAVES_SWAG.get(), 0.5F, 1.0F);
        }
        if (this.tickCount > 20 && carpet != carpet1) {
            this.playSound(ModSoundEvents.STEEL_GOLEM_CARPET_SWAG.get(), 0.5F, 1.0F);
        }
    }

    protected SoundEvent getDeathSound() {
        return isChassisCompromised() ? SoundEvents.IRON_GOLEM_DEATH : ModSoundEvents.STEEL_GOLEM_DOWN.get();
    }

    protected SoundEvent getHurtSound(@NotNull DamageSource pDamageSource) {
        return this.isChassisCompromised() ? null : SoundEvents.IRON_GOLEM_HURT;
    }

    @Override
    public @NotNull InteractionResult interactAt(@NotNull Player player, @NotNull Vec3 vec3, @NotNull InteractionHand hand) {
        double d0 = vec3.y;
        boolean saddleHeight = this.isInSittingPose() ? d0 >= 1.6D : d0 >= 1.9D;
        if (this.isTame()) {
            if (!this.isChassisBroken()) {
                if (this.isSaddled() && saddleHeight) {
                    if (!player.isCrouching()) {
                        return this.doPlayerRide(player);
                    } else if (!this.getPassengers().isEmpty()) {
                        this.ejectPassengers();
                        return InteractionResult.SUCCESS;
                    }
                }
                this.mobInteract(player, hand);
                return InteractionResult.SUCCESS;
            } else return InteractionResult.PASS;
        } else return super.interactAt(player, vec3, hand);
    }

    @Override
    public @NotNull InteractionResult mobInteract(@NotNull Player player, @NotNull InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        boolean shearsItem = itemStack.is(Items.SHEARS);
        boolean leaves = itemStack.is(Etags.Items.STEEL_GOLEM_LEAVES_DECORATION);
        boolean carpet = itemStack.is(Etags.Items.STEEL_GOLEM_CARPET_DECORATION);
        boolean wax = itemStack.is(Items.HONEYCOMB);
        boolean chassisCondition = this.getChassisHealth() < 5;
        boolean healItem = itemStack.is(Etags.Items.STEEL_GOLEM_HEAL);
        boolean repairHalf = itemStack.is(Etags.Items.STEEL_GOLEM_REPAIR_HALF);
        boolean repairFull = itemStack.is(Etags.Items.STEEL_GOLEM_REPAIR_FULL);
        boolean heal = (healItem || repairFull) && !repairHalf;
        boolean repair = (repairHalf || repairFull) && !healItem;
        InteractionResult superMobInteract = super.mobInteract(player, hand);
        boolean combat = (itemStack.is(ItemTags.SWORDS) || itemStack.is(ItemTags.AXES));

        if (this.getHealth() < this.getMaxHealth() && ((isChassisBroken() && repair) || (!isChassisBroken() && chassisCondition && heal) || (!isChassisBroken() && healItem))) {
            healGolem(player, itemStack);
            return InteractionResult.SUCCESS;
        }
        if (this.isTame()) {
            if (!this.isChassisBroken()) {
                if (player.isSecondaryUseActive() && combat) {
                    this.navigation.stop();
                    this.setAggroState(!this.getAggroState());
                    this.setTarget(null);
                    return InteractionResult.SUCCESS;
                }
                if (this.getMossStage() > 0 && this.readyForShearing() && shearsItem) {
                    this.shear(SoundSource.PLAYERS);
                    this.gameEvent(GameEvent.SHEAR, player);
                    itemStack.hurtAndBreak(1, player, (item) -> item.broadcastBreakEvent(hand));
                    return InteractionResult.SUCCESS;
                }
                if ((this.isCamouflaged().isEmpty() && leaves) || (this.getDripCarpet().isEmpty() && carpet) || (!this.isWaxed() && wax) || (this.isWaxed() && itemStack.is(ItemTags.AXES))) {
                    this.setGolemInteration(player, itemStack);
                    return InteractionResult.SUCCESS;
                }
                if (!superMobInteract.consumesAction() && this.isOwnedBy(player)) {
                    return sitOrder();
                } else return superMobInteract;
            } else return superMobInteract;
        } else if (this.isPlayerCreated() || (!this.isPlayerCreated() && player.isCreative())) {
            if (!this.isPlayerCreated()) this.setPlayerCreated(true);
            this.tame(player);
            this.navigation.stop();
            this.setTarget(null);
            return InteractionResult.SUCCESS;
        } else return superMobInteract;
    }

    public InteractionResult sitOrder() {
        if (this.getSitTick() <= 0) {
            this.setOrderedToSit(!this.isOrderedToSit());
            this.setSitTick(15);
        }
        this.jumping = false;
        this.navigation.stop();
        return InteractionResult.SUCCESS;
    }

    protected void healGolem(Player player, ItemStack stack) {
        boolean chassisCondition = this.getChassisHealth() < 5;
        boolean healItem = stack.is(Etags.Items.STEEL_GOLEM_HEAL);
        boolean repairHalf = stack.is(Etags.Items.STEEL_GOLEM_REPAIR_HALF);
        boolean repairFull = stack.is(Etags.Items.STEEL_GOLEM_REPAIR_FULL);
        boolean repair = (repairHalf || repairFull) && !healItem;
        float randomFloat = 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F;

        float healAmount = 1;
        if (repair) healAmount = 0.5F;

        this.setChassisState(false);
        if (chassisCondition && repairFull) setChassisHealth(getChassisHealth() + 1);
        this.heal((int) (EntityConfig.MaxHealth / EntityConfig.RepairAmount) * healAmount);
        this.playSound(isChassisBroken() ? ModSoundEvents.STEEL_GOLEM_REVIVE.get() : ModSoundEvents.STEEL_GOLEM_REPAIR.get(), 1.0F, randomFloat);
        if (!player.isCreative()) stack.shrink(1);
    }

    @Override
    protected void positionRider(@NotNull Entity passenger, @NotNull MoveFunction moveFunction) {
        if (this.hasPassenger(passenger)) {
            float dro = 0.75F;
            double ridingOffset = isInSittingPose() ? (this.getPassengersRidingOffset() + (double) ((getSitTick() + 10) / 10)) : (this.getPassengersRidingOffset() - (double) ((getSitTick() + 10) / 10));
            Vec3 offset = (switch (this.getPassengers().stream().toList().indexOf(passenger)) {
                case 0 -> new Vec3(0, ridingOffset + passenger.getMyRidingOffset(), 0);
                case 1 -> new Vec3(0, ridingOffset + passenger.getMyRidingOffset(), -dro);
                case 2 -> new Vec3(0, ridingOffset + passenger.getMyRidingOffset(), dro);
                default -> Vec3.ZERO;
            }).yRot((float) (-Math.toRadians(this.getYRot()) - Math.PI / 2));
            moveFunction.accept(passenger, this.getX() + offset.x, this.getY() + offset.y, this.getZ() + offset.z);
        }
    }

    @Override
    protected boolean canAddPassenger(@NotNull Entity pPassenger) {
        return this.getPassengers().size() < this.getMaxPassengers();
    }

    @Override
    protected int getMaxPassengers() {
        return 3;
    }

    public boolean hasEnoughSpaceFor(Entity pEntity) {
        return pEntity.getBbWidth() < this.getBbWidth() || pEntity.getBbHeight() < this.getBbHeight();
    }

    @Override
    public boolean canWearArmor() {
        return true;
    }

    @Override
    public boolean isArmor(@NotNull ItemStack stack) {
        return stack.getItem() instanceof SteelGolemUpgradeItem;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, EntityConfig.MaxHealth)
                .add(Attributes.MOVEMENT_SPEED, EntityConfig.MovementSpeed)
                .add(Attributes.KNOCKBACK_RESISTANCE, EntityConfig.KnockbackResist)
                .add(Attributes.ATTACK_DAMAGE, EntityConfig.AttackDamage)
                .add(Attributes.ARMOR, EntityConfig.Armor)
                .add(Attributes.ARMOR_TOUGHNESS, EntityConfig.Toughness);
    }

    @Override
    public void baseTick() {
        super.baseTick();
        if (this.level().isClientSide) {
            setupAnim();
        }

        if (!this.level().isClientSide) {
            this.updatePersistentAnger((ServerLevel)this.level(), true);

            if (getAoeTimer() > 0 && this.getTarget() != null)
                setAoeTimer(getAoeTimer() - 1);

            if(isAoeAttacking() && aoeAttackAnimTimeout <= 0 && getAoeTimer() <= 0) {
                spawnWideParticlesOnEntity(this.level(), this.position(), ParticleTypes.SMOKE, this, UniformInt.of(8, 10));
                spawnWideParticlesOnEntity(this.level(), this.position(), ParticleTypes.LARGE_SMOKE, this, UniformInt.of(4, 6));
            }

            if (this.isInSittingPose() || this.isChassisBroken()) {
                if (this.getMossStage() < 3 && !this.isWaxed()) this.setMossTimer(getMossTimer() + 1);
                if (this.getSitTick() > 0) this.setSitTick(getSitTick() - 1);
                this.setPose(Pose.SITTING);
            } else {
                if (this.getSitTick() > 0) this.setSitTick(getSitTick() - 1);
                this.setPose(Pose.STANDING);
            }

            if (this.isChassisBroken()) {
                this.setBrokenTick(20);
                this.navigation.stop();
            } else {
                if (this.getBrokenTick() > 0) this.setBrokenTick(getBrokenTick() - 1);
            }

            if (this.getMossTimer() > 144000) {
                this.setMossStage(this.getMossStage() + 1);
                this.setMossTimer(0);
            }

            if (!this.getArmor().isEmpty() && this.getArmor().getItem() instanceof SteelGolemUpgradeItem armorItem) {
                armorItem.onArmorTick(this.getArmor(), this.level(), this);
            }

            setSprinting(this.isSprinting());
        }

        setPose(isInWall() ? Pose.CROUCHING : this.getPose());
        refreshDimensions();

        List<Entity> list = this.level().getEntities(this, this.getBoundingBox().inflate(0.2F, -0.01F, 0.2F), EntitySelector.pushableBy(this));
        if (!list.isEmpty() && this.isInSittingPose()) {
            boolean flag = !this.level().isClientSide && !(this.getControllingPassenger() instanceof Player);

            for (Entity entity : list) {
                if (this.isSaddled() && flag && this.getPassengers().size() < this.getMaxPassengers() && this.hasEnoughSpaceFor(entity) && !(entity instanceof Enemy)) {
                    if (!entity.isPassenger() && entity instanceof LivingEntity && !(entity instanceof WaterAnimal) && !(entity instanceof Player)) {
                        entity.startRiding(this);
                    }
                } else {
                    this.push(entity);
                }
            }
        }

        Set<Entity> hitSet = new HashSet<>(level().getEntitiesOfClass(LivingEntity.class,
                (new AABB(new BlockPos((int) this.getX(), (int) this.getY(), (int) this.getZ()))).inflate(2),
                entity -> entity instanceof Enemy || entity == this.getTarget()
        ));

        if ((long) hitSet.size() > 3 && getAoeTimer() > 40) {
            setAoeTimer(40);
        }
    }

    @Override
    public boolean dampensVibrations() {
        return this.isCrouching();
    }

    @Override
    protected @NotNull PathNavigation createNavigation(@NotNull Level level) {
        return new TestGroundNavigation(this, level);
    }

    @Override
    public int getMaxFallDistance() {
        return this.getTarget() != null ? 3 + (int)(this.getHealth() - 1.0F) : 3;
    }

    private void setupAnim() {
        if(isAttacking() && attackAnimTimeout <= 0 && getAoeTimer() > 30) {
            if (getAttackType() == 1) {
                setAttackType(0);
            } else setAttackType(1);
            attackAnimTimeout = 20; // Length in ticks of your animation
            attackAnimationState(getAttackType(), 0).start(this.tickCount);
        } else {
            setAttackType(getAttackType());
            --this.attackAnimTimeout;
        }

        if(isAoeAttacking() && aoeAttackAnimTimeout <= 0 && getAoeTimer() <= 0) {
            setAttackType(2);
            aoeAttackAnimTimeout = 20; // Length in ticks of your animation
            attackAnimationState(getAttackType(), 0).start(this.tickCount);
        } else {
            setAttackType(getAttackType());
            --this.aoeAttackAnimTimeout;
        }

        if (this.getBrokenTick() > 0) {
            sitFromStandAnimState.stop();
        } else {
            sitFromStandAnimState.animateWhen(this.isInSittingPose(), this.tickCount);
        }
        standFromSitAnimState.animateWhen(!this.isInSittingPose(), this.tickCount);

        brokenAnim.animateWhen(this.isChassisBroken(), this.tickCount);
        repairedAnim.animateWhen(!this.isChassisBroken() && getBrokenTick() != 0, this.tickCount);

        ridden.animateWhen(this.isVehicle(), this.tickCount);
        unRide.animateWhen(!this.isVehicle(), this.tickCount);

        this.chestOpened.animateWhen(this.chestOpened(), this.tickCount);
        this.chestClosed.animateWhen(!this.chestOpened(), this.tickCount);
    }

    public AnimationState attackAnimationState(int attackType, int animType) {
        return switch (animType) {
            case 0 -> switch (attackType) {
                case 0 -> rightAttackAnimationState;
                case 1 -> leftAttackAnimationState;
                case 2 -> upswingAttackAnimState;
                default -> new AnimationState();
            };
            case 1 -> switch (attackType) {
                case 0 -> rightAttackEndAnimState;
                case 1 -> leftAttackEndAnimState;
                case 2 -> upswingAttackEndAnimState;
                default -> new AnimationState();
            };
            default -> new AnimationState();
        };
    }

    private void stopAllAttackAnimation() {
        this.leftAttackEndAnimState.stop();
        this.leftAttackAnimationState.stop();
        this.rightAttackEndAnimState.stop();
        this.rightAttackAnimationState.stop();
        this.upswingAttackAnimState.stop();
        this.upswingAttackEndAnimState.stop();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new GolemSitGoal(this));
        this.goalSelector.addGoal(1, new SteelGolemAttackGoal(this, 1.2D, true));
        this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 0.9D, 32.0F));
        this.goalSelector.addGoal(2, new GolemFollowOwnerGoal(this, 1.0D, 8.0F, 2.0F, false));
        this.goalSelector.addGoal(7, new GolemLookAtPlayerGoal(this, Player.class, 12.0F));
        this.goalSelector.addGoal(7, new GolemStroll(this, 0.5D));
        this.goalSelector.addGoal(8, new GolemRamdomLookAroundGoal(this));
        this.targetSelector.addGoal(0, new GolemNearestAttackableGoal<>(this, Mob.class, 5, false, false,
                (entity) -> entity.getType().is(Etags.Entity.STEEL_GOLEM_PRIORITY_TARGETS)));
        this.targetSelector.addGoal(1, new GolemNearestAttackableGoal<>(this, Mob.class, 5, true, false,
                (entity) -> entity instanceof Enemy && !(entity instanceof Creeper)));
        this.targetSelector.addGoal(1, new SteelGolemOwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(1, new SteelGolemOwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(2, (new HurtByTargetGoal(this).setAlertOthers()));
        this.targetSelector.addGoal(3, new GolemNearestAttackableGoal<>(this, Player.class, 10, true, false, this::isAngryAt));
        this.targetSelector.addGoal(4, new ResetUniversalAngerTargetGoal<>(this, false));
    }

    @Override
    public void handleEntityEvent(byte pId) {
        if (pId == 4) {
            if (getAoeTimer() <= 10) {
                setAttackType(2);
                this.aoeAttackAnimTimeout = 10;
            }
            stopAllAttackAnimation();
            attackAnimationState(getAttackType(), 1).start(this.tickCount);
            this.attackAnimTimeout = 10;
            this.playSound(SoundEvents.IRON_GOLEM_ATTACK, 1.0F, 0.5F);
        } else {
            super.handleEntityEvent(pId);
        }
    }

    @Override
    public @Nullable LivingEntity getTarget() {
        return super.getTarget();
    }

    @Override
    public int getMaxHeadXRot() {
        return isInSittingPose() || isVehicle() ? 0 : super.getMaxHeadXRot();
    }

    @Override
    public boolean canBeSeenAsEnemy() {
        return !this.isChassisBroken() && super.canBeSeenAsEnemy();
    }

    @Override
    protected @NotNull BodyRotationControl createBodyControl() {
        return new TestBodyControl(this);
    }

    @Override
    public @NotNull EntityDimensions getDimensions(@NotNull Pose pose) {
        if (pose == Pose.SITTING) {
            return SITTING_DIMENSIONS;
        } else if (pose == Pose.CROUCHING) {
            return CROUCHING_DIMENSIONS;
        } else return super.getDimensions(pose);
    }

    @Override
    public boolean isNoAi() {
        return this.isChassisBroken() && super.isNoAi();
    }

    public void setAttacking(boolean attacking) {
        this.entityData.set(IS_ATTACKING, attacking);
    }

    public boolean isAttacking() {
        return this.entityData.get(IS_ATTACKING);
    }

    public int getChassisHealth() {
        return this.entityData.get(CHASSIS_HEALTH);
    }

    public void setChassisHealth(int health) {
        this.entityData.set(CHASSIS_HEALTH, health);
    }

    public final int getMaxChassisHealth() {
        return 5;
    }

    public void setChassisState(boolean state) {
        this.entityData.set(CHASSIS_STATUS, state);
    }

    public boolean isChassisBroken() {
        return this.entityData.get(CHASSIS_STATUS);
    }

    public boolean isChassisCompromised() {
        if (this.getHealth() <= 0) {
            if (this.isPlayerCreated()) {
                if (this.getChassisHealth() > 1) {
                    this.stopAllAttackAnimation();
                    this.setChassisState(true);
                    this.setChassisHealth(this.getChassisHealth() - 1);
                    this.setHealth(1);
                    return false;
                } else return true;
            } else return true;
        } else return false;
    }

    public ItemStack getSaddle() {
        return this.inventory.getItem(0);
    }

    public void setGolemInteration(Player player, @NotNull ItemStack stack) {
        boolean leaves = stack.is(Etags.Items.STEEL_GOLEM_LEAVES_DECORATION);
        boolean carpet = stack.is(Etags.Items.STEEL_GOLEM_CARPET_DECORATION);
        boolean wax = stack.is(Items.HONEYCOMB);
        if (wax) {
            this.setWaxed(true);
            if (!player.isCreative()) stack.shrink(1);
            playSound(SoundEvents.HONEYCOMB_WAX_ON);
            spawnParticlesOnEntity(level(), position(), ParticleTypes.WAX_ON, this, UniformInt.of(3, 5));
        } else if (stack.is(ItemTags.AXES)) {
            this.setWaxed(false);
            if (!player.isCreative()) stack.hurtAndBreak(1, player, (e) -> e.broadcastBreakEvent(player.getUsedItemHand()));
            playSound(SoundEvents.AXE_WAX_OFF);
            spawnParticlesOnEntity(level(), position(), ParticleTypes.WAX_OFF, this, UniformInt.of(3, 5));
        } else {
            int slot = 3;
            if (leaves) slot = 2;
            if (!player.isCreative()) stack.shrink(1);
            if (!stack.isEmpty()) stack = stack.copyWithCount(1);
            this.inventory.setItem(slot, stack);
        }
        this.navigation.stop();
    }

    public void setAggroState(boolean state) {
        this.entityData.set(AGGRO, state);
    }

    public boolean getAggroState() {
        return this.entityData.get(AGGRO);
    }

    public void setAttackType(int type) {
        this.entityData.set(ATTACK_TYPE, type);
    }

    public int getAttackType() {
        return this.entityData.get(ATTACK_TYPE);
    }

    public int getMossTimer() {
        return this.entityData.get(MOSS_TIMER);
    }

    public void setMossTimer(int ticks) {
        this.entityData.set(MOSS_TIMER, ticks);
    }

    public int getMossStage() {
        return this.entityData.get(MOSS_STAGE);
    }

    public void setMossStage(int stage) {
        this.entityData.set(MOSS_STAGE, stage);
    }

    public boolean isWaxed() {
        return this.entityData.get(IS_WAXED);
    }

    public void setWaxed(boolean i) {
        this.entityData.set(IS_WAXED, i);
    }

    public int getBrokenTick() {
        return this.entityData.get(BROKEN_TICK);
    }

    public void setBrokenTick(int i) {
        this.entityData.set(BROKEN_TICK, i);
    }

    public int getSitTick() {
        return this.entityData.get(SIT_TICK);
    }

    public void setSitTick(int i) {
        this.entityData.set(SIT_TICK, i);
    }

    public int getAoeTimer() {
        return this.entityData.get(AOE_TIMER);
    }

    public void setAoeTimer(int i) {
        this.entityData.set(AOE_TIMER, i);
    }

    public void setAoeAttacking(boolean attacking) {
        this.entityData.set(IS_AOE_ATTACKING, attacking);
    }

    public boolean isAoeAttacking() {
        return this.entityData.get(IS_AOE_ATTACKING);
    }

    @Override
    public void openCustomInventoryScreen(@NotNull Player player) {
        if(player instanceof ServerPlayer serverplayer) {
            if (isAlive()) {
                if (serverplayer.containerMenu != serverplayer.inventoryMenu) {
                    serverplayer.closeContainer();
                }

                this.isChestOpened(true);
                serverplayer.nextContainerCounter();
                ModNetwork.INSTANCE.send(PacketDistributor.PLAYER.with(() -> serverplayer), new SteelGolemInventoryPacket(serverplayer.containerCounter, this.inventory.getContainerSize(), this.getId()));
                serverplayer.containerMenu = new SteelGolemInventoryMenu(serverplayer.containerCounter, serverplayer.getInventory(), this.inventory, this);
                serverplayer.initMenu(serverplayer.containerMenu);
                MinecraftForge.EVENT_BUS.post(new PlayerContainerEvent.Open(serverplayer, serverplayer.containerMenu));
            }
        }
    }

    protected int getInventorySize() {
        return 24;
    }

    @Override
    public boolean isInvulnerableTo(DamageSource source) {
        if (source.is(Etags.DamageTypes.STEEL_GOLEM_IMMUNE) && !source.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
            return true;
        } else if (isChassisBroken() && this.getChassisHealth() != 0) {
            return true;
        }
        return super.isInvulnerableTo(source);
    }

    protected void doPush(@NotNull Entity entity) {
        if (entity instanceof Enemy && !(entity instanceof Creeper) && this.getRandom().nextInt(20) == 0) {
            this.setTarget((LivingEntity)entity);
        }
        super.doPush(entity);
    }

    @Override
    public boolean isPushable() {
        return !(this.isInSittingPose() || this.isChassisBroken() || this.isVehicle() || this.isWearingArmor());
    }

    //Some Ridable Relate Stuff
    @Override
    protected float getRiddenSpeed(@NotNull Player player) {
        float f = player.isSprinting() /*&& this.getJumpCooldown() == 0*/ ? 0.1F : 0.0F;
        return (float) (this.getAttributeValue(Attributes.MOVEMENT_SPEED) * 0.3F) + f;
    }

    @Override
    public boolean canSprint() {
        return true;
    }

    public boolean canSpawnSprintParticle() {
        return this.getDeltaMovement().horizontalDistanceSqr() > (double)2.5000003E-7F && this.random.nextInt(5) == 0;
    }

    //NeutralMob Stuff
    public boolean canAttackType(@NotNull EntityType<?> pType) {
        if (this.isPlayerCreated() && pType == EntityType.PLAYER) {
            return false;
        } else {
            return pType != EntityType.CREEPER && super.canAttackType(pType);
        }
    }

    public void startPersistentAngerTimer() {
        this.setRemainingPersistentAngerTime(PERSISTENT_ANGER_TIME.sample(this.random));
    }

    public void setRemainingPersistentAngerTime(int time) {
        this.remainingPersistentAngerTime = time;
    }

    public int getRemainingPersistentAngerTime() {
        return this.remainingPersistentAngerTime;
    }

    public void setPersistentAngerTarget(@Nullable UUID target) {
        this.persistentAngerTarget = target;
    }

    @Nullable
    public UUID getPersistentAngerTarget() {
        return this.persistentAngerTarget;
    }

    @Override
    public boolean isAngry() {
        return NeutralMob.super.isAngry();
    }

    //Entity Damage / Hurt Events
    public boolean doHurtTarget(Entity entity) {
        this.level().broadcastEntityEvent(this, (byte)4);
        float f = this.getAttackDamage();
        float f1 = (int)f > 0 ? f / 1.5F + (float)this.random.nextInt((int)f) : f;
        boolean flag = entity.hurt(this.damageSources().mobAttack(this), f1);
        if (flag) {
            double d2;
            if (entity instanceof LivingEntity livingentity) {
                d2 = livingentity.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE);
            } else {
                d2 = 0.0D;
            }

            double d0 = d2;
            double d1 = Math.max(0.0D, 2.0D - d0);
            entity.setDeltaMovement(entity.getDeltaMovement().multiply(d1, 1.0D, d1));
            this.doEnchantDamageEffects(this, entity);
        }

        this.playSound(SoundEvents.IRON_GOLEM_ATTACK, 1.0F, 1.0F);
        return flag;
    }

    private float getAttackDamage() {
        return (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
    }

    @Override
    public boolean hurt(@NotNull DamageSource source, float amount) {
        SteelGolem.Crackiness steelgolem$crackiness = this.getCrackiness();
        boolean flag = super.hurt(source, amount);
        if (source.getEntity() == this.getControllingPassenger()) {
            return false;
        }
        if (flag && this.getCrackiness() != steelgolem$crackiness) {
            this.playSound(SoundEvents.IRON_GOLEM_DAMAGE, 1.0F, 1.0F);
        }

        return flag;
    }

    @Override
    public void die(@NotNull DamageSource cause) {
        if (!this.isChassisCompromised()) {
            this.dead = false;
            if (!this.level().isClientSide && this.level().getGameRules().getBoolean(GameRules.RULE_SHOWDEATHMESSAGES) && this.getOwner() instanceof ServerPlayer) {
                this.getOwner().sendSystemMessage(Component.translatable("entity.elementus.steel_golem_down", this.getChassisHealth()));
            }
        } else super.die(cause);
        this.stopBeingAngry();
        this.ejectPassengers();
    }

    @Override
    public boolean isAffectedByPotions() {
        return !this.isChassisBroken() && super.isAffectedByPotions();
    }

    @Override
    public boolean attackable() {
        return !this.isChassisBroken() && super.attackable();
    }

    public SteelGolem.Crackiness getCrackiness() {
        return SteelGolem.Crackiness.byFraction(this.getHealth() / this.getMaxHealth());
    }

    public ChassisCrackiness getChassisCrackiness() {
        if (this.isPlayerCreated()) {
            return ChassisCrackiness.byFraction((float) this.getChassisHealth() / this.getMaxChassisHealth());
        } else return ChassisCrackiness.NONE;
    }

    @Override
    public boolean canBeCollidedWith() {
        if (!this.getArmor().isEmpty() && this.getArmor().getItem() instanceof SteelGolemUpgradeItem armorItem) {
            return armorItem.identifier.equals("reinforced_plating") && this.isInSittingPose();
        } else return super.canBeCollidedWith();
    }

    @Override
    protected @NotNull ResourceLocation getDefaultLootTable() {
        if (this.getMossStage() == 0) {
            return this.getType().getDefaultLootTable();
        } else {
            return switch (this.getMossStage()) {
                case 1 -> modLoc("entities/steel_golem/moss_1");
                case 2 -> modLoc("entities/steel_golem/moss_2");
                case 3 -> modLoc("entities/steel_golem/moss_3");
                default -> throw new IncompatibleClassChangeError();
            };
        }
    }

    @Override
    public boolean skipAttackInteraction(@NotNull Entity pEntity) {
        return pEntity.isPassenger();
    }

    @Override
    public boolean canAttack(@NotNull LivingEntity pTarget) {
        return !this.isChassisBroken() && super.canAttack(pTarget);
    }

    protected void playStepSound(@NotNull BlockPos pPos, @NotNull BlockState pBlock) {
        this.playSound(SoundEvents.IRON_GOLEM_STEP, 2.0F, 0.5F);
    }

    @Override
    protected void dropCustomDeathLoot(@NotNull DamageSource pSource, int pLooting, boolean pRecentlyHit) {
        super.dropCustomDeathLoot(pSource, pLooting, pRecentlyHit);
        ItemStack leaves = this.isCamouflaged();
        ItemStack saddle = this.getSaddle();
        ItemStack carpet = this.getDripCarpet();
        if (leaves != null) this.spawnAtLocation(leaves);
        if (saddle != null) this.spawnAtLocation(saddle);
        if (carpet != null) this.spawnAtLocation(carpet);
    }

    public boolean isPlayerCreated() {
        return (this.entityData.get(DATA_FLAGS_ID) & 1) != 0;
    }

    public void setPlayerCreated(boolean playerMade) {
        byte b0 = this.entityData.get(DATA_FLAGS_ID);
        if (playerMade) {
            this.entityData.set(DATA_FLAGS_ID, (byte)(b0 | 1));
        } else {
            this.entityData.set(DATA_FLAGS_ID, (byte)(b0 & -2));
        }
    }

    @Override
    public boolean canBeLeashed(Player pPlayer) {
        return super.canBeLeashed(pPlayer);
    }

    public @NotNull Vec3 getLeashOffset() {
        return new Vec3(0.0D, 0.95F * this.getEyeHeight(), this.getBbWidth() * 0.4F);
    }

    @Override
    public boolean isAttackable() {
        return !this.isChassisBroken();
    }

    @Override
    public void equipSaddle(@Nullable SoundSource pSource) {
        this.inventory.setItem(0, new ItemStack(Items.SADDLE));
    }

    @Override
    public boolean isShearable(@NotNull ItemStack item, Level level, BlockPos pos) {
        return this.getMossStage() > 0 && !this.isChassisCompromised();
    }

    @Override
    public @NotNull List<ItemStack> onSheared(@Nullable Player player, @NotNull ItemStack item, Level level, BlockPos pos, int fortune) {
        level.playSound(null, this, SoundEvents.MOSS_BREAK, player == null ? SoundSource.BLOCKS : SoundSource.PLAYERS, 1.0F, 1.0F);
        this.gameEvent(GameEvent.SHEAR, player);
        if (!level.isClientSide) {
            int i = 1 + this.random.nextInt(3);

            List<ItemStack> items = new ArrayList<>();
            for (int j = 0; j < i; ++j) {
                items.add(new ItemStack(Items.MOSS_BLOCK));
            }
            return items;
        }
        return java.util.Collections.emptyList();
    }

    @Override
    public void shear(@NotNull SoundSource pSource) {
        this.level().playSound(null, this, SoundEvents.SHEEP_SHEAR, pSource, 1.0F, 1.0F);
        this.setMossStage(this.getMossStage() - 1);
        int i = 1 + this.random.nextInt(3);

        for(int j = 0; j < i; ++j) {
            ItemEntity itementity = this.spawnAtLocation(new ItemStack(Items.MOSS_BLOCK));
            if (itementity != null) {
                itementity.setDeltaMovement(itementity.getDeltaMovement().add((this.random.nextFloat() - this.random.nextFloat()) * 0.1F, this.random.nextFloat() * 0.05F, (this.random.nextFloat() - this.random.nextFloat()) * 0.1F));
            }
        }
    }

    @Override
    public boolean readyForShearing() {
        return this.getMossStage() > 0 && !this.isChassisCompromised();
    }

    //Golem Details
    public enum Crackiness {
        NONE(1.0F),
        LOW(0.75F),
        MEDIUM(0.5F),
        HIGH(0.25F);

        private static final List<SteelGolem.Crackiness> BY_DAMAGE = Stream.of(values()).sorted(Comparator.comparingDouble((p_28904_) -> p_28904_.fraction)).collect(ImmutableList.toImmutableList());

        private final float fraction;
        Crackiness(float pFraction) {
            this.fraction = pFraction;
        }

        public static SteelGolem.Crackiness byFraction(float pFraction) {
            for(SteelGolem.Crackiness steelgolem$crackiness : BY_DAMAGE) {
                if (pFraction < steelgolem$crackiness.fraction) {
                    return steelgolem$crackiness;
                }
            }

            return NONE;
        }

    }

    public enum ChassisCrackiness {
        NONE(1.0F),
        VERYLOW(0.81F),
        LOW(0.61F),
        MEDIUM(0.41F),
        HIGH(0.21F);

        private static final List<ChassisCrackiness> BY_DAMAGE = Stream.of(values())
                .sorted(Comparator.comparingDouble((p_28904_) -> p_28904_.fraction)).collect(ImmutableList.toImmutableList());

        private final float fraction;
        ChassisCrackiness(float pFraction) {
            this.fraction = pFraction;
        }

        public static ChassisCrackiness byFraction(float pFraction) {
            for(ChassisCrackiness steelgolem$chassis : BY_DAMAGE) {
                if (pFraction < steelgolem$chassis.fraction) {
                    return steelgolem$chassis;
                }
            }

            return NONE;
        }
    }

    //Goals
    static class GolemRamdomLookAroundGoal extends RandomLookAroundGoal {
        private final SteelGolem mob;

        public GolemRamdomLookAroundGoal(SteelGolem pMob) {
            super(pMob);
            this.mob = pMob;
        }

        @Override
        public boolean canUse() {
            return mob != null && !(mob.isInSittingPose() || mob.isChassisBroken()) && super.canUse();
        }
    }

    static class GolemSitGoal extends SitWhenOrderedToGoal {
        private final SteelGolem mob;


        public GolemSitGoal(SteelGolem pMob) {
            super(pMob);
            mob = pMob;
        }

        @Override
        public boolean canUse() {
            return mob != null && !mob.isChassisBroken() && super.canUse();
        }
    }

    static class GolemLookAtPlayerGoal extends LookAtPlayerGoal {
        private final SteelGolem mob;

        public GolemLookAtPlayerGoal(SteelGolem pMob, Class<? extends LivingEntity> pLookAtType, float pLookDistance) {
            super(pMob, pLookAtType, pLookDistance);
            this.mob = pMob;
        }

        @Override
        public boolean canUse() {
            return mob != null && !(mob.isInSittingPose() || mob.isChassisBroken()) && super.canUse();
        }
    }

    static class GolemNearestAttackableGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {
        private final SteelGolem mob;

        public GolemNearestAttackableGoal(SteelGolem pMob, Class<T> pTargetType, boolean pMustSee) {
            super(pMob, pTargetType, pMustSee);
            this.mob = pMob;
        }

        public GolemNearestAttackableGoal(SteelGolem pMob, Class<T> pTargetType, boolean pMustSee, Predicate<LivingEntity> pTargetPredicate) {
            super(pMob, pTargetType, pMustSee, pTargetPredicate);
            this.mob = pMob;
        }

        public GolemNearestAttackableGoal(SteelGolem pMob, Class<T> pTargetType, boolean pMustSee, boolean pMustReach) {
            super(pMob, pTargetType, pMustSee, pMustReach);
            this.mob = pMob;
        }

        public GolemNearestAttackableGoal(SteelGolem pMob, Class<T> pTargetType, int pRandomInterval, boolean pMustSee, boolean pMustReach, @Nullable Predicate<LivingEntity> pTargetPredicate) {
            super(pMob, pTargetType, pRandomInterval, pMustSee, pMustReach, pTargetPredicate);
            this.mob = pMob;
        }

        @Override
        public boolean canUse() {
            return mob != null && !mob.isChassisBroken() && mob.getAggroState() && super.canUse();
        }
    }

    static class  GolemStroll extends WaterAvoidingRandomStrollGoal {
        private final SteelGolem mob;

        public GolemStroll(SteelGolem pMob, double pSpeedModifier) {
            super(pMob, pSpeedModifier);
            this.mob = pMob;
        }

        @Override
        public boolean canUse() {
            return mob != null && !(mob.isInSittingPose() || mob.isChassisBroken()) && super.canUse();
        }
    }
}