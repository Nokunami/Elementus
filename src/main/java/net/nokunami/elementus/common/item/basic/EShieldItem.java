package net.nokunami.elementus.common.item.basic;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.nokunami.elementus.ModChecker;
import net.nokunami.elementus.common.item.ISecondaryBar;
import net.nokunami.elementus.common.registry.ETier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EShieldItem extends ShieldItem implements ISecondaryBar {
    private final Tier tier;
    private static final String BUFFER_TAG_NAME = "ShieldBuffer";
    private static final String BUFFER_TICK_TAG_NAME = "ShieldBufferTick";
    private static final Component DIARKRITE_TOOLTIP_INFO = Component.translatableWithFallback("item.elementus.diarkrite_shield_info", "Sturdy").withStyle(ChatFormatting.DARK_AQUA);
    private static final Component DIARKRITE_TOOLTIP_DESC = Component.translatableWithFallback("item.elementus.diarkrite_shield_desc", "Longer delay + can take multiple hits before getting disabled").withStyle(ChatFormatting.DARK_GRAY);
    private static final Component ANTHEKTITE_TOOLTIP_INFO = Component.translatableWithFallback("item.elementus.anthektite_shield_info", "Swift Recovery").withStyle(ChatFormatting.AQUA);
    private static final Component ANTHEKTITE_TOOLTIP_DESC = Component.translatableWithFallback("item.elementus.anthektite_shield_desc", "Instant block + fast cooldown").withStyle(ChatFormatting.DARK_GRAY);
    private final int buffer;

    public EShieldItem(Properties pProperties, int bufferValue, Tier modTiers) {
        super(pProperties);
        tier = modTiers;
        buffer = bufferValue;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        if (!getTier().equals(ETier.EnumTiers.ASTALITE) || ModChecker.archeryExp) {
            tooltip.add(getTier().equals(ETier.EnumTiers.DIARKRITE) ? DIARKRITE_TOOLTIP_INFO : ANTHEKTITE_TOOLTIP_INFO);
            if (Screen.hasShiftDown()) {
                tooltip.add(getTier().equals(ETier.EnumTiers.DIARKRITE) ? DIARKRITE_TOOLTIP_DESC : ANTHEKTITE_TOOLTIP_DESC);
            }
        }
    }

    @Override
    public void onInventoryTick(ItemStack stack, Level level, Player player, int slotIndex, int selectedIndex) {
        super.onInventoryTick(stack, level, player, slotIndex, selectedIndex);
        if (getBufferValue(stack) > 0) {
            if (getBufferTick(stack) > 0) setBufferTick(stack, getBufferTick(stack) - 1);
            if (!player.isUsingItem()) {
                if (level.getGameTime() % 100 == 0) {
                    setBufferValue(stack, getBufferValue(stack) - 1);
                    setBufferTick(stack, 2);
                }
            }
        }
    }

    public Tier getTier() {
        return this.tier;
    }

    public boolean isValidRepairItem(@NotNull ItemStack toRepair, @NotNull ItemStack repair) {
        return tier.getRepairIngredient().test(repair) || super.isValidRepairItem(toRepair, repair);
    }

    public void applyCustomCooldown(Player player, ItemStack stack) {
        int cooldown = 100;
//        int buffer = 0;
        if (getTier().equals(ETier.EnumTiers.DIARKRITE)) {
            cooldown = 160;
//            buffer = 3;
        }
        if (getTier().equals(ETier.EnumTiers.ANTHEKTITE)) {
            cooldown = 60;
        }
        if (getBufferValue(stack) < buffer) {
            setBufferValue(stack, getBufferValue(stack) + 1);
        } else {
            player.getCooldowns().addCooldown(this, cooldown);
            player.stopUsingItem();
            setBufferValue(stack, 0);
        }
    }

    public boolean applyCustomDelay(ItemStack stack, int useTime) {
        int delay = 5;
        if (getTier().equals(ETier.EnumTiers.DIARKRITE)) delay = 7;
        if (getTier().equals(ETier.EnumTiers.ANTHEKTITE)) delay = 0;
        return getUseDuration(stack) - useTime >= delay;
    }

    public static void setBufferValue(ItemStack stack, int buffer) {
        CompoundTag tag = stack.getOrCreateTag();
        tag.putInt(BUFFER_TAG_NAME, Math.max(buffer, 0));
    }
    public static int getBufferValue(ItemStack stack) {
        return stack.getOrCreateTag().getInt(BUFFER_TAG_NAME);
    }

    public static void setBufferTick(ItemStack stack, int tick) {
        CompoundTag tag = stack.getOrCreateTag();
        tag.putInt(BUFFER_TICK_TAG_NAME, tick);
    }
    public static int getBufferTick(ItemStack stack) {
        return stack.getOrCreateTag().getInt(BUFFER_TICK_TAG_NAME);
    }

//    public int getBufferBarColor(ItemStack stack) {
//        float f = Math.max(0.0F, (buffer - (float) getBufferValue(stack)) / buffer);
//        return Mth.hsvToRgb(f / 3.0F, 1.0F, 1.0F);
//    }

//    public int getBufferBarWidth(ItemStack stack) {
//        return Math.round(13.0F - (float) (buffer - getBufferValue(stack)) * 13.0F / (float) buffer);
//    }

    @Override
    public @NotNull SoundEvent getEquipSound() {
        return SoundEvents.ARMOR_EQUIP_IRON;
    }

    @Override
    public boolean isSecondBarVisible(ItemStack stack) {
        return getBufferValue(stack) > 0;
    }

    @Override
    public int getSecondBarWidth(ItemStack stack) {
        return Math.round(13 - (float) (buffer - getBufferValue(stack)) * 13 / (float) buffer);
    }

    @Override
    public int getSecondBarColor(ItemStack stack) {
        float f = Math.max(0, (buffer - (float) getBufferValue(stack)) / buffer);
        return Mth.hsvToRgb(f / 3, 1, 1);
    }
}
