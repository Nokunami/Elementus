package net.nokunami.elementus.common.registry;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;
import net.nokunami.elementus.common.config.EConfig;
import net.nokunami.elementus.common.config.configSets.item.TierConfig;
import net.nokunami.elementus.common.tags.EItemTags;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class ETier {

//    public static ForgeTier ASTALITE = newTier(EConfig.COMMON.ASTALITE.tier, BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(EItemTags.REPAIRS_ASTALITE_EQUIPMENT));
//    public static ForgeTier DIARKRITE = newTier(EConfig.COMMON.DIARKRITE.tier, BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(EItemTags.REPAIRS_DIARKRITE_EQUIPMENT));
//    public static ForgeTier ANTHEKTITE = newTier(EConfig.COMMON.ANTHEKTITE.tier, BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(EItemTags.REPAIRS_ANTHEKTITE_EQUIPMENT));
//    public static ForgeTier MOVCADIA = newTier(EConfig.COMMON.MOVCADIA.tier, BlockTags.NEEDS_IRON_TOOL, () -> Ingredient.of(EItemTags.REPAIRS_MOVCADIA_EQUIPMENT));
//
//    static ForgeTier newTier(TierConfig config, TagKey<Block> tagKey, Supplier<Ingredient> repairIngredient) {
//        return new ForgeTier(config.harvestLevel.get(),
//                config.durability.get(),
//                config.efficiency.get().floatValue(),
//                config.damage.get().floatValue(),
//                config.enchantability.get(),
//                tagKey,
//                repairIngredient
//        );
//    }
//
//    public static Tier getTierForTier(EnumTiers tiers) {
//        return switch (tiers) {
//            case ASTALITE_DUMMY -> ASTALITE;
//            case DIARKRITE_DUMMY -> DIARKRITE;
//            case ANTHEKTITE_DUMMY -> ANTHEKTITE;
//            case MOVCADIA_DUMMY -> MOVCADIA;
//        };
//    }

    public enum EnumTiers implements Tier {
        ASTALITE(EConfig.COMMON.ASTALITE.tier, BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(EItemTags.REPAIRS_ASTALITE_EQUIPMENT)),
        DIARKRITE(EConfig.COMMON.DIARKRITE.tier, BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(EItemTags.REPAIRS_DIARKRITE_EQUIPMENT)),
        ANTHEKTITE(EConfig.COMMON.ANTHEKTITE.tier, BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(EItemTags.REPAIRS_ANTHEKTITE_EQUIPMENT)),
        MOVCADIA(EConfig.COMMON.MOVCADIA.tier, BlockTags.NEEDS_IRON_TOOL, () -> Ingredient.of(EItemTags.REPAIRS_MOVCADIA_EQUIPMENT));

        private int harvestLevel;
        private int durability;
        private float toolEfficiency;
        private float attackDamage;
        private int enchantValue;
        private TagKey<Block> tagKey;
        private Supplier<Ingredient> repairItem;
        public TierConfig tierConfig;

    //    ETiers(int harvestLevel, int durability, float toolEfficiency, float attackDamage, int enchantValue, Supplier<Ingredient> ingredientSupplier) {
    //        this.harvestLevel = harvestLevel;
    //        this.durability = durability;
    //        this.toolEfficiency = toolEfficiency;
    //        this.attackDamage = attackDamage;
    //        this.enchantValue = enchantValue;
    //        this.ingredientSupplier = ingredientSupplier;
    //    }
        EnumTiers(TierConfig config, TagKey<Block> blockTagKey, Supplier<Ingredient> ingredientSupplier) {
            tierConfig = config;
            harvestLevel = config.getHarvestLevel();
            durability = config.getDurability();
            toolEfficiency = (float) config.getEfficiency();
            attackDamage = (float) config.getDamage();
            enchantValue = config.getEnchantability();
            tagKey = blockTagKey;
            repairItem = ingredientSupplier;
        }

        @Override public int getLevel() { return harvestLevel; }
        @Override public int getUses() { return durability; }
        @Override public float getSpeed() { return toolEfficiency; }
        @Override public float getAttackDamageBonus() { return attackDamage; }
        @Override public int getEnchantmentValue() { return enchantValue; }
        @Override public @NotNull Ingredient getRepairIngredient() { return repairItem.get(); }
        @Nullable public TagKey<Block> getTag() { return tagKey; }

        public static Tier getConfiguredTier(TierConfig config, Tier tier) {
            return new ForgeTier(config.harvestLevel.get(),
                    config.durability.get(),
                    config.efficiency.get().floatValue(),
                    config.damage.get().floatValue(),
                    config.enchantability.get(),
                    tier.getTag(),
                    tier::getRepairIngredient
            );
        }
    }
}
