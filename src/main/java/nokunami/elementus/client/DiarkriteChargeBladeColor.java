package nokunami.elementus.client;

import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.ItemStack;
import nokunami.elementus.common.item.DiarkriteChargeBlade;
import org.jetbrains.annotations.NotNull;

public class DiarkriteChargeBladeColor implements ItemColor {
    @Override
    public int getColor(@NotNull ItemStack stack, int pTintIndex) {
        if (pTintIndex == 0 && stack.getItem() instanceof DiarkriteChargeBlade chargeBlade) {
        }
        return 0;
    }
}
