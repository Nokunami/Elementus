package net.nokunami.elementus.common.config.configSets.item;

import net.minecraftforge.common.ForgeConfigSpec;

public class DiarkriteTierConfig extends TierConfig {
    public static int HarvestLevel = 4;
    public static int Durability = 2546;
    public static double Efficiency = 7;
    public static double Damage = 6;
    public static int Enchantability = 10;

    public DiarkriteTierConfig(final ForgeConfigSpec.Builder builder) {
        super(builder, "", "diarkrite", HarvestLevel, Durability, (float) Efficiency, (float) Damage, Enchantability);
    }

    @Override
    public void reload(ToolSetConfig config) {
        HarvestLevel = config.tier.harvestLevel.get();
        Durability = config.tier.durability.get();
        Efficiency = config.tier.efficiency.get();
        Damage = config.tier.damage.get();
        Enchantability = config.tier.enchantability.get();
    }

    @Override public int getHarvestLevel() { return HarvestLevel; }
    @Override public int getDurability() { return Durability; }
    @Override public double getEfficiency() { return Efficiency; }
    @Override public double getDamage() { return Damage; }
    @Override public int getEnchantability() { return Enchantability; }
}
