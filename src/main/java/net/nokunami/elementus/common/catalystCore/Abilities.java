package net.nokunami.elementus.common.catalystCore;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.mojang.datafixers.util.Pair;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraftforge.common.util.Lazy;
import net.nokunami.elementus.common.catalystCore.ability.CatalystAbility;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Abilities {
    private final List<Pair<Supplier<MobEffectInstance>, Float>> effects;
    private final List<Pair<Supplier<MobEffectInstance>, Float>> altEffects;
    private final boolean cheatDeath;
    private final List<Pair<Supplier<MobEffectInstance>, Float>> deathEffects;
    @Nullable
    private final Lazy<Multimap<Attribute, AttributeModifier>> attributes;

    public Abilities(Builder builder) {
        effects = builder.effects;
        altEffects = builder.altEffects;
        cheatDeath = builder.cheatDeath;
        deathEffects = builder.deathEffects;
        attributes = builder.attributes;
    }

    public List<Pair<Supplier<MobEffectInstance>, Float>> getEffects() {
        return effects.stream().map(pair -> Pair.of(pair.getFirst() != null ? pair.getFirst() : null, pair.getSecond())).collect(Collectors.toList());
    }

    public List<Pair<Supplier<MobEffectInstance>, Float>> getAltEffects() {
        return altEffects.stream().map(pair -> Pair.of(pair.getFirst() != null ? pair.getFirst() : null, pair.getSecond())).collect(Collectors.toList());
    }

    public List<Pair<Supplier<MobEffectInstance>, Float>> getDeathEffects() {
        return deathEffects.stream().map(pair -> Pair.of(pair.getFirst() != null ? pair.getFirst() : null, pair.getSecond())).collect(Collectors.toList());
    }

    public boolean cheatsDeath() {
        return cheatDeath;
    }

    public @Nullable Lazy<Multimap<Attribute, AttributeModifier>> getAttributes() {
        return attributes;
    }

    public static class Builder {
        private final List<Pair<Supplier<MobEffectInstance>, Float>> effects = Lists.newArrayList();
        private final List<Pair<Supplier<MobEffectInstance>, Float>> altEffects = Lists.newArrayList();
        private boolean cheatDeath = false;
        final private List<Pair<Supplier<MobEffectInstance>, Float>> deathEffects = Lists.newArrayList();
        private Lazy<Multimap<Attribute, AttributeModifier>> attributes = null;

        public Builder effect(Supplier<MobEffectInstance> effect) {
            return effect(effect, 1F);
        }

        public Builder effect(Supplier<MobEffectInstance> effect, float chance) {
            effects.add(Pair.of(effect, chance));
            return this;
        }

        public Builder altEffect(Supplier<MobEffectInstance> effect) {
            return altEffect(effect, 1F);
        }

        public Builder altEffect(Supplier<MobEffectInstance> effect, float chance) {
            altEffects.add(Pair.of(effect, chance));
            return this;
        }

        public Builder cheatDeath() {
            cheatDeath = true;
            return this;
        }

        public Builder cheatDeath(Supplier<MobEffectInstance> effect) {
            cheatDeath = true;
            deathEffects.add(Pair.of(effect, 1F));
            return this;
        }

        public Builder attributes(ImmutableMultimap.Builder<Attribute, AttributeModifier> builder) {
            attributes = Lazy.of(builder::build);
            return this;
        }

        public Builder attributes(Supplier<ImmutableMultimap.Builder<Attribute, AttributeModifier>> builder) {
            attributes = Lazy.of(builder.get()::build);
            return this;
        }

        public Abilities build() {
            return new Abilities(this);
        }
    }
}
