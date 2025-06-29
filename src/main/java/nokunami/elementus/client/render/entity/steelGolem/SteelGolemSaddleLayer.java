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
import nokunami.elementus.client.model.mob.*;
import nokunami.elementus.common.entity.living.SteelGolem;
import org.jetbrains.annotations.NotNull;

import static nokunami.elementus.Elementus.MODID;

@OnlyIn(Dist.CLIENT)
public class SteelGolemSaddleLayer extends RenderLayer<SteelGolem, SteelGolemModel<SteelGolem>> {
    private final SteelGolemSaddleModel<SteelGolem> model1;

    public SteelGolemSaddleLayer(RenderLayerParent<SteelGolem, SteelGolemModel<SteelGolem>> pRenderer, EntityModelSet pModelSet) {
        super(pRenderer);
        this.model1 = new SteelGolemSaddleModel<>(pModelSet.bakeLayer(ModModelLayers.STEEL_GOLEM_SADDLE));
    }

    public void render(@NotNull PoseStack poseStack, @NotNull MultiBufferSource buffer, int packedLight, SteelGolem golem, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (golem.isSaddled()) {
            ResourceLocation resourcelocation = new ResourceLocation(MODID, "textures/entity/golem/steel_golem/steel_golem_saddle.png");
            VertexConsumer vertexconsumer = buffer.getBuffer(RenderType.entityCutoutNoCull(resourcelocation));
            this.getParentModel().copyPropertiesTo(this.model1);
            this.model1.prepareMobModel(golem, limbSwing, limbSwingAmount, partialTicks);
            this.model1.setupAnim(golem, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            this.model1.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        }
    }
}
