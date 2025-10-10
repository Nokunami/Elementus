package net.nokunami.elementus.common.catalystCore;

import net.minecraft.ChatFormatting;
import net.nokunami.elementus.common.catalystCore.ability.CatalystAbility;

import java.util.function.Supplier;

public class CoreAttributes {
    CatalystAbility ability;
    boolean changeTexture;
    ChatFormatting tooltipColor;
    int descNum;

    public CoreAttributes(Builder builder) {
        ability = builder.ability;
        changeTexture = builder.changeTexture;
        tooltipColor = builder.tooltipColor;
        descNum = builder.descNum;
    }

    public CatalystAbility getAbility() {
        return ability;
    }

    public ChatFormatting getTooltipColor() {
        return tooltipColor;
    }

    public int getDescriptionNumber() {
        return descNum;
    }

    public static class Builder {
        private CatalystAbility ability;
        private boolean changeTexture = false;
        private ChatFormatting tooltipColor;
        private int descNum;

        public Builder ability(CatalystAbility ability) {
            this.ability = ability;
            return this;
        }

        public Builder ability(Supplier<CatalystAbility> ability) {
            this.ability = ability.get();
            return this;
        }

        public Builder changeTexture() {
            changeTexture = true;
            return this;
        }

        public Builder tooltipColor(ChatFormatting formatting) {
            tooltipColor = formatting;
            return this;
        }

        public Builder descriptionNumber(int number) {
            descNum = number;
            return this;
        }

        public CoreAttributes build() {
            return new CoreAttributes(this);
        }
    }
}
