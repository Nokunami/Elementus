package net.nokunami.elementus.datagen.generators;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import net.nokunami.elementus.common.Etags;
import net.nokunami.elementus.common.registry.ModItems.ElementusItems;
import net.nokunami.elementus.datagen.generators.create.CreateProcessingRecipe;
import net.nokunami.elementus.datagen.providers.ModRecipeProvider;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class ModRecipeData extends ModRecipeProvider {
    public ModRecipeData(PackOutput output) {
        super(output, "elementus");
        new CreateProcessingRecipe(output);
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
        helmetRecipe(ElementusItems.STEEL_HELMET, Etags.Items.INGOTS_STEEL, CriterionName.STEEL.name).save(writer);
        chestplateRecipe(ElementusItems.STEEL_CHESTPLATE, Etags.Items.INGOTS_STEEL, CriterionName.STEEL.name).save(writer);
        leggingsRecipe(ElementusItems.STEEL_LEGGINGS, Etags.Items.INGOTS_STEEL, CriterionName.STEEL.name).save(writer);
        bootsRecipe(ElementusItems.STEEL_BOOTS, Etags.Items.INGOTS_STEEL, CriterionName.STEEL.name).save(writer);

        //Smithing
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ElementusItems.STEEL_HELMET, Etags.Items.INGOTS_DIARKRITE, ElementusItems.DIARKRITE_HELMET);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ElementusItems.STEEL_CHESTPLATE, Etags.Items.INGOTS_DIARKRITE, ElementusItems.DIARKRITE_CHESTPLATE);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ElementusItems.STEEL_LEGGINGS, Etags.Items.INGOTS_DIARKRITE, ElementusItems.DIARKRITE_LEGGINGS);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ElementusItems.STEEL_BOOTS, Etags.Items.INGOTS_DIARKRITE, ElementusItems.DIARKRITE_BOOTS);

        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ElementusItems.STEEL_HELMET, Etags.Items.INGOTS_ANTHEKTITE, ElementusItems.ANTHEKTITE_HELMET);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ElementusItems.STEEL_CHESTPLATE, Etags.Items.INGOTS_ANTHEKTITE, ElementusItems.ANTHEKTITE_CHESTPLATE);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ElementusItems.STEEL_LEGGINGS, Etags.Items.INGOTS_ANTHEKTITE, ElementusItems.ANTHEKTITE_LEGGINGS);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ElementusItems.STEEL_BOOTS, Etags.Items.INGOTS_ANTHEKTITE, ElementusItems.ANTHEKTITE_BOOTS);

        //Tools
        //Crafting
        swordRecipe(ElementusItems.STEEL_SWORD, Etags.Items.INGOTS_STEEL, CriterionName.STEEL.name).save(writer);
        shovelRecipe(ElementusItems.STEEL_SHOVEL, Etags.Items.INGOTS_STEEL, CriterionName.STEEL.name).save(writer);
        pickaxeRecipe(ElementusItems.STEEL_PICKAXE, Etags.Items.INGOTS_STEEL, CriterionName.STEEL.name).save(writer);
        axeRecipe(ElementusItems.STEEL_AXE, Etags.Items.INGOTS_STEEL, CriterionName.STEEL.name).save(writer);
        hoeRecipe(ElementusItems.STEEL_HOE, Etags.Items.INGOTS_STEEL, CriterionName.STEEL.name).save(writer);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ElementusItems.MOVCADIA_SWORD.get())
                .define('#', ItemTags.PLANKS).define('$', Etags.Items.MOVCADIA_LOGS).define('/', Tags.Items.RODS_WOODEN)
                .pattern("#")
                .pattern("$")
                .pattern("/")
                .unlockedBy(CriterionName.MOVCADIA.name, has(Etags.Items.MOVCADIA_LOGS)).save(writer);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ElementusItems.MOVCADIA_SHOVEL.get())
                .define('$', Etags.Items.MOVCADIA_LOGS).define('/', Tags.Items.RODS_WOODEN)
                .pattern("$")
                .pattern("/")
                .pattern("/")
                .unlockedBy(CriterionName.MOVCADIA.name, has(Etags.Items.MOVCADIA_LOGS)).save(writer);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ElementusItems.MOVCADIA_PICKAXE.get())
                .define('#', ItemTags.PLANKS).define('$', Etags.Items.MOVCADIA_LOGS).define('/', Tags.Items.RODS_WOODEN)
                .pattern("#$#")
                .pattern(" / ")
                .pattern(" / ")
                .unlockedBy(CriterionName.MOVCADIA.name, has(Etags.Items.MOVCADIA_LOGS)).save(writer);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ElementusItems.MOVCADIA_AXE.get())
                .define('#', ItemTags.PLANKS).define('$', Etags.Items.MOVCADIA_LOGS).define('/', Tags.Items.RODS_WOODEN)
                .pattern("#$")
                .pattern("#/")
                .pattern(" /")
                .unlockedBy(CriterionName.MOVCADIA.name, has(Etags.Items.MOVCADIA_LOGS)).save(writer);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ElementusItems.MOVCADIA_HOE.get())
                .define('#', ItemTags.PLANKS).define('$', Etags.Items.MOVCADIA_LOGS).define('/', Tags.Items.RODS_WOODEN)
                .pattern("#$")
                .pattern(" /")
                .pattern(" /")
                .unlockedBy(CriterionName.MOVCADIA.name, has(Etags.Items.MOVCADIA_LOGS)).save(writer);

        //Smithing
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ElementusItems.STEEL_SWORD, Etags.Items.INGOTS_DIARKRITE, ElementusItems.DIARKRITE_SWORD);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ElementusItems.STEEL_SHOVEL, Etags.Items.INGOTS_DIARKRITE, ElementusItems.DIARKRITE_SHOVEL);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ElementusItems.STEEL_PICKAXE, Etags.Items.INGOTS_DIARKRITE, ElementusItems.DIARKRITE_PICKAXE);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ElementusItems.STEEL_AXE, Etags.Items.INGOTS_DIARKRITE, ElementusItems.DIARKRITE_AXE);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ElementusItems.STEEL_HOE, Etags.Items.INGOTS_DIARKRITE, ElementusItems.DIARKRITE_HOE);

        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ElementusItems.STEEL_SWORD, Etags.Items.INGOTS_ANTHEKTITE, ElementusItems.ANTHEKTITE_SWORD);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ElementusItems.STEEL_SHOVEL, Etags.Items.INGOTS_ANTHEKTITE, ElementusItems.ANTHEKTITE_SHOVEL);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ElementusItems.STEEL_PICKAXE, Etags.Items.INGOTS_ANTHEKTITE, ElementusItems.ANTHEKTITE_PICKAXE);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ElementusItems.STEEL_AXE, Etags.Items.INGOTS_ANTHEKTITE, ElementusItems.ANTHEKTITE_AXE);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ElementusItems.STEEL_HOE, Etags.Items.INGOTS_ANTHEKTITE, ElementusItems.ANTHEKTITE_HOE);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ElementusItems.STEEL_SHIELD.get())
                .define('#', Etags.Items.INGOTS_STEEL).define('W', ItemTags.PLANKS)
                .pattern("W#W").pattern("WWW").pattern(" W ")
                .unlockedBy(getHasName(ElementusItems.STEEL_INGOT.get()), has(ElementusItems.STEEL_INGOT.get()))
                .save(writer);

        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ElementusItems.STEEL_SHIELD, Etags.Items.INGOTS_DIARKRITE, ElementusItems.DIARKRITE_SHIELD);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ElementusItems.STEEL_SHIELD, Etags.Items.INGOTS_ANTHEKTITE, ElementusItems.ANTHEKTITE_SHIELD);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ElementusItems.STEEL_BOW.get())
                .define('#', Etags.Items.INGOTS_STEEL).define('/', Tags.Items.RODS_WOODEN).define('S', Tags.Items.STRING)
                .pattern("#/S").pattern("/ S").pattern("#/S")
                .unlockedBy(getHasName(ElementusItems.STEEL_INGOT.get()), has(ElementusItems.STEEL_INGOT.get()))
                .save(writer);

        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ElementusItems.STEEL_BOW, Etags.Items.INGOTS_DIARKRITE, ElementusItems.DIARKRITE_BOW);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ElementusItems.STEEL_BOW, Etags.Items.INGOTS_ANTHEKTITE, ElementusItems.ANTHEKTITE_BOW);

        smithingCombatTransform(writer, ElementusItems.WEAPON_FRAGMENT, ElementusItems.DIARKRITE_SWORD, Items.SCULK_CATALYST, ElementusItems.DIARKRITE_CHARGE_BLADE);

        //Ingredients
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ElementusItems.CRUDE_STEEL.get())
                .requires(Ingredient.of(Tags.Items.INGOTS_IRON), 2)
                .requires(Ingredient.of(Tags.Items.RAW_MATERIALS_IRON), 1)
                .requires(Ingredient.of(ItemTags.COALS), 2)
                .unlockedBy("has_iron_ingot", has(net.minecraft.world.item.Items.IRON_INGOT))
                .save(writer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ElementusItems.DIARKRITE_INGOT.get())
                .requires(Ingredient.of(Etags.Items.ORES_ATELIS), 3)
                .requires(Ingredient.of(Etags.Items.INGOTS_STEEL), 2)
                .requires(Ingredient.of(Items.ECHO_SHARD), 2)
                .unlockedBy("has_remnant_ingot", has(ElementusItems.MOVCADIA_ESSENCE.get()))
                .save(writer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ElementusItems.ANTHEKTITE_INGOT.get())
                .requires(Ingredient.of(Etags.Items.ORES_ATELIS), 3)
                .requires(Ingredient.of(Etags.Items.INGOTS_STEEL), 2)
                .requires(Ingredient.of(ElementusItems.MOVCADIA_ESSENCE.get()), 2)
                .unlockedBy("has_remnant_ingot", has(ElementusItems.MOVCADIA_ESSENCE.get()))
                .save(writer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ElementusItems.STEEL_INGOT.get())
                .requires(Tags.Items.INGOTS_IRON).requires(Tags.Items.INGOTS_IRON).requires(ElementusItems.STEEL_SCRAP.get())
                .unlockedBy(getHasName(ElementusItems.STEEL_INGOT.get()), has(ElementusItems.STEEL_INGOT.get()))
                .save(writer, this.name(getItemName(ElementusItems.STEEL_INGOT.get()) + "_from_steel_scrap"));

        storageBlock(writer, RecipeCategory.MISC,
                ElementusItems.STEEL_INGOT.get(), RecipeCategory.BUILDING_BLOCKS, ElementusItems.STEEL_BLOCK.get(),
                "steel_ingot_from_steel_block", "steel_ingot");

        nuggetIngot(writer, RecipeCategory.MISC, ElementusItems.STEEL_INGOT.get(), ElementusItems.STEEL_NUGGET.get(), "steel_ingot", "steel_ingot_from_nuggets");

        storageBlock(writer, RecipeCategory.MISC,
                ElementusItems.DIARKRITE_INGOT.get(), RecipeCategory.BUILDING_BLOCKS, ElementusItems.DIARKRITE_BLOCK.get(),
                "diarkrite_ingot_from_steel_block", "diarkrite_ingot");

        storageBlock(writer, RecipeCategory.MISC,
                ElementusItems.ANTHEKTITE_INGOT.get(), RecipeCategory.BUILDING_BLOCKS, ElementusItems.ANTHEKTITE_BLOCK.get(),
                "anthektite_ingot_from_steel_block", "anthektite_ingot");

        smeltingOreRecipe(writer, ElementusItems.STEEL_INGOT.get(), ElementusItems.CRUDE_STEEL.get(), 0.5F);
        smeltingOreRecipe(writer, ElementusItems.ATELIS_SCRAP.get(), ElementusItems.REMNANT.get(), 0.5F);

        blastingOreRecipe(writer, ElementusItems.STEEL_INGOT.get(), ElementusItems.CRUDE_STEEL.get(), 0.5F);
        blastingOreRecipe(writer, ElementusItems.ATELIS_SCRAP.get(), ElementusItems.REMNANT.get(), 0.5F);

        templateDuplication(ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, net.minecraft.world.item.Items.COBBLED_DEEPSLATE).save(writer);

        steelRecycleSmelt(writer, Ingredient.of(Etags.Items.STEEL_RECYCLABLE), 0.5F, CriterionName.STEEL.name);
        steelRecycleBlast(writer, Ingredient.of(Etags.Items.STEEL_RECYCLABLE), 0.5F, CriterionName.STEEL.name);

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ElementusItems.CRUSHED_REMNANT.get()), RecipeCategory.MISC, ElementusItems.ATELIS_SCRAP.get(), 0.5F, 200)
                .unlockedBy("has_crushed_remnant", has(ElementusItems.CRUSHED_REMNANT.get()))
                .save(writer, this.name(getItemName(ElementusItems.ATELIS_SCRAP.get()) + "_from_smelting_crushed_remnant"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ElementusItems.CRUSHED_REMNANT.get()), RecipeCategory.MISC, ElementusItems.ATELIS_SCRAP.get(), 0.5F, 100)
                .unlockedBy("has_crushed_remnant", has(ElementusItems.CRUSHED_REMNANT.get()))
                .save(writer, this.name(getItemName(ElementusItems.ATELIS_SCRAP.get()) + "_from_blasting_crushed_remnant"));

        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ElementusItems.MOVCADIA_BERRIES.get()), RecipeCategory.MISC, ElementusItems.MOVCADIA_ESSENCE.get(), 0.5F, 150)
                .unlockedBy("has_movcadia_berries", has(ElementusItems.MOVCADIA_BERRIES.get()))
                .save(writer, this.name(getItemName(ElementusItems.MOVCADIA_ESSENCE.get()) + "_from_blasting"));

        //Building blocks
        wood(writer, ElementusItems.MOVCADIA_LOG, ElementusItems.MOVCADIA_WOOD);
        wood(writer, ElementusItems.STRIPPED_MOVCADIA_LOG, ElementusItems.STRIPPED_MOVCADIA_WOOD);
        planks(writer, Etags.Items.MOVCADIA_LOGS, ElementusItems.MOVCADIA_PLANKS);
        stairs(writer, ElementusItems.MOVCADIA_STAIRS, ElementusItems.MOVCADIA_PLANKS);
        slab(writer, RecipeCategory.BUILDING_BLOCKS, ElementusItems.MOVCADIA_SLAB.get(), ElementusItems.MOVCADIA_PLANKS.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ElementusItems.STEEL_BARS.get(), 16)
                .define('#', ElementusItems.STEEL_INGOT.get())
                .pattern("###").pattern("###")
                .unlockedBy(getHasName(ElementusItems.STEEL_INGOT.get()), has(ElementusItems.STEEL_INGOT.get()))
                .save(writer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ElementusItems.STEEL_TILES.get(), 16)
                .define('#', ElementusItems.STEEL_BLOCK.get())
                .pattern("##").pattern("##")
                .unlockedBy(getHasName(ElementusItems.STEEL_BLOCK.get()), has(ElementusItems.STEEL_INGOT.get()))
                .save(writer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ElementusItems.STEEL_TILE_STAIR.get(), 4)
                .define('#', ElementusItems.STEEL_TILES.get())
                .pattern("#  ").pattern("## ").pattern("###")
                .unlockedBy(getHasName(ElementusItems.STEEL_BLOCK.get()), has(ElementusItems.STEEL_INGOT.get()))
                .save(writer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ElementusItems.STEEL_TILE_SLAB.get(), 2)
                .define('#', ElementusItems.STEEL_TILES.get())
                .pattern("###")
                .unlockedBy(getHasName(ElementusItems.STEEL_BLOCK.get()), has(ElementusItems.STEEL_INGOT.get()))
                .save(writer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ElementusItems.STEEL_BLOCK.get(), 1)
                .define('#', ElementusItems.STEEL_TILES.get())
                .pattern("##").pattern("##")
                .unlockedBy(getHasName(ElementusItems.STEEL_TILES.get()), has(ElementusItems.STEEL_BLOCK.get()))
                .save(writer, getHasName(ElementusItems.STEEL_BLOCK.get()) + "_from_steel_tiles");

        fence(writer, ElementusItems.MOVCADIA_FENCE, ElementusItems.MOVCADIA_PLANKS);
        fenceGate(writer, ElementusItems.MOVCADIA_FENCE_GATE, ElementusItems.MOVCADIA_PLANKS);

        door(writer, ElementusItems.MOVCADIA_DOOR, ElementusItems.MOVCADIA_PLANKS);
        trapdoor(writer, ElementusItems.MOVCADIA_TRAPDOOR, ElementusItems.MOVCADIA_PLANKS);

        pressurePlate(writer, ElementusItems.MOVCADIA_PRESSURE_PLATE.get(), ElementusItems.MOVCADIA_PLANKS.get());
        button(writer, ElementusItems.MOVCADIA_BUTTON, ElementusItems.MOVCADIA_PLANKS);

        sign(writer, ElementusItems.MOVCADIA_SIGN, ElementusItems.MOVCADIA_PLANKS);
        hangingSign(writer, ElementusItems.MOVCADIA_HANGING_SIGN.get(), ElementusItems.MOVCADIA_PLANKS.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ElementusItems.STURDY_MOVCADIA_SIGN.get())
                .define('#', ElementusItems.MOVCADIA_LOG.get()).define('I', ElementusItems.MOVCADIA_PLANKS.get()).define('/', net.minecraft.world.item.Items.STICK)
                .pattern("#I#").pattern("#I#").pattern(" / ")
                .unlockedBy(getHasName(ElementusItems.MOVCADIA_PLANKS.get()), has(ElementusItems.MOVCADIA_PLANKS.get()))
                .save(writer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ElementusItems.MOVCADIA_CHEST.get())
                .define('#', ElementusItems.MOVCADIA_PLANKS.get())
                .pattern("###").pattern("# #").pattern("###")
                .unlockedBy(getHasName(ElementusItems.MOVCADIA_PLANKS.get()), has(ElementusItems.MOVCADIA_PLANKS.get()))
                .save(writer);

        woodenBoat(writer, ElementusItems.MOVCADIA_BOAT.get(), ElementusItems.MOVCADIA_PLANKS.get());
        chestBoat(writer, ElementusItems.MOVCADIA_CHEST_BOAT.get(), ElementusItems.MOVCADIA_BOAT.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ElementusItems.GLISTERING_MOVCADIA_BERRIES.get())
                .define('#', ElementusItems.MOVCADIA_BERRIES.get())
                .define('/', Items.GOLD_NUGGET)
                .pattern("///").pattern("/#/").pattern("///")
                .unlockedBy(getHasName(ElementusItems.MOVCADIA_BERRIES.get()), has(Items.GOLD_INGOT))
                .save(writer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ElementusItems.REINFORCED_PLATING_GOLEM_UPGRADE.get())
                .define('#', ElementusItems.STEEL_BLOCK.get())
                .define('/', ElementusItems.STEEL_INGOT.get())
                .define('@', ElementusItems.STEEL_CHESTPLATE.get())
                .pattern("#/#").pattern("/#/").pattern("/@/")
                .unlockedBy(getHasName(ElementusItems.STEEL_BLOCK.get()), has(ElementusItems.STEEL_CHESTPLATE.get()))
                .save(writer);
    }
}
