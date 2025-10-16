package net.nokunami.elementus.client.render.entity.steelGolem;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.nokunami.elementus.client.model.ModModelLayers;
import net.nokunami.elementus.client.model.mob.AstaliteGolemModel;
import net.nokunami.elementus.client.model.mob.SteelGolemModel;
import net.nokunami.elementus.common.entity.living.AstaliteGolem;
import org.jetbrains.annotations.NotNull;

import static net.nokunami.elementus.Elementus.MODID;

@OnlyIn(Dist.CLIENT)
public class SteelGolemRenderer<T extends AstaliteGolem> extends MobRenderer<T, AstaliteGolemModel<T>> {
    private static final ResourceLocation GOLEM_LOCATION = new ResourceLocation(MODID, "textures/entity/golem/astalite_golem/astalite_golem.png");

    public SteelGolemRenderer(EntityRendererProvider.Context context) {
        super(context, new AstaliteGolemModel<>(context.bakeLayer(ModModelLayers.ASTALITE_GOLEM)), 0.7F);
//        this.addLayer(new SteelGolemLayer(this));
        this.addLayer(new AstaliteGolemLayer<>(this));
//        this.addLayer(new SteelGolemSaddleLayer(this, context.getModelSet()));
//        this.addLayer(new SteelGolemLeavesLayer(this, context.getModelSet()));
//        this.addLayer(new SteelGolemCarpetLayer(this, context.getModelSet()));
//        this.addLayer(new SteelGolemArmorLayer(this, context.getModelSet()));
//        this.addLayer(new SteelGolemChestLayer(this, context.getModelSet()));
    }

    public @NotNull ResourceLocation getTextureLocation(@NotNull AstaliteGolem pEntity) {
        return GOLEM_LOCATION;
    }
}
