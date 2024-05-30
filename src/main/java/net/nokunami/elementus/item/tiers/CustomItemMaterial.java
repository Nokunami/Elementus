package net.nokunami.elementus.item.tiers;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.nokunami.elementus.registry.ModItems;

import java.util.function.Supplier;

public enum CustomItemMaterial implements Tier {
    STEEL(3, 756, 7.0F, 2.0F, 15,
            () -> Ingredient.of(ModItems.STEEL_INGOT.get())),
    DIARKRITE(4, 2346, 8.0F, 6.0F, 15,
            () -> Ingredient.of(ModItems.DIARKRITE_INGOT.get())),
    ANTHEKTITE(4, 1946, 10.0F, 3.0F, 15,
            () -> Ingredient.of(ModItems.ANTHEKTITE_INGOT.get()));

    private final int harvestLevel;
    private final int durability;
    private final float toolEfficiency;
    private final float attackDamage;
    private final int enchantValue;
    private final Supplier<Ingredient> repaireItem;

    CustomItemMaterial(int harvestLevel, int uses, float toolEfficiency, float attackDamage, int enchantValue, Supplier repaireItem) {
        this.harvestLevel = harvestLevel;
        this.durability = uses;
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
