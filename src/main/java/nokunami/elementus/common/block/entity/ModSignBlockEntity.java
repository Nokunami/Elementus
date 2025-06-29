package nokunami.elementus.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import nokunami.elementus.common.registry.ModBlockEntityType;
import org.jetbrains.annotations.NotNull;

public class ModSignBlockEntity extends SignBlockEntity {

    public ModSignBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntityType.MOD_SIGN.get(), pPos, pBlockState);
    }

    @Override
    public @NotNull BlockEntityType<?> getType() {
        return ModBlockEntityType.MOD_SIGN.get();
    }
}
