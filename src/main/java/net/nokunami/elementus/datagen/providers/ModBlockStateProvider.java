package net.nokunami.elementus.datagen.providers;

import com.mrcrayfish.furniture.refurbished.block.ChairBlock;
import com.mrcrayfish.furniture.refurbished.block.TableBlock;
import com.mrcrayfish.furniture.refurbished.data.model.ModelTemplate;
import com.mrcrayfish.furniture.refurbished.data.model.PreparedVariantBlockState;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import vectorwing.farmersdelight.common.block.CabinetBlock;

import java.util.function.Consumer;
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

    public void transparentBlock(Supplier<? extends Block> block, String location) {
        this.simpleBlock(block.get(), this.cubeAllCutOut(block.get(), location));
    }

    public void columnBlock(Supplier<? extends Block> block, Supplier<? extends Block> endBlock, String location) {
        ModelFile bookshelf = this.models().cubeColumn(this.name(block.get()), this.texture(this.name(block.get()) + "_side", location), this.texture(this.name(endBlock.get()) + "_end", location));
        this.getVariantBuilder(block.get()).partialState().addModels(new ConfiguredModel(bookshelf));
    }

    public ModelFile cubeAll(Block block, String location) {
        return this.models().cubeAll(this.name(block), this.texture(this.name(block), location));
    }

    public ModelFile cubeAllCutOut(Block block, String location) {
        return this.models().cubeAll(this.name(block), this.texture(this.name(block), location)).renderType("cutout");
    }

    public void log(Supplier<? extends RotatedPillarBlock> block, String location) {
        this.axisBlock(block.get(), this.texture(this.name(block.get()), location), this.extend(this.texture(this.name(block.get()), location), "_top"));
    }

    public void wood(Supplier<? extends RotatedPillarBlock> block, Supplier<? extends RotatedPillarBlock> baseBlock, String location) {
        this.axisBlock(block.get(), this.texture(this.name(baseBlock.get()), location), this.texture(this.name(baseBlock.get()), location));
    }

    public void stairs(Supplier<? extends StairBlock> block, Supplier<? extends Block> baseBlock, String location) {
        this.stairsBlock(block.get(), this.texture(this.name(baseBlock.get()), location));
    }

    public void slab(Supplier<? extends SlabBlock> block, Supplier<? extends Block> baseBlock, String location) {
        this.slabBlock(block.get(), this.texture(this.name(baseBlock.get())), this.texture(this.name(baseBlock.get()), location));
    }

    public void fence(Supplier<? extends FenceBlock> block, Supplier<? extends Block> baseBlock, String location) {
        this.fenceBlock(block.get(), this.texture(this.name(baseBlock.get()), location));
        this.fenceColumn(block.get(), this.name(baseBlock.get()), location);
    }

    public void fenceColumn(CrossCollisionBlock block, String side, String location) {
        String baseName = this.name(block);
        this.fourWayBlock(block,
                this.models().fencePost(baseName + "_post", this.texture(side, location)),
                this.models().fenceSide(baseName + "_side", this.texture(side, location)));
    }

    public void fenceGateBlock(Supplier<? extends FenceGateBlock> block, Supplier<? extends Block> baseBlock, String location) {
        this.fenceGateBlockInternal(block.get(), this.name(block.get()), this.texture(this.name(baseBlock.get()), location));
    }

    public void fenceGateBlockInternal(FenceGateBlock block, String baseName, ResourceLocation texture) {
        ModelFile gate = this.models().fenceGate(baseName, texture);
        ModelFile gateOpen = this.models().fenceGateOpen(baseName + "_open", texture);
        ModelFile gateWall = this.models().fenceGateWall(baseName + "_wall", texture);
        ModelFile gateWallOpen = this.models().fenceGateWallOpen(baseName + "_wall_open", texture);
        this.fenceGateBlock(block, gate, gateOpen, gateWall, gateWallOpen);
    }

    public void doorBlock(Supplier<? extends DoorBlock> block, ResourceLocation bottom, ResourceLocation top) {
        this.doorBlockWithRenderType(block.get(), bottom, top, "cutout");
    }

    public void trapdoorBlock(Supplier<? extends TrapDoorBlock> block, ResourceLocation texture, boolean orientable) {
        this.trapdoorBlockWithRenderType(block.get(), texture, orientable, "cutout");
    }

    public void pressurePlateBlock(Supplier<? extends PressurePlateBlock> block, ResourceLocation texture) {
        ModelFile pressurePlate = this.models().pressurePlate(this.name(block.get()), texture);
        ModelFile pressurePlateDown = this.models().pressurePlateDown(this.name(block.get()) + "_down", texture);
        this.pressurePlateBlock(block.get(), pressurePlate, pressurePlateDown);
    }

    public void buttonBlock(Supplier<? extends ButtonBlock> block, ResourceLocation texture) {
        ModelFile button = this.models().button(this.name(block.get()), texture);
        ModelFile buttonPressed = this.models().buttonPressed(this.name(block.get()) + "_pressed", texture);
        this.buttonBlock(block.get(), button, buttonPressed);
    }

    public void signBlock(StandingSignBlock signBlock, WallSignBlock wallSignBlock, ResourceLocation texture) {
        ModelFile sign = this.models().sign(this.name(signBlock), texture);
        this.signBlock(signBlock, wallSignBlock, sign);
    }

    public void hangingSignBlock(CeilingHangingSignBlock signBlock, WallHangingSignBlock wallSignBlock, ResourceLocation texture) {
        ModelFile sign = this.models().sign(this.name(signBlock), texture);
        this.simpleBlock(signBlock, sign);
        this.simpleBlock(wallSignBlock, sign);
    }

    public void saplingBlock(Supplier<? extends Block> block, String location) {
        ModelFile sapling = models().cross(this.name(block.get()), this.texture(this.name(block.get()), location)).renderType(new ResourceLocation("cutout"));
        this.getVariantBuilder(block.get()).forAllStatesExcept(state -> ConfiguredModel.builder().modelFile(sapling).build(), SaplingBlock.STAGE);
    }

    public void chestModel(Block block, ModelFile chest) {
        this.getVariantBuilder(block).forAllStatesExcept(state -> ConfiguredModel.builder().modelFile(chest).build(),
                ChestBlock.TYPE, ChestBlock.WATERLOGGED);
    }

    public void chest(Supplier<? extends Block> block, Supplier<? extends Block> dummyBlock, String location) {
        ModelFile chest = this.models().cubeAll(this.name(block.get()), this.texture(this.name(dummyBlock.get()), location));
        this.chestModel(block.get(), chest);
    }

    public void transparentColumnBlock(Supplier<? extends Block> block, Supplier<? extends Block> endBlock, String location) {
        ModelFile bookshelf = this.models().cubeColumn(this.name(block.get()),
                this.texture(this.name(block.get()) + "_side", location),
                this.texture(this.name(endBlock.get()) + "_end", location))
                .renderType("cutout");
        this.getVariantBuilder(block.get()).partialState().addModels(new ConfiguredModel(bookshelf));
    }


    public void cabinetBlock(Block block, String woodType) {
        this.horizontalBlock(block, (state) -> {
            String suffix = (Boolean)state.getValue(CabinetBlock.OPEN) ? "_open" : "";
            return this.models().orientable(this.name(block) + suffix, this.texture(woodType + "_cabinet_side"), this.texture(woodType + "_cabinet_front" + suffix), this.texture(woodType + "_cabinet_top"));
        });
    }
}
