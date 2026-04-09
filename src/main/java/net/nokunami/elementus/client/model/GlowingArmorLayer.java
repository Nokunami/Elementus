package net.nokunami.elementus.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;

//public class GlowingArmorLayer<T extends LivingEntity, M extends HumanoidModel<T>, A extends HumanoidModel<T>> extends HumanoidArmorLayer<T, M, A>
//{
//    private final A innerModel;
//    private final A outerModel;
//    private final ResourceLocation resourceLocation;
//    private final ResourceLocation eyeLocation;
//
//    public GlowingArmorLayer(RenderLayerParent<T, M> p_117075_, A p_117076_, A p_117077_, ResourceLocation resourceLocation, ResourceLocation eyeLocation, ModelManager modelManager)
//    {
//        super(p_117075_, p_117076_, p_117077_, modelManager);
//        this.innerModel = p_117076_;
//        this.outerModel = p_117077_;
//        this.resourceLocation = resourceLocation;
//        this.eyeLocation = eyeLocation;
//    }
//
//    @Override
//    public void render(PoseStack p_117096_, MultiBufferSource p_117097_, int p_117098_, T p_117099_, float p_117100_, float p_117101_, float p_117102_, float p_117103_, float p_117104_, float p_117105_)
//    {
//        this.renderArmorPiece(p_117096_, p_117097_, p_117099_, EquipmentSlot.CHEST, p_117098_, this.getArmorModel(EquipmentSlot.CHEST));
//        this.renderArmorPiece(p_117096_, p_117097_, p_117099_, EquipmentSlot.LEGS, p_117098_, this.getArmorModel(EquipmentSlot.LEGS));
//        this.renderArmorPiece(p_117096_, p_117097_, p_117099_, EquipmentSlot.FEET, p_117098_, this.getArmorModel(EquipmentSlot.FEET));
//        this.renderArmorPiece(p_117096_, p_117097_, p_117099_, EquipmentSlot.HEAD, p_117098_, this.getArmorModel(EquipmentSlot.HEAD));
//    }
//
//    private void renderArmorPiece(PoseStack p_117119_, MultiBufferSource p_117120_, T p_117121_, EquipmentSlot p_117122_, int p_117123_, A p_117124_)
//    {
//        ItemStack itemstack = p_117121_.getItemBySlot(p_117122_);
//        if(itemstack.getItem() instanceof IceScourgeArmorItem)
//        {
//            ArmorItem armoritem = (ArmorItem)itemstack.getItem();
//            if(armoritem.getEquipmentSlot() == p_117122_)
//            {
//                this.getParentModel().copyPropertiesTo(p_117124_);
//                this.setPartVisibility(p_117124_, p_117122_);
//                Model model = this.getArmorModelHook(p_117121_, itemstack, p_117122_, p_117124_);
//                boolean flag1 = itemstack.hasFoil();
//                this.renderModel(p_117119_, p_117120_, p_117123_, flag1, model, 1.0F, 1.0F, 1.0F, this.resourceLocation, this.eyeLocation);
//            }
//        }
//    }
//
//    private boolean usesInnerModel(EquipmentSlot p_117129_)
//    {
//        return p_117129_ == EquipmentSlot.LEGS;
//    }
//
//    private A getArmorModel(EquipmentSlot p_117079_)
//    {
//        return (A)(this.usesInnerModel(p_117079_) ? this.innerModel : this.outerModel);
//    }
//
//    private void renderModel(PoseStack p_117107_, MultiBufferSource p_117108_, int p_117109_, boolean p_117111_, net.minecraft.client.model.Model p_117112_, float p_117114_, float p_117115_, float p_117116_, ResourceLocation armorResource, ResourceLocation eyeResource)
//    {
//        VertexConsumer vertexconsumer = ItemRenderer.getArmorFoilBuffer(p_117108_, SimplestRenderType.eyesNoAlpha(eyeResource), false, p_117111_);
//        p_117112_.renderToBuffer(p_117107_, vertexconsumer, 240, OverlayTexture.NO_OVERLAY, p_117114_, p_117115_, p_117116_, 1.0F);
//    }
//}