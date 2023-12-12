package net.nokunami.elementus.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ItemTiers {
    public static final ForgeTier STEEL = new ForgeTier(
            3, 756, 7.0F, 2F, 15,
            BlockTags.NEEDS_IRON_TOOL, () -> Ingredient.of(ItemsRegistry.STEEL_INGOT.get()));
    public static final ForgeTier ANTHEKTITE = new ForgeTier(
            4, 1678, 7.95F, 4F,13,
            BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(ItemsRegistry.STEEL_INGOT.get()));
    public static final ForgeTier DIARKRITE = new ForgeTier(
            4, 2496, 9.25F, 5F,15,
            BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(ItemsRegistry.STEEL_INGOT.get()));
}
