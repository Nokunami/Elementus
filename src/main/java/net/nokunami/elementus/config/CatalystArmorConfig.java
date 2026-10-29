package net.nokunami.elementus.config;

import me.fzzyhmstrs.fzzy_config.annotations.Comment;
import me.fzzyhmstrs.fzzy_config.config.Config;
import me.fzzyhmstrs.fzzy_config.config.ConfigGroup;
import me.fzzyhmstrs.fzzy_config.config.ConfigSection;
import me.fzzyhmstrs.fzzy_config.util.Walkable;
import me.fzzyhmstrs.fzzy_config.validation.misc.ValidatedBoolean;
import me.fzzyhmstrs.fzzy_config.validation.misc.ValidatedPair;
import me.fzzyhmstrs.fzzy_config.validation.number.ValidatedDouble;
import me.fzzyhmstrs.fzzy_config.validation.number.ValidatedFloat;
import me.fzzyhmstrs.fzzy_config.validation.number.ValidatedInt;
import net.minecraft.Util;
import net.minecraft.world.item.ArmorItem;
import net.nokunami.elementus.config.preset.IConfigArmor;

import java.util.EnumMap;

import static net.nokunami.elementus.Elementus.modLoc;

public class CatalystArmorConfig extends Config {

    public CatalystArmorConfig() { super(modLoc("catalyst_armor")); }

    public catalystArmor baseCatalyst = new catalystArmor();
    public static class catalystArmor extends ConfigSection implements IConfigArmor {
        public int armor = 10;
        public double toughness = 4;
        @Comment(value = "value * 16. (Only applies if boolean is set to false)")
        public ValidatedPair<Integer, Boolean> durability = new ValidatedInt(56).pairWith(new ValidatedBoolean(true));
        public int enchantability = 20;
        public double knockbackRes = 0.1;
        public double attackSpeed = 0;
        public double movementSpeed = 0;

        public catalystArmor() { }

        @Override
        public EnumMap<ArmorItem.Type, Integer> getArmor() {
            return Util.make(new EnumMap<>(ArmorItem.Type.class), c -> {
                c.put(ArmorItem.Type.HELMET, 0);
                c.put(ArmorItem.Type.CHESTPLATE, armor);
                c.put(ArmorItem.Type.LEGGINGS, 0);
                c.put(ArmorItem.Type.BOOTS, 0);
            });
        }
        @Override public int enchantability() { return enchantability; }
        @Override public int durability() { return durability.get().component1(); }
        @Override public double toughness() { return toughness; }
        @Override public double knockbackRes() { return knockbackRes; }
        @Override public double attackSpeed() { return attackSpeed; }
        @Override public double movementSpeed() { return movementSpeed; }
    }

    public beaconPower beaconPower = new beaconPower();
    public static class beaconPower extends ConfigSection {
        public baseCatalyst armor = new baseCatalyst(10, 4, 0.1, 0, 0);
        @ValidatedDouble.Restrict(min = 1)
        public int amp = 1;
        @ValidatedDouble.Restrict(min = 0)
        public int duration = 160;
        @ValidatedDouble.Restrict(min = 0)
        public int cooldown = 350;
        @ValidatedDouble.Restrict(min = 1)
        public int boostedAmp = 2;
        @ValidatedDouble.Restrict(min = 0)
        public int boostedDuration = 160;
        public areaEffect areaEffect = new areaEffect();

        public static class areaEffect implements Walkable {
            @ValidatedDouble.Restrict(min = 0)
            public int speed = 0;
            @ValidatedDouble.Restrict(min = 0)
            public int haste = 0;
            @ValidatedDouble.Restrict(min = 0)
            public int resistance = 0;
            @ValidatedDouble.Restrict(min = 0)
            public int regeneration = 0;
            @ValidatedDouble.Restrict(min = 0)
            public int jump = 0;
            @ValidatedDouble.Restrict(min = 0)
            public int strength = 0;

            public areaEffect() { }
        }
    }

    public totemOfUndying totemOfUndying = new totemOfUndying();
    public static class totemOfUndying extends ConfigSection {
        public baseCatalyst armor = new baseCatalyst(10, 4, 0.1, 0, 0);
        @ValidatedDouble.Restrict(min = 0)
        public int cooldown = 2400;

        public ConfigGroup regen = new ConfigGroup("regeneration");
        @ValidatedDouble.Restrict(min = 1)
        public int regenAmp = 2;
        @ConfigGroup.Pop
        @ValidatedDouble.Restrict(min = 0)
        public int regenDuration = 900;

        public ConfigGroup absorption = new ConfigGroup("absorption");
        @ValidatedDouble.Restrict(min = 1)
        public int absorbAmp = 2;
        @ConfigGroup.Pop
        @ValidatedDouble.Restrict(min = 0)
        public int absorbDuration = 100;

        public ConfigGroup fireResistance = new ConfigGroup("fire_resistance");
        @ValidatedDouble.Restrict(min = 1)
        public int fireResAmp = 1;
        @ConfigGroup.Pop
        @ValidatedDouble.Restrict(min = 0)
        public int fireResDuration = 800;
    }

    public heartOfTheSea heartOfTheSea = new heartOfTheSea();
    public static class heartOfTheSea extends ConfigSection {
        public baseCatalyst armor = new baseCatalyst(10, 4, 0.1, 0, 0);
        @ValidatedInt.Restrict(min = 1)
        public int amp = 1;
        @ValidatedInt.Restrict(min = 1)
        public int ampArea = 1;
        @ValidatedInt.Restrict(min = 0)
        public int durationArea = 400;

        public ConfigGroup conduitPower = new ConfigGroup("conduit_power");
        @ValidatedDouble.Restrict(min = 0)
        public int conduitAreaDamage = 0;
        @ConfigGroup.Pop
        @ValidatedDouble.Restrict(min = 0)
        public int conduitCatalystExhaustion = 0;

        public ConfigGroup cleanse = new ConfigGroup("conduit_cleanse");
        @ValidatedFloat.Restrict(min = 0)
        public float cleanseDamage = 6;
        @ValidatedDouble.Restrict(min = 0)
        public int cleanseCooldown = 50;
        @ValidatedDouble.Restrict(min = 0)
        public int cleanseCastDuration = 200;
        @ConfigGroup.Pop
        @ValidatedDouble.Restrict(min = 0)
        public int cleanseArea = 12;
    }

    public voidShifter voidShifter = new voidShifter();
    public static class voidShifter extends ConfigSection {
        public baseCatalyst armor = new baseCatalyst(10, 4, 0.1, 0, 0);

        public teleport teleportAbility = new teleport();
        public static class teleport implements Walkable {
            @ValidatedInt.Restrict(min = 0)
            public int range = 64;
            @Comment(value = "Should cooldown scale with distance. (Value only applies if boolean is set to true)")
            public ValidatedPair<Double, Boolean> cooldownMod = new ValidatedDouble(1, Double.MAX_VALUE, 1).pairWith(new ValidatedBoolean(true));
            public int baseCooldown = 10;

            public teleport() { }
        }
        public groupTeleport groupTeleportAbility = new groupTeleport();
        public static class groupTeleport implements Walkable {
            @ValidatedInt.Restrict(min = 0)
            public int range = 64;
            @ValidatedDouble.Restrict(min = 1)
            public ValidatedPair<Double, Boolean> cooldownMod = new ValidatedDouble(4).pairWith(new ValidatedBoolean(true));
            public int castDuration = 20;
            public int baseCooldown = 40;

            public groupTeleport() { }
        }
        public antiWarp antiWarpAbility = new antiWarp();
        public static class antiWarp implements Walkable {
            @ValidatedInt.Restrict(min = 0)
            public int range = 64;
            @Comment(value = "Should cooldown scale with distance. (Value only applies if boolean is set to true)")
            public ValidatedPair<Double, Boolean> cooldownMod = new ValidatedDouble(1, Double.MAX_VALUE, 1).pairWith(new ValidatedBoolean(true));
            public int baseCooldown = 10;

            public antiWarp() { }
        }
    }

    public wayfinder wayfinder = new wayfinder();
    public static class wayfinder extends ConfigSection {
        public baseCatalyst armor = new baseCatalyst(10, 4, 0.1, 0, 0);

        public lodehomeAbility lodehomeAbility = new lodehomeAbility();
        public static class lodehomeAbility implements Walkable {
            @ValidatedInt.Restrict(min = 0)
            public int castDuration = 60;
            public int cooldown = 6000;

            public lodehomeAbility() { }
        }
    }

    public static class baseCatalyst implements Walkable {
        public int armor;
        public double toughness;
        public double knockbackRes;
        public double attackSpeed;
        public double movementSpeed;

        public baseCatalyst() { }

        public baseCatalyst(int armor, double toughness, double knockbackRes, double attackSpeed, double movementSpeed) {
            this.armor = armor;
            this.toughness = toughness;
            this.knockbackRes = knockbackRes;
            this.attackSpeed = attackSpeed;
            this.movementSpeed = movementSpeed;
        }
    }
}