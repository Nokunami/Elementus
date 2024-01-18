package net.nokunami.elementus.item;

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

import net.nokunami.elementus.registry.ItemsRegistry;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

public enum ArmorTiers implements ArmorMaterial {
    STEEL("steel", 24, Util.make(new EnumMap<>(ArmorItem.Type.class), (armor) -> {
        armor.put(ArmorItem.Type.BOOTS, 2);
        armor.put(ArmorItem.Type.LEGGINGS, 6);
        armor.put(ArmorItem.Type.CHESTPLATE, 7);
        armor.put(ArmorItem.Type.HELMET, 2);
    }), 10, SoundEvents.ARMOR_EQUIP_IRON,
            1.0f, 0.025f,
            () -> Ingredient.of(ItemsRegistry.STEEL_INGOT.get()), Map.of()),
    ANTHEKTITE("anthektite", 34, Util.make(new EnumMap<>(ArmorItem.Type.class), (armor) -> {
        armor.put(ArmorItem.Type.BOOTS, 3);
        armor.put(ArmorItem.Type.LEGGINGS, 6);
        armor.put(ArmorItem.Type.CHESTPLATE, 8);
        armor.put(ArmorItem.Type.HELMET, 3);
    }), 15, SoundEvents.ARMOR_EQUIP_NETHERITE,
            2.25f, 0.125f,
            () -> Ingredient.of(ItemsRegistry.ANTHEKTITE_INGOT.get()), Map.of(
                    Attributes.MOVEMENT_SPEED, new AttributeModifier("Anthektite Heaviness", -0.02F, AttributeModifier.Operation.MULTIPLY_BASE)
    )),
    DIARKRITE("diarkrite", 40, Util.make(new EnumMap<>(ArmorItem.Type.class), (armor) -> {
        armor.put(ArmorItem.Type.BOOTS, 3);
        armor.put(ArmorItem.Type.LEGGINGS, 6);
        armor.put(ArmorItem.Type.CHESTPLATE, 8);
        armor.put(ArmorItem.Type.HELMET, 3);
    }), 18, SoundEvents.ARMOR_EQUIP_NETHERITE,
            4.0f, 0.175f,
            () -> Ingredient.of(ItemsRegistry.DIARKRITE_INGOT.get()), Map.of(
            Attributes.ATTACK_SPEED, new AttributeModifier("Diarkrite Attack Speed Bonus", 0.1F, AttributeModifier.Operation.MULTIPLY_BASE)
    ));

    private static final EnumMap<ArmorItem.Type, Integer> HEALTH_FUNCTION_FOR_TYPE = Util.make(new EnumMap<>(ArmorItem.Type.class), (p_266653_) -> {
        p_266653_.put(ArmorItem.Type.BOOTS, 13);
        p_266653_.put(ArmorItem.Type.LEGGINGS, 15);
        p_266653_.put(ArmorItem.Type.CHESTPLATE, 16);
        p_266653_.put(ArmorItem.Type.HELMET, 11);});
    private final String name;
    private final int durabilityMultiplier;
    private final EnumMap<ArmorItem.Type, Integer> protectionFunctionForType;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredient;

    private final Map<Attribute, AttributeModifier> additionalAttributes;

    ArmorTiers(String name, int durabilityMultiplier, EnumMap<ArmorItem.Type, Integer> protection, int enchantmentValue, SoundEvent soundEvent, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient, Map<Attribute, AttributeModifier> additionalAttributes) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionFunctionForType = protection;
        this.enchantmentValue = enchantmentValue;
        this.sound = soundEvent;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = Suppliers.memoize(repairIngredient::get);
        this.additionalAttributes = additionalAttributes;
    }

    public int getDurabilityForType(ArmorItem.@NotNull Type typeDurability) {
        return HEALTH_FUNCTION_FOR_TYPE.get(typeDurability) * this.durabilityMultiplier;
    }

    public int getDefenseForType(ArmorItem.@NotNull Type typeDefense) {
        return this.protectionFunctionForType.get(typeDefense);
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
    
    public Map<Attribute, AttributeModifier> getAdditionalAttributes() {return additionalAttributes;}
}