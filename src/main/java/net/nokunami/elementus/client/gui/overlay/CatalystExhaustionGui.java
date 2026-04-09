package net.nokunami.elementus.client.gui.overlay;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

import static net.nokunami.elementus.Elementus.modLoc;

public class CatalystExhaustionGui implements IGuiOverlay {
    public static CatalystExhaustionGui inst = new CatalystExhaustionGui();

    private static final ResourceLocation EMPTY = modLoc("textures/gui/icons/catalyst_exhaustion_empty.png");
    private static final ResourceLocation[] ICON = {
            modLoc("textures/gui/icons/catalyst_exhaustion_0.png"),
            modLoc("textures/gui/icons/catalyst_exhaustion_1.png"),
            modLoc("textures/gui/icons/catalyst_exhaustion_2.png"),
            modLoc("textures/gui/icons/catalyst_exhaustion_3.png"),
            modLoc("textures/gui/icons/catalyst_exhaustion_4.png")
    };

    @Override
    public void render(ForgeGui gui, GuiGraphics guiGraphics, float partialTick, int screenWidth, int screenHeight) {
        int x = screenWidth / 2;

        if (CatalystExhaustionClient.getExhaustion() > 0) {
            for(int i = 0; i < 10; i++) {
                guiGraphics.blit(EMPTY,
                        x - 94 + (i * 8), screenHeight - 54,
                        0,0,
                        9,9,
                        9,9);
            }

            for(int i = 0; i < 10; i++) {
                if(CatalystExhaustionClient.getExhaustionChunk() > i) {
                    int smallChunk = CatalystExhaustionClient.getExhaustionSmallChunk();
                    if(smallChunk > 0) {
                        guiGraphics.blit(ICON[smallChunk - 1],
                                x - 94 + (i * 8), screenHeight - 54,
                                0,0,
                                9,9,
                                9,9);
                    }
                } else {
                    break;
                }
            }
        }

    }
}
