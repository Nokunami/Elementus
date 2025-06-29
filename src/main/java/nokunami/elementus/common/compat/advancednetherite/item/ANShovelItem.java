package nokunami.elementus.common.compat.advancednetherite.item;

import com.autovw.advancednetherite.api.annotation.Internal;
import com.autovw.advancednetherite.common.item.AdvancedShovelItem;
import com.autovw.advancednetherite.config.ConfigHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import nokunami.elementus.common.compat.advancednetherite.ANUtil;

import java.util.List;
import java.util.Objects;

public class ANShovelItem extends AdvancedShovelItem {

    public ANShovelItem(Tier tier, float attackDamage, float attackSpeed, Item.Properties properties) {
        super(tier, attackDamage, attackSpeed, properties);
    }

    public boolean isFireResistant() {
        return true;
    }

    public void addTooltips(ItemStack stack, Level level, List<Component> tooltips, TooltipFlag flag) {
    }

    public ChatFormatting customDurabilityBarColor(ItemStack stack) {
        return null;
    }

    @Internal
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flag) {
        if (ConfigHelper.get().getClient().showTooltips()) {
            this.addTooltips(stack, world, tooltip, flag);
        }

    }

    @Internal
    public int getBarColor(ItemStack stack) {
        int originalColor = super.getBarColor(stack);
        return this.customDurabilityBarColor(stack)
                != null && ConfigHelper.get().getClient().matchingDurabilityBars()
                ? (Integer)Objects.requireNonNull(
                        this.customDurabilityBarColor(stack).getColor()) : ANUtil.getDurabilityBarColor(originalColor, stack);
    }

//    public float getDestroySpeed(ItemStack stack, BlockState state) {
//        float originalSpeed = super.getDestroySpeed(stack, state);
//        return ANUtil.getDestroySpeed(originalSpeed, stack, state);
//    }
}
