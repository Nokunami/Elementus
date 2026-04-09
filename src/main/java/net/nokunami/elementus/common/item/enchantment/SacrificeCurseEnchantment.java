package net.nokunami.elementus.common.item.enchantment;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.nokunami.elementus.common.registry.EEnchantments;
import net.nokunami.elementus.common.registry.EItems;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static net.nokunami.elementus.common.config.UniqueItemConfig.diarkriteChargeBladeSacrificeDamageBonus;
import static net.nokunami.elementus.common.config.UniqueItemConfig.diarkriteChargeBladeSelfSacrificeDamage;

public class SacrificeCurseEnchantment extends Enchantment implements IChargeBladeEnchantment {

    public static SacrificeCurseEnchantment init() {
        return new SacrificeCurseEnchantment(Enchantment.Rarity.VERY_RARE, EEnchantments.CHARGE_BLADE, EquipmentSlot.MAINHAND);
    }

    public SacrificeCurseEnchantment(Rarity rarity, EnchantmentCategory category, EquipmentSlot... applicableSlots) {
        super(rarity, category, applicableSlots);
    }

    @Override public boolean isCurse() { return true; }

    @Override
    protected boolean checkCompatibility(@NotNull Enchantment ench) {
        return !(ench instanceof ChargeStackingEnchantment) && super.checkCompatibility(ench);
    }

    @Override
    public boolean canEnchant(@NotNull ItemStack pStack) { return pStack.is(EItems.DIARKRITE_CHARGE_BLADE.get()); }

    @Override
    public void getDescription(List<Component> tooltip, ItemStack stack) {
        boolean hasBonus = !(diarkriteChargeBladeSacrificeDamageBonus <= 0);
        boolean hasPenalty = !(diarkriteChargeBladeSelfSacrificeDamage <= 0);
        if (hasBonus) tooltip.add(bonusComponent().copy());
        if (hasPenalty) tooltip.add(penaltyComponent().copy());
    }

    Component bonusComponent() {
        return Component.literal("| ").withStyle(ChatFormatting.GRAY)
                .append(Component.translatable(getDescriptionId() + ".bonus_desc",
                        Math.round((diarkriteChargeBladeSacrificeDamageBonus + 1) * 100),
                        Component.translatable(getDescriptionId()))
                        .withStyle(ChatFormatting.YELLOW));
    }
    Component penaltyComponent() {
        return Component.literal("| ").withStyle(ChatFormatting.GRAY)
                .append(Component.translatable(getDescriptionId() + ".penalty_desc",
                        Math.round(diarkriteChargeBladeSelfSacrificeDamage * 100),
                        Component.translatable(getDescriptionId()))
                        .withStyle(ChatFormatting.RED));
    }
}