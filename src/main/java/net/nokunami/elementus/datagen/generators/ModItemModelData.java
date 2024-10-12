package net.nokunami.elementus.datagen.generators;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.nokunami.elementus.ModChecker;
import net.nokunami.elementus.common.compat.advancednetherite.ANModBlocks;
import net.nokunami.elementus.common.compat.advancednetherite.ANModItems;
import net.nokunami.elementus.common.compat.epicsamurai.ESModItems;
import net.nokunami.elementus.common.compat.farmersdelight.FDModBlocks;
import net.nokunami.elementus.common.compat.farmersdelight.FDModItems;
import net.nokunami.elementus.common.compat.farmersdelight.NDModItems;
import net.nokunami.elementus.common.compat.ironsspellbooks.ISSModItems;
import net.nokunami.elementus.common.compat.piercingpaxels.PPModItems;
import net.nokunami.elementus.common.compat.sniffsweapons.SWModItems;
import net.nokunami.elementus.common.compat.theaether.TAModItems;
import net.nokunami.elementus.datagen.providers.ModItemModelProvider;
import net.nokunami.elementus.common.registry.ModBlocks;
import net.nokunami.elementus.common.registry.ModItems;

public class ModItemModelData extends ModItemModelProvider {
    public ModItemModelData(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
        super(output, "elementus", existingFileHelper);
    }

    @Override
    protected void registerModels() {
        generatedItem(ModItems.CRUDE_STEEL, "ingredients/");
        generatedItem(ModItems.STEEL_SCRAP, "ingredients/");
        generatedItem(ModItems.STEEL_INGOT, "ingredients/");
        generatedItem(ModItems.STEEL_NUGGET, "ingredients/");
        generatedItem(ModItems.ATELIS_SCRAP, "ingredients/");
        generatedItem(ModItems.ANTHEKTITE_INGOT, "ingredients/");
        generatedItem(ModItems.DIARKRITE_INGOT, "ingredients/");
        generatedItem(ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, "ingredients/");

        handheldItem(ModItems.STEEL_SWORD, "tools/");
        handheldItem(ModItems.STEEL_SHOVEL, "tools/");
        handheldItem(ModItems.STEEL_PICKAXE, "tools/");
        handheldItem(ModItems.STEEL_AXE, "tools/");
        handheldItem(ModItems.STEEL_HOE, "tools/");

        handheldItem(ModItems.ANTHEKTITE_SWORD, "tools/");
        handheldItem(ModItems.ANTHEKTITE_SHOVEL, "tools/");
        handheldItem(ModItems.ANTHEKTITE_PICKAXE, "tools/");
        handheldItem(ModItems.ANTHEKTITE_AXE, "tools/");
        handheldItem(ModItems.ANTHEKTITE_HOE, "tools/");

        handheldItem(ModItems.DIARKRITE_SWORD, "tools/");
        handheldItem(ModItems.DIARKRITE_SHOVEL, "tools/");
        handheldItem(ModItems.DIARKRITE_PICKAXE, "tools/");
        handheldItem(ModItems.DIARKRITE_AXE, "tools/");
        handheldItem(ModItems.DIARKRITE_HOE, "tools/");

        helmetItem(ModItems.STEEL_HELMET, "armor/");
        chestplateItem(ModItems.STEEL_CHESTPLATE, "armor/");
        leggingsItem(ModItems.STEEL_LEGGINGS, "armor/");
        bootsItem(ModItems.STEEL_BOOTS, "armor/");

        helmetItem(ModItems.ANTHEKTITE_HELMET, "armor/");
        chestplateItem(ModItems.ANTHEKTITE_CHESTPLATE, "armor/");
        leggingsItem(ModItems.ANTHEKTITE_LEGGINGS, "armor/");
        bootsItem(ModItems.ANTHEKTITE_BOOTS, "armor/");

        helmetItem(ModItems.DIARKRITE_HELMET, "armor/");
        chestplateItem(ModItems.DIARKRITE_CHESTPLATE, "armor/");
        leggingsItem(ModItems.DIARKRITE_LEGGINGS, "armor/");
        bootsItem(ModItems.DIARKRITE_BOOTS, "armor/");

        block(ModBlocks.STEEL_BLOCK);
        block(ModBlocks.ANTHEKTITE_BLOCK);
        block(ModBlocks.DIARKRITE_BLOCK);
        block(ModBlocks.REMNANT);

        blockFlat(ModBlocks.STEEL_BARS, "building/");

        blockFlat(ModBlocks.MOVCADIA_SAPLING, "natural/");
        block(ModBlocks.MOVCADIA_ROOTS);

        block(ModBlocks.MOVCADIA_LOG);
        block(ModBlocks.STRIPPED_MOVCADIA_LOG);
        block(ModBlocks.MOVCADIA_WOOD);
        block(ModBlocks.STRIPPED_MOVCADIA_WOOD);
        block(ModBlocks.MOVCADIA_PLANKS);
        block(ModBlocks.MOVCADIA_STAIRS);
        block(ModBlocks.MOVCADIA_SLAB);
        itemFence(ModBlocks.MOVCADIA_FENCE, ModBlocks.MOVCADIA_PLANKS, "building/");
        block(ModBlocks.MOVCADIA_FENCE_GATE);
        generatedItem((ModBlocks.MOVCADIA_DOOR.get()).asItem(), "miscellaneous/");
        itemBlock(ModBlocks.MOVCADIA_TRAPDOOR, "_bottom");
        block(ModBlocks.MOVCADIA_PRESSURE_PLATE);
        itemButton(ModBlocks.MOVCADIA_BUTTON, ModBlocks.MOVCADIA_PLANKS, "building/");
        generatedItem((ModItems.MOVCADIA_SIGN.get()).asItem(), "miscellaneous/");
        generatedItem((ModItems.MOVCADIA_HANGING_SIGN.get()).asItem(), "miscellaneous/");
        generatedItem((ModItems.STURDY_MOVCADIA_SIGN.get()).asItem(), "miscellaneous/");

        generatedItem(ModItems.MOVCADIA_BOAT, "miscellaneous/");
        generatedItem(ModItems.MOVCADIA_CHEST_BOAT, "miscellaneous/");

        generatedItem(ModItems.MOVCADIA_BARK, "miscellaneous/");

        block(ModBlocks.MOVCADIA_LEAVES);

        if (ModChecker.farmersdelight()) {
            handheldItem(FDModItems.STEEL_KNIFE, "compat/farmers_delight/");
            handheldItem(FDModItems.ANTHEKTITE_KNIFE, "compat/farmers_delight/");
            handheldItem(FDModItems.DIARKRITE_KNIFE, "compat/farmers_delight/");
            block(FDModBlocks.MOVCADIA_CABINET);
        }

        if (ModChecker.piercingpaxels()) {
            handheldItem(PPModItems.STEEL_PAXEL, "compat/piercingpaxels/");
            handheldItem(PPModItems.ANTHEKTITE_PAXEL, "compat/piercingpaxels/");
            handheldItem(PPModItems.DIARKRITE_PAXEL, "compat/piercingpaxels/");
            generatedItem(PPModItems.ANTHEKTITE_UPGRADE_KIT, "compat/piercingpaxels/");
            generatedItem(PPModItems.DIARKRITE_UPGRADE_KIT, "compat/piercingpaxels/");
        }

        if (ModChecker.nethersdelight()) {
            handheldItem(NDModItems.STEEL_MACHETE, "compat/nethers_delight/");
            handheldItem(NDModItems.ANTHEKTITE_MACHETE, "compat/nethers_delight/");
            handheldItem(NDModItems.DIARKRITE_MACHETE, "compat/nethers_delight/");
        }

        if (ModChecker.irons_spellbooks()) {
            generatedItem(ISSModItems.ANTHEKTITE_MAGE_HELMET, "compat/irons_spellbooks/");
            generatedItem(ISSModItems.ANTHEKTITE_MAGE_CHESTPLATE, "compat/irons_spellbooks/");
            generatedItem(ISSModItems.ANTHEKTITE_MAGE_LEGGINGS, "compat/irons_spellbooks/");
            generatedItem(ISSModItems.ANTHEKTITE_MAGE_BOOTS, "compat/irons_spellbooks/");
            generatedItem(ISSModItems.DIARKRITE_MAGE_HELMET, "compat/irons_spellbooks/");
            generatedItem(ISSModItems.DIARKRITE_MAGE_CHESTPLATE, "compat/irons_spellbooks/");
            generatedItem(ISSModItems.DIARKRITE_MAGE_LEGGINGS, "compat/irons_spellbooks/");
            generatedItem(ISSModItems.DIARKRITE_MAGE_BOOTS, "compat/irons_spellbooks/");
        }

        if (ModChecker.aether()) {
            glovesItem(TAModItems.STEEL_GLOVES.get(), "compat/aether/");
            glovesItem(TAModItems.DIARKRITE_GLOVES.get(), "compat/aether/");
            glovesItem(TAModItems.ANTHEKTITE_GLOVES.get(), "compat/aether/");
        }

        if (ModChecker.sniffsweapons()) {
            //Helmet
            twoLayeredGeneratedItem(new ResourceLocation("sniffsweapons", "item/feathers"), SWModItems.STEEL_HELM, "compat/sniffsweapons/armor/");
            twoLayeredGeneratedItem(new ResourceLocation("sniffsweapons", "item/feathers"), SWModItems.DIARKRITE_HELM, "compat/sniffsweapons/armor/");
            twoLayeredGeneratedItem(new ResourceLocation("sniffsweapons", "item/feathers"), SWModItems.ANTHEKTITE_HELM, "compat/sniffsweapons/armor/");

            twoLayeredGeneratedItem(new ResourceLocation("sniffsweapons", "item/long_feathers"), SWModItems.STEEL_HORNED_HELM, "compat/sniffsweapons/armor/");
            twoLayeredGeneratedItem(new ResourceLocation("sniffsweapons", "item/long_feathers"), SWModItems.DIARKRITE_HORNED_HELM, "compat/sniffsweapons/armor/");
            twoLayeredGeneratedItem(new ResourceLocation("sniffsweapons", "item/long_feathers"), SWModItems.ANTHEKTITE_HORNED_HELM, "compat/sniffsweapons/armor/");

            twoLayeredGeneratedItem(new ResourceLocation("sniffsweapons", "item/mask"), SWModItems.STEEL_KABUTO, "compat/sniffsweapons/armor/");
            twoLayeredGeneratedItem(new ResourceLocation("sniffsweapons", "item/mask"), SWModItems.DIARKRITE_KABUTO, "compat/sniffsweapons/armor/");
            twoLayeredGeneratedItem(new ResourceLocation("sniffsweapons", "item/mask"), SWModItems.ANTHEKTITE_KABUTO, "compat/sniffsweapons/armor/");

            //Chestplate
            twoLayeredGeneratedItem(new ResourceLocation("sniffsweapons", "item/surcoat"), SWModItems.STEEL_SURCOAT, "compat/sniffsweapons/armor/");
            twoLayeredGeneratedItem(new ResourceLocation("sniffsweapons", "item/surcoat"), SWModItems.DIARKRITE_SURCOAT, "compat/sniffsweapons/armor/");
            twoLayeredGeneratedItem(new ResourceLocation("sniffsweapons", "item/surcoat"), SWModItems.ANTHEKTITE_SURCOAT, "compat/sniffsweapons/armor/");

            twoLayeredGeneratedItem(new ResourceLocation("sniffsweapons", "item/plated_chestplate_layer"), SWModItems.PLATED_STEEL_CHESTPLATE, "compat/sniffsweapons/armor/");
            twoLayeredGeneratedItem(new ResourceLocation("sniffsweapons", "item/plated_chestplate_layer"), SWModItems.PLATED_DIARKRITE_CHESTPLATE, "compat/sniffsweapons/armor/");
            twoLayeredGeneratedItem(new ResourceLocation("sniffsweapons", "item/plated_chestplate_layer"), SWModItems.PLATED_ANTHEKTITE_CHESTPLATE, "compat/sniffsweapons/armor/");

            twoLayeredGeneratedItem(new ResourceLocation("sniffsweapons", "item/do_layer"), SWModItems.STEEL_DO, "compat/sniffsweapons/armor/");
            twoLayeredGeneratedItem(new ResourceLocation("sniffsweapons", "item/do_layer"), SWModItems.DIARKRITE_DO, "compat/sniffsweapons/armor/");
            twoLayeredGeneratedItem(new ResourceLocation("sniffsweapons", "item/do_layer"), SWModItems.ANTHEKTITE_DO, "compat/sniffsweapons/armor/");
        }

        if (ModChecker.advancednetherite()) {
            generatedItem(ANModItems.DIARKRITE_IRON, "compat/advancednetherite/ingredient/");
            generatedItem(ANModItems.DIARKRITE_GOLD, "compat/advancednetherite/ingredient/");
            generatedItem(ANModItems.DIARKRITE_EMERALD, "compat/advancednetherite/ingredient/");
            generatedItem(ANModItems.DIARKRITE_DIAMOND, "compat/advancednetherite/ingredient/");
            generatedItem(ANModItems.ANTHEKTITE_IRON, "compat/advancednetherite/ingredient/");
            generatedItem(ANModItems.ANTHEKTITE_GOLD, "compat/advancednetherite/ingredient/");
            generatedItem(ANModItems.ANTHEKTITE_EMERALD, "compat/advancednetherite/ingredient/");
            generatedItem(ANModItems.ANTHEKTITE_DIAMOND, "compat/advancednetherite/ingredient/");


            handheldItem(ANModItems.DIARKRITE_IRON_SWORD, "compat/advancednetherite/tools/");
            handheldItem(ANModItems.DIARKRITE_IRON_SHOVEL, "compat/advancednetherite/tools/");
            handheldItem(ANModItems.DIARKRITE_IRON_PICKAXE, "compat/advancednetherite/tools/");
            handheldItem(ANModItems.DIARKRITE_IRON_AXE, "compat/advancednetherite/tools/");
            handheldItem(ANModItems.DIARKRITE_IRON_HOE, "compat/advancednetherite/tools/");

            handheldItem(ANModItems.DIARKRITE_GOLD_SWORD, "compat/advancednetherite/tools/");
            handheldItem(ANModItems.DIARKRITE_GOLD_SHOVEL, "compat/advancednetherite/tools/");
            handheldItem(ANModItems.DIARKRITE_GOLD_PICKAXE, "compat/advancednetherite/tools/");
            handheldItem(ANModItems.DIARKRITE_GOLD_AXE, "compat/advancednetherite/tools/");
            handheldItem(ANModItems.DIARKRITE_GOLD_HOE, "compat/advancednetherite/tools/");

            handheldItem(ANModItems.DIARKRITE_EMERALD_SWORD, "compat/advancednetherite/tools/");
            handheldItem(ANModItems.DIARKRITE_EMERALD_SHOVEL, "compat/advancednetherite/tools/");
            handheldItem(ANModItems.DIARKRITE_EMERALD_PICKAXE, "compat/advancednetherite/tools/");
            handheldItem(ANModItems.DIARKRITE_EMERALD_AXE, "compat/advancednetherite/tools/");
            handheldItem(ANModItems.DIARKRITE_EMERALD_HOE, "compat/advancednetherite/tools/");

            handheldItem(ANModItems.DIARKRITE_DIAMOND_SWORD, "compat/advancednetherite/tools/");
            handheldItem(ANModItems.DIARKRITE_DIAMOND_SHOVEL, "compat/advancednetherite/tools/");
            handheldItem(ANModItems.DIARKRITE_DIAMOND_PICKAXE, "compat/advancednetherite/tools/");
            handheldItem(ANModItems.DIARKRITE_DIAMOND_AXE, "compat/advancednetherite/tools/");
            handheldItem(ANModItems.DIARKRITE_DIAMOND_HOE, "compat/advancednetherite/tools/");


            handheldItem(ANModItems.ANTHEKTITE_IRON_SWORD, "compat/advancednetherite/tools/");
            handheldItem(ANModItems.ANTHEKTITE_IRON_SHOVEL, "compat/advancednetherite/tools/");
            handheldItem(ANModItems.ANTHEKTITE_IRON_PICKAXE, "compat/advancednetherite/tools/");
            handheldItem(ANModItems.ANTHEKTITE_IRON_AXE, "compat/advancednetherite/tools/");
            handheldItem(ANModItems.ANTHEKTITE_IRON_HOE, "compat/advancednetherite/tools/");

            handheldItem(ANModItems.ANTHEKTITE_GOLD_SWORD, "compat/advancednetherite/tools/");
            handheldItem(ANModItems.ANTHEKTITE_GOLD_SHOVEL, "compat/advancednetherite/tools/");
            handheldItem(ANModItems.ANTHEKTITE_GOLD_PICKAXE, "compat/advancednetherite/tools/");
            handheldItem(ANModItems.ANTHEKTITE_GOLD_AXE, "compat/advancednetherite/tools/");
            handheldItem(ANModItems.ANTHEKTITE_GOLD_HOE, "compat/advancednetherite/tools/");

            handheldItem(ANModItems.ANTHEKTITE_EMERALD_SWORD, "compat/advancednetherite/tools/");
            handheldItem(ANModItems.ANTHEKTITE_EMERALD_SHOVEL, "compat/advancednetherite/tools/");
            handheldItem(ANModItems.ANTHEKTITE_EMERALD_PICKAXE, "compat/advancednetherite/tools/");
            handheldItem(ANModItems.ANTHEKTITE_EMERALD_AXE, "compat/advancednetherite/tools/");
            handheldItem(ANModItems.ANTHEKTITE_EMERALD_HOE, "compat/advancednetherite/tools/");

            handheldItem(ANModItems.ANTHEKTITE_DIAMOND_SWORD, "compat/advancednetherite/tools/");
            handheldItem(ANModItems.ANTHEKTITE_DIAMOND_SHOVEL, "compat/advancednetherite/tools/");
            handheldItem(ANModItems.ANTHEKTITE_DIAMOND_PICKAXE, "compat/advancednetherite/tools/");
            handheldItem(ANModItems.ANTHEKTITE_DIAMOND_AXE, "compat/advancednetherite/tools/");
            handheldItem(ANModItems.ANTHEKTITE_DIAMOND_HOE, "compat/advancednetherite/tools/");


            helmetItem(ANModItems.DIARKRITE_IRON_HELMET, "compat/advancednetherite/armor/");
            chestplateItem(ANModItems.DIARKRITE_IRON_CHESTPLATE, "compat/advancednetherite/armor/");
            leggingsItem(ANModItems.DIARKRITE_IRON_LEGGINGS, "compat/advancednetherite/armor/");
            bootsItem(ANModItems.DIARKRITE_IRON_BOOTS, "compat/advancednetherite/armor/");

            helmetItem(ANModItems.DIARKRITE_GOLD_HELMET, "compat/advancednetherite/armor/");
            chestplateItem(ANModItems.DIARKRITE_GOLD_CHESTPLATE, "compat/advancednetherite/armor/");
            leggingsItem(ANModItems.DIARKRITE_GOLD_LEGGINGS, "compat/advancednetherite/armor/");
            bootsItem(ANModItems.DIARKRITE_GOLD_BOOTS, "compat/advancednetherite/armor/");

            helmetItem(ANModItems.DIARKRITE_EMERALD_HELMET, "compat/advancednetherite/armor/");
            chestplateItem(ANModItems.DIARKRITE_EMERALD_CHESTPLATE, "compat/advancednetherite/armor/");
            leggingsItem(ANModItems.DIARKRITE_EMERALD_LEGGINGS, "compat/advancednetherite/armor/");
            bootsItem(ANModItems.DIARKRITE_EMERALD_BOOTS, "compat/advancednetherite/armor/");

            helmetItem(ANModItems.DIARKRITE_DIAMOND_HELMET, "compat/advancednetherite/armor/");
            chestplateItem(ANModItems.DIARKRITE_DIAMOND_CHESTPLATE, "compat/advancednetherite/armor/");
            leggingsItem(ANModItems.DIARKRITE_DIAMOND_LEGGINGS, "compat/advancednetherite/armor/");
            bootsItem(ANModItems.DIARKRITE_DIAMOND_BOOTS, "compat/advancednetherite/armor/");


            helmetItem(ANModItems.ANTHEKTITE_IRON_HELMET, "compat/advancednetherite/armor/");
            chestplateItem(ANModItems.ANTHEKTITE_IRON_CHESTPLATE, "compat/advancednetherite/armor/");
            leggingsItem(ANModItems.ANTHEKTITE_IRON_LEGGINGS, "compat/advancednetherite/armor/");
            bootsItem(ANModItems.ANTHEKTITE_IRON_BOOTS, "compat/advancednetherite/armor/");

            helmetItem(ANModItems.ANTHEKTITE_GOLD_HELMET, "compat/advancednetherite/armor/");
            chestplateItem(ANModItems.ANTHEKTITE_GOLD_CHESTPLATE, "compat/advancednetherite/armor/");
            leggingsItem(ANModItems.ANTHEKTITE_GOLD_LEGGINGS, "compat/advancednetherite/armor/");
            bootsItem(ANModItems.ANTHEKTITE_GOLD_BOOTS, "compat/advancednetherite/armor/");

            helmetItem(ANModItems.ANTHEKTITE_EMERALD_HELMET, "compat/advancednetherite/armor/");
            chestplateItem(ANModItems.ANTHEKTITE_EMERALD_CHESTPLATE, "compat/advancednetherite/armor/");
            leggingsItem(ANModItems.ANTHEKTITE_EMERALD_LEGGINGS, "compat/advancednetherite/armor/");
            bootsItem(ANModItems.ANTHEKTITE_EMERALD_BOOTS, "compat/advancednetherite/armor/");

            helmetItem(ANModItems.ANTHEKTITE_DIAMOND_HELMET, "compat/advancednetherite/armor/");
            chestplateItem(ANModItems.ANTHEKTITE_DIAMOND_CHESTPLATE, "compat/advancednetherite/armor/");
            leggingsItem(ANModItems.ANTHEKTITE_DIAMOND_LEGGINGS, "compat/advancednetherite/armor/");
            bootsItem(ANModItems.ANTHEKTITE_DIAMOND_BOOTS, "compat/advancednetherite/armor/");

            block(ANModBlocks.DIARKRITE_IRON_BLOCK);
            block(ANModBlocks.DIARKRITE_GOLD_BLOCK);
            block(ANModBlocks.DIARKRITE_EMERALD_BLOCK);
            block(ANModBlocks.DIARKRITE_DIAMOND_BLOCK);

            block(ANModBlocks.ANTHEKTITE_IRON_BLOCK);
            block(ANModBlocks.ANTHEKTITE_GOLD_BLOCK);
            block(ANModBlocks.ANTHEKTITE_EMERALD_BLOCK);
            block(ANModBlocks.ANTHEKTITE_DIAMOND_BLOCK);
        }

        if (ModChecker.epicsamurai()) {
            generatedItem(ESModItems.STEEL_SAMURAI_HELMET, "compat/epicsamurai/");
            generatedItem(ESModItems.STEEL_SAMURAI_CHESTPLATE, "compat/epicsamurai/");
            generatedItem(ESModItems.STEEL_SAMURAI_LEGGINGS, "compat/epicsamurai/");
            generatedItem(ESModItems.STEEL_SAMURAI_BOOTS, "compat/epicsamurai/");

            generatedItem(ESModItems.STEEL_SAMURAI_HELMET_LIGHT, "compat/epicsamurai/");
            generatedItem(ESModItems.STEEL_SAMURAI_CHESTPLATE_LIGHT, "compat/epicsamurai/");
            generatedItem(ESModItems.STEEL_SAMURAI_LEGGINGS_LIGHT, "compat/epicsamurai/");
            generatedItem(ESModItems.STEEL_SAMURAI_BOOTS_LIGHT, "compat/epicsamurai/");

            generatedItem(ESModItems.STEEL_SAMURAI_HELMET_MASTER, "compat/epicsamurai/");
            generatedItem(ESModItems.STEEL_SAMURAI_CHESTPLATE_MASTER, "compat/epicsamurai/");
            generatedItem(ESModItems.STEEL_SAMURAI_LEGGINGS_MASTER, "compat/epicsamurai/");
            generatedItem(ESModItems.STEEL_SAMURAI_BOOTS_MASTER, "compat/epicsamurai/");


            generatedItem(ESModItems.DIARKRITE_SAMURAI_HELMET, "compat/epicsamurai/");
            generatedItem(ESModItems.DIARKRITE_SAMURAI_CHESTPLATE, "compat/epicsamurai/");
            generatedItem(ESModItems.DIARKRITE_SAMURAI_LEGGINGS, "compat/epicsamurai/");
            generatedItem(ESModItems.DIARKRITE_SAMURAI_BOOTS, "compat/epicsamurai/");

            generatedItem(ESModItems.DIARKRITE_SAMURAI_HELMET_LIGHT, "compat/epicsamurai/");
            generatedItem(ESModItems.DIARKRITE_SAMURAI_CHESTPLATE_LIGHT, "compat/epicsamurai/");
            generatedItem(ESModItems.DIARKRITE_SAMURAI_LEGGINGS_LIGHT, "compat/epicsamurai/");
            generatedItem(ESModItems.DIARKRITE_SAMURAI_BOOTS_LIGHT, "compat/epicsamurai/");

            generatedItem(ESModItems.DIARKRITE_SAMURAI_HELMET_MASTER, "compat/epicsamurai/");
            generatedItem(ESModItems.DIARKRITE_SAMURAI_CHESTPLATE_MASTER, "compat/epicsamurai/");
            generatedItem(ESModItems.DIARKRITE_SAMURAI_LEGGINGS_MASTER, "compat/epicsamurai/");
            generatedItem(ESModItems.DIARKRITE_SAMURAI_BOOTS_MASTER, "compat/epicsamurai/");


            generatedItem(ESModItems.ANTHEKTITE_SAMURAI_HELMET, "compat/epicsamurai/");
            generatedItem(ESModItems.ANTHEKTITE_SAMURAI_CHESTPLATE, "compat/epicsamurai/");
            generatedItem(ESModItems.ANTHEKTITE_SAMURAI_LEGGINGS, "compat/epicsamurai/");
            generatedItem(ESModItems.ANTHEKTITE_SAMURAI_BOOTS, "compat/epicsamurai/");

            generatedItem(ESModItems.ANTHEKTITE_SAMURAI_HELMET_LIGHT, "compat/epicsamurai/");
            generatedItem(ESModItems.ANTHEKTITE_SAMURAI_CHESTPLATE_LIGHT, "compat/epicsamurai/");
            generatedItem(ESModItems.ANTHEKTITE_SAMURAI_LEGGINGS_LIGHT, "compat/epicsamurai/");
            generatedItem(ESModItems.ANTHEKTITE_SAMURAI_BOOTS_LIGHT, "compat/epicsamurai/");

            generatedItem(ESModItems.ANTHEKTITE_SAMURAI_HELMET_MASTER, "compat/epicsamurai/");
            generatedItem(ESModItems.ANTHEKTITE_SAMURAI_CHESTPLATE_MASTER, "compat/epicsamurai/");
            generatedItem(ESModItems.ANTHEKTITE_SAMURAI_LEGGINGS_MASTER, "compat/epicsamurai/");
            generatedItem(ESModItems.ANTHEKTITE_SAMURAI_BOOTS_MASTER, "compat/epicsamurai/");
        }
    }

}
