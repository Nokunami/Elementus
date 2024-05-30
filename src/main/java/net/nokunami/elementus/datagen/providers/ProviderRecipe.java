package net.nokunami.elementus.datagen.providers;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.nokunami.elementus.registry.ModBlocks;
import net.nokunami.elementus.registry.ModItems;

import java.util.List;
import java.util.function.Consumer;

public class ProviderRecipe extends RecipeProvider implements IConditionBuilder {
    public ProviderRecipe(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> writer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.STEEL_BLOCK.get())
            .pattern("III")
            .pattern("III")
            .pattern("III")
            .define('I', ModItems.STEEL_INGOT.get())
            .unlockedBy(getHasName(ModItems.STEEL_INGOT.get()), has(ModItems.STEEL_INGOT.get()))
            .save(writer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ANTHEKTITE_BLOCK.get())
            .pattern("III")
            .pattern("III")
            .pattern("III")
            .define('I', ModItems.ANTHEKTITE_INGOT.get())
            .unlockedBy(getHasName(ModItems.ANTHEKTITE_INGOT.get()), has(ModItems.ANTHEKTITE_INGOT.get()))
            .save(writer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.DIARKRITE_BLOCK.get())
            .pattern("III")
            .pattern("III")
            .pattern("III")
            .define('I', ModItems.DIARKRITE_INGOT.get())
            .unlockedBy(getHasName(ModItems.DIARKRITE_INGOT.get()), has(ModItems.DIARKRITE_INGOT.get()))
            .save(writer);

//        if (ModList.get().isLoaded("farmersdelight")) {
//            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, FDItemsRegistry.STEEL_KNIFE.get())
//                    .pattern("m")
//                    .pattern("s")
//                    .define('m', ModItems.STEEL_INGOT.get()).define('s', Items.STICK)
//                    .unlockedBy(getHasName(FDItemsRegistry.STEEL_KNIFE.get()), has(ModItems.STEEL_INGOT.get()))
//                    .save(writer);
//            SmithingTransformRecipeBuilder.smithing(
//                            Ingredient.of(new Item[]{ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE.get()}),
//                            Ingredient.of(new Item[]{FDItemsRegistry.STEEL_KNIFE.get()}),
//                            Ingredient.of(new Item[]{ModItems.ANTHEKTITE_INGOT.get()}), RecipeCategory.COMBAT, FDItemsRegistry.ANTHEKTITE_KNIFE.get())
//                    .unlocks("has_anthektite_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(new ItemLike[]{ModItems.ANTHEKTITE_INGOT.get()}))
//                    .save(writer, "elementus:anthektite_knife_smithing_advancement");
//
//            SmithingTransformRecipeBuilder.smithing(
//                            Ingredient.of(new Item[]{ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE.get()}),
//                            Ingredient.of(new Item[]{FDItemsRegistry.STEEL_KNIFE.get()}),
//                            Ingredient.of(new Item[]{ModItems.DIARKRITE_INGOT.get()}), RecipeCategory.COMBAT, FDItemsRegistry.DIARKRITE_KNIFE.get())
//                    .unlocks("has_diarkrite_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(new ItemLike[]{ModItems.DIARKRITE_INGOT.get()}))
//                    .save(writer, "elementus:diarkrite_knife_smithing_advancement");
//        }
    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }
}
