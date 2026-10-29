package net.nokunami.elementus.common.effect;

public interface IElementusMobEffect {

    default int getBonusDigSpeed() { return 0; }
    default boolean hasDigSpeed() { return false; }
}
