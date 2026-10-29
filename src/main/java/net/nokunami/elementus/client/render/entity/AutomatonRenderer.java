package net.nokunami.elementus.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.nokunami.elementus.client.model.geom.EModelLayers;
import net.nokunami.elementus.client.model.mob.AstaliteGolemLongarmModel;
import net.nokunami.elementus.client.model.mob.AutomatonModel;
import net.nokunami.elementus.common.entity.living.AstaliteGolemLongarm;
import net.nokunami.elementus.common.entity.living.TamableGolem;
import org.jetbrains.annotations.NotNull;

import static net.nokunami.elementus.Elementus.modLoc;

public class AutomatonRenderer<T extends TamableGolem> extends MobRenderer<T, AutomatonModel<T>> {
    private static final ResourceLocation GOLEM_LOCATION = modLoc("textures/entity/golem/astalite_golem/longarm/astalite_golem_longarm.png");

    public AutomatonRenderer(EntityRendererProvider.Context context) {
        super(context, new AutomatonModel<>(context.bakeLayer(EModelLayers.AUTOMATON)), 0.7F);
    }

    @Override public @NotNull ResourceLocation getTextureLocation(@NotNull T pEntity) { return GOLEM_LOCATION; }
}
