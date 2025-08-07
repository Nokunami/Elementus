package net.nokunami.elementus.common.compat.sniffsweapons;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nokunami.elementus.common.config.SWConfig;
import net.nokunami.elementus.common.registry.ModArmorMaterials;
import net.nokunami.elementus.common.registry.ModItems;
import net.nokunami.elementus.common.registry.ModTiers;
import nl.sniffiandros.sniffsweapons.item.*;

import static net.nokunami.elementus.Elementus.MODID;
import static net.nokunami.elementus.common.registry.ModItems.*;

public class SWModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final RegistryObject<Item> STEEL_GREAT_SWORD = ITEMS.register("steel_great_sword", () -> new GreatSwordItem(ModTiers.STEEL,
            SWConfig.steelGreatSwordDamage, (float) SWConfig.steelGreatSwordAttackSpeed + steelSpeed,new Item.Properties()));
    public static final RegistryObject<Item> DIARKRITE_GREAT_SWORD = ITEMS.register("diarkrite_great_sword", () -> new GreatSwordItem(ModTiers.DIARKRITE,
            SWConfig.diarkriteGreatSwordDamage, (float) SWConfig.diarkriteGreatSwordAttackSpeed + diarkriteSpeed,new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_GREAT_SWORD = ITEMS.register("anthektite_great_sword", () -> new GreatSwordItem(ModTiers.ANTHEKTITE,
            SWConfig.anthektiteGreatSwordDamage, (float) SWConfig.anthektiteGreatSwordAttackSpeed + anthektiteSpeed,new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> STEEL_GREAT_AXE = ITEMS.register("steel_great_axe", () -> new GreatAxeItem(ModTiers.STEEL,
            SWConfig.steelGreatAxeDamage, (float) SWConfig.steelGreatAxeAttackSpeed + steelSpeed,new Item.Properties()));
    public static final RegistryObject<Item> DIARKRITE_GREAT_AXE = ITEMS.register("diarkrite_great_axe", () -> new GreatAxeItem(ModTiers.DIARKRITE,
            SWConfig.diarkriteGreatAxeDamage, (float) SWConfig.diarkriteGreatAxeAttackSpeed + diarkriteSpeed,new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_GREAT_AXE = ITEMS.register("anthektite_great_axe", () -> new GreatAxeItem(ModTiers.ANTHEKTITE,
            SWConfig.anthektiteGreatAxeDamage, (float) SWConfig.anthektiteGreatAxeAttackSpeed + anthektiteSpeed,new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> STEEL_GREAT_PICKAXE = ITEMS.register("steel_great_pickaxe", () -> new ModGreatPickaxeItem(ModTiers.STEEL,
            SWConfig.steelGreatPickaxeDamage, (float) SWConfig.steelGreatPickaxeAttackSpeed + steelSpeed,new Item.Properties()));
    public static final RegistryObject<Item> DIARKRITE_GREAT_PICKAXE = ITEMS.register("diarkrite_great_pickaxe", () -> new ModGreatPickaxeItem(ModTiers.DIARKRITE,
            SWConfig.diarkriteGreatPickaxeDamage, (float) SWConfig.diarkriteGreatPickaxeAttackSpeed + diarkriteSpeed,new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_GREAT_PICKAXE = ITEMS.register("anthektite_great_pickaxe", () -> new ModGreatPickaxeItem(ModTiers.ANTHEKTITE,
            SWConfig.anthektiteGreatPickaxeDamage, (float) SWConfig.anthektiteGreatPickaxeAttackSpeed + anthektiteSpeed,new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> STEEL_NAGINATA = ITEMS.register("steel_naginata", () -> new NaginataItem(ModTiers.STEEL,
            SWConfig.steelNaginataDamage, (float) SWConfig.steelNaginataAttackSpeed + steelSpeed,new Item.Properties()));
    public static final RegistryObject<Item> DIARKRITE_NAGINATA = ITEMS.register("diarkrite_naginata", () -> new NaginataItem(ModTiers.DIARKRITE,
            SWConfig.diarkriteNaginataDamage, (float) SWConfig.diarkriteNaginataAttackSpeed + diarkriteSpeed,new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_NAGINATA = ITEMS.register("anthektite_naginata", () -> new NaginataItem(ModTiers.ANTHEKTITE,
            SWConfig.anthektiteNaginataDamage, (float) SWConfig.anthektiteNaginataAttackSpeed + anthektiteSpeed,new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> STEEL_SURCOAT = ITEMS.register("steel_surcoat",
            () -> new SniffsWeaponsArmorItem(ModArmorMaterials.STEEL, ArmorItem.Type.CHESTPLATE, "stylish", 10511680, new Item.Properties()));
    public static final RegistryObject<Item> DIARKRITE_SURCOAT = ITEMS.register("diarkrite_surcoat",
            () -> new SniffsWeaponsArmorItem(ModArmorMaterials.DIARKRITE, ArmorItem.Type.CHESTPLATE, "stylish", 10511680, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_SURCOAT = ITEMS.register("anthektite_surcoat",
            () -> new SniffsWeaponsArmorItem(ModArmorMaterials.ANTHEKTITE, ArmorItem.Type.CHESTPLATE, "stylish", 10511680, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> STEEL_HELM = ITEMS.register("steel_helm",
            () -> new SniffsWeaponsArmorItem(ModArmorMaterials.STEEL, ArmorItem.Type.HELMET, "stylish", 16777215, new Item.Properties()));
    public static final RegistryObject<Item> DIARKRITE_HELM = ITEMS.register("diarkrite_helm",
            () -> new SniffsWeaponsArmorItem(ModArmorMaterials.DIARKRITE, ArmorItem.Type.HELMET, "stylish", 16777215, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_HELM = ITEMS.register("anthektite_helm",
            () -> new SniffsWeaponsArmorItem(ModArmorMaterials.ANTHEKTITE, ArmorItem.Type.HELMET, "stylish", 16777215, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> PLATED_STEEL_CHESTPLATE = ITEMS.register("plated_steel_chestplate",
            () -> new SniffsWeaponsArmorItem(ModArmorMaterials.STEEL, ArmorItem.Type.CHESTPLATE, "horned", 10511680, new Item.Properties()));
    public static final RegistryObject<Item> PLATED_DIARKRITE_CHESTPLATE = ITEMS.register("plated_diarkrite_chestplate",
            () -> new SniffsWeaponsArmorItem(ModArmorMaterials.DIARKRITE, ArmorItem.Type.CHESTPLATE, "horned", 10511680, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> PLATED_ANTHEKTITE_CHESTPLATE = ITEMS.register("plated_anthektite_chestplate",
            () -> new SniffsWeaponsArmorItem(ModArmorMaterials.ANTHEKTITE, ArmorItem.Type.CHESTPLATE, "horned", 10511680, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> STEEL_HORNED_HELM = ITEMS.register("steel_horned_helm",
            () -> new SniffsWeaponsArmorItem(ModArmorMaterials.STEEL, ArmorItem.Type.HELMET, "horned", 16777215, new Item.Properties()));
    public static final RegistryObject<Item> DIARKRITE_HORNED_HELM = ITEMS.register("diarkrite_horned_helm",
            () -> new SniffsWeaponsArmorItem(ModArmorMaterials.DIARKRITE, ArmorItem.Type.HELMET, "horned", 16777215, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_HORNED_HELM = ITEMS.register("anthektite_horned_helm",
            () -> new SniffsWeaponsArmorItem(ModArmorMaterials.ANTHEKTITE, ArmorItem.Type.HELMET, "horned", 16777215, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> STEEL_DO = ITEMS.register("steel_do",
            () -> new SniffsWeaponsArmorItem(ModArmorMaterials.STEEL, ArmorItem.Type.CHESTPLATE, "samurai", 10511680, new Item.Properties()));
    public static final RegistryObject<Item> DIARKRITE_DO = ITEMS.register("diarkrite_do",
            () -> new SniffsWeaponsArmorItem(ModArmorMaterials.DIARKRITE, ArmorItem.Type.CHESTPLATE, "samurai", 10511680, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_DO = ITEMS.register("anthektite_do",
            () -> new SniffsWeaponsArmorItem(ModArmorMaterials.ANTHEKTITE, ArmorItem.Type.CHESTPLATE, "samurai", 10511680, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> STEEL_KABUTO = ITEMS.register("steel_kabuto",
            () -> new SniffsWeaponsArmorItem(ModArmorMaterials.STEEL, ArmorItem.Type.HELMET, "samurai", 16777215, new Item.Properties()));
    public static final RegistryObject<Item> DIARKRITE_KABUTO = ITEMS.register("diarkrite_kabuto",
            () -> new SniffsWeaponsArmorItem(ModArmorMaterials.DIARKRITE, ArmorItem.Type.HELMET, "samurai", 16777215, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_KABUTO = ITEMS.register("anthektite_kabuto",
            () -> new SniffsWeaponsArmorItem(ModArmorMaterials.ANTHEKTITE, ArmorItem.Type.HELMET, "samurai", 16777215, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> CLOTHED_STEEL_CUIRASS = ITEMS.register("clothed_steel_cuirass",
            () -> new SniffsWeaponsArmorItem(ModArmorMaterials.STEEL, ArmorItem.Type.CHESTPLATE, "clothed", 10511680, new Item.Properties()));
    public static final RegistryObject<Item> CLOTHED_DIARKRITE_CUIRASS = ITEMS.register("clothed_diarkrite_cuirass",
            () -> new SniffsWeaponsArmorItem(ModArmorMaterials.DIARKRITE, ArmorItem.Type.CHESTPLATE, "clothed", 10511680, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> CLOTHED_ANTHEKTITE_CUIRASS = ITEMS.register("clothed_anthektite_cuirass",
            () -> new SniffsWeaponsArmorItem(ModArmorMaterials.ANTHEKTITE, ArmorItem.Type.CHESTPLATE, "clothed", 10511680, new Item.Properties().fireResistant()));

    public static void register(IEventBus eventBus) {ITEMS.register(eventBus);}
}
