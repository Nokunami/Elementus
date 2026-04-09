package net.nokunami.elementus.common.entity.ai.goal;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.nokunami.elementus.common.entity.MovementType;
import net.nokunami.elementus.common.entity.living.AstaliteGolem;

import static net.nokunami.elementus.common.entity.MovementType.RUN;
import static net.nokunami.elementus.common.entity.MovementType.WALK;

public class MovementTypeGoal extends Goal {
    AstaliteGolem golem;

    public MovementTypeGoal(AstaliteGolem mob) {
        golem = mob;
    }

    @Override public boolean canUse() {
        return true;
    }

    @Override
    public void tick() {
        LivingEntity rider = golem.getControllingPassenger();
        if (rider != null) {
            setMoveType(rider.isSprinting() ? RUN : WALK);
        } else {
            setMoveType(golem.isSprinting() ? RUN : WALK);
        }
    }

    void setMoveType(MovementType moveType) {
        golem.setMovementType(moveType);
    }
}
