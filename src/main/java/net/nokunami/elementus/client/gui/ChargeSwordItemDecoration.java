package net.nokunami.elementus.client.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.client.IItemDecorator;
import net.nokunami.elementus.common.item.ChargeSwordItem;
import net.nokunami.elementus.common.item.DiarkriteChargeBlade;
import net.nokunami.elementus.common.registry.ModEnchantments;

import static net.nokunami.elementus.common.item.ChargeSwordItem.isEnchantedWith;

public class ChargeSwordItemDecoration implements IItemDecorator {
    @Override
    public boolean render(GuiGraphics guiGraphics, Font font, ItemStack stack, int xOffset, int yOffset) {
        if (!stack.isEmpty() && stack.getItem() instanceof ChargeSwordItem) {
            PoseStack posestack = guiGraphics.pose();
            posestack.pushPose();
            if (stack.getItem() instanceof DiarkriteChargeBlade blade) {
                if (blade.isMultiBarVisible(stack)) {
                    int level = EnchantmentHelper.getTagEnchantmentLevel(ModEnchantments.MULTI_CHARGE.get(), stack);
                    int l = stack.getBarWidth();
                    int i = stack.getBarColor();
                    int j = xOffset + 2;
                    int k = yOffset + 13;
                    int color = -16777216;
                    int barColor = i|-16777216;
                    guiGraphics.fill(RenderType.guiOverlay(), j, k, j + 13, k + 2, color);
                    if (level > 1) {
//                        guiGraphics.fill(RenderType.guiOverlay(), j, k, j + l, k + 1, barColor);
                    } else {
                        guiGraphics.fill(RenderType.guiOverlay(), j, k, j + l, k + 1, barColor);
                        guiGraphics.fill(RenderType.guiOverlay(), j, k, j + 7 + l, k + 1, barColor);
                    }
                }
            }

            posestack.popPose();
        }
        return false;
    }
}
