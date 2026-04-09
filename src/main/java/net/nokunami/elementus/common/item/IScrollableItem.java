package net.nokunami.elementus.common.item;

import net.minecraft.world.item.ItemStack;

public interface IScrollableItem {

    void onItemScroll(ItemStack stack, int scrollAmount);
}
