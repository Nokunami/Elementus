package net.nokunami.elementus.common.catalystCore.ability.activeAbility.heartOfTheSea;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.nokunami.elementus.common.capability.abilityData.AbilityData;
import net.nokunami.elementus.common.catalystCore.CatalystCoreRangedUtil;
import net.nokunami.elementus.common.catalystCore.ability.AbilityType;
import net.nokunami.elementus.common.catalystCore.ability.AbstractAbility;
import net.nokunami.elementus.config.CatalystArmorConfig;
import net.nokunami.elementus.config.EConfig;

import static net.nokunami.elementus.common.entity.MobUtil.EffectApplier.EAInst;

public class ConduitCleanseAbility extends AbstractAbility {
    CatalystArmorConfig.heartOfTheSea config = EConfig.catalystConfig.heartOfTheSea;
    CatalystCoreRangedUtil rangedUtil = new CatalystCoreRangedUtil();

    @Override public AbilityProperties properties() {
        return new AbilityProperties(config.cleanseCooldown)
                .setPreCastDuration(config.cleanseCastDuration);
    }

    @Override
    public void onCastTick(LivingEntity entity, Level level) {
        var pos = entity.blockPosition();
        long i = level.getGameTime();
        if (i % 40L == 0L) {
            level.playSound(entity, pos, SoundEvents.CONDUIT_ACTIVATE, entity.getSoundSource(), 1, 1);

            rangedUtil.updateDestroyTarget(entity, level, pos, config.cleanseArea, config.cleanseDamage);
        }

        if (i % 80L == 0L)
            level.playSound(entity, pos, SoundEvents.CONDUIT_AMBIENT, entity.getSoundSource(), 1, 1);
    }

    @Override public boolean isReadyToCast(LivingEntity entity) { return entity.isInWaterRainOrBubble(); }

    @Override
    public Component failedCastMessage(LivingEntity entity, AbstractAbility ability) {
        return entity.isInWaterRainOrBubble() ? super.failedCastMessage(entity, ability) :
                Component.translatableWithFallback(getDescriptionId() + ".not_in_water_cast", "Cast in water, rain or bubble column.");
    }
}