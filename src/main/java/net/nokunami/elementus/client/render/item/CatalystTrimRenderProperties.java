package net.nokunami.elementus.client.render.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.nokunami.elementus.client.extensions.ICatalystTrim;
import net.nokunami.elementus.client.model.ModModelLayers;
import net.nokunami.elementus.client.model.armor.CatalystArmorTrimModel;
import net.nokunami.elementus.common.registry.EItems;
import org.jetbrains.annotations.NotNull;

public class CatalystTrimRenderProperties implements ICatalystTrim {
    private static boolean init;
    public static CatalystArmorTrimModel<LivingEntity> CATALYST_ARMOR_TRIM_MODEL;

    public static void initializedModels() {
        init = true;
        EntityModelSet bake = Minecraft.getInstance().getEntityModels();
        CATALYST_ARMOR_TRIM_MODEL = new CatalystArmorTrimModel<>(bake.bakeLayer(ModModelLayers.CATALYST_ARMOR_TRIM_MODEL));
    }

    public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
        if (!init) initializedModels();
        Item item = itemStack.getItem();

        if ((item == EItems.TEST_CATALYST_CHESTPLATE.get())) {
            return CATALYST_ARMOR_TRIM_MODEL;
        }

        return original;
    }
}