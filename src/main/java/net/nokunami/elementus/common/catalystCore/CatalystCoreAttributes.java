package net.nokunami.elementus.common.catalystCore;

import com.mojang.datafixers.util.Pair;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.registries.RegistryObject;
import net.nokunami.elementus.common.catalystCore.ability.AbilityHolder;
import net.nokunami.elementus.common.catalystCore.ability.PassiveCatalystAbility;
import net.nokunami.elementus.common.catalystCore.ability.activeAbility.AbstractActiveAbility;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class CatalystCoreAttributes {
    List<Pair<PassiveCatalystAbility, Float>> passiveAbility;
//    List<AbstractActiveAbility> activeAbilities;
    List<AbilityHolder> activeAbilities;

    public CatalystCoreAttributes(Builder builder) {
        passiveAbility = builder.passiveAbility;
        activeAbilities = builder.catalystAbilities1;
    }

//    public PassiveCatalystAbility getPassiveAbility() {
//        return passiveAbility;
//    }
    public List<Pair<PassiveCatalystAbility, Float>> getPassiveAbility() {
        return this.passiveAbility.stream().map(pair -> Pair.of(pair.getFirst() != null ? pair.getFirst() : null, pair.getSecond())).collect(Collectors.toList());
    }

//    public List<AbstractActiveAbility> getActiveAbilities() {
//        return activeAbilities;
//    }
    public List<AbilityHolder> getActiveAbilities() {
        return activeAbilities;
    }

    public static class Builder {
        private final List<Pair<PassiveCatalystAbility, Float>> passiveAbility = new ArrayList<>();
//        List<AbstractActiveAbility> catalystAbilities = new ArrayList<>();
        List<AbilityHolder> catalystAbilities1 = new ArrayList<>();

        public Builder passiveAbility(PassiveCatalystAbility ability) {
            passiveAbility.add(Pair.of(ability, 1F));
            return this;
        }
        public Builder passiveAbility(Supplier<PassiveCatalystAbility> ability) {
            passiveAbility.add(Pair.of(ability.get(), 1F));
            return this;
        }

//        public Builder activeAbilities(AbilityHolder holder) { return activeAbilities(holder.getAbility()); }
//        public Builder activeAbilities(Supplier<AbstractActiveAbility> ability) { return activeAbilities(ability.get()); }
//        public Builder activeAbilities(AbilityHolder holder) { return activeAbilities(holder.getAbility().get()); }
//        public Builder activeAbilities(AbstractActiveAbility ability) {
//            catalystAbilities.add(ability);
//            return this;
//        }
        public Builder activeAbility(AbilityHolder holder) {
            catalystAbilities1.add(holder);
            return this;
        }

        public CatalystCoreAttributes build() {
            return new CatalystCoreAttributes(this);
        }
    }
}
