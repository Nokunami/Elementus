package net.nokunami.elementus.common.compat.sniffsweapons;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.nokunami.elementus.common.config.ModConfig;
import net.nokunami.elementus.common.item.EItemUtil;
import nl.sniffiandros.sniffsweapons.item.GreatPickaxeItem;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ModGreatPickaxeItem extends GreatPickaxeItem {
    public ModGreatPickaxeItem(Tier tier, int damage, float speed, Properties properties) {
        super(tier, damage, speed, properties);
    }

    public void appendHoverText(@NotNull ItemStack stack, @javax.annotation.Nullable Level worldIn, @NotNull List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        EItemUtil.pickaxeTooltip(stack, tooltip, getTier());
    }

    @Override
    public float getDestroySpeed(@NotNull ItemStack stack, @NotNull BlockState state) {
        if (ModConfig.COMMON.diarkriteEfficiency.get()) {
            float originalSpeed = super.getDestroySpeed(stack, state);
            return EItemUtil.pickaxeMiningSpeed(originalSpeed, stack, state);
        }
        return super.getDestroySpeed(stack, state);
    }

//    @Override
//    public int getUseDuration(ItemStack stack) {
//        if (getTier() == ModTiers.ANTHEKTITE) {
//            return
//        }
//        return super.getUseDuration(stack);
//    }
//
//    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
//        ItemStack itemstack = player.getItemInHand(hand);
//        if (!player.getOffhandItem().equals(itemstack) && !player.getCooldowns().isOnCooldown(itemstack.getItem()) && this.canBlock(player)) {
//            player.startUsingItem(hand);
//            itemstack.getOrCreateTag().putInt("hits_remaining", 2);
//            player.playSound(SoundsReg.GREAT_PICKAXE_DRAW.get(), 1.0F, 1.0F);
//            return InteractionResultHolder.consume(itemstack);
//        } else {
//            return InteractionResultHolder.pass(itemstack);
//        }
//    }
//
//    public void onStopUsing(ItemStack stack, LivingEntity entity, int count) {
//        int add = 0;
//        if (stack.getTag() != null) {
//            int i = stack.getTag().getInt("hits_remaining");
//            if (i <= 0) {
//                add = 30;
//            }
//        }
//
//        this.applyCooldown(entity, stack, Math.max((this.getUseDuration(stack) - count) * 3, 20) + add);
//        entity.playSound(SoundsReg.GREAT_PICKAXE_WITHDRAW.get(), 1.0F, 1.0F);
//    }
//
//    public void applyCooldown(LivingEntity entity, ItemStack itemStack, int durration) {
//        if (entity instanceof Player player) {
//            player.getCooldowns().addCooldown(itemStack.getItem(), durration);
//        }
//
//    }
}
