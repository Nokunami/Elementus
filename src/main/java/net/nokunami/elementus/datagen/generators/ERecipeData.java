package net.nokunami.elementus.datagen.generators;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import net.nokunami.elementus.common.registry.EItems;
import net.nokunami.elementus.common.tags.EItemTags;
import net.nokunami.elementus.datagen.providers.ModRecipeProvider;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class ERecipeData extends ModRecipeProvider {
    public ERecipeData(PackOutput output) {
        super(output, "elementus");
//        new CreateProcessingRecipe(output);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> writer) {
        Elementus(writer);
    }
    
    public enum CriterionName {
        STEEL ("has_steel_ingot"),
        DIARKRITE ("has_diarkrite_ingot"),
        ANTHEKTITE ("has_anthektite_ingot"),
        MOVCADIA ("has_movcadia_planks");

        private final String name;

        CriterionName(String pName) {
            this.name = pName;
        }

        public String getName() {
            return this.name;
        }
    }

    private void Elementus(Consumer<FinishedRecipe> writer) {
        //Armor
        //Crafting
        helmetRecipe(EItems.ASTALITE_HELMET, EItemTags.INGOTS_STEEL, CriterionName.STEEL.name).save(writer);
        chestplateRecipe(EItems.ASTALITE_CHESTPLATE, EItemTags.INGOTS_STEEL, CriterionName.STEEL.name).save(writer);
        leggingsRecipe(EItems.ASTALITE_LEGGINGS, EItemTags.INGOTS_STEEL, CriterionName.STEEL.name).save(writer);
        bootsRecipe(EItems.ASTALITE_BOOTS, EItemTags.INGOTS_STEEL, CriterionName.STEEL.name).save(writer);

        //Smithing
        smithingCombatTransform(writer, EItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, EItems.ASTALITE_HELMET, EItemTags.INGOTS_DIARKRITE, EItems.DIARKRITE_HELMET);
        smithingCombatTransform(writer, EItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, EItems.ASTALITE_CHESTPLATE, EItemTags.INGOTS_DIARKRITE, EItems.DIARKRITE_CHESTPLATE);
        smithingCombatTransform(writer, EItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, EItems.ASTALITE_LEGGINGS, EItemTags.INGOTS_DIARKRITE, EItems.DIARKRITE_LEGGINGS);
        smithingCombatTransform(writer, EItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, EItems.ASTALITE_BOOTS, EItemTags.INGOTS_DIARKRITE, EItems.DIARKRITE_BOOTS);

        smithingCombatTransform(writer, EItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, EItems.ASTALITE_HELMET, EItemTags.INGOTS_ANTHEKTITE, EItems.ANTHEKTITE_HELMET);
        smithingCombatTransform(writer, EItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, EItems.ASTALITE_CHESTPLATE, EItemTags.INGOTS_ANTHEKTITE, EItems.ANTHEKTITE_CHESTPLATE);
        smithingCombatTransform(writer, EItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, EItems.ASTALITE_LEGGINGS, EItemTags.INGOTS_ANTHEKTITE, EItems.ANTHEKTITE_LEGGINGS);
        smithingCombatTransform(writer, EItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, EItems.ASTALITE_BOOTS, EItemTags.INGOTS_ANTHEKTITE, EItems.ANTHEKTITE_BOOTS);

        //Tools
        //Crafting
        swordRecipe(EItems.ASTALITE_SWORD, EItemTags.INGOTS_STEEL, CriterionName.STEEL.name).save(writer);
        shovelRecipe(EItems.ASTALITE_SHOVEL, EItemTags.INGOTS_STEEL, CriterionName.STEEL.name).save(writer);
        pickaxeRecipe(EItems.ASTALITE_PICKAXE, EItemTags.INGOTS_STEEL, CriterionName.STEEL.name).save(writer);
        axeRecipe(EItems.ASTALITE_AXE, EItemTags.INGOTS_STEEL, CriterionName.STEEL.name).save(writer);
        hoeRecipe(EItems.ASTALITE_HOE, EItemTags.INGOTS_STEEL, CriterionName.STEEL.name).save(writer);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, EItems.MOVCADIA_SWORD.get())
                .define('#', ItemTags.PLANKS).define('$', EItemTags.MOVCADIA_LOGS).define('/', Tags.Items.RODS_WOODEN)
                .pattern("#")
                .pattern("$")
                .pattern("/")
                .unlockedBy(CriterionName.MOVCADIA.name, has(EItemTags.MOVCADIA_LOGS)).save(writer);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, EItems.MOVCADIA_SHOVEL.get())
                .define('$', EItemTags.MOVCADIA_LOGS).define('/', Tags.Items.RODS_WOODEN)
                .pattern("$")
                .pattern("/")
                .pattern("/")
                .unlockedBy(CriterionName.MOVCADIA.name, has(EItemTags.MOVCADIA_LOGS)).save(writer);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, EItems.MOVCADIA_PICKAXE.get())
                .define('#', ItemTags.PLANKS).define('$', EItemTags.MOVCADIA_LOGS).define('/', Tags.Items.RODS_WOODEN)
                .pattern("#$#")
                .pattern(" / ")
                .pattern(" / ")
                .unlockedBy(CriterionName.MOVCADIA.name, has(EItemTags.MOVCADIA_LOGS)).save(writer);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, EItems.MOVCADIA_AXE.get())
                .define('#', ItemTags.PLANKS).define('$', EItemTags.MOVCADIA_LOGS).define('/', Tags.Items.RODS_WOODEN)
                .pattern("#$")
                .pattern("#/")
                .pattern(" /")
                .unlockedBy(CriterionName.MOVCADIA.name, has(EItemTags.MOVCADIA_LOGS)).save(writer);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, EItems.MOVCADIA_HOE.get())
                .define('#', ItemTags.PLANKS).define('$', EItemTags.MOVCADIA_LOGS).define('/', Tags.Items.RODS_WOODEN)
                .pattern("#$")
                .pattern(" /")
                .pattern(" /")
                .unlockedBy(CriterionName.MOVCADIA.name, has(EItemTags.MOVCADIA_LOGS)).save(writer);

        //Smithing
        smithingCombatTransform(writer, EItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, EItems.ASTALITE_SWORD, EItemTags.INGOTS_DIARKRITE, EItems.DIARKRITE_SWORD);
        smithingCombatTransform(writer, EItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, EItems.ASTALITE_SHOVEL, EItemTags.INGOTS_DIARKRITE, EItems.DIARKRITE_SHOVEL);
        smithingCombatTransform(writer, EItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, EItems.ASTALITE_PICKAXE, EItemTags.INGOTS_DIARKRITE, EItems.DIARKRITE_PICKAXE);
        smithingCombatTransform(writer, EItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, EItems.ASTALITE_AXE, EItemTags.INGOTS_DIARKRITE, EItems.DIARKRITE_AXE);
        smithingCombatTransform(writer, EItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, EItems.ASTALITE_HOE, EItemTags.INGOTS_DIARKRITE, EItems.DIARKRITE_HOE);

        smithingCombatTransform(writer, EItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, EItems.ASTALITE_SWORD, EItemTags.INGOTS_ANTHEKTITE, EItems.ANTHEKTITE_SWORD);
        smithingCombatTransform(writer, EItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, EItems.ASTALITE_SHOVEL, EItemTags.INGOTS_ANTHEKTITE, EItems.ANTHEKTITE_SHOVEL);
        smithingCombatTransform(writer, EItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, EItems.ASTALITE_PICKAXE, EItemTags.INGOTS_ANTHEKTITE, EItems.ANTHEKTITE_PICKAXE);
        smithingCombatTransform(writer, EItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, EItems.ASTALITE_AXE, EItemTags.INGOTS_ANTHEKTITE, EItems.ANTHEKTITE_AXE);
        smithingCombatTransform(writer, EItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, EItems.ASTALITE_HOE, EItemTags.INGOTS_ANTHEKTITE, EItems.ANTHEKTITE_HOE);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, EItems.ASTALITE_SHIELD.get())
                .define('#', EItemTags.INGOTS_STEEL).define('W', ItemTags.PLANKS)
                .pattern("W#W").pattern("WWW").pattern(" W ")
                .unlockedBy(getHasName(EItems.ASTALITE_INGOT.get()), has(EItems.ASTALITE_INGOT.get()))
                .save(writer);

        smithingCombatTransform(writer, EItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, EItems.ASTALITE_SHIELD, EItemTags.INGOTS_DIARKRITE, EItems.DIARKRITE_SHIELD);
        smithingCombatTransform(writer, EItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, EItems.ASTALITE_SHIELD, EItemTags.INGOTS_ANTHEKTITE, EItems.ANTHEKTITE_SHIELD);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, EItems.ASTALITE_BOW.get())
                .define('#', EItemTags.INGOTS_STEEL).define('/', Tags.Items.RODS_WOODEN).define('S', Tags.Items.STRING)
                .pattern("#/S").pattern("/ S").pattern("#/S")
                .unlockedBy(getHasName(EItems.ASTALITE_INGOT.get()), has(EItems.ASTALITE_INGOT.get()))
                .save(writer);

        smithingCombatTransform(writer, EItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, EItems.ASTALITE_BOW, EItemTags.INGOTS_DIARKRITE, EItems.DIARKRITE_BOW);
        smithingCombatTransform(writer, EItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, EItems.ASTALITE_BOW, EItemTags.INGOTS_ANTHEKTITE, EItems.ANTHEKTITE_BOW);

        smithingCombatTransform(writer, EItems.WEAPON_FRAGMENT, EItems.DIARKRITE_SWORD, Items.SCULK_CATALYST, EItems.DIARKRITE_CHARGE_BLADE);
        smithingCombatTransform(writer, EItems.WEAPON_FRAGMENT, EItems.ANTHEKTITE_SWORD, Items.SCULK_CATALYST, EItems.ANTHEKTITE_CHARGE_BLADE);

        //Ingredients
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, EItems.CRUDE_STEEL.get())
                .requires(Ingredient.of(Tags.Items.INGOTS_IRON), 2)
                .requires(Ingredient.of(Tags.Items.RAW_MATERIALS_IRON), 1)
                .requires(Ingredient.of(ItemTags.COALS), 2)
                .unlockedBy("has_iron_ingot", has(net.minecraft.world.item.Items.IRON_INGOT))
                .save(writer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, EItems.DIARKRITE_INGOT.get())
                .requires(Ingredient.of(EItemTags.ORES_ATELIS), 3)
                .requires(Ingredient.of(EItemTags.INGOTS_STEEL), 2)
                .requires(Ingredient.of(Items.ECHO_SHARD), 2)
                .unlockedBy("has_remnant_ingot", has(EItems.MOVCADIA_ESSENCE.get()))
                .save(writer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, EItems.ANTHEKTITE_INGOT.get())
                .requires(Ingredient.of(EItemTags.ORES_ATELIS), 3)
                .requires(Ingredient.of(EItemTags.INGOTS_STEEL), 2)
                .requires(Ingredient.of(EItems.MOVCADIA_ESSENCE.get()), 2)
                .unlockedBy("has_remnant_ingot", has(EItems.MOVCADIA_ESSENCE.get()))
                .save(writer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, EItems.ASTALITE_INGOT.get())
                .requires(Tags.Items.INGOTS_IRON).requires(Tags.Items.INGOTS_IRON).requires(EItems.ASTALITE_SCRAP.get())
                .unlockedBy(getHasName(EItems.ASTALITE_INGOT.get()), has(EItems.ASTALITE_INGOT.get()))
                .save(writer, this.name(getItemName(EItems.ASTALITE_INGOT.get()) + "_from_steel_scrap"));

        storageBlock(writer, RecipeCategory.MISC,
                EItems.ASTALITE_INGOT.get(), RecipeCategory.BUILDING_BLOCKS, EItems.STEEL_BLOCK.get(),
                "steel_ingot_from_steel_block", "steel_ingot");

        nuggetIngot(writer, RecipeCategory.MISC, EItems.ASTALITE_INGOT.get(), EItems.ASTALITE_NUGGET.get(), "steel_ingot", "steel_ingot_from_nuggets");

        storageBlock(writer, RecipeCategory.MISC,
                EItems.DIARKRITE_INGOT.get(), RecipeCategory.BUILDING_BLOCKS, EItems.DIARKRITE_BLOCK.get(),
                "diarkrite_ingot_from_steel_block", "diarkrite_ingot");

        storageBlock(writer, RecipeCategory.MISC,
                EItems.ANTHEKTITE_INGOT.get(), RecipeCategory.BUILDING_BLOCKS, EItems.ANTHEKTITE_BLOCK.get(),
                "anthektite_ingot_from_steel_block", "anthektite_ingot");

        smeltingOreRecipe(writer, EItems.ASTALITE_INGOT.get(), EItems.CRUDE_STEEL.get(), 0.5F);
        smeltingOreRecipe(writer, EItems.ATELIS_SCRAP.get(), EItems.REMNANT.get(), 0.5F);

        blastingOreRecipe(writer, EItems.ASTALITE_INGOT.get(), EItems.CRUDE_STEEL.get(), 0.5F);
        blastingOreRecipe(writer, EItems.ATELIS_SCRAP.get(), EItems.REMNANT.get(), 0.5F);

        templateDuplication(EItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, net.minecraft.world.item.Items.COBBLED_DEEPSLATE).save(writer);

        steelRecycleSmelt(writer, Ingredient.of(EItemTags.STEEL_RECYCLABLE), 0.5F, CriterionName.STEEL.name);
        steelRecycleBlast(writer, Ingredient.of(EItemTags.STEEL_RECYCLABLE), 0.5F, CriterionName.STEEL.name);

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(EItems.CRUSHED_REMNANT.get()), RecipeCategory.MISC, EItems.ATELIS_SCRAP.get(), 0.5F, 200)
                .unlockedBy("has_crushed_remnant", has(EItems.CRUSHED_REMNANT.get()))
                .save(writer, this.name(getItemName(EItems.ATELIS_SCRAP.get()) + "_from_smelting_crushed_remnant"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(EItems.CRUSHED_REMNANT.get()), RecipeCategory.MISC, EItems.ATELIS_SCRAP.get(), 0.5F, 100)
                .unlockedBy("has_crushed_remnant", has(EItems.CRUSHED_REMNANT.get()))
                .save(writer, this.name(getItemName(EItems.ATELIS_SCRAP.get()) + "_from_blasting_crushed_remnant"));

        SimpleCookingRecipeBuilder.blasting(Ingredient.of(EItems.MOVCADIA_BERRIES.get()), RecipeCategory.MISC, EItems.MOVCADIA_ESSENCE.get(), 0.5F, 150)
                .unlockedBy("has_movcadia_berries", has(EItems.MOVCADIA_BERRIES.get()))
                .save(writer, this.name(getItemName(EItems.MOVCADIA_ESSENCE.get()) + "_from_blasting"));

        //Building blocks
        wood(writer, EItems.MOVCADIA_LOG, EItems.MOVCADIA_WOOD);
        wood(writer, EItems.STRIPPED_MOVCADIA_LOG, EItems.STRIPPED_MOVCADIA_WOOD);
        planks(writer, EItemTags.MOVCADIA_LOGS, EItems.MOVCADIA_PLANKS);
        stairs(writer, EItems.MOVCADIA_STAIRS, EItems.MOVCADIA_PLANKS);
        slab(writer, RecipeCategory.BUILDING_BLOCKS, EItems.MOVCADIA_SLAB.get(), EItems.MOVCADIA_PLANKS.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, EItems.STEEL_BARS.get(), 16)
                .define('#', EItems.ASTALITE_INGOT.get())
                .pattern("###").pattern("###")
                .unlockedBy(getHasName(EItems.ASTALITE_INGOT.get()), has(EItems.ASTALITE_INGOT.get()))
                .save(writer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, EItems.STEEL_TILES.get(), 16)
                .define('#', EItems.STEEL_BLOCK.get())
                .pattern("##").pattern("##")
                .unlockedBy(getHasName(EItems.STEEL_BLOCK.get()), has(EItems.ASTALITE_INGOT.get()))
                .save(writer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, EItems.STEEL_TILE_STAIR.get(), 4)
                .define('#', EItems.STEEL_TILES.get())
                .pattern("#  ").pattern("## ").pattern("###")
                .unlockedBy(getHasName(EItems.STEEL_BLOCK.get()), has(EItems.ASTALITE_INGOT.get()))
                .save(writer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, EItems.STEEL_TILE_SLAB.get(), 2)
                .define('#', EItems.STEEL_TILES.get())
                .pattern("###")
                .unlockedBy(getHasName(EItems.STEEL_BLOCK.get()), has(EItems.ASTALITE_INGOT.get()))
                .save(writer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, EItems.STEEL_BLOCK.get(), 1)
                .define('#', EItems.STEEL_TILES.get())
                .pattern("##").pattern("##")
                .unlockedBy(getHasName(EItems.STEEL_TILES.get()), has(EItems.STEEL_BLOCK.get()))
                .save(writer, getHasName(EItems.STEEL_BLOCK.get()) + "_from_steel_tiles");

        fence(writer, EItems.MOVCADIA_FENCE, EItems.MOVCADIA_PLANKS);
        fenceGate(writer, EItems.MOVCADIA_FENCE_GATE, EItems.MOVCADIA_PLANKS);

        door(writer, EItems.MOVCADIA_DOOR, EItems.MOVCADIA_PLANKS);
        trapdoor(writer, EItems.MOVCADIA_TRAPDOOR, EItems.MOVCADIA_PLANKS);

        pressurePlate(writer, EItems.MOVCADIA_PRESSURE_PLATE.get(), EItems.MOVCADIA_PLANKS.get());
        button(writer, EItems.MOVCADIA_BUTTON, EItems.MOVCADIA_PLANKS);

        sign(writer, EItems.MOVCADIA_SIGN, EItems.MOVCADIA_PLANKS);
        hangingSign(writer, EItems.MOVCADIA_HANGING_SIGN.get(), EItems.MOVCADIA_PLANKS.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, EItems.STURDY_MOVCADIA_SIGN.get())
                .define('#', EItems.MOVCADIA_LOG.get()).define('I', EItems.MOVCADIA_PLANKS.get()).define('/', net.minecraft.world.item.Items.STICK)
                .pattern("#I#").pattern("#I#").pattern(" / ")
                .unlockedBy(getHasName(EItems.MOVCADIA_PLANKS.get()), has(EItems.MOVCADIA_PLANKS.get()))
                .save(writer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, EItems.MOVCADIA_CHEST.get())
                .define('#', EItems.MOVCADIA_PLANKS.get())
                .define('L', EItems.MOVCADIA_LOG.get())
                .pattern("###").pattern("#L#").pattern("###")
                .unlockedBy(getHasName(EItems.MOVCADIA_PLANKS.get()), has(EItems.MOVCADIA_PLANKS.get()))
                .save(writer);

        woodenBoat(writer, EItems.MOVCADIA_BOAT.get(), EItems.MOVCADIA_PLANKS.get());
        chestBoat(writer, EItems.MOVCADIA_CHEST_BOAT.get(), EItems.MOVCADIA_BOAT.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, EItems.GLISTERING_MOVCADIA_BERRIES.get())
                .define('#', EItems.MOVCADIA_BERRIES.get())
                .define('/', Items.GOLD_NUGGET)
                .pattern("///").pattern("/#/").pattern("///")
                .unlockedBy(getHasName(EItems.MOVCADIA_BERRIES.get()), has(Items.GOLD_INGOT))
                .save(writer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, EItems.REINFORCED_PLATING_GOLEM_UPGRADE.get())
                .define('#', EItems.STEEL_BLOCK.get())
                .define('/', EItems.ASTALITE_INGOT.get())
                .define('@', EItems.ASTALITE_CHESTPLATE.get())
                .pattern("#/#").pattern("/#/").pattern("/@/")
                .unlockedBy(getHasName(EItems.STEEL_BLOCK.get()), has(EItems.ASTALITE_CHESTPLATE.get()))
                .save(writer);
    }
}
