package net.nokunami.elementus.common.item.unique;

import javax.annotation.Nullable;

public class ChargeBladeAttributes {
    private double damage = 0;
    private boolean overrideDamage = false;
    private int chargeMultiplier;
    private double chargeDamageMultiplier = 0;
    private String color = null;

    public double damage() { return damage; }
    public boolean override() { return overrideDamage; }
    public int chargeMultiplier() { return chargeMultiplier; }
    public double chargeDamageMultiplier() { return chargeDamageMultiplier; }
    public @Nullable String color() { return color; }

    public ChargeBladeAttributes setDamage(double damage) {
        this.damage = damage;
        return this;
    }
    public ChargeBladeAttributes overrideDamage(double damage) {
        this.damage = damage;
        overrideDamage = true;
        return this;
    }
    public ChargeBladeAttributes chargeMultiplier(int multiplier) {
        chargeMultiplier = multiplier;
        return this;
    }
    public ChargeBladeAttributes chargeDamageMultiplier(double multiplier) {
        chargeDamageMultiplier = multiplier;
        return this;
    }
    public ChargeBladeAttributes color(String s) {
        color = s;
        return this;
    }
}
