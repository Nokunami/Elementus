package net.nokunami.elementus.common.registry;

import net.dakotapride.vanilla_claws.item.ClawsItem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nokunami.elementus.common.compat.advancednetherite.ANModItems;
import net.nokunami.elementus.common.compat.epicsamurai.ESModItems;
import net.nokunami.elementus.common.compat.ironsspellbooks.ISSModItems;
import net.nokunami.elementus.common.compat.piercingpaxels.PPModItems;
import net.nokunami.elementus.common.compat.simplyswords.SSModItems;
import net.nokunami.elementus.common.compat.sniffsweapons.SWModItems;
import net.nokunami.elementus.common.compat.theaether.TAModItems;
import net.nokunami.elementus.common.compat.twigs.TWModItems;
import net.nokunami.elementus.common.config.*;
import net.nokunami.elementus.common.entity.vehicle.ModBoatEntity;
import net.nokunami.elementus.common.item.*;
import net.nokunami.elementus.common.registry.ModBlocks.ElementusBlocks;

import static net.nokunami.elementus.Elementus.MODID;
import static net.nokunami.elementus.ModChecker.*;

public class ModItems {
    public static float steelSpeed = (float) TierConfig.steelWeaponSpeedModifier;
    public static float diarkriteSpeed = (float) TierConfig.diarkriteWeaponSpeedModifier;
    public static float anthektiteSpeed = (float) TierConfig.anthektiteWeaponSpeedModifier;
    public static float movcadiaSpeed = (float) TierConfig.movcadiaWeaponSpeedModifier;

    public static class ElementusItems {
        public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

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
                () -> new ForgeSpawnEggItem(ModEntityType.STEEL_GOLEM, 14144729, 7238279, new Item.Properties()));

        public static final RegistryObject<Item> STEEL_SWORD = ITEMS.register("steel_sword", () -> new ModSwordItem(ModTiers.STEEL,
                ItemConfig.steelSwordDamage, (float) ItemConfig.steelSwordAttackSpeed + steelSpeed, new Item.Properties()));

        public static final RegistryObject<Item> STEEL_SHOVEL = ITEMS.register("steel_shovel", () -> new ShovelItem(ModTiers.STEEL,
                (float) ItemConfig.steelShovelDamage, (float) ItemConfig.steelShovelAttackSpeed, new Item.Properties()));

        public static final RegistryObject<Item> STEEL_PICKAXE = ITEMS.register("steel_pickaxe", () -> new PickaxeItem(ModTiers.STEEL,
                ItemConfig.steelPickaxeDamage, (float) ItemConfig.steelPickaxeAttackSpeed, new Item.Properties()));

        public static final RegistryObject<Item> STEEL_AXE = ITEMS.register("steel_axe", () -> new AxeItem(ModTiers.STEEL,
                (float) ItemConfig.steelAxeDamage, (float) ItemConfig.steelAxeAttackSpeed + steelSpeed, new Item.Properties()));

        public static final RegistryObject<Item> STEEL_HOE = ITEMS.register("steel_hoe", () -> new HoeItem(ModTiers.STEEL,
                ItemConfig.steelHoeDamage, (float) ItemConfig.steelHoeAttackSpeed, new Item.Properties()));


        public static final RegistryObject<Item> DIARKRITE_SWORD = ITEMS.register("diarkrite_sword", () -> new ModSwordItem(ModTiers.DIARKRITE,
                ItemConfig.diarkriteSwordDamage, (float) ItemConfig.diarkriteSwordAttackSpeed + diarkriteSpeed, new Item.Properties().fireResistant()));

        public static final RegistryObject<Item> DIARKRITE_SHOVEL = ITEMS.register("diarkrite_shovel", () -> new ShovelItem(ModTiers.DIARKRITE,
                (float) ItemConfig.diarkriteShovelDamage, (float) ItemConfig.diarkriteShovelAttackSpeed, new Item.Properties().fireResistant()));

        public static final RegistryObject<Item> DIARKRITE_PICKAXE = ITEMS.register("diarkrite_pickaxe", () -> new ModPickaxeItem(ModTiers.DIARKRITE,
                ItemConfig.diarkritePickaxeDamage, (float) ItemConfig.diarkritePickaxeAttackSpeed, new Item.Properties().fireResistant()));

        public static final RegistryObject<Item> DIARKRITE_AXE = ITEMS.register("diarkrite_axe", () -> new AxeItem(ModTiers.DIARKRITE,
                (float) ItemConfig.diarkriteAxeDamage, (float) ItemConfig.diarkriteAxeAttackSpeed + diarkriteSpeed, new Item.Properties().fireResistant()));

        public static final RegistryObject<Item> DIARKRITE_HOE = ITEMS.register("diarkrite_hoe", () -> new HoeItem(ModTiers.DIARKRITE,
                ItemConfig.diarkriteHoeDamage, (float) ItemConfig.diarkriteHoeAttackSpeed, new Item.Properties().fireResistant()));


        public static final RegistryObject<Item> ANTHEKTITE_SWORD = ITEMS.register("anthektite_sword", () -> new ModSwordItem(ModTiers.ANTHEKTITE,
                ItemConfig.anthektiteSwordDamage, (float) ItemConfig.anthektiteSwordAttackSpeed + anthektiteSpeed, new Item.Properties().fireResistant()));

        public static final RegistryObject<Item> ANTHEKTITE_SHOVEL = ITEMS.register("anthektite_shovel", () -> new ShovelItem(ModTiers.ANTHEKTITE,
                (float) ItemConfig.anthektiteShovelDamage, (float) ItemConfig.anthektiteShovelAttackSpeed, new Item.Properties().fireResistant()));

        public static final RegistryObject<Item> ANTHEKTITE_PICKAXE = ITEMS.register("anthektite_pickaxe", () -> new ModPickaxeItem(ModTiers.ANTHEKTITE,
                ItemConfig.anthektitePickaxeDamage, (float) ItemConfig.anthektitePickaxeAttackSpeed, new Item.Properties().fireResistant()));

        public static final RegistryObject<Item> ANTHEKTITE_AXE = ITEMS.register("anthektite_axe", () -> new AxeItem(ModTiers.ANTHEKTITE,
                (float) ItemConfig.anthektiteAxeDamage, (float) ItemConfig.anthektiteAxeAttackSpeed + anthektiteSpeed, new Item.Properties().fireResistant()));

        public static final RegistryObject<Item> ANTHEKTITE_HOE = ITEMS.register("anthektite_hoe", () -> new HoeItem(ModTiers.ANTHEKTITE,
                ItemConfig.anthektiteHoeDamage, (float) ItemConfig.anthektiteHoeAttackSpeed, new Item.Properties().fireResistant()));


        public static final RegistryObject<Item> MOVCADIA_SWORD = ITEMS.register("movcadia_sword", () -> new ModSwordItem(ModTiers.MOVCADIA,
                ItemConfig.movcadiaSwordDamage, (float) ItemConfig.movcadiaSwordAttackSpeed + movcadiaSpeed, new Item.Properties().fireResistant()));

        public static final RegistryObject<Item> MOVCADIA_SHOVEL = ITEMS.register("movcadia_shovel", () -> new ModShovelItem(ModTiers.MOVCADIA,
                (float) ItemConfig.movcadiaShovelDamage, (float) ItemConfig.movcadiaShovelAttackSpeed, new Item.Properties().fireResistant()));

        public static final RegistryObject<Item> MOVCADIA_PICKAXE = ITEMS.register("movcadia_pickaxe", () -> new ModPickaxeItem(ModTiers.MOVCADIA,
                ItemConfig.movcadiaPickaxeDamage, (float) ItemConfig.movcadiaPickaxeAttackSpeed, new Item.Properties().fireResistant()));

        public static final RegistryObject<Item> MOVCADIA_AXE = ITEMS.register("movcadia_axe", () -> new ModAxeItem(ModTiers.MOVCADIA,
                (float) ItemConfig.movcadiaAxeDamage, (float) ItemConfig.movcadiaAxeAttackSpeed + movcadiaSpeed, new Item.Properties().fireResistant()));

        public static final RegistryObject<Item> MOVCADIA_HOE = ITEMS.register("movcadia_hoe", () -> new ModHoeItem(ModTiers.MOVCADIA,
                ItemConfig.movcadiaHoeDamage, (float) ItemConfig.movcadiaHoeAttackSpeed, new Item.Properties().fireResistant()));

        public static final RegistryObject<Item> DIARKRITE_CHARGE_BLADE = ITEMS.register("diarkrite_charge_blade", DiarkriteChargeBlade::new);
        public static final RegistryObject<Item> ANTHEKTITE_CHARGE_BLADE = ITEMS.register("anthektite_charge_blade", AnthektiteChargeBlade::new);

        public static final RegistryObject<Item> STEEL_SHIELD = ITEMS.register("steel_shield",
                () -> new ElementusShieldItem(new Item.Properties().defaultDurability(ItemConfig.steelShieldDurability), ModTiers.STEEL));
        public static final RegistryObject<Item> DIARKRITE_SHIELD = ITEMS.register("diarkrite_shield",
                () -> new ElementusShieldItem(new Item.Properties().defaultDurability(ItemConfig.diarkriteShieldDurability).fireResistant(), ModTiers.DIARKRITE));
        public static final RegistryObject<Item> ANTHEKTITE_SHIELD = ITEMS.register("anthektite_shield",
                () -> new ElementusShieldItem(new Item.Properties().defaultDurability(ItemConfig.anthektiteShieldDurability).fireResistant(), ModTiers.ANTHEKTITE));

        public static final RegistryObject<Item> STEEL_BOW = ITEMS.register("steel_bow",
                () -> new ModBowItem(new Item.Properties().defaultDurability(ItemConfig.steelBowDurability), STEEL_INGOT.get()));
        public static final RegistryObject<Item> DIARKRITE_BOW = ITEMS.register("diarkrite_bow",
                () -> new ModBowItem(new Item.Properties().defaultDurability(ItemConfig.diarkriteBowDurability).fireResistant(), DIARKRITE_INGOT.get()));
        public static final RegistryObject<Item> ANTHEKTITE_BOW = ITEMS.register("anthektite_bow",
                () -> new ModBowItem(new Item.Properties().defaultDurability(ItemConfig.anthektiteBowDurability).fireResistant(), ANTHEKTITE_INGOT.get()));

        public static final RegistryObject<Item> STEEL_HELMET = ITEMS.register("steel_helmet",
                () -> new ElementusArmorItem(ModArmorMaterials.STEEL, ArmorItem.Type.HELMET, new Item.Properties()));
        public static final RegistryObject<Item> STEEL_CHESTPLATE = ITEMS.register("steel_chestplate",
                () -> new ElementusArmorItem(ModArmorMaterials.STEEL, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
        public static final RegistryObject<Item> STEEL_LEGGINGS = ITEMS.register("steel_leggings",
                () -> new ElementusArmorItem(ModArmorMaterials.STEEL, ArmorItem.Type.LEGGINGS, new Item.Properties()));
        public static final RegistryObject<Item> STEEL_BOOTS = ITEMS.register("steel_boots",
                () -> new ElementusArmorItem(ModArmorMaterials.STEEL, ArmorItem.Type.BOOTS, new Item.Properties()));

        public static final RegistryObject<Item> DIARKRITE_HELMET = ITEMS.register("diarkrite_helmet",
                () -> new ElementusArmorItem(ModArmorMaterials.DIARKRITE, ArmorItem.Type.HELMET, new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> DIARKRITE_CHESTPLATE = ITEMS.register("diarkrite_chestplate",
                () -> new ElementusArmorItem(ModArmorMaterials.DIARKRITE, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> DIARKRITE_LEGGINGS = ITEMS.register("diarkrite_leggings",
                () -> new ElementusArmorItem(ModArmorMaterials.DIARKRITE, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> DIARKRITE_BOOTS = ITEMS.register("diarkrite_boots",
                () -> new ElementusArmorItem(ModArmorMaterials.DIARKRITE, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant()));

        public static final RegistryObject<Item> ANTHEKTITE_HELMET = ITEMS.register("anthektite_helmet",
                () -> new ElementusArmorItem(ModArmorMaterials.ANTHEKTITE, ArmorItem.Type.HELMET, new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> ANTHEKTITE_CHESTPLATE = ITEMS.register("anthektite_chestplate",
                () -> new ElementusArmorItem(ModArmorMaterials.ANTHEKTITE, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> ANTHEKTITE_LEGGINGS = ITEMS.register("anthektite_leggings",
                () -> new ElementusArmorItem(ModArmorMaterials.ANTHEKTITE, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> ANTHEKTITE_BOOTS = ITEMS.register("anthektite_boots",
                () -> new ElementusArmorItem(ModArmorMaterials.ANTHEKTITE, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant()));

        public static final RegistryObject<Item> CATALYST_CHESTPLATE = ITEMS.register("catalyst_chestplate",
                () -> new CatalystArmorItem(ModArmorMaterials.CATALYST, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant().rarity(Rarity.EPIC)));

//        public static final RegistryObject<Item> DIARKRITE_BOOTS_SCULK = ITEMS.register("diarkrite_boots_sculk",
//                () -> new DiarkriteBootsItem(ModArmorMaterials.DIARKRITE, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant().rarity(Rarity.EPIC)));

        public static final RegistryObject<Item> REINFORCED_PLATING_GOLEM_UPGRADE = ITEMS.register("reinforced_plating_golem_upgrade",
                () -> new SteelGolemUpgradeItem("reinforced_plating", new Item.Properties(), new GolemUpgradeProperties.Builder()
                        .armor(10).toughness(8).isNotPushable()
                        .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 0, 1)).build()));

        public static final RegistryObject<Item> DAMAGE_GOLEM_UPGRADE = ITEMS.register("damage_golem_upgrade",
                () -> new SteelGolemUpgradeItem("reinforced_plating", new Item.Properties(), new GolemUpgradeProperties.Builder()
                        .armor(4).isFastAttack()
                        .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 0, 0)).build()));

        public static final RegistryObject<Item> MOVCADIA_SAPLING = ITEMS.register("movcadia_sapling",
                () -> new BlockItem(ElementusBlocks.MOVCADIA_SAPLING.get(), new Item.Properties().fireResistant()));

        public static final RegistryObject<Item> STEEL_BLOCK = ITEMS.register("steel_block",
                () -> new BlockItem(ElementusBlocks.STEEL_BLOCK.get(), new Item.Properties()));
        public static final RegistryObject<Item> DIARKRITE_BLOCK = ITEMS.register("diarkrite_block",
                () -> new BlockItem(ElementusBlocks.DIARKRITE_BLOCK.get(), new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> ANTHEKTITE_BLOCK = ITEMS.register("anthektite_block",
                () -> new BlockItem(ElementusBlocks.ANTHEKTITE_BLOCK.get(), new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> REMNANT = ITEMS.register("remnant",
                () -> new BlockItem(ElementusBlocks.REMNANT.get(), new Item.Properties().fireResistant()));

        public static final RegistryObject<Item> STEEL_BARS = ITEMS.register("steel_bars",
                () -> new BlockItem(ElementusBlocks.STEEL_BARS.get(), new Item.Properties()));

        public static final RegistryObject<Item> STEEL_TILES = ITEMS.register("steel_tiles",
                () -> new BlockItem(ElementusBlocks.STEEL_TILES.get(), new Item.Properties()));
        public static final RegistryObject<Item> STEEL_TILE_STAIR = ITEMS.register("steel_tile_stair",
                () -> new BlockItem(ElementusBlocks.STEEL_TILE_STAIR.get(), new Item.Properties()));
        public static final RegistryObject<Item> STEEL_TILE_SLAB = ITEMS.register("steel_tile_slab",
                () -> new BlockItem(ElementusBlocks.STEEL_TILE_SLAB.get(), new Item.Properties()));

        public static final RegistryObject<Item> MOVCADIA_ROOTED_DIRT = ITEMS.register("movcadia_rooted_dirt",
                () -> new BlockItem(ElementusBlocks.MOVCADIA_ROOTED_DIRT.get(), new Item.Properties()));
        public static final RegistryObject<Item> MOVCADIA_ROOTED_STONE = ITEMS.register("movcadia_rooted_stone",
                () -> new BlockItem(ElementusBlocks.MOVCADIA_ROOTED_STONE.get(), new Item.Properties()));
        public static final RegistryObject<Item> MOVCADIA_ROOTED_DEEPSLATE = ITEMS.register("movcadia_rooted_deepslate",
                () -> new BlockItem(ElementusBlocks.MOVCADIA_ROOTED_DEEPSLATE.get(), new Item.Properties()));

        public static final RegistryObject<Item> MOVCADIA_LOG = ITEMS.register("movcadia_log",
                () -> new BlockItem(ElementusBlocks.MOVCADIA_LOG.get(), new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> STRIPPED_MOVCADIA_LOG = ITEMS.register("stripped_movcadia_log",
                () -> new BlockItem(ElementusBlocks.STRIPPED_MOVCADIA_LOG.get(), new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> MOVCADIA_WOOD = ITEMS.register("movcadia_wood",
                () -> new BlockItem(ElementusBlocks.MOVCADIA_WOOD.get(), new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> STRIPPED_MOVCADIA_WOOD = ITEMS.register("stripped_movcadia_wood",
                () -> new BlockItem(ElementusBlocks.STRIPPED_MOVCADIA_WOOD.get(), new Item.Properties().fireResistant()));

        public static final RegistryObject<Item> MOVCADIA_LEAVES = ITEMS.register("movcadia_leaves",
                () -> new BlockItem(ElementusBlocks.MOVCADIA_LEAVES.get(), new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> FLOWERING_MOVCADIA_LEAVES = ITEMS.register("flowering_movcadia_leaves",
                () -> new BlockItem(ElementusBlocks.FLOWERING_MOVCADIA_LEAVES.get(), new Item.Properties().fireResistant()));

        public static final RegistryObject<Item> MOVCADIA_PLANKS = ITEMS.register("movcadia_planks",
                () -> new BlockItem(ElementusBlocks.MOVCADIA_PLANKS.get(), new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> MOVCADIA_STAIRS = ITEMS.register("movcadia_stairs",
                () -> new BlockItem(ElementusBlocks.MOVCADIA_STAIRS.get(), new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> MOVCADIA_SLAB = ITEMS.register("movcadia_slab",
                () -> new BlockItem(ElementusBlocks.MOVCADIA_SLAB.get(), new Item.Properties().fireResistant()));

        public static final RegistryObject<Item> MOVCADIA_DOOR = ITEMS.register("movcadia_door",
                () -> new BlockItem(ElementusBlocks.MOVCADIA_DOOR.get(), new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> MOVCADIA_TRAPDOOR = ITEMS.register("movcadia_trapdoor",
                () -> new BlockItem(ElementusBlocks.MOVCADIA_TRAPDOOR.get(), new Item.Properties().fireResistant()));

        public static final RegistryObject<Item> MOVCADIA_PRESSURE_PLATE = ITEMS.register("movcadia_pressure_plate",
                () -> new BlockItem(ElementusBlocks.MOVCADIA_PRESSURE_PLATE.get(), new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> MOVCADIA_BUTTON = ITEMS.register("movcadia_button",
                () -> new BlockItem(ElementusBlocks.MOVCADIA_BUTTON.get(), new Item.Properties().fireResistant()));

        public static final RegistryObject<Item> MOVCADIA_FENCE = ITEMS.register("movcadia_fence",
                () -> new BlockItem(ElementusBlocks.MOVCADIA_FENCE.get(), new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> MOVCADIA_FENCE_GATE = ITEMS.register("movcadia_fence_gate",
                () -> new BlockItem(ElementusBlocks.MOVCADIA_FENCE_GATE.get(), new Item.Properties().fireResistant()));

        public static final RegistryObject<Item> MOVCADIA_SIGN = ITEMS.register("movcadia_sign",
                () -> new SignItem(new Item.Properties().fireResistant().stacksTo(16), ElementusBlocks.MOVCADIA_SIGN.get(), ElementusBlocks.MOVCADIA_WALL_SIGN.get()));
        public static final RegistryObject<Item> MOVCADIA_HANGING_SIGN = ITEMS.register("movcadia_hanging_sign",
                () -> new HangingSignItem(ElementusBlocks.MOVCADIA_HANGING_SIGN.get(), ElementusBlocks.MOVCADIA_WALL_HANGING_SIGN.get(), new Item.Properties().fireResistant().stacksTo(16)));
        public static final RegistryObject<Item> STURDY_MOVCADIA_SIGN = ITEMS.register("sturdy_movcadia_sign",
                () -> new SignItem(new Item.Properties().fireResistant().stacksTo(16), ElementusBlocks.STURDY_MOVCADIA_SIGN.get(), ElementusBlocks.STURDY_MOVCADIA_WALL_SIGN.get()));

        public static final RegistryObject<Item> MOVCADIA_CHEST = ITEMS.register("movcadia_chest",
                () -> new BlockItem(ElementusBlocks.MOVCADIA_CHEST.get(), new Item.Properties().fireResistant()));

        public static final RegistryObject<Item> MOVCADIA_BOAT = ITEMS.register("movcadia_boat",
                () -> new ModBoatItem(false, ModBoatEntity.Type.MOVCADIA, new Item.Properties().fireResistant().stacksTo(1)));
        public static final RegistryObject<Item> MOVCADIA_CHEST_BOAT = ITEMS.register("movcadia_chest_boat",
                () -> new ModBoatItem(true, ModBoatEntity.Type.MOVCADIA, new Item.Properties().fireResistant().stacksTo(1)));

        public static void register(IEventBus eventBus) {
            ITEMS.register(eventBus);
        }
    }

    public static class WitherstormModItems {
        public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

        public static final RegistryObject<Item> STEEL_CMD_SWORD = ITEMS.register("steel_command_block_sword", () -> new ModSwordItem(ModTiers.STEEL_CMD,
                WSConfig.steelCMDSwordDamage, (float) WSConfig.steelCMDSwordAttackSpeed + steelSpeed, new Item.Properties()));
        public static final RegistryObject<Item> STEEL_CMD_SHOVEL = ITEMS.register("steel_command_block_shovel", () -> new ModShovelItem(ModTiers.STEEL_CMD,
                (float) WSConfig.steelCMDShovelDamage, (float) WSConfig.steelCMDShovelAttackSpeed, new Item.Properties()));
        public static final RegistryObject<Item> STEEL_CMD_PICKAXE = ITEMS.register("steel_command_block_pickaxe", () -> new ModPickaxeItem(ModTiers.STEEL_CMD,
                WSConfig.steelCMDPickaxeDamage, (float) WSConfig.steelCMDPickaxeAttackSpeed, new Item.Properties()));
        public static final RegistryObject<Item> STEEL_CMD_AXE = ITEMS.register("steel_command_block_axe", () -> new ModAxeItem(ModTiers.STEEL_CMD,
                WSConfig.steelCMDAxeDamage, (float) WSConfig.steelCMDAxeAttackSpeed + steelSpeed, new Item.Properties()));
        public static final RegistryObject<Item> STEEL_CMD_HOE = ITEMS.register("steel_command_block_hoe", () -> new ModHoeItem(ModTiers.STEEL_CMD,
                WSConfig.steelCMDHoeDamage, (float) WSConfig.steelCMDHoeAttackSpeed, new Item.Properties()));


        public static final RegistryObject<Item> DIARKRITE_CMD_SWORD = ITEMS.register("diarkrite_command_block_sword", () -> new ModSwordItem(ModTiers.DIARKRITE_CMD,
                WSConfig.diarkriteCMDSwordDamage, (float) WSConfig.diarkriteCMDSwordAttackSpeed + diarkriteSpeed, new Item.Properties()));
        public static final RegistryObject<Item> DIARKRITE_CMD_SHOVEL = ITEMS.register("diarkrite_command_block_shovel", () -> new ModShovelItem(ModTiers.DIARKRITE_CMD,
                (float) WSConfig.diarkriteCMDShovelDamage, (float) WSConfig.diarkriteCMDShovelAttackSpeed, new Item.Properties()));
        public static final RegistryObject<Item> DIARKRITE_CMD_PICKAXE = ITEMS.register("diarkrite_command_block_pickaxe", () -> new ModPickaxeItem(ModTiers.DIARKRITE_CMD,
                WSConfig.diarkriteCMDPickaxeDamage, (float) WSConfig.diarkriteCMDPickaxeAttackSpeed, new Item.Properties()));
        public static final RegistryObject<Item> DIARKRITE_CMD_AXE = ITEMS.register("diarkrite_command_block_axe", () -> new ModAxeItem(ModTiers.DIARKRITE_CMD,
                WSConfig.diarkriteCMDAxeDamage, (float) WSConfig.diarkriteCMDAxeAttackSpeed + diarkriteSpeed, new Item.Properties()));
        public static final RegistryObject<Item> DIARKRITE_CMD_HOE = ITEMS.register("diarkrite_command_block_hoe", () -> new ModHoeItem(ModTiers.DIARKRITE_CMD,
                WSConfig.diarkriteCMDHoeDamage, (float) WSConfig.diarkriteCMDHoeAttackSpeed, new Item.Properties()));


        public static final RegistryObject<Item> ANTHEKTITE_CMD_SWORD = ITEMS.register("anthektite_command_block_sword", () -> new ModSwordItem(ModTiers.ANTHEKTITE_CMD,
                WSConfig.anthektiteCMDSwordDamage, (float) WSConfig.anthektiteCMDSwordAttackSpeed + anthektiteSpeed, new Item.Properties()));
        public static final RegistryObject<Item> ANTHEKTITE_CMD_SHOVEL = ITEMS.register("anthektite_command_block_shovel", () -> new ModShovelItem(ModTiers.ANTHEKTITE_CMD,
                (float) WSConfig.anthektiteCMDShovelDamage, (float) WSConfig.anthektiteCMDShovelAttackSpeed, new Item.Properties()));
        public static final RegistryObject<Item> ANTHEKTITE_CMD_PICKAXE = ITEMS.register("anthektite_command_block_pickaxe", () -> new ModPickaxeItem(ModTiers.ANTHEKTITE_CMD,
                WSConfig.anthektiteCMDPickaxeDamage, (float) WSConfig.anthektiteCMDPickaxeAttackSpeed, new Item.Properties()));
        public static final RegistryObject<Item> ANTHEKTITE_CMD_AXE = ITEMS.register("anthektite_command_block_axe", () -> new ModAxeItem(ModTiers.ANTHEKTITE_CMD,
                WSConfig.anthektiteCMDAxeDamage, (float) WSConfig.anthektiteCMDAxeAttackSpeed + anthektiteSpeed, new Item.Properties()));
        public static final RegistryObject<Item> ANTHEKTITE_CMD_HOE = ITEMS.register("anthektite_command_block_hoe", () -> new ModHoeItem(ModTiers.ANTHEKTITE_CMD,
                WSConfig.anthektiteCMDHoeDamage, (float) WSConfig.anthektiteCMDHoeAttackSpeed, new Item.Properties()));

        public static void register(IEventBus eventBus) {
            ITEMS.register(eventBus);
        }
    }
    public static class BanillaClawsItems {
        public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

        public static final RegistryObject<Item> STEEL_CLAWS = ITEMS.register("steel_claws", () -> new ClawsItem(ModTiers.STEEL_CLAW,
                ItemConfig.steelClawDamage, (float) ItemConfig.steelClawAttackSpeed + steelSpeed, new Item.Properties()));
        public static final RegistryObject<Item> DIARKRITE_CLAWS = ITEMS.register("diarkrite_claws", () -> new ClawsItem(ModTiers.DIARKRITE_CLAW,
                ItemConfig.diarkriteClawDamage, (float) ItemConfig.diarkriteClawAttackSpeed + diarkriteSpeed, new Item.Properties()));
        public static final RegistryObject<Item> ANTHEKTITE_CLAWS = ITEMS.register("anthektite_claws", () -> new ClawsItem(ModTiers.ANTHEKTITE_CLAW,
                ItemConfig.anthektiteClawDamage, (float) ItemConfig.anthektiteClawAttackSpeed + anthektiteSpeed, new Item.Properties()));

        public static void register(IEventBus eventBus) {
            ITEMS.register(eventBus);
        }
    }

    public static void register(IEventBus eventBus) {
        ElementusItems.ITEMS.register(eventBus);
        if (farmersDelight) net.nokunami.elementus.common.compat.farmersdelight.FarmersDelightItems.ITEMS.register(eventBus);
        if (piercingPaxels) PPModItems.ITEMS.register(eventBus);
        if (nethersDelight) net.nokunami.elementus.common.compat.farmersdelight.NethersDelightItems.ITEMS.register(eventBus);
        if (ironsSpellbooks) ISSModItems.ITEMS.register(eventBus);
        if (aether) TAModItems.ITEMS.register(eventBus);
        if (simplySwords) SSModItems.ITEMS.register(eventBus);
        if (sniffsWeapons) SWModItems.ITEMS.register(eventBus);
        if (advancedNetherite) ANModItems.ITEMS.register(eventBus);
        if (samuraiDynasty) ESModItems.ITEMS.register(eventBus);
        if (twigs) TWModItems.ITEMS.register(eventBus);
        if (witherStormMod) {
            WitherstormModItems.ITEMS.register(eventBus);
        }
        if (vanillaClaws) {
            BanillaClawsItems.ITEMS.register(eventBus);
        }
    }
}
