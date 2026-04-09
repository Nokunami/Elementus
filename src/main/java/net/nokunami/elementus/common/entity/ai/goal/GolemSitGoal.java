package net.nokunami.elementus.common.entity.ai.goal;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.nokunami.elementus.common.entity.living.TamableGolem;

import java.util.EnumSet;

public class GolemSitGoal extends Goal {
    private final TamableGolem mob;

    public GolemSitGoal(TamableGolem golem) {
        mob = golem;
        setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        if (!mob.isTamed() || mob.isInWaterOrBubble() || !mob.onGround()) {
            return false;
        } else {
            LivingEntity owner = mob.getOwner();
            return owner == null || mob.distanceToSqr(owner) < Mth.square(12) && owner.getLastHurtByMob() != null || mob.isOrderedToSit();
        }
    }

//    @Override public void start() {
//        mob.getNavigation().stop();
//        mob.sitOrder(true);
//    }
//
//    @Override public void stop() { mob.sitOrder(false); }
}
