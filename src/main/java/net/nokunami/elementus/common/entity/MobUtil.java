package net.nokunami.elementus.common.entity;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.phys.Vec3;
import net.nokunami.elementus.common.item.unique.TestCatalystArmorItem;

import java.util.function.Supplier;

public class MobUtil {

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

    public static boolean allied(Entity ally, Entity entity, boolean friendlyFire) {
        return !(entity instanceof OwnableEntity) && (entity.isAlliedTo(ally) && friendlyFire || !entity.isAlliedTo(ally)) ||
                (entity instanceof OwnableEntity oE && ((oE.getOwner() != null && (oE.getOwner().is(ally) || oE.getOwner().isAlliedTo(ally)) && friendlyFire) ||
                        oE.getOwner() == null));
    }

    public static boolean alliedTamedMob(Entity team, Entity ally) {
        return ally instanceof OwnableEntity own && (own.getOwner() != null || own.getOwner() == team) && (ally.isAlliedTo(team));
    }

    public static boolean tamedMob(LivingEntity owner, Mob tamed) {
        return tamed instanceof OwnableEntity ownable && ownable.getOwner() != null && ownable.getOwner() != owner;
    }

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

    public static void applyEffect(Entity entity, MobEffect mobEffect) {
        applyEffect(entity, mobEffect, 0, 0, true, true, true);
    }

    public static void applyEffect(Entity entity, Supplier<MobEffect> mobEffect) {
        applyEffect(entity, mobEffect.get(), 0, 0, true, true, true);
    }

    public static void applyEffect(Entity entity, MobEffect mobEffect, boolean... booleans) {
        applyEffect(entity, mobEffect, 0, 0, booleans[0], booleans[1], booleans[2]);
    }

    public static void applyEffect(Entity entity, Supplier<MobEffect> mobEffect, boolean... booleans) {
        applyEffect(entity, mobEffect.get(), 0, 0, booleans[0], booleans[1], booleans[2]);
    }

    public static void applyEffect(Entity entity, MobEffect mobEffect, int... num) {
        applyEffect(entity, mobEffect, num[0], num[1], true, true, true);
    }

    public static void applyEffect(Entity entity, Supplier<MobEffect> mobEffect, int... num) {
        applyEffect(entity, mobEffect.get(), num[0], num[1], true, true, true);
    }

    public static void applyEffect(Entity entity, Supplier<MobEffect> mobEffect, int duration, int amp, boolean ambient, boolean visible, boolean showIcon) {
        applyEffect(entity, mobEffect.get(), duration, amp, ambient, visible, showIcon);
    }

    public static void applyEffect(Entity entity, MobEffect mobEffect, int duration, int amp, boolean ambient, boolean visible, boolean showIcon) {
        if (entity instanceof LivingEntity living) {
            living.addEffect(new MobEffectInstance(mobEffect, duration, amp, ambient, visible, showIcon));
        }
    }

    /// ArcheryExpansion code: BowItemMixin
    public static void applyRecoil(Entity target,Entity source, double aX, double aY, double aZ, boolean type) {
        Vec3 lookDirection = source.getViewVector(1.0f);
        double fX = -aX;
        double fY = -aY;
        double fZ = -aZ;
        if (type) {
            fX = aX;
            fY = aY;
            fZ = aZ;
        }
        Vec3 knockback = lookDirection.multiply(fX, fY, fZ);

        target.setDeltaMovement(
                source.getDeltaMovement().x + knockback.x,
                source.getDeltaMovement().y + knockback.y,
                source.getDeltaMovement().z + knockback.z
        );
        target.hurtMarked = true;
    }

    public static void applyRecoil(Entity target, Entity source, double amount, boolean type) {
        applyRecoil(target, source, amount, amount, amount, type);
    }
}
