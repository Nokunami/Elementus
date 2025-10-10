package net.nokunami.elementus.common.catalystCore.ability;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.nokunami.elementus.common.catalystCore.Abilities;

public class HeartOfTheSeaCatalystAbility extends CatalystAbility {

    public HeartOfTheSeaCatalystAbility() {
        super(new Abilities.Builder()
                .effect(() -> new MobEffectInstance(MobEffects.CONDUIT_POWER, 2, 0, false, true, true))
                .build());
    }

    @Override
    public void tick(Level level, Entity entity) {
        if (entity instanceof LivingEntity living && living.isInWaterRainOrBubble())
            super.tickEffect(level, entity);

        for (Entity targets : level.getEntities(entity, entity.getBoundingBox().inflate(16))) {
            if (targets.isInWaterRainOrBubble() && targets.isAlliedTo(entity)) {
                if (targets instanceof LivingEntity living) {
                    living.addEffect(new MobEffectInstance(MobEffects.CONDUIT_POWER, 400, 0));
                }
            }
        }
    }
}