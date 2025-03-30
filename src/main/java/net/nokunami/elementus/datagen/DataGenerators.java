package net.nokunami.elementus.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.nokunami.elementus.datagen.generators.*;
import net.nokunami.elementus.datagen.generators.create.CreateProcessingRecipe;
import net.nokunami.elementus.datagen.providers.ModGlobalLootModifierProvider;

import java.util.concurrent.CompletableFuture;

import static net.nokunami.elementus.Elementus.MODID;
import static net.nokunami.elementus.ModChecker.*;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        if (event.includeClient()) {
            generator.addProvider(event.includeClient(), new ModBlockStateData(packOutput, MODID, existingFileHelper));
            generator.addProvider(event.includeClient(), new ModItemModelData(packOutput, MODID, existingFileHelper));
            generator.addProvider(event.includeClient(), new ModDamageTypeTagsData(packOutput, lookupProvider, MODID, existingFileHelper));
        }

        if (event.includeServer()) {
            generator.addProvider(event.includeServer(), new ModRecipeData(packOutput));
            generator.addProvider(event.includeServer(), ModLootTableData.create(packOutput));

            ModBlockTagsData blockTagGenerator = generator.addProvider(event.includeServer(), new ModBlockTagsData(packOutput, lookupProvider, existingFileHelper));
            generator.addProvider(event.includeServer(), new ModItemTagsData(packOutput, lookupProvider, blockTagGenerator.contentsGetter(), existingFileHelper));


            if (advancedNetherite) generator.addProvider(event.includeServer(), new ModGlobalLootModifierProvider(packOutput));
            //generator.addProvider(event.includeServer(), new PoiTypeTagsProvider(packOutput, lookupProvider, existingFileHelper));

            generator.addProvider(event.includeServer(), new DatapackEntriesBuilder(packOutput, lookupProvider));

            if (projectE) {
                generator.addProvider (event.includeServer(), new PECustomConversionData(packOutput, lookupProvider));
            }

//            generator.addProvider (event.includeServer(), new CreateProcessingRecipe(packOutput));
            CreateProcessingRecipe.registerAll(generator, packOutput);
        }

//        if (ModChecker.refurbished_furniture()) {
//            generator.addProvider(event.includeServer(), new RFFurnitureModelProvider(packOutput, existingFileHelper));
//        }
    }
}
