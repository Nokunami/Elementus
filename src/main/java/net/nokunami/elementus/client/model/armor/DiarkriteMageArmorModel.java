package net.nokunami.elementus.client.model.armor;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class DiarkriteMageArmorModel extends HumanoidModel {

	public DiarkriteMageArmorModel(ModelPart root) {
		super(root);
	}

	public static LayerDefinition createArmorLayer(CubeDeformation deformation) {
		MeshDefinition meshdefinition = HumanoidModel.createMesh(deformation, 0.0F);
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition head = partdefinition.getChild("head");
		PartDefinition body = partdefinition.getChild("body");
		PartDefinition rightArm = partdefinition.getChild("right_arm");
		PartDefinition leftArm = partdefinition.getChild("left_arm");
		PartDefinition rightLeg = partdefinition.getChild("right_leg");
		PartDefinition leftLeg = partdefinition.getChild("left_leg");

		head.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 32).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.9F))
		.texOffs(32, 32).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		head.addOrReplaceChild("SmallTip_r1", CubeListBuilder.create().texOffs(80, 78).addBox(-2.0F, -5.25F, 0.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -14.75F, 0.0F, -0.8727F, 0.0F, 0.0F));

		head.addOrReplaceChild("Tip_r1", CubeListBuilder.create().texOffs(72, 87).addBox(-3.0F, -5.75F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.0F, 0.0F, -0.4363F, 0.0F, 0.0F));

		head.addOrReplaceChild("Base_r1", CubeListBuilder.create().texOffs(56, 99).addBox(-5.0F, -1.9F, -5.0F, 10.0F, 2.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(32, 111).addBox(-8.0F, 0.0F, -8.0F, 16.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.0F, 0.0F, -0.0873F, 0.0F, 0.0F));

		body.addOrReplaceChild("Chestplate", CubeListBuilder.create().texOffs(0, 48).addBox(0.0F, 0.0F, 0.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.7F))
		.texOffs(24, 48).addBox(0.0F, 0.0F, 0.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offset(-4.0F, 0.0F, -2.0F));

		body.addOrReplaceChild("RightFauld_r1", CubeListBuilder.create().texOffs(0, 96).addBox(-3.5F, 10.0F, 0.0F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.8F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, 0.0F, 0.0F, 0.0873F));

		body.addOrReplaceChild("LeftFauld_r1", CubeListBuilder.create().texOffs(0, 96).mirror().addBox(0.5F, 10.0F, 0.0F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.8F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, 0.0F, 0.0F, -0.0873F));

		rightArm.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(48, 64).addBox(0.0F, 0.0F, 0.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.27F)), PartPose.offset(-3.0F, -2.0F, -2.0F));

		rightArm.addOrReplaceChild("RightClothStrips_r1", CubeListBuilder.create().texOffs(32, 64).addBox(0.0F, 0.0F, 0.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.65F)), PartPose.offsetAndRotation(-3.0F, -2.0F, -2.0F, 0.0F, 0.0F, 0.0436F));

		rightArm.addOrReplaceChild("RightUnderShoulderPlate_r1", CubeListBuilder.create().texOffs(16, 64).addBox(0.0F, -0.5F, 0.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.75F))
		.texOffs(0, 64).addBox(0.0F, -0.5F, 0.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.95F)), PartPose.offsetAndRotation(-4.0F, -2.0F, -2.0F, 0.0F, 0.0F, -0.0873F));

		leftArm.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(48, 64).mirror().addBox(0.0F, 0.0F, 0.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.27F)).mirror(false), PartPose.offset(-1.0F, -2.0F, -2.0F));

		leftArm.addOrReplaceChild("LeftClothStrips_r1", CubeListBuilder.create().texOffs(32, 64).mirror().addBox(0.0F, 0.0F, 0.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.65F)).mirror(false), PartPose.offsetAndRotation(-1.0F, -2.0F, -2.0F, 0.0F, 0.0F, -0.0436F));

		leftArm.addOrReplaceChild("UnderArmLayer_r1", CubeListBuilder.create().texOffs(16, 64).mirror().addBox(0.0F, -0.5F, 0.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.75F)).mirror(false)
		.texOffs(0, 64).mirror().addBox(0.0F, -0.5F, 0.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.95F)).mirror(false), PartPose.offsetAndRotation(0.0F, -2.0F, -2.0F, 0.0F, 0.0F, 0.0873F));

		body.addOrReplaceChild("LeggingsPart", CubeListBuilder.create().texOffs(48, 48).addBox(0.0F, 0.0F, 0.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.45F)), PartPose.offset(-4.0F, 0.0F, -2.0F));

		rightLeg.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(0, 80).addBox(0.0F, 0.0F, 0.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.4F))
		.texOffs(16, 80).addBox(0.0F, 0.0F, 0.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.27F)), PartPose.offset(-2.0F, 0.0F, -2.0F));

		leftLeg.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(0, 80).mirror().addBox(0.0F, 0.0F, 0.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.4F)).mirror(false)
		.texOffs(16, 80).mirror().addBox(0.0F, 0.0F, 0.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.27F)).mirror(false), PartPose.offset(-2.0F, 0.0F, -2.0F));

		rightLeg.addOrReplaceChild("RightBoot", CubeListBuilder.create().texOffs(32, 80).addBox(0.0F, -0.5F, 0.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.9F))
		.texOffs(48, 80).addBox(0.0F, -0.5F, 0.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.75F)), PartPose.offset(-2.0F, 0.0F, -2.0F));

		leftLeg.addOrReplaceChild("LeftBoot", CubeListBuilder.create().texOffs(32, 80).mirror().addBox(0.0F, -0.5F, 0.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.9F)).mirror(false)
		.texOffs(48, 80).mirror().addBox(0.0F, -0.5F, 0.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.75F)).mirror(false), PartPose.offset(-2.0F, 0.0F, -2.0F));

		return LayerDefinition.create(meshdefinition, 96, 128);
	}
}