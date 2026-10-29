package net.nokunami.elementus.api.util;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.item.enchantment.ProtectionEnchantment;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.List;

// Copy of Explosion.class, just the knockback
public class KnockbackUtil {
    private final Entity source;
    Level level;
    private final double x, y, z;
    private final float radius;
    private final ObjectArrayList<BlockPos> toBlow = new ObjectArrayList<>();

    public KnockbackUtil(Entity entity, Level level, Vec3 vec3, float radius) {
        source = entity;
        this.level = level;
        x = vec3.x;
        y = vec3.y;
        z = vec3.z;
        this.radius = radius;
    }

    public static float getSeenPercent(Vec3 pExplosionVector, Entity pEntity) {
        AABB aabb = pEntity.getBoundingBox();
        double d0 = 1.0D / ((aabb.maxX - aabb.minX) * 2.0D + 1.0D);
        double d1 = 1.0D / ((aabb.maxY - aabb.minY) * 2.0D + 1.0D);
        double d2 = 1.0D / ((aabb.maxZ - aabb.minZ) * 2.0D + 1.0D);
        double d3 = (1.0D - Math.floor(1.0D / d0) * d0) / 2.0D;
        double d4 = (1.0D - Math.floor(1.0D / d2) * d2) / 2.0D;
        if (!(d0 < 0.0D) && !(d1 < 0.0D) && !(d2 < 0.0D)) {
            int i = 0;
            int j = 0;

            for(double d5 = 0.0D; d5 <= 1.0D; d5 += d0) {
                for(double d6 = 0.0D; d6 <= 1.0D; d6 += d1) {
                    for(double d7 = 0.0D; d7 <= 1.0D; d7 += d2) {
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

            return (float)i / (float)j;
        } else {
            return 0.0F;
        }
    }

    public void explode() {
//        this.level.gameEvent(this.source, GameEvent.EXPLODE, new Vec3(this.x, this.y, this.z));
//        Set<BlockPos> set = Sets.newHashSet();
        int i = 16;

        for(int j = 0; j < i; ++j) {
            for(int k = 0; k < i; ++k) {
                for(int l = 0; l < i; ++l) {
                    if (j == 0 || j == 15 || k == 0 || k == 15 || l == 0 || l == 15) {
                        double d0 = ((float) j / 15 * 2 - 1), d1 = ((float) k / 15 * 2 - 1), d2 = ((float) l / 15 * 2 - 1);
                        double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                        d0 /= d3;
                        d1 /= d3;
                        d2 /= d3;
                        float f = radius * (0.7F + level.random.nextFloat() * 0.6F);
                        double d4 = x, d6 = y, d8 = z;

                        for(float f1 = 0.3F; f > 0; f -= 0.22500001F) {
                            BlockPos blockpos = BlockPos.containing(d4, d6, d8);
//                            BlockState blockstate = level.getBlockState(blockpos);
//                            FluidState fluidstate = level.getFluidState(blockpos);
                            if (!level.isInWorldBounds(blockpos)) break;

//                            Optional<Float> optional = this.damageCalculator.getBlockExplosionResistance(this, this.level, blockpos, blockstate, fluidstate);
//                            if (optional.isPresent()) {
//                                f -= (optional.get() + 0.3F) * 0.3F;
//                            }

//                            if (f > 0.0F && this.damageCalculator.shouldBlockExplode(this, this.level, blockpos, blockstate, f)) {
//                                set.add(blockpos);
//                            }

                            d4 += d0 * (double)0.3F;
                            d6 += d1 * (double)0.3F;
                            d8 += d2 * (double)0.3F;
                        }
                    }
                }
            }
        }

//        this.toBlow.addAll(set);
        float f2 = this.radius * 2.0F;
        int k1 = Mth.floor(this.x - (double)f2 - 1);
        int l1 = Mth.floor(this.x + (double)f2 + 1);
        int i2 = Mth.floor(this.y - (double)f2 - 1);
        int i1 = Mth.floor(this.y + (double)f2 + 1);
        int j2 = Mth.floor(this.z - (double)f2 - 1);
        int j1 = Mth.floor(this.z + (double)f2 + 1);
        List<Entity> list = this.level.getEntities(this.source, new AABB(k1, i2, j2, l1, i1, j1));
//        net.minecraftforge.event.ForgeEventFactory.onExplosionDetonate(this.level, this, list, f2);
        Vec3 vec3 = new Vec3(this.x, this.y, this.z);

        for (Entity entity : list) {
            if (!entity.ignoreExplosion()) {
                double d12 = Math.sqrt(entity.distanceToSqr(vec3)) / (double) f2;
                if (d12 <= 1) {
                    double d5 = entity.getX() - this.x;
                    double d7 = (entity instanceof PrimedTnt ? entity.getY() : entity.getEyeY()) - this.y;
                    double d9 = entity.getZ() - this.z;
                    double d13 = Math.sqrt(d5 * d5 + d7 * d7 + d9 * d9);
                    if (d13 != 0) {
                        d5 /= d13;
                        d7 /= d13;
                        d9 /= d13;
                        double d14 = getSeenPercent(vec3, entity);
                        double d10 = (1 - d12) * d14;
//                        entity.hurt(this.getDamageSource(), (float) ((int) ((d10 * d10 + d10) / 2.0D * 7.0D * (double) f2 + 1.0D)));
                        double d11;
                        if (entity instanceof LivingEntity livingentity) {
                            d11 = ProtectionEnchantment.getExplosionKnockbackAfterDampener(livingentity, d10);
                        } else {
                            d11 = d10;
                        }

                        d5 *= d11;
                        d7 *= d11;
                        d9 *= d11;
                        Vec3 vec31 = new Vec3(d5, d7, d9);
                        entity.setDeltaMovement(entity.getDeltaMovement().add(vec31));
//                        if (entity instanceof Player player) {
//                            if (!player.isSpectator() && (!player.isCreative() || !player.getAbilities().flying)) {
//                                this.hitPlayers.put(player, vec31);
//                            }
//                        }
                    }
                }
            }
        }
    }
}
