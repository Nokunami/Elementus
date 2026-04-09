package net.nokunami.elementus.client;

import net.minecraft.server.level.ServerPlayer;
import net.nokunami.elementus.common.capability.cAbility.CACooldownInstance;
import net.nokunami.elementus.common.capability.cAbility.CAbility;
import net.nokunami.elementus.common.catalystCore.ability.activeAbility.AbstractActiveAbility;
import net.nokunami.elementus.common.network.ENetwork;
import net.nokunami.elementus.common.network.server.ability.CheckCastTickC2SPacket;

public class CAbilityClient {
    private static final CAbility cability = new CAbility();

    public static CACooldownInstance getCooldowns() { return cability.getCooldowns(); }

    public static boolean getCastState() { return cability.isCastKeyDown(); }
    public static void updateCastState(boolean key, int i) {
        cability.setCastKeyDown(key);
        cability.setAbilityCastDuration(i);
    }

    public static int getSelected() { return cability.getSelection(); }
    public static void setSelected(int i) { cability.setSelection(i); }

    public static float getCooldownPercent(AbstractActiveAbility ability) {
        return getCooldowns().getCooldownPercent(ability);
    }

    public static int getCastTick() { return cability.getCastTick(); }

    public static float getCastProgress() { return (float) getCastTick() / cability.getAbilityCastDuration(); }

    public static void clientCastTick() {
//        if (cability.isCastKeyDown()) {
//            cability.setCastTick(getCastTick() + 1);
//        } else {
//            cability.setCastTick(0);
//        }
        setClientCastTick(cability.isCastKeyDown() ? getCastTick() + 1 : 0);
    }
    public static void setClientCastTick(int i) {
        cability.setCastTick(i);
    }
}
