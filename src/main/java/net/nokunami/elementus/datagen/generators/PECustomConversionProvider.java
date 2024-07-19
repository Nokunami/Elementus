package net.nokunami.elementus.datagen.generators;

import moze_intel.projecte.PECore;
import moze_intel.projecte.api.data.CustomConversionProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.nokunami.elementus.common.Etags;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class PECustomConversionProvider extends CustomConversionProvider {

    public PECustomConversionProvider(@NotNull PackOutput output, @NotNull CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void addCustomConversions(@NotNull HolderLookup.Provider registries) {
        createConversionBuilder(PECore.rl("metals"))
                .before(Etags.Item.RAW_MATERIALS_STEEL, 1_280)
                .before(Etags.Item.ORES_ATELIS, 16_128)
                .before(Etags.Item.INGOTS_STEEL, 1_280)
        ;
    }
}
