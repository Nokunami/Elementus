package net.nokunami.elementus.common.compat.sniffsweapons;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nokunami.elementus.common.registry.ModArmorMaterials;
import net.nokunami.elementus.common.registry.ModItems;
import net.nokunami.elementus.common.registry.ModTiers;
import nl.sniffiandros.sniffsweapons.item.*;

import static net.nokunami.elementus.Elementus.MODID;

public class SWModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    protected static float diarkriteSpeed = ModItems.diarkriteSpeed;
    protected static float anthektiteSpeed = ModItems.anthektiteSpeed;

    public static final RegistryObject<Item> STEEL_GREAT_SWORD = ITEMS.register("steel_great_sword",
            () -> new GreatSwordItem(ModTiers.STEEL, 5, -2.9F,new Item.Properties()));

    public static final RegistryObject<Item> DIARKRITE_GREAT_SWORD = ITEMS.register("diarkrite_great_sword",
            () -> new GreatSwordItem(ModTiers.DIARKRITE, 5, -(2.9F + diarkriteSpeed),new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> ANTHEKTITE_GREAT_SWORD = ITEMS.register("anthektite_great_sword",
            () -> new GreatSwordItem(ModTiers.ANTHEKTITE, 5, -(2.5F - anthektiteSpeed),new Item.Properties().fireResistant()));


    public static final RegistryObject<Item> STEEL_GREAT_AXE = ITEMS.register("steel_great_axe",
            () -> new GreatAxeItem(ModTiers.STEEL, 7, -3.2F,new Item.Properties()));

    public static final RegistryObject<Item> DIARKRITE_GREAT_AXE = ITEMS.register("diarkrite_great_axe",
            () -> new GreatAxeItem(ModTiers.DIARKRITE, 7, -(3.2F + diarkriteSpeed),new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> ANTHEKTITE_GREAT_AXE = ITEMS.register("anthektite_great_axe",
            () -> new GreatAxeItem(ModTiers.ANTHEKTITE, 7, -(3.2F - anthektiteSpeed),new Item.Properties().fireResistant()));


    public static final RegistryObject<Item> STEEL_GREAT_PICKAXE = ITEMS.register("steel_great_pickaxe",
            () -> new GreatPickaxeItem(ModTiers.STEEL, 3, -3.05F,new Item.Properties()));

    public static final RegistryObject<Item> DIARKRITE_GREAT_PICKAXE = ITEMS.register("diarkrite_great_pickaxe",
            () -> new GreatPickaxeItem(ModTiers.DIARKRITE, 3, -(3.05F + diarkriteSpeed),new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> ANTHEKTITE_GREAT_PICKAXE = ITEMS.register("anthektite_great_pickaxe",
            () -> new GreatPickaxeItem(ModTiers.ANTHEKTITE, 3, -(3.05F - anthektiteSpeed),new Item.Properties().fireResistant()));


    public static final RegistryObject<Item> STEEL_NAGINATA = ITEMS.register("steel_naginata",
            () -> new NaginataItem(ModTiers.STEEL, 4, -3.0F,new Item.Properties()));

    public static final RegistryObject<Item> DIARKRITE_NAGINATA = ITEMS.register("diarkrite_naginata",
            () -> new NaginataItem(ModTiers.DIARKRITE, 4, -(3.0F + diarkriteSpeed),new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> ANTHEKTITE_NAGINATA = ITEMS.register("anthektite_naginata",
            () -> new NaginataItem(ModTiers.ANTHEKTITE, 4, -(3.0F - anthektiteSpeed),new Item.Properties().fireResistant()));


    public static final RegistryObject<Item> STEEL_SURCOAT = ITEMS.register("steel_surcoat",
            () -> new StylishArmorItem(ModArmorMaterials.STEEL, ArmorItem.Type.CHESTPLATE, "stylish", 10511680, new Item.Properties()));

    public static final RegistryObject<Item> DIARKRITE_SURCOAT = ITEMS.register("diarkrite_surcoat",
            () -> new StylishArmorItem(ModArmorMaterials.DIARKRITE, ArmorItem.Type.CHESTPLATE, "stylish", 10511680, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> ANTHEKTITE_SURCOAT = ITEMS.register("anthektite_surcoat",
            () -> new StylishArmorItem(ModArmorMaterials.ANTHEKTITE, ArmorItem.Type.CHESTPLATE, "stylish", 10511680, new Item.Properties().fireResistant()));


    public static final RegistryObject<Item> STEEL_HELM = ITEMS.register("steel_helm",
            () -> new StylishArmorItem(ModArmorMaterials.STEEL, ArmorItem.Type.HELMET, "stylish", 16777215, new Item.Properties()));

    public static final RegistryObject<Item> DIARKRITE_HELM = ITEMS.register("diarkrite_helm",
            () -> new StylishArmorItem(ModArmorMaterials.DIARKRITE, ArmorItem.Type.HELMET, "stylish", 16777215, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> ANTHEKTITE_HELM = ITEMS.register("anthektite_helm",
            () -> new StylishArmorItem(ModArmorMaterials.ANTHEKTITE, ArmorItem.Type.HELMET, "stylish", 16777215, new Item.Properties().fireResistant()));


    public static final RegistryObject<Item> PLATED_STEEL_CHESTPLATE = ITEMS.register("plated_steel_chestplate",
            () -> new HornedArmorItem(ModArmorMaterials.STEEL, ArmorItem.Type.CHESTPLATE, 10511680, new Item.Properties()));

    public static final RegistryObject<Item> PLATED_DIARKRITE_CHESTPLATE = ITEMS.register("plated_diarkrite_chestplate",
            () -> new HornedArmorItem(ModArmorMaterials.DIARKRITE, ArmorItem.Type.CHESTPLATE, 10511680, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> PLATED_ANTHEKTITE_CHESTPLATE = ITEMS.register("plated_anthektite_chestplate",
            () -> new HornedArmorItem(ModArmorMaterials.ANTHEKTITE, ArmorItem.Type.CHESTPLATE, 10511680, new Item.Properties().fireResistant()));


    public static final RegistryObject<Item> STEEL_HORNED_HELM = ITEMS.register("steel_horned_helm",
            () -> new HornedArmorItem(ModArmorMaterials.STEEL, ArmorItem.Type.HELMET, 16777215, new Item.Properties()));

    public static final RegistryObject<Item> DIARKRITE_HORNED_HELM = ITEMS.register("diarkrite_horned_helm",
            () -> new HornedArmorItem(ModArmorMaterials.DIARKRITE, ArmorItem.Type.HELMET, 16777215, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> ANTHEKTITE_HORNED_HELM = ITEMS.register("anthektite_horned_helm",
            () -> new HornedArmorItem(ModArmorMaterials.ANTHEKTITE, ArmorItem.Type.HELMET, 16777215, new Item.Properties().fireResistant()));


    public static final RegistryObject<Item> STEEL_DO = ITEMS.register("steel_do",
            () -> new SamuraiArmorItem(ModArmorMaterials.STEEL, ArmorItem.Type.CHESTPLATE, 10511680, new Item.Properties()));

    public static final RegistryObject<Item> DIARKRITE_DO = ITEMS.register("diarkrite_do",
            () -> new SamuraiArmorItem(ModArmorMaterials.DIARKRITE, ArmorItem.Type.CHESTPLATE, 10511680, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> ANTHEKTITE_DO = ITEMS.register("anthektite_do",
            () -> new SamuraiArmorItem(ModArmorMaterials.ANTHEKTITE, ArmorItem.Type.CHESTPLATE, 10511680, new Item.Properties().fireResistant()));


    public static final RegistryObject<Item> STEEL_KABUTO = ITEMS.register("steel_kabuto",
            () -> new SamuraiArmorItem(ModArmorMaterials.STEEL, ArmorItem.Type.HELMET, 16777215, new Item.Properties()));

    public static final RegistryObject<Item> DIARKRITE_KABUTO = ITEMS.register("diarkrite_kabuto",
            () -> new SamuraiArmorItem(ModArmorMaterials.DIARKRITE, ArmorItem.Type.HELMET, 16777215, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> ANTHEKTITE_KABUTO = ITEMS.register("anthektite_kabuto",
            () -> new SamuraiArmorItem(ModArmorMaterials.ANTHEKTITE, ArmorItem.Type.HELMET, 16777215, new Item.Properties().fireResistant()));

    public static void register(IEventBus eventBus) {ITEMS.register(eventBus);}
}
