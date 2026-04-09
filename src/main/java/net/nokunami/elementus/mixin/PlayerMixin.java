//package net.nokunami.elementus.mixin;
//
//import net.minecraft.world.entity.Entity;
//import net.minecraft.world.entity.EntityType;
//import net.minecraft.world.entity.LivingEntity;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.level.Level;
//import net.nokunami.elementus.common.item.basic.EShieldItem;
//import net.nokunami.elementus.common.item.unique.ChargeBladeItem;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
//@Mixin(Player.class)
//public abstract class PlayerMixin extends LivingEntity {
//
//    protected PlayerMixin(EntityType<? extends LivingEntity> pEntityType, Level pLevel) { super(pEntityType, pLevel); }
//
//    @Inject(method = "disableShield", at = @At("HEAD"), cancellable = true)
//    public void E$disableShields(boolean pBecauseOfAxe, CallbackInfo ci) {
//        Entity entity = this;
//        if (this.getUseItem().getItem() instanceof ChargeBladeItem) {
//            ci.cancel();
//        }
//        if (getUseItem().getItem() instanceof EShieldItem shieldItem) {
//            ci.cancel();
//            shieldItem.applyCustomCooldown((Player) entity, getUseItem());
//        }
//    }
//}
