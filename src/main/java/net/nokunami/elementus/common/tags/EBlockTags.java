package net.nokunami.elementus.common.tags;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class EBlockTags {
    public static final TagKey<Block> STEEL_STORAGE_BLOCK = forgeTag("storage_blocks/steel");
    public static final TagKey<Block> DIARKRITE_STORAGE_BLOCK = forgeTag("storage_blocks/diarkrite");
    public static final TagKey<Block> ANTHEKTITE_STORAGE_BLOCK = forgeTag("storage_blocks/anthektite");

    public static final TagKey<Block> MOVCADIA_LOGS = elementusTag("movcadia_logs");

    public static final TagKey<Block> DIARKRITE_EFFICIENT = elementusTag("diarkrite_efficient");

    public static final TagKey<Block> MOVCADIA_GROWS_ON = elementusTag("movcadia_grows_on");
    public static final TagKey<Block> MOVCADIA_ROOTED_DIRT = elementusTag("movcadia_rooted_dirt");
    public static final TagKey<Block> MOVCADIA_ROOTED_STONE = elementusTag("movcadia_rooted_stone");
    public static final TagKey<Block> MOVCADIA_ROOTED_DEEPSLATE = elementusTag("movcadia_rooted_deepslate");

    public static final TagKey<Block> ANTHEKTITE_SLASH_BREAKABLE = elementusTag("mine_with_anthektite_slash");
    public static final TagKey<Block> ANTHEKTITE_SLASH_BLACKLIST = elementusTag("mine_with_anthektite_slash_blacklist");

    private static TagKey<Block> forgeTag(String name) {
        return BlockTags.create(new ResourceLocation("forge", name));
    }

    private static TagKey<Block> elementusTag(String name) {
        return BlockTags.create(new ResourceLocation("elementus", name));
    }
}
