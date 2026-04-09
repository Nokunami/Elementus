package net.nokunami.elementus.common.config.configSets.armor;

import net.minecraftforge.common.ForgeConfigSpec;

import static net.nokunami.elementus.common.config.EConfig.PREFIX;

public class ArmorAttributesConfig {
    public final ForgeConfigSpec.IntValue durabilityMultiplier;
    public final ForgeConfigSpec.IntValue enchantability;
    public final ForgeConfigSpec.DoubleValue toughness;
    public final ForgeConfigSpec.DoubleValue knockback;
    public final ForgeConfigSpec.DoubleValue attackSpeed;
    public final ForgeConfigSpec.DoubleValue movementSpeed;

    public ArmorAttributesConfig(final ForgeConfigSpec.Builder builder, String path, String id, int defaultDurability, int defaultEnchantability, double defaultToughness, double defaultKnockback, double defaultAttackSpeed, double defaultMovementSpeed) {
        durabilityMultiplier = builder.translation(PREFIX + id + ".armor_material.durability")
                .defineInRange(path + ".durability", defaultDurability, Integer.MIN_VALUE, Integer.MAX_VALUE);
        enchantability = builder.translation(PREFIX + id + ".armor_material.enchantability")
                .defineInRange(path + ".enchantability", defaultEnchantability, Integer.MIN_VALUE, Integer.MAX_VALUE);
        toughness = builder.translation(PREFIX + id + ".armor_material.toughness")
                .defineInRange(path + ".toughness", defaultToughness, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
        knockback = builder.translation(PREFIX + id + ".armor_material.knockback")
                .defineInRange(path + ".knockback", defaultKnockback, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
        attackSpeed = builder.translation(PREFIX + id + ".armor_material.attack_speed")
                .defineInRange(path + ".attack_speed", defaultAttackSpeed, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
        movementSpeed = builder.translation(PREFIX + id + ".armor_material.movement_speed")
                .defineInRange(path + ".movement_speed", defaultMovementSpeed, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
    }

    public void reload(ArmorAttributesConfig config) {
    }

//    public int getDefenseForType(ArmorItem.@NotNull Type typeDefense) { return getConfigAmor().get(typeDefense); }
//    private EnumMap<ArmorItem.Type, Integer> getConfigAmor() {
//        return Util.make(new EnumMap<>(ArmorItem.Type.class), a -> {
//            a.put(ArmorItem.Type.BOOTS, boots.get());
//            a.put(ArmorItem.Type.LEGGINGS, leggings.get());
//            a.put(ArmorItem.Type.CHESTPLATE, chestplate.get());
//            a.put(ArmorItem.Type.HELMET, helmet.get());
//        });
//    }

//    public EnumMap<ArmorItem.Type, Integer> getArmor();
}
