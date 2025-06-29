package nokunami.elementus.client.model;

import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import nokunami.elementus.client.model.armor.*;
import nokunami.elementus.client.model.armor.ironsSpellbooks.AnthektiteMageArmorModel;
import nokunami.elementus.client.model.armor.ironsSpellbooks.DiarkriteMageArmorModel;
import nokunami.elementus.client.model.armor.samuraiDynasty.SDSamuraiArmorModel;
import nokunami.elementus.client.model.armor.samuraiDynasty.SDSamuraiLightArmorModel;
import nokunami.elementus.client.model.armor.samuraiDynasty.SDSamuraiMasterArmorModel;
import nokunami.elementus.client.model.armor.sniffsWeapons.ClothedCuirassModel;
import nokunami.elementus.client.model.armor.sniffsWeapons.HornedArmorModel;
import nokunami.elementus.client.model.armor.sniffsWeapons.SamuraiArmorModel;
import nokunami.elementus.client.model.armor.sniffsWeapons.StylishArmorModel;
import nokunami.elementus.client.model.mob.*;
import nokunami.elementus.client.model.projectile.AnthektiteSlashModel;

import static nokunami.elementus.Elementus.MODID;

@OnlyIn(Dist.CLIENT)
public class ModModelLayers {

    public static final ModelLayerLocation EXTENDED_ARMOR_MODEL = createLocation("extended_armor_model");
    public static final ModelLayerLocation EXTENDED_ARMOR_MODEL_LEGS = createLocation("extended_armor_model_leg");

    public static final ModelLayerLocation CATALYST_ARMOR_MODEL = createLocation("catalyst_armor_model");
    public static final ModelLayerLocation CATALYST_BASE_ELYTRA_MODEL = createLocation("catalyst_base_elytra_model");
    public static final ModelLayerLocation CATALYST_ELYTRA_MODEL = createLocation("catalyst_elytra_model");
    public static final ModelLayerLocation CATALYST_ELYTRA_OVERLAY_MODEL = createLocation("catalyst_elytra_overlay_model");

    public static final ModelLayerLocation DIARKRITE_MAGE_ARMOR_MODEL = createLocation("diarkrite_mage_armor_model");
    public static final ModelLayerLocation DIARKRITE_MAGE_ARMOR_MODEL_LEGS = createLocation("diarkrite_mage_armor_model_leg");

    public static final ModelLayerLocation ANTHEKTITE_MAGE_ARMOR_MODEL = createLocation("anthektite_mage_armor_model");
    public static final ModelLayerLocation ANTHEKTITE_MAGE_ARMOR_MODEL_LEGS = createLocation("anthektite_mage_armor_model_leg");

    public static final ModelLayerLocation STYLISH_ARMOR_MODEL = createLocation("sniffs_weapons_stylish_amor_model");
    public static final ModelLayerLocation HORNED_ARMOR_MODEL = createLocation("sniffs_weapons_horned_amor_model");
    public static final ModelLayerLocation SAMURAI_ARMOR_MODEL = createLocation("sniffs_weapons_samurai_amor_model");
    public static final ModelLayerLocation CLOTHED_CUIRASS_MODEL = createLocation("sniffs_weapons_clothed_cuirass_model");

    public static final ModelLayerLocation SD_SAMURAI_ARMOR_MODEL = createLocation("samurai_armor_model");
    public static final ModelLayerLocation SD_SAMURAI_ARMOR_MODEL_LEGS = createLocation("samurai_armor_model_leg");

    public static final ModelLayerLocation SD_SAMURAI_LIGHT_ARMOR_MODEL = createLocation("samurai_light_armor_model");
    public static final ModelLayerLocation SD_SAMURAI_LIGHT_ARMOR_MODEL_LEGS = createLocation("samurai_light_armor_model_leg");

    public static final ModelLayerLocation SD_SAMURAI_MASTER_ARMOR_MODEL = createLocation("samurai_master_armor_model");
    public static final ModelLayerLocation SD_SAMURAI_MASTER_ARMOR_MODEL_LEGS = createLocation("samurai_master_armor_model_leg");


    public static final ModelLayerLocation MOVCADIA_BOAT_LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(MODID, "boat/movcadia"), "main");
    public static final ModelLayerLocation MOVCADIA_CHEST_BOAT_LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(MODID, "chest_boat/movcadia"), "main");

    public static final ModelLayerLocation STEEL_GOLEM = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(MODID, "steel_golem"), "main");
    public static final ModelLayerLocation STEEL_GOLEM_CARPET = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(MODID, "steel_golem_carpet"), "main");
    public static final ModelLayerLocation STEEL_GOLEM_EXTRA_1 = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(MODID, "steel_golem_extra_1"), "main");
    public static final ModelLayerLocation STEEL_GOLEM_EXTRA_1S = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(MODID, "steel_golem_extra_1s"), "main");
    public static final ModelLayerLocation STEEL_GOLEM_EXTRA_2 = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(MODID, "steel_golem_extra_2"), "main");
    public static final ModelLayerLocation STEEL_GOLEM_EXTRA_2S = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(MODID, "steel_golem_extra_2s"), "main");
    public static final ModelLayerLocation STEEL_GOLEM_EXTRA_3 = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(MODID, "steel_golem_extra_3"), "main");
    public static final ModelLayerLocation STEEL_GOLEM_EXTRA_3S = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(MODID, "steel_golem_extra_3s"), "main");
    public static final ModelLayerLocation STEEL_GOLEM_SADDLE = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(MODID, "steel_golem_saddle"), "main");
    public static final ModelLayerLocation STEEL_GOLEM_ARMOR = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(MODID, "steel_golem_armor"), "main");
    public static final ModelLayerLocation STEEL_GOLEM_CHEST = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(MODID, "steel_golem_chest"), "main");

    public static final ModelLayerLocation ANTHEKTITE_SLASH = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(MODID, "anthektite_slash"), "main");


    public static void register(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(EXTENDED_ARMOR_MODEL, () -> ExtendedArmorModel.createArmorLayer(new CubeDeformation(1.0F)));
        event.registerLayerDefinition(EXTENDED_ARMOR_MODEL_LEGS, () -> ExtendedArmorModel.createArmorLayer(new CubeDeformation(0.5F)));
        event.registerLayerDefinition(CATALYST_ARMOR_MODEL, () -> CatalystBaseModel.createArmorLayer(new CubeDeformation(1.0F)));
        event.registerLayerDefinition(CATALYST_BASE_ELYTRA_MODEL, CatalystElytraModel::createBaseLayer);
        event.registerLayerDefinition(CATALYST_ELYTRA_MODEL, CatalystElytraModel::createCatalystLayer);
        event.registerLayerDefinition(CATALYST_ELYTRA_OVERLAY_MODEL, CatalystElytraModel::createCatalystOverlayLayer);
        event.registerLayerDefinition(DIARKRITE_MAGE_ARMOR_MODEL, () -> DiarkriteMageArmorModel.createArmorLayer(new CubeDeformation(1.0F)));
        event.registerLayerDefinition(DIARKRITE_MAGE_ARMOR_MODEL_LEGS, () -> DiarkriteMageArmorModel.createArmorLayer(new CubeDeformation(0.5F)));
        event.registerLayerDefinition(ANTHEKTITE_MAGE_ARMOR_MODEL, () -> AnthektiteMageArmorModel.createArmorLayer(new CubeDeformation(1.0F)));
        event.registerLayerDefinition(ANTHEKTITE_MAGE_ARMOR_MODEL_LEGS, () -> AnthektiteMageArmorModel.createArmorLayer(new CubeDeformation(0.3F)));
        event.registerLayerDefinition(STYLISH_ARMOR_MODEL, () -> StylishArmorModel.createArmorLayer(new CubeDeformation(1.0F)));
        event.registerLayerDefinition(HORNED_ARMOR_MODEL, () -> HornedArmorModel.createArmorLayer(new CubeDeformation(1.0F)));
        event.registerLayerDefinition(SAMURAI_ARMOR_MODEL, () -> SamuraiArmorModel.createArmorLayer(new CubeDeformation(1.0F)));
        event.registerLayerDefinition(CLOTHED_CUIRASS_MODEL, () -> ClothedCuirassModel.createArmorLayer(new CubeDeformation(1.0F)));
        event.registerLayerDefinition(SD_SAMURAI_ARMOR_MODEL, () -> SDSamuraiArmorModel.createArmorLayer(new CubeDeformation(1.0F)));
        event.registerLayerDefinition(SD_SAMURAI_ARMOR_MODEL_LEGS, () -> SDSamuraiArmorModel.createArmorLayer(new CubeDeformation(0.4F)));
        event.registerLayerDefinition(SD_SAMURAI_LIGHT_ARMOR_MODEL, () -> SDSamuraiLightArmorModel.createArmorLayer(new CubeDeformation(1.0F)));
        event.registerLayerDefinition(SD_SAMURAI_LIGHT_ARMOR_MODEL_LEGS, () -> SDSamuraiLightArmorModel.createArmorLayer(new CubeDeformation(0.4F)));
        event.registerLayerDefinition(SD_SAMURAI_MASTER_ARMOR_MODEL, () -> SDSamuraiMasterArmorModel.createArmorLayer(new CubeDeformation(1.0F)));
        event.registerLayerDefinition(SD_SAMURAI_MASTER_ARMOR_MODEL_LEGS, () -> SDSamuraiMasterArmorModel.createArmorLayer(new CubeDeformation(0.4F)));

        event.registerLayerDefinition(MOVCADIA_BOAT_LAYER, BoatModel::createBodyModel);
        event.registerLayerDefinition(MOVCADIA_CHEST_BOAT_LAYER, ChestBoatModel::createBodyModel);

        event.registerLayerDefinition(STEEL_GOLEM, SteelGolemModel::createBodyLayer);
        event.registerLayerDefinition(STEEL_GOLEM_CARPET, SteelGolemModel::createBodyCarpetLayer);
        event.registerLayerDefinition(STEEL_GOLEM_EXTRA_1, () -> SteelGolemModel.createExtraLayer1(new CubeDeformation(0)));
        event.registerLayerDefinition(STEEL_GOLEM_EXTRA_1S, () -> SteelGolemModel.createExtraLayer1(new CubeDeformation(0.25F)));
        event.registerLayerDefinition(STEEL_GOLEM_EXTRA_2, () -> SteelGolemModel.createExtraLayer2(new CubeDeformation(0.0F)));
        event.registerLayerDefinition(STEEL_GOLEM_EXTRA_2S, () -> SteelGolemModel.createExtraLayer2(new CubeDeformation(0.25F)));
        event.registerLayerDefinition(STEEL_GOLEM_EXTRA_3, () -> SteelGolemModel.createExtraLayer3(new CubeDeformation(0.0F)));
        event.registerLayerDefinition(STEEL_GOLEM_EXTRA_3S, () -> SteelGolemModel.createExtraLayer3(new CubeDeformation(0.25F)));
        event.registerLayerDefinition(STEEL_GOLEM_SADDLE, SteelGolemSaddleModel::createSaddleLayer);
        event.registerLayerDefinition(STEEL_GOLEM_ARMOR, SteelGolemArmorModel::createBodyLayer);
        event.registerLayerDefinition(STEEL_GOLEM_CHEST, SteelGolemChestModel::createChestLayer);

        event.registerLayerDefinition(ANTHEKTITE_SLASH, AnthektiteSlashModel::createLayer);
    }

    private static ModelLayerLocation createLocation(String model) {
        return new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MODID, model), "main");
    }
}