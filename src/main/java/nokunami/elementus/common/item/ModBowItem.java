package nokunami.elementus.common.item;

import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ModBowItem extends BowItem {
    private final Item repairItem;

    public ModBowItem(Properties pProperties, Item repairItem) {
        super(pProperties);
        this.repairItem = repairItem;
    }

    public boolean isValidRepairItem(@NotNull ItemStack stack, ItemStack repairStack) {
        return repairStack.is(repairItem);
    }
}
