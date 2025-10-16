package net.nokunami.elementus.common.registry;

import net.minecraft.ChatFormatting;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.nokunami.elementus.client.color.item;
import net.nokunami.elementus.common.Etags;
import net.nokunami.elementus.common.catalystCore.CatalystCoreAttributes;
import net.nokunami.elementus.common.catalystCore.core.CatalystCore;

import java.util.function.Supplier;

import static net.nokunami.elementus.Elementus.MODID;
import static net.nokunami.elementus.common.registry.CustomRegistries.CATALYST_CORE_RL;

public class CatalystCoreRegistry {
    public static final DeferredRegister<CatalystCore> CORE = DeferredRegister.create(CATALYST_CORE_RL, MODID);

    public static final RegistryObject<CatalystCore> NETHER_STAR = registerCore("beacon_power", Items.NETHER_STAR, ChatFormatting.AQUA, new CatalystCoreAttributes.Builder()
            .passiveAbility(CatalystAbilities.NETHER_STAR));

    public static final RegistryObject<CatalystCore> HEART_OF_THE_SEA = registerCore("heart_of_the_sea", Items.HEART_OF_THE_SEA, ChatFormatting.BLUE, new CatalystCoreAttributes.Builder()
            .passiveAbility(CatalystAbilities.HEART_OF_THE_SEA));

    public static final RegistryObject<CatalystCore> TOTEM_OF_UNDYING = registerCore("totem_of_undying", Items.TOTEM_OF_UNDYING, ChatFormatting.GOLD, new CatalystCoreAttributes.Builder()
            .passiveAbility(CatalystAbilities.TOTEM_OF_UNDYING));

    public static final RegistryObject<CatalystCore> END_SHIFTER = registerCore("end_shifter", Items.ENDER_PEARL, ChatFormatting.LIGHT_PURPLE, new CatalystCoreAttributes.Builder());

    public static RegistryObject<CatalystCore> registerCore(String id, Item item, ChatFormatting chatFormatting, CatalystCoreAttributes.Builder builder) {
        return CORE.register(id, () -> new CatalystCore(() -> new ItemStack(item), chatFormatting, builder.build()));
    }

    public static void register(IEventBus eventBus) {
        CORE.register(eventBus);
        CompatCoreRegistry.register(eventBus);
    }
}
