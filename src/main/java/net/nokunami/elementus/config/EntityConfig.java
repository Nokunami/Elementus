package net.nokunami.elementus.config;

import me.fzzyhmstrs.fzzy_config.config.Config;
import me.fzzyhmstrs.fzzy_config.config.ConfigSection;

import static net.nokunami.elementus.Elementus.modLoc;

public class EntityConfig extends Config {

    public baseConfig astaliteLongArm = new baseConfig(125, 0.25, 0.3, 1, 15, 10, 4, 32, 5);
    public baseConfig astaliteCarrier = new baseConfig(150, 0.21, 0.25, 1, 8, 15, 4, 32, 5);
    public baseConfig steel = new baseConfig(150, 0.25, 0.3, 1, 15, 10, 0, 32, 5);

    public baseConfig diarkriteGolem = new baseConfig(250, 0.2, 0.35, 2, 40, 20, 12, 32, 5);

    public baseConfig anthektiteGolem = new baseConfig(200, 0.3, 0.4, 0.5, 10, 10, 0, 32, 5);

    public EntityConfig() {
        super(modLoc("entity"));
    }

    public static class baseConfig extends ConfigSection {
        public double maxHealth;
        public double movementSpeed;
        public double angryMovementSpeed;
        public double knockbackResist;
        public double attackDamage;
        public double armor;
        public double toughness;
        public int followRange;
        public int repairAmount;

        public baseConfig() { }

        public baseConfig(double health, double moveSpeed, double angryMoveSpeed,
                          double knockbackRes, double damage, double armor, double toughness,
                          int follow, int repair) {
            maxHealth = health;
            movementSpeed = moveSpeed;
            angryMovementSpeed = angryMoveSpeed;
            knockbackResist = knockbackRes;
            attackDamage = damage;
            this.armor = armor;
            this.toughness = toughness;
            followRange = follow;
            repairAmount = repair;
        }
    }
}
