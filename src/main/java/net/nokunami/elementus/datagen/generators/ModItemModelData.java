package net.nokunami.elementus.datagen.generators;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.nokunami.elementus.common.registry.ModBlocks.ElementusBlocks;
import net.nokunami.elementus.common.registry.ModItems.ElementusItems;
import net.nokunami.elementus.datagen.providers.ModItemModelProvider;

public class ModItemModelData extends ModItemModelProvider {
    public ModItemModelData(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
        super(output, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        Elementus();
    }

    private void Elementus() {
        generatedItem(ElementusItems.CRUDE_STEEL, "ingredients");
        generatedItem(ElementusItems.STEEL_SCRAP, "ingredients");
        generatedItem(ElementusItems.STEEL_INGOT, "ingredients");
        generatedItem(ElementusItems.STEEL_NUGGET, "ingredients");
        generatedItem(ElementusItems.ATELIS_SCRAP, "ingredients");
        generatedItem(ElementusItems.ANTHEKTITE_INGOT, "ingredients");
        generatedItem(ElementusItems.DIARKRITE_INGOT, "ingredients");
        generatedItem(ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, "ingredients");
        generatedItem(ElementusItems.WEAPON_FRAGMENT, "ingredients");

        generatedItem(ElementusItems.MOVCADIA_BERRIES, "food");
        generatedItem(ElementusItems.GLISTERING_MOVCADIA_BERRIES, "food");
        generatedItem(ElementusItems.MOVCADIA_ESSENCE, "ingredients");

        generatedItem(ElementusItems.CRUSHED_REMNANT, "compat/create");

        spawnEggItem(ElementusItems.STEEL_GOLEM_SPAWN_EGG);

        handheldItem(ElementusItems.STEEL_SWORD, "tools");
        handheldItem(ElementusItems.STEEL_SHOVEL, "tools");
        handheldItem(ElementusItems.STEEL_PICKAXE, "tools");
        handheldItem(ElementusItems.STEEL_AXE, "tools");
        handheldItem(ElementusItems.STEEL_HOE, "tools");

        handheldItem(ElementusItems.DIARKRITE_SWORD, "tools");
        handheldItem(ElementusItems.DIARKRITE_SHOVEL, "tools");
        handheldItem(ElementusItems.DIARKRITE_PICKAXE, "tools");
        handheldItem(ElementusItems.DIARKRITE_AXE, "tools");
        handheldItem(ElementusItems.DIARKRITE_HOE, "tools");

        handheldItem(ElementusItems.ANTHEKTITE_SWORD, "tools");
        handheldItem(ElementusItems.ANTHEKTITE_SHOVEL, "tools");
        handheldItem(ElementusItems.ANTHEKTITE_PICKAXE, "tools");
        handheldItem(ElementusItems.ANTHEKTITE_AXE, "tools");
        handheldItem(ElementusItems.ANTHEKTITE_HOE, "tools");

        movcadiaTools(ElementusItems.MOVCADIA_SWORD, "tools");
        movcadiaTools(ElementusItems.MOVCADIA_SHOVEL, "tools");
        movcadiaTools(ElementusItems.MOVCADIA_PICKAXE, "tools");
        movcadiaTools(ElementusItems.MOVCADIA_AXE, "tools");
        movcadiaTools(ElementusItems.MOVCADIA_HOE, "tools");

//        diarkriteChargeBlade(ElementusItems.DIARKRITE_CHARGE_BLADE, "weapons/diarkrite_charge_blade");
//        anthektiteLongsword(ElementusItems.ANTHEKTITE_CHARGE_BLADE, "weapons/anthektite_longsword");

        tridentModel(ElementusItems.TEST_TRIDENT, "weapons");

        shieldItem(ElementusItems.STEEL_SHIELD, "shield");
        shieldItem(ElementusItems.DIARKRITE_SHIELD, "shield");
        shieldItem(ElementusItems.ANTHEKTITE_SHIELD, "shield");

        bowItem(ElementusItems.STEEL_BOW, "bows");
        bowItem(ElementusItems.DIARKRITE_BOW, "bows");
        bowItem(ElementusItems.ANTHEKTITE_BOW, "bows");

        armorItem(ElementusItems.STEEL_HELMET, "armor");
        armorItem(ElementusItems.STEEL_CHESTPLATE, "armor");
        armorItem(ElementusItems.STEEL_LEGGINGS, "armor");
        armorItem(ElementusItems.STEEL_BOOTS, "armor");

        armorItem(ElementusItems.ANTHEKTITE_HELMET, "armor");
        armorItem(ElementusItems.ANTHEKTITE_CHESTPLATE, "armor");
        armorItem(ElementusItems.ANTHEKTITE_LEGGINGS, "armor");
        armorItem(ElementusItems.ANTHEKTITE_BOOTS, "armor");

        armorItem(ElementusItems.DIARKRITE_HELMET, "armor");
        armorItem(ElementusItems.DIARKRITE_CHESTPLATE, "armor");
        armorItem(ElementusItems.DIARKRITE_LEGGINGS, "armor");
        armorItem(ElementusItems.DIARKRITE_BOOTS, "armor");

        catalystArmor(ElementusItems.CATALYST_CHESTPLATE);

        generatedItem(ElementusItems.REINFORCED_PLATING_GOLEM_UPGRADE, "miscellaneous");

        blockItem(ElementusBlocks.STEEL_BLOCK);
        blockItem(ElementusBlocks.ANTHEKTITE_BLOCK);
        blockItem(ElementusBlocks.DIARKRITE_BLOCK);
        blockItem(ElementusBlocks.REMNANT);

        itemBlockGenerated(ElementusItems.STEEL_BARS, "building");

        blockItem(ElementusBlocks.STEEL_TILES);
        blockItem(ElementusBlocks.STEEL_TILE_STAIR);
        blockItem(ElementusBlocks.STEEL_TILE_SLAB);

        itemBlockGenerated(ElementusItems.MOVCADIA_SAPLING, "natural");
        blockItem(ElementusBlocks.MOVCADIA_ROOTED_DIRT);
        blockItem(ElementusBlocks.MOVCADIA_ROOTED_STONE);
        blockItem(ElementusBlocks.MOVCADIA_ROOTED_DEEPSLATE);

        blockItem(ElementusBlocks.MOVCADIA_LOG);
        blockItem(ElementusBlocks.STRIPPED_MOVCADIA_LOG);
        blockItem(ElementusBlocks.MOVCADIA_WOOD);
        blockItem(ElementusBlocks.STRIPPED_MOVCADIA_WOOD);
        blockItem(ElementusBlocks.MOVCADIA_PLANKS);
        blockItem(ElementusBlocks.MOVCADIA_STAIRS);
        blockItem(ElementusBlocks.MOVCADIA_SLAB);
        fenceItem(ElementusBlocks.MOVCADIA_FENCE, ElementusBlocks.MOVCADIA_PLANKS, "building");
        blockItem(ElementusBlocks.MOVCADIA_FENCE_GATE);
        generatedItem(ElementusItems.MOVCADIA_DOOR, "miscellaneous");
        itemBlock(ElementusBlocks.MOVCADIA_TRAPDOOR, "_bottom");
        blockItem(ElementusBlocks.MOVCADIA_PRESSURE_PLATE);
        buttonItem(ElementusBlocks.MOVCADIA_BUTTON, ElementusBlocks.MOVCADIA_PLANKS, "building");
        generatedItem(ElementusItems.MOVCADIA_SIGN, "miscellaneous");
        generatedItem(ElementusItems.MOVCADIA_HANGING_SIGN, "miscellaneous");
        generatedItem(ElementusItems.STURDY_MOVCADIA_SIGN, "miscellaneous");

        itemCustomParentModel(ElementusItems.MOVCADIA_CHEST, "block/movcadia_chest");

        generatedItem(ElementusItems.MOVCADIA_BOAT, "miscellaneous");
        generatedItem(ElementusItems.MOVCADIA_CHEST_BOAT, "miscellaneous");

        blockItem(ElementusBlocks.MOVCADIA_LEAVES);
        blockItem(ElementusBlocks.FLOWERING_MOVCADIA_LEAVES);
    }
}
