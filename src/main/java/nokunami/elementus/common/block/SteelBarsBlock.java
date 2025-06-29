package nokunami.elementus.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CrossCollisionBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class SteelBarsBlock extends CrossCollisionBlock {
    public SteelBarsBlock(Properties pProperties) {
        super(1.0F, 1.0F, 16.0F, 16.0F, 16.0F, pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(NORTH, Boolean.FALSE).setValue(EAST, Boolean.FALSE).setValue(SOUTH, Boolean.FALSE).setValue(WEST, Boolean.FALSE).setValue(WATERLOGGED, Boolean.FALSE));
    }

    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        BlockGetter blockgetter = pContext.getLevel();
        BlockPos blockpos = pContext.getClickedPos();
        FluidState fluidstate = pContext.getLevel().getFluidState(pContext.getClickedPos());
        BlockPos blockpos1 = blockpos.north();
        BlockPos blockpos2 = blockpos.south();
        BlockPos blockpos3 = blockpos.west();
        BlockPos blockpos4 = blockpos.east();
        BlockState blockstate = blockgetter.getBlockState(blockpos1);
        BlockState blockstate1 = blockgetter.getBlockState(blockpos2);
        BlockState blockstate2 = blockgetter.getBlockState(blockpos3);
        BlockState blockstate3 = blockgetter.getBlockState(blockpos4);
        return this.defaultBlockState().setValue(NORTH, this.attachsTo(blockstate, blockstate.isFaceSturdy(blockgetter, blockpos1, Direction.SOUTH))).setValue(SOUTH, this.attachsTo(blockstate1, blockstate1.isFaceSturdy(blockgetter, blockpos2, Direction.NORTH))).setValue(WEST, this.attachsTo(blockstate2, blockstate2.isFaceSturdy(blockgetter, blockpos3, Direction.EAST))).setValue(EAST, this.attachsTo(blockstate3, blockstate3.isFaceSturdy(blockgetter, blockpos4, Direction.WEST))).setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }


    public @NotNull BlockState updateShape(BlockState pState, @NotNull Direction pFacing, @NotNull BlockState pFacingState, @NotNull LevelAccessor pLevel, @NotNull BlockPos pCurrentPos, @NotNull BlockPos pFacingPos) {
        if (pState.getValue(WATERLOGGED)) {
            pLevel.scheduleTick(pCurrentPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevel));
        }

        return pFacing.getAxis().isHorizontal() ? pState.setValue(PROPERTY_BY_DIRECTION.get(pFacing), this.attachsTo(pFacingState, pFacingState.isFaceSturdy(pLevel, pFacingPos, pFacing.getOpposite()))) : super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
    }

    public @NotNull VoxelShape getVisualShape(@NotNull BlockState pState, @NotNull BlockGetter pReader, @NotNull BlockPos pPos, @NotNull CollisionContext pContext) {
        return Shapes.empty();
    }

    public boolean skipRendering(@NotNull BlockState pState, BlockState pAdjacentBlockState, @NotNull Direction pSide) {
        if (pAdjacentBlockState.is(this)) {
            if (!pSide.getAxis().isHorizontal()) {
                return true;
            }

            if (pState.getValue(PROPERTY_BY_DIRECTION.get(pSide)) && pAdjacentBlockState.getValue(PROPERTY_BY_DIRECTION.get(pSide.getOpposite()))) {
                return true;
            }
        }

        return super.skipRendering(pState, pAdjacentBlockState, pSide);
    }

    public final boolean attachsTo(BlockState pState, boolean pSolidSide) {
        return !isExceptionForConnection(pState) && pSolidSide || pState.getBlock() instanceof SteelBarsBlock || pState.is(BlockTags.WALLS);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(NORTH, EAST, WEST, SOUTH, WATERLOGGED);
    }
}