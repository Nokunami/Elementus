package net.nokunami.elementus.client.render.item.inventory;

import net.minecraft.core.NonNullList;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;

public class CatalystTooltip implements TooltipComponent {
    private final NonNullList<ItemStack> items;
    private final int weight;

    public CatalystTooltip(NonNullList<ItemStack> pItems, int pWeight) {
        this.items = pItems;
        this.weight = pWeight;
    }

    public NonNullList<ItemStack> getItems() {
        return this.items;
    }

    public int getWeight() {
        return this.weight;
    }
}
