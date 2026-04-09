package net.nokunami.elementus.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.armortrim.ArmorTrim;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import javax.annotation.Nullable;

@Mixin(HumanoidArmorLayer.class)
public interface HumanoidArmorLayerAccessor {
    // Credits to Xaidee of TeamGelena, PssbleTrngle

    @Invoker Model invokeGetArmorModelHook(LivingEntity entity, ItemStack itemStack, EquipmentSlot slot, HumanoidModel<? extends LivingEntity> model);

    @Invoker void invokeRenderTrim(ArmorMaterial material, PoseStack poseStack, MultiBufferSource buffer, int packedLight, ArmorTrim trim, Model model, boolean p_289651_);

    @Invoker void invokeSetPartVisibility(HumanoidModel<? extends LivingEntity> model, EquipmentSlot slot);

    @Invoker boolean invokeUsesInnerModel(EquipmentSlot slot);


    @Invoker ResourceLocation invokeGetArmorResource(Entity entity, ItemStack stack, EquipmentSlot slot, @Nullable String type);

    @Invoker void invokeRenderModel(PoseStack poseStack, MultiBufferSource buffer, int packedLight, ArmorItem armorItem, Model model, boolean innerModel, float red, float green, float blue, ResourceLocation armorResource);

    @Invoker void invokeRenderGlint(PoseStack poseStack, MultiBufferSource buffer, int packedLight, Model model);
}