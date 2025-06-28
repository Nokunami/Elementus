package net.nokunami.elementus.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.vibrations.VibrationInfo;
import net.minecraft.world.level.gameevent.vibrations.VibrationSelector;
import net.nokunami.elementus.common.item.DiarkriteBootsItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(VibrationSelector.class)
public class VibrationSelectorMixin {

    @Inject(method = "shouldReplaceVibration", at = {@At("HEAD")}, cancellable = true)
    private void Elementus$Vibrations(VibrationInfo info, long pTick, CallbackInfoReturnable<Boolean> cir) {
        Entity entity = info.entity();
        GameEvent event = info.gameEvent();
        if (entity instanceof LivingEntity aliveEntity) {
            if (DiarkriteBootsItem.SculkWalkerActivation(aliveEntity)) {
                if (event.equals(GameEvent.STEP) || event.equals(GameEvent.HIT_GROUND) || event.equals(GameEvent.EQUIP)) {
                    cir.setReturnValue(false);
                }
            }
        }
    }
}
