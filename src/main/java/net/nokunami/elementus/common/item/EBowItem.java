package net.nokunami.elementus.common.item;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.ForgeEventFactory;
import net.nokunami.elementus.ModChecker;
import net.nokunami.elementus.common.capability.itemNotifier.ItemNotifier;
import net.nokunami.elementus.common.network.ENetwork;
import net.nokunami.elementus.common.network.server.ItemNotifierDingC2SPacket;
import net.nokunami.elementus.common.network.server.ItemNotifierResetC2SPacket;
import net.nokunami.elementus.common.registry.ETier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EBowItem extends BowItem {
    private final Tier tier;
    private static final Component DIARKRITE_TOOLTIP_INFO = Component.translatableWithFallback("item.elementus.diarkrite_bow_info", "Heavy shot").withStyle(ChatFormatting.DARK_AQUA);
    private static final Component DIARKRITE_TOOLTIP_DESC = Component.translatableWithFallback("item.elementus.diarkrite_bow_desc", "Longer draw time + higher damage").withStyle(ChatFormatting.DARK_GRAY);
    private static final Component ANTHEKTITE_TOOLTIP_INFO = Component.translatableWithFallback("item.elementus.anthektite_bow_info", "Quick draw").withStyle(ChatFormatting.AQUA);
    private static final Component ANTHEKTITE_TOOLTIP_DESC = Component.translatableWithFallback("item.elementus.anthektite_bow_desc", "Shorter draw time").withStyle(ChatFormatting.DARK_GRAY);

    public EBowItem(Properties pProperties, Tier tier) {
        super(pProperties);
        this.tier = tier;
    }

    public Tier getTier() { return this.tier; }

    public boolean isValidRepairItem(@NotNull ItemStack stack, @NotNull ItemStack repairStack) {
        return this.tier.getRepairIngredient().test(stack) || super.isValidRepairItem(stack, repairStack);
    }

//    @Override
//    public void inventoryTick(ItemStack stack, Level level, Entity pEntity, int pSlotId, boolean pIsSelected) {
//        super.inventoryTick(stack, level, pEntity, pSlotId, pIsSelected);
//        if (pEntity instanceof Player player && player.isUsingItem() && player.getUseItem().getItem() instanceof EBowItem b) {
//            int i = b.getUseDuration(stack) - player.getUseItemRemainingTicks();
//            i = ForgeEventFactory.onArrowLoose(stack, level, player, i, !player.getProjectile(stack).isEmpty());
//            float f = b.getPowerForTick(i);
//            player.displayClientMessage(Component.literal(String.valueOf(f)), true);
//            player.displayClientMessage(Component.literal(String.valueOf(b.getTier())), true);
//        }
//    }

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
    public void onUseTick(@NotNull Level level, @NotNull LivingEntity entity, @NotNull ItemStack stack, int remainingUseDuration) {
        super.onUseTick(level, entity, stack, remainingUseDuration);
        if (entity instanceof Player player && player.isUsingItem() && player.getUseItem().getItem() instanceof EBowItem b) {
            int i = getUseDuration(stack) - remainingUseDuration;
            i = ForgeEventFactory.onArrowLoose(stack, level, player, i, !player.getProjectile(stack).isEmpty());
            float f = b.getPowerForTick(i);
//            player.displayClientMessage(Component.literal(String.valueOf(f)), true);

            if (!entity.level().isClientSide) {
//                player.getCapability(ItemNotifierCap.CAP).ifPresent(
//                        c -> {
//                            int dih1 = c.getDing();
//                            if (f > 0.9 && dih1 < 1 || f > 1.9 && dih1 < 2) {
//                                ENetwork.sendToServer(new ItemNotifierDingC2SPacket());
//                                level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.PLAYERS, 1, 1 / (level.getRandom().nextFloat() * 0.1F + 0.8F) + f * 0.5F);
//                            }
//                        }
//                );
                ItemNotifier in = ItemNotifier.instance(player);
                int dih1 = in.getDing();
                if (f > 0.9 && dih1 < 1 || f > 1.9 && dih1 < 2) {
                    ENetwork.sendToServer(new ItemNotifierDingC2SPacket(f));
//                    level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.PLAYERS, 1, 1 / (level.getRandom().nextFloat() * 0.1F + 0.8F) + f * 0.5F);
                }
            }
        }
    }

    @Override
    public void onStopUsing(ItemStack stack, LivingEntity entity, int count) {
        ENetwork.sendToServer(new ItemNotifierResetC2SPacket());
    }

    @Override
    public void releaseUsing(@NotNull ItemStack stack, @NotNull Level level, @NotNull LivingEntity entity, int timeLeft) {
        if (ModChecker.archeryExp) super.releaseUsing(stack, level, entity, timeLeft);
        else if (entity instanceof Player player) {
            boolean arrowCheck = player.getAbilities().instabuild || stack.getEnchantmentLevel(Enchantments.INFINITY_ARROWS) > 0;
            ItemStack itemstack = player.getProjectile(stack);

            int i = getUseDuration(stack) - timeLeft;
            i = ForgeEventFactory.onArrowLoose(stack, level, player, i, !itemstack.isEmpty() || arrowCheck);
            if (i < 0) return;

            if (!itemstack.isEmpty() || arrowCheck) {
                if (itemstack.isEmpty()) itemstack = new ItemStack(Items.ARROW);

                float f = getPowerForTick(i);
                if (!((double)f < 0.1D)) {
                    boolean flag1 = player.getAbilities().instabuild || (itemstack.getItem() instanceof ArrowItem && ((ArrowItem)itemstack.getItem()).isInfinite(itemstack, stack, player));
                    if (!level.isClientSide) {
                        ArrowItem arrowitem = (ArrowItem)(itemstack.getItem() instanceof ArrowItem ? itemstack.getItem() : Items.ARROW);
                        AbstractArrow abstractarrow = arrowitem.createArrow(level, itemstack, player);
                        abstractarrow = customArrow(abstractarrow);
                        abstractarrow.shootFromRotation(player, player.getXRot(), player.getYRot(), 0, Math.min(f, 1.5F) * 3, 1);
                        abstractarrow.setBaseDamage(abstractarrow.getBaseDamage() + (f > 1 ? f * 2 : 0));
                        if (f == 1) abstractarrow.setCritArrow(true);

                        int powerEnch = stack.getEnchantmentLevel(Enchantments.POWER_ARROWS);
                        if (powerEnch > 0) abstractarrow.setBaseDamage(abstractarrow.getBaseDamage() + (double) powerEnch * 0.5 + 0.5);

                        int punchEnch = stack.getEnchantmentLevel(Enchantments.PUNCH_ARROWS);
                        if (punchEnch > 0) abstractarrow.setKnockback(punchEnch);

                        if (stack.getEnchantmentLevel(Enchantments.FLAMING_ARROWS) > 0) abstractarrow.setSecondsOnFire(100);

                        stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(player.getUsedItemHand()));
                        if (flag1 || player.getAbilities().instabuild && (itemstack.is(Items.SPECTRAL_ARROW) || itemstack.is(Items.TIPPED_ARROW))) {
                            abstractarrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                        }

                        level.addFreshEntity(abstractarrow);
                    }
//                    setDing(stack, 0);
                    level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1, 1 / (level.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                    if (!flag1 && !player.getAbilities().instabuild) {
                        itemstack.shrink(1);
                        if (itemstack.isEmpty()) player.getInventory().removeItem(itemstack);
                    }

                    player.awardStat(Stats.ITEM_USED.get(this));
                }
            }
        }
    }

    public float getPowerForTick(int charge) {
        boolean dir = getTier().equals(ETier.EnumTiers.DIARKRITE);

        float power = (float) charge / getPullLimit();
        power = (power * power + power * 2) / 3;
        float limit = dir ? 2 : 1;
        if (power > limit) power = limit;

        return power;
    }

    public float getPullLimit() {
        return getTier().equals(ETier.EnumTiers.DIARKRITE) ? 30 : getTier().equals(ETier.EnumTiers.ANTHEKTITE) ? 15 : 20;
    }

    public static void setDing(ItemStack stack, int i) {
        CompoundTag tag = stack.getOrCreateTag();
        if (i <= 0) tag.remove("Ding");
        else tag.putInt("Ding", i);
    }
    public static int getDing(ItemStack stack) {
        return stack.getOrCreateTag().getInt("Ding");
    }

    @Override
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack pStack) {
        return UseAnim.BOW;
    }
}
