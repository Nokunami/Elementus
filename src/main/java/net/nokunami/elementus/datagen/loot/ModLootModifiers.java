package net.nokunami.elementus.datagen.loot;

import com.mojang.serialization.Codec;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nokunami.elementus.Elementus;

import static net.nokunami.elementus.ModChecker.*;

public class ModLootModifiers {

    public static class ElementusLootModifiers {
        public static final DeferredRegister<Codec<? extends IGlobalLootModifier>>
                LOOT_MODIFIER_SERIALIZER = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, Elementus.MODID);

        public static void register(IEventBus eventBus) {
            LOOT_MODIFIER_SERIALIZER.register(eventBus);
        }
    }

    public static void register(IEventBus eventBus) {
        ElementusLootModifiers.register(eventBus);
    }
}