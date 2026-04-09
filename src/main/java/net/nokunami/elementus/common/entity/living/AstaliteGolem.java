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
import net.minecraft.util.Mth;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.MultifaceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.IForgeShearable;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.nokunami.elementus.common.config.EntityConfig;
import net.nokunami.elementus.common.entity.CombatAction;
import net.nokunami.elementus.common.entity.MobUtil;
import net.nokunami.elementus.common.entity.MovementType;
import net.nokunami.elementus.common.entity.ai.goal.*;
import net.nokunami.elementus.common.entity.ai.goal.astaliteGolem.AstaliteGolemAttackGoal;
import net.nokunami.elementus.common.entity.ai.goal.steelGolem.*;
import net.nokunami.elementus.common.entity.ai.navigation.GolemNavigation;
import net.nokunami.elementus.common.entity.living.combat.AttackStyle;
import net.nokunami.elementus.common.entity.living.combat.IAdaptiveAttacker;
import net.nokunami.elementus.common.inventory.AstaliteGolemInventoryMenu;
import net.nokunami.elementus.common.item.SteelGolemUpgradeItem;
import net.nokunami.elementus.common.network.ENetwork;
import net.nokunami.elementus.common.network.client.GolemInventoryS2CPacket;
import net.nokunami.elementus.common.network.syncer.EEntityDataSerializers;
import net.nokunami.elementus.common.registry.ESounds;
import net.nokunami.elementus.common.tags.EDamageTypeTags;
import net.nokunami.elementus.common.tags.EEntityTags;
import net.nokunami.elementus.common.tags.EItemTags;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static net.minecraft.world.level.block.Blocks.LAVA;
import static net.minecraft.world.level.block.Blocks.WATER;
import static net.nokunami.elementus.Elementus.modLoc;
import static net.nokunami.elementus.common.entity.MobUtil.tamedMob;
import static net.nokunami.elementus.common.entity.ModParticleUtil.spawnParticlesOnEntity;
import static net.nokunami.elementus.common.entity.ModParticleUtil.spawnWideParticlesOnEntity;
import static net.nokunami.elementus.common.registry.ESounds.STEEL_GOLEM_REPAIR;
import static net.nokunami.elementus.common.registry.ESounds.STEEL_GOLEM_REVIVE;

@SuppressWarnings("deprecation")
public class AstaliteGolem extends TamableGolem implements IForgeShearable, IMossOverTime, IAdaptiveAttacker {
    private static final EntityDataAccessor<Boolean> FAST_ATTACK = SynchedEntityData.defineId(AstaliteGolem.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IS_WAXED = SynchedEntityData.defineId(AstaliteGolem.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> MOSS_TIMER = SynchedEntityData.defineId(AstaliteGolem.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> MOSS_STAGE = SynchedEntityData.defineId(AstaliteGolem.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> SIT_TICK = SynchedEntityData.defineId(AstaliteGolem.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> CURRENT_ATTACK_ARM = SynchedEntityData.defineId(AstaliteGolem.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<MovementType> MOVEMENT_TYPE = SynchedEntityData.defineId(AstaliteGolem.class, EEntityDataSerializers.MOVEMENT_TYPE);
    private static final EntityDataAccessor<CombatAction> COMBAT_ACTION = SynchedEntityData.defineId(AstaliteGolem.class, EEntityDataSerializers.COMBAT_ACTION);
    private static final EntityDataAccessor<Float> SPRINT = SynchedEntityData.defineId(AstaliteGolem.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> SPRINT_O = SynchedEntityData.defineId(AstaliteGolem.class, EntityDataSerializers.FLOAT);
    public final AnimationState golemAttackAnim = new AnimationState();
    public final AnimationState upswingAttackAnimationState = new AnimationState();
    public int attackAnimTimeout = 0;
    public int aoeAttackAnimTimeout = 0;
    public int aoeAnimTimeout = 320;
    public float eyeLayerBrightness;
    public float eyeOldLayerBrightness;
    public int eyeLayerTick;
    public Predicate<Entity> GOLEM_SURROUNDING_TARGETS = (entity) -> entity instanceof  Mob mob
            && ((entity instanceof Enemy || entity == getTarget()) || (getOwner() != null && mob.getTarget() == getOwner()));
    private Entity priorityTarget;
    private Entity annoyingTarget;
    private Entity rangedTarget;
    private Entity currentTarget;

    public AstaliteGolem(EntityType<? extends AstaliteGolem> type, Level level) {
        super(type, level);
        createInventory();
        setCanEquipChest(true);
        setMaxUpStep(1.5F);
        setPathfindingMalus(BlockPathTypes.UNPASSABLE_RAIL, 0);
    }

    // ----- [ DATA ]

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

    public float getBBWidth() { return 0; }
    public float getBBHeight() { return 0; }

    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(FAST_ATTACK, false);
        entityData.define(IS_WAXED, false);
        entityData.define(MOSS_TIMER, 0);
        entityData.define(MOSS_STAGE, 0);
        entityData.define(SIT_TICK, 0);
        entityData.define(CURRENT_ATTACK_ARM, 0);
        entityData.define(MOVEMENT_TYPE, MovementType.STAND);
        entityData.define(COMBAT_ACTION, CombatAction.NONE);
        entityData.define(SPRINT, 0F);
        entityData.define(SPRINT_O, 0F);
    }

    public void addAdditionalSaveData(@NotNull CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putBoolean("FastAttack", getFastAttack());
        tag.putBoolean("Waxed", isWaxed());
        tag.putInt("MossTimer", getMossTimer());
        tag.putInt("MossStage", getMossStage());
        tag.putInt("SitTick", getSitTick());
        if (!inventory.getItem(1).isEmpty())
            tag.put("ArmorItem", inventory.getItem(1).save(new CompoundTag()));
        if (!inventory.getItem(2).isEmpty())
            tag.put("LeavesDecoration", inventory.getItem(2).save(new CompoundTag()));
        if (!inventory.getItem(3).isEmpty())
            tag.put("DecorItem", inventory.getItem(3).save(new CompoundTag()));
        tag.putInt("CurrentAttackArm", getCurrentAttackArm());
        tag.putString("MovementType", getMovementType().toString());
        tag.putString("CombatAction", getCombatAction().toString());
    }
    public void readAdditionalSaveData(@NotNull CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        setFastAttack(tag.getBoolean("FastAttack"));
        setWaxed(tag.getBoolean("Waxed"));
        setMossTimer(tag.getInt("MossTimer"));
        setMossStage(tag.getInt("MossStage"));
        if (tag.contains("ArmorItem", 10)) {
            ItemStack armorItem = ItemStack.of(tag.getCompound("ArmorItem"));
            if (!armorItem.isEmpty() && isArmor(armorItem))
                inventory.setItem(1, armorItem);
        }
        if (tag.contains("LeavesDecoration", 10)) {
            ItemStack leaves = ItemStack.of(tag.getCompound("LeavesDecoration"));
            if (leaves.is(EItemTags.STEEL_GOLEM_LEAVES_DECORATION))
                inventory.setItem(2, leaves);
        }
        if (tag.contains("DecorItem", 10))
            inventory.setItem(3, ItemStack.of(tag.getCompound("DecorItem")));
        updateContainerEquipment();
        setSitTick(tag.getInt("SitTick"));
        setCurrentAttackArm(tag.getInt("CurrentAttackArm"));
        setMovementType(MovementType.valueOf(tag.getString("MovementType")));
        setCombatAction(CombatAction.valueOf(tag.getString("CombatAction")));
//        setSprintPercent(tag.getFloat("Sprint"));
    }

    public boolean getFastAttack() { return entityData.get(FAST_ATTACK); }
    public void setFastAttack(boolean b) { entityData.set(FAST_ATTACK, b); }

    public boolean isWaxed() { return entityData.get(IS_WAXED); }
    public void setWaxed(boolean i) { entityData.set(IS_WAXED, i); }

    @Override public int getMossTimer() { return entityData.get(MOSS_TIMER); }
    @Override public void setMossTimer(int ticks) { entityData.set(MOSS_TIMER, ticks); }

    @Override public int getMossStage() { return entityData.get(MOSS_STAGE); }
    @Override public void setMossStage(int stage) { entityData.set(MOSS_STAGE, stage); }

    public int getSitTick() { return entityData.get(SIT_TICK); }
    public void setSitTick(int i) { entityData.set(SIT_TICK, i); }

    public int getCurrentAttackArm() { return entityData.get(CURRENT_ATTACK_ARM); }
    public void setCurrentAttackArm(int i) { entityData.set(CURRENT_ATTACK_ARM, i); }

    public MovementType getMovementType() { return entityData.get(MOVEMENT_TYPE); }
    public void setMovementType(MovementType type) { entityData.set(MOVEMENT_TYPE, type); }

    public CombatAction getCombatAction() { return entityData.get(COMBAT_ACTION); }
    public void setCombatAction(CombatAction type) { entityData.set(COMBAT_ACTION, type); }

    public float getSprintPercent() { return entityData.get(SPRINT); }
    public void setSprintPercent(float type) { entityData.set(SPRINT, type); }

    public float getSprintOPercent() { return entityData.get(SPRINT_O); }
    public void setSprintOPercent(float type) { entityData.set(SPRINT_O, type); }

    // ----- [ INVENTORY ]

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

    public ItemStack getArmor() { return getItemBySlot(EquipmentSlot.CHEST); }
    private void setArmor(ItemStack stack) {
        setItemSlot(EquipmentSlot.CHEST, stack);
        setDropChance(EquipmentSlot.CHEST, 0.0F);
    }

    public ItemStack isCamouflaged() { return getItemBySlot(EquipmentSlot.LEGS); }
    private void wearCamouflaged(ItemStack stack) {
        setItemSlot(EquipmentSlot.LEGS, stack);
        setDropChance(EquipmentSlot.LEGS, 0.0F);
    }

    public ItemStack getDripCarpet() { return getItemBySlot(EquipmentSlot.FEET); }
    private void wearDripCarpet(ItemStack stack) {
        setItemSlot(EquipmentSlot.FEET, stack);
        setDropChance(EquipmentSlot.FEET, 0.0F);
    }

    private void setArmorEquipment(ItemStack stack) {
        setArmor(stack);
        if (!level().isClientSide) {
            if (stack.getItem() instanceof SteelGolemUpgradeItem item) {
                if (!stack.isEmpty()) getAttributes().removeAttributeModifiers(item.getGolemAttributes());
                if (!stack.isEmpty()) getAttributes().addTransientAttributeModifiers(item.getGolemAttributes());
            }
        }
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
            playSound(ESounds.STEEL_GOLEM_ARMORED.get(), 0.5F, 1.0F);
        if (tickCount > 20 && leaves != leaves1)
            playSound(ESounds.STEEL_GOLEM_LEAVES_SWAG.get(), 0.5F, 1.0F);
        if (tickCount > 20 && carpet != carpet1)
            playSound(ESounds.STEEL_GOLEM_CARPET_SWAG.get(), 0.5F, 1.0F);
    }

    @Override
    public void openCustomInventoryScreen(@NotNull Player player) {
        if(player instanceof ServerPlayer serverplayer) {
            if (isAlive()) {
                if (serverplayer.containerMenu != serverplayer.inventoryMenu) serverplayer.closeContainer();
                isChestOpened(true);
                serverplayer.nextContainerCounter();
                ENetwork.sendTo(serverplayer, new GolemInventoryS2CPacket(serverplayer.containerCounter, inventory.getContainerSize(), getId()));
                serverplayer.containerMenu = new AstaliteGolemInventoryMenu(serverplayer.containerCounter, serverplayer.getInventory(), inventory, this);
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

    @Override protected int getInventorySize() { return 24; }
    @Override public boolean canWearArmor() { return true; }

    @Override public boolean isArmor(@NotNull ItemStack stack) {
        return stack.getItem() instanceof SteelGolemUpgradeItem;
    }

    // ----- [ ENTITY LOGIC ]

    @Override
    protected void registerGoals() {
//        goalSelector.addGoal(0, new AdaptiveAttackGoal<>(this));
//        goalSelector.addGoal(0, new AdaptiveAttackMoveGoal<>(this));
        goalSelector.addGoal(0, new MossGrowGoal<>(this));
        goalSelector.addGoal(0, new MovementTypeGoal(this));
        goalSelector.addGoal(0, new AvoidEntityGoal<>(this, LivingEntity.class, 6, 1, 1.2, e -> e.getType().is(EEntityTags.STEEL_GOLEM_AVOID)));
        goalSelector.addGoal(1, new AstaliteGolemAttackGoal(this, 1.2D, true));
        goalSelector.addGoal(2, new GolemMoveTowardsTargetGoal(this, 0.9D, 32.0F));
        goalSelector.addGoal(2, new GolemFollowOwnerGoal(this, new GolemFollowOwnerGoal.goalInfo().
                speed(1, 1.25).start(8, 20).stop(6, 9).teleport(12, 28)));
        goalSelector.addGoal(7, new GolemLookGoal(this, new GolemLookGoal.goalInfo().lookAt(Player.class, 12)));
        goalSelector.addGoal(7, new GolemStrollGoal(this, 0.5D));
        goalSelector.addGoal(8, new GolemLookGoal(this));
        targetSelector.addGoal(0, new SteelGolemNearestAttackableGoal<>(this, Mob.class, 5, false, false,
                (entity) -> entity.getType().is(EEntityTags.STEEL_GOLEM_PRIORITY_TARGETS)));
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

    @Override
    public void baseTick() {
        super.baseTick();
        setCustomName(Component.literal(String.valueOf(getAttackCooldown())));
        List<Entity> list = level().getEntities(this, getBoundingBox().inflate(0.2F, -0.01F, 0.2F), EntitySelector.pushableBy(this));
        Set<Entity> entitySet = new HashSet<>(level().getEntitiesOfClass(LivingEntity.class, getBoundingBox().inflate(2), GOLEM_SURROUNDING_TARGETS));

//        float f = getActTick() < getAnimDelay() && isProducingSun() && !isPlantSleeping() ? 1 : -2;
//        brightnessLayerO = brightnessLayer;
//        brightnessLayer = Mth.clamp(brightnessLayer + (f / (getAnimDelay())), 0, 1);
        float f = (float) (getMovementType().equals(MovementType.RUN) ? 1 : -1) / 50;
        setSprintOPercent(getSprintPercent());
        setSprintPercent(Mth.clamp(getSprintPercent() + f, 0, 1));

        if (level().isClientSide) setupAnim();

        if (!level().isClientSide) {
            updatePersistentAnger((ServerLevel) level(), true);
            if (getControllingPassenger() == null) setMovementType(MovementType.WALK);

            if (!isTamed() && isPlayerCreated()) sit(true);

            setSitTick(Math.max(getSitTick() - 1, 0));

            if (isOrderedToSit() || isChassisBroken() || (isPlayerCreated() && !isTamed())) {
//                if (getSitTick() < 6) setSitTick(getSitTick() + 1);
                setPose(Pose.SITTING);
            } else {
//                if (getSitTick() > 0) setSitTick(getSitTick() - 1);
                setPose(Pose.STANDING);
            }

            if (isChassisBroken()) {
                setBrokenTick(20);
                navigation.stop();
                if (getHealth() > 1) setHealth(1);
            } else {
                if (getBrokenTick() > 0) setBrokenTick(getBrokenTick() - 1);
            }

            if (!getArmor().isEmpty() && getArmor().getItem() instanceof SteelGolemUpgradeItem armorItem) {
                armorItem.onArmorTick(level(), this);
            }

            setFastAttack(getArmor().getItem() instanceof SteelGolemUpgradeItem upgradeItem && upgradeItem.isFastAttack());
//            setSprinting(isSprinting());
        }

        if (!list.isEmpty() && isOrderedToSit()) {
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

    @Override public boolean dampensVibrations() { return isCrouching(); }
    @Override public boolean isDiscrete() { return isCrouching(); }

    @Override protected @NotNull MovementEmission getMovementEmission() {
        return isCrouching() ? MovementEmission.NONE : super.getMovementEmission();
    }

    @Override
    protected @NotNull PathNavigation createNavigation(@NotNull Level level) {
        return new GolemNavigation(this, level);
    }

    @Override public int getMaxFallDistance() { return getTarget() != null ? 3 + (int) (getHealth() - 1.0F) : 3; }

    @Override
    public void customEntityEvent() {
        if (getAttackType() == 2) {
            spawnWideParticlesOnEntity(level(), position(), ParticleTypes.EXPLOSION, this, UniformInt.of(1, 2));
            spawnWideParticlesOnEntity(level(), position(), ParticleTypes.CAMPFIRE_COSY_SMOKE, this, UniformInt.of(1, 3));
            aoeAttackAnimTimeout = 15;
        }
        attackAnimTimeout = 10;
        playSound(SoundEvents.IRON_GOLEM_ATTACK, 1.0F, 0.5F);
    }

    @Override
    public boolean wantsToAttack(@NotNull LivingEntity target, @NotNull LivingEntity owner) {
        if (!(target instanceof Creeper) && !(target instanceof Ghast)) {
            if (target instanceof AstaliteGolem golem) {
                return !golem.isTamed() || golem.getOwner() != owner;
            } else if ((target instanceof Player && owner instanceof Player && !((Player)owner).canHarmPlayer((Player) target))/* || (target instanceof OwnableEntity o && o.getOwner() != null)*/) {
                return false;
            } /*else if (target instanceof OwnableEntity o && o.getOwner() != null) {
                return false;
            } else {
                return !(target instanceof TamableAnimal) || !((TamableAnimal)target).isTame();
            }*/ else {
                return !(target instanceof OwnableEntity o && o.getOwner() != null);
            }
        } else {
            return false;
        }
    }

    @Override
    public @NotNull EntityDimensions getDimensions(@NotNull Pose pose) {
        float sitting = getPose() == Pose.SITTING ? 0.5F : 0;
        float crouch = getPose() == Pose.CROUCHING ? 0.6F : 0;
        return EntityDimensions.scalable(getBBWidth(), getBBHeight() - Math.max(sitting, crouch));
    }

    @Override public boolean canSprint() { return true; }

    @Override
    public void die(@NotNull DamageSource cause) {
        if (!isChassisCompromised()) {
            dead = false;
            if (!level().isClientSide && level().getGameRules().getBoolean(GameRules.RULE_SHOWDEATHMESSAGES) && getOwner() instanceof ServerPlayer) {
                getOwner().sendSystemMessage(Component.translatable("entity.elementus.steel_golem_down", getChassisHealth()));
            }
        } else super.die(cause);
        ejectPassengers();
        stopBeingAngry();
    }

    @Override
    public boolean isChassisCompromised() {
        if (getHealth() <= 0)
            if (isPlayerCreated() && getChassisHealth() > 1) {
                setHealth(1);
                stopAllAnimation();
                setChassisState(true);
                setChassisHealth(getChassisHealth() - 1);
                setAggressive(false);
                sit(false);
                return false;
            } else return true;
        else return false;
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

    public static boolean canPassThrough(Block block) {
        return block instanceof MultifaceBlock || block.defaultBlockState().getBlock() == LAVA || block.defaultBlockState().getBlock() == WATER || block.isPossibleToRespawnInThis(block.defaultBlockState());
    }

    // ----- [ RIDEABLE ]

    @Override
    protected void positionRider(@NotNull Entity passenger, @NotNull MoveFunction moveFunction) {
        float sitting = ((float) (1 - (getSitTick()) / getDefaultSitTick())) * 0.4F;
        float crouch = getPose() == Pose.CROUCHING ? 1 : 0;
        double passengerRidingOffset = (getBBHeight() - Math.max(sitting, crouch)) * 0.85D;
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

    @Override protected boolean canAddPassenger(@NotNull Entity passenger) { return getPassengers().size() < getMaxPassengers(); }

    @Override protected int getMaxPassengers() { return 3; }

    public boolean hasEnoughSpaceFor(Entity entity) {
        return entity.getBbWidth() < getBbWidth() || entity.getBbHeight() < getBbHeight();
    }

    @Override
    protected float getRiddenSpeed(@NotNull Player player) {
        return (float) (getAttributeValue(Attributes.MOVEMENT_SPEED) * 0.3F) + (player.isSprinting() ? 0.1F : 0);
    }

    // ----- [ INTERACTION ]

    @Override
    public boolean isInvulnerableTo(@NotNull DamageSource source) {
        return super.isInvulnerableTo(source) || (isChassisBroken() && getChassisHealth() != 0) && (source.is(EDamageTypeTags.STEEL_GOLEM_IMMUNE) && !source.is(DamageTypeTags.BYPASSES_INVULNERABILITY));
    }

    @Override
    protected void doPush(@NotNull Entity entity) {
        if (entity instanceof Enemy && !(entity instanceof Creeper) && !tamedMob(entity) && getRandom().nextInt(20) == 0 && getAggroState()) {
            setTarget((LivingEntity)entity);
        }
        super.doPush(entity);
    }

    @Override
    public boolean isPushable() {
//        if (getArmor().getItem() instanceof SteelGolemUpgradeItem golemUpgradeItem) {
//            GolemUpgradeProperties golemUpgradeProperties = golemUpgradeItem.getGolemUpgradeProperties();
//            return golemUpgradeProperties != null && !golemUpgradeItem.isNotPushable() && !(isOrderedToSit() || isChassisBroken() || isVehicle());
//        }
//        return !(isOrderedToSit() || isChassisBroken() || isVehicle());
        return false;
    }

    @Override
    public boolean doHurtTarget(@NotNull Entity entity) {
        level().broadcastEntityEvent(this, (byte)4);
        float f = (float) getAttributeValue(Attributes.ATTACK_DAMAGE);
        float f1 = (int)f > 0 ? f / 1.5F + (float) random.nextInt((int)f) : f;
        boolean flag2 = fallDistance > 0.0F
                && !onGround() && !onClimbable()
                && !isInWater() && !hasEffect(MobEffects.BLINDNESS)
                && !isPassenger() && entity instanceof LivingEntity;
        if (flag2) f1 *= Math.max(1.5F, fallDistance / 2);
        boolean flag = entity.hurt(damageSources().mobAttack(this), f1);

        if (flag) {
            double d2;
            if (entity instanceof LivingEntity livingentity) d2 = livingentity.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE);
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
        Crackiness steelgolem$crackiness = getCrackiness();
        boolean flag = super.hurt(source, amount);
        if (!level().isClientSide) {
            if (!isWearingArmor() || (isWearingArmor() && (amount > getMaxHealth() / 5 || getHealth() < getMaxHealth() / 2))) sit(false);
        }

        if (flag && getCrackiness() != steelgolem$crackiness) playSound(SoundEvents.IRON_GOLEM_DAMAGE, 1.0F, 1.0F);

        return flag;
    }

    @Override
    public boolean canBeCollidedWith() {
        if (!getArmor().isEmpty() && getArmor().getItem() instanceof SteelGolemUpgradeItem armorItem) {
            return armorItem.isNotPushable() && (isOrderedToSit() || isChassisBroken());
        } else return super.canBeCollidedWith();
    }

    @Override
    public @NotNull InteractionResult interactAt(@NotNull Player player, @NotNull Vec3 vec3, @NotNull InteractionHand hand) {
        double d0 = vec3.y;
        boolean saddleHeight = d0 >= getBbHeight() * 0.65F;

        if (saddleHeight && isOrderedToSit() && isTamed() && !isChassisBroken() && isSaddled()) {
            if (!getPassengers().isEmpty() && isOwnedBy(player) && player.isSecondaryUseActive()) {
                ejectPassengers();
                return InteractionResult.SUCCESS;
            }
            return doPlayerRide(player);
        } else return super.interactAt(player, vec3, hand);
    }

    @Override
    public @NotNull InteractionResult mobInteract(@NotNull Player player, @NotNull InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        boolean canHeal = getHealth() < getMaxHealth();
        boolean leaves = itemStack.is(EItemTags.STEEL_GOLEM_LEAVES_DECORATION) && isCamouflaged().isEmpty();
        boolean carpet = itemStack.is(EItemTags.STEEL_GOLEM_CARPET_DECORATION) && getDripCarpet().isEmpty();
        boolean wax = itemStack.is(Items.HONEYCOMB) && !isWaxed();
        boolean scrapWax = itemStack.is(ItemTags.AXES) && isWaxed();
        boolean chassisCondition = getChassisHealth() < 5;
        boolean healItem = itemStack.is(EItemTags.STEEL_GOLEM_HEAL) || itemStack.is(EItemTags.STEEL_GOLEM_REPAIR_HALF);
        boolean repair = itemStack.is(EItemTags.STEEL_GOLEM_REPAIR_FULL);
        boolean aggroStateChanger = (itemStack.is(ItemTags.SWORDS) || itemStack.is(ItemTags.AXES)) && player.isSecondaryUseActive();
        InteractionResult interactionresult = super.mobInteract(player, hand);

        if ((isPlayerCreated() || player.isCreative()) && !isTamed()) tame(player);

        if ((canHeal && healItem) || (chassisCondition && repair))
            return healGolem(player, itemStack);
        else {
            if (isShearable(itemStack, level(), blockPosition())) {
                return interactionresult;
            }
            if (isOrderedToSit() && !isAngry() && player.isSecondaryUseActive())
                return openInventory(player);
            if (isOwnedBy(player)) {
                if (aggroStateChanger)
                    return aggroStateChange();
                if (leaves||carpet||wax||scrapWax)
                    return setGolemEquipable(player, hand);
            }

            if ((!interactionresult.consumesAction() || isBaby()) && isOwnedBy(player) && getSitTick() == 0 && !isChassisBroken()) {
                return sit(!isOrderedToSit());
            } else {
                return interactionresult;
            }
        }
    }

    @Override
    public InteractionResult sit(boolean sit) {
        setSitTick(getDefaultSitTick());
        return super.sit(sit);
    }

    public int getDefaultSitTick() {
        return 0;
    }

    public InteractionResult setGolemEquipable(Player player, InteractionHand hand) {
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
            inventory.setItem(stack.is(EItemTags.STEEL_GOLEM_LEAVES_DECORATION) ? 2 : 3, stack.copyWithCount(1));
            if (!player.isCreative()) stack.shrink(1);
        }
        navigation.stop();
        return InteractionResult.SUCCESS;
    }

    public InteractionResult healGolem(Player player, ItemStack stack) {
        boolean chassisCondition = getChassisHealth() < 5;
        boolean repairHalf = stack.is(EItemTags.STEEL_GOLEM_REPAIR_HALF);
        boolean repairFull = stack.is(EItemTags.STEEL_GOLEM_REPAIR_FULL);
        float randomFloat = 1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F;
        int b = isChassisBroken() ? 1 : 0;

        float healAmount = 1;
        if (repairHalf) healAmount = 0.5F;

        if (isChassisBroken() && isOrderedToSit()) sit(true);
        setChassisState(false);
        if (chassisCondition && repairFull) setChassisHealth(getChassisHealth() + 1);
        heal((int) ((EntityConfig.steelGolem_MaxHealth / EntityConfig.steelGolem_RepairAmount) * healAmount) - b);
        playSound(isChassisBroken() ? STEEL_GOLEM_REVIVE.get() : STEEL_GOLEM_REPAIR.get(), 1.0F, randomFloat);
        if (!player.isCreative()) stack.shrink(1);
        return InteractionResult.SUCCESS;
    }

    public InteractionResult aggroStateChange() {
        navigation.stop();
        setAggroState(!getAggroState());
//        setTarget(null);
        stopBeingAngry();
        return InteractionResult.SUCCESS;
    }

    @Override
    public boolean isShearable(@NotNull ItemStack item, Level level, BlockPos pos) {
        return item.is(Items.SHEARS) && getMossStage() > 0 && !isChassisBroken();
    }

    @Override
    public @NotNull List<ItemStack> onSheared(@Nullable Player player, @NotNull ItemStack item, Level level, BlockPos pos, int fortune) {
        level.playSound(null, this, SoundEvents.MOSS_BREAK, player == null ? SoundSource.BLOCKS : SoundSource.PLAYERS, 1.0F, 1.0F);
        gameEvent(GameEvent.SHEAR, player);
        if (!level.isClientSide) {
            setMossStage(getMossStage() - 1);

            List<ItemStack> items = new ArrayList<>();
            for (int j = 0; j < getMossStage() + random.nextInt(3); ++j)
                items.add(new ItemStack(Items.MOSS_BLOCK));
            return items;
        }
        return Collections.emptyList();
    }

    // ----- [ CLIENT DATA ]

    /// Animation stuff, mostly
    private void setupAnim() {
        if (!isChassisBroken()) {
            if (isAttacking() && attackAnimTimeout <= 0 && getAoeTimer() > 10) {
                if (getAttackType() == 1) {
                    setAttackType(0);
                } else setAttackType(1);
                attackAnimTimeout = getArmor().getItem() instanceof SteelGolemUpgradeItem upgradeItem && upgradeItem.isFastAttack() ? 10 : 20;
                golemAttackAnim.stop();
                golemAttackAnim.start(tickCount);
            } else {
                --attackAnimTimeout;
            }

            if (isAoeAttacking() && aoeAttackAnimTimeout <= 0 && getAoeTimer() <= 0) {
                setAttackType(2);
                aoeAttackAnimTimeout = 35;
                golemAttackAnim.stop();
                upswingAttackAnimationState.stop();
                upswingAttackAnimationState.start(tickCount);
            } else {
                --aoeAttackAnimTimeout;
            }
        }
        if (isChassisBroken()) {
            ++eyeLayerTick;
            if (eyeLayerBrightness < 1) eyeLayerBrightness += (0.0F - eyeLayerBrightness) * 0.02F;
        } else {
            --eyeLayerTick;
            if (eyeLayerBrightness > 0) eyeLayerBrightness -= (0.0F + eyeLayerBrightness) * 0.02F;
        }

        sitAnim.animateWhen(isOrderedToSit() && !(getBrokenTick() > 0), tickCount);
        standAnim.animateWhen(!isOrderedToSit(), tickCount);

        brokenAnim.animateWhen(isChassisBroken(), tickCount);
        repairedAnim.animateWhen(!isChassisBroken() && getBrokenTick() != 0, tickCount);

        riddenAnim.animateWhen(isVehicle(), tickCount);
        unRideAnim.animateWhen(!isVehicle(), tickCount);

        chestOpened.animateWhen(chestOpened(), tickCount);
        chestClosed.animateWhen(!chestOpened(), tickCount);
    }

    @Override
    public AnimationState actionAnim(int i) {
        return switch (i) {
            case 8 -> golemAttackAnim;
            case 9 -> upswingAttackAnimationState;
            default -> super.actionAnim(i);
        };
    }

    @Override
    public void stopAllAnimation() {
        super.stopAllAnimation();
        golemAttackAnim.stop();
    }

    @Override public int getMaxHeadXRot() { return isOrderedToSit() || isVehicle() ? 0 : super.getMaxHeadXRot(); }

//    @Override protected @NotNull BodyRotationControl createBodyControl() { return new SmoothBodyControl(this); }

    public boolean canSpawnSprintParticle() {
        return getDeltaMovement().horizontalDistanceSqr() > (double)2.5000003E-7F && random.nextInt(5) == 0;
    }

    @Override protected void playStepSound(@NotNull BlockPos pos, @NotNull BlockState block) { playSound(ESounds.STEEL_GOLEM_STEP.get(), 2.0F, 0.5F); }
    @Override public void playAmbientSound() { playSound(ESounds.STEEL_GOLEM_AMBIENT.get()); }
    @Override protected SoundEvent getHurtSound(@NotNull DamageSource source) { return isChassisCompromised() ? null : ESounds.STEEL_GOLEM_HURT.get(); }
    @Override protected SoundEvent getDeathSound() { return isChassisCompromised() ? ESounds.STEEL_GOLEM_DEATH.get() : ESounds.STEEL_GOLEM_DOWN.get(); }

    @Override public @NotNull Vec3 getLeashOffset() { return new Vec3(0, 0.95F * getEyeHeight(), getBbWidth() * 0.4F); }

    @Override public boolean thoseWhoGrow() {
        return getMossStage() < 3 && !isWaxed() && (isOrderedToSit() || isChassisBroken() || (isPlayerCreated() && !isTamed()));
    }

    public Crackiness getCrackiness() { return Crackiness.byFraction(getHealth() / getMaxHealth()); }

    public enum Crackiness {
        NONE(1.0F), LOW(0.75F), MEDIUM(0.5F), HIGH(0.25F);

        private static final List<AstaliteGolem.Crackiness> BY_DAMAGE = Stream.of(values()).sorted(Comparator.comparingDouble((crack) -> crack.fraction)).collect(ImmutableList.toImmutableList());

        private final float fraction;
        Crackiness(float f) { fraction = f; }

        public static AstaliteGolem.Crackiness byFraction(float pFraction) {
            for(AstaliteGolem.Crackiness crackiness : BY_DAMAGE)
                if (pFraction < crackiness.fraction) return crackiness;
            return NONE;
        }
    }

    public ChassisDamage getChassisDamage() { return ChassisDamage.byFraction((float) getChassisHealth() / 5 , isPlayerCreated() || isTamed()); }

    public enum ChassisDamage {
        NONE(1.0F), VERY_LOW(0.81F), LOW(0.61F), MEDIUM(0.41F), HIGH(0.21F);

        private static final List<ChassisDamage> BY_DAMAGE = Stream.of(values()).sorted(Comparator.comparingDouble((crack) -> crack.fraction)).collect(ImmutableList.toImmutableList());

        private final float fraction;
        ChassisDamage(float f) { fraction = f; }

        public static ChassisDamage byFraction(float fraction, boolean a) {
            for(ChassisDamage chassisDamage : BY_DAMAGE)
                if (a && fraction < chassisDamage.fraction) return chassisDamage;
            return NONE;
        }
    }

    // ----- [ ADAPTIVE ATTACK ]

    @Override public Entity getPriorityTarget() { return priorityTarget; }
    @Override public void setPriorityTarget(Entity target) { priorityTarget = target; }

    @Override public Entity getAnnoyingTarget() { return annoyingTarget; }
    @Override public void setAnnoyingTarget(Entity target) { annoyingTarget = target; }

    @Override public Entity getRangedTarget() { return rangedTarget; }
    @Override public void setRangedTarget(Entity target) { rangedTarget = target; }

    @Override public Entity getCurrentTarget() { return currentTarget; }
    @Override public void setCurrentTarget(Entity target) { currentTarget = target; }

    @Override
    public void attackRecord() {
        MobUtil.sendOwnerMsgs(this, Component.literal("test attack record"));
    }

    @Override
    public void hurtRecord(Entity target) {
        MobUtil.sendOwnerMsgs(this, Component.literal("test hurt record"));
    }

    @Override
    public void movement() {
        if (getCurrentTarget() != null) {
            Entity target = getCurrentTarget();

            double distToStop = Mth.square(2);

            if (distanceTo(target) > distToStop) {
                getNavigation().moveTo(target, 1);
            }
        }
    }

    @Override
    public void postAttack() {
        setAttackCooldown(60);
    }

    @Override
    public void performAttack() {
        if (!isChassisBroken()) {
//            if (isAttacking() && attackAnimTimeout <= 0 && getAoeTimer() > 10) {
//                if (getAttackType() == 1) {
//                    setAttackType(0);
//                } else setAttackType(1);
//                attackAnimTimeout = getArmor().getItem() instanceof SteelGolemUpgradeItem upgradeItem && upgradeItem.isFastAttack() ? 10 : 20;
//                sendAnimEvent(8);
//                normalJap();
//                golemAttackAnim.stop();
//                golemAttackAnim.start(tickCount);
//            } else {
//                --attackAnimTimeout;
//            }
//
//            if (isAoeAttacking() && aoeAttackAnimTimeout <= 0 && getAoeTimer() <= 0) {
//                setAttackType(2);
//                aoeAttackAnimTimeout = 35;
//                golemAttackAnim.stop();
//                upswingAttackAnimationState.stop();
//                upswingAttackAnimationState.start(tickCount);
//            } else {
//                --aoeAttackAnimTimeout;
//            }
            setAttackCooldown(Math.max(0, getAttackCooldown() -1));
            if (getAttackCooldown() <= 0) normalJap();
//            if ((float) golemAttackAnim.getAccumulatedTime() > 333F) broadcastCustomEntityEvent();
            if ((float) golemAttackAnim.getAccumulatedTime() > 333F) broadcastCustomEntityEvent();
        }
    }

//    public void normalJap() {
//        Vec3 target = getEyePosition().add(Vec3.directionFromRotation(getXRot(), yHeadRot)/*.scale(0.5)*/);
//        Vec3 source = getEyePosition();
//        Vec3 offsetToTarget = target.subtract(source);
//        Vec3 normalized = offsetToTarget.normalize();
//        Set<Entity> hitSet = new HashSet<>();
//
//        for(int particleIndex = 1; particleIndex < Mth.floor(offsetToTarget.length()) + 2; ++particleIndex) {
//            Vec3 particle = source.add(normalized.scale(particleIndex));
//            KnockbackUtil knockbackUtil = KnockbackUtil.knockbackUtil(level(), this, particle.x, particle.y, particle.z, 1);
//
//            hitSet.addAll(level().getEntitiesOfClass(LivingEntity.class, new AABB(new BlockPos((int) particle.x, (int) particle.y, (int) particle.z))
//                    .inflate(2), e -> MobUtil.can(this, e)
//            ));
//            knockbackUtil.knockback();
//        }
//        hitSet.remove(this);
//        for(Entity hitTarget : hitSet) {
//            if (hitTarget instanceof LivingEntity living) {
////                MobUtil.hurt(living, attacker.damageSources().sonicBoom(attacker), (float) UniqueItemConfig.diarkriteChargeBladeSonicDamage * chargeAmount, 0);
////                doHurtTarget(hitTarget);
//                if (doHurtTarget(hitTarget)) hurtRecord(hitTarget);
//            }
//        }
//        broadcastAnimEvent(8, false);
//        setAttackCooldown(60);
//    }
    public void normalJap() {
        Set<Entity> hitSet = aoeSet(this, getXRot(), getYRot(), 1, e -> MobUtil.isSeenAsEnemy(this, e));

        hitSet.remove(this);
        for(Entity hitTarget : hitSet) {
            if (hitTarget instanceof LivingEntity) {
                if (doHurtTarget(hitTarget)) hurtRecord(hitTarget);
            }
        }
        broadcastAnimEvent(8, false);
        setAttackCooldown(60);
    }

    @Override
    public Properties info() {
        return new Properties(this).defaultAttackStyle(AttackStyle.STANDARD).attackSpeed(0);
    }
}