package net.nokunami.elementus.common.item;

import com.google.common.collect.ImmutableMultimap;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import static net.nokunami.elementus.common.catalystCore.CatalystArmorAttributes.armorUUID;
import static net.nokunami.elementus.common.config.catalystConfigs.CatalystArmorConfig.*;

public class SteelGolemUpgradeAttributes {

    public static ImmutableMultimap.Builder<Attribute, AttributeModifier> reinforcedPlating() {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ARMOR, new AttributeModifier(armorUUID, "Armor modifier", 10, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(armorUUID, "Armor toughness", 8, AttributeModifier.Operation.ADDITION));
        return builder;
    }
}
