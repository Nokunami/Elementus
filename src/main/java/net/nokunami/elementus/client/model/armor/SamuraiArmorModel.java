package net.nokunami.elementus.client.model.armor;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class SamuraiArmorModel extends HumanoidModel {

    public SamuraiArmorModel(ModelPart root) {
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

        head.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(64, 0).addBox(-4.5F, -9.0F, -4.5F, 9.0F, 4.0F, 9.0F, new CubeDeformation(0.0F))
                .texOffs(108, 13).addBox(-4.0F, -6.0F, -6.5F, 8.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        head.addOrReplaceChild("leftHelmetFlap_r1", CubeListBuilder.create().texOffs(86, 15).addBox(-1.0F, -0.8F, -1.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(106, 17).addBox(-1.0F, -0.8F, 0.0F, 1.0F, 4.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(93, 13).addBox(0.0F, 2.2F, 1.0F, 1.0F, 3.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, -5.0F, -5.0F, 0.0F, 0.0F, -0.2618F));

        head.addOrReplaceChild("rightHelmetFlap_r1", CubeListBuilder.create().texOffs(86, 15).mirror().addBox(-3.0F, -0.8F, -1.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(106, 17).mirror().addBox(0.0F, -0.8F, 0.0F, 1.0F, 4.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(93, 13).mirror().addBox(-1.0F, 2.2F, 1.0F, 1.0F, 3.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-5.0F, -5.0F, -5.0F, 0.0F, 0.0F, 0.3054F));

        head.addOrReplaceChild("jewelFrame_r1", CubeListBuilder.create().texOffs(93, 3).addBox(-0.7F, -2.3F, -0.5F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(93, 0).addBox(-0.2F, -1.8F, -1.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(104, 0).addBox(-2.7F, -8.3F, 0.0F, 11.0F, 11.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.0F, -5.0F, 0.0F, 0.0F, -0.7854F));

        head.addOrReplaceChild("backPlate1_r1", CubeListBuilder.create().texOffs(64, 19).addBox(-4.5F, -0.5F, 0.2F, 9.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(64, 25).addBox(-6.5F, 2.3F, 0.19F, 13.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.0F, 4.0F, 0.2618F, 0.0F, 0.0F));

        body.addOrReplaceChild("Chestplate", CubeListBuilder.create().texOffs(36, 47).addBox(-4.0F, 0.0F, -2.5F, 8.0F, 12.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(62, 47).addBox(-4.0F, 0.0F, -2.5F, 8.0F, 12.0F, 5.0F, new CubeDeformation(0.4F))
                .texOffs(83, 45).addBox(-1.5F, 9.0F, -4.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        body.addOrReplaceChild("swordSheath_r1", CubeListBuilder.create().texOffs(29, 47).addBox(4.3F, -14.1F, -3.9F, 1.0F, 15.0F, 2.0F, new CubeDeformation(0.2F))
                .texOffs(19, 50).addBox(4.3F, -21.1F, -3.9F, 1.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(40, 40).addBox(3.3F, -15.1F, -4.9F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4361F, 17.3432F, 9.0191F, 0.9637F, -0.1006F, -0.2859F));

        body.addOrReplaceChild("backPlate_r1", CubeListBuilder.create().texOffs(88, 50).addBox(-4.0F, -26.0F, 1.4F, 8.0F, 11.0F, 3.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(0.0F, 24.1715F, 4.0395F, 0.1745F, 0.0F, 0.0F));

        body.addOrReplaceChild("belt_r1", CubeListBuilder.create().texOffs(66, 40).addBox(-4.0F, -1.0F, -0.4F, 8.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 11.0F, -3.0F, -0.1309F, 0.0F, 0.0F));

        rightArm.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(112, 34).addBox(0.0F, 0.0F, 0.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(96, 34).mirror().addBox(0.0F, 0.0F, 0.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.2F)).mirror(false), PartPose.offset(-3.0F, -2.0F, -2.0F));

        rightArm.addOrReplaceChild("rightShoulderPlate_r1", CubeListBuilder.create().texOffs(110, 50).mirror().addBox(-7.5F, -25.75F, -2.5F, 4.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.2283F, 22.33F, 0.0F, 0.0F, 0.0F, 0.1745F));

        leftArm.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(112, 34).addBox(0.0F, 0.0F, 0.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(96, 34).addBox(0.0F, 0.0F, 0.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.2F)), PartPose.offset(-1.0F, -2.0F, -2.0F));

        leftArm.addOrReplaceChild("leftShoulderPlate_r1", CubeListBuilder.create().texOffs(110, 50).addBox(3.5F, -25.5F, -2.5F, 4.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.2283F, 22.33F, 0.0F, 0.0F, 0.0F, -0.1745F));

        rightLeg.addOrReplaceChild("RightLeg", CubeListBuilder.create(), PartPose.offset(-2.0F, 12.0F, 0.0F));

        rightLeg.addOrReplaceChild("leftLegPlate_r1", CubeListBuilder.create().texOffs(12, 35).mirror().addBox(-5.0F, -12.0F, -2.5F, 4.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.9427F, 12.2158F, 0.0F, 0.0F, 0.0F, 0.0873F));

        rightLeg.addOrReplaceChild("rightLegBackPlate_r1", CubeListBuilder.create().texOffs(0, 37).mirror().addBox(-4.0F, 0.0F, -0.5F, 4.0F, 9.0F, 2.0F, new CubeDeformation(-0.001F)).mirror(false), PartPose.offsetAndRotation(2.0F, -1.0F, 1.0F, 0.1745F, 0.0F, 0.0F));

        rightLeg.addOrReplaceChild("rightLegFrontPlate_r1", CubeListBuilder.create().texOffs(30, 35).mirror().addBox(-3.2F, -1.0F, -1.2F, 3.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.2F, 0.0F, -2.0F, -0.1745F, 0.0F, 0.0F));

        leftLeg.addOrReplaceChild("LeftLeg", CubeListBuilder.create(), PartPose.offset(2.0F, 12.0F, 0.0F));

        leftLeg.addOrReplaceChild("leftLegPlate_r2", CubeListBuilder.create().texOffs(12, 35).addBox(1.0F, -12.0F, -2.5F, 4.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.9427F, 12.2158F, 0.0F, 0.0F, 0.0F, -0.0873F));

        leftLeg.addOrReplaceChild("leftLegBackPlate_r1", CubeListBuilder.create().texOffs(30, 35).addBox(-0.2F, -1.0F, -1.2F, 3.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.8F, 0.0F, -2.0F, -0.1745F, 0.0F, 0.0F));

        leftLeg.addOrReplaceChild("leftLegFrontPlate_r1", CubeListBuilder.create().texOffs(0, 37).addBox(0.0F, 0.0F, -0.5F, 4.0F, 9.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-2.0F, -1.0F, 1.0F, 0.1745F, 0.0F, 0.0F));

        rightLeg.addOrReplaceChild("RightBoot", CubeListBuilder.create().texOffs(16, 61).mirror().addBox(0.0F, 10.0F, -3.4F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.21F)).mirror(false)
                .texOffs(0, 48).mirror().addBox(0.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.2F)).mirror(false), PartPose.offset(-2.0F, 0.0F, 0.0F));

        leftLeg.addOrReplaceChild("LeftBoot", CubeListBuilder.create().texOffs(16, 61).addBox(0.0F, 10.0F, -3.4F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.21F))
                .texOffs(0, 48).addBox(0.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.21F)), PartPose.offset(-2.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 64);
    }
}
