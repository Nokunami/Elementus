package net.nokunami.elementus.client.extensions;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.nokunami.elementus.EClient;
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

    @OnlyIn(Dist.CLIENT)
    @NotNull
    default HumanoidModel<?> getHumanoidArmorModel(LivingEntity entity, ItemStack core, EquipmentSlot slot, HumanoidModel<?> original) {
        return original;
    }

    @OnlyIn(Dist.CLIENT)
    @NotNull
    default Model getGenericArmorModel(LivingEntity entity, ItemStack core, EquipmentSlot slot, HumanoidModel<?> original) {
        HumanoidModel<?> replacement = getHumanoidArmorModel(entity, core, slot, original);
        if (replacement != original) {
//            ElementusClient.copyTrimModelProperties(original, replacement);
            EClient.copyTrimModelProperties(original, replacement);
            return replacement;
        }
        return original;
    }
}
