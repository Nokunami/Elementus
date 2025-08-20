package net.nokunami.elementus.common;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.util.MutableHashedLinkedMap;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.nokunami.elementus.common.registry.ModItems;

import java.util.function.Supplier;

public class CreativeTabProperties {

    public static void addCreative(BuildCreativeModeTabContentsEvent event) {
        //Credit: oreganised mod, GitHub: https://github.com/Xaidee/oreganised

        ResourceKey<CreativeModeTab> tab = event.getTabKey();
        MutableHashedLinkedMap<ItemStack, CreativeModeTab.TabVisibility> entries = event.getEntries();
        // SpawnEgg
        if (tab == CreativeModeTabs.SPAWN_EGGS) {
            putAfter(entries, Items.SQUID_SPAWN_EGG, ModItems.STEEL_GOLEM_SPAWN_EGG);
        }
        // Food
        if (tab == CreativeModeTabs.FOOD_AND_DRINKS) {
            putAfter(entries, Items.GLOW_BERRIES, ModItems.MOVCADIA_BERRIES);
            putAfter(entries, ModItems.MOVCADIA_BERRIES, ModItems.GLISTERING_MOVCADIA_BERRIES);
        }
        // Ingredients
        if (tab == CreativeModeTabs.INGREDIENTS) {
            putAfter(entries, Items.ANCIENT_DEBRIS, ModItems.REMNANT);

            putAfter(entries, Items.IRON_NUGGET, ModItems.STEEL_NUGGET);
            putAfter(entries, Items.IRON_INGOT, ModItems.CRUDE_STEEL);
            putAfter(entries, ModItems.CRUDE_STEEL, ModItems.STEEL_INGOT);
            putAfter(entries, ModItems.STEEL_INGOT, ModItems.STEEL_SCRAP);

            putAfter(entries, Items.NETHERITE_INGOT, ModItems.ATELIS_SCRAP);
            putAfter(entries, ModItems.ATELIS_SCRAP, ModItems.DIARKRITE_INGOT);
            putAfter(entries, ModItems.DIARKRITE_INGOT, ModItems.ANTHEKTITE_INGOT);
            putAfter(entries, Items.BLAZE_POWDER, ModItems.MOVCADIA_ESSENCE);

            putAfter(entries, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE);
            putAfter(entries, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, ModItems.WEAPON_FRAGMENT);
        }
        // Weapons
        if (tab == CreativeModeTabs.COMBAT) {
            // Swords
            putAfter(entries, Items.NETHERITE_SWORD, ModItems.STEEL_SWORD);
            putAfter(entries, ModItems.STEEL_SWORD, ModItems.DIARKRITE_SWORD);
            putAfter(entries, ModItems.DIARKRITE_SWORD, ModItems.ANTHEKTITE_SWORD);
            putAfter(entries, ModItems.ANTHEKTITE_SWORD, ModItems.MOVCADIA_SWORD);

            // Axes
            putAfter(entries, Items.NETHERITE_AXE, ModItems.STEEL_AXE);
            putAfter(entries, ModItems.STEEL_AXE, ModItems.DIARKRITE_AXE);
            putAfter(entries, ModItems.DIARKRITE_AXE, ModItems.ANTHEKTITE_AXE);
            putAfter(entries, ModItems.ANTHEKTITE_AXE, ModItems.MOVCADIA_AXE);

            // Shields
            putAfter(entries, Items.SHIELD, ModItems.STEEL_SHIELD);
            putAfter(entries, ModItems.STEEL_SHIELD, ModItems.DIARKRITE_SHIELD);
            putAfter(entries, ModItems.DIARKRITE_SHIELD, ModItems.ANTHEKTITE_SHIELD);
            // Bows
            putAfter(entries, Items.BOW, ModItems.STEEL_BOW);
            putAfter(entries, ModItems.STEEL_BOW, ModItems.DIARKRITE_BOW);
            putAfter(entries, ModItems.DIARKRITE_BOW, ModItems.ANTHEKTITE_BOW);
            // Special Weapon
            putAfter(entries, Items.TRIDENT, ModItems.DIARKRITE_CHARGE_BLADE);
            putAfter(entries, ModItems.DIARKRITE_CHARGE_BLADE, ModItems.ANTHEKTITE_CHARGE_BLADE);
            putAfter(entries, ModItems.ANTHEKTITE_CHARGE_BLADE, ModItems.TEST_TRIDENT);

            // Armor
            putAfter(entries, Items.NETHERITE_BOOTS, ModItems.STEEL_HELMET);
            putAfter(entries, ModItems.STEEL_HELMET, ModItems.STEEL_CHESTPLATE);
            putAfter(entries, ModItems.STEEL_CHESTPLATE, ModItems.STEEL_LEGGINGS);
            putAfter(entries, ModItems.STEEL_LEGGINGS, ModItems.STEEL_BOOTS);

            putAfter(entries, ModItems.STEEL_BOOTS, ModItems.DIARKRITE_HELMET);
            putAfter(entries, ModItems.DIARKRITE_HELMET, ModItems.DIARKRITE_CHESTPLATE);
            putAfter(entries, ModItems.DIARKRITE_CHESTPLATE, ModItems.DIARKRITE_LEGGINGS);
            putAfter(entries, ModItems.DIARKRITE_LEGGINGS, ModItems.DIARKRITE_BOOTS);

            putAfter(entries, ModItems.DIARKRITE_BOOTS, ModItems.ANTHEKTITE_HELMET);
            putAfter(entries, ModItems.ANTHEKTITE_HELMET, ModItems.ANTHEKTITE_CHESTPLATE);
            putAfter(entries, ModItems.ANTHEKTITE_CHESTPLATE, ModItems.ANTHEKTITE_LEGGINGS);
            putAfter(entries, ModItems.ANTHEKTITE_LEGGINGS, ModItems.ANTHEKTITE_BOOTS);

            putAfter(entries, ModItems.ANTHEKTITE_BOOTS, ModItems.CATALYST_CHESTPLATE);

            putAfter(entries, Items.DIAMOND_HORSE_ARMOR, ModItems.REINFORCED_PLATING_GOLEM_UPGRADE);
        }
        // Tools
        if (tab == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            putAfter(entries, Items.NETHERITE_HOE, ModItems.STEEL_SHOVEL);
            putAfter(entries, ModItems.STEEL_SHOVEL, ModItems.STEEL_PICKAXE);
            putAfter(entries, ModItems.STEEL_PICKAXE, ModItems.STEEL_AXE);
            putAfter(entries, ModItems.STEEL_AXE, ModItems.STEEL_HOE);

            putAfter(entries, ModItems.STEEL_HOE, ModItems.DIARKRITE_SHOVEL);
            putAfter(entries, ModItems.DIARKRITE_SHOVEL, ModItems.DIARKRITE_PICKAXE);
            putAfter(entries, ModItems.DIARKRITE_PICKAXE, ModItems.DIARKRITE_AXE);
            putAfter(entries, ModItems.DIARKRITE_AXE, ModItems.DIARKRITE_HOE);

            putAfter(entries, ModItems.DIARKRITE_HOE, ModItems.ANTHEKTITE_SHOVEL);
            putAfter(entries, ModItems.ANTHEKTITE_SHOVEL, ModItems.ANTHEKTITE_PICKAXE);
            putAfter(entries, ModItems.ANTHEKTITE_PICKAXE, ModItems.ANTHEKTITE_AXE);
            putAfter(entries, ModItems.ANTHEKTITE_AXE, ModItems.ANTHEKTITE_HOE);

            putAfter(entries, ModItems.ANTHEKTITE_HOE, ModItems.MOVCADIA_SHOVEL);
            putAfter(entries, ModItems.MOVCADIA_SHOVEL, ModItems.MOVCADIA_PICKAXE);
            putAfter(entries, ModItems.MOVCADIA_PICKAXE, ModItems.MOVCADIA_AXE);
            putAfter(entries, ModItems.MOVCADIA_AXE, ModItems.MOVCADIA_HOE);

            putAfter(entries, Items.BAMBOO_CHEST_RAFT, ModItems.MOVCADIA_BOAT);
            putAfter(entries, ModItems.MOVCADIA_BOAT, ModItems.MOVCADIA_CHEST_BOAT);
        }
        // Blocks
        if (tab == CreativeModeTabs.BUILDING_BLOCKS) {
            putAfter(entries, Items.NETHERITE_BLOCK, ModItems.STEEL_BLOCK);
            putAfter(entries, ModItems.STEEL_BLOCK, ModItems.DIARKRITE_BLOCK);
            putAfter(entries, ModItems.DIARKRITE_BLOCK, ModItems.ANTHEKTITE_BLOCK);

            putAfter(entries, ModItems.STEEL_BLOCK, ModItems.STEEL_BARS);

            putAfter(entries, ModItems.STEEL_BARS, ModItems.STEEL_TILES);
            putAfter(entries, ModItems.STEEL_TILES, ModItems.STEEL_TILE_STAIR);
            putAfter(entries, ModItems.STEEL_TILE_STAIR, ModItems.STEEL_TILE_SLAB);

            putAfter(entries, Items.BAMBOO_BUTTON, ModItems.MOVCADIA_LOG);
            putAfter(entries, ModItems.MOVCADIA_LOG, ModItems.MOVCADIA_WOOD);
            putAfter(entries, ModItems.MOVCADIA_WOOD, ModItems.STRIPPED_MOVCADIA_LOG);
            putAfter(entries, ModItems.STRIPPED_MOVCADIA_LOG, ModItems.STRIPPED_MOVCADIA_WOOD);
            putAfter(entries, ModItems.STRIPPED_MOVCADIA_WOOD, ModItems.MOVCADIA_PLANKS);
            putAfter(entries, ModItems.MOVCADIA_PLANKS, ModItems.MOVCADIA_STAIRS);
            putAfter(entries, ModItems.MOVCADIA_STAIRS, ModItems.MOVCADIA_SLAB);
            putAfter(entries, ModItems.MOVCADIA_SLAB, ModItems.MOVCADIA_FENCE);
            putAfter(entries, ModItems.MOVCADIA_FENCE, ModItems.MOVCADIA_FENCE_GATE);
            putAfter(entries, ModItems.MOVCADIA_FENCE_GATE, ModItems.MOVCADIA_DOOR);
            putAfter(entries, ModItems.MOVCADIA_DOOR, ModItems.MOVCADIA_TRAPDOOR);
            putAfter(entries, ModItems.MOVCADIA_TRAPDOOR, ModItems.MOVCADIA_PRESSURE_PLATE);
            putAfter(entries, ModItems.MOVCADIA_PRESSURE_PLATE, ModItems.MOVCADIA_BUTTON);
        }
        if (tab == CreativeModeTabs.NATURAL_BLOCKS) {
            putAfter(entries, Items.CHERRY_LOG, ModItems.MOVCADIA_LOG);
            putAfter(entries, Items.FLOWERING_AZALEA_LEAVES, ModItems.MOVCADIA_LEAVES);
            putAfter(entries, ModItems.MOVCADIA_LEAVES, ModItems.FLOWERING_MOVCADIA_LEAVES);
            putAfter(entries, Items.FLOWERING_AZALEA, ModItems.MOVCADIA_SAPLING);
            putAfter(entries, Items.ROOTED_DIRT, ModItems.MOVCADIA_ROOTED_DIRT);
        }
        if (tab == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            putAfter(entries, Items.CHEST, ModItems.MOVCADIA_CHEST);
            putAfter(entries, Items.BAMBOO_HANGING_SIGN, ModItems.MOVCADIA_SIGN);
            putAfter(entries, ModItems.MOVCADIA_SIGN, ModItems.MOVCADIA_HANGING_SIGN);
            putAfter(entries, ModItems.MOVCADIA_SIGN, ModItems.STURDY_MOVCADIA_SIGN);
        }
        if (tab == CreativeModeTabs.REDSTONE_BLOCKS) {
            putAfter(entries, Items.CHEST, ModItems.MOVCADIA_CHEST);
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
