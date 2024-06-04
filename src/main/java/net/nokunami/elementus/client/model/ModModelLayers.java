package net.nokunami.elementus.client.model;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.nokunami.elementus.client.model.armor.*;

@OnlyIn(Dist.CLIENT)
public class ModModelLayers {

    public static final ModelLayerLocation EXTENDED_ARMOR_MODEL = createLocation("extended_armor_model", "main");
    public static final ModelLayerLocation EXTENDED_ARMOR_MODEL_LEGS = createLocation("extended_armor_model_leg", "main");

    public static final ModelLayerLocation DIARKRITE_MAGE_ARMOR_MODEL = createLocation("diarkrite_mage_armor_model", "main");
    public static final ModelLayerLocation DIARKRITE_MAGE_ARMOR_MODEL_LEGS = createLocation("diarkrite_mage_armor_model_leg", "main");

    public static final ModelLayerLocation ANTHEKTITE_MAGE_ARMOR_MODEL = createLocation("anthektite_mage_armor_model", "main");
    public static final ModelLayerLocation ANTHEKTITE_MAGE_ARMOR_MODEL_LEGS = createLocation("anthektite_mage_armor_model_leg", "main");

    public static final ModelLayerLocation SAMURAI_ARMOR_MODEL = createLocation("samurai_armor_model", "main");
    public static final ModelLayerLocation SAMURAI_ARMOR_MODEL_LEGS = createLocation("samurai_armor_model_leg", "main");

    public static final ModelLayerLocation SAMURAI_LIGHT_ARMOR_MODEL = createLocation("samurai_light_armor_model", "main");
    public static final ModelLayerLocation SAMURAI_LIGHT_ARMOR_MODEL_LEGS = createLocation("samurai_light_armor_model_leg", "main");

    public static final ModelLayerLocation SAMURAI_MASTER_ARMOR_MODEL = createLocation("samurai_master_armor_model", "main");
    public static final ModelLayerLocation SAMURAI_MASTER_ARMOR_MODEL_LEGS = createLocation("samurai_master_armor_model_leg", "main");

    public ModModelLayers() {
    }

    public static void register(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(EXTENDED_ARMOR_MODEL, () -> {
            return ExtendedArmorModel.createArmorLayer(new CubeDeformation(1.0F));
        });
        event.registerLayerDefinition(EXTENDED_ARMOR_MODEL_LEGS, () -> {
            return ExtendedArmorModel.createArmorLayer(new CubeDeformation(0.4F));
        });
        event.registerLayerDefinition(DIARKRITE_MAGE_ARMOR_MODEL, () -> {
            return DiarkriteMageArmorModel.createArmorLayer(new CubeDeformation(1.0F));
        });
        event.registerLayerDefinition(DIARKRITE_MAGE_ARMOR_MODEL_LEGS, () -> {
            return DiarkriteMageArmorModel.createArmorLayer(new CubeDeformation(0.4F));
        });
        event.registerLayerDefinition(ANTHEKTITE_MAGE_ARMOR_MODEL, () -> {
            return AnthektiteMageArmorModel.createArmorLayer(new CubeDeformation(1.0F));
        });
        event.registerLayerDefinition(ANTHEKTITE_MAGE_ARMOR_MODEL_LEGS, () -> {
            return AnthektiteMageArmorModel.createArmorLayer(new CubeDeformation(0.4F));
        });
        event.registerLayerDefinition(SAMURAI_ARMOR_MODEL, () -> {
            return SamuraiArmorModel.createArmorLayer(new CubeDeformation(1.0F));
        });
        event.registerLayerDefinition(SAMURAI_ARMOR_MODEL_LEGS, () -> {
            return SamuraiArmorModel.createArmorLayer(new CubeDeformation(0.4F));
        });
        event.registerLayerDefinition(SAMURAI_LIGHT_ARMOR_MODEL, () -> {
            return SamuraiLightArmorModel.createArmorLayer(new CubeDeformation(1.0F));
        });
        event.registerLayerDefinition(SAMURAI_LIGHT_ARMOR_MODEL_LEGS, () -> {
            return SamuraiLightArmorModel.createArmorLayer(new CubeDeformation(0.4F));
        });
        event.registerLayerDefinition(SAMURAI_MASTER_ARMOR_MODEL, () -> {
            return SamuraiMasterArmorModel.createArmorLayer(new CubeDeformation(1.0F));
        });
        event.registerLayerDefinition(SAMURAI_MASTER_ARMOR_MODEL_LEGS, () -> {
            return SamuraiMasterArmorModel.createArmorLayer(new CubeDeformation(0.4F));
        });
    }

    private static ModelLayerLocation createLocation(String model, String layer) {
        return new ModelLayerLocation(new ResourceLocation("elementus", model), layer);
    }
}