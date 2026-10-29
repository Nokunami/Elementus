package net.nokunami.elementus.common.registry;

import com.google.common.collect.ImmutableMultimap;
import net.minecraft.ChatFormatting;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.nokunami.elementus.common.catalystCore.CatalystArmorAttributes;
import net.nokunami.elementus.common.catalystCore.CatalystCoreAttributes;
import net.nokunami.elementus.common.catalystCore.core.CatalystCore;
import net.nokunami.elementus.common.catalystCore.core.WayfinderCatalystCore;

import static net.nokunami.elementus.Elementus.EID;
import static net.nokunami.elementus.common.registry.ERegistries.CATALYST_CORE;

public class CatalystCores {
    public static final DeferredRegister<CatalystCore> CORE = DeferredRegister.create(CATALYST_CORE, EID);

    public static final RegistryObject<CatalystCore> BEACON_POWER = registerCore("beacon_power", Items.NETHER_STAR, CatalystArmorAttributes.beaconPower(), ChatFormatting.AQUA, new CatalystCoreAttributes.Builder()
            .passiveAbility(CatalystAbilities.BEACON_POWER));

    public static final RegistryObject<CatalystCore> HEART_OF_THE_SEA = registerCore("heart_of_the_sea", Items.HEART_OF_THE_SEA, CatalystArmorAttributes.conduitPower(), ChatFormatting.BLUE, new CatalystCoreAttributes.Builder()
            .passiveAbility(CatalystAbilities.CONDUIT_POWER)
            .activeAbility(CatalystAbilities.CONDUIT_CLEANSE)
    );

    public static final RegistryObject<CatalystCore> TOTEM_OF_UNDYING = registerCore("totem_of_undying", Items.TOTEM_OF_UNDYING, CatalystArmorAttributes.totemOfUndying(), ChatFormatting.GOLD, new CatalystCoreAttributes.Builder()
            .passiveAbility(CatalystAbilities.GILDED_REVIVAL)
    );

    public static final RegistryObject<CatalystCore> VOID_SHIFTER = registerCore("void_shifter", Items.ENDER_PEARL, CatalystArmorAttributes.voidShifter(), ChatFormatting.LIGHT_PURPLE, new CatalystCoreAttributes.Builder()
            .activeAbility(CatalystAbilities.TELEPORT, CatalystAbilities.GROUP_TELEPORT)
    );

    public static final RegistryObject<CatalystCore> WAYFINDER = CORE.register("wayfinder", () -> new WayfinderCatalystCore(new CatalystCoreAttributes.Builder().activeAbility(CatalystAbilities.LODEHOME).build()));

    public static RegistryObject<CatalystCore> registerCore(String id, Item item, ImmutableMultimap.Builder<Attribute, AttributeModifier> map, ChatFormatting chatFormatting, CatalystCoreAttributes.Builder builder) {
        return CORE.register(id, () -> new CatalystCore(() -> new ItemStack(item), chatFormatting, map, builder.build()));
    }

    public static void register(IEventBus eventBus) { CORE.register(eventBus); }
}
