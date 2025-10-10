package net.nokunami.elementus.common.catalystCore.ability;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.TrackingEmitter;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.nokunami.elementus.common.catalystCore.Abilities;
import net.nokunami.elementus.common.config.catalystConfigs.CatalystArmorConfig;
import net.nokunami.elementus.common.entity.MobUtil;
import net.nokunami.elementus.common.network.CatalystDeathItemDisplayPacket;
import net.nokunami.elementus.common.network.CatalystDeathParticlePacket;
import net.nokunami.elementus.common.network.ModNetwork;
import net.nokunami.elementus.common.registry.CustomRegistries;
import net.nokunami.elementus.common.registry.ModMobEffects;

public class TotemOfUndyingCatalystAbility extends CatalystAbility {

    public TotemOfUndyingCatalystAbility() {
        super(new Abilities.Builder()
                .cheatDeath(() -> new MobEffectInstance(MobEffects.REGENERATION, CatalystArmorConfig.totem_RegenDuration, CatalystArmorConfig.totem_RegenAmp))
                .cheatDeath(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, CatalystArmorConfig.totem_FireResDuration, CatalystArmorConfig.totem_FireResAmp))
                .cheatDeath(() -> new MobEffectInstance(MobEffects.ABSORPTION, CatalystArmorConfig.totem_AbsorbDuration, CatalystArmorConfig.totem_AbsorbAmp))
                .cheatDeath(() -> new MobEffectInstance(ModMobEffects.ElementusEffects.TOTEM_COOLDOWN.get(), CatalystArmorConfig.totem_Cooldown))
                .build());
    }

    @Override
    public void postDeathEvent(LivingDeathEvent event) {
        LivingEntity entity = event.getEntity();
        entity.removeAllEffects();
        super.postDeathEvent(event);
        event.setCanceled(true);
        entity.setHealth(1);
        MobUtil.playEntitySound(entity, SoundEvents.TOTEM_USE);
        if (entity instanceof Player) {
            if (!entity.getItemBySlot(EquipmentSlot.CHEST).isEmpty())
                ModNetwork.INSTANCE.sendToServer(new CatalystDeathItemDisplayPacket(EquipmentSlot.CHEST));
        }
        Minecraft mc = Minecraft.getInstance();
        mc.particleEngine.createTrackingEmitter(entity, ParticleTypes.TOTEM_OF_UNDYING, 30);
    }
}