package net.nokunami.elementus.common.compat.theaether;

import com.aetherteam.aether.item.accessories.gloves.GlovesItem;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

import java.util.Collection;
import java.util.UUID;
import java.util.function.Supplier;

public class AttributeGlovesItem extends GlovesItem {
    protected final ArmorMaterial material;
    protected final double damage;
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

    public AttributeGlovesItem(ArmorMaterial material, double punchDamage, String glovesName, Supplier<? extends SoundEvent> glovesSound, Properties properties, Multimap<Attribute, AttributeModifier> defaultModifiers) {
        super(material, punchDamage, glovesName, glovesSound, properties);
        this.material = material;
        this.damage = punchDamage;
        this.defaultModifiers = defaultModifiers;
    }

    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> attributeBuilder = new ImmutableMultimap.Builder<>();

        for (Attribute attribute : this.defaultModifiers.keySet()) {
            Collection<AttributeModifier> modifiers = this.defaultModifiers.get(attribute);

            for (AttributeModifier attributeModifier : modifiers) {
                attributeBuilder.put(attribute, new AttributeModifier(uuid, attributeModifier.getName(), attributeModifier.getAmount(), attributeModifier.getOperation()));
                attributeBuilder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid, "Gloves Damage Bonus", this.damage, AttributeModifier.Operation.ADDITION));
            }
        }

        return attributeBuilder.build();
    }
}
