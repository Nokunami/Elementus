package net.nokunami.elementus.datagen.generators;

import com.aetherteam.aether.AetherTags;
import com.ninni.twigs.TwigsTags;
import io.redspace.ironsspellbooks.util.ModTags;
import moze_intel.projecte.api.ProjectEAPI;
import moze_intel.projecte.gameObjs.PETags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.nokunami.elementus.Elementus;
import net.nokunami.elementus.common.Etags;
import net.nokunami.elementus.common.registry.ModBlocks.*;
import nonamecrackers2.witherstormmod.common.tags.WitherStormModBlockTags;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

import static net.nokunami.elementus.Elementus.modLoc;
import static net.nokunami.elementus.ModChecker.*;

public class ModBlockTagsData extends BlockTagsProvider {
    public ModBlockTagsData(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> compFeaHoldP, @Nullable ExistingFileHelper existHelper) {
        super(packOutput, compFeaHoldP, Elementus.MODID, existHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider pProvider) {
        this.minecraftTags();
        this.forgeTags();
        this.elementusTags();
        this.modCompatibilityTags();
    }

    private void minecraftTags() {
        //Block Interaction Tags
        tag(BlockTags.MINEABLE_WITH_AXE).add(ElementusBlocks.MOVCADIA_LOG.get(), ElementusBlocks.STRIPPED_MOVCADIA_LOG.get())
                .add(ElementusBlocks.MOVCADIA_WOOD.get(), ElementusBlocks.STRIPPED_MOVCADIA_WOOD.get())
                .add(ElementusBlocks.MOVCADIA_PLANKS.get(), ElementusBlocks.MOVCADIA_STAIRS.get(), ElementusBlocks.MOVCADIA_SLAB.get())
                .add(ElementusBlocks.MOVCADIA_FENCE.get(), ElementusBlocks.MOVCADIA_FENCE_GATE.get())
                .add(ElementusBlocks.MOVCADIA_DOOR.get(), ElementusBlocks.MOVCADIA_TRAPDOOR.get())
                .add(ElementusBlocks.MOVCADIA_PRESSURE_PLATE.get(), ElementusBlocks.MOVCADIA_BUTTON.get())
                .add(ElementusBlocks.MOVCADIA_SIGN.get(), ElementusBlocks.MOVCADIA_WALL_SIGN.get())
                .add(ElementusBlocks.MOVCADIA_HANGING_SIGN.get(), ElementusBlocks.MOVCADIA_WALL_HANGING_SIGN.get())
                .add(ElementusBlocks.STURDY_MOVCADIA_SIGN.get()).add(ElementusBlocks.STURDY_MOVCADIA_WALL_SIGN.get())
                .add(ElementusBlocks.MOVCADIA_CHEST.get())
                .addOptional(modLoc("movcadia_cabinet")).addOptional(modLoc("movcadia_table"));

        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ElementusBlocks.STEEL_BLOCK.get(), ElementusBlocks.ANTHEKTITE_BLOCK.get(), ElementusBlocks.DIARKRITE_BLOCK.get(), ElementusBlocks.REMNANT.get(),
                        ElementusBlocks.STEEL_TILES.get(), ElementusBlocks.STEEL_TILE_STAIR.get(), ElementusBlocks.STEEL_TILE_SLAB.get())
                .addOptional(modLoc("diarkrite_iron_block")).addOptional(modLoc("diarkrite_gold_block"))
                .addOptional(modLoc("diarkrite_emerald_block")).addOptional(modLoc("diarkrite_diamond_block"))

                .addOptional(modLoc("anthektite_iron_block")).addOptional(modLoc("anthektite_gold_block"))
                .addOptional(modLoc("anthektite_emerald_block")).addOptional(modLoc("anthektite_diamond_block"));

        tag(BlockTags.MINEABLE_WITH_HOE).add(ElementusBlocks.MOVCADIA_LEAVES.get(), ElementusBlocks.FLOWERING_MOVCADIA_LEAVES.get());

        tag(BlockTags.NEEDS_DIAMOND_TOOL).add(ElementusBlocks.ANTHEKTITE_BLOCK.get(), ElementusBlocks.DIARKRITE_BLOCK.get(), ElementusBlocks.REMNANT.get())
                .addOptional(modLoc("diarkrite_iron_block")).addOptional(modLoc("diarkrite_gold_block"))
                .addOptional(modLoc("diarkrite_emerald_block")).addOptional(modLoc("diarkrite_diamond_block"))

                .addOptional(modLoc("anthektite_iron_block")).addOptional(modLoc("anthektite_gold_block"))
                .addOptional(modLoc("anthektite_emerald_block")).addOptional(modLoc("anthektite_diamond_block"));

        tag(BlockTags.NEEDS_IRON_TOOL).add(ElementusBlocks.STEEL_BLOCK.get(), ElementusBlocks.STEEL_TILES.get(), ElementusBlocks.STEEL_TILE_STAIR.get(), ElementusBlocks.STEEL_TILE_SLAB.get());

        tag(BlockTags.SWORD_EFFICIENT).add(ElementusBlocks.MOVCADIA_LEAVES.get(), ElementusBlocks.FLOWERING_MOVCADIA_LEAVES.get());

        //Block Tags
        //Logs
        tag(BlockTags.LOGS).add(ElementusBlocks.MOVCADIA_LOG.get(), ElementusBlocks.STRIPPED_MOVCADIA_LOG.get())
                .add(ElementusBlocks.MOVCADIA_WOOD.get(), ElementusBlocks.STRIPPED_MOVCADIA_WOOD.get());

        tag(BlockTags.OVERWORLD_NATURAL_LOGS).add(ElementusBlocks.MOVCADIA_LOG.get());

        tag(Etags.Blocks.MOVCADIA_LOGS).add(ElementusBlocks.MOVCADIA_LOG.get(), ElementusBlocks.STRIPPED_MOVCADIA_LOG.get())
                .add(ElementusBlocks.MOVCADIA_WOOD.get(), ElementusBlocks.STRIPPED_MOVCADIA_WOOD.get());

        //Planks
        tag(BlockTags.PLANKS).add(ElementusBlocks.MOVCADIA_PLANKS.get());

        //Block Variant Tags
        tag(BlockTags.STAIRS).add(ElementusBlocks.MOVCADIA_STAIRS.get(), ElementusBlocks.STEEL_TILE_STAIR.get());
        tag(BlockTags.SLABS).add(ElementusBlocks.MOVCADIA_SLAB.get(), ElementusBlocks.STEEL_TILE_SLAB.get());

        tag(BlockTags.WOODEN_STAIRS).add(ElementusBlocks.MOVCADIA_STAIRS.get());
        tag(BlockTags.WOODEN_SLABS).add(ElementusBlocks.MOVCADIA_SLAB.get());

        //Fences
        tag(BlockTags.FENCES).add(ElementusBlocks.MOVCADIA_FENCE.get());
        tag(BlockTags.FENCE_GATES).add(ElementusBlocks.MOVCADIA_FENCE_GATE.get());

        tag(BlockTags.WOODEN_FENCES).add(ElementusBlocks.MOVCADIA_FENCE.get());
        tag(BlockTags.UNSTABLE_BOTTOM_CENTER).add(ElementusBlocks.MOVCADIA_FENCE_GATE.get());

        //Doors
        tag(BlockTags.DOORS).add(ElementusBlocks.MOVCADIA_DOOR.get());
        tag(BlockTags.TRAPDOORS).add(ElementusBlocks.MOVCADIA_TRAPDOOR.get());

        tag(BlockTags.WOODEN_DOORS).add(ElementusBlocks.MOVCADIA_DOOR.get());
        tag(BlockTags.WOODEN_TRAPDOORS).add(ElementusBlocks.MOVCADIA_TRAPDOOR.get());

        //Redstone Blocks
        tag(BlockTags.PRESSURE_PLATES).add(ElementusBlocks.MOVCADIA_PRESSURE_PLATE.get());
        tag(BlockTags.BUTTONS).add(ElementusBlocks.MOVCADIA_BUTTON.get());

        tag(BlockTags.WOODEN_PRESSURE_PLATES).add(ElementusBlocks.MOVCADIA_PRESSURE_PLATE.get());
        tag(BlockTags.WOODEN_BUTTONS).add(ElementusBlocks.MOVCADIA_BUTTON.get());

        tag(BlockTags.WALL_POST_OVERRIDE).add(ElementusBlocks.MOVCADIA_PRESSURE_PLATE.get());

        //Signs
        tag(BlockTags.STANDING_SIGNS).add(ElementusBlocks.MOVCADIA_SIGN.get(), ElementusBlocks.STURDY_MOVCADIA_SIGN.get());
        tag(BlockTags.WALL_SIGNS).add(ElementusBlocks.MOVCADIA_WALL_SIGN.get(), ElementusBlocks.STURDY_MOVCADIA_WALL_SIGN.get());

        tag(BlockTags.CEILING_HANGING_SIGNS).add(ElementusBlocks.MOVCADIA_HANGING_SIGN.get());
        tag(BlockTags.WALL_HANGING_SIGNS).add(ElementusBlocks.MOVCADIA_WALL_HANGING_SIGN.get());

        //Misc Tags
        tag(BlockTags.PARROTS_SPAWNABLE_ON).add(ElementusBlocks.MOVCADIA_LOG.get(),
                ElementusBlocks.STRIPPED_MOVCADIA_LOG.get(), ElementusBlocks.MOVCADIA_WOOD.get(),
                ElementusBlocks.STRIPPED_MOVCADIA_WOOD.get(), ElementusBlocks.FLOWERING_MOVCADIA_LEAVES.get());

        tag(BlockTags.BEACON_BASE_BLOCKS).add(ElementusBlocks.STEEL_BLOCK.get(), ElementusBlocks.ANTHEKTITE_BLOCK.get(), ElementusBlocks.DIARKRITE_BLOCK.get())
                .addOptional(modLoc("diarkrite_iron_block")).addOptional(modLoc("diarkrite_gold_block"))
                .addOptional(modLoc("diarkrite_emerald_block")).addOptional(modLoc("diarkrite_diamond_block"))

                .addOptional(modLoc("anthektite_iron_block")).addOptional(modLoc("anthektite_gold_block"))
                .addOptional(modLoc("anthektite_emerald_block")).addOptional(modLoc("anthektite_diamond_block"));

//        tag(BlockTags.MOSS_REPLACEABLE).add(ElementusBlocks.MOVCADIA_ROOTS.get());
//        tag(BlockTags.LUSH_GROUND_REPLACEABLE).add(ElementusBlocks.MOVCADIA_ROOTS.get());
//        tag(BlockTags.SCULK_REPLACEABLE).add(ElementusBlocks.MOVCADIA_ROOTS.get());

        //Entity Interaction Tags
        tag(BlockTags.SNAPS_GOAT_HORN).add(ElementusBlocks.MOVCADIA_LOG.get());
        tag(BlockTags.GUARDED_BY_PIGLINS).add(ElementusBlocks.MOVCADIA_CHEST.get());

        //World Tags
        tag(BlockTags.LAVA_POOL_STONE_CANNOT_REPLACE).add(ElementusBlocks.MOVCADIA_LOG.get(),
                ElementusBlocks.STRIPPED_MOVCADIA_LOG.get(), ElementusBlocks.MOVCADIA_WOOD.get(),
                ElementusBlocks.STRIPPED_MOVCADIA_WOOD.get(), ElementusBlocks.MOVCADIA_CHEST.get(),
                ElementusBlocks.MOVCADIA_LEAVES.get(), ElementusBlocks.FLOWERING_MOVCADIA_LEAVES.get());
        tag(BlockTags.FEATURES_CANNOT_REPLACE).add(ElementusBlocks.MOVCADIA_CHEST.get());
        tag(BlockTags.REPLACEABLE_BY_TREES).add(ElementusBlocks.MOVCADIA_LEAVES.get(), ElementusBlocks.FLOWERING_MOVCADIA_LEAVES.get());

        //Game Interaction Tags
        tag(BlockTags.COMPLETES_FIND_TREE_TUTORIAL).add(ElementusBlocks.MOVCADIA_LOG.get(),
                ElementusBlocks.STRIPPED_MOVCADIA_LOG.get(), ElementusBlocks.MOVCADIA_WOOD.get(),
                ElementusBlocks.STRIPPED_MOVCADIA_WOOD.get(), ElementusBlocks.MOVCADIA_LEAVES.get(),
                ElementusBlocks.FLOWERING_MOVCADIA_LEAVES.get());
    }

    private void forgeTags() {
        //Storage Blocks
        tag(Tags.Blocks.STORAGE_BLOCKS).add(ElementusBlocks.STEEL_BLOCK.get(), ElementusBlocks.STEEL_BLOCK.get())
                .addTag(Etags.Blocks.STEEL_STORAGE_BLOCK).addTag(Etags.Blocks.DIARKRITE_STORAGE_BLOCK)
                .addTag(Etags.Blocks.ANTHEKTITE_STORAGE_BLOCK);
        tag(Etags.Blocks.STEEL_STORAGE_BLOCK).add(ElementusBlocks.STEEL_BLOCK.get());
        tag(Etags.Blocks.DIARKRITE_STORAGE_BLOCK).add(ElementusBlocks.DIARKRITE_BLOCK.get());
        tag(Etags.Blocks.ANTHEKTITE_STORAGE_BLOCK).add(ElementusBlocks.ANTHEKTITE_BLOCK.get());

        //Fences
        tag(Tags.Blocks.FENCE_GATES_WOODEN).add(ElementusBlocks.MOVCADIA_FENCE.get());

        //Chests
        tag(Tags.Blocks.CHESTS).add(ElementusBlocks.MOVCADIA_CHEST.get());
        tag(Tags.Blocks.CHESTS_WOODEN).add(ElementusBlocks.MOVCADIA_CHEST.get());
    }

    private void elementusTags() {
        tag(Etags.Blocks.MOVCADIA_LOGS).add(ElementusBlocks.MOVCADIA_LOG.get()).add(ElementusBlocks.STRIPPED_MOVCADIA_LOG.get())
                .add(ElementusBlocks.MOVCADIA_WOOD.get()).add(ElementusBlocks.STRIPPED_MOVCADIA_WOOD.get());

        tag(Etags.Blocks.DIARKRITE_EFFICIENT).addTag(BlockTags.NEEDS_DIAMOND_TOOL)
                .addOptionalTag(new ResourceLocation("forge", "needs_netherite_tools"));
    }

    private void modCompatibilityTags() {
        if (aether) {
            tag(AetherTags.Blocks.GRAVITITE_ABILITY_BLACKLIST).add(ElementusBlocks.MOVCADIA_FENCE_GATE.get(),
                    ElementusBlocks.MOVCADIA_TRAPDOOR.get(), ElementusBlocks.MOVCADIA_PRESSURE_PLATE.get(),
                    ElementusBlocks.MOVCADIA_BUTTON.get());
            tag(AetherTags.Blocks.NON_BRONZE_DUNGEON_REPLACEABLE).add(ElementusBlocks.MOVCADIA_CHEST.get());
        }
        if (ironsSpellbooks) tag(ModTags.GUARDED_BY_WIZARDS).add(ElementusBlocks.MOVCADIA_CHEST.get());
        if (projectE) {
            tag(PETags.Blocks.MINEABLE_WITH_HAMMER).add(ElementusBlocks.STEEL_TILES.get(), ElementusBlocks.STEEL_TILE_STAIR.get(), ElementusBlocks.STEEL_TILE_SLAB.get());
            tag(PETags.Blocks.MINEABLE_WITH_KATAR).add(ElementusBlocks.MOVCADIA_LEAVES.get(), ElementusBlocks.FLOWERING_MOVCADIA_LEAVES.get());
            tag(PETags.Blocks.MINEABLE_WITH_MORNING_STAR).add(ElementusBlocks.STEEL_TILES.get(), ElementusBlocks.STEEL_TILE_STAIR.get(), ElementusBlocks.STEEL_TILE_SLAB.get());
        }
        if (twigs) tag(TwigsTags.TABLES_BLOCK).addOptional(modLoc("movcadia_table"));
        if (witherStormMod) {
            tag(WitherStormModBlockTags.NATURE_CLUSTER_WHITELIST).add(ElementusBlocks.MOVCADIA_LEAVES.get(), ElementusBlocks.FLOWERING_MOVCADIA_LEAVES.get());
            tag(WitherStormModBlockTags.SMALL_CLUSTER_BLACKLIST).add(ElementusBlocks.MOVCADIA_LEAVES.get(), ElementusBlocks.FLOWERING_MOVCADIA_LEAVES.get());
        }
    }
}
