package net.nokunami.elementus.common.catalystCore.ability.activeAbility.essenceOfTheStorm;

import com.github.L_Ender.cataclysm.config.CMCommonConfig;
import com.github.L_Ender.cataclysm.entity.effect.Wave_Entity;
import com.github.L_Ender.cataclysm.init.ModSounds;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.nokunami.elementus.common.capability.cAbility.CAbility;
import net.nokunami.elementus.common.catalystCore.ability.AbilityType;
import net.nokunami.elementus.common.catalystCore.ability.activeAbility.AbstractActiveAbility;
import net.nokunami.elementus.common.entity.MobUtil;

import static net.nokunami.elementus.ModChecker.cataclysm;

public class WaveAbility extends AbstractActiveAbility {

    public WaveAbility() { super(new AbilityProperties(AbilityType.TRIGGERED, 20).setCastDuration(30)); }

    @Override
    public boolean castAbility(LivingEntity entity, Level level, CAbility ca) {
        if (cataclysm) {
            if (!level.isClientSide()) {
                int numberOfWaves = 4;
                float angleStep = 25;
                double firstAngleOffset = (double) (numberOfWaves - 1) / 2 * angleStep;
                float yawRadians = (float) Math.toRadians((90 + entity.getYRot()));
                double vecX = Math.cos(yawRadians);
                double vecZ = Math.sin(yawRadians);
                double vec = 2;
                double spawnX = entity.getX() + vecX * vec;
                double spawnY = entity.getY();
                double spawnZ = entity.getZ() + vecZ * vec;

                level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), ModSounds.HEAVY_SMASH.get(), SoundSource.PLAYERS, 0.6F, 1);

                for (int k = 0; k < numberOfWaves; ++k) {
                    double angle = entity.getYRot() - firstAngleOffset + (double) ((float) k * angleStep);
                    double rad = Math.toRadians(angle);
                    double dx = -Math.sin(rad);
                    double dz = Math.cos(rad);
                    Wave_Entity WaveEntity = new Wave_Entity(level, entity, 60, (float) CMCommonConfig.Ceraunus.waveDamage);
                    WaveEntity.setPos(spawnX, spawnY, spawnZ);
                    WaveEntity.setState(1);
                    WaveEntity.setYRot(-((float) (Mth.atan2(dx, dz) * (180 / Math.PI))));
                    entity.level().addFreshEntity(WaveEntity);
                }
                return true;
            }
        }
        return false;
    }
}
