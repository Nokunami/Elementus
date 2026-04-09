package net.nokunami.elementus.client.render.entity.armor;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.ElytraLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.PlayerModelPart;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.nokunami.elementus.client.model.geom.EModelLayers;
import net.nokunami.elementus.client.model.armor.CatalystElytraModel;
import net.nokunami.elementus.common.item.unique.CatalystItemUtil;
import org.jetbrains.annotations.NotNull;

import static net.nokunami.elementus.Elementus.modLoc;
import static net.nokunami.elementus.common.item.unique.CatalystItemUtil.getEquippedElytra;
import static net.nokunami.elementus.common.item.unique.CatalystItemUtil.hasElytra;

public class CatalystElytraLayer<T extends LivingEntity, M extends EntityModel<T>> extends ElytraLayer<T, M> {
    private final CatalystElytraModel<T> catalystElytraModel;
    private final CatalystElytraModel<T> elytraModel;

    public CatalystElytraLayer(RenderLayerParent<T, M> pRenderer, EntityModelSet pModelSet) {
        super(pRenderer, pModelSet);
        this.catalystElytraModel = new CatalystElytraModel<>(pModelSet.bakeLayer(EModelLayers.CATALYST_ELYTRA_MODEL));
        this.elytraModel = new CatalystElytraModel<>(pModelSet.bakeLayer(EModelLayers.CATALYST_BASE_ELYTRA_MODEL));
    }

    public void render(@NotNull PoseStack poseStack, @NotNull MultiBufferSource buffer, int packedLight, T livingEntity, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {
        ItemStack itemstack = livingEntity.getItemBySlot(EquipmentSlot.CHEST);
        int type = 0;
        if (shouldRender(itemstack, livingEntity)) {
            ResourceLocation resourcelocation;
//            ResourceLocation resourcelocation1 = getCatalystElytraTexture(itemstack, livingEntity);
            if (livingEntity instanceof AbstractClientPlayer abstractclientplayer) {
                if (abstractclientplayer.isElytraLoaded() && abstractclientplayer.getElytraTextureLocation() != null) {
                    resourcelocation = abstractclientplayer.getElytraTextureLocation();
                    type = 1;
                } else if (abstractclientplayer.isCapeLoaded() && abstractclientplayer.getCloakTextureLocation() != null && abstractclientplayer.isModelPartShown(PlayerModelPart.CAPE)) {
                    resourcelocation = abstractclientplayer.getCloakTextureLocation();
                    type = 1;
                } else {
                    resourcelocation = getElytraTexture(itemstack, livingEntity);
                }
            } else {
                resourcelocation = getElytraTexture(itemstack, livingEntity);
            }

            elytraModelType(type, poseStack, buffer, packedLight, livingEntity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, resourcelocation, itemstack);
        }
    }

    public boolean shouldRender(@NotNull ItemStack stack, @NotNull LivingEntity entity) {
//        return entity.getItemBySlot(EquipmentSlot.CHEST).is(CATALYST_CHESTPLATE.get()) && CatalystArmorItem.getElytraEquipped(stack).findAny().isPresent() ||
//                entity.getItemBySlot(EquipmentSlot.CHEST).is(TEST_CATALYST_CHESTPLATE.get()) && CatalystItemUtil.getEquippedElytra(stack).isPresent();
        return hasElytra(stack);
    }

    public @NotNull ResourceLocation getElytraTexture(@NotNull ItemStack stack, @NotNull LivingEntity entity) {
//        if (stack.is(CATALYST_CHESTPLATE.get()))
//            return new ResourceLocation(EID, CatalystItemUtil.getElytraTexture(stack));
//        else
//            return new ResourceLocation(EID, CatalystItemUtil.getTestElytraTexture(stack));
        return modLoc(CatalystItemUtil.getElytraTexture(stack));
    }

    public @NotNull ResourceLocation getCatalystElytraTexture(@NotNull ItemStack stack, @NotNull LivingEntity entity) {
//        if (stack.is(CATALYST_CHESTPLATE.get()))
//            return new ResourceLocation(CatalystItemUtil.baseTextures(stack, entity));
//        else
//            return new ResourceLocation(CatalystItemUtil.testBaseTextures(stack, entity));
        return new ResourceLocation(CatalystItemUtil.baseTextures(stack, entity));
    }

    public void elytraModelType(int type, PoseStack poseStack, MultiBufferSource buffer, int packedLight, T livingEntity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, ResourceLocation resourcelocation1, ItemStack itemStack) {
        poseStack.pushPose();
        poseStack.translate(0, 0, 0.125F);
        VertexConsumer vertexconsumer = ItemRenderer.getArmorFoilBuffer(buffer, RenderType.armorCutoutNoCull(resourcelocation1), false, itemStack.hasFoil());
        if (type == 1) {
            getParentModel().copyPropertiesTo(elytraModel);
            elytraModel.setupAnim(livingEntity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            elytraModel.renderBase(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
        } else {
            getParentModel().copyPropertiesTo(catalystElytraModel);
            catalystElytraModel.setupAnim(livingEntity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            catalystElytraModel.renderCatalyst(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
        }
        poseStack.popPose();
    }
}
