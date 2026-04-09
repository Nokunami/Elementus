package net.nokunami.elementus.client.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.client.IItemDecorator;
import net.nokunami.elementus.common.item.IMovcadiaTool;
import net.nokunami.elementus.common.item.ISecondaryBar;
import net.nokunami.elementus.common.item.basic.EShieldItem;
import net.nokunami.elementus.common.item.unique.ChargeBladeItem;
import net.nokunami.elementus.common.registry.EEnchantments;

import static net.nokunami.elementus.common.item.EItemUtil.getEssenceBarWidth;
import static net.nokunami.elementus.common.item.EItemUtil.getMovcadiaEssence;
import static net.nokunami.elementus.common.item.basic.EShieldItem.getBufferValue;

public class ItemBarItemDecoration implements IItemDecorator {
    private final int DEFAULT_BG_COLOR = -16777216;

    @Override
    public boolean render(GuiGraphics guiGraphics, Font font, ItemStack stack, int xOffset, int yOffset) {
        if (!stack.isEmpty()) {
            PoseStack posestack = guiGraphics.pose();
            posestack.pushPose();
            if (stack.getItem() instanceof ChargeBladeItem blade) {
                if (blade.isMultiBarVisible(stack)) {
                    int level = EnchantmentHelper.getTagEnchantmentLevel(EEnchantments.CHARGE_STACKING.get(), stack);
                    int l = stack.getBarWidth();
                    int i = stack.getBarColor();
                    int minX = xOffset + 2;
                    int minY = yOffset + 13;
                    int color = -16777216;
                    int barColor = i|-16777216;
                    guiGraphics.fill(RenderType.guiOverlay(),
                            minX, minY,
                            minX + 13, minY + 2,
                            color);
                    if (level > 1) {
                        int charge1 = Math.min(4, l);
                        int charge2 = Math.min(4, Math.max(0, l - 4));
                        int charge3 = Math.min(3, Math.max(0, l - 9));
                        guiGraphics.fill(RenderType.guiOverlay(),
                                minX, minY,
                                minX + charge1, minY + 1,
                                barColor);
                        guiGraphics.fill(RenderType.guiOverlay(),
                                minX + 5, minY,
                                minX + 5 + charge2, minY + 1,
                                barColor);
                        guiGraphics.fill(RenderType.guiOverlay(),
                                minX + 10, minY,
                                minX + 10 + charge3, minY + 1,
                                barColor);
                    } else {
                        int charge1 = Math.min(6, l);
                        int charge2 = Math.min(6, Math.max(0, l - 7));
                        guiGraphics.fill(RenderType.guiOverlay(),
                                minX, minY,
                                minX + charge1, minY + 1,
                                barColor);
                        guiGraphics.fill(RenderType.guiOverlay(),
                                minX + 7, minY,
                                minX + 7 + charge2, minY + 1,
                                barColor);
                    }
                }
            }
            if (stack.getItem() instanceof ISecondaryBar bar) {
                simpleBar(guiGraphics, stack, xOffset, yOffset, bar.isSecondBarVisible(stack), bar.getSecondBarWidth(stack), bar.getSecondBarColor(stack));
            }
            posestack.popPose();
        }
        return false;
    }

    private void simpleBar(GuiGraphics gui, ItemStack stack, int xOffset, int yOffset, boolean visible, int amount, int fgColor) {
        simpleBar(gui, stack, xOffset, yOffset, visible, amount, DEFAULT_BG_COLOR, fgColor);
    }
    private void simpleBar(GuiGraphics gui, ItemStack stack, int xOffset, int yOffset, boolean visible, int amount, int bgColor, int fgColor) {
        int minX = xOffset + 2;
        int minY = yOffset + 11;
        if (!stack.isBarVisible()) minY = yOffset + 13;
        int barColor = fgColor | bgColor;
        if (visible) {
            gui.fill(RenderType.guiOverlay(),
                    minX, minY,
                    minX + 13, minY + 2,
                    bgColor);
            gui.fill(RenderType.guiOverlay(),
                    minX, minY,
                    minX + amount, minY + 1,
                    barColor);
        }
    }
}
