package net.nokunami.elementus.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterItemDecorationsEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.nokunami.elementus.client.gui.CatalystCoreItemDecoration;
import net.nokunami.elementus.client.gui.ItemBarItemDecoration;
import net.nokunami.elementus.client.gui.overlay.CastProgressBarOverlay;
import net.nokunami.elementus.client.gui.overlay.CatalystAbilityOverlay;
import net.nokunami.elementus.common.registry.EItems;

import static net.nokunami.elementus.Elementus.EID;

@Mod.EventBusSubscriber(modid = EID)
public class ModEvents {

    @Mod.EventBusSubscriber(modid = EID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    static class Client {

        @SubscribeEvent
        public static void itemDecorations(RegisterItemDecorationsEvent event) {
            event.register(EItems.CATALYST_CHESTPLATE.get(), new CatalystCoreItemDecoration());
//            event.register(EItems.TEST_CATALYST_CHESTPLATE.get(), new CatalystCoreItemDecoration());
            event.register(EItems.DIARKRITE_CHARGE_BLADE.get(), new ItemBarItemDecoration());
            event.register(EItems.ANTHEKTITE_CHARGE_BLADE.get(), new ItemBarItemDecoration());

            event.register(EItems.MOVCADIA_SWORD.get(), new ItemBarItemDecoration());
            event.register(EItems.MOVCADIA_SHOVEL.get(), new ItemBarItemDecoration());
            event.register(EItems.MOVCADIA_PICKAXE.get(), new ItemBarItemDecoration());
            event.register(EItems.MOVCADIA_AXE.get(), new ItemBarItemDecoration());
            event.register(EItems.MOVCADIA_HOE.get(), new ItemBarItemDecoration());

            event.register(EItems.ASTALITE_SHIELD.get(), new ItemBarItemDecoration());
            event.register(EItems.DIARKRITE_SHIELD.get(), new ItemBarItemDecoration());
            event.register(EItems.ANTHEKTITE_SHIELD.get(), new ItemBarItemDecoration());
        }

        @SubscribeEvent
        public static void registerGui(RegisterGuiOverlaysEvent event) {
//            event.registerAboveAll("catalyst_exhaustion", CatalystExhaustionGui.inst);
            event.registerAbove(VanillaGuiOverlay.EXPERIENCE_BAR.id(), "catalyst_ability", CatalystAbilityOverlay.inst);
            event.registerAbove(VanillaGuiOverlay.EXPERIENCE_BAR.id(), "ability_cast_progress", new CastProgressBarOverlay());
        }
    }

    @Mod.EventBusSubscriber(modid = EID, bus = Mod.EventBusSubscriber.Bus.MOD)
    static class Server {
    }

//    @SubscribeEvent
//    public static void onPlayerCloned(PlayerEvent.Clone event) {
//        if (event.isWasDeath()) {
//            event.getOriginal().getCapability(TestArmorCapProvider.TEST_ARMOR_CAP).ifPresent(oldCap ->
//                    event.getOriginal().getCapability(TestArmorCapProvider.TEST_ARMOR_CAP).ifPresent(newCap -> newCap.copy(oldCap)));
//            event.getOriginal().getCapability(CatalystExhaustionProvider.CAP).ifPresent(oldCap ->
//                    event.getOriginal().getCapability(CatalystExhaustionProvider.CAP).ifPresent(newCap -> newCap.copyExhaustion(oldCap)));
//        }
//    }
}