package net.nokunami.elementus.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.nokunami.elementus.common.block.entity.ModChestBlockEntity;
import net.nokunami.elementus.common.registry.EBlockEntityType;

public enum ModChests {

    MOVCADIA
    ;

    public BlockEntityType<? extends ModChestBlockEntity> getBlockEntityType() {
        return switch (this) {
            case MOVCADIA -> EBlockEntityType.MOD_CHEST.get();
        };
    }

    public ModChestBlockEntity getBlockEntity(BlockPos pos, BlockState state) {
        return new ModChestBlockEntity(pos, state, this);
    }
}