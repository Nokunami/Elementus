package net.nokunami.elementus.client.model.armor;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class ExtendedArmorModel extends HumanoidModel {

    public ExtendedArmorModel(ModelPart root) {
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

        head.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.6F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        body.addOrReplaceChild("Chestplate", CubeListBuilder.create().texOffs(16, 0).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.6F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        rightArm.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(40, 0).addBox(2.0F, -4.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.601F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

        leftArm.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(40, 0).mirror().addBox(-6.0F, -4.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.601F)).mirror(false), PartPose.offset(5.0F, 2.0F, 0.0F));

        body.addOrReplaceChild("LeggingsPart", CubeListBuilder.create().texOffs(16, 0).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        rightLeg.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(40, 16).addBox(0.0F, -12.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.27F)), PartPose.offset(-2.0F, 12.0F, 0.0F));

        leftLeg.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-4.0F, -12.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.27F)).mirror(false), PartPose.offset(2.0F, 12.0F, 0.0F));

        rightLeg.addOrReplaceChild("RightBoot", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -12.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.75F)), PartPose.offset(-2.0F, 12.0F, 0.0F));

        leftLeg.addOrReplaceChild("LeftBoot", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4.0F, -12.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.75F)).mirror(false), PartPose.offset(2.0F, 12.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 32);
    }
}