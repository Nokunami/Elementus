package nokunami.elementus.datagen.generators;

import io.redspace.ironsspellbooks.registries.ItemRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import nokunami.elementus.common.Etags;
import nokunami.elementus.datagen.generators.create.CreateProcessingRecipe;
import nokunami.elementus.datagen.providers.ModRecipeProvider;
import nokunami.elementus.common.registry.ModBlocks.*;
import nokunami.elementus.common.registry.ModItems.*;
import net.veroxuniverse.samurai_dynasty.registry.ItemsRegistry;
import nl.sniffiandros.sniffsweapons.reg.ItemReg;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

import static nokunami.elementus.ModChecker.*;

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
        ANTHEKTITE ("has_anthektite_ingot");

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
//                .save(writer);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, FarmersDelightItems.STEEL_KNIFE, Etags.Items.INGOTS_DIARKRITE, FarmersDelightItems.DIARKRITE_KNIFE, CriterionName.DIARKRITE.name, farmersDelightID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, FarmersDelightItems.STEEL_KNIFE, Etags.Items.INGOTS_ANTHEKTITE, FarmersDelightItems.ANTHEKTITE_KNIFE, CriterionName.ANTHEKTITE.name, farmersDelightID);

        ConditionalRecipe.builder().addCondition(and(modLoaded(farmersDelightID), not(FALSE()))).addRecipe(
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, FarmersDelightItems.MOVCADIA_CABINET.get())
                .pattern("###").pattern("1 1").pattern("###").define('#', ElementusItems.MOVCADIA_SLAB.get()).define('1', ElementusItems.MOVCADIA_TRAPDOOR.get())
                .unlockedBy(getHasName(ElementusItems.MOVCADIA_PLANKS.get()), has(ElementusItems.MOVCADIA_PLANKS.get()))::save)
                .generateAdvancement()
                .build(writer, new ResourceLocation(getHasName(FarmersDelightItems.MOVCADIA_CABINET.get())));
//.save(writer);
    }
    private void PiercingPaxels(Consumer<FinishedRecipe> writer) {
        makePaxel(writer, PiercingPaxelsItems.STEEL_PAXEL, ElementusItems.STEEL_SWORD, ElementusItems.STEEL_SHOVEL, ElementusItems.STEEL_PICKAXE, ElementusItems.STEEL_AXE, ElementusItems.STEEL_HOE);
        makePaxel(writer, PiercingPaxelsItems.DIARKRITE_PAXEL, ElementusItems.DIARKRITE_SWORD, ElementusItems.DIARKRITE_SHOVEL, ElementusItems.DIARKRITE_PICKAXE, ElementusItems.DIARKRITE_AXE, ElementusItems.DIARKRITE_HOE);
        makePaxel(writer, PiercingPaxelsItems.ANTHEKTITE_PAXEL, ElementusItems.ANTHEKTITE_SWORD, ElementusItems.ANTHEKTITE_SHOVEL, ElementusItems.ANTHEKTITE_PICKAXE, ElementusItems.ANTHEKTITE_AXE, ElementusItems.ANTHEKTITE_HOE);

        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, PiercingPaxelsItems.STEEL_PAXEL, PiercingPaxelsItems.DIARKRITE_UPGRADE_KIT, PiercingPaxelsItems.DIARKRITE_PAXEL, CriterionName.DIARKRITE.name, piercingPaxelsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, PiercingPaxelsItems.STEEL_PAXEL, PiercingPaxelsItems.ANTHEKTITE_UPGRADE_KIT, PiercingPaxelsItems.ANTHEKTITE_PAXEL, CriterionName.ANTHEKTITE.name, piercingPaxelsID);

        makePaxelUpgradeKit(writer, PiercingPaxelsItems.DIARKRITE_UPGRADE_KIT, ElementusItems.DIARKRITE_INGOT);
        makePaxelUpgradeKit(writer, PiercingPaxelsItems.ANTHEKTITE_UPGRADE_KIT, ElementusItems.ANTHEKTITE_INGOT);
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
                ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, IronsSpellbooksItems.STEEL_SPELL_BOOK.get())
                        .pattern("NNA").pattern("IBA").pattern("NNA")
                        .define('N', Etags.Items.NUGGETS_STEEL)
                        .define('I', Etags.Items.INGOTS_STEEL)
                        .define('B', net.minecraft.world.item.Items.BOOK)
                        .define('A', ItemRegistry.ARCANE_ESSENCE.get())
                        .unlockedBy(CriterionName.STEEL.name, has(Etags.Items.INGOTS_STEEL))::save)
                .generateAdvancement()
                .build(writer, new ResourceLocation(getHasName(IronsSpellbooksItems.STEEL_SPELL_BOOK.get())));

        ConditionalRecipe.builder().addCondition(and(modLoaded(ironsSpellbooksID), not(FALSE()))).addRecipe(
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, IronsSpellbooksItems.DIARKRITE_SPELL_BOOK.get())
                .pattern("SAP").pattern("DRB").pattern("SAP")
                .define('S', Etags.Items.INGOTS_STEEL)
                .define('D', Etags.Items.INGOTS_DIARKRITE)
                .define('A', ItemRegistry.ARCANE_INGOT.get())
                .define('P', ItemRegistry.DIVINE_PEARL.get())
                .define('B', ItemRegistry.LIGHTNING_BOTTLE.get())
                .define('R', ItemRegistry.RUINED_BOOK.get())
                .unlockedBy(CriterionName.DIARKRITE.name, has(Etags.Items.INGOTS_DIARKRITE))::save)
                .generateAdvancement()
                .build(writer, new ResourceLocation(getHasName(IronsSpellbooksItems.DIARKRITE_SPELL_BOOK.get())));

        ConditionalRecipe.builder().addCondition(and(modLoaded(ironsSpellbooksID), not(FALSE()))).addRecipe(
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, IronsSpellbooksItems.ANTHEKTITE_SPELL_BOOK.get())
                .pattern("AAP").pattern("DRB").pattern("AAP")
                .define('D', Etags.Items.INGOTS_ANTHEKTITE)
                .define('A', ItemRegistry.ARCANE_INGOT.get())
                .define('P', ItemRegistry.DIVINE_PEARL.get())
                .define('B', ItemRegistry.LIGHTNING_BOTTLE.get())
                .define('R', ItemRegistry.RUINED_BOOK.get())
                .unlockedBy(CriterionName.ANTHEKTITE.name, has(Etags.Items.INGOTS_ANTHEKTITE))::save)
                .generateAdvancement()
                .build(writer, new ResourceLocation(getHasName(IronsSpellbooksItems.ANTHEKTITE_SPELL_BOOK.get())));

        issMageArmor(writer,
                IronsSpellbooksItems.DIARKRITE_MAGE_HELMET,
                IronsSpellbooksItems.DIARKRITE_MAGE_CHESTPLATE,
                IronsSpellbooksItems.DIARKRITE_MAGE_LEGGINGS,
                IronsSpellbooksItems.DIARKRITE_MAGE_BOOTS,
                Etags.Items.INGOTS_DIARKRITE, CriterionName.DIARKRITE.name);
        issMageArmor(writer,
                IronsSpellbooksItems.ANTHEKTITE_MAGE_HELMET,
                IronsSpellbooksItems.ANTHEKTITE_MAGE_CHESTPLATE,
                IronsSpellbooksItems.ANTHEKTITE_MAGE_LEGGINGS,
                IronsSpellbooksItems.ANTHEKTITE_MAGE_BOOTS,
                Etags.Items.INGOTS_ANTHEKTITE, CriterionName.ANTHEKTITE.name);
    }
    private void Aether(Consumer<FinishedRecipe> writer) {
        ConditionalRecipe.builder().addCondition(and(modLoaded(aetherID), not(FALSE()))).addRecipe(
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, AetherItems.STEEL_GLOVES.get())
                .pattern("# #").define('#', Etags.Items.INGOTS_STEEL)
                .unlockedBy(getHasName(ElementusItems.STEEL_INGOT.get()), has(ElementusItems.STEEL_INGOT.get()))::save)
                .build(writer, new ResourceLocation(getHasName(AetherItems.STEEL_GLOVES.get())));
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, AetherItems.STEEL_GLOVES, Etags.Items.INGOTS_DIARKRITE, AetherItems.DIARKRITE_GLOVES, CriterionName.DIARKRITE.name, aetherID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, AetherItems.STEEL_GLOVES, Etags.Items.INGOTS_ANTHEKTITE, AetherItems.ANTHEKTITE_GLOVES, CriterionName.ANTHEKTITE.name, aetherID);
    }
    private void SimplySwords(Consumer<FinishedRecipe> writer) {
        makeChakram(writer, SimplySwordsItems.STEEL_CHAKRAM, Etags.Items.INGOTS_STEEL, ElementusItems.STEEL_NUGGET, ElementusItems.STEEL_INGOT);
        makeClaymore(writer, SimplySwordsItems.STEEL_CLAYMORE, Etags.Items.INGOTS_STEEL, ElementusItems.STEEL_NUGGET, ElementusItems.STEEL_INGOT);
        makeCutlass(writer, SimplySwordsItems.STEEL_CUTLASS, Etags.Items.INGOTS_STEEL, ElementusItems.STEEL_NUGGET, ElementusItems.STEEL_INGOT);
        makeGlaive(writer, SimplySwordsItems.STEEL_GLAIVE, Etags.Items.INGOTS_STEEL, ElementusItems.STEEL_INGOT);
        makeGreataxe(writer, SimplySwordsItems.STEEL_GREATAXE, Etags.Items.INGOTS_STEEL, ElementusItems.STEEL_NUGGET, ElementusItems.STEEL_INGOT);
        makeGreathammer(writer, SimplySwordsItems.STEEL_GREATHAMMER, Etags.Items.INGOTS_STEEL, ElementusItems.STEEL_NUGGET, ElementusItems.STEEL_INGOT);
        makeHalberd(writer, SimplySwordsItems.STEEL_HALBERD, Etags.Items.INGOTS_STEEL, ElementusItems.STEEL_NUGGET, ElementusItems.STEEL_INGOT);
        makeKatana(writer, SimplySwordsItems.STEEL_KATANA, Etags.Items.INGOTS_STEEL, ElementusItems.STEEL_INGOT);
        makeLongSword(writer, SimplySwordsItems.STEEL_LONGSWORD, Etags.Items.INGOTS_STEEL, ElementusItems.STEEL_INGOT);
        makeRapier(writer, SimplySwordsItems.STEEL_RAPIER, Etags.Items.INGOTS_STEEL, ElementusItems.STEEL_INGOT);
        makeSai(writer, SimplySwordsItems.STEEL_SAI, Etags.Items.INGOTS_STEEL, ElementusItems.STEEL_INGOT);
        makeScythe(writer, SimplySwordsItems.STEEL_SCYTHE, Etags.Items.INGOTS_STEEL, ElementusItems.STEEL_INGOT);
        makeSpear(writer, SimplySwordsItems.STEEL_SPEAR, Etags.Items.INGOTS_STEEL, ElementusItems.STEEL_INGOT);
        makeTwinblade(writer, SimplySwordsItems.STEEL_TWINBLADE, Etags.Items.INGOTS_STEEL, ElementusItems.STEEL_INGOT);
        makeWarglaive(writer, SimplySwordsItems.STEEL_WARGLAIVE, Etags.Items.INGOTS_STEEL, ElementusItems.STEEL_NUGGET, ElementusItems.STEEL_INGOT);

        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SimplySwordsItems.STEEL_CHAKRAM, Etags.Items.INGOTS_DIARKRITE, SimplySwordsItems.DIARKRITE_CHAKRAM, CriterionName.DIARKRITE.name, simplySwordsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SimplySwordsItems.STEEL_CLAYMORE, Etags.Items.INGOTS_DIARKRITE, SimplySwordsItems.DIARKRITE_CLAYMORE, CriterionName.DIARKRITE.name, simplySwordsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SimplySwordsItems.STEEL_CUTLASS, Etags.Items.INGOTS_DIARKRITE, SimplySwordsItems.DIARKRITE_CUTLASS, CriterionName.DIARKRITE.name, simplySwordsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SimplySwordsItems.STEEL_GLAIVE, Etags.Items.INGOTS_DIARKRITE, SimplySwordsItems.DIARKRITE_GLAIVE, CriterionName.DIARKRITE.name, simplySwordsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SimplySwordsItems.STEEL_GREATAXE, Etags.Items.INGOTS_DIARKRITE, SimplySwordsItems.DIARKRITE_GREATAXE, CriterionName.DIARKRITE.name, simplySwordsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SimplySwordsItems.STEEL_GREATHAMMER, Etags.Items.INGOTS_DIARKRITE, SimplySwordsItems.DIARKRITE_GREATHAMMER, CriterionName.DIARKRITE.name, simplySwordsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SimplySwordsItems.STEEL_HALBERD, Etags.Items.INGOTS_DIARKRITE, SimplySwordsItems.DIARKRITE_HALBERD, CriterionName.DIARKRITE.name, simplySwordsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SimplySwordsItems.STEEL_KATANA, Etags.Items.INGOTS_DIARKRITE, SimplySwordsItems.DIARKRITE_KATANA, CriterionName.DIARKRITE.name, simplySwordsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SimplySwordsItems.STEEL_LONGSWORD, Etags.Items.INGOTS_DIARKRITE, SimplySwordsItems.DIARKRITE_LONGSWORD, CriterionName.DIARKRITE.name, simplySwordsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SimplySwordsItems.STEEL_RAPIER, Etags.Items.INGOTS_DIARKRITE, SimplySwordsItems.DIARKRITE_RAPIER, CriterionName.DIARKRITE.name, simplySwordsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SimplySwordsItems.STEEL_SAI, Etags.Items.INGOTS_DIARKRITE, SimplySwordsItems.DIARKRITE_SAI, CriterionName.DIARKRITE.name, simplySwordsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SimplySwordsItems.STEEL_SCYTHE, Etags.Items.INGOTS_DIARKRITE, SimplySwordsItems.DIARKRITE_SCYTHE, CriterionName.DIARKRITE.name, simplySwordsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SimplySwordsItems.STEEL_SPEAR, Etags.Items.INGOTS_DIARKRITE, SimplySwordsItems.DIARKRITE_SPEAR, CriterionName.DIARKRITE.name, simplySwordsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SimplySwordsItems.STEEL_TWINBLADE, Etags.Items.INGOTS_DIARKRITE, SimplySwordsItems.DIARKRITE_TWINBLADE, CriterionName.DIARKRITE.name, simplySwordsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SimplySwordsItems.STEEL_WARGLAIVE, Etags.Items.INGOTS_DIARKRITE, SimplySwordsItems.DIARKRITE_WARGLAIVE, CriterionName.DIARKRITE.name, simplySwordsID);

        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SimplySwordsItems.STEEL_CHAKRAM, Etags.Items.INGOTS_ANTHEKTITE, SimplySwordsItems.ANTHEKTITE_CHAKRAM, CriterionName.ANTHEKTITE.name, simplySwordsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SimplySwordsItems.STEEL_CLAYMORE, Etags.Items.INGOTS_ANTHEKTITE, SimplySwordsItems.ANTHEKTITE_CLAYMORE, CriterionName.ANTHEKTITE.name, simplySwordsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SimplySwordsItems.STEEL_CUTLASS, Etags.Items.INGOTS_ANTHEKTITE, SimplySwordsItems.ANTHEKTITE_CUTLASS, CriterionName.ANTHEKTITE.name, simplySwordsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SimplySwordsItems.STEEL_GLAIVE, Etags.Items.INGOTS_ANTHEKTITE, SimplySwordsItems.ANTHEKTITE_GLAIVE, CriterionName.ANTHEKTITE.name, simplySwordsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SimplySwordsItems.STEEL_GREATAXE, Etags.Items.INGOTS_ANTHEKTITE, SimplySwordsItems.ANTHEKTITE_GREATAXE, CriterionName.ANTHEKTITE.name, simplySwordsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SimplySwordsItems.STEEL_GREATHAMMER, Etags.Items.INGOTS_ANTHEKTITE, SimplySwordsItems.ANTHEKTITE_GREATHAMMER, CriterionName.ANTHEKTITE.name, simplySwordsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SimplySwordsItems.STEEL_HALBERD, Etags.Items.INGOTS_ANTHEKTITE, SimplySwordsItems.ANTHEKTITE_HALBERD, CriterionName.ANTHEKTITE.name, simplySwordsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SimplySwordsItems.STEEL_KATANA, Etags.Items.INGOTS_ANTHEKTITE, SimplySwordsItems.ANTHEKTITE_KATANA, CriterionName.ANTHEKTITE.name, simplySwordsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SimplySwordsItems.STEEL_LONGSWORD, Etags.Items.INGOTS_ANTHEKTITE, SimplySwordsItems.ANTHEKTITE_LONGSWORD, CriterionName.ANTHEKTITE.name, simplySwordsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SimplySwordsItems.STEEL_RAPIER, Etags.Items.INGOTS_ANTHEKTITE, SimplySwordsItems.ANTHEKTITE_RAPIER, CriterionName.ANTHEKTITE.name, simplySwordsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SimplySwordsItems.STEEL_SAI, Etags.Items.INGOTS_ANTHEKTITE, SimplySwordsItems.ANTHEKTITE_SAI, CriterionName.ANTHEKTITE.name, simplySwordsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SimplySwordsItems.STEEL_SCYTHE, Etags.Items.INGOTS_ANTHEKTITE, SimplySwordsItems.ANTHEKTITE_SCYTHE, CriterionName.ANTHEKTITE.name, simplySwordsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SimplySwordsItems.STEEL_SPEAR, Etags.Items.INGOTS_ANTHEKTITE, SimplySwordsItems.ANTHEKTITE_SPEAR, CriterionName.ANTHEKTITE.name, simplySwordsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SimplySwordsItems.STEEL_TWINBLADE, Etags.Items.INGOTS_ANTHEKTITE, SimplySwordsItems.ANTHEKTITE_TWINBLADE, CriterionName.ANTHEKTITE.name, simplySwordsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SimplySwordsItems.STEEL_WARGLAIVE, Etags.Items.INGOTS_ANTHEKTITE, SimplySwordsItems.ANTHEKTITE_WARGLAIVE, CriterionName.ANTHEKTITE.name, simplySwordsID);
    }
    private void SniffsWeapons(Consumer<FinishedRecipe> writer) {
        makeGreatAxe(writer, SniffsWeaponsItems.STEEL_GREAT_AXE, Etags.Items.INGOTS_STEEL, ElementusItems.STEEL_AXE);
        makeGreatPickaxe(writer, SniffsWeaponsItems.STEEL_GREAT_PICKAXE, Etags.Items.INGOTS_STEEL, ElementusItems.STEEL_PICKAXE);
        makeGreatSword(writer, SniffsWeaponsItems.STEEL_GREAT_SWORD, Etags.Items.INGOTS_STEEL, ElementusItems.STEEL_SWORD);
        makeNaginata(writer, SniffsWeaponsItems.STEEL_NAGINATA, Etags.Items.INGOTS_STEEL, ElementusItems.STEEL_SWORD);

        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SniffsWeaponsItems.STEEL_GREAT_AXE, Etags.Items.INGOTS_DIARKRITE, SniffsWeaponsItems.DIARKRITE_GREAT_AXE, CriterionName.DIARKRITE.name, sniffsWeaponsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SniffsWeaponsItems.STEEL_GREAT_PICKAXE, Etags.Items.INGOTS_DIARKRITE, SniffsWeaponsItems.DIARKRITE_GREAT_PICKAXE, CriterionName.DIARKRITE.name, sniffsWeaponsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SniffsWeaponsItems.STEEL_GREAT_SWORD, Etags.Items.INGOTS_DIARKRITE, SniffsWeaponsItems.DIARKRITE_GREAT_SWORD, CriterionName.DIARKRITE.name, sniffsWeaponsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SniffsWeaponsItems.STEEL_NAGINATA, Etags.Items.INGOTS_DIARKRITE, SniffsWeaponsItems.DIARKRITE_NAGINATA, CriterionName.DIARKRITE.name, sniffsWeaponsID);

        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SniffsWeaponsItems.STEEL_GREAT_AXE, Etags.Items.INGOTS_ANTHEKTITE, SniffsWeaponsItems.ANTHEKTITE_GREAT_AXE, CriterionName.ANTHEKTITE.name, sniffsWeaponsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SniffsWeaponsItems.STEEL_GREAT_PICKAXE, Etags.Items.INGOTS_ANTHEKTITE, SniffsWeaponsItems.ANTHEKTITE_GREAT_PICKAXE, CriterionName.ANTHEKTITE.name, sniffsWeaponsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SniffsWeaponsItems.STEEL_GREAT_SWORD, Etags.Items.INGOTS_ANTHEKTITE, SniffsWeaponsItems.ANTHEKTITE_GREAT_SWORD, CriterionName.ANTHEKTITE.name, sniffsWeaponsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SniffsWeaponsItems.STEEL_NAGINATA, Etags.Items.INGOTS_DIARKRITE, SniffsWeaponsItems.ANTHEKTITE_NAGINATA, CriterionName.ANTHEKTITE.name, sniffsWeaponsID);

        smithingSniffWeaponConvert(writer, ItemReg.HELM_ARMOR_TRIM_SMITHING_TEMPLATE, ElementusItems.STEEL_HELMET, net.minecraft.world.item.Items.FEATHER, SniffsWeaponsItems.STEEL_HELM);
        smithingSniffWeaponConvert(writer, ItemReg.SURCOAT_ARMOR_TRIM_SMITHING_TEMPLATE, ElementusItems.STEEL_CHESTPLATE, net.minecraft.world.item.Items.LEATHER, SniffsWeaponsItems.STEEL_SURCOAT);
        smithingSniffWeaponConvert(writer, ItemReg.HORNED_ARMOR_TRIM_SMITHING_TEMPLATE, ElementusItems.STEEL_HELMET, net.minecraft.world.item.Items.BONE, SniffsWeaponsItems.STEEL_HORNED_HELM);
        smithingSniffWeaponConvert(writer, ItemReg.PLATED_ARMOR_TRIM_SMITHING_TEMPLATE, ElementusItems.STEEL_CHESTPLATE, net.minecraft.world.item.Items.IRON_INGOT, SniffsWeaponsItems.PLATED_STEEL_CHESTPLATE);
        smithingSniffWeaponConvert(writer, ItemReg.SAMURAI_ARMOR_TRIM_SMITHING_TEMPLATE, ElementusItems.STEEL_HELMET, net.minecraft.world.item.Items.GOLD_NUGGET, SniffsWeaponsItems.STEEL_KABUTO);
        smithingSniffWeaponConvert(writer, ItemReg.SAMURAI_ARMOR_TRIM_SMITHING_TEMPLATE, ElementusItems.STEEL_CHESTPLATE, net.minecraft.world.item.Items.GOLD_NUGGET, SniffsWeaponsItems.STEEL_DO);

        smithingSniffWeaponConvert(writer, ItemReg.HELM_ARMOR_TRIM_SMITHING_TEMPLATE, ElementusItems.DIARKRITE_HELMET, net.minecraft.world.item.Items.FEATHER, SniffsWeaponsItems.DIARKRITE_HELM);
        smithingSniffWeaponConvert(writer, ItemReg.SURCOAT_ARMOR_TRIM_SMITHING_TEMPLATE, ElementusItems.DIARKRITE_CHESTPLATE, net.minecraft.world.item.Items.LEATHER, SniffsWeaponsItems.DIARKRITE_SURCOAT);
        smithingSniffWeaponConvert(writer, ItemReg.HORNED_ARMOR_TRIM_SMITHING_TEMPLATE, ElementusItems.DIARKRITE_HELMET, net.minecraft.world.item.Items.BONE, SniffsWeaponsItems.DIARKRITE_HORNED_HELM);
        smithingSniffWeaponConvert(writer, ItemReg.PLATED_ARMOR_TRIM_SMITHING_TEMPLATE, ElementusItems.DIARKRITE_CHESTPLATE, net.minecraft.world.item.Items.IRON_INGOT, SniffsWeaponsItems.PLATED_DIARKRITE_CHESTPLATE);
        smithingSniffWeaponConvert(writer, ItemReg.SAMURAI_ARMOR_TRIM_SMITHING_TEMPLATE, ElementusItems.DIARKRITE_HELMET, net.minecraft.world.item.Items.GOLD_NUGGET, SniffsWeaponsItems.DIARKRITE_KABUTO);
        smithingSniffWeaponConvert(writer, ItemReg.SAMURAI_ARMOR_TRIM_SMITHING_TEMPLATE, ElementusItems.DIARKRITE_CHESTPLATE, net.minecraft.world.item.Items.GOLD_NUGGET, SniffsWeaponsItems.DIARKRITE_DO);

        smithingSniffWeaponConvert(writer, ItemReg.HELM_ARMOR_TRIM_SMITHING_TEMPLATE, ElementusItems.ANTHEKTITE_HELMET, net.minecraft.world.item.Items.FEATHER, SniffsWeaponsItems.ANTHEKTITE_HELM);
        smithingSniffWeaponConvert(writer, ItemReg.SURCOAT_ARMOR_TRIM_SMITHING_TEMPLATE, ElementusItems.ANTHEKTITE_CHESTPLATE, net.minecraft.world.item.Items.LEATHER, SniffsWeaponsItems.ANTHEKTITE_SURCOAT);
        smithingSniffWeaponConvert(writer, ItemReg.HORNED_ARMOR_TRIM_SMITHING_TEMPLATE, ElementusItems.ANTHEKTITE_HELMET, net.minecraft.world.item.Items.BONE, SniffsWeaponsItems.ANTHEKTITE_HORNED_HELM);
        smithingSniffWeaponConvert(writer, ItemReg.PLATED_ARMOR_TRIM_SMITHING_TEMPLATE, ElementusItems.ANTHEKTITE_CHESTPLATE, net.minecraft.world.item.Items.IRON_INGOT, SniffsWeaponsItems.PLATED_ANTHEKTITE_CHESTPLATE);
        smithingSniffWeaponConvert(writer, ItemReg.SAMURAI_ARMOR_TRIM_SMITHING_TEMPLATE, ElementusItems.ANTHEKTITE_HELMET, net.minecraft.world.item.Items.GOLD_NUGGET, SniffsWeaponsItems.ANTHEKTITE_KABUTO);
        smithingSniffWeaponConvert(writer, ItemReg.SAMURAI_ARMOR_TRIM_SMITHING_TEMPLATE, ElementusItems.ANTHEKTITE_CHESTPLATE, net.minecraft.world.item.Items.GOLD_NUGGET, SniffsWeaponsItems.ANTHEKTITE_DO);

        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SniffsWeaponsItems.STEEL_HELM, Etags.Items.INGOTS_DIARKRITE, SniffsWeaponsItems.DIARKRITE_HELM, CriterionName.DIARKRITE.name, sniffsWeaponsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SniffsWeaponsItems.STEEL_SURCOAT, Etags.Items.INGOTS_DIARKRITE, SniffsWeaponsItems.DIARKRITE_SURCOAT, CriterionName.DIARKRITE.name, sniffsWeaponsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SniffsWeaponsItems.STEEL_HORNED_HELM, Etags.Items.INGOTS_DIARKRITE, SniffsWeaponsItems.DIARKRITE_HORNED_HELM, CriterionName.DIARKRITE.name, sniffsWeaponsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SniffsWeaponsItems.PLATED_STEEL_CHESTPLATE, Etags.Items.INGOTS_DIARKRITE, SniffsWeaponsItems.PLATED_DIARKRITE_CHESTPLATE, CriterionName.DIARKRITE.name, sniffsWeaponsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SniffsWeaponsItems.STEEL_KABUTO, Etags.Items.INGOTS_DIARKRITE, SniffsWeaponsItems.DIARKRITE_KABUTO, CriterionName.DIARKRITE.name, sniffsWeaponsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SniffsWeaponsItems.STEEL_DO, Etags.Items.INGOTS_DIARKRITE, SniffsWeaponsItems.DIARKRITE_DO, CriterionName.DIARKRITE.name, sniffsWeaponsID);

        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SniffsWeaponsItems.STEEL_HELM, Etags.Items.INGOTS_ANTHEKTITE, SniffsWeaponsItems.ANTHEKTITE_HELM, CriterionName.ANTHEKTITE.name, sniffsWeaponsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SniffsWeaponsItems.STEEL_SURCOAT, Etags.Items.INGOTS_ANTHEKTITE, SniffsWeaponsItems.ANTHEKTITE_SURCOAT, CriterionName.ANTHEKTITE.name, sniffsWeaponsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SniffsWeaponsItems.STEEL_HORNED_HELM, Etags.Items.INGOTS_ANTHEKTITE, SniffsWeaponsItems.ANTHEKTITE_HORNED_HELM, CriterionName.ANTHEKTITE.name, sniffsWeaponsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SniffsWeaponsItems.PLATED_STEEL_CHESTPLATE, Etags.Items.INGOTS_ANTHEKTITE, SniffsWeaponsItems.PLATED_ANTHEKTITE_CHESTPLATE, CriterionName.ANTHEKTITE.name, sniffsWeaponsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SniffsWeaponsItems.STEEL_KABUTO, Etags.Items.INGOTS_ANTHEKTITE, SniffsWeaponsItems.ANTHEKTITE_KABUTO, CriterionName.ANTHEKTITE.name, sniffsWeaponsID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, SniffsWeaponsItems.STEEL_DO, Etags.Items.INGOTS_ANTHEKTITE, SniffsWeaponsItems.ANTHEKTITE_DO, CriterionName.ANTHEKTITE.name, sniffsWeaponsID);
    }
    private void AdvancedNetherite(Consumer<FinishedRecipe> writer) {
        ingotBlockRecipes(writer, AdvancedNetheriteItems.DIARKRITE_IRON.get(), AdvancedNetheriteBlocks.DIARKRITE_IRON_BLOCK.get(), ElementusItems.DIARKRITE_INGOT.get(), Tags.Items.INGOTS_IRON, AdvancedNetheriteItems.DIARKRITE_IRON.get());
        ingotBlockRecipes(writer, AdvancedNetheriteItems.DIARKRITE_GOLD.get(), AdvancedNetheriteBlocks.DIARKRITE_GOLD_BLOCK.get(), AdvancedNetheriteItems.DIARKRITE_IRON.get(), Tags.Items.INGOTS_GOLD, AdvancedNetheriteItems.DIARKRITE_GOLD.get());
        ingotBlockRecipes(writer, AdvancedNetheriteItems.DIARKRITE_EMERALD.get(), AdvancedNetheriteBlocks.DIARKRITE_EMERALD_BLOCK.get(), AdvancedNetheriteItems.DIARKRITE_GOLD.get(), Tags.Items.GEMS_EMERALD, AdvancedNetheriteItems.DIARKRITE_EMERALD.get());
        ingotBlockRecipes(writer, AdvancedNetheriteItems.DIARKRITE_DIAMOND.get(), AdvancedNetheriteBlocks.DIARKRITE_DIAMOND_BLOCK.get(), AdvancedNetheriteItems.DIARKRITE_EMERALD.get(), Tags.Items.GEMS_DIAMOND, AdvancedNetheriteItems.DIARKRITE_DIAMOND.get());

        ingotBlockRecipes(writer, AdvancedNetheriteItems.ANTHEKTITE_IRON.get(), AdvancedNetheriteBlocks.ANTHEKTITE_IRON_BLOCK.get(), ElementusItems.ANTHEKTITE_INGOT.get(), Tags.Items.INGOTS_IRON, AdvancedNetheriteItems.ANTHEKTITE_IRON.get());
        ingotBlockRecipes(writer, AdvancedNetheriteItems.ANTHEKTITE_GOLD.get(), AdvancedNetheriteBlocks.ANTHEKTITE_GOLD_BLOCK.get(), AdvancedNetheriteItems.ANTHEKTITE_IRON.get(), Tags.Items.INGOTS_GOLD, AdvancedNetheriteItems.ANTHEKTITE_GOLD.get());
        ingotBlockRecipes(writer, AdvancedNetheriteItems.ANTHEKTITE_EMERALD.get(), AdvancedNetheriteBlocks.ANTHEKTITE_EMERALD_BLOCK.get(), AdvancedNetheriteItems.ANTHEKTITE_GOLD.get(), Tags.Items.GEMS_EMERALD, AdvancedNetheriteItems.ANTHEKTITE_EMERALD.get());
        ingotBlockRecipes(writer, AdvancedNetheriteItems.ANTHEKTITE_DIAMOND.get(), AdvancedNetheriteBlocks.ANTHEKTITE_DIAMOND_BLOCK.get(), AdvancedNetheriteItems.ANTHEKTITE_EMERALD.get(), Tags.Items.GEMS_DIAMOND, AdvancedNetheriteItems.ANTHEKTITE_DIAMOND.get());


        makeAdvancedNetheriteTools(writer, ElementusItems.DIARKRITE_SWORD, ElementusItems.DIARKRITE_SHOVEL, ElementusItems.DIARKRITE_PICKAXE, ElementusItems.DIARKRITE_AXE, ElementusItems.DIARKRITE_HOE, Etags.Items.INGOTS_DIARKRITE,
                AdvancedNetheriteItems.DIARKRITE_IRON_SWORD, AdvancedNetheriteItems.DIARKRITE_IRON_SHOVEL, AdvancedNetheriteItems.DIARKRITE_IRON_PICKAXE, AdvancedNetheriteItems.DIARKRITE_IRON_AXE, AdvancedNetheriteItems.DIARKRITE_IRON_HOE);

        makeAdvancedNetheriteTools(writer, AdvancedNetheriteItems.DIARKRITE_IRON_SWORD, AdvancedNetheriteItems.DIARKRITE_IRON_SHOVEL, AdvancedNetheriteItems.DIARKRITE_IRON_PICKAXE, AdvancedNetheriteItems.DIARKRITE_IRON_AXE, AdvancedNetheriteItems.DIARKRITE_IRON_HOE, Etags.Items.INGOTS_DIARKRITE_GOLD,
                AdvancedNetheriteItems.DIARKRITE_GOLD_SWORD, AdvancedNetheriteItems.DIARKRITE_GOLD_SHOVEL, AdvancedNetheriteItems.DIARKRITE_GOLD_PICKAXE, AdvancedNetheriteItems.DIARKRITE_GOLD_AXE, AdvancedNetheriteItems.DIARKRITE_GOLD_HOE);

        makeAdvancedNetheriteTools(writer, AdvancedNetheriteItems.DIARKRITE_GOLD_SWORD, AdvancedNetheriteItems.DIARKRITE_GOLD_SHOVEL, AdvancedNetheriteItems.DIARKRITE_GOLD_PICKAXE, AdvancedNetheriteItems.DIARKRITE_GOLD_AXE, AdvancedNetheriteItems.DIARKRITE_GOLD_HOE, Etags.Items.INGOTS_DIARKRITE_EMERALD,
                AdvancedNetheriteItems.DIARKRITE_EMERALD_SWORD, AdvancedNetheriteItems.DIARKRITE_EMERALD_SHOVEL, AdvancedNetheriteItems.DIARKRITE_EMERALD_PICKAXE, AdvancedNetheriteItems.DIARKRITE_EMERALD_AXE, AdvancedNetheriteItems.DIARKRITE_EMERALD_HOE);

        makeAdvancedNetheriteTools(writer, AdvancedNetheriteItems.DIARKRITE_EMERALD_SWORD, AdvancedNetheriteItems.DIARKRITE_EMERALD_SHOVEL, AdvancedNetheriteItems.DIARKRITE_EMERALD_PICKAXE, AdvancedNetheriteItems.DIARKRITE_EMERALD_AXE, AdvancedNetheriteItems.DIARKRITE_EMERALD_HOE, Etags.Items.INGOTS_DIARKRITE_DIAMOND,
                AdvancedNetheriteItems.DIARKRITE_DIAMOND_SWORD, AdvancedNetheriteItems.DIARKRITE_DIAMOND_SHOVEL, AdvancedNetheriteItems.DIARKRITE_DIAMOND_PICKAXE, AdvancedNetheriteItems.DIARKRITE_DIAMOND_AXE, AdvancedNetheriteItems.DIARKRITE_DIAMOND_HOE);


        makeAdvancedNetheriteArmors(writer, ElementusItems.DIARKRITE_HELMET, ElementusItems.DIARKRITE_CHESTPLATE, ElementusItems.DIARKRITE_LEGGINGS, ElementusItems.DIARKRITE_BOOTS, Etags.Items.INGOTS_DIARKRITE_IRON,
                AdvancedNetheriteItems.DIARKRITE_IRON_HELMET, AdvancedNetheriteItems.DIARKRITE_IRON_CHESTPLATE, AdvancedNetheriteItems.DIARKRITE_IRON_LEGGINGS, AdvancedNetheriteItems.DIARKRITE_IRON_BOOTS);

        makeAdvancedNetheriteArmors(writer, AdvancedNetheriteItems.DIARKRITE_IRON_HELMET, AdvancedNetheriteItems.DIARKRITE_IRON_CHESTPLATE, AdvancedNetheriteItems.DIARKRITE_IRON_LEGGINGS, AdvancedNetheriteItems.DIARKRITE_IRON_BOOTS, Etags.Items.INGOTS_DIARKRITE_GOLD,
                AdvancedNetheriteItems.DIARKRITE_GOLD_HELMET, AdvancedNetheriteItems.DIARKRITE_GOLD_CHESTPLATE, AdvancedNetheriteItems.DIARKRITE_GOLD_LEGGINGS, AdvancedNetheriteItems.DIARKRITE_GOLD_BOOTS);

        makeAdvancedNetheriteArmors(writer, AdvancedNetheriteItems.DIARKRITE_GOLD_HELMET, AdvancedNetheriteItems.DIARKRITE_GOLD_CHESTPLATE, AdvancedNetheriteItems.DIARKRITE_GOLD_LEGGINGS, AdvancedNetheriteItems.DIARKRITE_GOLD_BOOTS, Etags.Items.INGOTS_DIARKRITE_EMERALD,
                AdvancedNetheriteItems.DIARKRITE_EMERALD_HELMET, AdvancedNetheriteItems.DIARKRITE_EMERALD_CHESTPLATE, AdvancedNetheriteItems.DIARKRITE_EMERALD_LEGGINGS, AdvancedNetheriteItems.DIARKRITE_EMERALD_BOOTS);

        makeAdvancedNetheriteArmors(writer, AdvancedNetheriteItems.DIARKRITE_EMERALD_HELMET, AdvancedNetheriteItems.DIARKRITE_EMERALD_CHESTPLATE, AdvancedNetheriteItems.DIARKRITE_EMERALD_LEGGINGS, AdvancedNetheriteItems.DIARKRITE_EMERALD_BOOTS, Etags.Items.INGOTS_DIARKRITE_DIAMOND,
                AdvancedNetheriteItems.DIARKRITE_DIAMOND_HELMET, AdvancedNetheriteItems.DIARKRITE_DIAMOND_CHESTPLATE, AdvancedNetheriteItems.DIARKRITE_DIAMOND_LEGGINGS, AdvancedNetheriteItems.DIARKRITE_DIAMOND_BOOTS);



        makeAdvancedNetheriteTools(writer, ElementusItems.ANTHEKTITE_SWORD, ElementusItems.ANTHEKTITE_SHOVEL, ElementusItems.ANTHEKTITE_PICKAXE, ElementusItems.ANTHEKTITE_AXE, ElementusItems.ANTHEKTITE_HOE, Etags.Items.INGOTS_ANTHEKTITE_IRON,
                AdvancedNetheriteItems.ANTHEKTITE_IRON_SWORD, AdvancedNetheriteItems.ANTHEKTITE_IRON_SHOVEL, AdvancedNetheriteItems.ANTHEKTITE_IRON_PICKAXE, AdvancedNetheriteItems.ANTHEKTITE_IRON_AXE, AdvancedNetheriteItems.ANTHEKTITE_IRON_HOE);

        makeAdvancedNetheriteTools(writer, AdvancedNetheriteItems.ANTHEKTITE_IRON_SWORD, AdvancedNetheriteItems.ANTHEKTITE_IRON_SHOVEL, AdvancedNetheriteItems.ANTHEKTITE_IRON_PICKAXE, AdvancedNetheriteItems.ANTHEKTITE_IRON_AXE, AdvancedNetheriteItems.ANTHEKTITE_IRON_HOE, Etags.Items.INGOTS_ANTHEKTITE_GOLD,
                AdvancedNetheriteItems.ANTHEKTITE_GOLD_SWORD, AdvancedNetheriteItems.ANTHEKTITE_GOLD_SHOVEL, AdvancedNetheriteItems.ANTHEKTITE_GOLD_PICKAXE, AdvancedNetheriteItems.ANTHEKTITE_GOLD_AXE, AdvancedNetheriteItems.ANTHEKTITE_GOLD_HOE);

        makeAdvancedNetheriteTools(writer, AdvancedNetheriteItems.ANTHEKTITE_GOLD_SWORD, AdvancedNetheriteItems.ANTHEKTITE_GOLD_SHOVEL, AdvancedNetheriteItems.ANTHEKTITE_GOLD_PICKAXE, AdvancedNetheriteItems.ANTHEKTITE_GOLD_AXE, AdvancedNetheriteItems.ANTHEKTITE_GOLD_HOE, Etags.Items.INGOTS_ANTHEKTITE_EMERALD,
                AdvancedNetheriteItems.ANTHEKTITE_EMERALD_SWORD, AdvancedNetheriteItems.ANTHEKTITE_EMERALD_SHOVEL, AdvancedNetheriteItems.ANTHEKTITE_EMERALD_PICKAXE, AdvancedNetheriteItems.ANTHEKTITE_EMERALD_AXE, AdvancedNetheriteItems.ANTHEKTITE_EMERALD_HOE);

        makeAdvancedNetheriteTools(writer, AdvancedNetheriteItems.ANTHEKTITE_EMERALD_SWORD, AdvancedNetheriteItems.ANTHEKTITE_EMERALD_SHOVEL, AdvancedNetheriteItems.ANTHEKTITE_EMERALD_PICKAXE, AdvancedNetheriteItems.ANTHEKTITE_EMERALD_AXE, AdvancedNetheriteItems.ANTHEKTITE_EMERALD_HOE, Etags.Items.INGOTS_ANTHEKTITE_DIAMOND,
                AdvancedNetheriteItems.ANTHEKTITE_DIAMOND_SWORD, AdvancedNetheriteItems.ANTHEKTITE_DIAMOND_SHOVEL, AdvancedNetheriteItems.ANTHEKTITE_DIAMOND_PICKAXE, AdvancedNetheriteItems.ANTHEKTITE_DIAMOND_AXE, AdvancedNetheriteItems.ANTHEKTITE_DIAMOND_HOE);


        makeAdvancedNetheriteArmors(writer, ElementusItems.ANTHEKTITE_HELMET, ElementusItems.ANTHEKTITE_CHESTPLATE, ElementusItems.ANTHEKTITE_LEGGINGS, ElementusItems.ANTHEKTITE_BOOTS, Etags.Items.INGOTS_ANTHEKTITE_IRON,
                AdvancedNetheriteItems.ANTHEKTITE_IRON_HELMET, AdvancedNetheriteItems.ANTHEKTITE_IRON_CHESTPLATE, AdvancedNetheriteItems.ANTHEKTITE_IRON_LEGGINGS, AdvancedNetheriteItems.ANTHEKTITE_IRON_BOOTS);

        makeAdvancedNetheriteArmors(writer, AdvancedNetheriteItems.ANTHEKTITE_IRON_HELMET, AdvancedNetheriteItems.ANTHEKTITE_IRON_CHESTPLATE, AdvancedNetheriteItems.ANTHEKTITE_IRON_LEGGINGS, AdvancedNetheriteItems.ANTHEKTITE_IRON_BOOTS, Etags.Items.INGOTS_ANTHEKTITE_GOLD,
                AdvancedNetheriteItems.ANTHEKTITE_GOLD_HELMET, AdvancedNetheriteItems.ANTHEKTITE_GOLD_CHESTPLATE, AdvancedNetheriteItems.ANTHEKTITE_GOLD_LEGGINGS, AdvancedNetheriteItems.ANTHEKTITE_GOLD_BOOTS);

        makeAdvancedNetheriteArmors(writer, AdvancedNetheriteItems.ANTHEKTITE_GOLD_HELMET, AdvancedNetheriteItems.ANTHEKTITE_GOLD_CHESTPLATE, AdvancedNetheriteItems.ANTHEKTITE_GOLD_LEGGINGS, AdvancedNetheriteItems.ANTHEKTITE_GOLD_BOOTS, Etags.Items.INGOTS_ANTHEKTITE_EMERALD,
                AdvancedNetheriteItems.ANTHEKTITE_EMERALD_HELMET, AdvancedNetheriteItems.ANTHEKTITE_EMERALD_CHESTPLATE, AdvancedNetheriteItems.ANTHEKTITE_EMERALD_LEGGINGS, AdvancedNetheriteItems.ANTHEKTITE_EMERALD_BOOTS);

        makeAdvancedNetheriteArmors(writer, AdvancedNetheriteItems.ANTHEKTITE_EMERALD_HELMET, AdvancedNetheriteItems.ANTHEKTITE_EMERALD_CHESTPLATE, AdvancedNetheriteItems.ANTHEKTITE_EMERALD_LEGGINGS, AdvancedNetheriteItems.ANTHEKTITE_EMERALD_BOOTS, Etags.Items.INGOTS_ANTHEKTITE_DIAMOND,
                AdvancedNetheriteItems.ANTHEKTITE_DIAMOND_HELMET, AdvancedNetheriteItems.ANTHEKTITE_DIAMOND_CHESTPLATE, AdvancedNetheriteItems.ANTHEKTITE_DIAMOND_LEGGINGS, AdvancedNetheriteItems.ANTHEKTITE_DIAMOND_BOOTS);
    }
    private void EpicSamurai(Consumer<FinishedRecipe> writer) {
        ConditionalRecipe.builder().addCondition(and(modLoaded(samuraiDynastyID))).addRecipe(c ->
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, EpicSamuraiItems.STEEL_SAMURAI_HELMET.get())
                .define('#', Etags.Items.INGOTS_STEEL).define('J', ItemsRegistry.JADE.get()).define('I', Tags.Items.INGOTS_IRON)
                .pattern(" # ")
                .pattern("IJI")
                .pattern("# #")
                .unlockedBy(getHasName(ElementusItems.STEEL_INGOT.get()), has(Etags.Items.INGOTS_STEEL))
                .save(writer));
        ConditionalRecipe.builder().addCondition(and(modLoaded(samuraiDynastyID))).addRecipe(c ->
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, EpicSamuraiItems.STEEL_SAMURAI_CHESTPLATE.get())
                .define('#', Etags.Items.INGOTS_STEEL).define('I', Tags.Items.INGOTS_IRON)
                .pattern("# #")
                .pattern("I#I")
                .pattern("#I#")
                .unlockedBy(getHasName(ElementusItems.STEEL_INGOT.get()), has(Etags.Items.INGOTS_STEEL))
                .save(writer));
        ConditionalRecipe.builder().addCondition(and(modLoaded(samuraiDynastyID))).addRecipe(c ->
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, EpicSamuraiItems.STEEL_SAMURAI_LEGGINGS.get())
                .define('#', Etags.Items.INGOTS_STEEL).define('I', Tags.Items.INGOTS_IRON)
                .pattern("###")
                .pattern("I I")
                .pattern("# #")
                .unlockedBy(getHasName(ElementusItems.STEEL_INGOT.get()), has(Etags.Items.INGOTS_STEEL))
                .save(writer));
        ConditionalRecipe.builder().addCondition(and(modLoaded(samuraiDynastyID))).addRecipe(c ->
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, EpicSamuraiItems.STEEL_SAMURAI_BOOTS.get())
                .define('#', Etags.Items.INGOTS_STEEL).define('I', Tags.Items.INGOTS_IRON)
                .pattern("#I#")
                .pattern("# #")
                .unlockedBy(getHasName(ElementusItems.STEEL_INGOT.get()), has(Etags.Items.INGOTS_STEEL))
                .save(writer));

        ConditionalRecipe.builder().addCondition(and(modLoaded(samuraiDynastyID))).addRecipe(c ->
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, EpicSamuraiItems.STEEL_SAMURAI_HELMET_LIGHT.get())
                .define('#', Etags.Items.INGOTS_STEEL).define('I', Tags.Items.INGOTS_IRON)
                .pattern(" I ")
                .pattern("I#I")
                .pattern("# #")
                .unlockedBy(getHasName(ElementusItems.STEEL_INGOT.get()), has(Etags.Items.INGOTS_STEEL))
                .save(writer));
        ConditionalRecipe.builder().addCondition(and(modLoaded(samuraiDynastyID))).addRecipe(c ->
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, EpicSamuraiItems.STEEL_SAMURAI_CHESTPLATE_LIGHT.get())
                .define('#', Etags.Items.INGOTS_STEEL).define('I', Tags.Items.INGOTS_IRON)
                .pattern("# #")
                .pattern("#I#")
                .pattern("#I#")
                .unlockedBy(getHasName(ElementusItems.STEEL_INGOT.get()), has(Etags.Items.INGOTS_STEEL))
                .save(writer));
        ConditionalRecipe.builder().addCondition(and(modLoaded(samuraiDynastyID))).addRecipe(c ->
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, EpicSamuraiItems.STEEL_SAMURAI_LEGGINGS_LIGHT.get())
                .define('#', Etags.Items.INGOTS_STEEL).define('I', Tags.Items.INGOTS_IRON)
                .pattern("#I#")
                .pattern("# #")
                .pattern("# #")
                .unlockedBy(getHasName(ElementusItems.STEEL_INGOT.get()), has(Etags.Items.INGOTS_STEEL))
                .save(writer));
        ConditionalRecipe.builder().addCondition(and(modLoaded(samuraiDynastyID))).addRecipe(c ->
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, EpicSamuraiItems.STEEL_SAMURAI_BOOTS_LIGHT.get())
                .define('#', Etags.Items.INGOTS_STEEL).define('I', Tags.Items.INGOTS_IRON)
                .pattern("I#I")
                .pattern("# #")
                .unlockedBy(getHasName(ElementusItems.STEEL_INGOT.get()), has(Etags.Items.INGOTS_STEEL))
                .save(writer));

        ConditionalRecipe.builder().addCondition(and(modLoaded(samuraiDynastyID))).addRecipe(c ->
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, EpicSamuraiItems.STEEL_SAMURAI_HELMET_MASTER.get())
                .define('#', Etags.Items.INGOTS_STEEL).define('I', Tags.Items.INGOTS_IRON)
                .pattern(" # ")
                .pattern("III")
                .pattern("# #")
                .unlockedBy(getHasName(ElementusItems.STEEL_INGOT.get()), has(Etags.Items.INGOTS_STEEL))
                .save(writer));
        ConditionalRecipe.builder().addCondition(and(modLoaded(samuraiDynastyID))).addRecipe(c ->
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, EpicSamuraiItems.STEEL_SAMURAI_CHESTPLATE_MASTER.get())
                .define('#', Etags.Items.INGOTS_STEEL).define('I', Tags.Items.INGOTS_IRON)
                .pattern("# #")
                .pattern("I#I")
                .pattern("III")
                .unlockedBy(getHasName(ElementusItems.STEEL_INGOT.get()), has(Etags.Items.INGOTS_STEEL))
                .save(writer));
        ConditionalRecipe.builder().addCondition(and(modLoaded(samuraiDynastyID))).addRecipe(c ->
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, EpicSamuraiItems.STEEL_SAMURAI_LEGGINGS_MASTER.get())
                .define('#', Etags.Items.INGOTS_STEEL).define('I', Tags.Items.INGOTS_IRON)
                .pattern("#I#")
                .pattern("I I")
                .pattern("# #")
                .unlockedBy(getHasName(ElementusItems.STEEL_INGOT.get()), has(Etags.Items.INGOTS_STEEL))
                .save(writer));
        ConditionalRecipe.builder().addCondition(and(modLoaded(samuraiDynastyID))).addRecipe(c ->
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, EpicSamuraiItems.STEEL_SAMURAI_BOOTS_MASTER.get())
                .define('#', Etags.Items.INGOTS_STEEL).define('I', Tags.Items.INGOTS_IRON)
                .pattern("III")
                .pattern("# #")
                .unlockedBy(getHasName(ElementusItems.STEEL_INGOT.get()), has(Etags.Items.INGOTS_STEEL))
                .save(writer));

        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, EpicSamuraiItems.STEEL_SAMURAI_HELMET, Etags.Items.INGOTS_DIARKRITE, EpicSamuraiItems.DIARKRITE_SAMURAI_HELMET, CriterionName.DIARKRITE.name, samuraiDynastyID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, EpicSamuraiItems.STEEL_SAMURAI_CHESTPLATE, Etags.Items.INGOTS_DIARKRITE, EpicSamuraiItems.DIARKRITE_SAMURAI_CHESTPLATE, CriterionName.DIARKRITE.name, samuraiDynastyID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, EpicSamuraiItems.STEEL_SAMURAI_LEGGINGS, Etags.Items.INGOTS_DIARKRITE, EpicSamuraiItems.DIARKRITE_SAMURAI_LEGGINGS, CriterionName.DIARKRITE.name, samuraiDynastyID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, EpicSamuraiItems.STEEL_SAMURAI_BOOTS, Etags.Items.INGOTS_DIARKRITE, EpicSamuraiItems.DIARKRITE_SAMURAI_BOOTS, CriterionName.DIARKRITE.name, samuraiDynastyID);

        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, EpicSamuraiItems.STEEL_SAMURAI_HELMET_LIGHT, Etags.Items.INGOTS_DIARKRITE, EpicSamuraiItems.DIARKRITE_SAMURAI_HELMET_LIGHT, CriterionName.DIARKRITE.name, samuraiDynastyID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, EpicSamuraiItems.STEEL_SAMURAI_CHESTPLATE_LIGHT, Etags.Items.INGOTS_DIARKRITE, EpicSamuraiItems.DIARKRITE_SAMURAI_CHESTPLATE_LIGHT, CriterionName.DIARKRITE.name, samuraiDynastyID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, EpicSamuraiItems.STEEL_SAMURAI_LEGGINGS_LIGHT, Etags.Items.INGOTS_DIARKRITE, EpicSamuraiItems.DIARKRITE_SAMURAI_LEGGINGS_LIGHT, CriterionName.DIARKRITE.name, samuraiDynastyID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, EpicSamuraiItems.STEEL_SAMURAI_BOOTS_LIGHT, Etags.Items.INGOTS_DIARKRITE, EpicSamuraiItems.DIARKRITE_SAMURAI_BOOTS_LIGHT, CriterionName.DIARKRITE.name, samuraiDynastyID);

        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, EpicSamuraiItems.STEEL_SAMURAI_HELMET_MASTER, Etags.Items.INGOTS_DIARKRITE, EpicSamuraiItems.DIARKRITE_SAMURAI_HELMET_MASTER, CriterionName.DIARKRITE.name, samuraiDynastyID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, EpicSamuraiItems.STEEL_SAMURAI_CHESTPLATE_MASTER, Etags.Items.INGOTS_DIARKRITE, EpicSamuraiItems.DIARKRITE_SAMURAI_CHESTPLATE_MASTER, CriterionName.DIARKRITE.name, samuraiDynastyID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, EpicSamuraiItems.STEEL_SAMURAI_LEGGINGS_MASTER, Etags.Items.INGOTS_DIARKRITE, EpicSamuraiItems.DIARKRITE_SAMURAI_LEGGINGS_MASTER, CriterionName.DIARKRITE.name, samuraiDynastyID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, EpicSamuraiItems.STEEL_SAMURAI_BOOTS_MASTER, Etags.Items.INGOTS_DIARKRITE, EpicSamuraiItems.DIARKRITE_SAMURAI_BOOTS_MASTER, CriterionName.DIARKRITE.name, samuraiDynastyID);


        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, EpicSamuraiItems.STEEL_SAMURAI_HELMET, Etags.Items.INGOTS_ANTHEKTITE, EpicSamuraiItems.ANTHEKTITE_SAMURAI_HELMET, CriterionName.ANTHEKTITE.name, samuraiDynastyID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, EpicSamuraiItems.STEEL_SAMURAI_CHESTPLATE, Etags.Items.INGOTS_ANTHEKTITE, EpicSamuraiItems.ANTHEKTITE_SAMURAI_CHESTPLATE, CriterionName.ANTHEKTITE.name, samuraiDynastyID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, EpicSamuraiItems.STEEL_SAMURAI_LEGGINGS, Etags.Items.INGOTS_ANTHEKTITE, EpicSamuraiItems.ANTHEKTITE_SAMURAI_LEGGINGS, CriterionName.ANTHEKTITE.name, samuraiDynastyID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, EpicSamuraiItems.STEEL_SAMURAI_BOOTS, Etags.Items.INGOTS_ANTHEKTITE, EpicSamuraiItems.ANTHEKTITE_SAMURAI_BOOTS, CriterionName.ANTHEKTITE.name, samuraiDynastyID);

        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, EpicSamuraiItems.STEEL_SAMURAI_HELMET_LIGHT, Etags.Items.INGOTS_ANTHEKTITE, EpicSamuraiItems.ANTHEKTITE_SAMURAI_HELMET_LIGHT, CriterionName.ANTHEKTITE.name, samuraiDynastyID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, EpicSamuraiItems.STEEL_SAMURAI_CHESTPLATE_LIGHT, Etags.Items.INGOTS_ANTHEKTITE, EpicSamuraiItems.ANTHEKTITE_SAMURAI_CHESTPLATE_LIGHT, CriterionName.ANTHEKTITE.name, samuraiDynastyID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, EpicSamuraiItems.STEEL_SAMURAI_LEGGINGS_LIGHT, Etags.Items.INGOTS_ANTHEKTITE, EpicSamuraiItems.ANTHEKTITE_SAMURAI_LEGGINGS_LIGHT, CriterionName.ANTHEKTITE.name, samuraiDynastyID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, EpicSamuraiItems.STEEL_SAMURAI_BOOTS_LIGHT, Etags.Items.INGOTS_ANTHEKTITE, EpicSamuraiItems.ANTHEKTITE_SAMURAI_BOOTS_LIGHT, CriterionName.ANTHEKTITE.name, samuraiDynastyID);

        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, EpicSamuraiItems.STEEL_SAMURAI_HELMET_MASTER, Etags.Items.INGOTS_ANTHEKTITE, EpicSamuraiItems.ANTHEKTITE_SAMURAI_HELMET_MASTER, CriterionName.ANTHEKTITE.name, samuraiDynastyID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, EpicSamuraiItems.STEEL_SAMURAI_CHESTPLATE_MASTER, Etags.Items.INGOTS_ANTHEKTITE, EpicSamuraiItems.ANTHEKTITE_SAMURAI_CHESTPLATE_MASTER, CriterionName.ANTHEKTITE.name, samuraiDynastyID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, EpicSamuraiItems.STEEL_SAMURAI_LEGGINGS_MASTER, Etags.Items.INGOTS_ANTHEKTITE, EpicSamuraiItems.ANTHEKTITE_SAMURAI_LEGGINGS_MASTER, CriterionName.ANTHEKTITE.name, samuraiDynastyID);
        smithingCombatTransform(writer, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, EpicSamuraiItems.STEEL_SAMURAI_BOOTS_MASTER, Etags.Items.INGOTS_ANTHEKTITE, EpicSamuraiItems.ANTHEKTITE_SAMURAI_BOOTS_MASTER, CriterionName.ANTHEKTITE.name, samuraiDynastyID);
    }
    private void Twigs(Consumer<FinishedRecipe> writer) {
        ConditionalRecipe.builder().addCondition(and(modLoaded(twigsID), not(FALSE()))).addRecipe(
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TwigsItems.MOVCADIA_TABLE.get())
                .pattern("###").pattern("1 1").pattern("1 1").define('#', ElementusItems.MOVCADIA_SLAB.get()).define('1', ElementusItems.MOVCADIA_FENCE.get())
                .unlockedBy(getHasName(ElementusItems.MOVCADIA_PLANKS.get()), has(ElementusItems.MOVCADIA_PLANKS.get()))::save)
                .generateAdvancement()
                .build(writer, new ResourceLocation(getHasName(TwigsItems.MOVCADIA_TABLE.get())));
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
