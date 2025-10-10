package net.nokunami.elementus.event;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.ChatFormatting;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterItemDecorationsEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.nokunami.elementus.client.gui.CatalystCoreItemDecoration;
import net.nokunami.elementus.client.gui.ItemBarItemDecoration;
import net.nokunami.elementus.client.gui.overlay.CatalystExhaustionGui;
import net.nokunami.elementus.common.item.unique.ChargeBladeItem;
import net.nokunami.elementus.common.network.ChargeBladeAbilityPacket;
import net.nokunami.elementus.common.network.ModNetwork;
import net.nokunami.elementus.common.registry.EItems;
import org.lwjgl.glfw.GLFW;

import static net.nokunami.elementus.Elementus.MODID;
import static net.nokunami.elementus.common.catalystCore.core.CatalystCore.CORE_ITEM_MAP;

@EventBusSubscriber(modid = MODID, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void itemTooltip(ItemTooltipEvent event) {
        ItemStack itemStack = event.getItemStack();
        if (!event.getItemStack().isEmpty()) {
            if (CORE_ITEM_MAP.containsKey(itemStack.getItem())) {
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

    public static void scroll(InputEvent.MouseScrollingEvent event) {
        int direction = Mth.clamp((int) event.getScrollDelta(), -1, 1);
        Minecraft minecraft = Minecraft.getInstance();
        if(minecraft.level == null || minecraft.screen != null || Minecraft.getInstance().isPaused() || minecraft.player == null) return;

//        if (minecraft.player.containerMenu.getSlot()) {
//        }
//        ModNetwork.sendToServer();
    }

    public static void itemDecorations(RegisterItemDecorationsEvent event) {
//        event.register(ModItems.CATALYST_CHESTPLATE.get(), new CatalystCoreItemDecoration());
        event.register(EItems.TEST_CATALYST_CHESTPLATE.get(), new CatalystCoreItemDecoration());
        event.register(EItems.DIARKRITE_CHARGE_BLADE.get(), new ItemBarItemDecoration());
        event.register(EItems.ANTHEKTITE_CHARGE_BLADE.get(), new ItemBarItemDecoration());

        event.register(EItems.MOVCADIA_SWORD.get(), new ItemBarItemDecoration());
        event.register(EItems.MOVCADIA_SHOVEL.get(), new ItemBarItemDecoration());
        event.register(EItems.MOVCADIA_PICKAXE.get(), new ItemBarItemDecoration());
        event.register(EItems.MOVCADIA_AXE.get(), new ItemBarItemDecoration());
        event.register(EItems.MOVCADIA_HOE.get(), new ItemBarItemDecoration());
    }

    public static final Lazy<KeyMapping> TEST_KEY = Lazy.of(() -> new KeyMapping(
            "key.elementus.catalyst_ability_1",
            KeyConflictContext.UNIVERSAL,
            KeyModifier.ALT,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_C,
            KeyMapping.CATEGORY_GAMEPLAY));

    @EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
    public static class ModBus {
        @SubscribeEvent
        public static void registerKeybinds(RegisterKeyMappingsEvent event) {
            event.register(TEST_KEY.get());
        }

        @SubscribeEvent
        public static void registerGui(RegisterGuiOverlaysEvent event) {
            event.registerAboveAll("catalyst_exhaustion", CatalystExhaustionGui.inst);
        }
    }
}
