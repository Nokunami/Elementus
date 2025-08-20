package net.nokunami.elementus.datagen.generators;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.nokunami.elementus.common.registry.ModBlocks.ElementusBlocks;
import net.nokunami.elementus.common.registry.ModItems;
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
        generatedItem(ModItems.CRUDE_STEEL, "ingredients");
        generatedItem(ModItems.STEEL_SCRAP, "ingredients");
        generatedItem(ModItems.STEEL_INGOT, "ingredients");
        generatedItem(ModItems.STEEL_NUGGET, "ingredients");
        generatedItem(ModItems.ATELIS_SCRAP, "ingredients");
        generatedItem(ModItems.ANTHEKTITE_INGOT, "ingredients");
        generatedItem(ModItems.DIARKRITE_INGOT, "ingredients");
        generatedItem(ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, "ingredients");
        generatedItem(ModItems.WEAPON_FRAGMENT, "ingredients");

        generatedItem(ModItems.MOVCADIA_BERRIES, "food");
        generatedItem(ModItems.GLISTERING_MOVCADIA_BERRIES, "food");
        generatedItem(ModItems.MOVCADIA_ESSENCE, "ingredients");

        generatedItem(ModItems.CRUSHED_REMNANT, "compat/create");

        spawnEggItem(ModItems.STEEL_GOLEM_SPAWN_EGG);

        handheldItem(ModItems.STEEL_SWORD, "tools");
        handheldItem(ModItems.STEEL_SHOVEL, "tools");
        handheldItem(ModItems.STEEL_PICKAXE, "tools");
        handheldItem(ModItems.STEEL_AXE, "tools");
        handheldItem(ModItems.STEEL_HOE, "tools");

        handheldItem(ModItems.DIARKRITE_SWORD, "tools");
        handheldItem(ModItems.DIARKRITE_SHOVEL, "tools");
        handheldItem(ModItems.DIARKRITE_PICKAXE, "tools");
        handheldItem(ModItems.DIARKRITE_AXE, "tools");
        handheldItem(ModItems.DIARKRITE_HOE, "tools");

        handheldItem(ModItems.ANTHEKTITE_SWORD, "tools");
        handheldItem(ModItems.ANTHEKTITE_SHOVEL, "tools");
        handheldItem(ModItems.ANTHEKTITE_PICKAXE, "tools");
        handheldItem(ModItems.ANTHEKTITE_AXE, "tools");
        handheldItem(ModItems.ANTHEKTITE_HOE, "tools");

        movcadiaTools(ModItems.MOVCADIA_SWORD, "tools");
        movcadiaTools(ModItems.MOVCADIA_SHOVEL, "tools");
        movcadiaTools(ModItems.MOVCADIA_PICKAXE, "tools");
        movcadiaTools(ModItems.MOVCADIA_AXE, "tools");
        movcadiaTools(ModItems.MOVCADIA_HOE, "tools");

        diarkriteChargeBlade(ModItems.DIARKRITE_CHARGE_BLADE, "weapons/diarkrite_charge_blade");
        anthektiteLongsword(ModItems.ANTHEKTITE_CHARGE_BLADE, "weapons/anthektite_longsword");

        tridentModel(ModItems.TEST_TRIDENT, "weapons");

        shieldItem(ModItems.STEEL_SHIELD, "shield");
        shieldItem(ModItems.DIARKRITE_SHIELD, "shield");
        shieldItem(ModItems.ANTHEKTITE_SHIELD, "shield");

        bowItem(ModItems.STEEL_BOW, "bows");
        bowItem(ModItems.DIARKRITE_BOW, "bows");
        bowItem(ModItems.ANTHEKTITE_BOW, "bows");

        armorItem(ModItems.STEEL_HELMET, "armor");
        armorItem(ModItems.STEEL_CHESTPLATE, "armor");
        armorItem(ModItems.STEEL_LEGGINGS, "armor");
        armorItem(ModItems.STEEL_BOOTS, "armor");

        armorItem(ModItems.ANTHEKTITE_HELMET, "armor");
        armorItem(ModItems.ANTHEKTITE_CHESTPLATE, "armor");
        armorItem(ModItems.ANTHEKTITE_LEGGINGS, "armor");
        armorItem(ModItems.ANTHEKTITE_BOOTS, "armor");

        armorItem(ModItems.DIARKRITE_HELMET, "armor");
        armorItem(ModItems.DIARKRITE_CHESTPLATE, "armor");
        armorItem(ModItems.DIARKRITE_LEGGINGS, "armor");
        armorItem(ModItems.DIARKRITE_BOOTS, "armor");

        catalystArmor(ModItems.CATALYST_CHESTPLATE);

        generatedItem(ModItems.REINFORCED_PLATING_GOLEM_UPGRADE, "miscellaneous");

        blockItem(ElementusBlocks.STEEL_BLOCK);
        blockItem(ElementusBlocks.ANTHEKTITE_BLOCK);
        blockItem(ElementusBlocks.DIARKRITE_BLOCK);
        blockItem(ElementusBlocks.REMNANT);

        itemBlockGenerated(ModItems.STEEL_BARS, "building");

        blockItem(ElementusBlocks.STEEL_TILES);
        blockItem(ElementusBlocks.STEEL_TILE_STAIR);
        blockItem(ElementusBlocks.STEEL_TILE_SLAB);

        itemBlockGenerated(ModItems.MOVCADIA_SAPLING, "natural");
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
        generatedItem(ModItems.MOVCADIA_DOOR, "miscellaneous");
        itemBlock(ElementusBlocks.MOVCADIA_TRAPDOOR, "_bottom");
        blockItem(ElementusBlocks.MOVCADIA_PRESSURE_PLATE);
        buttonItem(ElementusBlocks.MOVCADIA_BUTTON, ElementusBlocks.MOVCADIA_PLANKS, "building");
        generatedItem(ModItems.MOVCADIA_SIGN, "miscellaneous");
        generatedItem(ModItems.MOVCADIA_HANGING_SIGN, "miscellaneous");
        generatedItem(ModItems.STURDY_MOVCADIA_SIGN, "miscellaneous");

        itemCustomParentModel(ModItems.MOVCADIA_CHEST, "block/movcadia_chest");

        generatedItem(ModItems.MOVCADIA_BOAT, "miscellaneous");
        generatedItem(ModItems.MOVCADIA_CHEST_BOAT, "miscellaneous");

        blockItem(ElementusBlocks.MOVCADIA_LEAVES);
        blockItem(ElementusBlocks.FLOWERING_MOVCADIA_LEAVES);
    }
}
