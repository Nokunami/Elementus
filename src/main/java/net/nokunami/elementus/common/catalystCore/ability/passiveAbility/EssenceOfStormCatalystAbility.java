package net.nokunami.elementus.common.catalystCore.ability.passiveAbility;

import com.github.L_Ender.cataclysm.init.ModEffect;
import com.github.L_Ender.cataclysm.init.ModSounds;
import com.github.L_Ender.cataclysm.util.CMDamageTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.nokunami.elementus.common.catalystCore.ability.PassiveCatalystAbility;
import net.nokunami.elementus.common.registry.CompatRegistryObjectGetter;

import static net.nokunami.elementus.ModChecker.cataclysm;
import static net.nokunami.elementus.common.entity.MobUtil.playEntitySound;
import static net.nokunami.elementus.common.item.unique.CatalystItemUtil.setTextureType;
import static net.nokunami.elementus.common.item.unique.CatalystItemUtil.getTextureType;

public class EssenceOfStormCatalystAbility extends PassiveCatalystAbility {

    @Override
    public void tick(Level level, Entity entity) {
        if (entity instanceof LivingEntity living) {
            ItemStack itemStack = living.getItemBySlot(EquipmentSlot.CHEST);
            if (cataclysm) {
                if (living.hasEffect(ModEffect.EFFECTWETNESS.get())) {
                    living.playSound(SoundEvents.GENERIC_SPLASH);
                    living.removeEffect(ModEffect.EFFECTWETNESS.get());
                    if (getTextureType(itemStack) > 0) setTextureType(itemStack, 0);
                }
            }
        }
    }

    @Override
    public void postDamageEvent(LivingDamageEvent event) {
        var attacker = event.getSource().getEntity();
        if (cataclysm) {
            if (event.getSource() != null && attacker != null) {
                if (event.getEntity().getRandom().nextFloat() < 0.5F) {
                    attacker.hurt(event.getEntity().damageSources().source(CMDamageTypes.LIGHTNING), 5);
                    playEntitySound(event.getEntity(), ModSounds.EMP_ACTIVATED);
                }
            }
        }
    }
}