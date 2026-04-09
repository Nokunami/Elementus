package net.nokunami.elementus.common.entity.living;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class AstaliteGolemCarrier extends AstaliteGolem {
    public static final float rawBbWidth = 1.8F, rawBbHeight = 2.4F;

    public AstaliteGolemCarrier(EntityType<? extends AstaliteGolem> type, Level level) {
        super(type, level);
    }

    // ----- [ DATA ]

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 150)
                .add(Attributes.MOVEMENT_SPEED, 0.21)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1)
                .add(Attributes.ATTACK_DAMAGE, 8)
                .add(Attributes.ARMOR, 15)
                .add(Attributes.ARMOR_TOUGHNESS, 4)
                .add(Attributes.FOLLOW_RANGE, 32)
                ;
    }

    @Override public float getBBWidth() { return rawBbWidth; }
    @Override public float getBBHeight() { return rawBbHeight; }

    @Override public int getDefaultSitTick() { return 6; }
}
