package net.nokunami.elementus.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.nokunami.elementus.client.model.mob.SteelGolemModel;
import net.nokunami.elementus.common.entity.living.SteelGolem;
import org.jetbrains.annotations.NotNull;

import static net.nokunami.elementus.Elementus.MODID;

@OnlyIn(Dist.CLIENT)
public class SteelGolemEyesLayer extends RenderLayer<SteelGolem, SteelGolemModel<SteelGolem>> {

    public SteelGolemEyesLayer(RenderLayerParent<SteelGolem, SteelGolemModel<SteelGolem>> pRenderer) {
        super(pRenderer);
    }

    public void render(@NotNull PoseStack pPoseStack, @NotNull MultiBufferSource pBuffer, int pPackedLight, SteelGolem pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        int fullBright = 15728880;
        if (!pLivingEntity.isInvisible()) {
            if (!pLivingEntity.getChassisState()) {
                String location = "neutral";
                if (pLivingEntity.getAggroState()) {
                    location = "aggressive";
                }
                ResourceLocation resourcelocation = new ResourceLocation(MODID, "textures/entity/golem/steel_golem/steel_golem_" + location + ".png");
                renderColoredCutoutModel(this.getParentModel(), resourcelocation, pPoseStack, pBuffer, fullBright, pLivingEntity, 1.0F, 1.0F, 1.0F);
            }
        }
    }
}
