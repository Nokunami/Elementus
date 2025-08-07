package net.nokunami.elementus.common.item;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.state.BlockState;
import net.nokunami.elementus.common.Etags;
import net.nokunami.elementus.common.config.TierConfig;
import net.nokunami.elementus.common.registry.ModItems;
import net.nokunami.elementus.common.registry.ModTiers;

import java.util.List;

import static net.nokunami.elementus.common.registry.ModTiers.*;

public class EItemUtil {
    public static void tooltip(ItemStack stack, List<Component> tooltip, Tier tier) {
        if (diarkriteTier(tier) && stack.getItem() instanceof PickaxeItem) {
            tooltip.add(Component.translatable("item.elementus.diarkrite_pickaxe.desc").withStyle(ChatFormatting.DARK_AQUA));
        }
        if (tier.equals(MOVCADIA))
            tooltip.add(Component.translatable("item.elementus.movcadia_tool.desc").withStyle(ChatFormatting.DARK_PURPLE));
    }

    public static float toolMiningSpeed(float originalSpeed, ItemStack stack, BlockState state) {
        float newSpeed = originalSpeed;
        Item var5 = stack.getItem();
        if (var5 instanceof DiggerItem diggerItem) {
            Tier tier = diggerItem.getTier();
            if (diggerItem.isCorrectToolForDrops(stack, state)) {
                if (matchBlockState(state)) {
                    if (diarkriteTier(tier) && stack.getItem() instanceof PickaxeItem) {
                        newSpeed *= (float) TierConfig.diarkriteAdditionalEfficiency;
                    }
                }
                if (tier.equals(MOVCADIA)) {
                    newSpeed *= (float) (1 - (((double) stack.getDamageValue()) / ((double) stack.getMaxDamage())));
                    if (getMovcadiaEssence(stack) > 0) newSpeed *= 2;
                }
            }
        }
        return newSpeed;
    }

    public static boolean matchBlockState(BlockState state) {
        return state.is(Etags.Blocks.DIARKRITE_EFFICIENT);
    }

    //code from aether mod
    public static float getDurablityBasedSpeed(ItemStack stack, boolean reverse) {
        if (reverse) return (float) ((-2.0 * ((double) stack.getDamageValue()) / ((double) stack.getMaxDamage()) + 0.5));
        return (float) ((2.0 * ((double) stack.getDamageValue()) / ((double) stack.getMaxDamage()) + 0.5));
    }

    public static boolean diarkriteTier(Tier tier) {
        return tier.equals(DIARKRITE)
                || tier.equals(DIARKRITE_IRON)
                || tier.equals(DIARKRITE_GOLD)
                || tier.equals(DIARKRITE_EMERALD)
                || tier.equals(DIARKRITE_DIAMOND)
                || tier.equals(DIARKRITE_CMD);
    }

    public static boolean anthektiteTier(Tier tier) {
        return tier.equals(ANTHEKTITE)
                || tier.equals(ANTHEKTITE_IRON)
                || tier.equals(ANTHEKTITE_GOLD)
                || tier.equals(ANTHEKTITE_EMERALD)
                || tier.equals(ANTHEKTITE_DIAMOND)
                || tier.equals(ANTHEKTITE_CMD);
    }

    public static boolean cmdTier(Tier tier) {
        return tier.equals(STEEL_CMD)
                || tier.equals(DIARKRITE_CMD)
                || tier.equals(ANTHEKTITE_CMD);
    }

    public static boolean getFriendlyFire(ItemStack stack) {
        return stack.getOrCreateTag().getBoolean("FriendlyFire");
    }

    public static void setFriendlyFire(ItemStack stack, boolean b) {
        stack.getOrCreateTag().putBoolean("FriendlyFire", b);
    }

    public static int getMovcadiaEssence(ItemStack stack) {
        CompoundTag tag = stack.getTag();
        return tag != null ? tag.getInt("MovcadiaEssence") : 0;
    }

    public static void setMovcadiaEssence(ItemStack stack) {
        setMovcadiaEssence(stack, 32);
    }

    public static void setMovcadiaEssence(ItemStack stack, int b) {
        stack.getOrCreateTag().putInt("MovcadiaEssence", b);
    }

    public static int getEssenceBarWidth(ItemStack stack) {
        return Math.round(13.0F - (float)(32 - getMovcadiaEssence(stack)) * 13.0F / (float)32);
    }

    public boolean canBeHurtBy(DamageSource source, Tier tier) {
        return !tier.equals(STEEL_CMD) && !tier.equals(DIARKRITE_CMD) && !tier.equals(ANTHEKTITE_CMD);
    }

    public static void movcadiaClickAction(ItemStack stack, ItemStack otherStack, Player player) {
        player.playSound(SoundEvents.ENDER_EYE_DEATH);
        otherStack.shrink(1);
        setMovcadiaEssence(stack);
    }
}