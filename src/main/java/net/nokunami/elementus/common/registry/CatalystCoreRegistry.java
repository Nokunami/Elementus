package net.nokunami.elementus.common.registry;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryObject;
import net.nokunami.elementus.Elementus;
import net.nokunami.elementus.common.item.catalystCore.CatalystCore;
import net.nokunami.elementus.common.item.catalystCore.CatalystCoreAbility;

import static net.nokunami.elementus.Elementus.MODID;
import static net.nokunami.elementus.Elementus.modLoc;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CatalystCoreRegistry {
    public static final ResourceLocation CATALYST_CORE_RL = Elementus.modLoc("catalyst_core");
    public static final ResourceKey<Registry<CatalystCore>> CATALYST_CORE_KEY = ResourceKey.createRegistryKey(modLoc("catalyst_core"));

    @SubscribeEvent
    public static void customRegistry(NewRegistryEvent event) {
        event.create(new RegistryBuilder<CatalystCore>().setName(modLoc("catalyst_core")));
    }

    public static final DeferredRegister<CatalystCore> CORE = DeferredRegister.create(CATALYST_CORE_RL, MODID);

    public static final RegistryObject<CatalystCore> NETHER_STAR = CORE.register("nether_star",
            () -> new CatalystCore("nether_star", Items.NETHER_STAR, new CatalystCore.CoreAttributes.Builder()
                    .ability(CoreAbilityRegistry.NETHER_STAR.get()).build()));

    public static final RegistryObject<CatalystCore> HEART_OF_THE_SEA = CORE.register("heart_of_the_sea",
            () -> new CatalystCore("heart_of_the_sea", Items.HEART_OF_THE_SEA, new CatalystCore.CoreAttributes.Builder().build()));

    public static final RegistryObject<CatalystCore> TOTEM_OF_UNDYING = CORE.register("totem_of_undying",
            () -> new CatalystCore("totem_of_undying", Items.TOTEM_OF_UNDYING, new CatalystCore.CoreAttributes.Builder().build()));

    public static void register(IEventBus eventBus) {
        CORE.register(eventBus);
    }
}
