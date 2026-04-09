package net.nokunami.elementus.common.config.configSets.armor;

import net.minecraft.Util;
import net.minecraft.world.item.ArmorItem;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.EnumMap;

import static net.nokunami.elementus.common.config.EConfig.PREFIX;

public class CatalystArmorMaterialConfig {
    public final ForgeConfigSpec.IntValue armor;
    public final ForgeConfigSpec.BooleanValue hasDurability;
    public final ArmorAttributesConfig attributesConfig;
    
    public static int chestplate = 10;
    public static int durabilityMultiplier = 40;
    public static int enchantability = 20;
    public static double toughness = 4;
    public static double knockback = 0.1;
    public static double attackSpeed = 0;
    public static double movementSpeed = 0;

    public CatalystArmorMaterialConfig(final ForgeConfigSpec.Builder builder) {
        hasDurability = builder.translation(PREFIX + "catalyst.has_durability").define("catalyst.has_durability", false);
        armor = builder.translation(PREFIX + "catalyst.armor")
                .defineInRange("catalyst.armor", chestplate, Integer.MIN_VALUE, Integer.MAX_VALUE);
        attributesConfig = new ArmorAttributesConfig(builder, "catalyst", "catalyst", durabilityMultiplier, enchantability, toughness, knockback, attackSpeed, movementSpeed);
    }

    public EnumMap<ArmorItem.Type, Integer> getArmor() {
        return Util.make(new EnumMap<>(ArmorItem.Type.class), a -> {
            a.put(ArmorItem.Type.BOOTS, 0);
            a.put(ArmorItem.Type.LEGGINGS, 0);
            a.put(ArmorItem.Type.CHESTPLATE, chestplate);
            a.put(ArmorItem.Type.HELMET, 0);
        });
    }
    public int getDurability() { return durabilityMultiplier; }
    public int getEnchantability() { return enchantability; }
    public double getToughness() { return toughness; }
    public double getKnockback() { return knockback; }
    public double getAttackSpeed() { return attackSpeed; }
    public double getMovementSpeed() { return movementSpeed; }
}