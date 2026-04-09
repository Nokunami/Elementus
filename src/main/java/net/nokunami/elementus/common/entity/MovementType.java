package net.nokunami.elementus.common.entity;

public enum MovementType {
    STAND, WALK, RUN, JUMP;

    public boolean isRunning() {
        return this == RUN;
    }
}
