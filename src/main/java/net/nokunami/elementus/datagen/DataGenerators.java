package net.nokunami.elementus.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.nokunami.elementus.Elementus;
import net.nokunami.elementus.datagen.generators.ModItemModelData;
import net.nokunami.elementus.datagen.providers.ProviderGlobalLootModifiers;
import net.nokunami.elementus.datagen.providers.ModItemModelProvider;
import net.nokunami.elementus.datagen.providers.ProviderRecipe;
import net.nokunami.elementus.datagen.providers.ProviderWorldGen;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = Elementus.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeServer(), new ProviderRecipe(packOutput));
        //generator.addProvider(event.includeServer(), LootTableProvidor.create(packOutput));

        //generator.addProvider(event.includeClient(), new BlockStateProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new ModItemModelData(packOutput, existingFileHelper));

        GenBlockTag blockTagGenerator = generator.addProvider(event.includeServer(),
                new GenBlockTag(packOutput, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new GenItemTag(packOutput, lookupProvider, blockTagGenerator.contentsGetter(), existingFileHelper));

        generator.addProvider(event.includeServer(), new ProviderGlobalLootModifiers(packOutput));
        //generator.addProvider(event.includeServer(), new PoiTypeTagsProvider(packOutput, lookupProvider, existingFileHelper));

        generator.addProvider(event.includeServer(), new ProviderWorldGen(packOutput, lookupProvider));
    }
}
