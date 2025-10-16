package net.nokunami.elementus.common.catalystCore.passiveAbility;

import com.github.L_Ender.cataclysm.init.ModEffect;
import com.github.L_Ender.cataclysm.init.ModSounds;
import com.github.L_Ender.cataclysm.util.CMDamageTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.nokunami.elementus.common.catalystCore.PassiveCatalystAbility;

import static net.nokunami.elementus.ModChecker.cataclysm;
import static net.nokunami.elementus.common.entity.MobUtil.playEntitySound;
import static net.nokunami.elementus.common.item.unique.CatalystItemUtil.changeTextureType;
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
                    if (getTextureType(itemStack) > 0) changeTextureType(itemStack, 0);
                }
            }
        }
    }

    @Override
    public void postDamageEvent(LivingDamageEvent event) {
        Entity attacker = event.getSource().getEntity();
        if (cataclysm) {
            if (event.getSource() != null && attacker != null) {
                attacker.hurt(event.getEntity().damageSources().source(CMDamageTypes.LIGHTNING), 5);
                playEntitySound(event.getEntity(), ModSounds.SUPER_LIGHTNING);
            }
        }
    }
}