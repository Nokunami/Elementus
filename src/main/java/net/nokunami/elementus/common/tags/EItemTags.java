package net.nokunami.elementus.common.tags;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import static net.nokunami.elementus.ModChecker.*;

public class EItemTags {
    public static final TagKey<Item> INGOTS_STEEL = forgeTag("ingots/steel");
    public static final TagKey<Item> INGOTS_DIARKRITE = forgeTag("ingots/diarkrite");
    public static final TagKey<Item> INGOTS_ANTHEKTITE = forgeTag("ingots/anthektite");
    public static final TagKey<Item> NUGGETS_STEEL = forgeTag("nuggets/steel");
    public static final TagKey<Item> RAW_MATERIALS_STEEL = forgeTag("raw_materials/steel");
    public static final TagKey<Item> ORES_ATELIS = forgeTag("ores/atelis_scrap");

    public static final TagKey<Item> REPAIRS_ASTALITE_EQUIPMENT = elementusTag("repairs_steel_equipment");
    public static final TagKey<Item> REPAIRS_DIARKRITE_EQUIPMENT = elementusTag("repairs_diarkrite_equipment");
    public static final TagKey<Item> REPAIRS_ANTHEKTITE_EQUIPMENT = elementusTag("repairs_anthektite_equipment");
    public static final TagKey<Item> REPAIRS_MOVCADIA_EQUIPMENT = elementusTag("repairs_movcadia_equipment");

    public static final TagKey<Item> REPAIRS_CATALYST_ARMOR = elementusTag("repairs_catalyst_armor");
    public static final TagKey<Item> CATALYST_ITEMS = elementusTag("catalyst/core");
    public static final TagKey<Item> CATALYST_ELYTRA = elementusTag("catalyst/elytra");
    public static final TagKey<Item> CORE_NETHER_STAR = elementusTag("catalyst/nether_star");
    public static final TagKey<Item> CORE_HEART_OF_THE_SEA = elementusTag("catalyst/heart_of_the_sea");
    public static final TagKey<Item> CORE_TOTEM_OF_UNDYING = elementusTag("catalyst/totem_of_undying");

    public static final TagKey<Item> ARCANE_SHARPNESS_COMPATIBLE = elementusTag("arcane_sharpness_compatible");

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

    public static final TagKey<Item> STEEL_GOLEM_HEAL = elementusTag("steel_golem_heal");
    public static final TagKey<Item> STEEL_GOLEM_REPAIR_HALF = elementusTag("steel_golem_repair_half");
    public static final TagKey<Item> STEEL_GOLEM_REPAIR_FULL = elementusTag("steel_golem_repair_full");
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
