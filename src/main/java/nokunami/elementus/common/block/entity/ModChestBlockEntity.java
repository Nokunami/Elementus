package nokunami.elementus.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.BlockState;
import nokunami.elementus.common.block.ModChests;

public class ModChestBlockEntity extends ChestBlockEntity {

    private final ModChests type;

    public ModChestBlockEntity(BlockPos pos, BlockState state, ModChests type) {
        this(type.getBlockEntityType(), pos, state, type);
    }

    public ModChestBlockEntity(BlockEntityType<?> blockEntityType, BlockPos pos, BlockState state, ModChests type) {
        super(blockEntityType, pos, state);
        this.type = type;
    }

    public ModChests getChestType() {
        return type;
    }

//    @Override
//    public @NotNull Component getDefaultName() {
//        return Component.translatable("container." + Elementus.MODID + "." + type.name().toLowerCase() + "_chest");
//    }

}