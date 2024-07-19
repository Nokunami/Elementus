package net.nokunami.elementus.common.registry;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.nokunami.elementus.common.compat.advancednetherite.ANModItems;
import net.nokunami.elementus.common.config.TierConfig;
import net.nokunami.elementus.common.config.TierConfig;

import java.util.function.Supplier;

public enum ModTiers implements Tier {
    STEEL(TierConfig.steelTierHarvestLevel, TierConfig.steelTierDurability, TierConfig.steelTierEfficiency,
            TierConfig.steelTierDamage, TierConfig.steelTierEnchantability,
            () -> Ingredient.of(ModItems.STEEL_INGOT.get())),
    DIARKRITE(TierConfig.diarkriteTierHarvestLevel, TierConfig.diarkriteTierDurability, TierConfig.diarkriteTierEfficiency,
            TierConfig.diarkriteTierDamage, TierConfig.diarkriteTierEnchantability,
            () -> Ingredient.of(ModItems.DIARKRITE_INGOT.get())),
    ANTHEKTITE(TierConfig.anthektiteTierHarvestLevel, TierConfig.anthektiteTierDurability, TierConfig.anthektiteTierEfficiency,
            TierConfig.anthektiteTierDamage, TierConfig.anthektiteTierEnchantability,
            () -> Ingredient.of(ModItems.ANTHEKTITE_INGOT.get())),

    DIARKRITE_IRON(TierConfig.diarkriteIronTierHarvestLevel, TierConfig.diarkriteIronTierDurability, TierConfig.diarkriteIronTierEfficiency,
            TierConfig.diarkriteIronTierDamage, TierConfig.diarkriteIronTierEnchantability,
            () -> Ingredient.of(ANModItems.DIARKRITE_IRON.get())),
    DIARKRITE_GOLD(TierConfig.diarkriteGoldTierHarvestLevel, TierConfig.diarkriteGoldTierDurability, TierConfig.diarkriteGoldTierEfficiency,
            TierConfig.diarkriteGoldTierDamage, TierConfig.diarkriteGoldTierEnchantability,
            () -> Ingredient.of(ANModItems.DIARKRITE_GOLD.get())),
    DIARKRITE_EMERALD(TierConfig.diarkriteEmeraldTierHarvestLevel, TierConfig.diarkriteEmeraldTierDurability, TierConfig.diarkriteEmeraldTierEfficiency,
            TierConfig.diarkriteEmeraldTierDamage, TierConfig.diarkriteEmeraldTierEnchantability,
            () -> Ingredient.of(ANModItems.DIARKRITE_EMERALD.get())),
    DIARKRITE_DIAMOND(TierConfig.diarkriteDiamondTierHarvestLevel, TierConfig.diarkriteDiamondTierDurability, TierConfig.diarkriteDiamondTierEfficiency,
            TierConfig.diarkriteDiamondTierDamage, TierConfig.diarkriteDiamondTierEnchantability,
            () -> Ingredient.of(ANModItems.DIARKRITE_DIAMOND.get())),

    ANTHEKTITE_IRON(TierConfig.anthektiteIronTierHarvestLevel, TierConfig.anthektiteIronTierDurability, TierConfig.anthektiteIronTierEfficiency,
            TierConfig.anthektiteIronTierDamage, TierConfig.anthektiteIronTierEnchantability,
            () -> Ingredient.of(ANModItems.ANTHEKTITE_IRON.get())),
    ANTHEKTITE_GOLD(TierConfig.anthektiteGoldTierHarvestLevel, TierConfig.anthektiteGoldTierDurability, TierConfig.anthektiteGoldTierEfficiency,
            TierConfig.anthektiteGoldTierDamage, TierConfig.anthektiteGoldTierEnchantability,
            () -> Ingredient.of(ANModItems.ANTHEKTITE_GOLD.get())),
    ANTHEKTITE_EMERALD(TierConfig.anthektiteEmeraldTierHarvestLevel, TierConfig.anthektiteEmeraldTierDurability, TierConfig.anthektiteEmeraldTierEfficiency,
            TierConfig.anthektiteEmeraldTierDamage, TierConfig.anthektiteEmeraldTierEnchantability,
            () -> Ingredient.of(ANModItems.ANTHEKTITE_EMERALD.get())),
    ANTHEKTITE_DIAMOND(TierConfig.anthektiteDiamondTierHarvestLevel, TierConfig.anthektiteDiamondTierDurability, TierConfig.anthektiteDiamondTierEfficiency,
            TierConfig.anthektiteDiamondTierDamage, TierConfig.anthektiteDiamondTierEnchantability,
            () -> Ingredient.of(ANModItems.ANTHEKTITE_DIAMOND.get()));

    private final int harvestLevel;
    private final int durability;
    private final float toolEfficiency;
    private final float attackDamage;
    private final int enchantValue;
    private final Supplier<Ingredient> repaireItem;

    ModTiers(int harvestLevel, int durability, float toolEfficiency, float attackDamage, int enchantValue, Supplier repaireItem) {
        this.harvestLevel = harvestLevel;
        this.durability = durability;
        this.toolEfficiency = toolEfficiency;
        this.attackDamage = attackDamage;
        this.enchantValue = enchantValue;
        this.repaireItem = repaireItem;
    }

    @Override
    public int getLevel() {
        return harvestLevel;
    }

    @Override
    public int getUses() {
        return durability;
    }

    @Override
    public float getSpeed() {
        return toolEfficiency;
    }

    @Override
    public float getAttackDamageBonus() {
        return attackDamage;
    }

    @Override
    public int getEnchantmentValue() {
        return enchantValue;
    }

    public Ingredient getRepairIngredient() {
        return repaireItem.get();
    }

}
