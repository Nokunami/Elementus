package net.nokunami.elementus.client.gui.overlay;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.nokunami.elementus.client.AbilityDataClient;
import net.nokunami.elementus.common.catalystCore.ability.PassiveAbility;
import net.nokunami.elementus.config.EConfig;

import static net.nokunami.elementus.Elementus.modLoc;

public class CatalystAbilityOverlay implements IGuiOverlay {
    public static CatalystAbilityOverlay inst = new CatalystAbilityOverlay();

    private final ResourceLocation MISSING_ICON = modLoc("textures/gui/catalyst/ability/missing_ability_icon.png");
    private final ResourceLocation ABILITY_FRAME = modLoc("textures/gui/catalyst/ability_frame.png");
    private final ResourceLocation ABILITY_FRAME_CHARGE = modLoc("textures/gui/catalyst/ability_frame_charge.png");
    private final ResourceLocation ABILITY_FRAME_ALT = modLoc("textures/gui/catalyst/ability_frame_alt.png");
    private final ResourceLocation ABILITY_FRAME_SELECTED = modLoc("textures/gui/catalyst/ability_frame_selected.png");
    private final ResourceLocation ABILITY_FRAME_SELECTED_CHARGE = modLoc("textures/gui/catalyst/ability_frame_selected_charge.png");
    private final int DEFAULT_PADDING = 21;
    static final int SCREEN_BORDER_MARGIN = 20;

    @Override
    public void render(ForgeGui fGui, GuiGraphics guiG, float partialTick, int screenWidth, int screenHeight) {
        Player player = Minecraft.getInstance().player;
        if (player != null) {

//            int selection = AbilityDataClient.getSelected();
            var abilities = AbilityDataClient.getAbilityStorage().getAbilitiesInStorage();
            var list = abilities.size();
            if (!abilities.isEmpty()) {
//                guiG.drawCenteredString(fGui.getFont(), READ_TO_CAST_TEXT, x, y + 36, TEXT_COLOR);
                renderAbilitySlots(list, screenWidth, screenHeight, fGui, guiG);
//                selection = Mth.clamp(selection, 0, list - 1);
//                if (!(abilities.get(selection).getAbility() instanceof PassiveAbility))
//                    renderSlotFrame(selection, list, screenWidth, screenHeight, fGui, guiG);
                renderSlotCharge(list, screenWidth, screenHeight, fGui, guiG);
            }
        }
    }

    public void renderAbilitySlots(int amount, int screenWidth, int screenHeight, ForgeGui fGui, GuiGraphics guiG) {
        for (int index = 0; index < amount; index++) {
            var abilities = AbilityDataClient.getAbilityStorage().getAbilitiesInStorage();
            var ability = abilities.get(index);
            int frameSize = 22;
            int iconSize = 16;

            int slotX = posX(screenWidth, frameSize, amount) + xSlotPosMargin(amount, index);
            int slotY = posY(screenHeight, frameSize, amount) + ySlotPosMargin(amount, index);
            int iconX = posX(screenWidth, iconSize, amount) + xSlotPosMargin(amount, index);
            int iconY = posY(screenHeight, iconSize, amount) + ySlotPosMargin(amount, index);

            if (ability != null) {
                ResourceLocation icon = ability.getAbility().abilityIcon() != null ? ability.getAbility().abilityIcon() : MISSING_ICON;
                guiG.blit(ABILITY_FRAME, slotX, slotY, 0, 0, frameSize, frameSize, frameSize, frameSize);
//                var cyclingFrame = new CyclingTexture(frameSize, frameSize);
//                cyclingFrame.tick();
                guiG.blit(icon, iconX, iconY, 0, 0, iconSize, iconSize, iconSize, iconSize);

                float f = AbilityDataClient.getCooldownPercent(ability.getAbility());
                int percent = (int) (iconSize * f + 1f);
                if (f > 0) guiG.blit(MISSING_ICON, iconX, iconY, 0, 0, iconSize, percent, iconSize, iconSize);

                if (!(ability.getAbility() instanceof PassiveAbility) && ability.isSelected())
                    renderSlotFrame(index, amount, screenWidth, screenHeight, fGui, guiG);
            }
        }
    }

    public void renderSlotFrame(int index, int amount, int screenWidth, int screenHeight, ForgeGui fGui, GuiGraphics guiG) {
//        var abilities = AbilityDataClient.getAbilityStorage().getAbilitiesInStorage();
//        var ability = abilities.get(index).getAbility();
        int selectorSize = 28;
        int frameX = posX(screenWidth, selectorSize, amount) + xSlotPosMargin(index, index);
        int frameY = posY(screenHeight, selectorSize, amount) + ySlotPosMargin(index, index);
        guiG.blit(ABILITY_FRAME_SELECTED, frameX, frameY, 0, 0, selectorSize, selectorSize, selectorSize, selectorSize);
//        int charge = AbilityDataClient.getCooldowns().getCharge(ability) - ability.getCharges();
//        if (charge > 0) {
//            int iconSize = 16;
//            int iconX = posX(screenWidth, iconSize, amount) + xSlotPosMargin(amount, index);
//            int iconY = posY(screenHeight, iconSize, amount) + ySlotPosMargin(amount, index);
//
//            int chargeFrame = 32;
//            int chargeFrameX = posX(screenWidth, chargeFrame, amount) + xSlotPosMargin(amount, index);
//            int chargeFrameY = posY(screenHeight, chargeFrame, amount) + ySlotPosMargin(amount, index);
//            guiG.blit(ABILITY_FRAME_CHARGE, chargeFrameX, chargeFrameY, 0, 0, chargeFrame, chargeFrame, chargeFrame, chargeFrame);
//            guiG.drawCenteredString(fGui.getFont(), String.valueOf(charge), iconX + iconSize + 2, iconY + iconSize - 2, 16777215);
//        }
    }

    public void renderSlotCharge(int amount, int screenWidth, int screenHeight, ForgeGui fGui, GuiGraphics guiG) {
        for (int index = 0; index < amount; index++) {
            var abilities = AbilityDataClient.getAbilityStorage().getAbilitiesInStorage();
            var ability = abilities.get(index).getAbility();
            int charge = AbilityDataClient.getCooldowns().getCharge(ability)/* - ability.getCharges()*/;
            if (charge > 0) {
                int iconSize = 16;
                int iconX = posX(screenWidth, iconSize, amount) + xSlotPosMargin(amount, index);
                int iconY = posY(screenHeight, iconSize, amount) + ySlotPosMargin(amount, index);

                int chargeFrame = 32;
                int chargeFrameX = posX(screenWidth, chargeFrame, amount) + xSlotPosMargin(amount, index);
                int chargeFrameY = posY(screenHeight, chargeFrame, amount) + ySlotPosMargin(amount, index);
                guiG.blit(ABILITY_FRAME_CHARGE, chargeFrameX, chargeFrameY, 0, 0, chargeFrame, chargeFrame, chargeFrame, chargeFrame);
                guiG.drawCenteredString(fGui.getFont(), String.valueOf(charge), iconX + iconSize + 2, iconY + iconSize - 2, 16777215);
            }
        }
    }

    private int posX(int screenWidth, int slotWidth, int amount) {
        var anchor = EConfig.overlayConfig.abilityIconAnchor;
        var padding = EConfig.overlayConfig.abilityXPadding;

        int i = screenWidth - SCREEN_BORDER_MARGIN;
        if (anchor.isCenterY()) i = screenWidth / 2;
        else if (anchor.isLeft()) i = SCREEN_BORDER_MARGIN;
        return (i + padding) - (slotWidth / 2) + offsetX(amount);
    }
    private int posY(int screenHeight, int slotHeight, int amt) {
        var anchor = EConfig.overlayConfig.abilityIconAnchor;
        var padding = EConfig.overlayConfig.abilityYPadding;

        int i = screenHeight - SCREEN_BORDER_MARGIN;
        if (anchor.isTop()) i = SCREEN_BORDER_MARGIN;
        else if (anchor.isCenterX()) i = screenHeight / 2;
        return (i + padding) - (slotHeight / 2) + offsetY(amt);
    }

    private int xSlotPosMargin(int amt, int index) {
        var anchor = EConfig.overlayConfig.abilityIconAnchor;
        var layout = EConfig.overlayConfig.abilityIconLayout;
        int padding = EConfig.overlayConfig.abilityIconPadding;

        int am = index;
        int i = layout.isHorizontal() ? (layout.isRight() ? -am : am) : 0;
        int i1 = (i * (DEFAULT_PADDING + padding));
        return anchor.isCenterY() ? -i1 : i1;
    }
    private int ySlotPosMargin(int amount, int index) {
        var anchor = EConfig.overlayConfig.abilityIconAnchor;
        var layout = EConfig.overlayConfig.abilityIconLayout;
        int padding = EConfig.overlayConfig.abilityIconPadding;

        int am = index;
//        int i = layout.isHorizontal() ? 0 : am;
//        return i * (DEFAULT_PADDING + padding);
        int i = layout.isVertical() ? (layout.isBottom() ? -am : am) : 0;
        int i1 = (i * (DEFAULT_PADDING + padding));
        return anchor.isCenterX() ? -i1 : i1;
    }

    private int offsetX(int amt) {
        var anchor = EConfig.overlayConfig.abilityIconAnchor;
        var layout = EConfig.overlayConfig.abilityIconLayout;
        int padding = EConfig.overlayConfig.abilityIconPadding;

        int a = amt > 1 ? (amt - 1) * ((DEFAULT_PADDING + padding) / (anchor.isCenterY() ? 2 : 1)) : 0;
        return layout.isVertical() ? 0 : layout.isRight() ? (anchor.isLeft() ? a : anchor.isRight() ? 0 : -a) : (anchor.isLeft() ? 0 : anchor.isRight() ? -a : a);
    }
    private int offsetY(int amt) {
        var anchor = EConfig.overlayConfig.abilityIconAnchor;
        var layout = EConfig.overlayConfig.abilityIconLayout;
        int padding = EConfig.overlayConfig.abilityIconPadding;

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