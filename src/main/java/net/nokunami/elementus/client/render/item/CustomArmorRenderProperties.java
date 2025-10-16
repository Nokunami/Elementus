package net.nokunami.elementus.client.render.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.nokunami.elementus.client.model.ModModelLayers;
import net.nokunami.elementus.client.model.armor.CatalystArmorModel;
import net.nokunami.elementus.client.model.armor.CatalystBaseModel;
import net.nokunami.elementus.client.model.armor.ExtendedArmorModel;
import net.nokunami.elementus.common.catalystCore.core.CatalystCore;
import net.nokunami.elementus.common.registry.CustomRegistries;
import net.nokunami.elementus.common.registry.EItems;
import org.jetbrains.annotations.NotNull;

import static net.nokunami.elementus.common.item.unique.CatalystItemUtil.getEquippedCore;

public class CustomArmorRenderProperties implements IClientItemExtensions {
    private static boolean init;
    public static ExtendedArmorModel<?> EXTENDED_ARMOR_MODEL;
    public static ExtendedArmorModel<?> EXTENDED_ARMOR_MODEL_LEGS;
    public static CatalystBaseModel<LivingEntity> CATALYST_ARMOR_MODEL;
    public static CatalystArmorModel<LivingEntity> TEST_CATALYST_ARMOR_MODEL;

    public static void initializedModels() {
        init = true;
        EntityModelSet bake = Minecraft.getInstance().getEntityModels();
        EXTENDED_ARMOR_MODEL = new ExtendedArmorModel<>(bake.bakeLayer(ModModelLayers.EXTENDED_ARMOR_MODEL));
        EXTENDED_ARMOR_MODEL_LEGS = new ExtendedArmorModel<>(bake.bakeLayer(ModModelLayers.EXTENDED_ARMOR_MODEL_LEGS));
        CATALYST_ARMOR_MODEL = new CatalystBaseModel<>(bake.bakeLayer(ModModelLayers.CATALYST_ARMOR_MODEL));
        TEST_CATALYST_ARMOR_MODEL = new CatalystArmorModel<>(bake.bakeLayer(ModModelLayers.TEST_CATALYST_ARMOR_MODEL));
    }

    public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
        if (!init) initializedModels();
        Item item = itemStack.getItem();

        if (item == EItems.STEEL_HELMET.get() ||
                item == EItems.STEEL_CHESTPLATE.get() ||
                item == EItems.STEEL_BOOTS.get()||
                item == EItems.DIARKRITE_HELMET.get() ||
                item == EItems.DIARKRITE_CHESTPLATE.get() ||
                item == EItems.DIARKRITE_BOOTS.get()||
                item == EItems.ANTHEKTITE_HELMET.get() ||
                item == EItems.ANTHEKTITE_CHESTPLATE.get() ||
                item == EItems.ANTHEKTITE_BOOTS.get()) {
            return EXTENDED_ARMOR_MODEL;}
        if (item == EItems.STEEL_LEGGINGS.get() ||
                item == EItems.DIARKRITE_LEGGINGS.get() ||
                item == EItems.ANTHEKTITE_LEGGINGS.get()) {
            return EXTENDED_ARMOR_MODEL_LEGS;
        }

        if ((item == EItems.CATALYST_CHESTPLATE.get())) {
            return CATALYST_ARMOR_MODEL;
        }

        if ((item == EItems.TEST_CATALYST_CHESTPLATE.get())) {
            if (getEquippedCore(itemStack).isPresent()) {
                CatalystCore core = CustomRegistries.getCatalystCore(getEquippedCore(itemStack).get());
                return core.getHumanoidArmorModel(livingEntity, getEquippedCore(itemStack).get(), equipmentSlot, TEST_CATALYST_ARMOR_MODEL);
            }
            return TEST_CATALYST_ARMOR_MODEL;
        }

        return original;
    }
}