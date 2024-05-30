package net.nokunami.elementus.compat.ironsspellbooks;

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
import net.nokunami.elementus.client.HeavyMageArmor;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class ElementusWizardArmorItem extends ArmorItem {
//    private static final UUID[] ARMOR_MODIFIERS = new UUID[]{
//            UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"),
//            UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"),
//            UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"),
//            UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150")};
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

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        if (material == MagicArmorMaterial.ANTHEKTITE_MAGE) {
            return "elementus:textures/models/armor/anthektite_wizard_armor_layer.png";
        } else if (material == MagicArmorMaterial.DIARKRITE_MAGE) {
            return "elementus:textures/models/armor/diarkrite_wizard_armor_layer.png";
        }
        return "elementus:textures/models/armor/armor_template.png";
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public HumanoidModel<?> getHumanoidArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> _default) {
                return new HeavyMageArmor<>(HeavyMageArmor.createBodyLayer().bakeRoot(), armorSlot);
            }
        });
    }
}