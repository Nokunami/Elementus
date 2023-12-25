package net.nokunami.elementus.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.nokunami.elementus.Elementus;
import net.nokunami.elementus.registry.BlocksRegistry;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class GenBlockTag extends BlockTagsProvider {
    public GenBlockTag(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> compFeaHoldP, @Nullable ExistingFileHelper existHelper) {
        super(packOutput, compFeaHoldP, Elementus.MODID, existHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(BlocksRegistry.STEEL_BLOCK.get(),
                     BlocksRegistry.ANTHEKTITE_BLOCK.get(),
                     BlocksRegistry.DIARKRITE_BLOCK.get(),
                     BlocksRegistry.REMNANT.get());

        this.tag(BlockTags.BEACON_BASE_BLOCKS)
                .add(BlocksRegistry.STEEL_BLOCK.get(),
                     BlocksRegistry.ANTHEKTITE_BLOCK.get(),
                     BlocksRegistry.DIARKRITE_BLOCK.get());
    }
}
