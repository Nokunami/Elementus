package nokunami.elementus.common.compat.advancednetherite.item;

import com.autovw.advancednetherite.AdvancedNetherite;
import com.autovw.advancednetherite.api.annotation.Internal;
import com.autovw.advancednetherite.config.ConfigHelper;
import com.autovw.advancednetherite.core.util.ModTooltips;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import nokunami.elementus.common.compat.advancednetherite.ANUtil;
import nokunami.elementus.common.config.ModConfig;
import nokunami.elementus.common.item.EItemUtil;
import nokunami.elementus.common.item.ModPickaxeItem;
import nokunami.elementus.common.registry.ModTiers;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class ANPickaxeItem extends ModPickaxeItem {
    private final Tier tier;

    public ANPickaxeItem(Tier tier, int attackDamage, float attackSpeed, Item.Properties properties) {
        super(tier, attackDamage, attackSpeed, properties);
        this.tier = tier;
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
    @Override
    public void appendHoverText(@NotNull ItemStack stack, Level world, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        EItemUtil.pickaxeTooltip(stack, tooltip, getTier());
        if (ConfigHelper.get().getClient().showTooltips()) {
            if (AdvancedNetherite.getRegistryHelper().getItemById(stack.getItem()).getNamespace().equals("elementus") && ConfigHelper.get().getCommon().getAdditionalDrops().enableAdditionalOreDrops()) {
                if (Screen.hasShiftDown()) {
                    if (this.tier == ModTiers.DIARKRITE_IRON) {
                        tooltip.add(ModTooltips.IRON_ORE_DROP_TOOLTIP);
                    }

                    if (this.tier == ModTiers.DIARKRITE_GOLD) {
                        tooltip.add(ModTooltips.GOLD_ORE_DROP_TOOLTIP);
                    }

                    if (this.tier == ModTiers.DIARKRITE_EMERALD) {
                        tooltip.add(ModTooltips.EMERALD_ORE_DROP_TOOLTIP);
                    }

                    if (this.tier == ModTiers.DIARKRITE_DIAMOND) {
                        tooltip.add(ModTooltips.DIAMOND_ORE_DROP_TOOLTIP);
                    }


                    if (this.tier == ModTiers.ANTHEKTITE_IRON) {
                        tooltip.add(ModTooltips.IRON_ORE_DROP_TOOLTIP);
                    }

                    if (this.tier == ModTiers.ANTHEKTITE_GOLD) {
                        tooltip.add(ModTooltips.GOLD_ORE_DROP_TOOLTIP);
                    }

                    if (this.tier == ModTiers.ANTHEKTITE_EMERALD) {
                        tooltip.add(ModTooltips.EMERALD_ORE_DROP_TOOLTIP);
                    }

                    if (this.tier == ModTiers.ANTHEKTITE_DIAMOND) {
                        tooltip.add(ModTooltips.DIAMOND_ORE_DROP_TOOLTIP);
                    }
                } else {
                    tooltip.add(ModTooltips.SHIFT_KEY_TOOLTIP);
                }
            }
            this.addTooltips(stack, world, tooltip, flag);
        }
    }

    @Override
    public float getDestroySpeed(@NotNull ItemStack stack, @NotNull BlockState state) {
        if (ModConfig.COMMON.diarkriteEfficiency.get()) {
            float originalSpeed = super.getDestroySpeed(stack, state);
            return EItemUtil.pickaxeMiningSpeed(originalSpeed, stack, state);
        }
        return super.getDestroySpeed(stack, state);
    }

    @Internal
    public int getBarColor(@NotNull ItemStack stack) {
        int originalColor = super.getBarColor(stack);
        return this.customDurabilityBarColor(stack)
                != null && ConfigHelper.get().getClient().matchingDurabilityBars()
                ? Objects.requireNonNull(
                this.customDurabilityBarColor(stack).getColor()) : ANUtil.getDurabilityBarColor(originalColor, stack);
    }

//    public float getDestroySpeed(ItemStack stack, BlockState state) {
//        float originalSpeed = super.getDestroySpeed(stack, state);
//        return ANUtil.getDestroySpeed(originalSpeed, stack, state);
//    }
}
