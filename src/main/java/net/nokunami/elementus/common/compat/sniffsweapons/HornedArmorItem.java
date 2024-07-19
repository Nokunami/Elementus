package net.nokunami.elementus.common.compat.sniffsweapons;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.nokunami.elementus.client.model.armor.HornedArmorModel;
import net.nokunami.elementus.common.registry.ModArmorMaterials;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;
import java.util.function.Consumer;

public class HornedArmorItem extends StylishArmorItem {

    public HornedArmorItem(ModArmorMaterials material, Type type, int color, Properties properties) {
        super(material, type, "horned", color, new Properties());
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> _default) {
                return new HornedArmorModel<>(HornedArmorModel.createBodyLayer().bakeRoot(), itemStack, equipmentSlot, livingEntity);
            }
        });
    }

    public @Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        ArmorItem item = (ArmorItem)stack.getItem();
        String texture = item.getMaterial().getName();
        String domain = "elementus";
        int idx = texture.indexOf(58);
        if (idx != -1) {
            domain = texture.substring(0, idx);
            texture = texture.substring(idx + 1);
        }

        return String.format(Locale.ROOT, "%s:textures/models/armor/sniffs_weapons/%s_%s.png", domain, "horned", texture);
    }
}