package net.nokunami.elementus.common.capability;

import net.minecraft.nbt.CompoundTag;

public class CatalystExhaustionCap {
    private int exhaustionCap;
    private final int MIN_EXHAUSTION = 0;
    private final int MAX_EXHAUSTION = 50;

    public int getExhaustionCap() {
        return exhaustionCap;
    }

    public void addExhaustion(int charge) {
        exhaustionCap = Math.min(exhaustionCap + charge, MAX_EXHAUSTION);
    }
    public void subExhaustion(int charge) {
        exhaustionCap = Math.max(exhaustionCap - charge, MIN_EXHAUSTION);
    }

    public void copyExhaustion(CatalystExhaustionCap cap) {
        exhaustionCap = cap.exhaustionCap;
    }

    public void save(CompoundTag tag) {
        tag.putInt("catalystExhaustionCap", exhaustionCap);
    }
    public void load(CompoundTag tag) {
        exhaustionCap = tag.getInt("catalystExhaustion");
    }
}
