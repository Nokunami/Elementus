package net.nokunami.elementus.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.client.event.RegisterItemDecorationsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.*;
import net.nokunami.elementus.client.gui.ChargeSwordItemDecoration;
import net.nokunami.elementus.common.registry.ModItems.*;

import static net.nokunami.elementus.Elementus.MODID;

@EventBusSubscriber(modid = MODID, value = Dist.CLIENT, bus = Bus.MOD)
public class ModClientEvents {

    public static void itemDecorations(RegisterItemDecorationsEvent event) {
//        event.register(ElementusItems.CATALYST_CHESTPLATE.get(), new CatalystCoreItemDecoration());
        event.register(ElementusItems.DIARKRITE_CHARGE_BLADE.get(), new ChargeSwordItemDecoration());
        event.register(ElementusItems.ANTHEKTITE_CHARGE_BLADE.get(), new ChargeSwordItemDecoration());
    }

    @SubscribeEvent
    public static void itemColorRegistry(RegisterColorHandlersEvent.Item event) {
//        ItemColors colors = event.getItemColors();
//
//        event.register(colors, ElementusItems.DIARKRITE_CHARGE_BLADE.get());
    }
}
