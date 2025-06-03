package net.nokunami.elementus.common.item;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class GolemUpgradeProperties {
    private final int armor;
    private final double toughness;
    private final boolean isNotPushable;
    private final boolean isFastAttack;
    private final List<Pair<Supplier<MobEffectInstance>, Float>> effects;
    private final List<Pair<Supplier<AttributeModifier>, Float>> attributes;

    public GolemUpgradeProperties(GolemUpgradeProperties.Builder builder) {
        this.armor = builder.armor;
        this.toughness = builder.toughness;
        this.isNotPushable = builder.pushable;
        this.isFastAttack = builder.fastAttack;
        this.effects = builder.effects;
        this.attributes = builder.attributes;
    }

    public int getArmor() {
        return this.armor;
    }

    public double getToughness() {
        return this.toughness;
    }

    public boolean isNotPushable() {
        return this.isNotPushable;
    }

    public boolean isFastAttack() {
        return this.isFastAttack;
    }

    public List<Pair<MobEffectInstance, Float>> getEffects() {
        return this.effects.stream().map(pair -> Pair.of(pair.getFirst() != null ? pair.getFirst().get() : null, pair.getSecond())).collect(Collectors.toList());
    }

    public List<Pair<AttributeModifier, Float>> getAttributes() {
        return this.attributes.stream().map(pair -> Pair.of(pair.getFirst() != null ? pair.getFirst().get() : null, pair.getSecond())).collect(Collectors.toList());
    }

    public static class Builder {
        private int armor;
        private double toughness;
        private boolean pushable;
        private boolean fastAttack;
        private final List<Pair<Supplier<MobEffectInstance>, Float>> effects = Lists.newArrayList();
        private final List<Pair<Supplier<AttributeModifier>, Float>> attributes = Lists.newArrayList();

        public GolemUpgradeProperties.Builder armor(int amount) {
            this.armor = amount;
            return this;
        }

        public GolemUpgradeProperties.Builder toughness(double amount) {
            this.toughness = amount;
            return this;
        }

        public GolemUpgradeProperties.Builder isNotPushable() {
            this.pushable = true;
            return this;
        }

        public GolemUpgradeProperties.Builder isFastAttack() {
            this.fastAttack = true;
            return this;
        }

        public GolemUpgradeProperties.Builder effect(Supplier<MobEffectInstance> effectIn) {
            this.effects.add(Pair.of(effectIn, 1F));
            return this;
        }

        public GolemUpgradeProperties.Builder attribute(Supplier<AttributeModifier> effectIn) {
            this.attributes.add(Pair.of(effectIn, 1F));
            return this;
        }

        public GolemUpgradeProperties build() {
            return new GolemUpgradeProperties(this);
        }
    }
}
