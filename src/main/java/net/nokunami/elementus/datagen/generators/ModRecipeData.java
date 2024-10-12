package net.nokunami.elementus.datagen.generators;

import io.redspace.ironsspellbooks.registries.ItemRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import net.nokunami.elementus.ModChecker;
import net.nokunami.elementus.common.Etags;
import net.nokunami.elementus.common.compat.advancednetherite.ANModBlocks;
import net.nokunami.elementus.common.compat.advancednetherite.ANModItems;
import net.nokunami.elementus.common.compat.epicsamurai.ESModItems;
import net.nokunami.elementus.common.compat.farmersdelight.FDModItems;
import net.nokunami.elementus.common.compat.farmersdelight.NDModItems;
import net.nokunami.elementus.common.compat.ironsspellbooks.ISSModItems;
import net.nokunami.elementus.common.compat.piercingpaxels.PPModItems;
import net.nokunami.elementus.common.compat.simplyswords.SSModItems;
import net.nokunami.elementus.common.compat.sniffsweapons.SWModItems;
import net.nokunami.elementus.common.compat.theaether.TAModItems;
import net.nokunami.elementus.common.compat.twigs.TWModItems;
import net.nokunami.elementus.datagen.providers.ModRecipeProvider;
import net.nokunami.elementus.common.registry.ModItems;
import net.veroxuniverse.epicsamurai.registry.ItemsRegistry;
import nl.sniffiandros.sniffsweapons.reg.ItemReg;

import java.util.function.Consumer;

public class ModRecipeData extends ModRecipeProvider {
    public ModRecipeData(PackOutput output) {
        super(output, "elementus");
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> writer) {

        //Armor
        //Crafting
        helmetRecipe(ModItems.STEEL_HELMET, Etags.Item.INGOTS_STEEL, "has_steel_ingot").save(writer);
        chestplateRecipe(ModItems.STEEL_CHESTPLATE, Etags.Item.INGOTS_STEEL, "has_steel_ingot").save(writer);
        leggingsRecipe(ModItems.STEEL_LEGGINGS, Etags.Item.INGOTS_STEEL, "has_steel_ingot").save(writer);
        bootsRecipe(ModItems.STEEL_BOOTS, Etags.Item.INGOTS_STEEL, "has_steel_ingot").save(writer);

        //Smithing
        smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ModItems.STEEL_HELMET, Etags.Item.INGOTS_DIARKRITE, ModItems.DIARKRITE_HELMET);
        smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ModItems.STEEL_CHESTPLATE, Etags.Item.INGOTS_DIARKRITE, ModItems.DIARKRITE_CHESTPLATE);
        smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ModItems.STEEL_LEGGINGS, Etags.Item.INGOTS_DIARKRITE, ModItems.DIARKRITE_LEGGINGS);
        smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ModItems.STEEL_BOOTS, Etags.Item.INGOTS_DIARKRITE, ModItems.DIARKRITE_BOOTS);

        smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ModItems.STEEL_HELMET, Etags.Item.INGOTS_DIARKRITE, ModItems.ANTHEKTITE_HELMET);
        smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ModItems.STEEL_CHESTPLATE, Etags.Item.INGOTS_ANTHEKTITE, ModItems.ANTHEKTITE_CHESTPLATE);
        smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ModItems.STEEL_LEGGINGS, Etags.Item.INGOTS_ANTHEKTITE, ModItems.ANTHEKTITE_LEGGINGS);
        smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ModItems.STEEL_BOOTS, Etags.Item.INGOTS_ANTHEKTITE, ModItems.ANTHEKTITE_BOOTS);

        //Tools
        //Crafting
        swordRecipe(ModItems.STEEL_SWORD, Etags.Item.INGOTS_STEEL, "has_steel_ingot").save(writer);
        shovelRecipe(ModItems.STEEL_SHOVEL, Etags.Item.INGOTS_STEEL, "has_steel_ingot").save(writer);
        pickaxeRecipe(ModItems.STEEL_PICKAXE, Etags.Item.INGOTS_STEEL, "has_steel_ingot").save(writer);
        axeRecipe(ModItems.STEEL_AXE, Etags.Item.INGOTS_STEEL, "has_steel_ingot").save(writer);
        hoeRecipe(ModItems.STEEL_HOE, Etags.Item.INGOTS_STEEL, "has_steel_ingot").save(writer);

        //Smithing
        smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ModItems.STEEL_SWORD, Etags.Item.INGOTS_DIARKRITE, ModItems.DIARKRITE_SWORD);
        smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ModItems.STEEL_SHOVEL, Etags.Item.INGOTS_DIARKRITE, ModItems.DIARKRITE_SHOVEL);
        smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ModItems.STEEL_PICKAXE, Etags.Item.INGOTS_DIARKRITE, ModItems.DIARKRITE_PICKAXE);
        smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ModItems.STEEL_AXE, Etags.Item.INGOTS_DIARKRITE, ModItems.DIARKRITE_AXE);
        smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ModItems.STEEL_HOE, Etags.Item.INGOTS_DIARKRITE, ModItems.DIARKRITE_HOE);

        smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ModItems.STEEL_SWORD, Etags.Item.INGOTS_ANTHEKTITE, ModItems.ANTHEKTITE_SWORD);
        smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ModItems.STEEL_SHOVEL, Etags.Item.INGOTS_ANTHEKTITE, ModItems.ANTHEKTITE_SHOVEL);
        smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ModItems.STEEL_PICKAXE, Etags.Item.INGOTS_ANTHEKTITE, ModItems.ANTHEKTITE_PICKAXE);
        smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ModItems.STEEL_AXE, Etags.Item.INGOTS_ANTHEKTITE, ModItems.ANTHEKTITE_AXE);
        smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ModItems.STEEL_HOE, Etags.Item.INGOTS_ANTHEKTITE, ModItems.ANTHEKTITE_HOE);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_SHIELD.get())
                .define('#', Etags.Item.INGOTS_STEEL).define('W', ItemTags.PLANKS)
                .pattern("W#W").pattern("WWW").pattern(" W ")
                .unlockedBy(getHasName(ModItems.STEEL_INGOT.get()), has(ModItems.STEEL_INGOT.get()))
                .save(writer);

        smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ModItems.STEEL_SHIELD, Etags.Item.INGOTS_DIARKRITE, ModItems.DIARKRITE_SHIELD);
        smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ModItems.STEEL_SHIELD, Etags.Item.INGOTS_ANTHEKTITE, ModItems.ANTHEKTITE_SHIELD);

        //Ingredients
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.CRUDE_STEEL.get())
                .requires(Ingredient.of(Tags.Items.INGOTS_IRON), 2)
                .requires(Ingredient.of(Tags.Items.RAW_MATERIALS_IRON), 1)
                .requires(Ingredient.of(ItemTags.COALS), 2)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(writer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DIARKRITE_INGOT.get())
                .define('A', Etags.Item.ORES_ATELIS).define('E', Items.ECHO_SHARD).define('S', Etags.Item.INGOTS_STEEL)
                .pattern(" E ").pattern("SSS").pattern("AAA")
                .unlockedBy(getHasName(ModItems.ATELIS_SCRAP.get()), has(ModItems.ATELIS_SCRAP.get()))
                .save(writer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ANTHEKTITE_INGOT.get())
                .define('A', Etags.Item.ORES_ATELIS).define('E', Items.ECHO_SHARD).define('S', Etags.Item.INGOTS_STEEL)
                .pattern("SSS").pattern("AAA").pattern(" E ")
                .unlockedBy(getHasName(ModItems.ATELIS_SCRAP.get()), has(ModItems.ATELIS_SCRAP.get()))
                .save(writer);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.STEEL_INGOT.get())
                .requires(Tags.Items.INGOTS_IRON).requires(Tags.Items.INGOTS_IRON).requires(ModItems.STEEL_SCRAP.get())
                .unlockedBy(getHasName(ModItems.STEEL_INGOT.get()), has(ModItems.STEEL_INGOT.get()))
                .save(writer, this.name(getItemName(ModItems.STEEL_INGOT.get()) + "_from_steel_scrap"));

        storageBlock(writer, RecipeCategory.MISC,
                ModItems.STEEL_INGOT.get(), RecipeCategory.BUILDING_BLOCKS, ModItems.STEEL_BLOCK.get(),
                "steel_ingot_from_steel_block", "steel_ingot");

        nuggetIngot(writer, RecipeCategory.MISC, ModItems.STEEL_INGOT.get(), ModItems.STEEL_NUGGET.get(), "steel_ingot", "steel_ingot_from_nuggets");

        storageBlock(writer, RecipeCategory.MISC,
                ModItems.DIARKRITE_INGOT.get(), RecipeCategory.BUILDING_BLOCKS, ModItems.DIARKRITE_BLOCK.get(),
                "diarkrite_ingot_from_steel_block", "diarkrite_ingot");

        storageBlock(writer, RecipeCategory.MISC,
                ModItems.ANTHEKTITE_INGOT.get(), RecipeCategory.BUILDING_BLOCKS, ModItems.ANTHEKTITE_BLOCK.get(),
                "anthektite_ingot_from_steel_block", "anthektite_ingot");

        smeltingOreRecipe(writer, ModItems.STEEL_INGOT.get(), ModItems.CRUDE_STEEL.get(), 0.5F);
        smeltingOreRecipe(writer, ModItems.ATELIS_SCRAP.get(), ModItems.REMNANT.get(), 0.5F);

        blastingOreRecipe(writer, ModItems.STEEL_INGOT.get(), ModItems.CRUDE_STEEL.get(), 0.5F);
        blastingOreRecipe(writer, ModItems.ATELIS_SCRAP.get(), ModItems.REMNANT.get(), 0.5F);

        templateDuplication(ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, Items.COBBLED_DEEPSLATE).save(writer);

        steelRecycleSmelt(writer, Ingredient.of(Etags.Item.STEEL_RECYCLABLE), 0.5F, "has_steel_ingot");
        steelRecycleBlast(writer, Ingredient.of(Etags.Item.STEEL_RECYCLABLE), 0.5F, "has_steel_ingot");

        //Building blocks
        wood(writer, ModItems.MOVCADIA_LOG, ModItems.MOVCADIA_WOOD);
        wood(writer, ModItems.STRIPPED_MOVCADIA_LOG, ModItems.STRIPPED_MOVCADIA_WOOD);
        planks(writer, Etags.Item.MOVCADIA_LOGS, ModItems.MOVCADIA_PLANKS);
        stairs(writer, ModItems.MOVCADIA_STAIRS, ModItems.MOVCADIA_PLANKS);
        slab(writer, RecipeCategory.BUILDING_BLOCKS, ModItems.MOVCADIA_SLAB.get(), ModItems.MOVCADIA_PLANKS.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_BARS.get(), 16)
                .define('#', ModItems.STEEL_INGOT.get())
                .pattern("###").pattern("###")
                .unlockedBy(getHasName(ModItems.STEEL_INGOT.get()), has(ModItems.STEEL_INGOT.get()))
                .save(writer);

        fence(writer, ModItems.MOVCADIA_FENCE, ModItems.MOVCADIA_PLANKS);
        fenceGate(writer, ModItems.MOVCADIA_FENCE_GATE, ModItems.MOVCADIA_PLANKS);

        door(writer, ModItems.MOVCADIA_DOOR, ModItems.MOVCADIA_PLANKS);
        trapdoor(writer, ModItems.MOVCADIA_TRAPDOOR, ModItems.MOVCADIA_PLANKS);

        pressurePlate(writer, ModItems.MOVCADIA_PRESSURE_PLATE.get(), ModItems.MOVCADIA_PLANKS.get());
        button(writer, ModItems.MOVCADIA_BUTTON, ModItems.MOVCADIA_PLANKS);

        sign(writer, ModItems.MOVCADIA_SIGN, ModItems.MOVCADIA_PLANKS);
        hangingSign(writer, ModItems.MOVCADIA_HANGING_SIGN.get(), ModItems.MOVCADIA_PLANKS.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STURDY_MOVCADIA_SIGN.get())
                .define('#', ModItems.MOVCADIA_LOG.get()).define('I', ModItems.MOVCADIA_PLANKS.get()).define('/', Items.STICK)
                .pattern("#I#").pattern("#I#").pattern(" / ")
                .unlockedBy(getHasName(ModItems.MOVCADIA_PLANKS.get()), has(ModItems.MOVCADIA_PLANKS.get()))
                .save(writer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MOVCADIA_CHEST.get())
                .define('#', ModItems.MOVCADIA_PLANKS.get())
                .pattern("###").pattern("# #").pattern("###")
                .unlockedBy(getHasName(ModItems.MOVCADIA_PLANKS.get()), has(ModItems.MOVCADIA_PLANKS.get()))
                .save(writer);

        woodenBoat(writer, ModItems.MOVCADIA_BOAT.get(), ModItems.MOVCADIA_PLANKS.get());
        chestBoat(writer, ModItems.MOVCADIA_CHEST_BOAT.get(), ModItems.MOVCADIA_BOAT.get());


        if (ModChecker.farmersdelight()) {
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, FDModItems.STEEL_KNIFE.get())
                    .pattern("#").pattern("/").define('#', ModItems.STEEL_INGOT.get()).define('/', Tags.Items.RODS_WOODEN)
                    .unlockedBy(getHasName(ModItems.STEEL_INGOT.get()), has(ModItems.STEEL_INGOT.get()))
                    .save(writer);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, FDModItems.STEEL_KNIFE, Etags.Item.INGOTS_DIARKRITE, FDModItems.DIARKRITE_KNIFE);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, FDModItems.STEEL_KNIFE, Etags.Item.INGOTS_ANTHEKTITE, FDModItems.ANTHEKTITE_KNIFE);
            stripLogForBark(writer, ModItems.MOVCADIA_LOG, ModItems.STRIPPED_MOVCADIA_LOG);
            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, vectorwing.farmersdelight.common.registry.ModItems.TREE_BARK.get(), 2).requires(ModItems.MOVCADIA_BARK.get())
                    .unlockedBy(getHasName(ModItems.MOVCADIA_BARK.get()), has(ModItems.MOVCADIA_BARK.get())).save(writer);

            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, FDModItems.MOVCADIA_CABINET.get())
                    .pattern("###").pattern("1 1").pattern("###").define('#', ModItems.MOVCADIA_SLAB.get()).define('1', ModItems.MOVCADIA_TRAPDOOR.get())
                    .unlockedBy(getHasName(ModItems.MOVCADIA_PLANKS.get()), has(ModItems.MOVCADIA_PLANKS.get())).save(writer);
        }

        if (ModChecker.piercingpaxels()) {
            makePaxel(writer, PPModItems.STEEL_PAXEL, ModItems.STEEL_SWORD, ModItems.STEEL_SHOVEL, ModItems.STEEL_PICKAXE, ModItems.STEEL_PICKAXE, ModItems.STEEL_HOE);
            makePaxel(writer, PPModItems.DIARKRITE_PAXEL, ModItems.DIARKRITE_SWORD, ModItems.DIARKRITE_SHOVEL, ModItems.DIARKRITE_PICKAXE, ModItems.DIARKRITE_PICKAXE, ModItems.DIARKRITE_HOE);
            makePaxel(writer, PPModItems.ANTHEKTITE_PAXEL, ModItems.ANTHEKTITE_SWORD, ModItems.ANTHEKTITE_SHOVEL, ModItems.ANTHEKTITE_PICKAXE, ModItems.ANTHEKTITE_PICKAXE, ModItems.ANTHEKTITE_HOE);

            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, PPModItems.STEEL_PAXEL, PPModItems.DIARKRITE_UPGRADE_KIT, PPModItems.DIARKRITE_PAXEL);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, PPModItems.STEEL_PAXEL, PPModItems.ANTHEKTITE_UPGRADE_KIT, PPModItems.ANTHEKTITE_PAXEL);

            makePaxelUpgradeKit(writer, PPModItems.DIARKRITE_UPGRADE_KIT, ModItems.DIARKRITE_INGOT);
            makePaxelUpgradeKit(writer, PPModItems.ANTHEKTITE_UPGRADE_KIT, ModItems.ANTHEKTITE_INGOT);
        }

        if (ModChecker.nethersdelight()) {
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, NDModItems.STEEL_MACHETE.get())
                    .pattern("  #").pattern(" # ").pattern("/  ")
                    .define('#', ModItems.STEEL_INGOT.get()).define('/', Tags.Items.RODS_WOODEN)
                    .unlockedBy(getHasName(ModItems.STEEL_INGOT.get()), has(ModItems.STEEL_INGOT.get()))
                    .save(writer);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, NDModItems.STEEL_MACHETE, Etags.Item.INGOTS_DIARKRITE, NDModItems.DIARKRITE_MACHETE);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, NDModItems.STEEL_MACHETE, Etags.Item.INGOTS_ANTHEKTITE, NDModItems.ANTHEKTITE_MACHETE);
        }

        if (ModChecker.irons_spellbooks()) {

            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ISSModItems.STEEL_SPELL_BOOK.get())
                    .pattern("NNA").pattern("IBA").pattern("NNA")
                    .define('N', Etags.Item.NUGGETS_STEEL)
                    .define('I', Etags.Item.INGOTS_STEEL)
                    .define('B', Items.BOOK)
                    .define('A', ItemRegistry.ARCANE_ESSENCE.get())
                    .unlockedBy("has_steel_ingot", has(Etags.Item.INGOTS_STEEL))
                    .save(writer);

            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ISSModItems.DIARKRITE_SPELL_BOOK.get())
                    .pattern("SAP").pattern("DRB").pattern("SAP")
                    .define('S', Etags.Item.INGOTS_STEEL)
                    .define('D', Etags.Item.INGOTS_DIARKRITE)
                    .define('A', ItemRegistry.ARCANE_INGOT.get())
                    .define('P', ItemRegistry.DIVINE_PEARL.get())
                    .define('B', ItemRegistry.LIGHTNING_BOTTLE.get())
                    .define('R', ItemRegistry.RUINED_BOOK.get())
                    .unlockedBy("has_diarkrite_ingot", has(Etags.Item.INGOTS_DIARKRITE))
                    .save(writer);

            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ISSModItems.ANTHEKTITE_SPELL_BOOK.get())
                    .pattern("AAP").pattern("DRB").pattern("AAP")
                    .define('D', Etags.Item.INGOTS_ANTHEKTITE)
                    .define('A', ItemRegistry.ARCANE_INGOT.get())
                    .define('P', ItemRegistry.DIVINE_PEARL.get())
                    .define('B', ItemRegistry.LIGHTNING_BOTTLE.get())
                    .define('R', ItemRegistry.RUINED_BOOK.get())
                    .unlockedBy("has_anthektite_ingot", has(Etags.Item.INGOTS_ANTHEKTITE))
                    .save(writer);

            mageHelmet(ISSModItems.DIARKRITE_MAGE_HELMET, ModItems.DIARKRITE_INGOT).save(writer);
            mageChestplate(ISSModItems.DIARKRITE_MAGE_CHESTPLATE, ModItems.DIARKRITE_INGOT).save(writer);
            mageLeggings(ISSModItems.DIARKRITE_MAGE_LEGGINGS, ModItems.DIARKRITE_INGOT).save(writer);
            mageBoots(ISSModItems.DIARKRITE_MAGE_BOOTS, ModItems.DIARKRITE_INGOT).save(writer);

            mageHelmet(ISSModItems.ANTHEKTITE_MAGE_HELMET, ModItems.ANTHEKTITE_INGOT).save(writer);
            mageChestplate(ISSModItems.ANTHEKTITE_MAGE_CHESTPLATE, ModItems.ANTHEKTITE_INGOT).save(writer);
            mageLeggings(ISSModItems.ANTHEKTITE_MAGE_LEGGINGS, ModItems.ANTHEKTITE_INGOT).save(writer);
            mageBoots(ISSModItems.ANTHEKTITE_MAGE_BOOTS, ModItems.ANTHEKTITE_INGOT).save(writer);
        }

        if (ModChecker.aether()) {
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, TAModItems.STEEL_GLOVES.get())
                    .pattern("# #").define('#', Etags.Item.INGOTS_STEEL)
                    .unlockedBy(getHasName(ModItems.STEEL_INGOT.get()), has(ModItems.STEEL_INGOT.get()))
                    .save(writer);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, TAModItems.STEEL_GLOVES, Etags.Item.INGOTS_DIARKRITE, TAModItems.DIARKRITE_GLOVES);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, TAModItems.STEEL_GLOVES, Etags.Item.INGOTS_ANTHEKTITE, TAModItems.ANTHEKTITE_GLOVES);
        }

        if (ModChecker.simplyswords()) {
            makeChakram(writer, SSModItems.STEEL_CHAKRAM, Etags.Item.INGOTS_STEEL, ModItems.STEEL_NUGGET, ModItems.STEEL_INGOT);
            makeClaymore(writer, SSModItems.STEEL_CLAYMORE, Etags.Item.INGOTS_STEEL, ModItems.STEEL_NUGGET, ModItems.STEEL_INGOT);
            makeCutlass(writer, SSModItems.STEEL_CUTLASS, Etags.Item.INGOTS_STEEL, ModItems.STEEL_NUGGET, ModItems.STEEL_INGOT);
            makeGlaive(writer, SSModItems.STEEL_GLAIVE, Etags.Item.INGOTS_STEEL, ModItems.STEEL_INGOT);
            makeGreataxe(writer, SSModItems.STEEL_GREATAXE, Etags.Item.INGOTS_STEEL, ModItems.STEEL_NUGGET, ModItems.STEEL_INGOT);
            makeGreathammer(writer, SSModItems.STEEL_GREATHAMMER, Etags.Item.INGOTS_STEEL, ModItems.STEEL_NUGGET, ModItems.STEEL_INGOT);
            makeHalberd(writer, SSModItems.STEEL_HALBERD, Etags.Item.INGOTS_STEEL, ModItems.STEEL_NUGGET, ModItems.STEEL_INGOT);
            makeKatana(writer, SSModItems.STEEL_KATANA, Etags.Item.INGOTS_STEEL, ModItems.STEEL_INGOT);
            makeLongSword(writer, SSModItems.STEEL_LONGSWORD, Etags.Item.INGOTS_STEEL, ModItems.STEEL_INGOT);
            makeRapier(writer, SSModItems.STEEL_RAPIER, Etags.Item.INGOTS_STEEL, ModItems.STEEL_INGOT);
            makeSai(writer, SSModItems.STEEL_SAI, Etags.Item.INGOTS_STEEL, ModItems.STEEL_INGOT);
            makeScythe(writer, SSModItems.STEEL_SCYTHE, Etags.Item.INGOTS_STEEL, ModItems.STEEL_INGOT);
            makeSpear(writer, SSModItems.STEEL_SPEAR, Etags.Item.INGOTS_STEEL, ModItems.STEEL_INGOT);
            makeTwinblade(writer, SSModItems.STEEL_TWINBLADE, Etags.Item.INGOTS_STEEL, ModItems.STEEL_INGOT);
            makeWarglaive(writer, SSModItems.STEEL_WARGLAIVE, Etags.Item.INGOTS_STEEL, ModItems.STEEL_NUGGET, ModItems.STEEL_INGOT);

            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SSModItems.STEEL_CHAKRAM, Etags.Item.INGOTS_DIARKRITE, SSModItems.DIARKRITE_CHAKRAM);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SSModItems.STEEL_CLAYMORE, Etags.Item.INGOTS_DIARKRITE, SSModItems.DIARKRITE_CLAYMORE);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SSModItems.STEEL_CUTLASS, Etags.Item.INGOTS_DIARKRITE, SSModItems.DIARKRITE_CUTLASS);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SSModItems.STEEL_GLAIVE, Etags.Item.INGOTS_DIARKRITE, SSModItems.DIARKRITE_GLAIVE);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SSModItems.STEEL_GREATAXE, Etags.Item.INGOTS_DIARKRITE, SSModItems.DIARKRITE_GREATAXE);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SSModItems.STEEL_GREATHAMMER, Etags.Item.INGOTS_DIARKRITE, SSModItems.DIARKRITE_GREATHAMMER);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SSModItems.STEEL_HALBERD, Etags.Item.INGOTS_DIARKRITE, SSModItems.DIARKRITE_HALBERD);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SSModItems.STEEL_KATANA, Etags.Item.INGOTS_DIARKRITE, SSModItems.DIARKRITE_KATANA);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SSModItems.STEEL_LONGSWORD, Etags.Item.INGOTS_DIARKRITE, SSModItems.DIARKRITE_LONGSWORD);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SSModItems.STEEL_RAPIER, Etags.Item.INGOTS_DIARKRITE, SSModItems.DIARKRITE_RAPIER);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SSModItems.STEEL_SAI, Etags.Item.INGOTS_DIARKRITE, SSModItems.DIARKRITE_SAI);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SSModItems.STEEL_SCYTHE, Etags.Item.INGOTS_DIARKRITE, SSModItems.DIARKRITE_SCYTHE);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SSModItems.STEEL_SPEAR, Etags.Item.INGOTS_DIARKRITE, SSModItems.DIARKRITE_SPEAR);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SSModItems.STEEL_TWINBLADE, Etags.Item.INGOTS_DIARKRITE, SSModItems.DIARKRITE_TWINBLADE);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SSModItems.STEEL_WARGLAIVE, Etags.Item.INGOTS_DIARKRITE, SSModItems.DIARKRITE_WARGLAIVE);

            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SSModItems.STEEL_CHAKRAM, Etags.Item.INGOTS_ANTHEKTITE, SSModItems.ANTHEKTITE_CHAKRAM);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SSModItems.STEEL_CLAYMORE, Etags.Item.INGOTS_ANTHEKTITE, SSModItems.ANTHEKTITE_CLAYMORE);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SSModItems.STEEL_CUTLASS, Etags.Item.INGOTS_ANTHEKTITE, SSModItems.ANTHEKTITE_CUTLASS);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SSModItems.STEEL_GLAIVE, Etags.Item.INGOTS_ANTHEKTITE, SSModItems.ANTHEKTITE_GLAIVE);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SSModItems.STEEL_GREATAXE, Etags.Item.INGOTS_ANTHEKTITE, SSModItems.ANTHEKTITE_GREATAXE);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SSModItems.STEEL_GREATHAMMER, Etags.Item.INGOTS_ANTHEKTITE, SSModItems.ANTHEKTITE_GREATHAMMER);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SSModItems.STEEL_HALBERD, Etags.Item.INGOTS_ANTHEKTITE, SSModItems.ANTHEKTITE_HALBERD);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SSModItems.STEEL_KATANA, Etags.Item.INGOTS_ANTHEKTITE, SSModItems.ANTHEKTITE_KATANA);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SSModItems.STEEL_LONGSWORD, Etags.Item.INGOTS_ANTHEKTITE, SSModItems.ANTHEKTITE_LONGSWORD);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SSModItems.STEEL_RAPIER, Etags.Item.INGOTS_ANTHEKTITE, SSModItems.ANTHEKTITE_RAPIER);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SSModItems.STEEL_SAI, Etags.Item.INGOTS_ANTHEKTITE, SSModItems.ANTHEKTITE_SAI);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SSModItems.STEEL_SCYTHE, Etags.Item.INGOTS_ANTHEKTITE, SSModItems.ANTHEKTITE_SCYTHE);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SSModItems.STEEL_SPEAR, Etags.Item.INGOTS_ANTHEKTITE, SSModItems.ANTHEKTITE_SPEAR);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SSModItems.STEEL_TWINBLADE, Etags.Item.INGOTS_ANTHEKTITE, SSModItems.ANTHEKTITE_TWINBLADE);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SSModItems.STEEL_WARGLAIVE, Etags.Item.INGOTS_ANTHEKTITE, SSModItems.ANTHEKTITE_WARGLAIVE);
        }

        if (ModChecker.sniffsweapons()) {
            makeGreatAxe(writer, SWModItems.STEEL_GREAT_AXE, Etags.Item.INGOTS_STEEL, ModItems.STEEL_AXE);
            makeGreatPickaxe(writer, SWModItems.STEEL_GREAT_PICKAXE, Etags.Item.INGOTS_STEEL, ModItems.STEEL_PICKAXE);
            makeGreatSword(writer, SWModItems.STEEL_GREAT_SWORD, Etags.Item.INGOTS_STEEL, ModItems.STEEL_SWORD);
            makeNaginata(writer, SWModItems.STEEL_NAGINATA, Etags.Item.INGOTS_STEEL, ModItems.STEEL_SWORD);

            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SWModItems.STEEL_GREAT_AXE, Etags.Item.INGOTS_DIARKRITE, SWModItems.DIARKRITE_GREAT_AXE);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SWModItems.STEEL_GREAT_PICKAXE, Etags.Item.INGOTS_DIARKRITE, SWModItems.DIARKRITE_GREAT_PICKAXE);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SWModItems.STEEL_GREAT_SWORD, Etags.Item.INGOTS_DIARKRITE, SWModItems.DIARKRITE_GREAT_SWORD);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SWModItems.STEEL_NAGINATA, Etags.Item.INGOTS_DIARKRITE, SWModItems.DIARKRITE_NAGINATA);

            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SWModItems.STEEL_GREAT_AXE, Etags.Item.INGOTS_ANTHEKTITE, SWModItems.ANTHEKTITE_GREAT_AXE);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SWModItems.STEEL_GREAT_PICKAXE, Etags.Item.INGOTS_ANTHEKTITE, SWModItems.ANTHEKTITE_GREAT_PICKAXE);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SWModItems.STEEL_GREAT_SWORD, Etags.Item.INGOTS_ANTHEKTITE, SWModItems.ANTHEKTITE_GREAT_SWORD);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SWModItems.STEEL_NAGINATA, Etags.Item.INGOTS_DIARKRITE, SWModItems.ANTHEKTITE_NAGINATA);

            smithingSniffWeaponConvert(writer, ItemReg.HELM_ARMOR_TRIM_SMITHING_TEMPLATE, ModItems.STEEL_HELMET, Items.FEATHER, SWModItems.STEEL_HELM);
            smithingSniffWeaponConvert(writer, ItemReg.SURCOAT_ARMOR_TRIM_SMITHING_TEMPLATE, ModItems.STEEL_CHESTPLATE, Items.LEATHER, SWModItems.STEEL_SURCOAT);
            smithingSniffWeaponConvert(writer, ItemReg.HORNED_ARMOR_TRIM_SMITHING_TEMPLATE, ModItems.STEEL_HELMET, Items.BONE, SWModItems.STEEL_HORNED_HELM);
            smithingSniffWeaponConvert(writer, ItemReg.PLATED_ARMOR_TRIM_SMITHING_TEMPLATE, ModItems.STEEL_CHESTPLATE, Items.IRON_INGOT, SWModItems.PLATED_STEEL_CHESTPLATE);
            smithingSniffWeaponConvert(writer, ItemReg.SAMURAI_ARMOR_TRIM_SMITHING_TEMPLATE, ModItems.STEEL_HELMET, Items.GOLD_NUGGET, SWModItems.STEEL_KABUTO);
            smithingSniffWeaponConvert(writer, ItemReg.SAMURAI_ARMOR_TRIM_SMITHING_TEMPLATE, ModItems.STEEL_CHESTPLATE, Items.GOLD_NUGGET, SWModItems.STEEL_DO);

            smithingSniffWeaponConvert(writer, ItemReg.HELM_ARMOR_TRIM_SMITHING_TEMPLATE, ModItems.DIARKRITE_HELMET, Items.FEATHER, SWModItems.DIARKRITE_HELM);
            smithingSniffWeaponConvert(writer, ItemReg.SURCOAT_ARMOR_TRIM_SMITHING_TEMPLATE, ModItems.DIARKRITE_CHESTPLATE, Items.LEATHER, SWModItems.DIARKRITE_SURCOAT);
            smithingSniffWeaponConvert(writer, ItemReg.HORNED_ARMOR_TRIM_SMITHING_TEMPLATE, ModItems.DIARKRITE_HELMET, Items.BONE, SWModItems.DIARKRITE_HORNED_HELM);
            smithingSniffWeaponConvert(writer, ItemReg.PLATED_ARMOR_TRIM_SMITHING_TEMPLATE, ModItems.DIARKRITE_CHESTPLATE, Items.IRON_INGOT, SWModItems.PLATED_DIARKRITE_CHESTPLATE);
            smithingSniffWeaponConvert(writer, ItemReg.SAMURAI_ARMOR_TRIM_SMITHING_TEMPLATE, ModItems.DIARKRITE_HELMET, Items.GOLD_NUGGET, SWModItems.DIARKRITE_KABUTO);
            smithingSniffWeaponConvert(writer, ItemReg.SAMURAI_ARMOR_TRIM_SMITHING_TEMPLATE, ModItems.DIARKRITE_CHESTPLATE, Items.GOLD_NUGGET, SWModItems.DIARKRITE_DO);

            smithingSniffWeaponConvert(writer, ItemReg.HELM_ARMOR_TRIM_SMITHING_TEMPLATE, ModItems.ANTHEKTITE_HELMET, Items.FEATHER, SWModItems.ANTHEKTITE_HELM);
            smithingSniffWeaponConvert(writer, ItemReg.SURCOAT_ARMOR_TRIM_SMITHING_TEMPLATE, ModItems.ANTHEKTITE_CHESTPLATE, Items.LEATHER, SWModItems.ANTHEKTITE_SURCOAT);
            smithingSniffWeaponConvert(writer, ItemReg.HORNED_ARMOR_TRIM_SMITHING_TEMPLATE, ModItems.ANTHEKTITE_HELMET, Items.BONE, SWModItems.ANTHEKTITE_HORNED_HELM);
            smithingSniffWeaponConvert(writer, ItemReg.PLATED_ARMOR_TRIM_SMITHING_TEMPLATE, ModItems.ANTHEKTITE_CHESTPLATE, Items.IRON_INGOT, SWModItems.PLATED_ANTHEKTITE_CHESTPLATE);
            smithingSniffWeaponConvert(writer, ItemReg.SAMURAI_ARMOR_TRIM_SMITHING_TEMPLATE, ModItems.ANTHEKTITE_HELMET, Items.GOLD_NUGGET, SWModItems.ANTHEKTITE_KABUTO);
            smithingSniffWeaponConvert(writer, ItemReg.SAMURAI_ARMOR_TRIM_SMITHING_TEMPLATE, ModItems.ANTHEKTITE_CHESTPLATE, Items.GOLD_NUGGET, SWModItems.ANTHEKTITE_DO);

            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SWModItems.STEEL_HELM, Etags.Item.INGOTS_DIARKRITE, SWModItems.DIARKRITE_HELM);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SWModItems.STEEL_SURCOAT, Etags.Item.INGOTS_DIARKRITE, SWModItems.DIARKRITE_SURCOAT);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SWModItems.STEEL_HORNED_HELM, Etags.Item.INGOTS_DIARKRITE, SWModItems.DIARKRITE_HORNED_HELM);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SWModItems.PLATED_STEEL_CHESTPLATE, Etags.Item.INGOTS_DIARKRITE, SWModItems.PLATED_DIARKRITE_CHESTPLATE);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SWModItems.STEEL_KABUTO, Etags.Item.INGOTS_DIARKRITE, SWModItems.DIARKRITE_KABUTO);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SWModItems.STEEL_DO, Etags.Item.INGOTS_DIARKRITE, SWModItems.DIARKRITE_DO);

            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SWModItems.STEEL_HELM, Etags.Item.INGOTS_ANTHEKTITE, SWModItems.ANTHEKTITE_HELM);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SWModItems.STEEL_SURCOAT, Etags.Item.INGOTS_ANTHEKTITE, SWModItems.ANTHEKTITE_SURCOAT);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SWModItems.STEEL_HORNED_HELM, Etags.Item.INGOTS_ANTHEKTITE, SWModItems.ANTHEKTITE_HORNED_HELM);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SWModItems.PLATED_STEEL_CHESTPLATE, Etags.Item.INGOTS_ANTHEKTITE, SWModItems.PLATED_ANTHEKTITE_CHESTPLATE);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SWModItems.STEEL_KABUTO, Etags.Item.INGOTS_ANTHEKTITE, SWModItems.ANTHEKTITE_KABUTO);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SWModItems.STEEL_DO, Etags.Item.INGOTS_ANTHEKTITE, SWModItems.ANTHEKTITE_DO);
        }

        if (ModChecker.advancednetherite()) {
            ingotBlockRecipes(writer, ANModItems.DIARKRITE_IRON.get(), ANModBlocks.DIARKRITE_IRON_BLOCK.get(), ModItems.DIARKRITE_INGOT.get(), Tags.Items.INGOTS_IRON, ANModItems.DIARKRITE_IRON.get());
            ingotBlockRecipes(writer, ANModItems.DIARKRITE_GOLD.get(), ANModBlocks.DIARKRITE_GOLD_BLOCK.get(), ANModItems.DIARKRITE_IRON.get(), Tags.Items.INGOTS_GOLD, ANModItems.DIARKRITE_GOLD.get());
            ingotBlockRecipes(writer, ANModItems.DIARKRITE_EMERALD.get(), ANModBlocks.DIARKRITE_EMERALD_BLOCK.get(), ANModItems.DIARKRITE_GOLD.get(), Tags.Items.GEMS_EMERALD, ANModItems.DIARKRITE_EMERALD.get());
            ingotBlockRecipes(writer, ANModItems.DIARKRITE_DIAMOND.get(), ANModBlocks.DIARKRITE_DIAMOND_BLOCK.get(), ANModItems.DIARKRITE_EMERALD.get(), Tags.Items.GEMS_DIAMOND, ANModItems.DIARKRITE_DIAMOND.get());

            ingotBlockRecipes(writer, ANModItems.ANTHEKTITE_IRON.get(), ANModBlocks.ANTHEKTITE_IRON_BLOCK.get(), ModItems.ANTHEKTITE_INGOT.get(), Tags.Items.INGOTS_IRON, ANModItems.ANTHEKTITE_IRON.get());
            ingotBlockRecipes(writer, ANModItems.ANTHEKTITE_GOLD.get(), ANModBlocks.ANTHEKTITE_GOLD_BLOCK.get(), ANModItems.ANTHEKTITE_IRON.get(), Tags.Items.INGOTS_GOLD, ANModItems.ANTHEKTITE_GOLD.get());
            ingotBlockRecipes(writer, ANModItems.ANTHEKTITE_EMERALD.get(), ANModBlocks.ANTHEKTITE_EMERALD_BLOCK.get(), ANModItems.ANTHEKTITE_GOLD.get(), Tags.Items.GEMS_EMERALD, ANModItems.ANTHEKTITE_EMERALD.get());
            ingotBlockRecipes(writer, ANModItems.ANTHEKTITE_DIAMOND.get(), ANModBlocks.ANTHEKTITE_DIAMOND_BLOCK.get(), ANModItems.ANTHEKTITE_EMERALD.get(), Tags.Items.GEMS_DIAMOND, ANModItems.ANTHEKTITE_DIAMOND.get());


            makeAdvancedNetheriteTools(writer, ModItems.DIARKRITE_SWORD, ModItems.DIARKRITE_SHOVEL, ModItems.DIARKRITE_PICKAXE, ModItems.DIARKRITE_AXE, ModItems.DIARKRITE_HOE, ANModItems.DIARKRITE_IRON,
                    ANModItems.DIARKRITE_IRON_SWORD, ANModItems.DIARKRITE_IRON_SHOVEL, ANModItems.DIARKRITE_IRON_PICKAXE, ANModItems.DIARKRITE_IRON_AXE, ANModItems.DIARKRITE_IRON_HOE);

            makeAdvancedNetheriteTools(writer, ANModItems.DIARKRITE_IRON_SWORD, ANModItems.DIARKRITE_IRON_SHOVEL, ANModItems.DIARKRITE_IRON_PICKAXE, ANModItems.DIARKRITE_IRON_AXE, ANModItems.DIARKRITE_IRON_HOE, ANModItems.DIARKRITE_GOLD,
                    ANModItems.DIARKRITE_GOLD_SWORD, ANModItems.DIARKRITE_GOLD_SHOVEL, ANModItems.DIARKRITE_GOLD_PICKAXE, ANModItems.DIARKRITE_GOLD_AXE, ANModItems.DIARKRITE_GOLD_HOE);

            makeAdvancedNetheriteTools(writer, ANModItems.DIARKRITE_GOLD_SWORD, ANModItems.DIARKRITE_GOLD_SHOVEL, ANModItems.DIARKRITE_GOLD_PICKAXE, ANModItems.DIARKRITE_GOLD_AXE, ANModItems.DIARKRITE_GOLD_HOE, ANModItems.DIARKRITE_EMERALD,
                    ANModItems.DIARKRITE_EMERALD_SWORD, ANModItems.DIARKRITE_EMERALD_SHOVEL, ANModItems.DIARKRITE_EMERALD_PICKAXE, ANModItems.DIARKRITE_EMERALD_AXE, ANModItems.DIARKRITE_EMERALD_HOE);

            makeAdvancedNetheriteTools(writer, ANModItems.DIARKRITE_EMERALD_SWORD, ANModItems.DIARKRITE_EMERALD_SHOVEL, ANModItems.DIARKRITE_EMERALD_PICKAXE, ANModItems.DIARKRITE_EMERALD_AXE, ANModItems.DIARKRITE_EMERALD_HOE, ANModItems.DIARKRITE_DIAMOND,
                    ANModItems.DIARKRITE_DIAMOND_SWORD, ANModItems.DIARKRITE_DIAMOND_SHOVEL, ANModItems.DIARKRITE_DIAMOND_PICKAXE, ANModItems.DIARKRITE_DIAMOND_AXE, ANModItems.DIARKRITE_DIAMOND_HOE);


            makeAdvancedNetheriteArmors(writer, ModItems.DIARKRITE_HELMET, ModItems.DIARKRITE_CHESTPLATE, ModItems.DIARKRITE_LEGGINGS, ModItems.DIARKRITE_BOOTS, ANModItems.DIARKRITE_IRON,
                    ANModItems.DIARKRITE_IRON_HELMET, ANModItems.DIARKRITE_IRON_CHESTPLATE, ANModItems.DIARKRITE_IRON_LEGGINGS, ANModItems.DIARKRITE_IRON_BOOTS);

            makeAdvancedNetheriteArmors(writer, ANModItems.DIARKRITE_IRON_HELMET, ANModItems.DIARKRITE_IRON_CHESTPLATE, ANModItems.DIARKRITE_IRON_LEGGINGS, ANModItems.DIARKRITE_IRON_BOOTS, ANModItems.DIARKRITE_GOLD,
                    ANModItems.DIARKRITE_GOLD_HELMET, ANModItems.DIARKRITE_GOLD_CHESTPLATE, ANModItems.DIARKRITE_GOLD_LEGGINGS, ANModItems.DIARKRITE_GOLD_BOOTS);

            makeAdvancedNetheriteArmors(writer, ANModItems.DIARKRITE_GOLD_HELMET, ANModItems.DIARKRITE_GOLD_CHESTPLATE, ANModItems.DIARKRITE_GOLD_LEGGINGS, ANModItems.DIARKRITE_GOLD_BOOTS, ANModItems.DIARKRITE_EMERALD,
                    ANModItems.DIARKRITE_EMERALD_HELMET, ANModItems.DIARKRITE_EMERALD_CHESTPLATE, ANModItems.DIARKRITE_EMERALD_LEGGINGS, ANModItems.DIARKRITE_EMERALD_BOOTS);

            makeAdvancedNetheriteArmors(writer, ANModItems.DIARKRITE_EMERALD_HELMET, ANModItems.DIARKRITE_EMERALD_CHESTPLATE, ANModItems.DIARKRITE_EMERALD_LEGGINGS, ANModItems.DIARKRITE_EMERALD_BOOTS, ANModItems.DIARKRITE_DIAMOND,
                    ANModItems.DIARKRITE_DIAMOND_HELMET, ANModItems.DIARKRITE_DIAMOND_CHESTPLATE, ANModItems.DIARKRITE_DIAMOND_LEGGINGS, ANModItems.DIARKRITE_DIAMOND_BOOTS);



            makeAdvancedNetheriteTools(writer, ModItems.ANTHEKTITE_SWORD, ModItems.ANTHEKTITE_SHOVEL, ModItems.ANTHEKTITE_PICKAXE, ModItems.ANTHEKTITE_AXE, ModItems.ANTHEKTITE_HOE, ANModItems.ANTHEKTITE_IRON,
                    ANModItems.ANTHEKTITE_IRON_SWORD, ANModItems.ANTHEKTITE_IRON_SHOVEL, ANModItems.ANTHEKTITE_IRON_PICKAXE, ANModItems.ANTHEKTITE_IRON_AXE, ANModItems.ANTHEKTITE_IRON_HOE);

            makeAdvancedNetheriteTools(writer, ANModItems.ANTHEKTITE_IRON_SWORD, ANModItems.ANTHEKTITE_IRON_SHOVEL, ANModItems.ANTHEKTITE_IRON_PICKAXE, ANModItems.ANTHEKTITE_IRON_AXE, ANModItems.ANTHEKTITE_IRON_HOE, ANModItems.ANTHEKTITE_GOLD,
                    ANModItems.ANTHEKTITE_GOLD_SWORD, ANModItems.ANTHEKTITE_GOLD_SHOVEL, ANModItems.ANTHEKTITE_GOLD_PICKAXE, ANModItems.ANTHEKTITE_GOLD_AXE, ANModItems.ANTHEKTITE_GOLD_HOE);

            makeAdvancedNetheriteTools(writer, ANModItems.ANTHEKTITE_GOLD_SWORD, ANModItems.ANTHEKTITE_GOLD_SHOVEL, ANModItems.ANTHEKTITE_GOLD_PICKAXE, ANModItems.ANTHEKTITE_GOLD_AXE, ANModItems.ANTHEKTITE_GOLD_HOE, ANModItems.ANTHEKTITE_EMERALD,
                    ANModItems.ANTHEKTITE_EMERALD_SWORD, ANModItems.ANTHEKTITE_EMERALD_SHOVEL, ANModItems.ANTHEKTITE_EMERALD_PICKAXE, ANModItems.ANTHEKTITE_EMERALD_AXE, ANModItems.ANTHEKTITE_EMERALD_HOE);

            makeAdvancedNetheriteTools(writer, ANModItems.ANTHEKTITE_EMERALD_SWORD, ANModItems.ANTHEKTITE_EMERALD_SHOVEL, ANModItems.ANTHEKTITE_EMERALD_PICKAXE, ANModItems.ANTHEKTITE_EMERALD_AXE, ANModItems.ANTHEKTITE_EMERALD_HOE, ANModItems.ANTHEKTITE_DIAMOND,
                    ANModItems.ANTHEKTITE_DIAMOND_SWORD, ANModItems.ANTHEKTITE_DIAMOND_SHOVEL, ANModItems.ANTHEKTITE_DIAMOND_PICKAXE, ANModItems.ANTHEKTITE_DIAMOND_AXE, ANModItems.ANTHEKTITE_DIAMOND_HOE);


            makeAdvancedNetheriteArmors(writer, ModItems.ANTHEKTITE_HELMET, ModItems.ANTHEKTITE_CHESTPLATE, ModItems.ANTHEKTITE_LEGGINGS, ModItems.ANTHEKTITE_BOOTS, ANModItems.ANTHEKTITE_IRON,
                    ANModItems.ANTHEKTITE_IRON_HELMET, ANModItems.ANTHEKTITE_IRON_CHESTPLATE, ANModItems.ANTHEKTITE_IRON_LEGGINGS, ANModItems.ANTHEKTITE_IRON_BOOTS);

            makeAdvancedNetheriteArmors(writer, ANModItems.ANTHEKTITE_IRON_HELMET, ANModItems.ANTHEKTITE_IRON_CHESTPLATE, ANModItems.ANTHEKTITE_IRON_LEGGINGS, ANModItems.ANTHEKTITE_IRON_BOOTS, ANModItems.ANTHEKTITE_GOLD,
                    ANModItems.ANTHEKTITE_GOLD_HELMET, ANModItems.ANTHEKTITE_GOLD_CHESTPLATE, ANModItems.ANTHEKTITE_GOLD_LEGGINGS, ANModItems.ANTHEKTITE_GOLD_BOOTS);

            makeAdvancedNetheriteArmors(writer, ANModItems.ANTHEKTITE_GOLD_HELMET, ANModItems.ANTHEKTITE_GOLD_CHESTPLATE, ANModItems.ANTHEKTITE_GOLD_LEGGINGS, ANModItems.ANTHEKTITE_GOLD_BOOTS, ANModItems.ANTHEKTITE_EMERALD,
                    ANModItems.ANTHEKTITE_EMERALD_HELMET, ANModItems.ANTHEKTITE_EMERALD_CHESTPLATE, ANModItems.ANTHEKTITE_EMERALD_LEGGINGS, ANModItems.ANTHEKTITE_EMERALD_BOOTS);

            makeAdvancedNetheriteArmors(writer, ANModItems.ANTHEKTITE_EMERALD_HELMET, ANModItems.ANTHEKTITE_EMERALD_CHESTPLATE, ANModItems.ANTHEKTITE_EMERALD_LEGGINGS, ANModItems.ANTHEKTITE_EMERALD_BOOTS, ANModItems.ANTHEKTITE_DIAMOND,
                    ANModItems.ANTHEKTITE_DIAMOND_HELMET, ANModItems.ANTHEKTITE_DIAMOND_CHESTPLATE, ANModItems.ANTHEKTITE_DIAMOND_LEGGINGS, ANModItems.ANTHEKTITE_DIAMOND_BOOTS);
        }

        if (ModChecker.epicsamurai()) {
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ESModItems.STEEL_SAMURAI_HELMET.get())
                    .define('#', Etags.Item.INGOTS_STEEL).define('J', ItemsRegistry.JADE.get()).define('I', Tags.Items.INGOTS_IRON)
                    .pattern(" # ")
                    .pattern("IJI")
                    .pattern("# #")
                    .unlockedBy(getHasName(ModItems.STEEL_INGOT.get()), has(Etags.Item.INGOTS_STEEL))
                    .save(writer);
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ESModItems.STEEL_SAMURAI_CHESTPLATE.get())
                    .define('#', Etags.Item.INGOTS_STEEL).define('I', Tags.Items.INGOTS_IRON)
                    .pattern("# #")
                    .pattern("I#I")
                    .pattern("#I#")
                    .unlockedBy(getHasName(ModItems.STEEL_INGOT.get()), has(Etags.Item.INGOTS_STEEL))
                    .save(writer);
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ESModItems.STEEL_SAMURAI_LEGGINGS.get())
                    .define('#', Etags.Item.INGOTS_STEEL).define('I', Tags.Items.INGOTS_IRON)
                    .pattern("###")
                    .pattern("I I")
                    .pattern("# #")
                    .unlockedBy(getHasName(ModItems.STEEL_INGOT.get()), has(Etags.Item.INGOTS_STEEL))
                    .save(writer);
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ESModItems.STEEL_SAMURAI_BOOTS.get())
                    .define('#', Etags.Item.INGOTS_STEEL).define('I', Tags.Items.INGOTS_IRON)
                    .pattern("#I#")
                    .pattern("# #")
                    .unlockedBy(getHasName(ModItems.STEEL_INGOT.get()), has(Etags.Item.INGOTS_STEEL))
                    .save(writer);

            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ESModItems.STEEL_SAMURAI_HELMET_LIGHT.get())
                    .define('#', Etags.Item.INGOTS_STEEL).define('I', Tags.Items.INGOTS_IRON)
                    .pattern(" I ")
                    .pattern("I#I")
                    .pattern("# #")
                    .unlockedBy(getHasName(ModItems.STEEL_INGOT.get()), has(Etags.Item.INGOTS_STEEL))
                    .save(writer);
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ESModItems.STEEL_SAMURAI_CHESTPLATE_LIGHT.get())
                    .define('#', Etags.Item.INGOTS_STEEL).define('I', Tags.Items.INGOTS_IRON)
                    .pattern("# #")
                    .pattern("#I#")
                    .pattern("#I#")
                    .unlockedBy(getHasName(ModItems.STEEL_INGOT.get()), has(Etags.Item.INGOTS_STEEL))
                    .save(writer);
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ESModItems.STEEL_SAMURAI_LEGGINGS_LIGHT.get())
                    .define('#', Etags.Item.INGOTS_STEEL).define('I', Tags.Items.INGOTS_IRON)
                    .pattern("#I#")
                    .pattern("# #")
                    .pattern("# #")
                    .unlockedBy(getHasName(ModItems.STEEL_INGOT.get()), has(Etags.Item.INGOTS_STEEL))
                    .save(writer);
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ESModItems.STEEL_SAMURAI_BOOTS_LIGHT.get())
                    .define('#', Etags.Item.INGOTS_STEEL).define('I', Tags.Items.INGOTS_IRON)
                    .pattern("I#I")
                    .pattern("# #")
                    .unlockedBy(getHasName(ModItems.STEEL_INGOT.get()), has(Etags.Item.INGOTS_STEEL))
                    .save(writer);

            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ESModItems.STEEL_SAMURAI_HELMET_MASTER.get())
                    .define('#', Etags.Item.INGOTS_STEEL).define('I', Tags.Items.INGOTS_IRON)
                    .pattern(" # ")
                    .pattern("III")
                    .pattern("# #")
                    .unlockedBy(getHasName(ModItems.STEEL_INGOT.get()), has(Etags.Item.INGOTS_STEEL))
                    .save(writer);
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ESModItems.STEEL_SAMURAI_CHESTPLATE_MASTER.get())
                    .define('#', Etags.Item.INGOTS_STEEL).define('I', Tags.Items.INGOTS_IRON)
                    .pattern("# #")
                    .pattern("I#I")
                    .pattern("III")
                    .unlockedBy(getHasName(ModItems.STEEL_INGOT.get()), has(Etags.Item.INGOTS_STEEL))
                    .save(writer);
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ESModItems.STEEL_SAMURAI_LEGGINGS_MASTER.get())
                    .define('#', Etags.Item.INGOTS_STEEL).define('I', Tags.Items.INGOTS_IRON)
                    .pattern("#I#")
                    .pattern("I I")
                    .pattern("# #")
                    .unlockedBy(getHasName(ModItems.STEEL_INGOT.get()), has(Etags.Item.INGOTS_STEEL))
                    .save(writer);
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ESModItems.STEEL_SAMURAI_BOOTS_MASTER.get())
                    .define('#', Etags.Item.INGOTS_STEEL).define('I', Tags.Items.INGOTS_IRON)
                    .pattern("III")
                    .pattern("# #")
                    .unlockedBy(getHasName(ModItems.STEEL_INGOT.get()), has(Etags.Item.INGOTS_STEEL))
                    .save(writer);

            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ESModItems.STEEL_SAMURAI_HELMET, Etags.Item.INGOTS_DIARKRITE, ESModItems.DIARKRITE_SAMURAI_HELMET);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ESModItems.STEEL_SAMURAI_CHESTPLATE, Etags.Item.INGOTS_DIARKRITE, ESModItems.DIARKRITE_SAMURAI_CHESTPLATE);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ESModItems.STEEL_SAMURAI_LEGGINGS, Etags.Item.INGOTS_DIARKRITE, ESModItems.DIARKRITE_SAMURAI_LEGGINGS);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ESModItems.STEEL_SAMURAI_BOOTS, Etags.Item.INGOTS_DIARKRITE, ESModItems.DIARKRITE_SAMURAI_BOOTS);

            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ESModItems.STEEL_SAMURAI_HELMET_LIGHT, Etags.Item.INGOTS_DIARKRITE, ESModItems.DIARKRITE_SAMURAI_HELMET_LIGHT);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ESModItems.STEEL_SAMURAI_CHESTPLATE_LIGHT, Etags.Item.INGOTS_DIARKRITE, ESModItems.DIARKRITE_SAMURAI_CHESTPLATE_LIGHT);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ESModItems.STEEL_SAMURAI_LEGGINGS_LIGHT, Etags.Item.INGOTS_DIARKRITE, ESModItems.DIARKRITE_SAMURAI_LEGGINGS_LIGHT);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ESModItems.STEEL_SAMURAI_BOOTS_LIGHT, Etags.Item.INGOTS_DIARKRITE, ESModItems.DIARKRITE_SAMURAI_BOOTS_LIGHT);

            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ESModItems.STEEL_SAMURAI_HELMET_MASTER, Etags.Item.INGOTS_DIARKRITE, ESModItems.DIARKRITE_SAMURAI_HELMET_MASTER);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ESModItems.STEEL_SAMURAI_CHESTPLATE_MASTER, Etags.Item.INGOTS_DIARKRITE, ESModItems.DIARKRITE_SAMURAI_CHESTPLATE_MASTER);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ESModItems.STEEL_SAMURAI_LEGGINGS_MASTER, Etags.Item.INGOTS_DIARKRITE, ESModItems.DIARKRITE_SAMURAI_LEGGINGS_MASTER);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ESModItems.STEEL_SAMURAI_BOOTS_MASTER, Etags.Item.INGOTS_DIARKRITE, ESModItems.DIARKRITE_SAMURAI_BOOTS_MASTER);


            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ESModItems.STEEL_SAMURAI_HELMET, Etags.Item.INGOTS_ANTHEKTITE, ESModItems.ANTHEKTITE_SAMURAI_HELMET);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ESModItems.STEEL_SAMURAI_CHESTPLATE, Etags.Item.INGOTS_ANTHEKTITE, ESModItems.ANTHEKTITE_SAMURAI_CHESTPLATE);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ESModItems.STEEL_SAMURAI_LEGGINGS, Etags.Item.INGOTS_ANTHEKTITE, ESModItems.ANTHEKTITE_SAMURAI_LEGGINGS);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ESModItems.STEEL_SAMURAI_BOOTS, Etags.Item.INGOTS_ANTHEKTITE, ESModItems.ANTHEKTITE_SAMURAI_BOOTS);

            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ESModItems.STEEL_SAMURAI_HELMET_LIGHT, Etags.Item.INGOTS_ANTHEKTITE, ESModItems.ANTHEKTITE_SAMURAI_HELMET_LIGHT);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ESModItems.STEEL_SAMURAI_CHESTPLATE_LIGHT, Etags.Item.INGOTS_ANTHEKTITE, ESModItems.ANTHEKTITE_SAMURAI_CHESTPLATE_LIGHT);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ESModItems.STEEL_SAMURAI_LEGGINGS_LIGHT, Etags.Item.INGOTS_ANTHEKTITE, ESModItems.ANTHEKTITE_SAMURAI_LEGGINGS_LIGHT);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ESModItems.STEEL_SAMURAI_BOOTS_LIGHT, Etags.Item.INGOTS_ANTHEKTITE, ESModItems.ANTHEKTITE_SAMURAI_BOOTS_LIGHT);

            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ESModItems.STEEL_SAMURAI_HELMET_MASTER, Etags.Item.INGOTS_ANTHEKTITE, ESModItems.ANTHEKTITE_SAMURAI_HELMET_MASTER);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ESModItems.STEEL_SAMURAI_CHESTPLATE_MASTER, Etags.Item.INGOTS_ANTHEKTITE, ESModItems.ANTHEKTITE_SAMURAI_CHESTPLATE_MASTER);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ESModItems.STEEL_SAMURAI_LEGGINGS_MASTER, Etags.Item.INGOTS_ANTHEKTITE, ESModItems.ANTHEKTITE_SAMURAI_LEGGINGS_MASTER);
            smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ESModItems.STEEL_SAMURAI_BOOTS_MASTER, Etags.Item.INGOTS_ANTHEKTITE, ESModItems.ANTHEKTITE_SAMURAI_BOOTS_MASTER);
        }

        if (ModChecker.twigs()) {
            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TWModItems.MOVCADIA_TABLE.get())
                    .pattern("###").pattern("1 1").pattern("1 1").define('#', ModItems.MOVCADIA_SLAB.get()).define('1', ModItems.MOVCADIA_FENCE.get())
                    .unlockedBy(getHasName(ModItems.MOVCADIA_PLANKS.get()), has(ModItems.MOVCADIA_PLANKS.get())).save(writer);
        }
    }
}
