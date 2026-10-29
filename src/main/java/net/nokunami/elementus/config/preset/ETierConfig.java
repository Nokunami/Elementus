package net.nokunami.elementus.config.preset;

import me.fzzyhmstrs.fzzy_config.annotations.Action;
import me.fzzyhmstrs.fzzy_config.annotations.Comment;
import me.fzzyhmstrs.fzzy_config.annotations.RequiresAction;
import me.fzzyhmstrs.fzzy_config.config.ConfigSection;
import me.fzzyhmstrs.fzzy_config.validation.number.ValidatedDouble;

public class ETierConfig extends ConfigSection implements IConfigTier {
    @RequiresAction(action = Action.RESTART)
    public int harvestLevel;
    @RequiresAction(action = Action.RESTART)
    public int durability;
    @RequiresAction(action = Action.RESTART)
    public double efficiency;
    @RequiresAction(action = Action.RESTART)
    public double damage;
    @RequiresAction(action = Action.RESTART)
    public int enchantability;
    public double weaponSpeed;
    @ValidatedDouble.Restrict(min = 1)
    @Comment(value = "Formula: (base + enchant/etc) * bonus.")
    public double bonusMiningSpeed;

    public ETierConfig(int level, int maxDurability, double toolEfficiency, double bonusDamage, int enchantValue, double addiotnalWeaponSpeed, double additionalMiningSpeed) {
        harvestLevel = level;
        durability = maxDurability;
        efficiency = toolEfficiency;
        damage = bonusDamage;
        enchantability = enchantValue;
        weaponSpeed = addiotnalWeaponSpeed;
        bonusMiningSpeed = additionalMiningSpeed;
    }

    @Override public int harvestLevel() { return harvestLevel; }
    @Override public int durability() { return durability; }
    @Override public double efficiency() { return efficiency; }
    @Override public double damage() { return damage; }
    @Override public int enchantability() { return enchantability; }
    @Override public double weaponSpeed() { return weaponSpeed; }
    @Override public double additionalMiningSpeed() { return bonusMiningSpeed; }
}