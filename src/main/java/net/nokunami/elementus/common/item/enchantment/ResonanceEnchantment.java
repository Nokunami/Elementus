package net.nokunami.elementus.common.item.enchantment;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.nokunami.elementus.common.registry.EEnchantments;
import net.nokunami.elementus.common.registry.EItems;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ResonanceEnchantment extends Enchantment implements IChargeBladeEnchantment {

    public static ResonanceEnchantment init() {
        return new ResonanceEnchantment(Enchantment.Rarity.VERY_RARE, EEnchantments.CHARGE_BLADE, EquipmentSlot.MAINHAND);
    }

    public ResonanceEnchantment(Rarity rarity, EnchantmentCategory category, EquipmentSlot... applicableSlots) {
        super(rarity, category, applicableSlots);
    }

    @Override
    public boolean canEnchant(@NotNull ItemStack pStack) {
        return pStack.is(EItems.DIARKRITE_CHARGE_BLADE.get());
    }

    @Override
    public void getDescription(List<Component> tooltip, ItemStack stack) {
    }
}