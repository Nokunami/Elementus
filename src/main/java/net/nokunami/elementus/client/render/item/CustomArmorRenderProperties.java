package net.nokunami.elementus.client.render.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.nokunami.elementus.client.model.ModModelLayers;
import net.nokunami.elementus.client.model.armor.*;
import net.nokunami.elementus.client.model.armor.ironsSpellbooks.AnthektiteMageArmorModel;
import net.nokunami.elementus.client.model.armor.ironsSpellbooks.DiarkriteMageArmorModel;
import net.nokunami.elementus.client.model.armor.samuraiDynasty.OldSDSamuraiArmorModel;
import net.nokunami.elementus.client.model.armor.samuraiDynasty.SDSamuraiArmorModel;
import net.nokunami.elementus.client.model.armor.samuraiDynasty.SDSamuraiLightArmorModel;
import net.nokunami.elementus.client.model.armor.samuraiDynasty.SDSamuraiMasterArmorModel;
import net.nokunami.elementus.client.model.armor.sniffsWeapons.ClothedCuirassModel;
import net.nokunami.elementus.client.model.armor.sniffsWeapons.HornedArmorModel;
import net.nokunami.elementus.client.model.armor.sniffsWeapons.SamuraiArmorModel;
import net.nokunami.elementus.client.model.armor.sniffsWeapons.StylishArmorModel;
import net.nokunami.elementus.common.registry.ModItems.*;
import org.jetbrains.annotations.NotNull;

import static net.nokunami.elementus.ModChecker.*;

public class CustomArmorRenderProperties implements IClientItemExtensions {
    private static boolean init;
    public static ExtendedArmorModel EXTENDED_ARMOR_MODEL;
    public static ExtendedArmorModel EXTENDED_ARMOR_MODEL_LEGS;
    public static CatalystBaseModel CATALYST_ARMOR_MODEL;
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
        EXTENDED_ARMOR_MODEL = new ExtendedArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModModelLayers.EXTENDED_ARMOR_MODEL));
        EXTENDED_ARMOR_MODEL_LEGS = new ExtendedArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModModelLayers.EXTENDED_ARMOR_MODEL_LEGS));
        CATALYST_ARMOR_MODEL = new CatalystBaseModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModModelLayers.CATALYST_ARMOR_MODEL));
        DIARKRITE_MAGE_ARMOR_MODEL = new DiarkriteMageArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModModelLayers.DIARKRITE_MAGE_ARMOR_MODEL));
        DIARKRITE_MAGE_ARMOR_MODEL_LEGS = new DiarkriteMageArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModModelLayers.DIARKRITE_MAGE_ARMOR_MODEL_LEGS));
        ANTHEKTITE_MAGE_ARMOR_MODEL = new AnthektiteMageArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModModelLayers.ANTHEKTITE_MAGE_ARMOR_MODEL));
        ANTHEKTITE_MAGE_ARMOR_MODEL_LEGS = new AnthektiteMageArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModModelLayers.ANTHEKTITE_MAGE_ARMOR_MODEL_LEGS));
        STYLISH_ARMOR_MODEL = new StylishArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModModelLayers.STYLISH_ARMOR_MODEL));
        HORNED_ARMOR_MODEL = new HornedArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModModelLayers.HORNED_ARMOR_MODEL));
        SAMURAI_ARMOR_MODEL = new SamuraiArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModModelLayers.SAMURAI_ARMOR_MODEL));
        CLOTHED_CUIRASS_MODEL = new ClothedCuirassModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModModelLayers.CLOTHED_CUIRASS_MODEL));
        SD_SAMURAI_ARMOR_MODEL = new SDSamuraiArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModModelLayers.SD_SAMURAI_ARMOR_MODEL));
        SD_SAMURAI_ARMOR_MODEL_LEGS = new SDSamuraiArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModModelLayers.SD_SAMURAI_ARMOR_MODEL_LEGS));
        SD_SAMURAI_LIGHT_ARMOR_MODEL = new SDSamuraiLightArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModModelLayers.SD_SAMURAI_LIGHT_ARMOR_MODEL));
        SD_SAMURAI_LIGHT_ARMOR_MODEL_LEGS = new SDSamuraiLightArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModModelLayers.SD_SAMURAI_LIGHT_ARMOR_MODEL_LEGS));
        SD_SAMURAI_MASTER_ARMOR_MODEL = new SDSamuraiMasterArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModModelLayers.SD_SAMURAI_MASTER_ARMOR_MODEL));
        SD_SAMURAI_MASTER_ARMOR_MODEL_LEGS = new SDSamuraiMasterArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModModelLayers.SD_SAMURAI_MASTER_ARMOR_MODEL_LEGS));
    }

    public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
        if (!init) { initializedModels(); }

        if (itemStack.getItem() == ElementusItems.STEEL_HELMET.get() ||
                itemStack.getItem() == ElementusItems.STEEL_CHESTPLATE.get() ||
                itemStack.getItem() == ElementusItems.STEEL_BOOTS.get()||
                itemStack.getItem() == ElementusItems.DIARKRITE_HELMET.get() ||
                itemStack.getItem() == ElementusItems.DIARKRITE_CHESTPLATE.get() ||
                itemStack.getItem() == ElementusItems.DIARKRITE_BOOTS.get()||
                itemStack.getItem() == ElementusItems.ANTHEKTITE_HELMET.get() ||
                itemStack.getItem() == ElementusItems.ANTHEKTITE_CHESTPLATE.get() ||
                itemStack.getItem() == ElementusItems.ANTHEKTITE_BOOTS.get()) {
            return EXTENDED_ARMOR_MODEL;}
        if (itemStack.getItem() == ElementusItems.STEEL_LEGGINGS.get() ||
                itemStack.getItem() == ElementusItems.DIARKRITE_LEGGINGS.get() ||
                itemStack.getItem() == ElementusItems.ANTHEKTITE_LEGGINGS.get()) {
            return EXTENDED_ARMOR_MODEL_LEGS;
        }

        if ((itemStack.getItem() == ElementusItems.CATALYST_CHESTPLATE.get())) {
            return CATALYST_ARMOR_MODEL;
        }
        if (sniffsWeapons) {
            if (itemStack.getItem() == SniffsWeaponsItems.STEEL_HELM.get() ||
                    itemStack.getItem() == SniffsWeaponsItems.STEEL_SURCOAT.get() ||
                    itemStack.getItem() == SniffsWeaponsItems.DIARKRITE_HELM.get() ||
                    itemStack.getItem() == SniffsWeaponsItems.DIARKRITE_SURCOAT.get() ||
                    itemStack.getItem() == SniffsWeaponsItems.ANTHEKTITE_HELM.get() ||
                    itemStack.getItem() == SniffsWeaponsItems.ANTHEKTITE_SURCOAT.get())
                return STYLISH_ARMOR_MODEL.animate(livingEntity, itemStack, equipmentSlot);
            if (itemStack.getItem() == SniffsWeaponsItems.STEEL_HORNED_HELM.get() ||
                    itemStack.getItem() == SniffsWeaponsItems.PLATED_STEEL_CHESTPLATE.get() ||
                    itemStack.getItem() == SniffsWeaponsItems.DIARKRITE_HORNED_HELM.get() ||
                    itemStack.getItem() == SniffsWeaponsItems.PLATED_DIARKRITE_CHESTPLATE.get() ||
                    itemStack.getItem() == SniffsWeaponsItems.ANTHEKTITE_HORNED_HELM.get() ||
                    itemStack.getItem() == SniffsWeaponsItems.PLATED_ANTHEKTITE_CHESTPLATE.get())
                return HORNED_ARMOR_MODEL.animate(livingEntity, itemStack, equipmentSlot);
            if (itemStack.getItem() == SniffsWeaponsItems.STEEL_KABUTO.get() ||
                    itemStack.getItem() == SniffsWeaponsItems.STEEL_DO.get() ||
                    itemStack.getItem() == SniffsWeaponsItems.DIARKRITE_KABUTO.get() ||
                    itemStack.getItem() == SniffsWeaponsItems.DIARKRITE_DO.get() ||
                    itemStack.getItem() == SniffsWeaponsItems.ANTHEKTITE_KABUTO.get() ||
                    itemStack.getItem() == SniffsWeaponsItems.ANTHEKTITE_DO.get())
                return SAMURAI_ARMOR_MODEL.animate(itemStack, equipmentSlot);
            if (itemStack.getItem() == SniffsWeaponsItems.CLOTHED_STEEL_CUIRASS.get() ||
                    itemStack.getItem() == SniffsWeaponsItems.CLOTHED_DIARKRITE_CUIRASS.get() ||
                    itemStack.getItem() == SniffsWeaponsItems.CLOTHED_ANTHEKTITE_CUIRASS.get())
                return CLOTHED_CUIRASS_MODEL;
        }
        if (advancedNetherite) {
            if (itemStack.getItem() == AdvancedNetheriteItems.DIARKRITE_IRON_HELMET.get() ||
                    itemStack.getItem() == AdvancedNetheriteItems.DIARKRITE_IRON_CHESTPLATE.get() ||
                    itemStack.getItem() == AdvancedNetheriteItems.DIARKRITE_IRON_BOOTS.get() ||
                    itemStack.getItem() == AdvancedNetheriteItems.DIARKRITE_GOLD_HELMET.get() ||
                    itemStack.getItem() == AdvancedNetheriteItems.DIARKRITE_GOLD_CHESTPLATE.get() ||
                    itemStack.getItem() == AdvancedNetheriteItems.DIARKRITE_GOLD_BOOTS.get() ||
                    itemStack.getItem() == AdvancedNetheriteItems.DIARKRITE_EMERALD_HELMET.get() ||
                    itemStack.getItem() == AdvancedNetheriteItems.DIARKRITE_EMERALD_CHESTPLATE.get() ||
                    itemStack.getItem() == AdvancedNetheriteItems.DIARKRITE_EMERALD_BOOTS.get() ||
                    itemStack.getItem() == AdvancedNetheriteItems.DIARKRITE_DIAMOND_HELMET.get() ||
                    itemStack.getItem() == AdvancedNetheriteItems.DIARKRITE_DIAMOND_CHESTPLATE.get() ||
                    itemStack.getItem() == AdvancedNetheriteItems.DIARKRITE_DIAMOND_BOOTS.get() ||

                    itemStack.getItem() == AdvancedNetheriteItems.ANTHEKTITE_IRON_HELMET.get() ||
                    itemStack.getItem() == AdvancedNetheriteItems.ANTHEKTITE_IRON_CHESTPLATE.get() ||
                    itemStack.getItem() == AdvancedNetheriteItems.ANTHEKTITE_IRON_BOOTS.get() ||
                    itemStack.getItem() == AdvancedNetheriteItems.ANTHEKTITE_GOLD_HELMET.get() ||
                    itemStack.getItem() == AdvancedNetheriteItems.ANTHEKTITE_GOLD_CHESTPLATE.get() ||
                    itemStack.getItem() == AdvancedNetheriteItems.ANTHEKTITE_GOLD_BOOTS.get() ||
                    itemStack.getItem() == AdvancedNetheriteItems.ANTHEKTITE_EMERALD_HELMET.get() ||
                    itemStack.getItem() == AdvancedNetheriteItems.ANTHEKTITE_EMERALD_CHESTPLATE.get() ||
                    itemStack.getItem() == AdvancedNetheriteItems.ANTHEKTITE_EMERALD_BOOTS.get() ||
                    itemStack.getItem() == AdvancedNetheriteItems.ANTHEKTITE_DIAMOND_HELMET.get() ||
                    itemStack.getItem() == AdvancedNetheriteItems.ANTHEKTITE_DIAMOND_CHESTPLATE.get() ||
                    itemStack.getItem() == AdvancedNetheriteItems.ANTHEKTITE_DIAMOND_BOOTS.get()) {
                return EXTENDED_ARMOR_MODEL;
            }
            if (itemStack.getItem() == AdvancedNetheriteItems.DIARKRITE_IRON_LEGGINGS.get() ||
                    itemStack.getItem() == AdvancedNetheriteItems.DIARKRITE_GOLD_LEGGINGS.get() ||
                    itemStack.getItem() == AdvancedNetheriteItems.DIARKRITE_EMERALD_LEGGINGS.get() ||
                    itemStack.getItem() == AdvancedNetheriteItems.DIARKRITE_DIAMOND_LEGGINGS.get() ||

                    itemStack.getItem() == AdvancedNetheriteItems.ANTHEKTITE_IRON_LEGGINGS.get() ||
                    itemStack.getItem() == AdvancedNetheriteItems.ANTHEKTITE_GOLD_LEGGINGS.get() ||
                    itemStack.getItem() == AdvancedNetheriteItems.ANTHEKTITE_EMERALD_LEGGINGS.get() ||
                    itemStack.getItem() == AdvancedNetheriteItems.ANTHEKTITE_DIAMOND_LEGGINGS.get()) {
                return EXTENDED_ARMOR_MODEL_LEGS;
            }
        }
        if (ironsSpellbooks) {
            if (itemStack.getItem() == IronsSpellbooksItems.DIARKRITE_MAGE_HELMET.get() ||
                    itemStack.getItem() == IronsSpellbooksItems.DIARKRITE_MAGE_CHESTPLATE.get() ||
                    itemStack.getItem() == IronsSpellbooksItems.DIARKRITE_MAGE_BOOTS.get()) {
                return DIARKRITE_MAGE_ARMOR_MODEL;
            }
            if (itemStack.getItem() == IronsSpellbooksItems.DIARKRITE_MAGE_LEGGINGS.get()) {
                return DIARKRITE_MAGE_ARMOR_MODEL_LEGS;
            }

            if (itemStack.getItem() == IronsSpellbooksItems.ANTHEKTITE_MAGE_HELMET.get() ||
                    itemStack.getItem() == IronsSpellbooksItems.ANTHEKTITE_MAGE_CHESTPLATE.get() ||
                    itemStack.getItem() == IronsSpellbooksItems.ANTHEKTITE_MAGE_BOOTS.get()) {
                return ANTHEKTITE_MAGE_ARMOR_MODEL;}
            if (itemStack.getItem() == IronsSpellbooksItems.ANTHEKTITE_MAGE_LEGGINGS.get()) {
                return ANTHEKTITE_MAGE_ARMOR_MODEL_LEGS;
            }
        }
        if (samuraiDynasty) {
            if (itemStack.getItem() == EpicSamuraiItems.STEEL_SAMURAI_HELMET.get() ||
                    itemStack.getItem() == EpicSamuraiItems.STEEL_SAMURAI_CHESTPLATE.get() ||
                    itemStack.getItem() == EpicSamuraiItems.STEEL_SAMURAI_BOOTS.get() ||
                    itemStack.getItem() == EpicSamuraiItems.DIARKRITE_SAMURAI_HELMET.get() ||
                    itemStack.getItem() == EpicSamuraiItems.DIARKRITE_SAMURAI_CHESTPLATE.get() ||
                    itemStack.getItem() == EpicSamuraiItems.DIARKRITE_SAMURAI_BOOTS.get() ||
                    itemStack.getItem() == EpicSamuraiItems.ANTHEKTITE_SAMURAI_HELMET.get() ||
                    itemStack.getItem() == EpicSamuraiItems.ANTHEKTITE_SAMURAI_CHESTPLATE.get() ||
                    itemStack.getItem() == EpicSamuraiItems.ANTHEKTITE_SAMURAI_BOOTS.get()) {
                return SD_SAMURAI_ARMOR_MODEL;
            }
            if (itemStack.getItem() == EpicSamuraiItems.STEEL_SAMURAI_LEGGINGS.get() ||
                    itemStack.getItem() == EpicSamuraiItems.DIARKRITE_SAMURAI_LEGGINGS.get() ||
                    itemStack.getItem() == EpicSamuraiItems.ANTHEKTITE_SAMURAI_LEGGINGS.get()) {
                return SD_SAMURAI_ARMOR_MODEL_LEGS;
            }

            if (itemStack.getItem() == EpicSamuraiItems.STEEL_SAMURAI_HELMET_LIGHT.get() ||
                    itemStack.getItem() == EpicSamuraiItems.STEEL_SAMURAI_CHESTPLATE_LIGHT.get() ||
                    itemStack.getItem() == EpicSamuraiItems.STEEL_SAMURAI_BOOTS_LIGHT.get() ||
                    itemStack.getItem() == EpicSamuraiItems.DIARKRITE_SAMURAI_HELMET_LIGHT.get() ||
                    itemStack.getItem() == EpicSamuraiItems.DIARKRITE_SAMURAI_CHESTPLATE_LIGHT.get() ||
                    itemStack.getItem() == EpicSamuraiItems.DIARKRITE_SAMURAI_BOOTS_LIGHT.get() ||
                    itemStack.getItem() == EpicSamuraiItems.ANTHEKTITE_SAMURAI_HELMET_LIGHT.get() ||
                    itemStack.getItem() == EpicSamuraiItems.ANTHEKTITE_SAMURAI_CHESTPLATE_LIGHT.get() ||
                    itemStack.getItem() == EpicSamuraiItems.ANTHEKTITE_SAMURAI_BOOTS_LIGHT.get()) {
                return SD_SAMURAI_LIGHT_ARMOR_MODEL;
            }
            if (itemStack.getItem() == EpicSamuraiItems.STEEL_SAMURAI_LEGGINGS_LIGHT.get() ||
                    itemStack.getItem() == EpicSamuraiItems.DIARKRITE_SAMURAI_LEGGINGS_LIGHT.get() ||
                    itemStack.getItem() == EpicSamuraiItems.ANTHEKTITE_SAMURAI_LEGGINGS_LIGHT.get()) {
                return SD_SAMURAI_LIGHT_ARMOR_MODEL_LEGS;
            }

            if (itemStack.getItem() == EpicSamuraiItems.STEEL_SAMURAI_HELMET_MASTER.get() ||
                    itemStack.getItem() == EpicSamuraiItems.STEEL_SAMURAI_CHESTPLATE_MASTER.get() ||
                    itemStack.getItem() == EpicSamuraiItems.STEEL_SAMURAI_BOOTS_MASTER.get() ||
                    itemStack.getItem() == EpicSamuraiItems.DIARKRITE_SAMURAI_HELMET_MASTER.get() ||
                    itemStack.getItem() == EpicSamuraiItems.DIARKRITE_SAMURAI_CHESTPLATE_MASTER.get() ||
                    itemStack.getItem() == EpicSamuraiItems.DIARKRITE_SAMURAI_BOOTS_MASTER.get() ||
                    itemStack.getItem() == EpicSamuraiItems.ANTHEKTITE_SAMURAI_HELMET_MASTER.get() ||
                    itemStack.getItem() == EpicSamuraiItems.ANTHEKTITE_SAMURAI_CHESTPLATE_MASTER.get() ||
                    itemStack.getItem() == EpicSamuraiItems.ANTHEKTITE_SAMURAI_BOOTS_MASTER.get()) {
                return SD_SAMURAI_MASTER_ARMOR_MODEL;
            }
            if (itemStack.getItem() == EpicSamuraiItems.STEEL_SAMURAI_LEGGINGS_MASTER.get() ||
                    itemStack.getItem() == EpicSamuraiItems.DIARKRITE_SAMURAI_LEGGINGS_MASTER.get() ||
                    itemStack.getItem() == EpicSamuraiItems.ANTHEKTITE_SAMURAI_LEGGINGS_MASTER.get()) {
                return SD_SAMURAI_MASTER_ARMOR_MODEL_LEGS;
            }
        }

        return original;
    }
}