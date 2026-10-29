package net.nokunami.elementus.common.catalystCore.ability.passiveAbility.beaconPower;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.nokunami.elementus.common.capability.abilityData.AbilityData;
import net.nokunami.elementus.common.catalystCore.ability.PassiveAbility;
import net.nokunami.elementus.common.entity.MobUtil;
import net.nokunami.elementus.common.registry.EMobEffects;
import net.nokunami.elementus.common.registry.ESounds;
import net.nokunami.elementus.config.CatalystArmorConfig;
import net.nokunami.elementus.config.EConfig;

import static net.nokunami.elementus.api.util.EffectUtil.effectUtil;
import static net.nokunami.elementus.api.util.ParticleUtil.particleUtil;
import static net.nokunami.elementus.common.entity.MobUtil.EffectApplier.EAInst;
import static net.nokunami.elementus.common.entity.MobUtil.playEntitySound;

public class BeaconPowerPassiveAbility extends PassiveAbility {
    CatalystArmorConfig.beaconPower config = EConfig.catalystConfig.beaconPower;

    @Override
    public AbilityProperties properties() {
        return new AbilityProperties(config.cooldown);
    }

    @Override
    public void onCastTick(LivingEntity entity, Level level) {
        if (MobUtil.healthPercent(entity, 0.5F)) {
            effectUtil(entity, EMobEffects.BEACON_POWER).condition(effectUtil -> effectUtil.getEffectInst() != null && effectUtil.getEffectInst().getAmplifier() < 1).add(config.boostedDuration, config.boostedAmp - 1);
            playEntitySound(entity, ESounds.CATALYST_ARMOR_ACTIVATE, 1.25F, 1.5F + entity.level().getRandom().nextFloat() * 0.4F);
            particleUtil(entity.level(), ParticleTypes.ELECTRIC_SPARK, entity.position()).spread(true, entity, UniformInt.of(5, 8));
            AbilityData.instance(entity).addCooldown(this);
        } else
            effectUtil(entity, EMobEffects.BEACON_POWER).refreshCondition().add(config.duration, config.amp - 1);
        for (Entity targets : level.getEntities(entity, entity.getBoundingBox().inflate(16))) {
            if (targets.isAlliedTo(entity)) {
                if (targets instanceof LivingEntity living) {
                    effectUtil(living, MobEffects.MOVEMENT_SPEED).add(400, config.areaEffect.speed);
                    effectUtil(living, MobEffects.DIG_SPEED).add(400, config.areaEffect.haste);
                    effectUtil(living, MobEffects.DAMAGE_RESISTANCE).add(400, config.areaEffect.resistance);
                    effectUtil(living, MobEffects.REGENERATION).add(400, config.areaEffect.regeneration);
                    effectUtil(living, MobEffects.JUMP).add(400, config.areaEffect.jump);
                    effectUtil(living, MobEffects.DAMAGE_BOOST).add(400, config.areaEffect.strength);
                }
            }
        }
    }
}