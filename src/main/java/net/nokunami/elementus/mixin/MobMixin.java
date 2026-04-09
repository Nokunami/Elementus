//package net.nokunami.elementus.mixin;
//
//import net.minecraft.world.entity.*;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.item.ItemStack;
//import net.nokunami.elementus.common.item.unique.ChargeBladeItem;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
//@Mixin(Mob.class)
//public abstract class MobMixin {
//
//    @Inject(at = @At("HEAD"), method = ("maybeDisableShield"), cancellable = true)
//    private void E$maybeDisableShield(Player player, ItemStack attackerAxe, ItemStack defendantShield, CallbackInfo ci) {
//        if (defendantShield.getItem() instanceof ChargeBladeItem) {
//            ci.cancel();
//        }
//    }
//}