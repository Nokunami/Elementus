package net.nokunami.elementus.config.preset;

import me.fzzyhmstrs.fzzy_config.annotations.Action;
import me.fzzyhmstrs.fzzy_config.annotations.RequiresAction;
import me.fzzyhmstrs.fzzy_config.config.ConfigGroup;
import me.fzzyhmstrs.fzzy_config.config.ConfigSection;

public class EToolSetConfig extends ConfigSection {
    @RequiresAction(action = Action.RESTART)
    public EToolConfig sword;
    @RequiresAction(action = Action.RESTART)
    public EToolConfig shovel;
    @RequiresAction(action = Action.RESTART)
    public EToolConfig pickaxe;
    @RequiresAction(action = Action.RESTART)
    public EToolConfig axe;
    @RequiresAction(action = Action.RESTART)
    public EToolConfig hoe;

    public ConfigGroup shield = new ConfigGroup("shield");
    @RequiresAction(action = Action.RESTART)
    public int shieldDurability;
    @ConfigGroup.Pop
    @RequiresAction(action = Action.RESTART)
    public int shieldBuffer;

    @RequiresAction(action = Action.RESTART)
    public int bowDurability;

    public EToolSetConfig(
            double swordDamage, double swordSpeed,
            double shovelDamage, double shovelSpeed,
            double pickaxeDamage, double pickaxeSpeed,
            double axeDamage, double axeSpeed,
            double hoeDamage, double hoeSpeed,
            int shield, int shieldBuffer, int bow
    ) {
        sword = new EToolConfig(swordDamage, swordSpeed, true);
        shovel = new EToolConfig(shovelDamage, shovelSpeed, false);
        pickaxe = new EToolConfig(pickaxeDamage, pickaxeSpeed, false);
        axe = new EToolConfig(axeDamage, axeSpeed, true);
        hoe = new EToolConfig(hoeDamage, hoeSpeed, false);
        shieldDurability = shield;
        this.shieldBuffer = shieldBuffer;
        bowDurability = bow;
    }
}