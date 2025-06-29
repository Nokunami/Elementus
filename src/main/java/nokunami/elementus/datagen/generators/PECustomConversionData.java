package nokunami.elementus.datagen.generators;

import moze_intel.projecte.PECore;
import moze_intel.projecte.api.data.CustomConversionProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import nokunami.elementus.common.Etags;
import nokunami.elementus.common.registry.ModItems.*;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class PECustomConversionData extends CustomConversionProvider {

    public PECustomConversionData(@NotNull PackOutput output, @NotNull CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void addCustomConversions(@NotNull HolderLookup.Provider registries) {
        createConversionBuilder(PECore.rl("elementus"))
//                .before(ModItems.CRUDE_STEEL.get(), 1_280)
//                .before(Etags.Items.ORES_ATELIS, 24_576)
                .before(Etags.Items.INGOTS_STEEL, 1_350)
//                .before(ElementusItems.ATELIS_SCRAP.get(), 12_288)
//                .before(ElementusItems.DIARKRITE_INGOT.get(), 77_906)
//                .before(ElementusItems.ANTHEKTITE_INGOT.get(), 77_906)
                .before(ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE.get(), 18_432)
                .before(ElementusItems.MOVCADIA_ESSENCE.get(), 192)

                .before(PiercingPaxelsItems.DIARKRITE_UPGRADE_KIT.get(), 384_060)
                .before(PiercingPaxelsItems.ANTHEKTITE_UPGRADE_KIT.get(), 384_060)
        ;
    }
}
