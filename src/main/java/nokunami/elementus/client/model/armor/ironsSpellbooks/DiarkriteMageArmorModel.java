package nokunami.elementus.client.model.armor.ironsSpellbooks;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;

public class DiarkriteMageArmorModel extends HumanoidModel<LivingEntity> {

	public DiarkriteMageArmorModel(ModelPart root) {
		super(root);
	}

	public static LayerDefinition createArmorLayer(CubeDeformation deformation) {
		MeshDefinition meshdefinition = HumanoidModel.createMesh(deformation, 0.0F);
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition hat = partdefinition.getChild("hat");
		PartDefinition body = partdefinition.getChild("body");
		PartDefinition rightArm = partdefinition.getChild("right_arm");
		PartDefinition leftArm = partdefinition.getChild("left_arm");

		partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.9F))
		.texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		hat.addOrReplaceChild("SmallTip", CubeListBuilder.create().texOffs(48, 55).addBox(-2.0F, -5.25F, 0.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -14.75F, 0.0F, -0.8727F, 0.0F, 0.0F));
		hat.addOrReplaceChild("Tip", CubeListBuilder.create().texOffs(40, 68).addBox(-3.0F, -5.75F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.0F, 0.0F, -0.4363F, 0.0F, 0.0F));
		hat.addOrReplaceChild("Base", CubeListBuilder.create().texOffs(0, 68).addBox(-5.0F, -1.9F, -5.0F, 10.0F, 2.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(0, 48).addBox(-8.0F, 0.0F, -8.0F, 16.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.0F, 0.0F, -0.0873F, 0.0F, 0.0F));

		body.addOrReplaceChild("Chestplate", CubeListBuilder.create().texOffs(0, 32).addBox(0.0F, 0.0F, 0.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.7F))
				.texOffs(24, 32).addBox(0.0F, 0.0F, 0.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.45F)), PartPose.offset(-4.0F, 0.0F, -2.0F));
		body.addOrReplaceChild("RightFauld", CubeListBuilder.create().texOffs(48, 32).addBox(-3.2F, 10.25F, -2.0F, 5.0F, 5.0F, 4.0F, new CubeDeformation(0.825F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0873F));
		body.addOrReplaceChild("LeftFauld", CubeListBuilder.create().texOffs(48, 32).mirror().addBox(-1.85F, 10.25F, -2.0F, 5.0F, 5.0F, 4.0F, new CubeDeformation(0.825F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.0873F));

		rightArm.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(48, 0).addBox(0.0F, 0.0F, 0.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.27F)), PartPose.offset(-3.0F, -2.0F, -2.0F));
		rightArm.addOrReplaceChild("RightArmUnderLayer", CubeListBuilder.create().texOffs(32, 0).addBox(0.0F, 0.0F, 0.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.65F)), PartPose.offsetAndRotation(-3.0F, -2.0F, -2.0F, 0.0F, 0.0F, 0.0436F));

		leftArm.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(48, 0).mirror().addBox(0.0F, 0.0F, 0.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.27F)).mirror(false), PartPose.offset(-1.0F, -2.0F, -2.0F));
		leftArm.addOrReplaceChild("LeftArmUnderLayer", CubeListBuilder.create().texOffs(32, 0).mirror().addBox(0.0F, 0.0F, 0.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.65F)).mirror(false), PartPose.offsetAndRotation(-1.0F, -2.0F, -2.0F, 0.0F, 0.0F, -0.0436F));

		partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, deformation.extend(-0.1F)), PartPose.offset(-1.9F, 12.0F, 0.0F));
		partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, deformation.extend(-0.1F)), PartPose.offset(1.9F, 12.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 80, 80);
	}
}