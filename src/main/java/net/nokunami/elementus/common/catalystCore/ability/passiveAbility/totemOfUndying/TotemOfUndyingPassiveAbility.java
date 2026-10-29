package net.nokunami.elementus.common.catalystCore.ability.passiveAbility.totemOfUndying;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.nokunami.elementus.common.catalystCore.ability.PassiveAbility;
import net.nokunami.elementus.common.network.ENet;
import net.nokunami.elementus.common.network.client.CatalystItemDisplayS2CPacket;
import net.nokunami.elementus.config.CatalystArmorConfig;
import net.nokunami.elementus.config.EConfig;

import static net.nokunami.elementus.api.util.EffectUtil.effectUtil;
import static net.nokunami.elementus.common.entity.MobUtil.EffectApplier.EAInst;

public class TotemOfUndyingPassiveAbility extends PassiveAbility {
    CatalystArmorConfig.totemOfUndying config = EConfig.catalystConfig.totemOfUndying;

    @Override
    public AbilityProperties properties() {
        return new AbilityProperties(config.cooldown);
    }

    @Override
    public void onDeathEvent(LivingDeathEvent event) {
        LivingEntity entity = event.getEntity();
        entity.removeAllEffects();
        effectUtil(entity, MobEffects.REGENERATION).add(config.regenDuration, config.regenAmp - 1);
        effectUtil(entity, MobEffects.FIRE_RESISTANCE).add(config.fireResDuration, config.fireResAmp - 1);
        effectUtil(entity, MobEffects.ABSORPTION).add(config.absorbDuration, config.absorbAmp - 1);

        event.setCanceled(true);
        entity.setHealth(1);
        if (entity instanceof LivingEntity) {
            if (!entity.getItemBySlot(EquipmentSlot.CHEST).isEmpty()) {
                if (entity instanceof ServerPlayer player) {
                    ENet.sendTo(player, new CatalystItemDisplayS2CPacket());
                    player.awardStat(Stats.ITEM_USED.get(Items.TOTEM_OF_UNDYING), 1);
                    CriteriaTriggers.USED_TOTEM.trigger(player, entity.getItemBySlot(EquipmentSlot.CHEST));
                }
                event.getEntity().level().broadcastEntityEvent(entity, (byte) 35);
            }
        }
    }

    @Override public boolean applyCooldownOnDeath(LivingEntity entity) { return true; }
}