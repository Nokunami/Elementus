package net.nokunami.elementus.common.registry;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.nokunami.elementus.common.compat.advancednetherite.ANModItems;

import java.util.function.Supplier;

public enum ModTiers implements Tier {
    STEEL(3, 756, 7.0F,2.0F, 15,
            () -> Ingredient.of(ModItems.STEEL_INGOT.get())),
    DIARKRITE(4, 2546, 9.0F, 6.0F, 15,
            () -> Ingredient.of(ModItems.DIARKRITE_INGOT.get())),
    ANTHEKTITE(4, 1946, 12.0F, 3.0F, 15,
            () -> Ingredient.of(ModItems.ANTHEKTITE_INGOT.get())),

    DIARKRITE_IRON(4, 2608, 1.0F, 6.0F, 15,
            () -> Ingredient.of(ANModItems.DIARKRITE_IRON.get())),
    DIARKRITE_GOLD(4, 2899, 1.0F, 6.0F, 25,
            () -> Ingredient.of(ANModItems.DIARKRITE_GOLD.get())),
    DIARKRITE_EMERALD(4, 3323, 1.0F, 7.0F, 20,
            () -> Ingredient.of(ANModItems.DIARKRITE_EMERALD.get())),
    DIARKRITE_DIAMOND(4, 3876, 1.0F, 7.0F, 15,
            () -> Ingredient.of(ANModItems.DIARKRITE_DIAMOND.get())),

    ANTHEKTITE_IRON(4, 1994, 1.0F, 3.0F, 15,
            () -> Ingredient.of(ANModItems.ANTHEKTITE_IRON.get())),
    ANTHEKTITE_GOLD(4, 2216, 1.0F, 3.0F, 25,
            () -> Ingredient.of(ANModItems.ANTHEKTITE_GOLD.get())),
    ANTHEKTITE_EMERALD(4, 2540, 1.0F, 4.0F, 20,
            () -> Ingredient.of(ANModItems.ANTHEKTITE_EMERALD.get())),
    ANTHEKTITE_DIAMOND(4, 2962, 1.0F, 4.0F, 15,
            () -> Ingredient.of(ANModItems.ANTHEKTITE_DIAMOND.get()));

    private final int harvestLevel;
    private final int durability;
    private final float toolEfficiency;
    private final float attackDamage;
    private final int enchantValue;
    private final Supplier<Ingredient> repaireItem;

    ModTiers(int harvestLevel, int uses, float toolEfficiency, float attackDamage, int enchantValue, Supplier repaireItem) {
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
