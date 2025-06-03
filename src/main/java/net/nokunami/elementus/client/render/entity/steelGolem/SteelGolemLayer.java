package net.nokunami.elementus.client.render.entity.steelGolem;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.nokunami.elementus.client.model.ModModelLayers;
import net.nokunami.elementus.client.model.mob.SteelGolemModel;
import net.nokunami.elementus.client.render.ModRenderType;
import net.nokunami.elementus.common.entity.living.SteelGolem;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

import static net.nokunami.elementus.Elementus.MODID;
import static net.nokunami.elementus.Elementus.modLoc;

@OnlyIn(Dist.CLIENT)
public class SteelGolemLayer extends RenderLayer<SteelGolem, SteelGolemModel<SteelGolem>> {
    private static final Map<SteelGolem.Crackiness, ResourceLocation> GOLEM_CRACKS = ImmutableMap.of(
            SteelGolem.Crackiness.LOW, new ResourceLocation(MODID, "textures/entity/golem/steel_golem/steel_golem_crackiness_low.png"),
            SteelGolem.Crackiness.MEDIUM, new ResourceLocation(MODID, "textures/entity/golem/steel_golem/steel_golem_crackiness_medium.png"),
            SteelGolem.Crackiness.HIGH, new ResourceLocation(MODID, "textures/entity/golem/steel_golem/steel_golem_crackiness_high.png")
    );
    private static final Map<SteelGolem.ChassisCrackiness, ResourceLocation> CHASSIS_CRACKS = ImmutableMap.of(
            SteelGolem.ChassisCrackiness.VERYLOW, new ResourceLocation(MODID, "textures/entity/golem/steel_golem/steel_golem_chassis_crackiness_very_low.png"),
            SteelGolem.ChassisCrackiness.LOW, new ResourceLocation(MODID, "textures/entity/golem/steel_golem/steel_golem_chassis_crackiness_low.png"),
            SteelGolem.ChassisCrackiness.MEDIUM, new ResourceLocation(MODID, "textures/entity/golem/steel_golem/steel_golem_chassis_crackiness_medium.png"),
            SteelGolem.ChassisCrackiness.HIGH, new ResourceLocation(MODID, "textures/entity/golem/steel_golem/steel_golem_chassis_crackiness_high.png")
    );

    public SteelGolemLayer(RenderLayerParent<SteelGolem, SteelGolemModel<SteelGolem>> render) {
        super(render);
    }

    @Override
    public void render(@NotNull PoseStack poseStack, @NotNull MultiBufferSource buffer, int packedLight, SteelGolem golem, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {
        int fullBright = 15728880;
        float eyeRGB = 1;
        if (golem.isChassisBroken()) {
            eyeRGB = Mth.clamp((float)Math.cos((golem.eyeLayerTick + partialTick) * 0.1F) - 0.125F, -0.25F, 1.0F);
        }
        eyeRGB += Mth.lerp(partialTick, golem.eyeOldLayerBrightness, golem.eyeLayerBrightness) * 1.0F * (float)Math.PI;
        eyeRGB = Mth.clamp(eyeRGB, 0.0F, 1.0F);
        if (!golem.isInvisible()) {
            String location = golem.getAggroState() ? "aggressive" : "neutral";
            ResourceLocation eyeTexture = new ResourceLocation(MODID, "textures/entity/golem/steel_golem/steel_golem_" + location + ".png");
            RenderType renderLayer = RenderType.entityTranslucentEmissive(eyeTexture);
            VertexConsumer vertex = buffer.getBuffer(renderLayer);
            this.getParentModel().renderToBuffer(poseStack, vertex, fullBright, OverlayTexture.NO_OVERLAY, eyeRGB, eyeRGB, eyeRGB, eyeRGB);

            SteelGolem.Crackiness steelgolem$crackiness = golem.getCrackiness();
            if (steelgolem$crackiness != SteelGolem.Crackiness.NONE) {
                ResourceLocation golemCracks = GOLEM_CRACKS.get(steelgolem$crackiness);
                renderColoredCutoutModel(this.getParentModel(), golemCracks, poseStack, buffer, packedLight, golem, 1.0F, 1.0F, 1.0F);
            }

            SteelGolem.ChassisCrackiness steelgolem$chassis = golem.getChassisCrackiness();
            if (steelgolem$chassis != SteelGolem.ChassisCrackiness.NONE) {
                ResourceLocation chassisCracks = CHASSIS_CRACKS.get(steelgolem$chassis);
                renderColoredCutoutModel(this.getParentModel(), chassisCracks, poseStack, buffer, packedLight, golem, 1.0F, 1.0F, 1.0F);
            }

            if (golem.getMossStage() > 0) {
                ResourceLocation mossTexture = modLoc("textures/entity/golem/steel_golem/moss/steel_golem_moss_" + golem.getMossStage() + ".png");
                renderColoredCutoutModel(this.getParentModel(), mossTexture, poseStack, buffer, packedLight, golem, 1, 1, 1);
            }
        }
    }
}
