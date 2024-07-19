package net.nokunami.elementus.common.block.entity;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.nokunami.elementus.common.registry.ModBlocks;

import javax.annotation.Nullable;

public class ModRotatedPillarBlock extends RotatedPillarBlock {
    public ModRotatedPillarBlock(Properties pProperties) {
        super(pProperties);
    }

//    @Override
//    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
//        return true;
//    }
//
//    @Override
//    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
//        return 5;
//    }
//
//    @Override
//    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
//        return 5;
//    }

    @Override
    public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
        if(context.getItemInHand().getItem() instanceof AxeItem) {
            if(state.is(ModBlocks.MOVCADIA_LOG.get())) {
                return ModBlocks.STRIPPED_MOVCADIA_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }

            if(state.is(ModBlocks.MOVCADIA_WOOD.get())) {
                return ModBlocks.STRIPPED_MOVCADIA_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
        }

        return super.getToolModifiedState(state, context, toolAction, simulate);
    }
}