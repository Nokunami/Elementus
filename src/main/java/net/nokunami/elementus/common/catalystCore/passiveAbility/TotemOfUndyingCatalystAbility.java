package net.nokunami.elementus.common.catalystCore.passiveAbility;

import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.nokunami.elementus.common.catalystCore.PassiveCatalystAbility;
import net.nokunami.elementus.common.entity.MobUtil;
import net.nokunami.elementus.common.network.CatalystDeathItemDisplayPacket;
import net.nokunami.elementus.common.network.ModNetwork;
import net.nokunami.elementus.common.registry.EMobEffects;

import static net.nokunami.elementus.common.config.catalystConfigs.CatalystArmorConfig.*;

public class TotemOfUndyingCatalystAbility extends PassiveCatalystAbility {

    @Override
    public void postDeathEvent(LivingDeathEvent event) {
        LivingEntity entity = event.getEntity();
        entity.removeAllEffects();
        MobUtil.applyEffect(entity, MobEffects.REGENERATION, totem_RegenDuration, totem_RegenAmp);
        MobUtil.applyEffect(entity, MobEffects.REGENERATION, totem_FireResDuration, totem_FireResAmp);
        MobUtil.applyEffect(entity, MobEffects.REGENERATION, totem_AbsorbDuration, totem_AbsorbAmp);
        MobUtil.applyEffect(entity, EMobEffects.TOTEM_COOLDOWN, totem_Cooldown, 0);

        event.setCanceled(true);
        entity.setHealth(1);
        MobUtil.playEntitySound(entity, SoundEvents.TOTEM_USE);
        if (entity instanceof Player) {
            if (!entity.getItemBySlot(EquipmentSlot.CHEST).isEmpty())
                ModNetwork.INSTANCE.sendToServer(new CatalystDeathItemDisplayPacket(EquipmentSlot.CHEST));
            Minecraft mc = Minecraft.getInstance(); // Not sure if it works on servers... need to find solutions
            mc.particleEngine.createTrackingEmitter(entity, ParticleTypes.TOTEM_OF_UNDYING, 30);
        }
    }
}