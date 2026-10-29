package net.nokunami.elementus.common.item;

import com.google.common.collect.ImmutableMultimap;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;

import java.util.UUID;
import java.util.function.Supplier;

public class AttributeUtil {
    public static UUID armorUUID = UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E");

    public static ImmutableMultimap.Builder<Attribute, AttributeModifier> toggleableAttribute(ImmutableMultimap.Builder<Attribute, AttributeModifier> builder, UUID uuid, boolean condition, Supplier<Attribute> attribute, String name, double value, Operation operation) {
        if (condition) builder.put(attribute.get(), new AttributeModifier(uuid, name, value, operation));
        return builder;
    }
    public static ImmutableMultimap.Builder<Attribute, AttributeModifier> toggleableAttribute(ImmutableMultimap.Builder<Attribute, AttributeModifier> builder, Supplier<Attribute> attribute, String name, double value, Operation operation) {
        return toggleableAttribute(builder, UUID.randomUUID(), value != 0, attribute, name, value, operation);
    }

    public static ImmutableMultimap.Builder<Attribute, AttributeModifier> toggleableAttribute(ImmutableMultimap.Builder<Attribute, AttributeModifier> builder, UUID uuid, boolean condition, Attribute attribute, String name, double value, Operation operation) {
        return toggleableAttribute(builder, uuid, condition, () -> attribute, name, value, operation);
    }
    public static ImmutableMultimap.Builder<Attribute, AttributeModifier> toggleableAttribute(ImmutableMultimap.Builder<Attribute, AttributeModifier> builder, Attribute attribute, String name, double value, Operation operation) {
        return toggleableAttribute(builder, UUID.randomUUID(), value != 0, attribute, name, value, operation);
    }
}
