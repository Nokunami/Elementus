package nokunami.elementus.common.compat.piercingpaxels;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import nokunami.elementus.common.config.ModConfig;
import nokunami.elementus.common.item.EItemUtil;
import org.jetbrains.annotations.Nullable;
import xyz.amymialee.piercingpaxels.items.PaxelItem;
import xyz.amymialee.piercingpaxels.util.PaxelSlot;

import java.util.List;

public class EPaxelItem extends PaxelItem {
    public EPaxelItem(Tier material, float attackDamage, float attackSpeed, Properties settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag context) {
        EItemUtil.pickaxeTooltip(stack, tooltip, getTier());
        boolean empty = true;

        for(PaxelSlot slot : PaxelSlot.values()) {
            ItemStack upgrade = getUpgrade(stack, slot);
            if (!upgrade.isEmpty()) {
                empty = false;
                break;
            }
        }

        if (empty) {
            Rarity rarity = Rarity.values()[Math.max(0, stack.getRarity().ordinal() - 1)];
            tooltip.add(Component.translatable("item.piercingpaxels.paxel.empty_tooltip").withStyle(rarity.color));
        }

    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        if (ModConfig.COMMON.diarkriteEfficiency.get()) {
            float originalSpeed = super.getDestroySpeed(stack, state);
            return EItemUtil.pickaxeMiningSpeed(originalSpeed, stack, state);
        }
        return super.getDestroySpeed(stack, state);
    }
}
