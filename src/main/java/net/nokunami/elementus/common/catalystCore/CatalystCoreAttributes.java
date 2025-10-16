package net.nokunami.elementus.common.catalystCore;

import com.mojang.datafixers.util.Pair;
import org.apache.commons.compress.utils.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class CatalystCoreAttributes {
    List<Pair<PassiveCatalystAbility, Float>> passiveAbility;
    List<AbstractActiveAbility> catalystAbilities;

    public CatalystCoreAttributes(Builder builder) {
        passiveAbility = builder.passiveAbility;
        catalystAbilities = builder.catalystAbilities;
    }

//    public PassiveCatalystAbility getPassiveAbility() {
//        return passiveAbility;
//    }
    public List<Pair<PassiveCatalystAbility, Float>> getPassiveAbility() {
        return this.passiveAbility.stream().map(pair -> Pair.of(pair.getFirst() != null ? pair.getFirst() : null, pair.getSecond())).collect(Collectors.toList());
    }

    public List<AbstractActiveAbility> getCatalystAbilities() {
        return catalystAbilities;
    }

    public static class Builder {
        private final List<Pair<PassiveCatalystAbility, Float>> passiveAbility = Lists.newArrayList();
        List<AbstractActiveAbility> catalystAbilities = new ArrayList<>();

        public Builder passiveAbility(PassiveCatalystAbility ability) {
            passiveAbility.add(Pair.of(ability, 1F));
            return this;
        }
        public Builder passiveAbility(Supplier<PassiveCatalystAbility> ability) {
            passiveAbility.add(Pair.of(ability.get(), 1F));
            return this;
        }

        public Builder catalystAbilities(Supplier<AbstractActiveAbility> ability) {
            return catalystAbilities(ability.get());
        }
        public Builder catalystAbilities(AbstractActiveAbility ability) {
            catalystAbilities.add(ability);
            return this;
        }

        public CatalystCoreAttributes build() {
            return new CatalystCoreAttributes(this);
        }
    }
}
