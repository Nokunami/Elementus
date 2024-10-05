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

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.Objects;

//public class ModSignRenderer extends SignRenderer {
//    public static final Vec3 TEXT_OFFSET = new Vec3(0.0, 0.3333333432674408, 0.046666666865348816);
//    private static final int OUTLINE_RENDER_DISTANCE = Mth.square(16);
//    private final Map<WoodType, SignModel> signModel;
//    private final Font font;
//
//    public ModSignRenderer(BlockEntityRendererProvider.Context context) {
//        super(context);
//        this.signModel = WoodType.values().collect(ImmutableMap.toImmutableMap(woodType -> woodType,
//                woodType -> new SignModel(context.bakeLayer(ModelLayers.createSignModelName(WoodType.SPRUCE)))));
//        this.font = context.getFont();
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
//    protected void renderSignWithText(SignBlockEntity signBlockEntity, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay, BlockState state, SignBlock block, @Nullable WoodType woodType, Model model) {
//        poseStack.pushPose();
//        this.translateSign(poseStack, -block.getYRotationDegrees(state), state);
//        this.renderSign(poseStack, bufferSource, packedLight, packedOverlay, woodType, model);
//        this.renderSignText(signBlockEntity.getBlockPos(), signBlockEntity.getFrontText(), poseStack, bufferSource, packedLight, signBlockEntity.getTextLineHeight(), signBlockEntity.getMaxTextLineWidth(), true);
//        this.renderSignText(signBlockEntity.getBlockPos(), signBlockEntity.getBackText(), poseStack, bufferSource, packedLight, signBlockEntity.getTextLineHeight(), signBlockEntity.getMaxTextLineWidth(), false);
//        poseStack.popPose();
//    }
//
//    protected void translateSign(PoseStack poseStack, float angle, BlockState state) {
//        poseStack.translate(0.5F, 0.75F * this.getSignModelRenderScale(), 0.5F);
//        poseStack.mulPose(Axis.YP.rotationDegrees(angle));
//        if (!(state.getBlock() instanceof StandingSignBlock)) {
//            poseStack.translate(0.0F, -0.3125F, -0.4375F);
//        }
//
//    }
//
//    protected void renderSign(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay, @Nullable WoodType woodType, Model model) {
//        poseStack.pushPose();
//        float rootScale = this.getSignModelRenderScale();
//        poseStack.scale(rootScale, -rootScale, -rootScale);
//        Material material = this.getSignMaterial(woodType);
//        Objects.requireNonNull(model);
//        VertexConsumer vertexConsumer = material.buffer(bufferSource, model::renderType);
//        this.renderSignModel(poseStack, packedLight, packedOverlay, model, vertexConsumer);
//        poseStack.popPose();
//    }
//
//    protected void renderSignModel(PoseStack poseStack, int packedLight, int packedOverlay, Model model, VertexConsumer vertexConsumer) {
//        SignRenderer.SignModel signModel = (SignRenderer.SignModel)model;
//        signModel.root.render(poseStack, vertexConsumer, packedLight, packedOverlay);
//    }
//
//    protected void renderSignText(BlockPos pos, SignText text, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int textLineHeight, int maxTextLineWidth, boolean isFrontText) {
//        poseStack.pushPose();
//        this.translateSignText(poseStack, isFrontText, this.getTextOffset());
//        FormattedCharSequence[] formattedCharSequenceList = text.getRenderMessages(Minecraft.getInstance().isTextFilteringEnabled(), (component) -> {
//            List<FormattedCharSequence> list = this.font.split(component, maxTextLineWidth);
//            return list.isEmpty() ? FormattedCharSequence.EMPTY : (FormattedCharSequence)list.get(0);
//        });
//        int darkColor;
//        int baseColor;
//        boolean hasOutline;
//        int light;
//        if (text.hasGlowingText()) {
//            darkColor = getDarkColor(text, true);
//            baseColor = text.getColor().getTextColor();
//            hasOutline = isOutlineVisible(pos, baseColor);
//            light = 15728880;
//        } else {
//            darkColor = getDarkColor(text, false);
//            baseColor = darkColor;
//            hasOutline = false;
//            light = packedLight;
//        }
//
//        int verticalOffset = 2 * textLineHeight + this.getCustomVerticalOffset();
//
//        for(int i = 0; i < 4; ++i) {
//            FormattedCharSequence formattedCharSequence = formattedCharSequenceList[i];
//            float x = (float)(-this.font.width(formattedCharSequence) / 2);
//            float y = (float)(i * textLineHeight - verticalOffset);
//            if (hasOutline) {
//                this.font.drawInBatch8xOutline(formattedCharSequence, x, y, baseColor, darkColor, poseStack.last().pose(), bufferSource, light);
//            } else {
//                this.font.drawInBatch(formattedCharSequence, x, y, baseColor, false, poseStack.last().pose(), bufferSource, Font.DisplayMode.POLYGON_OFFSET, 0, light);
//            }
//        }
//
//        poseStack.popPose();
//    }
//
//    private void translateSignText(PoseStack poseStack, boolean isFrontText, Vec3 pos) {
//        if (!isFrontText) {
//            poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
//        }
//
//        float textScale = 0.015625F * this.getSignTextRenderScale();
//        poseStack.translate(pos.x, pos.y, pos.z);
//        poseStack.scale(textScale, -textScale, textScale);
//    }
//
//    public static boolean isOutlineVisible(BlockPos pos, int textColor) {
//        if (textColor == DyeColor.BLACK.getTextColor()) {
//            return true;
//        } else {
//            Minecraft minecraft = Minecraft.getInstance();
//            LocalPlayer localPlayer = minecraft.player;
//            if (localPlayer != null && minecraft.options.getCameraType().isFirstPerson() && localPlayer.isScoping()) {
//                return true;
//            } else {
//                Entity entity = minecraft.getCameraEntity();
//                return entity != null && entity.distanceToSqr(Vec3.atCenterOf(pos)) < (double)OUTLINE_RENDER_DISTANCE;
//            }
//        }
//    }
//
//    protected static int getDarkColor(SignText text, boolean isOutlineVisible) {
//        int textColor = text.getColor().getTextColor();
//        if (textColor == DyeColor.BLACK.getTextColor() && text.hasGlowingText()) {
//            return -988212;
//        } else {
//            double brightness = isOutlineVisible ? 0.4 : 0.6;
//            int red = (int)((double) FastColor.ARGB32.red(textColor) * brightness);
//            int green = (int)((double) FastColor.ARGB32.green(textColor) * brightness);
//            int blue = (int)((double) FastColor.ARGB32.blue(textColor) * brightness);
//            return FastColor.ARGB32.color(0, red, green, blue);
//        }
//    }
//
//    Vec3 getTextOffset() {
//        return TEXT_OFFSET;
//    }
//
//    public int getCustomVerticalOffset() {
//        return -1;
//    }
//
//    Material getSignMaterial(WoodType pWoodType) {
//        return ModAtlases.getSignMaterial(pWoodType);
//    }
//}
