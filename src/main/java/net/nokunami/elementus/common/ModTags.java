package net.nokunami.elementus.common;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.ModList;

public class ModTags {
    public static class Items {
        public static final TagKey<Item> INGOTS_STEEL = forgeTag("ingots/steel");
        public static final TagKey<Item> INGOTS_DIARKRITE = forgeTag("ingots/diarkrite");
        public static final TagKey<Item> INGOTS_ANTHEKTITE = forgeTag("ingots/anthektite");
        public static final TagKey<Item> NUGGETS_STEEL = forgeTag("nuggets/steel");
        public static final TagKey<Item> RAW_MATERIALS_STEEL = forgeTag("raw_materials/steel");
        public static final TagKey<Item> ORES_ATELIS = forgeTag("ores/atelis");

        public static final TagKey<Item> STEEL_RECYCLABLE = modTag("steel_recyclable");

        private static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }

        private static TagKey<Item> modTag(String name) {
            return ItemTags.create(new ResourceLocation("elementus", name));
        }
    }
}
