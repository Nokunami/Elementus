package net.nokunami.elementus.datagen.generators;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.nokunami.elementus.datagen.providers.ModBlockStateProvider;
import net.nokunami.elementus.common.registry.ModBlocks.*;

import static net.nokunami.elementus.ModChecker.*;

public class ModBlockStateData extends ModBlockStateProvider {
    public ModBlockStateData(PackOutput output, String modid, ExistingFileHelper exFileHelper) {
        super(output, modid, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        block(ElementusBlocks.STEEL_BLOCK, "building/");
        block(ElementusBlocks.DIARKRITE_BLOCK, "building/");
        block(ElementusBlocks.ANTHEKTITE_BLOCK, "building/");
        columnBlock(ElementusBlocks.REMNANT, ElementusBlocks.REMNANT, "misc/");

        block(ElementusBlocks.STEEL_TILES, "building/");
        stairs(ElementusBlocks.STEEL_TILE_STAIR, ElementusBlocks.STEEL_TILES, "building/");
        slab(ElementusBlocks.STEEL_TILE_SLAB, ElementusBlocks.STEEL_TILES, "building/");

        log(ElementusBlocks.MOVCADIA_LOG, "natural/");
        log(ElementusBlocks.STRIPPED_MOVCADIA_LOG, "building/");
        wood(ElementusBlocks.MOVCADIA_WOOD, ElementusBlocks.MOVCADIA_LOG, "natural/");
        wood(ElementusBlocks.STRIPPED_MOVCADIA_WOOD, ElementusBlocks.STRIPPED_MOVCADIA_LOG, "building/");

        block(ElementusBlocks.MOVCADIA_ROOTED_DIRT, "natural/");
        block(ElementusBlocks.MOVCADIA_ROOTED_STONE, "natural/");
        block(ElementusBlocks.MOVCADIA_ROOTED_DEEPSLATE, "natural/");

        transparentBlock(ElementusBlocks.MOVCADIA_LEAVES, "natural/");
        transparentBlock(ElementusBlocks.FLOWERING_MOVCADIA_LEAVES, "natural/");

        block(ElementusBlocks.MOVCADIA_PLANKS, "building/");
        stairs(ElementusBlocks.MOVCADIA_STAIRS, ElementusBlocks.MOVCADIA_PLANKS, "building/");
        slab(ElementusBlocks.MOVCADIA_SLAB, ElementusBlocks.MOVCADIA_PLANKS, "building/");

        fence(ElementusBlocks.MOVCADIA_FENCE, ElementusBlocks.MOVCADIA_PLANKS, "building/");
        fenceGateBlock(ElementusBlocks.MOVCADIA_FENCE_GATE, ElementusBlocks.MOVCADIA_PLANKS, "building/");

        doorBlock(ElementusBlocks.MOVCADIA_DOOR,
                this.texture(this.name(ElementusBlocks.MOVCADIA_DOOR.get()), "building/", "_bottom"),
                this.texture(this.name(ElementusBlocks.MOVCADIA_DOOR.get()), "building/", "_top"));
        trapdoorBlock(ElementusBlocks.MOVCADIA_TRAPDOOR, this.texture(this.name(ElementusBlocks.MOVCADIA_TRAPDOOR.get()), "building/"), true);

        pressurePlateBlock(ElementusBlocks.MOVCADIA_PRESSURE_PLATE,  this.texture(this.name(ElementusBlocks.MOVCADIA_PLANKS.get()), "building/"));
        buttonBlock(ElementusBlocks.MOVCADIA_BUTTON, this.texture(this.name(ElementusBlocks.MOVCADIA_PLANKS.get()), "building/"));

        signBlock((StandingSignBlock) ElementusBlocks.MOVCADIA_SIGN.get(), (WallSignBlock) ElementusBlocks.MOVCADIA_WALL_SIGN.get(), this.texture(this.name(ElementusBlocks.MOVCADIA_PLANKS.get()), "building/"));
        hangingSignBlock((CeilingHangingSignBlock) ElementusBlocks.MOVCADIA_HANGING_SIGN.get(), (WallHangingSignBlock) ElementusBlocks.MOVCADIA_WALL_HANGING_SIGN.get(), this.texture(this.name(ElementusBlocks.STRIPPED_MOVCADIA_LOG.get()), "building/"));
        signBlock((StandingSignBlock) ElementusBlocks.STURDY_MOVCADIA_SIGN.get(), (WallSignBlock) ElementusBlocks.STURDY_MOVCADIA_WALL_SIGN.get(), this.texture(this.name(ElementusBlocks.MOVCADIA_LOG.get()), "natural/"));

        saplingBlock(ElementusBlocks.MOVCADIA_SAPLING, "natural/");

        if (farmersDelight) {
            cabinetBlock(FarmersDelightBlocks.MOVCADIA_CABINET.get(), "compat/farmersdelight/movcadia");
        }
        if (advancedNetherite) {
            block(AdvancedNetheriteBlocks.DIARKRITE_IRON_BLOCK, "compat/advancednetherite/");
            block(AdvancedNetheriteBlocks.DIARKRITE_GOLD_BLOCK, "compat/advancednetherite/");
            block(AdvancedNetheriteBlocks.DIARKRITE_EMERALD_BLOCK, "compat/advancednetherite/");
            block(AdvancedNetheriteBlocks.DIARKRITE_DIAMOND_BLOCK, "compat/advancednetherite/");

            block(AdvancedNetheriteBlocks.ANTHEKTITE_IRON_BLOCK, "compat/advancednetherite/");
            block(AdvancedNetheriteBlocks.ANTHEKTITE_GOLD_BLOCK, "compat/advancednetherite/");
            block(AdvancedNetheriteBlocks.ANTHEKTITE_EMERALD_BLOCK, "compat/advancednetherite/");
            block(AdvancedNetheriteBlocks.ANTHEKTITE_DIAMOND_BLOCK, "compat/advancednetherite/");
        }

    }
}
