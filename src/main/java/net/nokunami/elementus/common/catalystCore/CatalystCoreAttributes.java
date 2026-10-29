package net.nokunami.elementus.common.catalystCore;

import com.mojang.datafixers.util.Pair;
import net.minecraftforge.registries.RegistryObject;
import net.nokunami.elementus.Elementus;
import net.nokunami.elementus.common.catalystCore.ability.PassiveCatalystAbility;
import net.nokunami.elementus.common.catalystCore.ability.AbstractAbility;
import net.nokunami.elementus.common.catalystCore.ability.PassiveAbility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class CatalystCoreAttributes {
    List<Supplier<? extends AbstractAbility>> passive;
    List<Supplier<? extends AbstractAbility>> active;

    public CatalystCoreAttributes(Builder builder) {
        passive = builder.passive;
        active = builder.active;
    }

    public List<PassiveAbility> getPassiveAbilities() {
        List<PassiveAbility> list = new ArrayList<>();
        passive.forEach(holder -> {
            if (holder.get() instanceof PassiveAbility passiveAbility)
                list.add(passiveAbility);
            else Elementus.LOGGER.warn("Attempted adding Active Ability ({}) into Passive Abilities", holder.get().getDescriptionId());
        });
        return list;
    }
    public List<AbstractAbility> getActiveAbilities() {
        List<AbstractAbility> list = new ArrayList<>();
        active.forEach(holder -> {
            if (!(holder.get() instanceof PassiveAbility))
                list.add(holder.get());
            else Elementus.LOGGER.warn("Attempted adding Passive Ability ({}) into Active Abilities", holder.get().getDescriptionId());
        });
        return list;
    }
    public List<AbstractAbility> getAllAbilities() {
        List<AbstractAbility> list = new ArrayList<>();
        list.addAll(getActiveAbilities());
        list.addAll(getPassiveAbilities());
        return list;
    }

    public static class Builder {
        List<Supplier<? extends AbstractAbility>> passive = new ArrayList<>();
        List<Supplier<? extends AbstractAbility>> active = new ArrayList<>();

        @SafeVarargs public final Builder passiveAbility(Supplier<? extends AbstractAbility>... holder) { Collections.addAll(passive, holder); return this; }
        @SafeVarargs public final Builder activeAbility(Supplier<? extends AbstractAbility>... holder) { Collections.addAll(active, holder); return this; }

        public CatalystCoreAttributes build() { return new CatalystCoreAttributes(this); }
    }
}
