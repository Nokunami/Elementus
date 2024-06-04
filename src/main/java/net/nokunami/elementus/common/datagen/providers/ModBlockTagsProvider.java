package net.nokunami.elementus.common.datagen.providers;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.nokunami.elementus.Elementus;
import net.nokunami.elementus.common.registry.ModBlocks;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> compFeaHoldP, @Nullable ExistingFileHelper existHelper) {
        super(packOutput, compFeaHoldP, Elementus.MODID, existHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
//        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
//                .add(ModBlocks.STEEL_BLOCK.get(),
//                     ModBlocks.ANTHEKTITE_BLOCK.get(),
//                     ModBlocks.DIARKRITE_BLOCK.get(),
//                     ModBlocks.REMNANT.get());
//
//        this.tag(BlockTags.BEACON_BASE_BLOCKS)
//                .add(ModBlocks.STEEL_BLOCK.get(),
//                     ModBlocks.ANTHEKTITE_BLOCK.get(),
//                     ModBlocks.DIARKRITE_BLOCK.get());
    }
}
