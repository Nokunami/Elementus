package net.nokunami.elementus.common.catalystCore.ability.activeAbility.ignitium;

import com.github.L_Ender.cataclysm.entity.effect.ScreenShake_Entity;
import com.github.L_Ender.cataclysm.init.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.nokunami.elementus.common.capability.cAbility.CAbility;
import net.nokunami.elementus.common.catalystCore.ability.AbilityType;
import net.nokunami.elementus.common.catalystCore.ability.activeAbility.AbstractActiveAbility;
import net.nokunami.elementus.common.entity.MobUtil;
import net.nokunami.elementus.common.entity.effect.CatalystFlameStrike;

import static net.nokunami.elementus.common.entity.MobUtil.playEntitySound;

public class IgnisFinalAttackAbility extends AbstractActiveAbility {
    boolean cooldown = false;

    public IgnisFinalAttackAbility() { super(new AbilityProperties(AbilityType.CONSTANT, 1200).setCastDuration(60)); }

    @Override
    public boolean castAbility(LivingEntity entity, Level level, CAbility ca) {
        if (!level.isClientSide()) {
            if (ca.getCastTick() == 50)
                playEntitySound(entity, ModSounds.FLAME_BURST);
            return true;
        }
        return false;
    }

    @Override
    public void onCastStop(LivingEntity entity, Level level, CAbility ca) {
        if (entity instanceof Player player) {
            if (!level.isClientSide()) {
                int i = ca.getCastTick();
                double headY = player.getY() + 1;
                int standingOnY = Mth.floor(player.getY()) - 2;
                float yawRadians = (float) Math.toRadians((90 + player.getYRot()));
                boolean hasSucceeded = false;
                if (i >= 60) {
                    for (int l = 0; l < 10; ++l) {
                        double d2 = 2.25 * (l + 1);
                        int j2 = (int) (1.5F * l);
                        if (spawnFlameStrike(player.getX() + Mth.cos(yawRadians) * d2, player.getZ() + Mth.sin(yawRadians) * d2, standingOnY, headY, yawRadians, 40, j2, j2, level, 1.0F, player)) {
                            hasSucceeded = true;
                        }
                    }

                    if (hasSucceeded) {
                        ScreenShake_Entity.ScreenShake(level, player.position(), 30, 0.15F, 0, 30);
                        playEntitySound(player, ModSounds.SWORD_STOMP);
                    }
                }
                cooldown = hasSucceeded;
            }
        }
    }

    private boolean spawnFlameStrike(double x, double z, double minY, double maxY, float rotation, int duration, int wait, int delay, Level level, float radius, LivingEntity player) {
        BlockPos blockpos = BlockPos.containing(x, maxY, z);
        boolean flag = false;
        double d0 = 0;

        do {
            BlockPos blockpos1 = blockpos.below();
            BlockState blockstate = level.getBlockState(blockpos1);
            if (blockstate.isFaceSturdy(level, blockpos1, Direction.UP)) {
                if (!level.isEmptyBlock(blockpos)) {
                    BlockState blockstate1 = level.getBlockState(blockpos);
                    VoxelShape voxelshape = blockstate1.getCollisionShape(level, blockpos);
                    if (!voxelshape.isEmpty()) {
                        d0 = voxelshape.max(Direction.Axis.Y);
                    }
                }

                flag = true;
                break;
            }

            blockpos = blockpos.below();
        } while((double)blockpos.getY() >= minY);

        if (flag) {
//            level.addFreshEntity(new Flame_Strike_Entity(level, x, (double)blockpos.getY() + d0, z, rotation, duration, wait, delay, radius, 6, 2, MobUtil.healthPercent(player, 0.5F), player));
            level.addFreshEntity(new CatalystFlameStrike(level, x, (double)blockpos.getY() + d0, z, rotation, duration, wait, delay, radius, 12, 6, MobUtil.healthPercent(player, 0.5F), player));
            return true;
        } else return false;
    }
}