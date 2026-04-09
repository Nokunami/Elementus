//package net.nokunami.elementus.mixin;
//
//import net.minecraft.advancements.CriteriaTriggers;
//import net.minecraft.server.level.ServerPlayer;
//import net.minecraft.sounds.SoundSource;
//import net.minecraft.stats.Stats;
//import net.minecraft.tags.DamageTypeTags;
//import net.minecraft.util.Mth;
//import net.minecraft.world.damagesource.DamageSource;
//import net.minecraft.world.effect.MobEffect;
//import net.minecraft.world.effect.MobEffectInstance;
//import net.minecraft.world.effect.MobEffects;
//import net.minecraft.world.entity.Entity;
//import net.minecraft.world.entity.EntityType;
//import net.minecraft.world.entity.EquipmentSlot;
//import net.minecraft.world.entity.LivingEntity;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.item.Item;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.Items;
//import net.minecraft.world.level.Level;
//import net.minecraft.world.phys.AABB;
//import net.nokunami.elementus.client.color.item;
//import net.nokunami.elementus.common.config.catalystConfigs.CatalystArmorConfig;
//import net.nokunami.elementus.common.item.DiarkriteBootsItem;
//import net.nokunami.elementus.common.item.basic.EShieldItem;
//import net.nokunami.elementus.common.item.unique.CatalystArmorItem;
//import net.nokunami.elementus.common.item.unique.ChargeBladeItem;
//import net.nokunami.elementus.common.registry.EItems;
//import net.nokunami.elementus.common.registry.EMobEffects;
//import net.nokunami.elementus.common.registry.ESoundEvents;
//import org.jetbrains.annotations.NotNull;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Shadow;
//import org.spongepowered.asm.mixin.Unique;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
//
//import javax.annotation.Nullable;
//
//import java.util.List;
//
//import static net.nokunami.elementus.common.item.unique.CatalystItemUtil.totem;
//
//@SuppressWarnings("ConstantConditions")
//@Mixin(LivingEntity.class)
//public abstract class LivingEntityMixin extends Entity{
//    @Unique
//    protected int E$slashAttackTicks;
//
//    public LivingEntityMixin(EntityType<?> pEntityType, Level pLevel) {
//        super(pEntityType, pLevel);
//    }
//
//    @Shadow @Nullable public abstract MobEffectInstance getEffect(MobEffect pEffect);
//
//    @Shadow public abstract boolean hasEffect(MobEffect pEffect);
//
//    @Shadow public abstract ItemStack getUseItem();
//
//    @Shadow public abstract void setHealth(float pHealth);
//
//    @Shadow public abstract boolean removeAllEffects();
//
//    @Shadow public abstract boolean addEffect(MobEffectInstance pEffectInstance);
//
//    @Shadow public abstract @NotNull Iterable<ItemStack> getArmorSlots();
//
//    @Shadow
//    @Nullable
//    private DamageSource lastDamageSource;
//
//    @Shadow
//    public abstract boolean isUsingItem();
//
//    @Shadow
//    protected ItemStack useItem;
//
////    @Inject(method = "checkTotemDeathProtection", at = @At("RETURN"), cancellable = true)
////    public void E$catalystTotem(DamageSource damageSource, CallbackInfoReturnable<Boolean> cir) {
////        Entity entity = this;
////        if (!damageSource.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
////            if ((Object) this instanceof LivingEntity livingEntity) {
////                ItemStack chestplateItem = livingEntity.getItemBySlot(EquipmentSlot.CHEST);
////                if (chestplateItem.is(EItems.CATALYST_CHESTPLATE.get()) && CatalystArmorItem.catalystActivator(chestplateItem).equals(totem) &&
////                        !livingEntity.hasEffect(EMobEffects.TOTEM_COOLDOWN.get()))  {
////                    if (entity instanceof ServerPlayer serverplayer) {
////                        serverplayer.awardStat(Stats.ITEM_USED.get(Items.TOTEM_OF_UNDYING), 1);
////                        CriteriaTriggers.USED_TOTEM.trigger(serverplayer, chestplateItem);
////                    }
////
////                    this.setHealth(1.0F);
////                    this.removeAllEffects();
////                    this.addEffect(new MobEffectInstance(MobEffects.REGENERATION, CatalystArmorConfig.totem_RegenDuration, CatalystArmorConfig.totem_RegenAmp));
////                    this.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, CatalystArmorConfig.totem_AbsorbDuration, CatalystArmorConfig.totem_AbsorbAmp));
////                    this.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, CatalystArmorConfig.totem_FireResDuration, CatalystArmorConfig.totem_FireResAmp));
////                    if (CatalystArmorConfig.totem_Cooldown > 0) {
////                        this.addEffect(new MobEffectInstance(EMobEffects.TOTEM_COOLDOWN.get(), CatalystArmorConfig.totem_Cooldown, 0));
////                    }
////                    livingEntity.level().broadcastEntityEvent(this, (byte)35);
////                    cir.setReturnValue(true);
////                }
////            }
////        }
////    }
//
//    @Shadow
//    protected int useItemRemaining;
//
//    @Inject(method = "calculateFallDamage", at = @At("RETURN"), cancellable = true)
//    private void E$calculateFallDamage(float fallDistance, float damageMultiplier, CallbackInfoReturnable<Integer> cir) {
//        MobEffectInstance beaconPower = this.getEffect(EMobEffects.BEACON_POWER.get());
//        MobEffectInstance witheredBeaconPower = this.getEffect(EMobEffects.WITHERED_BEACON_POWER.get());
//        float f0 = beaconPower != null ? beaconPower.getAmplifier() + 1 : 0;
//        float f1 = witheredBeaconPower != null ? witheredBeaconPower.getAmplifier() + 1 : 0;
//
//        if (beaconPower != null || witheredBeaconPower != null) {
//            cir.setReturnValue((int) (Mth.ceil(fallDistance - 3.0F - Math.max(f0, f1)) * damageMultiplier));
//        }
//    }
//
//    @Inject(method = "getJumpBoostPower", at = @At("RETURN"), cancellable = true)
//    private void E$getJumpBoostPower(CallbackInfoReturnable<Float> cir) {
//        if ((Object) this instanceof LivingEntity livingEntity) {
//            if (DiarkriteBootsItem.SculkWalkerActivation(livingEntity)) {
//                cir.setReturnValue(-1F);
//            }
//        }
//        float ori = cir.getReturnValue();
//        MobEffectInstance beaconPower = this.getEffect(EMobEffects.BEACON_POWER.get());
//        MobEffectInstance witheredBeaconPower = this.getEffect(EMobEffects.WITHERED_BEACON_POWER.get());
//        float f0 = beaconPower != null ? beaconPower.getAmplifier() + 1 : ori;
//        float f1 = witheredBeaconPower != null ? witheredBeaconPower.getAmplifier() + 1 : ori;
//
//        if (beaconPower != null || witheredBeaconPower != null) {
//            cir.setReturnValue(0.1F * Math.max(f0, f1));
//        }
//    }
//
//    ///  Credits: Team Abode's Guarding Mod
//    @Inject(method = "hurt", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;broadcastEntityEvent(Lnet/minecraft/world/entity/Entity;B)V"), cancellable = true)
//    public void E$playBlockSound(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
//        if (this.getUseItem().is(EItems.DIARKRITE_SHIELD.get())) {
//            LivingEntity livingEntity = LivingEntity.class.cast(this);
//            livingEntity.level().playSound(null, livingEntity.blockPosition(), ESoundEvents.DIARKRITE_SHIELD_BLOCK.get(), SoundSource.PLAYERS, 1.0F, 0.8F + level().random.nextFloat() * 0.4F);
//            cir.cancel();
//        }
//        if (this.getUseItem().is(EItems.ANTHEKTITE_SHIELD.get())) {
//            LivingEntity livingEntity = LivingEntity.class.cast(this);
//            livingEntity.level().playSound(null, livingEntity.blockPosition(), ESoundEvents.ANTHEKTITE_SHIELD_BLOCK.get(), SoundSource.PLAYERS, 1.0F, 0.8F + level().random.nextFloat() * 0.4F);
//            cir.cancel();
//        }
//    }
//
////    @Inject(method = "isPushable", at = @At("RETURN"), cancellable = true)
////    private void E$isPushable(CallbackInfoReturnable<Boolean> cir) {
////        if ((Object) this instanceof LivingEntity livingEntity) {
////            if (DiarkriteBootsItem.SculkWalkerActivation(livingEntity)) {
////                cir.setReturnValue(false);
////            }
////        }
////    }
//
////    @Inject(method = "aiStep", at = @At("TAIL"), cancellable = true)
////    public void E$aiStep(CallbackInfo ci) {
////        if (E$slashAttackTicks > 0) {
////            --E$slashAttackTicks;
////            E$checkAutoSlashAttack(getBoundingBox(), getBoundingBox());
////        }
////    }
//
////    @Unique
////    public void E$checkAutoSlashAttack(AABB bbBeforeSlash, AABB bbAfterSlash) {
////        AABB aabb = bbBeforeSlash.minmax(bbAfterSlash);
////        List<Entity> list = this.level().getEntities(this, aabb);
////        if (!list.isEmpty()) {
////            for(int i = 0; i < list.size(); ++i) {
////                Entity entity = list.get(i);
////                if (entity instanceof LivingEntity living) {
////                    E$doAutoSlashOnTouch(this.getControllingPassenger(), 10, living);
////                    E$slashAttackTicks = 0;
//////                    this.setDeltaMovement(this.getDeltaMovement().scale(-0.2D));
////                    break;
////                }
////            }
////        } else if (horizontalCollision) {
////            E$slashAttackTicks = 0;
////        }
////
////        if (!level().isClientSide && E$slashAttackTicks <= 0) {
//////            setLivingEntityFlag(4, false);
////        }
////    }
////
////    @Unique
////    protected void E$doAutoSlashOnTouch(LivingEntity attacker, float damage, LivingEntity target) {
////        target.hurt(damageSources().playerAttack((Player) attacker), damage);
////    }
//
//    @Inject(method = "isBlocking", at = @At("HEAD"), cancellable = true)
//    public void E$isBlocking(CallbackInfoReturnable<Boolean> cir) {
//        Item item = this.useItem.getItem();
//        if (isUsingItem() && !useItem.isEmpty()) {
//            if (useItem.getItem() instanceof ChargeBladeItem) {
//                cir.setReturnValue(true);
//            }
//            if (useItem.getItem() instanceof EShieldItem shieldItem) {
//                cir.setReturnValue(shieldItem.applyCustomDelay(useItem, useItemRemaining));
//            }
//        }
//    }
//}