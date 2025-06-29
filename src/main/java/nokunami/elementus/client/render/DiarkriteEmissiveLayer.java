package nokunami.elementus.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import nokunami.elementus.client.model.ModModelLayers;
import nokunami.elementus.client.model.armor.ExtendedArmorModel;
import nokunami.elementus.common.item.DiarkriteBootsItem;
import org.jetbrains.annotations.NotNull;

import static nokunami.elementus.Elementus.modLoc;

@OnlyIn(Dist.CLIENT)
public class DiarkriteEmissiveLayer<T extends LivingEntity, M extends EntityModel<T>> extends RenderLayer<T, M> {
    private final ExtendedArmorModel<T> diarkriteEmissive;

    public DiarkriteEmissiveLayer(RenderLayerParent<T, M> pRenderer, EntityModelSet pModelSet) {
        super(pRenderer);
        this.diarkriteEmissive = new ExtendedArmorModel<>(pModelSet.bakeLayer(ModModelLayers.EXTENDED_ARMOR_MODEL));
    }

    public void render(@NotNull PoseStack poseStack, @NotNull MultiBufferSource buffer, int packedLight, T livingEntity, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {
        ItemStack itemstack = livingEntity.getItemBySlot(EquipmentSlot.FEET);
        if (shouldRender(itemstack, livingEntity)) {
            ResourceLocation resourcelocation1 = getElytraTexture(itemstack, livingEntity);

            VertexConsumer vertexconsumer1 = ItemRenderer.getArmorFoilBuffer(buffer, RenderType.armorCutoutNoCull(resourcelocation1), false, itemstack.hasFoil());
            poseStack.pushPose();
            this.getParentModel().copyPropertiesTo(this.diarkriteEmissive);
            this.diarkriteEmissive.setupAnim(livingEntity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

            float oBrightness = 0;
            float brightness = 0;
            brightness += (0.0F - brightness) * 0.8F;

            float strength = 0.5F + Mth.clamp((float)Math.cos((ageInTicks + partialTick) * 0.1F) - 0.25F, -0.25F, 0.5F);

            strength += Mth.lerp(partialTick, oBrightness, brightness) * 0.025F * (float)Math.PI;
            strength = Mth.clamp(strength, 0.5F, 1.0F);


            this.diarkriteEmissive.leftLeg.render(poseStack, vertexconsumer1, 15728640, OverlayTexture.NO_OVERLAY, strength, strength, strength, 1.0F);
            this.diarkriteEmissive.rightLeg.render(poseStack, vertexconsumer1, 15728640, OverlayTexture.NO_OVERLAY, strength, strength, strength, 1.0F);
            poseStack.popPose();
        }
    }

    public boolean shouldRender(@NotNull ItemStack stack, LivingEntity entity) {
        return DiarkriteBootsItem.SculkWalkerActivation(entity);
    }

    public @NotNull ResourceLocation getElytraTexture(@NotNull ItemStack stack, @NotNull LivingEntity entity) {
        return modLoc("textures/models/armor/diarkrite_layer_1_emissive.png");
    }
}
