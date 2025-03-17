package net.nokunami.elementus.common.compat.ironsspellbooks;

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
import net.nokunami.elementus.common.Etags;
import net.nokunami.elementus.common.config.ArmorConfig;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

public enum MagicArmorMaterial implements ArmorMaterial {

    DIARKRITE_MAGE("diarkrite_mage",  ArmorConfig.diarkriteMageArmor_DurabilityForType, Util.make(new EnumMap<>(ArmorItem.Type.class), (c) -> {
        c.put(ArmorItem.Type.BOOTS, ArmorConfig.diarkriteMageArmor_Boots);c.put(ArmorItem.Type.LEGGINGS, ArmorConfig.diarkriteMageArmor_Leggings);c.put(ArmorItem.Type.CHESTPLATE, ArmorConfig.diarkriteMageArmor_Chestplate);c.put(ArmorItem.Type.HELMET, ArmorConfig.diarkriteMageArmor_Helmet);
    }), ArmorConfig.diarkriteMageArmor_Enchantability, SoundEvents.ARMOR_EQUIP_NETHERITE,
            ArmorConfig.diarkriteMageArmor_Toughness, ArmorConfig.diarkriteMageArmor_KnockbackResistance, () -> Ingredient.of(Etags.Items.REPAIRS_DIARKRITE_MAGE_ARMOR), Map.of(
            Attributes.ATTACK_SPEED, new AttributeModifier("Armor Attack Speed Modifier", ArmorConfig.diarkriteMageArmor_AttackSpeed, AttributeModifier.Operation.MULTIPLY_BASE),
            Attributes.MOVEMENT_SPEED, new AttributeModifier("Armor Movement Speed Modifier", ArmorConfig.diarkriteMageArmor_MovementSpeed, AttributeModifier.Operation.MULTIPLY_BASE),
            AttributeRegistry.MAX_MANA.get(), new AttributeModifier("Mage Max Mana Modifier", ArmorConfig.diarkriteMageArmor_MaxMana, AttributeModifier.Operation.ADDITION),
            AttributeRegistry.MANA_REGEN.get(), new AttributeModifier("Mage Mana Regen Modifier", ArmorConfig.diarkriteMageArmor_ManaRegen, AttributeModifier.Operation.MULTIPLY_BASE),
            AttributeRegistry.SPELL_POWER.get(), new AttributeModifier("Mage Spell Power Modifier", ArmorConfig.diarkriteMageArmor_SpellPower, AttributeModifier.Operation.MULTIPLY_BASE),
            AttributeRegistry.SPELL_RESIST.get(), new AttributeModifier("Mage Spell Resist Modifier", ArmorConfig.diarkriteMageArmor_SpellResist, AttributeModifier.Operation.MULTIPLY_BASE),
            AttributeRegistry.COOLDOWN_REDUCTION.get(), new AttributeModifier("Mage Cooldown Reduction Modifier", ArmorConfig.diarkriteMageArmor_Cooldown, AttributeModifier.Operation.MULTIPLY_BASE),
            AttributeRegistry.CAST_TIME_REDUCTION.get(), new AttributeModifier("Mage Cast Time Reduction Modifier", ArmorConfig.diarkriteMageArmor_CastTime, AttributeModifier.Operation.MULTIPLY_BASE))),

    ANTHEKTITE_MAGE("anthektite_mage", ArmorConfig.anthektiteMageArmor_DurabilityForType, Util.make(new EnumMap<>(ArmorItem.Type.class), (c) -> {
        c.put(ArmorItem.Type.BOOTS, ArmorConfig.anthektiteMageArmor_Boots);c.put(ArmorItem.Type.LEGGINGS, ArmorConfig.anthektiteMageArmor_Leggings);c.put(ArmorItem.Type.CHESTPLATE, ArmorConfig.anthektiteMageArmor_Chestplate);c.put(ArmorItem.Type.HELMET, ArmorConfig.anthektiteMageArmor_Helmet);
    }), ArmorConfig.anthektiteMageArmor_Enchantability, SoundEvents.ARMOR_EQUIP_NETHERITE,
            ArmorConfig.anthektiteMageArmor_Toughness, ArmorConfig.anthektiteMageArmor_KnockbackResistance, () -> Ingredient.of(Etags.Items.REPAIRS_ANTHEKTITE_MAGE_ARMOR), Map.of(
            Attributes.ATTACK_SPEED, new AttributeModifier("Armor Attack Speed Modifier", ArmorConfig.anthektiteMageArmor_AttackSpeed, AttributeModifier.Operation.MULTIPLY_BASE),
            Attributes.MOVEMENT_SPEED, new AttributeModifier("Armor Movement Speed Modifier", ArmorConfig.anthektiteMageArmor_MovementSpeed, AttributeModifier.Operation.MULTIPLY_BASE),
            AttributeRegistry.MAX_MANA.get(), new AttributeModifier("Mage Max Mana Modifier", ArmorConfig.anthektiteMageArmor_MaxMana, AttributeModifier.Operation.ADDITION),
            AttributeRegistry.MANA_REGEN.get(), new AttributeModifier("Mage Mana Regen Modifier", ArmorConfig.anthektiteMageArmor_ManaRegen, AttributeModifier.Operation.MULTIPLY_BASE),
            AttributeRegistry.SPELL_POWER.get(), new AttributeModifier("Mage Spell Power Modifier", ArmorConfig.anthektiteMageArmor_SpellPower, AttributeModifier.Operation.MULTIPLY_BASE),
            AttributeRegistry.SPELL_RESIST.get(), new AttributeModifier("Mage Spell Resist Modifier", ArmorConfig.anthektiteMageArmor_SpellResist, AttributeModifier.Operation.MULTIPLY_BASE),
            AttributeRegistry.COOLDOWN_REDUCTION.get(), new AttributeModifier("Mage Cooldown Reduction Modifier", ArmorConfig.anthektiteMageArmor_Cooldown, AttributeModifier.Operation.MULTIPLY_BASE),
            AttributeRegistry.CAST_TIME_REDUCTION.get(), new AttributeModifier("Mage Cast Time Reduction Modifier", ArmorConfig.anthektiteMageArmor_CastTime, AttributeModifier.Operation.MULTIPLY_BASE)));


    private final String name;
    private final int durabilityMultiplier;
    private final EnumMap<ArmorItem.Type, Integer> armor;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredient;
    private final Map<Attribute, AttributeModifier> additionalAttributes;

    MagicArmorMaterial(String name, int durabilityMultiplier, EnumMap<ArmorItem.Type, Integer> armor, int enchantmentValue, SoundEvent soundEvent, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient, Map<Attribute, AttributeModifier> additionalAttributes) {
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