package net.nokunami.elementus.common.worldgen.tree;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.nokunami.elementus.Elementus;

public class ModTrunkPlacer {
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACER = DeferredRegister.create(
            Registries.TRUNK_PLACER_TYPE, Elementus.MODID);

    public static final RegistryObject<TrunkPlacerType<MovcadiaTrunkPlacer>> MOVCADIA_TRUNK_PLACER = TRUNK_PLACER
            .register("movcadia_trunk_placer", () -> new TrunkPlacerType<>(MovcadiaTrunkPlacer.CODEC));

    public static final RegistryObject<TrunkPlacerType<MegaMovcadiaTrunkPlacer>> MEGA_MOVCADIA_TRUNK_PLACER = TRUNK_PLACER
            .register("mega_movcadia_trunk_placer", () -> new TrunkPlacerType<>(MegaMovcadiaTrunkPlacer.CODEC));

    public static void register(IEventBus eventBus) {
        TRUNK_PLACER.register(eventBus);
    }
}
