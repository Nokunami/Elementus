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
import net.minecraft.world.item.*;
import net.minecraft.world.item.armortrim.ArmorTrim;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(HumanoidArmorLayer.class)
public interface HumanoidArmorLayerAccessor {
    // Credits to Xaidee of TeamGelena, PssbleTrngle

    @Invoker
    Model invokeGetArmorModelHook(LivingEntity entity, ItemStack itemStack, EquipmentSlot slot, HumanoidModel<? extends LivingEntity> model);

    @Invoker
    void invokeRenderTrim(ArmorMaterial p_289690_, PoseStack p_289687_, MultiBufferSource p_289643_, int p_289683_, ArmorTrim p_289692_, Model p_289663_, boolean p_289651_);


    @Invoker
    void invokeSetPartVisibility(HumanoidModel<? extends LivingEntity> p_117126_, EquipmentSlot p_117127_);

    @Invoker
    boolean invokeUsesInnerModel(EquipmentSlot p_117129_);


    @Invoker
    ResourceLocation invokeGetArmorResource(Entity entity, ItemStack stack, EquipmentSlot slot, @Nullable String type);

    @Invoker
    void invokeRenderModel(PoseStack p_289664_, MultiBufferSource p_289689_, int p_289681_, ArmorItem p_289650_, Model p_289658_, boolean p_289668_, float p_289678_, float p_289674_, float p_289693_, ResourceLocation armorResource);

    @Invoker
    void invokeRenderGlint(PoseStack p_289673_, MultiBufferSource p_289654_, int p_289649_, Model p_289659_);
}