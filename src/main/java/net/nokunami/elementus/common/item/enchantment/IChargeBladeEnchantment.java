package net.nokunami.elementus.common.item.enchantment;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public interface IChargeBladeEnchantment {

    void getDescription(List<Component> tooltip, ItemStack stack);
}
