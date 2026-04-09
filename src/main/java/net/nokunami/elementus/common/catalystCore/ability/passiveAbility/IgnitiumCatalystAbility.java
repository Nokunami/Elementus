package net.nokunami.elementus.common.catalystCore.ability.passiveAbility;

import com.github.L_Ender.cataclysm.init.ModEffect;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.nokunami.elementus.common.catalystCore.ability.PassiveCatalystAbility;
import net.nokunami.elementus.common.entity.MobUtil;
import net.nokunami.elementus.common.registry.EMobEffects;

import static net.nokunami.elementus.ModChecker.cataclysm;
import static net.nokunami.elementus.common.config.catalystConfigs.CatalystArmorConfig.*;
import static net.nokunami.elementus.common.entity.MobUtil.EffectApplier.EAInst;
import static net.nokunami.elementus.common.item.unique.CatalystItemUtil.setTextureType;
import static net.nokunami.elementus.common.item.unique.CatalystItemUtil.getTextureType;

public class IgnitiumCatalystAbility extends PassiveCatalystAbility {

    @Override
    public void tick(Level level, Entity entity) {
        if (entity instanceof LivingEntity living) {
            ItemStack itemStack = living.getItemBySlot(EquipmentSlot.CHEST);
            if (MobUtil.healthPercent(entity, 0.5F)) {
//                super.tickEffect(level, entity);
//                MobUtil.applyEffect(entity, MobEffects.DIG_SPEED, ignitium_HasteDuration, ignitium_HasteAmp, false, false, true);
//                MobUtil.applyEffect(entity, MobEffects.DAMAGE_BOOST, ignitium_StrengthDuration, ignitium_StrengthAmp, false, false, true);
//                MobUtil.applyEffect(entity, MobEffects.DAMAGE_RESISTANCE, ignitium_ResistanceDuration, ignitium_ResistanceAmp, false, false, true);
//                EAInst(living, MobEffects.DIG_SPEED).stats(ignitium_HasteDuration, ignitium_HasteAmp).showIconOnly().apply();
//                EAInst(living, MobEffects.DIG_SPEED).stats(ignitium_StrengthDuration, ignitium_StrengthAmp).showIconOnly().apply();
//                EAInst(living, MobEffects.DIG_SPEED).stats(ignitium_ResistanceDuration, ignitium_ResistanceAmp).showIconOnly().apply();
//                setTextureType(itemStack, 1);
            }
            if (cataclysm) {
                if (living.hasEffect(ModEffect.EFFECTBLAZING_BRAND.get())) {
                    living.playSound(SoundEvents.FIRE_EXTINGUISH);
                    living.removeEffect(ModEffect.EFFECTBLAZING_BRAND.get());
//                    if (getTextureType(itemStack) > 0) setTextureType(itemStack, 0);
                }
            }
        }
    }

    @Override
    public void postDamageEvent(LivingDamageEvent event) {
        Entity attacker = event.getSource().getEntity();
        if (cataclysm) {
            if (event.getSource() != null && attacker != null) {
                if (attacker instanceof LivingEntity && attacker != event.getEntity()) {
                    if (event.getEntity().getRandom().nextFloat() < 0.5F) {
                        MobEffectInstance blazingBrand = ((LivingEntity) attacker).getEffect(ModEffect.EFFECTBLAZING_BRAND.get());
                        int i = 1;
                        if (blazingBrand != null) {
                            i += blazingBrand.getAmplifier();
                            ((LivingEntity) attacker).removeEffectNoUpdate(ModEffect.EFFECTBLAZING_BRAND.get());
                        } else {
                            --i;
                        }

                        i = Mth.clamp(i, 0, 4);
                        MobEffectInstance effectinstance = new MobEffectInstance(ModEffect.EFFECTBLAZING_BRAND.get(), 100, i, false, false, true);
                        ((LivingEntity) attacker).addEffect(effectinstance);

                        if (!attacker.isOnFire()) {
                            attacker.setSecondsOnFire(5);
                        }
                    }
                }
            }
        }
    }
}