package net.nokunami.elementus.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ItemTiers {
    public static final ForgeTier STEEL = new ForgeTier(
            3, 716, 7.0F, 2F, 15,
            BlockTags.NEEDS_IRON_TOOL, () -> Ingredient.of(ItemsRegistry.STEEL_INGOT.get()));
}
