package net.nokunami.elementus.registry;

import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nokunami.elementus.item.ItemTiers;
import net.nokunami.elementus.item.ModSmithingTemplateItem;
import net.nokunami.elementus.item.ArmorTiers;
import net.nokunami.elementus.item.armor.AnthektiteArmorItem;
import net.nokunami.elementus.item.armor.DiarkriteArmorItem;
import net.nokunami.elementus.item.armor.SteelArmorItem;

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
    public static final RegistryObject<Item> ANTHEKTITE_INGOT = ITEMS.register("anthektite_ingot",
            () -> new Item(new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_SCRAP = ITEMS.register("anthektite_scrap",
            () -> new Item(new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> DIARKRITE_INGOT = ITEMS.register("diarkrite_ingot",
            () -> new Item(new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_UPGRADE_SMITHING_TEMPLATE = ITEMS.register("anthektite_upgrade_smithing_template",
            ModSmithingTemplateItem::createAnthektiteUpgradeTemplate);
    public static final RegistryObject<Item> DIARKRITE_UPGRADE_SMITHING_TEMPLATE = ITEMS.register("diarkrite_upgrade_smithing_template",
            ModSmithingTemplateItem::createDiarkriteUpgradeTemplate);

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

    public static final RegistryObject<Item> ANTHEKTITE_SWORD = ITEMS.register("anthektite_sword",
            () -> new SwordItem(ItemTiers.ANTHEKTITE, (int) 3f, -2.5f,
                    new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_SHOVEL = ITEMS.register("anthektite_shovel",
            () -> new ShovelItem(ItemTiers.ANTHEKTITE, (int) 1.5f, -3.1f,
                    new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_PICKAXE = ITEMS.register("anthektite_pickaxe",
            () -> new PickaxeItem(ItemTiers.ANTHEKTITE, (int) 1f, -2.9f,
                    new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_AXE = ITEMS.register("anthektite_axe",
            () -> new AxeItem(ItemTiers.ANTHEKTITE, (int) 6f, -3.2f,
                    new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_HOE = ITEMS.register("anthektite_hoe",
            () -> new HoeItem(ItemTiers.ANTHEKTITE, (int) -4f, 0f,
                    new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> DIARKRITE_SWORD = ITEMS.register("diarkrite_sword",
            () -> new SwordItem(ItemTiers.DIARKRITE, (int) 3f, -2.4f,
                    new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> DIARKRITE_SHOVEL = ITEMS.register("diarkrite_shovel",
            () -> new ShovelItem(ItemTiers.DIARKRITE, (int) 1.5f, -3.0f,
                    new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> DIARKRITE_PICKAXE = ITEMS.register("diarkrite_pickaxe",
            () -> new PickaxeItem(ItemTiers.DIARKRITE, (int) 1f, -2.8f,
                    new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> DIARKRITE_AXE = ITEMS.register("diarkrite_axe",
            () -> new AxeItem(ItemTiers.DIARKRITE, (int) 6f, -3.1f,
                    new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> DIARKRITE_HOE = ITEMS.register("diarkrite_hoe",
            () -> new HoeItem(ItemTiers.DIARKRITE, (int) -5f, 0f,
                    new Item.Properties().fireResistant()));

    public static final RegistryObject<ArmorItem> STEEL_HELEMT = ITEMS.register("steel_helmet",
            () -> new SteelArmorItem(ArmorTiers.STEEL, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<ArmorItem> STEEL_CHESTPLATE = ITEMS.register("steel_chestplate",
            () -> new SteelArmorItem(ArmorTiers.STEEL, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<ArmorItem> STEEL_LEGGINGS = ITEMS.register("steel_leggings",
            () -> new SteelArmorItem(ArmorTiers.STEEL, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<ArmorItem> STEEL_BOOTS = ITEMS.register("steel_boots",
            () -> new SteelArmorItem(ArmorTiers.STEEL, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<ArmorItem> ANTHEKTITE_HELEMT = ITEMS.register("anthektite_helmet",
            () -> new AnthektiteArmorItem(ArmorTiers.ANTHEKTITE, ArmorItem.Type.HELMET, new Item.Properties().fireResistant()));
    public static final RegistryObject<ArmorItem> ANTHEKTITE_CHESTPLATE = ITEMS.register("anthektite_chestplate",
            () -> new AnthektiteArmorItem(ArmorTiers.ANTHEKTITE, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant()));
    public static final RegistryObject<ArmorItem> ANTHEKTITE_LEGGINGS = ITEMS.register("anthektite_leggings",
            () -> new AnthektiteArmorItem(ArmorTiers.ANTHEKTITE, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant()));
    public static final RegistryObject<ArmorItem> ANTHEKTITE_BOOTS = ITEMS.register("anthektite_boots",
            () -> new AnthektiteArmorItem(ArmorTiers.ANTHEKTITE, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant()));

    public static final RegistryObject<ArmorItem> DIARKRITE_HELEMT = ITEMS.register("diarkrite_helmet",
            () -> new DiarkriteArmorItem(ArmorTiers.DIARKRITE, ArmorItem.Type.HELMET, new Item.Properties().fireResistant()));
    public static final RegistryObject<ArmorItem> DIARKRITE_CHESTPLATE = ITEMS.register("diarkrite_chestplate",
            () -> new DiarkriteArmorItem(ArmorTiers.DIARKRITE, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant()));
    public static final RegistryObject<ArmorItem> DIARKRITE_LEGGINGS = ITEMS.register("diarkrite_leggings",
            () -> new DiarkriteArmorItem(ArmorTiers.DIARKRITE, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant()));
    public static final RegistryObject<ArmorItem> DIARKRITE_BOOTS = ITEMS.register("diarkrite_boots",
            () -> new DiarkriteArmorItem(ArmorTiers.DIARKRITE, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant()));

/*
    public static final RegistryObject<BlockItem> STEEL_BLOCK = ITEMS.register("steel_block",
            () -> new BlockItem(BlocksRegistry.STEEL_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> ANTHEKTITE_BLOCK = ITEMS.register("anthektite_block",
            () -> new BlockItem(BlocksRegistry.ANTHEKTITE_BLOCK.get(), new Item.Properties().fireResistant()));
    public static final RegistryObject<BlockItem> DIARKRITE_BLOCK = ITEMS.register("diarkrite_block",
            () -> new BlockItem(BlocksRegistry.DIARKRITE_BLOCK.get(), new Item.Properties().fireResistant()));
    public static final RegistryObject<BlockItem> REMNANT = ITEMS.register("remnant",
            () -> new BlockItem(BlocksRegistry.REMNANT.get(), new Item.Properties().fireResistant()));*/

    public static void register(IEventBus eventBus) {ITEMS.register(eventBus);}
}
