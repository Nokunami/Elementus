package nokunami.elementus.client.render.entity.steelGolem;

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
import nokunami.elementus.client.model.ModModelLayers;
import nokunami.elementus.client.model.mob.SteelGolemModel;
import nokunami.elementus.common.Etags;
import nokunami.elementus.common.entity.living.SteelGolem;
import org.jetbrains.annotations.NotNull;

import static nokunami.elementus.Elementus.MODID;

@OnlyIn(Dist.CLIENT)
public class SteelGolemLeavesLayer extends RenderLayer<SteelGolem, SteelGolemModel<SteelGolem>> {
    private final SteelGolemModel<SteelGolem> model1;
    private final SteelGolemModel<SteelGolem> model2;
    private final SteelGolemModel<SteelGolem> modelE;

    public SteelGolemLeavesLayer(RenderLayerParent<SteelGolem, SteelGolemModel<SteelGolem>> pRenderer, EntityModelSet pModelSet) {
        super(pRenderer);
        this.model1 = new SteelGolemModel<>(pModelSet.bakeLayer(ModModelLayers.STEEL_GOLEM_EXTRA_1));
        this.model2 = new SteelGolemModel<>(pModelSet.bakeLayer(ModModelLayers.STEEL_GOLEM_EXTRA_2S));
        this.modelE = new SteelGolemModel<>(pModelSet.bakeLayer(ModModelLayers.STEEL_GOLEM_EXTRA_3S));
    }

    public void render(@NotNull PoseStack pPoseStack, @NotNull MultiBufferSource pBuffer, int pPackedLight, SteelGolem pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        int fullBright = 15728880;
        if (!pLivingEntity.isInvisible()) {
            if (pLivingEntity.isCamouflaged().is(Etags.Items.STEEL_GOLEM_LEAVES_DECORATION)) {
                ResourceLocation resourcelocation = new ResourceLocation(MODID, "textures/entity/golem/steel_golem/leaves/steel_golem_" + pLivingEntity.isCamouflaged().getItem() + ".png");
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
