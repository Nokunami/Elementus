package net.nokunami.elementus.client.gui;

import com.mojang.blaze3d.platform.Lighting;
import net.minecraft.CrashReport;
import net.minecraft.CrashReportCategory;
import net.minecraft.ReportedException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;

@OnlyIn(Dist.CLIENT)
public class CustomGui extends GuiGraphics {

    public CustomGui(Minecraft pMinecraft, MultiBufferSource.BufferSource pBufferSource) {
        super(pMinecraft, pBufferSource);
    }

    public void renderCustomItem(@NotNull ItemStack pStack, int pX, int pY) {
        Minecraft mc = Minecraft.getInstance();
        this.renderCustomItem(mc.player, mc.level, pStack, pX, pY, 0);
    }

    private void renderCustomItem(@Nullable LivingEntity pEntity, @Nullable Level pLevel, ItemStack pStack, int pX, int pY, int pSeed) {
        this.renderCustomItem(pEntity, pLevel, pStack, pX, pY, pSeed, 0);
    }

    private void renderCustomItem(@Nullable LivingEntity pEntity, @Nullable Level pLevel, ItemStack pStack, int pX, int pY, int pSeed, int pGuiOffset) {
        Minecraft mc = Minecraft.getInstance();
        if (!pStack.isEmpty()) {
            BakedModel bakedmodel = mc.getItemRenderer().getModel(pStack, pLevel, pEntity, pSeed);
            this.pose().pushPose();
            this.pose().translate((float)(pX + 8), (float)(pY + 8), (float)(150 + (bakedmodel.isGui3d() ? pGuiOffset : 0)));

            try {
                this.pose().mulPoseMatrix((new Matrix4f()).scaling(1.0F, -1.0F, 1.0F));
                this.pose().scale(16.0F, 16.0F, 16.0F);
                boolean flag = !bakedmodel.usesBlockLight();
                if (flag) {
                    Lighting.setupForFlatItems();
                }

                mc.getItemRenderer().render(pStack, ItemDisplayContext.HEAD, false, this.pose(), this.bufferSource(), 15728880, OverlayTexture.NO_OVERLAY, bakedmodel);
                this.flush();
                if (flag) {
                    Lighting.setupFor3DItems();
                }
            } catch (Throwable throwable) {
                CrashReport crashreport = CrashReport.forThrowable(throwable, "Rendering item");
                CrashReportCategory crashreportcategory = crashreport.addCategory("Item being rendered");
                crashreportcategory.setDetail("Item Type", () -> String.valueOf(pStack.getItem()));
                crashreportcategory.setDetail("Registry Name", () -> String.valueOf(net.minecraftforge.registries.ForgeRegistries.ITEMS.getKey(pStack.getItem())));
                crashreportcategory.setDetail("Item Damage", () -> String.valueOf(pStack.getDamageValue()));
                crashreportcategory.setDetail("Item NBT", () -> String.valueOf(pStack.getTag()));
                crashreportcategory.setDetail("Item Foil", () -> String.valueOf(pStack.hasFoil()));
                throw new ReportedException(crashreport);
            }

            this.pose().popPose();
        }
    }
}
