package net.nokunami.elementus.config.preset;

import net.minecraft.world.item.ArmorItem;

import java.util.EnumMap;

public interface IConfigArmor {
    EnumMap<ArmorItem.Type, Integer> getArmor();
    int enchantability();
    int durability();
    double toughness();
    double knockbackRes();
    double attackSpeed();
    double movementSpeed();
}
