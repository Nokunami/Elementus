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

public class DiarkriteGolem extends TamableGolem implements NeutralMob {

    public DiarkriteGolem(EntityType<? extends TamableGolem> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
    }

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
    public void addAdditionalSaveData(@NotNull CompoundTag tag) {
        super.addAdditionalSaveData(tag);
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag tag) {
        super.readAdditionalSaveData(tag);
    }
}