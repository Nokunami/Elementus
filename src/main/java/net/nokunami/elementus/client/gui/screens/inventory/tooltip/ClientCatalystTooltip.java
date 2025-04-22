package net.nokunami.elementus.client.gui.screens.inventory.tooltip;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.nokunami.elementus.client.render.item.inventory.CatalystTooltip;
import org.jetbrains.annotations.NotNull;

import static net.nokunami.elementus.Elementus.modLoc;

@OnlyIn(Dist.CLIENT)
public class ClientCatalystTooltip implements ClientTooltipComponent {
    public static final ResourceLocation TEXTURE_LOCATION = modLoc("textures/gui/container/catalyst_slot.png");
    private static final int MARGIN_Y = 4;
    private static final int TEX_SIZE_X = 32;
    private static final int TEX_SIZE_Y = 16;
    private static final int SLOT_SIZE_X = 16;
    private static final int SLOT_SIZE_Y = 12;
    private final NonNullList<ItemStack> coreItem;
    private final NonNullList<ItemStack> elytraItem;

    public ClientCatalystTooltip(CatalystTooltip tooltip) {
        this.coreItem = tooltip.getCoreItem();
        this.elytraItem = tooltip.getElytraItem();
    }

    public int getHeight() {
        return this.gridSizeY() * SLOT_SIZE_Y + 2 + MARGIN_Y;
    }

    public int getWidth(@NotNull Font pFont) {
        return this.gridSizeX() * SLOT_SIZE_X + 2;
    }

    public void renderImage(@NotNull Font pFont, int pX, int pY, @NotNull GuiGraphics pGuiGraphics) {
        int sizeX = this.gridSizeX();
        int sizeY = this.gridSizeY();
        int k = 0;

        for(int l = 0; l < sizeY; ++l) {
            for(int i1 = 0; i1 < sizeX; ++i1) {
                int j1 = pX + i1 * 16;
                int k1 = pY + l * 16;
                this.renderSlot(j1, k1, k++, pGuiGraphics);
            }
        }
    }

    private void renderSlot(int pX, int pY, int pItemIndex, GuiGraphics pGuiGraphics) {
        if (this.coreItem.isEmpty()) {
            this.blit(pGuiGraphics, pX, pY, Texture.CORE);
        } else {
            ItemStack itemstack = this.coreItem.get(0);
            pGuiGraphics.renderItem(itemstack, pX, pY, pItemIndex);
        }
        if (this.elytraItem.isEmpty()) {
            this.blit(pGuiGraphics, pX + 16, pY, Texture.ELYTRA);
        } else {
            ItemStack itemstack = this.elytraItem.get(0);
            pGuiGraphics.renderItem(itemstack, pX + 16, pY, pItemIndex);
        }
    }

    private void blit(GuiGraphics pGuiGraphics, int pX, int pY, ClientCatalystTooltip.Texture pTexture) {
        pGuiGraphics.blit(TEXTURE_LOCATION, pX, pY, 0, (float)pTexture.x, (float)pTexture.y, pTexture.w, pTexture.h, TEX_SIZE_X, TEX_SIZE_Y);
    }

    private int gridSizeX() {
        return 1;
    }

    private int gridSizeY() {
        return this.gridSizeX();
    }

    @OnlyIn(Dist.CLIENT)
    enum Texture {
//        SLOT(0, 0, 18, 20),
//        BLOCKED_SLOT(0, 40, 18, 20),
//        BORDER_VERTICAL(0, 18, 1, 20),
//        BORDER_HORIZONTAL_TOP(0, 20, 18, 1),
//        BORDER_HORIZONTAL_BOTTOM(0, 60, 18, 1),
//        BORDER_CORNER_TOP(0, 20, 1, 1),
//        BORDER_CORNER_BOTTOM(0, 60, 1, 1),
        ELYTRA(16, 0, 16, 16),
        CORE(0, 0, 16, 16);

        public final int x;
        public final int y;
        public final int w;
        public final int h;

        Texture(int pX, int pY, int pW, int pH) {
            this.x = pX;
            this.y = pY;
            this.w = pW;
            this.h = pH;
        }
    }
}
