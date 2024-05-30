package net.nokunami.elementus.compat.piercingpaxels;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nokunami.elementus.item.tiers.CustomItemMaterial;
import xyz.amymialee.piercingpaxels.items.PaxelItem;

import static net.nokunami.elementus.Elementus.MODID;

public class PPItemsRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final RegistryObject<Item> STEEL_PAXEL = ITEMS.register("steel_paxel",
            () -> new PaxelItem(CustomItemMaterial.STEEL, 5.0F, -2.8F, new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> DIARKRITE_PAXEL = ITEMS.register("diarkrite_paxel",
            () -> new PaxelItem(CustomItemMaterial.DIARKRITE, 5.0F, -3.1F, new Item.Properties().rarity(Rarity.RARE).fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_PAXEL = ITEMS.register("anthektite_paxel",
            () -> new PaxelItem(CustomItemMaterial.ANTHEKTITE, 5.0F, -2.3F, new Item.Properties().rarity(Rarity.RARE).fireResistant()));

    public static final RegistryObject<Item> ANTHEKTITE_UPGRADE_KIT = ITEMS.register("anthektite_paxel_upgrade_kit",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE).fireResistant()));
    public static final RegistryObject<Item> DIARKRITE_UPGRADE_KIT = ITEMS.register("diarkrite_paxel_upgrade_kit",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE).fireResistant()));

    public static void register(IEventBus eventBus) {ITEMS.register(eventBus);}
}
