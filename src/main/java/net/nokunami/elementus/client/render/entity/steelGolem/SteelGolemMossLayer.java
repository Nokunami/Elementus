package net.nokunami.elementus.client.render.entity.steelGolem;

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
import net.nokunami.elementus.client.model.mob.SteelGolemModel;
import net.nokunami.elementus.common.entity.living.SteelGolem;
import org.jetbrains.annotations.NotNull;

import static net.nokunami.elementus.Elementus.modLoc;

@OnlyIn(Dist.CLIENT)
public class SteelGolemMossLayer extends RenderLayer<SteelGolem, SteelGolemModel<SteelGolem>> {
    private final SteelGolemModel<SteelGolem> model;

    public SteelGolemMossLayer(RenderLayerParent<SteelGolem, SteelGolemModel<SteelGolem>> pRenderer, EntityModelSet pModelSet) {
        super(pRenderer);
        this.model = new SteelGolemModel<>(pModelSet.bakeLayer(ModModelLayers.STEEL_GOLEM_EXTRA_1));
    }

    public void render(@NotNull PoseStack pPoseStack, @NotNull MultiBufferSource pBuffer, int pPackedLight, SteelGolem pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        if (!pLivingEntity.isInvisible()) {
            if (pLivingEntity.getMossStage() > 0) {
                ResourceLocation resourcelocation = modLoc("textures/entity/golem/steel_golem/moss/steel_golem_moss_" + pLivingEntity.getMossStage() + ".png");
                this.getParentModel().copyPropertiesTo(this.model);
                this.model.prepareMobModel(pLivingEntity, pLimbSwing, pLimbSwingAmount, pPartialTicks);
                this.model.setupAnim(pLivingEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
                VertexConsumer vertexconsumer = pBuffer.getBuffer(RenderType.entityCutoutNoCull(resourcelocation));
                this.model.renderToBuffer(pPoseStack, vertexconsumer, pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
            }
        }
    }
}
