package net.nokunami.elementus.datagen.generators;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.nokunami.elementus.datagen.providers.ModItemModelProvider;
import net.nokunami.elementus.common.registry.ModBlocks.*;
import net.nokunami.elementus.common.registry.ModItems.*;

import static net.nokunami.elementus.ModChecker.*;

public class ModItemModelData extends ModItemModelProvider {
    public ModItemModelData(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
        super(output, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        Elementus();
        if (farmersDelight) FarmersDelight();
        if (piercingPaxels) PiercingPaxels();
        if (nethersDelight) NethersDelight();
        if (ironsSpellbooks) IronsSpellbooks();
        if (aether) Aether();
        if (simplySwords) SimplySwords();
        if (sniffsWeapons) SniffsWeapons();
        if (advancedNetherite) AdvancedNetherite();
        if (samuraiDynasty) EpicSamurai();
        if (twigs) Twigs();
        if (witherStormMod) WitherStormmod();
        if (vanillaClaws) BanillaClaws();
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

        handheldItem(ElementusItems.ANTHEKTITE_SWORD, "tools");
        handheldItem(ElementusItems.ANTHEKTITE_SHOVEL, "tools");
        handheldItem(ElementusItems.ANTHEKTITE_PICKAXE, "tools");
        handheldItem(ElementusItems.ANTHEKTITE_AXE, "tools");
        handheldItem(ElementusItems.ANTHEKTITE_HOE, "tools");

        handheldItem(ElementusItems.DIARKRITE_SWORD, "tools");
        handheldItem(ElementusItems.DIARKRITE_SHOVEL, "tools");
        handheldItem(ElementusItems.DIARKRITE_PICKAXE, "tools");
        handheldItem(ElementusItems.DIARKRITE_AXE, "tools");
        handheldItem(ElementusItems.DIARKRITE_HOE, "tools");

//        chargerItem(ElementusItems.DIARKRITE_CHARGE_BLADE, "tools/diarkrite_charge_blade");
        diarkriteChargeBlade(ElementusItems.DIARKRITE_CHARGE_BLADE, "tools/diarkrite_charge_blade");

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
    private void FarmersDelight() {
        handheldItem(FarmersDelightItems.STEEL_KNIFE, "compat/farmers_delight");
        handheldItem(FarmersDelightItems.ANTHEKTITE_KNIFE, "compat/farmers_delight");
        handheldItem(FarmersDelightItems.DIARKRITE_KNIFE, "compat/farmers_delight");
        blockItem(FarmersDelightBlocks.MOVCADIA_CABINET);
    }
    private void PiercingPaxels() {
        handheldItem(PiercingPaxelsItems.STEEL_PAXEL, "compat/piercingpaxels");
        handheldItem(PiercingPaxelsItems.ANTHEKTITE_PAXEL, "compat/piercingpaxels");
        handheldItem(PiercingPaxelsItems.DIARKRITE_PAXEL, "compat/piercingpaxels");
        generatedItem(PiercingPaxelsItems.ANTHEKTITE_UPGRADE_KIT, "compat/piercingpaxels");
        generatedItem(PiercingPaxelsItems.DIARKRITE_UPGRADE_KIT, "compat/piercingpaxels");
    }
    private void NethersDelight() {
        handheldItem(NethersDelightItems.STEEL_MACHETE, "compat/nethers_delight");
        handheldItem(NethersDelightItems.ANTHEKTITE_MACHETE, "compat/nethers_delight");
        handheldItem(NethersDelightItems.DIARKRITE_MACHETE, "compat/nethers_delight");
    }
    private void IronsSpellbooks() {
        generatedItem(IronsSpellbooksItems.ANTHEKTITE_MAGE_HELMET, "compat/irons_spellbooks");
        generatedItem(IronsSpellbooksItems.ANTHEKTITE_MAGE_CHESTPLATE, "compat/irons_spellbooks");
        generatedItem(IronsSpellbooksItems.ANTHEKTITE_MAGE_LEGGINGS, "compat/irons_spellbooks");
        generatedItem(IronsSpellbooksItems.ANTHEKTITE_MAGE_BOOTS, "compat/irons_spellbooks");
        generatedItem(IronsSpellbooksItems.DIARKRITE_MAGE_HELMET, "compat/irons_spellbooks");
        generatedItem(IronsSpellbooksItems.DIARKRITE_MAGE_CHESTPLATE, "compat/irons_spellbooks");
        generatedItem(IronsSpellbooksItems.DIARKRITE_MAGE_LEGGINGS, "compat/irons_spellbooks");
        generatedItem(IronsSpellbooksItems.DIARKRITE_MAGE_BOOTS, "compat/irons_spellbooks");
        spellBookItem(IronsSpellbooksItems.STEEL_SPELL_BOOK);
        spellBookItem(IronsSpellbooksItems.DIARKRITE_SPELL_BOOK);
        spellBookItem(IronsSpellbooksItems.ANTHEKTITE_SPELL_BOOK);
    }
    private void Aether() {
        armorItem(AetherItems.STEEL_GLOVES, "compat/aether");
        armorItem(AetherItems.DIARKRITE_GLOVES, "compat/aether");
        armorItem(AetherItems.ANTHEKTITE_GLOVES, "compat/aether");
    }
    private void SimplySwords() {
        simplySwordsItem("steel");
        simplySwordsItem("diarkrite");
        simplySwordsItem("anthektite");
    }
    private void SniffsWeapons() {
        sniffsWeaponsItem("steel", "armor");
        sniffsWeaponsItem("diarkrite", "armor");
        sniffsWeaponsItem("anthektite", "armor");

        sniffsWeaponsItem("steel", "greatSword");
        sniffsWeaponsItem("diarkrite", "greatSword");
        sniffsWeaponsItem("anthektite", "greatSword");

        sniffsWeaponsItem("steel", "greatAxe");
        sniffsWeaponsItem("diarkrite", "greatAxe");
        sniffsWeaponsItem("anthektite", "greatAxe");

        sniffsWeaponsItem("steel", "greatPickaxe");
        sniffsWeaponsItem("diarkrite", "greatPickaxe");
        sniffsWeaponsItem("anthektite", "greatPickaxe");

        sniffsWeaponsItem("steel", "naginata");
        sniffsWeaponsItem("diarkrite", "naginata");
        sniffsWeaponsItem("anthektite", "naginata");
    }
    private void AdvancedNetherite() {
        generatedItem(AdvancedNetheriteItems.DIARKRITE_IRON, "compat/advancednetherite/ingredient");
        generatedItem(AdvancedNetheriteItems.DIARKRITE_GOLD, "compat/advancednetherite/ingredient");
        generatedItem(AdvancedNetheriteItems.DIARKRITE_EMERALD, "compat/advancednetherite/ingredient");
        generatedItem(AdvancedNetheriteItems.DIARKRITE_DIAMOND, "compat/advancednetherite/ingredient");
        generatedItem(AdvancedNetheriteItems.ANTHEKTITE_IRON, "compat/advancednetherite/ingredient");
        generatedItem(AdvancedNetheriteItems.ANTHEKTITE_GOLD, "compat/advancednetherite/ingredient");
        generatedItem(AdvancedNetheriteItems.ANTHEKTITE_EMERALD, "compat/advancednetherite/ingredient");
        generatedItem(AdvancedNetheriteItems.ANTHEKTITE_DIAMOND, "compat/advancednetherite/ingredient");


        handheldItem(AdvancedNetheriteItems.DIARKRITE_IRON_SWORD, "compat/advancednetherite/tools");
        handheldItem(AdvancedNetheriteItems.DIARKRITE_IRON_SHOVEL, "compat/advancednetherite/tools");
        handheldItem(AdvancedNetheriteItems.DIARKRITE_IRON_PICKAXE, "compat/advancednetherite/tools");
        handheldItem(AdvancedNetheriteItems.DIARKRITE_IRON_AXE, "compat/advancednetherite/tools");
        handheldItem(AdvancedNetheriteItems.DIARKRITE_IRON_HOE, "compat/advancednetherite/tools");

        handheldItem(AdvancedNetheriteItems.DIARKRITE_GOLD_SWORD, "compat/advancednetherite/tools");
        handheldItem(AdvancedNetheriteItems.DIARKRITE_GOLD_SHOVEL, "compat/advancednetherite/tools");
        handheldItem(AdvancedNetheriteItems.DIARKRITE_GOLD_PICKAXE, "compat/advancednetherite/tools");
        handheldItem(AdvancedNetheriteItems.DIARKRITE_GOLD_AXE, "compat/advancednetherite/tools");
        handheldItem(AdvancedNetheriteItems.DIARKRITE_GOLD_HOE, "compat/advancednetherite/tools");

        handheldItem(AdvancedNetheriteItems.DIARKRITE_EMERALD_SWORD, "compat/advancednetherite/tools");
        handheldItem(AdvancedNetheriteItems.DIARKRITE_EMERALD_SHOVEL, "compat/advancednetherite/tools");
        handheldItem(AdvancedNetheriteItems.DIARKRITE_EMERALD_PICKAXE, "compat/advancednetherite/tools");
        handheldItem(AdvancedNetheriteItems.DIARKRITE_EMERALD_AXE, "compat/advancednetherite/tools");
        handheldItem(AdvancedNetheriteItems.DIARKRITE_EMERALD_HOE, "compat/advancednetherite/tools");

        handheldItem(AdvancedNetheriteItems.DIARKRITE_DIAMOND_SWORD, "compat/advancednetherite/tools");
        handheldItem(AdvancedNetheriteItems.DIARKRITE_DIAMOND_SHOVEL, "compat/advancednetherite/tools");
        handheldItem(AdvancedNetheriteItems.DIARKRITE_DIAMOND_PICKAXE, "compat/advancednetherite/tools");
        handheldItem(AdvancedNetheriteItems.DIARKRITE_DIAMOND_AXE, "compat/advancednetherite/tools");
        handheldItem(AdvancedNetheriteItems.DIARKRITE_DIAMOND_HOE, "compat/advancednetherite/tools");


        handheldItem(AdvancedNetheriteItems.ANTHEKTITE_IRON_SWORD, "compat/advancednetherite/tools");
        handheldItem(AdvancedNetheriteItems.ANTHEKTITE_IRON_SHOVEL, "compat/advancednetherite/tools");
        handheldItem(AdvancedNetheriteItems.ANTHEKTITE_IRON_PICKAXE, "compat/advancednetherite/tools");
        handheldItem(AdvancedNetheriteItems.ANTHEKTITE_IRON_AXE, "compat/advancednetherite/tools");
        handheldItem(AdvancedNetheriteItems.ANTHEKTITE_IRON_HOE, "compat/advancednetherite/tools");

        handheldItem(AdvancedNetheriteItems.ANTHEKTITE_GOLD_SWORD, "compat/advancednetherite/tools");
        handheldItem(AdvancedNetheriteItems.ANTHEKTITE_GOLD_SHOVEL, "compat/advancednetherite/tools");
        handheldItem(AdvancedNetheriteItems.ANTHEKTITE_GOLD_PICKAXE, "compat/advancednetherite/tools");
        handheldItem(AdvancedNetheriteItems.ANTHEKTITE_GOLD_AXE, "compat/advancednetherite/tools");
        handheldItem(AdvancedNetheriteItems.ANTHEKTITE_GOLD_HOE, "compat/advancednetherite/tools");

        handheldItem(AdvancedNetheriteItems.ANTHEKTITE_EMERALD_SWORD, "compat/advancednetherite/tools");
        handheldItem(AdvancedNetheriteItems.ANTHEKTITE_EMERALD_SHOVEL, "compat/advancednetherite/tools");
        handheldItem(AdvancedNetheriteItems.ANTHEKTITE_EMERALD_PICKAXE, "compat/advancednetherite/tools");
        handheldItem(AdvancedNetheriteItems.ANTHEKTITE_EMERALD_AXE, "compat/advancednetherite/tools");
        handheldItem(AdvancedNetheriteItems.ANTHEKTITE_EMERALD_HOE, "compat/advancednetherite/tools");

        handheldItem(AdvancedNetheriteItems.ANTHEKTITE_DIAMOND_SWORD, "compat/advancednetherite/tools");
        handheldItem(AdvancedNetheriteItems.ANTHEKTITE_DIAMOND_SHOVEL, "compat/advancednetherite/tools");
        handheldItem(AdvancedNetheriteItems.ANTHEKTITE_DIAMOND_PICKAXE, "compat/advancednetherite/tools");
        handheldItem(AdvancedNetheriteItems.ANTHEKTITE_DIAMOND_AXE, "compat/advancednetherite/tools");
        handheldItem(AdvancedNetheriteItems.ANTHEKTITE_DIAMOND_HOE, "compat/advancednetherite/tools");


        armorItem(AdvancedNetheriteItems.DIARKRITE_IRON_HELMET, "compat/advancednetherite/armor");
        armorItem(AdvancedNetheriteItems.DIARKRITE_IRON_CHESTPLATE, "compat/advancednetherite/armor");
        armorItem(AdvancedNetheriteItems.DIARKRITE_IRON_LEGGINGS, "compat/advancednetherite/armor");
        armorItem(AdvancedNetheriteItems.DIARKRITE_IRON_BOOTS, "compat/advancednetherite/armor");

        armorItem(AdvancedNetheriteItems.DIARKRITE_GOLD_HELMET, "compat/advancednetherite/armor");
        armorItem(AdvancedNetheriteItems.DIARKRITE_GOLD_CHESTPLATE, "compat/advancednetherite/armor");
        armorItem(AdvancedNetheriteItems.DIARKRITE_GOLD_LEGGINGS, "compat/advancednetherite/armor");
        armorItem(AdvancedNetheriteItems.DIARKRITE_GOLD_BOOTS, "compat/advancednetherite/armor");

        armorItem(AdvancedNetheriteItems.DIARKRITE_EMERALD_HELMET, "compat/advancednetherite/armor");
        armorItem(AdvancedNetheriteItems.DIARKRITE_EMERALD_CHESTPLATE, "compat/advancednetherite/armor");
        armorItem(AdvancedNetheriteItems.DIARKRITE_EMERALD_LEGGINGS, "compat/advancednetherite/armor");
        armorItem(AdvancedNetheriteItems.DIARKRITE_EMERALD_BOOTS, "compat/advancednetherite/armor");

        armorItem(AdvancedNetheriteItems.DIARKRITE_DIAMOND_HELMET, "compat/advancednetherite/armor");
        armorItem(AdvancedNetheriteItems.DIARKRITE_DIAMOND_CHESTPLATE, "compat/advancednetherite/armor");
        armorItem(AdvancedNetheriteItems.DIARKRITE_DIAMOND_LEGGINGS, "compat/advancednetherite/armor");
        armorItem(AdvancedNetheriteItems.DIARKRITE_DIAMOND_BOOTS, "compat/advancednetherite/armor");


        armorItem(AdvancedNetheriteItems.ANTHEKTITE_IRON_HELMET, "compat/advancednetherite/armor");
        armorItem(AdvancedNetheriteItems.ANTHEKTITE_IRON_CHESTPLATE, "compat/advancednetherite/armor");
        armorItem(AdvancedNetheriteItems.ANTHEKTITE_IRON_LEGGINGS, "compat/advancednetherite/armor");
        armorItem(AdvancedNetheriteItems.ANTHEKTITE_IRON_BOOTS, "compat/advancednetherite/armor");

        armorItem(AdvancedNetheriteItems.ANTHEKTITE_GOLD_HELMET, "compat/advancednetherite/armor");
        armorItem(AdvancedNetheriteItems.ANTHEKTITE_GOLD_CHESTPLATE, "compat/advancednetherite/armor");
        armorItem(AdvancedNetheriteItems.ANTHEKTITE_GOLD_LEGGINGS, "compat/advancednetherite/armor");
        armorItem(AdvancedNetheriteItems.ANTHEKTITE_GOLD_BOOTS, "compat/advancednetherite/armor");

        armorItem(AdvancedNetheriteItems.ANTHEKTITE_EMERALD_HELMET, "compat/advancednetherite/armor");
        armorItem(AdvancedNetheriteItems.ANTHEKTITE_EMERALD_CHESTPLATE, "compat/advancednetherite/armor");
        armorItem(AdvancedNetheriteItems.ANTHEKTITE_EMERALD_LEGGINGS, "compat/advancednetherite/armor");
        armorItem(AdvancedNetheriteItems.ANTHEKTITE_EMERALD_BOOTS, "compat/advancednetherite/armor");

        armorItem(AdvancedNetheriteItems.ANTHEKTITE_DIAMOND_HELMET, "compat/advancednetherite/armor");
        armorItem(AdvancedNetheriteItems.ANTHEKTITE_DIAMOND_CHESTPLATE, "compat/advancednetherite/armor");
        armorItem(AdvancedNetheriteItems.ANTHEKTITE_DIAMOND_LEGGINGS, "compat/advancednetherite/armor");
        armorItem(AdvancedNetheriteItems.ANTHEKTITE_DIAMOND_BOOTS, "compat/advancednetherite/armor");

        blockItem(AdvancedNetheriteBlocks.DIARKRITE_IRON_BLOCK);
        blockItem(AdvancedNetheriteBlocks.DIARKRITE_GOLD_BLOCK);
        blockItem(AdvancedNetheriteBlocks.DIARKRITE_EMERALD_BLOCK);
        blockItem(AdvancedNetheriteBlocks.DIARKRITE_DIAMOND_BLOCK);

        blockItem(AdvancedNetheriteBlocks.ANTHEKTITE_IRON_BLOCK);
        blockItem(AdvancedNetheriteBlocks.ANTHEKTITE_GOLD_BLOCK);
        blockItem(AdvancedNetheriteBlocks.ANTHEKTITE_EMERALD_BLOCK);
        blockItem(AdvancedNetheriteBlocks.ANTHEKTITE_DIAMOND_BLOCK);
    }
    private void EpicSamurai() {
        generatedItem(EpicSamuraiItems.STEEL_SAMURAI_HELMET, "compat/epicsamurai");
        generatedItem(EpicSamuraiItems.STEEL_SAMURAI_CHESTPLATE, "compat/epicsamurai");
        generatedItem(EpicSamuraiItems.STEEL_SAMURAI_LEGGINGS, "compat/epicsamurai");
        generatedItem(EpicSamuraiItems.STEEL_SAMURAI_BOOTS, "compat/epicsamurai");

        generatedItem(EpicSamuraiItems.STEEL_SAMURAI_HELMET_LIGHT, "compat/epicsamurai");
        generatedItem(EpicSamuraiItems.STEEL_SAMURAI_CHESTPLATE_LIGHT, "compat/epicsamurai");
        generatedItem(EpicSamuraiItems.STEEL_SAMURAI_LEGGINGS_LIGHT, "compat/epicsamurai");
        generatedItem(EpicSamuraiItems.STEEL_SAMURAI_BOOTS_LIGHT, "compat/epicsamurai");

        generatedItem(EpicSamuraiItems.STEEL_SAMURAI_HELMET_MASTER, "compat/epicsamurai");
        generatedItem(EpicSamuraiItems.STEEL_SAMURAI_CHESTPLATE_MASTER, "compat/epicsamurai");
        generatedItem(EpicSamuraiItems.STEEL_SAMURAI_LEGGINGS_MASTER, "compat/epicsamurai");
        generatedItem(EpicSamuraiItems.STEEL_SAMURAI_BOOTS_MASTER, "compat/epicsamurai");


        generatedItem(EpicSamuraiItems.DIARKRITE_SAMURAI_HELMET, "compat/epicsamurai");
        generatedItem(EpicSamuraiItems.DIARKRITE_SAMURAI_CHESTPLATE, "compat/epicsamurai");
        generatedItem(EpicSamuraiItems.DIARKRITE_SAMURAI_LEGGINGS, "compat/epicsamurai");
        generatedItem(EpicSamuraiItems.DIARKRITE_SAMURAI_BOOTS, "compat/epicsamurai");

        generatedItem(EpicSamuraiItems.DIARKRITE_SAMURAI_HELMET_LIGHT, "compat/epicsamurai");
        generatedItem(EpicSamuraiItems.DIARKRITE_SAMURAI_CHESTPLATE_LIGHT, "compat/epicsamurai");
        generatedItem(EpicSamuraiItems.DIARKRITE_SAMURAI_LEGGINGS_LIGHT, "compat/epicsamurai");
        generatedItem(EpicSamuraiItems.DIARKRITE_SAMURAI_BOOTS_LIGHT, "compat/epicsamurai");

        generatedItem(EpicSamuraiItems.DIARKRITE_SAMURAI_HELMET_MASTER, "compat/epicsamurai");
        generatedItem(EpicSamuraiItems.DIARKRITE_SAMURAI_CHESTPLATE_MASTER, "compat/epicsamurai");
        generatedItem(EpicSamuraiItems.DIARKRITE_SAMURAI_LEGGINGS_MASTER, "compat/epicsamurai");
        generatedItem(EpicSamuraiItems.DIARKRITE_SAMURAI_BOOTS_MASTER, "compat/epicsamurai");


        generatedItem(EpicSamuraiItems.ANTHEKTITE_SAMURAI_HELMET, "compat/epicsamurai");
        generatedItem(EpicSamuraiItems.ANTHEKTITE_SAMURAI_CHESTPLATE, "compat/epicsamurai");
        generatedItem(EpicSamuraiItems.ANTHEKTITE_SAMURAI_LEGGINGS, "compat/epicsamurai");
        generatedItem(EpicSamuraiItems.ANTHEKTITE_SAMURAI_BOOTS, "compat/epicsamurai");

        generatedItem(EpicSamuraiItems.ANTHEKTITE_SAMURAI_HELMET_LIGHT, "compat/epicsamurai");
        generatedItem(EpicSamuraiItems.ANTHEKTITE_SAMURAI_CHESTPLATE_LIGHT, "compat/epicsamurai");
        generatedItem(EpicSamuraiItems.ANTHEKTITE_SAMURAI_LEGGINGS_LIGHT, "compat/epicsamurai");
        generatedItem(EpicSamuraiItems.ANTHEKTITE_SAMURAI_BOOTS_LIGHT, "compat/epicsamurai");

        generatedItem(EpicSamuraiItems.ANTHEKTITE_SAMURAI_HELMET_MASTER, "compat/epicsamurai");
        generatedItem(EpicSamuraiItems.ANTHEKTITE_SAMURAI_CHESTPLATE_MASTER, "compat/epicsamurai");
        generatedItem(EpicSamuraiItems.ANTHEKTITE_SAMURAI_LEGGINGS_MASTER, "compat/epicsamurai");
        generatedItem(EpicSamuraiItems.ANTHEKTITE_SAMURAI_BOOTS_MASTER, "compat/epicsamurai");
    }
    private void Twigs() {
        itemCustomParentModel(TwigsItems.MOVCADIA_TABLE, "block/compat/twigs/movcadia_table_inventory");
    }
    private void WitherStormmod() {
        handheldItem(WitherstormModItems.STEEL_CMD_SWORD, "compat/witherstormmod");
        handheldItem(WitherstormModItems.STEEL_CMD_SHOVEL, "compat/witherstormmod");
        handheldItem(WitherstormModItems.STEEL_CMD_PICKAXE, "compat/witherstormmod");
        handheldItem(WitherstormModItems.STEEL_CMD_AXE, "compat/witherstormmod");
        handheldItem(WitherstormModItems.STEEL_CMD_HOE, "compat/witherstormmod");
        handheldItem(WitherstormModItems.DIARKRITE_CMD_SWORD, "compat/witherstormmod");
        handheldItem(WitherstormModItems.DIARKRITE_CMD_SHOVEL, "compat/witherstormmod");
        handheldItem(WitherstormModItems.DIARKRITE_CMD_PICKAXE, "compat/witherstormmod");
        handheldItem(WitherstormModItems.DIARKRITE_CMD_AXE, "compat/witherstormmod");
        handheldItem(WitherstormModItems.DIARKRITE_CMD_HOE, "compat/witherstormmod");
        handheldItem(WitherstormModItems.ANTHEKTITE_CMD_SWORD, "compat/witherstormmod");
        handheldItem(WitherstormModItems.ANTHEKTITE_CMD_SHOVEL, "compat/witherstormmod");
        handheldItem(WitherstormModItems.ANTHEKTITE_CMD_PICKAXE, "compat/witherstormmod");
        handheldItem(WitherstormModItems.ANTHEKTITE_CMD_AXE, "compat/witherstormmod");
        handheldItem(WitherstormModItems.ANTHEKTITE_CMD_HOE, "compat/witherstormmod");
    }
    private void BanillaClaws() {
        banillaClawsItem(BanillaClawsItems.STEEL_CLAWS);
        banillaClawsItem(BanillaClawsItems.DIARKRITE_CLAWS);
        banillaClawsItem(BanillaClawsItems.ANTHEKTITE_CLAWS);
    }
}
