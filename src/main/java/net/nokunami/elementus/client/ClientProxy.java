package net.nokunami.elementus.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod;
import net.nokunami.elementus.CommonProxy;
import net.nokunami.elementus.Elementus;
import net.nokunami.elementus.client.render.catalystCore.CatalystArmorRenderProperties;
import net.nokunami.elementus.client.render.item.CatalystTrimRenderProperties;
import net.nokunami.elementus.client.render.item.CustomArmorRenderProperties;

import static net.nokunami.elementus.Elementus.EID;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = EID, value = Dist.CLIENT)
public class ClientProxy extends CommonProxy {

    @Override public Object getArmorRenderProperties() { return new CustomArmorRenderProperties(); }
    @Override public Object getArmorCatalystRenderProperties() { return new CatalystArmorRenderProperties(); }
    @Override public Object getArmorCatalystTrimRenderProperties() { return new CatalystTrimRenderProperties(); }
}
