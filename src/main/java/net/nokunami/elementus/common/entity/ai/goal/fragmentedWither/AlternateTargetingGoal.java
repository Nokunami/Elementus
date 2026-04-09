package net.nokunami.elementus.common.entity.ai.goal.fragmentedWither;

import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import net.nokunami.elementus.common.entity.boss.FragmentedWither;

import java.util.List;

public class AlternateTargetingGoal extends Goal {
    FragmentedWither wither;

    public AlternateTargetingGoal(FragmentedWither fragmentedWither) {
        wither = fragmentedWither;
    }

    @Override public boolean canUse() { return wither.isAlive() && wither.level().getDifficulty() != Difficulty.PEACEFUL; }

    @Override public boolean requiresUpdateEveryTick() { return true; }

    @Override
    public void tick() {
        int tickCount = wither.tickCount;
        RandomSource random = wither.getRandom();
        Level level = wither.level();

        for(int i = 1; i < 3; ++i) {
//            if (tickCount >= wither.nextHeadUpdate[i - 1]) {
            if (tickCount >= wither.getNextHeadUpdate(i - 1)) {
//                wither.nextHeadUpdate[i - 1] = tickCount + 10 + random.nextInt(10);
                wither.setNextHeadUpdate(i - 1, tickCount + 10 + random.nextInt(10));
                if (level.getDifficulty() == Difficulty.NORMAL || level.getDifficulty() == Difficulty.HARD) {
                    int i3 = i - 1;
//                    int j3 = wither.idleHeadUpdates[i - 1];
                    int j3 = wither.getIdleHeadUpdates(i - 1);
//                    wither.idleHeadUpdates[i3] = wither.idleHeadUpdates[i - 1] + 1;
                    wither.setIdleHeadUpdates(i3, wither.getIdleHeadUpdates(i - 1) + 1);
                    if (j3 > 15) {
                        float f = 10;
                        float f1 = 5;
                        double d0 = Mth.nextDouble(random, wither.getX() - f, wither.getX() + f);
                        double d1 = Mth.nextDouble(random, wither.getY() - f1, wither.getY() + f1);
                        double d2 = Mth.nextDouble(random, wither.getZ() - f, wither.getZ() + f);
                        wither.performRangedAttack(i + 1, d0, d1, d2, true);
//                        wither.idleHeadUpdates[i - 1] = 0;
                        wither.setIdleHeadUpdates(i - 1, 0);
                    }
                }

                int l1 = wither.getAlternativeTarget(i);
                if (l1 > 0) {
                    LivingEntity livingentity = (LivingEntity) level.getEntity(l1);
                    if (livingentity != null && wither.canAttack(livingentity) && !(wither.distanceToSqr(livingentity) > 900) && wither.hasLineOfSight(livingentity)) {
                        wither.performRangedAttack(i + 1, livingentity);
//                        wither.nextHeadUpdate[i - 1] = tickCount + 40 + random.nextInt(20);
                        wither.setNextHeadUpdate(i - 1, tickCount + 40 + random.nextInt(20));
//                        wither.idleHeadUpdates[i - 1] = 0;
                        wither.setIdleHeadUpdates(i - 1, 0);
                    } else {
                        wither.setAlternativeTarget(i, 0);
                    }
                } else {
                    List<LivingEntity> list = level.getNearbyEntities(LivingEntity.class, FragmentedWither.TARGETING_CONDITIONS, wither, wither.getBoundingBox().inflate(20, 8, 20));
                    if (!list.isEmpty()) {
                        LivingEntity livingentity1 = list.get(random.nextInt(list.size()));
                        wither.setAlternativeTarget(i, livingentity1.getId());
                    }
                }
            }
        }

        if (wither.getTarget() != null) {
            wither.setAlternativeTarget(0, wither.getTarget().getId());
        } else {
            wither.setAlternativeTarget(0, 0);
        }
    }
}
