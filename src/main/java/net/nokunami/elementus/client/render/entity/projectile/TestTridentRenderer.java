package net.nokunami.elementus.client.render.entity.projectile;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.nokunami.elementus.common.entity.projectile.WrathTridentEntity;
import org.jetbrains.annotations.NotNull;

public class TestTridentRenderer extends EntityRenderer<WrathTridentEntity> {
    private final ItemRenderer item;

    public TestTridentRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.item = context.getItemRenderer();
    }

    @Override
    public void render(WrathTridentEntity entity, float entityYaw, float partialTick, PoseStack poseStack, @NotNull MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();
        //Credits: justliliandev @link{https://github.com/justliliandev/arrow-sprites/blob/1.20.x/common/src/main/java/dev/agnor/spritearrows/SpriteArrowRenderer.java}
        poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTick, entity.yRotO, entity.getYRot()) - 90.0F));
        poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partialTick, entity.xRotO, entity.getXRot())));
        poseStack.translate(-0.5, 0, 0);
        poseStack.mulPose(Axis.ZP.rotationDegrees(90));
        ItemStack pickupItem = entity.getTridentItem();
        item.renderStatic(pickupItem, ItemDisplayContext.HEAD, packedLight, OverlayTexture.NO_OVERLAY, poseStack, buffer, entity.level(), entity.getId());
        poseStack.popPose();
        super.render(entity, entityYaw, partialTick, poseStack, buffer, packedLight);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull WrathTridentEntity pEntity) {
        return null;
    }
}
