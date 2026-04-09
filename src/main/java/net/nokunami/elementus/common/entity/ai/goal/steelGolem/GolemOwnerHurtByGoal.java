package net.nokunami.elementus.common.entity.ai.goal.steelGolem;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.nokunami.elementus.common.entity.living.TamableGolem;

import java.util.EnumSet;

public class GolemOwnerHurtByGoal extends TargetGoal {
    private final TamableGolem golem;
    private LivingEntity ownerLastHurtBy;
    private int timestamp;

    public GolemOwnerHurtByGoal(TamableGolem golem) {
        super(golem, false);
        this.golem = golem;
        this.setFlags(EnumSet.of(Flag.TARGET));
    }

    @Override
    public boolean canUse() {
        if (this.golem.isTamed() && !this.golem.isOrderedToSit() && !this.golem.isChassisBroken()) {
            LivingEntity livingentity = this.golem.getOwner();
            if (livingentity == null) {
                return false;
            } else {
                this.ownerLastHurtBy = livingentity.getLastHurtByMob();
                int i = livingentity.getLastHurtByMobTimestamp();
                return i != this.timestamp && this.canAttack(this.ownerLastHurtBy, TargetingConditions.DEFAULT) && this.golem.wantsToAttack(this.ownerLastHurtBy, livingentity);
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
            this.mob.setTarget(this.ownerLastHurtBy);
            LivingEntity livingentity = this.golem.getOwner();
            if (livingentity != null) {
                this.timestamp = livingentity.getLastHurtByMobTimestamp();
            }
        }

        super.start();
    }
}
