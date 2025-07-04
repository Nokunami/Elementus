package net.nokunami.elementus.common.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.state.BlockState;
import net.nokunami.elementus.common.Etags;
import net.nokunami.elementus.common.config.TierConfig;

import java.util.List;
import java.util.function.Predicate;

import static net.nokunami.elementus.common.registry.ModTiers.*;

public class EItemUtil {
//    public static final Predicate<Etags.Entity> targets = (e) -> (e instanceof OwnableEntity ownable && ((ownable.getOwner() != null &&
//            (ownable.getOwner().is(livingEntity) || ownable.getOwner().isAlliedTo(livingEntity)) && getFriendlyFire(stack)) ||
//            ownable.getOwner() == null));

    public static void pickaxeTooltip(ItemStack stack, List<Component> tooltip, Tier tier) {
        if (diarkriteTier(tier)) {
            tooltip.add(Component.translatable("item.elementus.diarkrite_pickaxe.desc").withStyle(ChatFormatting.DARK_AQUA));
        }
    }

    public static float pickaxeMiningSpeed(float originalSpeed, ItemStack stack, BlockState state) {
        float newSpeed = originalSpeed;
        Item var5 = stack.getItem();
        if (var5 instanceof DiggerItem diggerItem) {
            Tier tier = diggerItem.getTier();
            if (diggerItem.isCorrectToolForDrops(stack, state)) {
                if (matchBlockState(state)) {
                    if (diarkriteTier(tier) || stack.is(Etags.Items.TOUGH_PICAXE)) {
                        newSpeed *= TierConfig.diarkriteAdditionalEfficiency;
                    }
                }
//                if (anthektiteTier(tier) || stack.is(Etags.Items.EFFICIENT_PICKAXE)) {
//                    newSpeed *= getDurablityBasedSpeed(stack);
//                }
            }
        }
        return newSpeed;
    }

    public static boolean matchBlockState(BlockState state) {
        return state.is(Etags.Blocks.DIARKRITE_EFFICIENT);
    }

    public static float getDurablityBasedSpeed(ItemStack stack) {
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
}