package net.nokunami.elementus.client;

import net.nokunami.elementus.common.capability.abilityData.AbilityData;
import net.nokunami.elementus.common.capability.abilityData.CooldownData;
import net.nokunami.elementus.common.catalystCore.ability.AbilityStorage;
import net.nokunami.elementus.common.catalystCore.ability.AbilityStorageInstance;
import net.nokunami.elementus.common.catalystCore.ability.AbstractAbility;

import java.util.List;

public class AbilityDataClient {
    private static final AbilityData ABILITY_DATA = new AbilityData();

    public static CooldownData getCooldowns() { return ABILITY_DATA.getCooldowns(); }

    public static void setCastKeyDown(boolean set) { ABILITY_DATA.setCastKeyDown(set); }
    public static boolean isCastKeyDown() { return ABILITY_DATA.isCastKeyDown(); }

    public static void scroll(int prev, int next) { ABILITY_DATA.scroll(prev, next); }

    public static int getPreCastDuration() { return ABILITY_DATA.getPreCastDuration(); }
    public static void setPreCastDuration(int i) { ABILITY_DATA.setPreCastDuration(i); }

    public static int getPreCastTick() { return ABILITY_DATA.getPreCastTick(); }
    public static void setPreCastTick(int i) { ABILITY_DATA.setPreCastTick(i); }

    public static int getCastDuration() { return ABILITY_DATA.getCastDuration(); }
    public static void setCastDuration(int i) { ABILITY_DATA.setCastDuration(i); }

    public static int getCastTick() { return ABILITY_DATA.getCastTick(); }
    public static void setCastTick(int i) { ABILITY_DATA.setCastTick(i); }

    public static AbilityStorage getAbilityStorage() { return ABILITY_DATA.getAbilityStorage(); }

    public static void addAbilities(List<AbilityStorageInstance> list) {
        getAbilityStorage().getAbilitiesInStorage().clear();
        getAbilityStorage().addAbilities(list);
    }

    public static float getCooldownPercent(AbstractAbility ability) {
        return getCooldowns().getCooldownPercent(ability);
    }

    public static float getPreCastProgress() { return (float) getPreCastTick() / getPreCastDuration(); }
    public static float getCastProgress() { return (float) getCastTick() / getCastDuration(); }

    public static void updateCastState(boolean key, int preCast, int cast) {
        setCastKeyDown(key);
        setPreCastDuration(preCast);
        setCastDuration(cast);
    }

    public static void clientTick() {
        getCooldowns().tick(null);
//        setCastTick(isCastKeyDown() ? getCastTick() + 1 : 0);
        setPreCastTick(isCastKeyDown() ? Math.min(getPreCastTick() + 1, getPreCastDuration()) : 0);
        setCastTick(getPreCastTick() >= getPreCastDuration() && getCastTick() < getCastDuration() ? Math.min(getCastTick() + 1, getCastDuration()) : 0);
    }

    public static boolean showCastBars() { return getPreCastDuration() > 0 || getCastDuration() > 0; }
    public static boolean showPreCastBar() { return getPreCastDuration() > 0; }
    public static boolean showCastBar() { return getCastDuration() > 0; }
}