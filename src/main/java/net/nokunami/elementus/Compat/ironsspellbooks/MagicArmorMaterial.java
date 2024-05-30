package net.nokunami.elementus.compat.ironsspellbooks;

import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.minecraft.Util;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.nokunami.elementus.ServerConfig;
import net.nokunami.elementus.registry.ModItems;

import java.util.EnumMap;
import java.util.UUID;
import java.util.function.Supplier;

public enum MagicArmorMaterial implements ArmorMaterial {

    ANTHEKTITE_MAGE("anthektite_mage", ServerConfig.ANTHEKTITE_WIZARD_ARMOR_CONFIG, 38, 15, SoundEvents.ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.of(ModItems.ANTHEKTITE_INGOT.get())),
    DIARKRITE_MAGE("diarkrite_mage",  ServerConfig.DIARKRITE_WIZARD_ARMOR_CONFIG, 38, 18, SoundEvents.ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.of(ModItems.DIARKRITE_INGOT.get()));

    private static final int[] HEALTH_PER_SLOT = new int[]{3,6,8,3};
    private final String name;
    private final int durabilityMultiplier;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final Supplier<Ingredient> repairIngredient;
    private final ServerConfig.ISSArmorConfig config;

    MagicArmorMaterial(String name, ServerConfig.ISSArmorConfig config, int durabilityMultiplier, int enchantmentValue, SoundEvent soundEvent, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.enchantmentValue = enchantmentValue;
        this.sound = soundEvent;
        this.repairIngredient = Suppliers.memoize(repairIngredient::get);
        this.config = config;
        slotToAttributeMap = null;
    }

    private EnumMap<EquipmentSlot, Multimap<Attribute, AttributeModifier>> slotToAttributeMap;

    public EnumMap<EquipmentSlot, Multimap<Attribute, AttributeModifier>> getSlotToAttributeMap() {
        if (slotToAttributeMap == null) {
            slotToAttributeMap = makeSlotToAttributeMap();
        }
        return slotToAttributeMap;
    }

    private EnumMap<EquipmentSlot, Multimap<Attribute, AttributeModifier>> makeSlotToAttributeMap() {
        return Util.make(new EnumMap<>(EquipmentSlot.class), (map) -> {
            map.put(EquipmentSlot.FEET, makeAttributeMap(EquipmentSlot.FEET));
            map.put(EquipmentSlot.LEGS, makeAttributeMap(EquipmentSlot.LEGS));
            map.put(EquipmentSlot.CHEST, makeAttributeMap(EquipmentSlot.CHEST));
            map.put(EquipmentSlot.HEAD, makeAttributeMap(EquipmentSlot.HEAD));
        });
    }

    private static final UUID[] ARMOR_MODIFIER_UUID_PER_SLOT = new UUID[]{UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"), UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"), UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"), UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150")};

    public void reload() {
        this.slotToAttributeMap = null;
    }

    private Multimap<Attribute, AttributeModifier> makeAttributeMap(EquipmentSlot slot) {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        UUID uuid = ARMOR_MODIFIER_UUID_PER_SLOT[slot.getIndex()];
        double defense = this.config.getDefenseFor(slot);
        double toughness = this.config.toughness().get();
        double knockbackResistance = this.config.knockbackResistance().get();
        float speedBoost = this.config.speedBoost().get();
        float atkSpeedBoost = this.config.atkSpeedBoost().get();
        double maxMana = this.config.maxMana().get();
        double manaRegen = this.config.manaRegen().get();
        double spellPower = this.config.spellPower().get();
        double spellResist = this.config.spellResist().get();
        double castTime = this.config.castTime().get();
        double cooldown = this.config.cooldown().get();
        if (defense != 0) {
            builder.put(Attributes.ARMOR, new AttributeModifier(uuid, "Armor modifier", defense, AttributeModifier.Operation.ADDITION));
        }
        if (toughness != 0) {
            builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(uuid, "Armor toughness", toughness, AttributeModifier.Operation.ADDITION));
        }
        if (knockbackResistance != 0) {
            builder.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(uuid, "Armor  knockback resistance", knockbackResistance, AttributeModifier.Operation.ADDITION));
        }
        if (speedBoost != 0) {
            builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(uuid, "Armor Movement Speed", speedBoost, AttributeModifier.Operation.MULTIPLY_BASE));
        }
        if (atkSpeedBoost != 0) {
            builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(uuid, "Armor Attack Speed Bonus", atkSpeedBoost, AttributeModifier.Operation.MULTIPLY_BASE));
        }
        if (maxMana != 0) {
            builder.put(AttributeRegistry.MAX_MANA.get(), new AttributeModifier(uuid, "Armor Max Mana", maxMana, AttributeModifier.Operation.ADDITION));
        }
        if (manaRegen != 0) {
            builder.put(AttributeRegistry.MANA_REGEN.get(), new AttributeModifier(uuid, "Armor Mana Regen", manaRegen, AttributeModifier.Operation.MULTIPLY_BASE));
        }
        if (spellPower != 0) {
            builder.put(AttributeRegistry.SPELL_POWER.get(), new AttributeModifier(uuid, "Armor Spell Power", spellPower, AttributeModifier.Operation.MULTIPLY_BASE));
        }
        if (spellResist != 0) {
            builder.put(AttributeRegistry.SPELL_RESIST.get(), new AttributeModifier(uuid, "Armor Spell Resistance", spellResist, AttributeModifier.Operation.ADDITION));
        }
        if (castTime != 0) {
            builder.put(AttributeRegistry.CAST_TIME_REDUCTION.get(), new AttributeModifier(uuid, "Armor Cast Time Reduction", castTime, AttributeModifier.Operation.MULTIPLY_BASE));
        }
        if (cooldown != 0) {
            builder.put(AttributeRegistry.COOLDOWN_REDUCTION.get(), new AttributeModifier(uuid, "Armor Cooldown Reduction", cooldown, AttributeModifier.Operation.MULTIPLY_BASE));
        }


        return builder.build();
    }

    static public EnumMap<ArmorItem.Type, Integer> makeArmorMap(int helmet, int chestplate, int leggings, int boots) {
        return Util.make(new EnumMap<>(ArmorItem.Type.class), (p_266655_) -> {
            p_266655_.put(ArmorItem.Type.BOOTS, boots);
            p_266655_.put(ArmorItem.Type.LEGGINGS, leggings);
            p_266655_.put(ArmorItem.Type.CHESTPLATE, chestplate);
            p_266655_.put(ArmorItem.Type.HELMET, helmet);
        });
    }


    public int getDurabilityForSlot(EquipmentSlot slot) {
        return HEALTH_PER_SLOT[slot.getIndex()] * this.durabilityMultiplier;
    }

    private static final EnumMap<ArmorItem.Type, Integer> HEALTH_FUNCTION_FOR_TYPE = Util.make(new EnumMap<>(ArmorItem.Type.class), (p_266653_) -> {
        p_266653_.put(ArmorItem.Type.BOOTS, 3);
        p_266653_.put(ArmorItem.Type.LEGGINGS, 6);
        p_266653_.put(ArmorItem.Type.CHESTPLATE, 8);
        p_266653_.put(ArmorItem.Type.HELMET, 3);
    });

    public int getDurabilityForType(ArmorItem.Type typeDurability) {
        return HEALTH_FUNCTION_FOR_TYPE.get(typeDurability) * this.durabilityMultiplier * 2;
    }

    public int getDefenseForType(ArmorItem.Type typeDefense) {
        return -1;
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
        return -1;
    }

    public float getKnockbackResistance() {
        return -1;
    }
}