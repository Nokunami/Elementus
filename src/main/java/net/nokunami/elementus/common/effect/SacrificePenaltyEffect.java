package net.nokunami.elementus.common.effect;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.nokunami.elementus.common.config.catalystConfigs.CatalystArmorConfig;
import net.nokunami.elementus.common.registry.EMobEffects;

@Mod.EventBusSubscriber
public class SacrificePenaltyEffect extends ModMobEffect {
    public SacrificePenaltyEffect() {
        super(MobEffectCategory.NEUTRAL, 16733525);
    }

    @SubscribeEvent
    public static void healingReduction(LivingHealEvent event) {
        var entity = event.getEntity();
        var effect = entity.getEffect(EMobEffects.SACRIFICE_PENALTY.get());
        if (effect != null) {
            event.setCanceled(true);
        }
    }
}
