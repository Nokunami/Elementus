package net.nokunami.elementus.client.model.armor;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class SamuraiLightArmorModel extends HumanoidModel {

	public SamuraiLightArmorModel(ModelPart root) {
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

		head.addOrReplaceChild("Helmet", CubeListBuilder.create().texOffs(64, 0).addBox(-3.5F, -10.0F, -5.0F, 7.0F, 4.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(64, 14).addBox(-4.5F, -9.0F, -4.5F, 9.0F, 4.0F, 9.0F, new CubeDeformation(0.0F))
				.texOffs(87, 28).addBox(-3.5F, -5.5F, 4.75F, 7.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(64, 28).addBox(-4.0F, -6.0F, -6.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(64, 28).addBox(-4.0F, -6.0F, -5.5F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		head.addOrReplaceChild("rightHelmetPlate1_r1", CubeListBuilder.create().texOffs(106, 25).mirror().addBox(0.0F, -0.8F, 0.0F, 1.0F, 4.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(106, 39).mirror().addBox(-1.0F, 1.2F, 1.0F, 1.0F, 3.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(107, 28).mirror().addBox(-3.0F, -0.8F, 0.75F, 3.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(102, 28).mirror().addBox(-3.0F, -0.8F, 1.75F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-5.0F, -5.0F, -5.0F, 0.0F, 0.0F, 0.2618F));

		head.addOrReplaceChild("leftHelmetPlate1_r1", CubeListBuilder.create().texOffs(106, 25).addBox(-1.0F, -0.8F, 0.0F, 1.0F, 4.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(106, 39).addBox(0.0F, 1.2F, 1.0F, 1.0F, 3.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(107, 28).addBox(0.0F, -0.8F, 0.75F, 3.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(102, 28).addBox(2.0F, -0.8F, 1.75F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, -5.0F, -5.0F, 0.0F, 0.0F, -0.2618F));

		head.addOrReplaceChild("crown_r1", CubeListBuilder.create().texOffs(98, 0).addBox(-5.5F, -5.5F, -1.0F, 11.0F, 11.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -10.9598F, -4.0F, -0.1745F, 0.0F, 0.0F));

		head.addOrReplaceChild("bone_r1", CubeListBuilder.create().texOffs(81, 31).addBox(-4.5F, -0.5F, 0.2F, 9.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(98, 18).addBox(-6.5F, 2.3F, 0.19F, 13.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(106, 13).addBox(-5.0F, -0.7F, 0.69F, 10.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.0F, 4.0F, 0.2618F, 0.0F, 0.0F));

		body.addOrReplaceChild("Chestplate", CubeListBuilder.create().texOffs(63, 37).addBox(-4.0F, 0.0F, -2.5F, 8.0F, 12.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(79, 55).addBox(-1.5F, 9.0F, -4.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(90, 42).mirror().addBox(-5.0F, -1.0F, -3.4F, 4.0F, 11.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(90, 42).addBox(1.0F, -1.0F, -3.4F, 4.0F, 11.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		body.addOrReplaceChild("leftCoat2_r1", CubeListBuilder.create().texOffs(90, 55).addBox(1.0F, -1.0F, -0.4F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(90, 55).mirror().addBox(-5.0F, -1.0F, -0.4F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 11.0F, -3.0F, -0.1309F, 0.0F, 0.0F));

		body.addOrReplaceChild("armor_back", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 1.0F, 3.0F, 0.1745F, 0.0F, 0.0F));

		rightArm.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(0, 32).mirror().addBox(0.0F, 0.0F, 0.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.3F)).mirror(false), PartPose.offset(-3.0F, -2.0F, -2.0F));

		rightArm.addOrReplaceChild("rightArmPlate_r1", CubeListBuilder.create().texOffs(0, 49).mirror().addBox(-8.5F, -25.25F, -2.5F, 4.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(19, 51).mirror().addBox(-9.0F, -24.25F, -2.5F, 3.0F, 4.0F, 5.0F, new CubeDeformation(0.25F)).mirror(false)
				.texOffs(17, 40).mirror().addBox(-9.05F, -27.25F, -3.0F, 3.0F, 4.0F, 6.0F, new CubeDeformation(0.3F)).mirror(false)
				.texOffs(17, 32).mirror().addBox(-10.425F, -27.475F, -3.225F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.075F)).mirror(false)
				.texOffs(25, 36).mirror().addBox(-10.425F, -27.475F, 2.225F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.075F)).mirror(false), PartPose.offsetAndRotation(0.6849F, 22.33F, 0.0F, 0.0F, 0.0F, 0.1745F));

		leftArm.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(0, 32).addBox(0.0F, 0.0F, 0.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.3F)), PartPose.offset(-1.0F, -2.0F, -2.0F));

		leftArm.addOrReplaceChild("leftArmPlate_r1", CubeListBuilder.create().texOffs(0, 49).addBox(4.5F, -25.25F, -2.5F, 4.0F, 9.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(19, 51).addBox(6.0F, -24.25F, -2.5F, 3.0F, 4.0F, 5.0F, new CubeDeformation(0.25F))
				.texOffs(17, 40).addBox(6.05F, -27.25F, -3.0F, 3.0F, 4.0F, 6.0F, new CubeDeformation(0.3F))
				.texOffs(17, 32).addBox(9.425F, -27.475F, -3.225F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.075F))
				.texOffs(25, 36).addBox(9.425F, -27.475F, 2.225F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.075F)), PartPose.offsetAndRotation(-0.6849F, 22.33F, 0.0F, 0.0F, 0.0F, -0.1745F));

		rightLeg.addOrReplaceChild("RightLeg", CubeListBuilder.create(), PartPose.offset(-2.0F, 12.0F, 0.0F));

		rightLeg.addOrReplaceChild("rightLegPlate_r1", CubeListBuilder.create().texOffs(13, 65).mirror().addBox(-5.0F, -12.0F, -2.5F, 4.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.9427F, 12.2158F, 0.0F, 0.0F, 0.0F, 0.0873F));

		rightLeg.addOrReplaceChild("rightLegFrontPlate_r1", CubeListBuilder.create().texOffs(0, 68).mirror().addBox(-4.8F, -1.0F, -1.0F, 4.0F, 8.0F, 2.0F, new CubeDeformation(0.2F)).mirror(false), PartPose.offsetAndRotation(1.8F, 0.0F, -2.0F, -0.1745F, 0.0F, 0.0F));

		rightLeg.addOrReplaceChild("rightLegBackPad2_r1", CubeListBuilder.create().texOffs(45, 73).mirror().addBox(-4.0F, 7.0F, -0.5F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.001F)).mirror(false)
				.texOffs(32, 69).mirror().addBox(-4.0F, 0.0F, -0.5F, 4.0F, 7.0F, 2.0F, new CubeDeformation(-0.001F)).mirror(false), PartPose.offsetAndRotation(2.0F, -1.0F, 1.0F, 0.1745F, 0.0F, 0.0F));

		leftLeg.addOrReplaceChild("LeftLeg", CubeListBuilder.create(), PartPose.offset(2.0F, 12.0F, 0.0F));

		leftLeg.addOrReplaceChild("leftLegPlate_r1", CubeListBuilder.create().texOffs(13, 65).addBox(1.0F, -12.0F, -2.5F, 4.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.9427F, 12.2158F, 0.0F, 0.0F, 0.0F, -0.0873F));

		leftLeg.addOrReplaceChild("leftLegFrontPlate_r1", CubeListBuilder.create().texOffs(0, 68).addBox(0.8F, -1.0F, -1.0F, 4.0F, 8.0F, 2.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(-1.8F, 0.0F, -2.0F, -0.1745F, 0.0F, 0.0F));

		leftLeg.addOrReplaceChild("leftLegBackPad2_r1", CubeListBuilder.create().texOffs(45, 73).addBox(1.0F, 7.0F, -0.5F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.001F))
				.texOffs(32, 69).addBox(0.0F, 0.0F, -0.5F, 4.0F, 7.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-2.0F, -1.0F, 1.0F, 0.1745F, 0.0F, 0.0F));

		rightLeg.addOrReplaceChild("RigthBoot", CubeListBuilder.create().texOffs(17, 92).mirror().addBox(0.0F, 10.0F, -3.4F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.21F)).mirror(false)
				.texOffs(0, 79).mirror().addBox(0.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.21F)).mirror(false), PartPose.offset(-2.0F, 0.0F, 0.0F));

		leftLeg.addOrReplaceChild("LeftBoot", CubeListBuilder.create().texOffs(17, 92).addBox(0.0F, 10.0F, -3.4F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.21F))
				.texOffs(0, 79).addBox(0.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.21F)), PartPose.offset(-2.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}
}