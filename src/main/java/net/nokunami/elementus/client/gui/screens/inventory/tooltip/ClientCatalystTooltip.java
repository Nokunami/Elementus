package net.nokunami.elementus.client.gui.screens.inventory.tooltip;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.nokunami.elementus.client.render.item.inventory.CatalystTooltip;
import org.jetbrains.annotations.NotNull;

import static net.nokunami.elementus.Elementus.modLoc;

public class ClientCatalystTooltip implements ClientTooltipComponent {
    public static final ResourceLocation LOC = modLoc("textures/gui/container/catalyst_slot.png");
    private static final int MARGIN_Y = 4;
    private static final int TEX_SIZE_X = 48;
    private static final int TEX_SIZE_Y = 48;
    private static final int SLOT_SIZE_X = 16;
    private static final int SLOT_SIZE_Y = 12;
    private final NonNullList<ItemStack> coreItem;
    private final NonNullList<ItemStack> elytraItem;
    private final int selection;
    private final int trim;
    private ItemStack stack;

    public ClientCatalystTooltip(CatalystTooltip tooltip) {
        coreItem = tooltip.getCoreStack();
        elytraItem = tooltip.getElytraStack();
        selection = tooltip.getSelection();
        trim = tooltip.trim();
        stack = tooltip.stack();
    }

    public int getHeight() { return gridSizeY() * SLOT_SIZE_Y + 2 + MARGIN_Y; }
    public int getWidth(@NotNull Font pFont) { return gridSizeX() * SLOT_SIZE_X + 2; }

    public void renderImage(@NotNull Font font, int x, int y, @NotNull GuiGraphics graphics) {
        int sizeX = gridSizeX();
        int sizeY = gridSizeY();
        int k = 0;

        for(int l = 0; l < sizeY; ++l) {
            for(int i1 = 0; i1 < sizeX; ++i1) {
                int j1 = x + i1 * 16;
                int k1 = y + l * 16;
                renderSlot(j1, k1, k++, graphics);
            }
        }
    }

    private void renderSlot(int x, int y, int index, GuiGraphics graphics) {
        if (coreItem.isEmpty()) {
            blit(graphics, x, y, Texture.CORE);
        } else {
            ItemStack itemstack = coreItem.get(0);
            graphics.renderItem(itemstack, x, y, index);
        }
        if (elytraItem.isEmpty()) {
            blit(graphics, x + 16, y, Texture.ELYTRA);
        } else {
            ItemStack itemstack = elytraItem.get(0);
            graphics.renderItem(itemstack, x + 16, y, index);
        }
        if (trim > 0) blit(graphics, x + 16 * 2, y, trim == 2 ? Texture.TRIM_OFF : Texture.TRIM_ON);
        blit(graphics, x + (selection * 16), y, Texture.SELECTION);
    }

    private void blit(GuiGraphics graphics, int x, int y, ClientCatalystTooltip.Texture texture) {
        graphics.blit(LOC, x, y, 0, (float) texture.x, (float) texture.y, texture.w, texture.h, TEX_SIZE_X, TEX_SIZE_Y);
    }

    private int gridSizeX() { return 1; }
    private int gridSizeY() { return gridSizeX(); }

    enum Texture {
        CORE(0, 0, 16, 16),
        ELYTRA(16, 0, 16, 16),
        SELECTION(0, 16, 16, 16),
        TRIM_ON(32, 0, 16, 16),
        TRIM_OFF(32, 16, 16, 16),
        ;

        public final int x;
        public final int y;
        public final int w;
        public final int h;

        Texture(int pX, int pY, int pW, int pH) {
            x = pX;
            y = pY;
            w = pW;
            h = pH;
        }
    }
}
