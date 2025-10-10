package net.nokunami.elementus.common.catalystCore.ability;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.nokunami.elementus.common.catalystCore.Abilities;
import net.nokunami.elementus.common.registry.ModMobEffects;

import static net.nokunami.elementus.common.entity.MobUtil.healthPercent;

public class WitheredNetherStarCatalystAbility extends CatalystAbility {

    public WitheredNetherStarCatalystAbility() {
        super(new Abilities.Builder()
                .effect(() -> new MobEffectInstance(ModMobEffects.ElementusEffects.WITHERED_BEACON_POWER.get(), 100))
                .altEffect(() -> new MobEffectInstance(ModMobEffects.ElementusEffects.WITHERED_BEACON_POWER.get(), 100, 1))
                .build());
    }

    @Override
    public void tickEffect(Level level, Entity entity) {
        if (entity instanceof LivingEntity living) {
            MobEffectInstance effect = living.getEffect(ModMobEffects.ElementusEffects.WITHERED_BEACON_POWER.get());
            if (!living.hasEffect(ModMobEffects.ElementusEffects.WITHERED_BEACON_POWER.get()))
                if (healthPercent(entity, 0.5F)) {
                    if (effect != null && effect.getAmplifier() < 1) {
                        super.tickAltEffect(level, entity);
                    }
//                    playEntitySound(entity, ModSoundEvents.CATALYST_ARMOR_ACTIVATE, 1.25F, 1.5F + entity.level().getRandom().nextFloat() * 0.4F);
                } else {
                    super.tickAltEffect(level, entity);
                }
        }
        for (Entity targets : level.getEntities(entity, entity.getBoundingBox().inflate(16))) {
            if (targets.isAlliedTo(entity)) {
                if (targets instanceof LivingEntity living) {
                    living.addEffect(new MobEffectInstance(MobEffects.JUMP, 400, 1));
                    living.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 400, 1));
                    if (!living.hasEffect(MobEffects.REGENERATION))
                        living.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 400, 1));
                    living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 400, 1));
                    living.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 400, 1));
                }
            }
        }
    }
}