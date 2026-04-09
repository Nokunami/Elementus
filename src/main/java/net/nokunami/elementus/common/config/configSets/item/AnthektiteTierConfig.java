package net.nokunami.elementus.common.config.configSets.item;

import net.minecraftforge.common.ForgeConfigSpec;

public class AnthektiteTierConfig extends TierConfig {
    public static int HarvestLevel = 4;
    public static int Durability = 1946;
    public static double Efficiency = 12;
    public static double Damage = 3;
    public static int Enchantability = 15;

    public AnthektiteTierConfig(final ForgeConfigSpec.Builder builder) {
        super(builder, "anthektite.", "anthektite", HarvestLevel, Durability, (float) Efficiency, (float) Damage, Enchantability);
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
