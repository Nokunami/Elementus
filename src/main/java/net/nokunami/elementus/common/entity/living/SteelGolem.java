package net.nokunami.elementus.common.entity.living;

import com.google.common.collect.ImmutableList;
import io.redspace.ironsspellbooks.registries.ItemRegistry;
import net.minecraft.core.BlockPos;
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
import net.minecraft.util.RandomSource;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
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
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.IForgeShearable;
import net.minecraftforge.entity.PartEntity;
import net.nokunami.elementus.Elementus;
import net.nokunami.elementus.common.Etags;
import net.nokunami.elementus.common.config.EntityConfig;
import net.nokunami.elementus.common.entity.ai.steelGolem.GolemFollowOwnerGoal;
import net.nokunami.elementus.common.entity.ai.steelGolem.SteelGolemAttackGoal;
import net.nokunami.elementus.common.entity.ai.steelGolem.SteelGolemOwnerHurtByTargetGoal;
import net.nokunami.elementus.common.entity.ai.steelGolem.SteelGolemOwnerHurtTargetGoal;
import net.nokunami.elementus.common.registry.ModEntityType;
import net.nokunami.elementus.common.registry.ModItems;
import net.nokunami.elementus.common.registry.ModSoundEvents;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static net.nokunami.elementus.Elementus.modLoc;
import static net.nokunami.elementus.ModChecker.ironsSpellbooks;

@SuppressWarnings("deprecation")
public class SteelGolem extends TamableGolem implements NeutralMob, Shearable, IForgeShearable {
    private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
    private int remainingPersistentAngerTime;
    @javax.annotation.Nullable
    private UUID persistentAngerTarget;
    private final GolemPart[] subEntities;
    public final GolemPart saddlePart;
    protected static final EntityDataAccessor<Byte> DATA_FLAGS_ID = SynchedEntityData.defineId(SteelGolem.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Boolean> DATA_ATTACKING_ID = SynchedEntityData.defineId(SteelGolem.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> DATA_CHASSIS_STATUS_ID = SynchedEntityData.defineId(SteelGolem.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> DATA_AGGRO_ID = SynchedEntityData.defineId(SteelGolem.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> DATA_CHASSIS_ID = SynchedEntityData.defineId(SteelGolem.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_ATTACK_TYPE = SynchedEntityData.defineId(SteelGolem.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_MOSS_TIMER = SynchedEntityData.defineId(SteelGolem.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_MOSS_STAGE = SynchedEntityData.defineId(SteelGolem.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<ItemStack> DATA_LEAVES_ID = SynchedEntityData.defineId(SteelGolem.class, EntityDataSerializers.ITEM_STACK);
    private static final EntityDataAccessor<ItemStack> DATA_SADDLE_ID = SynchedEntityData.defineId(SteelGolem.class, EntityDataSerializers.ITEM_STACK);
    private static final EntityDataAccessor<ItemStack> DATA_CARPET_ID = SynchedEntityData.defineId(SteelGolem.class, EntityDataSerializers.ITEM_STACK);
    public final AnimationState attackStartAnimState = new AnimationState();
    public final AnimationState leftAttackEndAnimState = new AnimationState();
    public final AnimationState rightAttackEndAnimState = new AnimationState();
    public final AnimationState leftAttackAnimationState = new AnimationState();
    public final AnimationState rightAttackAnimationState = new AnimationState();
    public final AnimationState upswingAttackAnimationState = new AnimationState();
    public final AnimationState upswingAttackEndAnimationState = new AnimationState();
    public final AnimationState sitAnimState = new AnimationState();
    public final AnimationState standFromSitAnimState = new AnimationState();
    private static final EntityDimensions SITTING_DIMENSIONS = EntityDimensions.scalable(ModEntityType.STEEL_GOLEM.get().getWidth(), ModEntityType.STEEL_GOLEM.get().getHeight() - 0.5F);
    public int attackAnimationTimeout = 0;

    public SteelGolem(EntityType<? extends TamableGolem> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.saddlePart = new GolemPart(this, "saddle_hitbox", 1.125F, 1F);
        this.subEntities = new GolemPart[] {this.saddlePart};
        this.setId(ENTITY_COUNTER.getAndAdd(this.subEntities.length + 1) + 1);
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
            if (this.getChassisState()) {
                this.stopBeingAngry();
                this.navigation.stop();
            }
        }

        float yPos = this.isInSittingPose() ? 1.5F : 2;
        this.tickPart(this.saddlePart, 0, yPos ,0);

        Vec3[] avec3 = new Vec3[this.subEntities.length];

        for(int j = 0; j < this.subEntities.length; ++j) {
            avec3[j] = new Vec3(this.subEntities[j].getX(), this.subEntities[j].getY(), this.subEntities[j].getZ());
        }

        for(int l = 0; l < this.subEntities.length; ++l) {
            this.subEntities[l].xo = avec3[l].x;
            this.subEntities[l].yo = avec3[l].y;
            this.subEntities[l].zo = avec3[l].z;
            this.subEntities[l].xOld = avec3[l].x;
            this.subEntities[l].yOld = avec3[l].y;
            this.subEntities[l].zOld = avec3[l].z;
        }

        if (this.isInSittingPose()) {
            if (this.getMossStage() < 3) this.setMossTimer(getMossTimer() + 1);
            this.setPose(Pose.CROUCHING);
        } else {
            this.setPose(Pose.STANDING);
            this.setMossTimer(0);
        }

        if (this.getMossTimer() > 144000) {
            this.setMossStage(this.getMossStage() + 1);
            this.setMossTimer(0);
        }
    }

    private void setupAnim() {
        if(this.isAttacking() && attackAnimationTimeout <= 0) {
            this.setAttackAnimType(Mth.randomBetweenInclusive(RandomSource.create(), 0, 2));
            if (this.getAttackAnimType() == 2) {
                this.setAttackAnimType(Mth.randomBetweenInclusive(RandomSource.create(), 0, 3));
            }
            attackAnimationTimeout = 20; // Length in ticks of your animation
            if (this.getAttackAnimType() == 1) {
                leftAttackAnimationState.start(this.tickCount);
            } else if (this.getAttackAnimType() == 3) {
                upswingAttackAnimationState.start(this.tickCount);
            } else rightAttackAnimationState.start(this.tickCount);
        } else {
            this.setAttackAnimType(this.getAttackAnimType());
            --this.attackAnimationTimeout;
        }
        this.navigation.isInProgress();
        this.navigation.isDone();
        sitAnimState.animateWhen(this.isInSittingPose(), this.tickCount);
        standFromSitAnimState.animateWhen(!this.isInSittingPose(), this.tickCount);
    }

    private void stopAttackAnimationCycle() {
        this.upswingAttackAnimationState.stop();
        this.rightAttackAnimationState.stop();
        this.leftAttackAnimationState.stop();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new SteelGolemAttackGoal(this, 1.2D, true));
        this.goalSelector.addGoal(1, new GolemSitGoal(this));
        this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 0.9D, 32.0F));
        this.goalSelector.addGoal(2, new GolemFollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
        this.goalSelector.addGoal(7, new GolemLookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new GolemStroll(this, 0.5D));
        this.goalSelector.addGoal(8, new GolemRamdomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new SteelGolemOwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(1, new SteelGolemOwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(2, (new HurtByTargetGoal(this).setAlertOthers()));
        this.targetSelector.addGoal(3, new GolemNearestAttackableGoal<>(this, Player.class, 10, true, false, this::isAngryAt));
        this.targetSelector.addGoal(3, new GolemNearestAttackableGoal<>(this, Mob.class, 5, false, false,
                (entity) -> entity instanceof Enemy && !(entity instanceof Creeper)));
        this.targetSelector.addGoal(4, new ResetUniversalAngerTargetGoal<>(this, false));
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_FLAGS_ID, (byte)0);
        this.entityData.define(DATA_ATTACKING_ID, false);
        this.entityData.define(DATA_CHASSIS_ID, 5);
        this.getEntityData().define(DATA_LEAVES_ID, ItemStack.EMPTY);
        this.getEntityData().define(DATA_SADDLE_ID, ItemStack.EMPTY);
        this.getEntityData().define(DATA_CARPET_ID, ItemStack.EMPTY);
        this.entityData.define(DATA_CHASSIS_STATUS_ID, false);
        this.entityData.define(DATA_AGGRO_ID, true);
        this.entityData.define(DATA_ATTACK_TYPE, 0);
        this.entityData.define(DATA_MOSS_TIMER, 0);
        this.entityData.define(DATA_MOSS_STAGE, 0);
    }

    //Unused for now
    @Override
    public void onSyncedDataUpdated(@NotNull EntityDataAccessor<?> pKey) {
        super.onSyncedDataUpdated(pKey);
    }

    //Loading from Save file
    public void readAdditionalSaveData(@NotNull CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setPlayerCreated(tag.getBoolean("PlayerCreated"));
        this.readPersistentAngerSaveData(this.level(), tag);
        CompoundTag leavesDecoration = tag.getCompound("LeavesDecoration");
        if (!leavesDecoration.isEmpty()) {
            ItemStack leaves = ItemStack.of(leavesDecoration);
            if (leaves.isEmpty()) Elementus.LOGGER.warn("Unable to load LeavesDeco from: {}", leavesDecoration);
            if (leaves.is(Etags.Items.STEEL_GOLEM_LEAVES_DECORATION)) this.setLeavesDeco(leaves);
        }
        CompoundTag saddleEquiped = tag.getCompound("Saddle");
        if (!saddleEquiped.isEmpty()) {
            ItemStack saddle = ItemStack.of(saddleEquiped);
            if (saddle.isEmpty()) Elementus.LOGGER.warn("Unable to load Saddle from: {}", saddleEquiped);
            if (saddle.is(Items.SADDLE)) this.setSaddle(saddle);
        }
        CompoundTag carpetDeco = tag.getCompound("CarpetDecoration");
        if (!carpetDeco.isEmpty()) {
            ItemStack carpet = ItemStack.of(carpetDeco);
            if (carpet.isEmpty()) Elementus.LOGGER.warn("Unable to load CarpetDeco from: {}", carpetDeco);
            if (carpet.is(Etags.Items.STEEL_GOLEM_CARPET_DECORATION)) this.setCarpet(carpet);
        }
        this.setChassisHealth(tag.getInt("ChassisValue"));
        this.setChassisState(tag.getBoolean("ChassisState"));
        this.setAggroState(tag.getBoolean("AggroState"));
        this.setAttackAnimType(tag.getInt("AttackType"));
        this.setMossTimer(tag.getInt("MossTimer"));
        this.setMossStage(tag.getInt("MossStage"));
    }

    //Saving to file
    public void addAdditionalSaveData(@NotNull CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putBoolean("PlayerCreated", this.isPlayerCreated());
        this.addPersistentAngerSaveData(tag);
        if (!this.getLeaves().isEmpty()) {
            tag.put("LeavesDecoration", this.getLeaves().save(new CompoundTag()));
        }
        if (!this.getSaddle().isEmpty()) {
            tag.put("Saddle", this.getSaddle().save(new CompoundTag()));
        }
        if (!this.getCarpet().isEmpty()) {
            tag.put("CarpetDecoration", this.getCarpet().save(new CompoundTag()));
        }
        tag.putInt("ChassisValue", this.getChassisHealth());
        tag.putBoolean("ChassisState", this.getChassisState());
        tag.putBoolean("AggroState", this.getAggroState());
        tag.putInt("AttackType", this.getAttackAnimType());
        tag.putInt("MossTimer", this.getMossTimer());
        tag.putInt("MossStage", this.getMossStage());
    }

    @Override
    public void handleEntityEvent(byte pId) {
        if (pId == 4) {
            stopAttackAnimationCycle();
            if (this.getAttackAnimType() == 1) {
                this.leftAttackEndAnimState.start(this.tickCount);
            } else if (this.getAttackAnimType() == 3) {
                this.upswingAttackEndAnimationState.start(this.tickCount);
            } else this.rightAttackEndAnimState.start(this.tickCount);
            this.attackAnimationTimeout = 10;
            this.playSound(SoundEvents.IRON_GOLEM_ATTACK, 1.0F, 0.5F);
        } else {
            super.handleEntityEvent(pId);
        }

    }

    public int getMaxHeadXRot() {
        if (this.isInSittingPose() || this.isSaddled()) {
            return 0;
        } else return super.getMaxHeadXRot();
    }

    @Override
    public int getMaxHeadYRot() {
        return this.isSaddleable() ? 0 : super.getMaxHeadYRot();
    }

    //MultiPart Stuff
    @Override
    public void setId(int id) {
        super.setId(id);
        for (int i = 0; i < this.subEntities.length; i++)
            this.subEntities[i].setId(id + i + 1);
    }

    private void tickPart(GolemPart pPart, double pOffsetX, double pOffsetY, double pOffsetZ) {
        pPart.setPos(this.getX() + pOffsetX, this.getY() + pOffsetY, this.getZ() + pOffsetZ);
    }

    @Override
    public boolean isMultipartEntity() {
        return true;
    }

    @Override
    public @Nullable PartEntity<?>[] getParts() {
        return this.subEntities;
    }

    @Override
    public @NotNull EntityDimensions getDimensions(@NotNull Pose pPose) {
        return pPose == Pose.CROUCHING ? SITTING_DIMENSIONS.scale(this.getScale()) : super.getDimensions(pPose);
    }

    @Override
    public boolean isNoAi() {
        return this.getChassisState() && super.isNoAi();
    }

    //Entity Data Stuff
    public void setAttacking(boolean attacking) {
        this.entityData.set(DATA_ATTACKING_ID, attacking);
    }

    public boolean isAttacking() {
        return this.entityData.get(DATA_ATTACKING_ID);
    }

    public int getChassisHealth() {
        return this.entityData.get(DATA_CHASSIS_ID);
    }

    public void setChassisHealth(int health) {
        this.entityData.set(DATA_CHASSIS_ID, health);
    }

    public final int getMaxChassisHealth() {
        return 5;
    }

    public void setChassisState(boolean state) {
        this.entityData.set(DATA_CHASSIS_STATUS_ID, state);
    }

    public boolean getChassisState() {
        return this.entityData.get(DATA_CHASSIS_STATUS_ID);
    }

    public boolean isChassisCompromised() {
        if (this.getHealth() <= 0) {
            if (this.isPlayerCreated()) {
                if (this.getChassisHealth() > 1) {
                    this.setChassisState(true);
                    this.setChassisHealth(this.getChassisHealth() - 1);
                    this.setHealth(1);
                    return false;
                } else return true;
            } else return true;
        } else return false;
    }

    public ItemStack getLeaves() {
        return this.getEntityData().get(DATA_LEAVES_ID);
    }

    public void setLeavesDeco(ItemStack itemStack) {
        if (!itemStack.isEmpty()) itemStack = itemStack.copyWithCount(1); this.playSound(SoundEvents.AZALEA_LEAVES_PLACE, 1, 1);
        this.getEntityData().set(DATA_LEAVES_ID, itemStack);
    }

    private void dropLeaves(ItemStack itemStack, Player player) {
        this.playSound(SoundEvents.AZALEA_BREAK);
        if (!player.getAbilities().instabuild) this.spawnAtLocation(itemStack);
    }

    public String getLeavesName() {
        return this.getLeaves().getItem().toString();
    }

    public void setAggroState(boolean state) {
        this.entityData.set(DATA_AGGRO_ID, state);
    }

    public boolean getAggroState() {
        return this.entityData.get(DATA_AGGRO_ID);
    }

    public void setSaddle(ItemStack itemStack) {
        if (!itemStack.isEmpty()) {
            itemStack = itemStack.copyWithCount(1);
            this.playSound(SoundEvents.HORSE_SADDLE);
        }
        this.getEntityData().set(DATA_SADDLE_ID, itemStack);
    }

    public void dropSaddle(ItemStack itemStack, Player player) {
        this.playSound(SoundEvents.SHEEP_SHEAR);
        if (!player.getAbilities().instabuild) this.spawnAtLocation(itemStack);
    }

    public ItemStack getSaddle() {
        return this.getEntityData().get(DATA_SADDLE_ID);
    }

    public void setAttackAnimType(int type) {
        this.entityData.set(DATA_ATTACK_TYPE, type);
    }

    public int getAttackAnimType() {
        return this.entityData.get(DATA_ATTACK_TYPE);
    }

    public void setCarpet(ItemStack itemStack) {
        if (!itemStack.isEmpty()) {
            itemStack = itemStack.copyWithCount(1);
            this.playSound(SoundEvents.WOOL_PLACE);
        }
        this.getEntityData().set(DATA_CARPET_ID, itemStack);
    }

    private void dropCarpet(ItemStack itemStack, Player player) {
        this.playSound(SoundEvents.WOOL_BREAK);
        if (!player.getAbilities().instabuild) this.spawnAtLocation(itemStack);
    }

    public ItemStack getCarpet() {
        return this.getEntityData().get(DATA_CARPET_ID);
    }

    public String getCarpetName() {
        return this.getCarpet().getItem().toString();
    }

    public int getMossTimer() {
        return this.entityData.get(DATA_MOSS_TIMER);
    }

    public void setMossTimer(int ticks) {
        this.entityData.set(DATA_MOSS_TIMER, ticks);
    }

    public int getMossStage() {
        return this.entityData.get(DATA_MOSS_STAGE);
    }

    public void setMossStage(int stage) {
        this.entityData.set(DATA_MOSS_STAGE, stage);
    }

    @Override
    public boolean isInvulnerableTo(DamageSource pSource) {
        if (pSource.is(Etags.DamageTypes.STEEL_GOLEM_IMMUNE) && !pSource.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
            return true;
        } else if (getChassisState() && this.getChassisHealth() != 0) {
            return true;
        }
        return super.isInvulnerableTo(pSource);
    }

    @Override
    public boolean isInSittingPose() {
        if (getChassisState()) {
            return true;
        }
        return super.isInSittingPose();
    }

    protected int decreaseAirSupply(int pAir) {
        return pAir;
    }

    protected void doPush(@NotNull Entity entity) {
        if (entity instanceof Enemy && !(entity instanceof Creeper) && this.getRandom().nextInt(20) == 0) {
            this.setTarget((LivingEntity)entity);
        }
        super.doPush(entity);
    }

    @Override
    public boolean isPushable() {
        return !this.isInSittingPose() && super.isPushable();
    }

    //Some Ridable Relate Stuff
    @Override
    protected float getRiddenSpeed(@NotNull Player pPlayer) {
        float f = pPlayer.isSprinting() /*&& this.getJumpCooldown() == 0*/ ? 0.1F : 0.0F;
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

    public void setRemainingPersistentAngerTime(int pTime) {
        this.remainingPersistentAngerTime = pTime;
    }

    public int getRemainingPersistentAngerTime() {
        return this.remainingPersistentAngerTime;
    }

    public void setPersistentAngerTarget(@javax.annotation.Nullable UUID pTarget) {
        this.persistentAngerTarget = pTarget;
    }

    @javax.annotation.Nullable
    public UUID getPersistentAngerTarget() {
        return this.persistentAngerTarget;
    }

    @Override
    public boolean isAngry() {
        return NeutralMob.super.isAngry();
    }

    //Entity Damage / Hurt Events
    public boolean doHurtTarget(Entity pEntity) {
        this.level().broadcastEntityEvent(this, (byte)4);
        float f = this.getAttackDamage();
        float f1 = (int)f > 0 ? f / 2.0F + (float)this.random.nextInt((int)f) : f;
        boolean flag = pEntity.hurt(this.damageSources().mobAttack(this), f1);
        if (flag) {
            double d2;
            if (pEntity instanceof LivingEntity livingentity) {
                d2 = livingentity.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE);
            } else {
                d2 = 0.0D;
            }

            double d0 = d2;
            double d1 = Math.max(0.0D, 2.0D - d0);
            pEntity.setDeltaMovement(pEntity.getDeltaMovement().multiply(d1, 1.0D, d1));
            this.doEnchantDamageEffects(this, pEntity);
        }

        this.playSound(SoundEvents.IRON_GOLEM_ATTACK, 1.0F, 1.0F);
        return flag;
    }

    private float getAttackDamage() {
        return (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
    }

    @Override
    public boolean hurt(@NotNull DamageSource pSource, float pAmount) {
        SteelGolem.Crackiness steelgolem$crackiness = this.getCrackiness();
        boolean flag = super.hurt(pSource, pAmount);
        if (pSource.getEntity() == this.getControllingPassenger()) {
            return false;
        }
        if (flag && this.getCrackiness() != steelgolem$crackiness) {
            this.playSound(SoundEvents.IRON_GOLEM_DAMAGE, 1.0F, 1.0F);
        }

        return flag;
    }

    @Override
    public void die(@NotNull DamageSource pCause) {
        if (!this.isChassisCompromised()) {
            this.dead = false;
            if (!this.level().isClientSide && this.level().getGameRules().getBoolean(GameRules.RULE_SHOWDEATHMESSAGES) && this.getOwner() instanceof ServerPlayer) {
                this.getOwner().sendSystemMessage(Component.translatable("entity.elementus.steel_golem_down", this.getChassisHealth()));
            }
        } else super.die(pCause);
        this.stopBeingAngry();
        this.ejectPassengers();
    }

    @Override
    public boolean isAffectedByPotions() {
        return !this.getChassisState() && super.isAffectedByPotions();
    }

    @Override
    public boolean attackable() {
        return !this.getChassisState() && super.attackable();
    }

    @Override
    public boolean canBeSeenAsEnemy() {
        return !this.getChassisState() && super.canBeSeenAsEnemy();
    }

    public SteelGolem.Crackiness getCrackiness() {
        return SteelGolem.Crackiness.byFraction(this.getHealth() / this.getMaxHealth());
    }

    public ChassisCrackiness getChassisCrackiness() {
        if (this.isPlayerCreated()) {
            return ChassisCrackiness.byFraction((float) this.getChassisHealth() / this.getMaxChassisHealth());
        } else return ChassisCrackiness.NONE;
    }

    //Interactions
    public @NotNull InteractionResult mobInteract(Player player, @NotNull InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        boolean shearsItem = itemStack.is(Items.SHEARS);
        boolean leaves = itemStack.is(Etags.Items.STEEL_GOLEM_LEAVES_DECORATION);
        boolean carpet = itemStack.is(Etags.Items.STEEL_GOLEM_CARPET_DECORATION);
        boolean instaBuild = player.getAbilities().instabuild;
        boolean flag = player.isSecondaryUseActive();
        InteractionResult interactionresult = super.mobInteract(player, hand);
        boolean shouldHeal = this.getHealth() < this.getMaxHealth();
        boolean chassisCondition = this.getChassisHealth() < 5;
        boolean aggroItem = itemStack.is(ItemTags.SWORDS) || itemStack.is(ItemTags.AXES);
        boolean healItem = itemStack.is(Etags.Items.STEEL_GOLEM_REPAIR);
        boolean healChassisItem = itemStack.is(ModItems.ElementusItems.STEEL_BLOCK.get());
        boolean repairItem = itemStack.is(ModItems.ElementusItems.STEEL_SCRAP.get()) || itemStack.is(ModItems.ElementusItems.STEEL_BLOCK.get());
        if (this.isTame()) {
            if (!this.getChassisState()) {
                if (shouldHeal) {
                    if (chassisCondition && healChassisItem) {
                        healGolem(player, hand);
                        return InteractionResult.SUCCESS;
                    } else if (healItem){
                        healGolem(player, hand);
                        return InteractionResult.SUCCESS;
                    }
                }
                if (flag && aggroItem) {
                    this.navigation.stop();
                    this.setAggroState(!this.getAggroState());
                    this.setTarget(null);
                    return InteractionResult.SUCCESS;
                }
            } else if (repairItem) {
                healGolem(player, hand);
                return InteractionResult.SUCCESS;
            }
            if (!this.isSaddled() && itemStack.is(Items.SADDLE)) {
                this.setSaddle(itemStack);
                if (!instaBuild) itemStack.shrink(1);
                this.getNavigation().stop();
                return InteractionResult.SUCCESS;
            }
            if (this.getMossStage() > 0 && this.readyForShearing()) {
                this.shear(SoundSource.PLAYERS);
                this.gameEvent(GameEvent.SHEAR, player);
                itemStack.hurtAndBreak(1, player, (item) -> item.broadcastBreakEvent(hand));
                return InteractionResult.SUCCESS;
            }
            if (this.getLeaves().isEmpty() && leaves) {
                this.setLeavesDeco(itemStack);
                if (!instaBuild) itemStack.shrink(1);
                this.navigation.stop();
                return InteractionResult.SUCCESS;
            } else if (!this.getLeaves().isEmpty() && shearsItem) {
                this.dropLeaves(this.getLeaves(), player);
                this.setLeavesDeco(ItemStack.EMPTY);
                this.navigation.stop();
                return InteractionResult.SUCCESS;
            }
            if (this.getCarpet().isEmpty() && carpet) {
                this.setCarpet(itemStack);
                if (!instaBuild) itemStack.shrink(1);
                this.navigation.stop();
                return InteractionResult.SUCCESS;
            } else if (!this.getCarpet().isEmpty() && shearsItem) {
                this.dropCarpet(this.getCarpet(), player);
                this.setCarpet(ItemStack.EMPTY);
                this.navigation.stop();
                return InteractionResult.SUCCESS;
            }

            if (!interactionresult.consumesAction() && this.isOwnedBy(player) && !this.getChassisState()) {
                if ((ironsSpellbooks && itemStack.is(ItemRegistry.SCROLL.get()))) {
                    return InteractionResult.PASS;
                } else {
                    sitOrder();
                    return InteractionResult.SUCCESS;
                }
            } else {
                return InteractionResult.PASS;
            }
        } else if (this.isPlayerCreated() || (!this.isPlayerCreated() && player.isCreative())) {
            if (!this.isPlayerCreated()) this.setPlayerCreated(true);
            this.tame(player);
            this.navigation.stop();
            this.setTarget(null);
            return InteractionResult.SUCCESS;
        } else
        return super.mobInteract(player, hand);
    }

    public void sitOrder() {
        this.setOrderedToSit(!this.isOrderedToSit());
        this.setPose(Pose.CROUCHING);
        this.jumping = false;
        this.navigation.stop();
        this.setTarget(null);
    }

    protected void healGolem( Player player, @NotNull InteractionHand hand) {
        int chassis = 0;
        float healAmount = 1;
        ItemStack itemstack = player.getItemInHand(hand);
        boolean instaBuild = player.getAbilities().instabuild;
        float randomFloat = 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F;
        boolean chassisState = this.getChassisState();
        boolean scrapItem = itemstack.is(ModItems.ElementusItems.STEEL_SCRAP.get());
        boolean chassisItem = itemstack.is(ModItems.ElementusItems.STEEL_BLOCK.get());
        boolean chassisCondition = this.getChassisHealth() < 5;

        if (chassisState) {
            if (scrapItem) healAmount = 0.5F;
            if (chassisItem) chassis = 1;
        } else {
            if (chassisItem && chassisCondition) chassis = 2;
        }

        this.setChassisState(false);
        this.heal((int) (EntityConfig.MaxHealth / EntityConfig.RepairAmount) * healAmount);
        if (chassis > 0) this.setChassisHealth(this.getChassisHealth() + 1);
        if (!instaBuild) itemstack.shrink(1);
        this.playSound(SoundEvents.IRON_GOLEM_REPAIR, 1.0F, randomFloat);
        if (chassisState) this.playSound(SoundEvents.ANVIL_PLACE, 1, randomFloat);
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
        return this.isVehicle();
    }

    @Override
    public boolean canAttack(@NotNull LivingEntity pTarget) {
        return !this.getChassisState() && super.canAttack(pTarget);
    }

    //Entity Sounds
    protected SoundEvent getHurtSound(@NotNull DamageSource pDamageSource) {
        return this.isChassisCompromised() ? null : SoundEvents.IRON_GOLEM_HURT;
    }

    protected SoundEvent getDeathSound() {
        return isChassisCompromised() ? SoundEvents.IRON_GOLEM_DEATH : ModSoundEvents.STEEL_GOLEM_DOWN.get();
    }

    protected void playStepSound(@NotNull BlockPos pPos, @NotNull BlockState pBlock) {
        this.playSound(SoundEvents.IRON_GOLEM_STEP, 2.0F, 0.5F);
    }

    @Override
    protected void dropCustomDeathLoot(@NotNull DamageSource pSource, int pLooting, boolean pRecentlyHit) {
        super.dropCustomDeathLoot(pSource, pLooting, pRecentlyHit);
        ItemStack leaves = this.getLeaves();
        ItemStack saddle = this.getSaddle();
        ItemStack carpet = this.getCarpet();
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

    public @NotNull Vec3 getLeashOffset() {
        return new Vec3(0.0D, 0.95F * this.getEyeHeight(), this.getBbWidth() * 0.4F);
    }

    @Override
    public boolean isSaddleable() {
        return true;
    }

    @Override
    public void equipSaddle(@Nullable SoundSource pSource) {
        this.playSound(SoundEvents.HORSE_SADDLE);
    }

    @Override
    public boolean isSaddled() {
        return !this.getSaddle().isEmpty();
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
            return mob != null && !mob.isInSittingPose() && super.canUse();
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
            return mob != null && !mob.getChassisState() && super.canUse();
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
            return mob != null && !mob.isInSittingPose() && super.canUse();
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
            return mob != null && !mob.getChassisState() && mob.getAggroState() && super.canUse();
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
            return mob != null && !mob.isInSittingPose() && super.canUse();
        }
    }

    /// No
    @Override
    public @Nullable AgeableMob getBreedOffspring(@NotNull ServerLevel pLevel, @NotNull AgeableMob pOtherParent) {
        return null;
    }
}
