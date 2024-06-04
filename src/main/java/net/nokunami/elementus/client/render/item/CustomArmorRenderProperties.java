package net.nokunami.elementus.client.render.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.fml.ModList;
import net.nokunami.elementus.client.model.ModModelLayers;
import net.nokunami.elementus.client.model.armor.*;
import net.nokunami.elementus.common.compat.advancednetherite.ANModItems;
import net.nokunami.elementus.common.compat.epicsamurai.ESModItems;
import net.nokunami.elementus.common.compat.ironsspellbooks.ISSModItems;
import net.nokunami.elementus.common.registry.ModItems;
import org.jetbrains.annotations.NotNull;

public class CustomArmorRenderProperties implements IClientItemExtensions {
    private static boolean init;
    public static ExtendedArmorModel EXTENDED_ARMOR_MODEL;
    public static ExtendedArmorModel EXTENDED_ARMOR_MODEL_LEGS;
    public static DiarkriteMageArmorModel EXTENDED_DIARKRITE_MAGE_ARMOR_MODEL;
    public static DiarkriteMageArmorModel EXTENDED_DIARKRITE_MAGE_ARMOR_MODEL_LEGS;
    public static AnthektiteMageArmorModel EXTENDED_ANTHEKTITE_MAGE_ARMOR_MODEL;
    public static AnthektiteMageArmorModel EXTENDED_ANTHEKTITE_MAGE_ARMOR_MODEL_LEGS;
    public static SamuraiArmorModel EXTENDED_SAMURAI_ARMOR_MODEL;
    public static SamuraiArmorModel EXTENDED_SAMURAI_ARMOR_MODEL_LEGS;
    public static SamuraiLightArmorModel EXTENDED_SAMURAI_LIGHT_ARMOR_MODEL;
    public static SamuraiLightArmorModel EXTENDED_SAMURAI_LIGHT_ARMOR_MODEL_LEGS;
    public static SamuraiMasterArmorModel EXTENDED_SAMURAI_MASTER_ARMOR_MODEL;
    public static SamuraiMasterArmorModel EXTENDED_SAMURAI_MASTER_ARMOR_MODEL_LEGS;

    public static void initializedModels() {
        init = true;
        EXTENDED_ARMOR_MODEL = new ExtendedArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModModelLayers.EXTENDED_ARMOR_MODEL));
        EXTENDED_ARMOR_MODEL_LEGS = new ExtendedArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModModelLayers.EXTENDED_ARMOR_MODEL_LEGS));
        EXTENDED_DIARKRITE_MAGE_ARMOR_MODEL = new DiarkriteMageArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModModelLayers.DIARKRITE_MAGE_ARMOR_MODEL));
        EXTENDED_DIARKRITE_MAGE_ARMOR_MODEL_LEGS = new DiarkriteMageArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModModelLayers.DIARKRITE_MAGE_ARMOR_MODEL_LEGS));
        EXTENDED_ANTHEKTITE_MAGE_ARMOR_MODEL = new AnthektiteMageArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModModelLayers.ANTHEKTITE_MAGE_ARMOR_MODEL));
        EXTENDED_ANTHEKTITE_MAGE_ARMOR_MODEL_LEGS = new AnthektiteMageArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModModelLayers.ANTHEKTITE_MAGE_ARMOR_MODEL_LEGS));
        EXTENDED_SAMURAI_ARMOR_MODEL = new SamuraiArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModModelLayers.SAMURAI_ARMOR_MODEL));
        EXTENDED_SAMURAI_ARMOR_MODEL_LEGS = new SamuraiArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModModelLayers.SAMURAI_ARMOR_MODEL_LEGS));
        EXTENDED_SAMURAI_LIGHT_ARMOR_MODEL = new SamuraiLightArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModModelLayers.SAMURAI_LIGHT_ARMOR_MODEL));
        EXTENDED_SAMURAI_LIGHT_ARMOR_MODEL_LEGS = new SamuraiLightArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModModelLayers.SAMURAI_LIGHT_ARMOR_MODEL_LEGS));
        EXTENDED_SAMURAI_MASTER_ARMOR_MODEL = new SamuraiMasterArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModModelLayers.SAMURAI_MASTER_ARMOR_MODEL));
        EXTENDED_SAMURAI_MASTER_ARMOR_MODEL_LEGS = new SamuraiMasterArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModModelLayers.SAMURAI_MASTER_ARMOR_MODEL_LEGS));
    }

    public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
        if (!init) {
            initializedModels();
        }

        if (itemStack.getItem() == ModItems.STEEL_HELMET.get()) {
            return EXTENDED_ARMOR_MODEL;}
        if (itemStack.getItem() == ModItems.STEEL_CHESTPLATE.get()) {
            return EXTENDED_ARMOR_MODEL;}
        if (itemStack.getItem() == ModItems.STEEL_LEGGINGS.get()) {
            return EXTENDED_ARMOR_MODEL_LEGS;}
        if (itemStack.getItem() == ModItems.STEEL_BOOTS.get()) {
            return EXTENDED_ARMOR_MODEL;}

        if (itemStack.getItem() == ModItems.DIARKRITE_HELMET.get()) {
            return EXTENDED_ARMOR_MODEL;}
        if (itemStack.getItem() == ModItems.DIARKRITE_CHESTPLATE.get()) {
            return EXTENDED_ARMOR_MODEL;}
        if (itemStack.getItem() == ModItems.DIARKRITE_LEGGINGS.get()) {
            return EXTENDED_ARMOR_MODEL_LEGS;}
        if (itemStack.getItem() == ModItems.DIARKRITE_BOOTS.get()) {
            return EXTENDED_ARMOR_MODEL;}

        if (itemStack.getItem() == ModItems.ANTHEKTITE_HELMET.get()) {
            return EXTENDED_ARMOR_MODEL;}
        if (itemStack.getItem() == ModItems.ANTHEKTITE_CHESTPLATE.get()) {
            return EXTENDED_ARMOR_MODEL;}
        if (itemStack.getItem() == ModItems.ANTHEKTITE_LEGGINGS.get()) {
            return EXTENDED_ARMOR_MODEL_LEGS;}
        if (itemStack.getItem() == ModItems.ANTHEKTITE_BOOTS.get()) {
            return EXTENDED_ARMOR_MODEL;}

        if (ModList.get().isLoaded("advancednetherite")) {
        if (itemStack.getItem() == ANModItems.DIARKRITE_IRON_HELMET.get()) {
            return EXTENDED_ARMOR_MODEL;}
        if (itemStack.getItem() == ANModItems.DIARKRITE_IRON_CHESTPLATE.get()) {
            return EXTENDED_ARMOR_MODEL;}
        if (itemStack.getItem() == ANModItems.DIARKRITE_IRON_LEGGINGS.get()) {
            return EXTENDED_ARMOR_MODEL_LEGS;}
        if (itemStack.getItem() == ANModItems.DIARKRITE_IRON_BOOTS.get()) {
            return EXTENDED_ARMOR_MODEL;}

        if (itemStack.getItem() == ANModItems.DIARKRITE_GOLD_HELMET.get()) {
            return EXTENDED_ARMOR_MODEL;}
        if (itemStack.getItem() == ANModItems.DIARKRITE_GOLD_CHESTPLATE.get()) {
            return EXTENDED_ARMOR_MODEL;}
        if (itemStack.getItem() == ANModItems.DIARKRITE_GOLD_LEGGINGS.get()) {
            return EXTENDED_ARMOR_MODEL_LEGS;}
        if (itemStack.getItem() == ANModItems.DIARKRITE_GOLD_BOOTS.get()) {
            return EXTENDED_ARMOR_MODEL;}

        if (itemStack.getItem() == ANModItems.DIARKRITE_EMERALD_HELMET.get()) {
            return EXTENDED_ARMOR_MODEL;}
        if (itemStack.getItem() == ANModItems.DIARKRITE_EMERALD_CHESTPLATE.get()) {
            return EXTENDED_ARMOR_MODEL;}
        if (itemStack.getItem() == ANModItems.DIARKRITE_EMERALD_LEGGINGS.get()) {
            return EXTENDED_ARMOR_MODEL_LEGS;}
        if (itemStack.getItem() == ANModItems.DIARKRITE_EMERALD_BOOTS.get()) {
            return EXTENDED_ARMOR_MODEL;}

        if (itemStack.getItem() == ANModItems.DIARKRITE_DIAMOND_HELMET.get()) {
            return EXTENDED_ARMOR_MODEL;}
        if (itemStack.getItem() == ANModItems.DIARKRITE_DIAMOND_CHESTPLATE.get()) {
            return EXTENDED_ARMOR_MODEL;}
        if (itemStack.getItem() == ANModItems.DIARKRITE_DIAMOND_LEGGINGS.get()) {
            return EXTENDED_ARMOR_MODEL_LEGS;}
        if (itemStack.getItem() == ANModItems.DIARKRITE_DIAMOND_BOOTS.get()) {
            return EXTENDED_ARMOR_MODEL;}

        if (itemStack.getItem() == ANModItems.ANTHEKTITE_IRON_HELMET.get()) {
            return EXTENDED_ARMOR_MODEL;}
        if (itemStack.getItem() == ANModItems.ANTHEKTITE_IRON_CHESTPLATE.get()) {
            return EXTENDED_ARMOR_MODEL;}
        if (itemStack.getItem() == ANModItems.ANTHEKTITE_IRON_LEGGINGS.get()) {
            return EXTENDED_ARMOR_MODEL_LEGS;}
        if (itemStack.getItem() == ANModItems.ANTHEKTITE_IRON_BOOTS.get()) {
            return EXTENDED_ARMOR_MODEL;}

        if (itemStack.getItem() == ANModItems.ANTHEKTITE_GOLD_HELMET.get()) {
            return EXTENDED_ARMOR_MODEL;}
        if (itemStack.getItem() == ANModItems.ANTHEKTITE_GOLD_CHESTPLATE.get()) {
            return EXTENDED_ARMOR_MODEL;}
        if (itemStack.getItem() == ANModItems.ANTHEKTITE_GOLD_LEGGINGS.get()) {
            return EXTENDED_ARMOR_MODEL_LEGS;}
        if (itemStack.getItem() == ANModItems.ANTHEKTITE_GOLD_BOOTS.get()) {
            return EXTENDED_ARMOR_MODEL;}

        if (itemStack.getItem() == ANModItems.ANTHEKTITE_EMERALD_HELMET.get()) {
            return EXTENDED_ARMOR_MODEL;}
        if (itemStack.getItem() == ANModItems.ANTHEKTITE_EMERALD_CHESTPLATE.get()) {
            return EXTENDED_ARMOR_MODEL;}
        if (itemStack.getItem() == ANModItems.ANTHEKTITE_EMERALD_LEGGINGS.get()) {
            return EXTENDED_ARMOR_MODEL_LEGS;}
        if (itemStack.getItem() == ANModItems.ANTHEKTITE_EMERALD_BOOTS.get()) {
            return EXTENDED_ARMOR_MODEL;}

        if (itemStack.getItem() == ANModItems.ANTHEKTITE_DIAMOND_HELMET.get()) {
            return EXTENDED_ARMOR_MODEL;}
        if (itemStack.getItem() == ANModItems.ANTHEKTITE_DIAMOND_CHESTPLATE.get()) {
            return EXTENDED_ARMOR_MODEL;}
        if (itemStack.getItem() == ANModItems.ANTHEKTITE_DIAMOND_LEGGINGS.get()) {
            return EXTENDED_ARMOR_MODEL_LEGS;}
        if (itemStack.getItem() == ANModItems.ANTHEKTITE_DIAMOND_BOOTS.get()) {
            return EXTENDED_ARMOR_MODEL;}
        }

        if (ModList.get().isLoaded("irons_spellbooks")) {
            if (itemStack.getItem() == ISSModItems.DIARKRITE_MAGE_HELMET.get()) {
                return EXTENDED_DIARKRITE_MAGE_ARMOR_MODEL;
            }
            if (itemStack.getItem() == ISSModItems.DIARKRITE_MAGE_CHESTPLATE.get()) {
                return EXTENDED_DIARKRITE_MAGE_ARMOR_MODEL;
            }
            if (itemStack.getItem() == ISSModItems.DIARKRITE_MAGE_LEGGINGS.get()) {
                return EXTENDED_DIARKRITE_MAGE_ARMOR_MODEL_LEGS;
            }
            if (itemStack.getItem() == ISSModItems.DIARKRITE_MAGE_BOOTS.get()) {
                return EXTENDED_DIARKRITE_MAGE_ARMOR_MODEL;
            }

            if (itemStack.getItem() == ISSModItems.ANTHEKTITE_MAGE_HELMET.get()) {
            return EXTENDED_ANTHEKTITE_MAGE_ARMOR_MODEL;}
            if (itemStack.getItem() == ISSModItems.ANTHEKTITE_MAGE_CHESTPLATE.get()) {
            return EXTENDED_ANTHEKTITE_MAGE_ARMOR_MODEL;}
            if (itemStack.getItem() == ISSModItems.ANTHEKTITE_MAGE_LEGGINGS.get()) {
            return EXTENDED_ANTHEKTITE_MAGE_ARMOR_MODEL_LEGS;}
            if (itemStack.getItem() == ISSModItems.ANTHEKTITE_MAGE_BOOTS.get()) {
            return EXTENDED_ANTHEKTITE_MAGE_ARMOR_MODEL;}
        }

        if (ModList.get().isLoaded("epicsamurai")) {
            if (itemStack.getItem() == ESModItems.STEEL_SAMURAI_HELMET.get()) {
                return EXTENDED_SAMURAI_ARMOR_MODEL;}
            if (itemStack.getItem() == ESModItems.STEEL_SAMURAI_CHESTPLATE.get()) {
                return EXTENDED_SAMURAI_ARMOR_MODEL;}
            if (itemStack.getItem() == ESModItems.STEEL_SAMURAI_LEGGINGS.get()) {
                return EXTENDED_SAMURAI_ARMOR_MODEL_LEGS;}
            if (itemStack.getItem() == ESModItems.STEEL_SAMURAI_BOOTS.get()) {
                return EXTENDED_SAMURAI_ARMOR_MODEL;}

            if (itemStack.getItem() == ESModItems.STEEL_SAMURAI_HELMET_LIGHT.get()) {
                return EXTENDED_SAMURAI_LIGHT_ARMOR_MODEL;}
            if (itemStack.getItem() == ESModItems.STEEL_SAMURAI_CHESTPLATE_LIGHT.get()) {
                return EXTENDED_SAMURAI_LIGHT_ARMOR_MODEL;}
            if (itemStack.getItem() == ESModItems.STEEL_SAMURAI_LEGGINGS_LIGHT.get()) {
                return EXTENDED_SAMURAI_LIGHT_ARMOR_MODEL_LEGS;}
            if (itemStack.getItem() == ESModItems.STEEL_SAMURAI_BOOTS_LIGHT.get()) {
                return EXTENDED_SAMURAI_LIGHT_ARMOR_MODEL;}

            if (itemStack.getItem() == ESModItems.STEEL_SAMURAI_HELMET_MASTER.get()) {
                return EXTENDED_SAMURAI_MASTER_ARMOR_MODEL;}
            if (itemStack.getItem() == ESModItems.STEEL_SAMURAI_CHESTPLATE_MASTER.get()) {
                return EXTENDED_SAMURAI_MASTER_ARMOR_MODEL;}
            if (itemStack.getItem() == ESModItems.STEEL_SAMURAI_LEGGINGS_MASTER.get()) {
                return EXTENDED_SAMURAI_MASTER_ARMOR_MODEL_LEGS;}
            if (itemStack.getItem() == ESModItems.STEEL_SAMURAI_BOOTS_MASTER.get()) {
                return EXTENDED_SAMURAI_MASTER_ARMOR_MODEL;}


            if (itemStack.getItem() == ESModItems.DIARKRITE_SAMURAI_HELMET.get()) {
                return EXTENDED_SAMURAI_ARMOR_MODEL;}
            if (itemStack.getItem() == ESModItems.DIARKRITE_SAMURAI_CHESTPLATE.get()) {
                return EXTENDED_SAMURAI_ARMOR_MODEL;}
            if (itemStack.getItem() == ESModItems.DIARKRITE_SAMURAI_LEGGINGS.get()) {
                return EXTENDED_SAMURAI_ARMOR_MODEL_LEGS;}
            if (itemStack.getItem() == ESModItems.DIARKRITE_SAMURAI_BOOTS.get()) {
                return EXTENDED_SAMURAI_ARMOR_MODEL;}

            if (itemStack.getItem() == ESModItems.DIARKRITE_SAMURAI_HELMET_LIGHT.get()) {
                return EXTENDED_SAMURAI_LIGHT_ARMOR_MODEL;}
            if (itemStack.getItem() == ESModItems.DIARKRITE_SAMURAI_CHESTPLATE_LIGHT.get()) {
                return EXTENDED_SAMURAI_LIGHT_ARMOR_MODEL;}
            if (itemStack.getItem() == ESModItems.DIARKRITE_SAMURAI_LEGGINGS_LIGHT.get()) {
                return EXTENDED_SAMURAI_LIGHT_ARMOR_MODEL_LEGS;}
            if (itemStack.getItem() == ESModItems.DIARKRITE_SAMURAI_BOOTS_LIGHT.get()) {
                return EXTENDED_SAMURAI_LIGHT_ARMOR_MODEL;}

            if (itemStack.getItem() == ESModItems.DIARKRITE_SAMURAI_HELMET_MASTER.get()) {
                return EXTENDED_SAMURAI_MASTER_ARMOR_MODEL;}
            if (itemStack.getItem() == ESModItems.DIARKRITE_SAMURAI_CHESTPLATE_MASTER.get()) {
                return EXTENDED_SAMURAI_MASTER_ARMOR_MODEL;}
            if (itemStack.getItem() == ESModItems.DIARKRITE_SAMURAI_LEGGINGS_MASTER.get()) {
                return EXTENDED_SAMURAI_MASTER_ARMOR_MODEL_LEGS;}
            if (itemStack.getItem() == ESModItems.DIARKRITE_SAMURAI_BOOTS_MASTER.get()) {
                return EXTENDED_SAMURAI_MASTER_ARMOR_MODEL;}


            if (itemStack.getItem() == ESModItems.ANTHEKTITE_SAMURAI_HELMET.get()) {
                return EXTENDED_SAMURAI_ARMOR_MODEL;}
            if (itemStack.getItem() == ESModItems.ANTHEKTITE_SAMURAI_CHESTPLATE.get()) {
                return EXTENDED_SAMURAI_ARMOR_MODEL;}
            if (itemStack.getItem() == ESModItems.ANTHEKTITE_SAMURAI_LEGGINGS.get()) {
                return EXTENDED_SAMURAI_ARMOR_MODEL_LEGS;}
            if (itemStack.getItem() == ESModItems.ANTHEKTITE_SAMURAI_BOOTS.get()) {
                return EXTENDED_SAMURAI_ARMOR_MODEL;}

            if (itemStack.getItem() == ESModItems.ANTHEKTITE_SAMURAI_HELMET_LIGHT.get()) {
                return EXTENDED_SAMURAI_LIGHT_ARMOR_MODEL;}
            if (itemStack.getItem() == ESModItems.ANTHEKTITE_SAMURAI_CHESTPLATE_LIGHT.get()) {
                return EXTENDED_SAMURAI_LIGHT_ARMOR_MODEL;}
            if (itemStack.getItem() == ESModItems.ANTHEKTITE_SAMURAI_LEGGINGS_LIGHT.get()) {
                return EXTENDED_SAMURAI_LIGHT_ARMOR_MODEL_LEGS;}
            if (itemStack.getItem() == ESModItems.ANTHEKTITE_SAMURAI_BOOTS_LIGHT.get()) {
                return EXTENDED_SAMURAI_LIGHT_ARMOR_MODEL;}

            if (itemStack.getItem() == ESModItems.ANTHEKTITE_SAMURAI_HELMET_MASTER.get()) {
                return EXTENDED_SAMURAI_MASTER_ARMOR_MODEL;}
            if (itemStack.getItem() == ESModItems.ANTHEKTITE_SAMURAI_CHESTPLATE_MASTER.get()) {
                return EXTENDED_SAMURAI_MASTER_ARMOR_MODEL;}
            if (itemStack.getItem() == ESModItems.ANTHEKTITE_SAMURAI_LEGGINGS_MASTER.get()) {
                return EXTENDED_SAMURAI_MASTER_ARMOR_MODEL_LEGS;}
            if (itemStack.getItem() == ESModItems.ANTHEKTITE_SAMURAI_BOOTS_MASTER.get()) {
                return EXTENDED_SAMURAI_MASTER_ARMOR_MODEL;}
        }

        return original;
    }
}