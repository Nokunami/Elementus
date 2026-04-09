package net.nokunami.elementus.common.catalystCore.ability;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.nokunami.elementus.common.capability.cAbility.CAbility;
import net.nokunami.elementus.common.network.ENetwork;
import net.nokunami.elementus.common.network.client.SyncCastStateS2CPacket;
import net.nokunami.elementus.common.registry.CustomRegistries;
import net.nokunami.elementus.common.registry.EGameRules;

import static net.nokunami.elementus.common.registry.CustomRegistries.CatalystCoreHelper.CatalystCoreUtil;

public class AbilityCastManager {

    public static void onServerCastStart(ServerPlayer player) {
        var helper = CatalystCoreUtil(player);
        var ca = CAbility.instance(player);
        var level = player.level();
        boolean castState = false;

        if (!helper.getAbilities().isEmpty()) {
            int selection = Mth.clamp(ca.getSelection(), 0, helper.getAbilities().size() - 1);
            ca.setCurrentAbility(helper.getAbility(selection));
            var ability = CustomRegistries.getAbility(ca.getCurrentAbilityId());

            if (ability.isReadyToCast(player, null)) {
                ability.onCastStart(player, level);
//                ability.attemptCast(player, level, ability.isTriggered());
                if (ability.isTriggered()) {
                    ability.castAbility(player, level, ca);
                    ability.onCastStop(player, level, ca);
                    ca.addCooldown(player, ability);
                } else {
                    castState = true;
                    ca.setCastKeyDown(castState);
                    ca.setCastTicking(true);
                }
            } else {
//                player.displayClientMessage(Component.translatableWithFallback("catalyst_ability.elementus.cooldown", "%s is on cooldown", ability.getDescription()), true);
                player.displayClientMessage(ability.failedCastMessage(player, ability), true);
            }
            ENetwork.sendTo(player, new SyncCastStateS2CPacket(castState, ability.getCastDuration()));
        }
    }

    public static void onServerCast(ServerPlayer player) {
        var ca = CAbility.instance(player);
        var level = player.level();

        var ability = CustomRegistries.getAbility(ca.getCurrentAbilityId());
        if (ca.isCastTicking() && ability.getType().isConstant()) {
            ability.castAbility(player, level, ca);
        }
    }

    public static void onServerCastStop(ServerPlayer player) {
        var ca = CAbility.instance(player);
        var level = player.level();
        var ability = ca.getStoredAbility();
        boolean shouldAddCooldown = false;

        if (EGameRules.isDebugModeOn(level)) player.sendSystemMessage(Component.literal("cancel test"));
        if (ability.isTriggered()) return;

        ca.setCastKeyDown(false);
        ENetwork.sendTo(player, new SyncCastStateS2CPacket(false, 0));
        if (!ca.isOnCooldown(ability) && player.isAlive()) {
            if (ability.castOnTick(ca.getCastTick()) && !ca.isCastKeyDown()) {
                ability.onCastStop(player, level, ca);
                shouldAddCooldown = ability.castAbility(player, level, ca);
            }
        }
        ca.setCastTicking(false);
        ca.setCastTick(0);
        if (shouldAddCooldown) ca.addCooldown(player, ability);
    }
}
