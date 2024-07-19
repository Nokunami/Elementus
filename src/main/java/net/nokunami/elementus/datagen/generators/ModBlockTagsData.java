package net.nokunami.elementus.datagen.generators;

import com.aetherteam.aether.AetherTags;
import com.ninni.twigs.TwigsTags;
import io.redspace.ironsspellbooks.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.nokunami.elementus.Elementus;
import net.nokunami.elementus.common.Etags;
import net.nokunami.elementus.common.compat.twigs.TWModBlocks;
import net.nokunami.elementus.common.registry.ModBlocks;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModBlockTagsData extends BlockTagsProvider {
    public ModBlockTagsData(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> compFeaHoldP, @Nullable ExistingFileHelper existHelper) {
        super(packOutput, compFeaHoldP, Elementus.MODID, existHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.minecraftTags();
        this.forgeTags();
        this.elementusTags();
        this.modCompatibilityTags();
    }

    private void minecraftTags() {
        //Block Interaction Tags
        this.tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.MOVCADIA_LOG.get()).add(ModBlocks.STRIPPED_MOVCADIA_LOG.get())
                .add(ModBlocks.MOVCADIA_WOOD.get()).add(ModBlocks.STRIPPED_MOVCADIA_WOOD.get())
                .add(ModBlocks.MOVCADIA_PLANKS.get()).add(ModBlocks.MOVCADIA_STAIRS.get()).add(ModBlocks.MOVCADIA_SLAB.get())
                .add(ModBlocks.MOVCADIA_FENCE.get()).add(ModBlocks.MOVCADIA_FENCE_GATE.get())
                .add(ModBlocks.MOVCADIA_DOOR.get()).add(ModBlocks.MOVCADIA_TRAPDOOR.get())
                .add(ModBlocks.MOVCADIA_PRESSURE_PLATE.get()).add(ModBlocks.MOVCADIA_BUTTON.get())
                .add(ModBlocks.MOVCADIA_SIGN.get()).add(ModBlocks.MOVCADIA_WALL_SIGN.get())
                .add(ModBlocks.MOVCADIA_HANGING_SIGN.get()).add(ModBlocks.MOVCADIA_WALL_HANGING_SIGN.get())
                .add(ModBlocks.STURDY_MOVCADIA_SIGN.get()).add(ModBlocks.STURDY_MOVCADIA_WALL_SIGN.get())
                .add(ModBlocks.MOVCADIA_CHEST.get())
                .addOptional(new ResourceLocation("elementus", "movcadia_cabinet"))
                .addOptional(new ResourceLocation("elementus", "movcadia_table"));

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.STEEL_BLOCK.get()).add(ModBlocks.ANTHEKTITE_BLOCK.get()).add(ModBlocks.DIARKRITE_BLOCK.get()).add(ModBlocks.REMNANT.get())
                .addOptional(new ResourceLocation("elementus", "diarkrite_iron_block"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_gold_block"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_emerald_block"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_diamond_block"))

                .addOptional(new ResourceLocation("elementus", "anthektite_iron_block"))
                .addOptional(new ResourceLocation("elementus", "anthektite_gold_block"))
                .addOptional(new ResourceLocation("elementus", "anthektite_emerald_block"))
                .addOptional(new ResourceLocation("elementus", "anthektite_diamond_block"));

        this.tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.ANTHEKTITE_BLOCK.get()).add(ModBlocks.DIARKRITE_BLOCK.get()).add(ModBlocks.REMNANT.get())
                .addOptional(new ResourceLocation("elementus", "diarkrite_iron_block"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_gold_block"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_emerald_block"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_diamond_block"))

                .addOptional(new ResourceLocation("elementus", "anthektite_iron_block"))
                .addOptional(new ResourceLocation("elementus", "anthektite_gold_block"))
                .addOptional(new ResourceLocation("elementus", "anthektite_emerald_block"))
                .addOptional(new ResourceLocation("elementus", "anthektite_diamond_block"));

        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.STEEL_BLOCK.get());


        //Block Tags
        ////Logs
        this.tag(BlockTags.LOGS)
                .add(ModBlocks.MOVCADIA_LOG.get()).add(ModBlocks.STRIPPED_MOVCADIA_LOG.get())
                .add(ModBlocks.MOVCADIA_WOOD.get()).add(ModBlocks.STRIPPED_MOVCADIA_WOOD.get());

        this.tag(BlockTags.OVERWORLD_NATURAL_LOGS)
                .add(ModBlocks.MOVCADIA_LOG.get());

        this.tag(Etags.Block.MOVCADIA_LOGS)
                .add(ModBlocks.MOVCADIA_LOG.get()).add(ModBlocks.STRIPPED_MOVCADIA_LOG.get())
                .add(ModBlocks.MOVCADIA_WOOD.get()).add(ModBlocks.STRIPPED_MOVCADIA_WOOD.get());


        ////Planks
        this.tag(BlockTags.PLANKS)
                .add(ModBlocks.MOVCADIA_PLANKS.get());


        ////Block Variant Tags
        this.tag(BlockTags.STAIRS)
                .add(ModBlocks.MOVCADIA_STAIRS.get());
        this.tag(BlockTags.SLABS)
                .add(ModBlocks.MOVCADIA_SLAB.get());

        this.tag(BlockTags.WOODEN_STAIRS)
                .add(ModBlocks.MOVCADIA_STAIRS.get());
        this.tag(BlockTags.WOODEN_SLABS)
                .add(ModBlocks.MOVCADIA_SLAB.get());


        ////Fences
        this.tag(BlockTags.FENCES)
                .add(ModBlocks.MOVCADIA_FENCE.get());
        this.tag(BlockTags.FENCE_GATES)
                .add(ModBlocks.MOVCADIA_FENCE_GATE.get());

        this.tag(BlockTags.WOODEN_FENCES)
                .add(ModBlocks.MOVCADIA_FENCE.get());
        this.tag(BlockTags.UNSTABLE_BOTTOM_CENTER)
                .add(ModBlocks.MOVCADIA_FENCE_GATE.get());


        ////Doors
        this.tag(BlockTags.DOORS)
                .add(ModBlocks.MOVCADIA_DOOR.get());
        this.tag(BlockTags.TRAPDOORS)
                .add(ModBlocks.MOVCADIA_TRAPDOOR.get());

        this.tag(BlockTags.WOODEN_DOORS)
                .add(ModBlocks.MOVCADIA_DOOR.get());
        this.tag(BlockTags.WOODEN_TRAPDOORS)
                .add(ModBlocks.MOVCADIA_TRAPDOOR.get());


        ////Redstone Blocks
        this.tag(BlockTags.PRESSURE_PLATES)
                .add(ModBlocks.MOVCADIA_PRESSURE_PLATE.get());
        this.tag(BlockTags.BUTTONS)
                .add(ModBlocks.MOVCADIA_BUTTON.get());

        this.tag(BlockTags.WOODEN_PRESSURE_PLATES)
                .add(ModBlocks.MOVCADIA_PRESSURE_PLATE.get());
        this.tag(BlockTags.WOODEN_BUTTONS)
                .add(ModBlocks.MOVCADIA_BUTTON.get());

        this.tag(BlockTags.WALL_POST_OVERRIDE)
                .add(ModBlocks.MOVCADIA_PRESSURE_PLATE.get());


        ////Signs
        this.tag(BlockTags.STANDING_SIGNS)
                .add(ModBlocks.MOVCADIA_SIGN.get()).add(ModBlocks.STURDY_MOVCADIA_SIGN.get());
        this.tag(BlockTags.WALL_SIGNS)
                .add(ModBlocks.MOVCADIA_WALL_SIGN.get()).add(ModBlocks.STURDY_MOVCADIA_WALL_SIGN.get());

        this.tag(BlockTags.CEILING_HANGING_SIGNS)
                .add(ModBlocks.MOVCADIA_HANGING_SIGN.get());
        this.tag(BlockTags.WALL_HANGING_SIGNS)
                .add(ModBlocks.MOVCADIA_WALL_HANGING_SIGN.get());


        //Misc Tags
        this.tag(BlockTags.PARROTS_SPAWNABLE_ON)
                .add(ModBlocks.MOVCADIA_LOG.get()).add(ModBlocks.STRIPPED_MOVCADIA_LOG.get())
                .add(ModBlocks.MOVCADIA_WOOD.get()).add(ModBlocks.STRIPPED_MOVCADIA_WOOD.get());

        this.tag(BlockTags.BEACON_BASE_BLOCKS)
                .add(ModBlocks.STEEL_BLOCK.get()).add(ModBlocks.ANTHEKTITE_BLOCK.get()).add(ModBlocks.DIARKRITE_BLOCK.get())
                .addOptional(new ResourceLocation("elementus", "diarkrite_iron_block"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_gold_block"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_emerald_block"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_diamond_block"))

                .addOptional(new ResourceLocation("elementus", "anthektite_iron_block"))
                .addOptional(new ResourceLocation("elementus", "anthektite_gold_block"))
                .addOptional(new ResourceLocation("elementus", "anthektite_emerald_block"))
                .addOptional(new ResourceLocation("elementus", "anthektite_diamond_block"));

        tag(BlockTags.MOSS_REPLACEABLE)
                .add(ModBlocks.MOVCADIA_ROOTS.get());
        tag(BlockTags.LUSH_GROUND_REPLACEABLE)
                .add(ModBlocks.MOVCADIA_ROOTS.get());
        tag(BlockTags.SCULK_REPLACEABLE)
                .add(ModBlocks.MOVCADIA_ROOTS.get());


        //Entity Interaction Tags
        this.tag(BlockTags.SNAPS_GOAT_HORN)
                .add(
                        ModBlocks.MOVCADIA_LOG.get());
        this.tag(BlockTags.GUARDED_BY_PIGLINS).add(
                ModBlocks.MOVCADIA_CHEST.get());


        //World Tags
        this.tag(BlockTags.LAVA_POOL_STONE_CANNOT_REPLACE)
                .add(ModBlocks.MOVCADIA_LOG.get()).add(ModBlocks.STRIPPED_MOVCADIA_LOG.get())
                .add(ModBlocks.MOVCADIA_WOOD.get()).add(ModBlocks.STRIPPED_MOVCADIA_WOOD.get())
                .add(ModBlocks.MOVCADIA_CHEST.get());
        this.tag(BlockTags.FEATURES_CANNOT_REPLACE)
                .add(ModBlocks.MOVCADIA_CHEST.get());


        //Game Interaction Tags
        this.tag(BlockTags.COMPLETES_FIND_TREE_TUTORIAL)
                .add(ModBlocks.MOVCADIA_LOG.get()).add(ModBlocks.STRIPPED_MOVCADIA_LOG.get())
                .add(ModBlocks.MOVCADIA_WOOD.get()).add(ModBlocks.STRIPPED_MOVCADIA_WOOD.get());
    }

    private void forgeTags() {
        //Storage Blocks
        tag(Tags.Blocks.STORAGE_BLOCKS)
                .addTags(Etags.Block.STEEL_STORAGE_BLOCK, Etags.Block.DIARKRITE_STORAGE_BLOCK, Etags.Block.ANTHEKTITE_STORAGE_BLOCK);
        tag(Etags.Block.STEEL_STORAGE_BLOCK).add(ModBlocks.STEEL_BLOCK.get());
        tag(Etags.Block.DIARKRITE_STORAGE_BLOCK).add(ModBlocks.DIARKRITE_BLOCK.get());
        tag(Etags.Block.ANTHEKTITE_STORAGE_BLOCK).add(ModBlocks.ANTHEKTITE_BLOCK.get());

        //Fences
        tag(Tags.Blocks.FENCE_GATES_WOODEN)
                .add(ModBlocks.MOVCADIA_FENCE.get());

        //Chests
        tag(Tags.Blocks.CHESTS)
                .add(ModBlocks.MOVCADIA_CHEST.get());

        tag(Tags.Blocks.CHESTS_WOODEN)
                .add(ModBlocks.MOVCADIA_CHEST.get());
    }

    private void elementusTags() {
        //Block Tags
        ////Logs
        this.tag(Etags.Block.MOVCADIA_LOGS)
                .add(ModBlocks.MOVCADIA_LOG.get()).add(ModBlocks.STRIPPED_MOVCADIA_LOG.get())
                .add(ModBlocks.MOVCADIA_WOOD.get()).add(ModBlocks.STRIPPED_MOVCADIA_WOOD.get());
    }

    private void modCompatibilityTags() {
        //Mod Compatibility Tags
        ////Aether Tags
        this.tag(AetherTags.Blocks.GRAVITITE_ABILITY_BLACKLIST)
                .add(
                        ModBlocks.MOVCADIA_FENCE_GATE.get(),
                        ModBlocks.MOVCADIA_TRAPDOOR.get(),
                        ModBlocks.MOVCADIA_PRESSURE_PLATE.get(),
                        ModBlocks.MOVCADIA_BUTTON.get());
        this.tag(AetherTags.Blocks.NON_BRONZE_DUNGEON_REPLACEABLE)
                .add(
                        ModBlocks.MOVCADIA_CHEST.get());

        ////ISS Tags
        this.tag(ModTags.GUARDED_BY_WIZARDS)
                .add(
                        ModBlocks.MOVCADIA_CHEST.get());

        ////Twigs Tags
        this.tag(TwigsTags.TABLES_BLOCK)
                .add(
                        TWModBlocks.MOVCADIA_TABLE.get());
    }
}
