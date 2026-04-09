package net.nokunami.elementus.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.armortrim.ArmorTrim;
import net.nokunami.elementus.EClient;
import net.nokunami.elementus.client.model.ArmorRenderProperties;
import net.nokunami.elementus.client.model.ICatalystArmorPostRenderer;
import net.nokunami.elementus.client.render.ERenderType;
import net.nokunami.elementus.common.item.unique.TestCatalystArmorItem;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.nokunami.elementus.common.item.unique.CatalystItemUtil.getTrimVisibility;
import static net.nokunami.elementus.common.registry.CustomRegistries.CatalystCoreHelper.CatalystCoreUtil;

@Mixin(HumanoidArmorLayer.class)
public class HumanoidArmorLayerMixin<T extends LivingEntity, A extends HumanoidModel<T>> {
    // Credits to Xaidee of TeamGelena

    @Shadow @Final private TextureAtlas armorTrimAtlas;

    @Inject(method = "renderArmorPiece", at = @At("HEAD"), cancellable = true)
    public void renderCatalystArmor(PoseStack poseStack, MultiBufferSource bufferSource, LivingEntity entity, EquipmentSlot slot, int packedLight, HumanoidModel<? extends LivingEntity> model, CallbackInfo ci) {
        var accessor = (HumanoidArmorLayerAccessor) this;
        var self = (HumanoidArmorLayer) (Object) this;

        ItemStack itemStack = entity.getItemBySlot(slot);
        Item item = itemStack.getItem();
        if (item instanceof TestCatalystArmorItem armorItem) {
            ci.cancel();
            if (armorItem.getEquipmentSlot() == slot) {
                var parent = (HumanoidModel) self.getParentModel();
                parent.copyPropertiesTo(model);
                accessor.invokeSetPartVisibility(model, slot);
                Model catalystArmorModel = accessor.invokeGetArmorModelHook(entity, itemStack, slot, model);
                Model armorTrimModel = elementus$getCatalystTrimModelHook(entity, itemStack, slot, parent);
//                boolean usesInnerModel = accessor.invokeUsesInnerModel(slot);
//                boolean isCoreEquipped = getEquippedCore(itemStack).isPresent();
                var core = CatalystCoreUtil(itemStack).getCore().getBaseTexture(itemStack, entity, slot, null);

                accessor.invokeRenderModel(poseStack, bufferSource, packedLight, armorItem, catalystArmorModel, false, 1, 1, 1, accessor.invokeGetArmorResource(entity, itemStack, slot, null));
                if (catalystArmorModel instanceof ICatalystArmorPostRenderer renderer) {
                    renderer.postRender(new ArmorRenderProperties(new ResourceLocation(core)), poseStack, bufferSource, entity, slot, packedLight, catalystArmorModel);
                }
                ArmorTrim.getTrim(entity.level().registryAccess(), itemStack).ifPresent((trim) -> catalyst$renderTrim(armorItem.getMaterial(), poseStack, bufferSource, packedLight, itemStack, trim, armorTrimModel));
//                ICatalystArmorPostRenderer.renderPost(new ArmorRenderProperties(new ResourceLocation(core)), poseStack, bufferSource, packedLight, entity, slot, catalystArmorModel);
                if (itemStack.hasFoil()) accessor.invokeRenderGlint(poseStack, bufferSource, packedLight, catalystArmorModel);

            }
        }
    }

    @Unique
    private void catalyst$renderTrim(ArmorMaterial armorMaterial, PoseStack poseStack, MultiBufferSource buffer, int packedLight, ItemStack stack, ArmorTrim trim, Model model) {
        if (getTrimVisibility(stack) < 2) {
            TextureAtlasSprite sprite = armorTrimAtlas.getSprite(trim.outerTexture(armorMaterial));
            VertexConsumer vertex = sprite.wrap(buffer.getBuffer(Sheets.armorTrimsSheet()));
            if (CatalystCoreUtil(stack).hasCore())  vertex = sprite.wrap(buffer.getBuffer(ERenderType.noShadingArmor(Sheets.ARMOR_TRIMS_SHEET)));
            model.renderToBuffer(poseStack, vertex, packedLight, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
        }
    }

    @Unique
    protected Model elementus$getCatalystTrimModelHook(LivingEntity entity, ItemStack itemStack, EquipmentSlot slot, HumanoidModel model) {
        return EClient.getTrimArmorModel(entity, itemStack, slot, model);
    }

    @Inject(method = "getArmorModelHook", at = @At("RETURN"), cancellable = true, remap = false)
    public void getArmorModelHook(T entity, ItemStack itemStack, EquipmentSlot slot, A model, CallbackInfoReturnable<Model> cir) {
        if (itemStack.getItem() instanceof TestCatalystArmorItem) {
            Model model1 = EClient.getArmorModel(entity, itemStack, slot, model);
            cir.setReturnValue(model1);
        }
    }
}