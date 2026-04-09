package net.nokunami.elementus.common.catalystCore.ability.passiveAbility;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.nokunami.elementus.common.catalystCore.ability.PassiveCatalystAbility;
import net.nokunami.elementus.common.entity.MobUtil;
import net.nokunami.elementus.common.network.ENetwork;
import net.nokunami.elementus.common.network.client.CatalystDeathParticleS2CPacket;
import net.nokunami.elementus.common.network.client.CatalystItemDisplayS2CPacket;
import net.nokunami.elementus.common.registry.EMobEffects;

import static net.nokunami.elementus.common.config.catalystConfigs.CatalystArmorConfig.*;
import static net.nokunami.elementus.common.entity.MobUtil.EffectApplier.EAInst;

public class TotemOfUndyingCatalystAbility extends PassiveCatalystAbility {

    @Override
    public void postDeathEvent(LivingDeathEvent event) {
        LivingEntity entity = event.getEntity();
        entity.removeAllEffects();
//        MobUtil.applyEffect(entity, MobEffects.REGENERATION, totem_RegenDuration, totem_RegenAmp);
//        MobUtil.applyEffect(entity, MobEffects.REGENERATION, totem_FireResDuration, totem_FireResAmp);
//        MobUtil.applyEffect(entity, MobEffects.REGENERATION, totem_AbsorbDuration, totem_AbsorbAmp);
//        MobUtil.applyEffect(entity, EMobEffects.TOTEM_COOLDOWN, totem_Cooldown, 0);
        EAInst(entity, MobEffects.REGENERATION).stats(totem_RegenDuration, totem_RegenAmp).apply();
        EAInst(entity, MobEffects.FIRE_RESISTANCE).stats(totem_FireResDuration, totem_FireResAmp).apply();
        EAInst(entity, MobEffects.ABSORPTION).stats(totem_AbsorbDuration, totem_AbsorbAmp).apply();
        EAInst(entity, EMobEffects.TOTEM_COOLDOWN).stats(totem_Cooldown).apply();

        event.setCanceled(true);
        entity.setHealth(1);
        if (entity instanceof LivingEntity) {
            if (!entity.getItemBySlot(EquipmentSlot.CHEST).isEmpty()) {
                if (entity instanceof ServerPlayer player) {
                    ENetwork.sendTo(player, new CatalystItemDisplayS2CPacket());
                    player.awardStat(Stats.ITEM_USED.get(Items.TOTEM_OF_UNDYING), 1);
                    CriteriaTriggers.USED_TOTEM.trigger(player, entity.getItemBySlot(EquipmentSlot.CHEST));
                }
                event.getEntity().level().broadcastEntityEvent(entity, (byte) 35);
            }
        }
    }
}