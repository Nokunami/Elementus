package net.nokunami.elementus;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.nokunami.elementus.common.entity.living.AstaliteGolem;
import net.nokunami.elementus.common.entity.living.SteelGolem;
import net.nokunami.elementus.common.registry.ModEntityType;

@Mod.EventBusSubscriber(modid = Elementus.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonProxy {

    public CommonProxy() {
    }

    public void clientInit() {
    }

    public Object getArmorRenderProperties() {
        return null;
    }

    public Object getArmorCatalystRenderProperties() {
        return null;
    }

    public Object getArmorCatalystTrimRenderProperties() {
        return null;
    }

    @SubscribeEvent
    public static void attributeCreationEvent(EntityAttributeCreationEvent event) {
        event.put(ModEntityType.ASTALITE_GOLEM.get(), AstaliteGolem.createAttributes().build());
        event.put(ModEntityType.OLD_STEEL_GOLEM.get(), SteelGolem.createAttributes().build());
    }
}