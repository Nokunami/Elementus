package net.nokunami.elementus.common.registry;

import net.minecraft.ChatFormatting;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.nokunami.elementus.common.catalystCore.CatalystCoreAttributes;
import net.nokunami.elementus.common.catalystCore.ability.AbilityHolder;
import net.nokunami.elementus.common.catalystCore.core.CatalystCore;
import net.nokunami.elementus.common.catalystCore.core.WayfinderCatalystCore;
import net.nokunami.elementus.common.registry.tempCompat.CompatCoreRegistry;

import static net.nokunami.elementus.Elementus.EID;
import static net.nokunami.elementus.common.registry.CustomRegistries.CATALYST_CORE_RL;

public class CatalystCoreRegistry {
    public static final DeferredRegister<CatalystCore> CORE = DeferredRegister.create(CATALYST_CORE_RL, EID);

    public static final RegistryObject<CatalystCore> BEACON_POWER = registerCore("beacon_power", Items.NETHER_STAR, ChatFormatting.AQUA, new CatalystCoreAttributes.Builder()
            .passiveAbility(CatalystAbilities.NETHER_STAR));

    public static final RegistryObject<CatalystCore> HEART_OF_THE_SEA = registerCore("heart_of_the_sea", Items.HEART_OF_THE_SEA, ChatFormatting.BLUE, new CatalystCoreAttributes.Builder()
            .passiveAbility(CatalystAbilities.HEART_OF_THE_SEA));

    public static final RegistryObject<CatalystCore> TOTEM_OF_UNDYING = registerCore("totem_of_undying", Items.TOTEM_OF_UNDYING, ChatFormatting.GOLD, new CatalystCoreAttributes.Builder()
            .passiveAbility(CatalystAbilities.TOTEM_OF_UNDYING)
            .activeAbility(new AbilityHolder(CatalystAbilities.TELEPORT))
            .activeAbility(new AbilityHolder(CatalystAbilities.TELEPORT))
            .activeAbility(new AbilityHolder(CatalystAbilities.TELEPORT))
    );

    public static final RegistryObject<CatalystCore> END_SHIFTER = registerCore("end_shifter", Items.ENDER_PEARL, ChatFormatting.LIGHT_PURPLE, new CatalystCoreAttributes.Builder()
            .activeAbility(new AbilityHolder(CatalystAbilities.TELEPORT))
            .activeAbility(new AbilityHolder(CatalystAbilities.GROUP_TELEPORT))
    );

    public static final RegistryObject<CatalystCore> WAYFINDER = CORE.register("wayfinder",
        () -> new WayfinderCatalystCore(new CatalystCoreAttributes.Builder().activeAbility(new AbilityHolder(CatalystAbilities.LODEHOME)).build()));

    public static RegistryObject<CatalystCore> registerCore(String id, Item item, ChatFormatting chatFormatting, CatalystCoreAttributes.Builder builder) {
        return CORE.register(id, () -> new CatalystCore(() -> new ItemStack(item), chatFormatting, builder.build()));
    }

    public static void register(IEventBus eventBus) {
        CORE.register(eventBus);
        CompatCoreRegistry.register(eventBus);
    }
}
