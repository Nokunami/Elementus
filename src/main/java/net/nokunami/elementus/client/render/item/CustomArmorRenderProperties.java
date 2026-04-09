package net.nokunami.elementus.client.render.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.nokunami.elementus.client.model.geom.EModelLayers;
import net.nokunami.elementus.client.model.armor.CatalystArmorModel;
import net.nokunami.elementus.client.model.armor.CatalystBaseModel;
import net.nokunami.elementus.client.model.armor.ExtendedArmorModel;
import net.nokunami.elementus.common.catalystCore.core.CatalystCore;
import net.nokunami.elementus.common.registry.CustomRegistries;
import net.nokunami.elementus.common.registry.EItems;
import org.jetbrains.annotations.NotNull;

import static net.nokunami.elementus.common.item.unique.CatalystItemUtil.getEquippedCore;
import static net.nokunami.elementus.common.registry.CustomRegistries.CatalystCoreHelper.CatalystCoreUtil;

@OnlyIn(Dist.CLIENT)
public class CustomArmorRenderProperties implements IClientItemExtensions {
    private static boolean init;
    public static ExtendedArmorModel<?> EXTENDED_ARMOR_MODEL;
    public static ExtendedArmorModel<?> EXTENDED_ARMOR_MODEL_LEGS;
    public static CatalystBaseModel<LivingEntity> CATALYST_ARMOR_MODEL;
    public static CatalystArmorModel<LivingEntity> TEST_CATALYST_ARMOR_MODEL;

    public static void initializedModels() {
        init = true;
        EntityModelSet bake = Minecraft.getInstance().getEntityModels();
        EXTENDED_ARMOR_MODEL = new ExtendedArmorModel<>(bake.bakeLayer(EModelLayers.EXTENDED_ARMOR_MODEL));
        EXTENDED_ARMOR_MODEL_LEGS = new ExtendedArmorModel<>(bake.bakeLayer(EModelLayers.EXTENDED_ARMOR_MODEL_LEGS));
        CATALYST_ARMOR_MODEL = new CatalystBaseModel<>(bake.bakeLayer(EModelLayers.CATALYST_ARMOR_MODEL));
        TEST_CATALYST_ARMOR_MODEL = new CatalystArmorModel<>(bake.bakeLayer(EModelLayers.TEST_CATALYST_ARMOR_MODEL));
    }

    public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
        if (!init) initializedModels();
        Item item = itemStack.getItem();

        if (item == EItems.ASTALITE_HELMET.get() ||
                item == EItems.ASTALITE_CHESTPLATE.get() ||
                item == EItems.ASTALITE_BOOTS.get()||
                item == EItems.DIARKRITE_HELMET.get() ||
                item == EItems.DIARKRITE_CHESTPLATE.get() ||
                item == EItems.DIARKRITE_BOOTS.get()||
                item == EItems.ANTHEKTITE_HELMET.get() ||
                item == EItems.ANTHEKTITE_CHESTPLATE.get() ||
                item == EItems.ANTHEKTITE_BOOTS.get()) {
            return EXTENDED_ARMOR_MODEL;}
        if (item == EItems.ASTALITE_LEGGINGS.get() ||
                item == EItems.DIARKRITE_LEGGINGS.get() ||
                item == EItems.ANTHEKTITE_LEGGINGS.get()) {
            return EXTENDED_ARMOR_MODEL_LEGS;
        }

        var core = CatalystCoreUtil(itemStack);
        if ((core.getChestplate().getItem() == EItems.CATALYST_CHESTPLATE.get())) {
//            if (getEquippedCore(itemStack).isPresent()) {
            if (core.hasCore()) {
//                CatalystCore core = CustomRegistries.getCatalystCore(getEquippedCore(itemStack).get());
                return core.getCore().getHumanoidArmorModel(livingEntity, core.getCoreStack(), equipmentSlot, TEST_CATALYST_ARMOR_MODEL);
            }
            return TEST_CATALYST_ARMOR_MODEL;
        }

//        if ((item == EItems.TEST_CATALYST_CHESTPLATE.get())) {
//            if (getEquippedCore(itemStack).isPresent()) {
////                CatalystCore core = CustomRegistries.getCatalystCore(getEquippedCore(itemStack).get());
//                return core.getHumanoidArmorModel(livingEntity, getEquippedCore(itemStack).get(), equipmentSlot, TEST_CATALYST_ARMOR_MODEL);
//            }
//            return TEST_CATALYST_ARMOR_MODEL;
//        }

        return original;
    }
}