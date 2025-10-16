package net.nokunami.elementus.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.armortrim.ArmorTrim;
import net.nokunami.elementus.ElementusClient;
import net.nokunami.elementus.common.item.unique.TestCatalystArmorItem;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.nokunami.elementus.common.item.unique.CatalystItemUtil.getEquippedCore;

@Mixin(HumanoidArmorLayer.class)
public class HumanoidArmorLayerMixin<T extends LivingEntity, A extends HumanoidModel<T>> {
    // Credits to Xaidee of TeamGelena

    @Shadow
    @Final
    private TextureAtlas armorTrimAtlas;

    @Inject(method = "renderArmorPiece", at = @At("HEAD"), cancellable = true)
    public void renderCatalystArmor(PoseStack poseStack, MultiBufferSource bufferSource, LivingEntity entity, EquipmentSlot slot, int packedLight, HumanoidModel<? extends LivingEntity> model, CallbackInfo ci) {
        var accessor = (HumanoidArmorLayerAccessor) this;
        var self = (HumanoidArmorLayer) (Object) this;

        ItemStack itemStack = entity.getItemBySlot(slot);
        Item item = itemStack.getItem();
        if (item instanceof TestCatalystArmorItem armorItem) {
            if (armorItem.getEquipmentSlot() == slot) {
                var parent = (HumanoidModel) self.getParentModel();
                parent.copyPropertiesTo(model);
                accessor.invokeSetPartVisibility(model, slot);
                Model catalystArmorModel = accessor.invokeGetArmorModelHook(entity, itemStack, slot, model);
                Model armorTrimModelReplacement = elementus$getCatalystTrimModelHook(entity, itemStack, slot, parent);
                boolean usesInnerModel = accessor.invokeUsesInnerModel(slot);
                accessor.invokeRenderModel(poseStack, bufferSource, packedLight, armorItem, catalystArmorModel, usesInnerModel, 1.0F, 1.0F, 1.0F, accessor.invokeGetArmorResource(entity, itemStack, slot, null));
                if (getEquippedCore(itemStack).isPresent())
                ArmorTrim.getTrim(entity.level().registryAccess(), itemStack).ifPresent((trim) -> catalyst$renderTrim(armorItem.getMaterial(), poseStack, bufferSource, LightTexture.FULL_BRIGHT, trim, armorTrimModelReplacement));
                else ArmorTrim.getTrim(entity.level().registryAccess(), itemStack).ifPresent((trim) -> catalyst$renderTrim(armorItem.getMaterial(), poseStack, bufferSource, packedLight, trim, armorTrimModelReplacement));

                if (itemStack.hasFoil()) {
                    accessor.invokeRenderGlint(poseStack, bufferSource, packedLight, catalystArmorModel);
                }
                ci.cancel();
            }
        }
    }

    @Unique
    private void catalyst$renderTrim(ArmorMaterial armorMaterial, PoseStack poseStack, MultiBufferSource buffer, int packedLight, ArmorTrim trim, Model model) {
        TextureAtlasSprite textureatlassprite = this.armorTrimAtlas.getSprite(trim.outerTexture(armorMaterial));
        VertexConsumer vertexconsumer = textureatlassprite.wrap(buffer.getBuffer(Sheets.armorTrimsSheet()));
        model.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    }

    @Unique
    protected Model elementus$getCatalystTrimModelHook(LivingEntity entity, ItemStack itemStack, EquipmentSlot slot, HumanoidModel model) {
        return ElementusClient.getTrimArmorModel(entity, itemStack, slot, model);
    }

    @Inject(method = "getArmorModelHook", at = @At("RETURN"), cancellable = true, remap = false)
    public void getArmorModelHook(T entity, ItemStack itemStack, EquipmentSlot slot, A model, CallbackInfoReturnable<Model> cir) {
        if (itemStack.getItem() instanceof TestCatalystArmorItem) {
            Model model1 = ElementusClient.getArmorModel(entity, itemStack, slot, model);
            cir.setReturnValue(model1);
        }
    }
}
