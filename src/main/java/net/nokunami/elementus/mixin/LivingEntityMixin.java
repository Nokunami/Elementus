package net.nokunami.elementus.mixin;

import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.nokunami.elementus.common.effect.IElementusMobEffect;
import net.nokunami.elementus.common.item.DiarkriteBootsItem;
import net.nokunami.elementus.common.item.IShieldedArmor;
import net.nokunami.elementus.common.item.basic.EShieldItem;
import net.nokunami.elementus.common.item.unique.ChargeBladeItem;
import net.nokunami.elementus.common.registry.EItems;
import net.nokunami.elementus.common.registry.ESounds;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Map;

@SuppressWarnings("ConstantConditions")
@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    @Unique
    protected int E$slashAttackTicks;

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

    @Shadow
    @Nullable
    private DamageSource lastDamageSource;

    @Shadow
    public abstract boolean isUsingItem();

    @Shadow
    protected ItemStack useItem;

    @Shadow
    protected int useItemRemaining;

    @Shadow
    public abstract Collection<MobEffectInstance> getActiveEffects();

    @Shadow
    public abstract Map<MobEffect, MobEffectInstance> getActiveEffectsMap();

//    @Inject(method = "calculateFallDamage", at = @At("RETURN"), cancellable = true)
//    private void E$calculateFallDamage(float fallDistance, float damageMultiplier, CallbackInfoReturnable<Integer> cir) {
////        MobEffectInstance beaconPower = getEffect(EMobEffects.BEACON_POWER.get());
////        MobEffectInstance witheredBeaconPower = getEffect(EMobEffects.WITHERED_BEACON_POWER.get());
////        float f0 = beaconPower != null ? beaconPower.getAmplifier() + 1 : 0;
////        float f1 = witheredBeaconPower != null ? witheredBeaconPower.getAmplifier() + 1 : 0;
//

    @Shadow
    public abstract ItemStack getItemBySlot(EquipmentSlot pSlot);

    ////        if (beaconPower != null || witheredBeaconPower != null) {
////            cir.setReturnValue((int) (Mth.ceil(fallDistance - 3.0F - Math.max(f0, f1)) * damageMultiplier));
////        }
//
//        float f0 = 0;
//        float f1 = cir.getReturnValue();
//        boolean shouldApply = false;
//        if (getActiveEffects().stream().findAny().isPresent()) {
//            for (MobEffectInstance effectInst : getActiveEffects()) {
//                if (effectInst != null && effectInst.getEffect() instanceof IElementusMobEffect) {
//                    f0 = effectInst.getAmplifier() + 1;
//                    shouldApply = true;
//                }
//            }
//        }
//
//        if (shouldApply) cir.setReturnValue((int) (Mth.ceil(fallDistance - 3.0F - Math.max(f0, f1)) * damageMultiplier));
//    }

    @Inject(method = "calculateFallDamage", at = @At("RETURN"), cancellable = true)
    private void E$calculateFallDamage(float fallDistance, float damageMultiplier, CallbackInfoReturnable<Integer> cir) {
        float f0 = 0;
        MobEffectInstance mobeffectinstance = this.getEffect(MobEffects.JUMP);
        float f1 = mobeffectinstance == null ? 0.0F : (float)(mobeffectinstance.getAmplifier() + 1);;
        boolean shouldApply = false;
        if (getActiveEffects().stream().findAny().isPresent()) {
            for (MobEffectInstance effectInst : getActiveEffects()) {
                if (effectInst != null && effectInst.getEffect() instanceof IElementusMobEffect) {
                    f0 = effectInst.getAmplifier() + 1;
                    shouldApply = true;
                }
            }
        }
        if (shouldApply) cir.setReturnValue((int) (Mth.ceil(fallDistance - 3.0F - Math.max(f0, f1)) * damageMultiplier));
    }

    @Inject(method = "getJumpBoostPower", at = @At("RETURN"), cancellable = true)
    private void E$getJumpBoostPower(CallbackInfoReturnable<Float> cir) {
        if ((Object) this instanceof LivingEntity livingEntity) {
            if (DiarkriteBootsItem.SculkWalkerActivation(livingEntity)) {
                cir.setReturnValue(-1F);
            }
        }
//        float ori = cir.getReturnValue();
//        MobEffectInstance beaconPower = this.getEffect(EMobEffects.BEACON_POWER.get());
//        MobEffectInstance witheredBeaconPower = this.getEffect(EMobEffects.WITHERED_BEACON_POWER.get());
//        float f0 = beaconPower != null ? beaconPower.getAmplifier() + 1 : ori;
//        float f1 = witheredBeaconPower != null ? witheredBeaconPower.getAmplifier() + 1 : ori;
//
//        if (beaconPower != null || witheredBeaconPower != null) {
//            cir.setReturnValue(0.1F * Math.max(f0, f1));
//        }

        float f0 = 0;
        float f1 = cir.getReturnValue();
        boolean shouldApply = false;
        if (getActiveEffects().stream().findAny().isPresent()) {
            for (MobEffectInstance effectInst : getActiveEffects()) {
                if (effectInst != null && effectInst.getEffect() instanceof IElementusMobEffect) {
                    f0 = effectInst.getAmplifier() + 1;
                    shouldApply = true;
                }
            }
        }

        if (shouldApply) cir.setReturnValue(0.1F * Math.max(f0, f1));
    }

    ///  Credits: Team Abode's Guarding Mod
    @Inject(method = "hurt", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;broadcastEntityEvent(Lnet/minecraft/world/entity/Entity;B)V"), cancellable = true)
    public void E$playBlockSound(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity entity = LivingEntity.class.cast(this);
        if (this.getUseItem().is(EItems.DIARKRITE_SHIELD.get())) {
            entity.level().playSound(null, entity.blockPosition(), ESounds.DIARKRITE_SHIELD_BLOCK.get(), SoundSource.PLAYERS, 1.0F, 0.8F + level().random.nextFloat() * 0.4F);
            cir.cancel();
        }
        if (this.getUseItem().is(EItems.ANTHEKTITE_SHIELD.get())) {
            entity.level().playSound(null, entity.blockPosition(), ESounds.ANTHEKTITE_SHIELD_BLOCK.get(), SoundSource.PLAYERS, 1.0F, 0.8F + level().random.nextFloat() * 0.4F);
            cir.cancel();
        }
    }

//    @Inject(method = "isPushable", at = @At("RETURN"), cancellable = true)
//    private void E$isPushable(CallbackInfoReturnable<Boolean> cir) {
//        if ((Object) this instanceof LivingEntity livingEntity) {
//            if (DiarkriteBootsItem.SculkWalkerActivation(livingEntity)) {
//                cir.setReturnValue(false);
//            }
//        }
//    }

//    @Inject(method = "aiStep", at = @At("TAIL"), cancellable = true)
//    public void E$aiStep(CallbackInfo ci) {
//        if (E$slashAttackTicks > 0) {
//            --E$slashAttackTicks;
//            E$checkAutoSlashAttack(getBoundingBox(), getBoundingBox());
//        }
//    }

//    @Unique
//    public void E$checkAutoSlashAttack(AABB bbBeforeSlash, AABB bbAfterSlash) {
//        AABB aabb = bbBeforeSlash.minmax(bbAfterSlash);
//        List<Entity> list = this.level().getEntities(this, aabb);
//        if (!list.isEmpty()) {
//            for(int i = 0; i < list.size(); ++i) {
//                Entity entity = list.get(i);
//                if (entity instanceof LivingEntity living) {
//                    E$doAutoSlashOnTouch(this.getControllingPassenger(), 10, living);
//                    E$slashAttackTicks = 0;
////                    this.setDeltaMovement(this.getDeltaMovement().scale(-0.2D));
//                    break;
//                }
//            }
//        } else if (horizontalCollision) {
//            E$slashAttackTicks = 0;
//        }
//
//        if (!level().isClientSide && E$slashAttackTicks <= 0) {
////            setLivingEntityFlag(4, false);
//        }
//    }
//
//    @Unique
//    protected void E$doAutoSlashOnTouch(LivingEntity attacker, float damage, LivingEntity target) {
//        target.hurt(damageSources().playerAttack((Player) attacker), damage);
//    }

    @Inject(method = "isBlocking", at = @At("HEAD"), cancellable = true)
    public void E$isBlocking(CallbackInfoReturnable<Boolean> cir) {
        Item item = this.useItem.getItem();
        if (isUsingItem() && !useItem.isEmpty()) {
            if (useItem.getItem() instanceof ChargeBladeItem) {
                cir.setReturnValue(true);
            }
            if (useItem.getItem() instanceof EShieldItem shieldItem) {
                cir.setReturnValue(shieldItem.applyCustomDelay(useItem, useItemRemaining));
            }
        }
    }

    @Inject(method = "isDamageSourceBlocked", at = @At("HEAD"), cancellable = true)
    public void E$isDamageSourceBlocked(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity entity = LivingEntity.class.cast(this);
        Entity directEntity = source.getDirectEntity();
        boolean flag = false;
        if (directEntity instanceof AbstractArrow abstractarrow) {
            if (abstractarrow.getPierceLevel() > 0) {
                flag = true;
            }
        }

        for (EquipmentSlot slot : EquipmentSlot.values()) {
            ItemStack stack = getItemBySlot(slot);
            if (slot.isArmor()) {
                if (stack.getItem() instanceof IShieldedArmor shieldedArmor) {
                    cir.setReturnValue(shieldedArmor.isShielded(entity, source, stack, flag));
                    stack.hurtAndBreak(1, entity, event -> event.broadcastBreakEvent(slot));
                }
            }
        }
    }
}