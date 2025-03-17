package net.nokunami.elementus.datagen.generators.create;

import com.simibubi.create.AllRecipeTypes;
import com.simibubi.create.content.processing.recipe.HeatCondition;
import com.simibubi.create.foundation.data.recipe.CreateRecipeProvider;
import com.simibubi.create.foundation.data.recipe.ProcessingRecipeGen;
import com.simibubi.create.foundation.recipe.IRecipeTypeInfo;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.nokunami.elementus.Elementus;
import net.nokunami.elementus.common.Etags;
import net.nokunami.elementus.common.registry.ModItems;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CreateProcessingRecipe extends CreateRecipeProvider {
    protected static final List<ProcessingRecipeGen> CCGENERATORS = new ArrayList<>();

    public CreateProcessingRecipe(PackOutput output) {
        super(output);
    }

    public static void registerAll(DataGenerator gen, PackOutput output) {
        CCGENERATORS.add(new Pressing(output));
        CCGENERATORS.add(new Crushing(output));
        CCGENERATORS.add(new Mixing(output));

        gen.addProvider(true, new DataProvider() {
            @Override
            public @NotNull String getName() {
                return "(ELementus) Create's Processing Recipes";
            }

            @Override
            public @NotNull CompletableFuture<?> run(@NotNull CachedOutput dc) {
                return CompletableFuture.allOf(CCGENERATORS.stream()
                        .map(gen -> gen.run(dc))
                        .toArray(CompletableFuture[]::new));
            }
        });
    }

    public static class Pressing extends ProcessingRecipeGen {
//        GeneratedRecipe STEEL = create(Elementus.modLoc("steel_pressing"), b -> b
//                .require(ModItems.ElementusItems.STEEL_SCRAP.get())
//                .require(ModItems.ElementusItems.STEEL_SCRAP.get())
//                .require(Items.IRON_INGOT)
//                .output(ModItems.ElementusItems.STEEL_INGOT.get()));

        public Pressing(PackOutput generator) {
            super(generator);
        }

        @Override
        protected IRecipeTypeInfo getRecipeType() {
            return AllRecipeTypes.PRESSING;
        }
    }
    public static class Crushing extends ProcessingRecipeGen {
        GeneratedRecipe STEEL_RECYCLE = create(Elementus.modLoc("steel_recycle"), b -> b
                .duration(200)
                .withItemIngredients(Ingredient.of(Etags.Items.STEEL_RECYCLABLE))
                .output(ModItems.ElementusItems.STEEL_SCRAP.get(), 1)
                .output(0.5F, ModItems.ElementusItems.STEEL_SCRAP.get()));
        GeneratedRecipe REMNANT = create(Elementus.modLoc("remnant_crushing"), b -> b
                .duration(300)
                .withItemIngredients(Ingredient.of(ModItems.ElementusItems.REMNANT.get()))
                .output(ModItems.ElementusItems.CRUSHED_REMNANT.get(), 1)
                .output(0.25F, Items.COBBLED_DEEPSLATE, 1)
                .output(0.05F, ModItems.ElementusItems.CRUSHED_REMNANT.get()));


        public Crushing(PackOutput generator) {
            super(generator);
        }

        @Override
        protected IRecipeTypeInfo getRecipeType() {
            return AllRecipeTypes.CRUSHING;
        }
    }
    public static class Mixing extends ProcessingRecipeGen {
        GeneratedRecipe STEEL = create(Elementus.modLoc("steel_mixing"), b -> b
                .require(ModItems.ElementusItems.STEEL_SCRAP.get())
                .require(ModItems.ElementusItems.STEEL_SCRAP.get())
                .require(Items.IRON_INGOT)
                .requiresHeat(HeatCondition.HEATED)
                .output(ModItems.ElementusItems.STEEL_INGOT.get()));

        public Mixing(PackOutput generator) {
            super(generator);
        }

        @Override
        protected IRecipeTypeInfo getRecipeType() {
            return AllRecipeTypes.MIXING;
        }
    }
}