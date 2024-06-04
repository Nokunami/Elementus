package net.nokunami.elementus.registry;

import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nokunami.elementus.item.EchoingMirrorItem;
import net.nokunami.elementus.item.armor.CustomArmorMaterial;
import net.nokunami.elementus.item.armor.ElementusArmorItem;
import net.nokunami.elementus.item.tiers.*;
import net.nokunami.elementus.item.ModSmithingTemplateItem;

import static net.nokunami.elementus.Elementus.MODID;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final RegistryObject<Item> CRUDE_STEEL = ITEMS.register("crude_steel",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STEEL_SCRAP = ITEMS.register("steel_scrap",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STEEL_NUGGET = ITEMS.register("steel_nugget",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ANTHEKTITE_SCRAP = ITEMS.register("anthektite_scrap",
                    () -> new Item(new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ATELIS_SCRAP = ITEMS.register("atelis_scrap",
                    () -> new Item(new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_INGOT = ITEMS.register("anthektite_ingot",
            () -> new Item(new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> DIARKRITE_INGOT = ITEMS.register("diarkrite_ingot",
            () -> new Item(new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_UPGRADE_SMITHING_TEMPLATE = ITEMS.register("anthektite_upgrade_smithing_template",
            ModSmithingTemplateItem::createAnthektiteUpgradeTemplate);
    public static final RegistryObject<Item> DIARKRITE_UPGRADE_SMITHING_TEMPLATE = ITEMS.register("diarkrite_upgrade_smithing_template",
            ModSmithingTemplateItem::createDiarkriteUpgradeTemplate);
    public static final RegistryObject<Item> ATELIS_UPGRADE_SMITHING_TEMPLATE = ITEMS.register("atelis_upgrade_smithing_template",
            ModSmithingTemplateItem::createAtelisUpgradeTemplate);
//    public static final RegistryObject<Item> ECHOING_MIRROR = ITEMS.register("echoing_mirror",
//            ModSmithingTemplateItem::createMirrorTemplate);

    public static final RegistryObject<Item> ECHOING_MIRROR = ITEMS.register("echoing_mirror",
            EchoingMirrorItem::createMirror);

    public static final RegistryObject<Item> STEEL_SWORD = ITEMS.register("steel_sword",
            () -> new SwordItem(CustomItemMaterial.STEEL, 3, -2.4f,
                    new Item.Properties()));
    public static final RegistryObject<Item> STEEL_SHOVEL = ITEMS.register("steel_shovel",
            () -> new ShovelItem(CustomItemMaterial.STEEL, 1.5F, -3.0f,
                    new Item.Properties()));
    public static final RegistryObject<Item> STEEL_PICKAXE = ITEMS.register("steel_pickaxe",
            () -> new PickaxeItem(CustomItemMaterial.STEEL, 1, -2.8f,
                    new Item.Properties()));
    public static final RegistryObject<Item> STEEL_AXE = ITEMS.register("steel_axe",
            () -> new AxeItem(CustomItemMaterial.STEEL, 6, -3.1f,
                    new Item.Properties()));
    public static final RegistryObject<Item> STEEL_HOE = ITEMS.register("steel_hoe",
            () -> new HoeItem(CustomItemMaterial.STEEL, 1, -3.0f,
                    new Item.Properties()));

    public static final RegistryObject<Item> DIARKRITE_SWORD = ITEMS.register("diarkrite_sword",
            () -> new SwordItem(CustomItemMaterial.DIARKRITE, 3, -2.7f,
                    new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> DIARKRITE_SHOVEL = ITEMS.register("diarkrite_shovel",
            () -> new ShovelItem(CustomItemMaterial.DIARKRITE, 1.5f, -3.0f,
                    new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> DIARKRITE_PICKAXE = ITEMS.register("diarkrite_pickaxe",
            () -> new PickaxeItem(CustomItemMaterial.DIARKRITE, 1, -2.8f,
                    new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> DIARKRITE_AXE = ITEMS.register("diarkrite_axe",
            () -> new AxeItem(CustomItemMaterial.DIARKRITE, 6, -3.3f,
                    new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> DIARKRITE_HOE = ITEMS.register("diarkrite_hoe",
            () -> new HoeItem(CustomItemMaterial.DIARKRITE, -3, -3.0f,
                    new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> ANTHEKTITE_SWORD = ITEMS.register("anthektite_sword",
            () -> new SwordItem(CustomItemMaterial.ANTHEKTITE, 3, -1.9f,
                    new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_SHOVEL = ITEMS.register("anthektite_shovel",
            () -> new ShovelItem(CustomItemMaterial.ANTHEKTITE, 1.5f, -3f,
                    new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_PICKAXE = ITEMS.register("anthektite_pickaxe",
            () -> new PickaxeItem(CustomItemMaterial.ANTHEKTITE, 1, -2.8f,
                    new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_AXE = ITEMS.register("anthektite_axe",
            () -> new AxeItem(CustomItemMaterial.ANTHEKTITE, 5, -2.7f,
                    new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_HOE = ITEMS.register("anthektite_hoe",
            () -> new HoeItem(CustomItemMaterial.ANTHEKTITE, 0, -3.0f,
                    new Item.Properties().fireResistant()));

    public static final RegistryObject<ShieldItem> STEEL_SHIELD = ITEMS.register("steel_shield",
            () -> new ShieldItem(new Item.Properties().defaultDurability(457)));
    public static final RegistryObject<ShieldItem> ANTHEKTITE_SHIELD = ITEMS.register("anthektite_shield",
            () -> new ShieldItem(new Item.Properties().defaultDurability(598).fireResistant()));
    public static final RegistryObject<ShieldItem> DIARKRITE_SHIELD = ITEMS.register("diarkrite_shield",
            () -> new ShieldItem(new Item.Properties().defaultDurability(843).fireResistant()));

    public static final RegistryObject<ArmorItem> STEEL_HELMET = ITEMS.register("steel_helmet",
            () -> new ElementusArmorItem(CustomArmorMaterial.STEEL, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<ArmorItem> STEEL_CHESTPLATE = ITEMS.register("steel_chestplate",
            () -> new ElementusArmorItem(CustomArmorMaterial.STEEL, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<ArmorItem> STEEL_LEGGINGS = ITEMS.register("steel_leggings",
            () -> new ElementusArmorItem(CustomArmorMaterial.STEEL, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<ArmorItem> STEEL_BOOTS = ITEMS.register("steel_boots",
            () -> new ElementusArmorItem(CustomArmorMaterial.STEEL, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<ArmorItem> ANTHEKTITE_HELMET = ITEMS.register("anthektite_helmet",
            () -> new ElementusArmorItem(CustomArmorMaterial.ANTHEKTITE, ArmorItem.Type.HELMET, new Item.Properties().fireResistant()));
    public static final RegistryObject<ArmorItem> ANTHEKTITE_CHESTPLATE = ITEMS.register("anthektite_chestplate",
            () -> new ElementusArmorItem(CustomArmorMaterial.ANTHEKTITE, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant()));
    public static final RegistryObject<ArmorItem> ANTHEKTITE_LEGGINGS = ITEMS.register("anthektite_leggings",
            () -> new ElementusArmorItem(CustomArmorMaterial.ANTHEKTITE, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant()));
    public static final RegistryObject<ArmorItem> ANTHEKTITE_BOOTS = ITEMS.register("anthektite_boots",
            () -> new ElementusArmorItem(CustomArmorMaterial.ANTHEKTITE, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant()));

    public static final RegistryObject<ArmorItem> DIARKRITE_HELMET = ITEMS.register("diarkrite_helmet",
            () -> new ElementusArmorItem(CustomArmorMaterial.DIARKRITE, ArmorItem.Type.HELMET, new Item.Properties().fireResistant()));
    public static final RegistryObject<ArmorItem> DIARKRITE_CHESTPLATE = ITEMS.register("diarkrite_chestplate",
            () -> new ElementusArmorItem(CustomArmorMaterial.DIARKRITE, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant()));
    public static final RegistryObject<ArmorItem> DIARKRITE_LEGGINGS = ITEMS.register("diarkrite_leggings",
            () -> new ElementusArmorItem(CustomArmorMaterial.DIARKRITE, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant()));
    public static final RegistryObject<ArmorItem> DIARKRITE_BOOTS = ITEMS.register("diarkrite_boots",
            () -> new ElementusArmorItem(CustomArmorMaterial.DIARKRITE, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant()));


    public static void register(IEventBus eventBus) {ITEMS.register(eventBus);}
}
