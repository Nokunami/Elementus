package nokunami.elementus.common.compat.advancednetherite.item;

import com.autovw.advancednetherite.api.annotation.Internal;
import com.autovw.advancednetherite.config.ConfigHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class ANItem extends Item {
    private final boolean isFireResistant;

    public ANItem(Item.Properties properties) {
        super(properties);
        this.isFireResistant = true;
    }

    public ANItem(Item.Properties properties, boolean isFireResistant) {
        super(properties);
        this.isFireResistant = isFireResistant;
    }

    public boolean isFireResistant() {
        return this.isFireResistant;
    }

    public void addTooltips(ItemStack stack, Level level, List<Component> tooltips, TooltipFlag flag) {
    }

    public ChatFormatting customDurabilityBarColor(ItemStack stack) {
        return null;
    }

    @Internal
    public void appendHoverText(@NotNull ItemStack stack, Level world, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        if (ConfigHelper.get().getClient().showTooltips()) {
            this.addTooltips(stack, world, tooltip, flag);
        }

    }

    @Internal
    public int getBarColor(@NotNull ItemStack stack) {
        return this.customDurabilityBarColor(stack) != null && ConfigHelper.get().getClient().matchingDurabilityBars() ? (Integer) Objects.requireNonNull(this.customDurabilityBarColor(stack).getColor()) : super.getBarColor(stack);
    }
}
