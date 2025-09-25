package net.nokunami.elementus.common.item.catalystCore;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.nokunami.elementus.common.item.GolemUpgradeProperties;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class CatalystCoreAbility {
    public final CatalystCoreAbility ability;
    private final List<Pair<Supplier<MobEffectInstance>, Float>> effects;

    public CatalystCoreAbility(Builder builder) {
        this.ability = builder.build();
        effects = ability.effects;
    }

    public CatalystCoreAbility getInst() {
        return ability;
    }

    public void tickEffect(Level level, LivingEntity entity) {
        for (Pair<MobEffectInstance, Float> pair : ability.getEffects()) {
            if (!level.isClientSide && pair.getFirst() != null && level.random.nextFloat() < pair.getSecond()) {
                entity.addEffect(new MobEffectInstance(pair.getFirst()));
            }
        }
    }

    public List<Pair<MobEffectInstance, Float>> getEffects() {
        return this.effects.stream().map(pair -> Pair.of(pair.getFirst() != null ? pair.getFirst().get() : null, pair.getSecond())).collect(Collectors.toList());
    }

    public static class Builder {
        private final List<Pair<Supplier<MobEffectInstance>, Float>> effects = Lists.newArrayList();

        public Builder effect(Supplier<MobEffectInstance> effectIn) {
            this.effects.add(Pair.of(effectIn, 1F));
            return this;
        }

        public CatalystCoreAbility build() {
            return new CatalystCoreAbility(this);
        }
    }
}