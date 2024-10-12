package net.nokunami.elementus.datagen.generators;

import moze_intel.projecte.PECore;
import moze_intel.projecte.api.data.CustomConversionProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.nokunami.elementus.common.Etags;
import net.nokunami.elementus.common.registry.ModItems;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class PECustomConversionProvider extends CustomConversionProvider {

    public PECustomConversionProvider(@NotNull PackOutput output, @NotNull CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void addCustomConversions(@NotNull HolderLookup.Provider registries) {
        createConversionBuilder(PECore.rl("elementus"))
//                .before(ModItems.CRUDE_STEEL.get(), 1_280)
                .before(Etags.Item.INGOTS_STEEL, 1_350)
//                .before(ModItems.ATELIS_SCRAP.get(), 24_576)
                .before(ModItems.ATELIS_SCRAP.get(), 12_288)
                .before(ModItems.DIARKRITE_INGOT.get(), 77_906)
                .before(ModItems.ANTHEKTITE_INGOT.get(), 77_906)
                .before(ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE.get(), 18_432)
        ;
    }
}
