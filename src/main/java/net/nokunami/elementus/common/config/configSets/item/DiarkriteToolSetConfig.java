package net.nokunami.elementus.common.config.configSets.item;

import net.minecraftforge.common.ForgeConfigSpec;

import static net.nokunami.elementus.common.config.EConfig.PREFIX;

public class DiarkriteToolSetConfig extends ToolSetConfig {
    public final ForgeConfigSpec.DoubleValue bonusEfficiency;

    public DiarkriteToolSetConfig(final ForgeConfigSpec.Builder builder, TierConfig tierConfig) {
        super(builder, false, "diarkrite", tierConfig, 3, -2.4, 1.5, -3, 1, -2.8, 6, -3.1, -6, 0, -0.3);
        bonusEfficiency = builder.translation(PREFIX + configId + "_bonus_efficiency")
                .comment("Bonus efficiency for mining blocks at diamond tier or above, set it to 0 to disable.")
                .comment("Formula: (base + enchant/etc) * bonus.")
                .defineInRange(configId + "_bonus_efficiency", 10, 1, Double.MAX_VALUE);
    }
}
