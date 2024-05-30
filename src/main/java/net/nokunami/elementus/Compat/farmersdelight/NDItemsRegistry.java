package net.nokunami.elementus.compat.farmersdelight;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nokunami.elementus.item.tiers.CustomItemMaterial;
import umpaz.nethersdelight.common.item.MacheteItem;

import static net.nokunami.elementus.Elementus.MODID;

public class NDItemsRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final RegistryObject<Item> STEEL_MACHETE = ITEMS.register("steel_machete",
            () -> new MacheteItem(CustomItemMaterial.STEEL, 2, -2.6F, new Item.Properties()));
    public static final RegistryObject<Item> DIARKRITE_MACHETE = ITEMS.register("diarkrite_machete",
            () -> new MacheteItem(CustomItemMaterial.DIARKRITE, 2, -2.9F, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_MACHETE = ITEMS.register("anthektite_machete",
            () -> new MacheteItem(CustomItemMaterial.ANTHEKTITE, 2, -2.1F, new Item.Properties().fireResistant()));

    public static void register(IEventBus eventBus) {ITEMS.register(eventBus);}
}
