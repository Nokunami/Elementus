package net.nokunami.elementus.event;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.client.model.EntityModel;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingConversionEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.MissingMappingsEvent;
import net.nokunami.elementus.client.CAbilityClient;
import net.nokunami.elementus.client.EKeyMap;
import net.nokunami.elementus.client.model.CustomModelProperties;
import net.nokunami.elementus.client.model.EHierarchicalModel;
import net.nokunami.elementus.common.capability.cAbility.CAbility;
import net.nokunami.elementus.common.catalystCore.ability.AbilityCastManager;
import net.nokunami.elementus.common.item.IMovcadiaTool;
import net.nokunami.elementus.common.item.unique.ChargeBladeItem;
import net.nokunami.elementus.common.item.unique.TestCatalystArmorItem;
import net.nokunami.elementus.common.network.ENetwork;
import net.nokunami.elementus.common.network.client.SyncAbilitySelectionS2CPacket;
import net.nokunami.elementus.common.network.server.CatalystAbilitySelectionPacket;
import net.nokunami.elementus.common.network.server.CatalystSlotC2SPacket;
import net.nokunami.elementus.common.network.server.ChargeBladeAbilityC2SPacket;
import net.nokunami.elementus.common.registry.EGameRules;

import java.util.HashMap;
import java.util.Map;

import static net.nokunami.elementus.Elementus.EID;
import static net.nokunami.elementus.Elementus.modLoc;
import static net.nokunami.elementus.common.catalystCore.core.CatalystCore.CORE_ITEM_MAP;
import static net.nokunami.elementus.common.registry.CustomRegistries.CatalystCoreHelper.CatalystCoreUtil;

public class ForgeEvents {

    @Mod.EventBusSubscriber(modid = EID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
    static class Client {
        @SubscribeEvent
        public static void itemTooltip(ItemTooltipEvent event) {
            ItemStack itemStack = event.getItemStack();
            Player player = event.getEntity();
            if (!event.getItemStack().isEmpty()) {
                if (CORE_ITEM_MAP.containsKey(itemStack.getItem())) {
                    if (itemStack.is(Items.BARRIER)/* && player != null && player.isSecondaryUseActive()*/) {
                        if (Screen.hasShiftDown()) event.getToolTip().add(Component.translatableWithFallback("item.elementus.catalyst_core.fallback_desc", "Fallback Catalyst Core").withStyle(ChatFormatting.GRAY));
                    } else {
                        event.getToolTip().add(1, Component.translatable("item.elementus.catalyst_core.desc").withStyle(ChatFormatting.GRAY));
                    }
                }
                if (itemStack.getItem() instanceof IMovcadiaTool) {
                    event.getToolTip().add(1, Component.translatable("item.elementus.movcadia_tool.desc").withStyle(ChatFormatting.DARK_PURPLE));
                    // TODO: Maybe a new tooltip
                    // Smth like [Fragile Tool] Shift for info
                    // - Tool degrades with use.
                    // - Attack Damage is at %.
                    // - Attack Speed is at %.
                    // - Mining Speed is at %.
                    // you get the gist
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
                ENetwork.sendToServer(new ChargeBladeAbilityC2SPacket(hand));
            }
        }

        @SubscribeEvent
        public static void castAbilityEvents(InputEvent.Key event) {
//            Minecraft mc = Minecraft.getInstance();
//            Player player = mc.player;
//            if (mc.level == null || mc.screen != null || mc.isPaused() || player == null) return;
//            KeyMapping key = EKeyMap.CATALYST_ABILITY_KEY;
//            boolean isKey = event.getKey() == key.getKey().getValue();
//
//            if (isKey) {
//                if (event.getAction() == InputConstants.PRESS) ENetwork.sendToServer(new CatalystAbilityC2SPacket(CastType.ON_CAST_START));
//                if (event.getAction() == InputConstants.REPEAT) ENetwork.sendToServer(new CatalystAbilityC2SPacket(CastType.CASTING));
//                if (event.getAction() == InputConstants.RELEASE) ENetwork.sendToServer(new CatalystAbilityC2SPacket(CastType.ON_CAST_STOP));
//            }
            EKeyMap.onKeyDown(event);
        }

        @SubscribeEvent
        public static void abilitySelectionEvent(InputEvent.MouseScrollingEvent event) {
            Minecraft mc = Minecraft.getInstance();
            Player player = mc.player;
            if (mc.level == null || mc.screen != null || mc.isPaused() || player == null) return;

            if (EKeyMap.CATALYST_ABILITY_SWITCH_KEY.isDown()) {
                int scroll = (int) Mth.clamp(event.getScrollDelta(), -1, 1);
                var core = CatalystCoreUtil(player);

                ENetwork.sendToServer(new CatalystAbilitySelectionPacket(scroll));
//                ENetwork.sendToServer(new CAbilitySelectionSyncS2CPacket(scroll));
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
                        ENetwork.sendToServer(new CatalystSlotC2SPacket(
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
                CAbilityClient.getCooldowns().tick();
                CAbilityClient.clientCastTick();
            }
        }

//        @SubscribeEvent
//        public static void onClientLogout(ClientPlayerNetworkEvent.LoggingOut event) {
//            if (event.getPlayer() != null) {
//                AbilityCastManager.onServerCastStop();
//            }
//        }
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
//                        ENetwork.sendTo(player, new CatalystCoreSyncPacket(chest));
//                        ENetwork.sendTo(player, new AbilityCooldownPacket());
                    }
                }
            }
        }

//        @SubscribeEvent
//        public static void attributeModifierEvent(ItemAttributeModifierEvent event) {
//            var item = event.getItemStack().getItem();
//            if (EConfig.COMMON_SPEC.isLoaded()) {
//                if (item instanceof IConfigAttributesItem config) {
//                    config.modifyAttributes(event);
//                }
//            }
//        }

        @SubscribeEvent
        public static void onLevelJoin(PlayerEvent.PlayerLoggedInEvent event) {
            if (event.getEntity() instanceof ServerPlayer player) {
                CAbility inst = CAbility.instance(player);
                inst.getCooldowns().sync(player);
                ENetwork.sendTo(player, new SyncAbilitySelectionS2CPacket(inst.getSelection()));
            }
        }

//        @SubscribeEvent
//        public static void onRespawn(PlayerEvent.PlayerLoggedInEvent event) {
//            if (event.getEntity() instanceof ServerPlayer player) {
//                CAbility inst = CAbility.instance(player);
//                inst.getCooldowns().sync(player);
//            }
//        }

//        @SubscribeEvent
//        public static void onChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
//            if (event.getEntity() instanceof ServerPlayer player) {
//                CAbility inst = CAbility.instance(player);
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

        @SubscribeEvent
        public static void recallTrident(PlayerInteractEvent.RightClickEmpty event) {
            ItemStack stack = event.getItemStack();
            Player player = event.getEntity();
            if (stack.isEmpty()) {
            }
        }
    }

    /// Credits: Supplementaries RemapHandler
    private static final Map<String, String> ITEM_REMAP = new HashMap<>();
    private static final Map<String, String> BLOCK_REMAP = new HashMap<>();
    private static final Map<String, String> ENTITY_REMAP = new HashMap<>();
    static {
        ITEM_REMAP.put(modLoc("crude_steel").toString(), modLoc("raw_astalite").toString());
        ITEM_REMAP.put(modLoc("steel_scrap").toString(), modLoc("astalite_scrap").toString());
        ITEM_REMAP.put(modLoc("steel_ingot").toString(), modLoc("astalite_ingot").toString());
        ITEM_REMAP.put(modLoc("steel_nugget").toString(), modLoc("astalite_nugget").toString());

        ITEM_REMAP.put(modLoc("steel_sword").toString(), modLoc("astalite_sword").toString());
        ITEM_REMAP.put(modLoc("steel_shovel").toString(), modLoc("astalite_shovel").toString());
        ITEM_REMAP.put(modLoc("steel_pickaxe").toString(), modLoc("astalite_pickaxe").toString());
        ITEM_REMAP.put(modLoc("steel_axe").toString(), modLoc("astalite_axe").toString());
        ITEM_REMAP.put(modLoc("steel_hoe").toString(), modLoc("astalite_hoe").toString());

        ITEM_REMAP.put(modLoc("steel_bow").toString(), modLoc("astalite_bow").toString());
        ITEM_REMAP.put(modLoc("steel_shield").toString(), modLoc("astalite_shield").toString());

        ITEM_REMAP.put(modLoc("steel_helmet").toString(), modLoc("astalite_helmet").toString());
        ITEM_REMAP.put(modLoc("steel_chestplate").toString(), modLoc("astalite_chestplate").toString());
        ITEM_REMAP.put(modLoc("steel_leggings").toString(), modLoc("astalite_leggings").toString());
        ITEM_REMAP.put(modLoc("steel_boots").toString(), modLoc("astalite_boots").toString());

        ITEM_REMAP.put(modLoc("diarkrite_charge_blade").toString(), modLoc("blade_of_resonance").toString());
        ITEM_REMAP.put(modLoc("anthektite_charge_blade").toString(), modLoc("blade_of_wind_charging").toString());

        ITEM_REMAP.put(modLoc("steel_block").toString(), modLoc("astalite_block").toString());
        ITEM_REMAP.put(modLoc("steel_bars").toString(), modLoc("astalite_block").toString());
        ITEM_REMAP.put(modLoc("steel_tiles").toString(), modLoc("astalite_block").toString());
        ITEM_REMAP.put(modLoc("steel_tile_stair").toString(), modLoc("astalite_block").toString());
        ITEM_REMAP.put(modLoc("steel_tile_slab").toString(), modLoc("astalite_block").toString());


        BLOCK_REMAP.put(modLoc("steel_block").toString(), modLoc("astalite_block").toString());
        BLOCK_REMAP.put(modLoc("steel_bars").toString(), modLoc("astalite_block").toString());
        BLOCK_REMAP.put(modLoc("steel_tiles").toString(), modLoc("astalite_block").toString());
        BLOCK_REMAP.put(modLoc("steel_tile_stair").toString(), modLoc("astalite_block").toString());
        BLOCK_REMAP.put(modLoc("steel_tile_slab").toString(), modLoc("astalite_block").toString());

        ENTITY_REMAP.put(modLoc("steel_golem").toString(), modLoc("astalite_golem_longarm").toString());
        ENTITY_REMAP.put(modLoc("astalite_golem").toString(), modLoc("astalite_golem_carrier").toString());
    }

    @SubscribeEvent
    public static void onRemap(MissingMappingsEvent event) {
        for (var v : event.getMappings(ForgeRegistries.BLOCKS.getRegistryKey(), EID)) {
            String rem = BLOCK_REMAP.get(v.getKey().toString());
            if (rem != null) {
                var b = BuiltInRegistries.BLOCK.getOptional(new ResourceLocation(rem));
                b.ifPresent(v::remap);
            } else v.ignore();
        }
        for (var v : event.getMappings(ForgeRegistries.ITEMS.getRegistryKey(), EID)) {
            String rem = ITEM_REMAP.get(v.getKey().toString());
            if (rem != null) {
                var b = BuiltInRegistries.ITEM.getOptional(new ResourceLocation(rem));
                b.ifPresent(v::remap);
            } else v.ignore();
        }
        for (var v : event.getMappings(ForgeRegistries.ENTITY_TYPES.getRegistryKey(), EID)) {
            String rem = ENTITY_REMAP.get(v.getKey().toString());
            if (rem != null) {
                var b = BuiltInRegistries.ENTITY_TYPE.getOptional(new ResourceLocation(rem));
                b.ifPresent(v::remap);
            } else v.ignore();
        }
    }
}
