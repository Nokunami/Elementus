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
import net.nokunami.elementus.config.EConfig;
import net.nokunami.elementus.config.preset.IConfigArmor;
import net.nokunami.elementus.common.tags.EItemTags;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

public enum EArmorMaterials implements ArmorMaterial {
    ASTALITE("astalite", EConfig.armorConfig.astalite, SoundEvents.ARMOR_EQUIP_IRON, () -> Ingredient.of(EItemTags.REPAIRS_ASTALITE_EQUIPMENT)),
    DIARKRITE("diarkrite", EConfig.armorConfig.diarkrite, SoundEvents.ARMOR_EQUIP_NETHERITE, () -> Ingredient.of(EItemTags.REPAIRS_DIARKRITE_EQUIPMENT)),
    ANTHEKTITE("anthektite", EConfig.armorConfig.anthektite, SoundEvents.ARMOR_EQUIP_NETHERITE, () -> Ingredient.of(EItemTags.REPAIRS_ANTHEKTITE_EQUIPMENT)),
    CATALYST("catalyst", EConfig.catalystConfig.baseCatalyst, SoundEvents.ARMOR_EQUIP_NETHERITE, () -> Ingredient.of(EItemTags.REPAIRS_CATALYST_ARMOR)),
    SHULKER("shulker", EConfig.armorConfig.leavi_pack, SoundEvents.ARMOR_EQUIP_NETHERITE, () -> Ingredient.of(EItemTags.REPAIRS_LEAVI_PACK));

    private final String name;
    private final int durabilityMultiplier;
    private final EnumMap<ArmorItem.Type, Integer> armor;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredient;
    private final Map<Attribute, AttributeModifier> additionalAttributes;

    EArmorMaterials(String materialName, IConfigArmor config, SoundEvent soundEvent, Supplier<Ingredient> ingredient) {
        name = materialName;
        durabilityMultiplier = config.durability();
        armor = config.getArmor();
        enchantmentValue = config.enchantability();
        sound = soundEvent;
        toughness = (float) config.toughness();
        knockbackResistance = (float) config.knockbackRes();
        repairIngredient = Suppliers.memoize(ingredient::get);
        additionalAttributes = Map.of(
                Attributes.ATTACK_SPEED, new AttributeModifier("Armor Attack Speed Modifier", config.attackSpeed(), AttributeModifier.Operation.MULTIPLY_BASE),
                Attributes.MOVEMENT_SPEED, new AttributeModifier("Armor Movement Speed Modifier", config.movementSpeed(), AttributeModifier.Operation.MULTIPLY_BASE)
        );
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
    public Map<Attribute, AttributeModifier> getAdditionalAttributes() { return additionalAttributes; }
}