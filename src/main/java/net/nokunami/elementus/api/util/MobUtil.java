package net.nokunami.elementus.api.util;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.entity.PartEntity;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

public class MobUtil {

    // ------ [ HURTING ]

    public static boolean customHurt(Entity target, ResourceKey<DamageType> source, Entity attacker, Entity directEntity, double amount) {
        return customHurt(target, target.damageSources().source(source, attacker, directEntity), amount);
    }
    public static boolean customHurt(Entity target, ResourceKey<DamageType> source, Entity attacker, double amount) {
        return customHurt(target, target.damageSources().source(source, attacker), amount);
    }
    public static boolean customHurt(Entity target, ResourceKey<DamageType> source, double amount) {
        return customHurt(target, target.damageSources().source(source), amount);
    }

    public static boolean customHurt(Entity target, DamageSource source, double amount) {
        return target.hurt(source, (float) amount);
    }

    // ------ [ TARGETING ]

    public static boolean isAttackedByPlayer(Entity victim, Entity abuser) {
        return victim instanceof LivingEntity l
                && abuser instanceof Player pp
                && l.getLastAttacker() instanceof Player
                && pp.getLastHurtMob() == l;
    }

    @Nullable
    public static LivingEntity getLastHurtByEntity(Entity attacker) {
        return attacker instanceof LivingEntity living ? living.getLastHurtByMob() : null;
    }

    @Nullable
    public static LivingEntity getLastHurtEntity(Entity attacker) {
        return attacker instanceof LivingEntity living ? living.getLastHurtMob() : null;
    }

    public static boolean isEnemyNotTamed(Entity attacker, Entity target) {
        return !(target instanceof Mob mob)
                || mob.getTarget() == attacker
                || !(target instanceof OwnableEntity ownable)
                || ownable.getOwner() == null;
    }

    public static boolean isNeutral(Entity attacker, Entity target) {
        return !(target instanceof NeutralMob mob) || mob.isAngry()
                || mob.getTarget() instanceof Player
                || mob.getTarget() == attacker;
    }

    /**
     * @param entity to be checked.
     * @return true if entity is not null and is alive.
     */
    public static boolean isEntityValid(Entity entity) { return entity != null && entity.isAlive(); }

    public static boolean isPeacefulMode(@NotNull Level level) { return level.getDifficulty() == Difficulty.PEACEFUL; }

    public static boolean validHealTarget(Entity target) {
        return target instanceof Mob mob
                && !isTargetingFriendly(mob)
                && isEntityHurt(mob);
    }

    public static boolean validEffectTarget(Entity target, MobEffectCategory category) {
        return target instanceof LivingEntity living
                && living.getActiveEffects().stream().anyMatch(e -> e.getEffect().getCategory().equals(category))
                && !(living instanceof Enemy);
    }

    public static boolean isEntityHurt(Entity entity) {
        return entity instanceof LivingEntity living
                && living.getHealth() < living.getMaxHealth();
    }

    public static boolean isTargetingFriendly(Entity entity) {
        return entity instanceof Mob mob && (mob.getTarget() instanceof Player || mob.getTarget() != null && mob.getTarget().isAlliedTo(entity));
    }

    public static boolean isPlayerFriendly(Entity entity, Player player) {
        return player.getTeam() == null || player.isAlliedTo(entity);
    }

    public static boolean isValidAttackTarget(Entity me, Entity attacker) {
        return attacker instanceof Mob mob
                && mob.getTarget() != null
                && mob.getTarget() == me
                || (
                        me instanceof Mob at
                        && at.getTarget() == attacker
                    )
                || me instanceof Mob tamedMob
                && tamedMob.getTarget() == attacker;
    }

    // ------ [ INTERACTION ]

    public static boolean isSmallerThan(Entity entity, double width, double height) {
        return (entity.getBbWidth() < width && entity.getBbHeight() < height);
    }

//    public static boolean isChompable(Entity entity, double width, double height) {
//        return entity.isAttackable() &&
//                (
//                entity instanceof AbstractBrainMuncher zombie ? !zombie.isDecaying()
//                        : !entity.isRemoved()
//                )
//                && isSmallerThan(entity, width, height)
//                && !entity.getType().is(FWEntityTypeTags.UNCHOMPABLE);
//    }

//    public static boolean isChompable(Entity plant, Entity target) { return isChompable(plant, target, 1.6, 1.35); }
//    public static boolean isChompable(Entity plant, Entity target, double width, double height) {
//        return target.isAttackable() &&
//                (
//                target instanceof AbstractBrainMuncher zombie ? !zombie.isDecaying()
//                        : !target.isRemoved()
//                )
//                && isSmallerThan(target, plant.getBbWidth() * width, plant.getBbHeight() * height)
//                && !target.getType().is(FWEntityTypeTags.UNCHOMPABLE);
//    }

    public static boolean targetHasLineOfSight(Entity entity1, Entity entity2) {
        return entity2 instanceof Mob mob
                && mob.getTarget() == entity1
                && mob.hasLineOfSight(entity1);
    }

    public static boolean canHitTarget(Entity attacker, Entity target) {
        return target instanceof Enemy
                || (
                        attacker instanceof Mob mob
                        && mob.getTarget() == target
                    )
                || isValidAttackTarget(attacker, target)
                || isPartEntity(attacker, target);
    }

    public static Set<Entity> getTargetsInCone(LivingEntity entity, double range, double angleDegrees) {
        Vec3 origin = entity.getEyePosition();
        Vec3 direction = Vec3.directionFromRotation(entity.getXRot(), entity.yHeadRot);
        double cosHalfAngle = Math.cos(Math.toRadians(angleDegrees / 2.0));

        AABB bounds = new AABB(
                origin.x - range, origin.y - range, origin.z - range,
                origin.x + range, origin.y + range, origin.z + range
        );

        Set<Entity> hitSet = new HashSet<>(entity.level().getEntitiesOfClass(
                LivingEntity.class,
                bounds,
                e -> {
                    if (!MobUtil.canHitTarget(entity, e)) return false;

                    Vec3 toTarget = e.getEyePosition().subtract(origin);
                    double distance = toTarget.length();
                    if (distance > range || distance < 1.0E-4) return false;

                    Vec3 toTargetNormalized = toTarget.scale(1.0 / distance);
                    return direction.dot(toTargetNormalized) >= cosHalfAngle;
                }
        ));
        hitSet.remove(entity);
        return hitSet;
    }

    public static boolean isPartEntity(Entity attacker, Entity target) {
        return target instanceof PartEntity<?> partEntity
                && partEntity.getParent() != null
                && partEntity.getParent() instanceof Mob mob
                && isValidAttackTarget(attacker, mob);
    }

    public static Vec3 positionWithEyeOffset(Entity entity) {
        return new Vec3(entity.getX(), entity.getEyeY(), entity.getZ());
    }

    public static BlockPos blockPosFromVec3(Vec3 pos) {
        return new BlockPos((int) pos.x(), (int) pos.y(), (int) pos.z());
    }

    // ------ [ MISC ]

//    public static void tamedHealthStat(AbstractPlant plant, double tamedValue) {
//        attributeUtil(plant, Attributes.MAX_HEALTH).modify(plant.isTamed() ? Math.max(tamedValue, plant.getHealth()) : plant.getHealth());
//        if (plant.isTamed()) plant.setHealth((float) tamedValue);
//    }
//
//    public static void tamedStat(AbstractPlant plant, Attribute attribute, double tamedValue) {
//        if (plant.isTamed()) attributeUtil(plant, attribute).modify(tamedValue);
//    }

    public static boolean percentHP(LivingEntity plant, float percent) {
        return plant.getHealth() < (plant.getMaxHealth() * percent);
    }

//    public static boolean projectileDespawnAnd(Entity entity, AbstractPlantProjectile projectile, double min, double max) {
//        var spawnPos = projectile.getSpawnPos();
//        return entity instanceof Mob mob && mob.getTarget() == null && projectile.distanceTo(entity) > min
//                || spawnPos != null && Math.sqrt(projectile.distanceToSqr(spawnPos.x, spawnPos.y, spawnPos.z)) > max;
//    }

    public static void levelPlaySound(Level level, BlockPos pos, Supplier<SoundEvent> sound, SoundSource source, float vol, float pitch) {
        levelPlaySound(level, pos.getX(), pos.getY(), pos.getZ(), sound.get(), source, vol, pitch);
    }
    public static void levelPlaySound(Level level, BlockPos pos, SoundEvent sound, SoundSource source, float vol, float pitch) {
        levelPlaySound(level, pos.getX(), pos.getY(), pos.getZ(), sound, source, vol, pitch);
    }

    public static void levelPlaySound(Level level, double pX, double pY, double pZ, Supplier<SoundEvent> sound, SoundSource source, float vol, float pitch) {
        levelPlaySound(level, pX, pY, pZ, sound.get(), source, vol, pitch);
    }
    public static void levelPlaySound(Level level, double pX, double pY, double pZ, SoundEvent sound, SoundSource source, float vol, float pitch) {
        level.playSound(null, pX, pY, pZ, sound, source, vol, pitch);
    }

    public static boolean playerInstaBuild(Entity entity) {
        if (entity instanceof Player player)
            return player.getAbilities().instabuild;
        return false;
    }

    public static AttributeSupplier.Builder defaultPlantAttributes() {
        return AttributeSupplier.builder()
                .add(Attributes.MAX_HEALTH)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1)
                .add(Attributes.MOVEMENT_SPEED)
                .add(Attributes.ARMOR)
                .add(Attributes.ARMOR_TOUGHNESS)
                .add(ForgeMod.SWIM_SPEED.get())
                .add(ForgeMod.NAMETAG_DISTANCE.get())
                .add(ForgeMod.ENTITY_GRAVITY.get())
                .add(ForgeMod.STEP_HEIGHT_ADDITION.get())

                .add(Attributes.FOLLOW_RANGE, 16.0D)
                .add(Attributes.ATTACK_DAMAGE, 2)
                .add(Attributes.ATTACK_KNOCKBACK, 0)
                .add(Attributes.ATTACK_SPEED, 1)
                ;
    }
}