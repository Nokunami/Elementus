package net.nokunami.elementus.common.item.unique;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.nokunami.elementus.common.entity.projectile.TestTridentEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TestTridentItem extends Item implements Vanishable {
    public static final int THROW_THRESHOLD_TIME = 10;
    public static final float BASE_DAMAGE = 8.0F;
    public static final float SHOOT_POWER = 2.5F;
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

    public TestTridentItem(Properties properties) {
        super(properties);
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Tool modifier", BASE_DAMAGE, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Tool modifier", -2.9F, AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = builder.build();
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, List<Component> list, @NotNull TooltipFlag flag) {
        list.add(Component.literal("WIP").withStyle(ChatFormatting.DARK_PURPLE));
    }

    @Override
    public boolean hasCustomEntity(ItemStack stack) {
        return true;
    }

    @Override
    public @Nullable Entity createEntity(Level level, Entity location, ItemStack stack) {
        TestTridentEntity testTrident = new TestTridentEntity(level, (LivingEntity) location, stack);
        return super.createEntity(level, location, stack);
    }

    public boolean canAttackBlock(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, Player player) {
        return !player.isCreative();
    }

    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack stack) {
        return UseAnim.SPEAR;
    }

    public int getUseDuration(@NotNull ItemStack stack) {
        return 72000;
    }

    @Override
    public void releaseUsing(@NotNull ItemStack stack, @NotNull Level level, @NotNull LivingEntity entity, int timeLeft) {
        if (entity instanceof Player player) {
            int i = this.getUseDuration(stack) - timeLeft;
            if (i >= THROW_THRESHOLD_TIME) {
                int j = EnchantmentHelper.getRiptide(stack);
                if (j <= 0 || player.isInWaterOrRain()) {
                    if (!level.isClientSide) {
                        stack.hurtAndBreak(1, player, (event) -> event.broadcastBreakEvent(entity.getUsedItemHand()));
                        if (j == 0) {
                            TestTridentEntity throwntrident = new TestTridentEntity(level, player, stack);
                            throwntrident.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, SHOOT_POWER + (float)j * 0.5F, 1.0F);
                            if (player.getAbilities().instabuild) throwntrident.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;

                            level.addFreshEntity(throwntrident);
                            level.playSound(null, throwntrident, SoundEvents.TRIDENT_THROW, SoundSource.PLAYERS, 1.0F, 1.0F);
                            if (!player.getAbilities().instabuild) player.getInventory().removeItem(stack);
                        }
                    }

                    player.awardStat(Stats.ITEM_USED.get(this));
                    if (j > 0) {
                        float f7 = player.getYRot();
                        float f = player.getXRot();
                        float f1 = -Mth.sin(f7 * ((float)Math.PI / 180F)) * Mth.cos(f * ((float)Math.PI / 180F));
                        float f2 = -Mth.sin(f * ((float)Math.PI / 180F));
                        float f3 = Mth.cos(f7 * ((float)Math.PI / 180F)) * Mth.cos(f * ((float)Math.PI / 180F));
                        float f4 = Mth.sqrt(f1 * f1 + f2 * f2 + f3 * f3);
                        float f5 = 3.0F * ((1.0F + (float)j) / 4.0F);
                        f1 *= f5 / f4;
                        f2 *= f5 / f4;
                        f3 *= f5 / f4;
                        player.push(f1, f2, f3);
                        player.startAutoSpinAttack(20);
                        if (player.onGround()) player.move(MoverType.SELF, new Vec3(0.0D, 1.1999999F, 0.0D));

                        SoundEvent soundevent;
                        if (j >= 3) soundevent = SoundEvents.TRIDENT_RIPTIDE_3;
                        else if (j == 2) soundevent = SoundEvents.TRIDENT_RIPTIDE_2;
                        else soundevent = SoundEvents.TRIDENT_RIPTIDE_1;

                        level.playSound(null, player, soundevent, SoundSource.PLAYERS, 1.0F, 1.0F);
                    }
                }
            }
        }
    }

    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (itemstack.getDamageValue() >= itemstack.getMaxDamage() - 1) {
            return InteractionResultHolder.fail(itemstack);
        } else if (EnchantmentHelper.getRiptide(itemstack) > 0 && !player.isInWaterOrRain()) {
            return InteractionResultHolder.fail(itemstack);
        } else {
            player.startUsingItem(hand);
            return InteractionResultHolder.consume(itemstack);
        }
    }

    public boolean hurtEnemy(ItemStack stack, @NotNull LivingEntity target, @NotNull LivingEntity attacker) {
        stack.hurtAndBreak(1, attacker, (entity) -> entity.broadcastBreakEvent(EquipmentSlot.MAINHAND));
        return true;
    }

    public boolean mineBlock(@NotNull ItemStack stack, @NotNull Level level, BlockState state, @NotNull BlockPos pos, @NotNull LivingEntity entity) {
        if ((double)state.getDestroySpeed(level, pos) != 0.0D) {
            stack.hurtAndBreak(1, entity, (livingEntity) -> livingEntity.broadcastBreakEvent(EquipmentSlot.MAINHAND));
        }

        return true;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        return slot == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getAttributeModifiers(slot, stack);
    }

    @Override
    public int getEnchantmentValue(ItemStack stack) {
        return 10;
    }
}
