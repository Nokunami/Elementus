package net.nokunami.elementus.common.entity.living;

import com.google.common.collect.ImmutableList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
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
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.BodyRotationControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.IForgeShearable;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.network.PacketDistributor;
import net.nokunami.elementus.common.Etags;
import net.nokunami.elementus.common.config.EntityConfig;
import net.nokunami.elementus.common.entity.ai.control.SmoothBodyControl;
import net.nokunami.elementus.common.entity.ai.goal.steelGolem.*;
import net.nokunami.elementus.common.entity.ai.navigation.TestGroundNavigation;
import net.nokunami.elementus.common.inventory.SteelGolemInventoryMenu;
import net.nokunami.elementus.common.item.GolemUpgradeProperties;
import net.nokunami.elementus.common.item.SteelGolemUpgradeItem;
import net.nokunami.elementus.common.network.ModNetwork;
import net.nokunami.elementus.common.network.SteelGolemInventoryPacket;
import net.nokunami.elementus.common.registry.ESoundEvents;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static net.nokunami.elementus.Elementus.modLoc;
import static net.nokunami.elementus.common.entity.MobUtil.tamedMob;
import static net.nokunami.elementus.common.entity.ModParticleUtil.spawnParticlesOnEntity;
import static net.nokunami.elementus.common.entity.ModParticleUtil.spawnWideParticlesOnEntity;
import static net.nokunami.elementus.common.registry.ESoundEvents.STEEL_GOLEM_REPAIR;
import static net.nokunami.elementus.common.registry.ESoundEvents.STEEL_GOLEM_REVIVE;

@SuppressWarnings("deprecation")
public class AstaliteGolem extends TamableGolem implements Shearable, IForgeShearable {
    private static final EntityDataAccessor<Boolean> FAST_ATTACK = SynchedEntityData.defineId(AstaliteGolem.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IS_WAXED = SynchedEntityData.defineId(AstaliteGolem.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> MOSS_TIMER = SynchedEntityData.defineId(AstaliteGolem.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> MOSS_STAGE = SynchedEntityData.defineId(AstaliteGolem.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> SIT_TICK = SynchedEntityData.defineId(AstaliteGolem.class, EntityDataSerializers.INT);
    public final AnimationState attackLoopAnimationState = new AnimationState();
    public final AnimationState upswingAttackAnimationState = new AnimationState();
    public final AnimationState ridden = new AnimationState();
    public final AnimationState unRide = new AnimationState();
    public int attackAnimTimeout = 0;
    public int aoeAttackAnimTimeout = 0;
    public int aoeAnimTimeout = 320;
    public float eyeLayerBrightness;
    public float eyeOldLayerBrightness;
    public int eyeLayerTick;
    private static final UUID ARMOR_MODIFIER_UUID = UUID.fromString("556E1665-8B10-40C8-8F9D-CF9B1667F295");
    private static final UUID TOUGHNESS_MODIFIER_UUID = UUID.fromString("0e936964-c9bb-4eb7-a718-ed69de8a4e8f");
    public Predicate<Entity> GOLEM_SURROUNDING_TARGETS = (entity) -> entity instanceof  Mob mob
            && ((entity instanceof Enemy || entity == getTarget()) || (getOwner() != null && mob.getTarget() == getOwner()));
    public static float rawBbWidth = 1.6F;
    public static float rawBbHeight = 2.9F;

    public AstaliteGolem(EntityType<? extends TamableGolem> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        createInventory();
        setCanEquipChest(true);
        setMaxUpStep(1.5F);
        GroundPathNavigation groundpathnavigation = (GroundPathNavigation) getNavigation();
        groundpathnavigation.setCanOpenDoors(true);
        groundpathnavigation.canPassDoors();
        groundpathnavigation.setCanFloat(false);
        groundpathnavigation.setCanWalkOverFences(true);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(IS_WAXED, false);
        entityData.define(MOSS_TIMER, 0);
        entityData.define(MOSS_STAGE, 0);
        entityData.define(SIT_TICK, 0);
        entityData.define(FAST_ATTACK, false);
    }

    public void addAdditionalSaveData(@NotNull CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putBoolean("Waxed", isWaxed());
        tag.putInt("AttackType", getAttackType());
        tag.putInt("MossTimer", getMossTimer());
        tag.putInt("MossStage", getMossStage());
        if (!inventory.getItem(1).isEmpty())
            tag.put("ArmorItem", inventory.getItem(1).save(new CompoundTag()));
        if (!inventory.getItem(2).isEmpty())
            tag.put("LeavesDecoration", inventory.getItem(2).save(new CompoundTag()));
        if (!inventory.getItem(3).isEmpty())
            tag.put("DecorItem", inventory.getItem(3).save(new CompoundTag()));
        tag.putInt("SitTick", getSitTick());
        tag.putInt("AoeTimer", getAoeTimer());
        tag.putBoolean("FastAttack", getFastAttack());
    }

    public void readAdditionalSaveData(@NotNull CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        setWaxed(tag.getBoolean("Waxed"));
        setAttackType(tag.getInt("AttackType"));
        setMossTimer(tag.getInt("MossTimer"));
        setMossStage(tag.getInt("MossStage"));
        if (tag.contains("ArmorItem", 10)) {
            ItemStack armorItem = ItemStack.of(tag.getCompound("ArmorItem"));
            if (!armorItem.isEmpty() && isArmor(armorItem))
                inventory.setItem(1, armorItem);
        }
        if (tag.contains("LeavesDecoration", 10)) {
            ItemStack leaves = ItemStack.of(tag.getCompound("LeavesDecoration"));
            if (leaves.is(Etags.Items.STEEL_GOLEM_LEAVES_DECORATION))
                inventory.setItem(2, leaves);
        }
        if (tag.contains("DecorItem", 10))
            inventory.setItem(3, ItemStack.of(tag.getCompound("DecorItem")));
        updateContainerEquipment();
        setSitTick(tag.getInt("SitTick"));
        setAoeTimer(tag.getInt("AoeTimer"));
        setFastAttack(tag.getBoolean("FastAttack"));
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(0, new SitWhenOrderedToGoal(this));
        goalSelector.addGoal(1, new SteelGolemAttackGoal(this, 1.2D, true));
        goalSelector.addGoal(2, new SteelGolemMoveTowardsTargetGoal(this, 0.9D, 32.0F));
        goalSelector.addGoal(2, new SteelGolemFollowOwnerGoal(this, new SteelGolemFollowOwnerGoal.goalInfo().
                speedAggro(1, 1.25).startAggro(8, 20).stopAggro(6, 9).teleportAggro(12, 28)));
        goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 12.0F) {
            @Override
            public boolean canUse() {
                return !isInSittingPose() && !isChassisBroken() && super.canUse();
            }
        });
        goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 0.5D) {
            @Override
            public boolean canUse() {
                return !isInSittingPose() && !isChassisBroken() && super.canUse();
            }
        });
        goalSelector.addGoal(8, new RandomLookAroundGoal(this) {
            @Override
            public boolean canUse() {
                return !isInSittingPose() && !isChassisBroken() && super.canUse();
            }
        });
        targetSelector.addGoal(0, new SteelGolemNearestAttackableGoal<>(this, Mob.class, 5, false, false,
                (entity) -> entity.getType().is(Etags.Entity.STEEL_GOLEM_PRIORITY_TARGETS)));
        targetSelector.addGoal(0, new SteelGolemNearestAttackableGoal<>(this, Mob.class, 5, false, false,
                (entity) -> entity instanceof Mob mob && getOwner() != null && mob.getTarget() == getOwner()));
        targetSelector.addGoal(1, new SteelGolemNearestAttackableGoal<>(this, Mob.class, 5, false, false,
                (entity) -> entity instanceof Enemy && !(entity instanceof Creeper)));
        targetSelector.addGoal(1, new GolemOwnerHurtByGoal(this));
        targetSelector.addGoal(1, new GolemOwnerHurtGoal(this));
        targetSelector.addGoal(2, (new HurtByTargetGoal(this)));
        targetSelector.addGoal(3, new SteelGolemNearestAttackableGoal<>(this, Player.class, 10, true, false, this::isAngryAt));
        targetSelector.addGoal(4, new ResetUniversalAngerTargetGoal<>(this, false));
    }

    public ItemStack getArmor() {
        return getItemBySlot(EquipmentSlot.CHEST);
    }

    private void setArmor(ItemStack stack) {
        setItemSlot(EquipmentSlot.CHEST, stack);
        setDropChance(EquipmentSlot.CHEST, 0.0F);
    }

    public ItemStack isCamouflaged() {
        return getItemBySlot(EquipmentSlot.LEGS);
    }

    private void wearCamouflaged(ItemStack stack) {
        setItemSlot(EquipmentSlot.LEGS, stack);
        setDropChance(EquipmentSlot.LEGS, 0.0F);
    }

    public ItemStack getDripCarpet() {
        return getItemBySlot(EquipmentSlot.FEET);
    }

    private void wearDripCarpet(ItemStack stack) {
        setItemSlot(EquipmentSlot.FEET, stack);
        setDropChance(EquipmentSlot.FEET, 0.0F);
    }

    @Override
    protected void updateContainerEquipment() {
        super.updateContainerEquipment();
        if (!level().isClientSide) {
            super.updateContainerEquipment();
            setArmorEquipment(inventory.getItem(1));
            setDropChance(EquipmentSlot.CHEST, 0.0F);
            wearCamouflaged(inventory.getItem(2));
            wearDripCarpet(inventory.getItem(3));
        }
    }

    private void setArmorEquipment(ItemStack pStack) {
        setArmor(pStack);
//        if (!level().isClientSide) {
//            Objects.requireNonNull(getAttribute(Attributes.ARMOR)).removeModifier(ARMOR_MODIFIER_UUID);
//            Objects.requireNonNull(getAttribute(Attributes.ARMOR_TOUGHNESS)).removeModifier(TOUGHNESS_MODIFIER_UUID);
//            if (isArmor(pStack)) {
//                int armor = ((SteelGolemUpgradeItem)pStack.getItem()).properties.getArmor();
//                double toughness = ((SteelGolemUpgradeItem)pStack.getItem()).properties.getToughness();
//                if (armor != 0) {
//                    Objects.requireNonNull(getAttribute(Attributes.ARMOR)).addTransientModifier(new AttributeModifier(ARMOR_MODIFIER_UUID, "Armor bonus", armor, AttributeModifier.Operation.ADDITION));
//                }
//                if (toughness != 0) {
//                    Objects.requireNonNull(getAttribute(Attributes.ARMOR_TOUGHNESS)).addTransientModifier(new AttributeModifier(TOUGHNESS_MODIFIER_UUID, "Toughness", toughness, AttributeModifier.Operation.ADDITION));
//                }
//            }
//        }
    }

    @Override
    public void containerChanged(@NotNull Container container) {
        ItemStack armor = getArmor();
        ItemStack leaves = isCamouflaged();
        ItemStack carpet = getDripCarpet();
        super.containerChanged(container);
        ItemStack armor1 = getArmor();
        ItemStack leaves1 = isCamouflaged();
        ItemStack carpet1 = getDripCarpet();
        if (tickCount > 20 && isArmor(armor1) && armor != armor1)
            playSound(ESoundEvents.STEEL_GOLEM_ARMORED.get(), 0.5F, 1.0F);
        if (tickCount > 20 && leaves != leaves1)
            playSound(ESoundEvents.STEEL_GOLEM_LEAVES_SWAG.get(), 0.5F, 1.0F);
        if (tickCount > 20 && carpet != carpet1)
            playSound(ESoundEvents.STEEL_GOLEM_CARPET_SWAG.get(), 0.5F, 1.0F);
    }

    protected SoundEvent getDeathSound() {
        return isChassisCompromised() ? SoundEvents.IRON_GOLEM_DEATH : ESoundEvents.STEEL_GOLEM_DOWN.get();
    }

    protected SoundEvent getHurtSound(@NotNull DamageSource pDamageSource) {
        return isChassisCompromised() ? null : SoundEvents.IRON_GOLEM_HURT;
    }

    @Override
    public @NotNull InteractionResult interactAt(@NotNull Player player, @NotNull Vec3 vec3, @NotNull InteractionHand hand) {
        double d0 = vec3.y;
        boolean saddleHeight = d0 >= getBbHeight() * 0.65F;

        if (saddleHeight && isInSittingPose() && isTame() && !isChassisBroken() && isSaddled()) {
            return doPlayerRide(player);
        } else return super.interactAt(player, vec3, hand);
    }

    @Override
    public @NotNull InteractionResult mobInteract(@NotNull Player player, @NotNull InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        boolean canHeal = getHealth() < getMaxHealth();
        boolean shearMoss = itemStack.is(Items.SHEARS) && getMossStage() > 0 && readyForShearing();
        boolean leaves = itemStack.is(Etags.Items.STEEL_GOLEM_LEAVES_DECORATION) && isCamouflaged().isEmpty();
        boolean carpet = itemStack.is(Etags.Items.STEEL_GOLEM_CARPET_DECORATION) && getDripCarpet().isEmpty();
        boolean wax = itemStack.is(Items.HONEYCOMB) && !isWaxed();
        boolean scrapWax = itemStack.is(ItemTags.AXES) && isWaxed();
        boolean chassisCondition = getChassisHealth() < 5;
        boolean healItem = itemStack.is(Etags.Items.STEEL_GOLEM_HEAL) || itemStack.is(Etags.Items.STEEL_GOLEM_REPAIR_HALF);
        boolean repair = itemStack.is(Etags.Items.STEEL_GOLEM_REPAIR_FULL);
        boolean aggroStateChanger = (itemStack.is(ItemTags.SWORDS) || itemStack.is(ItemTags.AXES)) && player.isSecondaryUseActive();

        if ((canHeal && healItem) || (chassisCondition && repair))
            return healGolem(player, itemStack);
        else {
            if (shearMoss)
                return trimMoss(player, hand);
            if (isInSittingPose() && !isAngry() && player.isSecondaryUseActive())
                return openInventory(player);
            if (isOwnedBy(player)) {
                if (aggroStateChanger)
                    return aggroStateChange();
                if (leaves||carpet||wax||scrapWax)
                    return setGolemEquippable(player, hand);
            }

            InteractionResult interactionresult = super.mobInteract(player, hand);
            if ((!interactionresult.consumesAction() || isBaby()) && isOwnedBy(player)) {
                return sitOrder();
            } else {
                return interactionresult;
            }
        }
    }

    public InteractionResult sitOrder() {
        if (getSitTick() == 0 || getSitTick() == 15) {
            setOrderedToSit(!isOrderedToSit());
        }
        jumping = false;
        navigation.stop();
        return InteractionResult.SUCCESS;
    }

    public InteractionResult setGolemEquippable(Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (isArmor(stack)) {
            equipArmor(player, stack);
        } else if (stack.is(Tags.Items.CHESTS_WOODEN)) {
            equipChest(player, stack);
        } else if (stack.is(Items.SADDLE)) {
            super.mobInteract(player, hand);
        } else if (stack.is(Items.HONEYCOMB) && !isWaxed()) {
            setWaxed(true);
            if (!player.isCreative()) stack.shrink(1);
            playSound(SoundEvents.HONEYCOMB_WAX_ON);
            spawnParticlesOnEntity(level(), position(), ParticleTypes.WAX_ON, this, UniformInt.of(3, 5));
        } else if (stack.is(ItemTags.AXES) && isWaxed()) {
            setWaxed(false);
            if (!player.isCreative()) stack.hurtAndBreak(1, player, (e) -> e.broadcastBreakEvent(player.getUsedItemHand()));
            playSound(SoundEvents.AXE_WAX_OFF);
            spawnParticlesOnEntity(level(), position(), ParticleTypes.WAX_OFF, this, UniformInt.of(3, 5));
        } else {
            inventory.setItem(stack.is(Etags.Items.STEEL_GOLEM_LEAVES_DECORATION) ? 2 : 3, stack.copyWithCount(1));
            if (!player.isCreative()) stack.shrink(1);
        }
        navigation.stop();
        return InteractionResult.SUCCESS;
    }

    public InteractionResult trimMoss(Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        shear(SoundSource.PLAYERS);
        gameEvent(GameEvent.SHEAR, player);
        itemStack.hurtAndBreak(1, player, (item) -> item.broadcastBreakEvent(hand));
        return InteractionResult.SUCCESS;
    }

    protected InteractionResult healGolem(Player player, ItemStack stack) {
        boolean chassisCondition = getChassisHealth() < 5;
        boolean repairHalf = stack.is(Etags.Items.STEEL_GOLEM_REPAIR_HALF);
        boolean repairFull = stack.is(Etags.Items.STEEL_GOLEM_REPAIR_FULL);
        float randomFloat = 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F;
        int b = isChassisBroken() ? 1 : 0;

        float healAmount = 1;
        if (repairHalf) healAmount = 0.5F;

        setChassisState(false);
        if (chassisCondition && repairFull) setChassisHealth(getChassisHealth() + 1);
        heal((int) ((EntityConfig.steelGolem_MaxHealth / EntityConfig.steelGolem_RepairAmount) * healAmount) - b);
        playSound(isChassisBroken() ? STEEL_GOLEM_REVIVE.get() : STEEL_GOLEM_REPAIR.get(), 1.0F, randomFloat);
        if (!player.isCreative()) stack.shrink(1);
        return InteractionResult.SUCCESS;
    }

    protected void tameGolem(Player player) {
        if (!isPlayerCreated()) setPlayerCreated(true);
        tame(player);
        navigation.stop();
        setTarget(null);
        setOrderedToSit(false);
        setInSittingPose(false);
        level().broadcastEntityEvent(this, (byte) 7);
    }

    protected InteractionResult aggroStateChange() {
        navigation.stop();
        setAggroState(!getAggroState());
        setTarget(null);
        return InteractionResult.SUCCESS;
    }

    @Override
    protected void positionRider(@NotNull Entity passenger, @NotNull MoveFunction moveFunction) {
        float height = rawBbHeight;
        float sitting = ((float) getSitTick() /15) * 0.75F;
        float crouch = getPose() == Pose.CROUCHING ? 1 : 0;
        double passengerRidingOffset = (height - Math.max(sitting, crouch)) * 0.75D;
        if (hasPassenger(passenger)) {
            float dro = 0.75F;
            Vec3 offset = (switch (getPassengers().stream().toList().indexOf(passenger)) {
                case 0 -> new Vec3(0, passengerRidingOffset + passenger.getMyRidingOffset(), 0);
                case 1 -> new Vec3(0, passengerRidingOffset + passenger.getMyRidingOffset(), -dro);
                case 2 -> new Vec3(0, passengerRidingOffset + passenger.getMyRidingOffset(), dro);
                default -> Vec3.ZERO;
            }).yRot((float) (-Math.toRadians(getYRot()) - Math.PI / 2));
            moveFunction.accept(passenger, getX() + offset.x, getY() + offset.y, getZ() + offset.z);
        }
    }

    @Override
    protected boolean canAddPassenger(@NotNull Entity passenger) {
        return getPassengers().size() < getMaxPassengers();
    }

    @Override
    protected int getMaxPassengers() {
        return 3;
    }

    public boolean hasEnoughSpaceFor(Entity entity) {
        return entity.getBbWidth() < getBbWidth() || entity.getBbHeight() < getBbHeight();
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
                .add(Attributes.MAX_HEALTH, EntityConfig.steelGolem_MaxHealth)
                .add(Attributes.MOVEMENT_SPEED, EntityConfig.steelGolem_MovementSpeed)
                .add(Attributes.KNOCKBACK_RESISTANCE, EntityConfig.steelGolem_KnockbackResist)
                .add(Attributes.ATTACK_DAMAGE, EntityConfig.steelGolem_AttackDamage)
                .add(Attributes.ARMOR, EntityConfig.steelGolem_Armor)
                .add(Attributes.ARMOR_TOUGHNESS, EntityConfig.steelGolem_Toughness)
                .add(Attributes.FOLLOW_RANGE, EntityConfig.steelGolem_FollowRange)
                ;
    }

    @Override
    public void baseTick() {
        super.baseTick();
        List<Entity> list = level().getEntities(this, getBoundingBox().inflate(0.2F, -0.01F, 0.2F), EntitySelector.pushableBy(this));
        Set<Entity> entitySet = new HashSet<>(level().getEntitiesOfClass(LivingEntity.class, getBoundingBox().inflate(2), GOLEM_SURROUNDING_TARGETS));

        if (level().isClientSide) setupAnim();

        if (!level().isClientSide) {
            updatePersistentAnger((ServerLevel) level(), true);

            if (!isTame() && isPlayerCreated()) {
                setOrderedToSit(true);
                setInSittingPose(true);
                List<Entity> player = level().getEntities(this, getBoundingBox().inflate(2F, 0.5F, 2F), e -> e instanceof Player);
                if (!player.isEmpty()) {
                    tameGolem((Player) player.get(0));
                }
            }

            if (isInSittingPose() || isChassisBroken() || (isPlayerCreated() && !isTame())) {
                if (getMossStage() < 3 && !isWaxed()) setMossTimer(getMossTimer() + 1);
                if (getSitTick() < 15) setSitTick(getSitTick() + 1);
                setPose(Pose.SITTING);
            } else {
                if (getSitTick() > 0) setSitTick(getSitTick() - 1);
                setPose(Pose.STANDING);
            }

            if (isChassisBroken()) {
                setBrokenTick(20);
                navigation.stop();
                if (getHealth() > 1) setHealth(1);
            } else {
                if (getBrokenTick() > 0) setBrokenTick(getBrokenTick() - 1);
            }

            if (getMossTimer() > 144000) {
                setMossStage(getMossStage() + 1);
                setMossTimer(0);
            }

            if (!getArmor().isEmpty() && getArmor().getItem() instanceof SteelGolemUpgradeItem armorItem) {
                armorItem.onArmorTick(level(), this);
            }

            setFastAttack(getArmor().getItem() instanceof SteelGolemUpgradeItem upgradeItem && upgradeItem.isFastAttack());
            setSprinting(isSprinting());
        }

        if (!list.isEmpty() && isInSittingPose()) {
            boolean flag = !level().isClientSide && !(getControllingPassenger() instanceof Player);
            for (Entity entity : list) {
                if (isSaddled() && flag && getPassengers().size() < getMaxPassengers() && hasEnoughSpaceFor(entity) && !(entity instanceof Enemy)) {
                    if (!entity.isPassenger() && entity instanceof LivingEntity && !(entity instanceof WaterAnimal) && !(entity instanceof Player))
                        entity.startRiding(this);
                } else push(entity);
            }
        }

        if (!entitySet.isEmpty() && entitySet.stream().findAny().get() instanceof Mob mob && !isChassisBroken()) {
            if (mob.getTarget() != null) {
                if (mob.getTarget() == this || (getOwner() != null && (mob.getTarget().is(getOwner()) || mob.getTarget().isAlliedTo(getOwner())))) {
                    if (entitySet.size() > 5 && getAoeTimer() > 160) {
                        setAoeTimer(160);
                    }
                    if (entitySet.size() > 2 && getAoeTimer() > 0 && getTarget() != null) {
                        setAoeTimer(getAoeTimer() - 1);
                    }
                }
            }
        }
    }

    @Override
    public void aiStep() {
        super.aiStep();

        // Code from CloudyMC's cave_dweller fork
        if (level() instanceof ServerLevel && !isChassisBroken()) {
            boolean isTwoAboveSolid = level().getBlockState(blockPosition().above(2)).isSolid();
            boolean isThreeAboveSolid = level().getBlockState(blockPosition().above(3)).isSolid();

            Vec3i offset = getDirectionVector();
            boolean isFacingSolid = level().getBlockState(blockPosition().relative(getDirection())).isSolid();

            if (isFacingSolid) offset = offset.offset(0, 1, 0);

            boolean isOffsetFacingSolid = level().getBlockState(blockPosition().offset(offset)).isSolid();
            boolean isOffsetFacingAboveSolid = level().getBlockState(blockPosition().offset(offset).above()).isSolid();
            boolean isOffsetFacingTwoAboveSolid = level().getBlockState(blockPosition().offset(offset).above(2)).isSolid();

            boolean shouldCrouch = isTwoAboveSolid || (!isOffsetFacingSolid && !isOffsetFacingAboveSolid && (isOffsetFacingTwoAboveSolid || isFacingSolid && isThreeAboveSolid));

            boolean isSculkSensorNearby = level().getBlockStates(getBoundingBox().inflate(9)).anyMatch((block) -> block.is(Blocks.SCULK_SENSOR));

            boolean isStuckWhileNav = getNavigation().isStuck();

            setPose(isInWall() || shouldCrouch || isSculkSensorNearby || isStuckWhileNav ? Pose.CROUCHING : getPose());
            refreshDimensions();
        }

        if (getTarget() != null && distanceToSqr(getTarget()) < 4.25D) {
            getNavigation().stop();
        }
    }

    private Vec3i getDirectionVector() {
        return new Vec3i(getDirection().getStepX(), getDirection().getStepY(), getDirection().getStepZ());
    }

    @Override
    public boolean dampensVibrations() {
        return isCrouching();
    }

    @Override
    public boolean isDiscrete() {
        return isCrouching();
    }

    @Override
    protected @NotNull MovementEmission getMovementEmission() {
        return isCrouching() ? MovementEmission.NONE : super.getMovementEmission();
    }

    @Override
    protected @NotNull PathNavigation createNavigation(@NotNull Level level) {
        return new TestGroundNavigation(this, level);
    }

    @Override
    public int getMaxFallDistance() {
        return getTarget() != null ? 3 + (int) (getHealth() - 1.0F) : 3;
    }

    private void setupAnim() {
        if (!isChassisBroken()) {
            if (isAttacking() && attackAnimTimeout <= 0 && getAoeTimer() > 10) {
                if (getAttackType() == 1) {
                    setAttackType(0);
                } else setAttackType(1);
                attackAnimTimeout = getArmor().getItem() instanceof SteelGolemUpgradeItem upgradeItem && upgradeItem.isFastAttack() ? 10 : 20;
                attackLoopAnimationState.stop();
                attackLoopAnimationState.start(tickCount);
            } else {
                --attackAnimTimeout;
            }

            if (isAoeAttacking() && aoeAttackAnimTimeout <= 0 && getAoeTimer() <= 0) {
                setAttackType(2);
                aoeAttackAnimTimeout = 35;
                attackLoopAnimationState.stop();
                upswingAttackAnimationState.stop();
                upswingAttackAnimationState.start(tickCount);
            } else {
                --aoeAttackAnimTimeout;
            }
        }
        if (isChassisBroken()) {
            ++eyeLayerTick;
            if (eyeLayerBrightness < 1) {
                eyeLayerBrightness += (0.0F - eyeLayerBrightness) * 0.02F;
            }
        } else {
            --eyeLayerTick;
            if (eyeLayerBrightness > 0) {
                eyeLayerBrightness -= (0.0F + eyeLayerBrightness) * 0.02F;
            }
        }

        sitFromStandAnimState.animateWhen(isInSittingPose() && !(getBrokenTick() > 0), tickCount);
        standFromSitAnimState.animateWhen(!isInSittingPose(), tickCount);

        brokenAnim.animateWhen(isChassisBroken(), tickCount);
        repairedAnim.animateWhen(!isChassisBroken() && getBrokenTick() != 0, tickCount);

        ridden.animateWhen(isVehicle(), tickCount);
        unRide.animateWhen(!isVehicle(), tickCount);

        chestOpened.animateWhen(chestOpened(), tickCount);
        chestClosed.animateWhen(!chestOpened(), tickCount);
    }

    public void stopAllAttackAnimation() {
        attackLoopAnimationState.stop();
    }

    @Override
    public void handleEntityEvent(byte pId) {
        if (pId == 4) {
            if (getAttackType() == 2) {
                spawnWideParticlesOnEntity(level(), position(), ParticleTypes.EXPLOSION, this, UniformInt.of(1, 2));
                spawnWideParticlesOnEntity(level(), position(), ParticleTypes.CAMPFIRE_COSY_SMOKE, this, UniformInt.of(1, 3));
                aoeAttackAnimTimeout = 15;
            }
            attackAnimTimeout = 10;
            playSound(SoundEvents.IRON_GOLEM_ATTACK, 1.0F, 0.5F);
        } else {
            super.handleEntityEvent(pId);
        }
    }

    @Override
    public boolean wantsToAttack(@NotNull LivingEntity target, @NotNull LivingEntity owner) {
        if (!(target instanceof Creeper) && !(target instanceof Ghast)) {
            if (target instanceof AstaliteGolem golem) {
                return !golem.isTame() || golem.getOwner() != owner;
            } else if (target instanceof Player && owner instanceof Player && !((Player)owner).canHarmPlayer((Player)target)) {
                return false;
            } else if (target instanceof OwnableEntity o && o.getOwner() != null) {
                return false;
            } else {
                return !(target instanceof TamableAnimal) || !((TamableAnimal)target).isTame();
            }
        } else {
            return false;
        }
    }

    @Override
    public int getMaxHeadXRot() {
        return isInSittingPose() || isVehicle() ? 0 : super.getMaxHeadXRot();
    }

    @Override
    public boolean canBeSeenAsEnemy() {
        return !isChassisBroken() && super.canBeSeenAsEnemy();
    }

    @Override
    protected @NotNull BodyRotationControl createBodyControl() {
        return new SmoothBodyControl(this);
    }

    @Override
    public @NotNull EntityDimensions getDimensions(@NotNull Pose pose) {
        if (pose == Pose.SITTING) {
            return EntityDimensions.scalable(rawBbWidth, rawBbHeight - 0.75F);
        } else if (pose == Pose.CROUCHING) {
            return EntityDimensions.scalable(rawBbWidth, rawBbHeight - 1);
        } else return super.getDimensions(pose);
    }

    @Override
    public boolean isNoAi() {
        return isChassisBroken() && super.isNoAi();
    }

    public ItemStack getSaddle() {
        return inventory.getItem(0);
    }

    public int getMossTimer() {
        return entityData.get(MOSS_TIMER);
    }

    public void setMossTimer(int ticks) {
        entityData.set(MOSS_TIMER, ticks);
    }

    public int getMossStage() {
        return entityData.get(MOSS_STAGE);
    }

    public void setMossStage(int stage) {
        entityData.set(MOSS_STAGE, stage);
    }

    public boolean isWaxed() {
        return entityData.get(IS_WAXED);
    }

    public void setWaxed(boolean i) {
        entityData.set(IS_WAXED, i);
    }

    public int getSitTick() {
        return entityData.get(SIT_TICK);
    }

    public void setSitTick(int i) {
        entityData.set(SIT_TICK, i);
    }

    public boolean getFastAttack() {
        return entityData.get(FAST_ATTACK);
    }

    public void setFastAttack(boolean b) {
        entityData.set(FAST_ATTACK, b);
    }

    @Override
    public boolean isChassisCompromised() {
        if (getHealth() <= 0) {
            if (isPlayerCreated() && getChassisHealth() > 1) {
                stopAllAttackAnimation();
                setChassisState(true);
                setChassisHealth(getChassisHealth() - 1);
                setHealth(1);
                stopBeingAngry();
                setAggressive(false);
                setOrderedToSit(false);
                return false;
            } else return true;
        } else return false;
    }

    @Override
    public void openCustomInventoryScreen(@NotNull Player player) {
        if(player instanceof ServerPlayer serverplayer) {
            if (isAlive()) {
                if (serverplayer.containerMenu != serverplayer.inventoryMenu) serverplayer.closeContainer();
                isChestOpened(true);
                serverplayer.nextContainerCounter();
                ModNetwork.INSTANCE.send(PacketDistributor.PLAYER.with(() -> serverplayer), new SteelGolemInventoryPacket(serverplayer.containerCounter, inventory.getContainerSize(), getId()));
                serverplayer.containerMenu = new SteelGolemInventoryMenu(serverplayer.containerCounter, serverplayer.getInventory(), inventory, this);
                serverplayer.initMenu(serverplayer.containerMenu);
                MinecraftForge.EVENT_BUS.post(new PlayerContainerEvent.Open(serverplayer, serverplayer.containerMenu));
            }
        }
    }

    @Override
    public InteractionResult openInventory(Player player) {
        openCustomInventoryScreen(player);
        return InteractionResult.SUCCESS;
    }

    protected int getInventorySize() {
        return 24;
    }

    @Override
    public boolean isInvulnerableTo(@NotNull DamageSource source) {
//        if (source.is(Etags.DamageTypes.STEEL_GOLEM_IMMUNE) && !source.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
//            return true;
//        } else if (isChassisBroken() && getChassisHealth() != 0) {
//            return true;
//        }
//        return super.isInvulnerableTo(source);
        return super.isInvulnerableTo(source) && (isChassisBroken() && getChassisHealth() != 0) && (source.is(Etags.DamageTypes.STEEL_GOLEM_IMMUNE) && !source.is(DamageTypeTags.BYPASSES_INVULNERABILITY));
    }

    protected void doPush(@NotNull Entity entity) {
        if (entity instanceof Enemy && !(entity instanceof Creeper) && !tamedMob(entity) && getRandom().nextInt(20) == 0 && getAggroState()) {
            setTarget((LivingEntity)entity);
        }
        super.doPush(entity);
    }

    @Override
    public boolean isPushable() {
        if (getArmor().getItem() instanceof SteelGolemUpgradeItem golemUpgradeItem) {
            GolemUpgradeProperties golemUpgradeProperties = golemUpgradeItem.getGolemUpgradeProperties();
            return golemUpgradeProperties != null && !golemUpgradeItem.isNotPushable() && !(isInSittingPose() || isChassisBroken() || isVehicle());
        }
        return !(isInSittingPose() || isChassisBroken() || isVehicle());
    }

    //Some Ridable Relate Stuff
    @Override
    protected float getRiddenSpeed(@NotNull Player player) {
        float f = player.isSprinting() ? 0.1F : 0.0F;
        return (float) (getAttributeValue(Attributes.MOVEMENT_SPEED) * 0.3F) + f;
    }

    @Override
    public boolean canSprint() {
        return true;
    }

    @Override
    protected boolean isImmobile() {
        return isChassisBroken() || (isPlayerCreated() && !isTame()) || super.isImmobile();
    }

    public boolean canSpawnSprintParticle() {
        return getDeltaMovement().horizontalDistanceSqr() > (double)2.5000003E-7F && random.nextInt(5) == 0;
    }

    //Entity Damage / Hurt Events
    public boolean doHurtTarget(@NotNull Entity entity) {
        level().broadcastEntityEvent(this, (byte)4);
        float f = (float) getAttributeValue(Attributes.ATTACK_DAMAGE);
        float f1 = (int)f > 0 ? f / 1.5F + (float) random.nextInt((int)f) : f;
        boolean flag2 = fallDistance > 0.0F
                && !onGround() && !onClimbable()
                && !isInWater() && !hasEffect(MobEffects.BLINDNESS)
                && !isPassenger() && entity instanceof LivingEntity;
        if (flag2) f1 *= 1.5F;
        boolean flag = entity.hurt(damageSources().mobAttack(this), f1);

        if (flag) {
            double d2;
            if (entity instanceof LivingEntity livingentity)
                d2 = livingentity.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE);
            else d2 = 0.0D;

            double d0 = d2;
            double d1 = Math.max(0.0D, 2.0D - d0);
            entity.setDeltaMovement(entity.getDeltaMovement().multiply(d1, 1.0D, d1));
            doEnchantDamageEffects(this, entity);
        }

        playSound(SoundEvents.IRON_GOLEM_ATTACK, 1.0F, 1.0F);
        return flag;
    }

    @Override
    public boolean hurt(@NotNull DamageSource source, float amount) {
        AstaliteGolem.Crackiness steelgolem$crackiness = getCrackiness();
        boolean flag = super.hurt(source, amount);
        if (!level().isClientSide) {
            if (!isWearingArmor() || (isWearingArmor() && (amount > getMaxHealth() / 5 || getHealth() < getMaxHealth() / 2)))
                setOrderedToSit(false);
        }

        if (flag && getCrackiness() != steelgolem$crackiness) {
            playSound(SoundEvents.IRON_GOLEM_DAMAGE, 1.0F, 1.0F);
        }

        return flag;
    }

    @Override
    public void die(@NotNull DamageSource cause) {
        if (!isChassisCompromised()) {
            dead = false;
            if (!level().isClientSide && level().getGameRules().getBoolean(GameRules.RULE_SHOWDEATHMESSAGES) && getOwner() instanceof ServerPlayer) {
                getOwner().sendSystemMessage(Component.translatable("entity.elementus.steel_golem_down", getChassisHealth()));
                stopBeingAngry();
                ejectPassengers();
            }
        } else super.die(cause);
        stopBeingAngry();
        ejectPassengers();
    }

    @Override
    public boolean canBeCollidedWith() {
        if (!getArmor().isEmpty() && getArmor().getItem() instanceof SteelGolemUpgradeItem armorItem) {
            return armorItem.isNotPushable() && (isInSittingPose() || isChassisBroken());
        } else return super.canBeCollidedWith();
    }

    @Override
    protected @NotNull ResourceLocation getDefaultLootTable() {
        if (getMossStage() == 0) {
            return getType().getDefaultLootTable();
        } else {
            return switch (getMossStage()) {
                case 1 -> modLoc("entities/steel_golem/moss_1");
                case 2 -> modLoc("entities/steel_golem/moss_2");
                case 3 -> modLoc("entities/steel_golem/moss_3");
                default -> throw new IncompatibleClassChangeError();
            };
        }
    }

    protected void playStepSound(@NotNull BlockPos pPos, @NotNull BlockState pBlock) {
        playSound(SoundEvents.IRON_GOLEM_STEP, 2.0F, 0.5F);
    }

    public @NotNull Vec3 getLeashOffset() {
        return new Vec3(0.0D, 0.95F * getEyeHeight(), getBbWidth() * 0.4F);
    }

    @Override
    public boolean isShearable(@NotNull ItemStack item, Level level, BlockPos pos) {
        return getMossStage() > 0 && !isChassisBroken();
    }

    @Override
    public @NotNull List<ItemStack> onSheared(@Nullable Player player, @NotNull ItemStack item, Level level, BlockPos pos, int fortune) {
        level.playSound(null, this, SoundEvents.MOSS_BREAK, player == null ? SoundSource.BLOCKS : SoundSource.PLAYERS, 1.0F, 1.0F);
        gameEvent(GameEvent.SHEAR, player);
        if (!level.isClientSide) {
            int i = 1 + random.nextInt(3);

            List<ItemStack> items = new ArrayList<>();
            for (int j = 0; j < i; ++j) {
                items.add(new ItemStack(Items.MOSS_BLOCK));
            }
            return items;
        }
        return Collections.emptyList();
    }

    @Override
    public void shear(@NotNull SoundSource pSource) {
        level().playSound(null, this, SoundEvents.SHEEP_SHEAR, pSource, 1.0F, 1.0F);
        setMossStage(getMossStage() - 1);
        int i = 1 + random.nextInt(3);

//        for(int j = 0; j < i; ++j) {
//        }
        ItemEntity itemEntity = spawnAtLocation(new ItemStack(Items.MOSS_BLOCK));
        if (itemEntity != null) {
            itemEntity.setDeltaMovement(itemEntity.getDeltaMovement().add((random.nextFloat() - random.nextFloat()) * 0.1F, random.nextFloat() * 0.05F, (random.nextFloat() - random.nextFloat()) * 0.1F));
        }
    }

    @Override
    public boolean readyForShearing() {
        return getMossStage() > 0 && !isChassisCompromised();
    }

    public AstaliteGolem.Crackiness getCrackiness() {
        return AstaliteGolem.Crackiness.byFraction(getHealth() / getMaxHealth());
    }

    public ChassisCrackiness getChassisCrackiness() {
        if (isPlayerCreated()) {
            return ChassisCrackiness.byFraction((float) getChassisHealth() / 5);
        } else return ChassisCrackiness.NONE;
    }

    //Golem Details
    public enum Crackiness {
        NONE(1.0F),
        LOW(0.75F),
        MEDIUM(0.5F),
        HIGH(0.25F);

        private static final List<AstaliteGolem.Crackiness> BY_DAMAGE = Stream.of(values())
                .sorted(Comparator.comparingDouble((crack) -> crack.fraction)).collect(ImmutableList.toImmutableList());

        private final float fraction;
        Crackiness(float pFraction) {
            fraction = pFraction;
        }

        public static AstaliteGolem.Crackiness byFraction(float pFraction) {
            for(AstaliteGolem.Crackiness steelgolem$crackiness : BY_DAMAGE)
                if (pFraction < steelgolem$crackiness.fraction)
                    return steelgolem$crackiness;
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
                .sorted(Comparator.comparingDouble((crack) -> crack.fraction)).collect(ImmutableList.toImmutableList());

        private final float fraction;
        ChassisCrackiness(float pFraction) {
            fraction = pFraction;
        }

        public static ChassisCrackiness byFraction(float pFraction) {
            for(ChassisCrackiness steelgolem$chassis : BY_DAMAGE)
                if (pFraction < steelgolem$chassis.fraction)
                    return steelgolem$chassis;
            return NONE;
        }
    }
}