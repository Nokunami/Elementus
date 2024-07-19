package net.nokunami.elementus.common.compat.theaether;

import com.aetherteam.aether.item.accessories.gloves.GlovesItem;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

import java.util.Collection;
import java.util.Iterator;
import java.util.UUID;
import java.util.function.Supplier;

public class AttributeGloveItem extends GlovesItem {
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

    public AttributeGloveItem(ArmorMaterial material, double punchDamage, String glovesName, Supplier<? extends SoundEvent> glovesSound, Properties properties, Multimap<Attribute, AttributeModifier> defaultModifiers) {
        super(material, punchDamage, glovesName, glovesSound, properties);
        this.defaultModifiers = defaultModifiers;
    }

    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> attributeBuilder = new ImmutableMultimap.Builder();
        Iterator var5 = this.defaultModifiers.keySet().iterator();

        while(var5.hasNext()) {
            Attribute attribute = (Attribute)var5.next();
            Collection<AttributeModifier> modifiers = this.defaultModifiers.get(attribute);
            Iterator var8 = modifiers.iterator();

            while(var8.hasNext()) {
                AttributeModifier attributeModifier = (AttributeModifier)var8.next();
                attributeBuilder.put(attribute, new AttributeModifier(uuid, attributeModifier.getName(), attributeModifier.getAmount(), attributeModifier.getOperation()));
            }
        }

        return attributeBuilder.build();
    }
}
