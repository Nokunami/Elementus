package net.nokunami.elementus.event;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.nokunami.elementus.common.Etags;
import net.nokunami.elementus.common.item.DiarkriteChargeBlade;
import net.nokunami.elementus.common.network.ModNetwork;
import net.nokunami.elementus.common.network.SonicBurstPacket;

import static net.nokunami.elementus.Elementus.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ModClientEvents {

    @SubscribeEvent
    public void catalystCoreItem(ItemTooltipEvent event) {
        Item item = event.getItemStack().getItem();
        ItemStack itemStack = event.getItemStack();
        if (!itemStack.isEmpty() && itemStack.is(Etags.Items.CATALYST_ITEMS)) {
            event.getToolTip().add(Component.translatable("elementus.catalyst_core_item.desc"));
        }
    }

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
            if(usedItem.getItem() instanceof DiarkriteChargeBlade && mc.options.keyAttack.consumeClick()) {
                shieldStack = player.getUseItem();
                shieldHand = player.getUsedItemHand();
            }
            else return;

//			Log.info("Bashing hand: " + shieldHand);
            if(player.getCooldowns().isOnCooldown(shieldStack.getItem()))
                return;

            player.swing(shieldHand, true);
            ModNetwork.sendToServer(new SonicBurstPacket(shieldHand));
        }
    }
}
