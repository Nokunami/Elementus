package net.nokunami.elementus.client.render.entity.astaliteGolem;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.nokunami.elementus.client.model.geom.EModelLayers;
import net.nokunami.elementus.client.model.mob.AstaliteGolemCarrierModel;
import net.nokunami.elementus.common.entity.living.AstaliteGolemCarrier;
import org.jetbrains.annotations.NotNull;

import static net.nokunami.elementus.Elementus.modLoc;

@OnlyIn(Dist.CLIENT)
public class AstaliteGolemCarrierRenderer<T extends AstaliteGolemCarrier> extends MobRenderer<T, AstaliteGolemCarrierModel<T>> {
    private static final ResourceLocation GOLEM_LOCATION = modLoc("textures/entity/golem/astalite_golem/astalite_golem.png");

    public AstaliteGolemCarrierRenderer(EntityRendererProvider.Context context) {
        super(context, new AstaliteGolemCarrierModel<>(context.bakeLayer(EModelLayers.ASTALITE_GOLEM)), 0.7F);
        addLayer(new AstaliteGolemCarrierLayer<>(this));
//        this.addLayer(new SteelGolemSaddleLayer(this, context.getModelSet()));
//        this.addLayer(new SteelGolemLeavesLayer(this, context.getModelSet()));
//        this.addLayer(new SteelGolemCarpetLayer(this, context.getModelSet()));
//        this.addLayer(new SteelGolemArmorLayer(this, context.getModelSet()));
//        this.addLayer(new SteelGolemChestLayer(this, context.getModelSet()));
    }

    public @NotNull ResourceLocation getTextureLocation(@NotNull T entity) { return GOLEM_LOCATION; }
}
