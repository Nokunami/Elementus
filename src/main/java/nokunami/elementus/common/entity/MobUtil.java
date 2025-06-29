package nokunami.elementus.common.entity;

import net.minecraft.world.entity.*;

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

    public static boolean alliedMob(LivingEntity team, LivingEntity ally) {
        return ally.isAlliedTo(team);
    }

    public static boolean alliedTamedMob(LivingEntity team, LivingEntity ally) {
        return ally instanceof OwnableEntity own && (own.getOwner() != null || own.getOwner() == team) && (ally.isAlliedTo(team));
    }

    public static boolean tamedMob(LivingEntity owner, Mob tamed) {
        return tamed instanceof OwnableEntity ownable && ownable.getOwner() != null && ownable.getOwner() != owner;
    }
}
