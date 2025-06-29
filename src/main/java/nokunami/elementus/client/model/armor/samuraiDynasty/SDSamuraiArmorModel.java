package nokunami.elementus.client.model.armor.samuraiDynasty;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;

public class SDSamuraiArmorModel extends HumanoidModel<LivingEntity> {

    public SDSamuraiArmorModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createArmorLayer(CubeDeformation deformation) {
        MeshDefinition meshdefinition = HumanoidModel.createMesh(deformation, 0.0F);
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition hat = partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);
        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.ZERO);
        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.ZERO);
        PartDefinition rightArm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.offset(-5.0F, 0.0F, 0.0F));
        PartDefinition leftArm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.offset(5.0F, 0.0F, 0.0F));
        PartDefinition rightLeg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.offset(-1.9F, 12.0F, 0.0F));
        PartDefinition leftLeg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.offset(1.9F, 12.0F, 0.0F));

        PartDefinition Head = head.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, -9.0F, -4.5F, 9.0F, 4.0F, 9.0F, new CubeDeformation(0.0F))
                .texOffs(0, 13).addBox(-4.0F, -6.0F, -6.5F, 8.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        Head.addOrReplaceChild("leftHelmFlap_r1", CubeListBuilder.create().texOffs(20, 16).addBox(-1.0F, -0.8F, -1.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, -5.0F, -5.0F, 0.0F, 0.0F, -0.2618F));
        Head.addOrReplaceChild("leftHelmPlate1_r1", CubeListBuilder.create().texOffs(30, 21).addBox(-10.0F, -0.8F, -1.0F, 10.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(30, 17).addBox(-11.0F, 2.2F, 0.0F, 10.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, -5.0F, -5.0F, 0.2618F, 1.5708F, 0.0F));
        Head.addOrReplaceChild("rightHelmFlap_r1", CubeListBuilder.create().texOffs(20, 16).mirror().addBox(-3.0F, -0.8F, -1.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-5.0F, -5.0F, -5.0F, 0.0F, 0.0F, 0.2618F));
        PartDefinition rightHelmPlate1_r1 = Head.addOrReplaceChild("rightHelmPlate1_r1", CubeListBuilder.create().texOffs(30, 21).mirror().addBox(0.0F, -0.8F, -1.0F, 10.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(30, 17).mirror().addBox(1.0F, 2.2F, 0.0F, 10.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-5.0F, -5.0F, -5.0F, 0.0F, -1.5708F, 0.2618F));
        PartDefinition jewelFrame_r1 = Head.addOrReplaceChild("jewelFrame_r1", CubeListBuilder.create().texOffs(27, 3).addBox(-0.7F, -2.3F, -0.5F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(27, 0).addBox(-0.2F, -1.8F, -1.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(36, 0).addBox(-2.7F, -8.3F, 0.0F, 11.0F, 11.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.0F, -5.0F, 0.0F, 0.0F, -0.7854F));
        PartDefinition backPlate1_r1 = Head.addOrReplaceChild("backPlate1_r1", CubeListBuilder.create().texOffs(0, 16).addBox(-4.5F, -0.5F, 0.2F, 9.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 21).addBox(-6.5F, 2.3F, 0.19F, 13.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.0F, 4.0F, 0.2618F, 0.0F, 0.0F));

        PartDefinition Chestplate = body.addOrReplaceChild("Chestplate", CubeListBuilder.create().texOffs(0, 31).addBox(-4.0F, 0.0F, -2.5F, 8.0F, 12.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(26, 31).addBox(-4.0F, 0.0F, -2.5F, 8.0F, 12.0F, 5.0F, new CubeDeformation(0.4F))
                .texOffs(52, 21).addBox(-1.5F, 9.0F, -4.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition swordSheath_r1 = Chestplate.addOrReplaceChild("swordSheath_r1", CubeListBuilder.create().texOffs(60, 0).addBox(4.3F, -14.1F, -3.9F, 1.0F, 15.0F, 2.0F, new CubeDeformation(0.2F))
                .texOffs(66, 0).addBox(4.3F, -21.1F, -3.9F, 1.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(66, 8).addBox(3.3F, -15.1F, -4.9F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4361F, 17.3432F, 9.0191F, 0.9637F, -0.1006F, -0.2859F));
        PartDefinition belt_r1 = Chestplate.addOrReplaceChild("belt_r1", CubeListBuilder.create().texOffs(62, 20).addBox(-4.0F, -1.0F, -0.4F, 8.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 11.0F, -3.0F, -0.1309F, 0.0F, 0.0F));
        PartDefinition backPlate_r1 = Chestplate.addOrReplaceChild("backPlate_r1", CubeListBuilder.create().texOffs(52, 34).addBox(-4.0F, -26.0F, 1.4F, 8.0F, 11.0F, 3.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(0.0F, 24.1715F, 4.0395F, 0.1745F, 0.0F, 0.0F));
        PartDefinition sword_two2 = Chestplate.addOrReplaceChild("sword_two2", CubeListBuilder.create().texOffs(60, 48).addBox(0.0F, -1.0F, -1.0F, 1.0F, 15.0F, 2.0F, new CubeDeformation(0.2F))
                .texOffs(66, 48).addBox(0.0F, -8.0F, -1.0F, 1.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(66, 56).addBox(-1.0F, -2.0F, -2.0F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.9F, 12.8F, -1.7F, 1.7142F, 0.0584F, 0.1338F));

        PartDefinition RightArm = rightArm.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(34, 48).addBox(-4.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(18, 48).mirror().addBox(-4.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.2F)).mirror(false), PartPose.offset(0.99F, -0.175F, 0.0F));
        PartDefinition rightShoulderPlate_r1 = RightArm.addOrReplaceChild("rightShoulderPlate_r1", CubeListBuilder.create().texOffs(0, 50).mirror().addBox(-8.5F, -25.5F, -2.5F, 4.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.2283F, 22.33F, 0.0F, 0.0F, 0.0F, 0.1745F));

        PartDefinition LeftArm = leftArm.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(34, 48).addBox(0.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(18, 48).addBox(0.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.2F)), PartPose.offset(-0.99F, -0.175F, 0.0F));
        PartDefinition leftShoulderPlate_r1 = LeftArm.addOrReplaceChild("leftShoulderPlate_r1", CubeListBuilder.create().texOffs(0, 50).addBox(4.5F, -25.5F, -2.5F, 4.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.2283F, 22.33F, 0.0F, 0.0F, 0.0F, -0.1745F));

        PartDefinition RightLeg = rightLeg.addOrReplaceChild("RightLeg", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition leftLegPlate_r1 = RightLeg.addOrReplaceChild("leftLegPlate_r1", CubeListBuilder.create().texOffs(12, 67).mirror().addBox(-5.0F, -12.0F, -2.5F, 4.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.9427F, 12.2158F, 0.0F, 0.0F, 0.0F, 0.0873F));
        PartDefinition rightLegBackPlate_r1 = RightLeg.addOrReplaceChild("rightLegBackPlate_r1", CubeListBuilder.create().texOffs(0, 69).mirror().addBox(-4.0F, 0.0F, -0.5F, 4.0F, 9.0F, 2.0F, new CubeDeformation(-0.001F)).mirror(false), PartPose.offsetAndRotation(2.0F, -1.0F, 1.0F, 0.1745F, 0.0F, 0.0F));
        PartDefinition rightLegFrontPlate_r1 = RightLeg.addOrReplaceChild("rightLegFrontPlate_r1", CubeListBuilder.create().texOffs(30, 70).mirror().addBox(-3.2F, -1.0F, -1.2F, 3.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.2F, 0.0F, -2.0F, -0.1745F, 0.0F, 0.0F));

        PartDefinition LeftLeg = leftLeg.addOrReplaceChild("LeftLeg", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition leftLegPlate_r2 = LeftLeg.addOrReplaceChild("leftLegPlate_r2", CubeListBuilder.create().texOffs(12, 67).addBox(1.0F, -12.0F, -2.5F, 4.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.9427F, 12.2158F, 0.0F, 0.0F, 0.0F, -0.0873F));
        PartDefinition leftLegBackPlate_r1 = LeftLeg.addOrReplaceChild("leftLegBackPlate_r1", CubeListBuilder.create().texOffs(30, 70).addBox(-0.2F, -1.0F, -1.2F, 3.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.8F, 0.0F, -2.0F, -0.1745F, 0.0F, 0.0F));
        PartDefinition leftLegFrontPlate_r1 = LeftLeg.addOrReplaceChild("leftLegFrontPlate_r1", CubeListBuilder.create().texOffs(0, 69).addBox(0.0F, 0.0F, -0.5F, 4.0F, 9.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-2.0F, -1.0F, 1.0F, 0.1745F, 0.0F, 0.0F));

        PartDefinition RightBoot = rightLeg.addOrReplaceChild("RightBoot", CubeListBuilder.create().texOffs(56, 77).mirror().addBox(-2.0F, 10.0F, -3.4F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.21F)).mirror(false)
                .texOffs(40, 64).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.2F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition LeftBoot = leftLeg.addOrReplaceChild("LeftBoot", CubeListBuilder.create().texOffs(56, 77).addBox(-2.0F, 10.0F, -3.4F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.21F))
                .texOffs(40, 64).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.21F)), PartPose.offset(0.0F, 0.0F, 0.0F));


        return LayerDefinition.create(meshdefinition, 80, 80);
    }
}
