package net.nokunami.elementus.common.entity.living.combat;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.nokunami.elementus.common.entity.KnockbackUtil;
import net.nokunami.elementus.common.entity.MobUtil;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public interface IAdaptiveAttacker {

    default void addAdaptiveAttackerSaveData(CompoundTag tag) {
    }

    default void readAdaptiveAttackerSaveData(CompoundTag tag) {
    }

    Entity getPriorityTarget();
    void setPriorityTarget(Entity target);

    Entity getAnnoyingTarget();
    void setAnnoyingTarget(Entity target);

    Entity getRangedTarget();
    void setRangedTarget(Entity target);

    Entity getCurrentTarget();
    void setCurrentTarget(Entity target);

    void attackRecord();

    void hurtRecord(Entity target);

    void movement();

    void postAttack();

    void performAttack();

    Properties info();

    default Set<Entity> aoeSet(Mob attacker, float vXOffset, float vYOffset, double scale, Predicate<Entity> predicate) {
        Vec3 target = attacker.getEyePosition().add(Vec3.directionFromRotation(vXOffset, vYOffset).scale(scale));
        Vec3 source = attacker.getEyePosition();
        Vec3 offsetToTarget = target.subtract(source);
        Vec3 normalized = offsetToTarget.normalize();
        Set<Entity> hitSet = new HashSet<>();

        for(int i = 1; i < Mth.floor(offsetToTarget.length()) + 2; ++i) {
            Vec3 pos = source.add(normalized.scale(i));
            KnockbackUtil knockbackUtil = KnockbackUtil.knockbackUtil(attacker.level(), attacker, pos.x, pos.y, pos.z, 1);

            hitSet.addAll(attacker.level().getEntitiesOfClass(LivingEntity.class, new AABB(new BlockPos((int) pos.x, (int) pos.y, (int) pos.z)).inflate(2),
//                    e -> MobUtil.can(attacker, e)
//                    e -> e != attacker
                    predicate
            ));
            knockbackUtil.knockback();
        }
        hitSet.remove(attacker);
        return hitSet;
    }

    default double getAttackReachSqr(LivingEntity pAttackTarget) {
        Mob mob = info().attacker;
        return mob.getBbWidth() * 2 * mob.getBbWidth() * 2 + pAttackTarget.getBbWidth();
    }

    class Properties {
        Mob attacker;
        AttackStyle defaultStyle;
        int attackSpeed;
        int attackDelay;
        boolean canPenalize;
        Path path;

        public Properties(Mob entity) { attacker = entity; }

        public Mob getMob() { return attacker; }
        public AttackStyle getStyle() { return defaultStyle; }
        public int getAttackSpeed() { return attackSpeed; }
        public int getAttackDelay() { return attackDelay; }
        public boolean canPenalize() { return canPenalize; }
        public Path getPath() { return path; }

        public Properties defaultAttackStyle(AttackStyle style) {
            defaultStyle = style;
            return this;
        }

        public Properties attackSpeed(int speed) {
            return attackSpeed(speed, speed);
        }

        public Properties attackSpeed(int speed, int delay) {
            attackSpeed = speed;
            attackDelay = delay;
            return this;
        }

        public Properties attackSpeed(boolean b) {
            canPenalize = b;
            return this;
        }
    }
}
