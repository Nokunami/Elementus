package nokunami.elementus.mixin;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
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
import nokunami.elementus.common.config.CatalystArmorConfig;
import nokunami.elementus.common.item.CatalystArmorItem;
import nokunami.elementus.common.item.DiarkriteBootsItem;
import nokunami.elementus.common.registry.ModItems;
import nokunami.elementus.common.registry.ModMobEffects.ElementusEffects;
import nokunami.elementus.common.registry.ModSoundEvents;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;

import static nokunami.elementus.common.item.CatalystItemUtil.totem;

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

    @Shadow public abstract @NotNull Iterable<ItemStack> getArmorSlots();

    @Inject(method = "checkTotemDeathProtection", at = @At("RETURN"), cancellable = true)
    public void Elementus$catalystTotem(DamageSource damageSource, CallbackInfoReturnable<Boolean> cir) {
        Entity entity = this;
        if (!damageSource.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
            if ((Object) this instanceof LivingEntity livingEntity) {
                ItemStack chestplateItem = livingEntity.getItemBySlot(EquipmentSlot.CHEST);
                if (chestplateItem.is(ModItems.ElementusItems.CATALYST_CHESTPLATE.get()) && CatalystArmorItem.catalystActivator(chestplateItem).equals(totem) &&
                        !livingEntity.hasEffect(ElementusEffects.TOTEM_COOLDOWN.get()))  {
                    if (entity instanceof ServerPlayer serverplayer) {
                        serverplayer.awardStat(Stats.ITEM_USED.get(Items.TOTEM_OF_UNDYING), 1);
                        CriteriaTriggers.USED_TOTEM.trigger(serverplayer, chestplateItem);
                    }
//                    if (entity instanceof Player player) {
//                        player.getCooldowns().addCooldown(chestplateItem.getItem(), 200);
//                    }

                    this.setHealth(1.0F);
                    this.removeAllEffects();
                    this.addEffect(new MobEffectInstance(MobEffects.REGENERATION, CatalystArmorConfig.totem_RegenDuration, CatalystArmorConfig.totem_RegenAmp));
                    this.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, CatalystArmorConfig.totem_AbsorbDuration, CatalystArmorConfig.totem_AbsorbAmp));
                    this.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, CatalystArmorConfig.totem_FireResDuration, CatalystArmorConfig.totem_FireResAmp));
                    if (CatalystArmorConfig.totem_Cooldown > 0) {
                        this.addEffect(new MobEffectInstance(ElementusEffects.TOTEM_COOLDOWN.get(), CatalystArmorConfig.totem_Cooldown, 0));
                    }
                    livingEntity.level().broadcastEntityEvent(this, (byte)35);
                    cir.setReturnValue(true);
                }
            }
        }
    }

    @Inject(method = "calculateFallDamage", at = @At("RETURN"), cancellable = true)
    private void Elementus$calculateFallDamage(float fallDistance, float damageMultiplier, CallbackInfoReturnable<Integer> cir) {
        MobEffectInstance beaconPower = this.getEffect(ElementusEffects.BEACON_POWER.get());
        MobEffectInstance witheredBeaconPower = this.getEffect(ElementusEffects.WITHERED_BEACON_POWER.get());
        float f0 = beaconPower != null ? beaconPower.getAmplifier() + 1 : 0;
        float f1 = witheredBeaconPower != null ? witheredBeaconPower.getAmplifier() + 1 : 0;

        float t2 = 0;

        if (beaconPower != null) {
            t2 = beaconPower.getAmplifier() + 1;
        }

        if (beaconPower != null || witheredBeaconPower != null) {
            cir.setReturnValue((int) (Mth.ceil(fallDistance - 3.0F - Math.max(f0, f1)) * damageMultiplier));
        }
    }

    @Inject(method = "getJumpBoostPower", at = @At("RETURN"), cancellable = true)
    private void Elementus$getJumpBoostPower(CallbackInfoReturnable<Float> cir) {
        if ((Object) this instanceof LivingEntity livingEntity) {
            if (DiarkriteBootsItem.SculkWalkerActivation(livingEntity)) {
                cir.setReturnValue(-1F);
            }
        }
        float ori = cir.getReturnValue();
        MobEffectInstance beaconPower = this.getEffect(ElementusEffects.BEACON_POWER.get());
        MobEffectInstance witheredBeaconPower = this.getEffect(ElementusEffects.WITHERED_BEACON_POWER.get());
        float f0 = beaconPower != null ? beaconPower.getAmplifier() + 1 : ori;
        float f1 = witheredBeaconPower != null ? witheredBeaconPower.getAmplifier() + 1 : ori;

        if (beaconPower != null || witheredBeaconPower != null) {
            cir.setReturnValue(0.1F * Math.max(f0, f1));
        }
    }

    ///  Credits: Team Abode's Guarding Mod
    @Inject(method = "hurt", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;broadcastEntityEvent(Lnet/minecraft/world/entity/Entity;B)V"), cancellable = true)
    private void Elementus$playBlockSound(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (this.getUseItem().is(ModItems.ElementusItems.DIARKRITE_SHIELD.get())) {
            LivingEntity livingEntity = LivingEntity.class.cast(this);
            livingEntity.level().playSound(null, livingEntity.blockPosition(), ModSoundEvents.DIARKRITE_SHIELD_BLOCK.get(), SoundSource.PLAYERS, 1.0F, 0.8F + this.level().random.nextFloat() * 0.4F);
            cir.cancel();
        }
        if (this.getUseItem().is(ModItems.ElementusItems.ANTHEKTITE_SHIELD.get())) {
            LivingEntity livingEntity = LivingEntity.class.cast(this);
            livingEntity.level().playSound(null, livingEntity.blockPosition(), ModSoundEvents.ANTHEKTITE_SHIELD_BLOCK.get(), SoundSource.PLAYERS, 1.0F, 0.8F + this.level().random.nextFloat() * 0.4F);
            cir.cancel();
        }
    }

    @Inject(method = "isPushable", at = @At("RETURN"), cancellable = true)
    private void Elementus$isPushable(CallbackInfoReturnable<Boolean> cir) {
        if ((Object) this instanceof LivingEntity livingEntity) {
            if (DiarkriteBootsItem.SculkWalkerActivation(livingEntity)) {
                cir.setReturnValue(false);
            }
        }
    }
}