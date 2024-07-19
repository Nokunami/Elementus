package net.nokunami.elementus.common.registry;

import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nokunami.elementus.common.config.TierConfig;
import net.nokunami.elementus.common.config.ToolsConfig;
import net.nokunami.elementus.common.config.ToolsConfig;
import net.nokunami.elementus.common.entity.ModBoatEntity;
import net.nokunami.elementus.common.item.*;

import static net.nokunami.elementus.Elementus.MODID;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static float steelSpeed = 0.2F;
    public static float diarkriteSpeed = 0.3F;
    public static float anthektiteSpeed = 0.5F;

    public static final RegistryObject<Item> CRUDE_STEEL = ITEMS.register("crude_steel",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STEEL_SCRAP = ITEMS.register("steel_scrap",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STEEL_NUGGET = ITEMS.register("steel_nugget",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ATELIS_SCRAP = ITEMS.register("atelis_scrap",
                    () -> new Item(new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_INGOT = ITEMS.register("anthektite_ingot",
            () -> new Item(new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> DIARKRITE_INGOT = ITEMS.register("diarkrite_ingot",
            () -> new Item(new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ATELIS_UPGRADE_SMITHING_TEMPLATE = ITEMS.register("atelis_upgrade_smithing_template",
            ModSmithingTemplateItem::createAtelisUpgradeTemplate);

    public static final RegistryObject<Item> STEEL_SWORD = ITEMS.register("steel_sword", () -> new SwordItem(ModTiers.STEEL,
            ToolsConfig.steelSwordDamage, ToolsConfig.steelSwordAttackSpeed - (float) TierConfig.steelWeaponHeaviness + (float) TierConfig.steelWeaponSpeedBoost, new Item.Properties()));

    public static final RegistryObject<Item> STEEL_SHOVEL = ITEMS.register("steel_shovel", () -> new ShovelItem(ModTiers.STEEL,
            ToolsConfig.steelShovelDamage, ToolsConfig.steelShovelAttackSpeed, new Item.Properties()));

    public static final RegistryObject<Item> STEEL_PICKAXE = ITEMS.register("steel_pickaxe", () -> new PickaxeItem(ModTiers.STEEL,
            ToolsConfig.steelPickaxeDamage, ToolsConfig.steelPickaxeAttackSpeed, new Item.Properties()));

    public static final RegistryObject<Item> STEEL_AXE = ITEMS.register("steel_axe", () -> new AxeItem(ModTiers.STEEL,
            ToolsConfig.steelAxeDamage, ToolsConfig.steelAxeAttackSpeed - (float) TierConfig.steelWeaponHeaviness + (float) TierConfig.steelWeaponSpeedBoost, new Item.Properties()));

    public static final RegistryObject<Item> STEEL_HOE = ITEMS.register("steel_hoe", () -> new HoeItem(ModTiers.STEEL,
            ToolsConfig.steelHoeDamage, ToolsConfig.steelHoeAttackSpeed, new Item.Properties()));


    public static final RegistryObject<Item> DIARKRITE_SWORD = ITEMS.register("diarkrite_sword", () -> new SwordItem(ModTiers.DIARKRITE,
             ToolsConfig.diarkriteSwordDamage, ToolsConfig.diarkriteSwordAttackSpeed - (float) TierConfig.diarkriteWeaponHeaviness + (float) TierConfig.diarkriteWeaponSpeedBoost, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> DIARKRITE_SHOVEL = ITEMS.register("diarkrite_shovel", () -> new ShovelItem(ModTiers.DIARKRITE,
            ToolsConfig.diarkriteShovelDamage, ToolsConfig.diarkriteShovelAttackSpeed, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> DIARKRITE_PICKAXE = ITEMS.register("diarkrite_pickaxe", () -> new PickaxeItem(ModTiers.DIARKRITE,
            ToolsConfig.diarkritePickaxeDamage, ToolsConfig.diarkritePickaxeAttackSpeed, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> DIARKRITE_AXE = ITEMS.register("diarkrite_axe", () -> new AxeItem(ModTiers.DIARKRITE,
            ToolsConfig.diarkriteAxeDamage, ToolsConfig.diarkriteAxeAttackSpeed - (float) TierConfig.diarkriteWeaponHeaviness + (float) TierConfig.diarkriteWeaponSpeedBoost, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> DIARKRITE_HOE = ITEMS.register("diarkrite_hoe", () -> new HoeItem(ModTiers.DIARKRITE,
            ToolsConfig.diarkriteHoeDamage, ToolsConfig.diarkriteHoeAttackSpeed, new Item.Properties().fireResistant()));


    public static final RegistryObject<Item> ANTHEKTITE_SWORD = ITEMS.register("anthektite_sword", () -> new SwordItem(ModTiers.ANTHEKTITE,
            ToolsConfig.anthektiteSwordDamage, ToolsConfig.anthektiteSwordAttackSpeed - (float) TierConfig.anthektiteWeaponHeaviness + (float) TierConfig.anthektiteWeaponSpeedBoost, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> ANTHEKTITE_SHOVEL = ITEMS.register("anthektite_shovel", () -> new ShovelItem(ModTiers.ANTHEKTITE,
            ToolsConfig.anthektiteShovelDamage, ToolsConfig.anthektiteShovelAttackSpeed, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> ANTHEKTITE_PICKAXE = ITEMS.register("anthektite_pickaxe", () -> new PickaxeItem(ModTiers.ANTHEKTITE,
            ToolsConfig.anthektitePickaxeDamage, ToolsConfig.anthektitePickaxeAttackSpeed, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> ANTHEKTITE_AXE = ITEMS.register("anthektite_axe", () -> new AxeItem(ModTiers.ANTHEKTITE,
            ToolsConfig.anthektiteAxeDamage, ToolsConfig.anthektiteAxeAttackSpeed - (float) TierConfig.anthektiteWeaponHeaviness + (float) TierConfig.anthektiteWeaponSpeedBoost, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> ANTHEKTITE_HOE = ITEMS.register("anthektite_hoe", () -> new HoeItem(ModTiers.ANTHEKTITE,
            ToolsConfig.anthektiteHoeDamage, ToolsConfig.anthektiteHoeAttackSpeed, new Item.Properties().fireResistant()));


//    public static final RegistryObject<ShieldItem> STEEL_SHIELD = ITEMS.register("steel_shield",
//            () -> new ElementusShieldItem(1,0,new Item.Properties().defaultDurability(Config.steelShieldDurability), ModTiers.STEEL));
//    public static final RegistryObject<ShieldItem> DIARKRITE_SHIELD = ITEMS.register("diarkrite_shield",
//            () -> new ElementusShieldItem(4,-0.05F,new Item.Properties().defaultDurability(Config.diarkriteShieldDurability), ModTiers.DIARKRITE));
//    public static final RegistryObject<ShieldItem> ANTHEKTITE_SHIELD = ITEMS.register("anthektite_shield",
//            () -> new ElementusShieldItem(0,0.1F,new Item.Properties().defaultDurability(Config.anthektiteShieldDurability), ModTiers.ANTHEKTITE));

    public static final RegistryObject<ShieldItem> STEEL_SHIELD = ITEMS.register("steel_shield",
            () -> new ShieldItem(new Item.Properties().defaultDurability(ToolsConfig.steelShieldDurability)));
    public static final RegistryObject<ShieldItem> DIARKRITE_SHIELD = ITEMS.register("diarkrite_shield",
            () -> new ShieldItem(new Item.Properties().defaultDurability(ToolsConfig.diarkriteShieldDurability).fireResistant()));
    public static final RegistryObject<ShieldItem> ANTHEKTITE_SHIELD = ITEMS.register("anthektite_shield",
            () -> new ShieldItem(new Item.Properties().defaultDurability(ToolsConfig.anthektiteShieldDurability).fireResistant()));

    public static final RegistryObject<Item> STEEL_HELMET = ITEMS.register("steel_helmet",
            () -> new ElementusArmorItem1(ModArmorMaterials1.STEEL, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_CHESTPLATE = ITEMS.register("steel_chestplate",
            () -> new ElementusArmorItem1(ModArmorMaterials1.STEEL, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_LEGGINGS = ITEMS.register("steel_leggings",
            () -> new ElementusArmorItem1(ModArmorMaterials1.STEEL, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_BOOTS = ITEMS.register("steel_boots",
            () -> new ElementusArmorItem1(ModArmorMaterials1.STEEL, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> DIARKRITE_HELMET = ITEMS.register("diarkrite_helmet",
            () -> new ElementusArmorItem1(ModArmorMaterials1.DIARKRITE, ArmorItem.Type.HELMET, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> DIARKRITE_CHESTPLATE = ITEMS.register("diarkrite_chestplate",
            () -> new ElementusArmorItem1(ModArmorMaterials1.DIARKRITE, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> DIARKRITE_LEGGINGS = ITEMS.register("diarkrite_leggings",
            () -> new ElementusArmorItem1(ModArmorMaterials1.DIARKRITE, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> DIARKRITE_BOOTS = ITEMS.register("diarkrite_boots",
            () -> new ElementusArmorItem1(ModArmorMaterials1.DIARKRITE, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> ANTHEKTITE_HELMET = ITEMS.register("anthektite_helmet",
            () -> new ElementusArmorItem1(ModArmorMaterials1.ANTHEKTITE, ArmorItem.Type.HELMET, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_CHESTPLATE = ITEMS.register("anthektite_chestplate",
            () -> new ElementusArmorItem1(ModArmorMaterials1.ANTHEKTITE, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_LEGGINGS = ITEMS.register("anthektite_leggings",
            () -> new ElementusArmorItem1(ModArmorMaterials1.ANTHEKTITE, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_BOOTS = ITEMS.register("anthektite_boots",
            () -> new ElementusArmorItem1(ModArmorMaterials1.ANTHEKTITE, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> MOVCADIA_BARK = ITEMS.register("movcadia_bark",
            () -> new Item(new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> MOVCADIA_SAPLING = ITEMS.register("movcadia_sapling",
            () -> new BlockItem(ModBlocks.MOVCADIA_SAPLING.get(), new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> STEEL_BLOCK = ITEMS.register("steel_block",
            () -> new BlockItem(ModBlocks.STEEL_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> DIARKRITE_BLOCK = ITEMS.register("diarkrite_block",
            () -> new BlockItem(ModBlocks.DIARKRITE_BLOCK.get(), new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_BLOCK = ITEMS.register("anthektite_block",
            () -> new BlockItem(ModBlocks.ANTHEKTITE_BLOCK.get(), new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> REMNANT = ITEMS.register("remnant",
            () -> new BlockItem(ModBlocks.REMNANT.get(), new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> STEEL_BARS = ITEMS.register("steel_bars",
            () -> new BlockItem(ModBlocks.STEEL_BARS.get(), new Item.Properties()));

    public static final RegistryObject<Item> MOVCADIA_ROOTS = ITEMS.register("movcadia_roots",
            () -> new BlockItem(ModBlocks.MOVCADIA_ROOTS.get(), new Item.Properties()));
    public static final RegistryObject<Item> MOVCADIA_LOG = ITEMS.register("movcadia_log",
            () -> new BlockItem(ModBlocks.MOVCADIA_LOG.get(), new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> STRIPPED_MOVCADIA_LOG = ITEMS.register("stripped_movcadia_log",
            () -> new BlockItem(ModBlocks.STRIPPED_MOVCADIA_LOG.get(), new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> MOVCADIA_WOOD = ITEMS.register("movcadia_wood",
            () -> new BlockItem(ModBlocks.MOVCADIA_WOOD.get(), new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> STRIPPED_MOVCADIA_WOOD = ITEMS.register("stripped_movcadia_wood",
            () -> new BlockItem(ModBlocks.STRIPPED_MOVCADIA_WOOD.get(), new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> MOVCADIA_LEAVES = ITEMS.register("movcadia_leaves",
            () -> new BlockItem(ModBlocks.MOVCADIA_LEAVES.get(), new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> MOVCADIA_PLANKS = ITEMS.register("movcadia_planks",
            () -> new BlockItem(ModBlocks.MOVCADIA_PLANKS.get(), new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> MOVCADIA_STAIRS = ITEMS.register("movcadia_stairs",
            () -> new BlockItem(ModBlocks.MOVCADIA_STAIRS.get(), new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> MOVCADIA_SLAB = ITEMS.register("movcadia_slab",
            () -> new BlockItem(ModBlocks.MOVCADIA_SLAB.get(), new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> MOVCADIA_DOOR = ITEMS.register("movcadia_door",
            () -> new BlockItem(ModBlocks.MOVCADIA_DOOR.get(), new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> MOVCADIA_TRAPDOOR = ITEMS.register("movcadia_trapdoor",
            () -> new BlockItem(ModBlocks.MOVCADIA_TRAPDOOR.get(), new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> MOVCADIA_PRESSURE_PLATE = ITEMS.register("movcadia_pressure_plate",
            () -> new BlockItem(ModBlocks.MOVCADIA_PRESSURE_PLATE.get(), new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> MOVCADIA_BUTTON = ITEMS.register("movcadia_button",
            () -> new BlockItem(ModBlocks.MOVCADIA_BUTTON.get(), new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> MOVCADIA_FENCE = ITEMS.register("movcadia_fence",
            () -> new BlockItem(ModBlocks.MOVCADIA_FENCE.get(), new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> MOVCADIA_FENCE_GATE = ITEMS.register("movcadia_fence_gate",
            () -> new BlockItem(ModBlocks.MOVCADIA_FENCE_GATE.get(), new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> MOVCADIA_SIGN = ITEMS.register("movcadia_sign",
            () -> new SignItem(new Item.Properties().fireResistant().stacksTo(16), ModBlocks.MOVCADIA_SIGN.get(), ModBlocks.MOVCADIA_WALL_SIGN.get()));
    public static final RegistryObject<Item> MOVCADIA_HANGING_SIGN = ITEMS.register("movcadia_hanging_sign",
            () -> new HangingSignItem(ModBlocks.MOVCADIA_HANGING_SIGN.get(), ModBlocks.MOVCADIA_WALL_HANGING_SIGN.get(), new Item.Properties().fireResistant().stacksTo(16)));
    public static final RegistryObject<Item> STURDY_MOVCADIA_SIGN = ITEMS.register("sturdy_movcadia_sign",
            () -> new SignItem(new Item.Properties().fireResistant().stacksTo(16), ModBlocks.STURDY_MOVCADIA_SIGN.get(), ModBlocks.STURDY_MOVCADIA_WALL_SIGN.get()));

    public static final RegistryObject<Item> MOVCADIA_CHEST = ITEMS.register("movcadia_chest",
            () -> new BlockItem(ModBlocks.MOVCADIA_CHEST.get(), new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> MOVCADIA_BOAT = ITEMS.register("movcadia_boat",
            () -> new ModBoatItem(false, ModBoatEntity.Type.MOVCADIA, new Item.Properties().fireResistant().stacksTo(1)));
    public static final RegistryObject<Item> MOVCADIA_CHEST_BOAT = ITEMS.register("movcadia_chest_boat",
            () -> new ModBoatItem(true, ModBoatEntity.Type.MOVCADIA, new Item.Properties().fireResistant().stacksTo(1)));


    public static void register(IEventBus eventBus) {ITEMS.register(eventBus);}
}
