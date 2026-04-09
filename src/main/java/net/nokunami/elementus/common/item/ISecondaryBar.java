package net.nokunami.elementus.common.item;

import net.minecraft.world.item.ItemStack;

public interface ISecondaryBar {

    boolean isSecondBarVisible(ItemStack stack);
    int getSecondBarWidth(ItemStack stack);
    int getSecondBarColor(ItemStack stack);
}
