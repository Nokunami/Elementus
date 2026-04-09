package net.nokunami.elementus.common.config.configSets.armor;

import net.minecraft.Util;
import net.minecraft.world.item.ArmorItem;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.EnumMap;

public class AnthektiteArmorMaterialConfig extends ArmorMaterialConfig {
    public static int helmet = 3;
    public static int chestplate = 8;
    public static int leggings = 6;
    public static int boots = 3;
    public static int durabilityMultiplier = 35;
    public static int enchantability = 15;
    public static double toughness = 2;
    public static double knockback = 0.05;
    public static double attackSpeed = 0.1;
    public static double movementSpeed = 0;

    public AnthektiteArmorMaterialConfig(final ForgeConfigSpec.Builder builder) {
        super(builder, "anthektite", "anthektite", helmet, chestplate, leggings, boots, durabilityMultiplier, enchantability, toughness, knockback, attackSpeed, movementSpeed);
    }

    @Override
    public void reload(ArmorMaterialConfig config) {
        helmet = config.armorSetConfig.helmet.get();
        chestplate = config.armorSetConfig.chestplate.get();
        leggings = config.armorSetConfig.leggings.get();
        boots = config.armorSetConfig.boots.get();
        durabilityMultiplier = config.attributesConfig.durabilityMultiplier.get();
        enchantability = config.attributesConfig.enchantability.get();
        toughness = config.attributesConfig.toughness.get();
        knockback = config.attributesConfig.knockback.get();
        attackSpeed = config.attributesConfig.attackSpeed.get();
        movementSpeed = config.attributesConfig.movementSpeed.get();
    }

    @Override
    public EnumMap<ArmorItem.Type, Integer> getArmor() {
        return Util.make(new EnumMap<>(ArmorItem.Type.class), a -> {
            a.put(ArmorItem.Type.BOOTS, boots);
            a.put(ArmorItem.Type.LEGGINGS, leggings);
            a.put(ArmorItem.Type.CHESTPLATE, chestplate);
            a.put(ArmorItem.Type.HELMET, helmet);
        });
    }
    @Override public int getDurability() { return durabilityMultiplier; }
    @Override public int getEnchantability() { return enchantability; }
    @Override public double getToughness() { return toughness; }
    @Override public double getKnockback() { return knockback; }
    @Override public double getAttackSpeed() { return attackSpeed; }
    @Override public double getMovementSpeed() { return movementSpeed; }
}