package net.nokunami.elementus.mixin;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.nokunami.elementus.common.config.CatalystArmorConfig;
import net.nokunami.elementus.common.item.CatalystArmorItem;
import net.nokunami.elementus.common.registry.ModItems;
import net.nokunami.elementus.common.registry.ModMobEffects.ElementusEffects;
import net.nokunami.elementus.common.registry.ModSoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;

import static net.nokunami.elementus.common.item.CatalystItemUtil.*;

@SuppressWarnings("ConstantConditions")
@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    public LivingEntityMixin(EntityType<?> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Shadow @Nullable public abstract MobEffectInstance getEffect(MobEffect pEffect);

    @Shadow public abstract boolean hasEffect(MobEffect pEffect);

    @Shadow public abstract ItemStack getUseItem();

    @Shadow public abstract void setHealth(float pHealth);

    @Shadow public abstract boolean removeAllEffects();

    @Shadow public abstract boolean addEffect(MobEffectInstance pEffectInstance);

    @Shadow public abstract ItemStack getItemBySlot(EquipmentSlot pSlot);

    @Inject(method = "checkTotemDeathProtection", at = @At(value = "RETURN"), cancellable = true)
    public void catalystTotem(DamageSource damageSource, CallbackInfoReturnable<Boolean> cir) {
        Entity entity = this;
        if (!damageSource.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
//            Elementus.LOGGER.debug("passed check for damage");
            if ((Object) this instanceof LivingEntity livingEntity) {
                ItemStack chestplateItem = livingEntity.getItemBySlot(EquipmentSlot.CHEST);
//                Elementus.LOGGER.debug("passed check for livingEntity");
                if (chestplateItem.is(ModItems.ElementusItems.CATALYST_CHESTPLATE.get()) && CatalystArmorItem.catalystActivator(chestplateItem).equals(totem) &&
                        !livingEntity.hasEffect(ElementusEffects.TOTEM_COOLDOWN.get()))  {
//                    Elementus.LOGGER.debug("passed check for chestplate");
                    if (entity instanceof ServerPlayer serverplayer) {
                        serverplayer.awardStat(Stats.ITEM_USED.get(Items.TOTEM_OF_UNDYING), 1);
                        CriteriaTriggers.USED_TOTEM.trigger(serverplayer, chestplateItem);
                    }

                    this.setHealth(1.0F);
                    this.removeAllEffects();
                    this.addEffect(new MobEffectInstance(MobEffects.REGENERATION, CatalystArmorConfig.totem_RegenDuration, CatalystArmorConfig.totem_RegenAmp));
                    this.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, CatalystArmorConfig.totem_AbsorbDuration, CatalystArmorConfig.totem_AbsorbAmp));
                    this.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, CatalystArmorConfig.totem_FireResDuration, CatalystArmorConfig.totem_FireResAmp));
                    if (CatalystArmorConfig.totem_Cooldown != 0) {
                        this.addEffect(new MobEffectInstance(ElementusEffects.TOTEM_COOLDOWN.get(), CatalystArmorConfig.totem_Cooldown));
                    }
                    livingEntity.level().broadcastEntityEvent(this, (byte)35);
                    cir.setReturnValue(true);
                }
            }
        }
    }

    @Inject(method = "getJumpBoostPower", at = @At(value = "RETURN"), cancellable = true)
    private void beaconPowerHaste(CallbackInfoReturnable<Float> cir) {
        float jumpBoost = cir.getReturnValue();
        if (this.hasEffect(ElementusEffects.BEACON_POWER.get())) {
            cir.setReturnValue(0.1F * ((float)this.getEffect(ElementusEffects.BEACON_POWER.get()).getAmplifier() + 1.0F));
        } else if (this.hasEffect(ElementusEffects.WITHERED_BEACON_POWER.get())) {
            cir.setReturnValue(0.1F * ((float)this.getEffect(ElementusEffects.WITHERED_BEACON_POWER.get()).getAmplifier() + 1.0F));
        } else {
            cir.setReturnValue(jumpBoost);
        }
    }

    ///  Credits: Team Abode's Guarding Mod
    @Inject(method = "hurt", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;broadcastEntityEvent(Lnet/minecraft/world/entity/Entity;B)V", shift = At.Shift.BEFORE))
    private void playBlockSound(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (this.getUseItem().is(ModItems.ElementusItems.DIARKRITE_SHIELD.get())) {
            LivingEntity livingEntity = LivingEntity.class.cast(this);
            livingEntity.level().playSound(null, livingEntity.blockPosition(), ModSoundEvents.DIARKRITE_SHIELD_BLOCK.get(), SoundSource.PLAYERS, 0.5f, 1.0f * livingEntity.getRandom().nextFloat() * 0.25F);
            cir.cancel();
        }
        if (this.getUseItem().is(ModItems.ElementusItems.ANTHEKTITE_SHIELD.get())) {
            LivingEntity livingEntity = LivingEntity.class.cast(this);
            livingEntity.level().playSound(null, livingEntity.blockPosition(), ModSoundEvents.DIARKRITE_SHIELD_BLOCK.get(), SoundSource.PLAYERS, 0.45f, 1.5f * livingEntity.getRandom().nextFloat() * 0.25F);
//            livingEntity.level().playSound(null, livingEntity.blockPosition(), ModSounds.DIARKRITE_SHIELD_BLOCK.get(), SoundSource.PLAYERS, 0.75f, 1.25f * livingEntity.getRandom().nextFloat());
//            livingEntity.level().playSound(null, livingEntity.blockPosition(), SoundEvents.ZOMBIE_ATTACK_IRON_DOOR, SoundSource.PLAYERS, 0.1f, 0.5f);
//            livingEntity.level().playSound(null, livingEntity.blockPosition(), SoundEvents.ANVIL_PLACE, SoundSource.PLAYERS, 0.1f, 1.5f);
//            livingEntity.level().playSound(null, livingEntity.blockPosition(), SoundEvents.LANTERN_BREAK, SoundSource.PLAYERS, 1.0f, 0.0f);
//            livingEntity.level().playSound(null, livingEntity.blockPosition(), SoundEvents.SHIELD_BLOCK, SoundSource.PLAYERS, 0.5f, 0.8f);
            cir.cancel();
        }
    }
}