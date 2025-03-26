package net.nokunami.elementus.common.registry;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.nokunami.elementus.common.Etags;
import net.nokunami.elementus.common.config.ANConfig;
import net.nokunami.elementus.common.config.TierConfig;
import net.nokunami.elementus.common.registry.ModItems.AdvancedNetheriteItems;
import net.nokunami.elementus.common.registry.ModItems.ElementusItems;
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

    DIARKRITE_IRON(ANConfig.diarkriteIronTierHarvestLevel, ANConfig.diarkriteIronTierDurability, ANConfig.diarkriteIronTierEfficiency,
            ANConfig.diarkriteIronTierDamage, ANConfig.diarkriteIronTierEnchantability,
            () -> Ingredient.of(AdvancedNetheriteItems.DIARKRITE_IRON.get())),
    DIARKRITE_GOLD(ANConfig.diarkriteGoldTierHarvestLevel, ANConfig.diarkriteGoldTierDurability, ANConfig.diarkriteGoldTierEfficiency,
            ANConfig.diarkriteGoldTierDamage, ANConfig.diarkriteGoldTierEnchantability,
            () -> Ingredient.of(AdvancedNetheriteItems.DIARKRITE_GOLD.get())),
    DIARKRITE_EMERALD(ANConfig.diarkriteEmeraldTierHarvestLevel, ANConfig.diarkriteEmeraldTierDurability, ANConfig.diarkriteEmeraldTierEfficiency,
            ANConfig.diarkriteEmeraldTierDamage, ANConfig.diarkriteEmeraldTierEnchantability,
            () -> Ingredient.of(AdvancedNetheriteItems.DIARKRITE_EMERALD.get())),
    DIARKRITE_DIAMOND(ANConfig.diarkriteDiamondTierHarvestLevel, ANConfig.diarkriteDiamondTierDurability, ANConfig.diarkriteDiamondTierEfficiency,
            ANConfig.diarkriteDiamondTierDamage, ANConfig.diarkriteDiamondTierEnchantability,
            () -> Ingredient.of(AdvancedNetheriteItems.DIARKRITE_DIAMOND.get())),

    ANTHEKTITE_IRON(ANConfig.anthektiteIronTierHarvestLevel, ANConfig.anthektiteIronTierDurability, ANConfig.anthektiteIronTierEfficiency,
            ANConfig.anthektiteIronTierDamage, ANConfig.anthektiteIronTierEnchantability,
            () -> Ingredient.of(AdvancedNetheriteItems.ANTHEKTITE_IRON.get())),
    ANTHEKTITE_GOLD(ANConfig.anthektiteGoldTierHarvestLevel, ANConfig.anthektiteGoldTierDurability, ANConfig.anthektiteGoldTierEfficiency,
            ANConfig.anthektiteGoldTierDamage, ANConfig.anthektiteGoldTierEnchantability,
            () -> Ingredient.of(AdvancedNetheriteItems.ANTHEKTITE_GOLD.get())),
    ANTHEKTITE_EMERALD(ANConfig.anthektiteEmeraldTierHarvestLevel, ANConfig.anthektiteEmeraldTierDurability, ANConfig.anthektiteEmeraldTierEfficiency,
            ANConfig.anthektiteEmeraldTierDamage, ANConfig.anthektiteEmeraldTierEnchantability,
            () -> Ingredient.of(AdvancedNetheriteItems.ANTHEKTITE_EMERALD.get())),
    ANTHEKTITE_DIAMOND(ANConfig.anthektiteDiamondTierHarvestLevel, ANConfig.anthektiteDiamondTierDurability, ANConfig.anthektiteDiamondTierEfficiency,
            ANConfig.anthektiteDiamondTierDamage, ANConfig.anthektiteDiamondTierEnchantability,
            () -> Ingredient.of(AdvancedNetheriteItems.ANTHEKTITE_DIAMOND.get())),

    STEEL_CMD(TierConfig.steelCMDTierHarvestLevel, TierConfig.steelCMDTierDurability, (float) TierConfig.steelCMDTierEfficiency,
            (float) TierConfig.steelCMDTierDamage, TierConfig.steelCMDTierEnchantability,
            () -> Ingredient.of(ElementusItems.STEEL_INGOT.get())),
    DIARKRITE_CMD(TierConfig.diarkriteCMDTierHarvestLevel, TierConfig.diarkriteCMDTierDurability, (float) TierConfig.diarkriteCMDTierEfficiency,
            (float) TierConfig.diarkriteCMDTierDamage, TierConfig.diarkriteCMDTierEnchantability,
            () -> Ingredient.of(ElementusItems.DIARKRITE_INGOT.get())),
    ANTHEKTITE_CMD(TierConfig.anthektiteCMDTierHarvestLevel, TierConfig.anthektiteCMDTierDurability, (float) TierConfig.anthektiteCMDTierEfficiency,
            (float) TierConfig.anthektiteCMDTierDamage, TierConfig.anthektiteCMDTierEnchantability,
            () -> Ingredient.of(ElementusItems.ANTHEKTITE_INGOT.get())),

    STEEL_CLAW(TierConfig.steelTierHarvestLevel, TierConfig.steelClawTierDurability, (float) TierConfig.steelClawTierEfficiency,
            (float) TierConfig.steelClawTierDamage, TierConfig.steelClawTierEnchantability,
            () -> Ingredient.of(Etags.Items.REPAIRS_STEEL_EQUIPMENT)),
    DIARKRITE_CLAW(TierConfig.diarkriteClawTierHarvestLevel, TierConfig.diarkriteClawTierDurability, (float) TierConfig.diarkriteClawTierEfficiency,
            (float) TierConfig.diarkriteClawTierDamage, TierConfig.diarkriteClawTierEnchantability,
            () -> Ingredient.of(Etags.Items.REPAIRS_DIARKRITE_EQUIPMENT)),
    ANTHEKTITE_CLAW(TierConfig.anthektiteClawTierHarvestLevel, TierConfig.anthektiteClawTierDurability, (float) TierConfig.anthektiteClawTierEfficiency,
            (float) TierConfig.anthektiteClawTierDamage, TierConfig.anthektiteClawTierEnchantability,
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
