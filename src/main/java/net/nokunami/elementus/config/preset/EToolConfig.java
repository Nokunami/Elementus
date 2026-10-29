package net.nokunami.elementus.config.preset;

import me.fzzyhmstrs.fzzy_config.util.Walkable;
import net.minecraft.world.item.Tier;
import net.nokunami.elementus.common.item.IETier;

public class EToolConfig implements Walkable {
    public double damage;
    public double speed;
    public boolean tierSpeed;

    public EToolConfig() { }

    public EToolConfig(double attackDamage, double attackSpeed, boolean weaponSpeed) {
        damage = attackDamage;
        speed = attackSpeed;
        tierSpeed = weaponSpeed;
    }

    public double getDamage(Tier tier) {
        return damage;
    }
    public double getSpeed(IETier tier) {
        return speed + (tierSpeed ? tier.getWeaponSpeed() : 0);
    }
    public double getSpeed(double weaponSpeed) {
        return speed + (tierSpeed ? weaponSpeed : 0);
    }
}
