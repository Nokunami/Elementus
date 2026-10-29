package net.nokunami.elementus.api.util;

import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITagManager;

import javax.annotation.Nullable;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class EffectUtil {
    Entity entity;
    MobEffect effect;
    Predicate<EffectUtil> condition = effectUtil -> true;

    public static EffectUtil effectUtil(Entity entity, Supplier<MobEffect> effect) {
        return new EffectUtil(entity, effect.get());
    }

    public static EffectUtil effectUtil(Entity entity, MobEffect effect) { return new EffectUtil(entity, effect); }

    public EffectUtil(Entity entity, MobEffect mobEffect) {
        this.entity = entity;
        this.effect = mobEffect;
    }

    public LivingEntity getEntity() { return (LivingEntity) entity; }
    public MobEffectInstance getEffectInst() { return getEntity().getEffect(effect); }

    public EffectUtil condition(Predicate<EffectUtil> predicate) { condition = predicate; return this; }
    public EffectUtil refreshCondition() {
        condition = effectUtil -> !effectUtil.getEntity().hasEffect(effect);
        return this;
    }

    public void add() { add(0, 0); }

    public void add(int duration, int level) { add(duration, level, false, true); }

    public void add(int duration, int level, boolean ambient, boolean visible) {
        add(duration, level, ambient, visible, visible);
    }

    public void add(int duration, int level, boolean ambient, boolean visible, boolean showIcon) {
        if (getEntity() != null && condition.test(this)) {
            getEntity().addEffect(new MobEffectInstance(effect, duration, level, ambient, visible, showIcon));
        }
    }

    public void increment() { increment(-1, -1); }

    public void increment(int min, int max) {
        if (getEntity() != null) {
            var inst = getEntity().getEffect(effect);
            if (inst != null) {
                int i = inst.getAmplifier();
                i = Mth.clamp(i + 1, Math.max(min, 0), max < 0 ? Integer.MAX_VALUE : max);
                MobEffectInstance effectInst = new MobEffectInstance(inst.getEffect(), inst.getDuration(), i, inst.isAmbient(), inst.isVisible(), inst.showIcon());
                getEntity().removeEffect(effect);
                getEntity().addEffect(effectInst);
            }
        }
    }

    public void decrement() { decrement(-1, -1); }

    public void decrement(int min, int max) {
        if (getEntity() != null) {
            var inst = getEntity().getEffect(effect);
            if (inst != null) {
                int i = inst.getAmplifier();
                i = Mth.clamp(i - 1, Math.max(min, 0), max < 0 ? Integer.MAX_VALUE : max);
                MobEffectInstance effectInst = new MobEffectInstance(inst.getEffect(), inst.getDuration(), i, inst.isAmbient(), inst.isVisible(), inst.showIcon());
                getEntity().removeEffect(effect);
                getEntity().addEffect(effectInst);
            }
        }
    }

    public void clear() {
        if (getEntity() != null && getEntity().getEffect(effect) != null)
            getEntity().removeEffect(effect);
    }

    public int getAmp() {
        if (getEntity() != null) {
            var inst = getEntity().getEffect(effect);
            if (inst != null) return inst.getAmplifier();
        }
        return 0;
    }

    public static boolean is(MobEffectInstance effect, TagKey<MobEffect> tagKey) {
        return is(effect.getEffect(), tagKey);
    }
    public static boolean is(MobEffect effect, TagKey<MobEffect> tagKey) {
        ITagManager<MobEffect> tags = ForgeRegistries.MOB_EFFECTS.tags();
        if (tags == null) return false;
        return tags.getTag(tagKey).contains(effect);
    }
}