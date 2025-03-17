package net.nokunami.elementus.common.effect;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.nokunami.elementus.Elementus;
import net.nokunami.elementus.common.config.CatalystArmorConfig;
import net.nokunami.elementus.common.registry.ModMobEffects;
import org.jetbrains.annotations.NotNull;

@Mod.EventBusSubscriber
public class WitheredBeaconPower extends ModMobEffect {

    public WitheredBeaconPower() {
        super(MobEffectCategory.BENEFICIAL, 5636095);
        addAttributeModifier(Attributes.MOVEMENT_SPEED, "2d58ffbf-d9cb-4fd3-91d7-d7609ae0c3d0", CatalystArmorConfig.WNSSpeed * 0.2, AttributeModifier.Operation.MULTIPLY_TOTAL);
        addAttributeModifier(Attributes.ATTACK_SPEED, "76da929d-8eb6-43eb-962a-cc655c1966a3", CatalystArmorConfig.WNSHaste * 0.1, AttributeModifier.Operation.MULTIPLY_TOTAL);
        addAttributeModifier(Attributes.ATTACK_DAMAGE, "2d58ffbf-d9cb-4fd3-91d7-d7609ae0c3d0", CatalystArmorConfig.WNSAttack, AttributeModifier.Operation.ADDITION);
    }

    @SubscribeEvent
    public static void resistanceEffect(LivingDamageEvent event) {
        var entity = event.getEntity();
        var effect = entity.getEffect(ModMobEffects.ElementusEffects.WITHERED_BEACON_POWER.get());
        if (effect != null) {
            int level = effect.getAmplifier();
            float reduction = 1 - getReductionAmount(level);
            if (reduction<=0) {
                reduction = 0;
            }
            float before = event.getAmount();
            event.setAmount(event.getAmount() * reduction);
            Elementus.LOGGER.debug("NetherStarPower.reduceDamage: {}->{}", before, event.getAmount());
        }
    }

    public static float getReductionAmount(int level) {
        return ((float) CatalystArmorConfig.NSResistance * 0.01F) + ((float) CatalystArmorConfig.WNSResistAmountPerLevel * 0.01F) * (float)level;
    }

    @Override
    public void applyEffectTick(@NotNull LivingEntity pLivingEntity, int pAmplifier) {
        if (this == ModMobEffects.ElementusEffects.WITHERED_BEACON_POWER.get()) {
            if (pLivingEntity.getHealth() < pLivingEntity.getMaxHealth()) {
                pLivingEntity.heal(2.0F);
            }
        }
    }

    @SubscribeEvent
    public static void healingReduction(LivingHealEvent event) {
        var entity = event.getEntity();
        var effect = entity.getEffect(ModMobEffects.ElementusEffects.WITHERED_BEACON_POWER.get());
        if (effect != null) {
            float heal = event.getAmount();
            event.setAmount(heal * ((float) CatalystArmorConfig.WNSRegenPenalty / 100));
        }
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        if (this == ModMobEffects.ElementusEffects.WITHERED_BEACON_POWER.get()) {
            int k = 50 >> pAmplifier;
            if (k > 0) {
                return pDuration % k == 0;
            } else {
                return true;
            }
        }
        return false;
    }
}