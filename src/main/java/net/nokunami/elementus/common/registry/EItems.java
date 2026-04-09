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
import net.nokunami.elementus.common.config.ItemConfig;
import net.nokunami.elementus.common.config.TierConfig;
import net.nokunami.elementus.common.entity.vehicle.ModBoatEntity;
import net.nokunami.elementus.common.item.*;
import net.nokunami.elementus.common.item.basic.*;
import net.nokunami.elementus.common.item.unique.BladeOfSurgingWinds;
import net.nokunami.elementus.common.item.unique.BladeOfResonance;
import net.nokunami.elementus.common.item.unique.TestCatalystArmorItem;
import net.nokunami.elementus.common.item.unique.WrathTridentItem;

import static net.nokunami.elementus.Elementus.EID;

public class EItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, EID);

    public static float steelSpeed = (float) TierConfig.steelWeaponSpeedModifier;
    public static float diarkriteSpeed = (float) TierConfig.diarkriteWeaponSpeedModifier;
    public static float anthektiteSpeed = (float) TierConfig.anthektiteWeaponSpeedModifier;
    public static float movcadiaSpeed = (float) TierConfig.movcadiaWeaponSpeedModifier;

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

    public static final RegistryObject<Item> STEEL_GOLEM_SPAWN_EGG = ITEMS.register("steel_golem_spawn_egg",
            () -> new ForgeSpawnEggItem(EEntityTypes.OLD_STEEL_GOLEM, 14144729, 7238279, new Item.Properties()));

    public static final RegistryObject<Item> ASTALITE_GOLEM_SPAWN_EGG = ITEMS.register("astalite_golem_spawn_egg",
            () -> new ForgeSpawnEggItem(EEntityTypes.ASTALITE_GOLEM_CARRIER, 14144729, 7238279, new Item.Properties()));

    public static final RegistryObject<Item> ASTALITE_GOLEM_LONGARM_SPAWN_EGG = ITEMS.register("astalite_golem_longarm_spawn_egg",
            () -> new ForgeSpawnEggItem(EEntityTypes.ASTALITE_GOLEM_LONGARM, 14144729, 7238279, new Item.Properties()));

    public static final RegistryObject<Item> ASTALITE_SWORD = ITEMS.register("astalite_sword", () -> new ESwordItem(ETier.EnumTiers.ASTALITE, new Item.Properties()));
    public static final RegistryObject<Item> ASTALITE_SHOVEL = ITEMS.register("astalite_shovel", () -> new EShovelItem(ETier.EnumTiers.ASTALITE, new Item.Properties()));
    public static final RegistryObject<Item> ASTALITE_PICKAXE = ITEMS.register("astalite_pickaxe", () -> new EPickaxeItem(ETier.EnumTiers.ASTALITE, new Item.Properties()));
    public static final RegistryObject<Item> ASTALITE_AXE = ITEMS.register("astalite_axe", () -> new EAxeItem(ETier.EnumTiers.ASTALITE, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ASTALITE_HOE = ITEMS.register("astalite_hoe", () -> new EHoeItem(ETier.EnumTiers.ASTALITE, new Item.Properties()));

    public static final RegistryObject<Item> DIARKRITE_SWORD = ITEMS.register("diarkrite_sword", () -> new ESwordItem(ETier.EnumTiers.DIARKRITE, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> DIARKRITE_SHOVEL = ITEMS.register("diarkrite_shovel", () -> new EShovelItem(ETier.EnumTiers.DIARKRITE, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> DIARKRITE_PICKAXE = ITEMS.register("diarkrite_pickaxe", () -> new EPickaxeItem(ETier.EnumTiers.DIARKRITE, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> DIARKRITE_AXE = ITEMS.register("diarkrite_axe", () -> new EAxeItem(ETier.EnumTiers.DIARKRITE, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> DIARKRITE_HOE = ITEMS.register("diarkrite_hoe", () -> new EHoeItem(ETier.EnumTiers.DIARKRITE, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> ANTHEKTITE_SWORD = ITEMS.register("anthektite_sword", () -> new ESwordItem(ETier.EnumTiers.ANTHEKTITE, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_SHOVEL = ITEMS.register("anthektite_shovel", () -> new EShovelItem(ETier.EnumTiers.ANTHEKTITE, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_PICKAXE = ITEMS.register("anthektite_pickaxe", () -> new EPickaxeItem(ETier.EnumTiers.ANTHEKTITE, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_AXE = ITEMS.register("anthektite_axe", () -> new EAxeItem(ETier.EnumTiers.ANTHEKTITE, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_HOE = ITEMS.register("anthektite_hoe", () -> new EHoeItem(ETier.EnumTiers.ANTHEKTITE, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> MOVCADIA_SWORD = ITEMS.register("movcadia_sword", MovcadiaSwordItem::new);
    public static final RegistryObject<Item> MOVCADIA_SHOVEL = ITEMS.register("movcadia_shovel", MovcadiaShovelItem::new);
    public static final RegistryObject<Item> MOVCADIA_PICKAXE = ITEMS.register("movcadia_pickaxe", MovcadiaPickaxeItem::new);
    public static final RegistryObject<Item> MOVCADIA_AXE = ITEMS.register("movcadia_axe", MovcadiaAxeItem::new);
    public static final RegistryObject<Item> MOVCADIA_HOE = ITEMS.register("movcadia_hoe", MovcadiaHoeItem::new);

    public static final RegistryObject<Item> DIARKRITE_CHARGE_BLADE = ITEMS.register("diarkrite_charge_blade", BladeOfResonance::new);
    public static final RegistryObject<Item> ANTHEKTITE_CHARGE_BLADE = ITEMS.register("anthektite_charge_blade", BladeOfSurgingWinds::new);

    public static final RegistryObject<Item> ASTALITE_SHIELD = ITEMS.register("astalite_shield",
            () -> new EShieldItem(new Item.Properties().defaultDurability(ItemConfig.steelShieldDurability), 0, ETier.EnumTiers.ASTALITE));
    public static final RegistryObject<Item> DIARKRITE_SHIELD = ITEMS.register("diarkrite_shield",
            () -> new EShieldItem(new Item.Properties().defaultDurability(ItemConfig.diarkriteShieldDurability).fireResistant(), 3, ETier.EnumTiers.DIARKRITE));
    public static final RegistryObject<Item> ANTHEKTITE_SHIELD = ITEMS.register("anthektite_shield",
            () -> new EShieldItem(new Item.Properties().defaultDurability(ItemConfig.anthektiteShieldDurability).fireResistant(), 0, ETier.EnumTiers.ANTHEKTITE));

    public static final RegistryObject<Item> ASTALITE_BOW = ITEMS.register("astalite_bow",
            () -> new EBowItem(new Item.Properties().defaultDurability(ItemConfig.steelBowDurability), ETier.EnumTiers.ASTALITE));
    public static final RegistryObject<Item> DIARKRITE_BOW = ITEMS.register("diarkrite_bow",
            () -> new EBowItem(new Item.Properties().defaultDurability(ItemConfig.diarkriteBowDurability).fireResistant(), ETier.EnumTiers.DIARKRITE));
    public static final RegistryObject<Item> ANTHEKTITE_BOW = ITEMS.register("anthektite_bow",
            () -> new EBowItem(new Item.Properties().defaultDurability(ItemConfig.anthektiteBowDurability).fireResistant(), ETier.EnumTiers.ANTHEKTITE));

    public static final RegistryObject<Item> ASTALITE_HELMET = ITEMS.register("astalite_helmet",
            () -> new ElementusArmorItem(EArmorMaterials.EnumArmorMaterials.ASTALITE, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> ASTALITE_CHESTPLATE = ITEMS.register("astalite_chestplate",
            () -> new ElementusArmorItem(EArmorMaterials.EnumArmorMaterials.ASTALITE, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> ASTALITE_LEGGINGS = ITEMS.register("astalite_leggings",
            () -> new ElementusArmorItem(EArmorMaterials.EnumArmorMaterials.ASTALITE, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> ASTALITE_BOOTS = ITEMS.register("astalite_boots",
            () -> new ElementusArmorItem(EArmorMaterials.EnumArmorMaterials.ASTALITE, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> DIARKRITE_HELMET = ITEMS.register("diarkrite_helmet",
            () -> new ElementusArmorItem(EArmorMaterials.EnumArmorMaterials.DIARKRITE, ArmorItem.Type.HELMET, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> DIARKRITE_CHESTPLATE = ITEMS.register("diarkrite_chestplate",
            () -> new ElementusArmorItem(EArmorMaterials.EnumArmorMaterials.DIARKRITE, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> DIARKRITE_LEGGINGS = ITEMS.register("diarkrite_leggings",
            () -> new ElementusArmorItem(EArmorMaterials.EnumArmorMaterials.DIARKRITE, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> DIARKRITE_BOOTS = ITEMS.register("diarkrite_boots",
            () -> new ElementusArmorItem(EArmorMaterials.EnumArmorMaterials.DIARKRITE, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> ANTHEKTITE_HELMET = ITEMS.register("anthektite_helmet",
            () -> new ElementusArmorItem(EArmorMaterials.EnumArmorMaterials.ANTHEKTITE, ArmorItem.Type.HELMET, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_CHESTPLATE = ITEMS.register("anthektite_chestplate",
            () -> new ElementusArmorItem(EArmorMaterials.EnumArmorMaterials.ANTHEKTITE, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_LEGGINGS = ITEMS.register("anthektite_leggings",
            () -> new ElementusArmorItem(EArmorMaterials.EnumArmorMaterials.ANTHEKTITE, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_BOOTS = ITEMS.register("anthektite_boots",
            () -> new ElementusArmorItem(EArmorMaterials.EnumArmorMaterials.ANTHEKTITE, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> CATALYST_CHESTPLATE = ITEMS.register("catalyst_chestplate",
            () -> new TestCatalystArmorItem(EArmorMaterials.EnumArmorMaterials.CATALYST, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant().rarity(Rarity.EPIC)));

//    public static final RegistryObject<Item> TEST_CATALYST_CHESTPLATE = ITEMS.register("test_catalyst_chestplate",
//            () -> new TestCatalystArmorItem(ModArmorMaterials.CATALYST, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant().rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> WRATH_TRIDENT = ITEMS.register("wrath_trident",
            () -> new WrathTridentItem(new Item.Properties().durability(250).fireResistant().rarity(Rarity.EPIC)));

//        public static final RegistryObject<Item> DIARKRITE_BOOTS_SCULK = ITEMS.register("diarkrite_boots_sculk",
//                () -> new DiarkriteBootsItem(ModArmorMaterials.DIARKRITE, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant().rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> REINFORCED_PLATING_GOLEM_UPGRADE = ITEMS.register("reinforced_plating_golem_upgrade",
            () -> new SteelGolemUpgradeItem("reinforced_plating", new Item.Properties(), new GolemUpgradeProperties.Builder()
//                    .armor(10).toughness(8)
                    .armorAttributes(SteelGolemUpgradeAttributes.reinforcedPlating())
                    .isNotPushable()
                    .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 0, 1)).build()));

    public static final RegistryObject<Item> DAMAGE_GOLEM_UPGRADE = ITEMS.register("damage_golem_upgrade",
            () -> new SteelGolemUpgradeItem("reinforced_plating", new Item.Properties(), new GolemUpgradeProperties.Builder()
                    .armor(4).isFastAttack()
                    .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 0, 0)).build()));

    public static final RegistryObject<Item> MOVCADIA_SAPLING = ITEMS.register("movcadia_sapling",
            () -> new BlockItem(EBlocks.MOVCADIA_SAPLING.get(), new Item.Properties().fireResistant()));

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

    public static void register(IEventBus eventBus) {
        EItems.ITEMS.register(eventBus);
    }
}
