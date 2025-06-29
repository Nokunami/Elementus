package nokunami.elementus.common.component;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import nokunami.elementus.common.item.component.CatalystCore;

import java.util.function.UnaryOperator;

import static nokunami.elementus.Elementus.MODID;

public class ModDataComponents {
    public static final DeferredRegister.DataComponents COMPONENTS = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, MODID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<CatalystCore>> CATALYST_CORE = register("catalyst_core", (builder) -> builder.persistent(CatalystCore.CODEC).networkSynchronized(CatalystCore.STREAM_CODEC).cacheEncoding());

    private static <T> DeferredHolder<DataComponentType<?>, DataComponentType<T>> register(String pName, UnaryOperator<DataComponentType.Builder<T>> pBuilder) {
        return COMPONENTS.register(pName, () -> pBuilder.apply(DataComponentType.builder()).build());
    }

    public static void register(IEventBus eventBus) {
        COMPONENTS.register(eventBus);
    }
}
