package net.nokunami.elementus.common.capability;

import net.minecraft.nbt.CompoundTag;

public class CatalystExhaustion {
    private int exhaustion;
    private int exhaustionPenalty;
    private final int MIN_EXHAUSTION = 0;
    private final int MAX_EXHAUSTION = 50;

    public int getExhaustion() {
        return exhaustion + exhaustionPenalty;
    }

    public void addExhaustion(int charge) {
        exhaustion = Math.min(exhaustion + charge, MAX_EXHAUSTION);
    }
    public void subExhaustion(int charge) {
        exhaustion = Math.max(exhaustion - charge, MIN_EXHAUSTION);
    }

    public void addExhaustionPenalty(int charge) {
        exhaustionPenalty = Math.min(charge, MAX_EXHAUSTION);
    }
    public void subExhaustionPenalty(int charge) {
        exhaustionPenalty = Math.max(charge, MIN_EXHAUSTION);
    }

    public void copyExhaustion(CatalystExhaustion cap) {
        exhaustion = cap.exhaustion;
        exhaustionPenalty = cap.exhaustionPenalty;
    }

    public boolean isExhausted() {
        return exhaustion + exhaustionPenalty >= 50;
    }

    public void save(CompoundTag tag) {
        tag.putInt("catalystExhaustion", exhaustion);
        tag.putInt("catalystExhaustionPenalty", exhaustionPenalty);
    }
    public void load(CompoundTag tag) {
        exhaustion = tag.getInt("catalystExhaustion");
        exhaustionPenalty = tag.getInt("catalystExhaustionPenalty");
    }
}
