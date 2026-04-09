//package net.nokunami.elementus.mixin.client;
//
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.player.LocalPlayer;
//import net.minecraft.client.renderer.ItemInHandRenderer;
//import net.minecraft.world.InteractionHand;
//import net.minecraft.world.item.ItemStack;
//import net.nokunami.elementus.common.item.unique.ChargeBladeItem;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
//@Mixin(ItemInHandRenderer.class)
//public class ItemInHandRendererMixin {
//
//    @Inject(method = "itemUsed", at = @At("HEAD"), cancellable = true)
//    public void elementus$itemUsed(InteractionHand pHand, CallbackInfo ci) {
//        LocalPlayer player = Minecraft.getInstance().player;
//        if (player != null) {
//            ItemStack itemStack = player.getUseItem();
//            if (itemStack.getItem() instanceof ChargeBladeItem) ci.cancel();
//        }
//    }
//}
