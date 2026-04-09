package net.nokunami.elementus.common.catalystCore.ability.passiveAbility;

import com.github.L_Ender.cataclysm.Cataclysm;
import com.github.L_Ender.cataclysm.init.ModEffect;
import com.github.L_Ender.cataclysm.init.ModParticle;
import com.github.L_Ender.cataclysm.message.MessageParticle;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.network.PacketDistributor;
import net.nokunami.elementus.common.catalystCore.ability.PassiveCatalystAbility;
import net.nokunami.elementus.common.config.catalystConfigs.CatalystArmorConfig;
import net.nokunami.elementus.common.entity.MobUtil;
import net.nokunami.elementus.common.network.ENetwork;
import net.nokunami.elementus.common.network.client.CatalystItemDisplayS2CPacket;

import static net.nokunami.elementus.ModChecker.cataclysm;
import static net.nokunami.elementus.common.entity.MobUtil.EffectApplier.EAInst;

public class CursiumCatalystAbility extends PassiveCatalystAbility {

    @Override
    public void postDamageEvent(LivingDamageEvent event) {
        Entity attacker = event.getSource().getEntity();
        if (cataclysm) {
            if (event.getSource() != null && attacker != null) {
                if (event.getEntity().hasEffect(ModEffect.EFFECTGHOST_FORM.get())) {
                    if (!event.getSource().is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
                        event.setCanceled(true);
                    }
                }
                if (event.getSource().is(DamageTypeTags.IS_PROJECTILE)) {
                    if (event.getEntity().getRandom().nextFloat() < CatalystArmorConfig.cursium_ProjectileDodgeChance) {
                        event.setCanceled(true);
                    }
                } else if (!event.getSource().is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
                    if (event.getEntity().getRandom().nextFloat() < CatalystArmorConfig.cursium_DodgeChance) {
                        event.setCanceled(true);
                    }
                }
            }
        }
    }

    @Override
    public void postDeathEvent(LivingDeathEvent event) {
        LivingEntity entity = event.getEntity();
        DamageSource source = event.getSource();
        if (cataclysm && !entity.level().isClientSide) {
            if (!source.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
                if(tryCursiumPlateRebirth(event.getEntity())) {
                    event.setCanceled(true);
//                    MobUtil.applyEffect(entity, MobEffects.FIRE_RESISTANCE, CatalystArmorConfig.cursium_FireResistDuration);
//                    MobUtil.applyEffect(entity, ModEffect.EFFECTGHOST_FORM, CatalystArmorConfig.cursium_GhostFormDuration);
                    EAInst(entity, MobEffects.FIRE_RESISTANCE).stats(CatalystArmorConfig.cursium_FireResistDuration).apply();
                    EAInst(entity, ModEffect.EFFECTGHOST_FORM).stats(CatalystArmorConfig.cursium_GhostFormDuration).apply();
                }
            }
//            if (entity instanceof ServerPlayer player)
//                if (!entity.getItemBySlot(EquipmentSlot.CHEST).isEmpty())
//                    ENetwork.sendTo(player, new CatalystItemDisplayS2CPacket(EquipmentSlot.CHEST));
        }
    }


    private static boolean tryCursiumPlateRebirth(LivingEntity living) {
        if (cataclysm) { // Copy-pasted from Cataclysm's ServerEventHandler
            if (!living.hasEffect(ModEffect.EFFECTGHOST_SICKNESS.get()) && !living.hasEffect(ModEffect.EFFECTGHOST_FORM.get())) {
                living.setHealth(5.0F);
                double d0 = living.getX();
                double d1 = living.getY() + 0.3F;
                double d2 = living.getZ();
                float size = 3.0F;
                for (ServerPlayer serverplayer : ((ServerLevel) living.level()).players()) {
                    if (serverplayer.distanceToSqr(Vec3.atCenterOf(living.blockPosition())) < 1024.0D) {
                        MessageParticle particlePacket = new MessageParticle();
                        for (float i = -size; i <= size; ++i) {
                            for (float j = -size; j <= size; ++j) {
                                for (float k = -size; k <= size; ++k) {
                                    double d3 = (double) j + (living.getRandom().nextDouble() - living.getRandom().nextDouble()) * 0.5D;
                                    double d4 = (double) i + (living.getRandom().nextDouble() - living.getRandom().nextDouble()) * 0.5D;
                                    double d5 = (double) k + (living.getRandom().nextDouble() - living.getRandom().nextDouble()) * 0.5D;
                                    double d6 = (double) Mth.sqrt((float) (d3 * d3 + d4 * d4 + d5 * d5)) / 0.5 + living.getRandom().nextGaussian() * 0.05D;
                                    particlePacket.queueParticle(ModParticle.CURSED_FLAME.get(),false, d0 , d1, d2, d3 / d6, d4 / d6, d5 / d6);
                                    if (i != -size && i != size && j != -size && j != size) {
                                        k += size * 2 - 1;
                                    }
                                }
                            }
                        }
                        Cataclysm.NETWORK_WRAPPER.send(PacketDistributor.PLAYER.with(() -> serverplayer), particlePacket);
                    }
                }
                return true;
            }
        }
        return false;
    }
}