package net.nokunami.elementus.client.gui.overlay;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

import static net.nokunami.elementus.Elementus.modLoc;

public class CatalystExhaustionGui implements IGuiOverlay {
    public static CatalystExhaustionGui inst = new CatalystExhaustionGui();

    private static final ResourceLocation EMPTY = modLoc("textures/gui/icons/catalyst_exhaustion_empty.png");
    private static final ResourceLocation STATE_0 = modLoc("textures/gui/icons/catalyst_exhaustion_0.png");
    private static final ResourceLocation STATE_1 = modLoc("textures/gui/icons/catalyst_exhaustion_1.png");
    private static final ResourceLocation STATE_2 = modLoc("textures/gui/icons/catalyst_exhaustion_2.png");
    private static final ResourceLocation STATE_3 = modLoc("textures/gui/icons/catalyst_exhaustion_3.png");

    @Override
    public void render(ForgeGui gui, GuiGraphics guiGraphics, float partialTick, int screenWidth, int screenHeight) {
        int x = screenWidth / 2;
        int y = screenHeight;

        for(int i = 0; i < 10; i++) {
            guiGraphics.blit(EMPTY, x - 94 + (i * 9), y - 54,0,0,9,9,
                    9,9);
        }

        for(int i = 0; i < 10; i++) {
            if(CatalystExhaustionClient.get() > i) {
                guiGraphics.blit(STATE_0,x - 94 + (i * 9),y - 54,0,0,9,9,
                        9,9);
            } else {
                break;
            }
        }
    }
}
