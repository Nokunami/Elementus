package net.nokunami.elementus.common.catalystCore.ability;

/**
 * <p>"TRIGGERED" when activated it goes into cooldown.<p>
 * <p>"DELAYED_TRIGGER" only activates when key is released after the cast duration.<p>
 * <p>"TOGGLED" when activated it goes into cooldown if the key is pressed again or the timer is up.<p>
 * <p>"CONSTANT" is only active if the key is pressed down.<p>
 */
public enum AbilityType {
    PASSIVE, TRIGGERED, DELAYED_TRIGGER, TOGGLED, CONSTANT;

    public final boolean isTriggered() { return this == AbilityType.TRIGGERED; }
//    public final boolean isDelayedTrigger() { return this == AbilityType.DELAYED_TRIGGER; }
    public final boolean isToggled() { return this == AbilityType.TOGGLED; }
    public final boolean isConstant() { return this == AbilityType.CONSTANT; }
}
