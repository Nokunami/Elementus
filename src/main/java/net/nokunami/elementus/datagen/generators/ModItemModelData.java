package net.nokunami.elementus.datagen.generators;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.nokunami.elementus.common.registry.EBlocks;
import net.nokunami.elementus.common.registry.EItems;
import net.nokunami.elementus.datagen.providers.ModItemModelProvider;

public class ModItemModelData extends ModItemModelProvider {
    public ModItemModelData(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
        super(output, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        generatedItem(EItems.CRUDE_STEEL, "ingredients");
        generatedItem(EItems.STEEL_SCRAP, "ingredients");
        generatedItem(EItems.STEEL_INGOT, "ingredients");
        generatedItem(EItems.STEEL_NUGGET, "ingredients");
        generatedItem(EItems.ATELIS_SCRAP, "ingredients");
        generatedItem(EItems.ANTHEKTITE_INGOT, "ingredients");
        generatedItem(EItems.DIARKRITE_INGOT, "ingredients");
        generatedItem(EItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, "ingredients");
        generatedItem(EItems.WEAPON_FRAGMENT, "ingredients");

        generatedItem(EItems.MOVCADIA_BERRIES, "food");
        generatedItem(EItems.GLISTERING_MOVCADIA_BERRIES, "food");
        generatedItem(EItems.MOVCADIA_ESSENCE, "ingredients");

        generatedItem(EItems.CRUSHED_REMNANT, "compat/create");

        spawnEggItem(EItems.ASTALITE_GOLEM_SPAWN_EGG);
        spawnEggItem(EItems.STEEL_GOLEM_SPAWN_EGG);

        handheldItem(EItems.STEEL_SWORD, "tools");
        handheldItem(EItems.STEEL_SHOVEL, "tools");
        handheldItem(EItems.STEEL_PICKAXE, "tools");
        handheldItem(EItems.STEEL_AXE, "tools");
        handheldItem(EItems.STEEL_HOE, "tools");

        handheldItem(EItems.DIARKRITE_SWORD, "tools");
        handheldItem(EItems.DIARKRITE_SHOVEL, "tools");
        handheldItem(EItems.DIARKRITE_PICKAXE, "tools");
        handheldItem(EItems.DIARKRITE_AXE, "tools");
        handheldItem(EItems.DIARKRITE_HOE, "tools");

        handheldItem(EItems.ANTHEKTITE_SWORD, "tools");
        handheldItem(EItems.ANTHEKTITE_SHOVEL, "tools");
        handheldItem(EItems.ANTHEKTITE_PICKAXE, "tools");
        handheldItem(EItems.ANTHEKTITE_AXE, "tools");
        handheldItem(EItems.ANTHEKTITE_HOE, "tools");

        movcadiaTools(EItems.MOVCADIA_SWORD, "tools");
        movcadiaTools(EItems.MOVCADIA_SHOVEL, "tools");
        movcadiaTools(EItems.MOVCADIA_PICKAXE, "tools");
        movcadiaTools(EItems.MOVCADIA_AXE, "tools");
        movcadiaTools(EItems.MOVCADIA_HOE, "tools");

        diarkriteChargeBlade(EItems.DIARKRITE_CHARGE_BLADE, "weapons/diarkrite_charge_blade");
        anthektiteLongsword(EItems.ANTHEKTITE_CHARGE_BLADE, "weapons/anthektite_longsword");

        tridentModel(EItems.WRATH_TRIDENT, "weapons");

        shieldItem(EItems.STEEL_SHIELD, "shield");
        shieldItem(EItems.DIARKRITE_SHIELD, "shield");
        shieldItem(EItems.ANTHEKTITE_SHIELD, "shield");

        bowItem(EItems.STEEL_BOW, "bows");
        bowItem(EItems.DIARKRITE_BOW, "bows");
        bowItem(EItems.ANTHEKTITE_BOW, "bows");

        armorItem(EItems.STEEL_HELMET, "armor");
        armorItem(EItems.STEEL_CHESTPLATE, "armor");
        armorItem(EItems.STEEL_LEGGINGS, "armor");
        armorItem(EItems.STEEL_BOOTS, "armor");

        armorItem(EItems.ANTHEKTITE_HELMET, "armor");
        armorItem(EItems.ANTHEKTITE_CHESTPLATE, "armor");
        armorItem(EItems.ANTHEKTITE_LEGGINGS, "armor");
        armorItem(EItems.ANTHEKTITE_BOOTS, "armor");

        armorItem(EItems.DIARKRITE_HELMET, "armor");
        armorItem(EItems.DIARKRITE_CHESTPLATE, "armor");
        armorItem(EItems.DIARKRITE_LEGGINGS, "armor");
        armorItem(EItems.DIARKRITE_BOOTS, "armor");

        catalystArmor(EItems.CATALYST_CHESTPLATE);

        armorItem(EItems.TEST_CATALYST_CHESTPLATE, "armor");

        generatedItem(EItems.REINFORCED_PLATING_GOLEM_UPGRADE, "miscellaneous");

        blockItem(EBlocks.STEEL_BLOCK);
        blockItem(EBlocks.ANTHEKTITE_BLOCK);
        blockItem(EBlocks.DIARKRITE_BLOCK);
        blockItem(EBlocks.REMNANT);

        itemBlockGenerated(EItems.STEEL_BARS, "building");

        blockItem(EBlocks.STEEL_TILES);
        blockItem(EBlocks.STEEL_TILE_STAIR);
        blockItem(EBlocks.STEEL_TILE_SLAB);

        itemBlockGenerated(EItems.MOVCADIA_SAPLING, "natural");
        blockItem(EBlocks.MOVCADIA_ROOTED_DIRT);
        blockItem(EBlocks.MOVCADIA_ROOTED_STONE);
        blockItem(EBlocks.MOVCADIA_ROOTED_DEEPSLATE);

        blockItem(EBlocks.MOVCADIA_LOG);
        blockItem(EBlocks.STRIPPED_MOVCADIA_LOG);
        blockItem(EBlocks.MOVCADIA_WOOD);
        blockItem(EBlocks.STRIPPED_MOVCADIA_WOOD);
        blockItem(EBlocks.MOVCADIA_PLANKS);
        blockItem(EBlocks.MOVCADIA_STAIRS);
        blockItem(EBlocks.MOVCADIA_SLAB);
        fenceItem(EBlocks.MOVCADIA_FENCE, EBlocks.MOVCADIA_PLANKS, "building");
        blockItem(EBlocks.MOVCADIA_FENCE_GATE);
        generatedItem(EItems.MOVCADIA_DOOR, "miscellaneous");
        itemBlock(EBlocks.MOVCADIA_TRAPDOOR, "_bottom");
        blockItem(EBlocks.MOVCADIA_PRESSURE_PLATE);
        buttonItem(EBlocks.MOVCADIA_BUTTON, EBlocks.MOVCADIA_PLANKS, "building");
        generatedItem(EItems.MOVCADIA_SIGN, "miscellaneous");
        generatedItem(EItems.MOVCADIA_HANGING_SIGN, "miscellaneous");
        generatedItem(EItems.STURDY_MOVCADIA_SIGN, "miscellaneous");

        itemCustomParentModel(EItems.MOVCADIA_CHEST, "block/movcadia_chest");

        generatedItem(EItems.MOVCADIA_BOAT, "miscellaneous");
        generatedItem(EItems.MOVCADIA_CHEST_BOAT, "miscellaneous");

        blockItem(EBlocks.MOVCADIA_LEAVES);
        blockItem(EBlocks.FLOWERING_MOVCADIA_LEAVES);
    }
}
