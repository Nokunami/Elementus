package net.nokunami.elementus.common.config;

import net.minecraftforge.common.ForgeConfigSpec;

import static net.nokunami.elementus.common.config.EConfig.PREFIX;

public class ToolConfig {
    public final ForgeConfigSpec.DoubleValue attackDamage;
    public final ForgeConfigSpec.DoubleValue attackSpeed;

    public ToolConfig(final ForgeConfigSpec.Builder builder, String id, double defaultDamage, double defaultSpeed) {
        attackDamage = builder.translation(PREFIX + id + ".attack_damage")
                .defineInRange(id + ".attack_damage", defaultDamage, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
        attackSpeed = builder.translation(PREFIX + id + ".attack_speed")
                .defineInRange(id + ".attack_speed", defaultSpeed, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
    }
}
