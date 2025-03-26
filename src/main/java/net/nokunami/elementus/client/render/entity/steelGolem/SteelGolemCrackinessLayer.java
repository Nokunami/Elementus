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
public class SteelGolemCrackinessLayer extends RenderLayer<SteelGolem, SteelGolemModel<SteelGolem>> {
    private static final Map<SteelGolem.Crackiness, ResourceLocation> resourceLocations = ImmutableMap.of(
            SteelGolem.Crackiness.LOW, new ResourceLocation(MODID, "textures/entity/golem/steel_golem/steel_golem_crackiness_low.png"),
            SteelGolem.Crackiness.MEDIUM, new ResourceLocation(MODID, "textures/entity/golem/steel_golem/steel_golem_crackiness_medium.png"),
            SteelGolem.Crackiness.HIGH, new ResourceLocation(MODID, "textures/entity/golem/steel_golem/steel_golem_crackiness_high.png")
//            SteelGolem.Crackiness.LOW, new ResourceLocation("textures/entity/iron_golem/iron_golem_crackiness_low.png"),
//            SteelGolem.Crackiness.MEDIUM, new ResourceLocation("textures/entity/iron_golem/iron_golem_crackiness_medium.png"),
//            SteelGolem.Crackiness.HIGH, new ResourceLocation("textures/entity/iron_golem/iron_golem_crackiness_high.png")
    );

    public SteelGolemCrackinessLayer(RenderLayerParent<SteelGolem, SteelGolemModel<SteelGolem>> pRenderer) {
        super(pRenderer);
    }

    public void render(@NotNull PoseStack pPoseStack, @NotNull MultiBufferSource pBuffer, int pPackedLight, SteelGolem pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        if (!pLivingEntity.isInvisible()) {
            SteelGolem.Crackiness steelgolem$crackiness = pLivingEntity.getCrackiness();
            if (steelgolem$crackiness != SteelGolem.Crackiness.NONE) {
                ResourceLocation resourcelocation = resourceLocations.get(steelgolem$crackiness);
                renderColoredCutoutModel(this.getParentModel(), resourcelocation, pPoseStack, pBuffer, pPackedLight, pLivingEntity, 1.0F, 1.0F, 1.0F);
            }
        }
    }
}
