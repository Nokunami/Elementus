package net.nokunami.elementus.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.nokunami.elementus.client.model.ModModelLayers;
import net.nokunami.elementus.client.model.mob.SteelGolemExtraLayer1;
import net.nokunami.elementus.client.model.mob.SteelGolemExtraLayer2;
import net.nokunami.elementus.client.model.mob.SteelGolemExtraLayer3;
import net.nokunami.elementus.client.model.mob.SteelGolemModel;
import net.nokunami.elementus.common.entity.living.SteelGolem;
import org.jetbrains.annotations.NotNull;

import static net.nokunami.elementus.Elementus.MODID;

@OnlyIn(Dist.CLIENT)
public class SteelGolemLeavesLayer extends RenderLayer<SteelGolem, SteelGolemModel<SteelGolem>> {
    private final SteelGolemExtraLayer1<SteelGolem> model1;
    private final SteelGolemExtraLayer2<SteelGolem> model2;
    private final SteelGolemExtraLayer3<SteelGolem> modelE;

    public SteelGolemLeavesLayer(RenderLayerParent<SteelGolem, SteelGolemModel<SteelGolem>> pRenderer, EntityModelSet pModelSet) {
        super(pRenderer);
        this.model1 = new SteelGolemExtraLayer1<>(pModelSet.bakeLayer(ModModelLayers.STEEL_GOLEM_EXTRA_1));
        this.model2 = new SteelGolemExtraLayer2<>(pModelSet.bakeLayer(ModModelLayers.STEEL_GOLEM_EXTRA_2S));
        this.modelE = new SteelGolemExtraLayer3<>(pModelSet.bakeLayer(ModModelLayers.STEEL_GOLEM_EXTRA_3S));
    }

    public void render(@NotNull PoseStack pPoseStack, @NotNull MultiBufferSource pBuffer, int pPackedLight, SteelGolem pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        int fullBright = 15728880;
        if (!pLivingEntity.isInvisible()) {
            if (!pLivingEntity.getLeavesDecoration().isEmpty()) {
                ResourceLocation resourcelocation = new ResourceLocation(MODID, "textures/entity/golem/steel_golem/leaves/steel_golem_" + pLivingEntity.getLeavesName() + ".png");
                VertexConsumer vertexconsumer = pBuffer.getBuffer(RenderType.entityCutoutNoCull(resourcelocation));
                this.getParentModel().copyPropertiesTo(this.model1);
                this.model1.prepareMobModel(pLivingEntity, pLimbSwing, pLimbSwingAmount, pPartialTicks);
                this.model1.setupAnim(pLivingEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
                this.model1.renderToBuffer(pPoseStack, vertexconsumer, pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
                this.getParentModel().copyPropertiesTo(this.model2);
                this.model2.prepareMobModel(pLivingEntity, pLimbSwing, pLimbSwingAmount, pPartialTicks);
                this.model2.setupAnim(pLivingEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
                this.model2.renderToBuffer(pPoseStack, vertexconsumer, pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
                this.getParentModel().copyPropertiesTo(this.modelE);
                this.modelE.prepareMobModel(pLivingEntity, pLimbSwing, pLimbSwingAmount, pPartialTicks);
                this.modelE.setupAnim(pLivingEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
                this.modelE.renderToBuffer(pPoseStack, vertexconsumer, fullBright, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
            }
        }
    }
}
