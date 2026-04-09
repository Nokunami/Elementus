package net.nokunami.elementus.client.gui.overlay;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.nokunami.elementus.client.CAbilityClient;

import static net.nokunami.elementus.Elementus.modLoc;

public class CastProgressBarOverlay implements IGuiOverlay {
    private final ResourceLocation BAR = modLoc("textures/gui/catalyst/ability_frame.png");
    private final int ABILITY_FRAME = 64;
    private final int BAR_X = 12;
    private final int BAR_Y = 5;
    private final String[] CASTING_TEXT = { "Casting.", "Casting..", "Casting..." };
    private final int TEXT_COLOR = ChatFormatting.WHITE.getColor();

    @Override
    public void render(ForgeGui gui, GuiGraphics guiGraphics, float partialTick, int screenWidth, int screenHeight) {
        int x = screenWidth / 2;
        int y = screenHeight / 2;

        var player = Minecraft.getInstance().player;
        if (player != null) {
            var progress = CAbilityClient.getCastTick();

//            guiGraphics.drawString(gui.getFont(), String.valueOf(progress), x, y - 20, ChatFormatting.WHITE.getColor());
//            guiGraphics.drawString(gui.getFont(), CASTING_TEXT[gui.getGuiTicks() / 20 % 3], x, y - 30, ChatFormatting.WHITE.getColor());
//            guiGraphics.drawString(gui.getFont(), String.valueOf(CAbilityClient.getCastState()), x, y, ChatFormatting.WHITE.getColor());

            if (CAbilityClient.getCastState()) {
                guiGraphics.drawCenteredString(gui.getFont(), CASTING_TEXT[gui.getGuiTicks() / 10 % 3], x, y + 36, TEXT_COLOR);
                renderBar(x, y + 20, 3, guiGraphics);
            }
        }
    }

    void renderBar(int x, int y, int segmentAmount, GuiGraphics guiGraphics) {
        int i1 = getWidth(36, 0);
        int i2 = getWidth(24, 6);
        int i3 = getWidth(12, 12);
        x -= (36 / 2);
        renderBarSegment(x, y, i1, guiGraphics, segment.START);
        renderBarSegment(x + 12, y, i2, guiGraphics, segment.MIDDLE);
        renderBarSegment(x + 24, y, i3, guiGraphics, segment.END);
    }
    void renderBarSegment(int x, int y, int barX, GuiGraphics guiGraphics, segment segment) {
        int i = segment.mid() ? 5 : segment.end() ? 10 : 0;
        guiGraphics.blit(BAR, x, y, 44, 32 + i, BAR_X, BAR_Y, ABILITY_FRAME, ABILITY_FRAME);
        guiGraphics.blit(BAR, x, y, 32, 32 + i, barX, BAR_Y, ABILITY_FRAME, ABILITY_FRAME);
    }
    int getWidth(int offset1, int offset2) {
        return (int) Mth.clamp((CAbilityClient.getCastProgress() * (BAR_X + offset1)) - offset2, 0, 12);
    }

    private enum segment {
        START, MIDDLE, END;
        boolean mid() { return this == MIDDLE; }
        boolean end() { return this == END; }
    }
}