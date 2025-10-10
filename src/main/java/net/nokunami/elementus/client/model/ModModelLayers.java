package net.nokunami.elementus.client.model;

import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.nokunami.elementus.client.model.armor.*;
import net.nokunami.elementus.client.model.mob.SteelGolemArmorModel;
import net.nokunami.elementus.client.model.mob.SteelGolemChestModel;
import net.nokunami.elementus.client.model.mob.SteelGolemModel;
import net.nokunami.elementus.client.model.mob.SteelGolemSaddleModel;
import net.nokunami.elementus.client.model.projectile.AnthektiteSlashModel;
import net.nokunami.elementus.client.model.projectile.SwordDanceSlashModel;

import static net.nokunami.elementus.Elementus.MODID;

@OnlyIn(Dist.CLIENT)
public class ModModelLayers {

    public static final ModelLayerLocation EXTENDED_ARMOR_MODEL = createLocation("extended_armor_model");
    public static final ModelLayerLocation EXTENDED_ARMOR_MODEL_LEGS = createLocation("extended_armor_model_leg");

    public static final ModelLayerLocation TEST_CATALYST_ARMOR_MODEL = createLocation("test_catalyst_armor_model");
    public static final ModelLayerLocation CATALYST_ARMOR_TRIM_MODEL = createLocation("catalyst_armor_trim_model");

    public static final ModelLayerLocation CATALYST_ARMOR_MODEL = createLocation("catalyst_armor_model");
    public static final ModelLayerLocation CATALYST_BASE_ELYTRA_MODEL = createLocation("catalyst_base_elytra_model");
    public static final ModelLayerLocation CATALYST_ELYTRA_MODEL = createLocation("catalyst_elytra_model");
    public static final ModelLayerLocation CATALYST_ELYTRA_OVERLAY_MODEL = createLocation("catalyst_elytra_overlay_model");

    public static final ModelLayerLocation MOVCADIA_BOAT_LAYER = createLocation("boat/movcadia");
    public static final ModelLayerLocation MOVCADIA_CHEST_BOAT_LAYER = createLocation("chest_boat/movcadia");

    public static final ModelLayerLocation STEEL_GOLEM = createLocation("steel_golem");
    public static final ModelLayerLocation STEEL_GOLEM_CARPET = createLocation("steel_golem_carpet");
    public static final ModelLayerLocation STEEL_GOLEM_EXTRA_1 = createLocation("steel_golem_extra_1");
    public static final ModelLayerLocation STEEL_GOLEM_EXTRA_1S = createLocation("steel_golem_extra_1s");
    public static final ModelLayerLocation STEEL_GOLEM_EXTRA_2 = createLocation("steel_golem_extra_2");
    public static final ModelLayerLocation STEEL_GOLEM_EXTRA_2S = createLocation("steel_golem_extra_2s");
    public static final ModelLayerLocation STEEL_GOLEM_EXTRA_3 = createLocation("steel_golem_extra_3");
    public static final ModelLayerLocation STEEL_GOLEM_EXTRA_3S = createLocation("steel_golem_extra_3s");
    public static final ModelLayerLocation STEEL_GOLEM_SADDLE = createLocation("steel_golem_saddle");
    public static final ModelLayerLocation STEEL_GOLEM_ARMOR = createLocation("steel_golem_armor");
    public static final ModelLayerLocation STEEL_GOLEM_CHEST = createLocation("steel_golem_chest");

    public static final ModelLayerLocation ANTHEKTITE_SLASH = createLocation("anthektite_slash");
    public static final ModelLayerLocation SWORD_DANCE_SLASH = createLocation("sword_dance_slash");


    public static void register(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(EXTENDED_ARMOR_MODEL, () -> ExtendedArmorModel.createArmorLayer(new CubeDeformation(1.0F)));
        event.registerLayerDefinition(EXTENDED_ARMOR_MODEL_LEGS, () -> ExtendedArmorModel.createArmorLayer(new CubeDeformation(0.5F)));
        event.registerLayerDefinition(TEST_CATALYST_ARMOR_MODEL, CatalystArmorModel::create);
        event.registerLayerDefinition(CATALYST_ARMOR_TRIM_MODEL, CatalystArmorTrimModel::create);
        event.registerLayerDefinition(CATALYST_ARMOR_MODEL, () -> CatalystBaseModel.createArmorLayer(new CubeDeformation(1.0F)));
        event.registerLayerDefinition(CATALYST_BASE_ELYTRA_MODEL, CatalystElytraModel::createBaseLayer);
        event.registerLayerDefinition(CATALYST_ELYTRA_MODEL, CatalystElytraModel::createCatalystLayer);
        event.registerLayerDefinition(CATALYST_ELYTRA_OVERLAY_MODEL, CatalystElytraModel::createCatalystOverlayLayer);

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
        event.registerLayerDefinition(SWORD_DANCE_SLASH, SwordDanceSlashModel::createLayer);
    }

    private static ModelLayerLocation createLocation(String model) {
        return new ModelLayerLocation(new ResourceLocation(MODID, model), "main");
    }
}