package net.nokunami.elementus.client.model;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.nokunami.elementus.client.model.armor.ExtendedArmorModel;

@OnlyIn(Dist.CLIENT)
public class ModModelLayers {

    public static final ModelLayerLocation EXTENDED_ARMOR_MODEL = createLocation("extended_armor_model", "main");
    public static final ModelLayerLocation EXTENDED_ARMOR_MODEL_LEGS = createLocation("extended_armor_model_leg", "main");

    public ModModelLayers() {
    }

    public static void register(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(EXTENDED_ARMOR_MODEL, () -> {
            return ExtendedArmorModel.createArmorLayer(new CubeDeformation(1.0F));
        });
        event.registerLayerDefinition(EXTENDED_ARMOR_MODEL_LEGS, () -> {
            return ExtendedArmorModel.createArmorLayer(new CubeDeformation(0.4F));
        });
    }

    private static ModelLayerLocation createLocation(String model, String layer) {
        return new ModelLayerLocation(new ResourceLocation("elementus", model), layer);
    }
}
