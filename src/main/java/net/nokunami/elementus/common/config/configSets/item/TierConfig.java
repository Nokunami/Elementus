package net.nokunami.elementus.common.config.configSets.item;

import net.minecraftforge.common.ForgeConfigSpec;

import static net.nokunami.elementus.common.config.EConfig.PREFIX;

public abstract class TierConfig {
    public final ForgeConfigSpec.IntValue harvestLevel;
    public final ForgeConfigSpec.IntValue durability;
    public final ForgeConfigSpec.DoubleValue efficiency;
    public final ForgeConfigSpec.DoubleValue damage;
    public final ForgeConfigSpec.IntValue enchantability;

    public TierConfig(final ForgeConfigSpec.Builder builder, String path, String id, int defaultHarvestLevel, int defaultDurability, float defaultEfficiency, float defaultDamage, int defaultEnchatability) {
        harvestLevel = builder.translation(PREFIX + id + "tier.harvest_level")
                .defineInRange(path + "tier.harvest_level", defaultHarvestLevel, Integer.MIN_VALUE, Integer.MAX_VALUE);
        durability = builder.translation(PREFIX + id + "tier.durability")
                .defineInRange(path + "tier.durability", defaultDurability, Integer.MIN_VALUE, Integer.MAX_VALUE);
        efficiency = builder.translation(PREFIX + id + "tier.efficiency")
                .defineInRange(path + "tier.efficiency", defaultEfficiency, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
        damage = builder.translation(PREFIX + id + "tier.damage")
                .defineInRange(path + "tier.damage", defaultDamage, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
        enchantability = builder.translation(PREFIX + id + ".tier.enchantability")
                .defineInRange(path + "tier.enchantability", defaultEnchatability, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public void reload(ToolSetConfig config) {
    }

    public abstract int getHarvestLevel();
    public abstract int getDurability();
    public abstract double getEfficiency();
    public abstract double getDamage();
    public abstract int getEnchantability();
}
