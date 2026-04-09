package net.nokunami.elementus.common.capability.cAbility;

public class CACooldown {
    private int cooldown;
    private int ticks;
    private int charge;
    private int chargeAmount;

    public CACooldown() { }

    public CACooldown(int cd, int tks, int ca) {
        cooldown = cd;
        ticks = tks;
        charge = 1;
        chargeAmount = ca;
    }

    public int getCooldown() { return cooldown; }
    public void setCooldown(int i) { cooldown = i; }

    public int getTicks() { return ticks; }
    public void setTicks(int i) { ticks = i; }

    public int getCharge() { return charge; }
    public void setCharge(int i) { charge = i; }
    public int getChargeAmount() { return chargeAmount; }
    public void setChargeAmount(int i) { chargeAmount = i; }

    public void decrement() { decrement(1); }
    public void decrement(int i) {
        setTicks(getTicks() - i);
        if (getTicks() <= 0) {
            if (getCharge() > 1) {
                setTicks(getCooldown());
                setCharge(getCharge() - 1);
            } else {
                setCharge(0);
            }
        }
    }

    public float getCooldownPercent() {
        return ticks == 0 ? 0 : ticks / (float) cooldown;
    }
}