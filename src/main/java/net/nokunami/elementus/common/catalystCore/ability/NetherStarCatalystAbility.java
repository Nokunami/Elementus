package net.nokunami.elementus.common.catalystCore.ability;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.nokunami.elementus.common.catalystCore.Abilities;
import net.nokunami.elementus.common.config.catalystConfigs.CatalystArmorConfig;
import net.nokunami.elementus.common.entity.MobUtil;
import net.nokunami.elementus.common.registry.ModMobEffects;
import net.nokunami.elementus.common.registry.ESoundEvents;

import static net.nokunami.elementus.common.entity.MobUtil.playEntitySound;

public class NetherStarCatalystAbility extends CatalystAbility {

    public NetherStarCatalystAbility() {
        super(new Abilities.Builder()
                .effect(() -> new MobEffectInstance(ModMobEffects.ElementusEffects.BEACON_POWER.get(), 100 + CatalystArmorConfig.NSDuration, CatalystArmorConfig.NSBaseAmp))
                .altEffect(() -> new MobEffectInstance(ModMobEffects.ElementusEffects.BEACON_POWER.get(), 100 + CatalystArmorConfig.NSDuration, CatalystArmorConfig.NSBoostedAmp))
                .build());
    }

    @Override
    public void tick(Level level, Entity entity) {
        if (MobUtil.healthPercent(entity, 0.5F) && !MobUtil.itemCooldown(entity)) {
            super.tickAltEffect(level, entity);
            MobUtil.applyItemCooldown(entity, CatalystArmorConfig.NSCooldown);
            playEntitySound(entity, ESoundEvents.CATALYST_ARMOR_ACTIVATE, 1.25F, 1.5F + entity.level().getRandom().nextFloat() * 0.4F);
        } else {
            if (!MobUtil.hasEffect(entity, ModMobEffects.ElementusEffects.BEACON_POWER.get()))
                super.tickEffect(level, entity);
        }
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