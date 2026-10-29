package net.nokunami.elementus.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.nokunami.elementus.common.registry.EBlockEntityType;
import org.jetbrains.annotations.NotNull;

public class ModSignBlockEntity extends SignBlockEntity {

    public ModSignBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(EBlockEntityType.MOD_SIGN.get(), pPos, pBlockState);
    }

    @Override
    public @NotNull BlockEntityType<?> getType() {
        return EBlockEntityType.MOD_SIGN.get();
    }
}
