package net.nokunami.elementus.common.entity.projectile.cataclysm;

import com.github.L_Ender.cataclysm.config.CMCommonConfig;
import com.github.L_Ender.cataclysm.entity.AnimationMonster.BossMonsters.Ignis_Entity;
import com.github.L_Ender.cataclysm.entity.effect.Cm_Falling_Block_Entity;
import com.github.L_Ender.cataclysm.entity.projectile.Ignis_Abyss_Fireball_Entity;
import com.github.L_Ender.cataclysm.entity.projectile.Ignis_Fireball_Entity;
import com.github.L_Ender.cataclysm.init.ModEffect;
import com.github.L_Ender.cataclysm.util.CustomExplosion.IgnisExplosion;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.nokunami.elementus.common.entity.MobUtil;
import net.nokunami.elementus.common.registry.tempCompat.CompatEntityTypes;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

// Credits: Cataclysm's Ignis_Fireball_Entity.class
public class CatalystIgnisFireBall extends AbstractHurtingProjectile {
    private static final EntityDataAccessor<Boolean> SOUL = SynchedEntityData.defineId(CatalystIgnisFireBall.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> FIRED = SynchedEntityData.defineId(CatalystIgnisFireBall.class, EntityDataSerializers.BOOLEAN);
    private int timer;
    private Vec3[] trailPositions;
    private int trailPointer;

    public CatalystIgnisFireBall(EntityType<? extends CatalystIgnisFireBall> type, Level level) {
        super(type, level);
        trailPositions = new Vec3[64];
        trailPointer = -1;
    }

    public CatalystIgnisFireBall(Level level, LivingEntity entity, double x, double y, double z) {
        super(CompatEntityTypes.CataclysmEntities.CATALYST_IGNIS_FIREBALL.get(), entity, x, y, z, level);
        trailPositions = new Vec3[64];
        trailPointer = -1;
    }

    public CatalystIgnisFireBall(Level level, LivingEntity entity) {
        super(CompatEntityTypes.CataclysmEntities.CATALYST_IGNIS_FIREBALL.get(), level);
        setOwner(entity);
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(SOUL, false);
        this.entityData.define(FIRED, false);
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putBoolean("isSoul", isSoul());
        tag.putInt("timer", timer);
        tag.putBoolean("fired", getFired());
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        setSoul(tag.getBoolean("is_soul"));
        timer = tag.getInt("timer");
        setFired(tag.getBoolean("fired"));
    }

    public boolean isSoul() { return entityData.get(SOUL); }
    public void setSoul(boolean IsSoul) { entityData.set(SOUL, IsSoul); }

    public void setFired(boolean fired) { entityData.set(FIRED, fired); }
    public boolean getFired() { return entityData.get(FIRED); }

    @Override public boolean isOnFire() { return false; }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide) {
            --timer;
            if (timer <= 0 && !getFired()) setFired(true);
        }
        if (timer < -160) discard();

        if (timer == 0 || timer == -40) {
            Entity entity = getOwner();
//            if (entity instanceof Mob && ((Mob) entity).getTarget() != null) {
//                LivingEntity target = ((Mob) entity).getTarget();
//                if (target == null) discard();
//
//                float speed = isSoul() ? 0.25F : 0.2F;
//                double dx = target.getX() - getX();
//                double dy = target.getY() + (double)(target.getBbHeight() * 0.5F) - getY();
//                double dz = target.getZ() - getZ();
//                double d = Math.sqrt(dx * dx + dy * dy + dz * dz);
//                dx /= d;
//                dy /= d;
//                dz /= d;
//                xPower = dx * (double)speed;
//                yPower = dy * (double)speed;
//                zPower = dz * (double)speed;
//            }
        }

        Vec3 trailAt = position().add(0, (getBbHeight() / 2.0F), 0);
        if (trailPointer == -1) Arrays.fill(trailPositions, trailAt);
        if (++trailPointer == trailPositions.length) trailPointer = 0;
        trailPositions[trailPointer] = trailAt;
    }

    public void setUp(int delay) {
        setFired(false);
        timer = delay;
    }

    protected void onHitEntity(@NotNull EntityHitResult result) {
        super.onHitEntity(result);
        Entity entity = result.getEntity();
        Entity shooter = getOwner();
        if (level() instanceof ServerLevel) {
            if (getFired() && MobUtil.isSeenAsEnemy(this, entity)) {

            }
            if (this.getFired() && !(entity instanceof Ignis_Fireball_Entity) && !(entity instanceof Ignis_Abyss_Fireball_Entity) && !(entity instanceof Cm_Falling_Block_Entity) && (!(entity instanceof Ignis_Entity) || !(shooter instanceof Ignis_Entity))) {
                boolean flag;
                if (shooter instanceof LivingEntity) {
                    LivingEntity owner = (LivingEntity)shooter;
                    this.damageSources().mobProjectile(this, owner);
                    float damage = this.isSoul() ? 8.0F : 6.0F;
                    if (entity instanceof LivingEntity) {
                        flag = entity.hurt(this.damageSources().mobProjectile(this, owner), damage + ((LivingEntity)entity).getMaxHealth() * 0.07F);
                    } else {
                        flag = entity.hurt(this.damageSources().mobProjectile(this, owner), damage);
                    }

                    if (flag) {
                        if (entity.isAlive()) {
                            this.doEnchantDamageEffects(owner, entity);
                        }

                        if (owner instanceof Ignis_Entity) {
                            owner.heal(5.0F * (float) CMCommonConfig.Ignis.HealingMultiplier);
                        } else {
                            owner.heal(5.0F);
                        }
                    }
                } else {
                    flag = entity.hurt(this.damageSources().magic(), 5.0F);
                }

                if (flag && entity instanceof LivingEntity) {
                    MobEffectInstance effectinstance1 = ((LivingEntity)entity).getEffect(ModEffect.EFFECTBLAZING_BRAND.get());
                    int i = 2;
                    if (effectinstance1 != null) {
                        i += effectinstance1.getAmplifier();
                        ((LivingEntity)entity).removeEffectNoUpdate(ModEffect.EFFECTBLAZING_BRAND.get());
                    } else {
                        --i;
                    }

                    i = Mth.clamp(i, 0, 4);
                    MobEffectInstance effectinstance = new MobEffectInstance(ModEffect.EFFECTBLAZING_BRAND.get(), 200, i, false, false, true);
                    ((LivingEntity)entity).addEffect(effectinstance);
                }

                IgnisExplosion explosion = new IgnisExplosion(this.level(), this, null, null, this.getX(), this.getY(), this.getZ(), 1.0F, true, Explosion.BlockInteraction.KEEP);
                explosion.explode();
                explosion.finalizeExplosion(this.isSoul() ? 2 : 1, 0.35);
                this.discard();
            }
        }

    }

    protected void onHitBlock(@NotNull BlockHitResult result) {
        super.onHitBlock(result);
        if (!this.level().isClientSide && this.getFired()) {
            IgnisExplosion explosion = new IgnisExplosion(this.level(), this, (DamageSource)null, (ExplosionDamageCalculator)null, this.getX(), this.getY(), this.getZ(), 1.0F, true, Explosion.BlockInteraction.KEEP);
            explosion.explode();
            explosion.finalizeExplosion(this.isSoul() ? 2 : 1, 0.35);
            this.discard();
        }

    }

    public boolean isPickable() { return false; }

    public boolean hurt(@NotNull DamageSource source, float amount) { return false; }

    public Vec3 getTrailPosition(int pointer, float partialTick) {
        if (isRemoved()) partialTick = 1.0F;

        int i = trailPointer - pointer & 63;
        int j = trailPointer - pointer - 1 & 63;
        Vec3 d0 = trailPositions[j];
        Vec3 d1 = trailPositions[i].subtract(d0);
        return d0.add(d1.scale(partialTick));
    }

    public boolean hasTrail() { return trailPointer != -1; }
}