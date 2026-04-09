package net.nokunami.elementus.client.gui.overlay;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.nokunami.elementus.client.CAbilityClient;
import net.nokunami.elementus.common.catalystCore.ability.AbilityHolder;
import net.nokunami.elementus.common.catalystCore.ability.activeAbility.AbstractActiveAbility;
import net.nokunami.elementus.common.config.EConfig;
import net.nokunami.elementus.common.registry.CustomRegistries;

import java.util.List;

import static net.nokunami.elementus.Elementus.modLoc;
import static net.nokunami.elementus.common.registry.CustomRegistries.CatalystCoreHelper.CatalystCoreUtil;

public class CatalystAbilityOverlay implements IGuiOverlay {
    public static CatalystAbilityOverlay inst = new CatalystAbilityOverlay();

    private final ResourceLocation MISSING_ICON = modLoc("textures/gui/catalyst/ability/missing_ability_icon.png");
    private final ResourceLocation FRAME = modLoc("textures/gui/catalyst/ability_frame.png");
    private final int ABILITY_FRAME = 64;
    private final int ICON_XY = 16;
    private final int SLOT_XY = 22;
    private final int SLOT_FRAME_XY = 28;
    private final int DEFAULT_PADDING = 21;
    static final int SCREEN_BORDER_MARGIN = 20;

    @Override
    public void render(ForgeGui gui, GuiGraphics guiGraphics, float partialTick, int screenWidth, int screenHeight) {
        int x = screenWidth / 2;
        int y = screenHeight / 2;
        int sidePad = EConfig.CLIENT.abilityXPadding.get();
        int topPad = EConfig.CLIENT.abilityYPadding.get();
        if (EConfig.CLIENT.abilityIconAnchor.get().isLeft()) x += sidePad; else x -= sidePad;
        if (EConfig.CLIENT.abilityIconAnchor.get().isTop()) y += topPad; else y -= topPad;

        Player player = Minecraft.getInstance().player;
        if (player != null) {

            int selection = CAbilityClient.getSelected();
            CustomRegistries.CatalystCoreHelper helper = CatalystCoreUtil(player);
            if (helper.hasCore()) {
                List<AbilityHolder> abilityHolders = helper.getAbilities();
                int list = abilityHolders.size();
                if (!abilityHolders.isEmpty()) {
                    renderAbilitySlots(list, helper, screenWidth, screenHeight, gui, guiGraphics);
                    selection = Mth.clamp(selection, 0, list - 1);
                    renderSlotFrame(selection, list, screenWidth, screenHeight, guiGraphics);
                }
            }
        }
    }

    public void renderAbilitySlots(int abilityAmount, CustomRegistries.CatalystCoreHelper helper, int screenWidth, int screenHeight, ForgeGui gui, GuiGraphics graphics) {
        int list = Math.max(abilityAmount - 1, 0);
        for (int i = 0; i < abilityAmount; i++) {
            AbstractActiveAbility ability = helper.getAbilities().get(i).getAbility().get();

            int slotX = posX(screenWidth, SLOT_XY, abilityAmount) + xSlotPosMargin(abilityAmount, i, false);
            int slotY = posY(screenHeight, SLOT_XY, abilityAmount) + ySlotPosMargin(abilityAmount, i, false);
            int iconX = posX(screenWidth, ICON_XY, abilityAmount) + xSlotPosMargin(abilityAmount, i, false);
            int iconY = posY(screenHeight, ICON_XY, abilityAmount) + ySlotPosMargin(abilityAmount, i, false);

            if (ability != null) {
                ResourceLocation icon = ability.abilityIcon() != null ? ability.abilityIcon() : MISSING_ICON;
                graphics.blit(FRAME, slotX, slotY, 0, 0, SLOT_XY, SLOT_XY, ABILITY_FRAME, ABILITY_FRAME);
                graphics.blit(icon, iconX, iconY, 0, 0, ICON_XY, ICON_XY, ICON_XY, ICON_XY);
                int charge = CAbilityClient.getCooldowns().getCharge(ability) - ability.getCharges();
                if (charge > 0) graphics.drawString(gui.getFont(), String.valueOf(charge), iconX, iconY, ChatFormatting.WHITE.getColor());

                float f = CAbilityClient.getCooldownPercent(ability);
                int percent = (int) (ICON_XY * f + 1f);
                if (f > 0) graphics.blit(MISSING_ICON, iconX, iconY, 0, 0, ICON_XY, percent, ICON_XY, ICON_XY);
            }
        }
    }

    public void renderSlotFrame(int index, int amount, int screenWidth, int screenHeight, GuiGraphics graphics) {
        int frameX = posX(screenWidth, SLOT_FRAME_XY, amount) + xSlotPosMargin(index, index, true);
        int frameY = posY(screenHeight, SLOT_FRAME_XY, amount) + ySlotPosMargin(index, index, true);
        graphics.blit(FRAME, frameX, frameY, 32, 0, SLOT_FRAME_XY, SLOT_FRAME_XY, ABILITY_FRAME, ABILITY_FRAME);
    }

    private int posX(int screenWidth, int slotWidth, int amount) {
        var anchor = EConfig.CLIENT.abilityIconAnchor.get();
        var padding = EConfig.CLIENT.abilityXPadding.get();
        int i = screenWidth - SCREEN_BORDER_MARGIN;
        if (anchor.isCenterY()) i = screenWidth / 2;
        else if (anchor.isLeft()) i = SCREEN_BORDER_MARGIN;
        return (i + padding) - (slotWidth / 2) + offsetX(amount);
    }
    private int posY(int screenHeight, int slotHeight, int amt) {
        Anchor anchor = EConfig.CLIENT.abilityIconAnchor.get();
        var padding = EConfig.CLIENT.abilityYPadding.get();
        int i = screenHeight - SCREEN_BORDER_MARGIN;
        if (anchor.isTop()) i = SCREEN_BORDER_MARGIN;
        else if (anchor.isCenterX()) i = screenHeight / 2;
        return (i + padding) - (slotHeight / 2) + offsetY(amt);
    }

    private int xSlotPosMargin(int amt, int index, boolean isSelector) {
        Anchor anchor = EConfig.CLIENT.abilityIconAnchor.get();
        Layout layout = EConfig.CLIENT.abilityIconLayout.get();
        int padding = EConfig.CLIENT.abilityIconPadding.get();
        int am = index;
        int i = layout.isHorizontal() ? (layout.isRight() ? -am : am) : 0;
        int i1 = (i * (DEFAULT_PADDING + padding));
        return anchor.isCenterY() ? -i1 : i1;
    }
    private int ySlotPosMargin(int amount, int index, boolean isSelector) {
        Anchor anchor = EConfig.CLIENT.abilityIconAnchor.get();
        Layout layout = EConfig.CLIENT.abilityIconLayout.get();
        int padding = EConfig.CLIENT.abilityIconPadding.get();
        int am = index;
//        int i = layout.isHorizontal() ? 0 : am;
//        return i * (DEFAULT_PADDING + padding);
        int i = layout.isVertical() ? (layout.isBottom() ? -am : am) : 0;
        int i1 = (i * (DEFAULT_PADDING + padding));
        return anchor.isCenterX() ? -i1 : i1;
    }

    private int offsetX(int amt) {
        var anchor = EConfig.CLIENT.abilityIconAnchor.get();
        var layout = EConfig.CLIENT.abilityIconLayout.get();
        int padding = EConfig.CLIENT.abilityIconPadding.get();
        int a = amt > 1 ? (amt - 1) * ((DEFAULT_PADDING + padding) / (anchor.isCenterY() ? 2 : 1)) : 0;
        return layout.isVertical() ? 0 : layout.isRight() ? (anchor.isLeft() ? a : anchor.isRight() ? 0 : -a) : (anchor.isLeft() ? 0 : anchor.isRight() ? -a : a);
    }
    private int offsetY(int amt) {
        var anchor = EConfig.CLIENT.abilityIconAnchor.get();
        var layout = EConfig.CLIENT.abilityIconLayout.get();
        int padding = EConfig.CLIENT.abilityIconPadding.get();
        int a = amt > 1 ? (amt - 1) * ((DEFAULT_PADDING + padding) / (anchor.isCenterX() ? 2 : 1)) : 0;
        return layout.isHorizontal() ? 0 : layout.isBottom() ? (anchor.isTop() ? a : anchor.isBottom() ? 0 : -a) : (anchor.isTop() ? 0 : anchor.isBottom() ? -a : a);
    }

    public static class SyncCoreToGui {
        private static ItemStack stack;
        public static void setStack(ItemStack itemStack) { stack = itemStack; }
        public static ItemStack getStack() { return stack; }
    }

    public enum Anchor {
        TopLeft,
        Top,
        TopRight,
        CenterLeft,
        Center,
        CenterRight,
        BottomLeft,
        Bottom,
        BottomRight;

        public boolean isTop() { return this == TopLeft || this == Top || this == TopRight; }
        public boolean isBottom() { return this == BottomLeft || this == Bottom || this == BottomRight; }
        public boolean isLeft() { return this == TopLeft || this == CenterLeft || this == BottomLeft; }
        public boolean isRight() { return this == TopRight || this == CenterRight || this == BottomRight; }
        public boolean isCenterX() { return this == CenterLeft || this == Center || this == CenterRight; }
        public boolean isCenterY() { return this == Top || this == Center || this == Bottom; }
    }
    public enum Layout {
        VerticalTop,
        VerticalBottom,
        HorizontalLeft,
        HorizontalRight;

        public boolean isVertical() { return this == VerticalTop || this == VerticalBottom; }
        public boolean isHorizontal() { return this == HorizontalLeft || this == HorizontalRight; }
        public boolean isTop() { return this == VerticalTop; }
        public boolean isBottom() { return this == VerticalBottom; }
        public boolean isLeft() { return this == HorizontalLeft; }
        public boolean isRight() { return this == HorizontalRight; }
    }
}
