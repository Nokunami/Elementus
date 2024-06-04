package net.nokunami.elementus.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod;
import net.nokunami.elementus.CommonProxy;
import net.nokunami.elementus.Elementus;
import net.nokunami.elementus.client.render.item.CustomArmorRenderProperties;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = Elementus.MODID, value = Dist.CLIENT)
public class ClientProxy extends CommonProxy {
    public ClientProxy() {
    }

    @Override
    public Object getArmorRenderProperties() {
        return new CustomArmorRenderProperties();
    }
}
