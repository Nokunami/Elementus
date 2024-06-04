package net.nokunami.elementus.common.compat.advancednetherite.util;

import com.autovw.advancednetherite.api.annotation.Internal;
import com.autovw.advancednetherite.config.ConfigHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import java.util.List;

public class ANBlockItem extends BlockItem {
    private final boolean isFireResistant;

    public ANBlockItem(Block block, Item.Properties properties) {
        super(block, properties);
        this.isFireResistant = true;
    }

    public ANBlockItem(Block block, Item.Properties properties, boolean isFireResistant) {
        super(block, properties);
        this.isFireResistant = isFireResistant;
    }

    public boolean isFireResistant() {
        return this.isFireResistant;
    }

    public void addTooltips(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flag) {
    }

    @Internal
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flag) {
        if (ConfigHelper.get().getClient().showTooltips()) {
            this.addTooltips(stack, world, tooltip, flag);
        }

    }
}
