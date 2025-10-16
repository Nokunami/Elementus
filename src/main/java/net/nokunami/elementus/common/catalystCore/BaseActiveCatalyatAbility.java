package net.nokunami.elementus.common.catalystCore;

public class BaseActiveCatalyatAbility {
    private AbilityType type;

    public BaseActiveCatalyatAbility(AbilityType type) {
        this.type = type;
    }

    boolean isActive() {
        return false;
    }

    public void performAbility() {
    }

    public enum AbilityType {
        SINGLE_USE, TOGGLED, DURATION
    }
}
