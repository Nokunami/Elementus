package net.nokunami.elementus.common.entity.living;

public enum GolemPose {
    SPAWN,
    IDLE,
    SIT,
    UNSIT,
    DAMAGED,
    REPAIRED;

    public boolean shouldResetToIdle() {
        return this == UNSIT || this == REPAIRED;
    }
}
