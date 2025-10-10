package net.nokunami.elementus.common.catalystCore.ability;

import com.github.L_Ender.cataclysm.init.ModEffect;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.nokunami.elementus.common.catalystCore.Abilities;

import static net.nokunami.elementus.ModChecker.cataclysm;
import static net.nokunami.elementus.common.item.unique.CatalystItemUtil.*;

public class EssenceOfStormCatalystAbility extends CatalystAbility {

    public EssenceOfStormCatalystAbility() {
        super(new Abilities.Builder().build());
    }

    @Override
    public void tick(Level level, Entity entity) {
        if (entity instanceof LivingEntity living) {
            ItemStack itemStack = living.getItemBySlot(EquipmentSlot.CHEST);
//            if (MobUtil.healthPercent(entity, 0.5F))
//                super.tickEffect(level, entity);
            if (cataclysm) {
                if (living.hasEffect(ModEffect.EFFECTWETNESS.get())) {
                    living.playSound(SoundEvents.GENERIC_SPLASH);
                    living.removeEffect(ModEffect.EFFECTWETNESS.get());
                    if (getTextureType(itemStack) > 0) changeTextureType(itemStack, 0);
                }
            }
        }
    }

    @Override
    public void postDamageEvent(LivingDamageEvent event) {
        Entity attacker = event.getSource().getEntity();
//        if (cataclysm) {
//            if (event.getSource() != null && attacker != null) {
//                if (event.getEntity().hasEffect(ModEffect.EFFECTGHOST_FORM.get())) {
//                    if (!event.getSource().is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
//                        event.setCanceled(true);
//                    }
//                }
//                if (event.getSource().is(DamageTypeTags.IS_PROJECTILE)) {
//                    if (event.getEntity().getRandom().nextFloat() < CatalystArmorConfig.cursium_ProjectileDodgeChance) {
//                        event.setCanceled(true);
//                    }
//                } else if (!event.getSource().is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
//                    if (event.getEntity().getRandom().nextFloat() < CatalystArmorConfig.cursium_DodgeChance) {
//                        event.setCanceled(true);
//                    }
//                }
//            }
//        }
    }
}