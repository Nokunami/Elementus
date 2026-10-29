package net.nokunami.elementus.common.catalystCore.ability.passiveAbility.heartOfTheSea;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.nokunami.elementus.common.catalystCore.ability.PassiveAbility;
import net.nokunami.elementus.config.CatalystArmorConfig;
import net.nokunami.elementus.config.EConfig;

import static net.nokunami.elementus.api.util.EffectUtil.effectUtil;
import static net.nokunami.elementus.common.entity.MobUtil.EffectApplier.EAInst;

public class ConduitPassiveAbility extends PassiveAbility {
    CatalystArmorConfig.heartOfTheSea config = EConfig.catalystConfig.heartOfTheSea;

    @Override
    public void onCastTick(LivingEntity entity, Level level) {
        EAInst(entity, MobEffects.CONDUIT_POWER).stats(2, config.amp - 1).noAmbient().apply();

        for (Entity targets : level.getEntities(entity, entity.getBoundingBox().inflate(16))) {
            if (targets.isInWaterRainOrBubble() && targets.isAlliedTo(entity)) {
                if (targets instanceof LivingEntity living) {
                    living.addEffect(new MobEffectInstance(MobEffects.CONDUIT_POWER, 400, 0));
                    EAInst(entity, MobEffects.CONDUIT_POWER).stats(config.durationArea, config.ampArea - 1).apply();
                }
            }
        }
    }
}