package nokunami.elementus;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import nokunami.elementus.common.entity.living.SteelGolem;
import nokunami.elementus.common.registry.ModEntityType;

@Mod.EventBusSubscriber(modid = Elementus.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonProxy {

    public CommonProxy() {
    }

    public void clientInit() {
    }

    public Object getArmorRenderProperties() {
        return null;
    }

    @SubscribeEvent
    public static void attributeCreationEvent(EntityAttributeCreationEvent event) {
        event.put(ModEntityType.STEEL_GOLEM.get(), SteelGolem.createAttributes().build());
    }
}