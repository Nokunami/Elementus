package net.nokunami.elementus.common.capability;

import net.minecraft.nbt.CompoundTag;

public class TestArmorCap {
    private int charge;
    private final int MIN_CHARGE = 0;
    private final int MAX_CHARGE = 20;

    public int getCharge() {
        return charge;
    }

    public void addCharge(int charge) {
        this.charge = Math.min(this.charge + charge, MAX_CHARGE);
    }
    public void subCharge(int charge) {
        this.charge = Math.max(this.charge - charge, MIN_CHARGE);
    }

    public void copy(TestArmorCap cap) {
        charge = cap.charge;
    }

    public void save(CompoundTag tag) {
        tag.putInt("charge", charge);
    }
    public void load(CompoundTag tag) {
        charge = tag.getInt("charge");
    }
}
