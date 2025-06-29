package nokunami.elementus.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import nokunami.elementus.common.block.entity.ModChestBlockEntity;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class ModChestBlock extends ChestBlock {

    public final ModChests chestType;

    public ModChestBlock(BlockBehaviour.Properties pProperties, ModChests pType) {
        super(pProperties, pType::getBlockEntityType);
        this.chestType = pType;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pPos, @NotNull BlockState pState) {
        return this.chestType.getBlockEntity(pPos, pState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, @NotNull BlockState pState, @NotNull BlockEntityType<T> pBlockEntityType) {
        return pLevel.isClientSide ? createTickerHelper(pBlockEntityType, this.blockEntityType(), ModChestBlockEntity::lidAnimateTick) : null;
    }

    public ModChests getType() {
        return chestType;
    }
}