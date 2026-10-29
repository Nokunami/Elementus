package net.nokunami.elementus.config;

import me.fzzyhmstrs.fzzy_config.config.Config;
import me.fzzyhmstrs.fzzy_config.config.ConfigSection;
import net.minecraft.world.item.Tier;

import static net.nokunami.elementus.Elementus.modLoc;

public class UniqueItemsConfig extends Config {

    public UniqueItemsConfig() { super(modLoc("unique_items")); }

    public bladeOfResonance bladeOfResonance = new bladeOfResonance();
    public static class bladeOfResonance extends ConfigSection {
        public int damage = 3;
        public double attackSpeed = -2.7;
        public double attackReach = 1;
        public double sonicDamage = 12;
        public int baseCharge = 7;
        public int chargePenalty = 10;

        public double getDamage(Tier tier) { return damage + tier.getAttackDamageBonus(); }
    }

    public bladeOfSurgingWinds bladeOfSurgingWinds = new bladeOfSurgingWinds();
    public static class bladeOfSurgingWinds extends ConfigSection {
        public int damage = 3;
        public double attackSpeed = -1.9;
        public double attackReach = 1;
        public double burstDamage = 12;

        public double getDamage(Tier tier) { return damage + tier.getAttackDamageBonus(); }
    }
}
