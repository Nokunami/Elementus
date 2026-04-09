package net.nokunami.elementus.client.render.entity.fragmentedWither;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.nokunami.elementus.client.model.geom.EModelLayers;
import net.nokunami.elementus.client.model.mob.FragmentedWitherModel;
import net.nokunami.elementus.common.entity.boss.FragmentedWither;
import org.jetbrains.annotations.NotNull;

import static net.nokunami.elementus.Elementus.modLoc;

@OnlyIn(Dist.CLIENT)
public class FragmentedWitherRenderer<T extends FragmentedWither> extends MobRenderer<T, FragmentedWitherModel<T>> {
    private static final ResourceLocation GOLEM_LOCATION = modLoc("textures/entity/fragmented_wither/fragmented_wither.png");

    public FragmentedWitherRenderer(EntityRendererProvider.Context context) {
        super(context, new FragmentedWitherModel<>(context.bakeLayer(EModelLayers.FRAGMENTED_WITHER)), 1);
        addLayer(new FragmentedWitherLayer<>(this));
//        this.addLayer(new SteelGolemSaddleLayer(this, context.getModelSet()));
//        this.addLayer(new SteelGolemLeavesLayer(this, context.getModelSet()));
//        this.addLayer(new SteelGolemCarpetLayer(this, context.getModelSet()));
//        this.addLayer(new SteelGolemArmorLayer(this, context.getModelSet()));
//        this.addLayer(new SteelGolemChestLayer(this, context.getModelSet()));
    }

    public @NotNull ResourceLocation getTextureLocation(@NotNull T pEntity) {
        return GOLEM_LOCATION;
    }
}
