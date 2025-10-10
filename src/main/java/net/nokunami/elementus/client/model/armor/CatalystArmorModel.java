package net.nokunami.elementus.client.model.armor;

import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;

public class CatalystArmorModel<T extends LivingEntity> extends HumanoidArmorModel<T> {

	public CatalystArmorModel(ModelPart root) {
        super(root);
	}

	public static LayerDefinition create() {
		MeshDefinition meshdefinition = HumanoidArmorModel.createMesh(new CubeDeformation(1), 0.0F);
		PartDefinition partdefinition = meshdefinition.getRoot();
//        PartDefinition rightArm = partdefinition.getChild("right_arm");
//        PartDefinition leftArm = partdefinition.getChild("left_arm");

//		PartDefinition Chestplate = partdefinition.addOrReplaceChild("Chestplate", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(1.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition rightArm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(1.1F))
		.texOffs(40, 0).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.65F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

        PartDefinition leftArm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(1.1F)).mirror(false)
		.texOffs(40, 0).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.65F)).mirror(false), PartPose.offset(5.0F, 2.0F, 0.0F));

//		PartDefinition RightLeg = partdefinition.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(0, 16).addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.4F)), PartPose.offset(-2.0F, 12.0F, 0.0F));
//		PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.4F)).mirror(false), PartPose.offset(2.0F, 12.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}
}