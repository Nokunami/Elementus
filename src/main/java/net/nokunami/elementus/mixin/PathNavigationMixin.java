//package net.nokunami.elementus.mixin;
//
//import net.minecraft.world.entity.Mob;
//import net.minecraft.world.entity.ai.navigation.PathNavigation;
//import org.spongepowered.asm.mixin.Final;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Shadow;
//import org.spongepowered.asm.mixin.Unique;
//
//@Mixin(PathNavigation.class)
//public abstract class PathNavigationMixin {
//    @Shadow
//    @Final
//    protected Mob mob;
//    @Unique
//    private boolean golemCrouching;
//
////    @Inject(method = "createPath(Ljava/util/Set;IZIF)Lnet/minecraft/world/level/pathfinder/Path;", at = @At("HEAD"))
////    public void elementus$golemCrouchPath(Set<BlockPos> pTargets, int pRegionOffset, boolean pOffsetUpward, int pAccuracy, float pFollowRange, CallbackInfoReturnable<Path> cir) {
////        if (mob instanceof AstaliteGolem golem) {
////            golemCrouching = golem.isCrouching();
////        }
////    }
//}
