package net.nokunami.elementus.common.catalystCore.ability.passiveAbility;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.nokunami.elementus.common.catalystCore.ability.PassiveCatalystAbility;
import net.nokunami.elementus.common.entity.MobUtil;
import net.nokunami.elementus.common.registry.EMobEffects;

import static net.nokunami.elementus.common.entity.MobUtil.EffectApplier.EAInst;
import static net.nokunami.elementus.common.entity.MobUtil.healthPercent;

public class WitheredNetherStarCatalystAbility extends PassiveCatalystAbility {

    @Override
    public void tick(Level level, Entity entity) {

        if (entity instanceof LivingEntity living) {
            MobEffectInstance effect = living.getEffect(EMobEffects.WITHERED_BEACON_POWER.get());
            if (!living.hasEffect(EMobEffects.WITHERED_BEACON_POWER.get()))
                if (healthPercent(entity, 0.5F)) {
//                    if (effect != null && effect.getAmplifier() < 1) {
//                        MobUtil.applyEffect(entity, EMobEffects.WITHERED_BEACON_POWER, 100, 1);
//                    }
                    EAInst(living, EMobEffects.WITHERED_BEACON_POWER).stats(100, 1).applyIf(effect != null && effect.getAmplifier() < 1);
                } else {
//                    MobUtil.applyEffect(entity, EMobEffects.WITHERED_BEACON_POWER, 100);
                    EAInst(living, EMobEffects.WITHERED_BEACON_POWER).stats(100).apply();
                }
        }
        for (Entity targets : level.getEntities(entity, entity.getBoundingBox().inflate(16))) {
            if (targets.isAlliedTo(entity)) {
                if (targets instanceof LivingEntity living) {
//                    living.addEffect(new MobEffectInstance(MobEffects.JUMP, 400, 1));
//                    living.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 400, 1));
//                    if (!living.hasEffect(MobEffects.REGENERATION))
//                        living.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 400, 1));
//                    living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 400, 1));
//                    living.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 400, 1));
                    EAInst(living, MobEffects.JUMP).stats(400, 1).apply();
                    EAInst(living, MobEffects.DIG_SPEED).stats(400, 1).apply();
                    EAInst(living, MobEffects.REGENERATION).stats(400, 1).applyRefresh();
                    EAInst(living, MobEffects.MOVEMENT_SPEED).stats(400, 1).apply();
                    EAInst(living, MobEffects.DAMAGE_RESISTANCE).stats(400, 1).apply();
                }
            }
        }
    }
}