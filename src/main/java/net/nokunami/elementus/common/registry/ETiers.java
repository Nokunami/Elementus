package net.nokunami.elementus.common.registry;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.nokunami.elementus.config.EConfig;
import net.nokunami.elementus.config.preset.IConfigTier;
import net.nokunami.elementus.common.item.IETier;
import net.nokunami.elementus.common.tags.EBlockTags;
import net.nokunami.elementus.common.tags.EItemTags;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public enum ETiers implements IETier {
    ASTALITE(EConfig.tierConfig.astalite, EBlockTags.NEEDS_ASTALITE_TOOL, () -> Ingredient.of(EItemTags.REPAIRS_ASTALITE_EQUIPMENT)),
    DIARKRITE(EConfig.tierConfig.diarkrite, EBlockTags.NEEDS_DIARKRITE_TOOL, () -> Ingredient.of(EItemTags.REPAIRS_DIARKRITE_EQUIPMENT)),
    ANTHEKTITE(EConfig.tierConfig.anthektite, EBlockTags.NEEDS_ANTHEKTITE_TOOL, () -> Ingredient.of(EItemTags.REPAIRS_ANTHEKTITE_EQUIPMENT)),
    MOVCADIA(EConfig.tierConfig.movcadia, EBlockTags.NEEDS_MOVCADIA_TOOL, () -> Ingredient.of(EItemTags.REPAIRS_MOVCADIA_EQUIPMENT));

    private final int harvestLevel;
    private final int durability;
    private final float toolEfficiency;
    private final float attackDamage;
    private final int enchantValue;
    private final TagKey<Block> tagKey;
    private final Supplier<Ingredient> repairItem;
    private final double attackSpeed;

    ETiers(IConfigTier config, TagKey<Block> blockTagKey, Supplier<Ingredient> ingredientSupplier) {
        harvestLevel = config.harvestLevel();
        durability = config.durability();
        toolEfficiency = (float) config.efficiency();
        attackDamage = (float) config.damage();
        enchantValue = config.enchantability();
        tagKey = blockTagKey;
        repairItem = ingredientSupplier;
        attackSpeed = config.weaponSpeed();
    }

    @Override public int getLevel() { return harvestLevel; }
    @Override public int getUses() { return durability; }
    @Override public float getSpeed() { return toolEfficiency; }
    @Override public float getAttackDamageBonus() { return attackDamage; }
    @Override public int getEnchantmentValue() { return enchantValue; }
    @Override public @NotNull Ingredient getRepairIngredient() { return repairItem.get(); }
    @Nullable public TagKey<Block> getTag() { return tagKey; }
    @Override public double getWeaponSpeed() { return attackSpeed; }
}