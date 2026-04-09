package net.nokunami.elementus.client.render.entity.projectile.cataclysm;

import com.github.L_Ender.cataclysm.client.model.entity.Ignis_Fireball_Model;
import com.github.L_Ender.cataclysm.client.render.CMRenderTypes;
import com.github.L_Ender.cataclysm.entity.projectile.Ignis_Fireball_Entity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.phys.Vec3;
import net.nokunami.elementus.common.entity.projectile.cataclysm.CatalystIgnisFireBall;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

import static net.nokunami.elementus.Elementus.modLoc;

public class CatalystIgnisFireballRenderer<T extends CatalystIgnisFireBall> extends EntityRenderer<T> {
    private static final ResourceLocation IGNIS_FIRE_BALL = modLoc("cataclysm", "textures/entity/ignis_fireball.png");
    private static final ResourceLocation IGNIS_FIRE_BALL_SOUL = modLoc("cataclysm", "textures/entity/ignis_fireball_soul.png");
    private static final ResourceLocation TRAIL_TEXTURE = modLoc("cataclysm", "textures/particle/storm.png");
    public Ignis_Fireball_Model model = new Ignis_Fireball_Model();
    public final RandomSource random = RandomSource.create();

    public CatalystIgnisFireballRenderer(EntityRendererProvider.Context context) { super(context); }

    @Override protected int getBlockLightLevel(@NotNull T entity, @NotNull BlockPos pos) { return 15; }

    public void render(T entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        matrixStackIn.pushPose();
        float f = this.rotLerp(entityIn.yRotO, entityIn.getYRot(), partialTicks);
        float f1 = Mth.lerp(partialTicks, entityIn.xRotO, entityIn.getXRot());
        float f2 = (float)entityIn.tickCount + partialTicks;
        matrixStackIn.translate(0, 0.3F, 0);
        matrixStackIn.mulPose(Axis.YP.rotationDegrees(Mth.sin(f2 * 0.1F) * 180));
        matrixStackIn.mulPose(Axis.XP.rotationDegrees(Mth.cos(f2 * 0.1F) * 180));
        matrixStackIn.mulPose(Axis.ZP.rotationDegrees(Mth.sin(f2 * 0.15F) * 360));
        model.setupAnim(entityIn, 0.0F, 0.0F, 0.0F, f, f1);
        VertexConsumer VertexConsumer = bufferIn.getBuffer(model.renderType(getTextureLocation(entityIn)));
        model.renderToBuffer(matrixStackIn, VertexConsumer, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStackIn.popPose();
        if (entityIn.hasTrail()) {
            double x = Mth.lerp(partialTicks, entityIn.xOld, entityIn.getX());
            double y = Mth.lerp(partialTicks, entityIn.yOld, entityIn.getY());
            double z = Mth.lerp(partialTicks, entityIn.zOld, entityIn.getZ());
            float ran = 0.04F;
            float r = (!entityIn.isSoul() ? 0.8039216F : 0.3254902F) + random.nextFloat() * ran;
            float g = (!entityIn.isSoul() ? 0.49411765F : 0.9372549F) + random.nextFloat() * ran;
            float b = (!entityIn.isSoul() ? 0.0F : 0.95686275F) + random.nextFloat() * ran;
            matrixStackIn.pushPose();
            matrixStackIn.translate(-x, -y, -z);
            renderTrail(entityIn, partialTicks, matrixStackIn, bufferIn, r, g, b, 1.0F, packedLightIn);
            matrixStackIn.popPose();
        }

        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    public @NotNull ResourceLocation getTextureLocation(T entity) {
        return entity.isSoul() ? IGNIS_FIRE_BALL_SOUL : IGNIS_FIRE_BALL;
    }

    private void renderTrail(T entityIn, float partialTicks, PoseStack poseStack, MultiBufferSource bufferIn, float trailR, float trailG, float trailB, float trailA, int packedLightIn) {
        int sampleSize = 10;
        float trailWidth = 0.2F;
        PoseStack.Pose lastPose = poseStack.last();
        Matrix4f matrix4f = lastPose.pose();
        Matrix3f matrix3f = lastPose.normal();
        VertexConsumer vertexconsumer = bufferIn.getBuffer(CMRenderTypes.getLightTrailEffect(TRAIL_TEXTURE));
        Vec3 drawFrom = entityIn.getTrailPosition(0, partialTicks);
        Vec3 cameraPos = entityRenderDispatcher.camera.getPosition();

        for(int i = 0; i < sampleSize; ++i) {
            Vec3 sample = entityIn.getTrailPosition(i + 1, partialTicks);
            float u1 = (float) i / (float) sampleSize;
            float u2 = u1 + 1.0F / (float) sampleSize;
            Vec3 forward = sample.subtract(drawFrom);
            if (forward.lengthSqr() != (double)0.0F) {
                Vec3 toCamera = cameraPos.subtract(drawFrom);
                Vec3 side = forward.cross(toCamera).normalize();
                Vec3 offset = side.scale(trailWidth / 2.0F);
                addVertex(vertexconsumer, matrix4f, matrix3f, drawFrom.add(offset), trailR, trailG, trailB, u1, 0.0F, packedLightIn);
                addVertex(vertexconsumer, matrix4f, matrix3f, drawFrom.add(offset.scale(-1.0F)), trailR, trailG, trailB, u1, 1.0F, packedLightIn);
                addVertex(vertexconsumer, matrix4f, matrix3f, sample.add(offset.scale(-1.0F)), trailR, trailG, trailB, u2, 1.0F, packedLightIn);
                addVertex(vertexconsumer, matrix4f, matrix3f, sample.add(offset), trailR, trailG, trailB, u2, 0.0F, packedLightIn);
                drawFrom = sample;
            }
        }

    }

    public void addVertex(VertexConsumer consumer, Matrix4f matrix, Matrix3f matrix3, Vec3 pos, float r, float g, float b, float u, float v, int light) {
        consumer.vertex(matrix, (float)pos.x, (float)pos.y, (float)pos.z).color(r, g, b, 1.0F).uv(u, v).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(matrix3, 0.0F, 1.0F, 0.0F).endVertex();
    }

    public float rotLerp(float prevRotation, float rotation, float partialTicks) {
        float f;
        for(f = rotation - prevRotation; f < -180.0F; f += 360.0F) { }

        while(f >= 180.0F) f -= 360.0F;

        return prevRotation + partialTicks * f;
    }
}
