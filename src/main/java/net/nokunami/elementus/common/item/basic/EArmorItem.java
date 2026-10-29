package net.nokunami.elementus.common.item.basic;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.nokunami.elementus.EClient;
import net.nokunami.elementus.common.registry.EArmorMaterials;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

import static net.nokunami.elementus.Elementus.EID;
import static net.nokunami.elementus.common.item.AttributeUtil.toggleableAttribute;

public class EArmorItem extends ArmorItem {

    public EArmorItem(EArmorMaterials material, Type type, Properties properties) { super(material, type, properties); }

    public ImmutableMultimap.Builder<Attribute, AttributeModifier> attributes() {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        UUID uuid = ARMOR_MODIFIER_UUID_PER_TYPE.get(type);
        builder.put(Attributes.ARMOR, new AttributeModifier(uuid, "Armor modifier", getDefense(), AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(uuid, "Armor toughness", getToughness(), AttributeModifier.Operation.ADDITION));
        toggleableAttribute(builder, Attributes.KNOCKBACK_RESISTANCE, "Armor knockback resistance", getMaterial().getKnockbackResistance(), AttributeModifier.Operation.ADDITION);
        if (getMaterial() instanceof EArmorMaterials e)
            for (Map.Entry<Attribute, AttributeModifier> modifierEntry : e.getAdditionalAttributes().entrySet()) {
                AttributeModifier atr = modifierEntry.getValue();
                atr = new AttributeModifier(uuid, atr.getName(), atr.getAmount(), atr.getOperation());
                builder.put(modifierEntry.getKey(), atr);
            }
        return builder;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        return slot == getEquipmentSlot() ? attributes().build() : super.getAttributeModifiers(slot, stack);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void initializeClient(@NotNull Consumer<IClientItemExtensions> consumer) {
        consumer.accept((IClientItemExtensions) EClient.PROXY.getArmorRenderProperties());
    }

    public @Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        ArmorItem item = (ArmorItem)stack.getItem();
        boolean leggings = slot == EquipmentSlot.HEAD || slot == EquipmentSlot.LEGS;
        return String.format(Locale.ROOT, "%s:textures/models/armor/%s_layer_" + (leggings ? "2.png" : "1.png"), EID, item.getMaterial().getName());
    }
}