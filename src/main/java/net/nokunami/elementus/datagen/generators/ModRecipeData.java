package net.nokunami.elementus.datagen.generators;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import net.nokunami.elementus.common.Etags;
import net.nokunami.elementus.common.registry.ModItems;
import net.nokunami.elementus.datagen.providers.ModRecipeProvider;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class ModRecipeData extends ModRecipeProvider {
    public ModRecipeData(PackOutput output) {
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
        helmetRecipe(ModItems.STEEL_HELMET, Etags.Items.INGOTS_STEEL, CriterionName.STEEL.name).save(writer);
        chestplateRecipe(ModItems.STEEL_CHESTPLATE, Etags.Items.INGOTS_STEEL, CriterionName.STEEL.name).save(writer);
        leggingsRecipe(ModItems.STEEL_LEGGINGS, Etags.Items.INGOTS_STEEL, CriterionName.STEEL.name).save(writer);
        bootsRecipe(ModItems.STEEL_BOOTS, Etags.Items.INGOTS_STEEL, CriterionName.STEEL.name).save(writer);

        //Smithing
        smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ModItems.STEEL_HELMET, Etags.Items.INGOTS_DIARKRITE, ModItems.DIARKRITE_HELMET);
        smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ModItems.STEEL_CHESTPLATE, Etags.Items.INGOTS_DIARKRITE, ModItems.DIARKRITE_CHESTPLATE);
        smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ModItems.STEEL_LEGGINGS, Etags.Items.INGOTS_DIARKRITE, ModItems.DIARKRITE_LEGGINGS);
        smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ModItems.STEEL_BOOTS, Etags.Items.INGOTS_DIARKRITE, ModItems.DIARKRITE_BOOTS);

        smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ModItems.STEEL_HELMET, Etags.Items.INGOTS_ANTHEKTITE, ModItems.ANTHEKTITE_HELMET);
        smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ModItems.STEEL_CHESTPLATE, Etags.Items.INGOTS_ANTHEKTITE, ModItems.ANTHEKTITE_CHESTPLATE);
        smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ModItems.STEEL_LEGGINGS, Etags.Items.INGOTS_ANTHEKTITE, ModItems.ANTHEKTITE_LEGGINGS);
        smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ModItems.STEEL_BOOTS, Etags.Items.INGOTS_ANTHEKTITE, ModItems.ANTHEKTITE_BOOTS);

        //Tools
        //Crafting
        swordRecipe(ModItems.STEEL_SWORD, Etags.Items.INGOTS_STEEL, CriterionName.STEEL.name).save(writer);
        shovelRecipe(ModItems.STEEL_SHOVEL, Etags.Items.INGOTS_STEEL, CriterionName.STEEL.name).save(writer);
        pickaxeRecipe(ModItems.STEEL_PICKAXE, Etags.Items.INGOTS_STEEL, CriterionName.STEEL.name).save(writer);
        axeRecipe(ModItems.STEEL_AXE, Etags.Items.INGOTS_STEEL, CriterionName.STEEL.name).save(writer);
        hoeRecipe(ModItems.STEEL_HOE, Etags.Items.INGOTS_STEEL, CriterionName.STEEL.name).save(writer);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.MOVCADIA_SWORD.get())
                .define('#', ItemTags.PLANKS).define('$', Etags.Items.MOVCADIA_LOGS).define('/', Tags.Items.RODS_WOODEN)
                .pattern("#")
                .pattern("$")
                .pattern("/")
                .unlockedBy(CriterionName.MOVCADIA.name, has(Etags.Items.MOVCADIA_LOGS)).save(writer);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.MOVCADIA_SHOVEL.get())
                .define('$', Etags.Items.MOVCADIA_LOGS).define('/', Tags.Items.RODS_WOODEN)
                .pattern("$")
                .pattern("/")
                .pattern("/")
                .unlockedBy(CriterionName.MOVCADIA.name, has(Etags.Items.MOVCADIA_LOGS)).save(writer);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.MOVCADIA_PICKAXE.get())
                .define('#', ItemTags.PLANKS).define('$', Etags.Items.MOVCADIA_LOGS).define('/', Tags.Items.RODS_WOODEN)
                .pattern("#$#")
                .pattern(" / ")
                .pattern(" / ")
                .unlockedBy(CriterionName.MOVCADIA.name, has(Etags.Items.MOVCADIA_LOGS)).save(writer);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.MOVCADIA_AXE.get())
                .define('#', ItemTags.PLANKS).define('$', Etags.Items.MOVCADIA_LOGS).define('/', Tags.Items.RODS_WOODEN)
                .pattern("#$")
                .pattern("#/")
                .pattern(" /")
                .unlockedBy(CriterionName.MOVCADIA.name, has(Etags.Items.MOVCADIA_LOGS)).save(writer);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.MOVCADIA_HOE.get())
                .define('#', ItemTags.PLANKS).define('$', Etags.Items.MOVCADIA_LOGS).define('/', Tags.Items.RODS_WOODEN)
                .pattern("#$")
                .pattern(" /")
                .pattern(" /")
                .unlockedBy(CriterionName.MOVCADIA.name, has(Etags.Items.MOVCADIA_LOGS)).save(writer);

        //Smithing
        smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ModItems.STEEL_SWORD, Etags.Items.INGOTS_DIARKRITE, ModItems.DIARKRITE_SWORD);
        smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ModItems.STEEL_SHOVEL, Etags.Items.INGOTS_DIARKRITE, ModItems.DIARKRITE_SHOVEL);
        smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ModItems.STEEL_PICKAXE, Etags.Items.INGOTS_DIARKRITE, ModItems.DIARKRITE_PICKAXE);
        smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ModItems.STEEL_AXE, Etags.Items.INGOTS_DIARKRITE, ModItems.DIARKRITE_AXE);
        smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ModItems.STEEL_HOE, Etags.Items.INGOTS_DIARKRITE, ModItems.DIARKRITE_HOE);

        smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ModItems.STEEL_SWORD, Etags.Items.INGOTS_ANTHEKTITE, ModItems.ANTHEKTITE_SWORD);
        smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ModItems.STEEL_SHOVEL, Etags.Items.INGOTS_ANTHEKTITE, ModItems.ANTHEKTITE_SHOVEL);
        smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ModItems.STEEL_PICKAXE, Etags.Items.INGOTS_ANTHEKTITE, ModItems.ANTHEKTITE_PICKAXE);
        smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ModItems.STEEL_AXE, Etags.Items.INGOTS_ANTHEKTITE, ModItems.ANTHEKTITE_AXE);
        smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ModItems.STEEL_HOE, Etags.Items.INGOTS_ANTHEKTITE, ModItems.ANTHEKTITE_HOE);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_SHIELD.get())
                .define('#', Etags.Items.INGOTS_STEEL).define('W', ItemTags.PLANKS)
                .pattern("W#W").pattern("WWW").pattern(" W ")
                .unlockedBy(getHasName(ModItems.STEEL_INGOT.get()), has(ModItems.STEEL_INGOT.get()))
                .save(writer);

        smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ModItems.STEEL_SHIELD, Etags.Items.INGOTS_DIARKRITE, ModItems.DIARKRITE_SHIELD);
        smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ModItems.STEEL_SHIELD, Etags.Items.INGOTS_ANTHEKTITE, ModItems.ANTHEKTITE_SHIELD);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.STEEL_BOW.get())
                .define('#', Etags.Items.INGOTS_STEEL).define('/', Tags.Items.RODS_WOODEN).define('S', Tags.Items.STRING)
                .pattern("#/S").pattern("/ S").pattern("#/S")
                .unlockedBy(getHasName(ModItems.STEEL_INGOT.get()), has(ModItems.STEEL_INGOT.get()))
                .save(writer);

        smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ModItems.STEEL_BOW, Etags.Items.INGOTS_DIARKRITE, ModItems.DIARKRITE_BOW);
        smithingCombatTransform(writer, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ModItems.STEEL_BOW, Etags.Items.INGOTS_ANTHEKTITE, ModItems.ANTHEKTITE_BOW);

        smithingCombatTransform(writer, ModItems.WEAPON_FRAGMENT, ModItems.DIARKRITE_SWORD, Items.SCULK_CATALYST, ModItems.DIARKRITE_CHARGE_BLADE);

        //Ingredients
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.CRUDE_STEEL.get())
                .requires(Ingredient.of(Tags.Items.INGOTS_IRON), 2)
                .requires(Ingredient.of(Tags.Items.RAW_MATERIALS_IRON), 1)
                .requires(Ingredient.of(ItemTags.COALS), 2)
                .unlockedBy("has_iron_ingot", has(net.minecraft.world.item.Items.IRON_INGOT))
                .save(writer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.DIARKRITE_INGOT.get())
                .requires(Ingredient.of(Etags.Items.ORES_ATELIS), 3)
                .requires(Ingredient.of(Etags.Items.INGOTS_STEEL), 2)
                .requires(Ingredient.of(Items.ECHO_SHARD), 2)
                .unlockedBy("has_remnant_ingot", has(ModItems.MOVCADIA_ESSENCE.get()))
                .save(writer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ANTHEKTITE_INGOT.get())
                .requires(Ingredient.of(Etags.Items.ORES_ATELIS), 3)
                .requires(Ingredient.of(Etags.Items.INGOTS_STEEL), 2)
                .requires(Ingredient.of(ModItems.MOVCADIA_ESSENCE.get()), 2)
                .unlockedBy("has_remnant_ingot", has(ModItems.MOVCADIA_ESSENCE.get()))
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

        templateDuplication(ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, net.minecraft.world.item.Items.COBBLED_DEEPSLATE).save(writer);

        steelRecycleSmelt(writer, Ingredient.of(Etags.Items.STEEL_RECYCLABLE), 0.5F, CriterionName.STEEL.name);
        steelRecycleBlast(writer, Ingredient.of(Etags.Items.STEEL_RECYCLABLE), 0.5F, CriterionName.STEEL.name);

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.CRUSHED_REMNANT.get()), RecipeCategory.MISC, ModItems.ATELIS_SCRAP.get(), 0.5F, 200)
                .unlockedBy("has_crushed_remnant", has(ModItems.CRUSHED_REMNANT.get()))
                .save(writer, this.name(getItemName(ModItems.ATELIS_SCRAP.get()) + "_from_smelting_crushed_remnant"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.CRUSHED_REMNANT.get()), RecipeCategory.MISC, ModItems.ATELIS_SCRAP.get(), 0.5F, 100)
                .unlockedBy("has_crushed_remnant", has(ModItems.CRUSHED_REMNANT.get()))
                .save(writer, this.name(getItemName(ModItems.ATELIS_SCRAP.get()) + "_from_blasting_crushed_remnant"));

        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.MOVCADIA_BERRIES.get()), RecipeCategory.MISC, ModItems.MOVCADIA_ESSENCE.get(), 0.5F, 150)
                .unlockedBy("has_movcadia_berries", has(ModItems.MOVCADIA_BERRIES.get()))
                .save(writer, this.name(getItemName(ModItems.MOVCADIA_ESSENCE.get()) + "_from_blasting"));

        //Building blocks
        wood(writer, ModItems.MOVCADIA_LOG, ModItems.MOVCADIA_WOOD);
        wood(writer, ModItems.STRIPPED_MOVCADIA_LOG, ModItems.STRIPPED_MOVCADIA_WOOD);
        planks(writer, Etags.Items.MOVCADIA_LOGS, ModItems.MOVCADIA_PLANKS);
        stairs(writer, ModItems.MOVCADIA_STAIRS, ModItems.MOVCADIA_PLANKS);
        slab(writer, RecipeCategory.BUILDING_BLOCKS, ModItems.MOVCADIA_SLAB.get(), ModItems.MOVCADIA_PLANKS.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_BARS.get(), 16)
                .define('#', ModItems.STEEL_INGOT.get())
                .pattern("###").pattern("###")
                .unlockedBy(getHasName(ModItems.STEEL_INGOT.get()), has(ModItems.STEEL_INGOT.get()))
                .save(writer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_TILES.get(), 16)
                .define('#', ModItems.STEEL_BLOCK.get())
                .pattern("##").pattern("##")
                .unlockedBy(getHasName(ModItems.STEEL_BLOCK.get()), has(ModItems.STEEL_INGOT.get()))
                .save(writer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_TILE_STAIR.get(), 4)
                .define('#', ModItems.STEEL_TILES.get())
                .pattern("#  ").pattern("## ").pattern("###")
                .unlockedBy(getHasName(ModItems.STEEL_BLOCK.get()), has(ModItems.STEEL_INGOT.get()))
                .save(writer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_TILE_SLAB.get(), 2)
                .define('#', ModItems.STEEL_TILES.get())
                .pattern("###")
                .unlockedBy(getHasName(ModItems.STEEL_BLOCK.get()), has(ModItems.STEEL_INGOT.get()))
                .save(writer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_BLOCK.get(), 1)
                .define('#', ModItems.STEEL_TILES.get())
                .pattern("##").pattern("##")
                .unlockedBy(getHasName(ModItems.STEEL_TILES.get()), has(ModItems.STEEL_BLOCK.get()))
                .save(writer, getHasName(ModItems.STEEL_BLOCK.get()) + "_from_steel_tiles");

        fence(writer, ModItems.MOVCADIA_FENCE, ModItems.MOVCADIA_PLANKS);
        fenceGate(writer, ModItems.MOVCADIA_FENCE_GATE, ModItems.MOVCADIA_PLANKS);

        door(writer, ModItems.MOVCADIA_DOOR, ModItems.MOVCADIA_PLANKS);
        trapdoor(writer, ModItems.MOVCADIA_TRAPDOOR, ModItems.MOVCADIA_PLANKS);

        pressurePlate(writer, ModItems.MOVCADIA_PRESSURE_PLATE.get(), ModItems.MOVCADIA_PLANKS.get());
        button(writer, ModItems.MOVCADIA_BUTTON, ModItems.MOVCADIA_PLANKS);

        sign(writer, ModItems.MOVCADIA_SIGN, ModItems.MOVCADIA_PLANKS);
        hangingSign(writer, ModItems.MOVCADIA_HANGING_SIGN.get(), ModItems.MOVCADIA_PLANKS.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STURDY_MOVCADIA_SIGN.get())
                .define('#', ModItems.MOVCADIA_LOG.get()).define('I', ModItems.MOVCADIA_PLANKS.get()).define('/', net.minecraft.world.item.Items.STICK)
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

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.GLISTERING_MOVCADIA_BERRIES.get())
                .define('#', ModItems.MOVCADIA_BERRIES.get())
                .define('/', Items.GOLD_NUGGET)
                .pattern("///").pattern("/#/").pattern("///")
                .unlockedBy(getHasName(ModItems.MOVCADIA_BERRIES.get()), has(Items.GOLD_INGOT))
                .save(writer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.REINFORCED_PLATING_GOLEM_UPGRADE.get())
                .define('#', ModItems.STEEL_BLOCK.get())
                .define('/', ModItems.STEEL_INGOT.get())
                .define('@', ModItems.STEEL_CHESTPLATE.get())
                .pattern("#/#").pattern("/#/").pattern("/@/")
                .unlockedBy(getHasName(ModItems.STEEL_BLOCK.get()), has(ModItems.STEEL_CHESTPLATE.get()))
                .save(writer);
    }
}
