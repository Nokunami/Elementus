package net.nokunami.elementus.common.catalystCore.ability.activeAbility.essenceOfTheStorm;

import com.github.L_Ender.cataclysm.config.CMCommonConfig;
import com.github.L_Ender.cataclysm.entity.projectile.Lightning_Spear_Entity;
import com.ninni.twigs.block.PaperLanternBlock;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.nokunami.elementus.common.capability.cAbility.CAbility;
import net.nokunami.elementus.common.catalystCore.ability.AbilityType;
import net.nokunami.elementus.common.catalystCore.ability.activeAbility.AbstractActiveAbility;

import static net.nokunami.elementus.ModChecker.cataclysm;

public class LightningSpearAbility extends AbstractActiveAbility {

    public LightningSpearAbility() { super(new AbilityProperties(AbilityType.TRIGGERED, 50).setDefaultCharge(3)); }

    @Override
    public boolean castAbility(LivingEntity entity, Level level, CAbility ca) {
        if (cataclysm) {
            if (!level.isClientSide()) {
                if (entity instanceof Player player) {
                    Vec3 lookDirection = player.getLookAngle();
                    Vec3 vec3 = new Vec3(lookDirection.x, lookDirection.y, lookDirection.z);
                    float yRot = (float) (Mth.atan2(vec3.z, vec3.x) * (180 / Math.PI)) + 90;
                    float xRot = (float) (-(Mth.atan2(vec3.y, Math.sqrt(vec3.x * vec3.x + vec3.z * vec3.z)) * (180 / Math.PI)));
                    Lightning_Spear_Entity lightning = new Lightning_Spear_Entity(player, vec3, level, (float) CMCommonConfig.Astrape.damage, 2.5);
                    lightning.setYRot(yRot);
                    lightning.setXRot(xRot);
                    lightning.setPos(lightning.getX(), player.getY(0.75), lightning.getZ());
                    lightning.setAreaDamage((float) CMCommonConfig.Astrape.areaDamage);
                    lightning.setAreaRadius(1);
                    level.addFreshEntity(lightning);
                }
            }
        }
        return false;
    }
}