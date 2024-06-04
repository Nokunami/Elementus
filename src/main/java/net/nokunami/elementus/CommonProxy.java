package net.nokunami.elementus;

import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Elementus.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonProxy {

    public CommonProxy() {
    }

    public void clientInit() {
    }

    public Object getArmorRenderProperties() {
        return null;
    }
}