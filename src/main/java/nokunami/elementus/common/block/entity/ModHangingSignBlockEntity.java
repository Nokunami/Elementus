package nokunami.elementus.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import nokunami.elementus.common.registry.ModBlockEntityType;
import org.jetbrains.annotations.NotNull;

public class ModHangingSignBlockEntity extends SignBlockEntity {
    private static final int MAX_TEXT_LINE_WIDTH = 60;
    private static final int TEXT_LINE_HEIGHT = 9;

    public ModHangingSignBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntityType.MOD_HANGING_SIGN.get(), pPos, pBlockState);
    }

    @Override
    public @NotNull BlockEntityType<?> getType() {
        return ModBlockEntityType.MOD_HANGING_SIGN.get();
    }

    public int getTextLineHeight() {
        return 9;
    }

    public int getMaxTextLineWidth() {
        return 60;
    }
}