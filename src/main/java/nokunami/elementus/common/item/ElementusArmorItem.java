package nokunami.elementus.common.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.ItemStack;
//import net.minecraftforge.api.distmarker.Dist;
//import net.minecraftforge.api.distmarker.OnlyIn;
//import net.minecraftforge.client.extensions.common.IClientItemExtensions;
//import net.minecraftforge.common.util.Lazy;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.neoforged.neoforge.common.util.Lazy;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import nokunami.elementus.ElementusClient;
import nokunami.elementus.common.registry.ModArmorMaterials;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;


public class ElementusArmorItem extends ArmorItem {
//    protected final Holder<ArmorMaterial> material;
//    protected final Multimap<Attribute, AttributeModifier> defaultModifiers;
//    protected final Lazy<Multimap<Attribute, AttributeModifier>> defaultModifiers;

    public ElementusArmorItem(Holder<ArmorMaterial> material, Type type, Properties properties) {
        super(material, type, properties);
//        this.material = material;
//        defaultModifiers = Lazy.of(() -> createDefaultAttributeModifiers(this.getDefense(), this.getToughness(), 1).build());
    }

//    @Override
//    public @NotNull ModArmorMaterials getMaterial() {
//        return this.material;
//    }

    // Attribute code from biomancy
//    protected ImmutableMultimap.Builder<Attribute, AttributeModifier> createDefaultAttributeModifiers(int defense, float toughness, float knockback) {
//        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
//        ItemAttributeModifiers.Builder attr = ItemAttributeModifiers.builder();
//        EquipmentSlotGroup slotGroup = EquipmentSlotGroup.bySlot(type.getSlot());
//        ResourceLocation resourcelocation = ResourceLocation.withDefaultNamespace("armor." + type.getName());
//        attr.add(
//                Attributes.ARMOR, new AttributeModifier(resourcelocation, material.value().getDefense(type), AttributeModifier.Operation.ADD_VALUE), slotGroup
//        );
//        attr.add(
//                Attributes.ARMOR_TOUGHNESS, new AttributeModifier(resourcelocation, material.value().toughness(), AttributeModifier.Operation.ADD_VALUE), slotGroup
//        );
//        if (material.value().knockbackResistance() <= 0)
//            attr.add(
//                    Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(resourcelocation, material.value().knockbackResistance(), AttributeModifier.Operation.ADD_VALUE), slotGroup
//            );
////        UUID uuid = ARMOR_MODIFIER_UUID_PER_TYPE.get(type);
////        builder.put(Attributes.ARMOR, new AttributeModifier(uuid, "Armor modifier", defense, AttributeModifier.Operation.ADDITION));
////        builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(uuid, "Armor toughness", toughness, AttributeModifier.Operation.ADDITION));
//        if (knockback != 0) {
//            builder.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(uuid, "Knockback resistance", knockback, AttributeModifier.Operation.ADDITION));
//        }
//        for (Map.Entry<Attribute, AttributeModifier> modifierEntry : material.getAdditionalAttributes().entrySet()) {
//            AttributeModifier atr = modifierEntry.getValue();
//            atr = new AttributeModifier(uuid, atr.getName(), atr.getAmount(), atr.getOperation());
//            builder.put(modifierEntry.getKey(), atr);
//        }
//        return builder;
//    }

//    public @NotNull Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(@NotNull EquipmentSlot pEquipmentSlot) {
//        return pEquipmentSlot == this.type.getSlot() ? defaultModifiers.get() : super.getDefaultAttributeModifiers(pEquipmentSlot);
//    }

//        @Override
//    @OnlyIn(Dist.CLIENT)
//    public void initializeClient(@NotNull Consumer<IClientItemExtensions> consumer) {
//        consumer.accept((IClientItemExtensions) ElementusClient.PROXY.getArmorRenderProperties());
//    }

//    public @Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
//        ArmorItem item = (ArmorItem)stack.getItem();
//        String texture = item.getMaterial().getName();
//        String domain = "elementus";
//        boolean helmet = slot == EquipmentSlot.HEAD;
//        boolean leggings = slot == EquipmentSlot.LEGS;
//
//        return String.format(Locale.ROOT, "%s:textures/models/armor/%s_layer_" + (helmet | leggings ? "2.png" : "1.png"), domain, texture);
//    }
}