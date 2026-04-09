package net.nokunami.elementus.common.entity.projectile.catalyst;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.nokunami.elementus.common.catalystCore.ability.activeAbility.AbstractActiveAbility;

public class AbstractCatalystProjectile extends Projectile {
    protected AbstractCatalystProjectile(EntityType<? extends AbstractCatalystProjectile> type, Level level) {
        super(type, level);
    }

    @Override
    protected void defineSynchedData() {

    }
}
