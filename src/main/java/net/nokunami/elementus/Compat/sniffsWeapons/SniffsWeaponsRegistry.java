package net.nokunami.elementus.compat.sniffsweapons;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nokunami.elementus.item.armor.CustomArmorMaterial;
import net.nokunami.elementus.item.tiers.CustomItemMaterial;
import nl.sniffiandros.sniffsweapons.item.*;

import static net.nokunami.elementus.Elementus.MODID;

public class SniffsWeaponsRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final RegistryObject<Item> STEEL_GREAT_SWORD = ITEMS.register("steel_great_sword",
            () -> new GreatSwordItem(CustomItemMaterial.STEEL, 5, -2.9F,new Item.Properties()));

    public static final RegistryObject<Item> DIARKRITE_GREAT_SWORD = ITEMS.register("diarkrite_great_sword",
            () -> new GreatSwordItem(CustomItemMaterial.DIARKRITE, 5, -3.2F,new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> ANTHEKTITE_GREAT_SWORD = ITEMS.register("anthektite_great_sword",
            () -> new GreatSwordItem(CustomItemMaterial.ANTHEKTITE, 5, -2.5F,new Item.Properties().fireResistant()));


    public static final RegistryObject<Item> STEEL_GREAT_AXE = ITEMS.register("steel_great_axe",
            () -> new GreatAxeItem(CustomItemMaterial.STEEL, 7, -3.2F,new Item.Properties()));

    public static final RegistryObject<Item> DIARKRITE_GREAT_AXE = ITEMS.register("diarkrite_great_axe",
            () -> new GreatAxeItem(CustomItemMaterial.DIARKRITE, 7, -3.5F,new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> ANTHEKTITE_GREAT_AXE = ITEMS.register("anthektite_great_axe",
            () -> new GreatAxeItem(CustomItemMaterial.ANTHEKTITE, 7, -2.8F,new Item.Properties().fireResistant()));


    public static final RegistryObject<Item> STEEL_GREAT_PICKAXE = ITEMS.register("steel_great_pickaxe",
            () -> new GreatPickaxeItem(CustomItemMaterial.STEEL, 3, -3.05F,new Item.Properties()));

    public static final RegistryObject<Item> DIARKRITE_GREAT_PICKAXE = ITEMS.register("diarkrite_great_pickaxe",
            () -> new GreatPickaxeItem(CustomItemMaterial.DIARKRITE, 3, -3.35F,new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> ANTHEKTITE_GREAT_PICKAXE = ITEMS.register("anthektite_great_pickaxe",
            () -> new GreatPickaxeItem(CustomItemMaterial.ANTHEKTITE, 3, -2.55F,new Item.Properties().fireResistant()));


    public static final RegistryObject<Item> STEEL_SURCOAT = ITEMS.register("steel_surcoat",
            () -> new StylishArmorItem(CustomArmorMaterial.STEEL, ArmorItem.Type.CHESTPLATE, "stylish", 10511680, new Item.Properties()));

    public static final RegistryObject<Item> DIARKRITE_SURCOAT = ITEMS.register("diarkrite_surcoat",
            () -> new StylishArmorItem(CustomArmorMaterial.DIARKRITE, ArmorItem.Type.CHESTPLATE, "stylish", 10511680, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> ANTHEKTITE_SURCOAT = ITEMS.register("anthektite_surcoat",
            () -> new StylishArmorItem(CustomArmorMaterial.ANTHEKTITE, ArmorItem.Type.CHESTPLATE, "stylish", 10511680, new Item.Properties().fireResistant()));


    public static final RegistryObject<Item> STEEL_HELM = ITEMS.register("steel_helm",
            () -> new StylishArmorItem(CustomArmorMaterial.STEEL, ArmorItem.Type.HELMET, "stylish", 16777215, new Item.Properties()));

    public static final RegistryObject<Item> DIARKRITE_HELM = ITEMS.register("diarkrite_helm",
            () -> new StylishArmorItem(CustomArmorMaterial.DIARKRITE, ArmorItem.Type.HELMET, "stylish", 16777215, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> ANTHEKTITE_HELM = ITEMS.register("anthektite_helm",
            () -> new StylishArmorItem(CustomArmorMaterial.ANTHEKTITE, ArmorItem.Type.HELMET, "stylish", 16777215, new Item.Properties().fireResistant()));


    public static final RegistryObject<Item> PLATED_STEEL_CHESTPLATE = ITEMS.register("plated_steel_chestplate",
            () -> new HornedArmorItem(CustomArmorMaterial.STEEL, ArmorItem.Type.CHESTPLATE, 10511680, new Item.Properties()));

    public static final RegistryObject<Item> PLATED_DIARKRITE_CHESTPLATE = ITEMS.register("plated_diarkrite_chestplate",
            () -> new HornedArmorItem(CustomArmorMaterial.DIARKRITE, ArmorItem.Type.CHESTPLATE, 10511680, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> PLATED_ANTHEKTITE_CHESTPLATE = ITEMS.register("plated_anthektite_chestplate",
            () -> new HornedArmorItem(CustomArmorMaterial.ANTHEKTITE, ArmorItem.Type.CHESTPLATE, 10511680, new Item.Properties().fireResistant()));


    public static final RegistryObject<Item> STEEL_HORNED_HELM = ITEMS.register("steel_horned_helm",
            () -> new HornedArmorItem(CustomArmorMaterial.STEEL, ArmorItem.Type.HELMET, 16777215, new Item.Properties()));

    public static final RegistryObject<Item> DIARKRITE_HORNED_HELM = ITEMS.register("diarkrite_horned_helm",
            () -> new HornedArmorItem(CustomArmorMaterial.DIARKRITE, ArmorItem.Type.HELMET, 16777215, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> ANTHEKTITE_HORNED_HELM = ITEMS.register("anthektite_horned_helm",
            () -> new HornedArmorItem(CustomArmorMaterial.ANTHEKTITE, ArmorItem.Type.HELMET, 16777215, new Item.Properties().fireResistant()));

    public static void register(IEventBus eventBus) {ITEMS.register(eventBus);}
}
