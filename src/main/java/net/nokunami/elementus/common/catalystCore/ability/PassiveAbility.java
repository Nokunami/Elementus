package net.nokunami.elementus.common.catalystCore.ability;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.nokunami.elementus.common.capability.abilityData.AbilityData;

import java.util.function.Predicate;

import static net.nokunami.elementus.Elementus.modLoc;

public abstract class PassiveAbility extends AbstractAbility {

    @Override public AbilityProperties properties() { return new AbilityProperties(0); }

    public void onHurtEvent(LivingHurtEvent event) { }
    public boolean applyCooldownOnHurt(LivingEntity entity) { return false; }

    public void onDamageEvent(LivingDamageEvent event) { }
    public boolean applyCooldownOnDamage(LivingEntity entity) { return false; }

    public void onDeathEvent(LivingDeathEvent event) { }
    public boolean applyCooldownOnDeath(LivingEntity entity) { return false; }

    public void onEffect(MobEffectEvent event) { }

    @Override public final boolean isReadyToCast(LivingEntity entity) { return false; }

//    @Override public ResourceLocation abilityIcon() { return null; }
}
