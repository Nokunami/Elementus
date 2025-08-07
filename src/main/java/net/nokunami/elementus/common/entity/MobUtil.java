package net.nokunami.elementus.common.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.OwnableEntity;

public class MobUtil {

    public static boolean alliedAttacked(Entity ally, Entity enemy) {
        if (enemy instanceof Mob mob) {
            return mob.getTarget() != null && (mob.getTarget().is(ally) || mob.getTarget().isAlliedTo(ally));
        } else return false;
    }

//    public static boolean alliedMob(Entity ally, Entity otherEntity) {
//        if (ally instanceof OwnableEntity allyOwnable) {
//            if ((otherEntity instanceof OwnableEntity oE && (oE.getOwner() != null/* && allyOwnable.getOwner() != null*/) && oE instanceof Enemy)) {
////                return oE.getOwner().is((allyOwnable.getOwner()));
//                return /*oE.getOwner().is((allyOwnable.getOwner())) || */oE.getOwner() != null;
//            } else if (otherEntity instanceof OwnableEntity oE && (oE.getOwner() != null/* && allyOwnable.getOwner() != null*/)) {
//                return /*oE.getOwner().is((allyOwnable.getOwner())) || */oE.getOwner() != null;
//            }
//        }
//        return otherEntity.isAlliedTo(ally);
//    }

    public static boolean tamedMob(Entity tamed) {
        return tamed instanceof OwnableEntity ownable && ownable.getOwner() != null;
    }

    public static boolean alliedMob(LivingEntity alliedTo, LivingEntity entity) {
        return alliedTo.isAlliedTo(entity);
    }

    public static boolean allied(Entity ally, Entity entity, boolean friendlyFire) {
        if (!(entity instanceof OwnableEntity) && (entity.isAlliedTo(ally) && friendlyFire || !entity.isAlliedTo(ally)) ||
                (entity instanceof OwnableEntity oE && ((oE.getOwner() != null && (oE.getOwner().is(ally) || oE.getOwner().isAlliedTo(ally)) && friendlyFire) ||
                        oE.getOwner() == null))) return true;
        return false;
//                    (e) -> !(e instanceof OwnableEntity) && (e.isAlliedTo(livingEntity) && getFriendlyFire(stack) || !e.isAlliedTo(livingEntity)) ||
//                            (e instanceof OwnableEntity ownable && ((ownable.getOwner() != null &&
//                                    (ownable.getOwner().is(livingEntity) || ownable.getOwner().isAlliedTo(livingEntity)) && getFriendlyFire(stack)) ||
//                                    ownable.getOwner() == null))

//        if (!friendlyFire && entity instanceof OwnableEntity oE && oE.getOwner() != null) return true;
//        if (!friendlyFire && entity.isAlliedTo(ally)) return true;
//        return false;
    }

    public static boolean alliedTamedMob(LivingEntity team, LivingEntity ally) {
        return ally instanceof OwnableEntity own && (own.getOwner() != null || own.getOwner() == team) && (ally.isAlliedTo(team));
    }

    public static boolean tamedMob(LivingEntity owner, Mob tamed) {
        return tamed instanceof OwnableEntity ownable && ownable.getOwner() != null && ownable.getOwner() != owner;
    }
}
