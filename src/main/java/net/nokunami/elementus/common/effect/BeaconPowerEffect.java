package net.nokunami.elementus.common.effect;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.nokunami.elementus.config.EConfig;
import net.nokunami.elementus.config.MobEffectConfig;
import net.nokunami.elementus.common.registry.EGameRules;
import net.nokunami.elementus.common.registry.EMobEffects;
import org.jetbrains.annotations.NotNull;

@Mod.EventBusSubscriber
public class BeaconPowerEffect extends ModMobEffect implements IElementusMobEffect {
    static MobEffectConfig.beaconPower config = EConfig.effectConfig.beaconPower;

    public BeaconPowerEffect() {
        super(MobEffectCategory.BENEFICIAL, 5636095);
//        addAttributeModifier(Attributes.MOVEMENT_SPEED, "2d58ffbf-d9cb-4fd3-91d7-d7609ae0c3d0", CatalystArmorConfig.NSSpeed * 0.2, AttributeModifier.Operation.MULTIPLY_TOTAL);
//        addAttributeModifier(Attributes.ATTACK_SPEED, "76da929d-8eb6-43eb-962a-cc655c1966a3", CatalystArmorConfig.NSHaste * 0.1, AttributeModifier.Operation.MULTIPLY_TOTAL);
////        addAttributeModifier(Attributes.JUMP_STRENGTH, "2d58ffbf-d9cb-4fd3-91d7-d7609ae0c3d0", CatalystArmorConfig.netherStar_JumpBoost, AttributeModifier.Operation.MULTIPLY_TOTAL);
//        addAttributeModifier(Attributes.ATTACK_DAMAGE, "2d58ffbf-d9cb-4fd3-91d7-d7609ae0c3d0", CatalystArmorConfig.NSAttack, AttributeModifier.Operation.ADDITION);

        addAttributeModifier(Attributes.MOVEMENT_SPEED, "2d58ffbf-d9cb-4fd3-91d7-d7609ae0c3d0", config.speed, Operation.MULTIPLY_TOTAL);
        addAttributeModifier(Attributes.ATTACK_SPEED, "76da929d-8eb6-43eb-962a-cc655c1966a3", config.haste * 0.1, Operation.MULTIPLY_TOTAL);
        if (config.jump > 0) addAttributeModifier(Attributes.JUMP_STRENGTH, "2d58ffbf-d9cb-4fd3-91d7-d7609ae0c3d0", config.jump, Operation.MULTIPLY_TOTAL);
        addAttributeModifier(Attributes.ATTACK_DAMAGE, "2d58ffbf-d9cb-4fd3-91d7-d7609ae0c3d0", config.strength, Operation.ADDITION);
    }

    @Override public int getBonusDigSpeed() { return config.haste; }
    @Override public boolean hasDigSpeed() { return true; }

    @SubscribeEvent
    public static void reduceDamage(LivingDamageEvent event) {
        var entity = event.getEntity();
        var effect = entity.getEffect(EMobEffects.BEACON_POWER.get());
        if (effect != null) {
            int level = effect.getAmplifier();
            float reduction = 1 - getReductionAmount(level);
            if (reduction <= 0) {
                reduction = 0;
            }
            float before = event.getAmount();
            event.setAmount(event.getAmount() * reduction);
            if (EGameRules.isDebugModeOn(entity.level()) && entity instanceof Player player) {
//                player.sendSystemMessage(Component.translatableWithFallback(String.format("NetherStarPower.reduceDamage: %s -> %s", before, event.getAmount())));
                player.sendSystemMessage(Component.translatableWithFallback("debug.elementus.beacon_power_damage", "NetherStarPower.reduceDamage: %s -> %s", before, event.getAmount()));
            }
//            Elementus.LOGGER.debug("NetherStarPower.reduceDamage: {}->{}", before, event.getAmount());
        }
    }

    public static float getReductionAmount(int level) {
//        return ((float) CatalystArmorConfig.NSResistance * 0.01F) + ((float) CatalystArmorConfig.NSResistAmountPerLevel * 0.01F) * (float) level;
        return config.resistance.get().component1().floatValue() + (config.resistance.get().component2().floatValue()) * (float) level;
    }

    @Override
    public void applyEffectTick(@NotNull LivingEntity pLivingEntity, int pAmplifier) {
        if (this == EMobEffects.BEACON_POWER.get()) {
            if (pLivingEntity.getHealth() < pLivingEntity.getMaxHealth()) {
                pLivingEntity.heal(1.0F);
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        if (this == EMobEffects.BEACON_POWER.get()) {
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
