package net.nokunami.elementus.common.item.unique;

public class ChargeBladeAbility {
    int cooldown;
    boolean stopUsingItem = false;

    public ChargeBladeAbility setCooldown(int cooldown) {
        this.cooldown = cooldown;
        return this;
    }

    public ChargeBladeAbility shouldStopUsingItem(boolean stopUsingItem) {
        this.stopUsingItem = stopUsingItem;
        return this;
    }

    public int getCooldown() { return cooldown; }
    public boolean stopUsingItem() { return stopUsingItem; }
}
