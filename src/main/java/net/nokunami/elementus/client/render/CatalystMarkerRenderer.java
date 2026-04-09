package net.nokunami.elementus.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.phys.Vec3;
import net.nokunami.elementus.common.entity.CatalystTotemMarker;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;

public class CatalystMarkerRenderer<T extends CatalystTotemMarker> extends EntityRenderer<T> {

    public CatalystMarkerRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(@NotNull T entity, float entityYaw, float partialTicks, @NotNull PoseStack poseStack, @NotNull MultiBufferSource buffer, int packedLight) {
        Entity leashHolder = entity.getOwner();
        if (leashHolder != null) {
//            this.renderLeash(entity, partialTicks, poseStack, buffer, leashHolder);
        }
    }

    @Override public @NotNull ResourceLocation getTextureLocation(@NotNull T entity) { return null; }

//    private <E extends Entity> void renderLeash(T entity, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, E leashHolder) {
//        poseStack.pushPose();
//        Vec3 vec3 = leashHolder.getRopeHoldPosition(partialTicks);
//        double d0 = (double) (Mth.lerp(partialTicks, entity.yBodyRotO, entity.yBodyRot) * ((float) Math.PI / 180F)) + (Math.PI / 2D);
//        Vec3 vec31 = entity.getLeashOffset(partialTicks);
//        double d1 = Math.cos(d0) * vec31.z + Math.sin(d0) * vec31.x;
//        double d2 = Math.sin(d0) * vec31.z - Math.cos(d0) * vec31.x;
//        double d3 = Mth.lerp(partialTicks, entity.xo, entity.getX()) + d1;
//        double d4 = Mth.lerp(partialTicks, entity.yo, entity.getY()) + vec31.y;
//        double d5 = Mth.lerp(partialTicks, entity.zo, entity.getZ()) + d2;
//        poseStack.translate(d1, vec31.y, d2);
//        float f = (float)(vec3.x - d3);
//        float f1 = (float)(vec3.y - d4);
//        float f2 = (float)(vec3.z - d5);
//        float f3 = 0.025F;
//        VertexConsumer vertexconsumer = buffer.getBuffer(RenderType.leash());
//        Matrix4f matrix4f = poseStack.last().pose();
//        float f4 = Mth.invSqrt(f * f + f2 * f2) * 0.025F / 2.0F;
//        float f5 = f2 * f4;
//        float f6 = f * f4;
//        BlockPos blockpos = BlockPos.containing(entity.getEyePosition(partialTicks));
//        BlockPos blockpos1 = BlockPos.containing(leashHolder.getEyePosition(partialTicks));
//        int i = getBlockLightLevel(entity, blockpos);
//        int j = entityRenderDispatcher.getRenderer(leashHolder).getBlockLightLevel(leashHolder, blockpos1);
//        int k = entity.level().getBrightness(LightLayer.SKY, blockpos);
//        int l = entity.level().getBrightness(LightLayer.SKY, blockpos1);
//
//        for(int i1 = 0; i1 <= 24; ++i1) {
//            addVertexPair(vertexconsumer, matrix4f, f, f1, f2, i, j, k, l, 0.025F, 0.025F, f5, f6, i1, false);
//        }
//
//        for(int j1 = 24; j1 >= 0; --j1) {
//            addVertexPair(vertexconsumer, matrix4f, f, f1, f2, i, j, k, l, 0.025F, 0.0F, f5, f6, j1, true);
//        }
//
//        poseStack.popPose();
//    }
//
//    private static void addVertexPair(VertexConsumer pConsumer, Matrix4f pMatrix, float p_174310_, float p_174311_, float p_174312_, int pEntityBlockLightLevel, int pLeashHolderBlockLightLevel, int pEntitySkyLightLevel, int pLeashHolderSkyLightLevel, float p_174317_, float p_174318_, float p_174319_, float p_174320_, int pIndex, boolean p_174322_) {
//        float f = (float)pIndex / 24.0F;
//        int i = (int)Mth.lerp(f, (float)pEntityBlockLightLevel, (float)pLeashHolderBlockLightLevel);
//        int j = (int)Mth.lerp(f, (float)pEntitySkyLightLevel, (float)pLeashHolderSkyLightLevel);
//        int k = LightTexture.pack(i, j);
//        float f1 = pIndex % 2 == (p_174322_ ? 1 : 0) ? 0.7F : 1.0F;
//        float f2 = 0.5F * f1;
//        float f3 = 0.4F * f1;
//        float f4 = 0.3F * f1;
//        float f5 = p_174310_ * f;
//        float f6 = p_174311_ > 0.0F ? p_174311_ * f * f : p_174311_ - p_174311_ * (1.0F - f) * (1.0F - f);
//        float f7 = p_174312_ * f;
//        pConsumer.vertex(pMatrix, f5 - p_174319_, f6 + p_174318_, f7 + p_174320_).color(f2, f3, f4, 1.0F).uv2(k).endVertex();
//        pConsumer.vertex(pMatrix, f5 + p_174319_, f6 + p_174317_ - p_174318_, f7 - p_174320_).color(f2, f3, f4, 1.0F).uv2(k).endVertex();
//    }
}