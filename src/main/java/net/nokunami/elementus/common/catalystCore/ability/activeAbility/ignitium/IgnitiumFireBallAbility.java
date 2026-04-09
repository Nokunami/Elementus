package net.nokunami.elementus.common.catalystCore.ability.activeAbility.ignitium;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.nokunami.elementus.common.capability.cAbility.CAbility;
import net.nokunami.elementus.common.catalystCore.ability.AbilityType;
import net.nokunami.elementus.common.catalystCore.ability.activeAbility.AbstractActiveAbility;
import net.nokunami.elementus.common.entity.MobUtil;
import net.nokunami.elementus.common.entity.projectile.cataclysm.CatalystIgnisAbyssFireBall;
import net.nokunami.elementus.common.entity.projectile.cataclysm.CatalystIgnisFireBall;

public class IgnitiumFireBallAbility extends AbstractActiveAbility {

    public IgnitiumFireBallAbility() {
        super(new AbilityProperties(AbilityType.CONSTANT, 20).setCastDuration(30));
    }

    @Override
    public boolean castAbility(LivingEntity entity, Level level, CAbility ca) {
        var inst = CAbility.instance(entity);
        if (castOnTick(inst.getCastTick())) {
            shootFireball(entity, level, new Vec3(-2, 3, 0), 61);
            shootAbyssFireball(entity, level, new Vec3(0, 3, 0), 77);
            shootFireball(entity, level, new Vec3(2, 3, 0), 93);
            return true;
        }
        return false;
    }

    private void shootAbyssFireball(LivingEntity entity, Level level, Vec3 shotAt, int timer) {
        shotAt = shotAt.yRot(-entity.getYRot() * ((float)Math.PI / 180F));
        CatalystIgnisAbyssFireBall shot = new CatalystIgnisAbyssFireBall(level, entity);
        shot.setPos(entity.getX() - (entity.getBbWidth() + 1) * 0.15 * (double) Mth.sin(entity.yBodyRot * ((float)Math.PI / 180F)), entity.getY() + 1, entity.getZ() + (entity.getBbWidth() + 1) * 0.15 * (double) Mth.cos(entity.yBodyRot * ((float)Math.PI / 180F)));
        double d0 = shotAt.x;
        double d1 = shotAt.y;
        double d2 = shotAt.z;
        float f = Mth.sqrt((float)(d0 * d0 + d2 * d2)) * 0.35F;
        shot.shoot(d0, d1 + (double)f, d2, 0.25F, 3.0F);
        shot.setUp(timer);
        level.addFreshEntity(shot);
    }

    private void shootFireball(LivingEntity entity, Level level, Vec3 shotAt, int timer) {
        shotAt = shotAt.yRot(-entity.getYRot() * ((float)Math.PI / 180F));
        CatalystIgnisFireBall shot = new CatalystIgnisFireBall(level, entity);
        shot.setPos(entity.getX() - (entity.getBbWidth() + 1) * 0.15 * (double) Mth.sin(entity.yBodyRot * ((float)Math.PI / 180F)), entity.getY() + 1, entity.getZ() + (entity.getBbWidth() + 1) * 0.15 * (double) Mth.cos(entity.yBodyRot * ((float)Math.PI / 180F)));
        double d0 = shotAt.x;
        double d1 = shotAt.y;
        double d2 = shotAt.z;
        float f = Mth.sqrt((float)(d0 * d0 + d2 * d2)) * 0.35F;
        shot.shoot(d0, d1 + (double)f, d2, 0.25F, 3.0F);
        shot.setUp(timer);
        shot.setSoul(MobUtil.healthPercent(entity, 0.5F));
        level.addFreshEntity(shot);
    }
}
