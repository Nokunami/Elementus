package net.nokunami.elementus.client.render.entity.astaliteGolem.longarm;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.nokunami.elementus.client.model.geom.EModelLayers;
import net.nokunami.elementus.client.model.mob.AstaliteGolemLongarmModel;
import net.nokunami.elementus.common.entity.living.AstaliteGolemLongarm;
import org.jetbrains.annotations.NotNull;

import static net.nokunami.elementus.Elementus.modLoc;

public class AstaliteGolemLongarmRenderer<T extends AstaliteGolemLongarm> extends MobRenderer<T, AstaliteGolemLongarmModel<T>> {
    private static final ResourceLocation GOLEM_LOCATION = modLoc("textures/entity/golem/astalite_golem/longarm/astalite_golem_longarm.png");

    public AstaliteGolemLongarmRenderer(EntityRendererProvider.Context context) {
        super(context, new AstaliteGolemLongarmModel<>(context.bakeLayer(EModelLayers.ASTALITE_GOLEM_LONGARM)), 0.7F);
        addLayer(new AstaliteGolemLongarmLayer<>(this));
//        this.addLayer(new SteelGolemSaddleLayer(this, context.getModelSet()));
//        this.addLayer(new SteelGolemLeavesLayer(this, context.getModelSet()));
//        this.addLayer(new SteelGolemCarpetLayer(this, context.getModelSet()));
//        this.addLayer(new SteelGolemArmorLayer(this, context.getModelSet()));
//        this.addLayer(new SteelGolemChestLayer(this, context.getModelSet()));
    }

    @Override public @NotNull ResourceLocation getTextureLocation(@NotNull T pEntity) { return GOLEM_LOCATION; }
}
