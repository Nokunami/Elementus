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

public class AdaptiveAttackGoal<T extends Mob & IAdaptiveAttacker> extends Goal {
    T mob;
    IAdaptiveAttacker.Properties properties;
    LivingEntity target;

    final double speedModifier;
    final boolean followingEvenIfNotSeen;
    Path path;
    int ticksUntilNextPathRecalculation;
    public int ticksUntilNextAttack;
    long lastCanUseCheck;
    boolean canPenalize = false;

    public AdaptiveAttackGoal(T attacker) {
        mob = attacker;
        properties = mob.info();

        speedModifier = 1;
        followingEvenIfNotSeen = false;
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
        mob.postAttack();

        mob.setAggressive(false);
        mob.getNavigation().stop();
    }

    @Override public boolean requiresUpdateEveryTick() { return true; }

    @Override
    public void tick() {
        if (target != null) {
            double d0 = mob.getPerceivedTargetDistanceSquareForMeleeAttack(target);
            checkAndPerformAttack(target, d0);
        }
    }

    protected void checkAndPerformAttack(LivingEntity target, double distToTarget) {
        double dist = getAttackReachSqr(target);
        if (distToTarget <= dist/* && isTimeToAttack()*/) {
            resetAttackCooldown();
            mob.performAttack();
        }
    }

    protected void resetAttackCooldown() {
        this.ticksUntilNextAttack = this.adjustedTickDelay(20);
    }

    protected double getAttackReachSqr(LivingEntity target) {
        return mob.getBbWidth() * 2 * mob.getBbWidth() * 2 + target.getBbWidth();
    }
}
