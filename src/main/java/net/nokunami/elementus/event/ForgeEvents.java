package net.nokunami.elementus.event;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.client.model.EntityModel;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.ModCheck;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.*;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingConversionEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import net.nokunami.elementus.EIdentifier;
import net.nokunami.elementus.ElementusModCheck;
import net.nokunami.elementus.client.AbilityDataClient;
import net.nokunami.elementus.client.EKeyMap;
import net.nokunami.elementus.client.model.CustomModelProperties;
import net.nokunami.elementus.client.model.EHierarchicalModel;
import net.nokunami.elementus.common.capability.abilityData.AbilityData;
import net.nokunami.elementus.common.catalystCore.ability.AbilityManager;
import net.nokunami.elementus.common.item.EBowItem;
import net.nokunami.elementus.common.item.IMovcadiaTool;
import net.nokunami.elementus.common.item.enchantment.IChargeBladeEnchantment;
import net.nokunami.elementus.common.item.unique.ChargeBladeItem;
import net.nokunami.elementus.common.item.unique.TestCatalystArmorItem;
import net.nokunami.elementus.common.network.ENet;
import net.nokunami.elementus.common.network.client.SyncAbilitySelectionS2CPacket;
import net.nokunami.elementus.common.network.server.AbilitySelectionPacket;
import net.nokunami.elementus.common.network.server.CatalystSlotC2SPacket;
import net.nokunami.elementus.common.network.server.ChargeBladeAbilityC2SPacket;
import net.nokunami.elementus.common.tags.EItemTags;

import static net.nokunami.elementus.Elementus.EID;
import static net.nokunami.elementus.Elementus.modLoc;
import static net.nokunami.elementus.common.catalystCore.core.CatalystCore.CORE_ITEM_MAP;
import static net.nokunami.elementus.common.registry.ERegistries.CatalystCoreHelper.CatalystCoreUtil;

public class ForgeEvents {

    @Mod.EventBusSubscriber(modid = EID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
    static class Client {
        @SubscribeEvent
        public static void itemTooltip(ItemTooltipEvent event) {
            ItemStack itemStack = event.getItemStack();
            if (!event.getItemStack().isEmpty()) {
                if (CORE_ITEM_MAP.containsKey(itemStack.getItem())) {
                    if (itemStack.is(Items.BARRIER)) {
                        if (Screen.hasShiftDown()) event.getToolTip().add(Component.translatableWithFallback(EIdentifier.Description.catalystCoreFallbackDesc, "Fallback Catalyst Core").withStyle(ChatFormatting.GRAY));
                    } else {
                        event.getToolTip().add(1, Component.translatable(EIdentifier.Description.catalystCoreDesc).withStyle(ChatFormatting.GRAY));
                    }
                }
                if (itemStack.getItem() instanceof IMovcadiaTool) {
                    event.getToolTip().add(1, Component.translatable(EIdentifier.Description.movcadiaToolDesc).withStyle(ChatFormatting.DARK_PURPLE));
                    // TODO: Maybe a new tooltip
                    // Smth like [Fragile Tool] Shift for info
                    // - Tool degrades with use.
                    // - Attack Damage is at %.
                    // - Attack Speed is at %.
                    // - Mining Speed is at %.
                    // you get the gist
                }
                if (itemStack.is(EItemTags.REPAIR_ON_KILL)) {
                    event.getToolTip().add(1, Component.translatableWithFallback(EIdentifier.Description.repairOnKillDesc, "Item repairs itself on kill.").withStyle(ChatFormatting.GRAY));
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
            if(mc.level == null || mc.screen != null || mc.isPaused() || player == null) return;

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
                ENet.sendToServer(new ChargeBladeAbilityC2SPacket(hand));
            }
        }

        @SubscribeEvent
        public static void castAbilityEvents(InputEvent.Key event) {
            EKeyMap.onKeyDown(event);
        }

        @SubscribeEvent
        public static void abilitySelectionEvent(InputEvent.MouseScrollingEvent event) {
            Minecraft mc = Minecraft.getInstance();
            Player player = mc.player;
            if (mc.level == null || mc.screen != null || mc.isPaused() || player == null) return;

            if (EKeyMap.ABILITY_SWITCH_KEY.isDown()) {
                int scroll = (int) Mth.clamp(event.getScrollDelta(), -1, 1);
                ENet.sendToServer(new AbilitySelectionPacket(scroll));
                event.setCanceled(true);
            }
        }

        @SubscribeEvent
        public static void onCatalystAbilityCast(ScreenEvent.MouseScrolled.Pre event) {
            Minecraft mc = Minecraft.getInstance();
            Player player = mc.player;
            if (mc.level == null || mc.screen == null || mc.isPaused() || player == null) return;

            if (mc.screen instanceof AbstractContainerScreen<?> container) {
                AbstractContainerMenu menu = container.getMenu();
                Slot hoveredSlot = container.getSlotUnderMouse();
                int scroll = (int) event.getScrollDelta();
                boolean creativeSlot = container instanceof CreativeModeInventoryScreen c && c.isCreativeSlot(hoveredSlot);

                if (hoveredSlot != null && !creativeSlot) {
                    int index = container.getMenu().slots.indexOf(hoveredSlot);

                    if (hoveredSlot.getItem().getItem() instanceof TestCatalystArmorItem) {
                        ENet.sendToServer(new CatalystSlotC2SPacket(
                                menu.containerId,
                                menu.getStateId(),
                                index,
                                -scroll));
                        event.setCanceled(true);
                    }
                }
            }
        }

        @SubscribeEvent
        public static <T extends LivingEntity, M extends EntityModel<T>> void preRenderEvent(RenderLivingEvent.Pre<T, M> event) {
            EntityModel<T> model = event.getRenderer().getModel();
            LivingEntity entity = event.getEntity();
            float partialTick = event.getPartialTick();
            model.riding = entity.isPassenger() && (entity.getVehicle() != null && entity.getVehicle().shouldRiderSit());
            float limbSwing = entity.walkAnimation.position(partialTick);
            float limbSwingAmount = entity.walkAnimation.speed(partialTick);
            float ageInTicks = getBob(entity, partialTick);

            if (model instanceof EHierarchicalModel<T> custom) {
                custom.postSetupAnim((T) entity, new CustomModelProperties()
                        .limbSwing(limbSwing, limbSwingAmount)
                        .ticks(partialTick, ageInTicks));
            }
        }

        protected static float getBob(Entity pLivingBase, float pPartialTick) {
            return (float) pLivingBase.tickCount + pPartialTick;
        }

        @SubscribeEvent
        public static void onClientTick(TickEvent.PlayerTickEvent event) {
            if (event.side.isClient() && event.phase == TickEvent.Phase.END && event.player == Minecraft.getInstance().player) {
                AbilityDataClient.clientTick();
            }
        }

//        @SubscribeEvent
//        public static void onClientLogout(ClientPlayerNetworkEvent.LoggingOut event) {
//            if (event.getPlayer() != null) {
//                AbilityManager.onServerCastStop();
//            }
//        }

//        @SubscribeEvent
//        public static void fovEvent(ViewportEvent.ComputeFov event) {
//            var mc = Minecraft.getInstance();
//            var player = mc.player;
//            var f = event.getFOV();
//
//            if (player != null) {
//                var bowItem = player.getUseItem();
//                if (player.isUsingItem()) {
//                    if (bowItem.getItem() instanceof EBowItem bow) {
//                        int i = player.getTicksUsingItem();
////                        float f1 = (float) i / 20.0F;
//                        float f1 = (float) i / bow.getPullLimit();
//                        if (f1 > 1) {
//                            f1 = 1;
//                        } else {
//                            f1 *= f1;
//                        }
//
//                        f *= 1 - f1 * 0.15F;
//                    } else if (mc.options.getCameraType().isFirstPerson() && player.isScoping()) {
//                        f = 0.1F;
//                    }
//                }
//                event.setFOV(f);
//            }
//        }
        @SubscribeEvent
        public static void fovEvent(ComputeFovModifierEvent event) {
            if (ElementusModCheck.archeryExp) return;
            var mc = Minecraft.getInstance();
            var player = event.getPlayer();
            var f = event.getFovModifier();

            var bowItem = player.getUseItem();
            if (player.isUsingItem()) {
                if (bowItem.getItem() instanceof EBowItem bow) {
                    int i = player.getTicksUsingItem();
//                        float f1 = (float) i / 20.0F;
                    float f1 = (float) i / bow.getPullLimit();
                    if (f1 > 1) {
                        f1 = 1;
                    } else {
                        f1 *= f1;
                    }

                    f *= 1 - f1 * 0.15F;
                } else if (mc.options.getCameraType().isFirstPerson() && player.isScoping()) {
                    f = 0.1F;
                }
            }
            event.setNewFovModifier(f);
        }
    }

    @Mod.EventBusSubscriber(modid = EID, bus = Mod.EventBusSubscriber.Bus.FORGE/*, value = Dist.DEDICATED_SERVER*/)
    static class Server {

        @SubscribeEvent
        public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
            if(event.side == LogicalSide.SERVER) {
                Player player = event.player;
                if (player != null) {
                    ItemStack chest = player.getItemBySlot(EquipmentSlot.CHEST);
                    if (chest.getItem() instanceof TestCatalystArmorItem) {
//                        ENet.sendTo(player, new CatalystCoreSyncPacket(chest));
//                        ENet.sendTo(player, new AbilityCooldownPacket());
                    }
                }
            }
        }

        @SubscribeEvent
        public static void attributeModifierEvent(ItemAttributeModifierEvent event) {
            var stack = event.getItemStack();
            var item = stack.getItem();
            if (!stack.getAllEnchantments().isEmpty()) {
                for (Enchantment enchantment : stack.getAllEnchantments().keySet()) {
                    if (enchantment instanceof IChargeBladeEnchantment bladeEnchantment) {

                    }
                }
            }
        }

        @SubscribeEvent
        public static void onLevelJoin(PlayerEvent.PlayerLoggedInEvent event) {
            if (event.getEntity() instanceof ServerPlayer player) {
                AbilityManager.syncData(player);
            }
        }

//        @SubscribeEvent
//        public static void onRespawn(PlayerEvent.PlayerLoggedInEvent event) {
//            if (event.getEntity() instanceof ServerPlayer player) {
//                AbilityData inst = AbilityData.instance(player);
//                inst.getCooldowns().sync(player);
//            }
//        }

//        @SubscribeEvent
//        public static void onChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
//            if (event.getEntity() instanceof ServerPlayer player) {
//                AbilityData inst = AbilityData.instance(player);
//                inst.getCooldowns().sync(player);
//            }
//        }

        public void steelGolemConversion(LivingConversionEvent event) {
        }

//    @SubscribeEvent
//    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
//        if(event.side == LogicalSide.SERVER) {
//            event.player.getCapability(CatalystExhaustionProvider.CAP).ifPresent(exhaustion -> {
//                if(exhaustion.getExhaustion() > 0 && event.player.tickCount % 20 == 0) { // Once Every 10 Seconds on Avg
//                    exhaustion.subExhaustion(1);
//                    ModNetwork.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) event.player), new CatalystExhaustionSyncPacket(exhaustion.getExhaustion()));
//                }
//            });
//        }
//    }

//        @SubscribeEvent
//        public static void recallTrident(PlayerInteractEvent.RightClickEmpty event) {
//            ItemStack stack = event.getItemStack();
//            Player player = event.getEntity();
//            if (stack.isEmpty()) {
//            }
//        }
    }
}
