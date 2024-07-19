package net.nokunami.elementus.datagen.providers;

import com.google.common.collect.ImmutableList;
import io.redspace.ironsspellbooks.registries.ItemRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.registries.ForgeRegistries;
import net.nokunami.elementus.common.registry.ModBlocks;
import net.nokunami.elementus.common.registry.ModItems;
import nl.sniffiandros.sniffsweapons.reg.ItemReg;
import vectorwing.farmersdelight.common.crafting.ingredient.ToolActionIngredient;
import vectorwing.farmersdelight.data.builder.CuttingBoardRecipeBuilder;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ModRecipeProvider extends RecipeProvider {
    protected final String id;

    public ModRecipeProvider(PackOutput output, String id) {
        super(output);
        this.id = id;
    }

    protected ResourceLocation name(String name) {
        return new ResourceLocation(this.id, name);
    }

    public static final ImmutableList<ItemLike> STEEL_SMELTABLES = ImmutableList.of(ModItems.CRUDE_STEEL.get());
    public static final ImmutableList<ItemLike> ATELIS_SMELTABLES = ImmutableList.of(ModBlocks.REMNANT.get());

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> writer) {
    }

    protected void smithingCombatTransform(Consumer<FinishedRecipe> consumer, Supplier<? extends Item> template, Supplier<? extends Item> base, TagKey<Item> addition, Supplier<? extends Item> result) {
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
        SimpleCookingRecipeBuilder.smelting(ingredient, RecipeCategory.MISC, ModItems.STEEL_SCRAP.get(), experience, 200)
                .unlockedBy(critName, has(ModItems.STEEL_INGOT.get()))
                .save(consumer, this.name(getItemName(ModItems.STEEL_SCRAP.get()) + "_from_smelting"));
    }
    protected void steelRecycleBlast(Consumer<FinishedRecipe> consumer, Ingredient ingredient, float experience, String critName) {
        SimpleCookingRecipeBuilder.blasting(ingredient, RecipeCategory.MISC, ModItems.STEEL_SCRAP.get(), experience, 100)
                .unlockedBy(critName, has(ModItems.STEEL_INGOT.get()))
                .save(consumer, this.name(getItemName(ModItems.STEEL_SCRAP.get()) + "_from_blasting"));
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

    //Farmer's Delight
    protected static void stripLogForBark(Consumer<FinishedRecipe> consumer, Supplier<? extends ItemLike> log, Supplier<? extends ItemLike> strippedLog) {
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(log.get()), new ToolActionIngredient(ToolActions.AXE_STRIP), strippedLog.get()).addResult(ModItems.MOVCADIA_BARK.get()).addSound(ForgeRegistries.SOUND_EVENTS.getKey(SoundEvents.AXE_STRIP).toString()).build(consumer);
    }

    //ISS ARMOR
    protected ShapedRecipeBuilder mageHelmet(Supplier<? extends ArmorItem> helmet, Supplier<? extends Item> material) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, helmet.get())
                .define('M', material.get())
                .define('C', ItemRegistry.MAGIC_CLOTH.get())
                .pattern("CCC")
                .pattern("CMC")
                .unlockedBy(getHasName(material.get()), has(material.get()));
    }
    protected ShapedRecipeBuilder mageChestplate(Supplier<? extends ArmorItem> helmet, Supplier<? extends Item> material) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, helmet.get())
                .define('M', material.get())
                .define('C', ItemRegistry.MAGIC_CLOTH.get())
                .pattern("CMC")
                .pattern("CCC")
                .pattern("CCC")
                .unlockedBy(getHasName(material.get()), has(material.get()));
    }
    protected ShapedRecipeBuilder mageLeggings(Supplier<? extends ArmorItem> helmet, Supplier<? extends Item> material) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, helmet.get())
                .define('M', material.get())
                .define('C', ItemRegistry.MAGIC_CLOTH.get())
                .pattern("CCC")
                .pattern("CMC")
                .pattern("C C")
                .unlockedBy(getHasName(material.get()), has(material.get()));
    }
    protected ShapedRecipeBuilder mageBoots(Supplier<? extends ArmorItem> helmet, Supplier<? extends Item> material) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, helmet.get())
                .define('M', material.get())
                .define('C', ItemRegistry.MAGIC_CLOTH.get())
                .pattern("C C")
                .pattern("CMC")
                .unlockedBy(getHasName(material.get()), has(material.get()));
    }

    //Paxel
    protected void makePaxel(Consumer<FinishedRecipe> consumer, Supplier<? extends Item> result, Supplier<? extends Item> sword, Supplier<? extends Item> shovel, Supplier<? extends Item> pickaxe, Supplier<? extends Item> axe, Supplier<? extends Item> hoe) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result.get())
                .define('1', sword.get()).define('2', shovel.get()).define('3', pickaxe.get())
                .define('4', axe.get()).define('5', hoe.get()).define('/', Tags.Items.RODS_WOODEN)
                .pattern("342").pattern("1/5").pattern(" / ")
                .unlockedBy(getHasName(ModItems.STEEL_INGOT.get()), has(ModItems.STEEL_INGOT.get()))
                .save(consumer);
    }
    protected void makePaxelUpgradeKit(Consumer<FinishedRecipe> consumer, Supplier<? extends Item> result, Supplier<? extends Item> material) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result.get())
                .define('#', material.get()).define('/', Tags.Items.RODS_WOODEN)
                .pattern("#/#").pattern("/#/").pattern("#/#")
                .unlockedBy(getHasName(material.get()), has(material.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, material.get(), 5)
                .requires(result.get()).unlockedBy(getHasName(result.get()), has(result.get()))
                .save(consumer, this.name(getItemName(material.get()) + "_from_kit"));
    }

    //Simply Swords
    protected void makeChakram(Consumer<FinishedRecipe> consumer, Supplier<? extends Item> result, TagKey<Item> material, Supplier<? extends Item> nugget, Supplier<? extends Item> critItem) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result.get())
                .define('#', material).define('N', nugget.get()).define('/', Tags.Items.RODS_WOODEN)
                .pattern("N#N").pattern("# #").pattern("N/N")
                .unlockedBy(getHasName(critItem.get()), has(material))
                .save(consumer);
    }
    protected void makeClaymore(Consumer<FinishedRecipe> consumer, Supplier<? extends Item> result, TagKey<Item> material, Supplier<? extends Item> nugget, Supplier<? extends Item> critItem) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result.get())
                .define('#', material).define('N', nugget.get()) .define('/', Tags.Items.RODS_WOODEN)
                .pattern(" N#").pattern("N#N").pattern("/N ")
                .unlockedBy(getHasName(critItem.get()), has(material))
                .save(consumer);
    }
    protected void makeCutlass(Consumer<FinishedRecipe> consumer, Supplier<? extends Item> result, TagKey<Item> material, Supplier<? extends Item> nugget, Supplier<? extends Item> critItem) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result.get())
                .define('#', material).define('N', nugget.get()) .define('/', Tags.Items.RODS_WOODEN)
                .pattern(" N").pattern("##").pattern("/ ")
                .unlockedBy(getHasName(critItem.get()), has(material))
                .save(consumer);
    }
    protected void makeGlaive(Consumer<FinishedRecipe> consumer, Supplier<? extends Item> result, TagKey<Item> material, Supplier<? extends Item> critItem) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result.get())
                .define('#', material).define('/', Tags.Items.RODS_WOODEN)
                .pattern("  #").pattern(" /#").pattern("/  ")
                .unlockedBy(getHasName(critItem.get()), has(material))
                .save(consumer);
    }
    protected void makeGreataxe(Consumer<FinishedRecipe> consumer, Supplier<? extends Item> result, TagKey<Item> material, Supplier<? extends Item> nugget, Supplier<? extends Item> critItem) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result.get())
                .define('#', material).define('N', nugget.get()) .define('/', Tags.Items.RODS_WOODEN)
                .pattern("###").pattern("N/N").pattern(" / ")
                .unlockedBy(getHasName(critItem.get()), has(material))
                .save(consumer);
    }
    protected void makeGreathammer(Consumer<FinishedRecipe> consumer, Supplier<? extends Item> result, TagKey<Item> material, Supplier<? extends Item> nugget, Supplier<? extends Item> critItem) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result.get())
                .define('#', material).define('N', nugget.get()) .define('/', Tags.Items.RODS_WOODEN)
                .pattern("###").pattern("NNN").pattern(" / ")
                .unlockedBy(getHasName(critItem.get()), has(material))
                .save(consumer);
    }
    protected void makeHalberd(Consumer<FinishedRecipe> consumer, Supplier<? extends Item> result, TagKey<Item> material, Supplier<? extends Item> nugget, Supplier<? extends Item> critItem) {
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result.get())
                    .define('#', material).define('N', nugget.get()).define('/', Tags.Items.RODS_WOODEN)
                    .pattern(" #N").pattern("#/#").pattern("/  ")
                    .unlockedBy(getHasName(critItem.get()), has(material))
                .save(consumer);
    }
    protected void makeKatana(Consumer<FinishedRecipe> consumer, Supplier<? extends Item> result, TagKey<Item> material, Supplier<? extends Item> critItem) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result.get())
                .define('#', material).define('/', Tags.Items.RODS_WOODEN)
                .pattern("   ").pattern("##/").pattern("   ")
                .unlockedBy(getHasName(critItem.get()), has(material))
                .save(consumer);
    }
    protected void makeLongSword(Consumer<FinishedRecipe> consumer, Supplier<? extends Item> result, TagKey<Item> material, Supplier<? extends Item> critItem) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result.get())
                .define('#', material).define('/', Tags.Items.RODS_WOODEN)
                .pattern("/  ").pattern(" # ").pattern("  #")
                .unlockedBy(getHasName(critItem.get()), has(material))
                .save(consumer);
    }
    protected void makeRapier(Consumer<FinishedRecipe> consumer, Supplier<? extends Item> result, TagKey<Item> material, Supplier<? extends Item> critItem) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result.get())
                .define('#', material).define('/', Tags.Items.RODS_WOODEN)
                .pattern("  #").pattern(" # ").pattern("/  ")
                .unlockedBy(getHasName(critItem.get()), has(material))
                .save(consumer);
    }
    protected void makeSai(Consumer<FinishedRecipe> consumer, Supplier<? extends Item> result, TagKey<Item> material, Supplier<? extends Item> critItem) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result.get())
                .define('#', material).define('/', Tags.Items.RODS_WOODEN)
                .pattern("/ ").pattern(" #").pattern("  ")
                .unlockedBy(getHasName(critItem.get()), has(material))
                .save(consumer);
    }
    protected void makeScythe(Consumer<FinishedRecipe> consumer, Supplier<? extends Item> result, TagKey<Item> material, Supplier<? extends Item> critItem) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result.get())
                .define('#', material).define('/', Tags.Items.RODS_WOODEN)
                .pattern("##/").pattern("#/ ").pattern("/  ")
                .unlockedBy(getHasName(critItem.get()), has(material))
                .save(consumer);
    }
    protected void makeSpear(Consumer<FinishedRecipe> consumer, Supplier<? extends Item> result, TagKey<Item> material, Supplier<? extends Item> critItem) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result.get())
                .define('#', material).define('/', Tags.Items.RODS_WOODEN)
                .pattern("  #").pattern(" / ").pattern("/  ")
                .unlockedBy(getHasName(critItem.get()), has(material))
                .save(consumer);
    }
    protected void makeTwinblade(Consumer<FinishedRecipe> consumer, Supplier<? extends Item> result, TagKey<Item> material, Supplier<? extends Item> critItem) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result.get())
                .define('#', material).define('/', Tags.Items.RODS_WOODEN)
                .pattern("  #").pattern(" / ").pattern("#  ")
                .unlockedBy(getHasName(critItem.get()), has(material))
                .save(consumer);
    }
    protected void makeWarglaive(Consumer<FinishedRecipe> consumer, Supplier<? extends Item> result, TagKey<Item> material, Supplier<? extends Item> nugget, Supplier<? extends Item> critItem) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result.get())
                .define('#', material).define('N', nugget.get()) .define('/', Tags.Items.RODS_WOODEN)
                .pattern(" N ").pattern("#/#").pattern("   ")
                .unlockedBy(getHasName(critItem.get()), has(material))
                .save(consumer);
    }

    //Sniff's Weapons
    protected void makeGreatAxe(Consumer<FinishedRecipe> consumer, Supplier<? extends Item> result, TagKey<Item> material, Supplier<? extends Item> item) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result.get())
                .define('#', material).define('S', ItemReg.BINDING_STRING.get()).define('/', item.get())
                .pattern("###").pattern("#S ").pattern("#/ ")
                .unlockedBy(getHasName(item.get()), has(item.get()))
                .save(consumer);
    }
    protected void makeGreatPickaxe(Consumer<FinishedRecipe> consumer, Supplier<? extends Item> result, TagKey<Item> material, Supplier<? extends Item> item) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result.get())
                .define('#', material).define('S', ItemReg.BINDING_STRING.get()).define('/', item.get())
                .pattern("###").pattern("#S#").pattern(" / ")
                .unlockedBy(getHasName(item.get()), has(item.get()))
                .save(consumer);
    }
    protected void makeGreatSword(Consumer<FinishedRecipe> consumer, Supplier<? extends Item> result, TagKey<Item> material, Supplier<? extends Item> item) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result.get())
                .define('#', material).define('S', ItemReg.BINDING_STRING.get()).define('/', item.get())
                .pattern(" ##").pattern("#S#").pattern("/# ")
                .unlockedBy(getHasName(item.get()), has(item.get()))
                .save(consumer);
    }
    protected void makeNaginata(Consumer<FinishedRecipe> consumer, Supplier<? extends Item> result, TagKey<Item> material, Supplier<? extends Item> item) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result.get())
                .define('#', material).define('S', ItemReg.BINDING_STRING.get()).define('/', item.get())
                .pattern(" #S").pattern("#/ ").pattern("/  ")
                .unlockedBy(getHasName(item.get()), has(item.get()))
                .save(consumer);
    }

    protected void smithingSniffWeaponConvert(Consumer<FinishedRecipe> consumer, Supplier<? extends Item> template, Supplier<? extends Item> base, ItemLike addition, Supplier<? extends Item> result) {
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(template.get()), Ingredient.of(base.get()),
                        Ingredient.of(addition), RecipeCategory.COMBAT, result.get())
                .unlocks(getHasName(base.get()), has(base.get()))
                .save(consumer, this.name(getItemName(result.get()) + "_convert_smithing"));
    }

    //Advanced Netherite
    public static void ingotBlockRecipes(Consumer<FinishedRecipe> consumer, ItemLike ingredient, Block blockResult, ItemLike ingotIngredient, TagKey<Item> upgradeIngredient, Item itemResult) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, blockResult).define('#', ingredient).pattern("###").pattern("###").pattern("###").unlockedBy("has_" + ingredient.toString(), has(ingredient)).save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ingredient, 9).requires(blockResult).unlockedBy("has_" + blockResult.toString(), has(blockResult)).save(consumer, new ResourceLocation(ForgeRegistries.BLOCKS.getKey(blockResult).getNamespace(), ingredient.toString() + "_from_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, itemResult).requires(ingotIngredient).requires(upgradeIngredient).requires(upgradeIngredient).requires(upgradeIngredient).requires(upgradeIngredient).unlockedBy("has_" + upgradeIngredient.toString(), has(upgradeIngredient)).save(consumer, new ResourceLocation(ForgeRegistries.ITEMS.getKey(itemResult).getNamespace(), itemResult.toString()));
    }

    protected void makeAdvancedNetheriteTools(Consumer<FinishedRecipe> consumer, Supplier<? extends Item> baseSword, Supplier<? extends Item> baseShovel, Supplier<? extends Item> basePickaxe, Supplier<? extends Item> baseAxe, Supplier<? extends Item> baseHoe, Supplier<? extends Item> addition, Supplier<? extends Item> resultSword, Supplier<? extends Item> resultShovel, Supplier<? extends Item> resultPickaxe, Supplier<? extends Item> resultAxe, Supplier<? extends Item> resultHoe) {
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE.get()), Ingredient.of(baseSword.get()), Ingredient.of(addition.get()), RecipeCategory.COMBAT, resultSword.get()).unlocks(getHasName(baseSword.get()), has(baseSword.get())).save(consumer, this.name(getItemName(resultSword.get()) + "_smithing"));
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE.get()), Ingredient.of(baseShovel.get()), Ingredient.of(addition.get()), RecipeCategory.COMBAT, resultShovel.get()).unlocks(getHasName(baseShovel.get()), has(baseShovel.get())).save(consumer, this.name(getItemName(resultShovel.get()) + "_smithing"));
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE.get()), Ingredient.of(basePickaxe.get()), Ingredient.of(addition.get()), RecipeCategory.COMBAT, resultPickaxe.get()).unlocks(getHasName(basePickaxe.get()), has(basePickaxe.get())).save(consumer, this.name(getItemName(resultPickaxe.get()) + "_smithing"));
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE.get()), Ingredient.of(baseAxe.get()), Ingredient.of(addition.get()), RecipeCategory.COMBAT, resultAxe.get()).unlocks(getHasName(baseAxe.get()), has(baseAxe.get())).save(consumer, this.name(getItemName(resultAxe.get()) + "_smithing"));
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE.get()), Ingredient.of(baseHoe.get()), Ingredient.of(addition.get()), RecipeCategory.COMBAT, resultHoe.get()).unlocks(getHasName(baseHoe.get()), has(baseHoe.get())).save(consumer, this.name(getItemName(resultHoe.get()) + "_smithing"));
    }

    protected void makeAdvancedNetheriteArmors(Consumer<FinishedRecipe> consumer, Supplier<? extends Item> baseHelmet, Supplier<? extends Item> baseChestplate, Supplier<? extends Item> baseLeggings, Supplier<? extends Item> baseBoots, Supplier<? extends Item> addition, Supplier<? extends Item> resultHelmet, Supplier<? extends Item> resultChestplate, Supplier<? extends Item> resultLeggings, Supplier<? extends Item> resultBoots) {
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE.get()), Ingredient.of(baseHelmet.get()), Ingredient.of(addition.get()), RecipeCategory.COMBAT, resultHelmet.get()).unlocks(getHasName(baseHelmet.get()), has(baseHelmet.get())).save(consumer, this.name(getItemName(resultHelmet.get()) + "_smithing"));
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE.get()), Ingredient.of(baseChestplate.get()), Ingredient.of(addition.get()), RecipeCategory.COMBAT, resultChestplate.get()).unlocks(getHasName(baseChestplate.get()), has(baseChestplate.get())).save(consumer, this.name(getItemName(resultChestplate.get()) + "_smithing"));
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE.get()), Ingredient.of(baseLeggings.get()), Ingredient.of(addition.get()), RecipeCategory.COMBAT, resultLeggings.get()).unlocks(getHasName(baseLeggings.get()), has(baseLeggings.get())).save(consumer, this.name(getItemName(resultLeggings.get()) + "_smithing"));
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE.get()), Ingredient.of(baseBoots.get()), Ingredient.of(addition.get()), RecipeCategory.COMBAT, resultBoots.get()).unlocks(getHasName(baseBoots.get()), has(baseBoots.get())).save(consumer, this.name(getItemName(resultBoots.get()) + "_smithing"));
    }

}

