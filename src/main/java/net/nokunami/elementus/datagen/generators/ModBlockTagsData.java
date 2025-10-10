package net.nokunami.elementus.datagen.generators;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.nokunami.elementus.Elementus;
import net.nokunami.elementus.common.Etags;
import net.nokunami.elementus.common.registry.EBlocks;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

import static net.nokunami.elementus.Elementus.modLoc;

public class ModBlockTagsData extends BlockTagsProvider {
    public ModBlockTagsData(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> compFeaHoldP, @Nullable ExistingFileHelper existHelper) {
        super(packOutput, compFeaHoldP, Elementus.MODID, existHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider pProvider) {
        this.minecraftTags();
        this.forgeTags();
        this.elementusTags();
    }

    private void minecraftTags() {
        //Block Interaction Tags
        tag(BlockTags.MINEABLE_WITH_AXE).add(EBlocks.MOVCADIA_LOG.get(), EBlocks.STRIPPED_MOVCADIA_LOG.get())
                .add(EBlocks.MOVCADIA_WOOD.get(), EBlocks.STRIPPED_MOVCADIA_WOOD.get())
                .add(EBlocks.MOVCADIA_PLANKS.get(), EBlocks.MOVCADIA_STAIRS.get(), EBlocks.MOVCADIA_SLAB.get())
                .add(EBlocks.MOVCADIA_FENCE.get(), EBlocks.MOVCADIA_FENCE_GATE.get())
                .add(EBlocks.MOVCADIA_DOOR.get(), EBlocks.MOVCADIA_TRAPDOOR.get())
                .add(EBlocks.MOVCADIA_PRESSURE_PLATE.get(), EBlocks.MOVCADIA_BUTTON.get())
                .add(EBlocks.MOVCADIA_SIGN.get(), EBlocks.MOVCADIA_WALL_SIGN.get())
                .add(EBlocks.MOVCADIA_HANGING_SIGN.get(), EBlocks.MOVCADIA_WALL_HANGING_SIGN.get())
                .add(EBlocks.STURDY_MOVCADIA_SIGN.get()).add(EBlocks.STURDY_MOVCADIA_WALL_SIGN.get())
                .add(EBlocks.MOVCADIA_CHEST.get())
                .addOptional(modLoc("movcadia_cabinet")).addOptional(modLoc("movcadia_table"));

        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(EBlocks.STEEL_BLOCK.get(), EBlocks.ANTHEKTITE_BLOCK.get(), EBlocks.DIARKRITE_BLOCK.get(), EBlocks.REMNANT.get(),
                        EBlocks.STEEL_BARS.get(), EBlocks.STEEL_TILES.get(), EBlocks.STEEL_TILE_STAIR.get(), EBlocks.STEEL_TILE_SLAB.get())
                .addOptional(modLoc("diarkrite_iron_block")).addOptional(modLoc("diarkrite_gold_block"))
                .addOptional(modLoc("diarkrite_emerald_block")).addOptional(modLoc("diarkrite_diamond_block"))

                .addOptional(modLoc("anthektite_iron_block")).addOptional(modLoc("anthektite_gold_block"))
                .addOptional(modLoc("anthektite_emerald_block")).addOptional(modLoc("anthektite_diamond_block"));

        tag(BlockTags.MINEABLE_WITH_HOE).add(EBlocks.MOVCADIA_LEAVES.get(), EBlocks.FLOWERING_MOVCADIA_LEAVES.get());

        tag(BlockTags.NEEDS_DIAMOND_TOOL).add(EBlocks.ANTHEKTITE_BLOCK.get(), EBlocks.DIARKRITE_BLOCK.get(), EBlocks.REMNANT.get())
                .addOptional(modLoc("diarkrite_iron_block")).addOptional(modLoc("diarkrite_gold_block"))
                .addOptional(modLoc("diarkrite_emerald_block")).addOptional(modLoc("diarkrite_diamond_block"))

                .addOptional(modLoc("anthektite_iron_block")).addOptional(modLoc("anthektite_gold_block"))
                .addOptional(modLoc("anthektite_emerald_block")).addOptional(modLoc("anthektite_diamond_block"));

        tag(BlockTags.NEEDS_IRON_TOOL).add(EBlocks.STEEL_BLOCK.get(), EBlocks.STEEL_TILES.get(), EBlocks.STEEL_TILE_STAIR.get(), EBlocks.STEEL_TILE_SLAB.get());

        tag(BlockTags.SWORD_EFFICIENT).add(EBlocks.MOVCADIA_LEAVES.get(), EBlocks.FLOWERING_MOVCADIA_LEAVES.get());

        //Block Tags
        //Logs
        tag(BlockTags.LOGS).add(EBlocks.MOVCADIA_LOG.get(), EBlocks.STRIPPED_MOVCADIA_LOG.get())
                .add(EBlocks.MOVCADIA_WOOD.get(), EBlocks.STRIPPED_MOVCADIA_WOOD.get());

        tag(BlockTags.OVERWORLD_NATURAL_LOGS).add(EBlocks.MOVCADIA_LOG.get());

        //Leaves
        tag(BlockTags.LEAVES).add(EBlocks.MOVCADIA_LEAVES.get(), EBlocks.FLOWERING_MOVCADIA_LEAVES.get());

        //Planks
        tag(BlockTags.PLANKS).add(EBlocks.MOVCADIA_PLANKS.get());

        //Block Variant Tags
        tag(BlockTags.STAIRS).add(EBlocks.MOVCADIA_STAIRS.get(), EBlocks.STEEL_TILE_STAIR.get());
        tag(BlockTags.SLABS).add(EBlocks.MOVCADIA_SLAB.get(), EBlocks.STEEL_TILE_SLAB.get());

        tag(BlockTags.WOODEN_STAIRS).add(EBlocks.MOVCADIA_STAIRS.get());
        tag(BlockTags.WOODEN_SLABS).add(EBlocks.MOVCADIA_SLAB.get());

        //Fences
        tag(BlockTags.FENCES).add(EBlocks.MOVCADIA_FENCE.get());
        tag(BlockTags.FENCE_GATES).add(EBlocks.MOVCADIA_FENCE_GATE.get());

        tag(BlockTags.WOODEN_FENCES).add(EBlocks.MOVCADIA_FENCE.get());
        tag(BlockTags.UNSTABLE_BOTTOM_CENTER).add(EBlocks.MOVCADIA_FENCE_GATE.get());

        //Doors
        tag(BlockTags.DOORS).add(EBlocks.MOVCADIA_DOOR.get());
        tag(BlockTags.TRAPDOORS).add(EBlocks.MOVCADIA_TRAPDOOR.get());

        tag(BlockTags.WOODEN_DOORS).add(EBlocks.MOVCADIA_DOOR.get());
        tag(BlockTags.WOODEN_TRAPDOORS).add(EBlocks.MOVCADIA_TRAPDOOR.get());

        //Redstone Blocks
        tag(BlockTags.PRESSURE_PLATES).add(EBlocks.MOVCADIA_PRESSURE_PLATE.get());
        tag(BlockTags.BUTTONS).add(EBlocks.MOVCADIA_BUTTON.get());

        tag(BlockTags.WOODEN_PRESSURE_PLATES).add(EBlocks.MOVCADIA_PRESSURE_PLATE.get());
        tag(BlockTags.WOODEN_BUTTONS).add(EBlocks.MOVCADIA_BUTTON.get());

        tag(BlockTags.WALL_POST_OVERRIDE).add(EBlocks.MOVCADIA_PRESSURE_PLATE.get());

        //Signs
        tag(BlockTags.STANDING_SIGNS).add(EBlocks.MOVCADIA_SIGN.get(), EBlocks.STURDY_MOVCADIA_SIGN.get());
        tag(BlockTags.WALL_SIGNS).add(EBlocks.MOVCADIA_WALL_SIGN.get(), EBlocks.STURDY_MOVCADIA_WALL_SIGN.get());

        tag(BlockTags.CEILING_HANGING_SIGNS).add(EBlocks.MOVCADIA_HANGING_SIGN.get());
        tag(BlockTags.WALL_HANGING_SIGNS).add(EBlocks.MOVCADIA_WALL_HANGING_SIGN.get());

        //Misc Tags
        tag(BlockTags.PARROTS_SPAWNABLE_ON).add(EBlocks.MOVCADIA_LOG.get(),
                EBlocks.STRIPPED_MOVCADIA_LOG.get(), EBlocks.MOVCADIA_WOOD.get(),
                EBlocks.STRIPPED_MOVCADIA_WOOD.get(), EBlocks.FLOWERING_MOVCADIA_LEAVES.get());

        tag(BlockTags.BEACON_BASE_BLOCKS).add(EBlocks.STEEL_BLOCK.get(), EBlocks.ANTHEKTITE_BLOCK.get(), EBlocks.DIARKRITE_BLOCK.get())
                .addOptional(modLoc("diarkrite_iron_block")).addOptional(modLoc("diarkrite_gold_block"))
                .addOptional(modLoc("diarkrite_emerald_block")).addOptional(modLoc("diarkrite_diamond_block"))

                .addOptional(modLoc("anthektite_iron_block")).addOptional(modLoc("anthektite_gold_block"))
                .addOptional(modLoc("anthektite_emerald_block")).addOptional(modLoc("anthektite_diamond_block"));

//        tag(BlockTags.MOSS_REPLACEABLE).add(ElementusBlocks.MOVCADIA_ROOTS.get());
//        tag(BlockTags.LUSH_GROUND_REPLACEABLE).add(ElementusBlocks.MOVCADIA_ROOTS.get());
//        tag(BlockTags.SCULK_REPLACEABLE).add(ElementusBlocks.MOVCADIA_ROOTS.get());

        //Entity Interaction Tags
        tag(BlockTags.SNAPS_GOAT_HORN).add(EBlocks.MOVCADIA_LOG.get());
        tag(BlockTags.GUARDED_BY_PIGLINS).add(EBlocks.MOVCADIA_CHEST.get());

        //World Tags
        tag(BlockTags.LAVA_POOL_STONE_CANNOT_REPLACE).add(EBlocks.MOVCADIA_LOG.get(),
                EBlocks.STRIPPED_MOVCADIA_LOG.get(), EBlocks.MOVCADIA_WOOD.get(),
                EBlocks.STRIPPED_MOVCADIA_WOOD.get(), EBlocks.MOVCADIA_CHEST.get(),
                EBlocks.MOVCADIA_LEAVES.get(), EBlocks.FLOWERING_MOVCADIA_LEAVES.get());
        tag(BlockTags.FEATURES_CANNOT_REPLACE).add(EBlocks.MOVCADIA_CHEST.get());
        tag(BlockTags.REPLACEABLE_BY_TREES).add(EBlocks.MOVCADIA_LEAVES.get(), EBlocks.FLOWERING_MOVCADIA_LEAVES.get());

        //Game Interaction Tags
        tag(BlockTags.COMPLETES_FIND_TREE_TUTORIAL).add(EBlocks.MOVCADIA_LOG.get(),
                EBlocks.STRIPPED_MOVCADIA_LOG.get(), EBlocks.MOVCADIA_WOOD.get(),
                EBlocks.STRIPPED_MOVCADIA_WOOD.get(), EBlocks.MOVCADIA_LEAVES.get(),
                EBlocks.FLOWERING_MOVCADIA_LEAVES.get());
    }

    private void forgeTags() {
        //Storage Blocks
        tag(Tags.Blocks.STORAGE_BLOCKS).add(EBlocks.STEEL_BLOCK.get(), EBlocks.STEEL_BLOCK.get())
                .addTag(Etags.Blocks.STEEL_STORAGE_BLOCK).addTag(Etags.Blocks.DIARKRITE_STORAGE_BLOCK)
                .addTag(Etags.Blocks.ANTHEKTITE_STORAGE_BLOCK);
        tag(Etags.Blocks.STEEL_STORAGE_BLOCK).add(EBlocks.STEEL_BLOCK.get());
        tag(Etags.Blocks.DIARKRITE_STORAGE_BLOCK).add(EBlocks.DIARKRITE_BLOCK.get());
        tag(Etags.Blocks.ANTHEKTITE_STORAGE_BLOCK).add(EBlocks.ANTHEKTITE_BLOCK.get());

        //Fences
        tag(Tags.Blocks.FENCE_GATES_WOODEN).add(EBlocks.MOVCADIA_FENCE.get());

        //Chests
        tag(Tags.Blocks.CHESTS).add(EBlocks.MOVCADIA_CHEST.get());
        tag(Tags.Blocks.CHESTS_WOODEN).add(EBlocks.MOVCADIA_CHEST.get());
    }

    private void elementusTags() {
        tag(Etags.Blocks.MOVCADIA_LOGS).add(EBlocks.MOVCADIA_LOG.get(), EBlocks.STRIPPED_MOVCADIA_LOG.get())
                .add(EBlocks.MOVCADIA_WOOD.get(), EBlocks.STRIPPED_MOVCADIA_WOOD.get());

        tag(Etags.Blocks.DIARKRITE_EFFICIENT).addTag(BlockTags.NEEDS_DIAMOND_TOOL)
                .addOptionalTag(new ResourceLocation("forge", "needs_netherite_tools"));

        tag(Etags.Blocks.MOVCADIA_GROWS_ON).add(Blocks.DIRT, Blocks.SAND, Blocks.TERRACOTTA, Blocks.SNOW_BLOCK, Blocks.POWDER_SNOW)
                .addTag(BlockTags.BASE_STONE_OVERWORLD).addTag(BlockTags.DIRT).addTag(Tags.Blocks.STONE);

        tag(Etags.Blocks.MOVCADIA_ROOTED_DIRT).add(Blocks.DIRT, Blocks.ROOTED_DIRT, Blocks.GRASS_BLOCK, Blocks.MYCELIUM, Blocks.PODZOL);
        tag(Etags.Blocks.MOVCADIA_ROOTED_STONE).add(Blocks.STONE);
        tag(Etags.Blocks.MOVCADIA_ROOTED_DEEPSLATE).add(Blocks.DEEPSLATE);
    }
}
