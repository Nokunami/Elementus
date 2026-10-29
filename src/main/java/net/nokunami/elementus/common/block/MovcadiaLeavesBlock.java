package net.nokunami.elementus.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.IForgeShearable;
import net.nokunami.elementus.common.block.state.properties.EBlockStateProperties;
import net.nokunami.elementus.common.registry.EBlocks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.OptionalInt;

public class MovcadiaLeavesBlock extends Block implements SimpleWaterloggedBlock, IForgeShearable {
    public static final int DECAY_DISTANCE = 12;
    public static final IntegerProperty DISTANCE = EBlockStateProperties.DISTANCE;
    public static final BooleanProperty PERSISTENT = BlockStateProperties.PERSISTENT;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public MovcadiaLeavesBlock(int lightLevel) {
        super(BlockBehaviour.Properties.of()
                .mapColor(MapColor.TERRACOTTA_LIGHT_BLUE)
                .strength(2F, 1F)
                .randomTicks()
                .sound(SoundType.AZALEA_LEAVES)
                .noOcclusion()
                .isValidSpawn(EBlocks::ocelotOrParrot)
                .isSuffocating(EBlocks::never)
                .isViewBlocking(EBlocks::never)
                .ignitedByLava()
                .pushReaction(PushReaction.DESTROY)
                .isRedstoneConductor(EBlocks::never)
                .lightLevel((takeThisL) -> lightLevel));
        registerDefaultState(stateDefinition
                .any()
                .setValue(DISTANCE, DECAY_DISTANCE)
                .setValue(PERSISTENT, Boolean.FALSE)
                .setValue(WATERLOGGED, Boolean.FALSE)
        );
    }

    @Override
    public VoxelShape getBlockSupportShape(BlockState pState, BlockGetter pReader, BlockPos pPos) {
        return Shapes.empty();
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return state.getValue(DISTANCE) == DECAY_DISTANCE && !state.getValue(PERSISTENT);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource randomSource) {
        if (decaying(state)) {
            dropResources(state, level, pos);
            level.removeBlock(pos, false);
        }
    }

    protected boolean decaying(BlockState state) {
        return !state.getValue(PERSISTENT) && state.getValue(DISTANCE) == DECAY_DISTANCE;
    }

    @Override
    public void tick(@NotNull BlockState state, ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random) {
        level.setBlock(pos, updateDistance(state, level, pos), 3);
    }

    @Override
    public int getLightBlock(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return 1;
    }

    @Override
    public BlockState updateShape(BlockState state, Direction pFacing, BlockState facingState, LevelAccessor level, BlockPos pCurrentPos, BlockPos pFacingPos) {
        if (state.getValue(WATERLOGGED))
            level.scheduleTick(pCurrentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));

        int i = getDistanceAt(facingState) + 1;
        if (i != 1 || state.getValue(DISTANCE) != i)
            level.scheduleTick(pCurrentPos, this, 1);

        return state;
    }

    private static BlockState updateDistance(BlockState state, LevelAccessor level, BlockPos pos) {
        int i = DECAY_DISTANCE;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for(Direction direction : Direction.values()) {
            blockpos$mutableblockpos.setWithOffset(pos, direction);
            i = Math.min(i, getDistanceAt(level.getBlockState(blockpos$mutableblockpos)) + 1);
            if (i == 1) {
                break;
            }
        }

        return state.setValue(DISTANCE, i);
    }

    private static int getDistanceAt(BlockState pNeighbor) {
        return getOptionalDistanceAt(pNeighbor).orElse(DECAY_DISTANCE);
    }

    public static OptionalInt getOptionalDistanceAt(BlockState pState) {
        if (pState.is(BlockTags.LOGS)) {
            return OptionalInt.of(0);
        } else {
            return pState.hasProperty(DISTANCE) ? OptionalInt.of(pState.getValue(DISTANCE)) : OptionalInt.empty();
        }
    }

    @Override
    public FluidState getFluidState(BlockState pState) {
        return pState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(pState);
    }

    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pLevel.isRainingAt(pPos.above())) {
            if (pRandom.nextInt(15) == 1) {
                BlockPos blockpos = pPos.below();
                BlockState blockstate = pLevel.getBlockState(blockpos);
                if (!blockstate.canOcclude() || !blockstate.isFaceSturdy(pLevel, blockpos, Direction.UP)) {
                    ParticleUtils.spawnParticleBelow(pLevel, pPos, pRandom, ParticleTypes.DRIPPING_WATER);
                }
            }
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(DISTANCE, PERSISTENT, WATERLOGGED);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext pContext) {
        FluidState fluidstate = pContext.getLevel().getFluidState(pContext.getClickedPos());
        BlockState blockstate = this.defaultBlockState().setValue(PERSISTENT, Boolean.valueOf(true)).setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
        return updateDistance(blockstate, pContext.getLevel(), pContext.getClickedPos());
    }
}