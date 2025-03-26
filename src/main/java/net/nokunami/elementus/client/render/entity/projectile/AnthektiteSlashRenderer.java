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
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.nokunami.elementus.client.model.ModModelLayers;
import net.nokunami.elementus.client.model.projectile.AnthektiteSlashModel;
import net.nokunami.elementus.common.entity.projectile.AnthektiteSlash;
import org.jetbrains.annotations.NotNull;

import static net.nokunami.elementus.Elementus.modLoc;

public class AnthektiteSlashRenderer extends EntityRenderer<AnthektiteSlash> {
    public static final ResourceLocation[] TEXTURE_BY_TYPE = {
        modLoc("textures/entity/projectiles/anthektite_slash/anthektite_slash_0.png"),
        modLoc("textures/entity/projectiles/anthektite_slash/anthektite_slash_1.png"),
        modLoc("textures/entity/projectiles/anthektite_slash/anthektite_slash_2.png"),
        modLoc("textures/entity/projectiles/anthektite_slash/anthektite_slash_3.png"),
        modLoc("textures/entity/projectiles/anthektite_slash/anthektite_slash_4.png"),
        modLoc("textures/entity/projectiles/anthektite_slash/anthektite_slash_5.png"),
        modLoc("textures/entity/projectiles/anthektite_slash/anthektite_slash_6.png"),
        modLoc("textures/entity/projectiles/anthektite_slash/anthektite_slash_7.png")
    };
    private final AnthektiteSlashModel model;

    public AnthektiteSlashRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        this.model = new AnthektiteSlashModel(pContext.bakeLayer(ModModelLayers.ANTHEKTITE_SLASH));
    }

    @Override
    public void render(AnthektiteSlash entity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, @NotNull MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.pushPose();
        Vec3 motion = entity.getDeltaMovement();
        float xRot = -((float) (Mth.atan2(motion.horizontalDistance(), motion.y) * (double) (180F / (float) Math.PI)) - 90.0F);
        float yRot = -((float) (Mth.atan2(motion.z, motion.x) * (double) (180F / (float) Math.PI)) + 90.0F);
        pPoseStack.mulPose(Axis.YP.rotationDegrees(yRot));
        pPoseStack.mulPose(Axis.YP.rotationDegrees(xRot));
        VertexConsumer vertexconsumer = pBuffer.getBuffer(RenderType.entityCutoutNoCull(getTextureLocation(entity)));
        this.model.renderToBuffer(pPoseStack, vertexconsumer, pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F,1.0F, 1.0F);
        pPoseStack.popPose();
        super.render(entity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull AnthektiteSlash pEntity) {
        int i = (pEntity.tickCount) % TEXTURE_BY_TYPE.length;
        return TEXTURE_BY_TYPE[i];
    }
}
