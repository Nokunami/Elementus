package net.nokunami.elementus.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.nokunami.elementus.datagen.generators.*;
import net.nokunami.elementus.datagen.providers.EGlobalLootModifierProvider;

import java.util.concurrent.CompletableFuture;

import static net.nokunami.elementus.Elementus.EID;
import static net.nokunami.elementus.ModChecker.projectE;

@Mod.EventBusSubscriber(modid = EID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator dataGen = event.getGenerator();
        PackOutput packOutput = dataGen.getPackOutput();
        ExistingFileHelper helper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        dataGen.addProvider(event.includeClient(), new ModBlockStateData(packOutput, EID, helper));
        dataGen.addProvider(event.includeClient(), new EItemModelData(packOutput, EID, helper));
        dataGen.addProvider(event.includeClient(), new ModDamageTypeTagsData(packOutput, lookupProvider, EID, helper));
        dataGen.addProvider(event.includeClient(), new ELangGen(packOutput, EID, "en_us"));
        dataGen.addProvider(event.includeClient(), new ESoundGen(packOutput, EID, helper));


        dataGen.addProvider(event.includeServer(), new ERecipeData(packOutput));
        dataGen.addProvider(event.includeServer(), ModLootTableData.create(packOutput));

        ModBlockTagsData blockTagGenerator = dataGen.addProvider(event.includeServer(), new ModBlockTagsData(packOutput, lookupProvider, helper));
        dataGen.addProvider(event.includeServer(), new EItemTagsData(packOutput, lookupProvider, blockTagGenerator.contentsGetter(), helper));

        dataGen.addProvider(event.includeServer(), new ModEntityTypeTags(packOutput, lookupProvider, helper));

        //dataGen.addProvider(event.includeServer(), new PoiTypeTagsProvider(packOutput, lookupProvider, helper));

        dataGen.addProvider(event.includeServer(), new DatapackEntriesBuilder(packOutput, lookupProvider));

        dataGen.addProvider(event.includeServer(), new EGlobalLootModifierProvider(packOutput));

        if (projectE) {
            dataGen.addProvider (event.includeServer(), new PECustomConversionData(packOutput, lookupProvider));
        }

//            dataGen.addProvider (event.includeServer(), new CreateProcessingRecipe(packOutput));
//            CreateProcessingRecipe.registerAll(dataGen, packOutput);

//        if (ModChecker.refurbished_furniture()) {
//            dataGen.addProvider(event.includeServer(), new RFFurnitureModelProvider(packOutput, helper));
//        }
    }
}
