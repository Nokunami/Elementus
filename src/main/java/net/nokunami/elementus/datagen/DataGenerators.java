package net.nokunami.elementus.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.nokunami.elementus.Elementus;
import net.nokunami.elementus.ModChecker;
import net.nokunami.elementus.datagen.generators.*;
import net.nokunami.elementus.datagen.providers.ModGlobalLootModifierProvider;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = Elementus.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeServer(), new ModRecipeData(packOutput));
        generator.addProvider(event.includeServer(), ModLootTableData.create(packOutput));

        generator.addProvider(event.includeClient(), new ModBlockStateData(packOutput, "elementus", existingFileHelper));
        generator.addProvider(event.includeClient(), new ModItemModelData(packOutput, "elementus", existingFileHelper));

        ModBlockTagsData blockTagGenerator = generator.addProvider(event.includeServer(),
                new ModBlockTagsData(packOutput, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new ModItemTagsProvider(packOutput, lookupProvider, blockTagGenerator.contentsGetter(), existingFileHelper));

        generator.addProvider(event.includeServer(), new ModGlobalLootModifierProvider(packOutput));
        //generator.addProvider(event.includeServer(), new PoiTypeTagsProvider(packOutput, lookupProvider, existingFileHelper));

        generator.addProvider(event.includeServer(), new DatapackEntriesBuilder(packOutput, lookupProvider));

        if (ModChecker.projecte()) {
        generator.addProvider (event.includeServer(), new PECustomConversionProvider(packOutput, lookupProvider));
        }

//        if (ModChecker.refurbished_furniture()) {
//            generator.addProvider(event.includeServer(), new RFFurnitureModelProvider(packOutput, existingFileHelper));
//        }
    }
}
