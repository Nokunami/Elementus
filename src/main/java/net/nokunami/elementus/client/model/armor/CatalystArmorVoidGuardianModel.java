package net.nokunami.elementus.client.model.armor;// Made with Blockbench 5.1.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.github.L_Ender.cataclysm.client.render.CMRenderTypes;
import com.github.L_Ender.cataclysm.init.ModItems;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.nokunami.elementus.client.model.ArmorRenderProperties;
import net.nokunami.elementus.client.model.ICatalystArmorPostRenderer;
import net.nokunami.elementus.client.render.ERenderType;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static net.nokunami.elementus.ModChecker.cataclysm;

public class CatalystArmorVoidGuardianModel<T extends LivingEntity> extends HumanoidModel<T> implements ICatalystArmorPostRenderer {
	private final ModelPart body;
	private final ModelPart core;
	private final ModelPart rightLegPart;
	private final ModelPart leftLegPart;
	private final ModelPart bodyEmissive;
	private final ModelPart leftArmEmissive;
	private final ModelPart rightArmEmissive;
	private final ModelPart rightLegEmissive;
	private final ModelPart leftLegEmissive;

	public CatalystArmorVoidGuardianModel(ModelPart root) {
		super(root);
		body = root.getChild("body");
		core = root.getChild("core");
//		rightLegPart = root.getChild("rightLeg");
//		leftLegPart = root.getChild("leftLeg");
		rightLegPart = root.getChild("rightLegPart");
		leftLegPart = root.getChild("leftLegPart");
		bodyEmissive = root.getChild("bodyEmissive");
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

//		partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4, 0, -2, 8, 12, 4, new CubeDeformation(1)), PartPose.ZERO);
//		partdefinition.addOrReplaceChild("core", CubeListBuilder.create().texOffs(0, 0).addBox(-3, 0.25F, -3.5F, 6, 6, 1, new CubeDeformation(-0.25F))
//				.texOffs(0, 7).addBox(-3, 0.25F, -4, 6, 6, 1, new CubeDeformation(-0.25F)), PartPose.ZERO);
//
//		PartDefinition leftArm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(48, 16).mirror().addBox(-1, -2, -2, 4, 12, 4, new CubeDeformation(0.65F)).mirror(false), PartPose.offset(5, 2, 0));
//		leftArm.addOrReplaceChild("LeftArm2_r1", CubeListBuilder.create().texOffs(16, 0).mirror().addBox(-0.25F, -6.75F, -3, 6, 8, 6, new CubeDeformation(0.5F)).mirror(false)
//				.texOffs(40, 0).mirror().addBox(0.5F, -6.5F, -2.5F, 5, 6, 5, new CubeDeformation(0.5F)).mirror(false), PartPose.offsetAndRotation(0.75F, 3.25F, 0, 0, 0, -0.1309F));
//		leftArm.addOrReplaceChild("LeftCrystal_r1", CubeListBuilder.create().texOffs(0, 32).mirror().addBox(-1.5F, -4, -1.5F, 3, 4, 3, new CubeDeformation(0)).mirror(false), PartPose.offsetAndRotation(4.75F, -3.25F, 0, 0, 0, 0.6545F));
//
//		PartDefinition rightArm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(48, 16).addBox(-3, -2, -2, 4, 12, 4, new CubeDeformation(0.65F)), PartPose.offset(-5, 2, 0));
//		rightArm.addOrReplaceChild("RightArm2_r1", CubeListBuilder.create().texOffs(16, 0).addBox(-5.75F, -6.75F, -3, 6, 8, 6, new CubeDeformation(0.5F))
//				.texOffs(40, 0).addBox(-5.5F, -6.5F, -2.5F, 5, 6, 5, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(-0.75F, 3.25F, 0, 0, 0, 0.1309F));
//		rightArm.addOrReplaceChild("RightCrystal_r1", CubeListBuilder.create().texOffs(0, 32).addBox(-1.5F, -4, -1.5F, 3, 4, 3, new CubeDeformation(0)), PartPose.offsetAndRotation(-4.75F, -3.25F, 0, 0, 0, -0.6545F));
//
//		partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.offset(-2, 12, 0));
//		partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.offset(2, 12, 0));
//
//		partdefinition.addOrReplaceChild("rightLeg", CubeListBuilder.create().texOffs(0, 16).addBox(-1.9F, 0, -2, 4, 12, 4, new CubeDeformation(0.6F)), PartPose.offset(-2, 12, 0));
//		partdefinition.addOrReplaceChild("leftLeg", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2.1F, 0, -2, 4, 12, 4, new CubeDeformation(0.6F)).mirror(false), PartPose.offset(2, 12, 0));
//
//		partdefinition.addOrReplaceChild("bodyEmissive", CubeListBuilder.create().texOffs(16, 48).addBox(-4, 0, -2, 8, 12, 4, new CubeDeformation(1)), PartPose.ZERO);
//
//		PartDefinition rightArmEmissive = partdefinition.addOrReplaceChild("rightArmEmissive", CubeListBuilder.create().texOffs(48, 48).addBox(-3, -2, -2, 4, 12, 4, new CubeDeformation(0.65F)), PartPose.offset(-5, 2, 0));
//		rightArmEmissive.addOrReplaceChild("RightArm1_r1", CubeListBuilder.create().texOffs(40, 32).addBox(-5.5F, -6.5F, -2.5F, 5, 6, 5, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(-0.75F, 3.25F, 0, 0, 0, 0.1309F));
//
//		PartDefinition leftArmEmissive = partdefinition.addOrReplaceChild("leftArmEmissive", CubeListBuilder.create().texOffs(48, 48).mirror().addBox(-1, -2, -2, 4, 12, 4, new CubeDeformation(0.65F)).mirror(false), PartPose.offset(5, 2, 0));
//		leftArmEmissive.addOrReplaceChild("LeftArm1_r1", CubeListBuilder.create().texOffs(40, 32).mirror().addBox(0.5F, -6.5F, -2.5F, 5, 6, 5, new CubeDeformation(0.5F)).mirror(false), PartPose.offsetAndRotation(0.75F, 3.25F, 0, 0, 0, -0.1309F));
//
//		partdefinition.addOrReplaceChild("rightLegEmissive", CubeListBuilder.create().texOffs(0, 48).addBox(-1.9F, 0, -2, 4, 12, 4, new CubeDeformation(0.4F)), PartPose.offset(-2, 12, 0));
//		partdefinition.addOrReplaceChild("leftLegEmissive", CubeListBuilder.create().texOffs(0, 48).mirror().addBox(-2.1F, 0, -2, 4, 12, 4, new CubeDeformation(0.4F)).mirror(false), PartPose.offset(2, 12, 0));


		partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(1.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		partdefinition.addOrReplaceChild("core", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, 0.25F, -3.5F, 6.0F, 6.0F, 1.0F, new CubeDeformation(-0.25F))
				.texOffs(0, 7).addBox(-3.0F, 0.25F, -4.0F, 6.0F, 6.0F, 1.0F, new CubeDeformation(-0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(48, 16).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.65F)).mirror(false), PartPose.offset(5.0F, 2.0F, 0.0F));
		left_arm.addOrReplaceChild("LeftShoulderRim_r1", CubeListBuilder.create().texOffs(16, 0).mirror().addBox(-0.25F, -6.75F, -3.0F, 6.0F, 8.0F, 6.0F, new CubeDeformation(0.65F)).mirror(false)
				.texOffs(40, 0).mirror().addBox(0.5F, -6.5F, -2.5F, 5.0F, 6.0F, 5.0F, new CubeDeformation(0.65F)).mirror(false), PartPose.offsetAndRotation(0.25F, 3.25F, 0.0F, 0.0F, 0.0F, -0.1309F));

		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(48, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.65F)), PartPose.offset(-5.0F, 2.0F, 0.0F));
		right_arm.addOrReplaceChild("RightShoulderRim_r1", CubeListBuilder.create().texOffs(16, 0).addBox(-5.75F, -6.75F, -3.0F, 6.0F, 8.0F, 6.0F, new CubeDeformation(0.65F))
				.texOffs(40, 0).addBox(-5.5F, -6.5F, -2.5F, 5.0F, 6.0F, 5.0F, new CubeDeformation(0.65F)), PartPose.offsetAndRotation(-0.25F, 3.25F, 0.0F, 0.0F, 0.0F, 0.1309F));

		partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.offset(-2, 12, 0));
		partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.offset(2, 12, 0));

		partdefinition.addOrReplaceChild("rightLegPart", CubeListBuilder.create().texOffs(0, 16).addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.6F)), PartPose.offset(-2.0F, 12.0F, 0.0F));
		partdefinition.addOrReplaceChild("leftLegPart", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.6F)).mirror(false), PartPose.offset(2.0F, 12.0F, 0.0F));

		partdefinition.addOrReplaceChild("bodyEmissive", CubeListBuilder.create().texOffs(16, 48).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(1.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition leftArmEmissive = partdefinition.addOrReplaceChild("leftArmEmissive", CubeListBuilder.create().texOffs(48, 48).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.65F)).mirror(false), PartPose.offset(5.0F, 2.0F, 0.0F));
		leftArmEmissive.addOrReplaceChild("LeftShoulder_r1", CubeListBuilder.create().texOffs(40, 32).mirror().addBox(0.5F, -6.5F, -2.5F, 5.0F, 6.0F, 5.0F, new CubeDeformation(0.65F)).mirror(false), PartPose.offsetAndRotation(0.25F, 3.25F, 0.0F, 0.0F, 0.0F, -0.1309F));
		leftArmEmissive.addOrReplaceChild("LeftCrystal_r1", CubeListBuilder.create().texOffs(0, 32).mirror().addBox(-1.5F, -4.0F, -1.5F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.0F, -3.25F, 0.0F, 0.0F, 0.0F, 0.6545F));

		PartDefinition rightArmEmissive = partdefinition.addOrReplaceChild("rightArmEmissive", CubeListBuilder.create().texOffs(48, 48).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.65F)), PartPose.offset(-5.0F, 2.0F, 0.0F));
		rightArmEmissive.addOrReplaceChild("RightShoulder_r1", CubeListBuilder.create().texOffs(40, 32).addBox(-5.5F, -6.5F, -2.5F, 5.0F, 6.0F, 5.0F, new CubeDeformation(0.65F)), PartPose.offsetAndRotation(-0.25F, 3.25F, 0.0F, 0.0F, 0.0F, 0.1309F));
		rightArmEmissive.addOrReplaceChild("RightCrystal_r1", CubeListBuilder.create().texOffs(0, 32).addBox(-1.5F, -4.0F, -1.5F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -3.25F, 0.0F, 0.0F, 0.0F, -0.6545F));

		partdefinition.addOrReplaceChild("rightLegEmissive", CubeListBuilder.create().texOffs(0, 48).addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.4F)), PartPose.offset(-2.0F, 12.0F, 0.0F));
		partdefinition.addOrReplaceChild("leftLegEmissive", CubeListBuilder.create().texOffs(0, 48).mirror().addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.4F)).mirror(false), PartPose.offset(2.0F, 12.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(@NotNull T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		super.renderToBuffer(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
//		bodyEmissive.copyFrom(body);
//		bodyEmissive.render(poseStack, buffer, LightTexture.FULL_BRIGHT, packedOverlay);
//		core.copyFrom(body);
//		core.render(poseStack, buffer, LightTexture.FULL_BRIGHT, packedOverlay);
//		leftArmEmissive.copyFrom(leftArm);
//		leftArmEmissive.render(poseStack, buffer, LightTexture.FULL_BRIGHT, packedOverlay);
//		rightArmEmissive.copyFrom(rightArm);
//		rightArmEmissive.render(poseStack, buffer, LightTexture.FULL_BRIGHT, packedOverlay);
//		leftLegPart.copyFrom(leftLeg);
//		leftLegPart.render(poseStack, buffer, packedLight, packedOverlay);
//		rightLegPart.copyFrom(rightLeg);
//		rightLegPart.render(poseStack, buffer, packedLight, packedOverlay);
//		leftLegEmissive.copyFrom(leftLegPart);
//		leftLegEmissive.render(poseStack, buffer, LightTexture.FULL_BRIGHT, packedOverlay);
//		rightLegEmissive.copyFrom(rightLegPart);
//		rightLegEmissive.render(poseStack, buffer, LightTexture.FULL_BRIGHT, packedOverlay);

		leftLegPart.copyFrom(leftLeg);
		leftLegPart.render(poseStack, buffer, packedLight, packedOverlay);
		rightLegPart.copyFrom(rightLeg);
		rightLegPart.render(poseStack, buffer, packedLight, packedOverlay);

		bodyEmissive.copyFrom(body);
		core.copyFrom(body);
		leftArmEmissive.copyFrom(leftArm);
		rightArmEmissive.copyFrom(rightArm);
		leftLegEmissive.copyFrom(leftLegPart);
		rightLegEmissive.copyFrom(rightLegPart);
		getEmissiveParts().forEach(p -> p.render(poseStack, buffer, packedLight, packedOverlay));
	}

	@Override
	public List<ModelPart> getNonEmissiveParts() {
		return List.of(head, hat, body, leftArm, rightArm, leftLeg, rightLeg, leftLegPart, rightLegPart);
	}

	@Override
	public List<ModelPart> getEmissiveParts() {
		return ImmutableList.of(core, bodyEmissive, rightArmEmissive, leftArmEmissive, rightLegEmissive, leftLegEmissive);
	}

	protected ModelPart getArmEmissive(HumanoidArm arm) {
		return arm == HumanoidArm.LEFT ? leftArmEmissive : rightArmEmissive;
	}

	public void hideArm(HumanoidArm arm, ItemStack stack) {
		if (cataclysm) {
			boolean hide = stack.is(ModItems.GAUNTLET_OF_GUARD.get()) || stack.is(ModItems.GAUNTLET_OF_BULWARK.get()) || stack.is(ModItems.GAUNTLET_OF_MAELSTROM.get());
			getArm(arm).visible = hide;
			getArmEmissive(arm).visible = hide;
//			getArm(arm).x += 10;
		}
	}

	@Override
	public void postRender(ArmorRenderProperties properties, PoseStack poseStack, MultiBufferSource bufferSource, LivingEntity entity, EquipmentSlot slot, int packedLight, Model model) {
		if (cataclysm) {
			if (model instanceof CatalystArmorVoidGuardianModel<? extends LivingEntity> model1) {
				onlyEmissive();
				VertexConsumer buffer = bufferSource.getBuffer(ERenderType.noShadingArmor(properties.location()));
//				VertexConsumer buffer = bufferSource.getBuffer(ERenderType.cmTranslucentGlow(properties.location()));
				model1.renderToBuffer(poseStack, buffer, packedLight, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
				hideArm(entity.getMainArm(), entity.getItemInHand(InteractionHand.MAIN_HAND));
				hideArm(entity.getMainArm().getOpposite(), entity.getItemInHand(InteractionHand.OFF_HAND));
			}
		}
	}
}