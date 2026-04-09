package net.nokunami.elementus.common.item.unique;

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
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.nokunami.elementus.Elementus;
import net.nokunami.elementus.common.config.UniqueItemConfig;
import net.nokunami.elementus.common.entity.KnockbackUtil;
import net.nokunami.elementus.common.entity.MobUtil;
import net.nokunami.elementus.common.entity.projectile.PulseBurstEntity;
import net.nokunami.elementus.common.item.enchantment.IChargeBladeEnchantment;
import net.nokunami.elementus.common.registry.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

import static net.nokunami.elementus.common.config.UniqueItemConfig.*;
import static net.nokunami.elementus.common.entity.MobUtil.blockVec;
import static net.nokunami.elementus.common.item.EItemUtil.*;
import static net.nokunami.elementus.common.registry.EEnchantments.*;
import static net.nokunami.elementus.common.registry.EParticles.PARRY;
import static net.nokunami.elementus.common.registry.EParticles.PARRY_RESONANCE;
import static net.nokunami.elementus.common.registry.ESounds.*;
import static net.nokunami.elementus.event.ServerEvents.parryWindow;

public class BladeOfResonance extends ChargeBladeItem {
    private static final int BURST_RANGE = 3;
    private static final int BOOM_RANGE = 20;
    private static final int RUSH_RANGE = 8;

    public BladeOfResonance() {
        super(ETier.EnumTiers.DIARKRITE, diarkriteChargeBladeDamage, (float) diarkriteChargeBladeAttackSpeed, (float) diarkriteChargeBladeAttackReach, new Properties().fireResistant().rarity(Rarity.EPIC));
    }

    @Override
    public boolean isFoil(@NotNull ItemStack stack) {
        boolean i = EnchantmentHelper.getTagEnchantmentLevel(SACRIFICE_CURSE.get(), stack) > 0;
        Map<Enchantment, Integer> Ench = EnchantmentHelper.getEnchantments(stack);
        if (Ench.size() == 1 && Ench.containsKey(SACRIFICE_CURSE.get())) return false;
        return super.isFoil(stack) || !i && stack.isEnchanted();
    }

    @Override public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        Minecraft mc = Minecraft.getInstance();
        assert mc.player != null;
        boolean creative = mc.player.isCreative();
        // TODO: make this dynamic.
        String damageModifierText = sacrifice(stack) ? " (+125%)" : "";
        ChatFormatting damageModifierColor = sacrifice(stack) || enchantedWith(stack, CONDENSED_BURST) ? ChatFormatting.GOLD : ChatFormatting.DARK_AQUA;
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
            for (Enchantment enchantment : EnchantmentHelper.getEnchantments(stack).keySet()) {
                if (enchantment instanceof IChargeBladeEnchantment bladeEnch) {
                    bladeEnch.getDescription(tooltip, stack);
                }
            }
        }
        friendlyFireTooltip(tooltip, stack);
        if (stack.isEnchanted()) tooltip.add(CommonComponents.EMPTY);
    }

    @Override public int getChargeStack(ItemStack stack) {
        return enchantedWith(stack, CONDENSED_BURST) ? diarkriteChargeBladeChargePenalty : diarkriteChargeBladeBaseCharge;
    }

    @Override public int getBarColor(@NotNull ItemStack stack) {
        return sacrifice(stack) ? 16733525 : 7924965;
    }
    @Override public boolean canDisableShield(ItemStack stack, ItemStack shield, LivingEntity entity, LivingEntity attacker) {
        return getChargedState(stack);
    }

    @Override public void blockDirectEvent(LivingHurtEvent event, ItemStack stack) {
        DamageSource damageSource = event.getSource();
        LivingEntity eventEntity = event.getEntity();
        Vec3 position = eventEntity.position();
        Vec3 viewVec = eventEntity.getViewVector(1);
        Vec3 vec32 = damageSource.getSourcePosition();
        Entity directAttacker = damageSource.getDirectEntity();
        Entity attacker = damageSource.getEntity();
        boolean usingItem = eventEntity.isUsingItem();
        Level level = eventEntity.level();
        float damage = event.getAmount();
        float randomFloat = 0.5F + (eventEntity.getRandom().nextFloat() - eventEntity.getRandom().nextFloat()) * 0.5F;

        int parryWindow = 6;

        boolean flag = directAttacker instanceof AbstractArrow abstractarrow && abstractarrow.getPierceLevel() > 0;
        if (blockVec(vec32, position, viewVec)) {

            boolean parry = eventEntity.getTicksUsingItem() <= parryWindow;
            boolean perfectParry = eventEntity.getTicksUsingItem() <= parryWindow / 2;

            if (!stack.isEmpty() && stack.getItem() instanceof ChargeBladeItem && usingItem) {
                float soundVol = 1;
                Supplier<SoundEvent> soundEvent = enchantedWith(stack, RESONANCE) ? BOR_BLOCK_RESONANCE : CHARGE_BLADE_BLOCK;
                float parryAmount = 0;
                float knockback = 0;
                if (parry) {
                    if (perfectParry) {
                        soundEvent = enchantedWith(stack, RESONANCE) ? () -> SoundEvents.EXPERIENCE_ORB_PICKUP : () -> SoundEvents.EXPERIENCE_ORB_PICKUP;
                        parryParticle(level, eventEntity, enchantedWith(stack, RESONANCE) ? PARRY_RESONANCE.get() : PARRY.get());
                        parryAmount = event.getAmount();
                        setCharge(stack, 2);
                        soundVol = 2F;
                        knockback = 4;
                    } else {
                        soundEvent = enchantedWith(stack, RESONANCE) ? BOR_PARRY_RESONANCE : CHARGE_BLADE_PARRY;
                        parryParticle(level, eventEntity, enchantedWith(stack, RESONANCE) ? PARRY_RESONANCE.get() : PARRY.get());
                        parryAmount = event.getAmount() / 2;
                        setCharge(stack, 1);
                        soundVol = 1.25F;
                        knockback = 2;
                    }
                    playerStuff(event);
                    event.setCanceled(true);
                } else if (!damageSource.is(DamageTypeTags.BYPASSES_SHIELD) && !flag) {
                    blockAction(event, false, stack, damage - (damage * damageAbsorption(stack)), Math.max(1, event.getAmount() / 8));
                    knockback = 1;
                } else if (stack.getItem() instanceof BladeOfResonance && damageSource.is(DamageTypes.SONIC_BOOM)) {
                    soundEvent = BOR_SONIC_RESONANCE;
                    blockAction(event, false, stack, event.getAmount() * 0.8F, event.getAmount() / 2);
                }
                level.playSound(null, eventEntity, soundEvent.get(), SoundSource.PLAYERS, soundVol, randomFloat);
                setResonanceCharge(stack, 1);
                MobUtil.applyRecoil(directAttacker, eventEntity, knockback, true);

                if ((directAttacker instanceof LivingEntity living && parry))
                    parry(level, (Player) eventEntity, living, parryAmount);

                if (attacker instanceof LivingEntity living && ((enchantedWith(stack, RESONANCE) && parry)))
                    parry(level, (Player) eventEntity, living, parryAmount);
            }
        }
    }

    private float damageAbsorption(ItemStack stack) {
        float i0 = Math.min(getCharge(stack), getMaxCharge(stack));
        float i1 = getMaxCharge(stack);
        return 1 - ((i0 / i1) * 0.5F);
    }

    private void blockAction(LivingHurtEvent event, boolean cancelEvent, ItemStack stack, float damage, double chargeAmount) {
        event.setCanceled(cancelEvent);
        event.setAmount(damage);
        setCharge(stack, (int) chargeAmount);
    }

    private void playerStuff(LivingHurtEvent event) {
        DamageSource damageSource = event.getSource();
        LivingEntity eventEntity = event.getEntity();
        Entity directEntity = damageSource.getDirectEntity();

        eventEntity.hurtDuration = 0;
        eventEntity.hurtTime = 0;
        eventEntity.hurtMarked = false;
        if (directEntity instanceof Projectile projectile) {
            // this is horrible
            if (projectile instanceof AbstractArrow ) eventEntity.setArrowCount(eventEntity.getArrowCount() - 1);
        }
        if (directEntity instanceof Bee) eventEntity.setStingerCount(eventEntity.getStingerCount() - 1);
    }

    public void parryParticle(Level level, LivingEntity livingEntity, ParticleOptions particleOptions) {
        Vec3 eyePos = livingEntity.getEyePosition();
        Vec3 target = eyePos.add(Vec3.directionFromRotation(livingEntity.getXRot(), livingEntity.yHeadRot).scale(1));
        Vec3 offsetToTarget = target.subtract(eyePos);

        Vec3 pos = eyePos.add(offsetToTarget.normalize().scale(1));
        ((ServerLevel)level).sendParticles(particleOptions, pos.x, pos.y, pos.z, 0, 0.0F, 0.0F, 0.0F, 0.0F);
    }

    public void parry(Level level, Player imTheOneWhoParries, LivingEntity waltuhPutTheSwordDownWaltuh, float damageAmount) {
        waltuhPutTheSwordDownWaltuh.hurt(level.damageSources().playerAttack(imTheOneWhoParries), damageAmount);
        waltuhPutTheSwordDownWaltuh.setLastHurtByPlayer(imTheOneWhoParries);
        waltuhPutTheSwordDownWaltuh.invulnerableTime = 0;
    }

    @Override public void blockProjectileEvent(ProjectileImpactEvent event, ItemStack stack, LivingEntity entity, Projectile projectile, Vec3 pDelta) {
        Level level = entity.level();
        float randomFloat = 0.5F + (entity.getRandom().nextFloat() - entity.getRandom().nextFloat()) * 0.5F;
        Vec3 position = entity.position();
        Vec3 viewVec = entity.getViewVector(1);
        Vec3 vec32 = projectile.position();


        Vec3 vec31 = vec32.vectorTo(position).normalize();
        vec31 = new Vec3(vec31.x, 0.0D, vec31.z);

        if (vec31.dot(viewVec) < 0.0D) {
            if (entity.isUsingItem() && entity.getTicksUsingItem() <= parryWindow && !enchantedWith(stack, RESONANCE)) {
                Elementus.LOGGER.debug("testHit2 this deflects \"i hope\"");
                event.setImpactResult(ProjectileImpactEvent.ImpactResult.SKIP_ENTITY);
                parryParticleAlt(level, entity, PARRY.get());
                level.playSound(null, entity, ESounds.CHARGE_BLADE_PARRY.get(), SoundSource.PLAYERS, 1, randomFloat);
                projectile.setDeltaMovement(-pDelta.x/2, -pDelta.y/2, -pDelta.z/2);
            }
        }
    }

    public void parryParticleAlt(Level level, LivingEntity livingEntity, ParticleOptions particleOptions) {
        Vec3 eyePos = livingEntity.getEyePosition();
        Vec3 target = eyePos.add(Vec3.directionFromRotation(livingEntity.getXRot(), livingEntity.yHeadRot).scale(1));
        Vec3 offsetToTarget = target.subtract(eyePos);

        Vec3 pos = eyePos.add(offsetToTarget.normalize().scale(1));
        level.addParticle(particleOptions, pos.x, pos.y, pos.z, 0, 0, 0);
    }

    @Override
    public ChargeBladeAbility castAbility(Player player, Level level, ItemStack stack, InteractionHand hand) {
        int cooldown = 0;
        boolean stopUsingItem = false;
        if (stack.getItem() instanceof BladeOfResonance && (getCharge(stack) > 2 || sacrifice(stack) || player.isCreative())) {
            createBoom(level, player, stack);
            if (sacrifice(stack)) {
                MobUtil.hurt(player, EDamageTypes.SACRIFICIAL, player, player.getMaxHealth() * (float) diarkriteChargeBladeSelfSacrificeDamage, 0);
                player.addEffect(new MobEffectInstance(EMobEffects.SACRIFICE_PENALTY.get(), 40));
            }
            cooldown = chargeStacking(stack) ? 5 : 10;
            stopUsingItem = true;
            player.swing(hand, true);
        }
        return new ChargeBladeAbility().setCooldown(cooldown).shouldStopUsingItem(stopUsingItem);
    }

    /// Crossbow Expansion code
    public void createBoom(Level level, LivingEntity attacker, ItemStack stack) {
        float chargeAmount = getChargeAmount(stack, ((Player) attacker).isCreative());
        SimpleParticleType beamParticle = sacrifice(stack) ? EParticles.SONIC_BOOM_SACRIFICE.get() : ParticleTypes.SONIC_BOOM;
        SimpleParticleType emitter = sacrifice(stack) ? EParticles.SONIC_BURST_SACRIFICE_EMITTER.get() : EParticles.SONIC_BURST_EMITTER.get();
        SimpleParticleType startParticle = sacrifice(stack) ? EParticles.INITIAL_BURST_SACRIFICE.get() : EParticles.INITIAL_BURST.get();
        double radius = rush(stack) ? 1.25 : 2.5;
        Vec3 target = attacker.getEyePosition().add(Vec3.directionFromRotation(attacker.getXRot(), attacker.yHeadRot).scale(boomRange(stack)));
        Vec3 source = attacker.getEyePosition();
        Vec3 offsetToTarget = target.subtract(source);
        Vec3 normalized = offsetToTarget.normalize();
        boolean firstTick = true;
        Set<Entity> hitSet = new HashSet<>();

        for(int particleIndex = 0; particleIndex < Mth.floor(offsetToTarget.length()) + 2; ++particleIndex) {
            Vec3 particle = source.add(normalized.scale(particleIndex));
            KnockbackUtil knockbackUtil = KnockbackUtil.knockbackUtil(level, attacker, particle.x, particle.y, particle.z, radius + (EnchantmentHelper.getEnchantmentLevel(Enchantments.KNOCKBACK, attacker) * 2));
            if (firstTick && particleIndex == 2) {
                ((ServerLevel) level).sendParticles(startParticle, particle.x, particle.y, particle.z, 0, 0, 0, 0, 0);
                ((ServerLevel) level).sendParticles(emitter, particle.x, particle.y, particle.z, 0, 0, 0, 0, 0);
                firstTick = false;
            }
            if (particleIndex > 2 && condensedBurst(stack)) ((ServerLevel) level).sendParticles(beamParticle, particle.x, particle.y, particle.z, 0, 0, 0, 0, 0);
            hitSet.addAll(level.getEntitiesOfClass(LivingEntity.class, new AABB(new BlockPos((int) particle.x, (int) particle.y, (int) particle.z))
                    .inflate(radius), (e) -> MobUtil.chargeBade(attacker, e, getFriendlyFire(stack) && e != attacker)
            ));
            knockbackUtil.knockback();
        }
        particles(attacker, sacrifice(stack) ? EParticles.SACRIFICE_SCULK_SOUL.get() : ParticleTypes.SCULK_SOUL, 24 * chargeAmount, 48 * chargeAmount);
        hitSet.remove(attacker);
        if (!attacker.onGround() || rush(stack)) MobUtil.applyRecoil(attacker, attacker, chargeAmount * (rush(stack) ? 2 : 1), rush(stack));
        if (!hitSet.isEmpty()) setCharge(stack, -Math.min(getCharge(stack), getChargeStack(stack)));
        for(Entity hitTarget : hitSet) {
            if (hitTarget instanceof LivingEntity living) {
                MobUtil.hurt(living, attacker.damageSources().sonicBoom(attacker), (float) UniqueItemConfig.diarkriteChargeBladeSonicDamage * chargeAmount, 0);
            }
        }

        if (burstPulse(stack) && !level.isClientSide) {
            PulseBurstEntity slash = new PulseBurstEntity(level, attacker);
            slash.emitPulse(attacker, 0.45F, 1, 24, sacrifice(stack) ? 10 : 5);
            level.addFreshEntity(slash);
        }

        if (attacker instanceof ServerPlayer serverPlayer) serverPlayer.awardStat(Stats.ITEM_USED.get(stack.getItem()));
        level.playSound(null, attacker, burstSound(stack), SoundSource.PLAYERS, condensedBurst(stack) ? 5 : 2.5F, 1);
    }

    public static float boomRange(ItemStack stack) {
        return enchantedWith(stack, CONDENSED_BURST) ? BOOM_RANGE :  enchantedWith(stack, RUSH) ? RUSH_RANGE : BURST_RANGE;
    }

    public void particles(LivingEntity entity, ParticleOptions particle, double minRange, double maxRange) {
        RandomSource random = entity.getRandom();
        for (int i = 0; i < Mth.randomBetween(random, (float) minRange, (float) maxRange); i++) {

            Vec3 eyePosition = entity.getEyePosition().add(Vec3.directionFromRotation(entity.getViewXRot(1), entity.getViewYRot(1)).scale(0.55));
            Vec3 viewVector = entity.getViewVector(1);
            Vec3 targetPosition = eyePosition.add(viewVector).add(0, 0, 0);

            Vec3 correctedDirection = targetPosition.subtract(eyePosition).normalize();

            // Apply random spread
            double spread = 1.25; // Adjust spread intensity
            double spreadX = (random.nextDouble() - 0.5) * spread;
            double spreadY = (random.nextDouble() - 0.5) * spread;
            double spreadZ = (random.nextDouble() - 0.5) * spread;

            Vec3 spreadVector = new Vec3(spreadX, spreadY, spreadZ);
            correctedDirection = correctedDirection.add(spreadVector).normalize().scale(1);

            ((ServerLevel) entity.level()).sendParticles(particle,
                    eyePosition.x, eyePosition.y, eyePosition.z,
                    0,
                    correctedDirection.x, correctedDirection.y, correctedDirection.z,
                    0.5);
        }
    }

    public SoundEvent burstSound(ItemStack stack) {
        boolean isCursed = sacrifice(stack);
        SoundEvent burstSound = isCursed ? ESounds.BOR_BURST_CURSED.get() : ESounds.BOR_BURST.get();
        SoundEvent condensedSound = isCursed ? ESounds.BOR_CONDENSED_BURST_CURSED.get() : ESounds.BOR_CONDENSED_BURST.get();
        return condensedBurst(stack) ? condensedSound : burstSound;
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ToolAction toolAction) {
        return ToolActions.DEFAULT_SHIELD_ACTIONS.contains(toolAction) || ToolActions.DEFAULT_SWORD_ACTIONS.contains(toolAction);
    }
}
