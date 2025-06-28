package net.nokunami.elementus.event;

import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.nokunami.elementus.common.item.ChargeSwordItem;
import net.nokunami.elementus.common.item.DiarkriteChargeBlade;
import net.nokunami.elementus.common.network.ModNetwork;
import net.nokunami.elementus.common.network.ChargeBladeAbilityPacket;

import static net.nokunami.elementus.Elementus.MODID;

@EventBusSubscriber(modid = MODID, value = Dist.CLIENT)
public class ModClientInputEvents {

    /// Code from SpartanObliviousSpartan's SpartanShields mod
    @SubscribeEvent
    public static void onMouseInputEvent(InputEvent.MouseButton ev) {
        Minecraft mc = Minecraft.getInstance();

        Player player = mc.player;

        // Ensure the following
        // - Shield Bashing is NOT disabled
        // - The game is NOT paused
        // - The game is NOT in any GUI
        // - The game is loaded into a world
        // - The player is valid. If there is no valid player, do not execute this event as it will cause a crash
        // If not, then don't continue the attack
        if(mc.level == null || mc.screen != null || Minecraft.getInstance().isPaused() || player == null) return;

        if(player.isUsingItem()) {
            ItemStack shieldStack;
            InteractionHand shieldHand;
            ItemStack usedItem = player.getUseItem();
            // NOTE: To prevent erroneous hand swinging, the attack keybind needs to be 'consumed' so it isn't used after this
            if(usedItem.getItem() instanceof ChargeSwordItem && mc.options.keyAttack.consumeClick()) {
                shieldStack = player.getUseItem();
                shieldHand = player.getUsedItemHand();
            }
            else return;

//			Log.info("Bashing hand: " + shieldHand);
            if(player.getCooldowns().isOnCooldown(shieldStack.getItem()))
                return;

            player.swing(shieldHand, true);
            ModNetwork.sendToServer(new ChargeBladeAbilityPacket(shieldHand));
        }
    }
}
