package net.nokunami.elementus.common.compat.ironsspellbooks;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.nokunami.elementus.Elementus;
import net.nokunami.elementus.client.HeavyMageArmor;

import javax.annotation.Nullable;
import java.util.Locale;
import java.util.function.Consumer;

public class ElementusWizardArmorItem extends ArmorItem {
    protected final MagicArmorMaterial material;

    public ElementusWizardArmorItem(MagicArmorMaterial material, Type type, Properties properties) {
        super(material, type, new Properties());
        this.material = material;
    }

    @Override
    public MagicArmorMaterial getMaterial() {
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

//    @Nullable
//    @Override
//    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
//        if (material == MagicArmorMaterial.ANTHEKTITE_MAGE) {
//            return "elementus:textures/models/armor/anthektite_wizard_armor_layer.png";
//        } else if (material == MagicArmorMaterial.DIARKRITE_MAGE) {
//            return "elementus:textures/models/armor/diarkrite_wizard_armor_layer.png";
//        }
//        return "elementus:textures/models/armor/armor_template.png";
//    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void initializeClient(java.util.function.Consumer<IClientItemExtensions> consumer) {
        consumer.accept((IClientItemExtensions) Elementus.PROXY.getArmorRenderProperties());
    }

    public @org.jetbrains.annotations.Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        ArmorItem item = (ArmorItem)stack.getItem();
        String texture = item.getMaterial().getName();
        String domain = "elementus";
        boolean helmet = slot == EquipmentSlot.HEAD;
        boolean leggings = slot == EquipmentSlot.LEGS;

        String s1 = String.format(Locale.ROOT, "%s:textures/models/armor/%s_layer_" + (leggings | helmet ? "2.png" : "1.png"), domain, texture);
        return s1;
    }
}