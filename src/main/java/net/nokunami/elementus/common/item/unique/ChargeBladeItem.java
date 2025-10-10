package net.nokunami.elementus.common.item.unique;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.common.ForgeMod;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static net.nokunami.elementus.ModChecker.betterCombat;
import static net.nokunami.elementus.common.config.UniqueItemConfig.diarkriteChargeBladeBaseCharge;
import static net.nokunami.elementus.common.config.UniqueItemConfig.diarkriteChargeBladeChargePenalty;
import static net.nokunami.elementus.common.registry.EEnchantments.*;

public class ChargeBladeItem extends SwordItem {
    public static final String CHARGE_TAG = "Charge";
    public static final String RESONANCE_CHARGE_TAG = "ResonanceCharge";
    public static final String RESONANCE_TICK_TAG = "ResonanceTick";
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;
    protected static final UUID BASE_ATTACK_REACH_UUID = UUID.fromString("fe181be2-3fd8-4a90-ba64-a4a06cef6d27");

    public ChargeBladeItem(Tier pTier, int attackDamage, float attackSpeed, float attackReach, Item.Properties properties) {
        super(pTier, attackDamage, attackSpeed, properties);
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        this.defaultModifiers = createAttributes(builder, attackDamage, attackSpeed, attackReach).build();
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        return slot.equals(EquipmentSlot.MAINHAND) ? this.defaultModifiers : super.getAttributeModifiers(slot, stack);
    }

    protected ImmutableMultimap.Builder<Attribute, AttributeModifier> createAttributes(ImmutableMultimap.Builder<Attribute, AttributeModifier> builder, int attackDamage, float attackSpeed, float attackReach) {
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", attackDamage + this.getTier().getAttackDamageBonus(), AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", attackSpeed, AttributeModifier.Operation.ADDITION));
        if (!betterCombat) {
            if (attackDamage != 0) builder.put(ForgeMod.ENTITY_REACH.get(), new AttributeModifier(BASE_ATTACK_REACH_UUID, "Weapon modifier", attackReach, AttributeModifier.Operation.ADDITION));
        }
        return builder;
    }

    public int getUseDuration(@NotNull ItemStack pStack) {
        return 72000;
    }

    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack pStack) {
        return UseAnim.CUSTOM;
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public HumanoidModel.@Nullable ArmPose getArmPose(LivingEntity entityLiving, InteractionHand hand, ItemStack itemStack) {
                return IClientItemExtensions.super.getArmPose(entityLiving, hand, itemStack);
            }

            @Override
            public boolean applyForgeHandTransform(PoseStack poseStack, LocalPlayer player, HumanoidArm arm, ItemStack itemInHand, float partialTick, float equipProcess, float swingProcess) {
                return IClientItemExtensions.super.applyForgeHandTransform(poseStack, player, arm, itemInHand, partialTick, equipProcess, swingProcess);
            }
        });
    }

    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(itemstack);
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return super.shouldCauseReequipAnimation(oldStack, newStack, slotChanged);
    }

    @Override
    public void inventoryTick(@NotNull ItemStack stack, @NotNull Level level, @NotNull Entity entity, int slotId, boolean isSelected) {
        if (isEnchantedWith(stack, RESONANCE) && getResonanceTick(stack) > 0) {
            setResonanceTick(stack, getResonanceTick(stack) - 1);
        }
        super.inventoryTick(stack, level, entity, slotId, isSelected);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        Minecraft mc = Minecraft.getInstance();
        assert mc.player != null;
        boolean creative = mc.player.isCreative();

        if (creative) tooltip.add(Component.literal("(").append(Component.translatable("gameMode.creative")).append(")").withStyle(ChatFormatting.DARK_GRAY));
        tooltip.add(Component.translatable("item.elementus.charge_item.charge_desc", getCharge(stack), getMaxCharge(stack)).withStyle(ChatFormatting.GRAY));
    }

    public void friendlyFireTooltip(List<Component> tooltip, ItemStack stack) {
        String friendlyFireStr = String.valueOf(getFriendlyFire(stack));

        tooltip.add(Component.translatable("item.elementus.charge_item.friendly_fire_desc",
            friendlyFireStr.substring(0, 1).toUpperCase(Locale.ROOT) + friendlyFireStr.substring(1)).withStyle(ChatFormatting.GRAY));
    }

    public static boolean isEnchantedWith(ItemStack stack, Supplier<? extends Enchantment> enchantment) {
        return EnchantmentHelper.getTagEnchantmentLevel(enchantment.get(), stack) > 0;
    }

    public static void setCharge(ItemStack stack, int amount) {
        stack.getOrCreateTag().putInt(CHARGE_TAG, Math.min(getCharge(stack) + amount, getMaxCharge(stack)));
    }

    public static int getCharge(ItemStack stack) {
        CompoundTag tag = stack.getTag();
        return tag != null ? tag.getInt(CHARGE_TAG) : 0;
    }

    public static boolean getChargedState(ItemStack stack) {
        return getCharge(stack) >= getMaxCharge(stack);
    }

    public static int getMaxCharge(ItemStack stack) {
        int level = EnchantmentHelper.getTagEnchantmentLevel(CHARGE_STACKING.get(), stack);
        return getChargeStack(stack) * (isEnchantedWith(stack, CHARGE_STACKING) ? level + 1 : 1);
    }

    public static int getChargeStack(ItemStack stack) {
        return isEnchantedWith(stack, CONDENSED_BURST) ? diarkriteChargeBladeChargePenalty : diarkriteChargeBladeBaseCharge;
    }

    public static float getChargeAmount(ItemStack stack, boolean b) {
        float i0 = Math.min(getCharge(stack), getChargeStack(stack));
        float i1 = getChargeStack(stack);
        float base = !b ? (i0 / i1) : 1;
        if (isEnchantedWith(stack, SACRIFICE_CURSE)) base += 1.25F;
        return base;
    }

    public static boolean getFriendlyFire(ItemStack stack) {
        return stack.getOrCreateTag().getBoolean("FriendlyFire");
    }

    public static void setFriendlyFire(ItemStack stack, boolean b) {
        stack.getOrCreateTag().putBoolean("FriendlyFire", b);
    }

    public static void setResonanceCharge(ItemStack stack, int amount) {
        if (isEnchantedWith(stack, RESONANCE)) {
            stack.getOrCreateTag().putInt(RESONANCE_CHARGE_TAG, (int) Math.min(getResonanceCharge(stack) + amount, getChargeStack(stack) * 1.5));
        }
    }

    public static int getResonanceCharge(ItemStack stack) {
        CompoundTag tag = stack.getTag();
        return tag != null ? tag.getInt(RESONANCE_CHARGE_TAG) : 0;
    }

    public static void setResonanceTick(ItemStack stack, int amount) {
        stack.getOrCreateTag().putInt(RESONANCE_TICK_TAG, amount);
    }

    public static int getResonanceTick(ItemStack stack) {
        CompoundTag tag = stack.getTag();
        return tag != null ? tag.getInt(RESONANCE_TICK_TAG) : 0;
    }

    @Override
    public boolean overrideOtherStackedOnMe(@NotNull ItemStack stack, @NotNull ItemStack otherStack, @NotNull Slot slot, @NotNull ClickAction action, @NotNull Player player, @NotNull SlotAccess access) {
        if (otherStack.isEmpty() && action == ClickAction.SECONDARY) {
            setFriendlyFire(stack, !getFriendlyFire(stack));
            player.playSound(SoundEvents.ENDER_EYE_DEATH);
            return true;
        }
        return super.overrideOtherStackedOnMe(stack, otherStack, slot, action, player, access);
    }

    @Override
    public boolean isEnchantable(@NotNull ItemStack stack) {
        return this.getMaxStackSize(stack) == 1;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return super.canApplyAtEnchantingTable(stack, enchantment) && enchantment != Enchantments.UNBREAKING && enchantment != Enchantments.MENDING;
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return false;
    }

    @Override
    public boolean canDisableShield(ItemStack stack, ItemStack shield, LivingEntity entity, LivingEntity attacker) {
        return true;
    }

    @Override
    public boolean canBeHurtBy(@NotNull DamageSource pDamageSource) {
        return false;
    }

    @Override
    public int getBarWidth(@NotNull ItemStack pStack) {
        return Math.round(13.0F - (float) (getMaxCharge(pStack) - getCharge(pStack)) * 13.0F / (float) getMaxCharge(pStack));
    }

    @Override
    public boolean isBarVisible(@NotNull ItemStack pStack) {
        return !isEnchantedWith(pStack, CHARGE_STACKING) && getCharge(pStack) > 0;
    }

    public boolean isMultiBarVisible(@NotNull ItemStack pStack) {
        return isEnchantedWith(pStack, CHARGE_STACKING) && getCharge(pStack) > 0;
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack stack, LivingEntity target, @NotNull LivingEntity attacker) {
        if (target.attackable() && attacker instanceof Player player && player.getAttackStrengthScale(1.0F) >= 0.5F && !isEnchantedWith(stack, SACRIFICE_CURSE)) {
            setCharge(stack, 1);
            ServerLevel level = (ServerLevel)attacker.level();
            if (isEnchantedWith(stack, RESONANCE) && !getChargedState(stack)) setResonanceCharge(stack, 1);
            if (!attacker.level().isClientSide && getChargedState(stack)) {
                level.playSound(null, target, SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.PLAYERS, 1, 0);
            }
        }
        return super.hurtEnemy(stack, target, attacker);
    }

    public boolean canParry() {
        return false;
    }

    /// ArcheryExpansion code: BowItemMixin
    public static void applyRecoil(Entity target,Entity source, double aX, double aY, double aZ, boolean type) {
        Vec3 lookDirection = source.getViewVector(1.0f);
        double fX = -aX;
        double fY = -aY;
        double fZ = -aZ;
        if (type) {
            fX = aX;
            fY = aY;
            fZ = aZ;
        }
        Vec3 knockback = lookDirection.multiply(fX, fY, fZ);

        target.setDeltaMovement(
                source.getDeltaMovement().x + knockback.x,
                source.getDeltaMovement().y + knockback.y,
                source.getDeltaMovement().z + knockback.z
        );
        target.hurtMarked = true;
    }

    public static void applyRecoil(Entity target,Entity source, double amount, boolean type) {
        applyRecoil(target, source, amount, amount, amount, type);
    }
}
