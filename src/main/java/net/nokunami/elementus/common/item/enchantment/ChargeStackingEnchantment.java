package net.nokunami.elementus.common.item.enchantment;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.nokunami.elementus.config.EnchantmentConfig;
import net.nokunami.elementus.common.item.unique.ChargeBladeItem;
import net.nokunami.elementus.common.registry.EEnchantments;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static net.nokunami.elementus.config.EConfig.enchantmentConfig;
import static net.nokunami.elementus.common.item.unique.ChargeBladeItem.getMaxCharge;

public class ChargeStackingEnchantment extends Enchantment implements IChargeBladeEnchantment {
    EnchantmentConfig.baseEnchantConfig properties = enchantmentConfig.chargeStacking.properties;

    public static ChargeStackingEnchantment init() {
        return new ChargeStackingEnchantment(Enchantment.Rarity.VERY_RARE, EEnchantments.CHARGE_BLADE, EquipmentSlot.MAINHAND);
    }

    public ChargeStackingEnchantment(Rarity rarity, EnchantmentCategory category, EquipmentSlot... applicableSlots) {
        super(rarity, category, applicableSlots);
    }

    @Override public boolean isTreasureOnly() { return properties.isTreasure; }
    @Override public boolean isCurse() { return properties.isCurse; }
    @Override public boolean isTradeable() { return properties.isTradable; }
    @Override public boolean isDiscoverable() { return properties.isDiscoverable; }
    @Override public boolean isAllowedOnBooks() { return properties.isAllowedOnBooks; }

    @Override public int getMaxLevel() { return enchantmentConfig.chargeStacking.level.get(); }

    @Override public boolean canEnchant(@NotNull ItemStack stack) { return stack.getItem() instanceof ChargeBladeItem; }

    @Override
    public void getDescription(List<Component> tooltip, ItemStack stack) {
        if (stack.getItem() instanceof ChargeBladeItem cbi) {
            tooltip.add(Component.literal("| ").withStyle(ChatFormatting.GRAY)
                    .append(Component.translatable(getDescriptionId() + ".bonus_1_desc",
                                    Math.round(((float) (getMaxCharge(stack) / cbi.getChargeStack(stack)) - 1) * 100),
                                    Component.translatable(getDescriptionId()))
                            .withStyle(ChatFormatting.GREEN)));
            tooltip.add(Component.literal("| ").withStyle(ChatFormatting.GRAY)
                    .append(Component.translatable(getDescriptionId() + ".bonus_2_desc",
                                    Math.round(((float) (10 / 5) - 1) * 100),
                                    Component.translatable(getDescriptionId()))
                            .withStyle(ChatFormatting.GREEN)));
        }
    }
}