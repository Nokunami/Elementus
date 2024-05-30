package net.nokunami.elementus.datagen.generators;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.ModList;
import net.nokunami.elementus.compat.theaether.AEItemsRegistry;
import net.nokunami.elementus.datagen.providers.ModItemModelProvider;
import net.nokunami.elementus.registry.ModBlocks;
import net.nokunami.elementus.registry.ModItems;

public class ModItemModelData extends ModItemModelProvider {
    public ModItemModelData(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, "elementus", existingFileHelper);
    }

    @Override
    protected void registerModels() {
        normalItemIngredient(ModItems.CRUDE_STEEL);
        normalItemIngredient(ModItems.STEEL_SCRAP);
        normalItemIngredient(ModItems.STEEL_INGOT);
        normalItemIngredient(ModItems.STEEL_NUGGET);
        normalItemIngredient(ModItems.ANTHEKTITE_SCRAP);
        normalItemIngredient(ModItems.ATELIS_SCRAP);
        normalItemIngredient(ModItems.ANTHEKTITE_INGOT);
        normalItemIngredient(ModItems.DIARKRITE_INGOT);
        normalItemIngredient(ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE);
        normalItemIngredient(ModItems.ANTHEKTITE_UPGRADE_SMITHING_TEMPLATE);
        normalItemIngredient(ModItems.DIARKRITE_UPGRADE_SMITHING_TEMPLATE);
        normalItemIngredient(ModItems.ECHOING_MIRROR);

        toolItem(ModItems.STEEL_SWORD);
        toolItem(ModItems.STEEL_SHOVEL);
        toolItem(ModItems.STEEL_PICKAXE);
        toolItem(ModItems.STEEL_AXE);
        toolItem(ModItems.STEEL_HOE);

        toolItem(ModItems.ANTHEKTITE_SWORD);
        toolItem(ModItems.ANTHEKTITE_SHOVEL);
        toolItem(ModItems.ANTHEKTITE_PICKAXE);
        toolItem(ModItems.ANTHEKTITE_AXE);
        toolItem(ModItems.ANTHEKTITE_HOE);

        toolItem(ModItems.DIARKRITE_SWORD);
        toolItem(ModItems.DIARKRITE_SHOVEL);
        toolItem(ModItems.DIARKRITE_PICKAXE);
        toolItem(ModItems.DIARKRITE_AXE);
        toolItem(ModItems.DIARKRITE_HOE);

        trimmedArmorItem(ModItems.STEEL_HELMET);
        trimmedArmorItem(ModItems.STEEL_CHESTPLATE);
        trimmedArmorItem(ModItems.STEEL_LEGGINGS);
        trimmedArmorItem(ModItems.STEEL_BOOTS);

        trimmedArmorItem(ModItems.ANTHEKTITE_HELEMT);
        trimmedArmorItem(ModItems.ANTHEKTITE_CHESTPLATE);
        trimmedArmorItem(ModItems.ANTHEKTITE_LEGGINGS);
        trimmedArmorItem(ModItems.ANTHEKTITE_BOOTS);

        trimmedArmorItem(ModItems.DIARKRITE_HELEMT);
        trimmedArmorItem(ModItems.DIARKRITE_CHESTPLATE);
        trimmedArmorItem(ModItems.DIARKRITE_LEGGINGS);
        trimmedArmorItem(ModItems.DIARKRITE_BOOTS);

        block(ModBlocks.STEEL_BLOCK);
        block(ModBlocks.ANTHEKTITE_BLOCK);
        block(ModBlocks.DIARKRITE_BLOCK);
        block(ModBlocks.REMNANT);

//        if (ModList.get().isLoaded("farmersdelight")) {
//            compatToolItem(FDItemsRegistry.STEEL_KNIFE);
//            compatToolItem(FDItemsRegistry.ANTHEKTITE_KNIFE);
//            compatToolItem(FDItemsRegistry.DIARKRITE_KNIFE);
//        }
//
//        if (ModList.get().isLoaded("piercingpaxels")) {
//            compatToolItem(PPItemsRegistry.STEEL_PAXEL);
//            compatToolItem(PPItemsRegistry.ANTHEKTITE_PAXEL);
//            compatToolItem(PPItemsRegistry.DIARKRITE_PAXEL);
//            compatNormalItem(PPItemsRegistry.ANTHEKTITE_UPGRADE_KIT);
//            compatNormalItem(PPItemsRegistry.DIARKRITE_UPGRADE_KIT);
//        }
//
//        if (ModList.get().isLoaded("nethersdelight")) {
//            compatToolItem(NDItemsRegistry.STEEL_MACHETE);
//            compatToolItem(NDItemsRegistry.ANTHEKTITE_MACHETE);
//            compatToolItem(NDItemsRegistry.DIARKRITE_MACHETE);
//        }
//
//        if (ModList.get().isLoaded("aether")) {
//            compatNormalItem(AEItemsRegistry.STEEL_GLOVES);
//            compatNormalItem(AEItemsRegistry.ANTHEKTITE_GLOVES);
//            compatNormalItem(AEItemsRegistry.DIARKRITE_GLOVES);
//        }
//
//        if (ModList.get().isLoaded("irons_spellbooks")) {
//            compatNormalItem(ISSItemsRegistry.ANTHEKTITE_WIZARD_HELMET);
//            compatNormalItem(ISSItemsRegistry.ANTHEKTITE_WIZARD_CHESTPLATE);
//            compatNormalItem(ISSItemsRegistry.ANTHEKTITE_WIZARD_LEGGINGS);
//            compatNormalItem(ISSItemsRegistry.ANTHEKTITE_WIZARD_BOOTS);
//            compatNormalItem(ISSItemsRegistry.DIARKRITE_WIZARD_HELMET);
//            compatNormalItem(ISSItemsRegistry.DIARKRITE_WIZARD_CHESTPLATE);
//            compatNormalItem(ISSItemsRegistry.DIARKRITE_WIZARD_LEGGINGS);
//            compatNormalItem(ISSItemsRegistry.DIARKRITE_WIZARD_BOOTS);
//        }

        if (ModList.get().isLoaded("aether")) {
            this.glovesItem(AEItemsRegistry.STEEL_GLOVES.get(), "compat/aether/");
            this.glovesItem(AEItemsRegistry.DIARKRITE_GLOVES.get(), "compat/aether/");
            this.glovesItem(AEItemsRegistry.ANTHEKTITE_GLOVES.get(), "compat/aether/");
        }
    }
}
