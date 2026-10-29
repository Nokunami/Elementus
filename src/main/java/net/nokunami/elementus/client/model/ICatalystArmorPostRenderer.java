package net.nokunami.elementus.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.nokunami.elementus.client.render.ERenderType;

import java.util.List;

public interface ICatalystArmorPostRenderer {

    List<ModelPart> getNonEmissiveParts();
    List<ModelPart> getEmissiveParts();

    default void onlyNonEmissive() {
        getNonEmissiveParts().forEach(part -> part.visible = true);
        getEmissiveParts().forEach(part -> part.visible = false);
    }

    default void onlyEmissive() {
        getNonEmissiveParts().forEach(part -> part.visible = false);
        getEmissiveParts().forEach(part -> part.visible = true);
    }

    default void postRender(ArmorRenderProperties properties, PoseStack poseStack, MultiBufferSource bufferSource, LivingEntity entity, EquipmentSlot slot, int packedLight, Model model) {
        VertexConsumer vertexconsumer = bufferSource.getBuffer(RenderType.armorCutoutNoCull(properties.location()));
        onlyNonEmissive();
        model.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
        VertexConsumer buffer = bufferSource.getBuffer(ERenderType.noShadingArmor(properties.location()));
        onlyEmissive();
        model.renderToBuffer(poseStack, buffer, packedLight, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
    }
}
