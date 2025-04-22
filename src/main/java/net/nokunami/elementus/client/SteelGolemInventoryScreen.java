package net.nokunami.elementus.client;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.nokunami.elementus.common.entity.living.TamableChestedGolem;
import net.nokunami.elementus.common.entity.living.TamableGolem;
import net.nokunami.elementus.common.inventory.SteelGolemInventoryMenu;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

import static net.nokunami.elementus.Elementus.modLoc;

// Code from Cataclysm Ministrosity
@OnlyIn(Dist.CLIENT)
public class SteelGolemInventoryScreen extends AbstractContainerScreen<SteelGolemInventoryMenu> {
    private static final ResourceLocation RESOURCE_LOCATION = modLoc("textures/gui/container/steel_golem.png");
    private static final Component MISSING_UPGRADE_TOOLTIP = Component.translatable("container.steel_golem.missing_upgrade_tooltip");
    private static final Component MISSING_LEAVES_TOOLTIP = Component.translatable("container.steel_golem.missing_leaves_tooltip");
    private static final Component MISSING_CARPET_TOOLTIP = Component.translatable("container.steel_golem.missing_carpet_tooltip");
    private final TamableGolem golem;
    private float xMouse;
    private float yMouse;

    public SteelGolemInventoryScreen(SteelGolemInventoryMenu inventoryMenu, Inventory inventory, TamableGolem steelGolem) {
        super(inventoryMenu, inventory, steelGolem.getDisplayName());
        this.golem = steelGolem;
        this.inventoryLabelY = this.imageHeight - 75;
    }


    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        guiGraphics.blit(RESOURCE_LOCATION, i, j, 0, 0, this.imageWidth, 182);
        if (this.golem instanceof TamableChestedGolem chestedGolem) {
            if (chestedGolem.hasChest()) {
                guiGraphics.blit(RESOURCE_LOCATION, i + 79, j + 17, 0, this.imageHeight + 18, ((TamableChestedGolem)golem).getInventoryColumns() * 18, 72);
//                guiGraphics.blit(RESOURCE_LOCATION, i + 61, j + 35 + 36, 90, this.imageHeight + 18, 18, 18);
            }
        }

        if (this.golem.isSaddleable()) {
            guiGraphics.blit(RESOURCE_LOCATION, i + 7, j + 35 - 18, 90, this.imageHeight + 18, 18, 18);
        }

        if (this.golem.canWearArmor()) {
            guiGraphics.blit(RESOURCE_LOCATION, i + 7, j + 35, 90, this.imageHeight + 36, 18, 18);
        }

        guiGraphics.blit(RESOURCE_LOCATION, i + 7, j + 35 + 18, 90, this.imageHeight + 54, 18, 18);
        guiGraphics.blit(RESOURCE_LOCATION, i + 7, j + 35 + 36, 90, this.imageHeight + 72, 18, 18);

        InventoryScreen.renderEntityInInventoryFollowsMouse(guiGraphics, i + 51, j + 80, 18, (float)(i + 51) - this.xMouse, (float)(j + 75 - 50) - this.yMouse, golem);
    }



    private void renderOnboardingTooltips(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY) {
        Optional<Component> optional = Optional.empty();

        if (this.hoveredSlot != null) {
            ItemStack armorSlot = this.menu.getSlot(1).getItem();
            ItemStack leavesSlot = this.menu.getSlot(2).getItem();
            ItemStack carpetSlot = this.menu.getSlot(3).getItem();
            if (armorSlot.isEmpty()) {
                if (this.hoveredSlot.index == 1) {
                    optional = Optional.of(MISSING_UPGRADE_TOOLTIP);
                }
            }
            if (leavesSlot.isEmpty()) {
                if (this.hoveredSlot.index == 2) {
                    optional = Optional.of(MISSING_LEAVES_TOOLTIP);
                }
            }
            if (carpetSlot.isEmpty()) {
                if (this.hoveredSlot.index == 3) {
                    optional = Optional.of(MISSING_CARPET_TOOLTIP);
                }
            }
        }

        optional.ifPresent((p_280863_) -> pGuiGraphics.renderTooltip(this.font, this.font.split(p_280863_, 115), pMouseX, pMouseY));
    }

    public void render(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics);
        this.xMouse = (float)mouseX;
        this.yMouse = (float)mouseY;
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        this.renderOnboardingTooltips(guiGraphics, mouseX, mouseY);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
