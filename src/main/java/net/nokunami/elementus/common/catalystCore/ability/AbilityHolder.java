package net.nokunami.elementus.common.catalystCore.ability;

import net.minecraftforge.registries.RegistryObject;
import net.nokunami.elementus.common.catalystCore.ability.activeAbility.AbstractActiveAbility;

import java.util.function.Supplier;

public class AbilityHolder {
    Supplier<AbstractActiveAbility> ability;

    public AbilityHolder(RegistryObject<AbstractActiveAbility> ability) {
        this.ability = ability;
    }

    public Supplier<AbstractActiveAbility> getAbility() {
        return ability;
    }

    public boolean isEmpty() {
        return ability == null;
    }
}
