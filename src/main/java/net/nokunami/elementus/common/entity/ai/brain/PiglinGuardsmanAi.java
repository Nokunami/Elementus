package net.nokunami.elementus.common.entity.ai.brain;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.GlobalPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.*;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.monster.piglin.AbstractPiglin;
import net.minecraft.world.entity.monster.piglin.PiglinAi;;
import net.minecraft.world.entity.schedule.Activity;
import net.minecraft.world.level.GameRules;
import net.nokunami.elementus.common.entity.living.PiglinGuardsman;

import java.util.Optional;

public class PiglinGuardsmanAi {
    private static final int ANGER_DURATION = 600;
    private static final int MELEE_ATTACK_COOLDOWN = 20;
    private static final double ACTIVITY_SOUND_LIKELIHOOD_PER_TICK = 0.0125D;
    private static final int MAX_LOOK_DIST = 8;
    private static final int INTERACTION_RANGE = 8;
    private static final double TARGETING_RANGE = 12.0D;
    private static final float SPEED_MULTIPLIER_WHEN_IDLING = 0.6F;
    private static final int HOME_CLOSE_ENOUGH_DISTANCE = 2;
    private static final int HOME_TOO_FAR_DISTANCE = 100;
    private static final int HOME_STROLL_AROUND_DISTANCE = 5;

    public static Brain<?> makeBrain(PiglinGuardsman guardsman, Brain<PiglinGuardsman> pBrain) {
        initCoreActivity(guardsman, pBrain);
        initIdleActivity(guardsman, pBrain);
        initFightActivity(guardsman, pBrain);
        pBrain.setCoreActivities(ImmutableSet.of(Activity.CORE));
        pBrain.setDefaultActivity(Activity.IDLE);
        pBrain.useDefaultActivity();
        return pBrain;
    }

    public static void initMemories(PiglinGuardsman guardsman) {
        GlobalPos globalpos = GlobalPos.of(guardsman.level().dimension(), guardsman.blockPosition());
        guardsman.getBrain().setMemory(MemoryModuleType.HOME, globalpos);
    }

    private static void initCoreActivity(PiglinGuardsman guardsman, Brain<PiglinGuardsman> pBrain) {
        pBrain.addActivity(Activity.CORE, 0, ImmutableList.of(new LookAtTargetSink(45, 90), new MoveToTargetSink(), InteractWithDoor.create(), StopBeingAngryIfTargetDead.create()));
    }

    private static void initIdleActivity(PiglinGuardsman guardsman, Brain<PiglinGuardsman> pBrain) {
        pBrain.addActivity(Activity.IDLE, 10, ImmutableList.of(StartAttacking.<PiglinGuardsman>create(PiglinGuardsmanAi::findNearestValidAttackTarget), createIdleLookBehaviors(), createIdleMovementBehaviors(), SetLookAndInteract.create(EntityType.PLAYER, 4)));
    }

    private static void initFightActivity(PiglinGuardsman guardsman, Brain<PiglinGuardsman> pBrain) {
        pBrain.addActivityAndRemoveMemoryWhenStopped(Activity.FIGHT, 10, ImmutableList.of(StopAttackingIfTargetInvalid.<Mob>create((p_35118_) -> {
            return !isNearestValidAttackTarget(guardsman, p_35118_);
        }), SetWalkTargetFromAttackTargetIfTargetOutOfReach.create(1.0F), MeleeAttack.create(20)), MemoryModuleType.ATTACK_TARGET);
    }

    private static RunOne<PiglinGuardsman> createIdleLookBehaviors() {
        return new RunOne<>(ImmutableList.of(Pair.of(SetEntityLookTarget.create(EntityType.PLAYER, 8.0F), 1), Pair.of(SetEntityLookTarget.create(EntityType.PIGLIN, 8.0F), 1), Pair.of(SetEntityLookTarget.create(EntityType.PIGLIN_BRUTE, 8.0F), 1), Pair.of(SetEntityLookTarget.create(8.0F), 1), Pair.of(new DoNothing(30, 60), 1)));
    }

    private static RunOne<PiglinGuardsman> createIdleMovementBehaviors() {
        return new RunOne<>(ImmutableList.of(Pair.of(RandomStroll.stroll(0.6F), 2), Pair.of(InteractWith.of(EntityType.PIGLIN, 8, MemoryModuleType.INTERACTION_TARGET, 0.6F, 2), 2), Pair.of(InteractWith.of(EntityType.PIGLIN_BRUTE, 8, MemoryModuleType.INTERACTION_TARGET, 0.6F, 2), 2), Pair.of(StrollToPoi.create(MemoryModuleType.HOME, 0.6F, 2, 100), 2), Pair.of(StrollAroundPoi.create(MemoryModuleType.HOME, 0.6F, 5), 2), Pair.of(new DoNothing(30, 60), 1)));
    }

    protected static void updateActivity(PiglinGuardsman guardsman) {
        Brain<PiglinGuardsman> brain = guardsman.getBrain();
        Activity activity = brain.getActiveNonCoreActivity().orElse(null);
        brain.setActiveActivityToFirstValid(ImmutableList.of(Activity.FIGHT, Activity.IDLE));
        Activity activity1 = brain.getActiveNonCoreActivity().orElse(null);
        if (activity != activity1) {
            playActivitySound(guardsman);
        }

        guardsman.setAggressive(brain.hasMemoryValue(MemoryModuleType.ATTACK_TARGET));
    }

    private static boolean isNearestValidAttackTarget(AbstractPiglin guardsman, LivingEntity pTarget) {
        return findNearestValidAttackTarget(guardsman).filter((p_35085_) -> p_35085_ == pTarget).isPresent();
    }

    private static Optional<? extends LivingEntity> findNearestValidAttackTarget(AbstractPiglin p_35087_) {
        Optional<LivingEntity> optional = BehaviorUtils.getLivingEntityFromUUIDMemory(p_35087_, MemoryModuleType.ANGRY_AT);
        if (optional.isPresent() && Sensor.isEntityAttackableIgnoringLineOfSight(p_35087_, optional.get())) {
            return optional;
        } else {
            Optional<? extends LivingEntity> optional1 = getTargetIfWithinRange(p_35087_, MemoryModuleType.NEAREST_VISIBLE_ATTACKABLE_PLAYER);
            return optional1.isPresent() ? optional1 : p_35087_.getBrain().getMemory(MemoryModuleType.NEAREST_VISIBLE_NEMESIS);
        }
    }

    private static Optional<? extends LivingEntity> getTargetIfWithinRange(AbstractPiglin guardsman, MemoryModuleType<? extends LivingEntity> pMemoryType) {
        return guardsman.getBrain().getMemory(pMemoryType).filter((p_35108_) -> p_35108_.closerThan(guardsman, 12.0D));
    }

    public static void wasHurtBy(PiglinGuardsman guardsman, LivingEntity pTarget) {
        if (!(pTarget instanceof AbstractPiglin)) {
//            PiglinAi.maybeRetaliate(guardsman, pTarget);
        }
    }

    protected static void setAngerTarget(PiglinGuardsman guardsman, LivingEntity pAngerTarget) {
        guardsman.getBrain().eraseMemory(MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE);
        guardsman.getBrain().setMemoryWithExpiry(MemoryModuleType.ANGRY_AT, pAngerTarget.getUUID(), 600L);
    }

    protected static void maybePlayActivitySound(PiglinGuardsman guardsman) {
        if ((double)guardsman.level().random.nextFloat() < 0.0125D) {
            playActivitySound(guardsman);
        }

    }

    private static void playActivitySound(PiglinGuardsman guardsman) {
        guardsman.getBrain().getActiveNonCoreActivity().ifPresent((p_35104_) -> {
            if (p_35104_ == Activity.FIGHT) {
                guardsman.playAngrySound();
            }
        });
    }
}
