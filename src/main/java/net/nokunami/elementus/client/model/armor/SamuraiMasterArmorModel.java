package net.nokunami.elementus.client.model.armor;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class SamuraiMasterArmorModel extends HumanoidModel {

    public SamuraiMasterArmorModel(ModelPart root) {
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
                .texOffs(101, 13).addBox(-4.0F, -6.0F, -5.5F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(101, 13).addBox(-4.0F, -6.0F, -6.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        head.addOrReplaceChild("leftHelmetPlate_r1", CubeListBuilder.create().texOffs(97, 16).addBox(-1.0F, -0.8F, 0.0F, 1.0F, 4.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(120, 25).addBox(0.0F, -0.8F, 0.75F, 3.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, -5.0F, -5.0F, 0.0F, 0.0F, -0.2618F));

        head.addOrReplaceChild("rightHelmetPlate_r1", CubeListBuilder.create().texOffs(97, 16).mirror().addBox(0.0F, -0.8F, 0.0F, 1.0F, 4.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(120, 25).mirror().addBox(-3.0F, -0.8F, 0.75F, 3.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-5.0F, -5.0F, -5.0F, 0.0F, 0.0F, 0.2618F));

        head.addOrReplaceChild("crown_r1", CubeListBuilder.create().texOffs(101, 0).addBox(-5.5F, -5.5F, -1.0F, 11.0F, 11.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -10.9598F, -4.0F, -0.1745F, 0.0F, 0.0F));

        head.addOrReplaceChild("backHelmetPlate3_r1", CubeListBuilder.create().texOffs(64, 19).addBox(-4.5F, -0.5F, 0.2F, 9.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(64, 14).addBox(-5.0F, -0.7F, 0.19F, 10.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(64, 14).addBox(-5.0F, -0.7F, 0.69F, 10.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.0F, 4.0F, 0.2618F, 0.0F, 0.0F));

        body.addOrReplaceChild("Chestplate", CubeListBuilder.create().texOffs(70, 31).addBox(-4.0F, 0.0F, -2.5F, 8.0F, 12.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(97, 36).addBox(2.0F, -1.0F, -3.4F, 3.0F, 11.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(97, 36).mirror().addBox(-5.0F, -1.0F, -3.4F, 3.0F, 11.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

        body.addOrReplaceChild("swordSheath_r1", CubeListBuilder.create().texOffs(106, 31).addBox(4.8F, -14.1F, -3.9F, 1.0F, 15.0F, 2.0F, new CubeDeformation(0.2F))
                .texOffs(113, 34).addBox(4.8F, -21.1F, -3.9F, 1.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(114, 43).addBox(3.8F, -15.1F, -4.9F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4589F, 17.4835F, 8.9689F, 0.9637F, -0.1006F, -0.2859F));

        body.addOrReplaceChild("frontRightCoat2_r1", CubeListBuilder.create().texOffs(120, 36).mirror().addBox(-5.0F, -1.0F, -0.4F, 3.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(120, 36).addBox(2.0F, -1.0F, -0.4F, 3.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 11.0F, -3.0F, -0.1309F, 0.0F, 0.0F));

        leftArm.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(0, 33).addBox(0.0F, 0.0F, 0.0F, 4.0F, 14.0F, 4.0F, new CubeDeformation(0.3F)), PartPose.offset(-1.0F, -4.0F, -2.0F));

        leftArm.addOrReplaceChild("leftShoulder_r1", CubeListBuilder.create().texOffs(17, 41).addBox(-3.8625F, -0.925F, -3.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.2F))
                .texOffs(17, 32).addBox(0.5375F, -0.925F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(3.8775F, -4.3482F, 0.0F, 0.0F, 0.0F, -0.0873F));

        rightArm.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(0, 33).mirror().addBox(0.0F, 0.0F, 0.0F, 4.0F, 14.0F, 4.0F, new CubeDeformation(0.3F)).mirror(false), PartPose.offset(-3.0F, -4.0F, -2.0F));

        rightArm.addOrReplaceChild("rigthShoulder_r1", CubeListBuilder.create().texOffs(17, 41).mirror().addBox(0.0625F, -0.925F, -3.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.2F)).mirror(false)
                .texOffs(17, 32).mirror().addBox(-2.3375F, -0.925F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.2F)).mirror(false), PartPose.offsetAndRotation(-3.8775F, -4.3482F, 0.0F, 0.0F, 0.0F, 0.0873F));

        rightLeg.addOrReplaceChild("RightLeg", CubeListBuilder.create(), PartPose.offset(-2.0F, 12.0F, 0.0F));

        rightLeg.addOrReplaceChild("rightLegPlate_r1", CubeListBuilder.create().texOffs(0, 52).addBox(-6.0F, -12.0F, -2.5F, 5.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.9427F, 12.2158F, 0.0F, 0.0F, 0.0F, 0.0873F));

        rightLeg.addOrReplaceChild("rightLegFrontPlate_r1", CubeListBuilder.create().texOffs(21, 55).addBox(-4.8F, -1.0F, -1.0F, 3.0F, 8.0F, 2.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(1.8F, 0.0F, -2.0F, -0.1745F, 0.0F, 0.0F));

        rightLeg.addOrReplaceChild("rightLegBackPlate2_r1", CubeListBuilder.create().texOffs(45, 60).addBox(-4.0F, 7.0F, -0.5F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.001F))
                .texOffs(32, 56).addBox(-4.0F, 0.0F, -0.5F, 4.0F, 7.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(2.0F, -1.0F, 1.0F, 0.1745F, 0.0F, 0.0F));

        leftLeg.addOrReplaceChild("LeftLeg", CubeListBuilder.create(), PartPose.offset(2.0F, 12.0F, 0.0F));

        leftLeg.addOrReplaceChild("leftLegPlate_r1", CubeListBuilder.create().texOffs(0, 52).mirror().addBox(1.0F, -12.0F, -2.5F, 5.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.9427F, 12.2158F, 0.0F, 0.0F, 0.0F, -0.0873F));

        leftLeg.addOrReplaceChild("leftLegFrontPlate_r1", CubeListBuilder.create().texOffs(21, 55).mirror().addBox(1.8F, -1.0F, -1.0F, 3.0F, 8.0F, 2.0F, new CubeDeformation(0.25F)).mirror(false), PartPose.offsetAndRotation(-1.8F, 0.0F, -2.0F, -0.1745F, 0.0F, 0.0F));

        leftLeg.addOrReplaceChild("leftLegBackPlate2_r1", CubeListBuilder.create().texOffs(45, 60).mirror().addBox(1.0F, 7.0F, -0.5F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.001F)).mirror(false)
                .texOffs(32, 56).mirror().addBox(0.0F, 0.0F, -0.5F, 4.0F, 7.0F, 2.0F, new CubeDeformation(-0.001F)).mirror(false), PartPose.offsetAndRotation(-2.0F, -1.0F, 1.0F, 0.1745F, 0.0F, 0.0F));

        rightLeg.addOrReplaceChild("RightBoot", CubeListBuilder.create().texOffs(17, 79).addBox(0.0F, 10.0F, -3.4F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.21F))
                .texOffs(0, 66).addBox(0.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.21F)), PartPose.offset(-2.0F, 0.0F, 0.0F));

        leftLeg.addOrReplaceChild("LeftBoot", CubeListBuilder.create().texOffs(17, 79).mirror().addBox(0.0F, 10.0F, -3.4F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.21F)).mirror(false)
                .texOffs(0, 66).mirror().addBox(0.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.21F)).mirror(false), PartPose.offset(-2.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }
}
