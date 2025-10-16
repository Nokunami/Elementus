package net.nokunami.elementus.common.catalystCore;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.nokunami.elementus.common.capability.CatalystExhaustion;

public abstract class AbstractActiveAbility {
    AbilityType abilityType;

    public AbstractActiveAbility(AbilityType type) {
        abilityType = type;
    }

    public AbilityType getAbilityType() {
        return abilityType;
    }

    public boolean isReadyToCast(Entity entity, ItemStack stack) {
//        return catalystExhaustion.isExhausted() || onCooldown();
        return false;
    }

    /**
     * Tick method, does what it says on the tin.
     * Override this to implement custom tick logic to ability
     * @param entity current holder
     * @param level level
     * @param stack stack
     */
    public void tick(Entity entity, Level level, ItemStack stack) {
//        if (getExhaustionTick() > 0) {
//            catalystExhaustion.subExhaustion(1);
//            setExhaustionTick(Mth.clamp(getExhaustionPenalty() - 1, 0, 50));
//        }
//        if (isReadyToCast(entity, stack)) {
//            if (getActiveState())
//                ability(entity, level);
//        }
    }

    public void ability(Entity entity, Level level) {
    }

    /**
     * <p>"TRIGGERED" when activated it goes into cooldown.<p>
     * <p>"TOGGLED" when activated it goes into cooldown if the key is pressed again or the timer is up.<p>
     * <p>"CONSTANT" is only active if the key is pressed.<p>
     */
    public enum AbilityType {
        TRIGGERED, TOGGLED, CONSTANT
    }
}
