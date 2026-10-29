package net.nokunami.elementus.client.gui.screens.inventory;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public class CyclingTexture {
    private static final int ICON_CHANGE_TICK_RATE = 30;
    private static final int ICON_SIZE = 16;
    private static final int ICON_TRANSITION_TICK_DURATION = 4;
    private List<ResourceLocation> icons = List.of();
    private int tick;
    private int iconIndex;
    private final int width;
    private final int height;

    public CyclingTexture(int sizeX, int sizeY) {
        width = sizeX;
        height = sizeY;
    }

    public void tick(List<ResourceLocation> list) {
        if (!icons.equals(list)) {
            icons = list;
            iconIndex = 0;
        }

        if (!icons.isEmpty() && ++tick % ICON_CHANGE_TICK_RATE == 0) {
            iconIndex = (iconIndex + 1) % icons.size();
        }

    }

    public void render(GuiGraphics guiG, float partialTick, int x, int y) {
        if (!icons.isEmpty()) {
            boolean flag = icons.size() > 1 && tick >= 30;
            float f = flag ? getIconTransitionTransparency(partialTick) : 1;
            if (f < 1) {
                int i = Math.floorMod(iconIndex - 1, icons.size());
                renderIcon(icons.get(i), 1 - f, guiG, x, y);
            }

            renderIcon(icons.get(iconIndex), f, guiG, x, y);
        }
    }

    private void renderIcon(ResourceLocation pIcon, float alpha, GuiGraphics guiG, int x, int y) {
        TextureAtlasSprite textureatlassprite = Minecraft.getInstance().getTextureAtlas(TextureAtlas.LOCATION_BLOCKS).apply(pIcon);
        guiG.blit(x, y, 0, width, height, textureatlassprite, 1, 1, 1, alpha);
    }

    private float getIconTransitionTransparency(float partialTIck) {
        float f = (tick % 30F) + partialTIck;
        return Math.min(f, ICON_TRANSITION_TICK_DURATION) / ICON_TRANSITION_TICK_DURATION;
    }
}
