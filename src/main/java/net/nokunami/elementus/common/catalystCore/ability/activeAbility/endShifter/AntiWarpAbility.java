package net.nokunami.elementus.common.catalystCore.ability.activeAbility.endShifter;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.nokunami.elementus.common.capability.abilityData.AbilityData;
import net.nokunami.elementus.common.catalystCore.ability.AbstractAbility;
import net.nokunami.elementus.config.CatalystArmorConfig;
import net.nokunami.elementus.config.EConfig;

import static net.nokunami.elementus.common.entity.MobUtil.teleportEntity;

public class AntiWarpAbility extends AbstractAbility {
    CatalystArmorConfig.voidShifter.antiWarp config = EConfig.catalystConfig.voidShifter.antiWarpAbility;
    int cooldown = config.baseCooldown;

    public AntiWarpAbility() { super(); }

    @Override
    public AbilityProperties properties() {
        return new AbilityProperties(cooldown);
    }

//    @Override public int getCooldown() { return cooldown; }

    @Override
    public boolean castAbility(LivingEntity entity, Level level, AbilityData data) {
        if (!level.isClientSide()) {
            level.playSound(null, entity, SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1, 0.5F);
        }
        return true;
    }

    public float distanceTo(Vec3 start, Vec3 stop) {
        float f = (float) (stop.x() - start.x());
        float f1 = (float) (stop.y() - start.y());
        float f2 = (float) (stop.z() - start.z());
        return Mth.sqrt(f * f + f1 * f1 + f2 * f2);
    }

    protected BlockState blockState(Level level, BlockPos pos) { return level.getBlockState(pos); }
}