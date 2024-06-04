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
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class ElementusArmorItem extends ArmorItem {
    protected final CustomArmorMaterial material;

    public ElementusArmorItem(CustomArmorMaterial material, ArmorItem.Type type, Item.Properties properties) {
        super(material, type, new Item.Properties());
        this.material = material;
    }

    @Override
    public @NotNull CustomArmorMaterial getMaterial() {
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
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> _default) {
                return new ArmorModelLayered<>(ArmorModelLayered.createBodyLayer().bakeRoot(), armorSlot);
            }
        });
    }

    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        if (material == CustomArmorMaterial.STEEL) {
            return Elementus.MODID + ":textures/models/armor/steel_layer.png";
        } else if (material == CustomArmorMaterial.DIARKRITE) {
            return Elementus.MODID + ":textures/models/armor/diarkrite_layer.png";
        } else if (material == CustomArmorMaterial.ANTHEKTITE) {
            return Elementus.MODID + ":textures/models/armor/anthektite_layer.png";
        }
        return Elementus.MODID + ":textures/models/armor/steel_layerpng";
    }
}