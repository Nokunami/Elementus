package net.nokunami.elementus.common.registry;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryObject;
import net.nokunami.elementus.Elementus;
import net.nokunami.elementus.common.item.catalystCore.CatalystCoreAbility;

import static net.nokunami.elementus.Elementus.MODID;
import static net.nokunami.elementus.Elementus.modLoc;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CoreAbilityRegistry {
    public static final ResourceLocation CORE_ABILITIES_RL = Elementus.modLoc("catalyst_core_abilities");
    public static final ResourceKey<Registry<CatalystCoreAbility>> CORE_ABILITIES_KEY = ResourceKey.createRegistryKey(modLoc("catalyst_core_abilities"));

    @SubscribeEvent
    public static void customRegistry(NewRegistryEvent event) {
        event.create(new RegistryBuilder<CatalystCoreAbility>().setName(modLoc("catalyst_core_abilities")));
    }

    public static final DeferredRegister<CatalystCoreAbility> CORE = DeferredRegister.create(CORE_ABILITIES_RL, MODID);

    public static final RegistryObject<CatalystCoreAbility> NETHER_STAR = CORE.register("nether_star", CatalystCoreAbility::new);

    public static final RegistryObject<CatalystCoreAbility> HEART_OF_THE_SEA = CORE.register("heart_of_the_sea", CatalystCoreAbility::new);

    public static final RegistryObject<CatalystCoreAbility> TOTEM_OF_UNDYING = CORE.register("totem_of_undying", CatalystCoreAbility::new);

    public static void register(IEventBus eventBus) {
        CORE.register(eventBus);
    }
}
