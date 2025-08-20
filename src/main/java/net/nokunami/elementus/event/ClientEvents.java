package net.nokunami.elementus.event;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterItemDecorationsEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.nokunami.elementus.client.gui.ItemBarItemDecoration;
import net.nokunami.elementus.common.Etags;
import net.nokunami.elementus.common.item.unique.ChargeBladeItem;
import net.nokunami.elementus.common.network.ChargeBladeAbilityPacket;
import net.nokunami.elementus.common.network.ModNetwork;
import net.nokunami.elementus.common.registry.ModItems;

import static net.nokunami.elementus.Elementus.MODID;

@EventBusSubscriber(modid = MODID, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void itemTooltip(ItemTooltipEvent event) {
        ItemStack itemStack = event.getItemStack();
        if (!event.getItemStack().isEmpty()) {
            if (itemStack.is(Etags.Items.CATALYST_ITEMS)) {
                event.getToolTip().add(Component.translatable("item.elementus.catalyst_core.desc").withStyle(ChatFormatting.GRAY));
            }
        }
    }

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
//        event.register(ModItems.CATALYST_CHESTPLATE.get(), new CatalystCoreItemDecoration());
        event.register(ModItems.DIARKRITE_CHARGE_BLADE.get(), new ItemBarItemDecoration());
        event.register(ModItems.ANTHEKTITE_CHARGE_BLADE.get(), new ItemBarItemDecoration());

        event.register(ModItems.MOVCADIA_SWORD.get(), new ItemBarItemDecoration());
        event.register(ModItems.MOVCADIA_SHOVEL.get(), new ItemBarItemDecoration());
        event.register(ModItems.MOVCADIA_PICKAXE.get(), new ItemBarItemDecoration());
        event.register(ModItems.MOVCADIA_AXE.get(), new ItemBarItemDecoration());
        event.register(ModItems.MOVCADIA_HOE.get(), new ItemBarItemDecoration());
    }
}
