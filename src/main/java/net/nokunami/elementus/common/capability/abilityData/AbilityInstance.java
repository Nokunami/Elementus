package net.nokunami.elementus.common.capability.abilityData;

import net.minecraft.nbt.CompoundTag;
import net.nokunami.elementus.common.catalystCore.ability.AbstractAbility;
import net.nokunami.elementus.common.catalystCore.ability.CastState;
import net.nokunami.elementus.common.registry.ERegistries;

import javax.annotation.Nullable;

public class AbilityInstance {
    private int prevSelection = 0;
    private int selection = 0;
    private AbstractAbility currentAbility = null;
    private int preCastDuration = 0;
    private int preCastTick = 0;
    private int castDuration = 0;
    private int castTick = 0;
    private CastState castState = CastState.NOT;
    private boolean applyCooldown;

    public int getPrevSelection() { return prevSelection; }
    public void setPrevSelection(int i) { prevSelection = i; }

    public int getSelection() { return selection; }
    public void setSelection(int i) { selection = i; }

    public AbstractAbility getAbility() { return currentAbility; }
    public void setAbility(AbstractAbility ability) { currentAbility = ability; }

    public int getPreCastDuration() { return preCastDuration; }
    public void setPreCastDuration(int i) { preCastDuration = i; }

    public int getPreCastTick() { return preCastTick; }
    public void setPreCastTick(int i) { preCastTick = i; }

    public int getCastDuration() { return castDuration; }
    public void setCastDuration(int i) { castDuration = i; }

    public int getCastTick() { return castTick; }
    public void setCastTick(int i) { castTick = i; }

    public CastState getCastState() { return castState; }
    public void setCastState(CastState state) { castState = state; }

    public boolean shouldApplyCooldown() { return applyCooldown; }
    public void setApplyCooldown(boolean b) { applyCooldown = b; }

    public void setCastTime(AbstractAbility ability) {
        setPreCastDuration(ability.getPreCast());
        setCastDuration(ability.getCastDuration());
    }

    public void clearCastTime() {
        setPreCastDuration(0);
        setPreCastTick(0);
        setCastDuration(0);
        setCastTick(0);
    }

    public void applyCooldown(AbilityData data) {
        if (shouldApplyCooldown())
            if (getAbility() != null)
                data.addCooldown(getAbility());
    }

    public void save(CompoundTag tag) {
        var instTag = new CompoundTag();
        instTag.putInt("PrevSelection", getPrevSelection());
        instTag.putInt("Selection", getSelection());
        if (getAbility() != null)
            instTag.putString("CurrentAbility", getAbility().getAsString());
        if (getPreCastDuration() > 0) {
            instTag.putInt("PreCastDuration", getPreCastDuration());
            instTag.putInt("PreCastTick", getPreCastTick());
        }
        if (getCastDuration() > 0) {
            instTag.putInt("CastDuration", getCastDuration());
            instTag.putInt("CastTick", getCastTick());
        }

        tag.put("AbilityInstance", instTag);
    }

    public void load(CompoundTag tag) {
        if (tag.contains("AbilityInstance")) {
            var instTag = tag.getCompound("AbilityInstance");
            setPrevSelection(instTag.getInt("PrevSelection"));
            setSelection(instTag.getInt("Selection"));
            if (instTag.contains("CurrentAbility")) {
                var abilityTag = instTag.getString("CurrentAbility");
                var ability = ERegistries.getAbility(abilityTag);
                if (ability != null) setAbility(ability);
            }
            if (instTag.contains("PreCastDuration")) {
                setPreCastDuration(instTag.getInt("PreCastDuration"));
                setPreCastTick(instTag.getInt("PreCastTick"));
            }
            if (tag.contains("CastDuration")) {
                setCastDuration(instTag.getInt("CastDuration"));
                setCastTick(instTag.getInt("CastTick"));
            }
        }
    }
}
