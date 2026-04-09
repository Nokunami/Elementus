package net.nokunami.elementus.common.entity.living;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.SitWhenOrderedToGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.nokunami.elementus.common.config.EntityConfig;
import net.nokunami.elementus.common.entity.ai.goal.steelGolem.GolemFollowOwnerGoal;
import net.nokunami.elementus.common.entity.ai.goal.steelGolem.GolemOwnerHurtByGoal;
import net.nokunami.elementus.common.entity.ai.goal.steelGolem.GolemOwnerHurtGoal;
import net.nokunami.elementus.common.entity.ai.goal.steelGolem.SteelGolemNearestAttackableGoal;
import net.nokunami.elementus.common.tags.EEntityTags;
import org.jetbrains.annotations.NotNull;

public class DiarkriteGolem extends TamableGolem {
    protected static final EntityDataAccessor<Integer> RESONANCE_AMOUNT = SynchedEntityData.defineId(DiarkriteGolem.class, EntityDataSerializers.INT);
    protected static final EntityDataAccessor<Integer> ANGER = SynchedEntityData.defineId(DiarkriteGolem.class, EntityDataSerializers.INT);
    protected static final EntityDataAccessor<Integer> INTEGRITY = SynchedEntityData.defineId(DiarkriteGolem.class, EntityDataSerializers.INT);
    public boolean isPushable;

    public DiarkriteGolem(EntityType<? extends TamableGolem> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        createInventory();
    }

    ////////////////////////////////////// DATA //////////////////////////////////////

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, EntityConfig.diarkriteGolem_MaxHealth)
                .add(Attributes.MOVEMENT_SPEED, EntityConfig.diarkriteGolem_MovementSpeed)
                .add(Attributes.KNOCKBACK_RESISTANCE, EntityConfig.diarkriteGolem_KnockbackResist)
                .add(Attributes.ATTACK_DAMAGE, EntityConfig.diarkriteGolem_AttackDamage)
                .add(Attributes.ARMOR, EntityConfig.diarkriteGolem_Armor)
                .add(Attributes.ARMOR_TOUGHNESS, EntityConfig.diarkriteGolem_Toughness)
                .add(Attributes.FOLLOW_RANGE, EntityConfig.diarkriteGolem_FollowRange)
                ;
    }

    @Override
    protected void registerGoals() {
//        goalSelector.addGoal(0, new SitWhenOrderedToGoal(this));
        goalSelector.addGoal(0, new AvoidEntityGoal<>(this, LivingEntity.class, 6, 1, 1.2, e -> e.getType().is(EEntityTags.DIARKRITE_GOLEM_AVOID)));
        goalSelector.addGoal(1, new GolemFollowOwnerGoal(this, new GolemFollowOwnerGoal.goalInfo()
                .speed(1, 1.25).start(8, 20).stop(6, 9).teleport(12, 28)));

        targetSelector.addGoal(0, new SteelGolemNearestAttackableGoal<>(this, Mob.class, 5, false, false,
                (entity) -> entity.getType().is(EEntityTags.DIARKRITE_GOLEM_PRIORITY_TARGETS)));
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
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(RESONANCE_AMOUNT, 0);
        entityData.define(ANGER, 0);
        entityData.define(INTEGRITY, 0);
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("Resonance", getResonanceAmount());
        tag.putInt("Anger", getAnger());
        tag.putInt("Integrity", getIntegrity());
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        setResonanceAmount(tag.getInt("Resonance"));
        setAnger(tag.getInt("Anger"));
        setIntegrity(tag.getInt("Integrity"));
    }

    public int getResonanceAmount() { return entityData.get(RESONANCE_AMOUNT); }
    public void setResonanceAmount(int amount) { entityData.set(RESONANCE_AMOUNT, amount); }

    public int getAnger() { return entityData.get(ANGER); }
    public void setAnger(int amount) { entityData.set(ANGER, amount); }

    public int getIntegrity() { return entityData.get(INTEGRITY); }
    public void setIntegrity(int amount) { entityData.set(INTEGRITY, amount); }

    ////////////////////////////////////// INVENTORY //////////////////////////////////////

    @Override
    protected int getInventorySize() { return 24; }

    ////////////////////////////////////// ENTITY LOGIC //////////////////////////////////////

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void setDeltaMovement(@NotNull Vec3 vec3) {
        if (isPushable()) super.setDeltaMovement(vec3);
    }

    @Override
    public void setDeltaMovement(double pX, double pY, double pZ) {
        if (isPushable()) super.setDeltaMovement(pX, pY, pZ);
    }

    @Override
    public void push(@NotNull Entity entity) {
        if (isPushable()) super.push(entity);
    }

    @Override
    public boolean isPushable() {
        return isPushable;
    }

    ////////////////////////////////////// RIDEABLE //////////////////////////////////////

    ////////////////////////////////////// INTERACTION //////////////////////////////////////

    ////////////////////////////////////// CLIENT DATA //////////////////////////////////////
}