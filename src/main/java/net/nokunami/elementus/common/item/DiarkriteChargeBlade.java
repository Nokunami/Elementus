package net.nokunami.elementus.common.item;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.nokunami.elementus.common.config.UniqueItemConfig;
import net.nokunami.elementus.common.registry.ModParticleTypes;
import net.nokunami.elementus.common.registry.ModTiers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import static net.nokunami.elementus.common.config.UniqueItemConfig.*;
import static net.nokunami.elementus.common.registry.ModEnchantments.*;

public class DiarkriteChargeBlade extends ChargeSwordItem {
    private static final int BURST_RANGE = 3;
    private static final int BOOM_RANGE = 16;
    private static final int RUSH_RANGE = 8;

    public DiarkriteChargeBlade() {
        super(ModTiers.DIARKRITE, diarkriteChargeBladeDamage, (float) diarkriteChargeBladeAttackSpeed, (float) diarkriteChargeBladeAttackReach, new Properties().fireResistant().rarity(Rarity.EPIC));
    }

    @Override
    public boolean isFoil(@NotNull ItemStack pStack) {
        boolean i = EnchantmentHelper.getTagEnchantmentLevel(SACRIFICE_CURSE.get(), pStack) > 0;
        return super.isFoil(pStack) || !i && pStack.isEnchanted();
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        Minecraft mc = Minecraft.getInstance();
        assert mc.player != null;
        boolean creative = mc.player.isCreative();
        String damageModifierText = isEnchantedWith(stack, SACRIFICE_CURSE) ? " (+125%)" : "";
        ChatFormatting damageModifierColor = isEnchantedWith(stack, SACRIFICE_CURSE) || isEnchantedWith(stack, CONDENSED_BURST) ? ChatFormatting.GOLD : ChatFormatting.DARK_AQUA;
        String friendlyFireStr = String.valueOf(getFriendlyFire(stack));
        double damage = getChargeAmount(stack, false) * diarkriteChargeBladeSonicDamage;
        double damageR = getChargeAmount(stack, creative) * diarkriteChargeBladeSonicDamage;
        double damageNumber = Math.round(damage * 10.0) / 10.0;
        double damageNumberR = Math.round(damageR * 10.0) / 10.0;
        Component damageText = Component.translatable(String.valueOf(damageNumber)).withStyle(ChatFormatting.DARK_AQUA).append(creative ? " (" + damageNumberR + ")" : "");

        super.appendHoverText(stack, level, tooltip, flag);
        if (getResonanceCharge(stack) > 0) tooltip.add(Component.translatable(getDescriptionId() + ".resonance_charge_desc", getResonanceCharge(stack)).withStyle(ChatFormatting.DARK_GRAY));
        tooltip.add(Component.translatable(getDescriptionId() + ".damage_desc").withStyle(ChatFormatting.GRAY)
                .append(damageText).append(Component.translatable(damageModifierText).withStyle((damageModifierColor))));
        if (Screen.hasShiftDown()) {
            if (isEnchantedWith(stack, SACRIFICE_CURSE)) {
                if (!(diarkriteChargeBladeSacrificeDamageBonus <= 0))
                    tooltip.add(Component.literal("|").withStyle(ChatFormatting.GRAY).append(CommonComponents.space()
                            .append(Component.translatable(getDescriptionId() + ".damage_bonus_sacrifice", Math.round((diarkriteChargeBladeSacrificeDamageBonus + 1) * 100))
                                    .append(Component.translatable("enchantment.elementus.sacrifice_curse")).withStyle(ChatFormatting.YELLOW))));
                if (!(diarkriteChargeBladeSelfSacrificeDamage <= 0))
                    tooltip.add(Component.literal("|").withStyle(ChatFormatting.GRAY).append(CommonComponents.space()
                            .append(Component.translatable(getDescriptionId() + ".self_sacrifice_damage", Math.round(diarkriteChargeBladeSelfSacrificeDamage * 100))
                                    .append(Component.translatable("enchantment.elementus.sacrifice_curse")).withStyle(ChatFormatting.RED))));
            }
            if (isEnchantedWith(stack, CONDENSED_BURST)) tooltip.add(Component.literal("|").withStyle(ChatFormatting.GRAY).append(CommonComponents.space()
                    .append(Component.translatable(getDescriptionId() + ".charge_penalty_condensed_burst", Math.round((((float) diarkriteChargeBladeChargePenalty / diarkriteChargeBladeBaseCharge) - 1) * 100))
                            .append(Component.translatable("enchantment.elementus.condensed_burst")).withStyle(ChatFormatting.RED))));
            if (isEnchantedWith(stack, MULTI_CHARGE)) tooltip.add(Component.literal("|").withStyle(ChatFormatting.GRAY).append(CommonComponents.space()
                    .append(Component.translatable(getDescriptionId() + ".multi_charge", Math.round(((float) (getMaxCharge(stack) /getChargeStack(stack)) - 1)) * 100)
                            .append(Component.translatable("enchantment.elementus.multi_charge")).withStyle(ChatFormatting.GREEN))));
        }
        tooltip.add(Component.translatable("item.elementus.charge_item.friendly_fire_desc",
                friendlyFireStr.substring(0, 1).toUpperCase(Locale.ROOT) + friendlyFireStr.substring(1)).withStyle(ChatFormatting.GRAY));
        if (stack.isEnchanted()) tooltip.add(CommonComponents.EMPTY);
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



    @Override
    public int getBarColor(@NotNull ItemStack stack) {
        return isEnchantedWith(stack, SACRIFICE_CURSE) ? 16733525 : 7924965;
    }

    @Override
    public boolean isBarVisible(@NotNull ItemStack pStack) {
        return !isEnchantedWith(pStack, MULTI_CHARGE) && super.isBarVisible(pStack);
    }

    public boolean isMultiBarVisible(@NotNull ItemStack pStack) {
        return isEnchantedWith(pStack, MULTI_CHARGE);
    }

    @Override
    public int getBarWidth(@NotNull ItemStack stack) {
        int level = EnchantmentHelper.getTagEnchantmentLevel(MULTI_CHARGE.get(), stack);
        return Math.round(13.0F - (float) (getMaxCharge(stack) - getCharge(stack)) * 13.0F / (float) getMaxCharge(stack));
    }

    @Override
    public boolean canParry() {
        return true;
    }

    public static float boomRadius(ItemStack stack) {
        return isEnchantedWith(stack, RUSH) || isEnchantedWith(stack, CONDENSED_BURST) ? 1.25F : 2.5F;
    }

    public static float boomRange(ItemStack stack) {
        return isEnchantedWith(stack, CONDENSED_BURST) ? BOOM_RANGE :  isEnchantedWith(stack, RUSH) ? RUSH_RANGE : BURST_RANGE;
    }

    /// Crossbow Expansion code
    public static void createBoom(Level level, LivingEntity livingEntity, ItemStack stack) {
        float chargeAmount = getChargeAmount(stack, ((Player)livingEntity).isCreative());
        boolean isCursed = isEnchantedWith(stack, SACRIFICE_CURSE);
        boolean isCondensed = isEnchantedWith(stack, CONDENSED_BURST);
        SimpleParticleType particleTypes = isCursed && !isCondensed ? ModParticleTypes.SACRIFICE_SONIC_BURST_EMITTER.get() : isCursed ? ModParticleTypes.SACRIFICE_SONIC_BOOM.get() : isCondensed ? ParticleTypes.SONIC_BOOM : ModParticleTypes.SONIC_BURST_EMITTER.get();
        SimpleParticleType startParticle = isCursed ? ModParticleTypes.SACRIFICE_SONIC_BOOM_START.get() : ModParticleTypes.SONIC_BOOM_START.get();
        Vec3 target = livingEntity.getEyePosition().add(Vec3.directionFromRotation(livingEntity.getXRot(), livingEntity.yHeadRot).scale(boomRange(stack)));
        Vec3 source = livingEntity.getEyePosition();
        Vec3 offsetToTarget = target.subtract(source);
        Vec3 normalized = offsetToTarget.normalize();
        boolean firstTick = true;
        Set<Entity> hitSet = new HashSet<>();

        for(int particleIndex = 2; particleIndex < Mth.floor(offsetToTarget.length()) + 2; ++particleIndex) {
            Vec3 particle = source.add(normalized.scale(particleIndex));
            if (firstTick) {
                ((ServerLevel)level).sendParticles(startParticle, particle.x, particle.y, particle.z, 0, 0.0F, 0.0F, 0.0F, 0.0F);
                firstTick = false;
            }
            if (particleIndex > 2) ((ServerLevel)level).sendParticles(particleTypes, particle.x, particle.y, particle.z, 0, 0.0F, 0.0F, 0.0F, 0.0F);
            hitSet.addAll(level.getEntitiesOfClass(LivingEntity.class, (new AABB(new BlockPos((int) particle.x(), (int) particle.y(), (int) particle.z()))).inflate(boomRadius(stack)),
                    (e) -> !(e instanceof OwnableEntity) && (e.isAlliedTo(livingEntity) && getFriendlyFire(stack) || !e.isAlliedTo(livingEntity)) ||
                            (e instanceof OwnableEntity ownable && ((ownable.getOwner() != null &&
                                    (ownable.getOwner().is(livingEntity) || ownable.getOwner().isAlliedTo(livingEntity)) && getFriendlyFire(stack)) ||
                                    ownable.getOwner() == null))
            ));
        }
        hitSet.remove(livingEntity);
        if (!livingEntity.onGround() || isEnchantedWith(stack, RUSH))
            applyRecoil(livingEntity, livingEntity, chargeAmount * (isEnchantedWith(stack, RUSH) ? 2 : 1), isEnchantedWith(stack, RUSH) ? 1 : 0);

        for(Entity hitTarget : hitSet) {
            if (hitTarget instanceof LivingEntity living) {
                if (livingEntity instanceof Player player) {
                    living.setLastHurtByPlayer(player);
                }
                living.hurt(livingEntity.damageSources().sonicBoom(livingEntity), (float) UniqueItemConfig.diarkriteChargeBladeSonicDamage * chargeAmount);
                applyRecoil(living, livingEntity, chargeAmount, 1);
            }
        }

        if (livingEntity instanceof ServerPlayer serverPlayer) {
            serverPlayer.awardStat(Stats.ITEM_USED.get(stack.getItem()));
        }
        setCharge(stack, -Math.min(getCharge(stack), getChargeStack(stack)));
        level.playSound(null, livingEntity, SoundEvents.WARDEN_SONIC_BOOM, SoundSource.PLAYERS, 1.0F, 1.0F);
        if (isEnchantedWith(stack, CONDENSED_BURST)) level.playSound(null, livingEntity, SoundEvents.TRIDENT_THUNDER, SoundSource.PLAYERS, 1.0F, 1.0F);
    }

    public static void parryParticle(Level level, LivingEntity livingEntity, ParticleOptions particleOptions) {
        Vec3 eyePos = livingEntity.getEyePosition();
        Vec3 target = eyePos.add(Vec3.directionFromRotation(livingEntity.getXRot(), livingEntity.yHeadRot).scale(1));
        Vec3 offsetToTarget = target.subtract(eyePos);

        Vec3 particle = eyePos.add(offsetToTarget.normalize().scale(1));
        ((ServerLevel)level).sendParticles(particleOptions, particle.x, particle.y, particle.z, 0, 0.0F, 0.0F, 0.0F, 0.0F);
    }

    /// ArcheryExpansion code: BowItemMixin
    public static void applyRecoil(Entity target,Entity source, double amount, int type) {
        Vec3 lookDirection = source.getViewVector(1.0f);
        double factor;
        if (type == 1) {
            factor = amount;
        } else factor = -amount;
        Vec3 knockback = lookDirection.multiply(factor, factor, factor);

        target.setDeltaMovement(
                source.getDeltaMovement().x + knockback.x,
                source.getDeltaMovement().y + knockback.y,
                source.getDeltaMovement().z + knockback.z
        );
        target.hurtMarked = true;
    }

    public static void parry(Level level, Player imTheOneWhoParries, LivingEntity waltuhPutTheSwordDownWaltuh, float damageAmount) {
        waltuhPutTheSwordDownWaltuh.hurt(level.damageSources().playerAttack(imTheOneWhoParries), damageAmount);
        waltuhPutTheSwordDownWaltuh.setLastHurtByPlayer(imTheOneWhoParries);
    }
}
