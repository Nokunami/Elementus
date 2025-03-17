package net.nokunami.elementus.common.registry;

import com.google.common.collect.ImmutableMultimap;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import io.redspace.ironsspellbooks.item.spell_books.SimpleAttributeSpellBook;
import net.dakotapride.vanilla_claws.item.ClawsItem;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nokunami.elementus.common.compat.advancednetherite.item.*;
import net.nokunami.elementus.common.compat.epicsamurai.ESArmorItem;
import net.nokunami.elementus.common.compat.ironsspellbooks.ISSArmorItem;
import net.nokunami.elementus.common.compat.ironsspellbooks.MagicArmorMaterial;
import net.nokunami.elementus.common.compat.piercingpaxels.EPaxelItem;
import net.nokunami.elementus.common.compat.simplyswords.SimplySwordItem;
import net.nokunami.elementus.common.compat.sniffsweapons.*;
import net.nokunami.elementus.common.compat.theaether.AttributeGlovesItem;
import net.nokunami.elementus.common.config.TierConfig;
import net.nokunami.elementus.common.config.ItemConfig;
import net.nokunami.elementus.common.entity.vehicle.ModBoatEntity;
import net.nokunami.elementus.common.item.*;
import net.nokunami.elementus.common.registry.ModBlocks.*;
import nl.sniffiandros.sniffsweapons.item.GreatAxeItem;
import nl.sniffiandros.sniffsweapons.item.GreatSwordItem;
import nl.sniffiandros.sniffsweapons.item.NaginataItem;
import umpaz.nethersdelight.common.item.MacheteItem;
import vectorwing.farmersdelight.common.item.KnifeItem;

import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.UUID;

import static net.nokunami.elementus.Elementus.MODID;
import static net.nokunami.elementus.ModChecker.*;

public class ModItems {
    public static float steelSpeed = (float) TierConfig.steelWeaponSpeedModifier;
    public static float diarkriteSpeed = (float) TierConfig.diarkriteWeaponSpeedModifier;
    public static float anthektiteSpeed = (float) TierConfig.anthektiteWeaponSpeedModifier;

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
                        .nutrition(4).saturationMod(0.4F)
                        .effect(new MobEffectInstance(MobEffects.DIG_SPEED, 600), 1)
                        .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 600), 1)
                        .fast()
                        .build())));
        public static final RegistryObject<Item> MOVCADIA_ESSENCE = ITEMS.register("movcadia_essence",
                () -> new Item(new Item.Properties().fireResistant()));

        public static final RegistryObject<Item> CRUSHED_REMNANT = ITEMS.register("crushed_remnant",
                () -> new Item(new Item.Properties().fireResistant()));

        public static final RegistryObject<Item> STEEL_GOLEM_SPAWN_EGG = ITEMS.register("steel_golem_spawn_egg",
                () -> new ForgeSpawnEggItem(ModEntityType.STEEL_GOLEM, 14144729, 7238279, new Item.Properties()));

        public static final RegistryObject<Item> STEEL_SWORD = ITEMS.register("steel_sword", () -> new ModSwordItem(ModTiers.STEEL,
                ItemConfig.steelSwordDamage, ItemConfig.steelSwordAttackSpeed + steelSpeed, new Item.Properties()));

        public static final RegistryObject<Item> STEEL_SHOVEL = ITEMS.register("steel_shovel", () -> new ShovelItem(ModTiers.STEEL,
                ItemConfig.steelShovelDamage, ItemConfig.steelShovelAttackSpeed, new Item.Properties()));

        public static final RegistryObject<Item> STEEL_PICKAXE = ITEMS.register("steel_pickaxe", () -> new PickaxeItem(ModTiers.STEEL,
                ItemConfig.steelPickaxeDamage, ItemConfig.steelPickaxeAttackSpeed, new Item.Properties()));

        public static final RegistryObject<Item> STEEL_AXE = ITEMS.register("steel_axe", () -> new AxeItem(ModTiers.STEEL,
                ItemConfig.steelAxeDamage, ItemConfig.steelAxeAttackSpeed + steelSpeed, new Item.Properties()));

        public static final RegistryObject<Item> STEEL_HOE = ITEMS.register("steel_hoe", () -> new HoeItem(ModTiers.STEEL,
                ItemConfig.steelHoeDamage, ItemConfig.steelHoeAttackSpeed, new Item.Properties()));


        public static final RegistryObject<Item> DIARKRITE_SWORD = ITEMS.register("diarkrite_sword", () -> new ModSwordItem(ModTiers.DIARKRITE,
                ItemConfig.diarkriteSwordDamage, ItemConfig.diarkriteSwordAttackSpeed + diarkriteSpeed, new Item.Properties().fireResistant()));

        public static final RegistryObject<Item> DIARKRITE_SHOVEL = ITEMS.register("diarkrite_shovel", () -> new ShovelItem(ModTiers.DIARKRITE,
                ItemConfig.diarkriteShovelDamage, ItemConfig.diarkriteShovelAttackSpeed, new Item.Properties().fireResistant()));

        public static final RegistryObject<Item> DIARKRITE_PICKAXE = ITEMS.register("diarkrite_pickaxe", () -> new ModPickaxeItem(ModTiers.DIARKRITE,
                ItemConfig.diarkritePickaxeDamage, ItemConfig.diarkritePickaxeAttackSpeed, new Item.Properties().fireResistant()));

        public static final RegistryObject<Item> DIARKRITE_AXE = ITEMS.register("diarkrite_axe", () -> new AxeItem(ModTiers.DIARKRITE,
                ItemConfig.diarkriteAxeDamage, ItemConfig.diarkriteAxeAttackSpeed + diarkriteSpeed, new Item.Properties().fireResistant()));

        public static final RegistryObject<Item> DIARKRITE_HOE = ITEMS.register("diarkrite_hoe", () -> new HoeItem(ModTiers.DIARKRITE,
                ItemConfig.diarkriteHoeDamage, ItemConfig.diarkriteHoeAttackSpeed, new Item.Properties().fireResistant()));


        public static final RegistryObject<Item> ANTHEKTITE_SWORD = ITEMS.register("anthektite_sword", () -> new ModSwordItem(ModTiers.ANTHEKTITE,
                ItemConfig.anthektiteSwordDamage, ItemConfig.anthektiteSwordAttackSpeed + anthektiteSpeed, new Item.Properties().fireResistant()));

        public static final RegistryObject<Item> ANTHEKTITE_SHOVEL = ITEMS.register("anthektite_shovel", () -> new ShovelItem(ModTiers.ANTHEKTITE,
                ItemConfig.anthektiteShovelDamage, ItemConfig.anthektiteShovelAttackSpeed, new Item.Properties().fireResistant()));

        public static final RegistryObject<Item> ANTHEKTITE_PICKAXE = ITEMS.register("anthektite_pickaxe", () -> new ModPickaxeItem(ModTiers.ANTHEKTITE,
                ItemConfig.anthektitePickaxeDamage, ItemConfig.anthektitePickaxeAttackSpeed, new Item.Properties().fireResistant()));

        public static final RegistryObject<Item> ANTHEKTITE_AXE = ITEMS.register("anthektite_axe", () -> new AxeItem(ModTiers.ANTHEKTITE,
                ItemConfig.anthektiteAxeDamage, ItemConfig.anthektiteAxeAttackSpeed + anthektiteSpeed, new Item.Properties().fireResistant()));

        public static final RegistryObject<Item> ANTHEKTITE_HOE = ITEMS.register("anthektite_hoe", () -> new HoeItem(ModTiers.ANTHEKTITE,
                ItemConfig.anthektiteHoeDamage, ItemConfig.anthektiteHoeAttackSpeed, new Item.Properties().fireResistant()));

        public static final RegistryObject<Item> DIARKRITE_CHARGE_BLADE = ITEMS.register("diarkrite_charge_blade", DiarkriteChargeBlade::new);

        public static final RegistryObject<Item> STEEL_SHIELD = ITEMS.register("steel_shield",
                () -> new ShieldItem(new Item.Properties().defaultDurability(ItemConfig.steelShieldDurability)));
        public static final RegistryObject<Item> DIARKRITE_SHIELD = ITEMS.register("diarkrite_shield",
                () -> new ShieldItem(new Item.Properties().defaultDurability(ItemConfig.diarkriteShieldDurability).fireResistant()));
        public static final RegistryObject<Item> ANTHEKTITE_SHIELD = ITEMS.register("anthektite_shield",
                () -> new ShieldItem(new Item.Properties().defaultDurability(ItemConfig.anthektiteShieldDurability).fireResistant()));

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
                () -> new CatalystArmorItem(ModArmorMaterials.CATALYST, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant()));

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

//        public static final RegistryObject<Item> MOVCADIA_ROOTS = ITEMS.register("movcadia_roots",
//                () -> new BlockItem(ElementusBlocks.MOVCADIA_ROOTS.get(), new Item.Properties()));
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

    public static class FarmersDelightItems {
        public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

        public static final RegistryObject<Item> STEEL_KNIFE = ITEMS.register("steel_knife", () -> new KnifeItem(ModTiers.STEEL,
                ItemConfig.steelKnifeDamage, ItemConfig.steelKnifeAttackSpeed + steelSpeed, new Item.Properties()));
        public static final RegistryObject<Item> DIARKRITE_KNIFE = ITEMS.register("diarkrite_knife", () -> new KnifeItem(ModTiers.DIARKRITE,
                ItemConfig.diarkriteKnifeDamage, ItemConfig.diarkriteKnifeAttackSpeed + diarkriteSpeed, new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> ANTHEKTITE_KNIFE = ITEMS.register("anthektite_knife", () -> new KnifeItem(ModTiers.ANTHEKTITE,
                ItemConfig.anthektiteKnifeDamage, ItemConfig.anthektiteKnifeAttackSpeed - anthektiteSpeed, new Item.Properties().fireResistant()));

        public static final RegistryObject<Item> MOVCADIA_CABINET = ITEMS.register("movcadia_cabinet",
                () -> new BlockItem(FarmersDelightBlocks.MOVCADIA_CABINET.get(), new Item.Properties().fireResistant()));

        public static void register(IEventBus eventBus) {ITEMS.register(eventBus);}
    }
    public static class PiercingPaxelsItems {
        public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

        public static final RegistryObject<Item> STEEL_PAXEL = ITEMS.register("steel_paxel", () -> new EPaxelItem(ModTiers.STEEL,
                ItemConfig.steelPaxelDamage, ItemConfig.steelPaxelAttackSpeed + steelSpeed, new Item.Properties().rarity(Rarity.UNCOMMON)));
        public static final RegistryObject<Item> DIARKRITE_PAXEL = ITEMS.register("diarkrite_paxel", () -> new EPaxelItem(ModTiers.DIARKRITE,
                ItemConfig.diarkritePaxelDamage, ItemConfig.diarkritePaxelAttackSpeed + diarkriteSpeed, new Item.Properties().rarity(Rarity.RARE).fireResistant()));
        public static final RegistryObject<Item> ANTHEKTITE_PAXEL = ITEMS.register("anthektite_paxel", () -> new EPaxelItem(ModTiers.ANTHEKTITE,
                ItemConfig.anthektiteKnifeDamage, ItemConfig.anthektitePaxelAttackSpeed + anthektiteSpeed, new Item.Properties().rarity(Rarity.RARE).fireResistant()));

        public static final RegistryObject<Item> ANTHEKTITE_UPGRADE_KIT = ITEMS.register("anthektite_paxel_upgrade_kit",
                () -> new Item(new Item.Properties().rarity(Rarity.RARE).fireResistant()));
        public static final RegistryObject<Item> DIARKRITE_UPGRADE_KIT = ITEMS.register("diarkrite_paxel_upgrade_kit",
                () -> new Item(new Item.Properties().rarity(Rarity.RARE).fireResistant()));

        public static void register(IEventBus eventBus) {ITEMS.register(eventBus);}
    }
    public static class NethersDelightItems {
        public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

        public static final RegistryObject<Item> STEEL_MACHETE = ITEMS.register("steel_machete", () -> new MacheteItem(ModTiers.STEEL,
                ItemConfig.steelMacheteDamage, ItemConfig.steelMacheteAttackSpeed + steelSpeed, new Item.Properties()));
        public static final RegistryObject<Item> DIARKRITE_MACHETE = ITEMS.register("diarkrite_machete", () -> new MacheteItem(ModTiers.DIARKRITE,
                ItemConfig.diarkriteMacheteDamage, ItemConfig.diarkriteMacheteAttackSpeed + diarkriteSpeed, new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> ANTHEKTITE_MACHETE = ITEMS.register("anthektite_machete", () -> new MacheteItem(ModTiers.ANTHEKTITE,
                ItemConfig.anthektiteMacheteDamage, ItemConfig.anthektiteMacheteAttackSpeed + anthektiteSpeed, new Item.Properties().fireResistant()));

        public static void register(IEventBus eventBus) {ITEMS.register(eventBus);}
    }
    public static class IronsSpellbooksItems {
        public static Collection<RegistryObject<Item>> getISSCompatItems() {
            return ITEMS.getEntries();
        }
        public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

        public static final RegistryObject<Item> STEEL_SPELL_BOOK = ITEMS.register("steel_spell_book", () -> {
            ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
            builder.put(AttributeRegistry.MAX_MANA.get(), new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Max Mana", ItemConfig.steelSpellbookMana, AttributeModifier.Operation.ADDITION));
            builder.put(AttributeRegistry.COOLDOWN_REDUCTION.get(), new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Cooldown Reduction", ItemConfig.steelSpellbookCooldown, AttributeModifier.Operation.MULTIPLY_BASE));
            builder.put(AttributeRegistry.CAST_TIME_REDUCTION.get(), new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Cast Time Reduction", ItemConfig.steelSpellbookCastTime, AttributeModifier.Operation.MULTIPLY_BASE));
            builder.put(AttributeRegistry.SPELL_POWER.get(), new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Spell Power", ItemConfig.steelSpellbookSpellPower, AttributeModifier.Operation.MULTIPLY_BASE));
            return new SimpleAttributeSpellBook(ItemConfig.steelSpellbookSlot, SpellRarity.RARE, builder.build());
        });
        public static final RegistryObject<Item> DIARKRITE_SPELL_BOOK = ITEMS.register("diarkrite_spell_book", () -> {
            ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
            builder.put(AttributeRegistry.MAX_MANA.get(), new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Max Mana", ItemConfig.diarkriteSpellbookMana, AttributeModifier.Operation.ADDITION));
            builder.put(AttributeRegistry.COOLDOWN_REDUCTION.get(), new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Cooldown Reduction", ItemConfig.diarkriteSpellbookCooldown, AttributeModifier.Operation.MULTIPLY_BASE));
            builder.put(AttributeRegistry.CAST_TIME_REDUCTION.get(), new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Cast Time Reduction", ItemConfig.diarkriteSpellbookCastTime, AttributeModifier.Operation.MULTIPLY_BASE));
            builder.put(AttributeRegistry.SPELL_POWER.get(), new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Spell Power", ItemConfig.diarkriteSpellbookSpellPower, AttributeModifier.Operation.MULTIPLY_BASE));
            return new SimpleAttributeSpellBook(ItemConfig.diarkriteSpellbookSlot, SpellRarity.LEGENDARY, builder.build());
        });
        public static final RegistryObject<Item> ANTHEKTITE_SPELL_BOOK = ITEMS.register("anthektite_spell_book", () -> {
            ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
            builder.put(AttributeRegistry.MAX_MANA.get(), new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Max Mana", ItemConfig.anthektiteSpellbookMana, AttributeModifier.Operation.ADDITION));
            builder.put(AttributeRegistry.COOLDOWN_REDUCTION.get(), new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Cooldown Reduction", ItemConfig.anthektiteSpellbookCooldown, AttributeModifier.Operation.MULTIPLY_BASE));
            builder.put(AttributeRegistry.CAST_TIME_REDUCTION.get(), new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Cast Time Reduction", ItemConfig.anthektiteSpellbookCastTime, AttributeModifier.Operation.MULTIPLY_BASE));
            builder.put(AttributeRegistry.SPELL_POWER.get(), new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Spell Power", ItemConfig.anthektiteSpellbookSpellPower, AttributeModifier.Operation.MULTIPLY_BASE));
            return new SimpleAttributeSpellBook(ItemConfig.anthektiteSpellbookSlot, SpellRarity.LEGENDARY, builder.build());
        });

        public static final RegistryObject<ArmorItem> DIARKRITE_MAGE_HELMET = ITEMS.register("diarkrite_mage_helmet",
                () -> new ISSArmorItem(MagicArmorMaterial.DIARKRITE_MAGE, ArmorItem.Type.HELMET, new Item.Properties().fireResistant()));
        public static final RegistryObject<ArmorItem> DIARKRITE_MAGE_CHESTPLATE = ITEMS.register("diarkrite_mage_chestplate",
                () -> new ISSArmorItem(MagicArmorMaterial.DIARKRITE_MAGE, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant()));
        public static final RegistryObject<ArmorItem> DIARKRITE_MAGE_LEGGINGS = ITEMS.register("diarkrite_mage_leggings",
                () -> new ISSArmorItem(MagicArmorMaterial.DIARKRITE_MAGE, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant()));
        public static final RegistryObject<ArmorItem> DIARKRITE_MAGE_BOOTS = ITEMS.register("diarkrite_mage_boots",
                () -> new ISSArmorItem(MagicArmorMaterial.DIARKRITE_MAGE, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant()));

        public static final RegistryObject<ArmorItem> ANTHEKTITE_MAGE_HELMET = ITEMS.register("anthektite_mage_helmet",
                () -> new ISSArmorItem(MagicArmorMaterial.ANTHEKTITE_MAGE, ArmorItem.Type.HELMET, new Item.Properties().fireResistant()));
        public static final RegistryObject<ArmorItem> ANTHEKTITE_MAGE_CHESTPLATE = ITEMS.register("anthektite_mage_chestplate",
                () -> new ISSArmorItem(MagicArmorMaterial.ANTHEKTITE_MAGE, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant()));
        public static final RegistryObject<ArmorItem> ANTHEKTITE_MAGE_LEGGINGS = ITEMS.register("anthektite_mage_leggings",
                () -> new ISSArmorItem(MagicArmorMaterial.ANTHEKTITE_MAGE, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant()));
        public static final RegistryObject<ArmorItem> ANTHEKTITE_MAGE_BOOTS = ITEMS.register("anthektite_mage_boots",
                () -> new ISSArmorItem(MagicArmorMaterial.ANTHEKTITE_MAGE, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant()));

        public static void register(IEventBus eventBus) {ITEMS.register(eventBus);}
    }
    public static class AetherItems {
        public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

        public static final RegistryObject<Item> STEEL_GLOVES = ITEMS.register("steel_gloves", () -> {
            ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
            builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Weapon modifier", ItemConfig.steelGloveMovementSpeed, AttributeModifier.Operation.MULTIPLY_BASE));
            builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Weapon modifier", ItemConfig.steelGloveAttackSpeed, AttributeModifier.Operation.MULTIPLY_BASE));
            builder.put(Attributes.ARMOR, new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Weapon modifier", ItemConfig.steelGloveArmorBonus, AttributeModifier.Operation.MULTIPLY_BASE));
            builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Weapon modifier", ItemConfig.steelGloveToughnessBonus, AttributeModifier.Operation.MULTIPLY_BASE));
            return new AttributeGlovesItem(ModArmorMaterials.STEEL, ItemConfig.steelGloveDamage, "steel_gloves", () -> SoundEvents.ARMOR_EQUIP_IRON,
                    new Item.Properties().defaultDurability(ModTiers.STEEL.getUses()), builder.build());
        });

        public static final RegistryObject<Item> DIARKRITE_GLOVES = ITEMS.register("diarkrite_gloves", () -> {
            ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
            builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Weapon modifier", ItemConfig.diarkriteGloveMovementSpeed, AttributeModifier.Operation.MULTIPLY_BASE));
            builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Weapon modifier", ItemConfig.diarkriteGloveAttackSpeed, AttributeModifier.Operation.MULTIPLY_BASE));
            builder.put(Attributes.ARMOR, new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Weapon modifier", ItemConfig.diarkriteGloveArmorBonus, AttributeModifier.Operation.MULTIPLY_BASE));
            builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Weapon modifier", ItemConfig.diarkriteGloveToughnessBonus, AttributeModifier.Operation.MULTIPLY_BASE));
            return new AttributeGlovesItem(ModArmorMaterials.DIARKRITE, ItemConfig.diarkriteGloveDamage, "diarkrite_gloves", () -> SoundEvents.ARMOR_EQUIP_NETHERITE,
                    new Item.Properties().defaultDurability(ModTiers.DIARKRITE.getUses()).fireResistant(), builder.build());
        });

        public static final RegistryObject<Item> ANTHEKTITE_GLOVES = ITEMS.register("anthektite_gloves", () -> {
            ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
            builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Weapon modifier", ItemConfig.anthektiteGloveMovementSpeed, AttributeModifier.Operation.MULTIPLY_BASE));
            builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Weapon modifier", ItemConfig.anthektiteGloveAttackSpeed, AttributeModifier.Operation.MULTIPLY_BASE));
            builder.put(Attributes.ARMOR, new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Weapon modifier", ItemConfig.anthektiteGloveArmorBonus, AttributeModifier.Operation.MULTIPLY_BASE));
            builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Weapon modifier", ItemConfig.anthektiteGloveToughnessBonus, AttributeModifier.Operation.MULTIPLY_BASE));
            return new AttributeGlovesItem(ModArmorMaterials.DIARKRITE, ItemConfig.anthektiteGloveDamage, "anthektite_gloves", () -> SoundEvents.ARMOR_EQUIP_NETHERITE,
                    new Item.Properties().defaultDurability(ModTiers.ANTHEKTITE.getUses()).fireResistant(), builder.build());
        });

        public static void register(IEventBus eventBus) {
            ITEMS.register(eventBus);
        }
    }
    public static class SimplySwordsItems {
        public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

        static int steel_modifier = ItemConfig.simplySwordsSteelDamage;
        static int diarkrite_modifier = ItemConfig.simplySwordsDiarkriteDamage;
        static int anthektite_modifier = ItemConfig.simplySwordsAnthektiteDamage;

        static int longsword_positive_modifier = ItemConfig.longsword_damageModifier;
        static int twinblade_positive_modifier = ItemConfig.twinblade_damageModifier;
        static int rapier_positive_modifier = ItemConfig.rapier_damageModifier;
        static int katana_positive_modifier = ItemConfig.katana_damageModifier;
        static int sai_positive_modifier = ItemConfig.sai_damageModifier;
        static int spear_positive_modifier = ItemConfig.spear_damageModifier;
        static int glaive_positive_modifier = ItemConfig.glaive_damageModifier;
        static int warglaive_positive_modifier = ItemConfig.warglaive_damageModifier;
        static int cutlass_positive_modifier = ItemConfig.cutlass_damageModifier;
        static int claymore_positive_modifier = ItemConfig.claymore_damageModifier;
        static int greataxe_positive_modifier = ItemConfig.greataxe_damageModifier;
        static int greathammer_positive_modifier = ItemConfig.greathammer_damageModifier;
        static int chakram_positive_modifier = ItemConfig.chakram_damageModifier;
        static int scythe_positive_modifier = ItemConfig.scythe_damageModifier;
        static int halberd_positive_modifier = ItemConfig.halberd_damageModifier;

        static float longsword_attackspeed = ItemConfig.longsword_attackSpeed;
        static float twinblade_attackspeed = ItemConfig.twinblade_attackSpeed;
        static float rapier_attackspeed = ItemConfig.rapier_attackSpeed;
        static float sai_attackspeed = ItemConfig.sai_attackSpeed;
        static float spear_attackspeed = ItemConfig.spear_attackSpeed;
        static float katana_attackspeed = ItemConfig.katana_attackSpeed;
        static float glaive_attackspeed = ItemConfig.glaive_attackSpeed;
        static float warglaive_attackspeed = ItemConfig.warglaive_attackSpeed;
        static float cutlass_attackspeed = ItemConfig.cutlass_attackSpeed;
        static float claymore_attackspeed = ItemConfig.claymore_attackSpeed;
        static float greataxe_attackspeed = ItemConfig.greataxe_attackSpeed;
        static float greathammer_attackspeed = ItemConfig.greathammer_attackSpeed;
        static float chakram_attackspeed = ItemConfig.chakram_attackSpeed;
        static float scythe_attackspeed = ItemConfig.scythe_attackSpeed;
        static float halberd_attackspeed = ItemConfig.halberd_attackSpeed;


        public static final RegistryObject<Item> STEEL_LONGSWORD = registerSteel("longsword");
        public static final RegistryObject<Item> STEEL_TWINBLADE = registerSteel("twinblade");
        public static final RegistryObject<Item> STEEL_RAPIER = registerSteel("rapier");
        public static final RegistryObject<Item> STEEL_SAI = registerSteel("sai");
        public static final RegistryObject<Item> STEEL_SPEAR = registerSteel("spear");
        public static final RegistryObject<Item> STEEL_KATANA = registerSteel("katana");
        public static final RegistryObject<Item> STEEL_GLAIVE = registerSteel("glaive");
        public static final RegistryObject<Item> STEEL_WARGLAIVE = registerSteel("warglaive");
        public static final RegistryObject<Item> STEEL_CUTLASS = registerSteel("cutlass");
        public static final RegistryObject<Item> STEEL_CLAYMORE = registerSteel("claymore");
        public static final RegistryObject<Item> STEEL_GREATAXE = registerSteel("greataxe");
        public static final RegistryObject<Item> STEEL_GREATHAMMER = registerSteel("greathammer");
        public static final RegistryObject<Item> STEEL_CHAKRAM = registerSteel("chakram");
        public static final RegistryObject<Item> STEEL_SCYTHE = registerSteel("scythe");
        public static final RegistryObject<Item> STEEL_HALBERD = registerSteel("halberd");

        public static final RegistryObject<Item> DIARKRITE_LONGSWORD = registerDiarkrite("longsword");
        public static final RegistryObject<Item> DIARKRITE_TWINBLADE = registerDiarkrite("twinblade");
        public static final RegistryObject<Item> DIARKRITE_RAPIER = registerDiarkrite("rapier");
        public static final RegistryObject<Item> DIARKRITE_SAI = registerDiarkrite("sai");
        public static final RegistryObject<Item> DIARKRITE_SPEAR = registerDiarkrite("spear");
        public static final RegistryObject<Item> DIARKRITE_KATANA = registerDiarkrite("katana");
        public static final RegistryObject<Item> DIARKRITE_GLAIVE = registerDiarkrite("glaive");
        public static final RegistryObject<Item> DIARKRITE_WARGLAIVE = registerDiarkrite("warglaive");
        public static final RegistryObject<Item> DIARKRITE_CUTLASS = registerDiarkrite("cutlass");
        public static final RegistryObject<Item> DIARKRITE_CLAYMORE = registerDiarkrite("claymore");
        public static final RegistryObject<Item> DIARKRITE_GREATAXE = registerDiarkrite("greataxe");
        public static final RegistryObject<Item> DIARKRITE_GREATHAMMER = registerDiarkrite("greathammer");
        public static final RegistryObject<Item> DIARKRITE_CHAKRAM = registerDiarkrite("chakram");
        public static final RegistryObject<Item> DIARKRITE_SCYTHE = registerDiarkrite("scythe");
        public static final RegistryObject<Item> DIARKRITE_HALBERD = registerDiarkrite("halberd");

        public static final RegistryObject<Item> ANTHEKTITE_LONGSWORD = registerAnthektite("longsword");
        public static final RegistryObject<Item> ANTHEKTITE_TWINBLADE = registerAnthektite("twinblade");
        public static final RegistryObject<Item> ANTHEKTITE_RAPIER = registerAnthektite("rapier");
        public static final RegistryObject<Item> ANTHEKTITE_SAI = registerAnthektite("sai");
        public static final RegistryObject<Item> ANTHEKTITE_SPEAR = registerAnthektite("spear");
        public static final RegistryObject<Item> ANTHEKTITE_KATANA = registerAnthektite("katana");
        public static final RegistryObject<Item> ANTHEKTITE_GLAIVE = registerAnthektite("glaive");
        public static final RegistryObject<Item> ANTHEKTITE_WARGLAIVE = registerAnthektite("warglaive");
        public static final RegistryObject<Item> ANTHEKTITE_CUTLASS = registerAnthektite("cutlass");
        public static final RegistryObject<Item> ANTHEKTITE_CLAYMORE = registerAnthektite("claymore");
        public static final RegistryObject<Item> ANTHEKTITE_GREATAXE = registerAnthektite("greataxe");
        public static final RegistryObject<Item> ANTHEKTITE_GREATHAMMER = registerAnthektite("greathammer");
        public static final RegistryObject<Item> ANTHEKTITE_CHAKRAM = registerAnthektite("chakram");
        public static final RegistryObject<Item> ANTHEKTITE_SCYTHE = registerAnthektite("scythe");
        public static final RegistryObject<Item> ANTHEKTITE_HALBERD = registerAnthektite("halberd");


        private static RegistryObject<Item> registerSteel(String id) {
            return ITEMS.register(ModTiers.STEEL.toString().toLowerCase(Locale.ROOT) + "_" + id, ()->
                    new SimplySwordItem(ModTiers.STEEL, (steel_modifier + getDamageMod(id)),
                            getAttackSpeedMod(id) + steelSpeed, new Item.Properties()));
        }

        private static RegistryObject<Item> registerDiarkrite(String id) {
            return ITEMS.register(ModTiers.DIARKRITE.toString().toLowerCase(Locale.ROOT) + "_" + id, ()->
                    new SimplySwordItem(ModTiers.DIARKRITE, (diarkrite_modifier + getDamageMod(id)),
                            getAttackSpeedMod(id) + diarkriteSpeed, new Item.Properties().fireResistant()));
        }

        private static RegistryObject<Item> registerAnthektite(String id) {
            return ITEMS.register(ModTiers.ANTHEKTITE.toString().toLowerCase(Locale.ROOT) + "_" + id, ()->
                    new SimplySwordItem(ModTiers.ANTHEKTITE, (anthektite_modifier + getDamageMod(id)),
                            getAttackSpeedMod(id) + anthektiteSpeed, new Item.Properties().fireResistant()));
        }


        public static int getDamageMod(String weaponType) {
            return ATK_DAMAGE.get(weaponType);
        }

        public static float getAttackSpeedMod(String weaponType) {
            return ATK_SPEED.get(weaponType);
        }

        private static final HashMap<String, Integer> ATK_DAMAGE = new HashMap<>();
        private static final HashMap<String, Float> ATK_SPEED = new HashMap<>();

        static {
            ATK_DAMAGE.put("chakram", chakram_positive_modifier);
            ATK_SPEED.put("chakram", chakram_attackspeed);

            ATK_DAMAGE.put("claymore", claymore_positive_modifier);
            ATK_SPEED.put("claymore", claymore_attackspeed);

            ATK_DAMAGE.put("cutlass", cutlass_positive_modifier);
            ATK_SPEED.put("cutlass", cutlass_attackspeed);

            ATK_DAMAGE.put("glaive", glaive_positive_modifier);
            ATK_SPEED.put("glaive", glaive_attackspeed);

            ATK_DAMAGE.put("greataxe", greataxe_positive_modifier );
            ATK_SPEED.put("greataxe", greataxe_attackspeed);

            ATK_DAMAGE.put("greathammer", greathammer_positive_modifier);
            ATK_SPEED.put("greathammer", greathammer_attackspeed);

            ATK_DAMAGE.put("halberd", halberd_positive_modifier);
            ATK_SPEED.put("halberd", halberd_attackspeed);

            ATK_DAMAGE.put("katana", katana_positive_modifier);
            ATK_SPEED.put("katana", katana_attackspeed);

            ATK_DAMAGE.put("longsword", longsword_positive_modifier);
            ATK_SPEED.put("longsword", longsword_attackspeed);

            ATK_DAMAGE.put("rapier", rapier_positive_modifier);
            ATK_SPEED.put("rapier", rapier_attackspeed);

            ATK_DAMAGE.put("sai", sai_positive_modifier);
            ATK_SPEED.put("sai", sai_attackspeed);

            ATK_DAMAGE.put("scythe", scythe_positive_modifier);
            ATK_SPEED.put("scythe", scythe_attackspeed);

            ATK_DAMAGE.put("spear", spear_positive_modifier);
            ATK_SPEED.put("spear", spear_attackspeed);

            ATK_DAMAGE.put("twinblade", twinblade_positive_modifier);
            ATK_SPEED.put("twinblade", twinblade_attackspeed);

            ATK_DAMAGE.put("warglaive", warglaive_positive_modifier);
            ATK_SPEED.put("warglaive", warglaive_attackspeed);
        }

        public static void register(IEventBus eventBus) {ITEMS.register(eventBus);}
    }
    public static class SniffsWeaponsItems {
        public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

        public static final RegistryObject<Item> STEEL_GREAT_SWORD = ITEMS.register("steel_great_sword", () -> new GreatSwordItem(ModTiers.STEEL,
                ItemConfig.steelGreatSwordDamage, ItemConfig.steelGreatSwordAttackSpeed + steelSpeed,new Item.Properties()));
        public static final RegistryObject<Item> DIARKRITE_GREAT_SWORD = ITEMS.register("diarkrite_great_sword", () -> new GreatSwordItem(ModTiers.DIARKRITE,
                ItemConfig.diarkriteGreatSwordDamage, ItemConfig.diarkriteGreatSwordAttackSpeed + diarkriteSpeed,new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> ANTHEKTITE_GREAT_SWORD = ITEMS.register("anthektite_great_sword", () -> new GreatSwordItem(ModTiers.ANTHEKTITE,
                ItemConfig.anthektiteGreatSwordDamage, ItemConfig.anthektiteGreatSwordAttackSpeed + anthektiteSpeed,new Item.Properties().fireResistant()));

        public static final RegistryObject<Item> STEEL_GREAT_AXE = ITEMS.register("steel_great_axe", () -> new GreatAxeItem(ModTiers.STEEL,
                ItemConfig.steelGreatAxeDamage, ItemConfig.steelGreatAxeAttackSpeed + steelSpeed,new Item.Properties()));
        public static final RegistryObject<Item> DIARKRITE_GREAT_AXE = ITEMS.register("diarkrite_great_axe", () -> new GreatAxeItem(ModTiers.DIARKRITE,
                ItemConfig.diarkriteGreatAxeDamage, ItemConfig.diarkriteGreatAxeAttackSpeed + diarkriteSpeed,new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> ANTHEKTITE_GREAT_AXE = ITEMS.register("anthektite_great_axe", () -> new GreatAxeItem(ModTiers.ANTHEKTITE,
                ItemConfig.anthektiteGreatAxeDamage, ItemConfig.anthektiteGreatAxeAttackSpeed + anthektiteSpeed,new Item.Properties().fireResistant()));

        public static final RegistryObject<Item> STEEL_GREAT_PICKAXE = ITEMS.register("steel_great_pickaxe", () -> new ModGreatPickaxeItem(ModTiers.STEEL,
                ItemConfig.steelGreatPickaxeDamage, ItemConfig.steelGreatPickaxeAttackSpeed + steelSpeed,new Item.Properties()));
        public static final RegistryObject<Item> DIARKRITE_GREAT_PICKAXE = ITEMS.register("diarkrite_great_pickaxe", () -> new ModGreatPickaxeItem(ModTiers.DIARKRITE,
                ItemConfig.diarkriteGreatPickaxeDamage, ItemConfig.diarkriteGreatPickaxeAttackSpeed + diarkriteSpeed,new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> ANTHEKTITE_GREAT_PICKAXE = ITEMS.register("anthektite_great_pickaxe", () -> new ModGreatPickaxeItem(ModTiers.ANTHEKTITE,
                ItemConfig.anthektiteGreatPickaxeDamage, ItemConfig.anthektiteGreatPickaxeAttackSpeed + anthektiteSpeed,new Item.Properties().fireResistant()));

        public static final RegistryObject<Item> STEEL_NAGINATA = ITEMS.register("steel_naginata", () -> new NaginataItem(ModTiers.STEEL,
                ItemConfig.steelNaginataDamage, ItemConfig.steelNaginataAttackSpeed + steelSpeed,new Item.Properties()));
        public static final RegistryObject<Item> DIARKRITE_NAGINATA = ITEMS.register("diarkrite_naginata", () -> new NaginataItem(ModTiers.DIARKRITE,
                ItemConfig.diarkriteNaginataDamage, ItemConfig.diarkriteNaginataAttackSpeed + diarkriteSpeed,new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> ANTHEKTITE_NAGINATA = ITEMS.register("anthektite_naginata", () -> new NaginataItem(ModTiers.ANTHEKTITE,
                ItemConfig.anthektiteNaginataDamage, ItemConfig.anthektiteNaginataAttackSpeed + anthektiteSpeed,new Item.Properties().fireResistant()));

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
    public static class AdvancedNetheriteItems {
        public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

        //Diarkrite Ingots
        public static final RegistryObject<Item> DIARKRITE_IRON = ITEMS.register("diarkrite_iron_ingot",
                () -> new ANItem(new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> DIARKRITE_GOLD = ITEMS.register("diarkrite_gold_ingot",
                () -> new ANItem(new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> DIARKRITE_EMERALD = ITEMS.register("diarkrite_emerald_ingot",
                () -> new ANItem(new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> DIARKRITE_DIAMOND = ITEMS.register("diarkrite_diamond_ingot",
                () -> new ANItem(new Item.Properties().fireResistant()));

        //Diarkrite Armor
        public static final RegistryObject<Item> DIARKRITE_IRON_HELMET = ITEMS.register("diarkrite_iron_helmet",
                () -> new ANArmorItem(ModArmorMaterials.DIARKRITE_IRON, ArmorItem.Type.HELMET, new Item.Properties()));
        public static final RegistryObject<Item> DIARKRITE_IRON_CHESTPLATE = ITEMS.register("diarkrite_iron_chestplate",
                () -> new ANArmorItem(ModArmorMaterials.DIARKRITE_IRON, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
        public static final RegistryObject<Item> DIARKRITE_IRON_LEGGINGS = ITEMS.register("diarkrite_iron_leggings",
                () -> new ANArmorItem(ModArmorMaterials.DIARKRITE_IRON, ArmorItem.Type.LEGGINGS, new Item.Properties()));
        public static final RegistryObject<Item> DIARKRITE_IRON_BOOTS = ITEMS.register("diarkrite_iron_boots",
                () -> new ANArmorItem(ModArmorMaterials.DIARKRITE_IRON, ArmorItem.Type.BOOTS, new Item.Properties()));

        public static final RegistryObject<Item> DIARKRITE_GOLD_HELMET = ITEMS.register("diarkrite_gold_helmet",
                () -> new ANArmorItem(ModArmorMaterials.DIARKRITE_GOLD, ArmorItem.Type.HELMET, new Item.Properties()));
        public static final RegistryObject<Item> DIARKRITE_GOLD_CHESTPLATE = ITEMS.register("diarkrite_gold_chestplate",
                () -> new ANArmorItem(ModArmorMaterials.DIARKRITE_GOLD, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
        public static final RegistryObject<Item> DIARKRITE_GOLD_LEGGINGS = ITEMS.register("diarkrite_gold_leggings",
                () -> new ANArmorItem(ModArmorMaterials.DIARKRITE_GOLD, ArmorItem.Type.LEGGINGS, new Item.Properties()));
        public static final RegistryObject<Item> DIARKRITE_GOLD_BOOTS = ITEMS.register("diarkrite_gold_boots",
                () -> new ANArmorItem(ModArmorMaterials.DIARKRITE_GOLD, ArmorItem.Type.BOOTS, new Item.Properties()));

        public static final RegistryObject<Item> DIARKRITE_EMERALD_HELMET = ITEMS.register("diarkrite_emerald_helmet",
                () -> new ANArmorItem(ModArmorMaterials.DIARKRITE_EMERALD, ArmorItem.Type.HELMET, new Item.Properties()));
        public static final RegistryObject<Item> DIARKRITE_EMERALD_CHESTPLATE = ITEMS.register("diarkrite_emerald_chestplate",
                () -> new ANArmorItem(ModArmorMaterials.DIARKRITE_EMERALD, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
        public static final RegistryObject<Item> DIARKRITE_EMERALD_LEGGINGS = ITEMS.register("diarkrite_emerald_leggings",
                () -> new ANArmorItem(ModArmorMaterials.DIARKRITE_EMERALD, ArmorItem.Type.LEGGINGS, new Item.Properties()));
        public static final RegistryObject<Item> DIARKRITE_EMERALD_BOOTS = ITEMS.register("diarkrite_emerald_boots",
                () -> new ANArmorItem(ModArmorMaterials.DIARKRITE_EMERALD, ArmorItem.Type.BOOTS, new Item.Properties()));

        public static final RegistryObject<Item> DIARKRITE_DIAMOND_HELMET = ITEMS.register("diarkrite_diamond_helmet",
                () -> new ANArmorItem(ModArmorMaterials.DIARKRITE_DIAMOND, ArmorItem.Type.HELMET, new Item.Properties()));
        public static final RegistryObject<Item> DIARKRITE_DIAMOND_CHESTPLATE = ITEMS.register("diarkrite_diamond_chestplate",
                () -> new ANArmorItem(ModArmorMaterials.DIARKRITE_DIAMOND, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
        public static final RegistryObject<Item> DIARKRITE_DIAMOND_LEGGINGS = ITEMS.register("diarkrite_diamond_leggings",
                () -> new ANArmorItem(ModArmorMaterials.DIARKRITE_DIAMOND, ArmorItem.Type.LEGGINGS, new Item.Properties()));
        public static final RegistryObject<Item> DIARKRITE_DIAMOND_BOOTS = ITEMS.register("diarkrite_diamond_boots",
                () -> new ANArmorItem(ModArmorMaterials.DIARKRITE_DIAMOND, ArmorItem.Type.BOOTS, new Item.Properties()));

        //Diarkrite Tools
        public static final RegistryObject<Item> DIARKRITE_IRON_AXE = ITEMS.register("diarkrite_iron_axe", () -> new ANAxeItem(ModTiers.DIARKRITE_IRON,
                ItemConfig.diarkriteIronAxeDamage, ItemConfig.diarkriteIronAxeAttackSpeed + diarkriteSpeed, new Item.Properties()));
        public static final RegistryObject<Item> DIARKRITE_GOLD_AXE = ITEMS.register("diarkrite_gold_axe", () -> new ANAxeItem(ModTiers.DIARKRITE_GOLD,
                ItemConfig.diarkriteGoldAxeDamage, ItemConfig.diarkriteGoldAxeAttackSpeed + diarkriteSpeed, new Item.Properties()));
        public static final RegistryObject<Item> DIARKRITE_EMERALD_AXE = ITEMS.register("diarkrite_emerald_axe", () -> new ANAxeItem(ModTiers.DIARKRITE_EMERALD,
                ItemConfig.diarkriteEmeraldAxeDamage, ItemConfig.diarkriteEmeraldAxeAttackSpeed + diarkriteSpeed, new Item.Properties()));
        public static final RegistryObject<Item> DIARKRITE_DIAMOND_AXE = ITEMS.register("diarkrite_diamond_axe", () -> new ANAxeItem(ModTiers.DIARKRITE_DIAMOND,
                ItemConfig.diarkriteDiamondAxeDamage, ItemConfig.diarkriteDiamondAxeAttackSpeed + diarkriteSpeed, new Item.Properties()));

        public static final RegistryObject<Item> DIARKRITE_IRON_HOE = ITEMS.register("diarkrite_iron_hoe", () -> new ANHoeItem(ModTiers.DIARKRITE_IRON,
                ItemConfig.diarkriteIronHoeDamage, ItemConfig.diarkriteIronHoeAttackSpeed, new Item.Properties()));
        public static final RegistryObject<Item> DIARKRITE_GOLD_HOE = ITEMS.register("diarkrite_gold_hoe", () -> new ANHoeItem(ModTiers.DIARKRITE_GOLD,
                ItemConfig.diarkriteGoldHoeDamage, ItemConfig.diarkriteGoldHoeAttackSpeed, new Item.Properties()));
        public static final RegistryObject<Item> DIARKRITE_EMERALD_HOE = ITEMS.register("diarkrite_emerald_hoe", () -> new ANHoeItem(ModTiers.DIARKRITE_EMERALD,
                ItemConfig.diarkriteEmeraldHoeDamage, ItemConfig.diarkriteEmeraldHoeAttackSpeed, new Item.Properties()));
        public static final RegistryObject<Item> DIARKRITE_DIAMOND_HOE = ITEMS.register("diarkrite_diamond_hoe", () -> new ANHoeItem(ModTiers.DIARKRITE_DIAMOND,
                ItemConfig.diarkriteDiamondHoeDamage, ItemConfig.diarkriteDiamondHoeAttackSpeed, new Item.Properties()));

        public static final RegistryObject<Item> DIARKRITE_IRON_PICKAXE = ITEMS.register("diarkrite_iron_pickaxe", () -> new ANPickaxeItem(ModTiers.DIARKRITE_IRON,
                ItemConfig.diarkriteIronPickaxeDamage, ItemConfig.diarkriteIronPickaxeAttackSpeed, new Item.Properties()));
        public static final RegistryObject<Item> DIARKRITE_GOLD_PICKAXE = ITEMS.register("diarkrite_gold_pickaxe", () -> new ANPickaxeItem(ModTiers.DIARKRITE_GOLD,
                ItemConfig.diarkriteGoldPickaxeDamage, ItemConfig.diarkriteGoldPickaxeAttackSpeed, new Item.Properties()));
        public static final RegistryObject<Item> DIARKRITE_EMERALD_PICKAXE = ITEMS.register("diarkrite_emerald_pickaxe", () -> new ANPickaxeItem(ModTiers.DIARKRITE_EMERALD,
                ItemConfig.diarkriteEmeraldPickaxeDamage, ItemConfig.diarkriteEmeraldPickaxeAttackSpeed, new Item.Properties()));
        public static final RegistryObject<Item> DIARKRITE_DIAMOND_PICKAXE = ITEMS.register("diarkrite_diamond_pickaxe", () -> new ANPickaxeItem(ModTiers.DIARKRITE_DIAMOND,
                ItemConfig.diarkriteDiamondPickaxeDamage, ItemConfig.diarkriteDiamondPickaxeAttackSpeed, new Item.Properties()));

        public static final RegistryObject<Item> DIARKRITE_IRON_SHOVEL = ITEMS.register("diarkrite_iron_shovel", () -> new ANShovelItem(ModTiers.DIARKRITE_IRON,
                ItemConfig.diarkriteIronShovelDamage, ItemConfig.diarkriteIronShovelAttackSpeed, new Item.Properties()));
        public static final RegistryObject<Item> DIARKRITE_GOLD_SHOVEL = ITEMS.register("diarkrite_gold_shovel", () -> new ANShovelItem(ModTiers.DIARKRITE_GOLD,
                ItemConfig.diarkriteGoldShovelDamage, ItemConfig.diarkriteGoldShovelAttackSpeed, new Item.Properties()));
        public static final RegistryObject<Item> DIARKRITE_EMERALD_SHOVEL = ITEMS.register("diarkrite_emerald_shovel", () -> new ANShovelItem(ModTiers.DIARKRITE_EMERALD,
                ItemConfig.diarkriteEmeraldShovelDamage, ItemConfig.diarkriteEmeraldShovelAttackSpeed, new Item.Properties()));
        public static final RegistryObject<Item> DIARKRITE_DIAMOND_SHOVEL = ITEMS.register("diarkrite_diamond_shovel", () -> new ANShovelItem(ModTiers.DIARKRITE_DIAMOND,
                ItemConfig.diarkriteDiamondShovelDamage, ItemConfig.diarkriteDiamondShovelAttackSpeed, new Item.Properties()));

        public static final RegistryObject<Item> DIARKRITE_IRON_SWORD = ITEMS.register("diarkrite_iron_sword", () -> new ANSwordItem(ModTiers.DIARKRITE_IRON,
                ItemConfig.diarkriteIronSwordDamage, ItemConfig.diarkriteIronSwordAttackSpeed + diarkriteSpeed, new Item.Properties()));
        public static final RegistryObject<Item> DIARKRITE_GOLD_SWORD = ITEMS.register("diarkrite_gold_sword", () -> new ANSwordItem(ModTiers.DIARKRITE_GOLD,
                ItemConfig.diarkriteGoldSwordDamage, ItemConfig.diarkriteGoldSwordAttackSpeed + diarkriteSpeed, new Item.Properties()));
        public static final RegistryObject<Item> DIARKRITE_EMERALD_SWORD = ITEMS.register("diarkrite_emerald_sword", () -> new ANSwordItem(ModTiers.DIARKRITE_EMERALD,
                ItemConfig.diarkriteEmeraldSwordDamage, ItemConfig.diarkriteEmeraldSwordAttackSpeed + diarkriteSpeed, new Item.Properties()));
        public static final RegistryObject<Item> DIARKRITE_DIAMOND_SWORD = ITEMS.register("diarkrite_diamond_sword", () -> new ANSwordItem(ModTiers.DIARKRITE_DIAMOND,
                ItemConfig.diarkriteDiamondSwordDamage, ItemConfig.diarkriteDiamondSwordAttackSpeed + diarkriteSpeed, new Item.Properties()));

        //Diarkrite Blocks
        public static final RegistryObject<Item> DIARKRITE_IRON_BLOCK = ITEMS.register("diarkrite_iron_block",
                () -> new BlockItem(AdvancedNetheriteBlocks.DIARKRITE_IRON_BLOCK.get(), new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> DIARKRITE_GOLD_BLOCK = ITEMS.register("diarkrite_gold_block",
                () -> new BlockItem(AdvancedNetheriteBlocks.DIARKRITE_GOLD_BLOCK.get(), new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> DIARKRITE_EMERALD_BLOCK = ITEMS.register("diarkrite_emerald_block",
                () -> new BlockItem(AdvancedNetheriteBlocks.DIARKRITE_EMERALD_BLOCK.get(), new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> DIARKRITE_DIAMOND_BLOCK = ITEMS.register("diarkrite_diamond_block",
                () -> new BlockItem(AdvancedNetheriteBlocks.DIARKRITE_DIAMOND_BLOCK.get(), new Item.Properties().fireResistant()));

        //Anthektite Ingots
        public static final RegistryObject<Item> ANTHEKTITE_IRON = ITEMS.register("anthektite_iron_ingot",
                () -> new ANItem(new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> ANTHEKTITE_GOLD = ITEMS.register("anthektite_gold_ingot",
                () -> new ANItem(new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> ANTHEKTITE_EMERALD = ITEMS.register("anthektite_emerald_ingot",
                () -> new ANItem(new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> ANTHEKTITE_DIAMOND = ITEMS.register("anthektite_diamond_ingot",
                () -> new ANItem(new Item.Properties().fireResistant()));

        //Anthektite Armor
        public static final RegistryObject<Item> ANTHEKTITE_IRON_HELMET = ITEMS.register("anthektite_iron_helmet",
                () -> new ANArmorItem(ModArmorMaterials.ANTHEKTITE_IRON, ArmorItem.Type.HELMET, new Item.Properties()));
        public static final RegistryObject<Item> ANTHEKTITE_IRON_CHESTPLATE = ITEMS.register("anthektite_iron_chestplate",
                () -> new ANArmorItem(ModArmorMaterials.ANTHEKTITE_IRON, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
        public static final RegistryObject<Item> ANTHEKTITE_IRON_LEGGINGS = ITEMS.register("anthektite_iron_leggings",
                () -> new ANArmorItem(ModArmorMaterials.ANTHEKTITE_IRON, ArmorItem.Type.LEGGINGS, new Item.Properties()));
        public static final RegistryObject<Item> ANTHEKTITE_IRON_BOOTS = ITEMS.register("anthektite_iron_boots",
                () -> new ANArmorItem(ModArmorMaterials.ANTHEKTITE_IRON, ArmorItem.Type.BOOTS, new Item.Properties()));

        public static final RegistryObject<Item> ANTHEKTITE_GOLD_HELMET = ITEMS.register("anthektite_gold_helmet",
                () -> new ANArmorItem(ModArmorMaterials.ANTHEKTITE_GOLD, ArmorItem.Type.HELMET, new Item.Properties()));
        public static final RegistryObject<Item> ANTHEKTITE_GOLD_CHESTPLATE = ITEMS.register("anthektite_gold_chestplate",
                () -> new ANArmorItem(ModArmorMaterials.ANTHEKTITE_GOLD, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
        public static final RegistryObject<Item> ANTHEKTITE_GOLD_LEGGINGS = ITEMS.register("anthektite_gold_leggings",
                () -> new ANArmorItem(ModArmorMaterials.ANTHEKTITE_GOLD, ArmorItem.Type.LEGGINGS, new Item.Properties()));
        public static final RegistryObject<Item> ANTHEKTITE_GOLD_BOOTS = ITEMS.register("anthektite_gold_boots",
                () -> new ANArmorItem(ModArmorMaterials.ANTHEKTITE_GOLD, ArmorItem.Type.BOOTS, new Item.Properties()));

        public static final RegistryObject<Item> ANTHEKTITE_EMERALD_HELMET = ITEMS.register("anthektite_emerald_helmet",
                () -> new ANArmorItem(ModArmorMaterials.ANTHEKTITE_EMERALD, ArmorItem.Type.HELMET, new Item.Properties()));
        public static final RegistryObject<Item> ANTHEKTITE_EMERALD_CHESTPLATE = ITEMS.register("anthektite_emerald_chestplate",
                () -> new ANArmorItem(ModArmorMaterials.ANTHEKTITE_EMERALD, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
        public static final RegistryObject<Item> ANTHEKTITE_EMERALD_LEGGINGS = ITEMS.register("anthektite_emerald_leggings",
                () -> new ANArmorItem(ModArmorMaterials.ANTHEKTITE_EMERALD, ArmorItem.Type.LEGGINGS, new Item.Properties()));
        public static final RegistryObject<Item> ANTHEKTITE_EMERALD_BOOTS = ITEMS.register("anthektite_emerald_boots",
                () -> new ANArmorItem(ModArmorMaterials.ANTHEKTITE_EMERALD, ArmorItem.Type.BOOTS, new Item.Properties()));

        public static final RegistryObject<Item> ANTHEKTITE_DIAMOND_HELMET = ITEMS.register("anthektite_diamond_helmet",
                () -> new ANArmorItem(ModArmorMaterials.ANTHEKTITE_DIAMOND, ArmorItem.Type.HELMET, new Item.Properties()));
        public static final RegistryObject<Item> ANTHEKTITE_DIAMOND_CHESTPLATE = ITEMS.register("anthektite_diamond_chestplate",
                () -> new ANArmorItem(ModArmorMaterials.ANTHEKTITE_DIAMOND, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
        public static final RegistryObject<Item> ANTHEKTITE_DIAMOND_LEGGINGS = ITEMS.register("anthektite_diamond_leggings",
                () -> new ANArmorItem(ModArmorMaterials.ANTHEKTITE_DIAMOND, ArmorItem.Type.LEGGINGS, new Item.Properties()));
        public static final RegistryObject<Item> ANTHEKTITE_DIAMOND_BOOTS = ITEMS.register("anthektite_diamond_boots",
                () -> new ANArmorItem(ModArmorMaterials.ANTHEKTITE_DIAMOND, ArmorItem.Type.BOOTS, new Item.Properties()));

        //Anthektite Tools
        public static final RegistryObject<Item> ANTHEKTITE_IRON_AXE = ITEMS.register("anthektite_iron_axe", () -> new ANAxeItem(ModTiers.ANTHEKTITE_IRON,
                ItemConfig.anthektiteIronAxeDamage, ItemConfig.anthektiteIronAxeAttackSpeed + anthektiteSpeed, new Item.Properties()));
        public static final RegistryObject<Item> ANTHEKTITE_GOLD_AXE = ITEMS.register("anthektite_gold_axe", () -> new ANAxeItem(ModTiers.ANTHEKTITE_GOLD,
                ItemConfig.anthektiteGoldAxeDamage, ItemConfig.anthektiteGoldAxeAttackSpeed + anthektiteSpeed, new Item.Properties()));
        public static final RegistryObject<Item> ANTHEKTITE_EMERALD_AXE = ITEMS.register("anthektite_emerald_axe", () -> new ANAxeItem(ModTiers.ANTHEKTITE_EMERALD,
                ItemConfig.anthektiteEmeraldAxeDamage, ItemConfig.anthektiteEmeraldAxeAttackSpeed + anthektiteSpeed, new Item.Properties()));
        public static final RegistryObject<Item> ANTHEKTITE_DIAMOND_AXE = ITEMS.register("anthektite_diamond_axe", () -> new ANAxeItem(ModTiers.ANTHEKTITE_DIAMOND,
                ItemConfig.anthektiteDiamondAxeDamage, ItemConfig.anthektiteDiamondAxeAttackSpeed + anthektiteSpeed, new Item.Properties()));

        public static final RegistryObject<Item> ANTHEKTITE_IRON_HOE = ITEMS.register("anthektite_iron_hoe", () -> new ANHoeItem(ModTiers.ANTHEKTITE_IRON,
                ItemConfig.anthektiteIronHoeDamage, ItemConfig.anthektiteIronHoeAttackSpeed, new Item.Properties()));
        public static final RegistryObject<Item> ANTHEKTITE_GOLD_HOE = ITEMS.register("anthektite_gold_hoe", () -> new ANHoeItem(ModTiers.ANTHEKTITE_GOLD,
                ItemConfig.anthektiteGoldHoeDamage, ItemConfig.anthektiteGoldHoeAttackSpeed, new Item.Properties()));
        public static final RegistryObject<Item> ANTHEKTITE_EMERALD_HOE = ITEMS.register("anthektite_emerald_hoe", () -> new ANHoeItem(ModTiers.ANTHEKTITE_EMERALD,
                ItemConfig.anthektiteEmeraldHoeDamage, ItemConfig.anthektiteEmeraldHoeAttackSpeed, new Item.Properties()));
        public static final RegistryObject<Item> ANTHEKTITE_DIAMOND_HOE = ITEMS.register("anthektite_diamond_hoe", () -> new ANHoeItem(ModTiers.ANTHEKTITE_DIAMOND,
                ItemConfig.anthektiteDiamondHoeDamage, ItemConfig.anthektiteDiamondHoeAttackSpeed, new Item.Properties()));

        public static final RegistryObject<Item> ANTHEKTITE_IRON_PICKAXE = ITEMS.register("anthektite_iron_pickaxe", () -> new ANPickaxeItem(ModTiers.ANTHEKTITE_IRON,
                ItemConfig.anthektiteIronPickaxeDamage, ItemConfig.anthektiteIronPickaxeAttackSpeed, new Item.Properties()));
        public static final RegistryObject<Item> ANTHEKTITE_GOLD_PICKAXE = ITEMS.register("anthektite_gold_pickaxe", () -> new ANPickaxeItem(ModTiers.ANTHEKTITE_GOLD,
                ItemConfig.anthektiteGoldPickaxeDamage, ItemConfig.anthektiteGoldPickaxeAttackSpeed, new Item.Properties()));
        public static final RegistryObject<Item> ANTHEKTITE_EMERALD_PICKAXE = ITEMS.register("anthektite_emerald_pickaxe", () -> new ANPickaxeItem(ModTiers.ANTHEKTITE_EMERALD,
                ItemConfig.anthektiteEmeraldPickaxeDamage, ItemConfig.anthektiteEmeraldPickaxeAttackSpeed, new Item.Properties()));
        public static final RegistryObject<Item> ANTHEKTITE_DIAMOND_PICKAXE = ITEMS.register("anthektite_diamond_pickaxe", () -> new ANPickaxeItem(ModTiers.ANTHEKTITE_DIAMOND,
                ItemConfig.anthektiteDiamondPickaxeDamage, ItemConfig.anthektiteDiamondPickaxeAttackSpeed, new Item.Properties()));

        public static final RegistryObject<Item> ANTHEKTITE_IRON_SHOVEL = ITEMS.register("anthektite_iron_shovel", () -> new ANShovelItem(ModTiers.ANTHEKTITE_IRON,
                ItemConfig.anthektiteIronShovelDamage, ItemConfig.anthektiteIronShovelAttackSpeed, new Item.Properties()));
        public static final RegistryObject<Item> ANTHEKTITE_GOLD_SHOVEL = ITEMS.register("anthektite_gold_shovel", () -> new ANShovelItem(ModTiers.ANTHEKTITE_GOLD,
                ItemConfig.anthektiteGoldShovelDamage, ItemConfig.anthektiteGoldShovelAttackSpeed, new Item.Properties()));
        public static final RegistryObject<Item> ANTHEKTITE_EMERALD_SHOVEL = ITEMS.register("anthektite_emerald_shovel", () -> new ANShovelItem(ModTiers.ANTHEKTITE_EMERALD,
                ItemConfig.anthektiteEmeraldShovelDamage, ItemConfig.anthektiteEmeraldShovelAttackSpeed, new Item.Properties()));
        public static final RegistryObject<Item> ANTHEKTITE_DIAMOND_SHOVEL = ITEMS.register("anthektite_diamond_shovel", () -> new ANShovelItem(ModTiers.ANTHEKTITE_DIAMOND,
                ItemConfig.anthektiteDiamondShovelDamage, ItemConfig.anthektiteDiamondShovelAttackSpeed, new Item.Properties()));

        public static final RegistryObject<Item> ANTHEKTITE_IRON_SWORD = ITEMS.register("anthektite_iron_sword", () -> new ANSwordItem(ModTiers.ANTHEKTITE_IRON,
                ItemConfig.anthektiteIronSwordDamage, ItemConfig.anthektiteIronSwordAttackSpeed + anthektiteSpeed, new Item.Properties()));
        public static final RegistryObject<Item> ANTHEKTITE_GOLD_SWORD = ITEMS.register("anthektite_gold_sword", () -> new ANSwordItem(ModTiers.ANTHEKTITE_GOLD,
                ItemConfig.anthektiteGoldSwordDamage, ItemConfig.anthektiteGoldSwordAttackSpeed + anthektiteSpeed, new Item.Properties()));
        public static final RegistryObject<Item> ANTHEKTITE_EMERALD_SWORD = ITEMS.register("anthektite_emerald_sword", () -> new ANSwordItem(ModTiers.ANTHEKTITE_EMERALD,
                ItemConfig.anthektiteEmeraldSwordDamage, ItemConfig.anthektiteEmeraldSwordAttackSpeed + anthektiteSpeed, new Item.Properties()));
        public static final RegistryObject<Item> ANTHEKTITE_DIAMOND_SWORD = ITEMS.register("anthektite_diamond_sword", () -> new ANSwordItem(ModTiers.ANTHEKTITE_DIAMOND,
                ItemConfig.anthektiteDiamondSwordDamage, ItemConfig.anthektiteDiamondSwordAttackSpeed + anthektiteSpeed, new Item.Properties()));

        //Anthektite Blocks
        public static final RegistryObject<Item> ANTHEKTITE_IRON_BLOCK = ITEMS.register("anthektite_iron_block",
                () -> new BlockItem(AdvancedNetheriteBlocks.ANTHEKTITE_IRON_BLOCK.get(), new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> ANTHEKTITE_GOLD_BLOCK = ITEMS.register("anthektite_gold_block",
                () -> new BlockItem(AdvancedNetheriteBlocks.ANTHEKTITE_GOLD_BLOCK.get(), new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> ANTHEKTITE_EMERALD_BLOCK = ITEMS.register("anthektite_emerald_block",
                () -> new BlockItem(AdvancedNetheriteBlocks.ANTHEKTITE_EMERALD_BLOCK.get(), new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> ANTHEKTITE_DIAMOND_BLOCK = ITEMS.register("anthektite_diamond_block",
                () -> new BlockItem(AdvancedNetheriteBlocks.ANTHEKTITE_DIAMOND_BLOCK.get(), new Item.Properties().fireResistant()));


        public static void register(IEventBus eventBus) {ITEMS.register(eventBus);}
    }
    public static class EpicSamuraiItems {
        public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

        public static final RegistryObject<Item> STEEL_SAMURAI_HELMET = ITEMS.register("steel_samurai_helmet",
                () -> new ESArmorItem(ModArmorMaterials.STEEL_SAMURAI, ArmorItem.Type.HELMET, new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> STEEL_SAMURAI_CHESTPLATE = ITEMS.register("steel_samurai_chestplate",
                () -> new ESArmorItem(ModArmorMaterials.STEEL_SAMURAI, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> STEEL_SAMURAI_LEGGINGS = ITEMS.register("steel_samurai_leggings",
                () -> new ESArmorItem(ModArmorMaterials.STEEL_SAMURAI, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> STEEL_SAMURAI_BOOTS = ITEMS.register("steel_samurai_boots",
                () -> new ESArmorItem(ModArmorMaterials.STEEL_SAMURAI, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant()));

        public static final RegistryObject<Item> STEEL_SAMURAI_HELMET_LIGHT = ITEMS.register("steel_samurai_helmet_light",
                () -> new ESArmorItem(ModArmorMaterials.STEEL_SAMURAI_LIGHT, ArmorItem.Type.HELMET, new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> STEEL_SAMURAI_CHESTPLATE_LIGHT = ITEMS.register("steel_samurai_chestplate_light",
                () -> new ESArmorItem(ModArmorMaterials.STEEL_SAMURAI_LIGHT, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> STEEL_SAMURAI_LEGGINGS_LIGHT = ITEMS.register("steel_samurai_leggings_light",
                () -> new ESArmorItem(ModArmorMaterials.STEEL_SAMURAI_LIGHT, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> STEEL_SAMURAI_BOOTS_LIGHT = ITEMS.register("steel_samurai_boots_light",
                () -> new ESArmorItem(ModArmorMaterials.STEEL_SAMURAI_LIGHT, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant()));

        public static final RegistryObject<Item> STEEL_SAMURAI_HELMET_MASTER = ITEMS.register("steel_samurai_helmet_master",
                () -> new ESArmorItem(ModArmorMaterials.STEEL_SAMURAI_MASTER, ArmorItem.Type.HELMET, new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> STEEL_SAMURAI_CHESTPLATE_MASTER = ITEMS.register("steel_samurai_chestplate_master",
                () -> new ESArmorItem(ModArmorMaterials.STEEL_SAMURAI_MASTER, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> STEEL_SAMURAI_LEGGINGS_MASTER = ITEMS.register("steel_samurai_leggings_master",
                () -> new ESArmorItem(ModArmorMaterials.STEEL_SAMURAI_MASTER, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> STEEL_SAMURAI_BOOTS_MASTER = ITEMS.register("steel_samurai_boots_master",
                () -> new ESArmorItem(ModArmorMaterials.STEEL_SAMURAI_MASTER, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant()));


        public static final RegistryObject<Item> DIARKRITE_SAMURAI_HELMET = ITEMS.register("diarkrite_samurai_helmet",
                () -> new ESArmorItem(ModArmorMaterials.DIARKRITE_SAMURAI, ArmorItem.Type.HELMET, new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> DIARKRITE_SAMURAI_CHESTPLATE = ITEMS.register("diarkrite_samurai_chestplate",
                () -> new ESArmorItem(ModArmorMaterials.DIARKRITE_SAMURAI, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> DIARKRITE_SAMURAI_LEGGINGS = ITEMS.register("diarkrite_samurai_leggings",
                () -> new ESArmorItem(ModArmorMaterials.DIARKRITE_SAMURAI, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> DIARKRITE_SAMURAI_BOOTS = ITEMS.register("diarkrite_samurai_boots",
                () -> new ESArmorItem(ModArmorMaterials.DIARKRITE_SAMURAI, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant()));

        public static final RegistryObject<Item> DIARKRITE_SAMURAI_HELMET_LIGHT = ITEMS.register("diarkrite_samurai_helmet_light",
                () -> new ESArmorItem(ModArmorMaterials.DIARKRITE_SAMURAI_LIGHT, ArmorItem.Type.HELMET, new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> DIARKRITE_SAMURAI_CHESTPLATE_LIGHT = ITEMS.register("diarkrite_samurai_chestplate_light",
                () -> new ESArmorItem(ModArmorMaterials.DIARKRITE_SAMURAI_LIGHT, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> DIARKRITE_SAMURAI_LEGGINGS_LIGHT = ITEMS.register("diarkrite_samurai_leggings_light",
                () -> new ESArmorItem(ModArmorMaterials.DIARKRITE_SAMURAI_LIGHT, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> DIARKRITE_SAMURAI_BOOTS_LIGHT = ITEMS.register("diarkrite_samurai_boots_light",
                () -> new ESArmorItem(ModArmorMaterials.DIARKRITE_SAMURAI_LIGHT, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant()));

        public static final RegistryObject<Item> DIARKRITE_SAMURAI_HELMET_MASTER = ITEMS.register("diarkrite_samurai_helmet_master",
                () -> new ESArmorItem(ModArmorMaterials.DIARKRITE_SAMURAI_MASTER, ArmorItem.Type.HELMET, new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> DIARKRITE_SAMURAI_CHESTPLATE_MASTER = ITEMS.register("diarkrite_samurai_chestplate_master",
                () -> new ESArmorItem(ModArmorMaterials.DIARKRITE_SAMURAI_MASTER, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> DIARKRITE_SAMURAI_LEGGINGS_MASTER = ITEMS.register("diarkrite_samurai_leggings_master",
                () -> new ESArmorItem(ModArmorMaterials.DIARKRITE_SAMURAI_MASTER, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> DIARKRITE_SAMURAI_BOOTS_MASTER = ITEMS.register("diarkrite_samurai_boots_master",
                () -> new ESArmorItem(ModArmorMaterials.DIARKRITE_SAMURAI_MASTER, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant()));


        public static final RegistryObject<Item> ANTHEKTITE_SAMURAI_HELMET = ITEMS.register("anthektite_samurai_helmet",
                () -> new ESArmorItem(ModArmorMaterials.ANTHEKTITE_SAMURAI, ArmorItem.Type.HELMET, new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> ANTHEKTITE_SAMURAI_CHESTPLATE = ITEMS.register("anthektite_samurai_chestplate",
                () -> new ESArmorItem(ModArmorMaterials.ANTHEKTITE_SAMURAI, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> ANTHEKTITE_SAMURAI_LEGGINGS = ITEMS.register("anthektite_samurai_leggings",
                () -> new ESArmorItem(ModArmorMaterials.ANTHEKTITE_SAMURAI, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> ANTHEKTITE_SAMURAI_BOOTS = ITEMS.register("anthektite_samurai_boots",
                () -> new ESArmorItem(ModArmorMaterials.ANTHEKTITE_SAMURAI, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant()));

        public static final RegistryObject<Item> ANTHEKTITE_SAMURAI_HELMET_LIGHT = ITEMS.register("anthektite_samurai_helmet_light",
                () -> new ESArmorItem(ModArmorMaterials.ANTHEKTITE_SAMURAI_LIGHT, ArmorItem.Type.HELMET, new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> ANTHEKTITE_SAMURAI_CHESTPLATE_LIGHT = ITEMS.register("anthektite_samurai_chestplate_light",
                () -> new ESArmorItem(ModArmorMaterials.ANTHEKTITE_SAMURAI_LIGHT, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> ANTHEKTITE_SAMURAI_LEGGINGS_LIGHT = ITEMS.register("anthektite_samurai_leggings_light",
                () -> new ESArmorItem(ModArmorMaterials.ANTHEKTITE_SAMURAI_LIGHT, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> ANTHEKTITE_SAMURAI_BOOTS_LIGHT = ITEMS.register("anthektite_samurai_boots_light",
                () -> new ESArmorItem(ModArmorMaterials.ANTHEKTITE_SAMURAI_LIGHT, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant()));

        public static final RegistryObject<Item> ANTHEKTITE_SAMURAI_HELMET_MASTER = ITEMS.register("anthektite_samurai_helmet_master",
                () -> new ESArmorItem(ModArmorMaterials.ANTHEKTITE_SAMURAI_MASTER, ArmorItem.Type.HELMET, new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> ANTHEKTITE_SAMURAI_CHESTPLATE_MASTER = ITEMS.register("anthektite_samurai_chestplate_master",
                () -> new ESArmorItem(ModArmorMaterials.ANTHEKTITE_SAMURAI_MASTER, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> ANTHEKTITE_SAMURAI_LEGGINGS_MASTER = ITEMS.register("anthektite_samurai_leggings_master",
                () -> new ESArmorItem(ModArmorMaterials.ANTHEKTITE_SAMURAI_MASTER, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> ANTHEKTITE_SAMURAI_BOOTS_MASTER = ITEMS.register("anthektite_samurai_boots_master",
                () -> new ESArmorItem(ModArmorMaterials.ANTHEKTITE_SAMURAI_MASTER, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant()));

        public static void register(IEventBus eventBus) {ITEMS.register(eventBus);}
    }
    public static class TwigsItems {
        public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

        public static final RegistryObject<Item> MOVCADIA_TABLE = ITEMS.register("movcadia_table",
                () -> new BlockItem(TwigsBlocks.MOVCADIA_TABLE.get(), new Item.Properties().fireResistant()));
        public static void register(IEventBus eventBus) {ITEMS.register(eventBus);}
    }
    public static class WitherstormModItems {
        public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

        public static final RegistryObject<Item> STEEL_CMD_SWORD = ITEMS.register("steel_command_block_sword", () -> new ModSwordItem(ModTiers.STEEL_CMD,
                ItemConfig.steelCMDSwordDamage, ItemConfig.steelCMDSwordAttackSpeed + steelSpeed, new Item.Properties()));
        public static final RegistryObject<Item> STEEL_CMD_SHOVEL = ITEMS.register("steel_command_block_shovel", () -> new ModShovelItem(ModTiers.STEEL_CMD,
                ItemConfig.steelCMDShovelDamage, ItemConfig.steelCMDShovelAttackSpeed, new Item.Properties()));
        public static final RegistryObject<Item> STEEL_CMD_PICKAXE = ITEMS.register("steel_command_block_pickaxe", () -> new ModPickaxeItem(ModTiers.STEEL_CMD,
                ItemConfig.steelCMDPickaxeDamage, ItemConfig.steelCMDPickaxeAttackSpeed, new Item.Properties()));
        public static final RegistryObject<Item> STEEL_CMD_AXE = ITEMS.register("steel_command_block_axe", () -> new ModAxeItem(ModTiers.STEEL_CMD,
                ItemConfig.steelCMDAxeDamage, ItemConfig.steelCMDAxeAttackSpeed + steelSpeed, new Item.Properties()));
        public static final RegistryObject<Item> STEEL_CMD_HOE = ITEMS.register("steel_command_block_hoe", () -> new ModHoeItem(ModTiers.STEEL_CMD,
                ItemConfig.steelCMDHoeDamage, ItemConfig.steelCMDHoeAttackSpeed, new Item.Properties()));


        public static final RegistryObject<Item> DIARKRITE_CMD_SWORD = ITEMS.register("diarkrite_command_block_sword", () -> new ModSwordItem(ModTiers.DIARKRITE_CMD,
                ItemConfig.diarkriteCMDSwordDamage, ItemConfig.diarkriteCMDSwordAttackSpeed + diarkriteSpeed, new Item.Properties()));
        public static final RegistryObject<Item> DIARKRITE_CMD_SHOVEL = ITEMS.register("diarkrite_command_block_shovel", () -> new ModShovelItem(ModTiers.DIARKRITE_CMD,
                ItemConfig.diarkriteCMDShovelDamage, ItemConfig.diarkriteCMDShovelAttackSpeed, new Item.Properties()));
        public static final RegistryObject<Item> DIARKRITE_CMD_PICKAXE = ITEMS.register("diarkrite_command_block_pickaxe", () -> new ModPickaxeItem(ModTiers.DIARKRITE_CMD,
                ItemConfig.diarkriteCMDPickaxeDamage, ItemConfig.diarkriteCMDPickaxeAttackSpeed, new Item.Properties()));
        public static final RegistryObject<Item> DIARKRITE_CMD_AXE = ITEMS.register("diarkrite_command_block_axe", () -> new ModAxeItem(ModTiers.DIARKRITE_CMD,
                ItemConfig.diarkriteCMDAxeDamage, ItemConfig.diarkriteCMDAxeAttackSpeed + diarkriteSpeed, new Item.Properties()));
        public static final RegistryObject<Item> DIARKRITE_CMD_HOE = ITEMS.register("diarkrite_command_block_hoe", () -> new ModHoeItem(ModTiers.DIARKRITE_CMD,
                ItemConfig.diarkriteCMDHoeDamage, ItemConfig.diarkriteCMDHoeAttackSpeed, new Item.Properties()));


        public static final RegistryObject<Item> ANTHEKTITE_CMD_SWORD = ITEMS.register("anthektite_command_block_sword", () -> new ModSwordItem(ModTiers.ANTHEKTITE_CMD,
                ItemConfig.anthektiteCMDSwordDamage, ItemConfig.anthektiteCMDSwordAttackSpeed + anthektiteSpeed, new Item.Properties()));
        public static final RegistryObject<Item> ANTHEKTITE_CMD_SHOVEL = ITEMS.register("anthektite_command_block_shovel", () -> new ModShovelItem(ModTiers.ANTHEKTITE_CMD,
                ItemConfig.anthektiteCMDShovelDamage, ItemConfig.anthektiteCMDShovelAttackSpeed, new Item.Properties()));
        public static final RegistryObject<Item> ANTHEKTITE_CMD_PICKAXE = ITEMS.register("anthektite_command_block_pickaxe", () -> new ModPickaxeItem(ModTiers.ANTHEKTITE_CMD,
                ItemConfig.anthektiteCMDPickaxeDamage, ItemConfig.anthektiteCMDPickaxeAttackSpeed, new Item.Properties()));
        public static final RegistryObject<Item> ANTHEKTITE_CMD_AXE = ITEMS.register("anthektite_command_block_axe", () -> new ModAxeItem(ModTiers.ANTHEKTITE_CMD,
                ItemConfig.anthektiteCMDAxeDamage, ItemConfig.anthektiteCMDAxeAttackSpeed + anthektiteSpeed, new Item.Properties()));
        public static final RegistryObject<Item> ANTHEKTITE_CMD_HOE = ITEMS.register("anthektite_command_block_hoe", () -> new ModHoeItem(ModTiers.ANTHEKTITE_CMD,
                ItemConfig.anthektiteCMDHoeDamage, ItemConfig.anthektiteCMDHoeAttackSpeed, new Item.Properties()));

        public static void register(IEventBus eventBus) {
            ITEMS.register(eventBus);
        }
    }
    public static class BanillaClawsItems {
        public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

        public static final RegistryObject<Item> STEEL_CLAWS = ITEMS.register("steel_claws", () -> new ClawsItem(ModTiers.STEEL_CLAW,
                ItemConfig.steelClawDamage, ItemConfig.steelClawAttackSpeed + steelSpeed, new Item.Properties()));
        public static final RegistryObject<Item> DIARKRITE_CLAWS = ITEMS.register("diarkrite_claws", () -> new ClawsItem(ModTiers.DIARKRITE_CLAW,
                ItemConfig.diarkriteClawDamage, ItemConfig.diarkriteClawAttackSpeed + diarkriteSpeed, new Item.Properties()));
        public static final RegistryObject<Item> ANTHEKTITE_CLAWS = ITEMS.register("anthektite_claws", () -> new ClawsItem(ModTiers.ANTHEKTITE_CLAW,
                ItemConfig.anthektiteClawDamage, ItemConfig.anthektiteClawAttackSpeed + anthektiteSpeed, new Item.Properties()));

        public static void register(IEventBus eventBus) {
            ITEMS.register(eventBus);
        }
    }

    public static void register(IEventBus eventBus) {
        ElementusItems.ITEMS.register(eventBus);
        if (farmersDelight) {
            FarmersDelightItems.ITEMS.register(eventBus);
        }
        if (piercingPaxels) {
            PiercingPaxelsItems.ITEMS.register(eventBus);
        }
        if (nethersDelight) {
            NethersDelightItems.ITEMS.register(eventBus);
        }
        if (ironsSpellbooks) {
            IronsSpellbooksItems.ITEMS.register(eventBus);
        }
        if (aether) {
            AetherItems.ITEMS.register(eventBus);
        }
        if (simplySwords) {
            SimplySwordsItems.ITEMS.register(eventBus);
        }
        if (sniffsWeapons) {
            SniffsWeaponsItems.ITEMS.register(eventBus);
        }
        if (advancedNetherite) {
            AdvancedNetheriteItems.ITEMS.register(eventBus);
        }
        if (samuraiDynasty) {
            EpicSamuraiItems.ITEMS.register(eventBus);
        }
        if (twigs) {
            TwigsItems.ITEMS.register(eventBus);
        }
        if (witherStormMod) {
            WitherstormModItems.ITEMS.register(eventBus);
        }
        if (vanillaClaws) {
            BanillaClawsItems.ITEMS.register(eventBus);
        }
    }
}
