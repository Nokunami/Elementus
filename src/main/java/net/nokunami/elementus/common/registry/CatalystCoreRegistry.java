package net.nokunami.elementus.common.registry;

import net.minecraft.ChatFormatting;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.nokunami.elementus.common.catalystCore.CompatCoreRegistry;
import net.nokunami.elementus.common.catalystCore.CoreAttributes;
import net.nokunami.elementus.common.catalystCore.core.CatalystCore;

import static net.nokunami.elementus.Elementus.MODID;
import static net.nokunami.elementus.common.registry.CustomRegistries.CATALYST_CORE_KEY;
import static net.nokunami.elementus.common.registry.CustomRegistries.CATALYST_CORE_RL;

public class CatalystCoreRegistry {
    public static final DeferredRegister<CatalystCore> CORE = DeferredRegister.create(CATALYST_CORE_RL, MODID);

    public static final RegistryObject<CatalystCore> NETHER_STAR = registerCore("beacon_power", Items.NETHER_STAR, new CoreAttributes.Builder()
            .tooltipColor(ChatFormatting.AQUA)
            .changeTexture()
            .ability(CatalystAbilities.NETHER_STAR));

    public static final RegistryObject<CatalystCore> HEART_OF_THE_SEA = registerCore("heart_of_the_sea", Items.HEART_OF_THE_SEA, new CoreAttributes.Builder()
            .tooltipColor(ChatFormatting.BLUE)
            .changeTexture()
            .ability(CatalystAbilities.HEART_OF_THE_SEA));

    public static final RegistryObject<CatalystCore> TOTEM_OF_UNDYING = registerCore("totem_of_undying", Items.TOTEM_OF_UNDYING, new CoreAttributes.Builder()
            .tooltipColor(ChatFormatting.GOLD)
            .changeTexture()
            .ability(CatalystAbilities.TOTEM_OF_UNDYING));

    public static RegistryObject<CatalystCore> registerCore(String id, Item item, CoreAttributes.Builder builder) {
        return CORE.register(id, () -> new CatalystCore(id, () -> new ItemStack(item), builder.build()));
    }

    public static void register(IEventBus eventBus) {
        CORE.register(eventBus);
        CompatCoreRegistry.register(eventBus);
    }
}
