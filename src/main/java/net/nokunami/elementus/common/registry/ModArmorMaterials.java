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
import net.nokunami.elementus.common.config.ArmorConfig;
import net.nokunami.elementus.common.config.catalystConfigs.CatalystArmorConfig;
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
            Attributes.MOVEMENT_SPEED, new AttributeModifier("Armor Movement Speed Modifier", CatalystArmorConfig.MovementSpeed, AttributeModifier.Operation.MULTIPLY_BASE)));

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