package net.nokunami.elementus.config;

import me.fzzyhmstrs.fzzy_config.annotations.Action;
import me.fzzyhmstrs.fzzy_config.annotations.Comment;
import me.fzzyhmstrs.fzzy_config.annotations.RequiresAction;
import me.fzzyhmstrs.fzzy_config.config.Config;
import me.fzzyhmstrs.fzzy_config.config.ConfigSection;
import me.fzzyhmstrs.fzzy_config.util.Walkable;
import me.fzzyhmstrs.fzzy_config.validation.number.ValidatedInt;

import static net.nokunami.elementus.Elementus.modLoc;

public class EnchantmentConfig extends Config {

    public EnchantmentConfig() { super(modLoc("enchantment")); }

    public ArcaneSharpness arcaneSharpness = new ArcaneSharpness();
    public static class ArcaneSharpness extends ConfigSection {
        public baseEnchantConfig properties = new baseEnchantConfig(true, false, true, true, true);
        @Comment(value = "Item's EnchantValue * this")
        public double damagePercent = 0.3;
        public boolean damageEnchantmentIncompatibility = true;
    }

    public sacrifice sacrifice = new sacrifice();
    public static class sacrifice extends ConfigSection {
        public baseEnchantConfig properties = new baseEnchantConfig(true, true, true, true, true);
        public double sacrificeBaseDamage = 15;
        public double sacrificeDamageBonus = 0.25;
        public double selfSacrificeDamage = 0.25;
    }

    public resonance resonance = new resonance();
    public static class resonance extends ConfigSection {
        public baseEnchantConfig properties = new baseEnchantConfig(true, false, true, true, true);
    }

    public condensedBurst condensedBurst = new condensedBurst();
    public static class condensedBurst extends ConfigSection {
        public baseEnchantConfig properties = new baseEnchantConfig(false, false, true, true, true);
        public int chargePenalty = 10;
    }

    public rush rush = new rush();
    public static class rush extends ConfigSection {
        public baseEnchantConfig properties = new baseEnchantConfig(false, false, true, true, true);
    }

    public chargeStacking chargeStacking = new chargeStacking();
    public static class chargeStacking extends ConfigSection {
        public baseEnchantConfig properties = new baseEnchantConfig(false, false, true, true, true);
        public ValidatedInt level = new ValidatedInt(2, 2, 1);
    }

    public pulseBurst pulseBurst = new pulseBurst();
    public static class pulseBurst extends ConfigSection {
        public baseEnchantConfig properties = new baseEnchantConfig(true, false, true, true, true);
    }

    public cleave cleave = new cleave();
    public static class cleave extends ConfigSection {
        public baseEnchantConfig properties = new baseEnchantConfig(true, true, true, true, true);
        public double cleaveBaseDamage = 15;
        public double cleaveDamageBonus = 0.25;
//        public double selfSacrificeDamage = 0.25;
    }

    public torrent torrent = new torrent();
    public static class torrent extends ConfigSection {
        public baseEnchantConfig properties = new baseEnchantConfig(false, false, true, true, true);
//        public double cleaveBaseDamage = 15;
//        public double cleaveDamageBonus = 0.25;
//        public double selfSacrificeDamage = 0.25;
    }

    public static class baseEnchantConfig implements Walkable {
        @RequiresAction(action = Action.RESTART)
        public boolean isTreasure;
        @RequiresAction(action = Action.RESTART)
        public boolean isCurse;
        @RequiresAction(action = Action.RESTART)
        public boolean isTradable;
        @RequiresAction(action = Action.RESTART)
        public boolean isDiscoverable;
        @RequiresAction(action = Action.RESTART)
        public boolean isAllowedOnBooks;

        public baseEnchantConfig() { }

        public baseEnchantConfig(boolean treasure, boolean curse, boolean tradable, boolean discoverable, boolean allowedOnBooks) {
            isTreasure = treasure;
            isCurse = curse;
            isTradable = tradable;
            isDiscoverable = discoverable;
            isAllowedOnBooks = allowedOnBooks;
        }
    }
}