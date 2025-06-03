package net.nokunami.elementus.common.registry;

import com.google.common.base.Suppliers;
import net.minecraft.Util;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.nokunami.elementus.common.Etags;
import net.nokunami.elementus.common.config.ANConfig;
import net.nokunami.elementus.common.config.ArmorConfig;
import net.nokunami.elementus.common.config.CatalystArmorConfig;
import net.nokunami.elementus.common.config.SDConfig;
import net.nokunami.elementus.common.registry.ModItems.AdvancedNetheriteItems;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

public enum ModArmorMaterials implements ArmorMaterial {

    STEEL("steel", ArmorConfig.steelArmor_DurabilityForType, Util.make(new EnumMap<>(ArmorItem.Type.class), (c) -> {
        c.put(ArmorItem.Type.BOOTS, ArmorConfig.steelArmor_Boots);c.put(ArmorItem.Type.LEGGINGS, ArmorConfig.steelArmor_Leggings);c.put(ArmorItem.Type.CHESTPLATE, ArmorConfig.steelArmor_Chestplate);c.put(ArmorItem.Type.HELMET, ArmorConfig.steelArmor_Helmet);
    }), ArmorConfig.steelArmor_Enchantability, SoundEvents.ARMOR_EQUIP_IRON,
            (float) ArmorConfig.steelArmor_Toughness, (float) ArmorConfig.steelArmor_KnockbackResistance, () -> Ingredient.of(Etags.Items.REPAIRS_STEEL_EQUIPMENT), Map.of(
            Attributes.ATTACK_SPEED, new AttributeModifier("Armor Attack Speed Modifier", ArmorConfig.steelArmor_AttackSpeed, AttributeModifier.Operation.MULTIPLY_BASE),
            Attributes.MOVEMENT_SPEED, new AttributeModifier("Armor Movement Speed Modifier", ArmorConfig.steelArmor_MovementSpeed, AttributeModifier.Operation.MULTIPLY_BASE))),

    DIARKRITE("diarkrite",  ArmorConfig.diarkriteArmor_DurabilityForType, Util.make(new EnumMap<>(ArmorItem.Type.class), (c) -> {
        c.put(ArmorItem.Type.BOOTS, ArmorConfig.diarkriteArmor_Boots);c.put(ArmorItem.Type.LEGGINGS, ArmorConfig.diarkriteArmor_Leggings);c.put(ArmorItem.Type.CHESTPLATE, ArmorConfig.diarkriteArmor_Chestplate);c.put(ArmorItem.Type.HELMET, ArmorConfig.diarkriteArmor_Helmet);
    }), ArmorConfig.diarkriteArmor_Enchantability, SoundEvents.ARMOR_EQUIP_NETHERITE,
            (float) ArmorConfig.diarkriteArmor_Toughness, (float) ArmorConfig.diarkriteArmor_KnockbackResistance, () -> Ingredient.of(Etags.Items.REPAIRS_DIARKRITE_EQUIPMENT), Map.of(
            Attributes.ATTACK_SPEED, new AttributeModifier("Armor Attack Speed Modifier", ArmorConfig.diarkriteArmor_AttackSpeed, AttributeModifier.Operation.MULTIPLY_BASE),
            Attributes.MOVEMENT_SPEED, new AttributeModifier("Armor Movement Speed Modifier", ArmorConfig.diarkriteArmor_MovementSpeed, AttributeModifier.Operation.MULTIPLY_BASE))),

    ANTHEKTITE("anthektite", ArmorConfig.anthektiteArmor_DurabilityForType, Util.make(new EnumMap<>(ArmorItem.Type.class), (c) -> {
        c.put(ArmorItem.Type.BOOTS, ArmorConfig.anthektiteArmor_Boots);c.put(ArmorItem.Type.LEGGINGS, ArmorConfig.anthektiteArmor_Leggings);c.put(ArmorItem.Type.CHESTPLATE, ArmorConfig.anthektiteArmor_Chestplate);c.put(ArmorItem.Type.HELMET, ArmorConfig.anthektiteArmor_Helmet);
    }), ArmorConfig.anthektiteArmor_Enchantability, SoundEvents.ARMOR_EQUIP_NETHERITE,
            (float) ArmorConfig.anthektiteArmor_Toughness, (float) ArmorConfig.anthektiteArmor_KnockbackResistance, () -> Ingredient.of(Etags.Items.REPAIRS_ANTHEKTITE_EQUIPMENT), Map.of(
            Attributes.ATTACK_SPEED, new AttributeModifier("Armor Attack Speed Modifier", ArmorConfig.anthektiteArmor_AttackSpeed, AttributeModifier.Operation.MULTIPLY_BASE),
            Attributes.MOVEMENT_SPEED, new AttributeModifier("Armor Movement Speed Modifier", ArmorConfig.anthektiteArmor_MovementSpeed, AttributeModifier.Operation.MULTIPLY_BASE))),

    CATALYST("catalyst", CatalystArmorConfig.Durability, Util.make(new EnumMap<>(ArmorItem.Type.class), (c) -> {
        c.put(ArmorItem.Type.BOOTS, 0);c.put(ArmorItem.Type.LEGGINGS, 0);c.put(ArmorItem.Type.CHESTPLATE, CatalystArmorConfig.Armor);c.put(ArmorItem.Type.HELMET, 0);
    }), CatalystArmorConfig.Enchantability, SoundEvents.ARMOR_EQUIP_NETHERITE,
            (float) CatalystArmorConfig.Toughness, (float) CatalystArmorConfig.KnockbackResist, () -> Ingredient.of(Etags.Items.REPAIRS_CATALYST_ARMOR), Map.of(
            Attributes.ATTACK_SPEED, new AttributeModifier("Armor Attack Speed Modifier", CatalystArmorConfig.AttackSpeed, AttributeModifier.Operation.MULTIPLY_BASE),
            Attributes.MOVEMENT_SPEED, new AttributeModifier("Armor Movement Speed Modifier", CatalystArmorConfig.MovementSpeed, AttributeModifier.Operation.MULTIPLY_BASE))),


    DIARKRITE_IRON("diarkrite_iron", ANConfig.diarkriteIronArmor_DurabilityForType, Util.make(new EnumMap<>(ArmorItem.Type.class), (c) -> {
        c.put(ArmorItem.Type.BOOTS, ANConfig.anthektiteIronArmor_Boots);c.put(ArmorItem.Type.LEGGINGS, ANConfig.anthektiteIronArmor_Leggings);c.put(ArmorItem.Type.CHESTPLATE, ANConfig.anthektiteIronArmor_Chestplate);c.put(ArmorItem.Type.HELMET, ANConfig.anthektiteIronArmor_Helmet);
    }), ANConfig.diarkriteIronArmor_Enchantability, SoundEvents.ARMOR_EQUIP_NETHERITE,
            ANConfig.diarkriteIronArmor_Toughness, ANConfig.diarkriteIronArmor_KnockbackResistance, () -> Ingredient.of(AdvancedNetheriteItems.DIARKRITE_IRON.get()), Map.of(
            Attributes.ATTACK_SPEED, new AttributeModifier("Armor Attack Speed Modifier", ANConfig.diarkriteIronArmor_AttackSpeed, AttributeModifier.Operation.MULTIPLY_BASE),
            Attributes.MOVEMENT_SPEED, new AttributeModifier("Armor Movement Speed Modifier", ANConfig.diarkriteIronArmor_MovementSpeed, AttributeModifier.Operation.MULTIPLY_BASE))),
    DIARKRITE_GOLD("diarkrite_gold", ANConfig.diarkriteGoldArmor_DurabilityForType, Util.make(new EnumMap<>(ArmorItem.Type.class), (c) -> {
        c.put(ArmorItem.Type.BOOTS, ANConfig.anthektiteGoldArmor_Boots);c.put(ArmorItem.Type.LEGGINGS, ANConfig.anthektiteGoldArmor_Leggings);c.put(ArmorItem.Type.CHESTPLATE, ANConfig.anthektiteGoldArmor_Chestplate);c.put(ArmorItem.Type.HELMET, ANConfig.anthektiteGoldArmor_Helmet);
    }), ANConfig.diarkriteGoldArmor_Enchantability, SoundEvents.ARMOR_EQUIP_NETHERITE,
            ANConfig.diarkriteGoldArmor_Toughness, ANConfig.diarkriteGoldArmor_KnockbackResistance, () -> Ingredient.of(AdvancedNetheriteItems.DIARKRITE_GOLD.get()), Map.of(
            Attributes.ATTACK_SPEED, new AttributeModifier("Armor Attack Speed Modifier", ANConfig.diarkriteGoldArmor_AttackSpeed, AttributeModifier.Operation.MULTIPLY_BASE),
            Attributes.MOVEMENT_SPEED, new AttributeModifier("Armor Movement Speed Modifier", ANConfig.diarkriteGoldArmor_MovementSpeed, AttributeModifier.Operation.MULTIPLY_BASE))),
    DIARKRITE_EMERALD("diarkrite_emerald", ANConfig.diarkriteEmeraldArmor_DurabilityForType, Util.make(new EnumMap<>(ArmorItem.Type.class), (c) -> {
        c.put(ArmorItem.Type.BOOTS, ANConfig.anthektiteEmeraldArmor_Boots);c.put(ArmorItem.Type.LEGGINGS, ANConfig.anthektiteEmeraldArmor_Leggings);c.put(ArmorItem.Type.CHESTPLATE, ANConfig.anthektiteEmeraldArmor_Chestplate);c.put(ArmorItem.Type.HELMET, ANConfig.anthektiteEmeraldArmor_Helmet);
    }), ANConfig.diarkriteEmeraldArmor_Enchantability, SoundEvents.ARMOR_EQUIP_NETHERITE,
            ANConfig.diarkriteEmeraldArmor_Toughness, ANConfig.diarkriteEmeraldArmor_KnockbackResistance, () -> Ingredient.of(AdvancedNetheriteItems.DIARKRITE_EMERALD.get()), Map.of(
            Attributes.ATTACK_SPEED, new AttributeModifier("Armor Attack Speed Modifier", ANConfig.diarkriteEmeraldArmor_AttackSpeed, AttributeModifier.Operation.MULTIPLY_BASE),
            Attributes.MOVEMENT_SPEED, new AttributeModifier("Armor Movement Speed Modifier", ANConfig.diarkriteEmeraldArmor_MovementSpeed, AttributeModifier.Operation.MULTIPLY_BASE))),
    DIARKRITE_DIAMOND("diarkrite_diamond", ANConfig.diarkriteDiamondArmor_DurabilityForType, Util.make(new EnumMap<>(ArmorItem.Type.class), (c) -> {
        c.put(ArmorItem.Type.BOOTS, ANConfig.anthektiteDiamondArmor_Boots);c.put(ArmorItem.Type.LEGGINGS, ANConfig.anthektiteDiamondArmor_Leggings);c.put(ArmorItem.Type.CHESTPLATE, ANConfig.anthektiteDiamondArmor_Chestplate);c.put(ArmorItem.Type.HELMET, ANConfig.anthektiteDiamondArmor_Helmet);
    }), ANConfig.diarkriteDiamondArmor_Enchantability, SoundEvents.ARMOR_EQUIP_NETHERITE,
            ANConfig.diarkriteDiamondArmor_Toughness, ANConfig.diarkriteDiamondArmor_KnockbackResistance, () -> Ingredient.of(AdvancedNetheriteItems.DIARKRITE_DIAMOND.get()), Map.of(
            Attributes.ATTACK_SPEED, new AttributeModifier("Armor Attack Speed Modifier", ANConfig.diarkriteDiamondArmor_AttackSpeed, AttributeModifier.Operation.MULTIPLY_BASE),
            Attributes.MOVEMENT_SPEED, new AttributeModifier("Armor Movement Speed Modifier", ANConfig.diarkriteDiamondArmor_MovementSpeed, AttributeModifier.Operation.MULTIPLY_BASE))),

    ANTHEKTITE_IRON("anthektite_iron", ANConfig.anthektiteIronArmor_DurabilityForType, Util.make(new EnumMap<>(ArmorItem.Type.class), (c) -> {
        c.put(ArmorItem.Type.BOOTS, ANConfig.anthektiteIronArmor_Boots);c.put(ArmorItem.Type.LEGGINGS, ANConfig.anthektiteIronArmor_Leggings);c.put(ArmorItem.Type.CHESTPLATE, ANConfig.anthektiteIronArmor_Chestplate);c.put(ArmorItem.Type.HELMET, ANConfig.anthektiteIronArmor_Helmet);
    }), ANConfig.anthektiteIronArmor_Enchantability, SoundEvents.ARMOR_EQUIP_NETHERITE,
            ANConfig.anthektiteIronArmor_Toughness, ANConfig.anthektiteIronArmor_KnockbackResistance, () -> Ingredient.of(AdvancedNetheriteItems.ANTHEKTITE_IRON.get()), Map.of(
            Attributes.ATTACK_SPEED, new AttributeModifier("Armor Attack Speed Modifier", ANConfig.anthektiteIronArmor_AttackSpeed, AttributeModifier.Operation.MULTIPLY_BASE),
            Attributes.MOVEMENT_SPEED, new AttributeModifier("Armor Movement Speed Modifier", ANConfig.anthektiteIronArmor_MovementSpeed, AttributeModifier.Operation.MULTIPLY_BASE))),
    ANTHEKTITE_GOLD("anthektite_gold", ANConfig.anthektiteGoldArmor_DurabilityForType, Util.make(new EnumMap<>(ArmorItem.Type.class), (c) -> {
        c.put(ArmorItem.Type.BOOTS, ANConfig.anthektiteGoldArmor_Boots);c.put(ArmorItem.Type.LEGGINGS, ANConfig.anthektiteGoldArmor_Leggings);c.put(ArmorItem.Type.CHESTPLATE, ANConfig.anthektiteGoldArmor_Chestplate);c.put(ArmorItem.Type.HELMET, ANConfig.anthektiteGoldArmor_Helmet);
    }), ANConfig.anthektiteGoldArmor_Enchantability, SoundEvents.ARMOR_EQUIP_NETHERITE,
            ANConfig.anthektiteGoldArmor_Toughness, ANConfig.anthektiteGoldArmor_KnockbackResistance, () -> Ingredient.of(AdvancedNetheriteItems.ANTHEKTITE_GOLD.get()), Map.of(
            Attributes.ATTACK_SPEED, new AttributeModifier("Armor Attack Speed Modifier", ANConfig.anthektiteGoldArmor_AttackSpeed, AttributeModifier.Operation.MULTIPLY_BASE),
            Attributes.MOVEMENT_SPEED, new AttributeModifier("Armor Movement Speed Modifier", ANConfig.anthektiteGoldArmor_MovementSpeed, AttributeModifier.Operation.MULTIPLY_BASE))),
    ANTHEKTITE_EMERALD("anthektite_emerald", ANConfig.anthektiteEmeraldArmor_DurabilityForType, Util.make(new EnumMap<>(ArmorItem.Type.class), (c) -> {
        c.put(ArmorItem.Type.BOOTS, ANConfig.anthektiteEmeraldArmor_Boots);c.put(ArmorItem.Type.LEGGINGS, ANConfig.anthektiteEmeraldArmor_Leggings);c.put(ArmorItem.Type.CHESTPLATE, ANConfig.anthektiteEmeraldArmor_Chestplate);c.put(ArmorItem.Type.HELMET, ANConfig.anthektiteEmeraldArmor_Helmet);
    }), ANConfig.anthektiteEmeraldArmor_Enchantability, SoundEvents.ARMOR_EQUIP_NETHERITE,
            ANConfig.anthektiteEmeraldArmor_Toughness, ANConfig.anthektiteEmeraldArmor_KnockbackResistance, () -> Ingredient.of(AdvancedNetheriteItems.ANTHEKTITE_EMERALD.get()), Map.of(
            Attributes.ATTACK_SPEED, new AttributeModifier("Armor Attack Speed Modifier", ANConfig.anthektiteEmeraldArmor_AttackSpeed, AttributeModifier.Operation.MULTIPLY_BASE),
            Attributes.MOVEMENT_SPEED, new AttributeModifier("Armor Movement Speed Modifier", ANConfig.anthektiteEmeraldArmor_MovementSpeed, AttributeModifier.Operation.MULTIPLY_BASE))),
    ANTHEKTITE_DIAMOND("anthektite_diamond", ANConfig.anthektiteDiamondArmor_DurabilityForType, Util.make(new EnumMap<>(ArmorItem.Type.class), (c) -> {
        c.put(ArmorItem.Type.BOOTS, ANConfig.anthektiteDiamondArmor_Boots);c.put(ArmorItem.Type.LEGGINGS, ANConfig.anthektiteDiamondArmor_Leggings);c.put(ArmorItem.Type.CHESTPLATE, ANConfig.anthektiteDiamondArmor_Chestplate);c.put(ArmorItem.Type.HELMET, ANConfig.anthektiteDiamondArmor_Helmet);
    }), ANConfig.anthektiteDiamondArmor_Enchantability, SoundEvents.ARMOR_EQUIP_NETHERITE,
            ANConfig.anthektiteDiamondArmor_Toughness, ANConfig.anthektiteDiamondArmor_KnockbackResistance, () -> Ingredient.of(AdvancedNetheriteItems.ANTHEKTITE_DIAMOND.get()), Map.of(
            Attributes.ATTACK_SPEED, new AttributeModifier("Armor Attack Speed Modifier", ANConfig.anthektiteDiamondArmor_AttackSpeed, AttributeModifier.Operation.MULTIPLY_BASE),
            Attributes.MOVEMENT_SPEED, new AttributeModifier("Armor Movement Speed Modifier", ANConfig.anthektiteDiamondArmor_MovementSpeed, AttributeModifier.Operation.MULTIPLY_BASE))),


    STEEL_SAMURAI("steel_samurai", SDConfig.steelSamuraiArmor_DurabilityForType, Util.make(new EnumMap<>(ArmorItem.Type.class), (c) -> {
        c.put(ArmorItem.Type.BOOTS, SDConfig.steelSamuraiArmor_Boots);c.put(ArmorItem.Type.LEGGINGS, SDConfig.steelSamuraiArmor_Leggings);c.put(ArmorItem.Type.CHESTPLATE, SDConfig.steelSamuraiArmor_Chestplate);c.put(ArmorItem.Type.HELMET, SDConfig.steelSamuraiArmor_Helmet);
    }), SDConfig.steelSamuraiArmor_Enchantability, SoundEvents.ARMOR_EQUIP_IRON,
            (float) SDConfig.steelSamuraiArmor_Toughness, (float) SDConfig.steelSamuraiArmor_KnockbackResistance, () -> Ingredient.of(Etags.Items.REPAIRS_STEEL_EQUIPMENT), Map.of(
            Attributes.ATTACK_SPEED, new AttributeModifier("Armor Attack Speed Modifier", SDConfig.steelSamuraiArmor_AttackSpeed, AttributeModifier.Operation.MULTIPLY_BASE),
            Attributes.MOVEMENT_SPEED, new AttributeModifier("Armor Movement Speed Modifier", SDConfig.steelSamuraiArmor_MovementSpeed, AttributeModifier.Operation.MULTIPLY_BASE))),

    STEEL_SAMURAI_LIGHT("steel_samurai_light", SDConfig.steelSamuraiLightArmor_DurabilityForType, Util.make(new EnumMap<>(ArmorItem.Type.class), (c) -> {
        c.put(ArmorItem.Type.BOOTS, SDConfig.steelSamuraiLightArmor_Boots);c.put(ArmorItem.Type.LEGGINGS, SDConfig.steelSamuraiLightArmor_Leggings);c.put(ArmorItem.Type.CHESTPLATE, SDConfig.steelSamuraiLightArmor_Chestplate);c.put(ArmorItem.Type.HELMET, SDConfig.steelSamuraiLightArmor_Helmet);
    }), SDConfig.steelSamuraiLightArmor_Enchantability, SoundEvents.ARMOR_EQUIP_IRON,
            (float) SDConfig.steelSamuraiLightArmor_Toughness, (float) SDConfig.steelSamuraiLightArmor_KnockbackResistance, () -> Ingredient.of(Etags.Items.REPAIRS_STEEL_EQUIPMENT), Map.of(
            Attributes.ATTACK_SPEED, new AttributeModifier("Armor Attack Speed Modifier", SDConfig.steelSamuraiLightArmor_AttackSpeed, AttributeModifier.Operation.MULTIPLY_BASE),
            Attributes.MOVEMENT_SPEED, new AttributeModifier("Armor Movement Speed Modifier", SDConfig.steelSamuraiLightArmor_MovementSpeed, AttributeModifier.Operation.MULTIPLY_BASE))),

    STEEL_SAMURAI_MASTER("steel_samurai_master", SDConfig.steelSamuraiMasterArmor_DurabilityForType, Util.make(new EnumMap<>(ArmorItem.Type.class), (c) -> {
        c.put(ArmorItem.Type.BOOTS, SDConfig.steelSamuraiMasterArmor_Boots);c.put(ArmorItem.Type.LEGGINGS, SDConfig.steelSamuraiMasterArmor_Leggings);c.put(ArmorItem.Type.CHESTPLATE, SDConfig.steelSamuraiMasterArmor_Chestplate);c.put(ArmorItem.Type.HELMET, SDConfig.steelSamuraiMasterArmor_Helmet);
    }), SDConfig.steelSamuraiMasterArmor_Enchantability, SoundEvents.ARMOR_EQUIP_IRON,
            (float) SDConfig.steelSamuraiMasterArmor_Toughness, (float) SDConfig.steelSamuraiMasterArmor_KnockbackResistance, () -> Ingredient.of(Etags.Items.REPAIRS_STEEL_EQUIPMENT), Map.of(
            Attributes.ATTACK_SPEED, new AttributeModifier("Armor Attack Speed Modifier", SDConfig.steelSamuraiMasterArmor_AttackSpeed, AttributeModifier.Operation.MULTIPLY_BASE),
            Attributes.MOVEMENT_SPEED, new AttributeModifier("Armor Movement Speed Modifier", SDConfig.steelSamuraiMasterArmor_MovementSpeed, AttributeModifier.Operation.MULTIPLY_BASE))),


    DIARKRITE_SAMURAI("diarkrite_samurai", SDConfig.diarkriteSamuraiArmor_DurabilityForType, Util.make(new EnumMap<>(ArmorItem.Type.class), (c) -> {
        c.put(ArmorItem.Type.BOOTS, SDConfig.diarkriteSamuraiArmor_Boots);c.put(ArmorItem.Type.LEGGINGS, SDConfig.diarkriteSamuraiArmor_Leggings);c.put(ArmorItem.Type.CHESTPLATE, SDConfig.diarkriteSamuraiArmor_Chestplate);c.put(ArmorItem.Type.HELMET, SDConfig.diarkriteSamuraiArmor_Helmet);
    }), SDConfig.diarkriteSamuraiArmor_Enchantability, SoundEvents.ARMOR_EQUIP_NETHERITE,
            (float) SDConfig.diarkriteSamuraiArmor_Toughness, (float) SDConfig.diarkriteSamuraiArmor_KnockbackResistance, () -> Ingredient.of(Etags.Items.REPAIRS_DIARKRITE_EQUIPMENT), Map.of(
            Attributes.ATTACK_SPEED, new AttributeModifier("Armor Attack Speed Modifier", SDConfig.diarkriteSamuraiArmor_AttackSpeed, AttributeModifier.Operation.MULTIPLY_BASE),
            Attributes.MOVEMENT_SPEED, new AttributeModifier("Armor Movement Speed Modifier", SDConfig.diarkriteSamuraiArmor_MovementSpeed, AttributeModifier.Operation.MULTIPLY_BASE))),

    DIARKRITE_SAMURAI_LIGHT("diarkrite_samurai_light", SDConfig.diarkriteSamuraiLightArmor_DurabilityForType, Util.make(new EnumMap<>(ArmorItem.Type.class), (c) -> {
        c.put(ArmorItem.Type.BOOTS, SDConfig.diarkriteSamuraiLightArmor_Boots);c.put(ArmorItem.Type.LEGGINGS, SDConfig.diarkriteSamuraiLightArmor_Leggings);c.put(ArmorItem.Type.CHESTPLATE, SDConfig.diarkriteSamuraiLightArmor_Chestplate);c.put(ArmorItem.Type.HELMET, SDConfig.diarkriteSamuraiLightArmor_Helmet);
    }), SDConfig.diarkriteSamuraiLightArmor_Enchantability, SoundEvents.ARMOR_EQUIP_NETHERITE,
            (float) SDConfig.diarkriteSamuraiLightArmor_Toughness, (float) SDConfig.diarkriteSamuraiLightArmor_KnockbackResistance, () -> Ingredient.of(Etags.Items.REPAIRS_DIARKRITE_EQUIPMENT), Map.of(
            Attributes.ATTACK_SPEED, new AttributeModifier("Armor Attack Speed Modifier", SDConfig.diarkriteSamuraiLightArmor_AttackSpeed, AttributeModifier.Operation.MULTIPLY_BASE),
            Attributes.MOVEMENT_SPEED, new AttributeModifier("Armor Movement Speed Modifier", SDConfig.diarkriteSamuraiLightArmor_MovementSpeed, AttributeModifier.Operation.MULTIPLY_BASE))),

    DIARKRITE_SAMURAI_MASTER("diarkrite_samurai_master", SDConfig.diarkriteSamuraiMasterArmor_DurabilityForType, Util.make(new EnumMap<>(ArmorItem.Type.class), (c) -> {
        c.put(ArmorItem.Type.BOOTS, SDConfig.diarkriteSamuraiMasterArmor_Boots);c.put(ArmorItem.Type.LEGGINGS, SDConfig.diarkriteSamuraiMasterArmor_Leggings);c.put(ArmorItem.Type.CHESTPLATE, SDConfig.diarkriteSamuraiMasterArmor_Chestplate);c.put(ArmorItem.Type.HELMET, SDConfig.diarkriteSamuraiMasterArmor_Helmet);
    }), SDConfig.diarkriteSamuraiMasterArmor_Enchantability, SoundEvents.ARMOR_EQUIP_NETHERITE,
            (float) SDConfig.diarkriteSamuraiMasterArmor_Toughness, (float) SDConfig.diarkriteSamuraiMasterArmor_KnockbackResistance, () -> Ingredient.of(Etags.Items.REPAIRS_DIARKRITE_EQUIPMENT), Map.of(
            Attributes.ATTACK_SPEED, new AttributeModifier("Armor Attack Speed Modifier", SDConfig.diarkriteSamuraiMasterArmor_AttackSpeed, AttributeModifier.Operation.MULTIPLY_BASE),
            Attributes.MOVEMENT_SPEED, new AttributeModifier("Armor Movement Speed Modifier", SDConfig.diarkriteSamuraiMasterArmor_MovementSpeed, AttributeModifier.Operation.MULTIPLY_BASE))),


    ANTHEKTITE_SAMURAI("anthektite_samurai", SDConfig.anthektiteSamuraiArmor_DurabilityForType, Util.make(new EnumMap<>(ArmorItem.Type.class), (c) -> {
        c.put(ArmorItem.Type.BOOTS, SDConfig.anthektiteSamuraiArmor_Boots);c.put(ArmorItem.Type.LEGGINGS, SDConfig.anthektiteSamuraiArmor_Leggings);c.put(ArmorItem.Type.CHESTPLATE, SDConfig.anthektiteSamuraiArmor_Chestplate);c.put(ArmorItem.Type.HELMET, SDConfig.anthektiteSamuraiArmor_Helmet);
    }), SDConfig.anthektiteSamuraiArmor_Enchantability, SoundEvents.ARMOR_EQUIP_NETHERITE,
            (float) SDConfig.anthektiteSamuraiArmor_Toughness, (float) SDConfig.anthektiteSamuraiArmor_KnockbackResistance, () -> Ingredient.of(Etags.Items.REPAIRS_ANTHEKTITE_EQUIPMENT), Map.of(
            Attributes.ATTACK_SPEED, new AttributeModifier("Armor Attack Speed Modifier", SDConfig.anthektiteSamuraiArmor_AttackSpeed, AttributeModifier.Operation.MULTIPLY_BASE),
            Attributes.MOVEMENT_SPEED, new AttributeModifier("Armor Movement Speed Modifier", SDConfig.anthektiteSamuraiArmor_MovementSpeed, AttributeModifier.Operation.MULTIPLY_BASE))),

    ANTHEKTITE_SAMURAI_LIGHT("anthektite_samurai_light", SDConfig.anthektiteSamuraiLightArmor_DurabilityForType, Util.make(new EnumMap<>(ArmorItem.Type.class), (c) -> {
        c.put(ArmorItem.Type.BOOTS, SDConfig.anthektiteSamuraiLightArmor_Boots);c.put(ArmorItem.Type.LEGGINGS, SDConfig.anthektiteSamuraiLightArmor_Leggings);c.put(ArmorItem.Type.CHESTPLATE, SDConfig.anthektiteSamuraiLightArmor_Chestplate);c.put(ArmorItem.Type.HELMET, SDConfig.anthektiteSamuraiLightArmor_Helmet);
    }), SDConfig.anthektiteSamuraiLightArmor_Enchantability, SoundEvents.ARMOR_EQUIP_NETHERITE,
            (float) SDConfig.anthektiteSamuraiLightArmor_Toughness, (float) SDConfig.anthektiteSamuraiLightArmor_KnockbackResistance, () -> Ingredient.of(Etags.Items.REPAIRS_ANTHEKTITE_EQUIPMENT), Map.of(
            Attributes.ATTACK_SPEED, new AttributeModifier("Armor Attack Speed Modifier", SDConfig.anthektiteSamuraiLightArmor_AttackSpeed, AttributeModifier.Operation.MULTIPLY_BASE),
            Attributes.MOVEMENT_SPEED, new AttributeModifier("Armor Movement Speed Modifier", SDConfig.anthektiteSamuraiLightArmor_MovementSpeed, AttributeModifier.Operation.MULTIPLY_BASE))),

    ANTHEKTITE_SAMURAI_MASTER("anthektite_samurai_master", SDConfig.anthektiteSamuraiMasterArmor_DurabilityForType, Util.make(new EnumMap<>(ArmorItem.Type.class), (c) -> {
        c.put(ArmorItem.Type.BOOTS, SDConfig.anthektiteSamuraiMasterArmor_Boots);c.put(ArmorItem.Type.LEGGINGS, SDConfig.anthektiteSamuraiMasterArmor_Leggings);c.put(ArmorItem.Type.CHESTPLATE, SDConfig.anthektiteSamuraiMasterArmor_Chestplate);c.put(ArmorItem.Type.HELMET, SDConfig.anthektiteSamuraiMasterArmor_Helmet);
    }), SDConfig.anthektiteSamuraiMasterArmor_Enchantability, SoundEvents.ARMOR_EQUIP_NETHERITE,
            (float) SDConfig.anthektiteSamuraiMasterArmor_Toughness, (float) SDConfig.anthektiteSamuraiMasterArmor_KnockbackResistance, () -> Ingredient.of(Etags.Items.REPAIRS_ANTHEKTITE_EQUIPMENT), Map.of(
            Attributes.ATTACK_SPEED, new AttributeModifier("Armor Attack Speed Modifier", SDConfig.anthektiteSamuraiMasterArmor_AttackSpeed, AttributeModifier.Operation.MULTIPLY_BASE),
            Attributes.MOVEMENT_SPEED, new AttributeModifier("Armor Movement Speed Modifier", SDConfig.anthektiteSamuraiMasterArmor_MovementSpeed, AttributeModifier.Operation.MULTIPLY_BASE)));


    private final String name;
    private final int durabilityMultiplier;
    private final EnumMap<ArmorItem.Type, Integer> armor;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredient;
    private final Map<Attribute, AttributeModifier> additionalAttributes;

    ModArmorMaterials(String name, int durabilityMultiplier, EnumMap<ArmorItem.Type, Integer> armor, int enchantmentValue, SoundEvent soundEvent, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient, Map<Attribute, AttributeModifier> additionalAttributes) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.armor = armor;
        this.enchantmentValue = enchantmentValue;
        this.sound = soundEvent;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = Suppliers.memoize(repairIngredient::get);
        this.additionalAttributes = additionalAttributes;
    }

    private static final EnumMap<ArmorItem.Type, Integer> HEALTH_FUNCTION_FOR_TYPE = Util.make(new EnumMap<>(ArmorItem.Type.class), (p_266653_) -> {
        p_266653_.put(ArmorItem.Type.BOOTS, 13);
        p_266653_.put(ArmorItem.Type.LEGGINGS, 15);
        p_266653_.put(ArmorItem.Type.CHESTPLATE, 16);
        p_266653_.put(ArmorItem.Type.HELMET, 11);
    });

    public int getDurabilityForType(ArmorItem.@NotNull Type typeDurability) {
        return HEALTH_FUNCTION_FOR_TYPE.get(typeDurability) * this.durabilityMultiplier;
    }

    public int getDefenseForType(ArmorItem.@NotNull Type typeDefense) {
        return this.armor.get(typeDefense);
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public @NotNull SoundEvent getEquipSound() {
        return this.sound;
    }

    public @NotNull Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    public @NotNull String getName() {
        return this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }

    public Map<Attribute, AttributeModifier> getAdditionalAttributes() {
        return additionalAttributes;
    }
}