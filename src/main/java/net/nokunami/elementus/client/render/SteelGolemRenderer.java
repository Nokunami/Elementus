package net.nokunami.elementus.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.nokunami.elementus.client.model.ModModelLayers;
import net.nokunami.elementus.client.model.mob.SteelGolemModel;
import net.nokunami.elementus.common.entity.living.SteelGolem;
import org.jetbrains.annotations.NotNull;

import static net.nokunami.elementus.Elementus.MODID;

public class SteelGolemRenderer extends MobRenderer<SteelGolem, SteelGolemModel<SteelGolem>> {
    private static final ResourceLocation GOLEM_LOCATION = new ResourceLocation(MODID, "textures/entity/golem/steel_golem/steel_golem.png");

    public SteelGolemRenderer(EntityRendererProvider.Context context) {
        super(context, new SteelGolemModel<>(context.bakeLayer(ModModelLayers.STEEL_GOLEM)), 0.7F);
        this.addLayer(new SteelGolemEyesLayer(this));
        this.addLayer(new SteelGolemChassisCrackinessLayer(this));
        this.addLayer(new SteelGolemCrackinessLayer(this));
        this.addLayer(new SteelGolemSaddleLayer(this, context.getModelSet()));
        this.addLayer(new SteelGolemLeavesLayer(this, context.getModelSet()));
        this.addLayer(new SteelGolemCarpetLayer(this, context.getModelSet()));
    }

    public @NotNull ResourceLocation getTextureLocation(@NotNull SteelGolem pEntity) {
        return GOLEM_LOCATION;
    }

    protected void setupRotations(@NotNull SteelGolem pEntityLiving, @NotNull PoseStack pPoseStack, float pAgeInTicks, float pRotationYaw, float pPartialTicks) {
        super.setupRotations(pEntityLiving, pPoseStack, pAgeInTicks, pRotationYaw, pPartialTicks);
        if (!((double)pEntityLiving.walkAnimation.speed() < 0.01D)) {
            float f = 13.0F;
            float f1 = pEntityLiving.walkAnimation.position(pPartialTicks) + 6.0F;
            float f2 = (Math.abs(f1 % 13.0F - 6.5F) - 3.25F) / 3.25F;
            pPoseStack.mulPose(Axis.ZP.rotationDegrees(6.5F * f2));
        }
    }
}
