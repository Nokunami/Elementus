package net.nokunami.elementus.common.compat.farmersdelight;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nokunami.elementus.common.config.ToolsConfig;
import net.nokunami.elementus.common.registry.ModItems;
import net.nokunami.elementus.common.registry.ModTiers;
import umpaz.nethersdelight.common.item.MacheteItem;

import static net.nokunami.elementus.Elementus.MODID;

public class NDModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    protected static float steelSpeed = ModItems.steelSpeed;
    protected static float diarkriteSpeed = ModItems.diarkriteSpeed;
    protected static float anthektiteSpeed = ModItems.anthektiteSpeed;

    public static final RegistryObject<Item> STEEL_MACHETE = ITEMS.register("steel_machete", () -> new MacheteItem(ModTiers.STEEL,
            ToolsConfig.steelMacheteDamage, ToolsConfig.steelMacheteAttackSpeed + steelSpeed, new Item.Properties()));
    public static final RegistryObject<Item> DIARKRITE_MACHETE = ITEMS.register("diarkrite_machete", () -> new MacheteItem(ModTiers.DIARKRITE,
            ToolsConfig.diarkriteMacheteDamage, ToolsConfig.diarkriteMacheteAttackSpeed + diarkriteSpeed, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_MACHETE = ITEMS.register("anthektite_machete", () -> new MacheteItem(ModTiers.ANTHEKTITE,
            ToolsConfig.anthektiteMacheteDamage, ToolsConfig.anthektiteMacheteAttackSpeed + anthektiteSpeed, new Item.Properties().fireResistant()));

    public static void register(IEventBus eventBus) {ITEMS.register(eventBus);}
}
