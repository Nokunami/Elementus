package net.nokunami.elementus.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.nokunami.elementus.common.block.entity.ModHangingSignBlockEntity;
import net.nokunami.elementus.common.block.entity.ModSignBlockEntity;
import net.nokunami.elementus.common.registry.ModBlockEntityType;

public class ModWallHangingSignBlock extends WallHangingSignBlock {

    public ModWallHangingSignBlock(Properties pProperties, WoodType pType) {
        super(pProperties, pType);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ModHangingSignBlockEntity(pos, state);
    }
}