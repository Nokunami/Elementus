package nokunami.elementus.common.registry;

import com.google.common.collect.ImmutableMultimap;
//import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
//import io.redspace.ironsspellbooks.api.spells.SpellRarity;
//import io.redspace.ironsspellbooks.item.spell_books.SimpleAttributeSpellBook;
//import net.dakotapride.vanilla_claws.item.ClawsItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
//import net.minecraftforge.common.ForgeSpawnEggItem;
//import net.minecraftforge.eventbus.api.IEventBus;
//import net.minecraftforge.registries.DeferredRegister;
//import net.minecraftforge.registries.ForgeRegistries;
//import net.minecraftforge.registries.RegistryObject;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import nokunami.elementus.common.compat.advancednetherite.item.*;
import nokunami.elementus.common.compat.epicsamurai.ESArmorItem;
import nokunami.elementus.common.compat.ironsspellbooks.ISSArmorItem;
import nokunami.elementus.common.compat.ironsspellbooks.MagicArmorMaterial;
import nokunami.elementus.common.compat.piercingpaxels.EPaxelItem;
import nokunami.elementus.common.compat.simplyswords.SimplySwordItem;
import nokunami.elementus.common.compat.sniffsweapons.ModGreatPickaxeItem;
import nokunami.elementus.common.compat.sniffsweapons.SniffsWeaponsArmorItem;
import nokunami.elementus.common.compat.theaether.AttributeGlovesItem;
import nokunami.elementus.common.config.*;
import nokunami.elementus.common.entity.vehicle.ModBoatEntity;
import nokunami.elementus.common.item.*;
//import nokunami.elementus.common.registry.ModBlocks.AdvancedNetheriteBlocks;
import nokunami.elementus.common.registry.ModBlocks.ElementusBlocks;
//import nokunami.elementus.common.registry.ModBlocks.FarmersDelightBlocks;
//import nokunami.elementus.common.registry.ModBlocks.TwigsBlocks;
//import nl.sniffiandros.sniffsweapons.item.GreatAxeItem;
//import nl.sniffiandros.sniffsweapons.item.GreatSwordItem;
//import nl.sniffiandros.sniffsweapons.item.NaginataItem;
//import umpaz.nethersdelight.common.item.MacheteItem;
//import vectorwing.farmersdelight.common.item.KnifeItem;

import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.UUID;

import static nokunami.elementus.Elementus.MODID;
import static nokunami.elementus.ModChecker.*;

public class ModItems {
    public static float steelSpeed = (float) TierConfig.steelWeaponSpeedModifier;
    public static float diarkriteSpeed = (float) TierConfig.diarkriteWeaponSpeedModifier;
    public static float anthektiteSpeed = (float) TierConfig.anthektiteWeaponSpeedModifier;

    public static class ElementusItems {
        public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

        public static final DeferredItem<Item> CRUDE_STEEL = ITEMS.registerSimpleItem("crude_steel");
        public static final DeferredItem<Item> STEEL_SCRAP = ITEMS.register("steel_scrap",
                () -> new Item(new Item.Properties()));
        public static final DeferredItem<Item> STEEL_INGOT = ITEMS.register("steel_ingot",
                () -> new Item(new Item.Properties()));
        public static final DeferredItem<Item> STEEL_NUGGET = ITEMS.register("steel_nugget",
                () -> new Item(new Item.Properties()));

        public static final DeferredItem<Item> ATELIS_SCRAP = ITEMS.register("atelis_scrap",
                () -> new Item(new Item.Properties().fireResistant()));
        public static final DeferredItem<Item> ANTHEKTITE_INGOT = ITEMS.register("anthektite_ingot",
                () -> new Item(new Item.Properties().fireResistant()));
        public static final DeferredItem<Item> DIARKRITE_INGOT = ITEMS.register("diarkrite_ingot",
                () -> new Item(new Item.Properties().fireResistant()));
        public static final DeferredItem<Item> ATELIS_UPGRADE_SMITHING_TEMPLATE = ITEMS.register("atelis_upgrade_smithing_template",
                ModSmithingTemplateItem::createAtelisUpgradeTemplate);
        public static final DeferredItem<Item> WEAPON_FRAGMENT = ITEMS.register("weapon_fragment",
                ModSmithingTemplateItem::createWeaponFragment);

        public static final DeferredItem<Item> MOVCADIA_BERRIES = ITEMS.register("movcadia_berries",
                () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                        .nutrition(4).saturationModifier(0.3F)
                        .effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 300), 1)
                        .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 300), 1)
                        .fast().build())));

        public static final DeferredItem<Item> GLISTERING_MOVCADIA_BERRIES = ITEMS.register("glistering_movcadia_berries",
                () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                        .alwaysEdible().nutrition(4).saturationModifier(0.8F)
                        .effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 500, 1), 1)
                        .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 500, 1), 1)
                        .fast().build())));

        public static final DeferredItem<Item> MOVCADIA_ESSENCE = ITEMS.register("movcadia_essence",
                () -> new Item(new Item.Properties().fireResistant()));

        public static final DeferredItem<Item> CRUSHED_REMNANT = ITEMS.register("crushed_remnant",
                () -> new Item(new Item.Properties().fireResistant()));

        public static final DeferredItem<Item> STEEL_GOLEM_SPAWN_EGG = ITEMS.register("steel_golem_spawn_egg",
                () -> new DeferredSpawnEggItem(ModEntityType.STEEL_GOLEM, 14144729, 7238279, new Item.Properties()));

        public static final DeferredItem<Item> STEEL_SWORD = ITEMS.register("steel_sword", () -> new ModSwordItem(ModTiers.STEEL,
                new Item.Properties().attributes(ModSwordItem.createAttributes(ModTiers.STEEL, ItemConfig.steelSwordDamage, (float) ItemConfig.steelSwordAttackSpeed + steelSpeed))));

        public static final DeferredItem<Item> STEEL_SHOVEL = ITEMS.register("steel_shovel", () -> new ModShovelItem(ModTiers.STEEL,
                new Item.Properties().attributes(ModShovelItem.createAttributes(ModTiers.STEEL, (float) ItemConfig.steelShovelDamage, (float) ItemConfig.steelShovelAttackSpeed))));

        public static final DeferredItem<Item> STEEL_PICKAXE = ITEMS.register("steel_pickaxe", () -> new ModPickaxeItem(ModTiers.STEEL,
                new Item.Properties().attributes(ModPickaxeItem.createAttributes(ModTiers.STEEL, (float) ItemConfig.steelPickaxeDamage, (float) ItemConfig.steelPickaxeAttackSpeed))));

        public static final DeferredItem<Item> STEEL_AXE = ITEMS.register("steel_axe", () -> new ModAxeItem(ModTiers.STEEL,
                new Item.Properties().attributes(ModAxeItem.createAttributes(ModTiers.STEEL, (float) ItemConfig.steelAxeDamage, (float) ItemConfig.steelAxeAttackSpeed + steelSpeed))));

        public static final DeferredItem<Item> STEEL_HOE = ITEMS.register("steel_hoe", () -> new ModAxeItem(ModTiers.STEEL,
                new Item.Properties().attributes(ModHoeItem.createAttributes(ModTiers.STEEL, (float) ItemConfig.steelHoeDamage, (float) ItemConfig.steelHoeAttackSpeed))));


        public static final DeferredItem<Item> DIARKRITE_SWORD = ITEMS.register("diarkrite_sword", () -> new ModSwordItem(ModTiers.DIARKRITE,
                new Item.Properties().attributes(ModSwordItem.createAttributes(ModTiers.DIARKRITE, (float) ItemConfig.diarkriteSwordDamage, (float) ItemConfig.diarkriteSwordAttackSpeed + diarkriteSpeed)).fireResistant()));

        public static final DeferredItem<Item> DIARKRITE_SHOVEL = ITEMS.register("diarkrite_shovel", () -> new ModShovelItem(ModTiers.DIARKRITE,
                new Item.Properties().attributes(ModShovelItem.createAttributes(ModTiers.DIARKRITE, (float) ItemConfig.diarkriteShovelDamage, (float) ItemConfig.diarkriteShovelAttackSpeed)).fireResistant()));

        public static final DeferredItem<Item> DIARKRITE_PICKAXE = ITEMS.register("diarkrite_pickaxe", () -> new ModPickaxeItem(ModTiers.DIARKRITE,
                new Item.Properties().attributes(ModPickaxeItem.createAttributes(ModTiers.DIARKRITE, (float) ItemConfig.diarkritePickaxeDamage, (float) ItemConfig.diarkritePickaxeAttackSpeed)).fireResistant()));

        public static final DeferredItem<Item> DIARKRITE_AXE = ITEMS.register("diarkrite_axe", () -> new ModAxeItem(ModTiers.DIARKRITE,
                new Item.Properties().attributes(ModAxeItem.createAttributes(ModTiers.DIARKRITE, (float) ItemConfig.diarkriteAxeDamage, (float) ItemConfig.diarkriteAxeAttackSpeed + diarkriteSpeed)).fireResistant()));

        public static final DeferredItem<Item> DIARKRITE_HOE = ITEMS.register("diarkrite_hoe", () -> new ModHoeItem(ModTiers.DIARKRITE,
                new Item.Properties().attributes(ModHoeItem.createAttributes(ModTiers.DIARKRITE, (float) ItemConfig.diarkriteHoeDamage, (float) ItemConfig.diarkriteHoeAttackSpeed)).fireResistant()));


        public static final DeferredItem<Item> ANTHEKTITE_SWORD = ITEMS.register("anthektite_sword", () -> new ModSwordItem(ModTiers.ANTHEKTITE,
                new Item.Properties().attributes(ModSwordItem.createAttributes(ModTiers.ANTHEKTITE, (float) ItemConfig.anthektiteSwordDamage, (float) ItemConfig.anthektiteSwordAttackSpeed + anthektiteSpeed)).fireResistant()));

        public static final DeferredItem<Item> ANTHEKTITE_SHOVEL = ITEMS.register("anthektite_shovel", () -> new ShovelItem(ModTiers.ANTHEKTITE,
                new Item.Properties().attributes(ModShovelItem.createAttributes(ModTiers.ANTHEKTITE, (float) ItemConfig.anthektiteShovelDamage, (float) ItemConfig.anthektiteShovelAttackSpeed)).fireResistant()));

        public static final DeferredItem<Item> ANTHEKTITE_PICKAXE = ITEMS.register("anthektite_pickaxe", () -> new ModPickaxeItem(ModTiers.ANTHEKTITE,
                new Item.Properties().attributes(ModPickaxeItem.createAttributes(ModTiers.ANTHEKTITE, (float) ItemConfig.anthektitePickaxeDamage, (float) ItemConfig.anthektitePickaxeAttackSpeed)).fireResistant()));

        public static final DeferredItem<Item> ANTHEKTITE_AXE = ITEMS.register("anthektite_axe", () -> new AxeItem(ModTiers.ANTHEKTITE,
                new Item.Properties().attributes(ModAxeItem.createAttributes(ModTiers.ANTHEKTITE, (float) ItemConfig.anthektiteAxeDamage, (float) ItemConfig.anthektiteAxeAttackSpeed + anthektiteSpeed)).fireResistant()));

        public static final DeferredItem<Item> ANTHEKTITE_HOE = ITEMS.register("anthektite_hoe", () -> new HoeItem(ModTiers.ANTHEKTITE,
                new Item.Properties().attributes(ModHoeItem.createAttributes(ModTiers.ANTHEKTITE, (float) ItemConfig.anthektiteHoeDamage, (float) ItemConfig.anthektiteHoeAttackSpeed)).fireResistant()));

        public static final DeferredItem<Item> DIARKRITE_CHARGE_BLADE = ITEMS.register("diarkrite_charge_blade", DiarkriteChargeBlade::new);
        public static final DeferredItem<Item> ANTHEKTITE_CHARGE_BLADE = ITEMS.register("anthektite_charge_blade", AnthektiteChargeBlade::new);

        public static final DeferredItem<Item> STEEL_SHIELD = ITEMS.register("steel_shield",
                () -> new ElementusShieldItem(new Item.Properties().durability(ItemConfig.steelShieldDurability), ModTiers.STEEL));
        public static final DeferredItem<Item> DIARKRITE_SHIELD = ITEMS.register("diarkrite_shield",
                () -> new ElementusShieldItem(new Item.Properties().durability(ItemConfig.diarkriteShieldDurability).fireResistant(), ModTiers.DIARKRITE));
        public static final DeferredItem<Item> ANTHEKTITE_SHIELD = ITEMS.register("anthektite_shield",
                () -> new ElementusShieldItem(new Item.Properties().durability(ItemConfig.anthektiteShieldDurability).fireResistant(), ModTiers.ANTHEKTITE));

        public static final DeferredItem<Item> STEEL_BOW = ITEMS.register("steel_bow",
                () -> new ModBowItem(new Item.Properties().durability(ItemConfig.steelBowDurability), STEEL_INGOT.get()));
        public static final DeferredItem<Item> DIARKRITE_BOW = ITEMS.register("diarkrite_bow",
                () -> new ModBowItem(new Item.Properties().durability(ItemConfig.diarkriteBowDurability).fireResistant(), DIARKRITE_INGOT.get()));
        public static final DeferredItem<Item> ANTHEKTITE_BOW = ITEMS.register("anthektite_bow",
                () -> new ModBowItem(new Item.Properties().durability(ItemConfig.anthektiteBowDurability).fireResistant(), ANTHEKTITE_INGOT.get()));

        public static final DeferredItem<Item> STEEL_HELMET = ITEMS.register("steel_helmet",
                () -> new ElementusArmorItem(ModArmorMaterials.STEEL, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(ArmorConfig.steelArmor_DurabilityForType))));
        public static final DeferredItem<Item> STEEL_CHESTPLATE = ITEMS.register("steel_chestplate",
                () -> new ElementusArmorItem(ModArmorMaterials.STEEL, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(ArmorConfig.steelArmor_DurabilityForType))));
        public static final DeferredItem<Item> STEEL_LEGGINGS = ITEMS.register("steel_leggings",
                () -> new ElementusArmorItem(ModArmorMaterials.STEEL, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(ArmorConfig.steelArmor_DurabilityForType))));
        public static final DeferredItem<Item> STEEL_BOOTS = ITEMS.register("steel_boots",
                () -> new ElementusArmorItem(ModArmorMaterials.STEEL, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(ArmorConfig.steelArmor_DurabilityForType))));

        public static final DeferredItem<Item> DIARKRITE_HELMET = ITEMS.register("diarkrite_helmet",
                () -> new ElementusArmorItem(ModArmorMaterials.DIARKRITE, ArmorItem.Type.HELMET, new Item.Properties().fireResistant()));
        public static final DeferredItem<Item> DIARKRITE_CHESTPLATE = ITEMS.register("diarkrite_chestplate",
                () -> new ElementusArmorItem(ModArmorMaterials.DIARKRITE, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant()));
        public static final DeferredItem<Item> DIARKRITE_LEGGINGS = ITEMS.register("diarkrite_leggings",
                () -> new ElementusArmorItem(ModArmorMaterials.DIARKRITE, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant()));
        public static final DeferredItem<Item> DIARKRITE_BOOTS = ITEMS.register("diarkrite_boots",
                () -> new ElementusArmorItem(ModArmorMaterials.DIARKRITE, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant()));

        public static final DeferredItem<Item> ANTHEKTITE_HELMET = ITEMS.register("anthektite_helmet",
                () -> new ElementusArmorItem(ModArmorMaterials.ANTHEKTITE, ArmorItem.Type.HELMET, new Item.Properties().fireResistant()));
        public static final DeferredItem<Item> ANTHEKTITE_CHESTPLATE = ITEMS.register("anthektite_chestplate",
                () -> new ElementusArmorItem(ModArmorMaterials.ANTHEKTITE, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant()));
        public static final DeferredItem<Item> ANTHEKTITE_LEGGINGS = ITEMS.register("anthektite_leggings",
                () -> new ElementusArmorItem(ModArmorMaterials.ANTHEKTITE, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant()));
        public static final DeferredItem<Item> ANTHEKTITE_BOOTS = ITEMS.register("anthektite_boots",
                () -> new ElementusArmorItem(ModArmorMaterials.ANTHEKTITE, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant()));

        public static final DeferredItem<Item> CATALYST_CHESTPLATE = ITEMS.register("catalyst_chestplate",
                () -> new CatalystArmorItem(ModArmorMaterials.CATALYST, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant().rarity(Rarity.EPIC)));

//        public static final RegistryObject<Item> DIARKRITE_BOOTS_SCULK = ITEMS.register("diarkrite_boots_sculk",
//                () -> new DiarkriteBootsItem(ModArmorMaterials.DIARKRITE, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant().rarity(Rarity.EPIC)));

        public static final DeferredItem<Item> REINFORCED_PLATING_GOLEM_UPGRADE = ITEMS.register("reinforced_plating_golem_upgrade",
                () -> new SteelGolemUpgradeItem("reinforced_plating", new Item.Properties(), new GolemUpgradeProperties.Builder()
                        .armor(10).toughness(8).isNotPushable()
                        .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 0, 1)).build()));

        public static final DeferredItem<Item> DAMAGE_GOLEM_UPGRADE = ITEMS.register("damage_golem_upgrade",
                () -> new SteelGolemUpgradeItem("reinforced_plating", new Item.Properties(), new GolemUpgradeProperties.Builder()
                        .armor(4).isFastAttack()
                        .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 0, 0)).build()));

        public static final DeferredItem<Item> MOVCADIA_SAPLING = ITEMS.register("movcadia_sapling",
                () -> new BlockItem(ElementusBlocks.MOVCADIA_SAPLING.get(), new Item.Properties().fireResistant()));

        public static final DeferredItem<Item> STEEL_BLOCK = ITEMS.register("steel_block",
                () -> new BlockItem(ElementusBlocks.STEEL_BLOCK.get(), new Item.Properties()));
        public static final DeferredItem<Item> DIARKRITE_BLOCK = ITEMS.register("diarkrite_block",
                () -> new BlockItem(ElementusBlocks.DIARKRITE_BLOCK.get(), new Item.Properties().fireResistant()));
        public static final DeferredItem<Item> ANTHEKTITE_BLOCK = ITEMS.register("anthektite_block",
                () -> new BlockItem(ElementusBlocks.ANTHEKTITE_BLOCK.get(), new Item.Properties().fireResistant()));
        public static final DeferredItem<Item> REMNANT = ITEMS.register("remnant",
                () -> new BlockItem(ElementusBlocks.REMNANT.get(), new Item.Properties().fireResistant()));

        public static final DeferredItem<Item> STEEL_BARS = ITEMS.register("steel_bars",
                () -> new BlockItem(ElementusBlocks.STEEL_BARS.get(), new Item.Properties()));

        public static final DeferredItem<Item> STEEL_TILES = ITEMS.register("steel_tiles",
                () -> new BlockItem(ElementusBlocks.STEEL_TILES.get(), new Item.Properties()));
        public static final DeferredItem<Item> STEEL_TILE_STAIR = ITEMS.register("steel_tile_stair",
                () -> new BlockItem(ElementusBlocks.STEEL_TILE_STAIR.get(), new Item.Properties()));
        public static final DeferredItem<Item> STEEL_TILE_SLAB = ITEMS.register("steel_tile_slab",
                () -> new BlockItem(ElementusBlocks.STEEL_TILE_SLAB.get(), new Item.Properties()));

        public static final DeferredItem<Item> MOVCADIA_ROOTED_DIRT = ITEMS.register("movcadia_rooted_dirt",
                () -> new BlockItem(ElementusBlocks.MOVCADIA_ROOTED_DIRT.get(), new Item.Properties()));
        public static final DeferredItem<Item> MOVCADIA_ROOTED_STONE = ITEMS.register("movcadia_rooted_stone",
                () -> new BlockItem(ElementusBlocks.MOVCADIA_ROOTED_STONE.get(), new Item.Properties()));
        public static final DeferredItem<Item> MOVCADIA_ROOTED_DEEPSLATE = ITEMS.register("movcadia_rooted_deepslate",
                () -> new BlockItem(ElementusBlocks.MOVCADIA_ROOTED_DEEPSLATE.get(), new Item.Properties()));

        public static final DeferredItem<Item> MOVCADIA_LOG = ITEMS.register("movcadia_log",
                () -> new BlockItem(ElementusBlocks.MOVCADIA_LOG.get(), new Item.Properties().fireResistant()));
        public static final DeferredItem<Item> STRIPPED_MOVCADIA_LOG = ITEMS.register("stripped_movcadia_log",
                () -> new BlockItem(ElementusBlocks.STRIPPED_MOVCADIA_LOG.get(), new Item.Properties().fireResistant()));
        public static final DeferredItem<Item> MOVCADIA_WOOD = ITEMS.register("movcadia_wood",
                () -> new BlockItem(ElementusBlocks.MOVCADIA_WOOD.get(), new Item.Properties().fireResistant()));
        public static final DeferredItem<Item> STRIPPED_MOVCADIA_WOOD = ITEMS.register("stripped_movcadia_wood",
                () -> new BlockItem(ElementusBlocks.STRIPPED_MOVCADIA_WOOD.get(), new Item.Properties().fireResistant()));

        public static final DeferredItem<Item> MOVCADIA_LEAVES = ITEMS.register("movcadia_leaves",
                () -> new BlockItem(ElementusBlocks.MOVCADIA_LEAVES.get(), new Item.Properties().fireResistant()));
        public static final DeferredItem<Item> FLOWERING_MOVCADIA_LEAVES = ITEMS.register("flowering_movcadia_leaves",
                () -> new BlockItem(ElementusBlocks.FLOWERING_MOVCADIA_LEAVES.get(), new Item.Properties().fireResistant()));

        public static final DeferredItem<Item> MOVCADIA_PLANKS = ITEMS.register("movcadia_planks",
                () -> new BlockItem(ElementusBlocks.MOVCADIA_PLANKS.get(), new Item.Properties().fireResistant()));
        public static final DeferredItem<Item> MOVCADIA_STAIRS = ITEMS.register("movcadia_stairs",
                () -> new BlockItem(ElementusBlocks.MOVCADIA_STAIRS.get(), new Item.Properties().fireResistant()));
        public static final DeferredItem<Item> MOVCADIA_SLAB = ITEMS.register("movcadia_slab",
                () -> new BlockItem(ElementusBlocks.MOVCADIA_SLAB.get(), new Item.Properties().fireResistant()));

        public static final DeferredItem<Item> MOVCADIA_DOOR = ITEMS.register("movcadia_door",
                () -> new BlockItem(ElementusBlocks.MOVCADIA_DOOR.get(), new Item.Properties().fireResistant()));
        public static final DeferredItem<Item> MOVCADIA_TRAPDOOR = ITEMS.register("movcadia_trapdoor",
                () -> new BlockItem(ElementusBlocks.MOVCADIA_TRAPDOOR.get(), new Item.Properties().fireResistant()));

        public static final DeferredItem<Item> MOVCADIA_PRESSURE_PLATE = ITEMS.register("movcadia_pressure_plate",
                () -> new BlockItem(ElementusBlocks.MOVCADIA_PRESSURE_PLATE.get(), new Item.Properties().fireResistant()));
        public static final DeferredItem<Item> MOVCADIA_BUTTON = ITEMS.register("movcadia_button",
                () -> new BlockItem(ElementusBlocks.MOVCADIA_BUTTON.get(), new Item.Properties().fireResistant()));

        public static final DeferredItem<Item> MOVCADIA_FENCE = ITEMS.register("movcadia_fence",
                () -> new BlockItem(ElementusBlocks.MOVCADIA_FENCE.get(), new Item.Properties().fireResistant()));
        public static final DeferredItem<Item> MOVCADIA_FENCE_GATE = ITEMS.register("movcadia_fence_gate",
                () -> new BlockItem(ElementusBlocks.MOVCADIA_FENCE_GATE.get(), new Item.Properties().fireResistant()));

        public static final DeferredItem<Item> MOVCADIA_SIGN = ITEMS.register("movcadia_sign",
                () -> new SignItem(new Item.Properties().fireResistant().stacksTo(16), ElementusBlocks.MOVCADIA_SIGN.get(), ElementusBlocks.MOVCADIA_WALL_SIGN.get()));
        public static final DeferredItem<Item> MOVCADIA_HANGING_SIGN = ITEMS.register("movcadia_hanging_sign",
                () -> new HangingSignItem(ElementusBlocks.MOVCADIA_HANGING_SIGN.get(), ElementusBlocks.MOVCADIA_WALL_HANGING_SIGN.get(), new Item.Properties().fireResistant().stacksTo(16)));
        public static final DeferredItem<Item> STURDY_MOVCADIA_SIGN = ITEMS.register("sturdy_movcadia_sign",
                () -> new SignItem(new Item.Properties().fireResistant().stacksTo(16), ElementusBlocks.STURDY_MOVCADIA_SIGN.get(), ElementusBlocks.STURDY_MOVCADIA_WALL_SIGN.get()));

        public static final DeferredItem<Item> MOVCADIA_CHEST = ITEMS.register("movcadia_chest",
                () -> new BlockItem(ElementusBlocks.MOVCADIA_CHEST.get(), new Item.Properties().fireResistant()));

        public static final DeferredItem<Item> MOVCADIA_BOAT = ITEMS.register("movcadia_boat",
                () -> new ModBoatItem(false, ModBoatEntity.Type.MOVCADIA, new Item.Properties().fireResistant().stacksTo(1)));
        public static final DeferredItem<Item> MOVCADIA_CHEST_BOAT = ITEMS.register("movcadia_chest_boat",
                () -> new ModBoatItem(true, ModBoatEntity.Type.MOVCADIA, new Item.Properties().fireResistant().stacksTo(1)));

        public static void register(IEventBus eventBus) {
            ITEMS.register(eventBus);
        }
    }

//    public static class FarmersDelightItems {
//        public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
//
//        public static final RegistryObject<Item> STEEL_KNIFE = ITEMS.register("steel_knife", () -> new KnifeItem(ModTiers.STEEL,
//                (float) ItemConfig.steelKnifeDamage, (float) ItemConfig.steelKnifeAttackSpeed + steelSpeed, new Item.Properties()));
//        public static final RegistryObject<Item> DIARKRITE_KNIFE = ITEMS.register("diarkrite_knife", () -> new KnifeItem(ModTiers.DIARKRITE,
//                (float) ItemConfig.diarkriteKnifeDamage, (float) ItemConfig.diarkriteKnifeAttackSpeed + diarkriteSpeed, new Item.Properties().fireResistant()));
//        public static final RegistryObject<Item> ANTHEKTITE_KNIFE = ITEMS.register("anthektite_knife", () -> new KnifeItem(ModTiers.ANTHEKTITE,
//                (float) ItemConfig.anthektiteKnifeDamage, (float) ItemConfig.anthektiteKnifeAttackSpeed - anthektiteSpeed, new Item.Properties().fireResistant()));
//
//        public static final RegistryObject<Item> MOVCADIA_CABINET = ITEMS.register("movcadia_cabinet",
//                () -> new BlockItem(FarmersDelightBlocks.MOVCADIA_CABINET.get(), new Item.Properties().fireResistant()));
//
//        public static void register(IEventBus eventBus) {ITEMS.register(eventBus);}
//    }
//    public static class PiercingPaxelsItems {
//        public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
//
//        public static final RegistryObject<Item> STEEL_PAXEL = ITEMS.register("steel_paxel", () -> new EPaxelItem(ModTiers.STEEL,
//                (float) ItemConfig.steelPaxelDamage, (float) ItemConfig.steelPaxelAttackSpeed + steelSpeed, new Item.Properties().rarity(Rarity.UNCOMMON)));
//        public static final RegistryObject<Item> DIARKRITE_PAXEL = ITEMS.register("diarkrite_paxel", () -> new EPaxelItem(ModTiers.DIARKRITE,
//                (float) ItemConfig.diarkritePaxelDamage, (float) ItemConfig.diarkritePaxelAttackSpeed + diarkriteSpeed, new Item.Properties().rarity(Rarity.RARE).fireResistant()));
//        public static final RegistryObject<Item> ANTHEKTITE_PAXEL = ITEMS.register("anthektite_paxel", () -> new EPaxelItem(ModTiers.ANTHEKTITE,
//                (float) ItemConfig.anthektiteKnifeDamage, (float) ItemConfig.anthektitePaxelAttackSpeed + anthektiteSpeed, new Item.Properties().rarity(Rarity.RARE).fireResistant()));
//
//        public static final RegistryObject<Item> ANTHEKTITE_UPGRADE_KIT = ITEMS.register("anthektite_paxel_upgrade_kit",
//                () -> new Item(new Item.Properties().rarity(Rarity.RARE).fireResistant()));
//        public static final RegistryObject<Item> DIARKRITE_UPGRADE_KIT = ITEMS.register("diarkrite_paxel_upgrade_kit",
//                () -> new Item(new Item.Properties().rarity(Rarity.RARE).fireResistant()));
//
//        public static void register(IEventBus eventBus) {ITEMS.register(eventBus);}
//    }
//    public static class NethersDelightItems {
//        public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
//
//        public static final RegistryObject<Item> STEEL_MACHETE = ITEMS.register("steel_machete", () -> new MacheteItem(ModTiers.STEEL,
//                ItemConfig.steelMacheteDamage, (float) ItemConfig.steelMacheteAttackSpeed + steelSpeed, new Item.Properties()));
//        public static final RegistryObject<Item> DIARKRITE_MACHETE = ITEMS.register("diarkrite_machete", () -> new MacheteItem(ModTiers.DIARKRITE,
//                ItemConfig.diarkriteMacheteDamage, (float) ItemConfig.diarkriteMacheteAttackSpeed + diarkriteSpeed, new Item.Properties().fireResistant()));
//        public static final RegistryObject<Item> ANTHEKTITE_MACHETE = ITEMS.register("anthektite_machete", () -> new MacheteItem(ModTiers.ANTHEKTITE,
//                ItemConfig.anthektiteMacheteDamage, (float) ItemConfig.anthektiteMacheteAttackSpeed + anthektiteSpeed, new Item.Properties().fireResistant()));
//
//        public static void register(IEventBus eventBus) {ITEMS.register(eventBus);}
//    }
//    public static class IronsSpellbooksItems {
//        public static Collection<RegistryObject<Item>> getISSCompatItems() {
//            return ITEMS.getEntries();
//        }
//        public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
//
//        public static final RegistryObject<Item> STEEL_SPELL_BOOK = ITEMS.register("steel_spell_book", () -> {
//            ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
//            builder.put(AttributeRegistry.MAX_MANA.get(), new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Max Mana", ISSConfig.steelSpellbookMana, AttributeModifier.Operation.ADDITION));
//            builder.put(AttributeRegistry.COOLDOWN_REDUCTION.get(), new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Cooldown Reduction", ISSConfig.steelSpellbookCooldown, AttributeModifier.Operation.MULTIPLY_BASE));
//            builder.put(AttributeRegistry.CAST_TIME_REDUCTION.get(), new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Cast Time Reduction", ISSConfig.steelSpellbookCastTime, AttributeModifier.Operation.MULTIPLY_BASE));
//            builder.put(AttributeRegistry.SPELL_POWER.get(), new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Spell Power", ISSConfig.steelSpellbookSpellPower, AttributeModifier.Operation.MULTIPLY_BASE));
//            return new SimpleAttributeSpellBook(ISSConfig.steelSpellbookSlot, SpellRarity.RARE, builder.build());
//        });
//        public static final RegistryObject<Item> DIARKRITE_SPELL_BOOK = ITEMS.register("diarkrite_spell_book", () -> {
//            ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
//            builder.put(AttributeRegistry.MAX_MANA.get(), new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Max Mana", ISSConfig.diarkriteSpellbookMana, AttributeModifier.Operation.ADDITION));
//            builder.put(AttributeRegistry.COOLDOWN_REDUCTION.get(), new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Cooldown Reduction", ISSConfig.diarkriteSpellbookCooldown, AttributeModifier.Operation.MULTIPLY_BASE));
//            builder.put(AttributeRegistry.CAST_TIME_REDUCTION.get(), new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Cast Time Reduction", ISSConfig.diarkriteSpellbookCastTime, AttributeModifier.Operation.MULTIPLY_BASE));
//            builder.put(AttributeRegistry.SPELL_POWER.get(), new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Spell Power", ISSConfig.diarkriteSpellbookSpellPower, AttributeModifier.Operation.MULTIPLY_BASE));
//            return new SimpleAttributeSpellBook(ISSConfig.diarkriteSpellbookSlot, SpellRarity.LEGENDARY, builder.build());
//        });
//        public static final RegistryObject<Item> ANTHEKTITE_SPELL_BOOK = ITEMS.register("anthektite_spell_book", () -> {
//            ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
//            builder.put(AttributeRegistry.MAX_MANA.get(), new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Max Mana", ISSConfig.anthektiteSpellbookMana, AttributeModifier.Operation.ADDITION));
//            builder.put(AttributeRegistry.COOLDOWN_REDUCTION.get(), new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Cooldown Reduction", ISSConfig.anthektiteSpellbookCooldown, AttributeModifier.Operation.MULTIPLY_BASE));
//            builder.put(AttributeRegistry.CAST_TIME_REDUCTION.get(), new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Cast Time Reduction", ISSConfig.anthektiteSpellbookCastTime, AttributeModifier.Operation.MULTIPLY_BASE));
//            builder.put(AttributeRegistry.SPELL_POWER.get(), new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Spell Power", ISSConfig.anthektiteSpellbookSpellPower, AttributeModifier.Operation.MULTIPLY_BASE));
//            return new SimpleAttributeSpellBook(ISSConfig.anthektiteSpellbookSlot, SpellRarity.LEGENDARY, builder.build());
//        });
//
//        public static final RegistryObject<ArmorItem> DIARKRITE_MAGE_HELMET = ITEMS.register("diarkrite_mage_helmet",
//                () -> new ISSArmorItem(MagicArmorMaterial.DIARKRITE_MAGE, ArmorItem.Type.HELMET, new Item.Properties().fireResistant()));
//        public static final RegistryObject<ArmorItem> DIARKRITE_MAGE_CHESTPLATE = ITEMS.register("diarkrite_mage_chestplate",
//                () -> new ISSArmorItem(MagicArmorMaterial.DIARKRITE_MAGE, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant()));
//        public static final RegistryObject<ArmorItem> DIARKRITE_MAGE_LEGGINGS = ITEMS.register("diarkrite_mage_leggings",
//                () -> new ISSArmorItem(MagicArmorMaterial.DIARKRITE_MAGE, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant()));
//        public static final RegistryObject<ArmorItem> DIARKRITE_MAGE_BOOTS = ITEMS.register("diarkrite_mage_boots",
//                () -> new ISSArmorItem(MagicArmorMaterial.DIARKRITE_MAGE, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant()));
//
//        public static final RegistryObject<ArmorItem> ANTHEKTITE_MAGE_HELMET = ITEMS.register("anthektite_mage_helmet",
//                () -> new ISSArmorItem(MagicArmorMaterial.ANTHEKTITE_MAGE, ArmorItem.Type.HELMET, new Item.Properties().fireResistant()));
//        public static final RegistryObject<ArmorItem> ANTHEKTITE_MAGE_CHESTPLATE = ITEMS.register("anthektite_mage_chestplate",
//                () -> new ISSArmorItem(MagicArmorMaterial.ANTHEKTITE_MAGE, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant()));
//        public static final RegistryObject<ArmorItem> ANTHEKTITE_MAGE_LEGGINGS = ITEMS.register("anthektite_mage_leggings",
//                () -> new ISSArmorItem(MagicArmorMaterial.ANTHEKTITE_MAGE, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant()));
//        public static final RegistryObject<ArmorItem> ANTHEKTITE_MAGE_BOOTS = ITEMS.register("anthektite_mage_boots",
//                () -> new ISSArmorItem(MagicArmorMaterial.ANTHEKTITE_MAGE, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant()));
//
//        public static void register(IEventBus eventBus) {ITEMS.register(eventBus);}
//    }
//    public static class AetherItems {
//        public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
//
//        public static final RegistryObject<Item> STEEL_GLOVES = ITEMS.register("steel_gloves", () -> {
//            ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
//            builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Weapon modifier", AEConfig.steelGloveMovementSpeed, AttributeModifier.Operation.MULTIPLY_BASE));
//            builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Weapon modifier", AEConfig.steelGloveAttackSpeed, AttributeModifier.Operation.MULTIPLY_BASE));
//            builder.put(Attributes.ARMOR, new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Weapon modifier", AEConfig.steelGloveArmorBonus, AttributeModifier.Operation.MULTIPLY_BASE));
//            builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Weapon modifier", AEConfig.steelGloveToughnessBonus, AttributeModifier.Operation.MULTIPLY_BASE));
//            return new AttributeGlovesItem(ModArmorMaterials.STEEL, AEConfig.steelGloveDamage, "steel_gloves", () -> SoundEvents.ARMOR_EQUIP_IRON,
//                    new Item.Properties().defaultDurability(ModTiers.STEEL.getUses()), builder.build());
//        });
//
//        public static final RegistryObject<Item> DIARKRITE_GLOVES = ITEMS.register("diarkrite_gloves", () -> {
//            ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
//            builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Weapon modifier", AEConfig.diarkriteGloveMovementSpeed, AttributeModifier.Operation.MULTIPLY_BASE));
//            builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Weapon modifier", AEConfig.diarkriteGloveAttackSpeed, AttributeModifier.Operation.MULTIPLY_BASE));
//            builder.put(Attributes.ARMOR, new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Weapon modifier", AEConfig.diarkriteGloveArmorBonus, AttributeModifier.Operation.MULTIPLY_BASE));
//            builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Weapon modifier", AEConfig.diarkriteGloveToughnessBonus, AttributeModifier.Operation.MULTIPLY_BASE));
//            return new AttributeGlovesItem(ModArmorMaterials.DIARKRITE, AEConfig.diarkriteGloveDamage, "diarkrite_gloves", () -> SoundEvents.ARMOR_EQUIP_NETHERITE,
//                    new Item.Properties().defaultDurability(ModTiers.DIARKRITE.getUses()).fireResistant(), builder.build());
//        });
//
//        public static final RegistryObject<Item> ANTHEKTITE_GLOVES = ITEMS.register("anthektite_gloves", () -> {
//            ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
//            builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Weapon modifier", AEConfig.anthektiteGloveMovementSpeed, AttributeModifier.Operation.MULTIPLY_BASE));
//            builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Weapon modifier", AEConfig.anthektiteGloveAttackSpeed, AttributeModifier.Operation.MULTIPLY_BASE));
//            builder.put(Attributes.ARMOR, new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Weapon modifier", AEConfig.anthektiteGloveArmorBonus, AttributeModifier.Operation.MULTIPLY_BASE));
//            builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Weapon modifier", AEConfig.anthektiteGloveToughnessBonus, AttributeModifier.Operation.MULTIPLY_BASE));
//            return new AttributeGlovesItem(ModArmorMaterials.DIARKRITE, AEConfig.anthektiteGloveDamage, "anthektite_gloves", () -> SoundEvents.ARMOR_EQUIP_NETHERITE,
//                    new Item.Properties().defaultDurability(ModTiers.ANTHEKTITE.getUses()).fireResistant(), builder.build());
//        });
//
//        public static void register(IEventBus eventBus) {
//            ITEMS.register(eventBus);
//        }
//    }
//    public static class SimplySwordsItems {
//        public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
//
//        static int steel_modifier = SSConfig.steelDamage;
//        static int diarkrite_modifier = SSConfig.diarkriteDamage;
//        static int anthektite_modifier = SSConfig.anthektiteDamage;
//
//        static int longsword_positive_modifier = SSConfig.longswordDamageModifier;
//        static int twinblade_positive_modifier = SSConfig.twinbladeDamageModifier;
//        static int rapier_positive_modifier = SSConfig.rapierDamageModifier;
//        static int katana_positive_modifier = SSConfig.katanaDamageModifier;
//        static int sai_positive_modifier = SSConfig.saiDamageModifier;
//        static int spear_positive_modifier = SSConfig.spearDamageModifier;
//        static int glaive_positive_modifier = SSConfig.glaiveDamageModifier;
//        static int warglaive_positive_modifier = SSConfig.warglaiveDamageModifier;
//        static int cutlass_positive_modifier = SSConfig.cutlassDamageModifier;
//        static int claymore_positive_modifier = SSConfig.claymoreDamageModifier;
//        static int greataxe_positive_modifier = SSConfig.greataxeDamageModifier;
//        static int greathammer_positive_modifier = SSConfig.greathammerDamageModifier;
//        static int chakram_positive_modifier = SSConfig.chakramDamageModifier;
//        static int scythe_positive_modifier = SSConfig.scytheDamageModifier;
//        static int halberd_positive_modifier = SSConfig.halberdDamageModifier;
//
//        static float longsword_attackspeed = (float) SSConfig.longswordAttackSpeed;
//        static float twinblade_attackspeed = (float) SSConfig.twinbladeAttackSpeed;
//        static float rapier_attackspeed = (float) SSConfig.rapierAttackSpeed;
//        static float sai_attackspeed = (float) SSConfig.saiAttackSpeed;
//        static float spear_attackspeed = (float) SSConfig.spearAttackSpeed;
//        static float katana_attackspeed = (float) SSConfig.katanaAttackSpeed;
//        static float glaive_attackspeed = (float) SSConfig.glaiveAttackSpeed;
//        static float warglaive_attackspeed = (float) SSConfig.warglaiveAttackSpeed;
//        static float cutlass_attackspeed = (float) SSConfig.cutlassAttackSpeed;
//        static float claymore_attackspeed = (float) SSConfig.claymoreAttackSpeed;
//        static float greataxe_attackspeed = (float) SSConfig.greataxeAttackSpeed;
//        static float greathammer_attackspeed = (float) SSConfig.greathammerAttackSpeed;
//        static float chakram_attackspeed = (float) SSConfig.chakramAttackSpeed;
//        static float scythe_attackspeed = (float) SSConfig.scytheAttackSpeed;
//        static float halberd_attackspeed = (float) SSConfig.halberdAttackSpeed;
//
//
//        public static final RegistryObject<Item> STEEL_LONGSWORD = registerSteel("longsword");
//        public static final RegistryObject<Item> STEEL_TWINBLADE = registerSteel("twinblade");
//        public static final RegistryObject<Item> STEEL_RAPIER = registerSteel("rapier");
//        public static final RegistryObject<Item> STEEL_SAI = registerSteel("sai");
//        public static final RegistryObject<Item> STEEL_SPEAR = registerSteel("spear");
//        public static final RegistryObject<Item> STEEL_KATANA = registerSteel("katana");
//        public static final RegistryObject<Item> STEEL_GLAIVE = registerSteel("glaive");
//        public static final RegistryObject<Item> STEEL_WARGLAIVE = registerSteel("warglaive");
//        public static final RegistryObject<Item> STEEL_CUTLASS = registerSteel("cutlass");
//        public static final RegistryObject<Item> STEEL_CLAYMORE = registerSteel("claymore");
//        public static final RegistryObject<Item> STEEL_GREATAXE = registerSteel("greataxe");
//        public static final RegistryObject<Item> STEEL_GREATHAMMER = registerSteel("greathammer");
//        public static final RegistryObject<Item> STEEL_CHAKRAM = registerSteel("chakram");
//        public static final RegistryObject<Item> STEEL_SCYTHE = registerSteel("scythe");
//        public static final RegistryObject<Item> STEEL_HALBERD = registerSteel("halberd");
//
//        public static final RegistryObject<Item> DIARKRITE_LONGSWORD = registerDiarkrite("longsword");
//        public static final RegistryObject<Item> DIARKRITE_TWINBLADE = registerDiarkrite("twinblade");
//        public static final RegistryObject<Item> DIARKRITE_RAPIER = registerDiarkrite("rapier");
//        public static final RegistryObject<Item> DIARKRITE_SAI = registerDiarkrite("sai");
//        public static final RegistryObject<Item> DIARKRITE_SPEAR = registerDiarkrite("spear");
//        public static final RegistryObject<Item> DIARKRITE_KATANA = registerDiarkrite("katana");
//        public static final RegistryObject<Item> DIARKRITE_GLAIVE = registerDiarkrite("glaive");
//        public static final RegistryObject<Item> DIARKRITE_WARGLAIVE = registerDiarkrite("warglaive");
//        public static final RegistryObject<Item> DIARKRITE_CUTLASS = registerDiarkrite("cutlass");
//        public static final RegistryObject<Item> DIARKRITE_CLAYMORE = registerDiarkrite("claymore");
//        public static final RegistryObject<Item> DIARKRITE_GREATAXE = registerDiarkrite("greataxe");
//        public static final RegistryObject<Item> DIARKRITE_GREATHAMMER = registerDiarkrite("greathammer");
//        public static final RegistryObject<Item> DIARKRITE_CHAKRAM = registerDiarkrite("chakram");
//        public static final RegistryObject<Item> DIARKRITE_SCYTHE = registerDiarkrite("scythe");
//        public static final RegistryObject<Item> DIARKRITE_HALBERD = registerDiarkrite("halberd");
//
//        public static final RegistryObject<Item> ANTHEKTITE_LONGSWORD = registerAnthektite("longsword");
//        public static final RegistryObject<Item> ANTHEKTITE_TWINBLADE = registerAnthektite("twinblade");
//        public static final RegistryObject<Item> ANTHEKTITE_RAPIER = registerAnthektite("rapier");
//        public static final RegistryObject<Item> ANTHEKTITE_SAI = registerAnthektite("sai");
//        public static final RegistryObject<Item> ANTHEKTITE_SPEAR = registerAnthektite("spear");
//        public static final RegistryObject<Item> ANTHEKTITE_KATANA = registerAnthektite("katana");
//        public static final RegistryObject<Item> ANTHEKTITE_GLAIVE = registerAnthektite("glaive");
//        public static final RegistryObject<Item> ANTHEKTITE_WARGLAIVE = registerAnthektite("warglaive");
//        public static final RegistryObject<Item> ANTHEKTITE_CUTLASS = registerAnthektite("cutlass");
//        public static final RegistryObject<Item> ANTHEKTITE_CLAYMORE = registerAnthektite("claymore");
//        public static final RegistryObject<Item> ANTHEKTITE_GREATAXE = registerAnthektite("greataxe");
//        public static final RegistryObject<Item> ANTHEKTITE_GREATHAMMER = registerAnthektite("greathammer");
//        public static final RegistryObject<Item> ANTHEKTITE_CHAKRAM = registerAnthektite("chakram");
//        public static final RegistryObject<Item> ANTHEKTITE_SCYTHE = registerAnthektite("scythe");
//        public static final RegistryObject<Item> ANTHEKTITE_HALBERD = registerAnthektite("halberd");
//
//
//        private static RegistryObject<Item> registerSteel(String id) {
//            return ITEMS.register(ModTiers.STEEL.toString().toLowerCase(Locale.ROOT) + "_" + id, ()->
//                    new SimplySwordItem(ModTiers.STEEL, (steel_modifier + getDamageMod(id)),
//                            getAttackSpeedMod(id) + steelSpeed, new Item.Properties()));
//        }
//
//        private static RegistryObject<Item> registerDiarkrite(String id) {
//            return ITEMS.register(ModTiers.DIARKRITE.toString().toLowerCase(Locale.ROOT) + "_" + id, ()->
//                    new SimplySwordItem(ModTiers.DIARKRITE, (diarkrite_modifier + getDamageMod(id)),
//                            getAttackSpeedMod(id) + diarkriteSpeed, new Item.Properties().fireResistant()));
//        }
//
//        private static RegistryObject<Item> registerAnthektite(String id) {
//            return ITEMS.register(ModTiers.ANTHEKTITE.toString().toLowerCase(Locale.ROOT) + "_" + id, ()->
//                    new SimplySwordItem(ModTiers.ANTHEKTITE, (anthektite_modifier + getDamageMod(id)),
//                            getAttackSpeedMod(id) + anthektiteSpeed, new Item.Properties().fireResistant()));
//        }
//
//
//        public static int getDamageMod(String weaponType) {
//            return ATK_DAMAGE.get(weaponType);
//        }
//
//        public static float getAttackSpeedMod(String weaponType) {
//            return ATK_SPEED.get(weaponType);
//        }
//
//        private static final HashMap<String, Integer> ATK_DAMAGE = new HashMap<>();
//        private static final HashMap<String, Float> ATK_SPEED = new HashMap<>();
//
//        static {
//            ATK_DAMAGE.put("chakram", chakram_positive_modifier);
//            ATK_SPEED.put("chakram", chakram_attackspeed);
//
//            ATK_DAMAGE.put("claymore", claymore_positive_modifier);
//            ATK_SPEED.put("claymore", claymore_attackspeed);
//
//            ATK_DAMAGE.put("cutlass", cutlass_positive_modifier);
//            ATK_SPEED.put("cutlass", cutlass_attackspeed);
//
//            ATK_DAMAGE.put("glaive", glaive_positive_modifier);
//            ATK_SPEED.put("glaive", glaive_attackspeed);
//
//            ATK_DAMAGE.put("greataxe", greataxe_positive_modifier );
//            ATK_SPEED.put("greataxe", greataxe_attackspeed);
//
//            ATK_DAMAGE.put("greathammer", greathammer_positive_modifier);
//            ATK_SPEED.put("greathammer", greathammer_attackspeed);
//
//            ATK_DAMAGE.put("halberd", halberd_positive_modifier);
//            ATK_SPEED.put("halberd", halberd_attackspeed);
//
//            ATK_DAMAGE.put("katana", katana_positive_modifier);
//            ATK_SPEED.put("katana", katana_attackspeed);
//
//            ATK_DAMAGE.put("longsword", longsword_positive_modifier);
//            ATK_SPEED.put("longsword", longsword_attackspeed);
//
//            ATK_DAMAGE.put("rapier", rapier_positive_modifier);
//            ATK_SPEED.put("rapier", rapier_attackspeed);
//
//            ATK_DAMAGE.put("sai", sai_positive_modifier);
//            ATK_SPEED.put("sai", sai_attackspeed);
//
//            ATK_DAMAGE.put("scythe", scythe_positive_modifier);
//            ATK_SPEED.put("scythe", scythe_attackspeed);
//
//            ATK_DAMAGE.put("spear", spear_positive_modifier);
//            ATK_SPEED.put("spear", spear_attackspeed);
//
//            ATK_DAMAGE.put("twinblade", twinblade_positive_modifier);
//            ATK_SPEED.put("twinblade", twinblade_attackspeed);
//
//            ATK_DAMAGE.put("warglaive", warglaive_positive_modifier);
//            ATK_SPEED.put("warglaive", warglaive_attackspeed);
//        }
//
//        public static void register(IEventBus eventBus) {ITEMS.register(eventBus);}
//    }
//    public static class SniffsWeaponsItems {
//        public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
//
//        public static final RegistryObject<Item> STEEL_GREAT_SWORD = ITEMS.register("steel_great_sword", () -> new GreatSwordItem(ModTiers.STEEL,
//                SWConfig.steelGreatSwordDamage, (float) SWConfig.steelGreatSwordAttackSpeed + steelSpeed,new Item.Properties()));
//        public static final RegistryObject<Item> DIARKRITE_GREAT_SWORD = ITEMS.register("diarkrite_great_sword", () -> new GreatSwordItem(ModTiers.DIARKRITE,
//                SWConfig.diarkriteGreatSwordDamage, (float) SWConfig.diarkriteGreatSwordAttackSpeed + diarkriteSpeed,new Item.Properties().fireResistant()));
//        public static final RegistryObject<Item> ANTHEKTITE_GREAT_SWORD = ITEMS.register("anthektite_great_sword", () -> new GreatSwordItem(ModTiers.ANTHEKTITE,
//                SWConfig.anthektiteGreatSwordDamage, (float) SWConfig.anthektiteGreatSwordAttackSpeed + anthektiteSpeed,new Item.Properties().fireResistant()));
//
//        public static final RegistryObject<Item> STEEL_GREAT_AXE = ITEMS.register("steel_great_axe", () -> new GreatAxeItem(ModTiers.STEEL,
//                SWConfig.steelGreatAxeDamage, (float) SWConfig.steelGreatAxeAttackSpeed + steelSpeed,new Item.Properties()));
//        public static final RegistryObject<Item> DIARKRITE_GREAT_AXE = ITEMS.register("diarkrite_great_axe", () -> new GreatAxeItem(ModTiers.DIARKRITE,
//                SWConfig.diarkriteGreatAxeDamage, (float) SWConfig.diarkriteGreatAxeAttackSpeed + diarkriteSpeed,new Item.Properties().fireResistant()));
//        public static final RegistryObject<Item> ANTHEKTITE_GREAT_AXE = ITEMS.register("anthektite_great_axe", () -> new GreatAxeItem(ModTiers.ANTHEKTITE,
//                SWConfig.anthektiteGreatAxeDamage, (float) SWConfig.anthektiteGreatAxeAttackSpeed + anthektiteSpeed,new Item.Properties().fireResistant()));
//
//        public static final RegistryObject<Item> STEEL_GREAT_PICKAXE = ITEMS.register("steel_great_pickaxe", () -> new ModGreatPickaxeItem(ModTiers.STEEL,
//                SWConfig.steelGreatPickaxeDamage, (float) SWConfig.steelGreatPickaxeAttackSpeed + steelSpeed,new Item.Properties()));
//        public static final RegistryObject<Item> DIARKRITE_GREAT_PICKAXE = ITEMS.register("diarkrite_great_pickaxe", () -> new ModGreatPickaxeItem(ModTiers.DIARKRITE,
//                SWConfig.diarkriteGreatPickaxeDamage, (float) SWConfig.diarkriteGreatPickaxeAttackSpeed + diarkriteSpeed,new Item.Properties().fireResistant()));
//        public static final RegistryObject<Item> ANTHEKTITE_GREAT_PICKAXE = ITEMS.register("anthektite_great_pickaxe", () -> new ModGreatPickaxeItem(ModTiers.ANTHEKTITE,
//                SWConfig.anthektiteGreatPickaxeDamage, (float) SWConfig.anthektiteGreatPickaxeAttackSpeed + anthektiteSpeed,new Item.Properties().fireResistant()));
//
//        public static final RegistryObject<Item> STEEL_NAGINATA = ITEMS.register("steel_naginata", () -> new NaginataItem(ModTiers.STEEL,
//                SWConfig.steelNaginataDamage, (float) SWConfig.steelNaginataAttackSpeed + steelSpeed,new Item.Properties()));
//        public static final RegistryObject<Item> DIARKRITE_NAGINATA = ITEMS.register("diarkrite_naginata", () -> new NaginataItem(ModTiers.DIARKRITE,
//                SWConfig.diarkriteNaginataDamage, (float) SWConfig.diarkriteNaginataAttackSpeed + diarkriteSpeed,new Item.Properties().fireResistant()));
//        public static final RegistryObject<Item> ANTHEKTITE_NAGINATA = ITEMS.register("anthektite_naginata", () -> new NaginataItem(ModTiers.ANTHEKTITE,
//                SWConfig.anthektiteNaginataDamage, (float) SWConfig.anthektiteNaginataAttackSpeed + anthektiteSpeed,new Item.Properties().fireResistant()));
//
//        public static final RegistryObject<Item> STEEL_SURCOAT = ITEMS.register("steel_surcoat",
//                () -> new SniffsWeaponsArmorItem(ModArmorMaterials.STEEL, ArmorItem.Type.CHESTPLATE, "stylish", 10511680, new Item.Properties()));
//        public static final RegistryObject<Item> DIARKRITE_SURCOAT = ITEMS.register("diarkrite_surcoat",
//                () -> new SniffsWeaponsArmorItem(ModArmorMaterials.DIARKRITE, ArmorItem.Type.CHESTPLATE, "stylish", 10511680, new Item.Properties().fireResistant()));
//        public static final RegistryObject<Item> ANTHEKTITE_SURCOAT = ITEMS.register("anthektite_surcoat",
//                () -> new SniffsWeaponsArmorItem(ModArmorMaterials.ANTHEKTITE, ArmorItem.Type.CHESTPLATE, "stylish", 10511680, new Item.Properties().fireResistant()));
//
//        public static final RegistryObject<Item> STEEL_HELM = ITEMS.register("steel_helm",
//                () -> new SniffsWeaponsArmorItem(ModArmorMaterials.STEEL, ArmorItem.Type.HELMET, "stylish", 16777215, new Item.Properties()));
//        public static final RegistryObject<Item> DIARKRITE_HELM = ITEMS.register("diarkrite_helm",
//                () -> new SniffsWeaponsArmorItem(ModArmorMaterials.DIARKRITE, ArmorItem.Type.HELMET, "stylish", 16777215, new Item.Properties().fireResistant()));
//        public static final RegistryObject<Item> ANTHEKTITE_HELM = ITEMS.register("anthektite_helm",
//                () -> new SniffsWeaponsArmorItem(ModArmorMaterials.ANTHEKTITE, ArmorItem.Type.HELMET, "stylish", 16777215, new Item.Properties().fireResistant()));
//
//        public static final RegistryObject<Item> PLATED_STEEL_CHESTPLATE = ITEMS.register("plated_steel_chestplate",
//                () -> new SniffsWeaponsArmorItem(ModArmorMaterials.STEEL, ArmorItem.Type.CHESTPLATE, "horned", 10511680, new Item.Properties()));
//        public static final RegistryObject<Item> PLATED_DIARKRITE_CHESTPLATE = ITEMS.register("plated_diarkrite_chestplate",
//                () -> new SniffsWeaponsArmorItem(ModArmorMaterials.DIARKRITE, ArmorItem.Type.CHESTPLATE, "horned", 10511680, new Item.Properties().fireResistant()));
//        public static final RegistryObject<Item> PLATED_ANTHEKTITE_CHESTPLATE = ITEMS.register("plated_anthektite_chestplate",
//                () -> new SniffsWeaponsArmorItem(ModArmorMaterials.ANTHEKTITE, ArmorItem.Type.CHESTPLATE, "horned", 10511680, new Item.Properties().fireResistant()));
//
//        public static final RegistryObject<Item> STEEL_HORNED_HELM = ITEMS.register("steel_horned_helm",
//                () -> new SniffsWeaponsArmorItem(ModArmorMaterials.STEEL, ArmorItem.Type.HELMET, "horned", 16777215, new Item.Properties()));
//        public static final RegistryObject<Item> DIARKRITE_HORNED_HELM = ITEMS.register("diarkrite_horned_helm",
//                () -> new SniffsWeaponsArmorItem(ModArmorMaterials.DIARKRITE, ArmorItem.Type.HELMET, "horned", 16777215, new Item.Properties().fireResistant()));
//        public static final RegistryObject<Item> ANTHEKTITE_HORNED_HELM = ITEMS.register("anthektite_horned_helm",
//                () -> new SniffsWeaponsArmorItem(ModArmorMaterials.ANTHEKTITE, ArmorItem.Type.HELMET, "horned", 16777215, new Item.Properties().fireResistant()));
//
//        public static final RegistryObject<Item> STEEL_DO = ITEMS.register("steel_do",
//                () -> new SniffsWeaponsArmorItem(ModArmorMaterials.STEEL, ArmorItem.Type.CHESTPLATE, "samurai", 10511680, new Item.Properties()));
//        public static final RegistryObject<Item> DIARKRITE_DO = ITEMS.register("diarkrite_do",
//                () -> new SniffsWeaponsArmorItem(ModArmorMaterials.DIARKRITE, ArmorItem.Type.CHESTPLATE, "samurai", 10511680, new Item.Properties().fireResistant()));
//        public static final RegistryObject<Item> ANTHEKTITE_DO = ITEMS.register("anthektite_do",
//                () -> new SniffsWeaponsArmorItem(ModArmorMaterials.ANTHEKTITE, ArmorItem.Type.CHESTPLATE, "samurai", 10511680, new Item.Properties().fireResistant()));
//
//        public static final RegistryObject<Item> STEEL_KABUTO = ITEMS.register("steel_kabuto",
//                () -> new SniffsWeaponsArmorItem(ModArmorMaterials.STEEL, ArmorItem.Type.HELMET, "samurai", 16777215, new Item.Properties()));
//        public static final RegistryObject<Item> DIARKRITE_KABUTO = ITEMS.register("diarkrite_kabuto",
//                () -> new SniffsWeaponsArmorItem(ModArmorMaterials.DIARKRITE, ArmorItem.Type.HELMET, "samurai", 16777215, new Item.Properties().fireResistant()));
//        public static final RegistryObject<Item> ANTHEKTITE_KABUTO = ITEMS.register("anthektite_kabuto",
//                () -> new SniffsWeaponsArmorItem(ModArmorMaterials.ANTHEKTITE, ArmorItem.Type.HELMET, "samurai", 16777215, new Item.Properties().fireResistant()));
//
//        public static final RegistryObject<Item> CLOTHED_STEEL_CUIRASS = ITEMS.register("clothed_steel_cuirass",
//                () -> new SniffsWeaponsArmorItem(ModArmorMaterials.STEEL, ArmorItem.Type.CHESTPLATE, "clothed", 10511680, new Item.Properties()));
//        public static final RegistryObject<Item> CLOTHED_DIARKRITE_CUIRASS = ITEMS.register("clothed_diarkrite_cuirass",
//                () -> new SniffsWeaponsArmorItem(ModArmorMaterials.DIARKRITE, ArmorItem.Type.CHESTPLATE, "clothed", 10511680, new Item.Properties().fireResistant()));
//        public static final RegistryObject<Item> CLOTHED_ANTHEKTITE_CUIRASS = ITEMS.register("clothed_anthektite_cuirass",
//                () -> new SniffsWeaponsArmorItem(ModArmorMaterials.ANTHEKTITE, ArmorItem.Type.CHESTPLATE, "clothed", 10511680, new Item.Properties().fireResistant()));
//
//        public static void register(IEventBus eventBus) {ITEMS.register(eventBus);}
//    }
//    public static class AdvancedNetheriteItems {
//        public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
//
//        //Diarkrite Ingots
//        public static final RegistryObject<Item> DIARKRITE_IRON = ITEMS.register("diarkrite_iron_ingot",
//                () -> new ANItem(new Item.Properties().fireResistant()));
//        public static final RegistryObject<Item> DIARKRITE_GOLD = ITEMS.register("diarkrite_gold_ingot",
//                () -> new ANItem(new Item.Properties().fireResistant()));
//        public static final RegistryObject<Item> DIARKRITE_EMERALD = ITEMS.register("diarkrite_emerald_ingot",
//                () -> new ANItem(new Item.Properties().fireResistant()));
//        public static final RegistryObject<Item> DIARKRITE_DIAMOND = ITEMS.register("diarkrite_diamond_ingot",
//                () -> new ANItem(new Item.Properties().fireResistant()));
//
//        //Diarkrite Armor
//        public static final RegistryObject<Item> DIARKRITE_IRON_HELMET = ITEMS.register("diarkrite_iron_helmet",
//                () -> new ANArmorItem(ModArmorMaterials.DIARKRITE_IRON, ArmorItem.Type.HELMET, new Item.Properties()));
//        public static final RegistryObject<Item> DIARKRITE_IRON_CHESTPLATE = ITEMS.register("diarkrite_iron_chestplate",
//                () -> new ANArmorItem(ModArmorMaterials.DIARKRITE_IRON, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
//        public static final RegistryObject<Item> DIARKRITE_IRON_LEGGINGS = ITEMS.register("diarkrite_iron_leggings",
//                () -> new ANArmorItem(ModArmorMaterials.DIARKRITE_IRON, ArmorItem.Type.LEGGINGS, new Item.Properties()));
//        public static final RegistryObject<Item> DIARKRITE_IRON_BOOTS = ITEMS.register("diarkrite_iron_boots",
//                () -> new ANArmorItem(ModArmorMaterials.DIARKRITE_IRON, ArmorItem.Type.BOOTS, new Item.Properties()));
//
//        public static final RegistryObject<Item> DIARKRITE_GOLD_HELMET = ITEMS.register("diarkrite_gold_helmet",
//                () -> new ANArmorItem(ModArmorMaterials.DIARKRITE_GOLD, ArmorItem.Type.HELMET, new Item.Properties()));
//        public static final RegistryObject<Item> DIARKRITE_GOLD_CHESTPLATE = ITEMS.register("diarkrite_gold_chestplate",
//                () -> new ANArmorItem(ModArmorMaterials.DIARKRITE_GOLD, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
//        public static final RegistryObject<Item> DIARKRITE_GOLD_LEGGINGS = ITEMS.register("diarkrite_gold_leggings",
//                () -> new ANArmorItem(ModArmorMaterials.DIARKRITE_GOLD, ArmorItem.Type.LEGGINGS, new Item.Properties()));
//        public static final RegistryObject<Item> DIARKRITE_GOLD_BOOTS = ITEMS.register("diarkrite_gold_boots",
//                () -> new ANArmorItem(ModArmorMaterials.DIARKRITE_GOLD, ArmorItem.Type.BOOTS, new Item.Properties()));
//
//        public static final RegistryObject<Item> DIARKRITE_EMERALD_HELMET = ITEMS.register("diarkrite_emerald_helmet",
//                () -> new ANArmorItem(ModArmorMaterials.DIARKRITE_EMERALD, ArmorItem.Type.HELMET, new Item.Properties()));
//        public static final RegistryObject<Item> DIARKRITE_EMERALD_CHESTPLATE = ITEMS.register("diarkrite_emerald_chestplate",
//                () -> new ANArmorItem(ModArmorMaterials.DIARKRITE_EMERALD, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
//        public static final RegistryObject<Item> DIARKRITE_EMERALD_LEGGINGS = ITEMS.register("diarkrite_emerald_leggings",
//                () -> new ANArmorItem(ModArmorMaterials.DIARKRITE_EMERALD, ArmorItem.Type.LEGGINGS, new Item.Properties()));
//        public static final RegistryObject<Item> DIARKRITE_EMERALD_BOOTS = ITEMS.register("diarkrite_emerald_boots",
//                () -> new ANArmorItem(ModArmorMaterials.DIARKRITE_EMERALD, ArmorItem.Type.BOOTS, new Item.Properties()));
//
//        public static final RegistryObject<Item> DIARKRITE_DIAMOND_HELMET = ITEMS.register("diarkrite_diamond_helmet",
//                () -> new ANArmorItem(ModArmorMaterials.DIARKRITE_DIAMOND, ArmorItem.Type.HELMET, new Item.Properties()));
//        public static final RegistryObject<Item> DIARKRITE_DIAMOND_CHESTPLATE = ITEMS.register("diarkrite_diamond_chestplate",
//                () -> new ANArmorItem(ModArmorMaterials.DIARKRITE_DIAMOND, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
//        public static final RegistryObject<Item> DIARKRITE_DIAMOND_LEGGINGS = ITEMS.register("diarkrite_diamond_leggings",
//                () -> new ANArmorItem(ModArmorMaterials.DIARKRITE_DIAMOND, ArmorItem.Type.LEGGINGS, new Item.Properties()));
//        public static final RegistryObject<Item> DIARKRITE_DIAMOND_BOOTS = ITEMS.register("diarkrite_diamond_boots",
//                () -> new ANArmorItem(ModArmorMaterials.DIARKRITE_DIAMOND, ArmorItem.Type.BOOTS, new Item.Properties()));
//
//        //Diarkrite Tools
//        public static final RegistryObject<Item> DIARKRITE_IRON_AXE = ITEMS.register("diarkrite_iron_axe", () -> new ANAxeItem(ModTiers.DIARKRITE_IRON,
//                ANConfig.diarkriteIronAxeDamage, ANConfig.diarkriteIronAxeAttackSpeed + diarkriteSpeed, new Item.Properties()));
//        public static final RegistryObject<Item> DIARKRITE_GOLD_AXE = ITEMS.register("diarkrite_gold_axe", () -> new ANAxeItem(ModTiers.DIARKRITE_GOLD,
//                ANConfig.diarkriteGoldAxeDamage, ANConfig.diarkriteGoldAxeAttackSpeed + diarkriteSpeed, new Item.Properties()));
//        public static final RegistryObject<Item> DIARKRITE_EMERALD_AXE = ITEMS.register("diarkrite_emerald_axe", () -> new ANAxeItem(ModTiers.DIARKRITE_EMERALD,
//                ANConfig.diarkriteEmeraldAxeDamage, ANConfig.diarkriteEmeraldAxeAttackSpeed + diarkriteSpeed, new Item.Properties()));
//        public static final RegistryObject<Item> DIARKRITE_DIAMOND_AXE = ITEMS.register("diarkrite_diamond_axe", () -> new ANAxeItem(ModTiers.DIARKRITE_DIAMOND,
//                ANConfig.diarkriteDiamondAxeDamage, ANConfig.diarkriteDiamondAxeAttackSpeed + diarkriteSpeed, new Item.Properties()));
//
//        public static final RegistryObject<Item> DIARKRITE_IRON_HOE = ITEMS.register("diarkrite_iron_hoe", () -> new ANHoeItem(ModTiers.DIARKRITE_IRON,
//                ANConfig.diarkriteIronHoeDamage, ANConfig.diarkriteIronHoeAttackSpeed, new Item.Properties()));
//        public static final RegistryObject<Item> DIARKRITE_GOLD_HOE = ITEMS.register("diarkrite_gold_hoe", () -> new ANHoeItem(ModTiers.DIARKRITE_GOLD,
//                ANConfig.diarkriteGoldHoeDamage, ANConfig.diarkriteGoldHoeAttackSpeed, new Item.Properties()));
//        public static final RegistryObject<Item> DIARKRITE_EMERALD_HOE = ITEMS.register("diarkrite_emerald_hoe", () -> new ANHoeItem(ModTiers.DIARKRITE_EMERALD,
//                ANConfig.diarkriteEmeraldHoeDamage, ANConfig.diarkriteEmeraldHoeAttackSpeed, new Item.Properties()));
//        public static final RegistryObject<Item> DIARKRITE_DIAMOND_HOE = ITEMS.register("diarkrite_diamond_hoe", () -> new ANHoeItem(ModTiers.DIARKRITE_DIAMOND,
//                ANConfig.diarkriteDiamondHoeDamage, ANConfig.diarkriteDiamondHoeAttackSpeed, new Item.Properties()));
//
//        public static final RegistryObject<Item> DIARKRITE_IRON_PICKAXE = ITEMS.register("diarkrite_iron_pickaxe", () -> new ANPickaxeItem(ModTiers.DIARKRITE_IRON,
//                ANConfig.diarkriteIronPickaxeDamage, ANConfig.diarkriteIronPickaxeAttackSpeed, new Item.Properties()));
//        public static final RegistryObject<Item> DIARKRITE_GOLD_PICKAXE = ITEMS.register("diarkrite_gold_pickaxe", () -> new ANPickaxeItem(ModTiers.DIARKRITE_GOLD,
//                ANConfig.diarkriteGoldPickaxeDamage, ANConfig.diarkriteGoldPickaxeAttackSpeed, new Item.Properties()));
//        public static final RegistryObject<Item> DIARKRITE_EMERALD_PICKAXE = ITEMS.register("diarkrite_emerald_pickaxe", () -> new ANPickaxeItem(ModTiers.DIARKRITE_EMERALD,
//                ANConfig.diarkriteEmeraldPickaxeDamage, ANConfig.diarkriteEmeraldPickaxeAttackSpeed, new Item.Properties()));
//        public static final RegistryObject<Item> DIARKRITE_DIAMOND_PICKAXE = ITEMS.register("diarkrite_diamond_pickaxe", () -> new ANPickaxeItem(ModTiers.DIARKRITE_DIAMOND,
//                ANConfig.diarkriteDiamondPickaxeDamage, ANConfig.diarkriteDiamondPickaxeAttackSpeed, new Item.Properties()));
//
//        public static final RegistryObject<Item> DIARKRITE_IRON_SHOVEL = ITEMS.register("diarkrite_iron_shovel", () -> new ANShovelItem(ModTiers.DIARKRITE_IRON,
//                ANConfig.diarkriteIronShovelDamage, ANConfig.diarkriteIronShovelAttackSpeed, new Item.Properties()));
//        public static final RegistryObject<Item> DIARKRITE_GOLD_SHOVEL = ITEMS.register("diarkrite_gold_shovel", () -> new ANShovelItem(ModTiers.DIARKRITE_GOLD,
//                ANConfig.diarkriteGoldShovelDamage, ANConfig.diarkriteGoldShovelAttackSpeed, new Item.Properties()));
//        public static final RegistryObject<Item> DIARKRITE_EMERALD_SHOVEL = ITEMS.register("diarkrite_emerald_shovel", () -> new ANShovelItem(ModTiers.DIARKRITE_EMERALD,
//                ANConfig.diarkriteEmeraldShovelDamage, ANConfig.diarkriteEmeraldShovelAttackSpeed, new Item.Properties()));
//        public static final RegistryObject<Item> DIARKRITE_DIAMOND_SHOVEL = ITEMS.register("diarkrite_diamond_shovel", () -> new ANShovelItem(ModTiers.DIARKRITE_DIAMOND,
//                ANConfig.diarkriteDiamondShovelDamage, ANConfig.diarkriteDiamondShovelAttackSpeed, new Item.Properties()));
//
//        public static final RegistryObject<Item> DIARKRITE_IRON_SWORD = ITEMS.register("diarkrite_iron_sword", () -> new ANSwordItem(ModTiers.DIARKRITE_IRON,
//                ANConfig.diarkriteIronSwordDamage, ANConfig.diarkriteIronSwordAttackSpeed + diarkriteSpeed, new Item.Properties()));
//        public static final RegistryObject<Item> DIARKRITE_GOLD_SWORD = ITEMS.register("diarkrite_gold_sword", () -> new ANSwordItem(ModTiers.DIARKRITE_GOLD,
//                ANConfig.diarkriteGoldSwordDamage, ANConfig.diarkriteGoldSwordAttackSpeed + diarkriteSpeed, new Item.Properties()));
//        public static final RegistryObject<Item> DIARKRITE_EMERALD_SWORD = ITEMS.register("diarkrite_emerald_sword", () -> new ANSwordItem(ModTiers.DIARKRITE_EMERALD,
//                ANConfig.diarkriteEmeraldSwordDamage, ANConfig.diarkriteEmeraldSwordAttackSpeed + diarkriteSpeed, new Item.Properties()));
//        public static final RegistryObject<Item> DIARKRITE_DIAMOND_SWORD = ITEMS.register("diarkrite_diamond_sword", () -> new ANSwordItem(ModTiers.DIARKRITE_DIAMOND,
//                ANConfig.diarkriteDiamondSwordDamage, ANConfig.diarkriteDiamondSwordAttackSpeed + diarkriteSpeed, new Item.Properties()));
//
//        //Diarkrite Blocks
//        public static final RegistryObject<Item> DIARKRITE_IRON_BLOCK = ITEMS.register("diarkrite_iron_block",
//                () -> new BlockItem(AdvancedNetheriteBlocks.DIARKRITE_IRON_BLOCK.get(), new Item.Properties().fireResistant()));
//        public static final RegistryObject<Item> DIARKRITE_GOLD_BLOCK = ITEMS.register("diarkrite_gold_block",
//                () -> new BlockItem(AdvancedNetheriteBlocks.DIARKRITE_GOLD_BLOCK.get(), new Item.Properties().fireResistant()));
//        public static final RegistryObject<Item> DIARKRITE_EMERALD_BLOCK = ITEMS.register("diarkrite_emerald_block",
//                () -> new BlockItem(AdvancedNetheriteBlocks.DIARKRITE_EMERALD_BLOCK.get(), new Item.Properties().fireResistant()));
//        public static final RegistryObject<Item> DIARKRITE_DIAMOND_BLOCK = ITEMS.register("diarkrite_diamond_block",
//                () -> new BlockItem(AdvancedNetheriteBlocks.DIARKRITE_DIAMOND_BLOCK.get(), new Item.Properties().fireResistant()));
//
//        //Anthektite Ingots
//        public static final RegistryObject<Item> ANTHEKTITE_IRON = ITEMS.register("anthektite_iron_ingot",
//                () -> new ANItem(new Item.Properties().fireResistant()));
//        public static final RegistryObject<Item> ANTHEKTITE_GOLD = ITEMS.register("anthektite_gold_ingot",
//                () -> new ANItem(new Item.Properties().fireResistant()));
//        public static final RegistryObject<Item> ANTHEKTITE_EMERALD = ITEMS.register("anthektite_emerald_ingot",
//                () -> new ANItem(new Item.Properties().fireResistant()));
//        public static final RegistryObject<Item> ANTHEKTITE_DIAMOND = ITEMS.register("anthektite_diamond_ingot",
//                () -> new ANItem(new Item.Properties().fireResistant()));
//
//        //Anthektite Armor
//        public static final RegistryObject<Item> ANTHEKTITE_IRON_HELMET = ITEMS.register("anthektite_iron_helmet",
//                () -> new ANArmorItem(ModArmorMaterials.ANTHEKTITE_IRON, ArmorItem.Type.HELMET, new Item.Properties()));
//        public static final RegistryObject<Item> ANTHEKTITE_IRON_CHESTPLATE = ITEMS.register("anthektite_iron_chestplate",
//                () -> new ANArmorItem(ModArmorMaterials.ANTHEKTITE_IRON, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
//        public static final RegistryObject<Item> ANTHEKTITE_IRON_LEGGINGS = ITEMS.register("anthektite_iron_leggings",
//                () -> new ANArmorItem(ModArmorMaterials.ANTHEKTITE_IRON, ArmorItem.Type.LEGGINGS, new Item.Properties()));
//        public static final RegistryObject<Item> ANTHEKTITE_IRON_BOOTS = ITEMS.register("anthektite_iron_boots",
//                () -> new ANArmorItem(ModArmorMaterials.ANTHEKTITE_IRON, ArmorItem.Type.BOOTS, new Item.Properties()));
//
//        public static final RegistryObject<Item> ANTHEKTITE_GOLD_HELMET = ITEMS.register("anthektite_gold_helmet",
//                () -> new ANArmorItem(ModArmorMaterials.ANTHEKTITE_GOLD, ArmorItem.Type.HELMET, new Item.Properties()));
//        public static final RegistryObject<Item> ANTHEKTITE_GOLD_CHESTPLATE = ITEMS.register("anthektite_gold_chestplate",
//                () -> new ANArmorItem(ModArmorMaterials.ANTHEKTITE_GOLD, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
//        public static final RegistryObject<Item> ANTHEKTITE_GOLD_LEGGINGS = ITEMS.register("anthektite_gold_leggings",
//                () -> new ANArmorItem(ModArmorMaterials.ANTHEKTITE_GOLD, ArmorItem.Type.LEGGINGS, new Item.Properties()));
//        public static final RegistryObject<Item> ANTHEKTITE_GOLD_BOOTS = ITEMS.register("anthektite_gold_boots",
//                () -> new ANArmorItem(ModArmorMaterials.ANTHEKTITE_GOLD, ArmorItem.Type.BOOTS, new Item.Properties()));
//
//        public static final RegistryObject<Item> ANTHEKTITE_EMERALD_HELMET = ITEMS.register("anthektite_emerald_helmet",
//                () -> new ANArmorItem(ModArmorMaterials.ANTHEKTITE_EMERALD, ArmorItem.Type.HELMET, new Item.Properties()));
//        public static final RegistryObject<Item> ANTHEKTITE_EMERALD_CHESTPLATE = ITEMS.register("anthektite_emerald_chestplate",
//                () -> new ANArmorItem(ModArmorMaterials.ANTHEKTITE_EMERALD, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
//        public static final RegistryObject<Item> ANTHEKTITE_EMERALD_LEGGINGS = ITEMS.register("anthektite_emerald_leggings",
//                () -> new ANArmorItem(ModArmorMaterials.ANTHEKTITE_EMERALD, ArmorItem.Type.LEGGINGS, new Item.Properties()));
//        public static final RegistryObject<Item> ANTHEKTITE_EMERALD_BOOTS = ITEMS.register("anthektite_emerald_boots",
//                () -> new ANArmorItem(ModArmorMaterials.ANTHEKTITE_EMERALD, ArmorItem.Type.BOOTS, new Item.Properties()));
//
//        public static final RegistryObject<Item> ANTHEKTITE_DIAMOND_HELMET = ITEMS.register("anthektite_diamond_helmet",
//                () -> new ANArmorItem(ModArmorMaterials.ANTHEKTITE_DIAMOND, ArmorItem.Type.HELMET, new Item.Properties()));
//        public static final RegistryObject<Item> ANTHEKTITE_DIAMOND_CHESTPLATE = ITEMS.register("anthektite_diamond_chestplate",
//                () -> new ANArmorItem(ModArmorMaterials.ANTHEKTITE_DIAMOND, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
//        public static final RegistryObject<Item> ANTHEKTITE_DIAMOND_LEGGINGS = ITEMS.register("anthektite_diamond_leggings",
//                () -> new ANArmorItem(ModArmorMaterials.ANTHEKTITE_DIAMOND, ArmorItem.Type.LEGGINGS, new Item.Properties()));
//        public static final RegistryObject<Item> ANTHEKTITE_DIAMOND_BOOTS = ITEMS.register("anthektite_diamond_boots",
//                () -> new ANArmorItem(ModArmorMaterials.ANTHEKTITE_DIAMOND, ArmorItem.Type.BOOTS, new Item.Properties()));
//
//        //Anthektite Tools
//        public static final RegistryObject<Item> ANTHEKTITE_IRON_AXE = ITEMS.register("anthektite_iron_axe", () -> new ANAxeItem(ModTiers.ANTHEKTITE_IRON,
//                ANConfig.anthektiteIronAxeDamage, ANConfig.anthektiteIronAxeAttackSpeed + anthektiteSpeed, new Item.Properties()));
//        public static final RegistryObject<Item> ANTHEKTITE_GOLD_AXE = ITEMS.register("anthektite_gold_axe", () -> new ANAxeItem(ModTiers.ANTHEKTITE_GOLD,
//                ANConfig.anthektiteGoldAxeDamage, ANConfig.anthektiteGoldAxeAttackSpeed + anthektiteSpeed, new Item.Properties()));
//        public static final RegistryObject<Item> ANTHEKTITE_EMERALD_AXE = ITEMS.register("anthektite_emerald_axe", () -> new ANAxeItem(ModTiers.ANTHEKTITE_EMERALD,
//                ANConfig.anthektiteEmeraldAxeDamage, ANConfig.anthektiteEmeraldAxeAttackSpeed + anthektiteSpeed, new Item.Properties()));
//        public static final RegistryObject<Item> ANTHEKTITE_DIAMOND_AXE = ITEMS.register("anthektite_diamond_axe", () -> new ANAxeItem(ModTiers.ANTHEKTITE_DIAMOND,
//                ANConfig.anthektiteDiamondAxeDamage, ANConfig.anthektiteDiamondAxeAttackSpeed + anthektiteSpeed, new Item.Properties()));
//
//        public static final RegistryObject<Item> ANTHEKTITE_IRON_HOE = ITEMS.register("anthektite_iron_hoe", () -> new ANHoeItem(ModTiers.ANTHEKTITE_IRON,
//                ANConfig.anthektiteIronHoeDamage, ANConfig.anthektiteIronHoeAttackSpeed, new Item.Properties()));
//        public static final RegistryObject<Item> ANTHEKTITE_GOLD_HOE = ITEMS.register("anthektite_gold_hoe", () -> new ANHoeItem(ModTiers.ANTHEKTITE_GOLD,
//                ANConfig.anthektiteGoldHoeDamage, ANConfig.anthektiteGoldHoeAttackSpeed, new Item.Properties()));
//        public static final RegistryObject<Item> ANTHEKTITE_EMERALD_HOE = ITEMS.register("anthektite_emerald_hoe", () -> new ANHoeItem(ModTiers.ANTHEKTITE_EMERALD,
//                ANConfig.anthektiteEmeraldHoeDamage, ANConfig.anthektiteEmeraldHoeAttackSpeed, new Item.Properties()));
//        public static final RegistryObject<Item> ANTHEKTITE_DIAMOND_HOE = ITEMS.register("anthektite_diamond_hoe", () -> new ANHoeItem(ModTiers.ANTHEKTITE_DIAMOND,
//                ANConfig.anthektiteDiamondHoeDamage, ANConfig.anthektiteDiamondHoeAttackSpeed, new Item.Properties()));
//
//        public static final RegistryObject<Item> ANTHEKTITE_IRON_PICKAXE = ITEMS.register("anthektite_iron_pickaxe", () -> new ANPickaxeItem(ModTiers.ANTHEKTITE_IRON,
//                ANConfig.anthektiteIronPickaxeDamage, ANConfig.anthektiteIronPickaxeAttackSpeed, new Item.Properties()));
//        public static final RegistryObject<Item> ANTHEKTITE_GOLD_PICKAXE = ITEMS.register("anthektite_gold_pickaxe", () -> new ANPickaxeItem(ModTiers.ANTHEKTITE_GOLD,
//                ANConfig.anthektiteGoldPickaxeDamage, ANConfig.anthektiteGoldPickaxeAttackSpeed, new Item.Properties()));
//        public static final RegistryObject<Item> ANTHEKTITE_EMERALD_PICKAXE = ITEMS.register("anthektite_emerald_pickaxe", () -> new ANPickaxeItem(ModTiers.ANTHEKTITE_EMERALD,
//                ANConfig.anthektiteEmeraldPickaxeDamage, ANConfig.anthektiteEmeraldPickaxeAttackSpeed, new Item.Properties()));
//        public static final RegistryObject<Item> ANTHEKTITE_DIAMOND_PICKAXE = ITEMS.register("anthektite_diamond_pickaxe", () -> new ANPickaxeItem(ModTiers.ANTHEKTITE_DIAMOND,
//                ANConfig.anthektiteDiamondPickaxeDamage, ANConfig.anthektiteDiamondPickaxeAttackSpeed, new Item.Properties()));
//
//        public static final RegistryObject<Item> ANTHEKTITE_IRON_SHOVEL = ITEMS.register("anthektite_iron_shovel", () -> new ANShovelItem(ModTiers.ANTHEKTITE_IRON,
//                ANConfig.anthektiteIronShovelDamage, ANConfig.anthektiteIronShovelAttackSpeed, new Item.Properties()));
//        public static final RegistryObject<Item> ANTHEKTITE_GOLD_SHOVEL = ITEMS.register("anthektite_gold_shovel", () -> new ANShovelItem(ModTiers.ANTHEKTITE_GOLD,
//                ANConfig.anthektiteGoldShovelDamage, ANConfig.anthektiteGoldShovelAttackSpeed, new Item.Properties()));
//        public static final RegistryObject<Item> ANTHEKTITE_EMERALD_SHOVEL = ITEMS.register("anthektite_emerald_shovel", () -> new ANShovelItem(ModTiers.ANTHEKTITE_EMERALD,
//                ANConfig.anthektiteEmeraldShovelDamage, ANConfig.anthektiteEmeraldShovelAttackSpeed, new Item.Properties()));
//        public static final RegistryObject<Item> ANTHEKTITE_DIAMOND_SHOVEL = ITEMS.register("anthektite_diamond_shovel", () -> new ANShovelItem(ModTiers.ANTHEKTITE_DIAMOND,
//                ANConfig.anthektiteDiamondShovelDamage, ANConfig.anthektiteDiamondShovelAttackSpeed, new Item.Properties()));
//
//        public static final RegistryObject<Item> ANTHEKTITE_IRON_SWORD = ITEMS.register("anthektite_iron_sword", () -> new ANSwordItem(ModTiers.ANTHEKTITE_IRON,
//                ANConfig.anthektiteIronSwordDamage, ANConfig.anthektiteIronSwordAttackSpeed + anthektiteSpeed, new Item.Properties()));
//        public static final RegistryObject<Item> ANTHEKTITE_GOLD_SWORD = ITEMS.register("anthektite_gold_sword", () -> new ANSwordItem(ModTiers.ANTHEKTITE_GOLD,
//                ANConfig.anthektiteGoldSwordDamage, ANConfig.anthektiteGoldSwordAttackSpeed + anthektiteSpeed, new Item.Properties()));
//        public static final RegistryObject<Item> ANTHEKTITE_EMERALD_SWORD = ITEMS.register("anthektite_emerald_sword", () -> new ANSwordItem(ModTiers.ANTHEKTITE_EMERALD,
//                ANConfig.anthektiteEmeraldSwordDamage, ANConfig.anthektiteEmeraldSwordAttackSpeed + anthektiteSpeed, new Item.Properties()));
//        public static final RegistryObject<Item> ANTHEKTITE_DIAMOND_SWORD = ITEMS.register("anthektite_diamond_sword", () -> new ANSwordItem(ModTiers.ANTHEKTITE_DIAMOND,
//                ANConfig.anthektiteDiamondSwordDamage, ANConfig.anthektiteDiamondSwordAttackSpeed + anthektiteSpeed, new Item.Properties()));
//
//        //Anthektite Blocks
//        public static final RegistryObject<Item> ANTHEKTITE_IRON_BLOCK = ITEMS.register("anthektite_iron_block",
//                () -> new BlockItem(AdvancedNetheriteBlocks.ANTHEKTITE_IRON_BLOCK.get(), new Item.Properties().fireResistant()));
//        public static final RegistryObject<Item> ANTHEKTITE_GOLD_BLOCK = ITEMS.register("anthektite_gold_block",
//                () -> new BlockItem(AdvancedNetheriteBlocks.ANTHEKTITE_GOLD_BLOCK.get(), new Item.Properties().fireResistant()));
//        public static final RegistryObject<Item> ANTHEKTITE_EMERALD_BLOCK = ITEMS.register("anthektite_emerald_block",
//                () -> new BlockItem(AdvancedNetheriteBlocks.ANTHEKTITE_EMERALD_BLOCK.get(), new Item.Properties().fireResistant()));
//        public static final RegistryObject<Item> ANTHEKTITE_DIAMOND_BLOCK = ITEMS.register("anthektite_diamond_block",
//                () -> new BlockItem(AdvancedNetheriteBlocks.ANTHEKTITE_DIAMOND_BLOCK.get(), new Item.Properties().fireResistant()));
//
//
//        public static void register(IEventBus eventBus) {ITEMS.register(eventBus);}
//    }
//    public static class EpicSamuraiItems {
//        public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
//
//        public static final RegistryObject<Item> STEEL_SAMURAI_HELMET = ITEMS.register("steel_samurai_helmet",
//                () -> new ESArmorItem(ModArmorMaterials.STEEL_SAMURAI, ArmorItem.Type.HELMET, new Item.Properties().fireResistant()));
//        public static final RegistryObject<Item> STEEL_SAMURAI_CHESTPLATE = ITEMS.register("steel_samurai_chestplate",
//                () -> new ESArmorItem(ModArmorMaterials.STEEL_SAMURAI, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant()));
//        public static final RegistryObject<Item> STEEL_SAMURAI_LEGGINGS = ITEMS.register("steel_samurai_leggings",
//                () -> new ESArmorItem(ModArmorMaterials.STEEL_SAMURAI, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant()));
//        public static final RegistryObject<Item> STEEL_SAMURAI_BOOTS = ITEMS.register("steel_samurai_boots",
//                () -> new ESArmorItem(ModArmorMaterials.STEEL_SAMURAI, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant()));
//
//        public static final RegistryObject<Item> STEEL_SAMURAI_HELMET_LIGHT = ITEMS.register("steel_samurai_helmet_light",
//                () -> new ESArmorItem(ModArmorMaterials.STEEL_SAMURAI_LIGHT, ArmorItem.Type.HELMET, new Item.Properties().fireResistant()));
//        public static final RegistryObject<Item> STEEL_SAMURAI_CHESTPLATE_LIGHT = ITEMS.register("steel_samurai_chestplate_light",
//                () -> new ESArmorItem(ModArmorMaterials.STEEL_SAMURAI_LIGHT, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant()));
//        public static final RegistryObject<Item> STEEL_SAMURAI_LEGGINGS_LIGHT = ITEMS.register("steel_samurai_leggings_light",
//                () -> new ESArmorItem(ModArmorMaterials.STEEL_SAMURAI_LIGHT, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant()));
//        public static final RegistryObject<Item> STEEL_SAMURAI_BOOTS_LIGHT = ITEMS.register("steel_samurai_boots_light",
//                () -> new ESArmorItem(ModArmorMaterials.STEEL_SAMURAI_LIGHT, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant()));
//
//        public static final RegistryObject<Item> STEEL_SAMURAI_HELMET_MASTER = ITEMS.register("steel_samurai_helmet_master",
//                () -> new ESArmorItem(ModArmorMaterials.STEEL_SAMURAI_MASTER, ArmorItem.Type.HELMET, new Item.Properties().fireResistant()));
//        public static final RegistryObject<Item> STEEL_SAMURAI_CHESTPLATE_MASTER = ITEMS.register("steel_samurai_chestplate_master",
//                () -> new ESArmorItem(ModArmorMaterials.STEEL_SAMURAI_MASTER, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant()));
//        public static final RegistryObject<Item> STEEL_SAMURAI_LEGGINGS_MASTER = ITEMS.register("steel_samurai_leggings_master",
//                () -> new ESArmorItem(ModArmorMaterials.STEEL_SAMURAI_MASTER, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant()));
//        public static final RegistryObject<Item> STEEL_SAMURAI_BOOTS_MASTER = ITEMS.register("steel_samurai_boots_master",
//                () -> new ESArmorItem(ModArmorMaterials.STEEL_SAMURAI_MASTER, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant()));
//
//
//        public static final RegistryObject<Item> DIARKRITE_SAMURAI_HELMET = ITEMS.register("diarkrite_samurai_helmet",
//                () -> new ESArmorItem(ModArmorMaterials.DIARKRITE_SAMURAI, ArmorItem.Type.HELMET, new Item.Properties().fireResistant()));
//        public static final RegistryObject<Item> DIARKRITE_SAMURAI_CHESTPLATE = ITEMS.register("diarkrite_samurai_chestplate",
//                () -> new ESArmorItem(ModArmorMaterials.DIARKRITE_SAMURAI, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant()));
//        public static final RegistryObject<Item> DIARKRITE_SAMURAI_LEGGINGS = ITEMS.register("diarkrite_samurai_leggings",
//                () -> new ESArmorItem(ModArmorMaterials.DIARKRITE_SAMURAI, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant()));
//        public static final RegistryObject<Item> DIARKRITE_SAMURAI_BOOTS = ITEMS.register("diarkrite_samurai_boots",
//                () -> new ESArmorItem(ModArmorMaterials.DIARKRITE_SAMURAI, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant()));
//
//        public static final RegistryObject<Item> DIARKRITE_SAMURAI_HELMET_LIGHT = ITEMS.register("diarkrite_samurai_helmet_light",
//                () -> new ESArmorItem(ModArmorMaterials.DIARKRITE_SAMURAI_LIGHT, ArmorItem.Type.HELMET, new Item.Properties().fireResistant()));
//        public static final RegistryObject<Item> DIARKRITE_SAMURAI_CHESTPLATE_LIGHT = ITEMS.register("diarkrite_samurai_chestplate_light",
//                () -> new ESArmorItem(ModArmorMaterials.DIARKRITE_SAMURAI_LIGHT, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant()));
//        public static final RegistryObject<Item> DIARKRITE_SAMURAI_LEGGINGS_LIGHT = ITEMS.register("diarkrite_samurai_leggings_light",
//                () -> new ESArmorItem(ModArmorMaterials.DIARKRITE_SAMURAI_LIGHT, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant()));
//        public static final RegistryObject<Item> DIARKRITE_SAMURAI_BOOTS_LIGHT = ITEMS.register("diarkrite_samurai_boots_light",
//                () -> new ESArmorItem(ModArmorMaterials.DIARKRITE_SAMURAI_LIGHT, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant()));
//
//        public static final RegistryObject<Item> DIARKRITE_SAMURAI_HELMET_MASTER = ITEMS.register("diarkrite_samurai_helmet_master",
//                () -> new ESArmorItem(ModArmorMaterials.DIARKRITE_SAMURAI_MASTER, ArmorItem.Type.HELMET, new Item.Properties().fireResistant()));
//        public static final RegistryObject<Item> DIARKRITE_SAMURAI_CHESTPLATE_MASTER = ITEMS.register("diarkrite_samurai_chestplate_master",
//                () -> new ESArmorItem(ModArmorMaterials.DIARKRITE_SAMURAI_MASTER, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant()));
//        public static final RegistryObject<Item> DIARKRITE_SAMURAI_LEGGINGS_MASTER = ITEMS.register("diarkrite_samurai_leggings_master",
//                () -> new ESArmorItem(ModArmorMaterials.DIARKRITE_SAMURAI_MASTER, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant()));
//        public static final RegistryObject<Item> DIARKRITE_SAMURAI_BOOTS_MASTER = ITEMS.register("diarkrite_samurai_boots_master",
//                () -> new ESArmorItem(ModArmorMaterials.DIARKRITE_SAMURAI_MASTER, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant()));
//
//
//        public static final RegistryObject<Item> ANTHEKTITE_SAMURAI_HELMET = ITEMS.register("anthektite_samurai_helmet",
//                () -> new ESArmorItem(ModArmorMaterials.ANTHEKTITE_SAMURAI, ArmorItem.Type.HELMET, new Item.Properties().fireResistant()));
//        public static final RegistryObject<Item> ANTHEKTITE_SAMURAI_CHESTPLATE = ITEMS.register("anthektite_samurai_chestplate",
//                () -> new ESArmorItem(ModArmorMaterials.ANTHEKTITE_SAMURAI, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant()));
//        public static final RegistryObject<Item> ANTHEKTITE_SAMURAI_LEGGINGS = ITEMS.register("anthektite_samurai_leggings",
//                () -> new ESArmorItem(ModArmorMaterials.ANTHEKTITE_SAMURAI, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant()));
//        public static final RegistryObject<Item> ANTHEKTITE_SAMURAI_BOOTS = ITEMS.register("anthektite_samurai_boots",
//                () -> new ESArmorItem(ModArmorMaterials.ANTHEKTITE_SAMURAI, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant()));
//
//        public static final RegistryObject<Item> ANTHEKTITE_SAMURAI_HELMET_LIGHT = ITEMS.register("anthektite_samurai_helmet_light",
//                () -> new ESArmorItem(ModArmorMaterials.ANTHEKTITE_SAMURAI_LIGHT, ArmorItem.Type.HELMET, new Item.Properties().fireResistant()));
//        public static final RegistryObject<Item> ANTHEKTITE_SAMURAI_CHESTPLATE_LIGHT = ITEMS.register("anthektite_samurai_chestplate_light",
//                () -> new ESArmorItem(ModArmorMaterials.ANTHEKTITE_SAMURAI_LIGHT, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant()));
//        public static final RegistryObject<Item> ANTHEKTITE_SAMURAI_LEGGINGS_LIGHT = ITEMS.register("anthektite_samurai_leggings_light",
//                () -> new ESArmorItem(ModArmorMaterials.ANTHEKTITE_SAMURAI_LIGHT, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant()));
//        public static final RegistryObject<Item> ANTHEKTITE_SAMURAI_BOOTS_LIGHT = ITEMS.register("anthektite_samurai_boots_light",
//                () -> new ESArmorItem(ModArmorMaterials.ANTHEKTITE_SAMURAI_LIGHT, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant()));
//
//        public static final RegistryObject<Item> ANTHEKTITE_SAMURAI_HELMET_MASTER = ITEMS.register("anthektite_samurai_helmet_master",
//                () -> new ESArmorItem(ModArmorMaterials.ANTHEKTITE_SAMURAI_MASTER, ArmorItem.Type.HELMET, new Item.Properties().fireResistant()));
//        public static final RegistryObject<Item> ANTHEKTITE_SAMURAI_CHESTPLATE_MASTER = ITEMS.register("anthektite_samurai_chestplate_master",
//                () -> new ESArmorItem(ModArmorMaterials.ANTHEKTITE_SAMURAI_MASTER, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant()));
//        public static final RegistryObject<Item> ANTHEKTITE_SAMURAI_LEGGINGS_MASTER = ITEMS.register("anthektite_samurai_leggings_master",
//                () -> new ESArmorItem(ModArmorMaterials.ANTHEKTITE_SAMURAI_MASTER, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant()));
//        public static final RegistryObject<Item> ANTHEKTITE_SAMURAI_BOOTS_MASTER = ITEMS.register("anthektite_samurai_boots_master",
//                () -> new ESArmorItem(ModArmorMaterials.ANTHEKTITE_SAMURAI_MASTER, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant()));
//
//        public static void register(IEventBus eventBus) {ITEMS.register(eventBus);}
//    }
//    public static class TwigsItems {
//        public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
//
//        public static final RegistryObject<Item> MOVCADIA_TABLE = ITEMS.register("movcadia_table",
//                () -> new BlockItem(TwigsBlocks.MOVCADIA_TABLE.get(), new Item.Properties().fireResistant()));
//        public static void register(IEventBus eventBus) {ITEMS.register(eventBus);}
//    }
//    public static class WitherstormModItems {
//        public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
//
//        public static final RegistryObject<Item> STEEL_CMD_SWORD = ITEMS.register("steel_command_block_sword", () -> new ModSwordItem(ModTiers.STEEL_CMD,
//                WSConfig.steelCMDSwordDamage, (float) WSConfig.steelCMDSwordAttackSpeed + steelSpeed, new Item.Properties()));
//        public static final RegistryObject<Item> STEEL_CMD_SHOVEL = ITEMS.register("steel_command_block_shovel", () -> new ModShovelItem(ModTiers.STEEL_CMD,
//                (float) WSConfig.steelCMDShovelDamage, (float) WSConfig.steelCMDShovelAttackSpeed, new Item.Properties()));
//        public static final RegistryObject<Item> STEEL_CMD_PICKAXE = ITEMS.register("steel_command_block_pickaxe", () -> new ModPickaxeItem(ModTiers.STEEL_CMD,
//                WSConfig.steelCMDPickaxeDamage, (float) WSConfig.steelCMDPickaxeAttackSpeed, new Item.Properties()));
//        public static final RegistryObject<Item> STEEL_CMD_AXE = ITEMS.register("steel_command_block_axe", () -> new ModAxeItem(ModTiers.STEEL_CMD,
//                WSConfig.steelCMDAxeDamage, (float) WSConfig.steelCMDAxeAttackSpeed + steelSpeed, new Item.Properties()));
//        public static final RegistryObject<Item> STEEL_CMD_HOE = ITEMS.register("steel_command_block_hoe", () -> new ModHoeItem(ModTiers.STEEL_CMD,
//                WSConfig.steelCMDHoeDamage, (float) WSConfig.steelCMDHoeAttackSpeed, new Item.Properties()));
//
//
//        public static final RegistryObject<Item> DIARKRITE_CMD_SWORD = ITEMS.register("diarkrite_command_block_sword", () -> new ModSwordItem(ModTiers.DIARKRITE_CMD,
//                WSConfig.diarkriteCMDSwordDamage, (float) WSConfig.diarkriteCMDSwordAttackSpeed + diarkriteSpeed, new Item.Properties()));
//        public static final RegistryObject<Item> DIARKRITE_CMD_SHOVEL = ITEMS.register("diarkrite_command_block_shovel", () -> new ModShovelItem(ModTiers.DIARKRITE_CMD,
//                (float) WSConfig.diarkriteCMDShovelDamage, (float) WSConfig.diarkriteCMDShovelAttackSpeed, new Item.Properties()));
//        public static final RegistryObject<Item> DIARKRITE_CMD_PICKAXE = ITEMS.register("diarkrite_command_block_pickaxe", () -> new ModPickaxeItem(ModTiers.DIARKRITE_CMD,
//                WSConfig.diarkriteCMDPickaxeDamage, (float) WSConfig.diarkriteCMDPickaxeAttackSpeed, new Item.Properties()));
//        public static final RegistryObject<Item> DIARKRITE_CMD_AXE = ITEMS.register("diarkrite_command_block_axe", () -> new ModAxeItem(ModTiers.DIARKRITE_CMD,
//                WSConfig.diarkriteCMDAxeDamage, (float) WSConfig.diarkriteCMDAxeAttackSpeed + diarkriteSpeed, new Item.Properties()));
//        public static final RegistryObject<Item> DIARKRITE_CMD_HOE = ITEMS.register("diarkrite_command_block_hoe", () -> new ModHoeItem(ModTiers.DIARKRITE_CMD,
//                WSConfig.diarkriteCMDHoeDamage, (float) WSConfig.diarkriteCMDHoeAttackSpeed, new Item.Properties()));
//
//
//        public static final RegistryObject<Item> ANTHEKTITE_CMD_SWORD = ITEMS.register("anthektite_command_block_sword", () -> new ModSwordItem(ModTiers.ANTHEKTITE_CMD,
//                WSConfig.anthektiteCMDSwordDamage, (float) WSConfig.anthektiteCMDSwordAttackSpeed + anthektiteSpeed, new Item.Properties()));
//        public static final RegistryObject<Item> ANTHEKTITE_CMD_SHOVEL = ITEMS.register("anthektite_command_block_shovel", () -> new ModShovelItem(ModTiers.ANTHEKTITE_CMD,
//                (float) WSConfig.anthektiteCMDShovelDamage, (float) WSConfig.anthektiteCMDShovelAttackSpeed, new Item.Properties()));
//        public static final RegistryObject<Item> ANTHEKTITE_CMD_PICKAXE = ITEMS.register("anthektite_command_block_pickaxe", () -> new ModPickaxeItem(ModTiers.ANTHEKTITE_CMD,
//                WSConfig.anthektiteCMDPickaxeDamage, (float) WSConfig.anthektiteCMDPickaxeAttackSpeed, new Item.Properties()));
//        public static final RegistryObject<Item> ANTHEKTITE_CMD_AXE = ITEMS.register("anthektite_command_block_axe", () -> new ModAxeItem(ModTiers.ANTHEKTITE_CMD,
//                WSConfig.anthektiteCMDAxeDamage, (float) WSConfig.anthektiteCMDAxeAttackSpeed + anthektiteSpeed, new Item.Properties()));
//        public static final RegistryObject<Item> ANTHEKTITE_CMD_HOE = ITEMS.register("anthektite_command_block_hoe", () -> new ModHoeItem(ModTiers.ANTHEKTITE_CMD,
//                WSConfig.anthektiteCMDHoeDamage, (float) WSConfig.anthektiteCMDHoeAttackSpeed, new Item.Properties()));
//
//        public static void register(IEventBus eventBus) {
//            ITEMS.register(eventBus);
//        }
//    }
//    public static class BanillaClawsItems {
//        public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
//
//        public static final RegistryObject<Item> STEEL_CLAWS = ITEMS.register("steel_claws", () -> new ClawsItem(ModTiers.STEEL_CLAW,
//                ItemConfig.steelClawDamage, (float) ItemConfig.steelClawAttackSpeed + steelSpeed, new Item.Properties()));
//        public static final RegistryObject<Item> DIARKRITE_CLAWS = ITEMS.register("diarkrite_claws", () -> new ClawsItem(ModTiers.DIARKRITE_CLAW,
//                ItemConfig.diarkriteClawDamage, (float) ItemConfig.diarkriteClawAttackSpeed + diarkriteSpeed, new Item.Properties()));
//        public static final RegistryObject<Item> ANTHEKTITE_CLAWS = ITEMS.register("anthektite_claws", () -> new ClawsItem(ModTiers.ANTHEKTITE_CLAW,
//                ItemConfig.anthektiteClawDamage, (float) ItemConfig.anthektiteClawAttackSpeed + anthektiteSpeed, new Item.Properties()));
//
//        public static void register(IEventBus eventBus) {
//            ITEMS.register(eventBus);
//        }
//    }

    public static void register(IEventBus eventBus) {
        ElementusItems.ITEMS.register(eventBus);
//        if (farmersDelight) {
//            FarmersDelightItems.ITEMS.register(eventBus);
//        }
//        if (piercingPaxels) {
//            PiercingPaxelsItems.ITEMS.register(eventBus);
//        }
//        if (nethersDelight) {
//            NethersDelightItems.ITEMS.register(eventBus);
//        }
//        if (ironsSpellbooks) {
//            IronsSpellbooksItems.ITEMS.register(eventBus);
//        }
//        if (aether) {
//            AetherItems.ITEMS.register(eventBus);
//        }
//        if (simplySwords) {
//            SimplySwordsItems.ITEMS.register(eventBus);
//        }
//        if (sniffsWeapons) {
//            SniffsWeaponsItems.ITEMS.register(eventBus);
//        }
//        if (advancedNetherite) {
//            AdvancedNetheriteItems.ITEMS.register(eventBus);
//        }
//        if (samuraiDynasty) {
//            EpicSamuraiItems.ITEMS.register(eventBus);
//        }
//        if (twigs) {
//            TwigsItems.ITEMS.register(eventBus);
//        }
//        if (witherStormMod) {
//            WitherstormModItems.ITEMS.register(eventBus);
//        }
//        if (vanillaClaws) {
//            BanillaClawsItems.ITEMS.register(eventBus);
//        }
    }
}
