package net.nokunami.elementus.client;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractSignEditScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

public class ModHangingSignEditScreen extends AbstractSignEditScreen {
    public static final float MAGIC_BACKGROUND_SCALE = 4.5F;
    private static final Vector3f TEXT_SCALE = new Vector3f(1.0F, 1.0F, 1.0F);
    private static final int TEXTURE_WIDTH = 16;
    private static final int TEXTURE_HEIGHT = 16;
    private final ResourceLocation texture = new ResourceLocation(this.woodType.name() + ".png").withPrefix("textures/gui/hanging_signs/");

    public ModHangingSignEditScreen(SignBlockEntity p_278017_, boolean p_277942_, boolean p_277778_) {
        super(p_278017_, p_277942_, p_277778_, Component.translatable("hanging_sign.edit"));
    }

    protected void offsetSign(GuiGraphics p_282472_, @NotNull BlockState p_282359_) {
        p_282472_.pose().translate((float)this.width / 2.0F, 125.0F, 50.0F);
    }

    protected void renderSignBackground(GuiGraphics p_282580_, @NotNull BlockState p_283648_) {
        p_282580_.pose().translate(0.0F, -13.0F, 0.0F);
        p_282580_.pose().scale(MAGIC_BACKGROUND_SCALE, MAGIC_BACKGROUND_SCALE, 1.0F);
        p_282580_.blit(this.texture, -8, -8, 0.0F, 0.0F, 16, 16, TEXTURE_WIDTH, TEXTURE_HEIGHT);
    }

    protected @NotNull Vector3f getSignTextScale() {
        return TEXT_SCALE;
    }
}
