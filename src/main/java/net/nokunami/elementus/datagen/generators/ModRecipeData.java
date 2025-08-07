package net.nokunami.elementus.datagen.generators;

import io.redspace.ironsspellbooks.registries.ItemRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.nokunami.elementus.common.Etags;
import net.nokunami.elementus.common.compat.advancednetherite.ANModBlocks;
import net.nokunami.elementus.common.compat.advancednetherite.ANModItems;
import net.nokunami.elementus.common.compat.epicsamurai.ESModItems;
import net.nokunami.elementus.common.compat.farmersdelight.FarmersDelightItems;
import net.nokunami.elementus.common.compat.farmersdelight.NethersDelightItems;
import net.nokunami.elementus.common.compat.ironsspellbooks.ISSModItems;
import net.nokunami.elementus.common.compat.piercingpaxels.PPModItems;
import net.nokunami.elementus.common.compat.simplyswords.SSModItems;
import net.nokunami.elementus.common.compat.sniffsweapons.SWModItems;
import net.nokunami.elementus.common.compat.theaether.TAModItems;
import net.nokunami.elementus.common.compat.twigs.TWModItems;
import net.nokunami.elementus.datagen.generators.create.CreateProcessingRecipe;
import net.nokunami.elementus.datagen.providers.ModRecipeProvider;
import net.nokunami.elementus.common.registry.ModItems.*;
import net.veroxuniverse.samurai_dynasty.registry.ItemsRegistry;
import nl.sniffiandros.sniffsweapons.reg.ItemReg;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

import static net.nokunami.elementus.ModChecker.*;

public class ModRecipeData extends ModRecipeProvider {
    public ModRecipeData(PackOutput output) {
        super(output, "elementus");
        new CreateProcessingRecipe(output);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> writer) {
        Elementus(writer);
        if (farmersDelight) FarmersDelight(writer);
        if (piercingPaxels) PiercingPaxels(writer);
        if (nethersDelight) NethersDelight(writer);
        if (ironsSpellbooks) IronsSpellbooks(writer);
        if (aether) Aether(writer);
        if (simplySwords) SimplySwords(writer);
        if (sniffsWeapons) SniffsWeapons(writer);
        if (advancedNetherite) AdvancedNetherite(writer);
        if (samuraiDynasty) EpicSamurai(writer);
        if (twigs) Twigs(writer);
        if (witherStormMod) WitherStormmod(writer);
        if (vanillaClaws) BanillaClaws(writer);
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
    private void FarmersDelight(Consumer<FinishedRecipe> writer) {
        ConditionalRecipe.builder().addCondition(and(modLoaded(farmersDelightID), not(FALSE()))).addRecipe(
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, FarmersDelightItems.STEEL_KNIFE.get())
                .pattern("#").pattern("/").define('#', ElementusItems.STEEL_INGOT.get()).define('/', Tags.Items.RODS_WOODEN)
                .unlockedBy(getHasName(ElementusItems.STEEL_INGOT.get()), has(ElementusItems.STEEL_INGOT.get()))::save)
                .generateAdvancement()
                .build(writer, new ResourceLocation(getHasName(FarmersDelightItems.STEEL_KNIFE.get())));
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, FarmersDelightItems.STEEL_KNIFE, Etags.Items.INGOTS_DIARKRITE, FarmersDelightItems.DIARKRITE_KNIFE, CriterionName.DIARKRITE.name, farmersDelightID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, FarmersDelightItems.STEEL_KNIFE, Etags.Items.INGOTS_ANTHEKTITE, FarmersDelightItems.ANTHEKTITE_KNIFE, CriterionName.ANTHEKTITE.name, farmersDelightID);

        ConditionalRecipe.builder().addCondition(and(modLoaded(farmersDelightID), not(FALSE()))).addRecipe(
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, FarmersDelightItems.MOVCADIA_CABINET.get())
                .pattern("###").pattern("1 1").pattern("###").define('#', ElementusItems.MOVCADIA_SLAB.get()).define('1', ElementusItems.MOVCADIA_TRAPDOOR.get())
                .unlockedBy(getHasName(ElementusItems.MOVCADIA_PLANKS.get()), has(ElementusItems.MOVCADIA_PLANKS.get()))::save)
                .generateAdvancement()
                .build(writer, new ResourceLocation(getHasName(FarmersDelightItems.MOVCADIA_CABINET.get())));
    }
    private void PiercingPaxels(Consumer<FinishedRecipe> writer) {
        makePaxel(writer, PPModItems.STEEL_PAXEL, ElementusItems.STEEL_SWORD, ElementusItems.STEEL_SHOVEL, ElementusItems.STEEL_PICKAXE, ElementusItems.STEEL_AXE, ElementusItems.STEEL_HOE);
        makePaxel(writer, PPModItems.DIARKRITE_PAXEL, ElementusItems.DIARKRITE_SWORD, ElementusItems.DIARKRITE_SHOVEL, ElementusItems.DIARKRITE_PICKAXE, ElementusItems.DIARKRITE_AXE, ElementusItems.DIARKRITE_HOE);
        makePaxel(writer, PPModItems.ANTHEKTITE_PAXEL, ElementusItems.ANTHEKTITE_SWORD, ElementusItems.ANTHEKTITE_SHOVEL, ElementusItems.ANTHEKTITE_PICKAXE, ElementusItems.ANTHEKTITE_AXE, ElementusItems.ANTHEKTITE_HOE);

        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, PPModItems.STEEL_PAXEL, PPModItems.DIARKRITE_UPGRADE_KIT, PPModItems.DIARKRITE_PAXEL, CriterionName.DIARKRITE.name, piercingPaxelsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, PPModItems.STEEL_PAXEL, PPModItems.ANTHEKTITE_UPGRADE_KIT, PPModItems.ANTHEKTITE_PAXEL, CriterionName.ANTHEKTITE.name, piercingPaxelsID);

        makePaxelUpgradeKit(writer, PPModItems.DIARKRITE_UPGRADE_KIT, ElementusItems.DIARKRITE_INGOT);
        makePaxelUpgradeKit(writer, PPModItems.ANTHEKTITE_UPGRADE_KIT, ElementusItems.ANTHEKTITE_INGOT);
    }
    private void NethersDelight(Consumer<FinishedRecipe> writer) {
        ConditionalRecipe.builder().addCondition(and(modLoaded(farmersDelightID), not(FALSE()))).addRecipe(
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, NethersDelightItems.STEEL_MACHETE.get())
                .pattern("  #").pattern(" # ").pattern("/  ")
                .define('#', ElementusItems.STEEL_INGOT.get()).define('/', Tags.Items.RODS_WOODEN)
                .unlockedBy(getHasName(ElementusItems.STEEL_INGOT.get()), has(ElementusItems.STEEL_INGOT.get()))::save)
                .generateAdvancement()
                .build(writer, new ResourceLocation(getHasName(NethersDelightItems.STEEL_MACHETE.get())));
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, NethersDelightItems.STEEL_MACHETE, Etags.Items.INGOTS_DIARKRITE, NethersDelightItems.DIARKRITE_MACHETE, CriterionName.DIARKRITE.name, nethersDelightID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, NethersDelightItems.STEEL_MACHETE, Etags.Items.INGOTS_ANTHEKTITE, NethersDelightItems.ANTHEKTITE_MACHETE, CriterionName.ANTHEKTITE.name, nethersDelightID);
    }
    private void IronsSpellbooks(Consumer<FinishedRecipe> writer) {
        ConditionalRecipe.builder().addCondition(and(modLoaded(ironsSpellbooksID), not(FALSE()))).addRecipe(
                ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ISSModItems.STEEL_SPELL_BOOK.get())
                        .pattern("NNA").pattern("IBA").pattern("NNA")
                        .define('N', Etags.Items.NUGGETS_STEEL)
                        .define('I', Etags.Items.INGOTS_STEEL)
                        .define('B', net.minecraft.world.item.Items.BOOK)
                        .define('A', ItemRegistry.ARCANE_ESSENCE.get())
                        .unlockedBy(CriterionName.STEEL.name, has(Etags.Items.INGOTS_STEEL))::save)
                .generateAdvancement()
                .build(writer, new ResourceLocation(getHasName(ISSModItems.STEEL_SPELL_BOOK.get())));

        ConditionalRecipe.builder().addCondition(and(modLoaded(ironsSpellbooksID), not(FALSE()))).addRecipe(
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ISSModItems.DIARKRITE_SPELL_BOOK.get())
                .pattern("SAP").pattern("DRB").pattern("SAP")
                .define('S', Etags.Items.INGOTS_STEEL)
                .define('D', Etags.Items.INGOTS_DIARKRITE)
                .define('A', ItemRegistry.ARCANE_INGOT.get())
                .define('P', ItemRegistry.DIVINE_PEARL.get())
                .define('B', ItemRegistry.LIGHTNING_BOTTLE.get())
                .define('R', ItemRegistry.RUINED_BOOK.get())
                .unlockedBy(CriterionName.DIARKRITE.name, has(Etags.Items.INGOTS_DIARKRITE))::save)
                .generateAdvancement()
                .build(writer, new ResourceLocation(getHasName(ISSModItems.DIARKRITE_SPELL_BOOK.get())));

        ConditionalRecipe.builder().addCondition(and(modLoaded(ironsSpellbooksID), not(FALSE()))).addRecipe(
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ISSModItems.ANTHEKTITE_SPELL_BOOK.get())
                .pattern("AAP").pattern("DRB").pattern("AAP")
                .define('D', Etags.Items.INGOTS_ANTHEKTITE)
                .define('A', ItemRegistry.ARCANE_INGOT.get())
                .define('P', ItemRegistry.DIVINE_PEARL.get())
                .define('B', ItemRegistry.LIGHTNING_BOTTLE.get())
                .define('R', ItemRegistry.RUINED_BOOK.get())
                .unlockedBy(CriterionName.ANTHEKTITE.name, has(Etags.Items.INGOTS_ANTHEKTITE))::save)
                .generateAdvancement()
                .build(writer, new ResourceLocation(getHasName(ISSModItems.ANTHEKTITE_SPELL_BOOK.get())));

        issMageArmor(writer,
                ISSModItems.DIARKRITE_MAGE_HELMET,
                ISSModItems.DIARKRITE_MAGE_CHESTPLATE,
                ISSModItems.DIARKRITE_MAGE_LEGGINGS,
                ISSModItems.DIARKRITE_MAGE_BOOTS,
                Etags.Items.INGOTS_DIARKRITE, CriterionName.DIARKRITE.name);
        issMageArmor(writer,
                ISSModItems.ANTHEKTITE_MAGE_HELMET,
                ISSModItems.ANTHEKTITE_MAGE_CHESTPLATE,
                ISSModItems.ANTHEKTITE_MAGE_LEGGINGS,
                ISSModItems.ANTHEKTITE_MAGE_BOOTS,
                Etags.Items.INGOTS_ANTHEKTITE, CriterionName.ANTHEKTITE.name);
    }
    private void Aether(Consumer<FinishedRecipe> writer) {
        ConditionalRecipe.builder().addCondition(and(modLoaded(aetherID), not(FALSE()))).addRecipe(
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, TAModItems.STEEL_GLOVES.get())
                .pattern("# #").define('#', Etags.Items.INGOTS_STEEL)
                .unlockedBy(getHasName(ElementusItems.STEEL_INGOT.get()), has(ElementusItems.STEEL_INGOT.get()))::save)
                .build(writer, new ResourceLocation(getHasName(TAModItems.STEEL_GLOVES.get())));
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, TAModItems.STEEL_GLOVES, Etags.Items.INGOTS_DIARKRITE, TAModItems.DIARKRITE_GLOVES, CriterionName.DIARKRITE.name, aetherID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, TAModItems.STEEL_GLOVES, Etags.Items.INGOTS_ANTHEKTITE, TAModItems.ANTHEKTITE_GLOVES, CriterionName.ANTHEKTITE.name, aetherID);
    }
    private void SimplySwords(Consumer<FinishedRecipe> writer) {
        makeChakram(writer, SSModItems.STEEL_CHAKRAM, Etags.Items.INGOTS_STEEL, ElementusItems.STEEL_NUGGET, ElementusItems.STEEL_INGOT);
        makeClaymore(writer, SSModItems.STEEL_CLAYMORE, Etags.Items.INGOTS_STEEL, ElementusItems.STEEL_NUGGET, ElementusItems.STEEL_INGOT);
        makeCutlass(writer, SSModItems.STEEL_CUTLASS, Etags.Items.INGOTS_STEEL, ElementusItems.STEEL_NUGGET, ElementusItems.STEEL_INGOT);
        makeGlaive(writer, SSModItems.STEEL_GLAIVE, Etags.Items.INGOTS_STEEL, ElementusItems.STEEL_INGOT);
        makeGreataxe(writer, SSModItems.STEEL_GREATAXE, Etags.Items.INGOTS_STEEL, ElementusItems.STEEL_NUGGET, ElementusItems.STEEL_INGOT);
        makeGreathammer(writer, SSModItems.STEEL_GREATHAMMER, Etags.Items.INGOTS_STEEL, ElementusItems.STEEL_NUGGET, ElementusItems.STEEL_INGOT);
        makeHalberd(writer, SSModItems.STEEL_HALBERD, Etags.Items.INGOTS_STEEL, ElementusItems.STEEL_NUGGET, ElementusItems.STEEL_INGOT);
        makeKatana(writer, SSModItems.STEEL_KATANA, Etags.Items.INGOTS_STEEL, ElementusItems.STEEL_INGOT);
        makeLongSword(writer, SSModItems.STEEL_LONGSWORD, Etags.Items.INGOTS_STEEL, ElementusItems.STEEL_INGOT);
        makeRapier(writer, SSModItems.STEEL_RAPIER, Etags.Items.INGOTS_STEEL, ElementusItems.STEEL_INGOT);
        makeSai(writer, SSModItems.STEEL_SAI, Etags.Items.INGOTS_STEEL, ElementusItems.STEEL_INGOT);
        makeScythe(writer, SSModItems.STEEL_SCYTHE, Etags.Items.INGOTS_STEEL, ElementusItems.STEEL_INGOT);
        makeSpear(writer, SSModItems.STEEL_SPEAR, Etags.Items.INGOTS_STEEL, ElementusItems.STEEL_INGOT);
        makeTwinblade(writer, SSModItems.STEEL_TWINBLADE, Etags.Items.INGOTS_STEEL, ElementusItems.STEEL_INGOT);
        makeWarglaive(writer, SSModItems.STEEL_WARGLAIVE, Etags.Items.INGOTS_STEEL, ElementusItems.STEEL_NUGGET, ElementusItems.STEEL_INGOT);

        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SSModItems.STEEL_CHAKRAM, Etags.Items.INGOTS_DIARKRITE, SSModItems.DIARKRITE_CHAKRAM, CriterionName.DIARKRITE.name, simplySwordsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SSModItems.STEEL_CLAYMORE, Etags.Items.INGOTS_DIARKRITE, SSModItems.DIARKRITE_CLAYMORE, CriterionName.DIARKRITE.name, simplySwordsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SSModItems.STEEL_CUTLASS, Etags.Items.INGOTS_DIARKRITE, SSModItems.DIARKRITE_CUTLASS, CriterionName.DIARKRITE.name, simplySwordsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SSModItems.STEEL_GLAIVE, Etags.Items.INGOTS_DIARKRITE, SSModItems.DIARKRITE_GLAIVE, CriterionName.DIARKRITE.name, simplySwordsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SSModItems.STEEL_GREATAXE, Etags.Items.INGOTS_DIARKRITE, SSModItems.DIARKRITE_GREATAXE, CriterionName.DIARKRITE.name, simplySwordsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SSModItems.STEEL_GREATHAMMER, Etags.Items.INGOTS_DIARKRITE, SSModItems.DIARKRITE_GREATHAMMER, CriterionName.DIARKRITE.name, simplySwordsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SSModItems.STEEL_HALBERD, Etags.Items.INGOTS_DIARKRITE, SSModItems.DIARKRITE_HALBERD, CriterionName.DIARKRITE.name, simplySwordsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SSModItems.STEEL_KATANA, Etags.Items.INGOTS_DIARKRITE, SSModItems.DIARKRITE_KATANA, CriterionName.DIARKRITE.name, simplySwordsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SSModItems.STEEL_LONGSWORD, Etags.Items.INGOTS_DIARKRITE, SSModItems.DIARKRITE_LONGSWORD, CriterionName.DIARKRITE.name, simplySwordsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SSModItems.STEEL_RAPIER, Etags.Items.INGOTS_DIARKRITE, SSModItems.DIARKRITE_RAPIER, CriterionName.DIARKRITE.name, simplySwordsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SSModItems.STEEL_SAI, Etags.Items.INGOTS_DIARKRITE, SSModItems.DIARKRITE_SAI, CriterionName.DIARKRITE.name, simplySwordsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SSModItems.STEEL_SCYTHE, Etags.Items.INGOTS_DIARKRITE, SSModItems.DIARKRITE_SCYTHE, CriterionName.DIARKRITE.name, simplySwordsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SSModItems.STEEL_SPEAR, Etags.Items.INGOTS_DIARKRITE, SSModItems.DIARKRITE_SPEAR, CriterionName.DIARKRITE.name, simplySwordsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SSModItems.STEEL_TWINBLADE, Etags.Items.INGOTS_DIARKRITE, SSModItems.DIARKRITE_TWINBLADE, CriterionName.DIARKRITE.name, simplySwordsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SSModItems.STEEL_WARGLAIVE, Etags.Items.INGOTS_DIARKRITE, SSModItems.DIARKRITE_WARGLAIVE, CriterionName.DIARKRITE.name, simplySwordsID);

        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SSModItems.STEEL_CHAKRAM, Etags.Items.INGOTS_ANTHEKTITE, SSModItems.ANTHEKTITE_CHAKRAM, CriterionName.ANTHEKTITE.name, simplySwordsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SSModItems.STEEL_CLAYMORE, Etags.Items.INGOTS_ANTHEKTITE, SSModItems.ANTHEKTITE_CLAYMORE, CriterionName.ANTHEKTITE.name, simplySwordsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SSModItems.STEEL_CUTLASS, Etags.Items.INGOTS_ANTHEKTITE, SSModItems.ANTHEKTITE_CUTLASS, CriterionName.ANTHEKTITE.name, simplySwordsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SSModItems.STEEL_GLAIVE, Etags.Items.INGOTS_ANTHEKTITE, SSModItems.ANTHEKTITE_GLAIVE, CriterionName.ANTHEKTITE.name, simplySwordsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SSModItems.STEEL_GREATAXE, Etags.Items.INGOTS_ANTHEKTITE, SSModItems.ANTHEKTITE_GREATAXE, CriterionName.ANTHEKTITE.name, simplySwordsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SSModItems.STEEL_GREATHAMMER, Etags.Items.INGOTS_ANTHEKTITE, SSModItems.ANTHEKTITE_GREATHAMMER, CriterionName.ANTHEKTITE.name, simplySwordsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SSModItems.STEEL_HALBERD, Etags.Items.INGOTS_ANTHEKTITE, SSModItems.ANTHEKTITE_HALBERD, CriterionName.ANTHEKTITE.name, simplySwordsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SSModItems.STEEL_KATANA, Etags.Items.INGOTS_ANTHEKTITE, SSModItems.ANTHEKTITE_KATANA, CriterionName.ANTHEKTITE.name, simplySwordsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SSModItems.STEEL_LONGSWORD, Etags.Items.INGOTS_ANTHEKTITE, SSModItems.ANTHEKTITE_LONGSWORD, CriterionName.ANTHEKTITE.name, simplySwordsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SSModItems.STEEL_RAPIER, Etags.Items.INGOTS_ANTHEKTITE, SSModItems.ANTHEKTITE_RAPIER, CriterionName.ANTHEKTITE.name, simplySwordsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SSModItems.STEEL_SAI, Etags.Items.INGOTS_ANTHEKTITE, SSModItems.ANTHEKTITE_SAI, CriterionName.ANTHEKTITE.name, simplySwordsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SSModItems.STEEL_SCYTHE, Etags.Items.INGOTS_ANTHEKTITE, SSModItems.ANTHEKTITE_SCYTHE, CriterionName.ANTHEKTITE.name, simplySwordsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SSModItems.STEEL_SPEAR, Etags.Items.INGOTS_ANTHEKTITE, SSModItems.ANTHEKTITE_SPEAR, CriterionName.ANTHEKTITE.name, simplySwordsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SSModItems.STEEL_TWINBLADE, Etags.Items.INGOTS_ANTHEKTITE, SSModItems.ANTHEKTITE_TWINBLADE, CriterionName.ANTHEKTITE.name, simplySwordsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SSModItems.STEEL_WARGLAIVE, Etags.Items.INGOTS_ANTHEKTITE, SSModItems.ANTHEKTITE_WARGLAIVE, CriterionName.ANTHEKTITE.name, simplySwordsID);
    }
    private void SniffsWeapons(Consumer<FinishedRecipe> writer) {
        makeGreatAxe(writer, SWModItems.STEEL_GREAT_AXE, Etags.Items.INGOTS_STEEL, ElementusItems.STEEL_AXE);
        makeGreatPickaxe(writer, SWModItems.STEEL_GREAT_PICKAXE, Etags.Items.INGOTS_STEEL, ElementusItems.STEEL_PICKAXE);
        makeGreatSword(writer, SWModItems.STEEL_GREAT_SWORD, Etags.Items.INGOTS_STEEL, ElementusItems.STEEL_SWORD);
        makeNaginata(writer, SWModItems.STEEL_NAGINATA, Etags.Items.INGOTS_STEEL, ElementusItems.STEEL_SWORD);

        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SWModItems.STEEL_GREAT_AXE, Etags.Items.INGOTS_DIARKRITE, SWModItems.DIARKRITE_GREAT_AXE, CriterionName.DIARKRITE.name, sniffsWeaponsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SWModItems.STEEL_GREAT_PICKAXE, Etags.Items.INGOTS_DIARKRITE, SWModItems.DIARKRITE_GREAT_PICKAXE, CriterionName.DIARKRITE.name, sniffsWeaponsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SWModItems.STEEL_GREAT_SWORD, Etags.Items.INGOTS_DIARKRITE, SWModItems.DIARKRITE_GREAT_SWORD, CriterionName.DIARKRITE.name, sniffsWeaponsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SWModItems.STEEL_NAGINATA, Etags.Items.INGOTS_DIARKRITE, SWModItems.DIARKRITE_NAGINATA, CriterionName.DIARKRITE.name, sniffsWeaponsID);

        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SWModItems.STEEL_GREAT_AXE, Etags.Items.INGOTS_ANTHEKTITE, SWModItems.ANTHEKTITE_GREAT_AXE, CriterionName.ANTHEKTITE.name, sniffsWeaponsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SWModItems.STEEL_GREAT_PICKAXE, Etags.Items.INGOTS_ANTHEKTITE, SWModItems.ANTHEKTITE_GREAT_PICKAXE, CriterionName.ANTHEKTITE.name, sniffsWeaponsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SWModItems.STEEL_GREAT_SWORD, Etags.Items.INGOTS_ANTHEKTITE, SWModItems.ANTHEKTITE_GREAT_SWORD, CriterionName.ANTHEKTITE.name, sniffsWeaponsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SWModItems.STEEL_NAGINATA, Etags.Items.INGOTS_DIARKRITE, SWModItems.ANTHEKTITE_NAGINATA, CriterionName.ANTHEKTITE.name, sniffsWeaponsID);

        smithingSniffWeaponConvert(writer, ItemReg.HELM_ARMOR_TRIM_SMITHING_TEMPLATE, ElementusItems.STEEL_HELMET, net.minecraft.world.item.Items.FEATHER, SWModItems.STEEL_HELM);
        smithingSniffWeaponConvert(writer, ItemReg.SURCOAT_ARMOR_TRIM_SMITHING_TEMPLATE, ElementusItems.STEEL_CHESTPLATE, net.minecraft.world.item.Items.LEATHER, SWModItems.STEEL_SURCOAT);
        smithingSniffWeaponConvert(writer, ItemReg.HORNED_ARMOR_TRIM_SMITHING_TEMPLATE, ElementusItems.STEEL_HELMET, net.minecraft.world.item.Items.BONE, SWModItems.STEEL_HORNED_HELM);
        smithingSniffWeaponConvert(writer, ItemReg.PLATED_ARMOR_TRIM_SMITHING_TEMPLATE, ElementusItems.STEEL_CHESTPLATE, net.minecraft.world.item.Items.IRON_INGOT, SWModItems.PLATED_STEEL_CHESTPLATE);
        smithingSniffWeaponConvert(writer, ItemReg.SAMURAI_ARMOR_TRIM_SMITHING_TEMPLATE, ElementusItems.STEEL_HELMET, net.minecraft.world.item.Items.GOLD_NUGGET, SWModItems.STEEL_KABUTO);
        smithingSniffWeaponConvert(writer, ItemReg.SAMURAI_ARMOR_TRIM_SMITHING_TEMPLATE, ElementusItems.STEEL_CHESTPLATE, net.minecraft.world.item.Items.GOLD_NUGGET, SWModItems.STEEL_DO);

        smithingSniffWeaponConvert(writer, ItemReg.HELM_ARMOR_TRIM_SMITHING_TEMPLATE, ElementusItems.DIARKRITE_HELMET, net.minecraft.world.item.Items.FEATHER, SWModItems.DIARKRITE_HELM);
        smithingSniffWeaponConvert(writer, ItemReg.SURCOAT_ARMOR_TRIM_SMITHING_TEMPLATE, ElementusItems.DIARKRITE_CHESTPLATE, net.minecraft.world.item.Items.LEATHER, SWModItems.DIARKRITE_SURCOAT);
        smithingSniffWeaponConvert(writer, ItemReg.HORNED_ARMOR_TRIM_SMITHING_TEMPLATE, ElementusItems.DIARKRITE_HELMET, net.minecraft.world.item.Items.BONE, SWModItems.DIARKRITE_HORNED_HELM);
        smithingSniffWeaponConvert(writer, ItemReg.PLATED_ARMOR_TRIM_SMITHING_TEMPLATE, ElementusItems.DIARKRITE_CHESTPLATE, net.minecraft.world.item.Items.IRON_INGOT, SWModItems.PLATED_DIARKRITE_CHESTPLATE);
        smithingSniffWeaponConvert(writer, ItemReg.SAMURAI_ARMOR_TRIM_SMITHING_TEMPLATE, ElementusItems.DIARKRITE_HELMET, net.minecraft.world.item.Items.GOLD_NUGGET, SWModItems.DIARKRITE_KABUTO);
        smithingSniffWeaponConvert(writer, ItemReg.SAMURAI_ARMOR_TRIM_SMITHING_TEMPLATE, ElementusItems.DIARKRITE_CHESTPLATE, net.minecraft.world.item.Items.GOLD_NUGGET, SWModItems.DIARKRITE_DO);

        smithingSniffWeaponConvert(writer, ItemReg.HELM_ARMOR_TRIM_SMITHING_TEMPLATE, ElementusItems.ANTHEKTITE_HELMET, net.minecraft.world.item.Items.FEATHER, SWModItems.ANTHEKTITE_HELM);
        smithingSniffWeaponConvert(writer, ItemReg.SURCOAT_ARMOR_TRIM_SMITHING_TEMPLATE, ElementusItems.ANTHEKTITE_CHESTPLATE, net.minecraft.world.item.Items.LEATHER, SWModItems.ANTHEKTITE_SURCOAT);
        smithingSniffWeaponConvert(writer, ItemReg.HORNED_ARMOR_TRIM_SMITHING_TEMPLATE, ElementusItems.ANTHEKTITE_HELMET, net.minecraft.world.item.Items.BONE, SWModItems.ANTHEKTITE_HORNED_HELM);
        smithingSniffWeaponConvert(writer, ItemReg.PLATED_ARMOR_TRIM_SMITHING_TEMPLATE, ElementusItems.ANTHEKTITE_CHESTPLATE, net.minecraft.world.item.Items.IRON_INGOT, SWModItems.PLATED_ANTHEKTITE_CHESTPLATE);
        smithingSniffWeaponConvert(writer, ItemReg.SAMURAI_ARMOR_TRIM_SMITHING_TEMPLATE, ElementusItems.ANTHEKTITE_HELMET, net.minecraft.world.item.Items.GOLD_NUGGET, SWModItems.ANTHEKTITE_KABUTO);
        smithingSniffWeaponConvert(writer, ItemReg.SAMURAI_ARMOR_TRIM_SMITHING_TEMPLATE, ElementusItems.ANTHEKTITE_CHESTPLATE, net.minecraft.world.item.Items.GOLD_NUGGET, SWModItems.ANTHEKTITE_DO);

        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SWModItems.STEEL_HELM, Etags.Items.INGOTS_DIARKRITE, SWModItems.DIARKRITE_HELM, CriterionName.DIARKRITE.name, sniffsWeaponsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SWModItems.STEEL_SURCOAT, Etags.Items.INGOTS_DIARKRITE, SWModItems.DIARKRITE_SURCOAT, CriterionName.DIARKRITE.name, sniffsWeaponsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SWModItems.STEEL_HORNED_HELM, Etags.Items.INGOTS_DIARKRITE, SWModItems.DIARKRITE_HORNED_HELM, CriterionName.DIARKRITE.name, sniffsWeaponsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SWModItems.PLATED_STEEL_CHESTPLATE, Etags.Items.INGOTS_DIARKRITE, SWModItems.PLATED_DIARKRITE_CHESTPLATE, CriterionName.DIARKRITE.name, sniffsWeaponsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SWModItems.STEEL_KABUTO, Etags.Items.INGOTS_DIARKRITE, SWModItems.DIARKRITE_KABUTO, CriterionName.DIARKRITE.name, sniffsWeaponsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SWModItems.STEEL_DO, Etags.Items.INGOTS_DIARKRITE, SWModItems.DIARKRITE_DO, CriterionName.DIARKRITE.name, sniffsWeaponsID);

        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SWModItems.STEEL_HELM, Etags.Items.INGOTS_ANTHEKTITE, SWModItems.ANTHEKTITE_HELM, CriterionName.ANTHEKTITE.name, sniffsWeaponsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SWModItems.STEEL_SURCOAT, Etags.Items.INGOTS_ANTHEKTITE, SWModItems.ANTHEKTITE_SURCOAT, CriterionName.ANTHEKTITE.name, sniffsWeaponsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SWModItems.STEEL_HORNED_HELM, Etags.Items.INGOTS_ANTHEKTITE, SWModItems.ANTHEKTITE_HORNED_HELM, CriterionName.ANTHEKTITE.name, sniffsWeaponsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SWModItems.PLATED_STEEL_CHESTPLATE, Etags.Items.INGOTS_ANTHEKTITE, SWModItems.PLATED_ANTHEKTITE_CHESTPLATE, CriterionName.ANTHEKTITE.name, sniffsWeaponsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SWModItems.STEEL_KABUTO, Etags.Items.INGOTS_ANTHEKTITE, SWModItems.ANTHEKTITE_KABUTO, CriterionName.ANTHEKTITE.name, sniffsWeaponsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SWModItems.STEEL_DO, Etags.Items.INGOTS_ANTHEKTITE, SWModItems.ANTHEKTITE_DO, CriterionName.ANTHEKTITE.name, sniffsWeaponsID);
    }
    private void AdvancedNetherite(Consumer<FinishedRecipe> writer) {
        ingotBlockRecipes(writer, ANModItems.DIARKRITE_IRON.get(), ANModBlocks.DIARKRITE_IRON_BLOCK.get(), ElementusItems.DIARKRITE_INGOT.get(), Tags.Items.INGOTS_IRON, ANModItems.DIARKRITE_IRON.get());
        ingotBlockRecipes(writer, ANModItems.DIARKRITE_GOLD.get(), ANModBlocks.DIARKRITE_GOLD_BLOCK.get(), ANModItems.DIARKRITE_IRON.get(), Tags.Items.INGOTS_GOLD, ANModItems.DIARKRITE_GOLD.get());
        ingotBlockRecipes(writer, ANModItems.DIARKRITE_EMERALD.get(), ANModBlocks.DIARKRITE_EMERALD_BLOCK.get(), ANModItems.DIARKRITE_GOLD.get(), Tags.Items.GEMS_EMERALD, ANModItems.DIARKRITE_EMERALD.get());
        ingotBlockRecipes(writer, ANModItems.DIARKRITE_DIAMOND.get(), ANModBlocks.DIARKRITE_DIAMOND_BLOCK.get(), ANModItems.DIARKRITE_EMERALD.get(), Tags.Items.GEMS_DIAMOND, ANModItems.DIARKRITE_DIAMOND.get());

        ingotBlockRecipes(writer, ANModItems.ANTHEKTITE_IRON.get(), ANModBlocks.ANTHEKTITE_IRON_BLOCK.get(), ElementusItems.ANTHEKTITE_INGOT.get(), Tags.Items.INGOTS_IRON, ANModItems.ANTHEKTITE_IRON.get());
        ingotBlockRecipes(writer, ANModItems.ANTHEKTITE_GOLD.get(), ANModBlocks.ANTHEKTITE_GOLD_BLOCK.get(), ANModItems.ANTHEKTITE_IRON.get(), Tags.Items.INGOTS_GOLD, ANModItems.ANTHEKTITE_GOLD.get());
        ingotBlockRecipes(writer, ANModItems.ANTHEKTITE_EMERALD.get(), ANModBlocks.ANTHEKTITE_EMERALD_BLOCK.get(), ANModItems.ANTHEKTITE_GOLD.get(), Tags.Items.GEMS_EMERALD, ANModItems.ANTHEKTITE_EMERALD.get());
        ingotBlockRecipes(writer, ANModItems.ANTHEKTITE_DIAMOND.get(), ANModBlocks.ANTHEKTITE_DIAMOND_BLOCK.get(), ANModItems.ANTHEKTITE_EMERALD.get(), Tags.Items.GEMS_DIAMOND, ANModItems.ANTHEKTITE_DIAMOND.get());


        makeAdvancedNetheriteTools(writer, ElementusItems.DIARKRITE_SWORD, ElementusItems.DIARKRITE_SHOVEL, ElementusItems.DIARKRITE_PICKAXE, ElementusItems.DIARKRITE_AXE, ElementusItems.DIARKRITE_HOE, Etags.Items.INGOTS_DIARKRITE,
                ANModItems.DIARKRITE_IRON_SWORD, ANModItems.DIARKRITE_IRON_SHOVEL, ANModItems.DIARKRITE_IRON_PICKAXE, ANModItems.DIARKRITE_IRON_AXE, ANModItems.DIARKRITE_IRON_HOE);

        makeAdvancedNetheriteTools(writer, ANModItems.DIARKRITE_IRON_SWORD, ANModItems.DIARKRITE_IRON_SHOVEL, ANModItems.DIARKRITE_IRON_PICKAXE, ANModItems.DIARKRITE_IRON_AXE, ANModItems.DIARKRITE_IRON_HOE, Etags.Items.INGOTS_DIARKRITE_GOLD,
                ANModItems.DIARKRITE_GOLD_SWORD, ANModItems.DIARKRITE_GOLD_SHOVEL, ANModItems.DIARKRITE_GOLD_PICKAXE, ANModItems.DIARKRITE_GOLD_AXE, ANModItems.DIARKRITE_GOLD_HOE);

        makeAdvancedNetheriteTools(writer, ANModItems.DIARKRITE_GOLD_SWORD, ANModItems.DIARKRITE_GOLD_SHOVEL, ANModItems.DIARKRITE_GOLD_PICKAXE, ANModItems.DIARKRITE_GOLD_AXE, ANModItems.DIARKRITE_GOLD_HOE, Etags.Items.INGOTS_DIARKRITE_EMERALD,
                ANModItems.DIARKRITE_EMERALD_SWORD, ANModItems.DIARKRITE_EMERALD_SHOVEL, ANModItems.DIARKRITE_EMERALD_PICKAXE, ANModItems.DIARKRITE_EMERALD_AXE, ANModItems.DIARKRITE_EMERALD_HOE);

        makeAdvancedNetheriteTools(writer, ANModItems.DIARKRITE_EMERALD_SWORD, ANModItems.DIARKRITE_EMERALD_SHOVEL, ANModItems.DIARKRITE_EMERALD_PICKAXE, ANModItems.DIARKRITE_EMERALD_AXE, ANModItems.DIARKRITE_EMERALD_HOE, Etags.Items.INGOTS_DIARKRITE_DIAMOND,
                ANModItems.DIARKRITE_DIAMOND_SWORD, ANModItems.DIARKRITE_DIAMOND_SHOVEL, ANModItems.DIARKRITE_DIAMOND_PICKAXE, ANModItems.DIARKRITE_DIAMOND_AXE, ANModItems.DIARKRITE_DIAMOND_HOE);


        makeAdvancedNetheriteArmors(writer, ElementusItems.DIARKRITE_HELMET, ElementusItems.DIARKRITE_CHESTPLATE, ElementusItems.DIARKRITE_LEGGINGS, ElementusItems.DIARKRITE_BOOTS, Etags.Items.INGOTS_DIARKRITE_IRON,
                ANModItems.DIARKRITE_IRON_HELMET, ANModItems.DIARKRITE_IRON_CHESTPLATE, ANModItems.DIARKRITE_IRON_LEGGINGS, ANModItems.DIARKRITE_IRON_BOOTS);

        makeAdvancedNetheriteArmors(writer, ANModItems.DIARKRITE_IRON_HELMET, ANModItems.DIARKRITE_IRON_CHESTPLATE, ANModItems.DIARKRITE_IRON_LEGGINGS, ANModItems.DIARKRITE_IRON_BOOTS, Etags.Items.INGOTS_DIARKRITE_GOLD,
                ANModItems.DIARKRITE_GOLD_HELMET, ANModItems.DIARKRITE_GOLD_CHESTPLATE, ANModItems.DIARKRITE_GOLD_LEGGINGS, ANModItems.DIARKRITE_GOLD_BOOTS);

        makeAdvancedNetheriteArmors(writer, ANModItems.DIARKRITE_GOLD_HELMET, ANModItems.DIARKRITE_GOLD_CHESTPLATE, ANModItems.DIARKRITE_GOLD_LEGGINGS, ANModItems.DIARKRITE_GOLD_BOOTS, Etags.Items.INGOTS_DIARKRITE_EMERALD,
                ANModItems.DIARKRITE_EMERALD_HELMET, ANModItems.DIARKRITE_EMERALD_CHESTPLATE, ANModItems.DIARKRITE_EMERALD_LEGGINGS, ANModItems.DIARKRITE_EMERALD_BOOTS);

        makeAdvancedNetheriteArmors(writer, ANModItems.DIARKRITE_EMERALD_HELMET, ANModItems.DIARKRITE_EMERALD_CHESTPLATE, ANModItems.DIARKRITE_EMERALD_LEGGINGS, ANModItems.DIARKRITE_EMERALD_BOOTS, Etags.Items.INGOTS_DIARKRITE_DIAMOND,
                ANModItems.DIARKRITE_DIAMOND_HELMET, ANModItems.DIARKRITE_DIAMOND_CHESTPLATE, ANModItems.DIARKRITE_DIAMOND_LEGGINGS, ANModItems.DIARKRITE_DIAMOND_BOOTS);



        makeAdvancedNetheriteTools(writer, ElementusItems.ANTHEKTITE_SWORD, ElementusItems.ANTHEKTITE_SHOVEL, ElementusItems.ANTHEKTITE_PICKAXE, ElementusItems.ANTHEKTITE_AXE, ElementusItems.ANTHEKTITE_HOE, Etags.Items.INGOTS_ANTHEKTITE_IRON,
                ANModItems.ANTHEKTITE_IRON_SWORD, ANModItems.ANTHEKTITE_IRON_SHOVEL, ANModItems.ANTHEKTITE_IRON_PICKAXE, ANModItems.ANTHEKTITE_IRON_AXE, ANModItems.ANTHEKTITE_IRON_HOE);

        makeAdvancedNetheriteTools(writer, ANModItems.ANTHEKTITE_IRON_SWORD, ANModItems.ANTHEKTITE_IRON_SHOVEL, ANModItems.ANTHEKTITE_IRON_PICKAXE, ANModItems.ANTHEKTITE_IRON_AXE, ANModItems.ANTHEKTITE_IRON_HOE, Etags.Items.INGOTS_ANTHEKTITE_GOLD,
                ANModItems.ANTHEKTITE_GOLD_SWORD, ANModItems.ANTHEKTITE_GOLD_SHOVEL, ANModItems.ANTHEKTITE_GOLD_PICKAXE, ANModItems.ANTHEKTITE_GOLD_AXE, ANModItems.ANTHEKTITE_GOLD_HOE);

        makeAdvancedNetheriteTools(writer, ANModItems.ANTHEKTITE_GOLD_SWORD, ANModItems.ANTHEKTITE_GOLD_SHOVEL, ANModItems.ANTHEKTITE_GOLD_PICKAXE, ANModItems.ANTHEKTITE_GOLD_AXE, ANModItems.ANTHEKTITE_GOLD_HOE, Etags.Items.INGOTS_ANTHEKTITE_EMERALD,
                ANModItems.ANTHEKTITE_EMERALD_SWORD, ANModItems.ANTHEKTITE_EMERALD_SHOVEL, ANModItems.ANTHEKTITE_EMERALD_PICKAXE, ANModItems.ANTHEKTITE_EMERALD_AXE, ANModItems.ANTHEKTITE_EMERALD_HOE);

        makeAdvancedNetheriteTools(writer, ANModItems.ANTHEKTITE_EMERALD_SWORD, ANModItems.ANTHEKTITE_EMERALD_SHOVEL, ANModItems.ANTHEKTITE_EMERALD_PICKAXE, ANModItems.ANTHEKTITE_EMERALD_AXE, ANModItems.ANTHEKTITE_EMERALD_HOE, Etags.Items.INGOTS_ANTHEKTITE_DIAMOND,
                ANModItems.ANTHEKTITE_DIAMOND_SWORD, ANModItems.ANTHEKTITE_DIAMOND_SHOVEL, ANModItems.ANTHEKTITE_DIAMOND_PICKAXE, ANModItems.ANTHEKTITE_DIAMOND_AXE, ANModItems.ANTHEKTITE_DIAMOND_HOE);


        makeAdvancedNetheriteArmors(writer, ElementusItems.ANTHEKTITE_HELMET, ElementusItems.ANTHEKTITE_CHESTPLATE, ElementusItems.ANTHEKTITE_LEGGINGS, ElementusItems.ANTHEKTITE_BOOTS, Etags.Items.INGOTS_ANTHEKTITE_IRON,
                ANModItems.ANTHEKTITE_IRON_HELMET, ANModItems.ANTHEKTITE_IRON_CHESTPLATE, ANModItems.ANTHEKTITE_IRON_LEGGINGS, ANModItems.ANTHEKTITE_IRON_BOOTS);

        makeAdvancedNetheriteArmors(writer, ANModItems.ANTHEKTITE_IRON_HELMET, ANModItems.ANTHEKTITE_IRON_CHESTPLATE, ANModItems.ANTHEKTITE_IRON_LEGGINGS, ANModItems.ANTHEKTITE_IRON_BOOTS, Etags.Items.INGOTS_ANTHEKTITE_GOLD,
                ANModItems.ANTHEKTITE_GOLD_HELMET, ANModItems.ANTHEKTITE_GOLD_CHESTPLATE, ANModItems.ANTHEKTITE_GOLD_LEGGINGS, ANModItems.ANTHEKTITE_GOLD_BOOTS);

        makeAdvancedNetheriteArmors(writer, ANModItems.ANTHEKTITE_GOLD_HELMET, ANModItems.ANTHEKTITE_GOLD_CHESTPLATE, ANModItems.ANTHEKTITE_GOLD_LEGGINGS, ANModItems.ANTHEKTITE_GOLD_BOOTS, Etags.Items.INGOTS_ANTHEKTITE_EMERALD,
                ANModItems.ANTHEKTITE_EMERALD_HELMET, ANModItems.ANTHEKTITE_EMERALD_CHESTPLATE, ANModItems.ANTHEKTITE_EMERALD_LEGGINGS, ANModItems.ANTHEKTITE_EMERALD_BOOTS);

        makeAdvancedNetheriteArmors(writer, ANModItems.ANTHEKTITE_EMERALD_HELMET, ANModItems.ANTHEKTITE_EMERALD_CHESTPLATE, ANModItems.ANTHEKTITE_EMERALD_LEGGINGS, ANModItems.ANTHEKTITE_EMERALD_BOOTS, Etags.Items.INGOTS_ANTHEKTITE_DIAMOND,
                ANModItems.ANTHEKTITE_DIAMOND_HELMET, ANModItems.ANTHEKTITE_DIAMOND_CHESTPLATE, ANModItems.ANTHEKTITE_DIAMOND_LEGGINGS, ANModItems.ANTHEKTITE_DIAMOND_BOOTS);
    }
    private void EpicSamurai(Consumer<FinishedRecipe> writer) {
        ConditionalRecipe.builder().addCondition(and(modLoaded(samuraiDynastyID))).addRecipe(c ->
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ESModItems.STEEL_SAMURAI_HELMET.get())
                .define('#', Etags.Items.INGOTS_STEEL).define('J', ItemsRegistry.JADE.get()).define('I', Tags.Items.INGOTS_IRON)
                .pattern(" # ")
                .pattern("IJI")
                .pattern("# #")
                .unlockedBy(getHasName(ElementusItems.STEEL_INGOT.get()), has(Etags.Items.INGOTS_STEEL))
                .save(writer));
        ConditionalRecipe.builder().addCondition(and(modLoaded(samuraiDynastyID))).addRecipe(c ->
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ESModItems.STEEL_SAMURAI_CHESTPLATE.get())
                .define('#', Etags.Items.INGOTS_STEEL).define('I', Tags.Items.INGOTS_IRON)
                .pattern("# #")
                .pattern("I#I")
                .pattern("#I#")
                .unlockedBy(getHasName(ElementusItems.STEEL_INGOT.get()), has(Etags.Items.INGOTS_STEEL))
                .save(writer));
        ConditionalRecipe.builder().addCondition(and(modLoaded(samuraiDynastyID))).addRecipe(c ->
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ESModItems.STEEL_SAMURAI_LEGGINGS.get())
                .define('#', Etags.Items.INGOTS_STEEL).define('I', Tags.Items.INGOTS_IRON)
                .pattern("###")
                .pattern("I I")
                .pattern("# #")
                .unlockedBy(getHasName(ElementusItems.STEEL_INGOT.get()), has(Etags.Items.INGOTS_STEEL))
                .save(writer));
        ConditionalRecipe.builder().addCondition(and(modLoaded(samuraiDynastyID))).addRecipe(c ->
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ESModItems.STEEL_SAMURAI_BOOTS.get())
                .define('#', Etags.Items.INGOTS_STEEL).define('I', Tags.Items.INGOTS_IRON)
                .pattern("#I#")
                .pattern("# #")
                .unlockedBy(getHasName(ElementusItems.STEEL_INGOT.get()), has(Etags.Items.INGOTS_STEEL))
                .save(writer));

        ConditionalRecipe.builder().addCondition(and(modLoaded(samuraiDynastyID))).addRecipe(c ->
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ESModItems.STEEL_SAMURAI_HELMET_LIGHT.get())
                .define('#', Etags.Items.INGOTS_STEEL).define('I', Tags.Items.INGOTS_IRON)
                .pattern(" I ")
                .pattern("I#I")
                .pattern("# #")
                .unlockedBy(getHasName(ElementusItems.STEEL_INGOT.get()), has(Etags.Items.INGOTS_STEEL))
                .save(writer));
        ConditionalRecipe.builder().addCondition(and(modLoaded(samuraiDynastyID))).addRecipe(c ->
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ESModItems.STEEL_SAMURAI_CHESTPLATE_LIGHT.get())
                .define('#', Etags.Items.INGOTS_STEEL).define('I', Tags.Items.INGOTS_IRON)
                .pattern("# #")
                .pattern("#I#")
                .pattern("#I#")
                .unlockedBy(getHasName(ElementusItems.STEEL_INGOT.get()), has(Etags.Items.INGOTS_STEEL))
                .save(writer));
        ConditionalRecipe.builder().addCondition(and(modLoaded(samuraiDynastyID))).addRecipe(c ->
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ESModItems.STEEL_SAMURAI_LEGGINGS_LIGHT.get())
                .define('#', Etags.Items.INGOTS_STEEL).define('I', Tags.Items.INGOTS_IRON)
                .pattern("#I#")
                .pattern("# #")
                .pattern("# #")
                .unlockedBy(getHasName(ElementusItems.STEEL_INGOT.get()), has(Etags.Items.INGOTS_STEEL))
                .save(writer));
        ConditionalRecipe.builder().addCondition(and(modLoaded(samuraiDynastyID))).addRecipe(c ->
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ESModItems.STEEL_SAMURAI_BOOTS_LIGHT.get())
                .define('#', Etags.Items.INGOTS_STEEL).define('I', Tags.Items.INGOTS_IRON)
                .pattern("I#I")
                .pattern("# #")
                .unlockedBy(getHasName(ElementusItems.STEEL_INGOT.get()), has(Etags.Items.INGOTS_STEEL))
                .save(writer));

        ConditionalRecipe.builder().addCondition(and(modLoaded(samuraiDynastyID))).addRecipe(c ->
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ESModItems.STEEL_SAMURAI_HELMET_MASTER.get())
                .define('#', Etags.Items.INGOTS_STEEL).define('I', Tags.Items.INGOTS_IRON)
                .pattern(" # ")
                .pattern("III")
                .pattern("# #")
                .unlockedBy(getHasName(ElementusItems.STEEL_INGOT.get()), has(Etags.Items.INGOTS_STEEL))
                .save(writer));
        ConditionalRecipe.builder().addCondition(and(modLoaded(samuraiDynastyID))).addRecipe(c ->
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ESModItems.STEEL_SAMURAI_CHESTPLATE_MASTER.get())
                .define('#', Etags.Items.INGOTS_STEEL).define('I', Tags.Items.INGOTS_IRON)
                .pattern("# #")
                .pattern("I#I")
                .pattern("III")
                .unlockedBy(getHasName(ElementusItems.STEEL_INGOT.get()), has(Etags.Items.INGOTS_STEEL))
                .save(writer));
        ConditionalRecipe.builder().addCondition(and(modLoaded(samuraiDynastyID))).addRecipe(c ->
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ESModItems.STEEL_SAMURAI_LEGGINGS_MASTER.get())
                .define('#', Etags.Items.INGOTS_STEEL).define('I', Tags.Items.INGOTS_IRON)
                .pattern("#I#")
                .pattern("I I")
                .pattern("# #")
                .unlockedBy(getHasName(ElementusItems.STEEL_INGOT.get()), has(Etags.Items.INGOTS_STEEL))
                .save(writer));
        ConditionalRecipe.builder().addCondition(and(modLoaded(samuraiDynastyID))).addRecipe(c ->
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ESModItems.STEEL_SAMURAI_BOOTS_MASTER.get())
                .define('#', Etags.Items.INGOTS_STEEL).define('I', Tags.Items.INGOTS_IRON)
                .pattern("III")
                .pattern("# #")
                .unlockedBy(getHasName(ElementusItems.STEEL_INGOT.get()), has(Etags.Items.INGOTS_STEEL))
                .save(writer));

        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ESModItems.STEEL_SAMURAI_HELMET, Etags.Items.INGOTS_DIARKRITE, ESModItems.DIARKRITE_SAMURAI_HELMET, CriterionName.DIARKRITE.name, samuraiDynastyID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ESModItems.STEEL_SAMURAI_CHESTPLATE, Etags.Items.INGOTS_DIARKRITE, ESModItems.DIARKRITE_SAMURAI_CHESTPLATE, CriterionName.DIARKRITE.name, samuraiDynastyID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ESModItems.STEEL_SAMURAI_LEGGINGS, Etags.Items.INGOTS_DIARKRITE, ESModItems.DIARKRITE_SAMURAI_LEGGINGS, CriterionName.DIARKRITE.name, samuraiDynastyID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ESModItems.STEEL_SAMURAI_BOOTS, Etags.Items.INGOTS_DIARKRITE, ESModItems.DIARKRITE_SAMURAI_BOOTS, CriterionName.DIARKRITE.name, samuraiDynastyID);

        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ESModItems.STEEL_SAMURAI_HELMET_LIGHT, Etags.Items.INGOTS_DIARKRITE, ESModItems.DIARKRITE_SAMURAI_HELMET_LIGHT, CriterionName.DIARKRITE.name, samuraiDynastyID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ESModItems.STEEL_SAMURAI_CHESTPLATE_LIGHT, Etags.Items.INGOTS_DIARKRITE, ESModItems.DIARKRITE_SAMURAI_CHESTPLATE_LIGHT, CriterionName.DIARKRITE.name, samuraiDynastyID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ESModItems.STEEL_SAMURAI_LEGGINGS_LIGHT, Etags.Items.INGOTS_DIARKRITE, ESModItems.DIARKRITE_SAMURAI_LEGGINGS_LIGHT, CriterionName.DIARKRITE.name, samuraiDynastyID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ESModItems.STEEL_SAMURAI_BOOTS_LIGHT, Etags.Items.INGOTS_DIARKRITE, ESModItems.DIARKRITE_SAMURAI_BOOTS_LIGHT, CriterionName.DIARKRITE.name, samuraiDynastyID);

        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ESModItems.STEEL_SAMURAI_HELMET_MASTER, Etags.Items.INGOTS_DIARKRITE, ESModItems.DIARKRITE_SAMURAI_HELMET_MASTER, CriterionName.DIARKRITE.name, samuraiDynastyID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ESModItems.STEEL_SAMURAI_CHESTPLATE_MASTER, Etags.Items.INGOTS_DIARKRITE, ESModItems.DIARKRITE_SAMURAI_CHESTPLATE_MASTER, CriterionName.DIARKRITE.name, samuraiDynastyID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ESModItems.STEEL_SAMURAI_LEGGINGS_MASTER, Etags.Items.INGOTS_DIARKRITE, ESModItems.DIARKRITE_SAMURAI_LEGGINGS_MASTER, CriterionName.DIARKRITE.name, samuraiDynastyID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ESModItems.STEEL_SAMURAI_BOOTS_MASTER, Etags.Items.INGOTS_DIARKRITE, ESModItems.DIARKRITE_SAMURAI_BOOTS_MASTER, CriterionName.DIARKRITE.name, samuraiDynastyID);


        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ESModItems.STEEL_SAMURAI_HELMET, Etags.Items.INGOTS_ANTHEKTITE, ESModItems.ANTHEKTITE_SAMURAI_HELMET, CriterionName.ANTHEKTITE.name, samuraiDynastyID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ESModItems.STEEL_SAMURAI_CHESTPLATE, Etags.Items.INGOTS_ANTHEKTITE, ESModItems.ANTHEKTITE_SAMURAI_CHESTPLATE, CriterionName.ANTHEKTITE.name, samuraiDynastyID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ESModItems.STEEL_SAMURAI_LEGGINGS, Etags.Items.INGOTS_ANTHEKTITE, ESModItems.ANTHEKTITE_SAMURAI_LEGGINGS, CriterionName.ANTHEKTITE.name, samuraiDynastyID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ESModItems.STEEL_SAMURAI_BOOTS, Etags.Items.INGOTS_ANTHEKTITE, ESModItems.ANTHEKTITE_SAMURAI_BOOTS, CriterionName.ANTHEKTITE.name, samuraiDynastyID);

        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ESModItems.STEEL_SAMURAI_HELMET_LIGHT, Etags.Items.INGOTS_ANTHEKTITE, ESModItems.ANTHEKTITE_SAMURAI_HELMET_LIGHT, CriterionName.ANTHEKTITE.name, samuraiDynastyID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ESModItems.STEEL_SAMURAI_CHESTPLATE_LIGHT, Etags.Items.INGOTS_ANTHEKTITE, ESModItems.ANTHEKTITE_SAMURAI_CHESTPLATE_LIGHT, CriterionName.ANTHEKTITE.name, samuraiDynastyID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ESModItems.STEEL_SAMURAI_LEGGINGS_LIGHT, Etags.Items.INGOTS_ANTHEKTITE, ESModItems.ANTHEKTITE_SAMURAI_LEGGINGS_LIGHT, CriterionName.ANTHEKTITE.name, samuraiDynastyID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ESModItems.STEEL_SAMURAI_BOOTS_LIGHT, Etags.Items.INGOTS_ANTHEKTITE, ESModItems.ANTHEKTITE_SAMURAI_BOOTS_LIGHT, CriterionName.ANTHEKTITE.name, samuraiDynastyID);

        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ESModItems.STEEL_SAMURAI_HELMET_MASTER, Etags.Items.INGOTS_ANTHEKTITE, ESModItems.ANTHEKTITE_SAMURAI_HELMET_MASTER, CriterionName.ANTHEKTITE.name, samuraiDynastyID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ESModItems.STEEL_SAMURAI_CHESTPLATE_MASTER, Etags.Items.INGOTS_ANTHEKTITE, ESModItems.ANTHEKTITE_SAMURAI_CHESTPLATE_MASTER, CriterionName.ANTHEKTITE.name, samuraiDynastyID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ESModItems.STEEL_SAMURAI_LEGGINGS_MASTER, Etags.Items.INGOTS_ANTHEKTITE, ESModItems.ANTHEKTITE_SAMURAI_LEGGINGS_MASTER, CriterionName.ANTHEKTITE.name, samuraiDynastyID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ESModItems.STEEL_SAMURAI_BOOTS_MASTER, Etags.Items.INGOTS_ANTHEKTITE, ESModItems.ANTHEKTITE_SAMURAI_BOOTS_MASTER, CriterionName.ANTHEKTITE.name, samuraiDynastyID);
    }
    private void Twigs(Consumer<FinishedRecipe> writer) {
        ConditionalRecipe.builder().addCondition(and(modLoaded(twigsID), not(FALSE()))).addRecipe(
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TWModItems.MOVCADIA_TABLE.get())
                .pattern("###").pattern("1 1").pattern("1 1").define('#', ElementusItems.MOVCADIA_SLAB.get()).define('1', ElementusItems.MOVCADIA_FENCE.get())
                .unlockedBy(getHasName(ElementusItems.MOVCADIA_PLANKS.get()), has(ElementusItems.MOVCADIA_PLANKS.get()))::save)
                .generateAdvancement()
                .build(writer, new ResourceLocation(getHasName(TWModItems.MOVCADIA_TABLE.get())));
//        .save(writer);
    }
    private void WitherStormmod(Consumer<FinishedRecipe> w) {
        cmdTool(w, ElementusItems.STEEL_SWORD, RecipeCategory.TOOLS, WitherstormModItems.STEEL_CMD_SWORD);
        cmdTool(w, ElementusItems.STEEL_SHOVEL, RecipeCategory.TOOLS, WitherstormModItems.STEEL_CMD_SHOVEL);
        cmdTool(w, ElementusItems.STEEL_PICKAXE, RecipeCategory.TOOLS, WitherstormModItems.STEEL_CMD_PICKAXE);
        cmdTool(w, ElementusItems.STEEL_AXE, RecipeCategory.TOOLS, WitherstormModItems.STEEL_CMD_AXE);
        cmdTool(w, ElementusItems.STEEL_HOE, RecipeCategory.TOOLS, WitherstormModItems.STEEL_CMD_HOE);
        cmdTool(w, ElementusItems.DIARKRITE_SWORD, RecipeCategory.TOOLS, WitherstormModItems.DIARKRITE_CMD_SWORD);
        cmdTool(w, ElementusItems.DIARKRITE_SHOVEL, RecipeCategory.TOOLS, WitherstormModItems.DIARKRITE_CMD_SHOVEL);
        cmdTool(w, ElementusItems.DIARKRITE_PICKAXE, RecipeCategory.TOOLS, WitherstormModItems.DIARKRITE_CMD_PICKAXE);
        cmdTool(w, ElementusItems.DIARKRITE_AXE, RecipeCategory.TOOLS, WitherstormModItems.DIARKRITE_CMD_AXE);
        cmdTool(w, ElementusItems.DIARKRITE_HOE, RecipeCategory.TOOLS, WitherstormModItems.DIARKRITE_CMD_HOE);
        cmdTool(w, ElementusItems.ANTHEKTITE_SWORD, RecipeCategory.TOOLS, WitherstormModItems.ANTHEKTITE_CMD_SWORD);
        cmdTool(w, ElementusItems.ANTHEKTITE_SHOVEL, RecipeCategory.TOOLS, WitherstormModItems.ANTHEKTITE_CMD_SHOVEL);
        cmdTool(w, ElementusItems.ANTHEKTITE_PICKAXE, RecipeCategory.TOOLS, WitherstormModItems.ANTHEKTITE_CMD_PICKAXE);
        cmdTool(w, ElementusItems.ANTHEKTITE_AXE, RecipeCategory.TOOLS, WitherstormModItems.ANTHEKTITE_CMD_AXE);
        cmdTool(w, ElementusItems.ANTHEKTITE_HOE, RecipeCategory.TOOLS, WitherstormModItems.ANTHEKTITE_CMD_HOE);
    }
    private void BanillaClaws(Consumer<FinishedRecipe> w) {
        ConditionalRecipe.builder().addCondition(and(modLoaded(vanillaClawsID))).addRecipe(c ->
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, BanillaClawsItems.STEEL_CLAWS.get())
                .define('#', Etags.Items.INGOTS_STEEL).define('L', net.minecraft.world.item.Items.LEATHER)
                .pattern("###")
                .pattern("LLL")
                .pattern(" L ")
                .unlockedBy(getHasName(ElementusItems.STEEL_INGOT.get()), has(Etags.Items.INGOTS_STEEL))
                .save(w));
        smithingCombatTransform(w, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, BanillaClawsItems.STEEL_CLAWS, Etags.Items.INGOTS_DIARKRITE, BanillaClawsItems.DIARKRITE_CLAWS, CriterionName.DIARKRITE.name, vanillaClawsID);
        smithingCombatTransform(w, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, BanillaClawsItems.STEEL_CLAWS, Etags.Items.INGOTS_ANTHEKTITE, BanillaClawsItems.ANTHEKTITE_CLAWS, CriterionName.ANTHEKTITE.name, vanillaClawsID);
    }
}
