package net.nokunami.elementus.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.item.armortrim.ArmorTrim;
import net.nokunami.elementus.registry.ItemsRegistry;
import net.nokunami.elementus.item.armor.AnthektiteArmorItem;
import net.nokunami.elementus.item.armor.DiarkriteArmorItem;
import net.nokunami.elementus.item.armor.SteelArmorItem;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HumanoidArmorLayer.class)
public abstract class HumanoidArmorLayerMixin extends RenderLayer<LivingEntity, HumanoidModel<LivingEntity>> {
    // Credits to Xaidee of TeamGelena


    @Shadow @Final private TextureAtlas armorTrimAtlas;

    public HumanoidArmorLayerMixin(RenderLayerParent p_117346_) {
        super(p_117346_);
    }

    @Shadow protected abstract Model getArmorModelHook(LivingEntity entity, ItemStack itemStack, EquipmentSlot slot, HumanoidModel<? extends LivingEntity> model);

    @Shadow protected abstract void renderTrim(ArmorMaterial p_289690_, PoseStack p_289687_, MultiBufferSource p_289643_, int p_289683_, ArmorTrim p_289692_, Model p_289663_, boolean p_289651_);


    @Shadow protected abstract void setPartVisibility(HumanoidModel<? extends LivingEntity> p_117126_, EquipmentSlot p_117127_);

    @Shadow protected abstract boolean usesInnerModel(EquipmentSlot p_117129_);



    @Shadow public abstract ResourceLocation getArmorResource(Entity entity, ItemStack stack, EquipmentSlot slot, @Nullable String type);

    @Shadow protected abstract void renderModel(PoseStack p_289664_, MultiBufferSource p_289689_, int p_289681_, ArmorItem p_289650_, Model p_289658_, boolean p_289668_, float p_289678_, float p_289674_, float p_289693_, ResourceLocation armorResource);

    @Shadow protected abstract void renderGlint(PoseStack p_289673_, MultiBufferSource p_289654_, int p_289649_, Model p_289659_);

    @Inject(method = "renderArmorPiece", at = @At("HEAD"), cancellable = true)
    public void renderElementusArmorPiece(PoseStack poseStack, MultiBufferSource bufferSource, LivingEntity entity, EquipmentSlot slot, int packedLight, HumanoidModel<? extends LivingEntity> model, CallbackInfo ci) {
        ItemStack itemStack = entity.getItemBySlot(slot);
        Item item = itemStack.getItem();
        if (item instanceof SteelArmorItem armorItem) {
            if (armorItem.getEquipmentSlot() == slot) {
                this.getParentModel().copyPropertiesTo((HumanoidModel<LivingEntity>) model);
                setPartVisibility(model, slot);
                Model steelArmorModel = getArmorModelHook(entity, itemStack, slot, model);
                boolean usesInnerModel = usesInnerModel(slot);
                renderModel(poseStack, bufferSource, packedLight, armorItem, steelArmorModel, usesInnerModel, 1.0F, 1.0F, 1.0F, getArmorResource(entity, itemStack, slot, null));

                Item baseArmorItem = (
                        item == ItemsRegistry.STEEL_HELEMT.get()  ? Items.IRON_HELMET :
                                item == ItemsRegistry.STEEL_CHESTPLATE.get() ? Items.IRON_CHESTPLATE :
                                        item == ItemsRegistry.STEEL_LEGGINGS.get() ? Items.IRON_LEGGINGS :
                                                Items.IRON_BOOTS
                );

                Model baseArmorModel = getArmorModelHook(entity, new ItemStack(baseArmorItem), slot, model);

                ArmorTrim.getTrim(entity.level().registryAccess(), itemStack).ifPresent((armorTrim) -> this.renderTrim(armorItem.getMaterial(), poseStack, bufferSource, packedLight, armorTrim, baseArmorModel, usesInnerModel));
                if (itemStack.hasFoil()) {
                    this.renderGlint(poseStack, bufferSource, packedLight, steelArmorModel);
                }
                ci.cancel();
            }
        }
        if (item instanceof AnthektiteArmorItem armorItem) {
            if (armorItem.getEquipmentSlot() == slot) {
                this.getParentModel().copyPropertiesTo((HumanoidModel<LivingEntity>) model);
                setPartVisibility(model, slot);
                Model AnthektiteArmorModel = getArmorModelHook(entity, itemStack, slot, model);
                boolean usesInnerModel = usesInnerModel(slot);
                renderModel(poseStack, bufferSource, packedLight, armorItem, AnthektiteArmorModel, usesInnerModel, 1.0F, 1.0F, 1.0F, getArmorResource(entity, itemStack, slot, null));

                Item baseArmorItem = (
                        item == ItemsRegistry.ANTHEKTITE_HELEMT.get()  ? Items.IRON_HELMET :
                                item == ItemsRegistry.ANTHEKTITE_CHESTPLATE.get() ? Items.IRON_CHESTPLATE :
                                        item == ItemsRegistry.ANTHEKTITE_LEGGINGS.get() ? Items.IRON_LEGGINGS :
                                                 Items.IRON_BOOTS
                );

                Model baseArmorModel = getArmorModelHook(entity, new ItemStack(baseArmorItem), slot, model);

                ArmorTrim.getTrim(entity.level().registryAccess(), itemStack).ifPresent((armorTrim) -> this.renderTrim(armorItem.getMaterial(), poseStack, bufferSource, packedLight, armorTrim, baseArmorModel, usesInnerModel));
                if (itemStack.hasFoil()) {
                    this.renderGlint(poseStack, bufferSource, packedLight, AnthektiteArmorModel);
                }
                ci.cancel();
            }
        }
        if (item instanceof DiarkriteArmorItem armorItem) {
            if (armorItem.getEquipmentSlot() == slot) {
                this.getParentModel().copyPropertiesTo((HumanoidModel<LivingEntity>) model);
                setPartVisibility(model, slot);
                Model DiarkriteArmorModel = getArmorModelHook(entity, itemStack, slot, model);
                boolean usesInnerModel = usesInnerModel(slot);
                renderModel(poseStack, bufferSource, packedLight, armorItem, DiarkriteArmorModel, usesInnerModel, 1.0F, 1.0F, 1.0F, getArmorResource(entity, itemStack, slot, null));

                Item baseArmorItem = (
                        item == ItemsRegistry.DIARKRITE_HELEMT.get()  ? Items.IRON_HELMET :
                                item == ItemsRegistry.DIARKRITE_CHESTPLATE.get() ? Items.IRON_CHESTPLATE :
                                        item == ItemsRegistry.DIARKRITE_LEGGINGS.get() ? Items.IRON_LEGGINGS :
                                                Items.IRON_BOOTS
                );

                Model baseArmorModel = getArmorModelHook(entity, new ItemStack(baseArmorItem), slot, model);

                ArmorTrim.getTrim(entity.level().registryAccess(), itemStack).ifPresent((armorTrim) -> this.renderTrim(armorItem.getMaterial(), poseStack, bufferSource, packedLight, armorTrim, baseArmorModel, usesInnerModel));
                if (itemStack.hasFoil()) {
                    this.renderGlint(poseStack, bufferSource, packedLight, DiarkriteArmorModel);
                }
                ci.cancel();
            }
        }
    }
}