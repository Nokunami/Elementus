package net.nokunami.elementus.datagen.generators;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.nokunami.elementus.ModChecker;
import net.nokunami.elementus.common.compat.advancednetherite.ANModBlocks;
import net.nokunami.elementus.common.compat.farmersdelight.FDModBlocks;
import net.nokunami.elementus.datagen.providers.ModBlockStateProvider;
import net.nokunami.elementus.common.registry.ModBlocks;

public class ModBlockStateData extends ModBlockStateProvider {
    public ModBlockStateData(PackOutput output, String modid, ExistingFileHelper exFileHelper) {
        super(output, modid, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        block(ModBlocks.STEEL_BLOCK, "building/");
        block(ModBlocks.DIARKRITE_BLOCK, "building/");
        block(ModBlocks.ANTHEKTITE_BLOCK, "building/");
        columnBlock(ModBlocks.REMNANT, ModBlocks.REMNANT, "misc/");


        block(ModBlocks.MOVCADIA_ROOTS, "misc/");

        log(ModBlocks.MOVCADIA_LOG, "natural/");
        log(ModBlocks.STRIPPED_MOVCADIA_LOG, "building/");
        wood(ModBlocks.MOVCADIA_WOOD, ModBlocks.MOVCADIA_LOG, "natural/");
        wood(ModBlocks.STRIPPED_MOVCADIA_WOOD, ModBlocks.STRIPPED_MOVCADIA_LOG, "building/");

        transparentBlock(ModBlocks.MOVCADIA_LEAVES, "natural/");

        block(ModBlocks.MOVCADIA_PLANKS, "building/");
        stairs(ModBlocks.MOVCADIA_STAIRS, ModBlocks.MOVCADIA_PLANKS, "building/");
        slab(ModBlocks.MOVCADIA_SLAB, ModBlocks.MOVCADIA_PLANKS, "building/");

        fence(ModBlocks.MOVCADIA_FENCE, ModBlocks.MOVCADIA_PLANKS, "building/");
        fenceGateBlock(ModBlocks.MOVCADIA_FENCE_GATE, ModBlocks.MOVCADIA_PLANKS, "building/");

        doorBlock(ModBlocks.MOVCADIA_DOOR,
                this.texture(this.name(ModBlocks.MOVCADIA_DOOR.get()), "building/", "_bottom"),
                this.texture(this.name(ModBlocks.MOVCADIA_DOOR.get()), "building/", "_top"));
        trapdoorBlock(ModBlocks.MOVCADIA_TRAPDOOR, this.texture(this.name(ModBlocks.MOVCADIA_TRAPDOOR.get()), "building/"), true);

        pressurePlateBlock(ModBlocks.MOVCADIA_PRESSURE_PLATE,  this.texture(this.name(ModBlocks.MOVCADIA_PLANKS.get()), "building/"));
        buttonBlock(ModBlocks.MOVCADIA_BUTTON, this.texture(this.name(ModBlocks.MOVCADIA_PLANKS.get()), "building/"));

        signBlock((StandingSignBlock) ModBlocks.MOVCADIA_SIGN.get(), (WallSignBlock) ModBlocks.MOVCADIA_WALL_SIGN.get(), this.texture(this.name(ModBlocks.MOVCADIA_PLANKS.get()), "building/"));
        hangingSignBlock((CeilingHangingSignBlock) ModBlocks.MOVCADIA_HANGING_SIGN.get(), (WallHangingSignBlock) ModBlocks.MOVCADIA_WALL_HANGING_SIGN.get(), this.texture(this.name(ModBlocks.STRIPPED_MOVCADIA_LOG.get()), "building/"));
        signBlock((StandingSignBlock) ModBlocks.STURDY_MOVCADIA_SIGN.get(), (WallSignBlock) ModBlocks.STURDY_MOVCADIA_WALL_SIGN.get(), this.texture(this.name(ModBlocks.MOVCADIA_LOG.get()), "natural/"));

        saplingBlock(ModBlocks.MOVCADIA_SAPLING, "natural/");

        if (ModChecker.farmersdelight()) {
            cabinetBlock(FDModBlocks.MOVCADIA_CABINET.get(), "compat/farmersdelight/movcadia");
        }
        if (ModChecker.advancednetherite()) {
            block(ANModBlocks.DIARKRITE_IRON_BLOCK, "compat/advancednetherite/");
            block(ANModBlocks.DIARKRITE_GOLD_BLOCK, "compat/advancednetherite/");
            block(ANModBlocks.DIARKRITE_EMERALD_BLOCK, "compat/advancednetherite/");
            block(ANModBlocks.DIARKRITE_DIAMOND_BLOCK, "compat/advancednetherite/");

            block(ANModBlocks.ANTHEKTITE_IRON_BLOCK, "compat/advancednetherite/");
            block(ANModBlocks.ANTHEKTITE_GOLD_BLOCK, "compat/advancednetherite/");
            block(ANModBlocks.ANTHEKTITE_EMERALD_BLOCK, "compat/advancednetherite/");
            block(ANModBlocks.ANTHEKTITE_DIAMOND_BLOCK, "compat/advancednetherite/");
        }

    }
}
