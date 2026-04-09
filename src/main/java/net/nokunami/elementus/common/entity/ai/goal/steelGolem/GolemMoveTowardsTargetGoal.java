package net.nokunami.elementus.common.entity.ai.goal.steelGolem;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.phys.Vec3;
import net.nokunami.elementus.common.entity.living.TamableGolem;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class GolemMoveTowardsTargetGoal extends Goal {
    private final TamableGolem mob;
    @Nullable
    private LivingEntity target;
    private double wantedX;
    private double wantedY;
    private double wantedZ;
    private final double speedModifier;
    private final float within;

    public GolemMoveTowardsTargetGoal(TamableGolem mob, double speed, float within) {
        this.mob = mob;
        this.speedModifier = speed;
        this.within = within;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    /**
     * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
     * method as well.
     */
    public boolean canUse() {
        this.target = this.mob.getTarget();
        if (mob.isChassisBroken() || mob.getAoeTimer() <= 0 || mob.isOrderedToSit())
            return false;
        else if (this.target == null) {
            return false;
        } else if (this.target.distanceToSqr(this.mob) > (double)(this.within * this.within)) {
            return false;
        } else {
            Vec3 vec3 = DefaultRandomPos.getPosTowards(this.mob, 16, 7, this.target.position(), (float)Math.PI / 2F);
            if (vec3 == null) {
                return false;
            } else {
                this.wantedX = vec3.x;
                this.wantedY = vec3.y;
                this.wantedZ = vec3.z;
                return true;
            }
        }
    }

    public boolean canContinueToUse() {
        return !this.mob.getNavigation().isDone() && this.target.isAlive() && this.target.distanceToSqr(this.mob) < (double)(this.within * this.within);
    }

    public void stop() {
        this.target = null;
    }

    public void start() {
        this.mob.getNavigation().moveTo(this.wantedX, this.wantedY, this.wantedZ, this.speedModifier);
    }
}
