package net.nokunami.elementus.common;

import com.ninni.twigs.registry.TwigsCreativeModeTabs;
import com.simibubi.create.AllCreativeModeTabs;
import io.redspace.ironsspellbooks.registries.CreativeTabRegistry;
import io.redspace.ironsspellbooks.registries.ItemRegistry;
import net.dakotapride.vanilla_claws.registry.ItemsInit;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.util.MutableHashedLinkedMap;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.nokunami.elementus.Elementus;
import net.nokunami.elementus.common.registry.ModItems.*;
import net.sweenus.simplyswords.SimplySwords;
import net.sweenus.simplyswords.registry.ItemsRegistry;
import nl.sniffiandros.sniffsweapons.reg.ItemReg;
import nonamecrackers2.witherstormmod.common.init.WitherStormModItemTabs;
import nonamecrackers2.witherstormmod.common.init.WitherStormModItems;
import umpaz.nethersdelight.common.registry.NDCreativeTab;
import umpaz.nethersdelight.common.registry.NDItems;
import vectorwing.farmersdelight.common.registry.ModCreativeTabs;
import vectorwing.farmersdelight.common.registry.ModItems;
import xyz.amymialee.piercingpaxels.PiercingPaxels;

import java.util.function.Supplier;

import static net.nokunami.elementus.ModChecker.*;

public class CreativeTabProperties {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Elementus.MODID);

    public static void addCreative(BuildCreativeModeTabContentsEvent event) {
        //Credit: oreganised mod, GitHub: https://github.com/Xaidee/oreganised

        ResourceKey<CreativeModeTab> tab = event.getTabKey();
        MutableHashedLinkedMap<ItemStack, CreativeModeTab.TabVisibility> entries = event.getEntries();
        // SpawnEgg
        if (tab == CreativeModeTabs.SPAWN_EGGS) {
            putAfter(entries, Items.SQUID_SPAWN_EGG, ElementusItems.STEEL_GOLEM_SPAWN_EGG);
        }
        // Food
        if (tab == CreativeModeTabs.FOOD_AND_DRINKS) {
            putAfter(entries, Items.GLOW_BERRIES, ElementusItems.MOVCADIA_BERRIES);
        }
        // Ingredients
        if (tab == CreativeModeTabs.INGREDIENTS) {
            putAfter(entries, Items.ANCIENT_DEBRIS, ElementusItems.REMNANT);

            putAfter(entries, Items.IRON_NUGGET, ElementusItems.STEEL_NUGGET);
            putAfter(entries, Items.IRON_INGOT, ElementusItems.CRUDE_STEEL);
            putAfter(entries, ElementusItems.CRUDE_STEEL, ElementusItems.STEEL_INGOT);
            putAfter(entries, ElementusItems.STEEL_INGOT, ElementusItems.STEEL_SCRAP);

            putAfter(entries, Items.NETHERITE_INGOT, ElementusItems.ATELIS_SCRAP);
            putAfter(entries, ElementusItems.ATELIS_SCRAP, ElementusItems.DIARKRITE_INGOT);
            putAfter(entries, ElementusItems.DIARKRITE_INGOT, ElementusItems.ANTHEKTITE_INGOT);
            putAfter(entries, Items.BLAZE_POWDER, ElementusItems.MOVCADIA_ESSENCE);

            putAfter(entries, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE);
            putAfter(entries, ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ElementusItems.WEAPON_FRAGMENT);
        }
        // Weapons
        if (tab == CreativeModeTabs.COMBAT) {
            // Swords
            if  (sniffsWeapons) {
                putBefore(entries, ItemReg.WOODEN_GREAT_SWORD, ElementusItems.STEEL_SWORD);
            } else {
                putAfter(entries, Items.NETHERITE_SWORD, ElementusItems.STEEL_SWORD);
            }
            putAfter(entries, ElementusItems.STEEL_SWORD, ElementusItems.DIARKRITE_SWORD);
            putAfter(entries, ElementusItems.DIARKRITE_SWORD, ElementusItems.ANTHEKTITE_SWORD);

            // Axes
            if  (sniffsWeapons) {
                putBefore(entries, ItemReg.WOODEN_GREAT_AXE, ElementusItems.STEEL_AXE);
            } else {
                putAfter(entries, Items.NETHERITE_AXE, ElementusItems.STEEL_AXE);
            }
            putAfter(entries, ElementusItems.STEEL_AXE, ElementusItems.DIARKRITE_AXE);
            putAfter(entries, ElementusItems.DIARKRITE_AXE, ElementusItems.ANTHEKTITE_AXE);

            // Shields
            putAfter(entries, Items.SHIELD, ElementusItems.STEEL_SHIELD);
            putAfter(entries, ElementusItems.STEEL_SHIELD, ElementusItems.DIARKRITE_SHIELD);
            putAfter(entries, ElementusItems.DIARKRITE_SHIELD, ElementusItems.ANTHEKTITE_SHIELD);
            // Bows
            putAfter(entries, Items.BOW, ElementusItems.STEEL_BOW);
            putAfter(entries, ElementusItems.STEEL_BOW, ElementusItems.DIARKRITE_BOW);
            putAfter(entries, ElementusItems.DIARKRITE_BOW, ElementusItems.ANTHEKTITE_BOW);
            // Special Weapon
            putAfter(entries, Items.TRIDENT, ElementusItems.DIARKRITE_CHARGE_BLADE);

            if (sniffsWeapons) {
                putAfter(entries, ItemReg.NETHERITE_GREAT_SWORD, SniffsWeaponsItems.STEEL_GREAT_SWORD);
                putAfter(entries, SniffsWeaponsItems.STEEL_GREAT_SWORD, SniffsWeaponsItems.DIARKRITE_GREAT_SWORD);
                putAfter(entries, SniffsWeaponsItems.DIARKRITE_GREAT_SWORD, SniffsWeaponsItems.ANTHEKTITE_GREAT_SWORD);

                putAfter(entries, ItemReg.NETHERITE_GREAT_AXE, SniffsWeaponsItems.STEEL_GREAT_AXE);
                putAfter(entries, SniffsWeaponsItems.STEEL_GREAT_AXE, SniffsWeaponsItems.DIARKRITE_GREAT_AXE);
                putAfter(entries, SniffsWeaponsItems.DIARKRITE_GREAT_AXE, SniffsWeaponsItems.ANTHEKTITE_GREAT_AXE);

                putAfter(entries, ItemReg.NETHERITE_GREAT_PICKAXE, SniffsWeaponsItems.STEEL_GREAT_PICKAXE);
                putAfter(entries, SniffsWeaponsItems.STEEL_GREAT_PICKAXE, SniffsWeaponsItems.DIARKRITE_GREAT_PICKAXE);
                putAfter(entries, SniffsWeaponsItems.DIARKRITE_GREAT_PICKAXE, SniffsWeaponsItems.ANTHEKTITE_GREAT_PICKAXE);

                putAfter(entries, ItemReg.NETHERITE_NAGINATA, SniffsWeaponsItems.STEEL_NAGINATA);
                putAfter(entries, SniffsWeaponsItems.STEEL_NAGINATA, SniffsWeaponsItems.DIARKRITE_NAGINATA);
                putAfter(entries, SniffsWeaponsItems.DIARKRITE_NAGINATA, SniffsWeaponsItems.ANTHEKTITE_NAGINATA);
            }
            if (vanillaClaws) {
                putAfter(entries, ItemsInit.ZIRCON_CLAWS, BanillaClawsItems.STEEL_CLAWS);
                putAfter(entries, BanillaClawsItems.STEEL_CLAWS, BanillaClawsItems.DIARKRITE_CLAWS);
                putAfter(entries, BanillaClawsItems.DIARKRITE_CLAWS, BanillaClawsItems.ANTHEKTITE_CLAWS);
            }
        }
        // Tools
        if (tab == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            putAfter(entries, Items.NETHERITE_HOE, ElementusItems.STEEL_SHOVEL);
            putAfter(entries, ElementusItems.STEEL_SHOVEL, ElementusItems.STEEL_PICKAXE);
            putAfter(entries, ElementusItems.STEEL_PICKAXE, ElementusItems.STEEL_AXE);
            putAfter(entries, ElementusItems.STEEL_AXE, ElementusItems.STEEL_HOE);

            putAfter(entries, ElementusItems.STEEL_HOE, ElementusItems.DIARKRITE_SHOVEL);
            putAfter(entries, ElementusItems.DIARKRITE_SHOVEL, ElementusItems.DIARKRITE_PICKAXE);
            putAfter(entries, ElementusItems.DIARKRITE_PICKAXE, ElementusItems.DIARKRITE_AXE);
            putAfter(entries, ElementusItems.DIARKRITE_AXE, ElementusItems.DIARKRITE_HOE);

            putAfter(entries, ElementusItems.DIARKRITE_HOE, ElementusItems.ANTHEKTITE_SHOVEL);
            putAfter(entries, ElementusItems.ANTHEKTITE_SHOVEL, ElementusItems.ANTHEKTITE_PICKAXE);
            putAfter(entries, ElementusItems.ANTHEKTITE_PICKAXE, ElementusItems.ANTHEKTITE_AXE);
            putAfter(entries, ElementusItems.ANTHEKTITE_AXE, ElementusItems.ANTHEKTITE_HOE);

            putAfter(entries, Items.BAMBOO_CHEST_RAFT, ElementusItems.MOVCADIA_BOAT);
            putAfter(entries, ElementusItems.MOVCADIA_BOAT, ElementusItems.MOVCADIA_CHEST_BOAT);
        }
        // Armor
        if (tab == CreativeModeTabs.COMBAT) {
            if  (aether) {
                putAfter(entries, com.aetherteam.aether.item.AetherItems.NETHERITE_GLOVES, ElementusItems.STEEL_HELMET);
            } else {
                putAfter(entries, Items.NETHERITE_BOOTS, ElementusItems.STEEL_HELMET);
            }
            putAfter(entries, ElementusItems.STEEL_HELMET, ElementusItems.STEEL_CHESTPLATE);
            putAfter(entries, ElementusItems.STEEL_CHESTPLATE, ElementusItems.STEEL_LEGGINGS);
            putAfter(entries, ElementusItems.STEEL_LEGGINGS, ElementusItems.STEEL_BOOTS);

            putAfter(entries, ElementusItems.STEEL_BOOTS, ElementusItems.DIARKRITE_HELMET);
            putAfter(entries, ElementusItems.DIARKRITE_HELMET, ElementusItems.DIARKRITE_CHESTPLATE);
            putAfter(entries, ElementusItems.DIARKRITE_CHESTPLATE, ElementusItems.DIARKRITE_LEGGINGS);
            putAfter(entries, ElementusItems.DIARKRITE_LEGGINGS, ElementusItems.DIARKRITE_BOOTS);

            putAfter(entries, ElementusItems.DIARKRITE_BOOTS, ElementusItems.ANTHEKTITE_HELMET);
            putAfter(entries, ElementusItems.ANTHEKTITE_HELMET, ElementusItems.ANTHEKTITE_CHESTPLATE);
            putAfter(entries, ElementusItems.ANTHEKTITE_CHESTPLATE, ElementusItems.ANTHEKTITE_LEGGINGS);
            putAfter(entries, ElementusItems.ANTHEKTITE_LEGGINGS, ElementusItems.ANTHEKTITE_BOOTS);

            putAfter(entries, ElementusItems.ANTHEKTITE_BOOTS, ElementusItems.CATALYST_CHESTPLATE);

            if (aether) {
                putAfter(entries, ElementusItems.STEEL_BOOTS, AetherItems.STEEL_GLOVES);
                putAfter(entries, ElementusItems.DIARKRITE_BOOTS, AetherItems.DIARKRITE_GLOVES);
                putAfter(entries, ElementusItems.ANTHEKTITE_BOOTS, AetherItems.ANTHEKTITE_GLOVES);
            }

            if (sniffsWeapons) {
                putAfter(entries, ElementusItems.STEEL_HELMET, SniffsWeaponsItems.STEEL_HELM);
                putAfter(entries, SniffsWeaponsItems.STEEL_HELM, SniffsWeaponsItems.STEEL_HORNED_HELM);
                putAfter(entries, SniffsWeaponsItems.STEEL_HORNED_HELM, SniffsWeaponsItems.STEEL_KABUTO);
                putAfter(entries, ElementusItems.STEEL_CHESTPLATE, SniffsWeaponsItems.STEEL_SURCOAT);
                putAfter(entries, SniffsWeaponsItems.STEEL_SURCOAT, SniffsWeaponsItems.PLATED_STEEL_CHESTPLATE);
                putAfter(entries, SniffsWeaponsItems.PLATED_STEEL_CHESTPLATE, SniffsWeaponsItems.STEEL_DO);
                putAfter(entries, SniffsWeaponsItems.STEEL_DO, SniffsWeaponsItems.CLOTHED_STEEL_CUIRASS);

                putAfter(entries, ElementusItems.DIARKRITE_HELMET, SniffsWeaponsItems.DIARKRITE_HELM);
                putAfter(entries, SniffsWeaponsItems.DIARKRITE_HELM, SniffsWeaponsItems.DIARKRITE_HORNED_HELM);
                putAfter(entries, SniffsWeaponsItems.DIARKRITE_HORNED_HELM, SniffsWeaponsItems.DIARKRITE_KABUTO);
                putAfter(entries, ElementusItems.DIARKRITE_CHESTPLATE, SniffsWeaponsItems.DIARKRITE_SURCOAT);
                putAfter(entries, SniffsWeaponsItems.DIARKRITE_SURCOAT, SniffsWeaponsItems.PLATED_DIARKRITE_CHESTPLATE);
                putAfter(entries, SniffsWeaponsItems.PLATED_DIARKRITE_CHESTPLATE, SniffsWeaponsItems.DIARKRITE_DO);
                putAfter(entries, SniffsWeaponsItems.DIARKRITE_DO, SniffsWeaponsItems.CLOTHED_DIARKRITE_CUIRASS);

                putAfter(entries, ElementusItems.ANTHEKTITE_HELMET, SniffsWeaponsItems.ANTHEKTITE_HELM);
                putAfter(entries, SniffsWeaponsItems.ANTHEKTITE_HELM, SniffsWeaponsItems.ANTHEKTITE_HORNED_HELM);
                putAfter(entries, SniffsWeaponsItems.ANTHEKTITE_HORNED_HELM, SniffsWeaponsItems.ANTHEKTITE_KABUTO);
                putAfter(entries, ElementusItems.ANTHEKTITE_CHESTPLATE, SniffsWeaponsItems.ANTHEKTITE_SURCOAT);
                putAfter(entries, SniffsWeaponsItems.ANTHEKTITE_SURCOAT, SniffsWeaponsItems.PLATED_ANTHEKTITE_CHESTPLATE);
                putAfter(entries, SniffsWeaponsItems.PLATED_ANTHEKTITE_CHESTPLATE, SniffsWeaponsItems.ANTHEKTITE_DO);
                putAfter(entries, SniffsWeaponsItems.ANTHEKTITE_DO, SniffsWeaponsItems.CLOTHED_ANTHEKTITE_CUIRASS);
            }
        }
        // Blocks
        if (tab == CreativeModeTabs.BUILDING_BLOCKS) {
            putAfter(entries, Items.NETHERITE_BLOCK, ElementusItems.STEEL_BLOCK);
            putAfter(entries, ElementusItems.STEEL_BLOCK, ElementusItems.DIARKRITE_BLOCK);
            putAfter(entries, ElementusItems.DIARKRITE_BLOCK, ElementusItems.ANTHEKTITE_BLOCK);

            putAfter(entries, ElementusItems.STEEL_BLOCK, ElementusItems.STEEL_BARS);

            putAfter(entries, ElementusItems.STEEL_BARS, ElementusItems.STEEL_TILES);
            putAfter(entries, ElementusItems.STEEL_TILES, ElementusItems.STEEL_TILE_STAIR);
            putAfter(entries, ElementusItems.STEEL_TILE_STAIR, ElementusItems.STEEL_TILE_SLAB);

            putAfter(entries, Items.BAMBOO_BUTTON, ElementusItems.MOVCADIA_LOG);
            putAfter(entries, ElementusItems.MOVCADIA_LOG, ElementusItems.MOVCADIA_WOOD);
            putAfter(entries, ElementusItems.MOVCADIA_WOOD, ElementusItems.STRIPPED_MOVCADIA_LOG);
            putAfter(entries, ElementusItems.STRIPPED_MOVCADIA_LOG, ElementusItems.STRIPPED_MOVCADIA_WOOD);
            putAfter(entries, ElementusItems.STRIPPED_MOVCADIA_WOOD, ElementusItems.MOVCADIA_PLANKS);
            putAfter(entries, ElementusItems.MOVCADIA_PLANKS, ElementusItems.MOVCADIA_STAIRS);
            putAfter(entries, ElementusItems.MOVCADIA_STAIRS, ElementusItems.MOVCADIA_SLAB);
            putAfter(entries, ElementusItems.MOVCADIA_SLAB, ElementusItems.MOVCADIA_FENCE);
            putAfter(entries, ElementusItems.MOVCADIA_FENCE, ElementusItems.MOVCADIA_FENCE_GATE);
            putAfter(entries, ElementusItems.MOVCADIA_FENCE_GATE, ElementusItems.MOVCADIA_DOOR);
            putAfter(entries, ElementusItems.MOVCADIA_DOOR, ElementusItems.MOVCADIA_TRAPDOOR);
            putAfter(entries, ElementusItems.MOVCADIA_TRAPDOOR, ElementusItems.MOVCADIA_PRESSURE_PLATE);
            putAfter(entries, ElementusItems.MOVCADIA_PRESSURE_PLATE, ElementusItems.MOVCADIA_BUTTON);
        }
        if (tab == CreativeModeTabs.NATURAL_BLOCKS) {
            putAfter(entries, Items.CHERRY_LOG, ElementusItems.MOVCADIA_LOG);
            putAfter(entries, Items.FLOWERING_AZALEA_LEAVES, ElementusItems.MOVCADIA_LEAVES);
            putAfter(entries, ElementusItems.MOVCADIA_LEAVES, ElementusItems.FLOWERING_MOVCADIA_LEAVES);
            putAfter(entries, Items.FLOWERING_AZALEA, ElementusItems.MOVCADIA_SAPLING);
            putAfter(entries, Items.ROOTED_DIRT, ElementusItems.MOVCADIA_ROOTED_DIRT);
        }
        if (tab == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            putAfter(entries, Items.CHEST, ElementusItems.MOVCADIA_CHEST);
            putAfter(entries, Items.BAMBOO_HANGING_SIGN, ElementusItems.MOVCADIA_SIGN);
            putAfter(entries, ElementusItems.MOVCADIA_SIGN, ElementusItems.MOVCADIA_HANGING_SIGN);
            putAfter(entries, ElementusItems.MOVCADIA_SIGN, ElementusItems.STURDY_MOVCADIA_SIGN);
        }
        if (tab == CreativeModeTabs.REDSTONE_BLOCKS) {
            putAfter(entries, Items.CHEST, ElementusItems.MOVCADIA_CHEST);
        }

        //Modded Tabs
        if (farmersDelight) {
            if (tab == ModCreativeTabs.TAB_FARMERS_DELIGHT.getKey()) {
                putAfter(entries, ModItems.BAMBOO_CABINET, FarmersDelightItems.MOVCADIA_CABINET);
                putAfter(entries, ModItems.GOLDEN_KNIFE, FarmersDelightItems.STEEL_KNIFE);
                putAfter(entries, FarmersDelightItems.STEEL_KNIFE, FarmersDelightItems.DIARKRITE_KNIFE);
                putAfter(entries, FarmersDelightItems.DIARKRITE_KNIFE, FarmersDelightItems.ANTHEKTITE_KNIFE);
            }
        }
        if (nethersDelight) {
            if (tab == NDCreativeTab.NETHERS_DELIGHT_TAB.getKey()) {
                putAfter(entries, NDItems.NETHERITE_MACHETE, NethersDelightItems.STEEL_MACHETE);
                putAfter(entries, NethersDelightItems.STEEL_MACHETE, NethersDelightItems.DIARKRITE_MACHETE);
                putAfter(entries, NethersDelightItems.DIARKRITE_MACHETE, NethersDelightItems.ANTHEKTITE_MACHETE);
            }
        }
        if (piercingPaxels) {
            if (tab == PiercingPaxels.PIERCING_PAXELS_ITEM_GROUP.getKey()) {
                putAfter(entries, PiercingPaxels.NETHERITE_PAXEL, PiercingPaxelsItems.STEEL_PAXEL);
                putAfter(entries, PiercingPaxelsItems.STEEL_PAXEL, PiercingPaxelsItems.DIARKRITE_PAXEL);
                putAfter(entries, PiercingPaxelsItems.DIARKRITE_PAXEL, PiercingPaxelsItems.ANTHEKTITE_PAXEL);
                putAfter(entries, PiercingPaxels.NETHERITE_UPGRADE_KIT, PiercingPaxelsItems.DIARKRITE_UPGRADE_KIT);
                putAfter(entries, PiercingPaxelsItems.DIARKRITE_UPGRADE_KIT, PiercingPaxelsItems.ANTHEKTITE_UPGRADE_KIT);
            }
        }
        if (ironsSpellbooks) {
            if (tab == CreativeTabRegistry.EQUIPMENT_TAB.getKey()) {
                putAfter(entries, ItemRegistry.DRUIDIC_SPELL_BOOK, IronsSpellbooksItems.STEEL_SPELL_BOOK);
                putAfter(entries, IronsSpellbooksItems.STEEL_SPELL_BOOK, IronsSpellbooksItems.DIARKRITE_SPELL_BOOK);
                putAfter(entries, IronsSpellbooksItems.DIARKRITE_SPELL_BOOK, IronsSpellbooksItems.ANTHEKTITE_SPELL_BOOK);
                putAfter(entries, ItemRegistry.NETHERITE_MAGE_BOOTS, IronsSpellbooksItems.DIARKRITE_MAGE_HELMET);
                putAfter(entries, IronsSpellbooksItems.DIARKRITE_MAGE_HELMET, IronsSpellbooksItems.DIARKRITE_MAGE_CHESTPLATE);
                putAfter(entries, IronsSpellbooksItems.DIARKRITE_MAGE_CHESTPLATE, IronsSpellbooksItems.DIARKRITE_MAGE_LEGGINGS);
                putAfter(entries, IronsSpellbooksItems.DIARKRITE_MAGE_LEGGINGS, IronsSpellbooksItems.DIARKRITE_MAGE_BOOTS);
                putAfter(entries, IronsSpellbooksItems.DIARKRITE_MAGE_BOOTS, IronsSpellbooksItems.ANTHEKTITE_MAGE_HELMET);
                putAfter(entries, IronsSpellbooksItems.ANTHEKTITE_MAGE_HELMET, IronsSpellbooksItems.ANTHEKTITE_MAGE_CHESTPLATE);
                putAfter(entries, IronsSpellbooksItems.ANTHEKTITE_MAGE_CHESTPLATE, IronsSpellbooksItems.ANTHEKTITE_MAGE_LEGGINGS);
                putAfter(entries, IronsSpellbooksItems.ANTHEKTITE_MAGE_LEGGINGS, IronsSpellbooksItems.ANTHEKTITE_MAGE_BOOTS);
            }
        }
        if (simplySwords) {
            if (tab == SimplySwords.SIMPLYSWORDS.getKey()) {
                putAfter(entries, ItemsRegistry.RUNIC_HALBERD, SimplySwordsItems.STEEL_LONGSWORD);
                putAfter(entries, SimplySwordsItems.STEEL_LONGSWORD, SimplySwordsItems.STEEL_TWINBLADE);
                putAfter(entries, SimplySwordsItems.STEEL_TWINBLADE, SimplySwordsItems.STEEL_RAPIER);
                putAfter(entries, SimplySwordsItems.STEEL_RAPIER, SimplySwordsItems.STEEL_KATANA);
                putAfter(entries, SimplySwordsItems.STEEL_KATANA, SimplySwordsItems.STEEL_SAI);
                putAfter(entries, SimplySwordsItems.STEEL_SAI, SimplySwordsItems.STEEL_SPEAR);
                putAfter(entries, SimplySwordsItems.STEEL_SPEAR, SimplySwordsItems.STEEL_GLAIVE);
                putAfter(entries, SimplySwordsItems.STEEL_GLAIVE, SimplySwordsItems.STEEL_CUTLASS);
                putAfter(entries, SimplySwordsItems.STEEL_CUTLASS, SimplySwordsItems.STEEL_CLAYMORE);
                putAfter(entries, SimplySwordsItems.STEEL_CLAYMORE, SimplySwordsItems.STEEL_CHAKRAM);
                putAfter(entries, SimplySwordsItems.STEEL_CHAKRAM, SimplySwordsItems.STEEL_GREATAXE);
                putAfter(entries, SimplySwordsItems.STEEL_GREATAXE, SimplySwordsItems.STEEL_GREATHAMMER);
                putAfter(entries, SimplySwordsItems.STEEL_GREATHAMMER, SimplySwordsItems.STEEL_WARGLAIVE);
                putAfter(entries, SimplySwordsItems.STEEL_WARGLAIVE, SimplySwordsItems.STEEL_SCYTHE);
                putAfter(entries, SimplySwordsItems.STEEL_SCYTHE, SimplySwordsItems.STEEL_HALBERD);
                putAfter(entries, SimplySwordsItems.STEEL_HALBERD, SimplySwordsItems.DIARKRITE_LONGSWORD);
                putAfter(entries, SimplySwordsItems.DIARKRITE_LONGSWORD, SimplySwordsItems.DIARKRITE_TWINBLADE);
                putAfter(entries, SimplySwordsItems.DIARKRITE_TWINBLADE, SimplySwordsItems.DIARKRITE_RAPIER);
                putAfter(entries, SimplySwordsItems.DIARKRITE_RAPIER, SimplySwordsItems.DIARKRITE_KATANA);
                putAfter(entries, SimplySwordsItems.DIARKRITE_KATANA, SimplySwordsItems.DIARKRITE_SAI);
                putAfter(entries, SimplySwordsItems.DIARKRITE_SAI, SimplySwordsItems.DIARKRITE_SPEAR);
                putAfter(entries, SimplySwordsItems.DIARKRITE_SPEAR, SimplySwordsItems.DIARKRITE_GLAIVE);
                putAfter(entries, SimplySwordsItems.DIARKRITE_GLAIVE, SimplySwordsItems.DIARKRITE_CUTLASS);
                putAfter(entries, SimplySwordsItems.DIARKRITE_CUTLASS, SimplySwordsItems.DIARKRITE_CLAYMORE);
                putAfter(entries, SimplySwordsItems.DIARKRITE_CLAYMORE, SimplySwordsItems.DIARKRITE_CHAKRAM);
                putAfter(entries, SimplySwordsItems.DIARKRITE_CHAKRAM, SimplySwordsItems.DIARKRITE_GREATAXE);
                putAfter(entries, SimplySwordsItems.DIARKRITE_GREATAXE, SimplySwordsItems.DIARKRITE_GREATHAMMER);
                putAfter(entries, SimplySwordsItems.DIARKRITE_GREATHAMMER, SimplySwordsItems.DIARKRITE_WARGLAIVE);
                putAfter(entries, SimplySwordsItems.DIARKRITE_WARGLAIVE, SimplySwordsItems.DIARKRITE_SCYTHE);
                putAfter(entries, SimplySwordsItems.DIARKRITE_SCYTHE, SimplySwordsItems.DIARKRITE_HALBERD);
                putAfter(entries, SimplySwordsItems.DIARKRITE_HALBERD, SimplySwordsItems.ANTHEKTITE_LONGSWORD);
                putAfter(entries, SimplySwordsItems.ANTHEKTITE_LONGSWORD, SimplySwordsItems.ANTHEKTITE_TWINBLADE);
                putAfter(entries, SimplySwordsItems.ANTHEKTITE_TWINBLADE, SimplySwordsItems.ANTHEKTITE_RAPIER);
                putAfter(entries, SimplySwordsItems.ANTHEKTITE_RAPIER, SimplySwordsItems.ANTHEKTITE_KATANA);
                putAfter(entries, SimplySwordsItems.ANTHEKTITE_KATANA, SimplySwordsItems.ANTHEKTITE_SAI);
                putAfter(entries, SimplySwordsItems.ANTHEKTITE_SAI, SimplySwordsItems.ANTHEKTITE_SPEAR);
                putAfter(entries, SimplySwordsItems.ANTHEKTITE_SPEAR, SimplySwordsItems.ANTHEKTITE_GLAIVE);
                putAfter(entries, SimplySwordsItems.ANTHEKTITE_GLAIVE, SimplySwordsItems.ANTHEKTITE_CUTLASS);
                putAfter(entries, SimplySwordsItems.ANTHEKTITE_CUTLASS, SimplySwordsItems.ANTHEKTITE_CLAYMORE);
                putAfter(entries, SimplySwordsItems.ANTHEKTITE_CLAYMORE, SimplySwordsItems.ANTHEKTITE_CHAKRAM);
                putAfter(entries, SimplySwordsItems.ANTHEKTITE_CHAKRAM, SimplySwordsItems.ANTHEKTITE_GREATAXE);
                putAfter(entries, SimplySwordsItems.ANTHEKTITE_GREATAXE, SimplySwordsItems.ANTHEKTITE_GREATHAMMER);
                putAfter(entries, SimplySwordsItems.ANTHEKTITE_GREATHAMMER, SimplySwordsItems.ANTHEKTITE_WARGLAIVE);
                putAfter(entries, SimplySwordsItems.ANTHEKTITE_WARGLAIVE, SimplySwordsItems.ANTHEKTITE_SCYTHE);
                putAfter(entries, SimplySwordsItems.ANTHEKTITE_SCYTHE, SimplySwordsItems.ANTHEKTITE_HALBERD);
            }
        }
        if (samuraiDynasty) {
            if (tab == net.veroxuniverse.samurai_dynasty.registry.CreativeTabRegistry.TAB.getKey()) {
                putAfter(entries, net.veroxuniverse.samurai_dynasty.registry.ItemsRegistry.GRAY_SAMURAI_BOOTS_MASTER, EpicSamuraiItems.STEEL_SAMURAI_HELMET);
                putAfter(entries, EpicSamuraiItems.STEEL_SAMURAI_HELMET, EpicSamuraiItems.STEEL_SAMURAI_CHESTPLATE);
                putAfter(entries, EpicSamuraiItems.STEEL_SAMURAI_CHESTPLATE, EpicSamuraiItems.STEEL_SAMURAI_LEGGINGS);
                putAfter(entries, EpicSamuraiItems.STEEL_SAMURAI_LEGGINGS, EpicSamuraiItems.STEEL_SAMURAI_BOOTS);
                putAfter(entries, EpicSamuraiItems.STEEL_SAMURAI_BOOTS, EpicSamuraiItems.STEEL_SAMURAI_HELMET_LIGHT);
                putAfter(entries, EpicSamuraiItems.STEEL_SAMURAI_HELMET_LIGHT, EpicSamuraiItems.STEEL_SAMURAI_CHESTPLATE_LIGHT);
                putAfter(entries, EpicSamuraiItems.STEEL_SAMURAI_CHESTPLATE_LIGHT, EpicSamuraiItems.STEEL_SAMURAI_LEGGINGS_LIGHT);
                putAfter(entries, EpicSamuraiItems.STEEL_SAMURAI_LEGGINGS_LIGHT, EpicSamuraiItems.STEEL_SAMURAI_BOOTS_LIGHT);
                putAfter(entries, EpicSamuraiItems.STEEL_SAMURAI_BOOTS_LIGHT, EpicSamuraiItems.STEEL_SAMURAI_HELMET_MASTER);
                putAfter(entries, EpicSamuraiItems.STEEL_SAMURAI_HELMET_MASTER, EpicSamuraiItems.STEEL_SAMURAI_CHESTPLATE_MASTER);
                putAfter(entries, EpicSamuraiItems.STEEL_SAMURAI_CHESTPLATE_MASTER, EpicSamuraiItems.STEEL_SAMURAI_LEGGINGS_MASTER);
                putAfter(entries, EpicSamuraiItems.STEEL_SAMURAI_LEGGINGS_MASTER, EpicSamuraiItems.STEEL_SAMURAI_BOOTS_MASTER);

                putAfter(entries, EpicSamuraiItems.STEEL_SAMURAI_BOOTS_MASTER, EpicSamuraiItems.DIARKRITE_SAMURAI_HELMET);
                putAfter(entries, EpicSamuraiItems.DIARKRITE_SAMURAI_HELMET, EpicSamuraiItems.DIARKRITE_SAMURAI_CHESTPLATE);
                putAfter(entries, EpicSamuraiItems.DIARKRITE_SAMURAI_CHESTPLATE, EpicSamuraiItems.DIARKRITE_SAMURAI_LEGGINGS);
                putAfter(entries, EpicSamuraiItems.DIARKRITE_SAMURAI_LEGGINGS, EpicSamuraiItems.DIARKRITE_SAMURAI_BOOTS);
                putAfter(entries, EpicSamuraiItems.DIARKRITE_SAMURAI_BOOTS, EpicSamuraiItems.DIARKRITE_SAMURAI_HELMET_LIGHT);
                putAfter(entries, EpicSamuraiItems.DIARKRITE_SAMURAI_HELMET_LIGHT, EpicSamuraiItems.DIARKRITE_SAMURAI_CHESTPLATE_LIGHT);
                putAfter(entries, EpicSamuraiItems.DIARKRITE_SAMURAI_CHESTPLATE_LIGHT, EpicSamuraiItems.DIARKRITE_SAMURAI_LEGGINGS_LIGHT);
                putAfter(entries, EpicSamuraiItems.DIARKRITE_SAMURAI_LEGGINGS_LIGHT, EpicSamuraiItems.DIARKRITE_SAMURAI_BOOTS_LIGHT);
                putAfter(entries, EpicSamuraiItems.DIARKRITE_SAMURAI_BOOTS_LIGHT, EpicSamuraiItems.DIARKRITE_SAMURAI_HELMET_MASTER);
                putAfter(entries, EpicSamuraiItems.DIARKRITE_SAMURAI_HELMET_MASTER, EpicSamuraiItems.DIARKRITE_SAMURAI_CHESTPLATE_MASTER);
                putAfter(entries, EpicSamuraiItems.DIARKRITE_SAMURAI_CHESTPLATE_MASTER, EpicSamuraiItems.DIARKRITE_SAMURAI_LEGGINGS_MASTER);
                putAfter(entries, EpicSamuraiItems.DIARKRITE_SAMURAI_LEGGINGS_MASTER, EpicSamuraiItems.DIARKRITE_SAMURAI_BOOTS_MASTER);

                putAfter(entries, EpicSamuraiItems.DIARKRITE_SAMURAI_BOOTS_MASTER, EpicSamuraiItems.ANTHEKTITE_SAMURAI_HELMET);
                putAfter(entries, EpicSamuraiItems.ANTHEKTITE_SAMURAI_HELMET, EpicSamuraiItems.ANTHEKTITE_SAMURAI_CHESTPLATE);
                putAfter(entries, EpicSamuraiItems.ANTHEKTITE_SAMURAI_CHESTPLATE, EpicSamuraiItems.ANTHEKTITE_SAMURAI_LEGGINGS);
                putAfter(entries, EpicSamuraiItems.ANTHEKTITE_SAMURAI_LEGGINGS, EpicSamuraiItems.ANTHEKTITE_SAMURAI_BOOTS);
                putAfter(entries, EpicSamuraiItems.ANTHEKTITE_SAMURAI_BOOTS, EpicSamuraiItems.ANTHEKTITE_SAMURAI_HELMET_LIGHT);
                putAfter(entries, EpicSamuraiItems.ANTHEKTITE_SAMURAI_HELMET_LIGHT, EpicSamuraiItems.ANTHEKTITE_SAMURAI_CHESTPLATE_LIGHT);
                putAfter(entries, EpicSamuraiItems.ANTHEKTITE_SAMURAI_CHESTPLATE_LIGHT, EpicSamuraiItems.ANTHEKTITE_SAMURAI_LEGGINGS_LIGHT);
                putAfter(entries, EpicSamuraiItems.ANTHEKTITE_SAMURAI_LEGGINGS_LIGHT, EpicSamuraiItems.ANTHEKTITE_SAMURAI_BOOTS_LIGHT);
                putAfter(entries, EpicSamuraiItems.ANTHEKTITE_SAMURAI_BOOTS_LIGHT, EpicSamuraiItems.ANTHEKTITE_SAMURAI_HELMET_MASTER);
                putAfter(entries, EpicSamuraiItems.ANTHEKTITE_SAMURAI_HELMET_MASTER, EpicSamuraiItems.ANTHEKTITE_SAMURAI_CHESTPLATE_MASTER);
                putAfter(entries, EpicSamuraiItems.ANTHEKTITE_SAMURAI_CHESTPLATE_MASTER, EpicSamuraiItems.ANTHEKTITE_SAMURAI_LEGGINGS_MASTER);
                putAfter(entries, EpicSamuraiItems.ANTHEKTITE_SAMURAI_LEGGINGS_MASTER, EpicSamuraiItems.ANTHEKTITE_SAMURAI_BOOTS_MASTER);
            }
        }
        if (twigs) {
            if (tab == TwigsCreativeModeTabs.TWIG.getKey()) {
                putAfter(entries, com.ninni.twigs.registry.TwigsItems.BAMBOO_TABLE, TwigsItems.MOVCADIA_TABLE);
            }
        }
        if (witherStormMod) {
            if (tab == WitherStormModItemTabs.CREATIVE_TAB.getKey()) {
                putAfter(entries, WitherStormModItems.FORMIDI_BLADE, WitherstormModItems.STEEL_CMD_SWORD);
                putAfter(entries, WitherstormModItems.STEEL_CMD_SWORD, WitherstormModItems.STEEL_CMD_PICKAXE);
                putAfter(entries, WitherstormModItems.STEEL_CMD_PICKAXE, WitherstormModItems.STEEL_CMD_AXE);
                putAfter(entries, WitherstormModItems.STEEL_CMD_AXE, WitherstormModItems.STEEL_CMD_SHOVEL);
                putAfter(entries, WitherstormModItems.STEEL_CMD_SHOVEL, WitherstormModItems.STEEL_CMD_HOE);
                putAfter(entries, WitherstormModItems.STEEL_CMD_HOE, WitherstormModItems.DIARKRITE_CMD_SWORD);
                putAfter(entries, WitherstormModItems.DIARKRITE_CMD_SWORD, WitherstormModItems.DIARKRITE_CMD_PICKAXE);
                putAfter(entries, WitherstormModItems.DIARKRITE_CMD_PICKAXE, WitherstormModItems.DIARKRITE_CMD_AXE);
                putAfter(entries, WitherstormModItems.DIARKRITE_CMD_AXE, WitherstormModItems.DIARKRITE_CMD_SHOVEL);
                putAfter(entries, WitherstormModItems.DIARKRITE_CMD_SHOVEL, WitherstormModItems.DIARKRITE_CMD_HOE);
                putAfter(entries, WitherstormModItems.DIARKRITE_CMD_HOE, WitherstormModItems.ANTHEKTITE_CMD_SWORD);
                putAfter(entries, WitherstormModItems.ANTHEKTITE_CMD_SWORD, WitherstormModItems.ANTHEKTITE_CMD_PICKAXE);
                putAfter(entries, WitherstormModItems.ANTHEKTITE_CMD_PICKAXE, WitherstormModItems.ANTHEKTITE_CMD_AXE);
                putAfter(entries, WitherstormModItems.ANTHEKTITE_CMD_AXE, WitherstormModItems.ANTHEKTITE_CMD_SHOVEL);
                putAfter(entries, WitherstormModItems.ANTHEKTITE_CMD_SHOVEL, WitherstormModItems.ANTHEKTITE_CMD_HOE);
            }
        }

        if (create && tab.equals(AllCreativeModeTabs.BASE_CREATIVE_TAB.getKey())) {
//            putAfter(entries, (Item) AllItems.CRUSHED_ZINC, ElementusItems.CRUSHED_REMNANT);
            entries.put(ElementusItems.CRUSHED_REMNANT.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }


    //Credit: oreganised mod, GitHub: https://github.com/Xaidee/oreganised
    private static void putAfter(MutableHashedLinkedMap<ItemStack, CreativeModeTab.TabVisibility> entries, Item after, Supplier<? extends ItemLike> supplier) {
        ItemLike key = supplier.get();
        entries.putAfter(new ItemStack(after), new ItemStack(key), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
    }
    private static void putAfter(MutableHashedLinkedMap<ItemStack, CreativeModeTab.TabVisibility> entries, Supplier<? extends  ItemLike> after, Supplier<? extends ItemLike> supplier) {
        ItemLike key1 = supplier.get();
        ItemLike key2 = after.get();
        entries.putAfter(new ItemStack(key2), new ItemStack(key1), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
    }

    private static void putBefore(MutableHashedLinkedMap<ItemStack, CreativeModeTab.TabVisibility> entries, ItemLike after, Supplier<? extends ItemLike> supplier) {
        ItemLike key = supplier.get();
        entries.putBefore(new ItemStack(after), new ItemStack(key), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
    }
    private static void putBefore(MutableHashedLinkedMap<ItemStack, CreativeModeTab.TabVisibility> entries, Supplier<? extends  ItemLike> after, Supplier<? extends ItemLike> supplier) {
        ItemLike key1 = supplier.get();
        ItemLike key2 = after.get();
        entries.putBefore(new ItemStack(key2), new ItemStack(key1), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
    }


    public static final RegistryObject<CreativeModeTab> ELEMENTUS_MOD_INTEGRATION = CREATIVE_MODE_TABS.register("elementus_mod_integration",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ElementusItems.DIARKRITE_INGOT.get()))
                    .title(Component.translatable("creativetab.elementus_mod_integration"))
                    .displayItems((parameters, output) -> {
                        if (farmersDelight) FarmersDelightItems.ITEMS.getEntries().stream().map(RegistryObject::get).forEach(output::accept);
                        if (piercingPaxels) PiercingPaxelsItems.ITEMS.getEntries().stream().map(RegistryObject::get).forEach(output::accept);
                        if (nethersDelight) NethersDelightItems.ITEMS.getEntries().stream().map(RegistryObject::get).forEach(output::accept);
                        if (ironsSpellbooks) IronsSpellbooksItems.ITEMS.getEntries().stream().map(RegistryObject::get).forEach(output::accept);
                        if (aether) AetherItems.ITEMS.getEntries().stream().map(RegistryObject::get).forEach(output::accept);
                        if (simplySwords) SimplySwordsItems.ITEMS.getEntries().stream().map(RegistryObject::get).forEach(output::accept);
                        if (sniffsWeapons) SniffsWeaponsItems.ITEMS.getEntries().stream().map(RegistryObject::get).forEach(output::accept);
                        if (advancedNetherite) AdvancedNetheriteItems.ITEMS.getEntries().stream().map(RegistryObject::get).forEach(output::accept);
                        if (samuraiDynasty) EpicSamuraiItems.ITEMS.getEntries().stream().map(RegistryObject::get).forEach(output::accept);
                        if (twigs) TwigsItems.ITEMS.getEntries().stream().map(RegistryObject::get).forEach(output::accept);
                        if (witherStormMod) WitherstormModItems.ITEMS.getEntries().stream().map(RegistryObject::get).forEach(output::accept);
                        if (vanillaClaws) BanillaClawsItems.ITEMS.getEntries().stream().map(RegistryObject::get).forEach(output::accept);
                        if (create) output.accept(ElementusItems.CRUSHED_REMNANT.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
