package net.nokunami.elementus.common.catalystCore.ability.passiveAbility;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.nokunami.elementus.common.catalystCore.ability.PassiveCatalystAbility;
import net.nokunami.elementus.common.config.catalystConfigs.CatalystArmorConfig;
import net.nokunami.elementus.common.entity.MobUtil;
import net.nokunami.elementus.common.registry.EMobEffects;
import net.nokunami.elementus.common.registry.ESounds;

import static net.nokunami.elementus.common.config.catalystConfigs.CatalystArmorConfig.*;
import static net.nokunami.elementus.common.entity.MobUtil.EffectApplier.EAInst;
import static net.nokunami.elementus.common.entity.MobUtil.playEntitySound;

public class NetherStarCatalystAbility extends PassiveCatalystAbility {

    @Override
    public void tick(Level level, Entity entity) {
        if (MobUtil.healthPercent(entity, 0.5F) && !MobUtil.itemCooldown(entity)) {
            if (entity instanceof LivingEntity living) {
                MobEffectInstance effect = living.getEffect(EMobEffects.BEACON_POWER.get());
//                if (effect != null && effect.getAmplifier() < 1)
//                    MobUtil.applyEffect(entity, EMobEffects.BEACON_POWER, CatalystArmorConfig.NSDuration, CatalystArmorConfig.NSBoostedAmp);
                EAInst(entity, EMobEffects.BEACON_POWER).stats(NSDuration, NSBoostedAmp).applyIf(effect != null && effect.getAmplifier() < 1);
            }
//            super.tickAltEffect(level, entity);
            MobUtil.applyItemCooldown(entity, CatalystArmorConfig.NSCooldown);
            playEntitySound(entity, ESounds.CATALYST_ARMOR_ACTIVATE, 1.25F, 1.5F + entity.level().getRandom().nextFloat() * 0.4F);
        } else {
//            if (!MobUtil.hasEffect(entity, EMobEffects.BEACON_POWER.get()))
//                super.tickEffect(level, entity);
//                MobUtil.applyEffect(entity, EMobEffects.BEACON_POWER, NSDuration, CatalystArmorConfig.NSBaseAmp);
            EAInst(entity, EMobEffects.BEACON_POWER).stats(NSDuration, NSBaseAmp).applyRefresh();
        }
        for (Entity targets : level.getEntities(entity, entity.getBoundingBox().inflate(16))) {
            if (targets.isAlliedTo(entity)) {
                if (targets instanceof LivingEntity living) {
//                    living.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 400, 0));
//                    if (!living.hasEffect(MobEffects.REGENERATION))
//                        living.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 400, 0));
//                    living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 400, 0));
//                    living.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 400, 0));
                    EAInst(living, MobEffects.DIG_SPEED).stats(400).apply();
                    EAInst(living, MobEffects.REGENERATION).stats(400).applyRefresh();
                    EAInst(living, MobEffects.MOVEMENT_SPEED).stats(400).apply();
                    EAInst(living, MobEffects.DAMAGE_RESISTANCE).stats(400).apply();
                }
            }
        }
    }
}