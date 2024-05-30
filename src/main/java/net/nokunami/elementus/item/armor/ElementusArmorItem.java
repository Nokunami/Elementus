package net.nokunami.elementus.item.armor;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.nokunami.elementus.Elementus;
import net.nokunami.elementus.client.ArmorModelLayered;
import net.nokunami.elementus.item.armor.CustomArmorMaterial;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class ElementusArmorItem extends ArmorItem {
    protected final CustomArmorMaterial material;

    public ElementusArmorItem(CustomArmorMaterial material, ArmorItem.Type type, Item.Properties properties) {
        super(material, type, new Item.Properties());
        this.material = material;
    }

    @Override
    public CustomArmorMaterial getMaterial() {
        return this.material;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot pEquipmentSlot) {
        if (pEquipmentSlot == this.type.getSlot()) {
            return this.material.getSlotToAttributeMap().get(pEquipmentSlot);
        } else {
            return ImmutableMultimap.of();
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void initializeClient(java.util.function.Consumer<IClientItemExtensions> consumer) {
        consumer.accept((IClientItemExtensions) Elementus.PROXY.getArmorRenderProperties());
    }

    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        if (material == CustomArmorMaterial.STEEL) {
            return Elementus.MODID + ":textures/models/armor/steel_layer_" + (slot == EquipmentSlot.LEGS ? "2.png" : "1.png");
        } else if (material == CustomArmorMaterial.DIARKRITE) {
            return Elementus.MODID + ":textures/models/armor/diarkrite_layer_" + (slot == EquipmentSlot.LEGS ? "2.png" : "1.png");
        } else if (material == CustomArmorMaterial.ANTHEKTITE) {
            return Elementus.MODID + ":textures/models/armor/anthektite_layer_" + (slot == EquipmentSlot.LEGS ? "2.png" : "1.png");
        }
        return Elementus.MODID + ":textures/models/armor/steel_layer_" + (slot == EquipmentSlot.LEGS ? "2.png" : "1.png");
    }
}