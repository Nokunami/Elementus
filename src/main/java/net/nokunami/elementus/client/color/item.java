package net.nokunami.elementus.client.color;

import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.ItemStack;
import net.nokunami.elementus.common.item.unique.BladeOfResonance;
import org.jetbrains.annotations.NotNull;

public class item {
    public static class DiarkriteChargeBladeColor implements ItemColor {
        @Override
        public int getColor(@NotNull ItemStack stack, int pTintIndex) {
            if (pTintIndex == 0 && stack.getItem() instanceof BladeOfResonance chargeBlade) {
            }
            return 0;
        }
    }
}
