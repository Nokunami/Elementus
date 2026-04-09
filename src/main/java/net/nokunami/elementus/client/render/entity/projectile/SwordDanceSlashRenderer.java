package net.nokunami.elementus.client.render.entity.projectile;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.nokunami.elementus.client.model.geom.EModelLayers;
import net.nokunami.elementus.client.model.projectile.SwordDanceSlashModel;
import net.nokunami.elementus.common.entity.projectile.SwordDanceSlashEntity;
import org.jetbrains.annotations.NotNull;

import static net.nokunami.elementus.Elementus.modLoc;

public class SwordDanceSlashRenderer extends EntityRenderer<SwordDanceSlashEntity> {
    public static final ResourceLocation[] DEFAULT_TEXTURE = {
        modLoc("textures/entity/projectiles/sword_dance_slash/sword_dance_slash_0.png"),
        modLoc("textures/entity/projectiles/sword_dance_slash/sword_dance_slash_1.png"),
        modLoc("textures/entity/projectiles/sword_dance_slash/sword_dance_slash_2.png"),
        modLoc("textures/entity/projectiles/sword_dance_slash/sword_dance_slash_3.png"),
        modLoc("textures/entity/projectiles/sword_dance_slash/sword_dance_slash_4.png"),
        modLoc("textures/entity/projectiles/sword_dance_slash/sword_dance_slash_5.png")
    };
    public static final ResourceLocation[] MIRRORED_TEXTURE = {
        modLoc("textures/entity/projectiles/sword_dance_slash/sword_dance_slash_0_m.png"),
        modLoc("textures/entity/projectiles/sword_dance_slash/sword_dance_slash_1_m.png"),
        modLoc("textures/entity/projectiles/sword_dance_slash/sword_dance_slash_2_m.png"),
        modLoc("textures/entity/projectiles/sword_dance_slash/sword_dance_slash_3_m.png"),
        modLoc("textures/entity/projectiles/sword_dance_slash/sword_dance_slash_4_m.png"),
        modLoc("textures/entity/projectiles/sword_dance_slash/sword_dance_slash_5_m.png")
    };
    private final SwordDanceSlashModel model;

    public SwordDanceSlashRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        this.model = new SwordDanceSlashModel(pContext.bakeLayer(EModelLayers.SWORD_DANCE_SLASH));
    }

    @Override
    public void render(@NotNull SwordDanceSlashEntity entity, float entityYaw, float partialTick, PoseStack poseStack, @NotNull MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(90.0F - entity.getYRot()));
        poseStack.mulPose(Axis.XP.rotationDegrees(-entity.getXRot()));
        poseStack.mulPose(Axis.ZN.rotationDegrees(entity.getOffsetDegree()));

        VertexConsumer defaultLayer = buffer.getBuffer(RenderType.entityCutoutNoCull(getTextureLocation(entity)));
        this.model.renderToBuffer(poseStack, defaultLayer, 15728880, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F,1.0F, 1.0F);
        poseStack.popPose();
        super.render(entity, entityYaw, partialTick, poseStack, buffer, packedLight);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull SwordDanceSlashEntity entity) {
        int i = (entity.tickCount) / 2 % DEFAULT_TEXTURE.length;
        if (entity.getMirrored()) return MIRRORED_TEXTURE[i];
        return DEFAULT_TEXTURE[i];
    }
}
