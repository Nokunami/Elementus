package net.nokunami.elementus.common.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.nokunami.elementus.ModClientEvents;
import net.nokunami.elementus.common.registry.ModArmorMaterials;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;


public class ElementusArmorItem extends ArmorItem {
    protected final ModArmorMaterials material;

    public ElementusArmorItem(ModArmorMaterials material, ArmorItem.Type type, Item.Properties properties) {
        super(material, type, new Item.Properties());
        this.material = material;
    }

    @Override
    public @NotNull ModArmorMaterials getMaterial() {
        return this.material;
    }

    @Override
    public @NotNull Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(@NotNull EquipmentSlot pEquipmentSlot) {
        if (pEquipmentSlot == this.type.getSlot()) {
            return this.material.getSlotToAttributeMap().get(pEquipmentSlot);
        } else {
            return ImmutableMultimap.of();
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void initializeClient(java.util.function.Consumer<IClientItemExtensions> consumer) {
        consumer.accept((IClientItemExtensions) ModClientEvents.PROXY.getArmorRenderProperties());
    }

    public @Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        ArmorItem item = (ArmorItem)stack.getItem();
        String texture = item.getMaterial().getName();
        String domain = "elementus";
        boolean helmet = slot == EquipmentSlot.HEAD;
        boolean chestplate = slot == EquipmentSlot.CHEST;
        boolean leggings = slot == EquipmentSlot.LEGS;

        return String.format(Locale.ROOT, "%s:textures/models/armor/%s_layer_" + (helmet ? "1.png" : chestplate ? "2.png" : leggings ? "3.png" : "4.png"), domain, texture);
    }
}