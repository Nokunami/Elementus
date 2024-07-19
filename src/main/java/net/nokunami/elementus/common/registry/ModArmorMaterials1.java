package net.nokunami.elementus.common.registry;

import com.google.common.base.Suppliers;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.minecraft.Util;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.nokunami.elementus.common.compat.advancednetherite.ANModItems;
import net.nokunami.elementus.common.config.ArmorConfig;
import net.nokunami.elementus.common.config.ArmorConfig;
import net.nokunami.elementus.common.config.ArmorConfig;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

public enum ModArmorMaterials1 implements ArmorMaterial {

    STEEL("steel", ArmorConfig.steelArmor_DurabilityForType, Util.make(new EnumMap<>(ArmorItem.Type.class), (c) -> {
        c.put(ArmorItem.Type.BOOTS, ArmorConfig.steelArmor_Boots);c.put(ArmorItem.Type.LEGGINGS, ArmorConfig.steelArmor_Leggings);c.put(ArmorItem.Type.CHESTPLATE, ArmorConfig.steelArmor_Chestplate);c.put(ArmorItem.Type.HELMET, ArmorConfig.steelArmor_Helmet);
    }), ArmorConfig.steelArmor_Enchantability, SoundEvents.ARMOR_EQUIP_IRON,
            ArmorConfig.steelArmor_Toughness, ArmorConfig.steelArmor_KnockbackResistance, () -> Ingredient.of(ModItems.STEEL_INGOT.get()), Map.of(
            Attributes.ATTACK_SPEED, new AttributeModifier("Armor Attack Speed Modifier", ArmorConfig.steelArmor_AttackSpeed, AttributeModifier.Operation.MULTIPLY_BASE),
            Attributes.MOVEMENT_SPEED, new AttributeModifier("Armor Movement Speed Modifier", ArmorConfig.steelArmor_MovementSpeed, AttributeModifier.Operation.MULTIPLY_BASE))),

    DIARKRITE("diarkrite",  ArmorConfig.diarkriteArmor_DurabilityForType, Util.make(new EnumMap<>(ArmorItem.Type.class), (c) -> {
        c.put(ArmorItem.Type.BOOTS, ArmorConfig.diarkriteArmor_Boots);c.put(ArmorItem.Type.LEGGINGS, ArmorConfig.diarkriteArmor_Leggings);c.put(ArmorItem.Type.CHESTPLATE, ArmorConfig.diarkriteArmor_Chestplate);c.put(ArmorItem.Type.HELMET, ArmorConfig.diarkriteArmor_Helmet);
    }), ArmorConfig.diarkriteArmor_Enchantability, SoundEvents.ARMOR_EQUIP_NETHERITE,
            ArmorConfig.diarkriteArmor_Toughness, ArmorConfig.diarkriteArmor_KnockbackResistance, () -> Ingredient.of(ModItems.DIARKRITE_INGOT.get()), Map.of(
            Attributes.ATTACK_SPEED, new AttributeModifier("Armor Attack Speed Modifier", ArmorConfig.diarkriteArmor_AttackSpeed, AttributeModifier.Operation.MULTIPLY_BASE),
            Attributes.MOVEMENT_SPEED, new AttributeModifier("Armor Movement Speed Modifier", ArmorConfig.diarkriteArmor_MovementSpeed, AttributeModifier.Operation.MULTIPLY_BASE))),

    ANTHEKTITE("anthektite", ArmorConfig.anthektiteArmor_DurabilityForType, Util.make(new EnumMap<>(ArmorItem.Type.class), (c) -> {
        c.put(ArmorItem.Type.BOOTS, ArmorConfig.anthektiteArmor_Boots);c.put(ArmorItem.Type.LEGGINGS, ArmorConfig.anthektiteArmor_Leggings);c.put(ArmorItem.Type.CHESTPLATE, ArmorConfig.anthektiteArmor_Chestplate);c.put(ArmorItem.Type.HELMET, ArmorConfig.anthektiteArmor_Helmet);
    }), ArmorConfig.anthektiteArmor_Enchantability, SoundEvents.ARMOR_EQUIP_NETHERITE,
            ArmorConfig.anthektiteArmor_Toughness, ArmorConfig.anthektiteArmor_KnockbackResistance, () -> Ingredient.of(ModItems.ANTHEKTITE_INGOT.get()), Map.of(
            Attributes.ATTACK_SPEED, new AttributeModifier("Armor Attack Speed Modifier", ArmorConfig.anthektiteArmor_AttackSpeed, AttributeModifier.Operation.MULTIPLY_BASE),
            Attributes.MOVEMENT_SPEED, new AttributeModifier("Armor Movement Speed Modifier", ArmorConfig.anthektiteArmor_MovementSpeed, AttributeModifier.Operation.MULTIPLY_BASE))),


    DIARKRITE_IRON("diarkrite_iron", ArmorConfig.diarkriteIronArmor_DurabilityForType, Util.make(new EnumMap<>(ArmorItem.Type.class), (c) -> {
        c.put(ArmorItem.Type.BOOTS, ArmorConfig.anthektiteIronArmor_Boots);c.put(ArmorItem.Type.LEGGINGS, ArmorConfig.anthektiteIronArmor_Leggings);c.put(ArmorItem.Type.CHESTPLATE, ArmorConfig.anthektiteIronArmor_Chestplate);c.put(ArmorItem.Type.HELMET, ArmorConfig.anthektiteIronArmor_Helmet);
    }), ArmorConfig.diarkriteIronArmor_Enchantability, SoundEvents.ARMOR_EQUIP_NETHERITE,
            ArmorConfig.diarkriteIronArmor_Toughness, ArmorConfig.diarkriteIronArmor_KnockbackResistance, () -> Ingredient.of(ANModItems.DIARKRITE_IRON.get()), Map.of(
            Attributes.ATTACK_SPEED, new AttributeModifier("Armor Attack Speed Modifier", ArmorConfig.diarkriteIronArmor_AttackSpeed, AttributeModifier.Operation.MULTIPLY_BASE),
            Attributes.MOVEMENT_SPEED, new AttributeModifier("Armor Movement Speed Modifier", ArmorConfig.diarkriteIronArmor_MovementSpeed, AttributeModifier.Operation.MULTIPLY_BASE))),
    DIARKRITE_GOLD("diarkrite_gold", ArmorConfig.diarkriteGoldArmor_DurabilityForType, Util.make(new EnumMap<>(ArmorItem.Type.class), (c) -> {
        c.put(ArmorItem.Type.BOOTS, ArmorConfig.anthektiteGoldArmor_Boots);c.put(ArmorItem.Type.LEGGINGS, ArmorConfig.anthektiteGoldArmor_Leggings);c.put(ArmorItem.Type.CHESTPLATE, ArmorConfig.anthektiteGoldArmor_Chestplate);c.put(ArmorItem.Type.HELMET, ArmorConfig.anthektiteGoldArmor_Helmet);
    }), ArmorConfig.diarkriteGoldArmor_Enchantability, SoundEvents.ARMOR_EQUIP_NETHERITE,
            ArmorConfig.diarkriteGoldArmor_Toughness, ArmorConfig.diarkriteGoldArmor_KnockbackResistance, () -> Ingredient.of(ANModItems.DIARKRITE_GOLD.get()), Map.of(
            Attributes.ATTACK_SPEED, new AttributeModifier("Armor Attack Speed Modifier", ArmorConfig.diarkriteGoldArmor_AttackSpeed, AttributeModifier.Operation.MULTIPLY_BASE),
            Attributes.MOVEMENT_SPEED, new AttributeModifier("Armor Movement Speed Modifier", ArmorConfig.diarkriteGoldArmor_MovementSpeed, AttributeModifier.Operation.MULTIPLY_BASE))),
    DIARKRITE_EMERALD("diarkrite_emerald", ArmorConfig.diarkriteEmeraldArmor_DurabilityForType, Util.make(new EnumMap<>(ArmorItem.Type.class), (c) -> {
        c.put(ArmorItem.Type.BOOTS, ArmorConfig.anthektiteEmeraldArmor_Boots);c.put(ArmorItem.Type.LEGGINGS, ArmorConfig.anthektiteEmeraldArmor_Leggings);c.put(ArmorItem.Type.CHESTPLATE, ArmorConfig.anthektiteEmeraldArmor_Chestplate);c.put(ArmorItem.Type.HELMET, ArmorConfig.anthektiteEmeraldArmor_Helmet);
    }), ArmorConfig.diarkriteEmeraldArmor_Enchantability, SoundEvents.ARMOR_EQUIP_NETHERITE,
            ArmorConfig.diarkriteEmeraldArmor_Toughness, ArmorConfig.diarkriteEmeraldArmor_KnockbackResistance, () -> Ingredient.of(ANModItems.DIARKRITE_EMERALD.get()), Map.of(
            Attributes.ATTACK_SPEED, new AttributeModifier("Armor Attack Speed Modifier", ArmorConfig.diarkriteEmeraldArmor_AttackSpeed, AttributeModifier.Operation.MULTIPLY_BASE),
            Attributes.MOVEMENT_SPEED, new AttributeModifier("Armor Movement Speed Modifier", ArmorConfig.diarkriteEmeraldArmor_MovementSpeed, AttributeModifier.Operation.MULTIPLY_BASE))),
    DIARKRITE_DIAMOND("diarkrite_diamond", ArmorConfig.diarkriteDiamondArmor_DurabilityForType, Util.make(new EnumMap<>(ArmorItem.Type.class), (c) -> {
        c.put(ArmorItem.Type.BOOTS, ArmorConfig.anthektiteDiamondArmor_Boots);c.put(ArmorItem.Type.LEGGINGS, ArmorConfig.anthektiteDiamondArmor_Leggings);c.put(ArmorItem.Type.CHESTPLATE, ArmorConfig.anthektiteDiamondArmor_Chestplate);c.put(ArmorItem.Type.HELMET, ArmorConfig.anthektiteDiamondArmor_Helmet);
    }), ArmorConfig.diarkriteDiamondArmor_Enchantability, SoundEvents.ARMOR_EQUIP_NETHERITE,
            ArmorConfig.diarkriteDiamondArmor_Toughness, ArmorConfig.diarkriteDiamondArmor_KnockbackResistance, () -> Ingredient.of(ANModItems.DIARKRITE_DIAMOND.get()), Map.of(
            Attributes.ATTACK_SPEED, new AttributeModifier("Armor Attack Speed Modifier", ArmorConfig.diarkriteDiamondArmor_AttackSpeed, AttributeModifier.Operation.MULTIPLY_BASE),
            Attributes.MOVEMENT_SPEED, new AttributeModifier("Armor Movement Speed Modifier", ArmorConfig.diarkriteDiamondArmor_MovementSpeed, AttributeModifier.Operation.MULTIPLY_BASE))),

    ANTHEKTITE_IRON("anthektite_iron", ArmorConfig.anthektiteIronArmor_DurabilityForType, Util.make(new EnumMap<>(ArmorItem.Type.class), (c) -> {
        c.put(ArmorItem.Type.BOOTS, ArmorConfig.anthektiteIronArmor_Boots);c.put(ArmorItem.Type.LEGGINGS, ArmorConfig.anthektiteIronArmor_Leggings);c.put(ArmorItem.Type.CHESTPLATE, ArmorConfig.anthektiteIronArmor_Chestplate);c.put(ArmorItem.Type.HELMET, ArmorConfig.anthektiteIronArmor_Helmet);
    }), ArmorConfig.anthektiteIronArmor_Enchantability, SoundEvents.ARMOR_EQUIP_NETHERITE,
            ArmorConfig.anthektiteIronArmor_Toughness, ArmorConfig.anthektiteIronArmor_KnockbackResistance, () -> Ingredient.of(ANModItems.ANTHEKTITE_IRON.get()), Map.of(
            Attributes.ATTACK_SPEED, new AttributeModifier("Armor Attack Speed Modifier", ArmorConfig.anthektiteIronArmor_AttackSpeed, AttributeModifier.Operation.MULTIPLY_BASE),
            Attributes.MOVEMENT_SPEED, new AttributeModifier("Armor Movement Speed Modifier", ArmorConfig.anthektiteIronArmor_MovementSpeed, AttributeModifier.Operation.MULTIPLY_BASE))),
    ANTHEKTITE_GOLD("anthektite_gold", ArmorConfig.anthektiteGoldArmor_DurabilityForType, Util.make(new EnumMap<>(ArmorItem.Type.class), (c) -> {
        c.put(ArmorItem.Type.BOOTS, ArmorConfig.anthektiteGoldArmor_Boots);c.put(ArmorItem.Type.LEGGINGS, ArmorConfig.anthektiteGoldArmor_Leggings);c.put(ArmorItem.Type.CHESTPLATE, ArmorConfig.anthektiteGoldArmor_Chestplate);c.put(ArmorItem.Type.HELMET, ArmorConfig.anthektiteGoldArmor_Helmet);
    }), ArmorConfig.anthektiteGoldArmor_Enchantability, SoundEvents.ARMOR_EQUIP_NETHERITE,
            ArmorConfig.anthektiteGoldArmor_Toughness, ArmorConfig.anthektiteGoldArmor_KnockbackResistance, () -> Ingredient.of(ANModItems.ANTHEKTITE_GOLD.get()), Map.of(
            Attributes.ATTACK_SPEED, new AttributeModifier("Armor Attack Speed Modifier", ArmorConfig.anthektiteGoldArmor_AttackSpeed, AttributeModifier.Operation.MULTIPLY_BASE),
            Attributes.MOVEMENT_SPEED, new AttributeModifier("Armor Movement Speed Modifier", ArmorConfig.anthektiteGoldArmor_MovementSpeed, AttributeModifier.Operation.MULTIPLY_BASE))),
    ANTHEKTITE_EMERALD("anthektite_emerald", ArmorConfig.anthektiteEmeraldArmor_DurabilityForType, Util.make(new EnumMap<>(ArmorItem.Type.class), (c) -> {
        c.put(ArmorItem.Type.BOOTS, ArmorConfig.anthektiteEmeraldArmor_Boots);c.put(ArmorItem.Type.LEGGINGS, ArmorConfig.anthektiteEmeraldArmor_Leggings);c.put(ArmorItem.Type.CHESTPLATE, ArmorConfig.anthektiteEmeraldArmor_Chestplate);c.put(ArmorItem.Type.HELMET, ArmorConfig.anthektiteEmeraldArmor_Helmet);
    }), ArmorConfig.anthektiteEmeraldArmor_Enchantability, SoundEvents.ARMOR_EQUIP_NETHERITE,
            ArmorConfig.anthektiteEmeraldArmor_Toughness, ArmorConfig.anthektiteEmeraldArmor_KnockbackResistance, () -> Ingredient.of(ANModItems.ANTHEKTITE_EMERALD.get()), Map.of(
            Attributes.ATTACK_SPEED, new AttributeModifier("Armor Attack Speed Modifier", ArmorConfig.anthektiteEmeraldArmor_AttackSpeed, AttributeModifier.Operation.MULTIPLY_BASE),
            Attributes.MOVEMENT_SPEED, new AttributeModifier("Armor Movement Speed Modifier", ArmorConfig.anthektiteEmeraldArmor_MovementSpeed, AttributeModifier.Operation.MULTIPLY_BASE))),
    ANTHEKTITE_DIAMOND("anthektite_diamond", ArmorConfig.anthektiteDiamondArmor_DurabilityForType, Util.make(new EnumMap<>(ArmorItem.Type.class), (c) -> {
        c.put(ArmorItem.Type.BOOTS, ArmorConfig.anthektiteDiamondArmor_Boots);c.put(ArmorItem.Type.LEGGINGS, ArmorConfig.anthektiteDiamondArmor_Leggings);c.put(ArmorItem.Type.CHESTPLATE, ArmorConfig.anthektiteDiamondArmor_Chestplate);c.put(ArmorItem.Type.HELMET, ArmorConfig.anthektiteDiamondArmor_Helmet);
    }), ArmorConfig.anthektiteDiamondArmor_Enchantability, SoundEvents.ARMOR_EQUIP_NETHERITE,
            ArmorConfig.anthektiteDiamondArmor_Toughness, ArmorConfig.anthektiteDiamondArmor_KnockbackResistance, () -> Ingredient.of(ANModItems.ANTHEKTITE_DIAMOND.get()), Map.of(
            Attributes.ATTACK_SPEED, new AttributeModifier("Armor Attack Speed Modifier", ArmorConfig.anthektiteDiamondArmor_AttackSpeed, AttributeModifier.Operation.MULTIPLY_BASE),
            Attributes.MOVEMENT_SPEED, new AttributeModifier("Armor Movement Speed Modifier", ArmorConfig.anthektiteDiamondArmor_MovementSpeed, AttributeModifier.Operation.MULTIPLY_BASE))),


    STEEL_SAMURAI("steel_samurai", ArmorConfig.steelSamuraiArmor_DurabilityForType, Util.make(new EnumMap<>(ArmorItem.Type.class), (c) -> {
        c.put(ArmorItem.Type.BOOTS, ArmorConfig.steelSamuraiArmor_Boots);c.put(ArmorItem.Type.LEGGINGS, ArmorConfig.steelSamuraiArmor_Leggings);c.put(ArmorItem.Type.CHESTPLATE, ArmorConfig.steelSamuraiArmor_Chestplate);c.put(ArmorItem.Type.HELMET, ArmorConfig.steelSamuraiArmor_Helmet);
    }), ArmorConfig.steelSamuraiArmor_Enchantability, SoundEvents.ARMOR_EQUIP_IRON,
            ArmorConfig.steelSamuraiArmor_Toughness, ArmorConfig.steelSamuraiArmor_KnockbackResistance, () -> Ingredient.of(ModItems.STEEL_INGOT.get()), Map.of(
            Attributes.ATTACK_SPEED, new AttributeModifier("Armor Attack Speed Modifier", ArmorConfig.steelSamuraiArmor_AttackSpeed, AttributeModifier.Operation.MULTIPLY_BASE),
            Attributes.MOVEMENT_SPEED, new AttributeModifier("Armor Movement Speed Modifier", ArmorConfig.steelSamuraiArmor_MovementSpeed, AttributeModifier.Operation.MULTIPLY_BASE))),

    STEEL_SAMURAI_LIGHT("steel_samurai_light", ArmorConfig.steelSamuraiLightArmor_DurabilityForType, Util.make(new EnumMap<>(ArmorItem.Type.class), (c) -> {
        c.put(ArmorItem.Type.BOOTS, ArmorConfig.steelSamuraiLightArmor_Boots);c.put(ArmorItem.Type.LEGGINGS, ArmorConfig.steelSamuraiLightArmor_Leggings);c.put(ArmorItem.Type.CHESTPLATE, ArmorConfig.steelSamuraiLightArmor_Chestplate);c.put(ArmorItem.Type.HELMET, ArmorConfig.steelSamuraiLightArmor_Helmet);
    }), ArmorConfig.steelSamuraiLightArmor_Enchantability, SoundEvents.ARMOR_EQUIP_IRON,
            ArmorConfig.steelSamuraiLightArmor_Toughness, ArmorConfig.steelSamuraiLightArmor_KnockbackResistance, () -> Ingredient.of(ModItems.STEEL_INGOT.get()), Map.of(
            Attributes.ATTACK_SPEED, new AttributeModifier("Armor Attack Speed Modifier", ArmorConfig.steelSamuraiLightArmor_AttackSpeed, AttributeModifier.Operation.MULTIPLY_BASE),
            Attributes.MOVEMENT_SPEED, new AttributeModifier("Armor Movement Speed Modifier", ArmorConfig.steelSamuraiLightArmor_MovementSpeed, AttributeModifier.Operation.MULTIPLY_BASE))),

    STEEL_SAMURAI_MASTER("steel_samurai_master", ArmorConfig.steelSamuraiMasterArmor_DurabilityForType, Util.make(new EnumMap<>(ArmorItem.Type.class), (c) -> {
        c.put(ArmorItem.Type.BOOTS, ArmorConfig.steelSamuraiMasterArmor_Boots);c.put(ArmorItem.Type.LEGGINGS, ArmorConfig.steelSamuraiMasterArmor_Leggings);c.put(ArmorItem.Type.CHESTPLATE, ArmorConfig.steelSamuraiMasterArmor_Chestplate);c.put(ArmorItem.Type.HELMET, ArmorConfig.steelSamuraiMasterArmor_Helmet);
    }), ArmorConfig.steelSamuraiMasterArmor_Enchantability, SoundEvents.ARMOR_EQUIP_IRON,
            ArmorConfig.steelSamuraiMasterArmor_Toughness, ArmorConfig.steelSamuraiMasterArmor_KnockbackResistance, () -> Ingredient.of(ModItems.STEEL_INGOT.get()), Map.of(
            Attributes.ATTACK_SPEED, new AttributeModifier("Armor Attack Speed Modifier", ArmorConfig.steelSamuraiMasterArmor_AttackSpeed, AttributeModifier.Operation.MULTIPLY_BASE),
            Attributes.MOVEMENT_SPEED, new AttributeModifier("Armor Movement Speed Modifier", ArmorConfig.steelSamuraiMasterArmor_MovementSpeed, AttributeModifier.Operation.MULTIPLY_BASE))),


    DIARKRITE_SAMURAI("diarkrite_samurai", ArmorConfig.diarkriteSamuraiArmor_DurabilityForType, Util.make(new EnumMap<>(ArmorItem.Type.class), (c) -> {
        c.put(ArmorItem.Type.BOOTS, ArmorConfig.diarkriteSamuraiArmor_Boots);c.put(ArmorItem.Type.LEGGINGS, ArmorConfig.diarkriteSamuraiArmor_Leggings);c.put(ArmorItem.Type.CHESTPLATE, ArmorConfig.diarkriteSamuraiArmor_Chestplate);c.put(ArmorItem.Type.HELMET, ArmorConfig.diarkriteSamuraiArmor_Helmet);
    }), ArmorConfig.diarkriteSamuraiArmor_Enchantability, SoundEvents.ARMOR_EQUIP_NETHERITE,
            ArmorConfig.diarkriteSamuraiArmor_Toughness, ArmorConfig.diarkriteSamuraiArmor_KnockbackResistance, () -> Ingredient.of(ModItems.DIARKRITE_INGOT.get()), Map.of(
            Attributes.ATTACK_SPEED, new AttributeModifier("Armor Attack Speed Modifier", ArmorConfig.diarkriteSamuraiArmor_AttackSpeed, AttributeModifier.Operation.MULTIPLY_BASE),
            Attributes.MOVEMENT_SPEED, new AttributeModifier("Armor Movement Speed Modifier", ArmorConfig.diarkriteSamuraiArmor_MovementSpeed, AttributeModifier.Operation.MULTIPLY_BASE))),

    DIARKRITE_SAMURAI_LIGHT("diarkrite_samurai_light", ArmorConfig.diarkriteSamuraiLightArmor_DurabilityForType, Util.make(new EnumMap<>(ArmorItem.Type.class), (c) -> {
        c.put(ArmorItem.Type.BOOTS, ArmorConfig.diarkriteSamuraiLightArmor_Boots);c.put(ArmorItem.Type.LEGGINGS, ArmorConfig.diarkriteSamuraiLightArmor_Leggings);c.put(ArmorItem.Type.CHESTPLATE, ArmorConfig.diarkriteSamuraiLightArmor_Chestplate);c.put(ArmorItem.Type.HELMET, ArmorConfig.diarkriteSamuraiLightArmor_Helmet);
    }), ArmorConfig.diarkriteSamuraiLightArmor_Enchantability, SoundEvents.ARMOR_EQUIP_NETHERITE,
            ArmorConfig.diarkriteSamuraiLightArmor_Toughness, ArmorConfig.diarkriteSamuraiLightArmor_KnockbackResistance, () -> Ingredient.of(ModItems.DIARKRITE_INGOT.get()), Map.of(
            Attributes.ATTACK_SPEED, new AttributeModifier("Armor Attack Speed Modifier", ArmorConfig.diarkriteSamuraiLightArmor_AttackSpeed, AttributeModifier.Operation.MULTIPLY_BASE),
            Attributes.MOVEMENT_SPEED, new AttributeModifier("Armor Movement Speed Modifier", ArmorConfig.diarkriteSamuraiLightArmor_MovementSpeed, AttributeModifier.Operation.MULTIPLY_BASE))),

    DIARKRITE_SAMURAI_MASTER("diarkrite_samurai_master", ArmorConfig.diarkriteSamuraiMasterArmor_DurabilityForType, Util.make(new EnumMap<>(ArmorItem.Type.class), (c) -> {
        c.put(ArmorItem.Type.BOOTS, ArmorConfig.diarkriteSamuraiMasterArmor_Boots);c.put(ArmorItem.Type.LEGGINGS, ArmorConfig.diarkriteSamuraiMasterArmor_Leggings);c.put(ArmorItem.Type.CHESTPLATE, ArmorConfig.diarkriteSamuraiMasterArmor_Chestplate);c.put(ArmorItem.Type.HELMET, ArmorConfig.diarkriteSamuraiMasterArmor_Helmet);
    }), ArmorConfig.diarkriteSamuraiMasterArmor_Enchantability, SoundEvents.ARMOR_EQUIP_NETHERITE,
            ArmorConfig.diarkriteSamuraiMasterArmor_Toughness, ArmorConfig.diarkriteSamuraiMasterArmor_KnockbackResistance, () -> Ingredient.of(ModItems.DIARKRITE_INGOT.get()), Map.of(
            Attributes.ATTACK_SPEED, new AttributeModifier("Armor Attack Speed Modifier", ArmorConfig.diarkriteSamuraiMasterArmor_AttackSpeed, AttributeModifier.Operation.MULTIPLY_BASE),
            Attributes.MOVEMENT_SPEED, new AttributeModifier("Armor Movement Speed Modifier", ArmorConfig.diarkriteSamuraiMasterArmor_MovementSpeed, AttributeModifier.Operation.MULTIPLY_BASE))),


    ANTHEKTITE_SAMURAI("anthektite_samurai", ArmorConfig.anthektiteSamuraiArmor_DurabilityForType, Util.make(new EnumMap<>(ArmorItem.Type.class), (c) -> {
        c.put(ArmorItem.Type.BOOTS, ArmorConfig.anthektiteSamuraiArmor_Boots);c.put(ArmorItem.Type.LEGGINGS, ArmorConfig.anthektiteSamuraiArmor_Leggings);c.put(ArmorItem.Type.CHESTPLATE, ArmorConfig.anthektiteSamuraiArmor_Chestplate);c.put(ArmorItem.Type.HELMET, ArmorConfig.anthektiteSamuraiArmor_Helmet);
    }), ArmorConfig.anthektiteSamuraiArmor_Enchantability, SoundEvents.ARMOR_EQUIP_NETHERITE,
            ArmorConfig.anthektiteSamuraiArmor_Toughness, ArmorConfig.anthektiteSamuraiArmor_KnockbackResistance, () -> Ingredient.of(ModItems.ANTHEKTITE_INGOT.get()), Map.of(
            Attributes.ATTACK_SPEED, new AttributeModifier("Armor Attack Speed Modifier", ArmorConfig.anthektiteSamuraiArmor_AttackSpeed, AttributeModifier.Operation.MULTIPLY_BASE),
            Attributes.MOVEMENT_SPEED, new AttributeModifier("Armor Movement Speed Modifier", ArmorConfig.anthektiteSamuraiArmor_MovementSpeed, AttributeModifier.Operation.MULTIPLY_BASE))),

    ANTHEKTITE_SAMURAI_LIGHT("anthektite_samurai_light", ArmorConfig.anthektiteSamuraiLightArmor_DurabilityForType, Util.make(new EnumMap<>(ArmorItem.Type.class), (c) -> {
        c.put(ArmorItem.Type.BOOTS, ArmorConfig.anthektiteSamuraiLightArmor_Boots);c.put(ArmorItem.Type.LEGGINGS, ArmorConfig.anthektiteSamuraiLightArmor_Leggings);c.put(ArmorItem.Type.CHESTPLATE, ArmorConfig.anthektiteSamuraiLightArmor_Chestplate);c.put(ArmorItem.Type.HELMET, ArmorConfig.anthektiteSamuraiLightArmor_Helmet);
    }), ArmorConfig.anthektiteSamuraiLightArmor_Enchantability, SoundEvents.ARMOR_EQUIP_NETHERITE,
            ArmorConfig.anthektiteSamuraiLightArmor_Toughness, ArmorConfig.anthektiteSamuraiLightArmor_KnockbackResistance, () -> Ingredient.of(ModItems.ANTHEKTITE_INGOT.get()), Map.of(
            Attributes.ATTACK_SPEED, new AttributeModifier("Armor Attack Speed Modifier", ArmorConfig.anthektiteSamuraiLightArmor_AttackSpeed, AttributeModifier.Operation.MULTIPLY_BASE),
            Attributes.MOVEMENT_SPEED, new AttributeModifier("Armor Movement Speed Modifier", ArmorConfig.anthektiteSamuraiLightArmor_MovementSpeed, AttributeModifier.Operation.MULTIPLY_BASE))),

    ANTHEKTITE_SAMURAI_MASTER("anthektite_samurai_master", ArmorConfig.anthektiteSamuraiMasterArmor_DurabilityForType, Util.make(new EnumMap<>(ArmorItem.Type.class), (c) -> {
        c.put(ArmorItem.Type.BOOTS, ArmorConfig.anthektiteSamuraiMasterArmor_Boots);c.put(ArmorItem.Type.LEGGINGS, ArmorConfig.anthektiteSamuraiMasterArmor_Leggings);c.put(ArmorItem.Type.CHESTPLATE, ArmorConfig.anthektiteSamuraiMasterArmor_Chestplate);c.put(ArmorItem.Type.HELMET, ArmorConfig.anthektiteSamuraiMasterArmor_Helmet);
    }), ArmorConfig.anthektiteSamuraiMasterArmor_Enchantability, SoundEvents.ARMOR_EQUIP_NETHERITE,
            ArmorConfig.anthektiteSamuraiMasterArmor_Toughness, ArmorConfig.anthektiteSamuraiMasterArmor_KnockbackResistance, () -> Ingredient.of(ModItems.ANTHEKTITE_INGOT.get()), Map.of(
            Attributes.ATTACK_SPEED, new AttributeModifier("Armor Attack Speed Modifier", ArmorConfig.anthektiteSamuraiMasterArmor_AttackSpeed, AttributeModifier.Operation.MULTIPLY_BASE),
            Attributes.MOVEMENT_SPEED, new AttributeModifier("Armor Movement Speed Modifier", ArmorConfig.anthektiteSamuraiMasterArmor_MovementSpeed, AttributeModifier.Operation.MULTIPLY_BASE)));


    private final String name;
    private final int durabilityMultiplier;
    private final EnumMap<ArmorItem.Type, Integer> armor;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
//    private final float attackSpeed;
//    private final float movementSpeed;
    private final Supplier<Ingredient> repairIngredient;
    private final Map<Attribute, AttributeModifier> additionalAttributes;

    ModArmorMaterials1(String name, int durabilityMultiplier, EnumMap<ArmorItem.Type, Integer> armor, int enchantmentValue, SoundEvent soundEvent, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient, Map<Attribute, AttributeModifier> additionalAttributes) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.armor = armor;
        this.enchantmentValue = enchantmentValue;
        this.sound = soundEvent;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
//        this.attackSpeed = attackSpeed;
//        this.movementSpeed = movementSpeed;
        this.repairIngredient = Suppliers.memoize(repairIngredient::get);
//        slotToAttributeMap = null;
        this.additionalAttributes = additionalAttributes;
    }

//    private EnumMap<EquipmentSlot, Multimap<Attribute, AttributeModifier>> slotToAttributeMap;

//    public EnumMap<EquipmentSlot, Multimap<Attribute, AttributeModifier>> getSlotToAttributeMap() {
//        if (slotToAttributeMap == null) {
//            slotToAttributeMap = makeSlotToAttributeMap();
//        }
//        return slotToAttributeMap;
//    }

//    private EnumMap<EquipmentSlot, Multimap<Attribute, AttributeModifier>> makeSlotToAttributeMap() {
//        return Util.make(new EnumMap<>(EquipmentSlot.class), (map) -> {
//            map.put(EquipmentSlot.FEET, makeAttributeMap(EquipmentSlot.FEET));
//            map.put(EquipmentSlot.LEGS, makeAttributeMap(EquipmentSlot.LEGS));
//            map.put(EquipmentSlot.CHEST, makeAttributeMap(EquipmentSlot.CHEST));
//            map.put(EquipmentSlot.HEAD, makeAttributeMap(EquipmentSlot.HEAD));
//        });
//    }

//    private static final UUID[] ARMOR_MODIFIER_UUID_PER_SLOT = new UUID[]{UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"), UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"), UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"), UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150")};

//    public void reload() {
//        this.slotToAttributeMap = null;
//    }

//    private Multimap<Attribute, AttributeModifier> makeAttributeMap(EquipmentSlot slot) {
//        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
//        UUID uuid = ARMOR_MODIFIER_UUID_PER_SLOT[slot.getIndex()];
//        double defense = this.config.getDefenseFor(slot);
//        double toughness = this.config.toughness().get();
//        double knockbackResistance = this.config.knockbackResistance().get();
//        float speedBoost = this.config.speedBoost().get();
//        float atkSpeedBoost = this.config.atkSpeedBoost().get();
//        if (defense != 0) {
//            builder.put(Attributes.ARMOR, new AttributeModifier(uuid, "Armor modifier", defense, AttributeModifier.Operation.ADDITION));
//        }
//        if (toughness != 0) {
//            builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(uuid, "Armor toughness", toughness, AttributeModifier.Operation.ADDITION));
//        }
//        if (knockbackResistance != 0) {
//            builder.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(uuid, "Armor  knockback resistance", knockbackResistance, AttributeModifier.Operation.ADDITION));
//        }
//        if (speedBoost != 0) {
//            builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(uuid, "Armor Movement Speed", speedBoost, AttributeModifier.Operation.MULTIPLY_BASE));
//        }
//        if (atkSpeedBoost != 0) {
//            builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(uuid, "Armor Attack Speed Bonus", atkSpeedBoost, AttributeModifier.Operation.MULTIPLY_BASE));
//        }
//        return builder.build();
//    }

    private static final EnumMap<ArmorItem.Type, Integer> HEALTH_FUNCTION_FOR_TYPE = Util.make(new EnumMap<>(ArmorItem.Type.class), (p_266653_) -> {
        p_266653_.put(ArmorItem.Type.BOOTS, 13);
        p_266653_.put(ArmorItem.Type.LEGGINGS, 15);
        p_266653_.put(ArmorItem.Type.CHESTPLATE, 16);
        p_266653_.put(ArmorItem.Type.HELMET, 11);
    });

    public int getDurabilityForType(ArmorItem.Type typeDurability) {
        return HEALTH_FUNCTION_FOR_TYPE.get(typeDurability) * this.durabilityMultiplier;
    }

    public int getDefenseForType(ArmorItem.Type typeDefense) {
        return this.armor.get(typeDefense);
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public SoundEvent getEquipSound() {
        return this.sound;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    public String getName() {
        return this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }

//    public float getAttackSpeed() {
//        return this.attackSpeed;
//    }
//
//    public float getMovementSpeed() {
//        return this.movementSpeed;
//    }

    public Map<Attribute, AttributeModifier> getAdditionalAttributes() {
        return additionalAttributes;
    }
}