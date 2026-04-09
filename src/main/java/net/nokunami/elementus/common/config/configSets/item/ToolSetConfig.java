package net.nokunami.elementus.common.config.configSets.item;

import net.minecraftforge.common.ForgeConfigSpec;
import net.nokunami.elementus.common.config.ToolConfig;

import static net.nokunami.elementus.common.config.EConfig.PREFIX;

public class ToolSetConfig {
    public final ToolConfig sword;
    public final ToolConfig shovel;
    public final ToolConfig pickaxe;
    public final ToolConfig axe;
    public final ToolConfig hoe;
    public final ForgeConfigSpec.DoubleValue materialSpeed;
    public final TierConfig tier;

    String configId;

    public ToolSetConfig(final ForgeConfigSpec.Builder builder, boolean shouldPop, String id, TierConfig tierConfig, double swordDamage, double swordSpeed, double shovelDamage, double shovelSpeed, double pickaxeDamage, double pickaxeSpeed, double axeDamage, double axeSpeed, double hoeDamage, double hoeSpeed, double defaultSpeed) {
        configId = id;
        if (shouldPop) builder.push(id);
        sword = new ToolConfig(builder, "sword", swordDamage, swordSpeed);
        shovel = new ToolConfig(builder, "shovel", shovelDamage, shovelSpeed);
        pickaxe = new ToolConfig(builder, "pickaxe", pickaxeDamage, pickaxeSpeed);
        axe = new ToolConfig(builder, "axe", axeDamage, axeSpeed);
        hoe = new ToolConfig(builder, "hoe", hoeDamage, hoeSpeed);
        materialSpeed = builder.translation(PREFIX + id + "_material_speed")
                .comment("Only applies to weapons (swords & axes).")
                .defineInRange(id + "_material_speed", defaultSpeed, Double.NEGATIVE_INFINITY, Double.MAX_VALUE);
        tier = tierConfig;
        if (shouldPop) builder.pop();
    }
}
