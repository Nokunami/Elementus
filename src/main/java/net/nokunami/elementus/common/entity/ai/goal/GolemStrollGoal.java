package net.nokunami.elementus.common.entity.ai.goal;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.nokunami.elementus.common.entity.living.TamableGolem;

public class GolemStrollGoal extends WaterAvoidingRandomStrollGoal {
    TamableGolem mob;

    public GolemStrollGoal(TamableGolem golem, double speedMod) {
        super(golem, speedMod);
        mob = golem;
    }
    @Override public boolean canUse() { return !mob.isOrderedToSit() && !mob.isChassisBroken() && super.canUse(); }
}
