//package net.nokunami.elementus.mixin.overlayConfig;
//
//import com.mojang.blaze3d.vertex.PoseStack;
//import com.mojang.blaze3d.vertex.VertexConsumer;
//import net.minecraft.overlayConfig.color.item.ItemColors;
//import net.minecraft.overlayConfig.renderer.MultiBufferSource;
//import net.minecraft.overlayConfig.renderer.block.model.BakedQuad;
//import net.minecraft.overlayConfig.renderer.entity.ItemRenderer;
//import net.minecraft.overlayConfig.renderer.texture.OverlayTexture;
//import net.minecraft.overlayConfig.resources.model.BakedModel;
//import net.minecraft.core.Direction;
//import net.minecraft.util.RandomSource;
//import net.minecraft.world.item.ItemDisplayContext;
//import net.minecraft.world.item.ItemStack;
//import net.minecraftforge.overlayConfig.ForgeHooksClient;
//import net.nokunami.elementus.common.item.unique.ChargeBladeItem;
//import org.spongepowered.asm.mixin.Final;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Shadow;
//import org.spongepowered.asm.mixin.Unique;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
//import java.util.List;
//
//import static net.minecraft.overlayConfig.renderer.entity.ItemRenderer.getFoilBuffer;
//
//@Mixin(ItemRenderer.class)
//public abstract class ItemRendererMixin {
//
//    @Shadow
//    public abstract void renderModelLists(BakedModel pModel, ItemStack pStack, int pCombinedLight, int pCombinedOverlay, PoseStack pPoseStack, VertexConsumer pBuffer);
//
//    @Shadow
//    @Final
//    private ItemColors itemColors;
//
//    @Inject(method = "render", at = @At(value = "HEAD"), cancellable = true)
//    public void e$render(ItemStack stack, ItemDisplayContext displayContext, boolean leftHand, PoseStack poseStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay, BakedModel model, CallbackInfo ci) {
//        if (stack.getItem() instanceof ChargeBladeItem) {
//            ci.cancel();
//            poseStack.pushPose();
//
//            model = ForgeHooksClient.handleCameraTransforms(poseStack, model, displayContext, leftHand);
//            poseStack.translate(-0.5F, -0.5F, -0.5F);
//
//            for (var bakedModel : model.getRenderPasses(stack, false)) {
//                for (var rendertype : bakedModel.getRenderTypes(stack, false)) {
////                    VertexConsumer vertexconsumer = getFoilBuffer(buffer, rendertype, true, stack.hasFoil());
//                    VertexConsumer vertexconsumer = getFoilBuffer(buffer, rendertype, true, stack.hasFoil());
//
////                    renderModelLists(bakedModel, stack, combinedLight, OverlayTexture.NO_WHITE_U, poseStack, vertexconsumer);
//                    e$renderModelLists(bakedModel, stack, combinedLight, OverlayTexture.NO_OVERLAY, poseStack, vertexconsumer);
//                }
//            }
//
//            poseStack.popPose();
//        }
//    }
//
//    @Unique
//    public void e$renderModelLists(BakedModel pModel, ItemStack pStack, int pCombinedLight, int pCombinedOverlay, PoseStack pPoseStack, VertexConsumer pBuffer) {
//        RandomSource randomsource = RandomSource.create();
//        long i = 42L;
//
//        for(Direction direction : Direction.values()) {
//            randomsource.setSeed(i);
//            e$renderQuadList(pPoseStack, pBuffer, pModel.getQuads(null, direction, randomsource), pStack, pCombinedLight, pCombinedOverlay);
//        }
//
////        randomsource.setSeed(i);
////        e$renderQuadList(pPoseStack, pBuffer, pModel.getQuads(null, null, randomsource), pStack, pCombinedLight, pCombinedOverlay);
//    }
//
//    @Unique
//    public void e$renderQuadList(PoseStack pPoseStack, VertexConsumer pBuffer, List<BakedQuad> pQuads, ItemStack pItemStack, int pCombinedLight, int pCombinedOverlay) {
//        boolean flag = !pItemStack.isEmpty();
//        PoseStack.Pose posestack$pose = pPoseStack.last();
//
//        for(BakedQuad bakedquad : pQuads) {
//            int i = -1;
//            if (flag && bakedquad.isTinted()) {
//                i = itemColors.getColor(pItemStack, bakedquad.getTintIndex());
//            }
//
//            float f = (float)(i >> 16 & 255) / 255.0F;
//            float f1 = (float)(i >> 8 & 255) / 255.0F;
//            float f2 = (float)(i & 255) / 255.0F;
//            pBuffer.putBulkData(posestack$pose, bakedquad, f, f1, f2, 1, pCombinedLight, pCombinedOverlay, true);
//        }
//
//    }
//}
