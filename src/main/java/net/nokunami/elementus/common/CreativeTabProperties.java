package net.nokunami.elementus.common;

import com.aetherteam.aether.item.AetherItems;
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
import net.nokunami.elementus.common.compat.advancednetherite.ANModItems;
import net.nokunami.elementus.common.compat.epicsamurai.ESModItems;
import net.nokunami.elementus.common.compat.farmersdelight.FarmersDelightItems;
import net.nokunami.elementus.common.compat.farmersdelight.NethersDelightItems;
import net.nokunami.elementus.common.compat.ironsspellbooks.ISSModItems;
import net.nokunami.elementus.common.compat.piercingpaxels.PPModItems;
import net.nokunami.elementus.common.compat.simplyswords.SSModItems;
import net.nokunami.elementus.common.compat.sniffsweapons.SWModItems;
import net.nokunami.elementus.common.compat.theaether.TAModItems;
import net.nokunami.elementus.common.compat.twigs.TWModItems;
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
            putAfter(entries, ElementusItems.MOVCADIA_BERRIES, ElementusItems.GLISTERING_MOVCADIA_BERRIES);
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
            putAfter(entries, ElementusItems.ANTHEKTITE_SWORD, ElementusItems.MOVCADIA_SWORD);

            // Axes
            if  (sniffsWeapons) {
                putBefore(entries, ItemReg.WOODEN_GREAT_AXE, ElementusItems.STEEL_AXE);
            } else {
                putAfter(entries, Items.NETHERITE_AXE, ElementusItems.STEEL_AXE);
            }
            putAfter(entries, ElementusItems.STEEL_AXE, ElementusItems.DIARKRITE_AXE);
            putAfter(entries, ElementusItems.DIARKRITE_AXE, ElementusItems.ANTHEKTITE_AXE);
            putAfter(entries, ElementusItems.ANTHEKTITE_AXE, ElementusItems.MOVCADIA_AXE);

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
            putAfter(entries, ElementusItems.DIARKRITE_CHARGE_BLADE, ElementusItems.ANTHEKTITE_CHARGE_BLADE);

            if (sniffsWeapons) {
                putAfter(entries, ItemReg.NETHERITE_GREAT_SWORD, SWModItems.STEEL_GREAT_SWORD);
                putAfter(entries, SWModItems.STEEL_GREAT_SWORD, SWModItems.DIARKRITE_GREAT_SWORD);
                putAfter(entries, SWModItems.DIARKRITE_GREAT_SWORD, SWModItems.ANTHEKTITE_GREAT_SWORD);

                putAfter(entries, ItemReg.NETHERITE_GREAT_AXE, SWModItems.STEEL_GREAT_AXE);
                putAfter(entries, SWModItems.STEEL_GREAT_AXE, SWModItems.DIARKRITE_GREAT_AXE);
                putAfter(entries, SWModItems.DIARKRITE_GREAT_AXE, SWModItems.ANTHEKTITE_GREAT_AXE);

                putAfter(entries, ItemReg.NETHERITE_GREAT_PICKAXE, SWModItems.STEEL_GREAT_PICKAXE);
                putAfter(entries, SWModItems.STEEL_GREAT_PICKAXE, SWModItems.DIARKRITE_GREAT_PICKAXE);
                putAfter(entries, SWModItems.DIARKRITE_GREAT_PICKAXE, SWModItems.ANTHEKTITE_GREAT_PICKAXE);

                putAfter(entries, ItemReg.NETHERITE_NAGINATA, SWModItems.STEEL_NAGINATA);
                putAfter(entries, SWModItems.STEEL_NAGINATA, SWModItems.DIARKRITE_NAGINATA);
                putAfter(entries, SWModItems.DIARKRITE_NAGINATA, SWModItems.ANTHEKTITE_NAGINATA);
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

            putAfter(entries, ElementusItems.ANTHEKTITE_HOE, ElementusItems.MOVCADIA_SHOVEL);
            putAfter(entries, ElementusItems.MOVCADIA_SHOVEL, ElementusItems.MOVCADIA_PICKAXE);
            putAfter(entries, ElementusItems.MOVCADIA_PICKAXE, ElementusItems.MOVCADIA_AXE);
            putAfter(entries, ElementusItems.MOVCADIA_AXE, ElementusItems.MOVCADIA_HOE);

            putAfter(entries, Items.BAMBOO_CHEST_RAFT, ElementusItems.MOVCADIA_BOAT);
            putAfter(entries, ElementusItems.MOVCADIA_BOAT, ElementusItems.MOVCADIA_CHEST_BOAT);
        }
        // Armor
        if (tab == CreativeModeTabs.COMBAT) {
            if  (aether) {
                putAfter(entries, AetherItems.NETHERITE_GLOVES, ElementusItems.STEEL_HELMET);
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
                putAfter(entries, ElementusItems.STEEL_BOOTS, TAModItems.STEEL_GLOVES);
                putAfter(entries, ElementusItems.DIARKRITE_BOOTS, TAModItems.DIARKRITE_GLOVES);
                putAfter(entries, ElementusItems.ANTHEKTITE_BOOTS, TAModItems.ANTHEKTITE_GLOVES);
            }

            if (sniffsWeapons) {
                putAfter(entries, ElementusItems.STEEL_HELMET, SWModItems.STEEL_HELM);
                putAfter(entries, SWModItems.STEEL_HELM, SWModItems.STEEL_HORNED_HELM);
                putAfter(entries, SWModItems.STEEL_HORNED_HELM, SWModItems.STEEL_KABUTO);
                putAfter(entries, ElementusItems.STEEL_CHESTPLATE, SWModItems.STEEL_SURCOAT);
                putAfter(entries, SWModItems.STEEL_SURCOAT, SWModItems.PLATED_STEEL_CHESTPLATE);
                putAfter(entries, SWModItems.PLATED_STEEL_CHESTPLATE, SWModItems.STEEL_DO);
                putAfter(entries, SWModItems.STEEL_DO, SWModItems.CLOTHED_STEEL_CUIRASS);

                putAfter(entries, ElementusItems.DIARKRITE_HELMET, SWModItems.DIARKRITE_HELM);
                putAfter(entries, SWModItems.DIARKRITE_HELM, SWModItems.DIARKRITE_HORNED_HELM);
                putAfter(entries, SWModItems.DIARKRITE_HORNED_HELM, SWModItems.DIARKRITE_KABUTO);
                putAfter(entries, ElementusItems.DIARKRITE_CHESTPLATE, SWModItems.DIARKRITE_SURCOAT);
                putAfter(entries, SWModItems.DIARKRITE_SURCOAT, SWModItems.PLATED_DIARKRITE_CHESTPLATE);
                putAfter(entries, SWModItems.PLATED_DIARKRITE_CHESTPLATE, SWModItems.DIARKRITE_DO);
                putAfter(entries, SWModItems.DIARKRITE_DO, SWModItems.CLOTHED_DIARKRITE_CUIRASS);

                putAfter(entries, ElementusItems.ANTHEKTITE_HELMET, SWModItems.ANTHEKTITE_HELM);
                putAfter(entries, SWModItems.ANTHEKTITE_HELM, SWModItems.ANTHEKTITE_HORNED_HELM);
                putAfter(entries, SWModItems.ANTHEKTITE_HORNED_HELM, SWModItems.ANTHEKTITE_KABUTO);
                putAfter(entries, ElementusItems.ANTHEKTITE_CHESTPLATE, SWModItems.ANTHEKTITE_SURCOAT);
                putAfter(entries, SWModItems.ANTHEKTITE_SURCOAT, SWModItems.PLATED_ANTHEKTITE_CHESTPLATE);
                putAfter(entries, SWModItems.PLATED_ANTHEKTITE_CHESTPLATE, SWModItems.ANTHEKTITE_DO);
                putAfter(entries, SWModItems.ANTHEKTITE_DO, SWModItems.CLOTHED_ANTHEKTITE_CUIRASS);
            }

            putAfter(entries, Items.DIAMOND_HORSE_ARMOR, ElementusItems.REINFORCED_PLATING_GOLEM_UPGRADE);
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
                putAfter(entries, PiercingPaxels.NETHERITE_PAXEL, PPModItems.STEEL_PAXEL);
                putAfter(entries, PPModItems.STEEL_PAXEL, PPModItems.DIARKRITE_PAXEL);
                putAfter(entries, PPModItems.DIARKRITE_PAXEL, PPModItems.ANTHEKTITE_PAXEL);
                putAfter(entries, PiercingPaxels.NETHERITE_UPGRADE_KIT, PPModItems.DIARKRITE_UPGRADE_KIT);
                putAfter(entries, PPModItems.DIARKRITE_UPGRADE_KIT, PPModItems.ANTHEKTITE_UPGRADE_KIT);
            }
        }
        if (ironsSpellbooks) {
            if (tab == CreativeTabRegistry.EQUIPMENT_TAB.getKey()) {
                putAfter(entries, ItemRegistry.DRUIDIC_SPELL_BOOK, ISSModItems.STEEL_SPELL_BOOK);
                putAfter(entries, ISSModItems.STEEL_SPELL_BOOK, ISSModItems.DIARKRITE_SPELL_BOOK);
                putAfter(entries, ISSModItems.DIARKRITE_SPELL_BOOK, ISSModItems.ANTHEKTITE_SPELL_BOOK);
                putAfter(entries, ItemRegistry.NETHERITE_MAGE_BOOTS, ISSModItems.DIARKRITE_MAGE_HELMET);
                putAfter(entries, ISSModItems.DIARKRITE_MAGE_HELMET, ISSModItems.DIARKRITE_MAGE_CHESTPLATE);
                putAfter(entries, ISSModItems.DIARKRITE_MAGE_CHESTPLATE, ISSModItems.DIARKRITE_MAGE_LEGGINGS);
                putAfter(entries, ISSModItems.DIARKRITE_MAGE_LEGGINGS, ISSModItems.DIARKRITE_MAGE_BOOTS);
                putAfter(entries, ISSModItems.DIARKRITE_MAGE_BOOTS, ISSModItems.ANTHEKTITE_MAGE_HELMET);
                putAfter(entries, ISSModItems.ANTHEKTITE_MAGE_HELMET, ISSModItems.ANTHEKTITE_MAGE_CHESTPLATE);
                putAfter(entries, ISSModItems.ANTHEKTITE_MAGE_CHESTPLATE, ISSModItems.ANTHEKTITE_MAGE_LEGGINGS);
                putAfter(entries, ISSModItems.ANTHEKTITE_MAGE_LEGGINGS, ISSModItems.ANTHEKTITE_MAGE_BOOTS);
            }
        }
        if (simplySwords) {
            if (tab == SimplySwords.SIMPLYSWORDS.getKey()) {
                putAfter(entries, ItemsRegistry.RUNIC_HALBERD, SSModItems.STEEL_LONGSWORD);
                putAfter(entries, SSModItems.STEEL_LONGSWORD, SSModItems.STEEL_TWINBLADE);
                putAfter(entries, SSModItems.STEEL_TWINBLADE, SSModItems.STEEL_RAPIER);
                putAfter(entries, SSModItems.STEEL_RAPIER, SSModItems.STEEL_KATANA);
                putAfter(entries, SSModItems.STEEL_KATANA, SSModItems.STEEL_SAI);
                putAfter(entries, SSModItems.STEEL_SAI, SSModItems.STEEL_SPEAR);
                putAfter(entries, SSModItems.STEEL_SPEAR, SSModItems.STEEL_GLAIVE);
                putAfter(entries, SSModItems.STEEL_GLAIVE, SSModItems.STEEL_CUTLASS);
                putAfter(entries, SSModItems.STEEL_CUTLASS, SSModItems.STEEL_CLAYMORE);
                putAfter(entries, SSModItems.STEEL_CLAYMORE, SSModItems.STEEL_CHAKRAM);
                putAfter(entries, SSModItems.STEEL_CHAKRAM, SSModItems.STEEL_GREATAXE);
                putAfter(entries, SSModItems.STEEL_GREATAXE, SSModItems.STEEL_GREATHAMMER);
                putAfter(entries, SSModItems.STEEL_GREATHAMMER, SSModItems.STEEL_WARGLAIVE);
                putAfter(entries, SSModItems.STEEL_WARGLAIVE, SSModItems.STEEL_SCYTHE);
                putAfter(entries, SSModItems.STEEL_SCYTHE, SSModItems.STEEL_HALBERD);
                putAfter(entries, SSModItems.STEEL_HALBERD, SSModItems.DIARKRITE_LONGSWORD);
                putAfter(entries, SSModItems.DIARKRITE_LONGSWORD, SSModItems.DIARKRITE_TWINBLADE);
                putAfter(entries, SSModItems.DIARKRITE_TWINBLADE, SSModItems.DIARKRITE_RAPIER);
                putAfter(entries, SSModItems.DIARKRITE_RAPIER, SSModItems.DIARKRITE_KATANA);
                putAfter(entries, SSModItems.DIARKRITE_KATANA, SSModItems.DIARKRITE_SAI);
                putAfter(entries, SSModItems.DIARKRITE_SAI, SSModItems.DIARKRITE_SPEAR);
                putAfter(entries, SSModItems.DIARKRITE_SPEAR, SSModItems.DIARKRITE_GLAIVE);
                putAfter(entries, SSModItems.DIARKRITE_GLAIVE, SSModItems.DIARKRITE_CUTLASS);
                putAfter(entries, SSModItems.DIARKRITE_CUTLASS, SSModItems.DIARKRITE_CLAYMORE);
                putAfter(entries, SSModItems.DIARKRITE_CLAYMORE, SSModItems.DIARKRITE_CHAKRAM);
                putAfter(entries, SSModItems.DIARKRITE_CHAKRAM, SSModItems.DIARKRITE_GREATAXE);
                putAfter(entries, SSModItems.DIARKRITE_GREATAXE, SSModItems.DIARKRITE_GREATHAMMER);
                putAfter(entries, SSModItems.DIARKRITE_GREATHAMMER, SSModItems.DIARKRITE_WARGLAIVE);
                putAfter(entries, SSModItems.DIARKRITE_WARGLAIVE, SSModItems.DIARKRITE_SCYTHE);
                putAfter(entries, SSModItems.DIARKRITE_SCYTHE, SSModItems.DIARKRITE_HALBERD);
                putAfter(entries, SSModItems.DIARKRITE_HALBERD, SSModItems.ANTHEKTITE_LONGSWORD);
                putAfter(entries, SSModItems.ANTHEKTITE_LONGSWORD, SSModItems.ANTHEKTITE_TWINBLADE);
                putAfter(entries, SSModItems.ANTHEKTITE_TWINBLADE, SSModItems.ANTHEKTITE_RAPIER);
                putAfter(entries, SSModItems.ANTHEKTITE_RAPIER, SSModItems.ANTHEKTITE_KATANA);
                putAfter(entries, SSModItems.ANTHEKTITE_KATANA, SSModItems.ANTHEKTITE_SAI);
                putAfter(entries, SSModItems.ANTHEKTITE_SAI, SSModItems.ANTHEKTITE_SPEAR);
                putAfter(entries, SSModItems.ANTHEKTITE_SPEAR, SSModItems.ANTHEKTITE_GLAIVE);
                putAfter(entries, SSModItems.ANTHEKTITE_GLAIVE, SSModItems.ANTHEKTITE_CUTLASS);
                putAfter(entries, SSModItems.ANTHEKTITE_CUTLASS, SSModItems.ANTHEKTITE_CLAYMORE);
                putAfter(entries, SSModItems.ANTHEKTITE_CLAYMORE, SSModItems.ANTHEKTITE_CHAKRAM);
                putAfter(entries, SSModItems.ANTHEKTITE_CHAKRAM, SSModItems.ANTHEKTITE_GREATAXE);
                putAfter(entries, SSModItems.ANTHEKTITE_GREATAXE, SSModItems.ANTHEKTITE_GREATHAMMER);
                putAfter(entries, SSModItems.ANTHEKTITE_GREATHAMMER, SSModItems.ANTHEKTITE_WARGLAIVE);
                putAfter(entries, SSModItems.ANTHEKTITE_WARGLAIVE, SSModItems.ANTHEKTITE_SCYTHE);
                putAfter(entries, SSModItems.ANTHEKTITE_SCYTHE, SSModItems.ANTHEKTITE_HALBERD);
            }
        }
        if (samuraiDynasty) {
            if (tab == net.veroxuniverse.samurai_dynasty.registry.CreativeTabRegistry.TAB.getKey()) {
                putAfter(entries, net.veroxuniverse.samurai_dynasty.registry.ItemsRegistry.GRAY_SAMURAI_BOOTS_MASTER, ESModItems.STEEL_SAMURAI_HELMET);
                putAfter(entries, ESModItems.STEEL_SAMURAI_HELMET, ESModItems.STEEL_SAMURAI_CHESTPLATE);
                putAfter(entries, ESModItems.STEEL_SAMURAI_CHESTPLATE, ESModItems.STEEL_SAMURAI_LEGGINGS);
                putAfter(entries, ESModItems.STEEL_SAMURAI_LEGGINGS, ESModItems.STEEL_SAMURAI_BOOTS);
                putAfter(entries, ESModItems.STEEL_SAMURAI_BOOTS, ESModItems.STEEL_SAMURAI_HELMET_LIGHT);
                putAfter(entries, ESModItems.STEEL_SAMURAI_HELMET_LIGHT, ESModItems.STEEL_SAMURAI_CHESTPLATE_LIGHT);
                putAfter(entries, ESModItems.STEEL_SAMURAI_CHESTPLATE_LIGHT, ESModItems.STEEL_SAMURAI_LEGGINGS_LIGHT);
                putAfter(entries, ESModItems.STEEL_SAMURAI_LEGGINGS_LIGHT, ESModItems.STEEL_SAMURAI_BOOTS_LIGHT);
                putAfter(entries, ESModItems.STEEL_SAMURAI_BOOTS_LIGHT, ESModItems.STEEL_SAMURAI_HELMET_MASTER);
                putAfter(entries, ESModItems.STEEL_SAMURAI_HELMET_MASTER, ESModItems.STEEL_SAMURAI_CHESTPLATE_MASTER);
                putAfter(entries, ESModItems.STEEL_SAMURAI_CHESTPLATE_MASTER, ESModItems.STEEL_SAMURAI_LEGGINGS_MASTER);
                putAfter(entries, ESModItems.STEEL_SAMURAI_LEGGINGS_MASTER, ESModItems.STEEL_SAMURAI_BOOTS_MASTER);

                putAfter(entries, ESModItems.STEEL_SAMURAI_BOOTS_MASTER, ESModItems.DIARKRITE_SAMURAI_HELMET);
                putAfter(entries, ESModItems.DIARKRITE_SAMURAI_HELMET, ESModItems.DIARKRITE_SAMURAI_CHESTPLATE);
                putAfter(entries, ESModItems.DIARKRITE_SAMURAI_CHESTPLATE, ESModItems.DIARKRITE_SAMURAI_LEGGINGS);
                putAfter(entries, ESModItems.DIARKRITE_SAMURAI_LEGGINGS, ESModItems.DIARKRITE_SAMURAI_BOOTS);
                putAfter(entries, ESModItems.DIARKRITE_SAMURAI_BOOTS, ESModItems.DIARKRITE_SAMURAI_HELMET_LIGHT);
                putAfter(entries, ESModItems.DIARKRITE_SAMURAI_HELMET_LIGHT, ESModItems.DIARKRITE_SAMURAI_CHESTPLATE_LIGHT);
                putAfter(entries, ESModItems.DIARKRITE_SAMURAI_CHESTPLATE_LIGHT, ESModItems.DIARKRITE_SAMURAI_LEGGINGS_LIGHT);
                putAfter(entries, ESModItems.DIARKRITE_SAMURAI_LEGGINGS_LIGHT, ESModItems.DIARKRITE_SAMURAI_BOOTS_LIGHT);
                putAfter(entries, ESModItems.DIARKRITE_SAMURAI_BOOTS_LIGHT, ESModItems.DIARKRITE_SAMURAI_HELMET_MASTER);
                putAfter(entries, ESModItems.DIARKRITE_SAMURAI_HELMET_MASTER, ESModItems.DIARKRITE_SAMURAI_CHESTPLATE_MASTER);
                putAfter(entries, ESModItems.DIARKRITE_SAMURAI_CHESTPLATE_MASTER, ESModItems.DIARKRITE_SAMURAI_LEGGINGS_MASTER);
                putAfter(entries, ESModItems.DIARKRITE_SAMURAI_LEGGINGS_MASTER, ESModItems.DIARKRITE_SAMURAI_BOOTS_MASTER);

                putAfter(entries, ESModItems.DIARKRITE_SAMURAI_BOOTS_MASTER, ESModItems.ANTHEKTITE_SAMURAI_HELMET);
                putAfter(entries, ESModItems.ANTHEKTITE_SAMURAI_HELMET, ESModItems.ANTHEKTITE_SAMURAI_CHESTPLATE);
                putAfter(entries, ESModItems.ANTHEKTITE_SAMURAI_CHESTPLATE, ESModItems.ANTHEKTITE_SAMURAI_LEGGINGS);
                putAfter(entries, ESModItems.ANTHEKTITE_SAMURAI_LEGGINGS, ESModItems.ANTHEKTITE_SAMURAI_BOOTS);
                putAfter(entries, ESModItems.ANTHEKTITE_SAMURAI_BOOTS, ESModItems.ANTHEKTITE_SAMURAI_HELMET_LIGHT);
                putAfter(entries, ESModItems.ANTHEKTITE_SAMURAI_HELMET_LIGHT, ESModItems.ANTHEKTITE_SAMURAI_CHESTPLATE_LIGHT);
                putAfter(entries, ESModItems.ANTHEKTITE_SAMURAI_CHESTPLATE_LIGHT, ESModItems.ANTHEKTITE_SAMURAI_LEGGINGS_LIGHT);
                putAfter(entries, ESModItems.ANTHEKTITE_SAMURAI_LEGGINGS_LIGHT, ESModItems.ANTHEKTITE_SAMURAI_BOOTS_LIGHT);
                putAfter(entries, ESModItems.ANTHEKTITE_SAMURAI_BOOTS_LIGHT, ESModItems.ANTHEKTITE_SAMURAI_HELMET_MASTER);
                putAfter(entries, ESModItems.ANTHEKTITE_SAMURAI_HELMET_MASTER, ESModItems.ANTHEKTITE_SAMURAI_CHESTPLATE_MASTER);
                putAfter(entries, ESModItems.ANTHEKTITE_SAMURAI_CHESTPLATE_MASTER, ESModItems.ANTHEKTITE_SAMURAI_LEGGINGS_MASTER);
                putAfter(entries, ESModItems.ANTHEKTITE_SAMURAI_LEGGINGS_MASTER, ESModItems.ANTHEKTITE_SAMURAI_BOOTS_MASTER);
            }
        }
        if (twigs) {
            if (tab == TwigsCreativeModeTabs.TWIG.getKey()) {
                putAfter(entries, com.ninni.twigs.registry.TwigsItems.BAMBOO_TABLE, TWModItems.MOVCADIA_TABLE);
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
                        if (piercingPaxels) PPModItems.ITEMS.getEntries().stream().map(RegistryObject::get).forEach(output::accept);
                        if (nethersDelight) NethersDelightItems.ITEMS.getEntries().stream().map(RegistryObject::get).forEach(output::accept);
                        if (ironsSpellbooks) ISSModItems.ITEMS.getEntries().stream().map(RegistryObject::get).forEach(output::accept);
                        if (aether) TAModItems.ITEMS.getEntries().stream().map(RegistryObject::get).forEach(output::accept);
                        if (simplySwords) SSModItems.ITEMS.getEntries().stream().map(RegistryObject::get).forEach(output::accept);
                        if (sniffsWeapons) SWModItems.ITEMS.getEntries().stream().map(RegistryObject::get).forEach(output::accept);
                        if (advancedNetherite) ANModItems.ITEMS.getEntries().stream().map(RegistryObject::get).forEach(output::accept);
                        if (samuraiDynasty) ESModItems.ITEMS.getEntries().stream().map(RegistryObject::get).forEach(output::accept);
                        if (twigs) TWModItems.ITEMS.getEntries().stream().map(RegistryObject::get).forEach(output::accept);
                        if (witherStormMod) WitherstormModItems.ITEMS.getEntries().stream().map(RegistryObject::get).forEach(output::accept);
                        if (vanillaClaws) BanillaClawsItems.ITEMS.getEntries().stream().map(RegistryObject::get).forEach(output::accept);
                        if (create) output.accept(ElementusItems.CRUSHED_REMNANT.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
