package net.nokunami.elementus.mixin;

import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.entity.LivingEntity;
import net.nokunami.elementus.common.config.CatalystArmorConfig;
import net.nokunami.elementus.common.registry.ModMobEffects.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(MobEffectUtil.class)
public class MobEffectUtilMixin {

    @Inject(method = "getDigSpeedAmplification", at = @At(value = "RETURN"), cancellable = true)
    private static void beaconPowerHaste(LivingEntity entity, CallbackInfoReturnable<Integer> cir) {
        int defaullValue = cir.getReturnValue();
        int netherStarHaste = 0;
        if (entity.hasEffect(ElementusEffects.BEACON_POWER.get())) {
            int level = entity.getEffect(ElementusEffects.BEACON_POWER.get()).getAmplifier();
            netherStarHaste = CatalystArmorConfig.NSHaste + level;
        }
        cir.setReturnValue(Math.max(defaullValue, netherStarHaste));
    }

    @Inject(method = "hasDigSpeed", at = @At(value = "RETURN"), cancellable = true)
    private static void beaconPowerBoolean(LivingEntity entity, CallbackInfoReturnable<Boolean> cir) {
        if (entity.hasEffect(ElementusEffects.BEACON_POWER.get())) {
            cir.setReturnValue(true);
        }
    }
}
