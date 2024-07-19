package net.nokunami.elementus.common.compat.advancednetherite.item;

import com.autovw.advancednetherite.AdvancedNetherite;
import com.autovw.advancednetherite.api.annotation.Internal;
import com.autovw.advancednetherite.api.impl.IAdvancedHooks;
import com.autovw.advancednetherite.common.item.AdvancedSwordItem;
import com.autovw.advancednetherite.config.ConfigHelper;
import com.autovw.advancednetherite.core.util.ModTooltips;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.nokunami.elementus.common.compat.advancednetherite.util.ANUtil;
import net.nokunami.elementus.common.registry.ModTiers;

import java.util.List;
import java.util.Objects;

public class ANSwordItem extends AdvancedSwordItem {
    private final Tier tier;

    public ANSwordItem(Tier tier, int attackDamage, float attackSpeed, Item.Properties properties) {
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
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flag) {
        if (ConfigHelper.get().getClient().showTooltips()) {
            if (AdvancedNetherite.getRegistryHelper().getItemById(stack.getItem()).getNamespace().equals("elementus") && ConfigHelper.get().getCommon().getAdditionalDrops().enableAdditionalMobDrops()) {
                if (Screen.hasShiftDown()) {
                    if (this.tier == ModTiers.DIARKRITE_IRON) {
                        tooltip.add(ModTooltips.PHANTOM_MOB_DROP_TOOLTIP);
                    }

                    if (this.tier == ModTiers.DIARKRITE_GOLD) {
                        tooltip.add(ModTooltips.PIGLIN_MOB_DROP_TOOLTIP);
                        tooltip.add(ModTooltips.ZOMBIFIED_PIGLIN_MOB_DROP_TOOLTIP);
                    }

                    if (this.tier == ModTiers.DIARKRITE_EMERALD) {
                        tooltip.add(ModTooltips.ENDERMAN_MOB_DROP_TOOLTIP);
                    }

                    if (this.tier == ModTiers.DIARKRITE_DIAMOND) {
                        tooltip.add(ModTooltips.PIGLIN_MOB_DROP_TOOLTIP);
                        tooltip.add(ModTooltips.ZOMBIFIED_PIGLIN_MOB_DROP_TOOLTIP);
                        tooltip.add(ModTooltips.ENDERMAN_MOB_DROP_TOOLTIP);
                    }


                    if (this.tier == ModTiers.ANTHEKTITE_IRON) {
                        tooltip.add(ModTooltips.PHANTOM_MOB_DROP_TOOLTIP);
                    }

                    if (this.tier == ModTiers.ANTHEKTITE_GOLD) {
                        tooltip.add(ModTooltips.PIGLIN_MOB_DROP_TOOLTIP);
                        tooltip.add(ModTooltips.ZOMBIFIED_PIGLIN_MOB_DROP_TOOLTIP);
                    }

                    if (this.tier == ModTiers.ANTHEKTITE_EMERALD) {
                        tooltip.add(ModTooltips.ENDERMAN_MOB_DROP_TOOLTIP);
                    }

                    if (this.tier == ModTiers.ANTHEKTITE_DIAMOND) {
                        tooltip.add(ModTooltips.PIGLIN_MOB_DROP_TOOLTIP);
                        tooltip.add(ModTooltips.ZOMBIFIED_PIGLIN_MOB_DROP_TOOLTIP);
                        tooltip.add(ModTooltips.ENDERMAN_MOB_DROP_TOOLTIP);
                    }
                } else {
                    tooltip.add(ModTooltips.SHIFT_KEY_TOOLTIP);
                }
            }

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
