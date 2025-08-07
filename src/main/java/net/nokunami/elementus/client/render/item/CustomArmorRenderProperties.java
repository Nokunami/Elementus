package net.nokunami.elementus.client.render.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.nokunami.elementus.client.model.ModModelLayers;
import net.nokunami.elementus.client.model.armor.CatalystBaseModel;
import net.nokunami.elementus.client.model.armor.ExtendedArmorModel;
import net.nokunami.elementus.client.model.armor.ironsSpellbooks.AnthektiteMageArmorModel;
import net.nokunami.elementus.client.model.armor.ironsSpellbooks.DiarkriteMageArmorModel;
import net.nokunami.elementus.client.model.armor.samuraiDynasty.SDSamuraiArmorModel;
import net.nokunami.elementus.client.model.armor.samuraiDynasty.SDSamuraiLightArmorModel;
import net.nokunami.elementus.client.model.armor.samuraiDynasty.SDSamuraiMasterArmorModel;
import net.nokunami.elementus.client.model.armor.sniffsWeapons.ClothedCuirassModel;
import net.nokunami.elementus.client.model.armor.sniffsWeapons.HornedArmorModel;
import net.nokunami.elementus.client.model.armor.sniffsWeapons.SamuraiArmorModel;
import net.nokunami.elementus.client.model.armor.sniffsWeapons.StylishArmorModel;
import net.nokunami.elementus.common.registry.ModItems.ElementusItems;
import org.jetbrains.annotations.NotNull;

public class CustomArmorRenderProperties implements IClientItemExtensions {
    private static boolean init;
    public static ExtendedArmorModel EXTENDED_ARMOR_MODEL;
    public static ExtendedArmorModel EXTENDED_ARMOR_MODEL_LEGS;
    public static CatalystBaseModel<LivingEntity> CATALYST_ARMOR_MODEL;
    public static DiarkriteMageArmorModel DIARKRITE_MAGE_ARMOR_MODEL;
    public static DiarkriteMageArmorModel DIARKRITE_MAGE_ARMOR_MODEL_LEGS;
    public static AnthektiteMageArmorModel ANTHEKTITE_MAGE_ARMOR_MODEL;
    public static AnthektiteMageArmorModel ANTHEKTITE_MAGE_ARMOR_MODEL_LEGS;
    public static StylishArmorModel STYLISH_ARMOR_MODEL;
    public static HornedArmorModel HORNED_ARMOR_MODEL;
    public static SamuraiArmorModel SAMURAI_ARMOR_MODEL;
    public static ClothedCuirassModel CLOTHED_CUIRASS_MODEL;
    public static SDSamuraiArmorModel SD_SAMURAI_ARMOR_MODEL;
    public static SDSamuraiArmorModel SD_SAMURAI_ARMOR_MODEL_LEGS;
    public static SDSamuraiLightArmorModel SD_SAMURAI_LIGHT_ARMOR_MODEL;
    public static SDSamuraiLightArmorModel SD_SAMURAI_LIGHT_ARMOR_MODEL_LEGS;
    public static SDSamuraiMasterArmorModel SD_SAMURAI_MASTER_ARMOR_MODEL;
    public static SDSamuraiMasterArmorModel SD_SAMURAI_MASTER_ARMOR_MODEL_LEGS;

    public static void initializedModels() {
        init = true;
        EntityModelSet bake = Minecraft.getInstance().getEntityModels();
        EXTENDED_ARMOR_MODEL = new ExtendedArmorModel<>(bake.bakeLayer(ModModelLayers.EXTENDED_ARMOR_MODEL));
        EXTENDED_ARMOR_MODEL_LEGS = new ExtendedArmorModel<>(bake.bakeLayer(ModModelLayers.EXTENDED_ARMOR_MODEL_LEGS));
        CATALYST_ARMOR_MODEL = new CatalystBaseModel<>(bake.bakeLayer(ModModelLayers.CATALYST_ARMOR_MODEL));
        DIARKRITE_MAGE_ARMOR_MODEL = new DiarkriteMageArmorModel(bake.bakeLayer(ModModelLayers.DIARKRITE_MAGE_ARMOR_MODEL));
        DIARKRITE_MAGE_ARMOR_MODEL_LEGS = new DiarkriteMageArmorModel(bake.bakeLayer(ModModelLayers.DIARKRITE_MAGE_ARMOR_MODEL_LEGS));
        ANTHEKTITE_MAGE_ARMOR_MODEL = new AnthektiteMageArmorModel(bake.bakeLayer(ModModelLayers.ANTHEKTITE_MAGE_ARMOR_MODEL));
        ANTHEKTITE_MAGE_ARMOR_MODEL_LEGS = new AnthektiteMageArmorModel(bake.bakeLayer(ModModelLayers.ANTHEKTITE_MAGE_ARMOR_MODEL_LEGS));
        STYLISH_ARMOR_MODEL = new StylishArmorModel(bake.bakeLayer(ModModelLayers.STYLISH_ARMOR_MODEL));
        HORNED_ARMOR_MODEL = new HornedArmorModel(bake.bakeLayer(ModModelLayers.HORNED_ARMOR_MODEL));
        SAMURAI_ARMOR_MODEL = new SamuraiArmorModel(bake.bakeLayer(ModModelLayers.SAMURAI_ARMOR_MODEL));
        CLOTHED_CUIRASS_MODEL = new ClothedCuirassModel(bake.bakeLayer(ModModelLayers.CLOTHED_CUIRASS_MODEL));
        SD_SAMURAI_ARMOR_MODEL = new SDSamuraiArmorModel(bake.bakeLayer(ModModelLayers.SD_SAMURAI_ARMOR_MODEL));
        SD_SAMURAI_ARMOR_MODEL_LEGS = new SDSamuraiArmorModel(bake.bakeLayer(ModModelLayers.SD_SAMURAI_ARMOR_MODEL_LEGS));
        SD_SAMURAI_LIGHT_ARMOR_MODEL = new SDSamuraiLightArmorModel(bake.bakeLayer(ModModelLayers.SD_SAMURAI_LIGHT_ARMOR_MODEL));
        SD_SAMURAI_LIGHT_ARMOR_MODEL_LEGS = new SDSamuraiLightArmorModel(bake.bakeLayer(ModModelLayers.SD_SAMURAI_LIGHT_ARMOR_MODEL_LEGS));
        SD_SAMURAI_MASTER_ARMOR_MODEL = new SDSamuraiMasterArmorModel(bake.bakeLayer(ModModelLayers.SD_SAMURAI_MASTER_ARMOR_MODEL));
        SD_SAMURAI_MASTER_ARMOR_MODEL_LEGS = new SDSamuraiMasterArmorModel(bake.bakeLayer(ModModelLayers.SD_SAMURAI_MASTER_ARMOR_MODEL_LEGS));
    }

    public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
        if (!init) initializedModels();
        Item item = itemStack.getItem();

        if (item == ElementusItems.STEEL_HELMET.get() ||
                item == ElementusItems.STEEL_CHESTPLATE.get() ||
                item == ElementusItems.STEEL_BOOTS.get()||
                item == ElementusItems.DIARKRITE_HELMET.get() ||
                item == ElementusItems.DIARKRITE_CHESTPLATE.get() ||
                item == ElementusItems.DIARKRITE_BOOTS.get()||
                item == ElementusItems.ANTHEKTITE_HELMET.get() ||
                item == ElementusItems.ANTHEKTITE_CHESTPLATE.get() ||
                item == ElementusItems.ANTHEKTITE_BOOTS.get()) {
            return EXTENDED_ARMOR_MODEL;}
        if (item == ElementusItems.STEEL_LEGGINGS.get() ||
                item == ElementusItems.DIARKRITE_LEGGINGS.get() ||
                item == ElementusItems.ANTHEKTITE_LEGGINGS.get()) {
            return EXTENDED_ARMOR_MODEL_LEGS;
        }

        if ((item == ElementusItems.CATALYST_CHESTPLATE.get())) {
            return CATALYST_ARMOR_MODEL;
        }

        return original;
    }
}