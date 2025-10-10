package net.nokunami.elementus.common.entity;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
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
        entity.level().playSound(null, entity.getX(), entity.getY(), entity.getZ(), sound, entity.getSoundSource(), volume, pitch);
    }
}
