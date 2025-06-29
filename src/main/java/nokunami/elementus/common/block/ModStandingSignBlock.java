package nokunami.elementus.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import nokunami.elementus.common.block.entity.ModSignBlockEntity;
import org.jetbrains.annotations.NotNull;

public class ModStandingSignBlock extends StandingSignBlock {

    public ModStandingSignBlock(Properties pProperties, WoodType pType) {
        super(pProperties, pType);
    }

    @Override
    public @NotNull BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new ModSignBlockEntity(pos, state);
    }
}