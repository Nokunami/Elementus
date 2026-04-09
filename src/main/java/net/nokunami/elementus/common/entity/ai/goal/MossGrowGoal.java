package net.nokunami.elementus.common.entity.ai.goal;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.nokunami.elementus.common.entity.living.IMossOverTime;

public class MossGrowGoal<T extends LivingEntity & IMossOverTime> extends Goal {
    T mob;

    public MossGrowGoal(T golem) {
        mob = golem;
    }

    @Override public boolean canUse() {
        return mob.thoseWhoGrow();
    }

    @Override
    public void tick() {
        mob.setMossTimer(mob.getMossTimer() + 1);

        if (mob.getMossTimer() > 144000) {
            mob.setMossStage(mob.getMossStage() + 1);
            mob.setMossTimer(0);
        }
    }
}
