package net.nokunami.elementus.client.render.entity.steelGolem;

import com.google.common.collect.ImmutableMap;
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

import java.util.Map;

import static net.nokunami.elementus.Elementus.MODID;

@OnlyIn(Dist.CLIENT)
public class SteelGolemChassisCrackinessLayer extends RenderLayer<SteelGolem, SteelGolemModel<SteelGolem>> {
    private static final Map<SteelGolem.ChassisCrackiness, ResourceLocation> CHASSIS_CRACKS = ImmutableMap.of(
            SteelGolem.ChassisCrackiness.VERYLOW, new ResourceLocation(MODID, "textures/entity/golem/steel_golem/steel_golem_chassis_crackiness_very_low.png"),
            SteelGolem.ChassisCrackiness.LOW, new ResourceLocation(MODID, "textures/entity/golem/steel_golem/steel_golem_chassis_crackiness_low.png"),
            SteelGolem.ChassisCrackiness.MEDIUM, new ResourceLocation(MODID, "textures/entity/golem/steel_golem/steel_golem_chassis_crackiness_medium.png"),
            SteelGolem.ChassisCrackiness.HIGH, new ResourceLocation(MODID, "textures/entity/golem/steel_golem/steel_golem_chassis_crackiness_high.png")
    );

    public SteelGolemChassisCrackinessLayer(RenderLayerParent<SteelGolem, SteelGolemModel<SteelGolem>> pRenderer) {
        super(pRenderer);
    }

    public void render(@NotNull PoseStack pPoseStack, @NotNull MultiBufferSource pBuffer, int pPackedLight, SteelGolem pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        if (!pLivingEntity.isInvisible()) {
            SteelGolem.ChassisCrackiness steelgolem$chassis = pLivingEntity.getChassisCrackiness();
            if (steelgolem$chassis != SteelGolem.ChassisCrackiness.NONE) {
                ResourceLocation resourcelocation = CHASSIS_CRACKS.get(steelgolem$chassis);
                renderColoredCutoutModel(this.getParentModel(), resourcelocation, pPoseStack, pBuffer, pPackedLight, pLivingEntity, 1.0F, 1.0F, 1.0F);
            }
        }
    }
}
