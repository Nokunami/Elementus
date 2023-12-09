package net.nokunami.elementus.item;

import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.nokunami.elementus.Elementus.MODID;

public class ItemsRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final RegistryObject<Item> CRUDE_STEEL = ITEMS.register("crude_steel",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STEEL_SCRAP = ITEMS.register("steel_scrap",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STEEL_NUGGET = ITEMS.register("steel_nugget",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STEEL_SWORD = ITEMS.register("steel_sword",
            () -> new SwordItem(ItemTiers.STEEL, (int) 3f, -2.4f,
                    new Item.Properties()));
    public static final RegistryObject<Item> STEEL_SHOVEL = ITEMS.register("steel_shovel",
            () -> new ShovelItem(ItemTiers.STEEL, (int) 1.5f, -3.0f,
                    new Item.Properties()));
    public static final RegistryObject<Item> STEEL_PICKAXE = ITEMS.register("steel_pickaxe",
            () -> new PickaxeItem(ItemTiers.STEEL, (int) 1f, -2.8f,
                    new Item.Properties()));
    public static final RegistryObject<Item> STEEL_AXE = ITEMS.register("steel_axe",
            () -> new AxeItem(ItemTiers.STEEL, (int) 6f, -3.1f,
                    new Item.Properties()));
    public static final RegistryObject<Item> STEEL_HOE = ITEMS.register("steel_hoe",
            () -> new HoeItem(ItemTiers.STEEL, (int) -2f, -1.0f,
                    new Item.Properties()));

    public static void register(IEventBus eventBus) {ITEMS.register(eventBus);}
}
