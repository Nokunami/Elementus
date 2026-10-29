package net.nokunami.elementus.common.entity.living;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.nokunami.elementus.config.EConfig;
import net.nokunami.elementus.config.EntityConfig;

public class AstaliteGolemCarrier extends AstaliteGolem {
    public static EntityConfig.baseConfig config = EConfig.entityConfig.astaliteCarrier;
    public static final float rawBbWidth = 1.8F, rawBbHeight = 2.4F;

    public AstaliteGolemCarrier(EntityType<? extends AstaliteGolem> type, Level level) {
        super(type, level);
    }

    // ----- [ DATA ]

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, config.maxHealth)
                .add(Attributes.MOVEMENT_SPEED, config.movementSpeed)
                .add(Attributes.KNOCKBACK_RESISTANCE, config.knockbackResist)
                .add(Attributes.ATTACK_DAMAGE, config.attackDamage)
                .add(Attributes.ARMOR, config.armor)
                .add(Attributes.ARMOR_TOUGHNESS, config.toughness)
                .add(Attributes.FOLLOW_RANGE, config.followRange)
                ;
    }

    @Override public float getBBWidth() { return rawBbWidth; }
    @Override public float getBBHeight() { return rawBbHeight; }

    @Override public int getDefaultSitTick() { return 6; }
}
