package net.nokunami.elementus.client.render.entity.projectile;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.nokunami.elementus.common.entity.projectile.PulseBurstEntity;
import net.nokunami.elementus.common.entity.projectile.RushProjectileEntity;
import org.jetbrains.annotations.NotNull;

public class PulseBurstEntityRenderer extends EntityRenderer<PulseBurstEntity> {

    public PulseBurstEntityRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public void render(@NotNull PulseBurstEntity entity, float entityYaw, float partialTick, @NotNull PoseStack poseStack, @NotNull MultiBufferSource buffer, int packedLight) {
        super.render(entity, entityYaw, partialTick, poseStack, buffer, packedLight);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull PulseBurstEntity entity) {
        return null;
    }
}
