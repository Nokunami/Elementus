package net.nokunami.elementus.common;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.util.MutableHashedLinkedMap;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.nokunami.elementus.common.registry.ModItems.ElementusItems;

import java.util.function.Supplier;

public class CreativeTabProperties {

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
            putAfter(entries, Items.NETHERITE_SWORD, ElementusItems.STEEL_SWORD);
            putAfter(entries, ElementusItems.STEEL_SWORD, ElementusItems.DIARKRITE_SWORD);
            putAfter(entries, ElementusItems.DIARKRITE_SWORD, ElementusItems.ANTHEKTITE_SWORD);
            putAfter(entries, ElementusItems.ANTHEKTITE_SWORD, ElementusItems.MOVCADIA_SWORD);

            // Axes
            putAfter(entries, Items.NETHERITE_AXE, ElementusItems.STEEL_AXE);
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
            putAfter(entries, Items.NETHERITE_BOOTS, ElementusItems.STEEL_HELMET);
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
}
