package net.nokunami.elementus.block;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class TestBlock extends Block {
    public TestBlock(Properties pProperties) {
        super(pProperties);
    }

    ItemStack stack;

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        pPlayer.sendSystemMessage(Component.literal("enchantment value: " + stack.getEnchantmentValue()));
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }
}
