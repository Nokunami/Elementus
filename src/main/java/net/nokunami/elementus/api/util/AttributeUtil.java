package net.nokunami.elementus.api.util;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

import javax.annotation.Nullable;
import java.util.UUID;
import java.util.function.Supplier;

public class AttributeUtil {
    Entity entity;
    Attribute attribute;

    public static AttributeUtil attributeUtil(Entity entity, Supplier<Attribute> attribute) {
        return new AttributeUtil(entity, attribute.get());
    }

    public static AttributeUtil attributeUtil(Entity entity, Attribute attribute) {
        return new AttributeUtil(entity, attribute);
    }

    public AttributeUtil(Entity entity, Attribute attribute) {
        this.entity = entity;
        this.attribute = attribute;
    }

    LivingEntity getEntity() { return (LivingEntity) entity; }

    public int valueInt() { return (int) value(); }
    public double value() {
        var attr = attributeInstance();
        if (attr != null) return attr.getValue();
        return 0;
    }

    public int baseValueInt() { return (int) baseValue(); }
    public double baseValue() {
        var attr = attributeInstance();
        if (attr != null) return attr.getBaseValue();
        return 0;
    }

    @Nullable public AttributeInstance attributeInstance() { return getEntity().getAttribute(attribute); }

    public void modify(double value) {
        var attr = getEntity().getAttribute(attribute);
        if (attr != null) attr.setBaseValue(value);
    }

    public void addTransient(AttributeModifier modifier) {
        AttributeInstance attributeinstance = getEntity().getAttribute(attribute);
        if (attributeinstance != null && !attributeinstance.hasModifier(modifier)) attributeinstance.addTransientModifier(modifier);
    }
    public void removeModifier(UUID uuid) {
        AttributeInstance attributeinstance = getEntity().getAttribute(attribute);
        if (attributeinstance != null && attributeinstance.getModifier(uuid) != null) attributeinstance.removeModifier(uuid);
    }
}