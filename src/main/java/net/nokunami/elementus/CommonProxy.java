package net.nokunami.elementus;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.nokunami.elementus.common.entity.boss.FragmentedWither;
import net.nokunami.elementus.common.entity.living.AstaliteGolemCarrier;
import net.nokunami.elementus.common.entity.living.AstaliteGolemLongarm;
import net.nokunami.elementus.common.entity.living.SteelGolem;
import net.nokunami.elementus.common.registry.EEntityTypes;

import static net.nokunami.elementus.Elementus.EID;

@Mod.EventBusSubscriber(modid = EID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonProxy {

    public CommonProxy() { }

    public void clientInit() { }

    public Object getArmorRenderProperties() { return null; }

    public Object getArmorCatalystRenderProperties() { return null; }

    public Object getArmorCatalystTrimRenderProperties() { return null; }

    @SubscribeEvent
    public static void attributeCreationEvent(EntityAttributeCreationEvent event) {
        event.put(EEntityTypes.OLD_STEEL_GOLEM.get(), SteelGolem.createAttributes().build());
        event.put(EEntityTypes.ASTALITE_GOLEM_CARRIER.get(), AstaliteGolemCarrier.createAttributes().build());
        event.put(EEntityTypes.ASTALITE_GOLEM_LONGARM.get(), AstaliteGolemLongarm.createAttributes().build());
        event.put(EEntityTypes.FRAGMENTED_WITHER.get(), FragmentedWither.createAttributes().build());
    }
}