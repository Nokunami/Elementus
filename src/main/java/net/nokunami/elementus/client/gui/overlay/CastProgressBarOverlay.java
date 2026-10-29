package net.nokunami.elementus.client.gui.overlay;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.nokunami.elementus.client.AbilityDataClient;

import static net.nokunami.elementus.Elementus.modLoc;

public class CastProgressBarOverlay implements IGuiOverlay {
    private final ResourceLocation[] BAR_BG = {
            modLoc("textures/gui/catalyst/cast_bar_bg_1.png"),
            modLoc("textures/gui/catalyst/cast_bar_bg_2.png"),
            modLoc("textures/gui/catalyst/cast_bar_bg_3.png")
    };
    private final ResourceLocation[] BAR_FG = {
            modLoc("textures/gui/catalyst/cast_bar_fg_1.png"),
            modLoc("textures/gui/catalyst/cast_bar_fg_2.png"),
            modLoc("textures/gui/catalyst/cast_bar_fg_3.png")
    };
    private final ResourceLocation BAR_RL= modLoc("textures/gui/catalyst/cast_bar.png");
    private final int BAR_WIDTH = 78;
    private final int BAR_HEIGHT = 5;
    private final int BAR_X = 12;
    private final int BAR_Y = 5;
    private final String[] CASTING_TEXT = { "Casting.", "Casting..", "Casting..." };
    private final String READ_TO_CAST_TEXT = "Ready.";
    private final int TEXT_COLOR = ChatFormatting.WHITE.getColor();

    @Override
    public void render(ForgeGui fGui, GuiGraphics guiG, float partialTick, int screenWidth, int screenHeight) {
        int x = screenWidth / 2;
        int y = screenHeight / 2;

        var player = Minecraft.getInstance().player;
        if (player != null) {
            var tick = AbilityDataClient.getCastTick();
            var progress = AbilityDataClient.getCastProgress();
            var preProgress = AbilityDataClient.getPreCastProgress();

//            guiG.drawString(fGui.getFont(), String.valueOf(tick), x, y - 20, ChatFormatting.WHITE.getColor());
//            guiG.drawString(fGui.getFont(), CASTING_TEXT[fGui.getGuiTicks() / 20 % 3], x, y - 30, ChatFormatting.WHITE.getColor());
//            guiG.drawString(fGui.getFont(), String.valueOf(AbilityDataClient.getCastState()), x, y, ChatFormatting.WHITE.getColor());

//            if (AbilityDataClient.isCastKeyDown()) {
            if (AbilityDataClient.showCastBars()) {
                if (preProgress < 1) guiG.drawCenteredString(fGui.getFont(), CASTING_TEXT[fGui.getGuiTicks() / 10 % 3], x, y + 36, TEXT_COLOR);
                else guiG.drawCenteredString(fGui.getFont(), READ_TO_CAST_TEXT, x, y + 36, TEXT_COLOR);
//                renderBar(x, y + 20, 3, guiG);
                if (AbilityDataClient.showPreCastBar()) {
                    RenderSystem.setShaderColor(0.5F, 0.5F, 1, 1);
                    renderBar(preProgress, x, y + 20, guiG);
                    RenderSystem.setShaderColor(1, 1, 1, 1);
                }
                if (AbilityDataClient.showCastBar()) {
                    RenderSystem.setShaderColor(1, 0.5F, 0.5F, 1);
                    renderBar(progress, x, y + 30, guiG);
                    RenderSystem.setShaderColor(1, 1, 1, 1);
                }
            }
        }
    }

//    void renderBar(int x, int y, int segmentAmount, GuiGraphics guiG) {
//        int i1 = getWidth(36, 0);
//        int i2 = getWidth(24, 6);
//        int i3 = getWidth(12, 12);
//        x -= (36 / 2);
//        renderBarSegment(x, y, i1, guiG, segment.START);
//        renderBarSegment(x + 12, y, i2, guiG, segment.MIDDLE);
//        renderBarSegment(x + 24, y, i3, guiG, segment.END);
//    }
    void renderBarSegment(int x, int y, int barX, GuiGraphics guiGraphics, segment segment) {
        int i = segment.mid() ? 5 : segment.end() ? 10 : 0;
        guiGraphics.blit(BAR_BG[segment.part], x, y, 0, i, BAR_X, BAR_Y, 12, 5);
        guiGraphics.blit(BAR_FG[segment.part], x, y, 0, i, barX, BAR_Y, 12, 5);
    }
    int getWidth(int offset1, int offset2) {
        return (int) Mth.clamp((AbilityDataClient.getCastProgress() * (BAR_X + offset1)) - offset2, 0, 12);
    }
    void renderBar(float progress, int x, int y, GuiGraphics guiG) {
        var texWidth = 80;
        var texHeight = 16;
        x -= (BAR_WIDTH / 2);
        guiG.blit(BAR_RL, x, y, 0, 6, BAR_WIDTH, BAR_HEIGHT, texWidth, texHeight);
        guiG.blit(BAR_RL, x, y, 0, 0, (int) (BAR_WIDTH * progress), BAR_HEIGHT, texWidth, texHeight);
    }

    private enum segment {
        START(0), MIDDLE(1), END(2);
        final int part;
        segment(int i) { part = i; }

        boolean mid() { return this == MIDDLE; }
        boolean end() { return this == END; }
    }
}