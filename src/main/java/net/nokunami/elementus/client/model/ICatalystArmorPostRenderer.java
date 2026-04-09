package net.nokunami.elementus.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;

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
        onlyEmissive();
    }
}
