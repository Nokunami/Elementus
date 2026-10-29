package net.nokunami.elementus.common.tags;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import static net.nokunami.elementus.Elementus.EID;
import static net.nokunami.elementus.Elementus.modLoc;
import static net.nokunami.elementus.ElementusModCheck.*;

public class EBlockTags {

    public static class IronsSpellbooks {
        public static final TagKey<Block> GUARDED_BY_WIZARDS = modTag(ironsSpellbooksID, "guarded_by_wizards");
    }
    public static class Aether {
        public static final TagKey<Block> GRAVITITE_ABILITY_BLACKLIST = modTag(aetherID, "gravitite_ability_blacklist");
        public static final TagKey<Block> NON_TUNNEL_REPLACEABLE = modTag(aetherID, "non_tunnel_replaceable");
    }
    public static class WitherStormMod {
        public static final TagKey<Block> CLUSTER_WHITELIST = modTag(witherStormModID, "wither_storm_nature_cluster_whitelist");
    }

    public static final TagKey<Block> NEEDS_ASTALITE_TOOL = elementusTag("needs_astalite_tool");
    public static final TagKey<Block> NEEDS_DIARKRITE_TOOL = elementusTag("needs_diarkrite_tool");
    public static final TagKey<Block> NEEDS_ANTHEKTITE_TOOL = elementusTag("needs_anthektite_tool");
    public static final TagKey<Block> NEEDS_MOVCADIA_TOOL = elementusTag("needs_movcadia_tool");

    public static final TagKey<Block> NEEDS_NETHERITE_TOOL = forgeTag("needs_netherite_tools");
    public static final TagKey<Block> STEEL_STORAGE_BLOCK = forgeTag("storage_blocks/steel");
    public static final TagKey<Block> DIARKRITE_STORAGE_BLOCK = forgeTag("storage_blocks/diarkrite");
    public static final TagKey<Block> ANTHEKTITE_STORAGE_BLOCK = forgeTag("storage_blocks/anthektite");

    public static final TagKey<Block> MOVCADIA_LOGS = elementusTag("movcadia_logs");

    public static final TagKey<Block> ASTALITE_EFFICIENT = elementusTag("astalite_efficient");
    public static final TagKey<Block> DIARKRITE_EFFICIENT = elementusTag("diarkrite_efficient");
    public static final TagKey<Block> ANTHEKTITE_EFFICIENT = elementusTag("anthektite_efficient");
    public static final TagKey<Block> MOVCADIA_EFFICIENT = elementusTag("movcadia_efficient");

    public static final TagKey<Block> MOVCADIA_GROWS_ON = elementusTag("movcadia_grows_on");
    public static final TagKey<Block> MOVCADIA_ROOTED_DIRT = elementusTag("movcadia_rooted_dirt");
    public static final TagKey<Block> MOVCADIA_ROOTED_STONE = elementusTag("movcadia_rooted_stone");
    public static final TagKey<Block> MOVCADIA_ROOTED_DEEPSLATE = elementusTag("movcadia_rooted_deepslate");

    public static final TagKey<Block> ANTHEKTITE_SLASH_BREAKABLE = elementusTag("mine_with_anthektite_slash");
    public static final TagKey<Block> ANTHEKTITE_SLASH_BLACKLIST = elementusTag("mine_with_anthektite_slash_blacklist");

    private static TagKey<Block> forgeTag(String name) { return modTag("forge", name); }
    private static TagKey<Block> elementusTag(String name) { return modTag(EID, name); }
    private static TagKey<Block> modTag(String mod, String name) { return BlockTags.create(modLoc(mod, name)); }
}
