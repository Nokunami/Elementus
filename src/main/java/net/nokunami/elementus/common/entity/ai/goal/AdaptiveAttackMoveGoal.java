package net.nokunami.elementus.common.entity.ai.goal;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.pathfinder.Node;
import net.minecraft.world.level.pathfinder.Path;
import net.nokunami.elementus.common.entity.living.combat.IAdaptiveAttacker;

import java.util.EnumSet;

public class AdaptiveAttackMoveGoal<T extends Mob & IAdaptiveAttacker> extends Goal {
    T mob;
    IAdaptiveAttacker.Properties properties;
    LivingEntity target;

    final double speedModifier;
    final boolean followingEvenIfNotSeen;
    Path path;
    double pathedTargetX;
    double pathedTargetY;
    double pathedTargetZ;
    int ticksUntilNextPathRecalculation;
    public int ticksUntilNextAttack;
    long lastCanUseCheck;
    int failedPathFindingPenalty = 0;
    boolean canPenalize = false;

    public AdaptiveAttackMoveGoal(T attacker) {
        mob = attacker;
        properties = mob.info();

        speedModifier = 1;
        followingEvenIfNotSeen = false;
        setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        long i = mob.level().getGameTime();
        if (i - lastCanUseCheck < 20L) {
            return false;
        } else {
            lastCanUseCheck = i;
            LivingEntity livingentity = mob.getTarget();
            if (livingentity == null) {
                return false;
            } else if (!livingentity.isAlive()) {
                return false;
            } else {
                target = livingentity;
                if (canPenalize) {
                    if (--ticksUntilNextPathRecalculation <= 0) {
                        path = mob.getNavigation().createPath(livingentity, 0);
                        ticksUntilNextPathRecalculation = 4 + mob.getRandom().nextInt(7);
                        return path != null;
                    } else {
                        return true;
                    }
                }
                path = mob.getNavigation().createPath(livingentity, 0);
                if (path != null) {
                    return true;
                } else {
                    return getAttackReachSqr(livingentity) >= mob.distanceToSqr(livingentity.getX(), livingentity.getY(), livingentity.getZ());
                }
            }
        }
    }

    @Override
    public boolean canContinueToUse() {
        return target instanceof Player p ? !(p.isCreative() || p.isSpectator()) && p.isAlive() : (target.isAttackable() && target.isAlive() && !target.isSpectator());
    }

    @Override
    public void start() {
        target = mob.getTarget();
        mob.getNavigation().moveTo(path, speedModifier);
        mob.setAggressive(true);
        ticksUntilNextPathRecalculation = 0;
        ticksUntilNextAttack = 0;
    }

    @Override
    public void stop() {
        LivingEntity livingentity = mob.getTarget();
        if (!EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(livingentity)) mob.setTarget(null);

        mob.setAggressive(false);
        mob.getNavigation().stop();
    }

    @Override public boolean requiresUpdateEveryTick() { return true; }

    @Override
    public void tick() {
        if (target != null) {
            double d0 = mob.getPerceivedTargetDistanceSquareForMeleeAttack(target);
            movement(d0);
        }
    }

    protected double getAttackReachSqr(LivingEntity target) {
        return mob.getBbWidth() * 2 * mob.getBbWidth() * 2 + target.getBbWidth();
    }

    protected void movement(double distToTarget) {
        mob.getLookControl().setLookAt(target, 30.0F, 30.0F);
        ticksUntilNextPathRecalculation = Math.max(ticksUntilNextPathRecalculation - 1, 0);
        if ((followingEvenIfNotSeen || mob.getSensing().hasLineOfSight(target))
                && ticksUntilNextPathRecalculation <= 0
                && (isALlPathZero() || isSqrDistMoreThan1() || mob.getRandom().nextFloat() < 0.05F)) {
            pathedTargetX = target.getX();
            pathedTargetY = target.getY();
            pathedTargetZ = target.getZ();
            ticksUntilNextPathRecalculation = 4 + mob.getRandom().nextInt(7);
            if (canPenalize) {
                ticksUntilNextPathRecalculation += failedPathFindingPenalty;
                if (mob.getNavigation().getPath() != null) {
                    Node finalPathPoint = mob.getNavigation().getPath().getEndNode();
                    if (finalPathPoint != null && isSqrDistLessThan(finalPathPoint, 1)) failedPathFindingPenalty = 0;
                    else failedPathFindingPenalty += 10;
                } else failedPathFindingPenalty += 10;
            }
            if (distToTarget > Mth.square(32)) ticksUntilNextPathRecalculation += 10;
            else if (distToTarget > Mth.square(16)) ticksUntilNextPathRecalculation += 5;

            if (!mob.getNavigation().moveTo(target, speedModifier)) ticksUntilNextPathRecalculation += 15;
            ticksUntilNextPathRecalculation = adjustedTickDelay(ticksUntilNextPathRecalculation);
        }
        ticksUntilNextAttack = Math.max(ticksUntilNextAttack - 1, 0);
    }

    boolean isALlPathZero() {
        return pathedTargetX == 0 && pathedTargetY == 0 && pathedTargetZ == 0;
    }

    boolean isSqrDistMoreThan1() {
        return target.distanceToSqr(pathedTargetX, pathedTargetY, pathedTargetZ) >= 1;
    }

    boolean isSqrDistLessThan(Node node, double value) {
        return target.distanceToSqr(node.x, node.y, node.z) >= value;
    }
}
