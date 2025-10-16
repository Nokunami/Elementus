package net.nokunami.elementus.common.entity.living;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.nokunami.elementus.common.config.EntityConfig;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class AnthektiteGolem extends TamableGolem {

    public AnthektiteGolem(EntityType<? extends TamableGolem> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
    }

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
    public void addAdditionalSaveData(@NotNull CompoundTag tag) {
        super.addAdditionalSaveData(tag);
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag tag) {
        super.readAdditionalSaveData(tag);
    }
}
