package net.nokunami.elementus.common.datagen.providers;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, String modid, ExistingFileHelper exFileHelper) {
        super(output, modid, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
    }

    public String name(Block block) {
        ResourceLocation location = ForgeRegistries.BLOCKS.getKey(block);
        if (location != null) {
            return location.getPath();
        } else {
            throw new IllegalStateException("Unknown block: " + block.toString());
        }
    }

    public ResourceLocation texture(String name) {
        return this.modLoc("block/" + name);
    }

    public ResourceLocation texture(String name, String location) {
        return this.modLoc("block/" + location + name);
    }

    public ResourceLocation texture(String name, String location, String suffix) {
        return this.modLoc("block/" + location + name + suffix);
    }

    public ResourceLocation extend(ResourceLocation location, String suffix) {
        return new ResourceLocation(location.getNamespace(), location.getPath() + suffix);
    }

    public void block(Supplier<? extends Block> block, String location) {
        this.simpleBlock(block.get(), this.cubeAll(block.get(), location));
    }

    public void columnBlock(Supplier<? extends Block> block, Supplier<? extends Block> endBlock, String location) {
        ModelFile bookshelf = this.models().cubeColumn(this.name(block.get()), this.texture(this.name(block.get()) + "_side", location), this.texture(this.name(endBlock.get()) + "_end", location));
        this.getVariantBuilder(block.get()).partialState().addModels(new ConfiguredModel(bookshelf));
    }

    public ModelFile cubeAll(Block block, String location) {
        return this.models().cubeAll(this.name(block), this.texture(this.name(block), location));
    }
}
