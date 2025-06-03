package net.nokunami.elementus.client.render.entity.steelGolem;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.nokunami.elementus.client.model.ModModelLayers;
import net.nokunami.elementus.client.model.mob.SteelGolemModel;
import net.nokunami.elementus.common.entity.living.SteelGolem;
import org.jetbrains.annotations.NotNull;

import static net.nokunami.elementus.Elementus.MODID;

@OnlyIn(Dist.CLIENT)
public class SteelGolemRenderer extends MobRenderer<SteelGolem, SteelGolemModel<SteelGolem>> {
    private static final ResourceLocation GOLEM_LOCATION = new ResourceLocation(MODID, "textures/entity/golem/steel_golem/steel_golem.png");

    public SteelGolemRenderer(EntityRendererProvider.Context context) {
        super(context, new SteelGolemModel<>(context.bakeLayer(ModModelLayers.STEEL_GOLEM)), 0.7F);
        this.addLayer(new SteelGolemLayer(this));
        this.addLayer(new SteelGolemSaddleLayer(this, context.getModelSet()));
        this.addLayer(new SteelGolemLeavesLayer(this, context.getModelSet()));
        this.addLayer(new SteelGolemCarpetLayer(this, context.getModelSet()));
        this.addLayer(new SteelGolemArmorLayer(this, context.getModelSet()));
        this.addLayer(new SteelGolemChestLayer(this, context.getModelSet()));
    }

    public @NotNull ResourceLocation getTextureLocation(@NotNull SteelGolem pEntity) {
        return GOLEM_LOCATION;
    }
}
