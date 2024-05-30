package net.nokunami.elementus.client.render.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.nokunami.elementus.client.model.ModModelLayers;
import net.nokunami.elementus.client.model.armor.ExtendedArmorModel;
import net.nokunami.elementus.registry.ModItems;
import org.jetbrains.annotations.NotNull;

public class CustomArmorRenderProperties implements IClientItemExtensions {
    private static boolean init;
    public static ExtendedArmorModel EXTENDED_ARMOR_MODEL;
    public static ExtendedArmorModel EXTENDED_ARMOR_MODEL_LEGS;

    public static void initializedModels() {
        init = true;
        EXTENDED_ARMOR_MODEL = new ExtendedArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModModelLayers.EXTENDED_ARMOR_MODEL));
        EXTENDED_ARMOR_MODEL_LEGS = new ExtendedArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModModelLayers.EXTENDED_ARMOR_MODEL_LEGS));
    }

    public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
        if (!init) {
            initializedModels();
        }

        if (itemStack.getItem() == ModItems.STEEL_HELMET.get()) {
            return EXTENDED_ARMOR_MODEL;}
        if (itemStack.getItem() == ModItems.STEEL_CHESTPLATE.get()) {
            return EXTENDED_ARMOR_MODEL;}
        if (itemStack.getItem() == ModItems.STEEL_LEGGINGS.get()) {
            return EXTENDED_ARMOR_MODEL_LEGS;}
        if (itemStack.getItem() == ModItems.STEEL_BOOTS.get()) {
            return EXTENDED_ARMOR_MODEL;}

        if (itemStack.getItem() == ModItems.DIARKRITE_HELEMT.get()) {
            return EXTENDED_ARMOR_MODEL;}
        if (itemStack.getItem() == ModItems.DIARKRITE_CHESTPLATE.get()) {
            return EXTENDED_ARMOR_MODEL;}
        if (itemStack.getItem() == ModItems.DIARKRITE_LEGGINGS.get()) {
            return EXTENDED_ARMOR_MODEL_LEGS;}
        if (itemStack.getItem() == ModItems.DIARKRITE_BOOTS.get()) {
            return EXTENDED_ARMOR_MODEL;}

        if (itemStack.getItem() == ModItems.ANTHEKTITE_HELEMT.get()) {
            return EXTENDED_ARMOR_MODEL;}
        if (itemStack.getItem() == ModItems.ANTHEKTITE_CHESTPLATE.get()) {
            return EXTENDED_ARMOR_MODEL;}
        if (itemStack.getItem() == ModItems.ANTHEKTITE_LEGGINGS.get()) {
            return EXTENDED_ARMOR_MODEL_LEGS;}
        if (itemStack.getItem() == ModItems.ANTHEKTITE_BOOTS.get()) {
            return EXTENDED_ARMOR_MODEL;}

        return original;
    }
}
