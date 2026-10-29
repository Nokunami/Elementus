package net.nokunami.elementus.common.registry;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nokunami.elementus.config.EConfig;
import net.nokunami.elementus.common.entity.vehicle.ModBoatEntity;
import net.nokunami.elementus.common.item.*;
import net.nokunami.elementus.common.item.basic.*;
import net.nokunami.elementus.common.item.unique.*;

import static net.nokunami.elementus.Elementus.EID;

public class EItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, EID);

    // ------ [ MATERIALS ]

    public static final RegistryObject<Item> CRUDE_STEEL = ITEMS.register("crude_steel",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ASTALITE_SCRAP = ITEMS.register("astalite_scrap",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ASTALITE_INGOT = ITEMS.register("astalite_ingot",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ASTALITE_NUGGET = ITEMS.register("astalite_nugget",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ATELIS_SCRAP = ITEMS.register("atelis_scrap",
            () -> new Item(new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_INGOT = ITEMS.register("anthektite_ingot",
            () -> new Item(new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> DIARKRITE_INGOT = ITEMS.register("diarkrite_ingot",
            () -> new Item(new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ATELIS_UPGRADE_SMITHING_TEMPLATE = ITEMS.register("atelis_upgrade_smithing_template",
            ModSmithingTemplateItem::createAtelisUpgradeTemplate);
    public static final RegistryObject<Item> WEAPON_FRAGMENT = ITEMS.register("weapon_fragment",
            ModSmithingTemplateItem::createWeaponFragment);

    public static final RegistryObject<Item> MOVCADIA_BERRIES = ITEMS.register("movcadia_berries",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(4).saturationMod(0.3F)
                    .effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 300), 1)
                    .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 300), 1)
                    .fast().build())));

    public static final RegistryObject<Item> GLISTERING_MOVCADIA_BERRIES = ITEMS.register("glistering_movcadia_berries",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .alwaysEat().nutrition(4).saturationMod(0.8F)
                    .effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 500, 1), 1)
                    .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 500, 1), 1)
                    .fast().build())));

    public static final RegistryObject<Item> MOVCADIA_ESSENCE = ITEMS.register("movcadia_essence",
            () -> new Item(new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> CRUSHED_REMNANT = ITEMS.register("crushed_remnant",
            () -> new Item(new Item.Properties().fireResistant()));

    // ------ [ MISC ]

    public static final RegistryObject<Item> STEEL_GOLEM_SPAWN_EGG = ITEMS.register("steel_golem_spawn_egg",
            () -> new ForgeSpawnEggItem(EEntityTypes.OLD_STEEL_GOLEM, 14144729, 7238279, new Item.Properties()));

    public static final RegistryObject<Item> ASTALITE_GOLEM_SPAWN_EGG = ITEMS.register("astalite_golem_spawn_egg",
            () -> new ForgeSpawnEggItem(EEntityTypes.ASTALITE_GOLEM_CARRIER, 14144729, 7238279, new Item.Properties()));

    public static final RegistryObject<Item> ASTALITE_GOLEM_LONGARM_SPAWN_EGG = ITEMS.register("astalite_golem_longarm_spawn_egg",
            () -> new ForgeSpawnEggItem(EEntityTypes.ASTALITE_GOLEM_LONGARM, 14144729, 7238279, new Item.Properties()));

    // ------ [ TOOLS / WEAPONS ]

    public static final RegistryObject<Item> ASTALITE_SWORD = tool(ToolType.SWORD, "astalite_sword", ETiers.ASTALITE, new Item.Properties());
    public static final RegistryObject<Item> ASTALITE_SHOVEL = tool(ToolType.SHOVEL, "astalite_shovel", ETiers.ASTALITE, new Item.Properties());
    public static final RegistryObject<Item> ASTALITE_PICKAXE = tool(ToolType.PICKAXE, "astalite_pickaxe", ETiers.ASTALITE, new Item.Properties());
    public static final RegistryObject<Item> ASTALITE_AXE = tool(ToolType.AXE, "astalite_axe", ETiers.ASTALITE, new Item.Properties());
    public static final RegistryObject<Item> ASTALITE_HOE = tool(ToolType.HOE, "astalite_hoe", ETiers.ASTALITE, new Item.Properties());

    public static final RegistryObject<Item> DIARKRITE_SWORD = tool(ToolType.SWORD, "diarkrite_sword", ETiers.DIARKRITE, new Item.Properties().fireResistant());
    public static final RegistryObject<Item> DIARKRITE_SHOVEL = tool(ToolType.SHOVEL, "diarkrite_shovel", ETiers.DIARKRITE, new Item.Properties().fireResistant());
    public static final RegistryObject<Item> DIARKRITE_PICKAXE = tool(ToolType.PICKAXE, "diarkrite_pickaxe", ETiers.DIARKRITE, new Item.Properties().fireResistant());
    public static final RegistryObject<Item> DIARKRITE_AXE = tool(ToolType.AXE, "diarkrite_axe", ETiers.DIARKRITE, new Item.Properties().fireResistant());
    public static final RegistryObject<Item> DIARKRITE_HOE = tool(ToolType.HOE, "diarkrite_hoe", ETiers.DIARKRITE, new Item.Properties().fireResistant());

    public static final RegistryObject<Item> ANTHEKTITE_SWORD = tool(ToolType.SWORD, "anthektite_sword", ETiers.ANTHEKTITE, new Item.Properties().fireResistant());
    public static final RegistryObject<Item> ANTHEKTITE_SHOVEL = tool(ToolType.SHOVEL, "anthektite_shovel", ETiers.ANTHEKTITE, new Item.Properties().fireResistant());
    public static final RegistryObject<Item> ANTHEKTITE_PICKAXE = tool(ToolType.PICKAXE, "anthektite_pickaxe", ETiers.ANTHEKTITE, new Item.Properties().fireResistant());
    public static final RegistryObject<Item> ANTHEKTITE_AXE = tool(ToolType.AXE, "anthektite_axe", ETiers.ANTHEKTITE, new Item.Properties().fireResistant());
    public static final RegistryObject<Item> ANTHEKTITE_HOE = tool(ToolType.HOE, "anthektite_hoe", ETiers.ANTHEKTITE, new Item.Properties().fireResistant());

    public static final RegistryObject<Item> MOVCADIA_SWORD = movcadiaTool(ToolType.SWORD, "movcadia_sword", ETiers.MOVCADIA);
    public static final RegistryObject<Item> MOVCADIA_SHOVEL = movcadiaTool(ToolType.SHOVEL, "movcadia_shovel", ETiers.MOVCADIA);
    public static final RegistryObject<Item> MOVCADIA_PICKAXE = movcadiaTool(ToolType.PICKAXE, "movcadia_pickaxe", ETiers.MOVCADIA);
    public static final RegistryObject<Item> MOVCADIA_AXE = movcadiaTool(ToolType.AXE, "movcadia_axe", ETiers.MOVCADIA);
    public static final RegistryObject<Item> MOVCADIA_HOE = movcadiaTool(ToolType.HOE, "movcadia_hoe", ETiers.MOVCADIA);

    public static final RegistryObject<Item> ASTALITE_SHIELD = ITEMS.register("astalite_shield", () -> new EShieldItem(ETiers.ASTALITE));
    public static final RegistryObject<Item> DIARKRITE_SHIELD = ITEMS.register("diarkrite_shield", () -> new EShieldItem(ETiers.DIARKRITE));
    public static final RegistryObject<Item> ANTHEKTITE_SHIELD = ITEMS.register("anthektite_shield", () -> new EShieldItem(ETiers.ANTHEKTITE));

    public static final RegistryObject<Item> ASTALITE_BOW = ITEMS.register("astalite_bow",
            () -> new EBowItem(new Item.Properties(), ETiers.ASTALITE));
    public static final RegistryObject<Item> DIARKRITE_BOW = ITEMS.register("diarkrite_bow",
            () -> new EBowItem(new Item.Properties().fireResistant(), ETiers.DIARKRITE));
    public static final RegistryObject<Item> ANTHEKTITE_BOW = ITEMS.register("anthektite_bow",
            () -> new EBowItem(new Item.Properties().fireResistant(), ETiers.ANTHEKTITE));

    // ------ [ ARMORS ]

    public static final RegistryObject<Item> ASTALITE_HELMET = ITEMS.register("astalite_helmet",
            () -> new EArmorItem(EArmorMaterials.ASTALITE, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> ASTALITE_CHESTPLATE = ITEMS.register("astalite_chestplate",
            () -> new EArmorItem(EArmorMaterials.ASTALITE, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> ASTALITE_LEGGINGS = ITEMS.register("astalite_leggings",
            () -> new EArmorItem(EArmorMaterials.ASTALITE, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> ASTALITE_BOOTS = ITEMS.register("astalite_boots",
            () -> new EArmorItem(EArmorMaterials.ASTALITE, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> DIARKRITE_HELMET = ITEMS.register("diarkrite_helmet",
            () -> new EArmorItem(EArmorMaterials.DIARKRITE, ArmorItem.Type.HELMET, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> DIARKRITE_CHESTPLATE = ITEMS.register("diarkrite_chestplate",
            () -> new EArmorItem(EArmorMaterials.DIARKRITE, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> DIARKRITE_LEGGINGS = ITEMS.register("diarkrite_leggings",
            () -> new EArmorItem(EArmorMaterials.DIARKRITE, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> DIARKRITE_BOOTS = ITEMS.register("diarkrite_boots",
            () -> new EArmorItem(EArmorMaterials.DIARKRITE, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> ANTHEKTITE_HELMET = ITEMS.register("anthektite_helmet",
            () -> new EArmorItem(EArmorMaterials.ANTHEKTITE, ArmorItem.Type.HELMET, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_CHESTPLATE = ITEMS.register("anthektite_chestplate",
            () -> new EArmorItem(EArmorMaterials.ANTHEKTITE, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_LEGGINGS = ITEMS.register("anthektite_leggings",
            () -> new EArmorItem(EArmorMaterials.ANTHEKTITE, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_BOOTS = ITEMS.register("anthektite_boots",
            () -> new EArmorItem(EArmorMaterials.ANTHEKTITE, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant()));

    // ------ [ RELICS ]

    public static final RegistryObject<Item> BLADE_OF_RESONANCE = ITEMS.register("diarkrite_charge_blade", BladeOfResonance::new);
    public static final RegistryObject<Item> ANTHEKTITE_CHARGE_BLADE = ITEMS.register("anthektite_charge_blade", BladeOfSurgingWinds::new);

    public static final RegistryObject<Item> CATALYST_CHESTPLATE = ITEMS.register("catalyst_chestplate",
            () -> new TestCatalystArmorItem(new Item.Properties().fireResistant().rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> WRATH_TRIDENT = ITEMS.register("wrath_trident",
            () -> new WrathTridentItem(new Item.Properties().durability(250).fireResistant().rarity(Rarity.EPIC)));

//        public static final RegistryObject<Item> DIARKRITE_BOOTS_SCULK = ITEMS.register("diarkrite_boots_sculk",
//                () -> new DiarkriteBootsItem(ModArmorMaterials.DIARKRITE, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant().rarity(Rarity.EPIC)));

        public static final RegistryObject<Item> LEAVI_PACK = ITEMS.register("leavi_pack",
                () -> new LeaviPackItem(new Item.Properties().fireResistant().rarity(Rarity.EPIC)));

    // ------ [ GOLEM UPGRADES ]

    public static final RegistryObject<Item> REINFORCED_PLATING_GOLEM_UPGRADE = ITEMS.register("reinforced_plating_golem_upgrade",
            () -> new SteelGolemUpgradeItem("reinforced_plating", new Item.Properties(), new GolemUpgradeProperties.Builder()
                    .armorAttributes(SteelGolemUpgradeAttributes.reinforcedPlating())
                    .isNotPushable()
                    .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 0, 1)).build()));

    public static final RegistryObject<Item> DAMAGE_GOLEM_UPGRADE = ITEMS.register("damage_golem_upgrade",
            () -> new SteelGolemUpgradeItem("reinforced_plating", new Item.Properties(), new GolemUpgradeProperties.Builder()
                    .armor(4).isFastAttack()
                    .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 0, 0)).build()));

    // ------ [ TREE ]

    public static final RegistryObject<Item> MOVCADIA_SAPLING = ITEMS.register("movcadia_sapling",
            () -> new BlockItem(EBlocks.MOVCADIA_SAPLING.get(), new Item.Properties().fireResistant()));

    // ------ [ BUILDING BLOCKS ]

    public static final RegistryObject<Item> STEEL_BLOCK = ITEMS.register("steel_block",
            () -> new BlockItem(EBlocks.ASTALITE_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> DIARKRITE_BLOCK = ITEMS.register("diarkrite_block",
            () -> new BlockItem(EBlocks.DIARKRITE_BLOCK.get(), new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_BLOCK = ITEMS.register("anthektite_block",
            () -> new BlockItem(EBlocks.ANTHEKTITE_BLOCK.get(), new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> REMNANT = ITEMS.register("remnant",
            () -> new BlockItem(EBlocks.REMNANT.get(), new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> STEEL_BARS = ITEMS.register("steel_bars",
            () -> new BlockItem(EBlocks.STEEL_BARS.get(), new Item.Properties()));

    public static final RegistryObject<Item> STEEL_TILES = ITEMS.register("steel_tiles",
            () -> new BlockItem(EBlocks.STEEL_TILES.get(), new Item.Properties()));
    public static final RegistryObject<Item> STEEL_TILE_STAIR = ITEMS.register("steel_tile_stair",
            () -> new BlockItem(EBlocks.STEEL_TILE_STAIR.get(), new Item.Properties()));
    public static final RegistryObject<Item> STEEL_TILE_SLAB = ITEMS.register("steel_tile_slab",
            () -> new BlockItem(EBlocks.STEEL_TILE_SLAB.get(), new Item.Properties()));

    public static final RegistryObject<Item> MOVCADIA_ROOTED_DIRT = ITEMS.register("movcadia_rooted_dirt",
            () -> new BlockItem(EBlocks.MOVCADIA_ROOTED_DIRT.get(), new Item.Properties()));
    public static final RegistryObject<Item> MOVCADIA_ROOTED_STONE = ITEMS.register("movcadia_rooted_stone",
            () -> new BlockItem(EBlocks.MOVCADIA_ROOTED_STONE.get(), new Item.Properties()));
    public static final RegistryObject<Item> MOVCADIA_ROOTED_DEEPSLATE = ITEMS.register("movcadia_rooted_deepslate",
            () -> new BlockItem(EBlocks.MOVCADIA_ROOTED_DEEPSLATE.get(), new Item.Properties()));

    public static final RegistryObject<Item> MOVCADIA_LOG = ITEMS.register("movcadia_log",
            () -> new BlockItem(EBlocks.MOVCADIA_LOG.get(), new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> STRIPPED_MOVCADIA_LOG = ITEMS.register("stripped_movcadia_log",
            () -> new BlockItem(EBlocks.STRIPPED_MOVCADIA_LOG.get(), new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> MOVCADIA_WOOD = ITEMS.register("movcadia_wood",
            () -> new BlockItem(EBlocks.MOVCADIA_WOOD.get(), new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> STRIPPED_MOVCADIA_WOOD = ITEMS.register("stripped_movcadia_wood",
            () -> new BlockItem(EBlocks.STRIPPED_MOVCADIA_WOOD.get(), new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> MOVCADIA_LEAVES = ITEMS.register("movcadia_leaves",
            () -> new BlockItem(EBlocks.MOVCADIA_LEAVES.get(), new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> FLOWERING_MOVCADIA_LEAVES = ITEMS.register("flowering_movcadia_leaves",
            () -> new BlockItem(EBlocks.FLOWERING_MOVCADIA_LEAVES.get(), new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> MOVCADIA_PLANKS = ITEMS.register("movcadia_planks",
            () -> new BlockItem(EBlocks.MOVCADIA_PLANKS.get(), new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> MOVCADIA_STAIRS = ITEMS.register("movcadia_stairs",
            () -> new BlockItem(EBlocks.MOVCADIA_STAIRS.get(), new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> MOVCADIA_SLAB = ITEMS.register("movcadia_slab",
            () -> new BlockItem(EBlocks.MOVCADIA_SLAB.get(), new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> MOVCADIA_DOOR = ITEMS.register("movcadia_door",
            () -> new BlockItem(EBlocks.MOVCADIA_DOOR.get(), new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> MOVCADIA_TRAPDOOR = ITEMS.register("movcadia_trapdoor",
            () -> new BlockItem(EBlocks.MOVCADIA_TRAPDOOR.get(), new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> MOVCADIA_PRESSURE_PLATE = ITEMS.register("movcadia_pressure_plate",
            () -> new BlockItem(EBlocks.MOVCADIA_PRESSURE_PLATE.get(), new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> MOVCADIA_BUTTON = ITEMS.register("movcadia_button",
            () -> new BlockItem(EBlocks.MOVCADIA_BUTTON.get(), new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> MOVCADIA_FENCE = ITEMS.register("movcadia_fence",
            () -> new BlockItem(EBlocks.MOVCADIA_FENCE.get(), new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> MOVCADIA_FENCE_GATE = ITEMS.register("movcadia_fence_gate",
            () -> new BlockItem(EBlocks.MOVCADIA_FENCE_GATE.get(), new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> MOVCADIA_SIGN = ITEMS.register("movcadia_sign",
            () -> new SignItem(new Item.Properties().fireResistant().stacksTo(16), EBlocks.MOVCADIA_SIGN.get(), EBlocks.MOVCADIA_WALL_SIGN.get()));
    public static final RegistryObject<Item> MOVCADIA_HANGING_SIGN = ITEMS.register("movcadia_hanging_sign",
            () -> new HangingSignItem(EBlocks.MOVCADIA_HANGING_SIGN.get(), EBlocks.MOVCADIA_WALL_HANGING_SIGN.get(), new Item.Properties().fireResistant().stacksTo(16)));
    public static final RegistryObject<Item> STURDY_MOVCADIA_SIGN = ITEMS.register("sturdy_movcadia_sign",
            () -> new SignItem(new Item.Properties().fireResistant().stacksTo(16), EBlocks.STURDY_MOVCADIA_SIGN.get(), EBlocks.STURDY_MOVCADIA_WALL_SIGN.get()));

    public static final RegistryObject<Item> MOVCADIA_CHEST = ITEMS.register("movcadia_chest",
            () -> new BlockItem(EBlocks.MOVCADIA_CHEST.get(), new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> MOVCADIA_BOAT = ITEMS.register("movcadia_boat",
            () -> new ModBoatItem(false, ModBoatEntity.Type.MOVCADIA, new Item.Properties().fireResistant().stacksTo(1)));
    public static final RegistryObject<Item> MOVCADIA_CHEST_BOAT = ITEMS.register("movcadia_chest_boat",
            () -> new ModBoatItem(true, ModBoatEntity.Type.MOVCADIA, new Item.Properties().fireResistant().stacksTo(1)));

    public static RegistryObject<Item> movcadiaTool(ToolType type, String id, IETier tier) {
        var tool = EConfig.toolConfig.get(tier);
        if (type == ToolType.SWORD) return ITEMS.register(id, () -> new MovcadiaSwordItem(tier, tool.sword.getDamage(tier), tool.sword.getSpeed(tier)));
        else if (type == ToolType.SHOVEL) return ITEMS.register(id, () -> new MovcadiaShovelItem(tier, tool.shovel.getDamage(tier), tool.shovel.getSpeed(tier)));
        else if (type == ToolType.PICKAXE) return ITEMS.register(id, () -> new MovcadiaPickaxeItem(tier, tool.pickaxe.getDamage(tier), tool.pickaxe.getSpeed(tier)));
        else if (type == ToolType.AXE) return ITEMS.register(id, () -> new MovcadiaAxeItem(tier, tool.axe.getDamage(tier), tool.axe.getSpeed(tier)));
        else return ITEMS.register(id, () -> new MovcadiaHoeItem(tier, tool.hoe.getDamage(tier), tool.hoe.getSpeed(tier)));
    }
    public static RegistryObject<Item> tool(ToolType type, String id, IETier tier, Item.Properties properties) {
        var tool = EConfig.toolConfig.get(tier);
        if (type == ToolType.SWORD) return ITEMS.register(id, () -> new ESwordItem(tier, tool.sword.getDamage(tier), tool.sword.getSpeed(tier), properties));
        else if (type == ToolType.SHOVEL) return ITEMS.register(id, () -> new EShovelItem(tier, tool.shovel.getDamage(tier), tool.shovel.getSpeed(tier), properties));
        else if (type == ToolType.PICKAXE) return ITEMS.register(id, () -> new EPickaxeItem(tier, tool.pickaxe.getDamage(tier), tool.pickaxe.getSpeed(tier), properties));
        else if (type == ToolType.AXE) return ITEMS.register(id, () -> new EAxeItem(tier, tool.axe.getDamage(tier), tool.axe.getSpeed(tier), properties));
        else return ITEMS.register(id, () -> new EHoeItem(tier, tool.hoe.getDamage(tier), tool.hoe.getSpeed(tier), properties));
    }

    public static void register(IEventBus eventBus) { EItems.ITEMS.register(eventBus); }
}
