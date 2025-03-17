package net.nokunami.elementus.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.nokunami.elementus.common.block.entity.ModHangingSignBlockEntity;
import org.jetbrains.annotations.NotNull;

public class ModHangingSignBlock extends CeilingHangingSignBlock {

    public ModHangingSignBlock(Properties pProperties, WoodType pType) {
        super(pProperties, pType);
    }

    @Override
    public @NotNull BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new ModHangingSignBlockEntity(pos, state);
    }
}