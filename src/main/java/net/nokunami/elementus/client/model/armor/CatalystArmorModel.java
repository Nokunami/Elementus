package net.nokunami.elementus.client.model.armor;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.nokunami.elementus.client.model.ArmorRenderProperties;
import net.nokunami.elementus.client.model.ICatalystArmorPostRenderer;
import net.nokunami.elementus.client.render.ERenderType;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CatalystArmorModel<T extends LivingEntity> extends HumanoidModel<T> implements ICatalystArmorPostRenderer {
    public final ModelPart bodyEmissive;
    public final ModelPart core;
    public final ModelPart rightLegPart;
    public final ModelPart leftLegPart;
    public final ModelPart rightArmEmissive;
    public final ModelPart leftArmEmissive;
    public final ModelPart rightLegEmissive;
    public final ModelPart leftLegEmissive;

	public CatalystArmorModel(ModelPart root) {
        super(root);
        bodyEmissive = root.getChild("bodyEmissive");
        core = root.getChild("core");
        rightLegPart = root.getChild("rightLeg");
        leftLegPart = root.getChild("leftLeg");
        rightArmEmissive = root.getChild("rightArmEmissive");
        leftArmEmissive = root.getChild("leftArmEmissive");
        rightLegEmissive = root.getChild("rightLegEmissive");
        leftLegEmissive = root.getChild("leftLegEmissive");
	}

	public static LayerDefinition create() {
		MeshDefinition meshdefinition = HumanoidModel.createMesh(new CubeDeformation(1), 0);
		PartDefinition partdefinition = meshdefinition.getRoot();

        partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.ZERO);
        partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);

        partdefinition.addOrReplaceChild("core", CubeListBuilder.create()
                .texOffs(0, 0).addBox(-3, 0.25F, -3.5F, 6, 6, 1, new CubeDeformation(-0.25F))
                .texOffs(0, 7).addBox(-3, 0.25F, -4, 6, 6, 1, new CubeDeformation(-0.25F)),
                PartPose.offset(0, 0, 0));
        partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create()
                .texOffs(40, 16).addBox(-3, -2, -2, 4, 12, 4, new CubeDeformation(1.1F))
                .texOffs(40, 0).addBox(-3, -2, -2, 4, 12, 4, new CubeDeformation(0.65F)),
                PartPose.offset(-5, 2, 0));
        partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create()
                .texOffs(40, 16).mirror().addBox(-1, -2, -2, 4, 12, 4, new CubeDeformation(1.1F)).mirror(false)
                .texOffs(40, 0).mirror().addBox(-1, -2, -2, 4, 12, 4, new CubeDeformation(0.65F)).mirror(false),
                PartPose.offset(5, 2, 0));
        partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.offset(-2, 12, 0));
        partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.offset(2, 12, 0));

        partdefinition.addOrReplaceChild("rightLeg", CubeListBuilder.create()
                .texOffs(0, 16).addBox(-1.9F, 0, -2, 4, 12, 4, new CubeDeformation(0.6F)), PartPose.offset(-2, 12, 0));
		partdefinition.addOrReplaceChild("leftLeg", CubeListBuilder.create()
                .texOffs(0, 16).mirror().addBox(-2.1F, 0, -2, 4, 12, 4, new CubeDeformation(0.6F)).mirror(false), PartPose.offset(2, 12, 0));

		partdefinition.addOrReplaceChild("bodyEmissive", CubeListBuilder.create()
                .texOffs(16, 48).addBox(-4, 0, -2, 8, 12, 4, new CubeDeformation(1)),
                PartPose.offset(0, 0, 0));
        partdefinition.addOrReplaceChild("rightArmEmissive", CubeListBuilder.create()
                .texOffs(40, 48).addBox(-3, -2, -2, 4, 12, 4, new CubeDeformation(1.1F))
                .texOffs(40, 32).addBox(-3, -2, -2, 4, 12, 4, new CubeDeformation(0.65F)),
                PartPose.offset(-5, 2, 0));
        partdefinition.addOrReplaceChild("leftArmEmissive", CubeListBuilder.create()
                .texOffs(40, 48).mirror().addBox(-1, -2, -2, 4, 12, 4, new CubeDeformation(1.1F)).mirror(false)
                .texOffs(40, 32).mirror().addBox(-1, -2, -2, 4, 12, 4, new CubeDeformation(0.65F)).mirror(false),
                PartPose.offset(5, 2, 0));
        partdefinition.addOrReplaceChild("rightLegEmissive", CubeListBuilder.create()
                .texOffs(0, 48).addBox(-1.9F, 0, -2, 4, 12, 4, new CubeDeformation(0.6F)), PartPose.offset(-2, 12, 0));
        partdefinition.addOrReplaceChild("leftLegEmissive", CubeListBuilder.create()
                .texOffs(0, 48).mirror().addBox(-2.1F, 0, -2, 4, 12, 4, new CubeDeformation(0.6F)).mirror(false), PartPose.offset(2, 12, 0));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

    @Override
    public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        super.renderToBuffer(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        bodyEmissive.copyFrom(body);
        bodyEmissive.render(poseStack, buffer, LightTexture.FULL_BRIGHT, packedOverlay);
        core.copyFrom(body);
        core.render(poseStack, buffer, LightTexture.FULL_BRIGHT, packedOverlay);
        leftArmEmissive.copyFrom(leftArm);
        leftArmEmissive.render(poseStack, buffer, LightTexture.FULL_BRIGHT, packedOverlay);
        rightArmEmissive.copyFrom(rightArm);
        rightArmEmissive.render(poseStack, buffer, LightTexture.FULL_BRIGHT, packedOverlay);
        leftLegPart.copyFrom(leftLeg);
        leftLegPart.render(poseStack, buffer, packedLight, packedOverlay);
        rightLegPart.copyFrom(rightLeg);
        rightLegPart.render(poseStack, buffer, packedLight, packedOverlay);
        leftLegEmissive.copyFrom(leftLeg);
        leftLegEmissive.render(poseStack, buffer, LightTexture.FULL_BRIGHT, packedOverlay);
        rightLegEmissive.copyFrom(rightLeg);
        rightLegEmissive.render(poseStack, buffer, LightTexture.FULL_BRIGHT, packedOverlay);
    }

    @Override
    public List<ModelPart> getNonEmissiveParts() {
        return List.of(head, hat, body, leftArm, rightArm, leftLeg, rightLeg, leftLegPart, rightLegPart);
    }

    @Override
    public List<ModelPart> getEmissiveParts() {
        return List.of(core, bodyEmissive, leftArmEmissive, rightArmEmissive, leftLegEmissive, rightLegEmissive);
    }

    @Override
    public void postRender(ArmorRenderProperties properties, PoseStack poseStack, MultiBufferSource bufferSource, LivingEntity entity, EquipmentSlot slot, int packedLight, Model model) {
        onlyEmissive();
        VertexConsumer buffer = bufferSource.getBuffer(ERenderType.noShadingArmor(properties.location()));
        model.renderToBuffer(poseStack, buffer, packedLight, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
        onlyNonEmissive();
    }
}