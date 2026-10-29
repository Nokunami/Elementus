package net.nokunami.elementus.common.item.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.DamageEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.nokunami.elementus.config.EConfig;
import net.nokunami.elementus.config.EnchantmentConfig;
import net.nokunami.elementus.common.tags.EItemTags;
import org.jetbrains.annotations.NotNull;

import static net.nokunami.elementus.config.EConfig.enchantmentConfig;

public class ArcaneSharpnessEnchantment extends Enchantment {
    private final EnchantmentConfig.baseEnchantConfig properties = enchantmentConfig.arcaneSharpness.properties;

    public static ArcaneSharpnessEnchantment init() {
        return new ArcaneSharpnessEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentCategory.WEAPON, EquipmentSlot.MAINHAND);
    }

    public ArcaneSharpnessEnchantment(Rarity rarity, EnchantmentCategory category, EquipmentSlot... applicableSlots) {
        super(rarity, category, applicableSlots);
    }

    @Override
    public float getDamageBonus(int level, MobType mobType, ItemStack enchantedItem) {
        return (float) (enchantedItem.getEnchantmentValue() * EConfig.enchantmentConfig.arcaneSharpness.damagePercent);
    }

    @Override public boolean isTreasureOnly() { return properties.isTreasure; }
    @Override public boolean isCurse() { return properties.isCurse; }
    @Override public boolean isTradeable() { return properties.isTradable; }
    @Override public boolean isDiscoverable() { return properties.isDiscoverable; }
    @Override public boolean isAllowedOnBooks() { return properties.isAllowedOnBooks; }

    public boolean checkCompatibility(@NotNull Enchantment ench) {
        if (EConfig.enchantmentConfig.arcaneSharpness.damageEnchantmentIncompatibility) {
            return !(ench instanceof DamageEnchantment);
        }
        return false;
    }

    @Override
    public boolean canEnchant(@NotNull ItemStack stack) {
        return stack.getItem() instanceof AxeItem || stack.is(EItemTags.ARCANE_SHARPNESS_COMPATIBLE) || super.canEnchant(stack);
    }
}