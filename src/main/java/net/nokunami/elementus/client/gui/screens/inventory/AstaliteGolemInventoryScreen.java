package net.nokunami.elementus.client.gui.screens.inventory;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.nokunami.elementus.common.entity.living.TamableGolem;
import net.nokunami.elementus.common.inventory.AstaliteGolemInventoryMenu;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

import static net.nokunami.elementus.Elementus.modLoc;

// Code from Cataclysm Ministrosity
@OnlyIn(Dist.CLIENT)
public class AstaliteGolemInventoryScreen extends AbstractContainerScreen<AstaliteGolemInventoryMenu> {
    private static final ResourceLocation RESOURCE_LOCATION = modLoc("textures/gui/container/steel_golem.png");
    private static final Component MISSING_UPGRADE_TOOLTIP = Component.translatable("container.steel_golem.missing_upgrade_tooltip");
    private static final Component MISSING_LEAVES_TOOLTIP = Component.translatable("container.steel_golem.missing_leaves_tooltip");
    private static final Component MISSING_CARPET_TOOLTIP = Component.translatable("container.steel_golem.missing_carpet_tooltip");
    private final TamableGolem golem;
    private float xMouse;
    private float yMouse;

    public AstaliteGolemInventoryScreen(AstaliteGolemInventoryMenu inventoryMenu, Inventory inventory, TamableGolem steelGolem) {
        super(inventoryMenu, inventory, steelGolem.getDisplayName());
        golem = steelGolem;
        inventoryLabelY = imageHeight - 75;
    }

    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        int i = (width - imageWidth) / 2;
        int j = (height - imageHeight) / 2;
        guiGraphics.blit(RESOURCE_LOCATION, i, j, 0, 0, imageWidth, 182);
        guiGraphics.blit(RESOURCE_LOCATION, i + 79, j + 17, 0, imageHeight + 18, golem.getInventoryColumns() * 18, 18 * 2);
        if (golem.hasChest()) guiGraphics.blit(RESOURCE_LOCATION, i + 79, j + 17, 0, imageHeight + (18 * 3), golem.getInventoryColumns() * 18, 18 * 2);
        if (golem.isSaddleable()) guiGraphics.blit(RESOURCE_LOCATION, i + 7, j + 35 - 18, 90, imageHeight + 18, 18, 18);
        if (golem.canWearArmor()) guiGraphics.blit(RESOURCE_LOCATION, i + 7, j + 35, 90, imageHeight + 36, 18, 18);

        guiGraphics.blit(RESOURCE_LOCATION, i + 7, j + 35 + 18, 90, imageHeight + 54, 18, 18);
        guiGraphics.blit(RESOURCE_LOCATION, i + 7, j + 35 + 36, 90, imageHeight + 72, 18, 18);
        golem.isRenderedOnClient = true;
        InventoryScreen.renderEntityInInventoryFollowsMouse(guiGraphics, i + 51, j + 80, 18, (float) (i + 51) - xMouse, (float) (j + 80 - 50) - yMouse, golem);
    }

    private void renderOnboardingTooltips(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY) {
        Optional<Component> optional = Optional.empty();

        if (hoveredSlot != null) {
            ItemStack armorSlot = menu.getSlot(1).getItem();
            ItemStack leavesSlot = menu.getSlot(2).getItem();
            ItemStack carpetSlot = menu.getSlot(3).getItem();
            if (armorSlot.isEmpty()) {
                if (hoveredSlot.index == 1) {
                    optional = Optional.of(MISSING_UPGRADE_TOOLTIP);
                }
            }
            if (leavesSlot.isEmpty()) {
                if (hoveredSlot.index == 2) {
                    optional = Optional.of(MISSING_LEAVES_TOOLTIP);
                }
            }
            if (carpetSlot.isEmpty()) {
                if (hoveredSlot.index == 3) {
                    optional = Optional.of(MISSING_CARPET_TOOLTIP);
                }
            }
        }

        optional.ifPresent(component -> pGuiGraphics.renderTooltip(font, font.split(component, 115), pMouseX, pMouseY));
    }

    public void render(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        renderBackground(guiGraphics);
        xMouse = (float)mouseX;
        yMouse = (float)mouseY;
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        renderOnboardingTooltips(guiGraphics, mouseX, mouseY);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
