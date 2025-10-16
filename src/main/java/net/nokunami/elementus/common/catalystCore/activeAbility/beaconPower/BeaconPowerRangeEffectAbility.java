package net.nokunami.elementus.common.catalystCore.activeAbility.beaconPower;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.nokunami.elementus.common.catalystCore.AbstractActiveAbility;

public class BeaconPowerRangeEffectAbility extends AbstractActiveAbility {

    public BeaconPowerRangeEffectAbility() {
        super(AbilityType.TOGGLED);
    }

    @Override
    public void ability(Entity entity, Level level) {
//        setCooldown(20);
//        setExhaustionTick(25);
//        setExhaustionPenalty(25);
        for (Entity targets : level.getEntities(entity, entity.getBoundingBox().inflate(16))) {
            if (targets.isAlliedTo(entity)) {
                if (targets instanceof LivingEntity living) {
                    living.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 400, 0));
                    if (!living.hasEffect(MobEffects.REGENERATION))
                        living.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 400, 0));
                    living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 400, 0));
                    living.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 400, 0));
                }
            }
        }
    }
}
