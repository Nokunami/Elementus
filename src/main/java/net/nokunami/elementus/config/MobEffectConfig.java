package net.nokunami.elementus.config;

import me.fzzyhmstrs.fzzy_config.annotations.Comment;
import me.fzzyhmstrs.fzzy_config.config.Config;
import me.fzzyhmstrs.fzzy_config.config.ConfigSection;
import me.fzzyhmstrs.fzzy_config.validation.misc.ValidatedPair;
import me.fzzyhmstrs.fzzy_config.validation.number.ValidatedDouble;
import me.fzzyhmstrs.fzzy_config.validation.number.ValidatedInt;

import static net.nokunami.elementus.Elementus.modLoc;

public class MobEffectConfig extends Config {

    public MobEffectConfig() { super(modLoc("mob_effect")); }

    public beaconPower beaconPower = new beaconPower();
    public beaconPower witheredBeaconPower = new beaconPower();
    public static class beaconPower extends ConfigSection {
        @ValidatedDouble.Restrict(min = 0)
        public double speed = 0.2;
        @ValidatedInt.Restrict(min = 0)
        public int haste = 1;
        @ValidatedDouble.Restrict(min = 0)
        @Comment(value = "Test")
        public ValidatedPair<Double, Double> resistance = new ValidatedDouble(0.1).pairWith(new ValidatedDouble(0.1));
        @ValidatedDouble.Restrict(min = 0)
        public double jump = 0;
        @ValidatedDouble.Restrict(min = 0)
        public double strength = 3;
    }
}
