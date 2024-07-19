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
import net.nokunami.elementus.common.compat.advancednetherite.ANModItems;
import net.nokunami.elementus.common.config.ANConfig;
import net.nokunami.elementus.common.config.BaseConfig;
import net.nokunami.elementus.common.registry.ModItems;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

public enum MagicArmorMaterial1 implements ArmorMaterial {

    DIARKRITE_MAGE("diarkrite_mage",  BaseConfig.diarkriteMageArmor_DurabilityForType, Util.make(new EnumMap<>(ArmorItem.Type.class), (c) -> {
        c.put(ArmorItem.Type.BOOTS, BaseConfig.diarkriteMageArmor_Boots);c.put(ArmorItem.Type.LEGGINGS, BaseConfig.diarkriteMageArmor_Leggings);c.put(ArmorItem.Type.CHESTPLATE, BaseConfig.diarkriteMageArmor_Chestplate);c.put(ArmorItem.Type.HELMET, BaseConfig.diarkriteMageArmor_Helmet);
    }), BaseConfig.diarkriteMageArmor_Enchantability, SoundEvents.ARMOR_EQUIP_NETHERITE,
            BaseConfig.diarkriteMageArmor_Toughness, BaseConfig.diarkriteMageArmor_KnockbackResistance, () -> Ingredient.of(ModItems.DIARKRITE_INGOT.get()), Map.of(
            Attributes.ATTACK_SPEED, new AttributeModifier("Armor Attack Speed Modifier", BaseConfig.diarkriteMageArmor_AttackSpeed, AttributeModifier.Operation.MULTIPLY_BASE),
            Attributes.MOVEMENT_SPEED, new AttributeModifier("Armor Movement Speed Modifier", BaseConfig.diarkriteMageArmor_MovementSpeed, AttributeModifier.Operation.MULTIPLY_BASE),
            AttributeRegistry.MAX_MANA.get(), new AttributeModifier("Mage Max Mana Modifier", BaseConfig.diarkriteMageArmor_MaxMana, AttributeModifier.Operation.ADDITION),
            AttributeRegistry.MANA_REGEN.get(), new AttributeModifier("Mage Mana Regen Modifier", BaseConfig.diarkriteMageArmor_ManaRegen, AttributeModifier.Operation.MULTIPLY_BASE),
            AttributeRegistry.SPELL_POWER.get(), new AttributeModifier("Mage Spell Power Modifier", BaseConfig.diarkriteMageArmor_SpellPower, AttributeModifier.Operation.MULTIPLY_BASE),
            AttributeRegistry.SPELL_RESIST.get(), new AttributeModifier("Mage Spell Resist Modifier", BaseConfig.diarkriteMageArmor_SpellResist, AttributeModifier.Operation.MULTIPLY_BASE),
            AttributeRegistry.COOLDOWN_REDUCTION.get(), new AttributeModifier("Mage Cooldown Reduction Modifier", BaseConfig.diarkriteMageArmor_Cooldown, AttributeModifier.Operation.MULTIPLY_BASE),
            AttributeRegistry.CAST_TIME_REDUCTION.get(), new AttributeModifier("Mage Cast Time Reduction Modifier", BaseConfig.diarkriteMageArmor_CastTime, AttributeModifier.Operation.MULTIPLY_BASE))),

    ANTHEKTITE_MAGE("anthektite_mage", BaseConfig.anthektiteMageArmor_DurabilityForType, Util.make(new EnumMap<>(ArmorItem.Type.class), (c) -> {
        c.put(ArmorItem.Type.BOOTS, BaseConfig.anthektiteMageArmor_Boots);c.put(ArmorItem.Type.LEGGINGS, BaseConfig.anthektiteMageArmor_Leggings);c.put(ArmorItem.Type.CHESTPLATE, BaseConfig.anthektiteMageArmor_Chestplate);c.put(ArmorItem.Type.HELMET, BaseConfig.anthektiteMageArmor_Helmet);
    }), BaseConfig.anthektiteMageArmor_Enchantability, SoundEvents.ARMOR_EQUIP_NETHERITE,
            BaseConfig.anthektiteMageArmor_Toughness, BaseConfig.anthektiteMageArmor_KnockbackResistance, () -> Ingredient.of(ModItems.ANTHEKTITE_INGOT.get()), Map.of(
            Attributes.ATTACK_SPEED, new AttributeModifier("Armor Attack Speed Modifier", BaseConfig.anthektiteMageArmor_AttackSpeed, AttributeModifier.Operation.MULTIPLY_BASE),
            Attributes.MOVEMENT_SPEED, new AttributeModifier("Armor Movement Speed Modifier", BaseConfig.anthektiteMageArmor_MovementSpeed, AttributeModifier.Operation.MULTIPLY_BASE),
            AttributeRegistry.MAX_MANA.get(), new AttributeModifier("Mage Max Mana Modifier", BaseConfig.anthektiteMageArmor_MaxMana, AttributeModifier.Operation.ADDITION),
            AttributeRegistry.MANA_REGEN.get(), new AttributeModifier("Mage Mana Regen Modifier", BaseConfig.anthektiteMageArmor_ManaRegen, AttributeModifier.Operation.MULTIPLY_BASE),
            AttributeRegistry.SPELL_POWER.get(), new AttributeModifier("Mage Spell Power Modifier", BaseConfig.anthektiteMageArmor_SpellPower, AttributeModifier.Operation.MULTIPLY_BASE),
            AttributeRegistry.SPELL_RESIST.get(), new AttributeModifier("Mage Spell Resist Modifier", BaseConfig.anthektiteMageArmor_SpellResist, AttributeModifier.Operation.MULTIPLY_BASE),
            AttributeRegistry.COOLDOWN_REDUCTION.get(), new AttributeModifier("Mage Cooldown Reduction Modifier", BaseConfig.anthektiteMageArmor_Cooldown, AttributeModifier.Operation.MULTIPLY_BASE),
            AttributeRegistry.CAST_TIME_REDUCTION.get(), new AttributeModifier("Mage Cast Time Reduction Modifier", BaseConfig.anthektiteMageArmor_CastTime, AttributeModifier.Operation.MULTIPLY_BASE)));


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

    MagicArmorMaterial1(String name, int durabilityMultiplier, EnumMap<ArmorItem.Type, Integer> armor, int enchantmentValue, SoundEvent soundEvent, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient, Map<Attribute, AttributeModifier> additionalAttributes) {
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