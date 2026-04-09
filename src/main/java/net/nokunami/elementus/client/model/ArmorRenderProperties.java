package net.nokunami.elementus.client.model;

import net.minecraft.resources.ResourceLocation;

public class ArmorRenderProperties {
    private ResourceLocation resLoc;

    public ArmorRenderProperties(ResourceLocation resourceLocation) {
        resLoc = resourceLocation;
    }

    public ResourceLocation location() { return resLoc; }
}
