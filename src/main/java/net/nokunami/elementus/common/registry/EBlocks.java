package net.nokunami.elementus.common.registry;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nokunami.elementus.Elementus;
import net.nokunami.elementus.common.block.*;
import net.nokunami.elementus.common.block.entity.ModRotatedPillarBlock;

public class EBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Elementus.MODID);


    public static final RegistryObject<Block> REMNANT = BLOCKS.register("remnant",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.ANCIENT_DEBRIS)));
    public static final RegistryObject<Block> DIARKRITE_BLOCK = BLOCKS.register("diarkrite_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK)));
    public static final RegistryObject<Block> ANTHEKTITE_BLOCK = BLOCKS.register("anthektite_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK)));
    public static final RegistryObject<Block> STEEL_BLOCK = BLOCKS.register("steel_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.METAL)
                    .instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                    .requiresCorrectToolForDrops()
                    .strength(5.0F, 6.0F)
                    .sound(SoundType.METAL)));

    public static final RegistryObject<Block> STEEL_BARS = BLOCKS.register("steel_bars",
            () -> new IronBarsBlock(BlockBehaviour.Properties.copy(STEEL_BLOCK.get()).noCollission()));
    public static final RegistryObject<Block> STEEL_TILES = BLOCKS.register("steel_tiles",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> STEEL_TILE_STAIR = BLOCKS.register("steel_tile_stair",
            () -> new StairBlock(STEEL_TILES.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> STEEL_TILE_SLAB = BLOCKS.register("steel_tile_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));

    public static final RegistryObject<Block> ASTALITE_BARS = BLOCKS.register("astalite_bars",
            () -> new IronBarsBlock(BlockBehaviour.Properties.copy(STEEL_BLOCK.get()).noCollission()));
    public static final RegistryObject<Block> ASTALITE_TILES = BLOCKS.register("astalite_tiles",
            () -> new Block(BlockBehaviour.Properties.copy(EBlocks.STEEL_BLOCK.get())));
    public static final RegistryObject<Block> ASTALITE_TILE_STAIR = BLOCKS.register("astalite_tile_stair",
            () -> new StairBlock(ASTALITE_TILES.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> ASTALITE_TILE_SLAB = BLOCKS.register("astalite_tile_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> ASTALITE_DOOR = BLOCKS.register("astalite_door",
            () -> new DoorBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.METAL)
                    .requiresCorrectToolForDrops()
                    .strength(5.0F)
                    .noOcclusion()
                    .pushReaction(PushReaction.DESTROY), ModBlockSetType.ASTALITE_BLOC_SET));
    public static final RegistryObject<Block> ASTALITE_TRAPDOOR = BLOCKS.register("astalite_trapdoor",
            () -> new DoorBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.METAL)
                    .requiresCorrectToolForDrops()
                    .strength(5.0F)
                    .noOcclusion()
                    .pushReaction(PushReaction.DESTROY), ModBlockSetType.ASTALITE_BLOC_SET));
    public static final RegistryObject<Block> ASTALITE_LANTERN = BLOCKS.register("astalite_lantern",
            () -> new LanternBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.METAL)
                    .forceSolidOn()
                    .requiresCorrectToolForDrops()
                    .strength(3.5F)
                    .sound(SoundType.LANTERN)
                    .lightLevel((state) ->  15)
                    .noOcclusion()
                    .pushReaction(PushReaction.DESTROY)));

    public static final RegistryObject<Block> MOVCADIA_ROOTED_DIRT = BLOCKS.register("movcadia_rooted_dirt",
            () -> new RootedDirtBlock(BlockBehaviour.Properties.copy(Blocks.ROOTED_DIRT)));
    public static final RegistryObject<Block> MOVCADIA_ROOTED_STONE = BLOCKS.register("movcadia_rooted_stone",
            () -> new RootedDirtBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> MOVCADIA_ROOTED_DEEPSLATE = BLOCKS.register("movcadia_rooted_deepslate",
            () -> new RootedDirtBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)));

    public static final RegistryObject<RotatedPillarBlock> MOVCADIA_LOG = BLOCKS.register("movcadia_log",
            () -> log(MapColor.TERRACOTTA_PURPLE, MapColor.TERRACOTTA_BLUE, 12F, 32F, SoundType.CHERRY_WOOD));
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_MOVCADIA_LOG = BLOCKS.register("stripped_movcadia_log",
            () -> log(MapColor.TERRACOTTA_PURPLE, MapColor.TERRACOTTA_PURPLE, 8F, 16F, SoundType.CHERRY_WOOD));
    public static final RegistryObject<RotatedPillarBlock> MOVCADIA_WOOD = BLOCKS.register("movcadia_wood",
            () -> log(MapColor.TERRACOTTA_BLUE, MapColor.TERRACOTTA_BLUE, 12F, 32F, SoundType.CHERRY_WOOD));
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_MOVCADIA_WOOD = BLOCKS.register("stripped_movcadia_wood",
            () -> log(MapColor.TERRACOTTA_PURPLE, MapColor.TERRACOTTA_PURPLE, 8F, 16F, SoundType.CHERRY_WOOD));

    public static final RegistryObject<Block> MOVCADIA_LEAVES = BLOCKS.register("movcadia_leaves",
            () -> leaves(MapColor.TERRACOTTA_LIGHT_BLUE, 2F, 1F, SoundType.AZALEA_LEAVES, 0));
    public static final RegistryObject<Block> FLOWERING_MOVCADIA_LEAVES = BLOCKS.register("flowering_movcadia_leaves",
            () -> leaves(MapColor.TERRACOTTA_LIGHT_BLUE, 2F, 1F, SoundType.AZALEA_LEAVES, 5));

    public static final RegistryObject<Block> MOVCADIA_PLANKS = BLOCKS.register("movcadia_planks",
            () -> new Block(BlockBehaviour
                    .Properties.of().mapColor(MapColor.TERRACOTTA_PURPLE)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(8F, 16F)
                    .sound(SoundType.CHERRY_WOOD)));
    public static final RegistryObject<StairBlock> MOVCADIA_STAIRS = BLOCKS.register("movcadia_stairs",
            () -> new StairBlock(MOVCADIA_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(MOVCADIA_PLANKS.get())));
    public static final RegistryObject<SlabBlock> MOVCADIA_SLAB = BLOCKS.register("movcadia_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(MOVCADIA_PLANKS.get())));

    public static final RegistryObject<Block> STURDY_MOVCADIA_SIGN = BLOCKS.register("sturdy_movcadia_sign",
            () -> new ModStandingSignBlock(BlockBehaviour.Properties.copy(MOVCADIA_PLANKS.get()).forceSolidOn().noOcclusion(), ModBlockSetType.STURDY_MOVCADIA_WOOD_TYPE));
    public static final RegistryObject<Block> STURDY_MOVCADIA_WALL_SIGN = BLOCKS.register("sturdy_movcadia_wall_sign",
            () -> new ModWallSignBlock(BlockBehaviour.Properties.copy(MOVCADIA_PLANKS.get()).forceSolidOn().noOcclusion().lootFrom(STURDY_MOVCADIA_SIGN), ModBlockSetType.STURDY_MOVCADIA_WOOD_TYPE));

    public static final RegistryObject<Block> MOVCADIA_HANGING_SIGN = BLOCKS.register("movcadia_hanging_sign",
            () -> new ModHangingSignBlock(BlockBehaviour.Properties.copy(MOVCADIA_PLANKS.get()).forceSolidOn().noCollission(), ModBlockSetType.MOVCADIA_WOOD_TYPE));
    public static final RegistryObject<Block> MOVCADIA_WALL_HANGING_SIGN = BLOCKS.register("movcadia_wall_hanging_sign",
            () -> new ModWallHangingSignBlock(BlockBehaviour.Properties.copy(MOVCADIA_PLANKS.get()).forceSolidOn().noCollission().lootFrom(MOVCADIA_HANGING_SIGN), ModBlockSetType.MOVCADIA_WOOD_TYPE));

    public static final RegistryObject<Block> MOVCADIA_SIGN = BLOCKS.register("movcadia_sign",
            () -> new ModStandingSignBlock(BlockBehaviour.Properties.copy(MOVCADIA_PLANKS.get()).forceSolidOn().noCollission(), ModBlockSetType.MOVCADIA_WOOD_TYPE));
    public static final RegistryObject<Block> MOVCADIA_WALL_SIGN = BLOCKS.register("movcadia_wall_sign",
            () -> new ModWallSignBlock(BlockBehaviour.Properties.copy(MOVCADIA_PLANKS.get()).forceSolidOn().noCollission().lootFrom(MOVCADIA_SIGN), ModBlockSetType.MOVCADIA_WOOD_TYPE));

    public static final RegistryObject<ButtonBlock> MOVCADIA_BUTTON = BLOCKS.register("movcadia_button",
            () -> new ButtonBlock(BlockBehaviour.Properties.copy(MOVCADIA_PLANKS.get())
                    .noCollission().strength(4F).pushReaction(PushReaction.DESTROY), ModBlockSetType.MOVCADIA_BLOCK_SET, 40, false));
    public static final RegistryObject<PressurePlateBlock> MOVCADIA_PRESSURE_PLATE = BLOCKS.register("movcadia_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
                    BlockBehaviour.Properties.copy(MOVCADIA_PLANKS.get()).forceSolidOn().instrument(NoteBlockInstrument.BASS)
                            .noCollission().strength(4F).pushReaction(PushReaction.DESTROY), ModBlockSetType.MOVCADIA_BLOCK_SET));

    public static final RegistryObject<TrapDoorBlock> MOVCADIA_TRAPDOOR = BLOCKS.register("movcadia_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(MOVCADIA_PLANKS.get()).noOcclusion().pushReaction(PushReaction.NORMAL),
                    ModBlockSetType.MOVCADIA_BLOCK_SET));
    public static final RegistryObject<DoorBlock> MOVCADIA_DOOR = BLOCKS.register("movcadia_door",
            () -> new DoorBlock(BlockBehaviour.Properties.copy(MOVCADIA_PLANKS.get()).noOcclusion().pushReaction(PushReaction.NORMAL),
                    ModBlockSetType.MOVCADIA_BLOCK_SET));

    public static final RegistryObject<FenceGateBlock> MOVCADIA_FENCE_GATE = BLOCKS.register("movcadia_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.copy(MOVCADIA_PLANKS.get()).forceSolidOn(), ModBlockSetType.MOVCADIA_WOOD_TYPE));
    public static final RegistryObject<FenceBlock> MOVCADIA_FENCE = BLOCKS.register("movcadia_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(MOVCADIA_PLANKS.get())));

    public static final RegistryObject<Block> MOVCADIA_CHEST = BLOCKS.register("movcadia_chest",
            () -> new ModChestBlock(BlockBehaviour.Properties.copy(MOVCADIA_PLANKS.get()), ModChests.MOVCADIA));

    public static final RegistryObject<Block> MOVCADIA_SAPLING = BLOCKS.register("movcadia_sapling",
            () -> new MovcadiaSapling(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.TERRACOTTA_PURPLE).instrument(NoteBlockInstrument.BASS)
                    .strength(2F, 4F).sound(SoundType.BAMBOO_SAPLING)
                    .noCollission().randomTicks().instabreak().pushReaction(PushReaction.DESTROY)));

    private static RotatedPillarBlock log(MapColor pTopMapColor, MapColor pSideMapColor, float destroyTime, float blastResistance, SoundType pSoundType) {
        return new ModRotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(
                        (mapColor) -> mapColor.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? pTopMapColor : pSideMapColor)
                .instrument(NoteBlockInstrument.BASS).strength(destroyTime, blastResistance).sound(pSoundType));
    }

    private static Boolean ocelotOrParrot(BlockState p_50822_, BlockGetter p_50823_, BlockPos p_50824_, EntityType<?> p_50825_) {
        return p_50825_ == EntityType.OCELOT || p_50825_ == EntityType.PARROT;
    }

    private static boolean never(BlockState p_50806_, BlockGetter p_50807_, BlockPos p_50808_) {
        return false;
    }

    private static LeavesBlock leaves(MapColor mapColor, float destroyTime, float explosionRes, SoundType pType, int lightLevel) {
        return new LeavesBlock(BlockBehaviour.Properties.of().mapColor(mapColor)
                .strength(destroyTime, explosionRes).randomTicks().sound(pType)
                .noOcclusion().isValidSpawn(EBlocks::ocelotOrParrot)
                .isSuffocating(EBlocks::never).isViewBlocking(EBlocks::never)
                .ignitedByLava().pushReaction(PushReaction.DESTROY).isRedstoneConductor(EBlocks::never)
                .lightLevel((takeThisL) -> lightLevel));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
