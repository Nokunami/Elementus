package net.nokunami.elementus.common.catalystCore;

import com.google.common.collect.ImmutableMultimap;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.nokunami.elementus.config.EConfig;
import net.nokunami.elementus.common.item.AttributeUtil;

import static net.nokunami.elementus.common.item.AttributeUtil.armorUUID;

public class CatalystArmorAttributes {

    public static ImmutableMultimap.Builder<Attribute, AttributeModifier> baseAttributes(int armor, double toughness, double knockbackResist, double attackSpeed, double moveSpeed) {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ARMOR, new AttributeModifier(armorUUID, "Armor modifier", armor, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(armorUUID, "Armor toughness", toughness, AttributeModifier.Operation.ADDITION));
        AttributeUtil.toggleableAttribute(builder, Attributes.KNOCKBACK_RESISTANCE, "Armor knockback resistance", knockbackResist, AttributeModifier.Operation.ADDITION);
        AttributeUtil.toggleableAttribute(builder, Attributes.ATTACK_SPEED, "Armor attack speed", attackSpeed, AttributeModifier.Operation.MULTIPLY_BASE);
        AttributeUtil.toggleableAttribute(builder, Attributes.MOVEMENT_SPEED, "Armor movement speed", moveSpeed, AttributeModifier.Operation.MULTIPLY_BASE);
        return builder;
    }

    public static ImmutableMultimap.Builder<Attribute, AttributeModifier> baseAttributes() {
        var config = EConfig.catalystConfig.baseCatalyst;
        return baseAttributes(config.armor, config.toughness, config.knockbackRes, config.attackSpeed, config.movementSpeed);
    }

    public static ImmutableMultimap.Builder<Attribute, AttributeModifier> beaconPower() {
        var config = EConfig.catalystConfig.beaconPower.armor;
        return baseAttributes(config.armor, config.toughness, config.knockbackRes, config.attackSpeed, config.movementSpeed);
    }

    public static ImmutableMultimap.Builder<Attribute, AttributeModifier> totemOfUndying() {
        var config = EConfig.catalystConfig.totemOfUndying.armor;
        return baseAttributes(config.armor, config.toughness, config.knockbackRes, config.attackSpeed, config.movementSpeed);
    }

    public static ImmutableMultimap.Builder<Attribute, AttributeModifier> conduitPower() {
        var config = EConfig.catalystConfig.heartOfTheSea.armor;
        return baseAttributes(config.armor, config.toughness, config.knockbackRes, config.attackSpeed, config.movementSpeed);
    }

    public static ImmutableMultimap.Builder<Attribute, AttributeModifier> voidShifter() {
        var config = EConfig.catalystConfig.voidShifter.armor;
        return baseAttributes(config.armor, config.toughness, config.knockbackRes, config.attackSpeed, config.movementSpeed);
    }

    public static ImmutableMultimap.Builder<Attribute, AttributeModifier> wayfinder() {
        var config = EConfig.catalystConfig.wayfinder.armor;
        return baseAttributes(config.armor, config.toughness, config.knockbackRes, config.attackSpeed, config.movementSpeed);
    }
}