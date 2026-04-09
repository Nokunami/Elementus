package net.nokunami.elementus.common.catalystCore.ability.passiveAbility;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.nokunami.elementus.common.catalystCore.ability.PassiveCatalystAbility;

import static net.nokunami.elementus.common.entity.MobUtil.EffectApplier.EAInst;

public class HeartOfTheSeaCatalystAbility extends PassiveCatalystAbility {

    @Override
    public void tick(Level level, Entity entity) {
        if (entity instanceof LivingEntity living && living.isInWaterRainOrBubble())
//            super.tickEffect(level, entity);
//            applyEffect(entity, MobEffects.CONDUIT_POWER, 2, 0, false, true, true);
              EAInst(entity, MobEffects.CONDUIT_POWER).stats(2).noAmbient().apply();

        for (Entity targets : level.getEntities(entity, entity.getBoundingBox().inflate(16))) {
            if (targets.isInWaterRainOrBubble() && targets.isAlliedTo(entity)) {
                if (targets instanceof LivingEntity living) {
                    living.addEffect(new MobEffectInstance(MobEffects.CONDUIT_POWER, 400, 0));
                }
            }
        }
    }
}