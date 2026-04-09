package net.nokunami.elementus.common.catalystCore.ability;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class PassiveCatalystAbility {

    public void tick(Level level, Entity entity) {
    }

    public void postHurtEvent(LivingHurtEvent event) {
    }

    public void postDamageEvent(LivingDamageEvent event) {
    }

    public void postDeathEvent(LivingDeathEvent event) {
    }
}