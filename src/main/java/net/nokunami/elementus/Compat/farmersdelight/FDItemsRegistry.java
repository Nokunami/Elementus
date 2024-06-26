package net.nokunami.elementus.compat.farmersdelight;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nokunami.elementus.item.tiers.CustomItemMaterial;
import vectorwing.farmersdelight.common.item.KnifeItem;

import static net.nokunami.elementus.Elementus.MODID;

public class FDItemsRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final RegistryObject<Item> STEEL_KNIFE = ITEMS.register("steel_knife",
            () -> new KnifeItem(CustomItemMaterial.STEEL, 0.5F, -2.0F, new Item.Properties()));
    public static final RegistryObject<Item> DIARKRITE_KNIFE = ITEMS.register("diarkrite_knife",
            () -> new KnifeItem(CustomItemMaterial.DIARKRITE, 0.5F, -2.3F, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_KNIFE = ITEMS.register("anthektite_knife",
            () -> new KnifeItem(CustomItemMaterial.ANTHEKTITE, 0.5F, -1.5F, new Item.Properties().fireResistant()));

    public static void register(IEventBus eventBus) {ITEMS.register(eventBus);}
}
