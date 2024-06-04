package net.nokunami.elementus.common.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.nokunami.elementus.Elementus;
import net.nokunami.elementus.common.datagen.generators.ModBlockStateData;
import net.nokunami.elementus.common.datagen.generators.ModItemModelData;
import net.nokunami.elementus.common.datagen.generators.ModRecipeData;
import net.nokunami.elementus.common.datagen.providers.*;

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
        //generator.addProvider(event.includeServer(), LootTableProvidor.create(packOutput));

        generator.addProvider(event.includeClient(), new ModBlockStateData(packOutput, "elementus", existingFileHelper));
        generator.addProvider(event.includeClient(), new ModItemModelData(packOutput, "elementus", existingFileHelper));

        ModBlockTagsProvider blockTagGenerator = generator.addProvider(event.includeServer(),
                new ModBlockTagsProvider(packOutput, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new ModItemTagsProvider(packOutput, lookupProvider, blockTagGenerator.contentsGetter(), existingFileHelper));

        generator.addProvider(event.includeServer(), new ModGlobalLootModifierProvider(packOutput));
        //generator.addProvider(event.includeServer(), new PoiTypeTagsProvider(packOutput, lookupProvider, existingFileHelper));

        generator.addProvider(event.includeServer(), new DatapackEntriesBuilder(packOutput, lookupProvider));
    }
}
