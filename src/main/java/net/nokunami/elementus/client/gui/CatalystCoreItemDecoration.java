package net.nokunami.elementus.client.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.IItemDecorator;
import net.nokunami.elementus.common.item.unique.CatalystItemUtil;
import net.nokunami.elementus.common.item.unique.TestCatalystArmorItem;

public class CatalystCoreItemDecoration implements IItemDecorator {
    @Override
    public boolean render(GuiGraphics guiGraphics, Font font, ItemStack stack, int xOffset, int yOffset) {
//        CustomGui gui = (CustomGui) guiGraphics;
        CustomGui gui = new CustomGui(Minecraft.getInstance(), guiGraphics.bufferSource());
//        if (!stack.isEmpty() && stack.getItem() instanceof CatalystArmorItem) {
//            PoseStack posestack = guiGraphics.pose();
//            posestack.pushPose();
//            CatalystArmorItem.getContents(stack).findAny().ifPresent(itemStack -> {
//                posestack.translate(xOffset + 7, yOffset + 7, 0);
////                posestack.scale(0.5f, 0.5f, 1.0F);
////                guiGraphics.renderItem(itemStack.getItem().getDefaultInstance(), 0, 0);
//                posestack.scale(1.0f, 1.0f, 1.0F);
//                gui.renderCustomItem(itemStack, 0, 0);
//            });
//            posestack.popPose();
//        }
        if (!stack.isEmpty() && stack.getItem() instanceof TestCatalystArmorItem) {
            PoseStack posestack = guiGraphics.pose();
            posestack.pushPose();
            CatalystItemUtil.getCatalystContents(stack).findAny().ifPresent(itemStack -> {
                posestack.translate(xOffset + 7, yOffset + 7, 0);
//                posestack.scale(0.5f, 0.5f, 1.0F);
//                guiGraphics.renderItem(itemStack.getItem().getDefaultInstance(), 0, 0);
                posestack.scale(1.0f, 1.0f, 1.0F);
                gui.renderCustomItem(itemStack.getItem().getDefaultInstance(), 0, 0);
            });
            posestack.popPose();
        }
        return false;
    }
}
