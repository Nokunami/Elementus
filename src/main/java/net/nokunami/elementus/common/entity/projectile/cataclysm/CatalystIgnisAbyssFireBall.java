package net.nokunami.elementus.common.entity.projectile.cataclysm;

import com.github.L_Ender.cataclysm.entity.projectile.Ignis_Abyss_Fireball_Entity;
import com.github.L_Ender.cataclysm.entity.projectile.Ignis_Fireball_Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class CatalystIgnisAbyssFireBall extends Ignis_Abyss_Fireball_Entity {

    public CatalystIgnisAbyssFireBall(EntityType<? extends Ignis_Abyss_Fireball_Entity> type, Level level) {
        super(type, level);
    }

    public CatalystIgnisAbyssFireBall(Level level, LivingEntity entity, double x, double y, double z) {
        super(level, entity, x, y, z);
    }

    public CatalystIgnisAbyssFireBall(Level worldIn, LivingEntity entity) {
        super(worldIn, entity);
    }
}
