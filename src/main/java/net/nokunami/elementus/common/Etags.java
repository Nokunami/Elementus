package net.nokunami.elementus.common;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import static net.nokunami.elementus.Elementus.MODID;
import static net.nokunami.elementus.ModChecker.*;

public class Etags {
    public static class Items {
        public static final TagKey<Item> INGOTS_STEEL = forgeTag("ingots/steel");
        public static final TagKey<Item> INGOTS_DIARKRITE = forgeTag("ingots/diarkrite");
        public static final TagKey<Item> INGOTS_ANTHEKTITE = forgeTag("ingots/anthektite");
        public static final TagKey<Item> NUGGETS_STEEL = forgeTag("nuggets/steel");
        public static final TagKey<Item> RAW_MATERIALS_STEEL = forgeTag("raw_materials/steel");
        public static final TagKey<Item> ORES_ATELIS = forgeTag("ores/atelis_scrap");

        public static final TagKey<Item> REPAIRS_STEEL_EQUIPMENT = elementusTag("repairs_steel_equipment");
        public static final TagKey<Item> REPAIRS_DIARKRITE_EQUIPMENT = elementusTag("repairs_diarkrite_equipment");
        public static final TagKey<Item> REPAIRS_ANTHEKTITE_EQUIPMENT = elementusTag("repairs_anthektite_equipment");

        public static final TagKey<Item> REPAIRS_CATALYST_ARMOR = elementusTag("repairs_catalyst_armor");
        public static final TagKey<Item> CATALYST_ITEMS = elementusTag("catalyst/core");
        public static final TagKey<Item> CATALYST_ELYTRA = elementusTag("catalyst/elytra");

        public static final TagKey<Item> TOUGH_PICAXE = elementusTag("tough_pickaxe");
        public static final TagKey<Item> EFFICIENT_PICKAXE = elementusTag("efficient_pickaxe");

        public static final TagKey<Item> REPAIRS_DIARKRITE_MAGE_ARMOR = elementusTag("repairs_diarkrite_mage_armor");
        public static final TagKey<Item> REPAIRS_ANTHEKTITE_MAGE_ARMOR = elementusTag("repairs_anthektite_mage_armor");

        public static final TagKey<Item> STORAGE_BLOCK_STEEL = forgeTag("storage_blocks/steel");
        public static final TagKey<Item> STORAGE_BLOCK_DIARKRITE = forgeTag("storage_blocks/diarkrite");
        public static final TagKey<Item> STORAGE_BLOCK_ANTHEKTITE = forgeTag("storage_blocks/anthektite");

        public static final TagKey<Item> STEEL_RECYCLABLE = elementusTag("steel_recyclable");
        public static final TagKey<Item> MOVCADIA_LOGS = elementusTag("movcadia_logs");

        public static final TagKey<Item> FD_KNIFE = forgeTag("tools/knifes");
        public static final TagKey<Item> BERRIES = forgeTag("berries");

        public static final TagKey<Item> STEEL_GOLEM_REPAIR = elementusTag("steel_golem_repair");
        public static final TagKey<Item> STEEL_GOLEM_LEAVES_DECORATION = elementusTag("steel_golem_leaves_decoration");
        public static final TagKey<Item> STEEL_GOLEM_CARPET_DECORATION = elementusTag("steel_golem_carpet");
        public static final TagKey<Item> STEEL_GOLEM_MOSS = elementusTag("steel_golem_moss");

        public static final TagKey<Item> REPAIRS_DIARKRITE_IRON_ARMOR = elementusTag("repairs_diarkrite_iron_armor");
        public static final TagKey<Item> REPAIRS_DIARKRITE_GOLD_ARMOR = elementusTag("repairs_diarkrite_gold_armor");
        public static final TagKey<Item> REPAIRS_DIARKRITE_EMERALD_ARMOR = elementusTag("repairs_diarkrite_emerald_armor");
        public static final TagKey<Item> REPAIRS_DIARKRITE_DIAMOND_ARMOR = elementusTag("repairs_diarkrite_diamond_armor");
        public static final TagKey<Item> REPAIRS_ANTHEKTITE_IRON_ARMOR = elementusTag("repairs_anthektite_iron_armor");
        public static final TagKey<Item> REPAIRS_ANTHEKTITE_GOLD_ARMOR = elementusTag("repairs_anthektite_gold_armor");
        public static final TagKey<Item> REPAIRS_ANTHEKTITE_EMERALD_ARMOR = elementusTag("repairs_anthektite_emerald_armor");
        public static final TagKey<Item> REPAIRS_ANTHEKTITE_DIAMOND_ARMOR = elementusTag("repairs_anthektite_diamond_armor");

        public static final TagKey<Item> INGOTS_DIARKRITE_IRON = forgeTag("ingots/diarkrite_iron");
        public static final TagKey<Item> INGOTS_DIARKRITE_GOLD = forgeTag("ingots/diarkrite_gold");
        public static final TagKey<Item> INGOTS_DIARKRITE_EMERALD = forgeTag("ingots/diarkrite_emerald");
        public static final TagKey<Item> INGOTS_DIARKRITE_DIAMOND = forgeTag("ingots/diarkrite_diamond");
        public static final TagKey<Item> INGOTS_ANTHEKTITE_IRON = forgeTag("ingots/anthektite_iron");
        public static final TagKey<Item> INGOTS_ANTHEKTITE_GOLD = forgeTag("ingots/anthektite_gold");
        public static final TagKey<Item> INGOTS_ANTHEKTITE_EMERALD = forgeTag("ingots/anthektite_emerald");
        public static final TagKey<Item> INGOTS_ANTHEKTITE_DIAMOND = forgeTag("ingots/anthektite_diamond");

        public static final TagKey<Item> SIMPLY_SWORDS_SWORDS = modTag(simplySwordsID, "swords");
        public static final TagKey<Item> SIMPLY_SWORDS_STEEL = modTag(simplySwordsID, "steel_tools");
        public static final TagKey<Item> SIMPLY_SWORDS_DIARKRITE = modTag(simplySwordsID, "diarkrite_tools");
        public static final TagKey<Item> SIMPLY_SWORDS_ANTHEKTITE = modTag(simplySwordsID, "anthektite_tools");

        public static final TagKey<Item> SNIFFS_WEAPONS_GREAT_AXES = modTag(sniffsWeaponsID, "great_axes");
        public static final TagKey<Item> SNIFFS_WEAPONS_GREAT_PICKAXES = modTag(sniffsWeaponsID, "great_pickaxes");
        public static final TagKey<Item> SNIFFS_WEAPONS_GREAT_SWORDS = modTag(sniffsWeaponsID, "great_swords");
        public static final TagKey<Item> SNIFFS_WEAPONS_GREAT_NAGINATA = modTag(sniffsWeaponsID, "naginata");

        public static final TagKey<Item> CURIOS_SPELLBOOK = modTag("curios", "spellbook");

        public static final TagKey<Item> AC_FERROMAGNETIC = modTag("alexscaves", "ferromagnetic_items");

        public static final TagKey<Item> COMMAND_BLOCK_TOOLS = modTag(witherStormModID, "command_block_tools");

        public static final TagKey<Item> ANTI_POWER_BOW = modTag(archeryExpID, "anti_power_bow");

        public static final TagKey<Item> CREATE_MOODED_STRIPPED_LOGS = modTag(createID, "modded_stripped_logs");

        private static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }

        private static TagKey<Item> elementusTag(String name) {
            return ItemTags.create(new ResourceLocation("elementus", name));
        }

        private static TagKey<Item> modTag(String namespace, String path) {
            return ItemTags.create(new ResourceLocation(namespace, path));
        }
    }
    public static class Blocks {
        public static final TagKey<Block> STEEL_STORAGE_BLOCK = forgeTag("storage_blocks/steel");
        public static final TagKey<Block> DIARKRITE_STORAGE_BLOCK = forgeTag("storage_blocks/diarkrite");
        public static final TagKey<Block> ANTHEKTITE_STORAGE_BLOCK = forgeTag("storage_blocks/anthektite");

        public static final TagKey<Block> MOVCADIA_LOGS = elementusTag("movcadia_logs");

        public static final TagKey<Block> DIARKRITE_EFFICIENT = elementusTag("diarkrite_efficient");

        public static final TagKey<Block> MOVCADIA_GROWS_ON = elementusTag("movcadia_grows_on");
        public static final TagKey<Block> MOVCADIA_ROOTED_DIRT = elementusTag("movcadia_rooted_dirt");
        public static final TagKey<Block> MOVCADIA_ROOTED_STONE = elementusTag("movcadia_rooted_stone");
        public static final TagKey<Block> MOVCADIA_ROOTED_DEEPSLATE = elementusTag("movcadia_rooted_deepslate");

        private static TagKey<Block> forgeTag(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }

        private static TagKey<Block> elementusTag(String name) {
            return BlockTags.create(new ResourceLocation("elementus", name));
        }
    }
    public static class DamageTypes {
        public static final TagKey<DamageType> STEEL_GOLEM_IMMUNE = elementusTag("steel_golem_immune");

        private static TagKey<DamageType> forgeTag(String name) {
            return TagKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("forge", name));
        }

        private static TagKey<DamageType> elementusTag(String name) {
            return TagKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(MODID, name));
        }
    }
}
