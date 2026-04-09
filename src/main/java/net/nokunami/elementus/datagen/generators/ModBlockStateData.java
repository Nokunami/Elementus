package net.nokunami.elementus.datagen.generators;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.nokunami.elementus.common.registry.EBlocks;
import net.nokunami.elementus.datagen.providers.ModBlockStateProvider;

public class ModBlockStateData extends ModBlockStateProvider {
    public ModBlockStateData(PackOutput output, String modid, ExistingFileHelper exFileHelper) {
        super(output, modid, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        block(EBlocks.ASTALITE_BLOCK, "building/");
        block(EBlocks.DIARKRITE_BLOCK, "building/");
        block(EBlocks.ANTHEKTITE_BLOCK, "building/");
        columnBlock(EBlocks.REMNANT, EBlocks.REMNANT, "misc/");

        block(EBlocks.STEEL_TILES, "building/");
        stairs(EBlocks.STEEL_TILE_STAIR, EBlocks.STEEL_TILES, "building/");
        slab(EBlocks.STEEL_TILE_SLAB, EBlocks.STEEL_TILES, "building/");

        log(EBlocks.MOVCADIA_LOG, "natural/");
        log(EBlocks.STRIPPED_MOVCADIA_LOG, "building/");
        wood(EBlocks.MOVCADIA_WOOD, EBlocks.MOVCADIA_LOG, "natural/");
        wood(EBlocks.STRIPPED_MOVCADIA_WOOD, EBlocks.STRIPPED_MOVCADIA_LOG, "building/");

        block(EBlocks.MOVCADIA_ROOTED_DIRT, "natural/");
        block(EBlocks.MOVCADIA_ROOTED_STONE, "natural/");
        block(EBlocks.MOVCADIA_ROOTED_DEEPSLATE, "natural/");

        transparentBlock(EBlocks.MOVCADIA_LEAVES, "natural/");
        transparentBlock(EBlocks.FLOWERING_MOVCADIA_LEAVES, "natural/");

        block(EBlocks.MOVCADIA_PLANKS, "building/");
        stairs(EBlocks.MOVCADIA_STAIRS, EBlocks.MOVCADIA_PLANKS, "building/");
        slab(EBlocks.MOVCADIA_SLAB, EBlocks.MOVCADIA_PLANKS, "building/");

        fence(EBlocks.MOVCADIA_FENCE, EBlocks.MOVCADIA_PLANKS, "building/");
        fenceGateBlock(EBlocks.MOVCADIA_FENCE_GATE, EBlocks.MOVCADIA_PLANKS, "building/");

        doorBlock(EBlocks.MOVCADIA_DOOR,
                this.texture(this.name(EBlocks.MOVCADIA_DOOR.get()), "building/", "_bottom"),
                this.texture(this.name(EBlocks.MOVCADIA_DOOR.get()), "building/", "_top"));
        trapdoorBlock(EBlocks.MOVCADIA_TRAPDOOR, this.texture(this.name(EBlocks.MOVCADIA_TRAPDOOR.get()), "building/"), true);

        pressurePlateBlock(EBlocks.MOVCADIA_PRESSURE_PLATE,  this.texture(this.name(EBlocks.MOVCADIA_PLANKS.get()), "building/"));
        buttonBlock(EBlocks.MOVCADIA_BUTTON, this.texture(this.name(EBlocks.MOVCADIA_PLANKS.get()), "building/"));

        signBlock((StandingSignBlock) EBlocks.MOVCADIA_SIGN.get(), (WallSignBlock) EBlocks.MOVCADIA_WALL_SIGN.get(), this.texture(this.name(EBlocks.MOVCADIA_PLANKS.get()), "building/"));
        hangingSignBlock((CeilingHangingSignBlock) EBlocks.MOVCADIA_HANGING_SIGN.get(), (WallHangingSignBlock) EBlocks.MOVCADIA_WALL_HANGING_SIGN.get(), this.texture(this.name(EBlocks.STRIPPED_MOVCADIA_LOG.get()), "building/"));
        signBlock((StandingSignBlock) EBlocks.STURDY_MOVCADIA_SIGN.get(), (WallSignBlock) EBlocks.STURDY_MOVCADIA_WALL_SIGN.get(), this.texture(this.name(EBlocks.MOVCADIA_LOG.get()), "natural/"));

        saplingBlock(EBlocks.MOVCADIA_SAPLING, "natural/");
    }
}
