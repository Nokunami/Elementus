package nokunami.elementus.client.render.entity.steelGolem;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import nokunami.elementus.client.model.mob.SteelGolemModel;
import nokunami.elementus.common.entity.living.SteelGolem;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class SteelGolemEyesLayer extends RenderLayer<SteelGolem, SteelGolemModel<SteelGolem>> {

    public SteelGolemEyesLayer(RenderLayerParent<SteelGolem, SteelGolemModel<SteelGolem>> pRenderer) {
        super(pRenderer);
    }

    public void render(@NotNull PoseStack pPoseStack, @NotNull MultiBufferSource pBuffer, int pPackedLight, SteelGolem pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {

    }
}
