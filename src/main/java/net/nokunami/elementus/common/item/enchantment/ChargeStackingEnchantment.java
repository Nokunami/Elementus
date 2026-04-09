package net.nokunami.elementus.common.item.enchantment;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.nokunami.elementus.common.item.unique.ChargeBladeItem;
import net.nokunami.elementus.common.registry.EEnchantments;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static net.nokunami.elementus.common.item.unique.ChargeBladeItem.getMaxCharge;

public class ChargeStackingEnchantment extends Enchantment implements IChargeBladeEnchantment {

    public static ChargeStackingEnchantment init() {
        return new ChargeStackingEnchantment(Enchantment.Rarity.VERY_RARE, EEnchantments.CHARGE_BLADE, EquipmentSlot.MAINHAND);
    }

    public ChargeStackingEnchantment(Rarity rarity, EnchantmentCategory category, EquipmentSlot... applicableSlots) {
        super(rarity, category, applicableSlots);
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public boolean canEnchant(@NotNull ItemStack pStack) {
        return pStack.getItem() instanceof ChargeBladeItem;
    }

    @Override
    public void getDescription(List<Component> tooltip, ItemStack stack) {
        ChargeBladeItem item = (ChargeBladeItem) stack.getItem();
        tooltip.add(Component.literal("| ").withStyle(ChatFormatting.GRAY)
                .append(Component.translatable(getDescriptionId() + ".bonus_1_desc",
                        Math.round(((float) (getMaxCharge(stack) / item.getChargeStack(stack)) - 1) * 100),
                        Component.translatable(getDescriptionId()))
                        .withStyle(ChatFormatting.GREEN)));
        tooltip.add(Component.literal("| ").withStyle(ChatFormatting.GRAY)
                .append(Component.translatable(getDescriptionId() + ".bonus_2_desc",
                        Math.round(((float) (10 / 5) - 1) * 100),
                        Component.translatable(getDescriptionId()))
                        .withStyle(ChatFormatting.GREEN)));
    }
}