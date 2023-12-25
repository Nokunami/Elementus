package net.nokunami.elementus.datagen;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.fml.ModList;
import net.nokunami.elementus.Compat.farmersdelight.FDItemsRegistry;
import net.nokunami.elementus.registry.BlocksRegistry;
import net.nokunami.elementus.registry.ItemsRegistry;

import java.util.List;
import java.util.function.Consumer;

public class ProviderRecipe extends RecipeProvider implements IConditionBuilder {
    public ProviderRecipe(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> writer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlocksRegistry.STEEL_BLOCK.get())
            .pattern("III")
            .pattern("III")
            .pattern("III")
            .define('I', ItemsRegistry.STEEL_INGOT.get())
            .unlockedBy(getHasName(ItemsRegistry.STEEL_INGOT.get()), has(ItemsRegistry.STEEL_INGOT.get()))
            .save(writer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlocksRegistry.ANTHEKTITE_BLOCK.get())
            .pattern("III")
            .pattern("III")
            .pattern("III")
            .define('I', ItemsRegistry.ANTHEKTITE_INGOT.get())
            .unlockedBy(getHasName(ItemsRegistry.ANTHEKTITE_INGOT.get()), has(ItemsRegistry.ANTHEKTITE_INGOT.get()))
            .save(writer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlocksRegistry.DIARKRITE_BLOCK.get())
            .pattern("III")
            .pattern("III")
            .pattern("III")
            .define('I', ItemsRegistry.DIARKRITE_INGOT.get())
            .unlockedBy(getHasName(ItemsRegistry.DIARKRITE_INGOT.get()), has(ItemsRegistry.DIARKRITE_INGOT.get()))
            .save(writer);

        if (ModList.get().isLoaded("farmersdelight")) {
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, FDItemsRegistry.STEEL_KNIFE.get())
                    .pattern("m")
                    .pattern("s")
                    .define('m', ItemsRegistry.STEEL_INGOT.get()).define('s', Items.STICK)
                    .unlockedBy(getHasName(FDItemsRegistry.STEEL_KNIFE.get()), has(ItemsRegistry.STEEL_INGOT.get()))
                    .save(writer);
            SmithingTransformRecipeBuilder.smithing(
                            Ingredient.of(new Item[]{ItemsRegistry.ANTHEKTITE_UPGRADE_SMITHING_TEMPLATE.get()}),
                            Ingredient.of(new Item[]{FDItemsRegistry.STEEL_KNIFE.get()}),
                            Ingredient.of(new Item[]{ItemsRegistry.ANTHEKTITE_INGOT.get()}), RecipeCategory.COMBAT, FDItemsRegistry.ANTHEKTITE_KNIFE.get())
                    .unlocks("has_anthektite_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(new ItemLike[]{ItemsRegistry.ANTHEKTITE_INGOT.get()}))
                    .save(writer, "elementus:anthektite_knife_smithing_advancement");

            SmithingTransformRecipeBuilder.smithing(
                            Ingredient.of(new Item[]{ItemsRegistry.DIARKRITE_UPGRADE_SMITHING_TEMPLATE.get()}),
                            Ingredient.of(new Item[]{FDItemsRegistry.ANTHEKTITE_KNIFE.get()}),
                            Ingredient.of(new Item[]{ItemsRegistry.DIARKRITE_INGOT.get()}), RecipeCategory.COMBAT, FDItemsRegistry.DIARKRITE_KNIFE.get())
                    .unlocks("has_diarkrite_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(new ItemLike[]{ItemsRegistry.DIARKRITE_INGOT.get()}))
                    .save(writer, "elementus:diarkrite_knife_smithing_advancement");
        }
    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }
}
