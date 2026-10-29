package net.nokunami.elementus.config.preset;

import me.fzzyhmstrs.fzzy_config.config.ConfigGroup;
import me.fzzyhmstrs.fzzy_config.config.ConfigSection;
import net.minecraft.Util;
import net.minecraft.world.item.ArmorItem;

import java.util.EnumMap;

public class EArmorConfig extends ConfigSection implements IConfigArmor {

    public ConfigGroup armor = new ConfigGroup("armor_slot");
    public int helmet;
    public int chestplate;
    public int leggings;
    @ConfigGroup.Pop
    public int boots;

    public int enchantability;
    public int durability;
    public double toughness;
    public double knockbackRes;
    public double attackSpeed;
    public double movementSpeed;

    public EArmorConfig(
            int helmetArmor, int chestplateArmor, int leggingsArmor, int bootsArmor,
            int enchantValue, int durabilityForType, double armorToughness,
            double knockbackResistance, double attackSpeedBonus,
            double movementSpeedBonus
    ) {
        helmet = helmetArmor;
        chestplate = chestplateArmor;
        leggings = leggingsArmor;
        boots = bootsArmor;
        enchantability = enchantValue;
        durability = durabilityForType;
        toughness = armorToughness;
        knockbackRes = knockbackResistance;
        attackSpeed = attackSpeedBonus;
        movementSpeed = movementSpeedBonus;
    }

    @Override
    public EnumMap<ArmorItem.Type, Integer> getArmor() {
        return Util.make(new EnumMap<>(ArmorItem.Type.class), c -> {
            c.put(ArmorItem.Type.HELMET, helmet);
            c.put(ArmorItem.Type.CHESTPLATE, chestplate);
            c.put(ArmorItem.Type.LEGGINGS, leggings);
            c.put(ArmorItem.Type.BOOTS, boots);
        });
    }
    @Override public int enchantability() { return enchantability; }
    @Override public int durability() { return durability; }
    @Override public double toughness() { return toughness; }
    @Override public double knockbackRes() { return knockbackRes; }
    @Override public double attackSpeed() { return attackSpeed; }
    @Override public double movementSpeed() { return movementSpeed; }
}