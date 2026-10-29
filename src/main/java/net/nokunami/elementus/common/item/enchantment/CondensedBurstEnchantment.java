package net.nokunami.elementus.common.item.enchantment;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.nokunami.elementus.config.EnchantmentConfig;
import net.nokunami.elementus.common.item.unique.BladeOfResonance;
import net.nokunami.elementus.common.registry.EEnchantments;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static net.nokunami.elementus.config.EConfig.BOR;
import static net.nokunami.elementus.config.EConfig.enchantmentConfig;

public class CondensedBurstEnchantment extends Enchantment implements IChargeBladeEnchantment {
    EnchantmentConfig.baseEnchantConfig properties = enchantmentConfig.condensedBurst.properties;

    public static CondensedBurstEnchantment init() {
        return new CondensedBurstEnchantment(Rarity.RARE, EEnchantments.CHARGE_BLADE, EquipmentSlot.MAINHAND);
    }

    public CondensedBurstEnchantment(Rarity rarity, EnchantmentCategory category, EquipmentSlot... applicableSlots) {
        super(rarity, category, applicableSlots);
    }

    @Override public boolean isTreasureOnly() { return properties.isTreasure; }
    @Override public boolean isCurse() { return properties.isCurse; }
    @Override public boolean isTradeable() { return properties.isTradable; }
    @Override public boolean isDiscoverable() { return properties.isDiscoverable; }
    @Override public boolean isAllowedOnBooks() { return properties.isAllowedOnBooks; }

    //    @Override public boolean canEnchant(@NotNull ItemStack stack) { return stack.is(EItems.DIARKRITE_CHARGE_BLADE.get()); }
    @Override public boolean canEnchant(@NotNull ItemStack stack) { return stack.getItem() instanceof BladeOfResonance; }

    @Override
    public void getDescription(List<Component> tooltip, ItemStack stack) {
        tooltip.add(Component.literal("| ").withStyle(ChatFormatting.GRAY)
                .append(Component.translatable(getDescriptionId() + ".penalty_desc",
//                        Math.round((((float) diarkriteChargeBladeChargePenalty / diarkriteChargeBladeBaseCharge) - 1) * 100),
                        Math.round((((float) enchantmentConfig.condensedBurst.chargePenalty / BOR.baseCharge) - 1) * 100),
                        Component.translatable(getDescriptionId()))
                        .withStyle(ChatFormatting.RED)));
    }
}