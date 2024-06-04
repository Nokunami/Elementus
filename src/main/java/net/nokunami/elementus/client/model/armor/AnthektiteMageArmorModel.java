package net.nokunami.elementus.client.model.armor;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class AnthektiteMageArmorModel extends HumanoidModel {

	public AnthektiteMageArmorModel(ModelPart root) {
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

//		head.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 32).addBox(0.0F, 0.0F, 0.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.55F))
//				.texOffs(32, 32).addBox(0.0F, 0.0F, 0.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.25F)), PartPose.offset(-4.0F, -8.0F, -4.0F));
//
//		head.addOrReplaceChild("FlatHat_r1", CubeListBuilder.create().texOffs(32, 111).addBox(-8.0F, -1.0F, -8.0F, 16.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.0F, 0.0F, 0.1745F, 0.0F, 0.0F));
//
//		head.addOrReplaceChild("Base_r1", CubeListBuilder.create().texOffs(60, 96).addBox(-4.5F, -1.875F, -4.5F, 9.0F, 6.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -10.0F, -0.15F, -0.1309F, 0.0F, 0.0F));
//
//		head.addOrReplaceChild("Tip_r1", CubeListBuilder.create().texOffs(74, 81).addBox(-3.0F, 0.0F, -4.0F, 6.0F, 10.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -16.0F, 6.85F, -0.7854F, 0.0F, 0.0F));
//
//		head.addOrReplaceChild("SmallTip_r1", CubeListBuilder.create().texOffs(82, 73).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -19.0F, 9.8F, -1.309F, 0.0F, 0.0F));


		PartDefinition Head = head.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 32).addBox(0.0F, 0.0F, 0.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.55F))
				.texOffs(32, 32).addBox(0.0F, 0.0F, 0.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.25F)), PartPose.offset(-4.0F, -8.0F, -4.0F));

		PartDefinition Head2 = Head.addOrReplaceChild("Head2", CubeListBuilder.create(), PartPose.offset(4.0F, 8.0F, 4.0F));

		PartDefinition Tip_r1 = Head2.addOrReplaceChild("Tip_r1", CubeListBuilder.create().texOffs(74, 81).addBox(-3.0F, -10.0F, -4.0F, 6.0F, 10.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.0F, 0.0F, -0.9163F, 0.0F, 0.0F));

		PartDefinition Base_r1 = Head2.addOrReplaceChild("Base_r1", CubeListBuilder.create().texOffs(60, 96).addBox(-4.5F, -4.125F, -4.5F, 9.0F, 6.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition HatRim_r1 = Head2.addOrReplaceChild("HatRim_r1", CubeListBuilder.create().texOffs(32, 111).addBox(-8.0F, 0.0F, -8.0F, 16.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.0F, 0.0F, 0.1745F, 0.0F, 0.0F));

		body.addOrReplaceChild("Chestplate", CubeListBuilder.create().texOffs(0, 48).addBox(0.0F, 0.0F, 0.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.5F))
				.texOffs(0, 106).addBox(0.0F, 0.0F, 0.0F, 8.0F, 16.0F, 4.0F, new CubeDeformation(0.8F)), PartPose.offset(-4.0F, 0.0F, -2.0F));

		body.addOrReplaceChild("CoatExtra_r1", CubeListBuilder.create().texOffs(24, 106).addBox(0.0F, 0.0F, 0.0F, 8.0F, 16.0F, 4.0F, new CubeDeformation(0.8F)), PartPose.offsetAndRotation(-4.0F, 7.125F, -1.9F, 0.1265F, 0.0F, 0.0F));

		rightArm.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(0, 64).addBox(-1.0F, -0.95F, 0.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.95F))
				.texOffs(16, 64).addBox(-1.0F, -0.75F, 0.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.75F))
				.texOffs(32, 64).addBox(0.25F, 0.0F, 0.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.27F)), PartPose.offset(-3.0F, -2.0F, -2.0F));

		leftArm.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(0, 64).mirror().addBox(1.0F, -0.95F, 0.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.95F)).mirror(false)
				.texOffs(16, 64).mirror().addBox(1.0F, -0.75F, 0.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.75F)).mirror(false)
				.texOffs(32, 64).mirror().addBox(-0.25F, 0.0F, 0.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.27F)).mirror(false), PartPose.offset(-1.0F, -2.0F, -2.0F));

		body.addOrReplaceChild("LeggingsPart", CubeListBuilder.create().texOffs(48, 48).addBox(0.0F, 0.0F, 0.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.45F)), PartPose.offset(-4.0F, 0.0F, -2.0F));

		rightLeg.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(0, 80).addBox(0.0F, 0.0F, 0.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.4F))
				.texOffs(16, 80).addBox(0.0F, 0.0F, 0.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.27F)), PartPose.offset(-2.0F, 0.0F, -2.0F));

		leftLeg.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(0, 80).mirror().addBox(0.0F, 0.0F, 0.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.4F)).mirror(false)
				.texOffs(16, 80).mirror().addBox(0.0F, 0.0F, 0.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.27F)).mirror(false), PartPose.offset(-2.0F, 0.0F, -2.0F));

		rightLeg.addOrReplaceChild("RightBoot", CubeListBuilder.create().texOffs(32, 80).addBox(0.0F, -0.5F, 0.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.9F))
				.texOffs(48, 80).addBox(0.0F, -0.5F, 0.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.75F)), PartPose.offset(-2.1F, 0.0F, -2.0F));

		leftLeg.addOrReplaceChild("LeftBoot", CubeListBuilder.create().texOffs(32, 80).mirror().addBox(0.0F, -0.5F, 0.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.9F)).mirror(false)
				.texOffs(48, 80).mirror().addBox(0.0F, -0.5F, 0.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.75F)).mirror(false), PartPose.offset(-2.1F, 0.0F, -2.0F));

		return LayerDefinition.create(meshdefinition, 96, 128);
	}
}