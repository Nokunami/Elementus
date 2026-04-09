package net.nokunami.elementus.datagen.providers;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.nokunami.elementus.common.registry.EItems;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    protected final String id;

    public ModRecipeProvider(PackOutput output, String id) {
        super(output);
        this.id = id;
    }

    protected ResourceLocation name(String name) {
        return new ResourceLocation(this.id, name);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> writer) {
    }

    protected void smithingCombatTransform(Consumer<FinishedRecipe> consumer, Supplier<? extends Item> template, Supplier<? extends Item> base, TagKey<Item> addition, Supplier<? extends Item> result) {
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(template.get()), Ingredient.of(base.get()),
                        Ingredient.of(addition), RecipeCategory.COMBAT, result.get())
                .unlocks(getHasName(result.get()), has(addition))
                .save(consumer, this.name(getItemName(result.get()) + "_smithing"));
    }

    protected void smithingCombatTransform(Consumer<FinishedRecipe> consumer, Supplier<? extends Item> template, Supplier<? extends Item> base, Item addition, Supplier<? extends Item> result) {
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(template.get()), Ingredient.of(base.get()),
                        Ingredient.of(addition), RecipeCategory.COMBAT, result.get())
                .unlocks(getHasName(result.get()), has(addition))
                .save(consumer, this.name(getItemName(result.get()) + "_smithing"));
    }

    protected void smithingCombatTransform(Consumer<FinishedRecipe> consumer, Supplier<? extends Item> template, Supplier<? extends Item> base, Supplier<? extends Item> addition, Supplier<? extends Item> result) {
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(template.get()), Ingredient.of(base.get()),
                        Ingredient.of(addition.get()), RecipeCategory.COMBAT, result.get())
                .unlocks(getHasName(result.get()), has(addition.get()))
                .save(consumer, this.name(getItemName(result.get()) + "_smithing"));
    }

    protected void smithingToolsTransform(Consumer<FinishedRecipe> consumer, Supplier<? extends Item> template, Supplier<? extends Item> base, TagKey<Item> addition, Supplier<? extends Item> result) {
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(template.get()), Ingredient.of(base.get()),
                        Ingredient.of(addition), RecipeCategory.TOOLS, result.get())
                .unlocks(getHasName(result.get()), has(addition))
                .save(consumer, this.name(getItemName(result.get()) + "_smithing"));
    }

    protected ShapedRecipeBuilder helmetRecipe(Supplier<? extends Item> result, Supplier<? extends Item> material) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result.get())
                .define('#', material.get())
                .pattern("###")
                .pattern("# #")
                .unlockedBy(getHasName(material.get()), has(material.get()));
    }
    protected ShapedRecipeBuilder chestplateRecipe(Supplier<? extends Item> result, Supplier<? extends Item> material) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result.get())
                .define('#', material.get())
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .unlockedBy(getHasName(material.get()), has(material.get()));
    }
    protected ShapedRecipeBuilder leggingsRecipe(Supplier<? extends Item> result, Supplier<? extends Item> material) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result.get())
                .define('#', material.get())
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .unlockedBy(getHasName(material.get()), has(material.get()));
    }
    protected ShapedRecipeBuilder bootsRecipe(Supplier<? extends Item> result, Supplier<? extends Item> material) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result.get())
                .define('#', material.get())
                .pattern("# #")
                .pattern("# #")
                .unlockedBy(getHasName(material.get()), has(material.get()));
    }

    protected ShapedRecipeBuilder helmetRecipe(Supplier<? extends Item> result, TagKey<Item> material, String critName) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result.get())
                .define('#', material)
                .pattern("###")
                .pattern("# #")
                .unlockedBy(critName, has(material));
    }
    protected ShapedRecipeBuilder chestplateRecipe(Supplier<? extends Item> result, TagKey<Item> material, String critName) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result.get())
                .define('#', material)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .unlockedBy(critName, has(material));
    }
    protected ShapedRecipeBuilder leggingsRecipe(Supplier<? extends Item> result, TagKey<Item> material, String critName) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result.get())
                .define('#', material)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .unlockedBy(critName, has(material));
    }
    protected ShapedRecipeBuilder bootsRecipe(Supplier<? extends Item> result, TagKey<Item> material, String critName) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result.get())
                .define('#', material)
                .pattern("# #")
                .pattern("# #")
                .unlockedBy(critName, has(material));
    }


    protected ShapedRecipeBuilder swordRecipe(Supplier<? extends Item> sword, Supplier<? extends Item> material) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, sword.get())
                .define('#', material.get())
                .define('/', Tags.Items.RODS_WOODEN)
                .pattern("#")
                .pattern("#")
                .pattern("/")
                .unlockedBy(getHasName(material.get()), has(material.get()));
    }
    protected ShapedRecipeBuilder shovelRecipe(Supplier<? extends Item> shovel, Supplier<? extends Item> material) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, shovel.get())
                .define('#', material.get())
                .define('/', Tags.Items.RODS_WOODEN)
                .pattern("#")
                .pattern("/")
                .pattern("/")
                .unlockedBy(getHasName(material.get()), has(material.get()));
    }
    protected ShapedRecipeBuilder pickaxeRecipe(Supplier<? extends Item> pickaxe, Supplier<? extends Item> material) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, pickaxe.get())
                .define('#', material.get())
                .define('/', Tags.Items.RODS_WOODEN)
                .pattern("###")
                .pattern(" / ")
                .pattern(" / ")
                .unlockedBy(getHasName(material.get()), has(material.get()));
    }
    protected ShapedRecipeBuilder axeRecipe(Supplier<? extends Item> axe, Supplier<? extends Item> material) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, axe.get())
                .define('#', material.get())
                .define('/', Tags.Items.RODS_WOODEN)
                .pattern("##")
                .pattern("#/")
                .pattern(" /")
                .unlockedBy(getHasName(material.get()), has(material.get()));
    }
    protected ShapedRecipeBuilder hoeRecipe(Supplier<? extends Item> hoe, Supplier<? extends Item> material) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, hoe.get())
                .define('#', material.get())
                .define('/', Tags.Items.RODS_WOODEN)
                .pattern("##")
                .pattern(" /")
                .pattern(" /")
                .unlockedBy(getHasName(material.get()), has(material.get()));
    }

    protected ShapedRecipeBuilder swordRecipe(Supplier<? extends Item> sword, TagKey<Item> material, String critName) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, sword.get())
                .define('#', material)
                .define('/', Tags.Items.RODS_WOODEN)
                .pattern("#")
                .pattern("#")
                .pattern("/")
                .unlockedBy(critName, has(material));
    }
    protected ShapedRecipeBuilder shovelRecipe(Supplier<? extends Item> shovel, TagKey<Item> material, String critName) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, shovel.get())
                .define('#', material)
                .define('/', Tags.Items.RODS_WOODEN)
                .pattern("#")
                .pattern("/")
                .pattern("/")
                .unlockedBy(critName, has(material));
    }
    protected ShapedRecipeBuilder pickaxeRecipe(Supplier<? extends Item> pickaxe, TagKey<Item> material, String critName) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, pickaxe.get())
                .define('#', material)
                .define('/', Tags.Items.RODS_WOODEN)
                .pattern("###")
                .pattern(" / ")
                .pattern(" / ")
                .unlockedBy(critName, has(material));
    }
    protected ShapedRecipeBuilder axeRecipe(Supplier<? extends Item> axe, TagKey<Item> material, String critName) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, axe.get())
                .define('#', material)
                .define('/', Tags.Items.RODS_WOODEN)
                .pattern("##")
                .pattern("#/")
                .pattern(" /")
                .unlockedBy(critName, has(material));
    }
    protected ShapedRecipeBuilder hoeRecipe(Supplier<? extends Item> hoe, TagKey<Item> material, String critName) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, hoe.get())
                .define('#', material)
                .define('/', Tags.Items.RODS_WOODEN)
                .pattern("##")
                .pattern(" /")
                .pattern(" /")
                .unlockedBy(critName, has(material));
    }

    protected ShapedRecipeBuilder templateDuplication(Supplier<? extends Item> template, ItemLike material) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.MISC, template.get(), 2)
                .define('#', Items.DIAMOND)
                .define('C', material)
                .define('S', template.get())
                .pattern("#S#")
                .pattern("#C#")
                .pattern("###")
                .unlockedBy(getHasName(template.get()), has(material));
    }

    protected void storageBlock(Consumer<FinishedRecipe> consumer, RecipeCategory itemCategory, ItemLike item, RecipeCategory blockCategory, ItemLike block, String itemRecipeName, String itemGroup) {
        ShapelessRecipeBuilder.shapeless(itemCategory, item, 9).requires(block).group(itemGroup).unlockedBy(getHasName(block), has(block)).save(consumer, this.name(itemRecipeName));
        ShapedRecipeBuilder.shaped(blockCategory, block).define('#', item).pattern("###").pattern("###").pattern("###").unlockedBy(getHasName(item), has(item)).save(consumer, this.name(getSimpleRecipeName(block)));
    }
    protected void nuggetIngot(Consumer<FinishedRecipe> consumer, RecipeCategory itemCategory, ItemLike item, ItemLike nugget, String itemGroup, String itemRecipeName) {
        ShapelessRecipeBuilder.shapeless(itemCategory, nugget, 9).requires(item).unlockedBy(getHasName(item), has(item)).save(consumer, this.name(getSimpleRecipeName(nugget)));
        ShapedRecipeBuilder.shaped(itemCategory, item).define('#', nugget).pattern("###").pattern("###").pattern("###").group(itemGroup).unlockedBy(getHasName(item), has(item)).save(consumer, this.name(itemRecipeName));
    }

    protected void smeltingOreRecipe(Consumer<FinishedRecipe> consumer, ItemLike result, ItemLike ingredient, float experience) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient), RecipeCategory.MISC, result, experience, 200)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(consumer, this.name(getItemName(result) + "_from_smelting_" + getItemName(ingredient)));
    }
    protected void blastingOreRecipe(Consumer<FinishedRecipe> consumer, ItemLike result, ItemLike ingredient, float experience) {
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ingredient), RecipeCategory.MISC, result, experience, 100)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(consumer, this.name(getItemName(result) + "_from_blasting_" + getItemName(ingredient)));
    }

    protected void steelRecycleSmelt(Consumer<FinishedRecipe> consumer, Ingredient ingredient, float experience, String critName) {
        SimpleCookingRecipeBuilder.smelting(ingredient, RecipeCategory.MISC, EItems.ASTALITE_SCRAP.get(), experience, 200)
                .unlockedBy(critName, has(EItems.ASTALITE_INGOT.get()))
                .save(consumer, this.name(getItemName(EItems.ASTALITE_SCRAP.get()) + "_from_smelting"));
    }
    protected void steelRecycleBlast(Consumer<FinishedRecipe> consumer, Ingredient ingredient, float experience, String critName) {
        SimpleCookingRecipeBuilder.blasting(ingredient, RecipeCategory.MISC, EItems.ASTALITE_SCRAP.get(), experience, 100)
                .unlockedBy(critName, has(EItems.ASTALITE_INGOT.get()))
                .save(consumer, this.name(getItemName(EItems.ASTALITE_SCRAP.get()) + "_from_blasting"));
    }

    protected void wood(Consumer<FinishedRecipe> consumer, Supplier<? extends  Item> log, Supplier<? extends  Item> result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result.get(), 3)
                .define('#', log.get()).pattern("##").pattern("##")
                .unlockedBy(getHasName(result.get()), has(result.get())).save(consumer);
    }
    protected void planks(Consumer<FinishedRecipe> consumer, TagKey<Item> log, Supplier<? extends  Item> result) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, result.get(), 4).requires(log)
                .unlockedBy(getHasName(result.get()), has(result.get())).save(consumer);
    }
    protected void stairs(Consumer<FinishedRecipe> consumer, Supplier<? extends ItemLike> stairs, Supplier<? extends  Item> material) {
        stairBuilder(stairs.get(), Ingredient.of(material.get())).unlockedBy(getHasName(material.get()), has(material.get())).save(consumer);
    }

    protected void fence(Consumer<FinishedRecipe> consumer, Supplier<? extends ItemLike> fence, Supplier<? extends  Item> material) {
        fenceBuilder(fence.get(), Ingredient.of(material.get())).unlockedBy(getHasName(material.get()), has(material.get())).save(consumer);
    }
    protected void fenceGate(Consumer<FinishedRecipe> consumer, Supplier<? extends ItemLike> fenceGate, Supplier<? extends  Item> material) {
        fenceGateBuilder(fenceGate.get(), Ingredient.of(material.get())).unlockedBy(getHasName(material.get()), has(material.get())).save(consumer);
    }
    protected void door(Consumer<FinishedRecipe> consumer, Supplier<? extends ItemLike> door, Supplier<? extends  Item> material) {
        doorBuilder(door.get(), Ingredient.of(material.get())).unlockedBy(getHasName(material.get()), has(material.get())).save(consumer);
    }
    protected void trapdoor(Consumer<FinishedRecipe> consumer, Supplier<? extends ItemLike> trapdoor, Supplier<? extends  Item> material) {
        trapdoorBuilder(trapdoor.get(), Ingredient.of(material.get())).unlockedBy(getHasName(material.get()), has(material.get())).save(consumer);
    }
    protected void button(Consumer<FinishedRecipe> consumer, Supplier<? extends ItemLike> button, Supplier<? extends  Item> material) {
        buttonBuilder(button.get(), Ingredient.of(material.get())).unlockedBy(getHasName(material.get()), has(material.get())).save(consumer);
    }
    protected void sign(Consumer<FinishedRecipe> consumer, Supplier<? extends ItemLike> sign, Supplier<? extends  Item> material) {
        signBuilder(sign.get(), Ingredient.of(material.get())).unlockedBy(getHasName(material.get()), has(material.get())).save(consumer);
    }

    //Mod recipes
    protected void smithingCombatTransform(Consumer<FinishedRecipe> consumer, Supplier<? extends Item> template, Supplier<? extends Item> base, TagKey<Item> addition, Supplier<? extends Item> result, String critName, String modid) {
        ConditionalRecipe.builder().addCondition(and(modLoaded(modid))).addRecipe(c ->
                SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(template.get()),
                        Ingredient.of(base.get()),
                        Ingredient.of(addition),
                        RecipeCategory.TOOLS, result.get())
                .unlocks(critName, has(addition))
                .save(c, new ResourceLocation(getItemName(result.get()) + "_smithing")))
                .build(consumer, new ResourceLocation(getItemName(result.get()) + "_smithing"));
    }
    protected void smithingCombatTransform(Consumer<FinishedRecipe> consumer, Supplier<? extends Item> template, Supplier<? extends Item> base, Supplier<? extends Item> addition, Supplier<? extends Item> result, String critName, String modid) {
        ConditionalRecipe.builder().addCondition(and(modLoaded(modid))).addRecipe(c ->
                SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(template.get()),
                        Ingredient.of(base.get()),
                        Ingredient.of(addition.get()),
                        RecipeCategory.TOOLS, result.get())
                .unlocks(critName, has(addition.get()))
                .save(c, new ResourceLocation(getItemName(result.get()) + "_smithing")))
                .build(consumer, new ResourceLocation(getItemName(result.get()) + "_smithing"));
    }
}

