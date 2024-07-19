package net.nokunami.elementus.client.render;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.BlockPos;
import net.minecraft.util.FastColor;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.SignBlock;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.entity.SignText;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.phys.Vec3;
import net.nokunami.elementus.client.ModAtlases;
import vectorwing.farmersdelight.common.block.state.CanvasSign;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.Objects;

//public class ModHangingSignRenderer extends ModSignRenderer {
//    private static final Vec3 TEXT_OFFSET = new Vec3(0.0, -0.3199999928474426, 0.0729999989271164);
//    private final Map<WoodType, SignModel> signModel;
//
//    public ModHangingSignRenderer(BlockEntityRendererProvider.Context context) {
//        super(context);
//        this.signModel = WoodType.values().collect(ImmutableMap.toImmutableMap(woodType -> woodType,
//                woodType -> new SignModel(context.bakeLayer(ModelLayers.createSignModelName(WoodType.SPRUCE)))));
//    }
//
//    public float getSignModelRenderScale() {
//        return 1.0F;
//    }
//
//    public float getSignTextRenderScale() {
//        return 0.8F;
//    }
//
//    public void render(SignBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
//        BlockState state = blockEntity.getBlockState();
//        SignBlock block = (SignBlock)state.getBlock();
//        WoodType woodType = SignBlock.getWoodType(block);
//        SignRenderer.SignModel model = this.signModel.get(woodType);
//        model.stick.visible = block instanceof StandingSignBlock;
//        this.renderSignWithText(blockEntity, poseStack, bufferSource, packedLight, packedOverlay, state, block, woodType, model);
//    }
//
//    protected void translateSign(PoseStack poseStack, float angle, BlockState state) {
//        poseStack.translate(0.5, 0.9375, 0.5);
//        poseStack.mulPose(Axis.YP.rotationDegrees(angle));
//        poseStack.translate(0.0F, -0.3125F, 0.0F);
//    }
//
//    protected void renderSignModel(PoseStack poseStack, int packedLight, int packedOverlay, Model model, VertexConsumer vertexConsumer) {
//        HangingSignRenderer.HangingSignModel hangingSignModel = (HangingSignRenderer.HangingSignModel)model;
//        hangingSignModel.root.render(poseStack, vertexConsumer, packedLight, packedOverlay);
//    }
//
//    Material getSignMaterial(WoodType pWoodType) {
//        return ModAtlases.getSignMaterial(pWoodType);
//    }
//
//    public int getCustomVerticalOffset() {
//        return 0;
//    }
//
//    Vec3 getTextOffset() {
//        return TEXT_OFFSET;
//    }
//}