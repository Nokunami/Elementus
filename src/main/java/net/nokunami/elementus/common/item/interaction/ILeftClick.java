package net.nokunami.elementus.common.item.interaction;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public interface ILeftClick {
    boolean onLeftClick(ItemStack stack, Player livingEntity);
}
