package net.nokunami.elementus.common.entity;

import com.google.common.collect.Maps;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.ProtectionEnchantment;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

public class KnockbackUtil {
//    private static final ExplosionDamageCalculator EXPLOSION_DAMAGE_CALCULATOR = new ExplosionDamageCalculator();
//    private final RandomSource random = RandomSource.create();
    private final Level level;
    private final double x;
    private final double y;
    private final double z;
    @Nullable
    private final Entity entitySource;
    private final float knockbackRadius;
//    private final ObjectArrayList<BlockPos> toBlow = new ObjectArrayList<>();
//    private final Map<Player, Vec3> hitPlayers = Maps.newHashMap();
//    private final Vec3 position;

    public static KnockbackUtil knockbackUtil(Level level, @Nullable Entity source, double toBlowX, double toBlowY, double toBlowZ, double radius) {
        return new KnockbackUtil(level, source, toBlowX, toBlowY, toBlowZ, radius);
    }

    public KnockbackUtil(Level level, @Nullable Entity source, double toBlowX, double toBlowY, double toBlowZ, double radius) {
        this.level = level;
        entitySource = source;
        knockbackRadius = (float) radius;
        x = toBlowX;
        y = toBlowY;
        z = toBlowZ;
//        position = new Vec3(x, y, z);
    }

    public void knockback() {
        double f2 = knockbackRadius * 2;
        int k1 = Mth.floor(x - f2 - 1.0D);
        int l1 = Mth.floor(x + f2 + 1.0D);
        int i2 = Mth.floor(y - f2 - 1.0D);
        int i1 = Mth.floor(y + f2 + 1.0D);
        int j2 = Mth.floor(z - f2 - 1.0D);
        int j1 = Mth.floor(z + f2 + 1.0D);
        List<Entity> list = level.getEntities(entitySource, new AABB(k1, i2, j2, l1, i1, j1));
        Vec3 vec3 = new Vec3(x, y, z);

        for (Entity entity : list) {
            if (!entity.ignoreExplosion()) {
                double d12 = Math.sqrt(entity.distanceToSqr(vec3)) / f2;
                if (d12 <= 1.0D) {
                    double d5 = entity.getX() - x;
                    double d7 = entity.getEyeY() - y;
                    double d9 = entity.getZ() - z;
                    double d13 = Math.sqrt(d5 * d5 + d7 * d7 + d9 * d9);
                    if (d13 != 0.0D) {
                        d5 /= d13;
                        d7 /= d13;
                        d9 /= d13;
                        double d14 = getSeenPercent(vec3, entity);
                        double d10 = (1.0D - d12) * d14;
                        double d11 = entity instanceof LivingEntity living ? ProtectionEnchantment.getExplosionKnockbackAfterDampener(living, d10) : d10;

                        d5 *= d11;
                        d7 *= d11;
                        d9 *= d11;
                        Vec3 vec31 = new Vec3(d5, d7, d9);
                        entity.setDeltaMovement(entity.getDeltaMovement().add(vec31));
//                        if (entity instanceof Player player) {
//                            if (!player.isSpectator() && (!player.isCreative() || !player.getAbilities().flying)) {
//                                hitPlayers.put(player, vec31);
//                            }
//                        }
                    }
                }
            }
        }
    }

    public static float getSeenPercent(Vec3 pExplosionVector, Entity pEntity) {
        AABB aabb = pEntity.getBoundingBox();
        double d0 = 1 / ((aabb.maxX - aabb.minX) * 2 + 1);
        double d1 = 1 / ((aabb.maxY - aabb.minY) * 2 + 1);
        double d2 = 1 / ((aabb.maxZ - aabb.minZ) * 2 + 1);
        double d3 = (1 - Math.floor(1 / d0) * d0) / 2;
        double d4 = (1 - Math.floor(1 / d2) * d2) / 2;
        if (!(d0 < 0) && !(d1 < 0) && !(d2 < 0)) {
            int i = 0, j = 0;

            for(double d5 = 0; d5 <= 1; d5 += d0) {
                for(double d6 = 0; d6 <= 1; d6 += d1) {
                    for(double d7 = 0; d7 <= 1; d7 += d2) {
                        double d8 = Mth.lerp(d5, aabb.minX, aabb.maxX);
                        double d9 = Mth.lerp(d6, aabb.minY, aabb.maxY);
                        double d10 = Mth.lerp(d7, aabb.minZ, aabb.maxZ);
                        Vec3 vec3 = new Vec3(d8 + d3, d9, d10 + d4);
                        if (pEntity.level().clip(new ClipContext(vec3, pExplosionVector, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, pEntity)).getType() == HitResult.Type.MISS) {
                            ++i;
                        }
                        ++j;
                    }
                }
            }
            return (float) (i / j);
        } else {
            return 0;
        }
    }
}
