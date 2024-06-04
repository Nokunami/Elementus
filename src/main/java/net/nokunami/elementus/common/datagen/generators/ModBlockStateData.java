package net.nokunami.elementus.common.datagen.generators;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.nokunami.elementus.common.compat.advancednetherite.ANModBlocks;
import net.nokunami.elementus.common.datagen.providers.ModBlockStateProvider;
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
