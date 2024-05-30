package net.nokunami.elementus.compat.ironsspellbooks;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.nokunami.elementus.client.LightMageArmor;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class ISSLightMageArmorItem extends ImbuableChestplateArmorItem {
    public ISSLightMageArmorItem(MagicArmorMaterial material, Type type, Properties properties) {
        super(material, type, new Properties());
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
                return new LightMageArmor(LightMageArmor.createBodyLayer().bakeRoot(), armorSlot);
            }
        });
    }
}
