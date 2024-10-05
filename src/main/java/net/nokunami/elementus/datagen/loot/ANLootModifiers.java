package net.nokunami.elementus.datagen.loot;

import com.mojang.serialization.Codec;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nokunami.elementus.Elementus;

public class ANLootModifiers {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>>
            LOOT_MODIFIER_SERIALIZER = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, Elementus.MODID);

    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> ADD_ITEM = LOOT_MODIFIER_SERIALIZER
            .register("add_item", ModLootModifier.CODEC);

<<<<<<<< HEAD:src/main/java/net/nokunami/elementus/datagen/loot/ANLootModifiers.java

    public static final RegistryObject<Codec<OreDropsLootModifier>> ORE_DROPS_LOOT_MODIFIER = LOOT_MODIFIER_SERIALIZER
            .register("ore_drops_loot_modifier", () -> OreDropsLootModifier.CODEC);

    public static final RegistryObject<Codec<MobDropsLootModifier>> MOB_DROPS_LOOT_MODIFIER = LOOT_MODIFIER_SERIALIZER
            .register("mob_drops_loot_modifier", () -> MobDropsLootModifier.CODEC);

========
>>>>>>>> origin/main:src/main/java/net/nokunami/elementus/common/datagen/loot/ModLootModifiers.java
    public static void register(IEventBus eventBus) {
        LOOT_MODIFIER_SERIALIZER.register(eventBus);
    }
}
