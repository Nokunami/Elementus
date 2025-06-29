package nokunami.elementus.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import nokunami.elementus.common.block.grower.MovcadiaTreeGrower;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Objects;

public class MovcadiaSapling extends SaplingBlock implements SimpleWaterloggedBlock {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_4;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public MovcadiaSapling(Properties pProperties) {
        super(new MovcadiaTreeGrower(0.5F), pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(STAGE, 0).setValue(AGE, 0).setValue(WATERLOGGED, Boolean.FALSE));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(STAGE).add(AGE).add(WATERLOGGED);
    }


    protected boolean mayPlaceOn(@NotNull BlockState pState, @NotNull BlockGetter pLevel, @NotNull BlockPos pPos) {
        return super.mayPlaceOn(pState, pLevel, pPos)/* || pState.is(Etags.Blocks.MOVCADIA_GROWS_ON)*/;
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        FluidState fluidstate = pContext.getLevel().getFluidState(pContext.getClickedPos());
        boolean flag = fluidstate.getType() == Fluids.WATER;
        return Objects.requireNonNull(super.getStateForPlacement(pContext)).setValue(WATERLOGGED, flag).setValue(AGE, 4);
    }

    public @NotNull FluidState getFluidState(BlockState pState) {
        return pState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(pState);
    }
}