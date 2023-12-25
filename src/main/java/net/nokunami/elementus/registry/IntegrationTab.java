package net.nokunami.elementus.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.nokunami.elementus.Compat.farmersdelight.FDItemsRegistry;
import net.nokunami.elementus.Elementus;

public class IntegrationTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Elementus.MODID);

    public static final RegistryObject<CreativeModeTab> ELEMENTUS_MOD_INTEGRATION = CREATIVE_MODE_TABS.register("elementus_mod_integration",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ItemsRegistry.DIARKRITE_INGOT.get()))
                    .title(Component.translatable("creativetab.elementus_mod_integration"))
                    .displayItems((pParameters, pOutput) -> {

                        if (ModList.get().isLoaded("farmersdelight")) {
                        pOutput.accept(FDItemsRegistry.STEEL_KNIFE.get());
                        pOutput.accept(FDItemsRegistry.ANTHEKTITE_KNIFE.get());
                        pOutput.accept(FDItemsRegistry.DIARKRITE_KNIFE.get());
                        }

                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
