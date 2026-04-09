package net.nokunami.elementus.client.render.item.inventory;

import net.minecraft.core.NonNullList;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;

public class CatalystTooltip implements TooltipComponent {
    private final NonNullList<ItemStack> coreStack;
    private final NonNullList<ItemStack> elytraStck;
    private final int selection;
    private final int trim;
    private final ItemStack stack;

    public CatalystTooltip(NonNullList<ItemStack> coreItem, NonNullList<ItemStack> elytraItem, int select, int trimmed, ItemStack itemStack) {
        coreStack = coreItem;
        elytraStck = elytraItem;
        selection = select;
        trim = trimmed;
        stack = itemStack;
    }

    public NonNullList<ItemStack> getCoreStack() { return coreStack; }
    public NonNullList<ItemStack> getElytraStack() { return elytraStck; }
    public int getSelection() { return selection; }
    public int trim() { return trim; }
    public ItemStack stack() { return stack; }
}
