package net.nokunami.elementus.mixin.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.nokunami.elementus.client.ModHangingSignEditScreen;
import net.nokunami.elementus.common.block.entity.ModHangingSignBlockEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LocalPlayer.class)
public class HangingSignEditScreenMixin {
    @Shadow
    @Final
    protected Minecraft minecraft;

    @Inject(at = @At(value = "HEAD"), method = "openTextEdit", cancellable = true)
    private void elementus$openCanvasSignEditScreen(SignBlockEntity signBlockEntity, boolean isFront, CallbackInfo ci) {
        if (signBlockEntity instanceof ModHangingSignBlockEntity) {
            minecraft.setScreen(new ModHangingSignEditScreen(signBlockEntity, isFront, minecraft.isTextFilteringEnabled()));
            ci.cancel();
        }
    }
}
