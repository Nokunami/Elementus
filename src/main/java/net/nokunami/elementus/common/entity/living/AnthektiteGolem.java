package net.nokunami.elementus.common.entity.living;

import net.minecraft.nbt.CompoundTag;
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
import net.nokunami.elementus.common.Etags;
import net.nokunami.elementus.common.config.EntityConfig;
import net.nokunami.elementus.common.entity.ai.goal.steelGolem.GolemFollowOwnerGoal;
import net.nokunami.elementus.common.entity.ai.goal.steelGolem.GolemOwnerHurtByGoal;
import net.nokunami.elementus.common.entity.ai.goal.steelGolem.GolemOwnerHurtGoal;
import net.nokunami.elementus.common.entity.ai.goal.steelGolem.SteelGolemNearestAttackableGoal;
import org.jetbrains.annotations.NotNull;

public class AnthektiteGolem extends TamableGolem {

    public AnthektiteGolem(EntityType<? extends TamableGolem> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    ////////////////////////////////////// DATA //////////////////////////////////////

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, EntityConfig.anthektiteGolem_MaxHealth)
                .add(Attributes.MOVEMENT_SPEED, EntityConfig.anthektiteGolem_MovementSpeed)
                .add(Attributes.KNOCKBACK_RESISTANCE, EntityConfig.anthektiteGolem_KnockbackResist)
                .add(Attributes.ATTACK_DAMAGE, EntityConfig.anthektiteGolem_AttackDamage)
                .add(Attributes.ARMOR, EntityConfig.anthektiteGolem_Armor)
                .add(Attributes.ARMOR_TOUGHNESS, EntityConfig.anthektiteGolem_Toughness)
                .add(Attributes.FOLLOW_RANGE, EntityConfig.anthektiteGolem_FollowRange)
                ;
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(0, new SitWhenOrderedToGoal(this));
        goalSelector.addGoal(0, new AvoidEntityGoal<>(this, LivingEntity.class, 6, 1, 1.2, e -> e.getType().is(Etags.Entity.ANTHEKTITE_GOLEM_AVOID)));
        goalSelector.addGoal(1, new GolemFollowOwnerGoal(this, new GolemFollowOwnerGoal.goalInfo()
                .speed(1, 1.25).start(8, 20).stop(6, 9).teleport(12, 28)));

        targetSelector.addGoal(0, new SteelGolemNearestAttackableGoal<>(this, Mob.class, 5, false, false,
                (entity) -> entity.getType().is(Etags.Entity.ANTHEKTITE_GOLEM_PRIORITY_TARGETS)));
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
    public void addAdditionalSaveData(@NotNull CompoundTag tag) {
        super.addAdditionalSaveData(tag);
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag tag) {
        super.readAdditionalSaveData(tag);
    }

    ////////////////////////////////////// INVENTORY //////////////////////////////////////

    ////////////////////////////////////// ENTITY LOGIC //////////////////////////////////////

    ////////////////////////////////////// RIDEABLE //////////////////////////////////////

    ////////////////////////////////////// INTERACTION //////////////////////////////////////

    ////////////////////////////////////// CLIENT DATA //////////////////////////////////////
}
