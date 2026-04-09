package net.nokunami.elementus.common.catalystCore.ability.activeAbility;

import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.RegistryManager;
import net.nokunami.elementus.common.capability.cAbility.CAbility;
import net.nokunami.elementus.common.catalystCore.ability.AbilityType;

import java.util.*;

import static net.nokunami.elementus.Elementus.modLoc;
import static net.nokunami.elementus.common.registry.CustomRegistries.ABILITY_KEY;

public abstract class AbstractActiveAbility {
    public static final List<AbstractActiveAbility> ABILITIES = new ArrayList<>();
    public static final Map<String, AbstractActiveAbility> ABILITIES_MAP = new IdentityHashMap<>();
    String id;
    AbilityProperties properties;

    public AbstractActiveAbility(AbilityProperties abilityProperties) { properties = abilityProperties; }

    public final AbilityProperties getProperties() { return properties; }

    public String getId() { return id; }
    public String getOrCreateDescriptionId() {
        return id == null ? Util.makeDescriptionId("ability", RegistryManager.ACTIVE.getRegistry(ABILITY_KEY).getKey(this)) : id;
    }
    public String getDescriptionId() { return getOrCreateDescriptionId(); }
    public Component getDescription() { return Component.translatable(getDescriptionId()); }

    public ResourceLocation get() { return RegistryManager.ACTIVE.getRegistry(ABILITY_KEY).getKey(this); }
    public String getWithNamespace() { return get().getNamespace() != null ? get().toString() : "test" + "." + get().getPath(); }

    public final AbilityType getType() { return properties.getAbilityType(); }
    public boolean isType(AbilityType type) { return type == properties.getAbilityType(); }
    public boolean isTriggered() { return getProperties().getAbilityType().isTriggered() && getProperties().getCastDuration() <= 0; }
    public boolean isDelayedTrigger() { return getProperties().getAbilityType().isTriggered() && getProperties().getCastDuration() > 0; }
    public int getCooldown() { return properties.getCooldown(); }
    public int getCastDuration() { return properties.getCastDuration(); }
    public int getCharges() { return properties.getCharges(); }

    public final boolean castOnTick(int tick) {
        return tick >= Math.max(properties.getCastDuration() - 1, 0);
    }

    //    public abstract boolean isReadyToCast(Entity entity, ItemStack stack);

    public boolean isReadyToCast(LivingEntity entity, ItemStack stack) {
//        if (entity instanceof LivingEntity living) {
//            CAbility ca = CAbility.instance(living);
//            return !ca.isOnCooldown(this);
//        }
//        return false;
        CAbility ca = CAbility.instance(entity);
        return !ca.isOnCooldown(this);
    }

    /**
     * Tick method, does what it says on the tin.
     * Only ticks if ability type is set to .
     * Override this to implement custom tick logic to ability
     *
     * @param entity current holder
     * @param level  level
     */
    public void tick(Entity entity, Level level) { }

    public abstract boolean castAbility(LivingEntity entity, Level level, CAbility ca);

    public void onCastStart(LivingEntity entity, Level level) { }

    public void onCastStop(LivingEntity entity, Level level, CAbility ca) { }

    public boolean attemptCast(LivingEntity entity, Level level, boolean triggerCooldown) {
        if (!level.isClientSide) {
            if (entity instanceof ServerPlayer player) {
                CAbility ca = CAbility.instance(player);
                onCastStart(entity, level);
                castAbility(entity, level, ca);
                if (triggerCooldown) ca.addCooldown(entity, this);
            }
        } else {
            return false;
        }
        return false;
    }

    public ResourceLocation abilityIcon() {
        return modLoc(get().getNamespace(), "textures/gui/catalyst/ability/" + get().getPath() + ".png");
    }

    public static ResourceLocation icon(String string) {
        return modLoc("textures/gui/catalyst/ability/" + string + ".png");
    }

    public Component failedCastMessage(LivingEntity entity, AbstractActiveAbility ability) {
        return Component.translatableWithFallback("catalyst_ability.elementus.cooldown", "%s is on cooldown", ability.getDescription());
    }

    public static class AbilityProperties {
        AbilityType abilityType;
        int cooldown;
        int castDuration = 0;
        int charge = 0;

        public AbilityProperties(AbilityType type) {
            this(type, 0);
        }

        public AbilityProperties(AbilityType type, int cooldownDuration) {
            abilityType = type;
            cooldown = cooldownDuration;
        }

        public AbilityType getAbilityType() { return abilityType; }
        public int getCooldown() { return cooldown; }
        public int getCastDuration() { return castDuration; }
        public int getCharges() { return charge; }

        public AbilityProperties setCastDuration(int i) {
            castDuration = i;
            return this;
        }
        public AbilityProperties setDefaultCharge(int i) {
            charge = i;
            return this;
        }
    }
}
