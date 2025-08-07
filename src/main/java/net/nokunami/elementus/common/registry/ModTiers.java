package net.nokunami.elementus.common.registry;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.nokunami.elementus.common.Etags;
import net.nokunami.elementus.common.config.TierConfig;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public enum ModTiers implements Tier {
    STEEL(TierConfig.steelTierHarvestLevel, TierConfig.steelTierDurability, (float) TierConfig.steelTierEfficiency,
            (float) TierConfig.steelTierDamage, TierConfig.steelTierEnchantability,
            () -> Ingredient.of(Etags.Items.REPAIRS_STEEL_EQUIPMENT)),
    DIARKRITE(TierConfig.diarkriteTierHarvestLevel, TierConfig.diarkriteTierDurability, (float) TierConfig.diarkriteTierEfficiency,
            (float) TierConfig.diarkriteTierDamage, TierConfig.diarkriteTierEnchantability,
            () -> Ingredient.of(Etags.Items.REPAIRS_DIARKRITE_EQUIPMENT)),
    ANTHEKTITE(TierConfig.anthektiteTierHarvestLevel, TierConfig.anthektiteTierDurability, (float) TierConfig.anthektiteTierEfficiency,
            (float) TierConfig.anthektiteTierDamage, TierConfig.anthektiteTierEnchantability,
            () -> Ingredient.of(Etags.Items.REPAIRS_ANTHEKTITE_EQUIPMENT)),
    MOVCADIA(TierConfig.movcadiaTierHarvestLevel, TierConfig.movcadiaTierDurability, (float) TierConfig.movcadiaTierEfficiency,
            (float) TierConfig.movcadiaTierDamage, TierConfig.movcadiaTierEnchantability,
            () -> Ingredient.of(Etags.Items.REPAIRS_MOVCADIA_EQUIPMENT));

    private final int harvestLevel;
    private final int durability;
    private final float toolEfficiency;
    private final float attackDamage;
    private final int enchantValue;
    private final Supplier<Ingredient> repaireItem;

    ModTiers(int harvestLevel, int durability, float toolEfficiency, float attackDamage, int enchantValue, Supplier<Ingredient> repaireItem) {
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

    public @NotNull Ingredient getRepairIngredient() {
        return repaireItem.get();
    }

}
