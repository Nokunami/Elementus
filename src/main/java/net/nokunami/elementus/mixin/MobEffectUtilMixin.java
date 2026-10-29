package net.nokunami.elementus.mixin;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.entity.LivingEntity;
import net.nokunami.elementus.common.effect.IElementusMobEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(MobEffectUtil.class)
public class MobEffectUtilMixin {

    @Inject(method = "getDigSpeedAmplification", at = @At(value = "RETURN"), cancellable = true)
    private static void beaconPowerHaste(LivingEntity entity, CallbackInfoReturnable<Integer> cir) {
        int defaultValue = cir.getReturnValue();
        int netherStarHaste = 0;
//        if (entity.hasEffect(EMobEffects.BEACON_POWER.get())) {
//            int level = entity.getEffect(EMobEffects.BEACON_POWER.get()).getAmplifier();
////            netherStarHaste = CatalystArmorConfig.NSHaste + level;
//            netherStarHaste = EConfig.effectConfig.beaconPower.haste + level;
//        }
        if (entity.getActiveEffects().stream().findAny().isPresent()) {
            for (MobEffectInstance effectInst : entity.getActiveEffects()) {
                if (effectInst != null && effectInst.getEffect() instanceof IElementusMobEffect effect) {
                    netherStarHaste = effect.getBonusDigSpeed() + effectInst.getAmplifier();
                }
            }
        }
        cir.setReturnValue(Math.max(defaultValue, netherStarHaste));
    }

    @Inject(method = "hasDigSpeed", at = @At(value = "RETURN"), cancellable = true)
    private static void beaconPowerBoolean(LivingEntity entity, CallbackInfoReturnable<Boolean> cir) {
//        if (entity.hasEffect(EMobEffects.BEACON_POWER.get())) {
//            cir.setReturnValue(true);
//        }
        boolean hasDigSpeed = false;
        if (entity.getActiveEffects().stream().findAny().isPresent()) {
            for (MobEffectInstance effectInst : entity.getActiveEffects()) {
                if (effectInst != null && effectInst.getEffect() instanceof IElementusMobEffect effect) {
                    hasDigSpeed = effect.hasDigSpeed();
                }
            }
        }
        if (cir.getReturnValue() || hasDigSpeed) {
            cir.setReturnValue(true);
        }
    }
}
