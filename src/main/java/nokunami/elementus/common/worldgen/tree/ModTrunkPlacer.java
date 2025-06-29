package nokunami.elementus.common.worldgen.tree;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import nokunami.elementus.Elementus;

public class ModTrunkPlacer {
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACER = DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, Elementus.MODID);

    public static final DeferredHolder<TrunkPlacerType<MovcadiaTrunkPlacer>, TrunkPlacerType> MOVCADIA_TRUNK_PLACER = TRUNK_PLACER
            .register("movcadia_trunk_placer", () -> new TrunkPlacerType<>(MovcadiaTrunkPlacer.CODEC));

    public static final RegistryObject<TrunkPlacerType<MegaMovcadiaTrunkPlacer>> MEGA_MOVCADIA_TRUNK_PLACER = TRUNK_PLACER
            .register("mega_movcadia_trunk_placer", () -> new TrunkPlacerType<>(MegaMovcadiaTrunkPlacer.CODEC));

    public static void register(IEventBus eventBus) {
        TRUNK_PLACER.register(eventBus);
    }
}
