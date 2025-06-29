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
import nokunami.elementus.client.model.mob.SteelGolemChestModel;
import nokunami.elementus.client.model.mob.SteelGolemModel;
import nokunami.elementus.common.entity.living.SteelGolem;
import org.jetbrains.annotations.NotNull;

import static nokunami.elementus.Elementus.MODID;

@OnlyIn(Dist.CLIENT)
public class SteelGolemChestLayer extends RenderLayer<SteelGolem, SteelGolemModel<SteelGolem>> {
    private final SteelGolemChestModel<SteelGolem> model;

    public SteelGolemChestLayer(RenderLayerParent<SteelGolem, SteelGolemModel<SteelGolem>> renderer, EntityModelSet modelSet) {
        super(renderer);
        this.model = new SteelGolemChestModel<>(modelSet.bakeLayer(ModModelLayers.STEEL_GOLEM_CHEST));
    }

    public void render(@NotNull PoseStack poseStack, @NotNull MultiBufferSource buffer, int packedLight, SteelGolem steelGolem, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!steelGolem.isInvisible()) {
            if (steelGolem.hasChest()) {
                ResourceLocation resourcelocation = new ResourceLocation(MODID, "textures/entity/golem/steel_golem/steel_golem_chest.png");
                this.getParentModel().copyPropertiesTo(this.model);
                this.model.prepareMobModel(steelGolem, limbSwing, limbSwingAmount, partialTicks);
                this.model.setupAnim(steelGolem, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
                VertexConsumer vertexconsumer = buffer.getBuffer(RenderType.entityCutoutNoCull(resourcelocation));
                this.model.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
            }
        }
    }
}
