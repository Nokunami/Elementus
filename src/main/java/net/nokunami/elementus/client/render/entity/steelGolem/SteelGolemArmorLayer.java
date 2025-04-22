package net.nokunami.elementus.client.render.entity.steelGolem;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.nokunami.elementus.client.model.ModModelLayers;
import net.nokunami.elementus.client.model.mob.SteelGolemArmorModel;
import net.nokunami.elementus.client.model.mob.SteelGolemModel;
import net.nokunami.elementus.common.entity.living.SteelGolem;
import net.nokunami.elementus.common.item.DyeableSteelGolemUpgradeItem;
import net.nokunami.elementus.common.item.SteelGolemUpgradeItem;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class SteelGolemArmorLayer extends RenderLayer<SteelGolem, SteelGolemModel<SteelGolem>> {
    private final SteelGolemArmorModel<SteelGolem> model;

    public SteelGolemArmorLayer(RenderLayerParent<SteelGolem, SteelGolemModel<SteelGolem>> renderer, EntityModelSet modelSet) {
        super(renderer);
        this.model = new SteelGolemArmorModel<>(modelSet.bakeLayer(ModModelLayers.STEEL_GOLEM_ARMOR));
    }

    public void render(@NotNull PoseStack poseStack, @NotNull MultiBufferSource buffer, int packedLight, SteelGolem steelGolem, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        ItemStack itemStack = steelGolem.getArmor();
        if (itemStack.getItem() instanceof SteelGolemUpgradeItem armorItem) {
            this.getParentModel().copyPropertiesTo(this.model);
            this.model.prepareMobModel(steelGolem, limbSwing, limbSwingAmount, partialTicks);
            this.model.setupAnim(steelGolem, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            float f;
            float f1;
            float f2;
            if (armorItem instanceof DyeableSteelGolemUpgradeItem dyeableSteelGolemArmorItem) {
                int i = dyeableSteelGolemArmorItem.getColor(itemStack);
                f = (float)(i >> 16 & 255) / 255.0F;
                f1 = (float)(i >> 8 & 255) / 255.0F;
                f2 = (float)(i & 255) / 255.0F;
            } else {
                f = 1.0F;
                f1 = 1.0F;
                f2 = 1.0F;
            }

            VertexConsumer vertexconsumer = buffer.getBuffer(RenderType.entityCutoutNoCull(armorItem.getTexture()));
            this.model.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY, f, f1, f2, 1.0F);
        }
    }
}
