package net.nokunami.elementus.common.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.mojang.datafixers.util.Pair;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraftforge.common.util.Lazy;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class GolemUpgradeProperties {
    private final int armor;
    private final double toughness;
    private final boolean isNotPushable;
    private final boolean isFastAttack;
    private final Lazy<Multimap<Attribute, AttributeModifier>> attributes;
    private final List<Pair<Supplier<MobEffectInstance>, Float>> effects;

    public GolemUpgradeProperties(GolemUpgradeProperties.Builder builder) {
        this.armor = builder.armor;
        this.toughness = builder.toughness;
        this.isNotPushable = builder.pushable;
        this.isFastAttack = builder.fastAttack;
        this.attributes = builder.attributes;
        this.effects = builder.effects;
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

    public Lazy<Multimap<Attribute, AttributeModifier>> getAttributes() {
        return attributes;
    }

    private static ImmutableMultimap.Builder<Attribute, AttributeModifier> emptyAttributes() {
        return ImmutableMultimap.builder();
    }

    public static class Builder {
        private int armor;
        private double toughness;
        private boolean pushable;
        private boolean fastAttack;
        private Lazy<Multimap<Attribute, AttributeModifier>> attributes = Lazy.of(emptyAttributes()::build);
        private final List<Pair<Supplier<MobEffectInstance>, Float>> effects = Lists.newArrayList();

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

        public Builder armorAttributes(Supplier<ImmutableMultimap.Builder<Attribute, AttributeModifier>> attributes) {
            return armorAttributes(attributes.get());
        }
        public Builder armorAttributes(ImmutableMultimap.Builder<Attribute, AttributeModifier> attributes) {
            this.attributes = Lazy.of(attributes::build);
            return this;
        }

        public GolemUpgradeProperties.Builder effect(Supplier<MobEffectInstance> effectIn) {
            this.effects.add(Pair.of(effectIn, 1F));
            return this;
        }

        public GolemUpgradeProperties build() {
            return new GolemUpgradeProperties(this);
        }
    }
}
