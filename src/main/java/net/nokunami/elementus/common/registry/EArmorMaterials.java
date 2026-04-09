package net.nokunami.elementus.common.registry;

import com.google.common.base.Suppliers;
import net.minecraft.Util;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.nokunami.elementus.common.config.EConfig;
import net.nokunami.elementus.common.config.configSets.armor.ArmorMaterialConfig;
import net.nokunami.elementus.common.tags.EItemTags;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.function.Supplier;

public class EArmorMaterials {



    public enum EnumArmorMaterials implements ArmorMaterial {
//        ASTALITE("astalite", ArmorConfig.steelArmor_DurabilityForType, Util.make(new EnumMap<>(ArmorItem.Type.class), (c) -> {
//            c.put(ArmorItem.Type.BOOTS, ArmorConfig.steelArmor_Boots);c.put(ArmorItem.Type.LEGGINGS, ArmorConfig.steelArmor_Leggings);c.put(ArmorItem.Type.CHESTPLATE, ArmorConfig.steelArmor_Chestplate);c.put(ArmorItem.Type.HELMET, ArmorConfig.steelArmor_Helmet);
//        }), ArmorConfig.steelArmor_Enchantability, SoundEvents.ARMOR_EQUIP_IRON,
//                (float) ArmorConfig.steelArmor_Toughness, (float) ArmorConfig.steelArmor_KnockbackResistance, () -> Ingredient.of(EItemTags.REPAIRS_ASTALITE_EQUIPMENT), Map.of(
//                Attributes.ATTACK_SPEED, new AttributeModifier("Armor Attack Speed Modifier", ArmorConfig.steelArmor_AttackSpeed, AttributeModifier.Operation.MULTIPLY_BASE),
//                Attributes.MOVEMENT_SPEED, new AttributeModifier("Armor Movement Speed Modifier", ArmorConfig.steelArmor_MovementSpeed, AttributeModifier.Operation.MULTIPLY_BASE))),
//
//        DIARKRITE("diarkrite",  ArmorConfig.diarkriteArmor_DurabilityForType, Util.make(new EnumMap<>(ArmorItem.Type.class), (c) -> {
//            c.put(ArmorItem.Type.BOOTS, ArmorConfig.diarkriteArmor_Boots);c.put(ArmorItem.Type.LEGGINGS, ArmorConfig.diarkriteArmor_Leggings);c.put(ArmorItem.Type.CHESTPLATE, ArmorConfig.diarkriteArmor_Chestplate);c.put(ArmorItem.Type.HELMET, ArmorConfig.diarkriteArmor_Helmet);
//        }), ArmorConfig.diarkriteArmor_Enchantability, SoundEvents.ARMOR_EQUIP_NETHERITE,
//                (float) ArmorConfig.diarkriteArmor_Toughness, (float) ArmorConfig.diarkriteArmor_KnockbackResistance, () -> Ingredient.of(EItemTags.REPAIRS_DIARKRITE_EQUIPMENT), Map.of(
//                Attributes.ATTACK_SPEED, new AttributeModifier("Armor Attack Speed Modifier", ArmorConfig.diarkriteArmor_AttackSpeed, AttributeModifier.Operation.MULTIPLY_BASE),
//                Attributes.MOVEMENT_SPEED, new AttributeModifier("Armor Movement Speed Modifier", ArmorConfig.diarkriteArmor_MovementSpeed, AttributeModifier.Operation.MULTIPLY_BASE))),
//
//        ANTHEKTITE("anthektite", ArmorConfig.anthektiteArmor_DurabilityForType, Util.make(new EnumMap<>(ArmorItem.Type.class), (c) -> {
//            c.put(ArmorItem.Type.BOOTS, ArmorConfig.anthektiteArmor_Boots);c.put(ArmorItem.Type.LEGGINGS, ArmorConfig.anthektiteArmor_Leggings);c.put(ArmorItem.Type.CHESTPLATE, ArmorConfig.anthektiteArmor_Chestplate);c.put(ArmorItem.Type.HELMET, ArmorConfig.anthektiteArmor_Helmet);
//        }), ArmorConfig.anthektiteArmor_Enchantability, SoundEvents.ARMOR_EQUIP_NETHERITE,
//                (float) ArmorConfig.anthektiteArmor_Toughness, (float) ArmorConfig.anthektiteArmor_KnockbackResistance, () -> Ingredient.of(EItemTags.REPAIRS_ANTHEKTITE_EQUIPMENT), Map.of(
//                Attributes.ATTACK_SPEED, new AttributeModifier("Armor Attack Speed Modifier", ArmorConfig.anthektiteArmor_AttackSpeed, AttributeModifier.Operation.MULTIPLY_BASE),
//                Attributes.MOVEMENT_SPEED, new AttributeModifier("Armor Movement Speed Modifier", ArmorConfig.anthektiteArmor_MovementSpeed, AttributeModifier.Operation.MULTIPLY_BASE))),
//
//        CATALYST("catalyst", CatalystArmorConfig.Durability, Util.make(new EnumMap<>(ArmorItem.Type.class), (c) -> {
//            c.put(ArmorItem.Type.BOOTS, 0);c.put(ArmorItem.Type.LEGGINGS, 0);c.put(ArmorItem.Type.CHESTPLATE, CatalystArmorConfig.Armor);c.put(ArmorItem.Type.HELMET, 0);
//        }), CatalystArmorConfig.Enchantability, SoundEvents.ARMOR_EQUIP_NETHERITE,
//                (float) CatalystArmorConfig.Toughness, (float) CatalystArmorConfig.KnockbackResist, () -> Ingredient.of(EItemTags.REPAIRS_CATALYST_ARMOR), Map.of(
//                Attributes.ATTACK_SPEED, new AttributeModifier("Armor Attack Speed Modifier", CatalystArmorConfig.AttackSpeed, AttributeModifier.Operation.MULTIPLY_BASE),
//                Attributes.MOVEMENT_SPEED, new AttributeModifier("Armor Movement Speed Modifier", CatalystArmorConfig.MovementSpeed, AttributeModifier.Operation.MULTIPLY_BASE)));

        ASTALITE("astalite", EConfig.COMMON.ASTALITE_ARMOR, SoundEvents.ARMOR_EQUIP_IRON, () -> Ingredient.of(EItemTags.REPAIRS_ASTALITE_EQUIPMENT)),
        DIARKRITE("diarkrite", EConfig.COMMON.DIARKRITE_ARMOR, ESounds.ARMOR_EQUIP_DIARKRITE.get(), () -> Ingredient.of(EItemTags.REPAIRS_DIARKRITE_EQUIPMENT)),
        ANTHEKTITE("anthektite", EConfig.COMMON.ANTHEKTITE_ARMOR, SoundEvents.ARMOR_EQUIP_NETHERITE, () -> Ingredient.of(EItemTags.REPAIRS_ANTHEKTITE_EQUIPMENT)),
        CATALYST("catalyst", EConfig.COMMON.ANTHEKTITE_ARMOR, SoundEvents.ARMOR_EQUIP_NETHERITE, () -> Ingredient.of(EItemTags.REPAIRS_CATALYST_ARMOR));


        private final String name;
        private final int durabilityMultiplier;
        private final EnumMap<ArmorItem.Type, Integer> armor;
        private final int enchantmentValue;
        private final SoundEvent sound;
        private final float toughness;
        private final float knockbackResistance;
        private final float attackSpeed;
        private final float movementSpeed;
        private final Supplier<Ingredient> repairIngredient;
//        private final Map<Attribute, AttributeModifier> additionalAttributes;

//        EnumArmorMaterials(String materialName, int durabilityMultiplier, EnumMap<ArmorItem.Type, Integer> armor, int enchantmentValue, SoundEvent soundEvent, float toughness, float knockbackResistance, Supplier<Ingredient> ingredient, Map<Attribute, AttributeModifier> additionalAttributes) {
//            this.materialName = materialName;
//            this.durabilityMultiplier = durabilityMultiplier;
//            this.armor = armor;
//            this.enchantmentValue = enchantmentValue;
//            this.sound = soundEvent;
//            this.toughness = toughness;
//            this.knockbackResistance = knockbackResistance;
//            this.ingredient = Suppliers.memoize(ingredient::get);
//            this.additionalAttributes = additionalAttributes;
//        }
        EnumArmorMaterials(String materialName, ArmorMaterialConfig config, SoundEvent soundEvent, Supplier<Ingredient> ingredient) {
            name = materialName;
            durabilityMultiplier = config.getDurability();
            armor = config.getArmor();
            enchantmentValue = config.getEnchantability();
            sound = soundEvent;
            toughness = (float) config.getToughness();
            knockbackResistance = (float) config.getKnockback();
            attackSpeed = (float) config.getAttackSpeed();
            movementSpeed = (float) config.getMovementSpeed();
            repairIngredient = Suppliers.memoize(ingredient::get);
//            this.additionalAttributes = additionalAttributes;
        }

        public static final EnumMap<ArmorItem.Type, Integer> HEALTH_FUNCTION_FOR_TYPE = Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
            map.put(ArmorItem.Type.BOOTS, 13);
            map.put(ArmorItem.Type.LEGGINGS, 15);
            map.put(ArmorItem.Type.CHESTPLATE, 16);
            map.put(ArmorItem.Type.HELMET, 11);
        });

        public int getDurabilityForType(ArmorItem.@NotNull Type typeDurability) { return HEALTH_FUNCTION_FOR_TYPE.get(typeDurability) * durabilityMultiplier; }

        public int getDefenseForType(ArmorItem.@NotNull Type typeDefense) { return armor.get(typeDefense); }
        public int getEnchantmentValue() { return enchantmentValue; }
        public @NotNull SoundEvent getEquipSound() { return sound; }
        public @NotNull Ingredient getRepairIngredient() { return repairIngredient.get(); }
        public @NotNull String getName() { return name; }
        public float getToughness() { return toughness; }
        public float getKnockbackResistance() { return knockbackResistance; }
        public float getAttackSpeed() { return attackSpeed; }
        public float getMovementSpeed() { return movementSpeed; }
//        public Map<Attribute, AttributeModifier> getAdditionalAttributes() { return additionalAttributes; }
    }
}
