package net.nokunami.elementus.api;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.nokunami.elementus.ElementusClient;
import net.nokunami.elementus.common.catalystCore.core.CatalystCore;
import net.nokunami.elementus.common.item.unique.TestCatalystArmorItem;
import org.jetbrains.annotations.NotNull;

public interface ICatalystTrim {
    /// Copied from @param IClientItemExtension

    ICatalystTrim DEFAULT = new ICatalystTrim() { };

    static ICatalystTrim of(ItemStack stack) {
        return of((TestCatalystArmorItem) stack.getItem());
    }

    static ICatalystTrim of(TestCatalystArmorItem stack) {
        return stack.getRenderTrimPropertiesInternal() instanceof ICatalystTrim e ? e : DEFAULT;
    }

    @NotNull
    default HumanoidModel<?> getHumanoidArmorModel(LivingEntity entity, ItemStack core, EquipmentSlot slot, HumanoidModel<?> original) {
        return original;
    }

    @NotNull
    default Model getGenericArmorModel(LivingEntity entity, ItemStack core, EquipmentSlot slot, HumanoidModel<?> original) {
        HumanoidModel<?> replacement = getHumanoidArmorModel(entity, core, slot, original);
        if (replacement != original) {
            ElementusClient.copyTrimModelProperties(original, replacement);
            return replacement;
        }
        return original;
    }
}
