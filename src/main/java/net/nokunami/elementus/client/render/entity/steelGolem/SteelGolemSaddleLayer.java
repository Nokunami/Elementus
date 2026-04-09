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
import net.nokunami.elementus.client.model.geom.EModelLayers;
import net.nokunami.elementus.client.model.mob.SteelGolemModel;
import net.nokunami.elementus.client.model.mob.SteelGolemSaddleModel;
import net.nokunami.elementus.common.entity.living.SteelGolem;
import org.jetbrains.annotations.NotNull;

import static net.nokunami.elementus.Elementus.EID;

@OnlyIn(Dist.CLIENT)
public class SteelGolemSaddleLayer<T extends SteelGolem> extends RenderLayer<T, SteelGolemModel<T>> {
    private final SteelGolemSaddleModel<T> model1;

    public SteelGolemSaddleLayer(RenderLayerParent<T, SteelGolemModel<T>> pRenderer, EntityModelSet pModelSet) {
        super(pRenderer);
        this.model1 = new SteelGolemSaddleModel<>(pModelSet.bakeLayer(EModelLayers.STEEL_GOLEM_SADDLE));
    }

    public void render(@NotNull PoseStack poseStack, @NotNull MultiBufferSource buffer, int packedLight, T golem, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (golem.isSaddled()) {
            ResourceLocation resourcelocation = new ResourceLocation(EID, "textures/entity/golem/steel_golem/steel_golem_saddle.png");
            VertexConsumer vertexconsumer = buffer.getBuffer(RenderType.entityCutoutNoCull(resourcelocation));
            this.getParentModel().copyPropertiesTo(this.model1);
            this.model1.prepareMobModel(golem, limbSwing, limbSwingAmount, partialTicks);
            this.model1.setupAnim(golem, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            this.model1.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        }
    }
}
