package net.nokunami.elementus.common.item.basic;

import com.google.common.collect.ImmutableMultimap;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.nokunami.elementus.common.config.configSets.item.ToolSetConfig;

public interface IConfiguredItem {

    ToolSetConfig config();

    ImmutableMultimap.Builder<Attribute, AttributeModifier> attributes(ItemStack stack);

    double getAttackSpeed();
}
