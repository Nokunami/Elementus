package net.nokunami.elementus.common.registry;

import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
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
import net.nokunami.elementus.common.compat.advancednetherite.ANModItems;
import net.nokunami.elementus.common.config.ServerConfig;

import java.util.EnumMap;
import java.util.UUID;
import java.util.function.Supplier;

public enum ModArmorMaterials implements ArmorMaterial {

    STEEL("steel", ServerConfig.STEEL_ARMOR_CONFIG, 24, 10, SoundEvents.ARMOR_EQUIP_IRON,
            () -> Ingredient.of(ModItems.STEEL_INGOT.get())),

    DIARKRITE("diarkrite",  ServerConfig.DIARKRITE_ARMOR_CONFIG, 38, 18, SoundEvents.ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.of(ModItems.DIARKRITE_INGOT.get())),

    ANTHEKTITE("anthektite", ServerConfig.ANTHEKTITE_ARMOR_CONFIG, 35, 15, SoundEvents.ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.of(ModItems.ANTHEKTITE_INGOT.get())),



    DIARKRITE_IRON("diarkrite_iron",  ServerConfig.DIARKRITE_IRON_ARMOR_CONFIG, 40, 18, SoundEvents.ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.of(ANModItems.DIARKRITE_IRON.get())),

    DIARKRITE_GOLD("diarkrite_gold",  ServerConfig.DIARKRITE_GOLD_ARMOR_CONFIG, 42, 28, SoundEvents.ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.of(ANModItems.DIARKRITE_GOLD.get())),

    DIARKRITE_EMERALD("diarkrite_emerald",  ServerConfig.DIARKRITE_EMERALD_ARMOR_CONFIG, 44, 23, SoundEvents.ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.of(ANModItems.DIARKRITE_EMERALD.get())),

    DIARKRITE_DIAMOND("diarkrite_diamond",  ServerConfig.DIARKRITE_DIAMOND_ARMOR_CONFIG, 48, 18, SoundEvents.ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.of(ANModItems.DIARKRITE_DIAMOND.get())),


    ANTHEKTITE_IRON("anthektite_iron", ServerConfig.ANTHEKTITE_IRON_ARMOR_CONFIG, 37, 15, SoundEvents.ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.of(ANModItems.ANTHEKTITE_IRON.get())),

    ANTHEKTITE_GOLD("anthektite_gold", ServerConfig.ANTHEKTITE_GOLD_ARMOR_CONFIG, 39, 25, SoundEvents.ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.of(ANModItems.ANTHEKTITE_GOLD.get())),

    ANTHEKTITE_EMERALD("anthektite_emerald", ServerConfig.ANTHEKTITE_EMERALD_ARMOR_CONFIG, 41, 20, SoundEvents.ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.of(ANModItems.ANTHEKTITE_EMERALD.get())),

    ANTHEKTITE_DIAMOND("anthektite_diamond", ServerConfig.ANTHEKTITE_DIAMOND_ARMOR_CONFIG, 45, 15, SoundEvents.ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.of(ANModItems.ANTHEKTITE_DIAMOND.get())),



    STEEL_SAMURAI("steel_samurai", ServerConfig.STEEL_SAMURAI_ARMOR_CONFIG, 24, 10, SoundEvents.ARMOR_EQUIP_IRON,
            () -> Ingredient.of(ModItems.STEEL_INGOT.get())),

    STEEL_SAMURAI_LIGHT("steel_samurai_light", ServerConfig.STEEL_SAMURAI_LIGHT_ARMOR_CONFIG, 24, 10, SoundEvents.ARMOR_EQUIP_IRON,
            () -> Ingredient.of(ModItems.STEEL_INGOT.get())),

    STEEL_SAMURAI_MASTER("steel_samurai_master", ServerConfig.STEEL_SAMURAI_MASTER_ARMOR_CONFIG, 24, 10, SoundEvents.ARMOR_EQUIP_IRON,
            () -> Ingredient.of(ModItems.STEEL_INGOT.get())),


    DIARKRITE_SAMURAI("diarkrite_samurai",  ServerConfig.DIARKRITE_SAMURAI_ARMOR_CONFIG, 38, 18, SoundEvents.ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.of(ModItems.DIARKRITE_INGOT.get())),

    DIARKRITE_SAMURAI_LIGHT("diarkrite_samurai_light",  ServerConfig.DIARKRITE_SAMURAI_LIGHT_ARMOR_CONFIG, 38, 18, SoundEvents.ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.of(ModItems.DIARKRITE_INGOT.get())),

    DIARKRITE_SAMURAI_MASTER("diarkrite_samurai_master",  ServerConfig.DIARKRITE_SAMURAI_MASTER_ARMOR_CONFIG, 38, 18, SoundEvents.ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.of(ModItems.DIARKRITE_INGOT.get())),


    ANTHEKTITE_SAMURAI("anthektite_samurai", ServerConfig.ANTHEKTITE_SAMURAI_ARMOR_CONFIG, 35, 15, SoundEvents.ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.of(ModItems.ANTHEKTITE_INGOT.get())),

    ANTHEKTITE_SAMURAI_LIGHT("anthektite_samurai_light", ServerConfig.ANTHEKTITE_SAMURAI_LIGHT_ARMOR_CONFIG, 35, 15, SoundEvents.ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.of(ModItems.ANTHEKTITE_INGOT.get())),

    ANTHEKTITE_SAMURAI_MASTER("anthektite_samurai_master", ServerConfig.ANTHEKTITE_SAMURAI_MASTER_ARMOR_CONFIG, 35, 15, SoundEvents.ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.of(ModItems.ANTHEKTITE_INGOT.get()));

//    private static final int[] HEALTH_PER_SLOT = new int[]{13,15,16,11};
    private final String name;
    private final int durabilityMultiplier;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final Supplier<Ingredient> repairIngredient;
    private final ServerConfig.ArmorSetConfig config;

    ModArmorMaterials(String name, ServerConfig.ArmorSetConfig config, int durabilityMultiplier, int enchantmentValue, SoundEvent soundEvent, Supplier<Ingredient> repairIngredient) {
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
        return builder.build();
    }

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