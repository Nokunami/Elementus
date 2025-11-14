package net.nokunami.elementus.common.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.nokunami.elementus.common.config.EConfig;
import net.nokunami.elementus.common.registry.EItems;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

import static net.nokunami.elementus.common.item.EItemUtil.getMovcadiaEssence;
import static net.nokunami.elementus.common.item.EItemUtil.setMovcadiaEssence;

public interface IMovcadiaTool {

    default boolean overrideOtherStackedOnMe(ItemStack stack, ItemStack otherStack, Slot slot, ClickAction action, Player player, SlotAccess access) {
        if (getMovcadiaEssence(stack) < 1 && otherStack.getItem() == EItems.MOVCADIA_ESSENCE.get() && action.equals(ClickAction.SECONDARY)) {
            EItemUtil.movcadiaClickAction(stack, otherStack, player);
            return true;
        }
        return false;
    }

    default void drain(ItemStack stack) {
        if (getMovcadiaEssence(stack) > 0) setMovcadiaEssence(stack, getMovcadiaEssence(stack) - 1);
    }

    default void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        tooltip.add(Component.translatable("item.elementus.movcadia_tool.desc").withStyle(ChatFormatting.DARK_PURPLE));
    }

    default float getMovcadiaDestroySpeed(@NotNull ItemStack stack, @NotNull BlockState state, float defaultSpeed) {
        if (EConfig.COMMON.diarkriteEfficiency.get()) {
            return EItemUtil.toolMiningSpeed(defaultSpeed, stack, state);
        }
        return defaultSpeed;
    }
}
