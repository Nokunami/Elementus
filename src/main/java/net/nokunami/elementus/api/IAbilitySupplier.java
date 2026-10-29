package net.nokunami.elementus.api;

import net.minecraft.world.item.ItemStack;
import net.nokunami.elementus.common.catalystCore.ability.AbstractAbility;

import java.util.List;

public interface IAbilitySupplier {

    List<AbstractAbility> getAbilities(ItemStack stack);
}
