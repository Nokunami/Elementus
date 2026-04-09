package net.nokunami.elementus.common.catalystCore.ability;

import java.util.Arrays;

public enum CastState {
    NOT(0), CAST_TRIGGER(1), CAST_START(2), CAST_STOP(3), CASTING(4);

    final int id;
    CastState(int id) { this.id = id; }

    public int getId() { return id; }
    public CastState getAsEnum(int i) {
        var v = Arrays.stream(values()).filter(c -> c.getId() == i).findAny();
        return v.orElse(NOT);
    }

    public boolean triggered() { return this == CAST_TRIGGER; }
    public boolean started() { return this == CAST_START; }
    public boolean stopped() { return this == CAST_STOP; }
    public boolean casting() { return this == CASTING; }
}
