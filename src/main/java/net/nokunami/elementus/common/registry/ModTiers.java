package net.nokunami.elementus.common.registry;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.nokunami.elementus.common.Etags;
import net.nokunami.elementus.common.registry.ModItems.*;
import net.nokunami.elementus.common.config.TierConfig;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public enum ModTiers implements Tier {
    STEEL(TierConfig.steelTierHarvestLevel, TierConfig.steelTierDurability, TierConfig.steelTierEfficiency,
            TierConfig.steelTierDamage, TierConfig.steelTierEnchantability,
            () -> Ingredient.of(Etags.Items.REPAIRS_STEEL_EQUIPMENT)),
    DIARKRITE(TierConfig.diarkriteTierHarvestLevel, TierConfig.diarkriteTierDurability, TierConfig.diarkriteTierEfficiency,
            TierConfig.diarkriteTierDamage, TierConfig.diarkriteTierEnchantability,
            () -> Ingredient.of(Etags.Items.REPAIRS_DIARKRITE_EQUIPMENT)),
    ANTHEKTITE(TierConfig.anthektiteTierHarvestLevel, TierConfig.anthektiteTierDurability, TierConfig.anthektiteTierEfficiency,
            TierConfig.anthektiteTierDamage, TierConfig.anthektiteTierEnchantability,
            () -> Ingredient.of(Etags.Items.REPAIRS_ANTHEKTITE_EQUIPMENT)),

    DIARKRITE_IRON(TierConfig.diarkriteIronTierHarvestLevel, TierConfig.diarkriteIronTierDurability, TierConfig.diarkriteIronTierEfficiency,
            TierConfig.diarkriteIronTierDamage, TierConfig.diarkriteIronTierEnchantability,
            () -> Ingredient.of(AdvancedNetheriteItems.DIARKRITE_IRON.get())),
    DIARKRITE_GOLD(TierConfig.diarkriteGoldTierHarvestLevel, TierConfig.diarkriteGoldTierDurability, TierConfig.diarkriteGoldTierEfficiency,
            TierConfig.diarkriteGoldTierDamage, TierConfig.diarkriteGoldTierEnchantability,
            () -> Ingredient.of(AdvancedNetheriteItems.DIARKRITE_GOLD.get())),
    DIARKRITE_EMERALD(TierConfig.diarkriteEmeraldTierHarvestLevel, TierConfig.diarkriteEmeraldTierDurability, TierConfig.diarkriteEmeraldTierEfficiency,
            TierConfig.diarkriteEmeraldTierDamage, TierConfig.diarkriteEmeraldTierEnchantability,
            () -> Ingredient.of(AdvancedNetheriteItems.DIARKRITE_EMERALD.get())),
    DIARKRITE_DIAMOND(TierConfig.diarkriteDiamondTierHarvestLevel, TierConfig.diarkriteDiamondTierDurability, TierConfig.diarkriteDiamondTierEfficiency,
            TierConfig.diarkriteDiamondTierDamage, TierConfig.diarkriteDiamondTierEnchantability,
            () -> Ingredient.of(AdvancedNetheriteItems.DIARKRITE_DIAMOND.get())),

    ANTHEKTITE_IRON(TierConfig.anthektiteIronTierHarvestLevel, TierConfig.anthektiteIronTierDurability, TierConfig.anthektiteIronTierEfficiency,
            TierConfig.anthektiteIronTierDamage, TierConfig.anthektiteIronTierEnchantability,
            () -> Ingredient.of(AdvancedNetheriteItems.ANTHEKTITE_IRON.get())),
    ANTHEKTITE_GOLD(TierConfig.anthektiteGoldTierHarvestLevel, TierConfig.anthektiteGoldTierDurability, TierConfig.anthektiteGoldTierEfficiency,
            TierConfig.anthektiteGoldTierDamage, TierConfig.anthektiteGoldTierEnchantability,
            () -> Ingredient.of(AdvancedNetheriteItems.ANTHEKTITE_GOLD.get())),
    ANTHEKTITE_EMERALD(TierConfig.anthektiteEmeraldTierHarvestLevel, TierConfig.anthektiteEmeraldTierDurability, TierConfig.anthektiteEmeraldTierEfficiency,
            TierConfig.anthektiteEmeraldTierDamage, TierConfig.anthektiteEmeraldTierEnchantability,
            () -> Ingredient.of(AdvancedNetheriteItems.ANTHEKTITE_EMERALD.get())),
    ANTHEKTITE_DIAMOND(TierConfig.anthektiteDiamondTierHarvestLevel, TierConfig.anthektiteDiamondTierDurability, TierConfig.anthektiteDiamondTierEfficiency,
            TierConfig.anthektiteDiamondTierDamage, TierConfig.anthektiteDiamondTierEnchantability,
            () -> Ingredient.of(AdvancedNetheriteItems.ANTHEKTITE_DIAMOND.get())),

    STEEL_CMD(TierConfig.steelCMDTierHarvestLevel, TierConfig.steelCMDTierDurability, TierConfig.steelCMDTierEfficiency,
            TierConfig.steelCMDTierDamage, TierConfig.steelCMDTierEnchantability,
            () -> Ingredient.of(ElementusItems.STEEL_INGOT.get())),
    DIARKRITE_CMD(TierConfig.diarkriteCMDTierHarvestLevel, TierConfig.diarkriteCMDTierDurability, TierConfig.diarkriteCMDTierEfficiency,
            TierConfig.diarkriteCMDTierDamage, TierConfig.diarkriteCMDTierEnchantability,
            () -> Ingredient.of(ElementusItems.DIARKRITE_INGOT.get())),
    ANTHEKTITE_CMD(TierConfig.anthektiteCMDTierHarvestLevel, TierConfig.anthektiteCMDTierDurability, TierConfig.anthektiteCMDTierEfficiency,
            TierConfig.anthektiteCMDTierDamage, TierConfig.anthektiteCMDTierEnchantability,
            () -> Ingredient.of(ElementusItems.ANTHEKTITE_INGOT.get())),

    STEEL_CLAW(TierConfig.steelTierHarvestLevel, TierConfig.steelClawTierDurability, TierConfig.steelClawTierEfficiency,
            TierConfig.steelClawTierDamage, TierConfig.steelClawTierEnchantability,
            () -> Ingredient.of(Etags.Items.REPAIRS_STEEL_EQUIPMENT)),
    DIARKRITE_CLAW(TierConfig.diarkriteClawTierHarvestLevel, TierConfig.diarkriteClawTierDurability, TierConfig.diarkriteClawTierEfficiency,
            TierConfig.diarkriteClawTierDamage, TierConfig.diarkriteClawTierEnchantability,
            () -> Ingredient.of(Etags.Items.REPAIRS_DIARKRITE_EQUIPMENT)),
    ANTHEKTITE_CLAW(TierConfig.anthektiteClawTierHarvestLevel, TierConfig.anthektiteClawTierDurability, TierConfig.anthektiteClawTierEfficiency,
            TierConfig.anthektiteClawTierDamage, TierConfig.anthektiteClawTierEnchantability,
            () -> Ingredient.of(Etags.Items.REPAIRS_ANTHEKTITE_EQUIPMENT));

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
