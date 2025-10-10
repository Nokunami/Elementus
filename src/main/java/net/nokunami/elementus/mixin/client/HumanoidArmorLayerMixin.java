package net.nokunami.elementus.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
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
import net.nokunami.elementus.ElementusClient;
import net.nokunami.elementus.client.model.armor.CatalystArmorTrimModel;
import net.nokunami.elementus.common.catalystCore.core.CatalystCore;
import net.nokunami.elementus.common.item.unique.TestCatalystArmorItem;
import net.nokunami.elementus.common.registry.CustomRegistries;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.nokunami.elementus.Elementus.modLoc;
import static net.nokunami.elementus.common.item.unique.CatalystItemUtil.getEquippedCore;

@Mixin(HumanoidArmorLayer.class)
public class HumanoidArmorLayerMixin {
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
                ArmorTrim.getTrim(entity.level().registryAccess(), itemStack).ifPresent((trim) -> catalyst$renderTrim(armorItem.getMaterial(), poseStack, bufferSource, packedLight, trim, armorTrimModelReplacement));

                getEquippedCore(itemStack).ifPresent(core -> catalyst$EmissiveRender(core, poseStack, bufferSource, packedLight, catalystArmorModel));
//                catalyst$EmissiveRender(getEquippedCore(itemStack).get(), poseStack, bufferSource, packedLight, model);
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
    private void catalyst$EmissiveRender(ItemStack stack, PoseStack poseStack, MultiBufferSource buffer, int packedLight, Model model) {
        String path = CustomRegistries.getCatalystAbility(stack).getEmissiveTexture(stack);
        if (!path.matches("null")) {
            ResourceLocation rl = modLoc(path);
            VertexConsumer vc = buffer.getBuffer(RenderType.entitySmoothCutout(rl));
            model.renderToBuffer(poseStack, vc, packedLight, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
        }
    }

    @Unique
    protected Model elementus$getArmorModelHook(LivingEntity entity, ItemStack itemStack, EquipmentSlot slot, HumanoidModel model) {
        return ElementusClient.getArmorModel(entity, itemStack, slot, model);
    }

    @Unique
    protected Model elementus$getCatalystTrimModelHook(LivingEntity entity, ItemStack itemStack, EquipmentSlot slot, HumanoidModel model) {
        return ElementusClient.getTrimArmorModel(entity, itemStack, slot, model);
    }
}
