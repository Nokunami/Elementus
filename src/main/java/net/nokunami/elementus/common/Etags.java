package net.nokunami.elementus.common;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;

public class Etags {
    public static class Item {
        public static final TagKey<net.minecraft.world.item.Item> INGOTS_STEEL = forgeTag("ingots/steel");
        public static final TagKey<net.minecraft.world.item.Item> INGOTS_DIARKRITE = forgeTag("ingots/diarkrite");
        public static final TagKey<net.minecraft.world.item.Item> INGOTS_ANTHEKTITE = forgeTag("ingots/anthektite");
        public static final TagKey<net.minecraft.world.item.Item> NUGGETS_STEEL = forgeTag("nuggets/steel");
        public static final TagKey<net.minecraft.world.item.Item> RAW_MATERIALS_STEEL = forgeTag("raw_materials/steel");
        public static final TagKey<net.minecraft.world.item.Item> ORES_ATELIS = forgeTag("ores/atelis_scrap");

        public static final TagKey<net.minecraft.world.item.Item> STORAGE_BLOCK_STEEL = forgeTag("storage_blocks/steel");
        public static final TagKey<net.minecraft.world.item.Item> STORAGE_BLOCK_DIARKRITE = forgeTag("storage_blocks/diarkrite");
        public static final TagKey<net.minecraft.world.item.Item> STORAGE_BLOCK_ANTHEKTITE = forgeTag("storage_blocks/anthektite");

        public static final TagKey<net.minecraft.world.item.Item> FD_KNIFE = forgeTag("tools/knifes");

        public static final TagKey<net.minecraft.world.item.Item> STEEL_RECYCLABLE = elementusTag("steel_recyclable");
        public static final TagKey<net.minecraft.world.item.Item> MOVCADIA_LOGS = elementusTag("movcadia_logs");

        public static final TagKey<net.minecraft.world.item.Item> SIMPLY_SWORDS_SWORDS = modTag("simplyswords", "swords");
        public static final TagKey<net.minecraft.world.item.Item> SIMPLY_SWORDS_STEEL = modTag("simplyswords", "steel_tools");
        public static final TagKey<net.minecraft.world.item.Item> SIMPLY_SWORDS_DIARKRITE = modTag("simplyswords", "diarkrite_tools");
        public static final TagKey<net.minecraft.world.item.Item> SIMPLY_SWORDS_ANTHEKTITE = modTag("simplyswords", "anthektite_tools");

        public static final TagKey<net.minecraft.world.item.Item> CURIOS_SPELLBOOK = modTag("curios", "spellbook");

        public static final TagKey<net.minecraft.world.item.Item> AC_FERROMAGNETIC = modTag("alexscaves", "ferromagnetic_items");

        private static TagKey<net.minecraft.world.item.Item> forgeTag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }

        private static TagKey<net.minecraft.world.item.Item> elementusTag(String name) {
            return ItemTags.create(new ResourceLocation("elementus", name));
        }

        private static TagKey<net.minecraft.world.item.Item> modTag(String namespace, String path) {
            return ItemTags.create(new ResourceLocation(namespace, path));
        }
    }

    public static class Block {
        public static final TagKey<net.minecraft.world.level.block.Block> STEEL_STORAGE_BLOCK = forgeTag("storage_blocks/steel");
        public static final TagKey<net.minecraft.world.level.block.Block> DIARKRITE_STORAGE_BLOCK = forgeTag("storage_blocks/diarkrite");
        public static final TagKey<net.minecraft.world.level.block.Block> ANTHEKTITE_STORAGE_BLOCK = forgeTag("storage_blocks/anthektite");

        public static final TagKey<net.minecraft.world.level.block.Block> MOVCADIA_LOGS = elementusTag("movcadia_logs");

        private static TagKey<net.minecraft.world.level.block.Block> forgeTag(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }

        private static TagKey<net.minecraft.world.level.block.Block> elementusTag(String name) {
            return BlockTags.create(new ResourceLocation("elementus", name));
        }
    }
}
