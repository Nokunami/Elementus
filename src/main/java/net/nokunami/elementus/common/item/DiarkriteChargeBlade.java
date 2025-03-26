package net.nokunami.elementus.common.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
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
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import net.nokunami.elementus.common.config.ItemConfig;
import net.nokunami.elementus.common.registry.ModEnchantments;
import net.nokunami.elementus.common.registry.ModParticleTypes;
import net.nokunami.elementus.common.registry.ModTiers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

import static net.nokunami.elementus.ModChecker.betterCombat;

public class DiarkriteChargeBlade extends SwordItem {
    private static final int BURST_RANGE = 1;
    private static final int BOOM_RANGE = 16;
    public static final String CHARGE_TAG = "Charge";
    private boolean startSoundPlayed = false;
    private boolean midLoadSoundPlayed = false;
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;
    protected static final UUID ATTACK_REACH_UUID = UUID.fromString("fe181be2-3fd8-4a90-ba64-a4a06cef6d27");

    public DiarkriteChargeBlade() {
        super(ModTiers.DIARKRITE, 0, 0, new Properties().fireResistant().rarity(Rarity.EPIC));
        float attackDamage = (float) ItemConfig.diarkriteChargeBladeDamage + this.getTier().getAttackDamageBonus();
        float attackSpeed = (float) ItemConfig.diarkriteChargeBladeAttackSpeed;
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", attackDamage, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", attackSpeed, AttributeModifier.Operation.ADDITION));
        if (!betterCombat) {
            if (ItemConfig.diarkriteChargeBladeAttackReach != 0) builder.put(ForgeMod.ENTITY_REACH.get(), new AttributeModifier(ATTACK_REACH_UUID, "Weapon modifier", ItemConfig.diarkriteChargeBladeAttackReach, AttributeModifier.Operation.ADDITION));
        }
        this.defaultModifiers = builder.build();
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        return slot.equals(EquipmentSlot.MAINHAND) ? this.defaultModifiers : super.getAttributeModifiers(slot, stack);
    }

    @Override
    public void releaseUsing(@NotNull ItemStack stack, @NotNull Level level, @NotNull LivingEntity livingEntity, int timeCharged) {
        if (livingEntity instanceof Player) {
            int i = this.getUseDuration(stack) - timeCharged;
            if (i < 0) return;
            float f = getPowerForTime(i);
            if (!((double)f < 1D)) {
                if (!level.isClientSide) {
                    boolean c = EnchantmentHelper.getTagEnchantmentLevel(ModEnchantments.CONDENSED_BURST.get(), stack) > 0;
                    createBoom(level, livingEntity, stack);
                    level.playSound(null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), SoundEvents.WARDEN_SONIC_BOOM, SoundSource.PLAYERS, 1.0F, 1.0F);
                    if (c) level.playSound(null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), SoundEvents.TRIDENT_THUNDER, SoundSource.PLAYERS, 1.0F, 1.0F);
                }
                if (enchanted(stack, 0)) livingEntity.hurt(level.damageSources().sonicBoom(livingEntity), livingEntity.getMaxHealth()*0.25F);
            }
        }
    }

    public static float getPowerForTime(int pCharge) {
        float f = (float)pCharge / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }
        return f;
    }

    public int getUseDuration(@NotNull ItemStack pStack) {
        return 72000;
    }

    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack pStack) {
        return UseAnim.BOW;
    }

    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (getCharge(itemStack) > 2 || enchanted(itemStack, 0) || player.isCreative()) {
            player.startUsingItem(hand);
            return InteractionResultHolder.consume(itemStack);
        } else return super.use(level, player, hand);
    }

    @Override
    public void onUseTick(Level pLevel, @NotNull LivingEntity pLivingEntity, @NotNull ItemStack pStack, int pRemainingUseDuration) {
        if (!pLevel.isClientSide) {
            int i = EnchantmentHelper.getTagEnchantmentLevel(Enchantments.QUICK_CHARGE, pStack);
            SoundEvent soundevent = SoundEvents.WARDEN_SONIC_CHARGE;
            SoundEvent soundevent1 = i == 0 ? SoundEvents.VEX_CHARGE : null;
            float f = (float)(pStack.getUseDuration() - pRemainingUseDuration) / (float)getChargeDuration(pStack);
            if (f < 0.2F) {
                this.startSoundPlayed = false;
                this.midLoadSoundPlayed = false;
            }
            if (f >= 0.2F && !this.startSoundPlayed) {
                this.startSoundPlayed = true;
                pLevel.playSound(null, pLivingEntity.getX(), pLivingEntity.getY(), pLivingEntity.getZ(), soundevent, SoundSource.PLAYERS, 0.5F, 1.0F);
            }
            if (f >= 0.5F && soundevent1 != null && !this.midLoadSoundPlayed) {
                this.midLoadSoundPlayed = true;
                pLevel.playSound(null, pLivingEntity.getX(), pLivingEntity.getY(), pLivingEntity.getZ(), soundevent1, SoundSource.PLAYERS, 0.5F, 1.0F);
            }
        }
    }

    public static int getChargeDuration(ItemStack pCrossbowStack) {
        int i = EnchantmentHelper.getTagEnchantmentLevel(Enchantments.QUICK_CHARGE, pCrossbowStack);
        return i == 0 ? 25 : 25 - 5 * i;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        String chargeText = enchanted(stack, 0) ? "item.elementus.diarkrite_charge_blade.cursed_charge_desc" : "item.elementus.diarkrite_charge_blade.charge_desc";
        ChatFormatting chargeTextColor = enchanted(stack, 0) ? ChatFormatting.DARK_RED : ChatFormatting.GRAY;
        ChatFormatting underlineText = enchanted(stack, 0) ? ChatFormatting.UNDERLINE : ChatFormatting.GRAY;
        String damageModifierText = enchanted(stack, 0) && enchanted(stack, 1) ? " (+25%) (-40%)" : enchanted(stack, 0) ? " (+25%)" : enchanted(stack, 1) ? " (-40%)" : "";
        ChatFormatting damageModifierColor = enchanted(stack, 0) || enchanted(stack, 1) ? ChatFormatting.GOLD : ChatFormatting.DARK_AQUA;

        tooltip.add(Component.translatable(chargeText, String.valueOf(getCharge(stack))).withStyle(underlineText).withStyle(chargeTextColor));
        if (!Screen.hasShiftDown()) {
            tooltip.add(Component.translatable("item.elementus.diarkrite_charge_blade.damage_ratio_desc").withStyle(ChatFormatting.GRAY)
                    .append(Component.translatable(Math.round(getChargeAmount(stack) * 100) + "%").withStyle(ChatFormatting.DARK_AQUA)
                            .append(Component.translatable(damageModifierText).withStyle((damageModifierColor)))));
        } else {
            tooltip.add(Component.translatable("item.elementus.diarkrite_charge_blade.damage_ratio_shift_desc").withStyle(ChatFormatting.GRAY)
                    .append(Component.translatable(Math.round(getChargeAmount(stack) * 100) + "%").withStyle(ChatFormatting.DARK_AQUA)));
            if (enchanted(stack, 0)) tooltip.add(Component.translatable(" +25% ").append(Component.translatable("enchantment.elementus.sacrifice_curse")).withStyle(ChatFormatting.RED));
            if (enchanted(stack, 1)) tooltip.add(Component.translatable(" -40% ").append(Component.translatable("enchantment.elementus.condensed_burst")).withStyle(ChatFormatting.AQUA));
        }
        tooltip.add(Component.translatable("item.elementus.diarkrite_charge_blade.friendly_fire_desc",
                String.valueOf(getFriendlyFire(stack))).withStyle(ChatFormatting.GRAY));
        if (stack.isEnchanted()) tooltip.add(CommonComponents.EMPTY);
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack stack, LivingEntity target, @NotNull LivingEntity attacker) {
        if (target.attackable() && attacker instanceof Player player && player.getAttackStrengthScale(1.0F) >= 0.5F && !enchanted(stack, 0)) {
            setCharge(stack, getCharge(stack) < getMaxCharge(stack) ? getCharge(stack) + 1 : getCharge(stack));
            ServerLevel level = (ServerLevel)attacker.level();
            if (!attacker.level().isClientSide && getChargedState(stack)) {
                level.playSound(null, target, SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.PLAYERS, 1, 0);
            }
        }
        return super.hurtEnemy(stack, target, attacker);
    }

    public static boolean enchanted(ItemStack itemStack, int type) {
        int curse = EnchantmentHelper.getTagEnchantmentLevel(ModEnchantments.SACRIFICE_CURSE.get(), itemStack);
        int resonance = EnchantmentHelper.getTagEnchantmentLevel(ModEnchantments.CONDENSED_BURST.get(), itemStack);
        return type == 0 ? curse > 0 : type == 1 && resonance > 0;
    }

    public static void setCharge(ItemStack stack, int amount) {
        stack.getOrCreateTag().putInt(CHARGE_TAG, amount);
    }

    public static int getCharge(ItemStack stack) {
        return stack.getOrCreateTag().getInt(CHARGE_TAG);
    }

    public boolean getChargedState(ItemStack stack) {
        return getCharge(stack) == getMaxCharge(stack);
    }

    public static int getMaxCharge(ItemStack itemStack) {
        int i = EnchantmentHelper.getTagEnchantmentLevel(Enchantments.BLOCK_FORTUNE, itemStack);
        return i > 0 ? 8 : 7;
    }

    public static float getChargeAmount(ItemStack stack) {
        int i0 = getCharge(stack);
        int i1 = getMaxCharge(stack);
        float base = (float) i0 / i1;
        if (enchanted(stack, 0)) base = 1.25F;
        return base * (enchanted(stack, 1) ? 0.6F : 1);
    }

    public static boolean getFriendlyFire(ItemStack stack) {
        return stack.getOrCreateTag().getBoolean("FriendlyFire");
    }

    public void setFriendlyFire(ItemStack stack, boolean b) {
        stack.getOrCreateTag().putBoolean("FriendlyFire", b);
    }

    @Override
    public boolean overrideOtherStackedOnMe(@NotNull ItemStack stack, ItemStack otherStack, @NotNull Slot slot, @NotNull ClickAction action, @NotNull Player player, @NotNull SlotAccess access) {
        if (otherStack.isEmpty() && Screen.hasAltDown()) {
            setFriendlyFire(stack, !getFriendlyFire(stack));
            player.playSound(SoundEvents.ENDER_EYE_DEATH);
            return true;
        }
        return super.overrideOtherStackedOnMe(stack, otherStack, slot, action, player, access);
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
    public boolean canBeHurtBy(DamageSource pDamageSource) {
        return pDamageSource.is(DamageTypeTags.BYPASSES_INVULNERABILITY);
    }

    @Override
    public int getBarWidth(@NotNull ItemStack pStack) {
        return Math.round(13.0F - (float) (getMaxCharge(pStack) - getCharge(pStack)) * 13.0F / (float) getMaxCharge(pStack));
    }

    @Override
    public int getBarColor(@NotNull ItemStack pStack) {
        return 7924965;
    }

    @Override
    public boolean isBarVisible(@NotNull ItemStack pStack) {
        return getCharge(pStack) > 0;
    }

    /// Crossbow Expansion code
    private static void createBoom(Level level, LivingEntity livingEntity, ItemStack stack) {
        int i = EnchantmentHelper.getTagEnchantmentLevel(ModEnchantments.CONDENSED_BURST.get(), stack);
        float range = i > 0 ? BOOM_RANGE : BURST_RANGE;
        float radius = i > 0 ? 0.5F : 2F;
        float creativeModeCharge = enchanted(stack, 1) ? 0.6F : 1F;
        float chargeAmount = ((Player)livingEntity).isCreative() && !enchanted(stack, 0) ? creativeModeCharge : getChargeAmount(stack);
        SimpleParticleType particleTypes = i > 0 ? ParticleTypes.SONIC_BOOM : ModParticleTypes.SONIC_BURST_EMITTER.get();
        Vec3 target = livingEntity.getEyePosition().add(Vec3.directionFromRotation(livingEntity.getXRot(), livingEntity.yHeadRot).scale(range));
        Vec3 source = livingEntity.getEyePosition();
        Vec3 offsetToTarget = target.subtract(source);
        Vec3 normalized = offsetToTarget.normalize();
        boolean firstTick = true;
        Set<Entity> hitSet = new HashSet<>();

        for(int particleIndex = 1; particleIndex < Mth.floor(offsetToTarget.length()) + 2; ++particleIndex) {
            Vec3 particlePos = source.add(normalized.scale(particleIndex));
            if (firstTick) {
                ((ServerLevel)level).sendParticles(ParticleTypes.SONIC_BOOM, particlePos.x, particlePos.y, particlePos.z, 0, 0.0F, 0.0F, 0.0F, 0.0F);
                firstTick = false;
            }
            ((ServerLevel)level).sendParticles(particleTypes, particlePos.x, particlePos.y, particlePos.z, 0, 0.0F, 0.0F, 0.0F, 0.0F);
            hitSet.addAll(level.getEntitiesOfClass(LivingEntity.class, (new AABB(new BlockPos((int)particlePos.x(), (int)particlePos.y(), (int)particlePos.z()))).inflate(radius),
                    (e) -> !(e instanceof OwnableEntity) && (e.isAlliedTo(livingEntity) && getFriendlyFire(stack) || !e.isAlliedTo(livingEntity)) ||
                            (e instanceof OwnableEntity && (((OwnableEntity)e).getOwner() != null && getFriendlyFire(stack))) ||
                            (e instanceof OwnableEntity && (((OwnableEntity)e).getOwner() == null)) ||
                            (e instanceof OwnableEntity && (((OwnableEntity)e).getOwner() != null)
                                    && (((OwnableEntity)e).getOwner().isAlliedTo(livingEntity) && getFriendlyFire(stack)))
            ));
        }
        applyRecoil(livingEntity, chargeAmount);
        hitSet.remove(livingEntity);

        for(Entity hitTarget : hitSet) {
            if (hitTarget instanceof LivingEntity living) {
                if (livingEntity instanceof Player player) {
                    living.setLastHurtByPlayer(player);
                }
                living.hurt(livingEntity.damageSources().sonicBoom(livingEntity), (float) ItemConfig.diarkriteChargeBladeSonicDamage * chargeAmount);
                living.knockback(chargeAmount, Mth.sin(livingEntity.getYRot() * ((float)Math.PI / 180F)), -Mth.cos(livingEntity.getYRot() * ((float)Math.PI / 180F)));
            }
        }

        if (livingEntity instanceof ServerPlayer serverPlayer) {
            serverPlayer.awardStat(Stats.ITEM_USED.get(stack.getItem()));
        }
        setCharge(stack, getCharge(stack) - getCharge(stack));
    }

    /// ArcheryExpansion code: BowItemMixin
    private static void applyRecoil(LivingEntity user, double amount) {
        Vec3 lookDirection = user.getViewVector(1.0f);
        Vec3 knockback = lookDirection.multiply(-amount, -amount, -amount);

        user.setDeltaMovement(
                user.getDeltaMovement().x + knockback.x,
                user.getDeltaMovement().y + knockback.y,
                user.getDeltaMovement().z + knockback.z
        );
        user.hurtMarked = true;
    }
}
