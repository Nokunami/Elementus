package nokunami.elementus.client;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.fml.common.EventBusSubscriber;
import nokunami.elementus.CommonProxy;
import nokunami.elementus.Elementus;
import nokunami.elementus.client.render.item.CustomArmorRenderProperties;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = Elementus.MODID, value = Dist.CLIENT)
public class ClientProxy extends CommonProxy {
    public ClientProxy() {
    }

    @Override
    public Object getArmorRenderProperties() {
        return new CustomArmorRenderProperties();
    }
}
