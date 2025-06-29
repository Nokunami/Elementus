package nokunami.elementus.common.registry;

//import com.ninni.twigs.block.TableBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
//import net.minecraftforge.eventbus.api.IEventBus;
//import net.minecraftforge.registries.DeferredRegister;
//import net.minecraftforge.registries.ForgeRegistries;
//import net.minecraftforge.registries.RegistryObject;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import nokunami.elementus.Elementus;
import nokunami.elementus.common.block.*;
import nokunami.elementus.common.block.entity.ModRotatedPillarBlock;
//import vectorwing.farmersdelight.common.block.CabinetBlock;

import static nokunami.elementus.Elementus.MODID;
import static nokunami.elementus.ModChecker.*;

public class ModBlocks {

    public static class ElementusBlocks {
        public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Elementus.MODID);

        public static final DeferredBlock<Block> STEEL_BLOCK = BLOCKS.register("steel_block",
                () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
        public static final DeferredBlock<Block> ANTHEKTITE_BLOCK = BLOCKS.register("anthektite_block",
                () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERITE_BLOCK)));
        public static final DeferredBlock<Block> DIARKRITE_BLOCK = BLOCKS.register("diarkrite_block",
                () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERITE_BLOCK)));
        public static final DeferredBlock<Block> REMNANT = BLOCKS.register("remnant",
                () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.ANCIENT_DEBRIS)));

        public static final DeferredBlock<Block> STEEL_BARS = BLOCKS.register("steel_bars",
                () -> new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(STEEL_BLOCK.get()).noCollission()));

        public static final DeferredBlock<Block> STEEL_TILES = BLOCKS.register("steel_tiles",
                () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
        public static final DeferredBlock<Block> STEEL_TILE_STAIR = BLOCKS.register("steel_tile_stair",
                () -> new StairBlock(STEEL_TILES.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
        public static final DeferredBlock<Block> STEEL_TILE_SLAB = BLOCKS.register("steel_tile_slab",
                () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));

        public static final DeferredBlock<Block> MOVCADIA_ROOTED_DIRT = BLOCKS.register("movcadia_rooted_dirt",
                () -> new RootedDirtBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ROOTED_DIRT)));
        public static final DeferredBlock<Block> MOVCADIA_ROOTED_STONE = BLOCKS.register("movcadia_rooted_stone",
                () -> new RootedDirtBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
        public static final DeferredBlock<Block> MOVCADIA_ROOTED_DEEPSLATE = BLOCKS.register("movcadia_rooted_deepslate",
                () -> new RootedDirtBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE)));

        public static final DeferredBlock<RotatedPillarBlock> MOVCADIA_LOG = BLOCKS.register("movcadia_log",
                () -> log(MapColor.TERRACOTTA_PURPLE, MapColor.TERRACOTTA_BLUE, 12F, 32F, SoundType.CHERRY_WOOD));

        public static final DeferredBlock<RotatedPillarBlock> STRIPPED_MOVCADIA_LOG = BLOCKS.register("stripped_movcadia_log",
                () -> log(MapColor.TERRACOTTA_PURPLE, MapColor.TERRACOTTA_PURPLE, 8F, 16F, SoundType.CHERRY_WOOD));

        public static final DeferredBlock<RotatedPillarBlock> MOVCADIA_WOOD = BLOCKS.register("movcadia_wood",
                () -> log(MapColor.TERRACOTTA_BLUE, MapColor.TERRACOTTA_BLUE, 12F, 32F, SoundType.CHERRY_WOOD));

        public static final DeferredBlock<RotatedPillarBlock> STRIPPED_MOVCADIA_WOOD = BLOCKS.register("stripped_movcadia_wood",
                () -> log(MapColor.TERRACOTTA_PURPLE, MapColor.TERRACOTTA_PURPLE, 8F, 16F, SoundType.CHERRY_WOOD));

        public static final DeferredBlock<Block> MOVCADIA_PLANKS = BLOCKS.register("movcadia_planks",
                () -> new Block(BlockBehaviour
                        .Properties.of().mapColor(MapColor.TERRACOTTA_PURPLE)
                        .instrument(NoteBlockInstrument.BASS)
                        .strength(8F, 16F)
                        .sound(SoundType.CHERRY_WOOD)));

        public static final DeferredBlock<StairBlock> MOVCADIA_STAIRS = BLOCKS.register("movcadia_stairs",
                () -> new StairBlock(MOVCADIA_PLANKS.get().defaultBlockState(),
                        BlockBehaviour.Properties.ofFullCopy(MOVCADIA_PLANKS.get())));

        public static final DeferredBlock<SlabBlock> MOVCADIA_SLAB = BLOCKS.register("movcadia_slab",
                () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(MOVCADIA_PLANKS.get())));


        public static final DeferredBlock<FenceBlock> MOVCADIA_FENCE = BLOCKS.register("movcadia_fence",
                () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(MOVCADIA_PLANKS.get())));

        public static final DeferredBlock<FenceGateBlock> MOVCADIA_FENCE_GATE = BLOCKS.register("movcadia_fence_gate",
                () -> new FenceGateBlock(ModBlockSetType.MOVCADIA_WOOD_TYPE, BlockBehaviour.Properties.ofFullCopy(MOVCADIA_PLANKS.get()).forceSolidOn()));


        public static final DeferredBlock<DoorBlock> MOVCADIA_DOOR = BLOCKS.register("movcadia_door",
                () -> new DoorBlock(ModBlockSetType.MOVCADIA_BLOCK_SET, BlockBehaviour.Properties.ofFullCopy(MOVCADIA_PLANKS.get()).noOcclusion().pushReaction(PushReaction.NORMAL)));

        public static final DeferredBlock<TrapDoorBlock> MOVCADIA_TRAPDOOR = BLOCKS.register("movcadia_trapdoor",
                () -> new TrapDoorBlock(ModBlockSetType.MOVCADIA_BLOCK_SET, BlockBehaviour.Properties.ofFullCopy(MOVCADIA_PLANKS.get()).noOcclusion().pushReaction(PushReaction.NORMAL)));


        public static final DeferredBlock<PressurePlateBlock> MOVCADIA_PRESSURE_PLATE = BLOCKS.register("movcadia_pressure_plate",
                () -> new PressurePlateBlock(ModBlockSetType.MOVCADIA_BLOCK_SET,
                        BlockBehaviour.Properties.ofFullCopy(MOVCADIA_PLANKS.get()).forceSolidOn().instrument(NoteBlockInstrument.BASS)
                                .noCollission().strength(4F).pushReaction(PushReaction.DESTROY)));

        public static final DeferredBlock<ButtonBlock> MOVCADIA_BUTTON = BLOCKS.register("movcadia_button",
                () -> new ButtonBlock(BlockBehaviour.Properties.ofFullCopy(MOVCADIA_PLANKS.get())
                        .noCollission().strength(4F).pushReaction(PushReaction.DESTROY), ModBlockSetType.MOVCADIA_BLOCK_SET, 40, false));


        public static final DeferredBlock<Block> MOVCADIA_SIGN = BLOCKS.register("movcadia_sign",
                () -> new ModStandingSignBlock(BlockBehaviour.Properties.ofFullCopy(MOVCADIA_PLANKS.get()).forceSolidOn().noCollission(), ModBlockSetType.MOVCADIA_WOOD_TYPE));
        public static final DeferredBlock<Block> MOVCADIA_WALL_SIGN = BLOCKS.register("movcadia_wall_sign",
                () -> new ModWallSignBlock(BlockBehaviour.Properties.ofFullCopy(MOVCADIA_PLANKS.get()).forceSolidOn().noCollission().lootFrom(MOVCADIA_SIGN), ModBlockSetType.MOVCADIA_WOOD_TYPE));

        public static final DeferredBlock<Block> MOVCADIA_HANGING_SIGN = BLOCKS.register("movcadia_hanging_sign",
                () -> new ModHangingSignBlock(BlockBehaviour.Properties.ofFullCopy(MOVCADIA_PLANKS.get()).forceSolidOn().noCollission(), ModBlockSetType.MOVCADIA_WOOD_TYPE));
        public static final DeferredBlock<Block> MOVCADIA_WALL_HANGING_SIGN = BLOCKS.register("movcadia_wall_hanging_sign",
                () -> new ModWallHangingSignBlock(BlockBehaviour.Properties.ofFullCopy(MOVCADIA_PLANKS.get()).forceSolidOn().noCollission().lootFrom(MOVCADIA_HANGING_SIGN), ModBlockSetType.MOVCADIA_WOOD_TYPE));

        public static final DeferredBlock<Block> STURDY_MOVCADIA_SIGN = BLOCKS.register("sturdy_movcadia_sign",
                () -> new ModStandingSignBlock(BlockBehaviour.Properties.ofFullCopy(MOVCADIA_PLANKS.get()).forceSolidOn().noOcclusion(), ModBlockSetType.STURDY_MOVCADIA_WOOD_TYPE));
        public static final DeferredBlock<Block> STURDY_MOVCADIA_WALL_SIGN = BLOCKS.register("sturdy_movcadia_wall_sign",
                () -> new ModWallSignBlock(BlockBehaviour.Properties.ofFullCopy(MOVCADIA_PLANKS.get()).forceSolidOn().noOcclusion().lootFrom(STURDY_MOVCADIA_SIGN), ModBlockSetType.STURDY_MOVCADIA_WOOD_TYPE));

        public static final DeferredBlock<Block> MOVCADIA_CHEST = BLOCKS.register("movcadia_chest",
                () -> new ModChestBlock(BlockBehaviour.Properties.ofFullCopy(MOVCADIA_PLANKS.get()), ModChests.MOVCADIA));

        public static final DeferredBlock<Block> MOVCADIA_LEAVES = BLOCKS.register("movcadia_leaves",
                () -> leaves(MapColor.TERRACOTTA_LIGHT_BLUE, 2F, 1F, SoundType.AZALEA_LEAVES, 0));
        public static final DeferredBlock<Block> FLOWERING_MOVCADIA_LEAVES = BLOCKS.register("flowering_movcadia_leaves",
                () -> leaves(MapColor.TERRACOTTA_LIGHT_BLUE, 2F, 1F, SoundType.AZALEA_LEAVES, 5));

        public static final DeferredBlock<Block> MOVCADIA_SAPLING = BLOCKS.register("movcadia_sapling",
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
                    .noOcclusion().isValidSpawn(ElementusBlocks::ocelotOrParrot)
                    .isSuffocating(ElementusBlocks::never).isViewBlocking(ElementusBlocks::never)
                    .ignitedByLava().pushReaction(PushReaction.DESTROY).isRedstoneConductor(ElementusBlocks::never)
                    .lightLevel((takeThisL) -> lightLevel));
        }

        public static void register(IEventBus eventBus) {
            BLOCKS.register(eventBus);
        }
    }

//    public static class FarmersDelightBlocks {
//        public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Elementus.MODID);
//
//        public static final RegistryObject<Block> MOVCADIA_CABINET = BLOCKS.register("movcadia_cabinet",
//                () -> new CabinetBlock(BlockBehaviour.Properties.copy(ElementusBlocks.MOVCADIA_PLANKS.get())));
//
//        public static void register(IEventBus eventBus) {
//            BLOCKS.register(eventBus);
//        }
//    }
//    public static class AdvancedNetheriteBlocks {
//        public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Elementus.MODID);
//
//        public static final RegistryObject<Block> DIARKRITE_IRON_BLOCK = BLOCKS.register("diarkrite_iron_block",
//                () -> new Block(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK)));
//        public static final RegistryObject<Block> DIARKRITE_GOLD_BLOCK = BLOCKS.register("diarkrite_gold_block",
//                () -> new Block(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK)));
//        public static final RegistryObject<Block> DIARKRITE_EMERALD_BLOCK = BLOCKS.register("diarkrite_emerald_block",
//                () -> new Block(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK)));
//        public static final RegistryObject<Block> DIARKRITE_DIAMOND_BLOCK = BLOCKS.register("diarkrite_diamond_block",
//                () -> new Block(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK)));
//
//        public static final RegistryObject<Block> ANTHEKTITE_IRON_BLOCK = BLOCKS.register("anthektite_iron_block",
//                () -> new Block(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK)));
//        public static final RegistryObject<Block> ANTHEKTITE_GOLD_BLOCK = BLOCKS.register("anthektite_gold_block",
//                () -> new Block(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK)));
//        public static final RegistryObject<Block> ANTHEKTITE_EMERALD_BLOCK = BLOCKS.register("anthektite_emerald_block",
//                () -> new Block(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK)));
//        public static final RegistryObject<Block> ANTHEKTITE_DIAMOND_BLOCK = BLOCKS.register("anthektite_diamond_block",
//                () -> new Block(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK)));
//
//        public static void register(IEventBus eventBus) {
//            BLOCKS.register(eventBus);
//        }
//    }
//    public static class TwigsBlocks {
//        public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
//
//        public static final RegistryObject<Block> MOVCADIA_TABLE = BLOCKS.register("movcadia_table",
//                () -> new TableBlock(BlockBehaviour.Properties.copy(ElementusBlocks.MOVCADIA_PLANKS.get())));
//
//        public static void register(IEventBus eventBus) {
//            BLOCKS.register(eventBus);
//        }
//    }

    public static void register(IEventBus eventBus) {
//        BLOCKS.register(eventBus);
        ElementusBlocks.register(eventBus);
//        if (farmersDelight) {
//            FarmersDelightBlocks.register(eventBus);
//        }
//        if (advancedNetherite) {
//            AdvancedNetheriteBlocks.register(eventBus);
//        }
//        if (twigs) {
//            TwigsBlocks.register(eventBus);
//        }
    }
}
