package net.nokunami.elementus.client.model.armor.ironsSpellbooks;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;

public class AnthektiteMageArmorModel extends HumanoidModel<LivingEntity> {

	public AnthektiteMageArmorModel(ModelPart root) {
		super(root);
	}

	public static LayerDefinition createArmorLayer(CubeDeformation deformation) {
		MeshDefinition meshdefinition = HumanoidModel.createMesh(deformation, 0.0F);
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition Body = partdefinition.getChild("body");

		partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		Head.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.55F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		Head.addOrReplaceChild("Tip", CubeListBuilder.create().texOffs(48, 64).addBox(-3.0F, -10.0F, -4.0F, 6.0F, 10.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.0F, 0.0F, -0.9163F, 0.0F, 0.0F));
		Head.addOrReplaceChild("Base", CubeListBuilder.create().texOffs(28, 48).addBox(-4.5F, -4.125F, -4.5F, 9.0F, 6.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.0F, 0.0F, -0.1745F, 0.0F, 0.0F));
		Head.addOrReplaceChild("HatRim", CubeListBuilder.create().texOffs(0, 63).addBox(-8.0F, 0.0F, -8.0F, 16.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.0F, 0.0F, 0.1745F, 0.0F, 0.0F));

		Body.addOrReplaceChild("Chestplate", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.5F))
				.texOffs(0, 32).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 14.0F, 4.0F, new CubeDeformation(0.8F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		Body.addOrReplaceChild("CoatExtra", CubeListBuilder.create().texOffs(24, 32).addBox(-4.0F, 7.925F, -3.275F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.81F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1789F, 0.0F, 0.0F));

		PartDefinition RightArm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.offset(-5.0F, 2.0F, 0.0F));
		RightArm.addOrReplaceChild("RightArmPlate", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.75F)), PartPose.ZERO);
		RightArm.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(56, 16).addBox(-2.75F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.27F)), PartPose.ZERO);

		PartDefinition LeftArm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().mirror(), PartPose.offset(5.0F, 2.0F, 0.0F));
		LeftArm.addOrReplaceChild("LeftArmPlate", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.75F)).mirror(false), PartPose.ZERO);
		LeftArm.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(56, 16).mirror().addBox(-1.25F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.27F)).mirror(false), PartPose.ZERO);

		partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, -0.6F, -2.0F, 4.0F, 12.0F, 4.0F, deformation.extend(-0.2F)), PartPose.offset(-1.9F, 12.0F, 0.0F));
		partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2.0F, -0.6F, -2.0F, 4.0F, 12.0F, 4.0F, deformation.extend(-0.2F)), PartPose.offset(1.9F, 12.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 80, 80);
	}
}