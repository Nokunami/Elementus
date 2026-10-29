package net.nokunami.elementus.client.render.catalystCore;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.nokunami.elementus.client.extensions.IClientCatalystExtension;
import net.nokunami.elementus.client.model.armor.CatalystArmorModel;
import net.nokunami.elementus.client.model.armor.CatalystBaseModel;
import net.nokunami.elementus.client.model.geom.EModelLayers;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class CatalystArmorRenderProperties implements IClientCatalystExtension {
    private static boolean init;
    public static CatalystArmorModel<LivingEntity> TEST_CATALYST_ARMOR_MODEL;
    public static CatalystBaseModel<LivingEntity> CATALYST_ARMOR_MODEL;

    public static void initializedModels() {
        init = true;
        EntityModelSet bake = Minecraft.getInstance().getEntityModels();
        TEST_CATALYST_ARMOR_MODEL = new CatalystArmorModel<>(bake.bakeLayer(EModelLayers.TEST_CATALYST_ARMOR_MODEL));
        CATALYST_ARMOR_MODEL = new CatalystBaseModel<>(bake.bakeLayer(EModelLayers.CATALYST_ARMOR_MODEL));
    }

    @Override
    public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity entity, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> original) {
        if (!init) initializedModels();

        return TEST_CATALYST_ARMOR_MODEL;
    }
}