package net.nokunami.elementus.common.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.nokunami.elementus.common.item.unique.TestCatalystArmorItem;

import java.util.function.Supplier;

public class MobUtil {

    // ----- [ HURTING ]

    public static void hurt(Entity target, ResourceKey<DamageType> source, float amount) {
        hurt(target, target.damageSources().source(source), amount, 20);
    }

    public static void hurt(Entity target, DamageSource source, float amount) {
        hurt(target, source, amount, 20);
    }

    public static void hurt(Entity target, ResourceKey<DamageType> source, Entity attacker, float amount, int invulnerableTick) {
        hurt(target, attacker.damageSources().source(source, attacker), amount, invulnerableTick);
    }

    public static void hurt(Entity target, DamageSource source, float amount, int invulnerableTick) {
        target.invulnerableTime = invulnerableTick;
        target.hurt(source, amount);
        target.invulnerableTime = invulnerableTick;
    }

    // ----- [ BOOL ]

    public static boolean alliedAttacked(Entity ally, Entity enemy) {
        if (enemy instanceof Mob mob) {
            return mob.getTarget() != null && (mob.getTarget().is(ally) || mob.getTarget().isAlliedTo(ally));
        } else return false;
    }

    public static boolean tamedMob(Entity tamed) {
        return tamed instanceof OwnableEntity ownable && ownable.getOwner() != null;
    }

    public static boolean alliedMob(LivingEntity alliedTo, LivingEntity entity) {
        return alliedTo.isAlliedTo(entity);
    }

    public static boolean chargeBade(Entity ally, Entity entity, boolean friendlyFire) {
        return allied(ally, entity, friendlyFire);
    }

    public static boolean allied(Entity ally, Entity entity, boolean friendlyFire) {
        return !(entity instanceof OwnableEntity) && (entity.isAlliedTo(ally) && friendlyFire || !entity.isAlliedTo(ally)) ||
                (entity instanceof OwnableEntity oE && ((oE.getOwner() != null && (oE.getOwner().is(ally) || oE.getOwner().isAlliedTo(ally)) && friendlyFire) ||
                        oE.getOwner() == null));
    }

    public static boolean can(Entity ally, Entity target) {
//        return !(target instanceof OwnableEntity) && (target.isAlliedTo(ally) && friendlyFire || !target.isAlliedTo(ally)) ||
//                (target instanceof OwnableEntity oE && ((oE.getOwner() != null && (oE.getOwner().is(ally) || oE.getOwner().isAlliedTo(ally)) && friendlyFire) ||
//                        oE.getOwner() == null));
        return !(target.isAlliedTo(ally) || target instanceof Mob m && (m.getTarget() == ally));
    }

    public static boolean alliedTamedMob(Entity team, Entity ally) {
        return ally instanceof OwnableEntity own && (own.getOwner() != null || own.getOwner() == team) && (ally.isAlliedTo(team));
    }

    public static boolean tamedMob(LivingEntity owner, Mob tamed) {
        return tamed instanceof OwnableEntity ownable && ownable.getOwner() != null && ownable.getOwner() != owner;
    }

    public static boolean isGardenerOrOwnerAttacked(Entity entity, Entity target) {
        if (entity instanceof OwnableEntity ownable && target instanceof Mob mob) {
            LivingEntity mobTarget = mob.getTarget();
            LivingEntity owner = ownable.getOwner();
            return mobTarget != null && !isAttackedByPlayer(entity, mobTarget)
                    && (owner != null && target.is(owner)
            );
        }
        return false;
    }

    public static boolean isAttackedByPlayer(Entity victim, Entity abuser) {
        return victim instanceof LivingEntity l
                && abuser instanceof Player pp
                && l.getLastAttacker() instanceof Player
                && pp.getLastHurtMob() == l;
    }

    public static boolean isSeenAsEnemy(Entity attacker, Entity target) {
        return target instanceof Mob mob
                && mob instanceof Enemy && isNeutral(attacker, target)
                && (
                isEnemyNotTamed(attacker, target)
                        || (isTargetingFriendly(mob) || isGardenerOrOwnerAttacked(attacker, mob))
        );
    }

    public static boolean isEnemyNotTamed(Entity attacker, Entity target) {
        return !(target instanceof Mob mob)
                || mob.getTarget() == attacker
                || !(target instanceof OwnableEntity ownable)
                || ownable.getOwner() == null;
    }

    public static boolean isNeutral(Entity attacker, Entity target) {
        return !(target instanceof NeutralMob mob) || mob.isAngry()
//                || mob.getTarget() instanceof AbstractPlant
                || mob.getTarget() instanceof Player
                || mob.getTarget() == attacker;
    }

    public static boolean isTargetingFriendly(Entity entity) {
        return entity instanceof Mob mob
                && (mob.getTarget() instanceof Player
//                || mob.getTarget() instanceof AbstractPlant
        );
    }

    // ----- [ UTILS ]

    public static boolean healthPercent(Entity entity, float amount) {
        return entity instanceof LivingEntity living && living.getHealth() < living.getMaxHealth() * amount;
    }

    public static boolean itemCooldown(Entity entity) {
        return entity instanceof Player living &&
                living.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof TestCatalystArmorItem &&
                living.getCooldowns().isOnCooldown(living.getItemBySlot(EquipmentSlot.CHEST).getItem());
    }

    public static void applyItemCooldown(Entity entity, int cooldown) {
        if (entity instanceof Player living) {
            Item chestplate = living.getItemBySlot(EquipmentSlot.CHEST).getItem();
            if (chestplate instanceof TestCatalystArmorItem)
                living.getCooldowns().addCooldown(chestplate, cooldown);
        }
    }

    public static boolean hasEffect(Entity entity, MobEffect effect) {
        return entity instanceof LivingEntity living && living.hasEffect(effect);
    }

    public static boolean blockVec(Vec3 vec3, Vec3 pos, Vec3 view) {
        if (vec3 != null) {
            Vec3 vec31 = vec3.vectorTo(pos).normalize();
            vec31 = new Vec3(vec31.x, 0, vec31.z);
            return vec31.dot(view) < 0;
        }
        return false;
    }

    public static void playEntitySound(Entity entity, Supplier<SoundEvent> sound) {
        playEntitySound(entity, sound.get(), 1, 1);
    }

    public static void playEntitySound(Entity entity, SoundEvent sound) {
        playEntitySound(entity, sound, 1, 1);
    }

    public static void playEntitySound(Entity entity, Supplier<SoundEvent> sound, float volume, float pitch) {
        playEntitySound(entity, sound.get(), volume, pitch);
    }

    public static void playEntitySound(Entity entity, SoundEvent sound, float volume, float pitch) {
        playEntitySound(entity, sound, entity.getSoundSource(), volume, pitch);
    }

    public static void playEntitySound(Entity entity, Supplier<SoundEvent> sound, SoundSource source, float volume, float pitch) {
        playEntitySound(entity, sound.get(), source, volume, pitch);
    }

    public static void playEntitySound(Entity entity, SoundEvent sound, SoundSource source, float volume, float pitch) {
        entity.level().playSound(null, entity.getX(), entity.getY(), entity.getZ(), sound, source, volume, pitch);
    }

    public static class EffectApplier {
        Entity entity;
        MobEffect mobEffect;
        int duration = 0, amplifier = 0;
        boolean ambient = true, visible = true, showIcon = true;

        public static EffectApplier EAInst(Entity entity, MobEffect mobEffect) { return new EffectApplier(entity, mobEffect); }
        public static EffectApplier EAInst(Entity entity, Supplier<MobEffect> mobEffect) { return new EffectApplier(entity, mobEffect.get()); }
        public EffectApplier(Entity entity, MobEffect mobEffect) {
            this.entity = entity;
            this.mobEffect = mobEffect;
        }

        public EffectApplier stats(int dur) { duration = dur; return this; }
        public EffectApplier stats(int dur, int amp) { duration = dur; amplifier = amp; return this; }

        public EffectApplier noAmbient() { ambient = false; return this; }
        public EffectApplier hasAmbient() { ambient = true; return this; }
        public EffectApplier notVisible() { visible = false; return this; }
        public EffectApplier visible() { visible = true; return this; }
        public EffectApplier hideIcon() { showIcon = false; return this; }
        public EffectApplier showIcon() { showIcon = true; return this; }

        public EffectApplier allFalse() { return noAmbient().notVisible().hideIcon(); }
        public EffectApplier allTrue() { return hasAmbient().visible().showIcon(); }
        public EffectApplier showIconOnly() { return allFalse().showIcon(); }

        public void applyRefresh() { applyIf(!MobUtil.hasEffect(entity, mobEffect)); }
        public void apply() { applyIf(true); }
        public void applyIf(boolean condition) {
            if (entity instanceof LivingEntity living && condition) {
                living.addEffect(new MobEffectInstance(mobEffect, duration, amplifier, ambient, visible, showIcon));
            }
        }
    }

    public static void applyRecoil(Entity target, Entity source, double amount, boolean type) {
        applyRecoil(target, source, amount, amount, amount, type);
    }

    /// ArcheryExpansion code: BowItemMixin
    public static void applyRecoil(Entity target,Entity source, double aX, double aY, double aZ, boolean type) {
        Vec3 lookDirection = source.getViewVector(1.0f);
        double fX = type ? aX : -aX;
        double fY = type ? aY : -aY;
        double fZ = type ? aZ : -aZ;
        Vec3 knockback = lookDirection.multiply(fX, fY, fZ);

        target.setDeltaMovement(
                source.getDeltaMovement().x + knockback.x,
                source.getDeltaMovement().y + knockback.y,
                source.getDeltaMovement().z + knockback.z
        );
        target.hurtMarked = true;
    }

    public static Vec3 calculateViewVector(float xRot, float yRot) {
        float f = xRot * ((float)Math.PI / 180F);
        float f1 = -yRot * ((float)Math.PI / 180F);
        float f2 = Mth.cos(f1);
        float f3 = Mth.sin(f1);
        float f4 = Mth.cos(f);
        float f5 = Mth.sin(f);
        return new Vec3(f3 * f4, -f5, f2 * f4);
    }

    public static BlockHitResult POVHitResult(Level level, LivingEntity entity, double range, ClipContext.Fluid fluid) {
        float f = entity.getXRot();
        float f1 = entity.getYRot();
        Vec3 vec3 = entity.getEyePosition();
        float f2 = Mth.cos(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
        float f3 = Mth.sin(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
        float f4 = -Mth.cos(-f * ((float)Math.PI / 180F));
        float f5 = Mth.sin(-f * ((float)Math.PI / 180F));
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        Vec3 vec31 = vec3.add((double) f6 * range, (double) f5 * range, (double) f7 * range);
        return level.clip(new ClipContext(vec3, vec31, ClipContext.Block.OUTLINE, fluid, entity));
    }

    public static void teleportEntity(Entity entity, BlockPos vec3) { entity.teleportTo(vec3.getX(), vec3.getY(), vec3.getZ()); }
    public static void teleportEntity(Entity entity, Vec3 vec3) { entity.teleportTo(vec3.x, vec3.y, vec3.z); }
    public static void teleportEntity(Entity entity, double x, double y, double z) { entity.teleportTo(x, y, z); }

    public static <T extends Mob & OwnableEntity> void sendOwnerMsgs(T entity, Component component) {
        if (entity.getOwner() != null) {
            entity.getOwner().sendSystemMessage(component);
        }
    }
}
