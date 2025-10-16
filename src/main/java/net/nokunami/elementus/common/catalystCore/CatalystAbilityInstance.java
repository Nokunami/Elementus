package net.nokunami.elementus.common.catalystCore;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.nokunami.elementus.common.capability.CatalystExhaustion;
import org.jetbrains.annotations.NotNull;

public class CatalystAbilityInstance implements Comparable<AbstractActiveAbility> {
    private final AbstractActiveAbility ability;
    private int exhaustion;
    private int strain;
//    private CatalystExhaustion catalystExhaustion;
    private boolean state;
    private int cooldown;

    public CatalystAbilityInstance(AbstractActiveAbility ability, int exhaustion, int strain, int cooldown) {
        this.ability = ability;
        this.exhaustion = exhaustion;
        this.strain = strain;
        this.cooldown = cooldown;
    }

    @Override
    public int compareTo(@NotNull AbstractActiveAbility o) {
        return 0;
    }

    public boolean getActiveState() {
        return state;
    }

    public void setActiveState(boolean state) {
        this.state = state;
    }

    public int getExhaustion() {
        return exhaustion;
    }

    public void setExhaustion(int exhaustion) {
        this.exhaustion = exhaustion;
    }

    public int getStrain() {
        return strain;
    }

    public void setStrain(int strain) {
        this.strain = strain;
    }

    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public boolean onCooldown() {
        return this.cooldown > 0;
    }


    public void applyAbilityTick() {
    }

    /**
     * <p>"TRIGGERED" when activated it goes into cooldown.<p>
     * <p>"TOGGLED" when activated it goes into cooldown if the key is pressed again or the timer is up.<p>
     * <p>"CONSTANT" is only active if the key is pressed.<p>
     */
    public enum AbilityType {
        TRIGGERED, TOGGLED, CONSTANT
    }

//    public static class FactorData {
//        public static final Codec<FactorData> CODEC = RecordCodecBuilder
//                .create((instance) -> instance.group(ExtraCodecs.NON_NEGATIVE_INT.fieldOf("padding_duration")
//                        .forGetter((data) -> data.paddingDuration), Codec.FLOAT.fieldOf("factor_start").orElse(0.0F)
//                        .forGetter((data) -> data.factorStart), Codec.FLOAT.fieldOf("factor_target").orElse(1.0F)
//                        .forGetter((data) -> data.factorTarget), Codec.FLOAT.fieldOf("factor_current").orElse(0.0F)
//                        .forGetter((data) -> data.factorCurrent), ExtraCodecs.NON_NEGATIVE_INT.fieldOf("ticks_active").orElse(0)
//                        .forGetter((data) -> data.ticksActive), Codec.FLOAT.fieldOf("factor_previous_frame").orElse(0.0F)
//                        .forGetter((data) -> data.factorPreviousFrame), Codec.BOOL.fieldOf("had_effect_last_tick").orElse(false)
//                        .forGetter((data) -> data.hadEffectLastTick)).apply(instance, FactorData::new));
//        private final int paddingDuration;
//        private float factorStart;
//        private float factorTarget;
//        private float factorCurrent;
//        private int ticksActive;
//        private float factorPreviousFrame;
//        private boolean hadEffectLastTick;
//
//        public FactorData(int p_216919_, float p_216920_, float p_216921_, float p_216922_, int p_216923_, float p_216924_, boolean p_216925_) {
//            this.paddingDuration = p_216919_;
//            this.factorStart = p_216920_;
//            this.factorTarget = p_216921_;
//            this.factorCurrent = p_216922_;
//            this.ticksActive = p_216923_;
//            this.factorPreviousFrame = p_216924_;
//            this.hadEffectLastTick = p_216925_;
//        }
//
//        public FactorData(int pPaddingDuration) {
//            this(pPaddingDuration, 0.0F, 1.0F, 0.0F, 0, 0.0F, false);
//        }
//
//        public void tick(MobEffectInstance pEffect) {
//            this.factorPreviousFrame = this.factorCurrent;
//            boolean flag = !pEffect.endsWithin(this.paddingDuration);
//            ++this.ticksActive;
//            if (this.hadEffectLastTick != flag) {
//                this.hadEffectLastTick = flag;
//                this.ticksActive = 0;
//                this.factorStart = this.factorCurrent;
//                this.factorTarget = flag ? 1.0F : 0.0F;
//            }
//
//            float f = Mth.clamp((float)this.ticksActive / (float)this.paddingDuration, 0.0F, 1.0F);
//            this.factorCurrent = Mth.lerp(f, this.factorStart, this.factorTarget);
//        }
//
//        public float getFactor(LivingEntity pEntity, float pPartialTick) {
//            if (pEntity.isRemoved()) {
//                this.factorPreviousFrame = this.factorCurrent;
//            }
//
//            return Mth.lerp(pPartialTick, this.factorPreviousFrame, this.factorCurrent);
//        }
//    }
}