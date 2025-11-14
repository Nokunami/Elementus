package net.nokunami.elementus.common;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.util.MutableHashedLinkedMap;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.nokunami.elementus.common.registry.EItems;

import java.util.function.Supplier;

public class CreativeTabProperties {

    public static void addCreative(BuildCreativeModeTabContentsEvent event) {
        //Credit: oreganised mod, GitHub: https://github.com/Xaidee/oreganised

        ResourceKey<CreativeModeTab> tab = event.getTabKey();
        MutableHashedLinkedMap<ItemStack, CreativeModeTab.TabVisibility> entries = event.getEntries();
        // SpawnEgg
        if (tab == CreativeModeTabs.SPAWN_EGGS) {
            putBefore(entries, Items.ALLAY_SPAWN_EGG, EItems.ASTALITE_GOLEM_SPAWN_EGG);
            putAfter(entries, Items.SQUID_SPAWN_EGG, EItems.STEEL_GOLEM_SPAWN_EGG);
        }
        // Food
        if (tab == CreativeModeTabs.FOOD_AND_DRINKS) {
            putAfter(entries, Items.GLOW_BERRIES, EItems.MOVCADIA_BERRIES);
            putAfter(entries, EItems.MOVCADIA_BERRIES, EItems.GLISTERING_MOVCADIA_BERRIES);
        }
        // Ingredients
        if (tab == CreativeModeTabs.INGREDIENTS) {
            putAfter(entries, Items.ANCIENT_DEBRIS, EItems.REMNANT);

            putAfter(entries, Items.IRON_NUGGET, EItems.STEEL_NUGGET);
            putAfter(entries, Items.IRON_INGOT, EItems.CRUDE_STEEL);
            putAfter(entries, EItems.CRUDE_STEEL, EItems.STEEL_INGOT);
            putAfter(entries, EItems.STEEL_INGOT, EItems.STEEL_SCRAP);

            putAfter(entries, Items.NETHERITE_INGOT, EItems.ATELIS_SCRAP);
            putAfter(entries, EItems.ATELIS_SCRAP, EItems.DIARKRITE_INGOT);
            putAfter(entries, EItems.DIARKRITE_INGOT, EItems.ANTHEKTITE_INGOT);
            putAfter(entries, Items.BLAZE_POWDER, EItems.MOVCADIA_ESSENCE);

            putAfter(entries, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, EItems.ATELIS_UPGRADE_SMITHING_TEMPLATE);
            putAfter(entries, EItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, EItems.WEAPON_FRAGMENT);
        }
        // Weapons
        if (tab == CreativeModeTabs.COMBAT) {
            // Swords
            putAfter(entries, Items.NETHERITE_SWORD, EItems.STEEL_SWORD);
            putAfter(entries, EItems.STEEL_SWORD, EItems.DIARKRITE_SWORD);
            putAfter(entries, EItems.DIARKRITE_SWORD, EItems.ANTHEKTITE_SWORD);
            putAfter(entries, EItems.ANTHEKTITE_SWORD, EItems.MOVCADIA_SWORD);

            // Axes
            putAfter(entries, Items.NETHERITE_AXE, EItems.STEEL_AXE);
            putAfter(entries, EItems.STEEL_AXE, EItems.DIARKRITE_AXE);
            putAfter(entries, EItems.DIARKRITE_AXE, EItems.ANTHEKTITE_AXE);
            putAfter(entries, EItems.ANTHEKTITE_AXE, EItems.MOVCADIA_AXE);

            // Shields
            putAfter(entries, Items.SHIELD, EItems.STEEL_SHIELD);
            putAfter(entries, EItems.STEEL_SHIELD, EItems.DIARKRITE_SHIELD);
            putAfter(entries, EItems.DIARKRITE_SHIELD, EItems.ANTHEKTITE_SHIELD);
            // Bows
            putAfter(entries, Items.BOW, EItems.STEEL_BOW);
            putAfter(entries, EItems.STEEL_BOW, EItems.DIARKRITE_BOW);
            putAfter(entries, EItems.DIARKRITE_BOW, EItems.ANTHEKTITE_BOW);
            // Special Weapon
            putAfter(entries, Items.TRIDENT, EItems.DIARKRITE_CHARGE_BLADE);
            putAfter(entries, EItems.DIARKRITE_CHARGE_BLADE, EItems.ANTHEKTITE_CHARGE_BLADE);
            putAfter(entries, EItems.ANTHEKTITE_CHARGE_BLADE, EItems.WRATH_TRIDENT);

            // Armor
            putAfter(entries, Items.NETHERITE_BOOTS, EItems.STEEL_HELMET);
            putAfter(entries, EItems.STEEL_HELMET, EItems.STEEL_CHESTPLATE);
            putAfter(entries, EItems.STEEL_CHESTPLATE, EItems.STEEL_LEGGINGS);
            putAfter(entries, EItems.STEEL_LEGGINGS, EItems.STEEL_BOOTS);

            putAfter(entries, EItems.STEEL_BOOTS, EItems.DIARKRITE_HELMET);
            putAfter(entries, EItems.DIARKRITE_HELMET, EItems.DIARKRITE_CHESTPLATE);
            putAfter(entries, EItems.DIARKRITE_CHESTPLATE, EItems.DIARKRITE_LEGGINGS);
            putAfter(entries, EItems.DIARKRITE_LEGGINGS, EItems.DIARKRITE_BOOTS);

            putAfter(entries, EItems.DIARKRITE_BOOTS, EItems.ANTHEKTITE_HELMET);
            putAfter(entries, EItems.ANTHEKTITE_HELMET, EItems.ANTHEKTITE_CHESTPLATE);
            putAfter(entries, EItems.ANTHEKTITE_CHESTPLATE, EItems.ANTHEKTITE_LEGGINGS);
            putAfter(entries, EItems.ANTHEKTITE_LEGGINGS, EItems.ANTHEKTITE_BOOTS);

            putAfter(entries, EItems.ANTHEKTITE_BOOTS, EItems.CATALYST_CHESTPLATE);

            putAfter(entries, Items.DIAMOND_HORSE_ARMOR, EItems.REINFORCED_PLATING_GOLEM_UPGRADE);
        }
        // Tools
        if (tab == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            putAfter(entries, Items.NETHERITE_HOE, EItems.STEEL_SHOVEL);
            putAfter(entries, EItems.STEEL_SHOVEL, EItems.STEEL_PICKAXE);
            putAfter(entries, EItems.STEEL_PICKAXE, EItems.STEEL_AXE);
            putAfter(entries, EItems.STEEL_AXE, EItems.STEEL_HOE);

            putAfter(entries, EItems.STEEL_HOE, EItems.DIARKRITE_SHOVEL);
            putAfter(entries, EItems.DIARKRITE_SHOVEL, EItems.DIARKRITE_PICKAXE);
            putAfter(entries, EItems.DIARKRITE_PICKAXE, EItems.DIARKRITE_AXE);
            putAfter(entries, EItems.DIARKRITE_AXE, EItems.DIARKRITE_HOE);

            putAfter(entries, EItems.DIARKRITE_HOE, EItems.ANTHEKTITE_SHOVEL);
            putAfter(entries, EItems.ANTHEKTITE_SHOVEL, EItems.ANTHEKTITE_PICKAXE);
            putAfter(entries, EItems.ANTHEKTITE_PICKAXE, EItems.ANTHEKTITE_AXE);
            putAfter(entries, EItems.ANTHEKTITE_AXE, EItems.ANTHEKTITE_HOE);

            putAfter(entries, EItems.ANTHEKTITE_HOE, EItems.MOVCADIA_SHOVEL);
            putAfter(entries, EItems.MOVCADIA_SHOVEL, EItems.MOVCADIA_PICKAXE);
            putAfter(entries, EItems.MOVCADIA_PICKAXE, EItems.MOVCADIA_AXE);
            putAfter(entries, EItems.MOVCADIA_AXE, EItems.MOVCADIA_HOE);

            putAfter(entries, Items.BAMBOO_CHEST_RAFT, EItems.MOVCADIA_BOAT);
            putAfter(entries, EItems.MOVCADIA_BOAT, EItems.MOVCADIA_CHEST_BOAT);
        }
        // Blocks
        if (tab == CreativeModeTabs.BUILDING_BLOCKS) {
            putAfter(entries, Items.NETHERITE_BLOCK, EItems.STEEL_BLOCK);
            putAfter(entries, EItems.STEEL_BLOCK, EItems.DIARKRITE_BLOCK);
            putAfter(entries, EItems.DIARKRITE_BLOCK, EItems.ANTHEKTITE_BLOCK);

            putAfter(entries, EItems.STEEL_BLOCK, EItems.STEEL_BARS);

            putAfter(entries, EItems.STEEL_BARS, EItems.STEEL_TILES);
            putAfter(entries, EItems.STEEL_TILES, EItems.STEEL_TILE_STAIR);
            putAfter(entries, EItems.STEEL_TILE_STAIR, EItems.STEEL_TILE_SLAB);

            putAfter(entries, Items.BAMBOO_BUTTON, EItems.MOVCADIA_LOG);
            putAfter(entries, EItems.MOVCADIA_LOG, EItems.MOVCADIA_WOOD);
            putAfter(entries, EItems.MOVCADIA_WOOD, EItems.STRIPPED_MOVCADIA_LOG);
            putAfter(entries, EItems.STRIPPED_MOVCADIA_LOG, EItems.STRIPPED_MOVCADIA_WOOD);
            putAfter(entries, EItems.STRIPPED_MOVCADIA_WOOD, EItems.MOVCADIA_PLANKS);
            putAfter(entries, EItems.MOVCADIA_PLANKS, EItems.MOVCADIA_STAIRS);
            putAfter(entries, EItems.MOVCADIA_STAIRS, EItems.MOVCADIA_SLAB);
            putAfter(entries, EItems.MOVCADIA_SLAB, EItems.MOVCADIA_FENCE);
            putAfter(entries, EItems.MOVCADIA_FENCE, EItems.MOVCADIA_FENCE_GATE);
            putAfter(entries, EItems.MOVCADIA_FENCE_GATE, EItems.MOVCADIA_DOOR);
            putAfter(entries, EItems.MOVCADIA_DOOR, EItems.MOVCADIA_TRAPDOOR);
            putAfter(entries, EItems.MOVCADIA_TRAPDOOR, EItems.MOVCADIA_PRESSURE_PLATE);
            putAfter(entries, EItems.MOVCADIA_PRESSURE_PLATE, EItems.MOVCADIA_BUTTON);
        }
        if (tab == CreativeModeTabs.NATURAL_BLOCKS) {
            putAfter(entries, Items.CHERRY_LOG, EItems.MOVCADIA_LOG);
            putAfter(entries, Items.FLOWERING_AZALEA_LEAVES, EItems.MOVCADIA_LEAVES);
            putAfter(entries, EItems.MOVCADIA_LEAVES, EItems.FLOWERING_MOVCADIA_LEAVES);
            putAfter(entries, Items.FLOWERING_AZALEA, EItems.MOVCADIA_SAPLING);
            putAfter(entries, Items.ROOTED_DIRT, EItems.MOVCADIA_ROOTED_DIRT);
        }
        if (tab == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            putAfter(entries, Items.CHEST, EItems.MOVCADIA_CHEST);
            putAfter(entries, Items.BAMBOO_HANGING_SIGN, EItems.MOVCADIA_SIGN);
            putAfter(entries, EItems.MOVCADIA_SIGN, EItems.MOVCADIA_HANGING_SIGN);
            putAfter(entries, EItems.MOVCADIA_SIGN, EItems.STURDY_MOVCADIA_SIGN);
        }
        if (tab == CreativeModeTabs.REDSTONE_BLOCKS) {
            putAfter(entries, Items.CHEST, EItems.MOVCADIA_CHEST);
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
