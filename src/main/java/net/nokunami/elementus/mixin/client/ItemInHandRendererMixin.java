package net.nokunami.elementus.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.nokunami.elementus.common.item.unique.ChargeBladeItem;
import net.nokunami.elementus.common.registry.EItems;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemInHandRenderer.class)
public class ItemInHandRendererMixin {

    @Shadow
    @Final
    private ItemRenderer itemRenderer;

    @Inject(method = "itemUsed", at = @At("HEAD"), cancellable = true)
    public void e$itemUsed(InteractionHand hand, CallbackInfo ci) {
//        LocalPlayer player = Minecraft.getInstance().player;
//        if (player != null) {
//            ItemStack itemStack = player.getUseItem();
//            if (itemStack.getItem() instanceof ChargeBladeItem) ci.cancel();
//        }
    }

//    @Inject(method = "renderItem", at = @At("HEAD"), cancellable = true)
//    public void e$renderItem(LivingEntity entity, ItemStack stack, ItemDisplayContext displayContext, boolean leftHand, PoseStack poseStack, MultiBufferSource buffer, int seed, CallbackInfo ci) {
////        LocalPlayer player = Minecraft.getInstance().player;
////        if (player != null) {
////            ItemStack itemStack = player.getUseItem();
////            if (itemStack.getItem() instanceof ChargeBladeItem) {
////                ci.cancel();
////            }
////        }
//        if (stack.getItem() instanceof ChargeBladeItem && entity != null) {
//            ci.cancel();
////            itemRenderer.renderStatic(entity, stack, displayContext, leftHand, poseStack, buffer, entity.level(), seed, OverlayTexture.NO_OVERLAY, entity.getId() + displayContext.ordinal());
//            itemRenderer.renderStatic(entity, stack, displayContext, leftHand, poseStack, buffer, entity.level(), LightTexture.FULL_BRIGHT, OverlayTexture.NO_OVERLAY, entity.getId() + displayContext.ordinal());
//        }
//    }
}
