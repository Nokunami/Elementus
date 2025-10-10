package net.nokunami.elementus.common.entity.ai.goal.steelGolem;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.nokunami.elementus.common.entity.living.TamableGolem;

import java.util.EnumSet;

public class GolemOwnerHurtGoal extends TargetGoal {
    private final TamableGolem golem;
    private LivingEntity ownerLastHurt;
    private int timestamp;

    public GolemOwnerHurtGoal(TamableGolem golem) {
        super(golem, false);
        this.golem = golem;
        this.setFlags(EnumSet.of(Goal.Flag.TARGET));
    }

    @Override
    public boolean canUse() {
        if (this.golem.isTame() && !this.golem.isOrderedToSit() && !this.golem.isChassisBroken()) {
            LivingEntity livingentity = this.golem.getOwner();
            if (livingentity == null) {
                return false;
            } else {
                this.ownerLastHurt = livingentity.getLastHurtMob();
                int i = livingentity.getLastHurtMobTimestamp();
                return i != this.timestamp && this.canAttack(this.ownerLastHurt, TargetingConditions.DEFAULT) && this.golem.wantsToAttack(this.ownerLastHurt, livingentity);
            }
        } else {
            return false;
        }
    }

    @Override
    public void tick() {
        if (!this.golem.isChassisBroken())
            super.tick();
    }

    @Override
    public void start() {
        if (!this.golem.isChassisBroken()) {
            this.mob.setTarget(this.ownerLastHurt);
            LivingEntity livingentity = this.golem.getOwner();
            if (livingentity != null) {
                this.timestamp = livingentity.getLastHurtMobTimestamp();
            }

            super.start();
        }
    }
}
