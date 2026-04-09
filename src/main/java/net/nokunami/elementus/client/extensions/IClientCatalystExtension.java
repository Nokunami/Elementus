package net.nokunami.elementus.client.extensions;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.nokunami.elementus.EClient;
import net.nokunami.elementus.common.registry.CustomRegistries;
import org.jetbrains.annotations.NotNull;

import static net.nokunami.elementus.common.registry.CustomRegistries.CatalystCoreHelper.CatalystCoreUtil;

public interface IClientCatalystExtension {
    /// Copied from @param IClientItemExtension

    IClientCatalystExtension DEFAULT = new IClientCatalystExtension() { };

    static IClientCatalystExtension ofCore(ItemStack core) {
//        return CustomRegistries.getCatalystCore(core).getRenderPropertiesInternal() instanceof IClientCatalystExtension e ? e : DEFAULT;
        return CatalystCoreUtil(core).getCore().getRenderPropertiesInternal() instanceof IClientCatalystExtension e ? e : DEFAULT;
    }

    /**
     * Queries the humanoid armor model for this item when it's equipped.
     *
     * @param entity  The entity wearing the armor
     * @param core     The item stack
     * @param slot The slot the item is in
     * @param original      The original armor model. Will have attributes set.
     * @return A HumanoidModel to be rendered. Relevant properties are to be copied over by the caller.
     * @see #getGenericArmorModel(LivingEntity, ItemStack, EquipmentSlot, HumanoidModel)
     */
    @OnlyIn(Dist.CLIENT)
    @NotNull
    default HumanoidModel<?> getHumanoidArmorModel(LivingEntity entity, ItemStack core, EquipmentSlot slot, HumanoidModel<?> original) {
        return original;
    }

    /**
     * Queries the armor model for this item when it's equipped. Useful in place of
     * {@link #getHumanoidArmorModel(LivingEntity, ItemStack, EquipmentSlot, HumanoidModel)} for wrapping the original
     * model or returning anything non-standard.
     * <p>
     * If you override this method you are responsible for copying any properties you care about from the original model.
     *
     * @param entity  The entity wearing the armor
     * @param core     The item stack
     * @param slot The slot the item is in
     * @param original      The original armor model. Will have attributes set.
     * @return A Model to be rendered. Relevant properties must be copied over manually.
     * @see #getHumanoidArmorModel(LivingEntity, ItemStack, EquipmentSlot, HumanoidModel)
     */
    @OnlyIn(Dist.CLIENT)
    @NotNull
    default Model getGenericArmorModel(LivingEntity entity, ItemStack core, EquipmentSlot slot, HumanoidModel<?> original) {
        HumanoidModel<?> replacement = getHumanoidArmorModel(entity, core, slot, original);
        if (replacement != original) {
            EClient.copyModelProperties(original, replacement);
            return replacement;
        }
        return original;
    }
}
