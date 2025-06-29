package nokunami.elementus.client.render.item.inventory;

import net.minecraft.core.NonNullList;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;

public class CatalystTooltip implements TooltipComponent {
    private final NonNullList<ItemStack> coreitem;
    private final NonNullList<ItemStack> elytraItem;

    public CatalystTooltip(NonNullList<ItemStack> coreItem, NonNullList<ItemStack> elytraItem) {
        this.coreitem = coreItem;
        this.elytraItem = elytraItem;
    }

    public NonNullList<ItemStack> getCoreItem() {
        return this.coreitem;
    }

    public NonNullList<ItemStack> getElytraItem() {
        return this.elytraItem;
    }
}
