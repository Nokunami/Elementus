package net.nokunami.elementus.event;

import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterItemDecorationsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.nokunami.elementus.client.gui.ItemBarItemDecoration;
import net.nokunami.elementus.common.item.unique.ChargeBladeItem;
import net.nokunami.elementus.common.network.ChargeBladeAbilityPacket;
import net.nokunami.elementus.common.network.ModNetwork;
import net.nokunami.elementus.common.registry.ModItems.ElementusItems;

import static net.nokunami.elementus.Elementus.MODID;

@EventBusSubscriber(modid = MODID, value = Dist.CLIENT)
public class ClientEvents {

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
            ItemStack itemStack;
            InteractionHand hand;
            ItemStack usedItem = player.getUseItem();
            Item item = usedItem.getItem();
            // NOTE: To prevent erroneous hand swinging, the attack keybind needs to be 'consumed' so it isn't used after this
            if(item instanceof ChargeBladeItem && mc.options.keyAttack.consumeClick()) {
                itemStack = player.getUseItem();
                hand = player.getUsedItemHand();
            } else return;

            if(player.getCooldowns().isOnCooldown(itemStack.getItem())) return;

//            player.swing(hand, true);
            ModNetwork.sendToServer(new ChargeBladeAbilityPacket(hand));
        }
    }

    public static void itemDecorations(RegisterItemDecorationsEvent event) {
//        event.register(ElementusItems.CATALYST_CHESTPLATE.get(), new CatalystCoreItemDecoration());
        event.register(ElementusItems.DIARKRITE_CHARGE_BLADE.get(), new ItemBarItemDecoration());
        event.register(ElementusItems.ANTHEKTITE_CHARGE_BLADE.get(), new ItemBarItemDecoration());

        event.register(ElementusItems.MOVCADIA_SWORD.get(), new ItemBarItemDecoration());
        event.register(ElementusItems.MOVCADIA_SHOVEL.get(), new ItemBarItemDecoration());
        event.register(ElementusItems.MOVCADIA_PICKAXE.get(), new ItemBarItemDecoration());
        event.register(ElementusItems.MOVCADIA_AXE.get(), new ItemBarItemDecoration());
        event.register(ElementusItems.MOVCADIA_HOE.get(), new ItemBarItemDecoration());
    }
}
