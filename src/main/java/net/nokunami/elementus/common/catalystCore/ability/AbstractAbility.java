package net.nokunami.elementus.common.catalystCore.ability;

import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.nokunami.elementus.common.capability.abilityData.AbilityData;
import net.nokunami.elementus.common.capability.abilityData.CooldownData;
import net.nokunami.elementus.common.registry.ERegistries;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

import static net.nokunami.elementus.Elementus.modLoc;

public abstract class AbstractAbility {
    public static final List<AbstractAbility> ABILITIES = new ArrayList<>();
    public static final Map<String, AbstractAbility> ABILITIES_MAP = new IdentityHashMap<>();
    private String id;

//    public AbstractAbility() { }

    public abstract AbilityProperties properties();

    public String getId() { return id; }
    public String getOrCreateDescriptionId() { return id == null ? Util.makeDescriptionId("ability", ERegistries.abilities().getKey(this)) : id; }
    public String getDescriptionId() { return getOrCreateDescriptionId(); }
    public Component getDescription() { return Component.translatable(getDescriptionId()); }

    public ResourceLocation get() { return ERegistries.abilities().getKey(this); }
    public String getAsString() { return get().toString(); }

//    public final AbilityType getType() { return abilityProperties().getAbilityType(); }
//    public boolean isType(AbilityType type) { return type == abilityProperties().getAbilityType(); }
//    public boolean isTriggered() { return properties().getAbilityType().isTriggered() && getCastDuration() <= 0; }
//    public boolean isDelayedTrigger() { return properties().getAbilityType().isTriggered() && getCastDuration() > 0; }
    public boolean isInstant() { return getPreCast() <= 0; }
//    public boolean isTriggered() { return getPreCast() <= 0; }

    public int getCooldown() { return properties().getCooldown(); }
    public boolean isConstant() { return properties().getCastType().equals(CastType.CONSTANT); }
    public boolean isToggled() { return properties().getCastType().equals(CastType.TOGGLED); }
    public boolean castOnRelease() { return properties().castOnRelease(); }
    public int getPreCast() { return properties().getPreCastDuration(); }
    public int getCastDuration() { return properties().getCastDuration(); }
    public int getCharges() { return properties().getCharges(); }

    public final boolean castOnTick(int tick) { return tick >= Math.max(getPreCast() - 1, 0); }

    public boolean isReadyToCast(LivingEntity entity) {
        AbilityData data = AbilityData.instance(entity);
        return !data.isOnCooldown(this);
    }

    /**
     * Only ticks if {@link AbstractAbility#getCastDuration()} is more than 0 or ability is an instance of {@link PassiveAbility}.<br>
     * Override this to implement custom tick logic to ability
     *
     * @param entity casting entity
     * @param level level
     */
    public void onCastTick(LivingEntity entity, Level level) { }

    public boolean castAbility(LivingEntity entity, Level level, AbilityData data) { return false; }

    /**
     * Fires at the start of a cast.<br>
     *
     * @param entity casting entity
     * @param level level
     * @param data entity's ability data
     */
    public void onCastStart(LivingEntity entity, Level level, AbilityData data) { }

    /**
     * Fires at the end of a cast.<br>
     *
     * @param entity casting entity
     * @param level level
     * @param data entity's ability data
     */
    public void onCastStop(LivingEntity entity, Level level, AbilityData data) { }

    public void saveAdditionalData(AbilityData ca) { }
    public void loadAdditionalData(AbilityData ca) { }

    public ResourceLocation abilityIcon() {
        return modLoc(get().getNamespace(), "textures/gui/catalyst/ability/" + get().getPath() + ".png");
    }

    public Component failedCastMessage(LivingEntity entity, AbstractAbility ability) {
        return Component.translatableWithFallback("catalyst_ability.elementus.cooldown", "%s is on cooldown", getDescription());
    }

    public final boolean showOnClient(CooldownData data) {
        return !(this instanceof PassiveAbility) || data.isOnCooldown(this);
    }

    public static class AbilityProperties {
        CastType castType = CastType.CONSTANT;
        boolean onRelease = false;
        int cooldown;
        int preCastDuration;
        int castDuration;
        int charge;

        public AbilityProperties(int cooldownTicks) { cooldown = cooldownTicks; }

        public CastType getCastType() { return castType; }
        public boolean isConstant() { return castType.equals(CastType.CONSTANT); }
        public boolean isToggled() { return castType.equals(CastType.TOGGLED); }
        public boolean castOnRelease() { return onRelease; }
        public int getCooldown() { return cooldown; }
        public int getPreCastDuration() { return preCastDuration; }
        public int getCastDuration() { return castDuration; }
        public int getCharges() { return charge; }

        public AbilityProperties setCooldown(int i) { cooldown = i; return this; }
        public AbilityProperties setCastType(CastType type) { castType = type; return this; }
        public AbilityProperties setCastOnRelease(boolean b) { onRelease = b; return this; }
        public AbilityProperties setPreCastDuration(int i) { preCastDuration = i; return this; }
        public AbilityProperties setCastDuration(int i) { castDuration = i; return this; }
        public AbilityProperties setCharges(int i) { charge = i; return this; }
    }
}