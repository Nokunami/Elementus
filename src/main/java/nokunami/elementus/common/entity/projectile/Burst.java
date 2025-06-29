package nokunami.elementus.common.entity.projectile;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;

public class Burst extends Projectile {
    public double xPower;
    public double yPower;
    public double zPower;
    private static final EntityDataAccessor<Float> DAMAGE = SynchedEntityData.defineId(Burst.class, EntityDataSerializers.FLOAT);

    public Burst(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public Burst(EntityType<? extends Burst> type, double getX, double gety, double getz, double x, double y, double z, Level level) {
        this(type, level);
        this.moveTo(getX, gety, getz, this.getYRot(), this.getXRot());
        this.reapplyPosition();
        double d0 = Math.sqrt(x * x + y * y + z * z);
        if (d0 != (double)0.0F) {
            this.xPower = x / d0 * 0.1;
            this.yPower = y / d0 * 0.1;
            this.zPower = z / d0 * 0.1;
        }

    }

//    public Burst(LivingEntity livingEntity, double x, double y, double z, Level level, float damage) {
//        this(ModEntityType.BURST.get(), livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), x, y, z, level);
//        this.setOwner(livingEntity);
//        this.setDamage(damage);
//    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(DAMAGE, 0F);
    }

    public float getDamage() {
        return (Float)this.entityData.get(DAMAGE);
    }

    public void setDamage(float damage) {
        this.entityData.set(DAMAGE, damage);
    }
}
