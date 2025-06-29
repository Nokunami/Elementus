package nokunami.elementus.client.model.armor.samuraiDynasty;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class SDSamuraiMasterArmorModel extends HumanoidModel {

    public SDSamuraiMasterArmorModel(ModelPart root) {
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
                .texOffs(36, 12).addBox(-4.0F, -6.0F, -5.5F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(36, 12).addBox(-4.0F, -6.0F, -6.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition leftHelmetPlate_r1 = Head.addOrReplaceChild("leftHelmetPlate_r1", CubeListBuilder.create().texOffs(20, 17).addBox(-10.0F, -0.8F, -1.0F, 10.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, -5.0F, -5.0F, 0.0F, 1.5708F, -0.2618F));
        PartDefinition rightHelmetPlate_r1 = Head.addOrReplaceChild("rightHelmetPlate_r1", CubeListBuilder.create().texOffs(20, 17).mirror().addBox(0.0F, -0.8F, -1.0F, 10.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-5.0F, -5.0F, -5.0F, 0.0F, -1.5708F, 0.2618F));
        PartDefinition leftHelmetFlap_r1 = Head.addOrReplaceChild("leftHelmetFlap_r1", CubeListBuilder.create().texOffs(42, 17).addBox(0.0F, -0.8F, 0.75F, 3.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, -5.0F, -5.0F, 0.0F, 0.0F, -0.2618F));
        PartDefinition rightHelmetFlap_r1 = Head.addOrReplaceChild("rightHelmetFlap_r1", CubeListBuilder.create().texOffs(42, 17).mirror().addBox(-3.0F, -0.8F, 0.75F, 3.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-5.0F, -5.0F, -5.0F, 0.0F, 0.0F, 0.2618F));
        PartDefinition crown_r1 = Head.addOrReplaceChild("crown_r1", CubeListBuilder.create().texOffs(36, 0).addBox(-5.5F, -5.5F, -1.0F, 11.0F, 11.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -10.9598F, -4.0F, -0.1745F, 0.0F, 0.0F));
        PartDefinition backHelmetPlate3_r1 = Head.addOrReplaceChild("backHelmetPlate3_r1", CubeListBuilder.create().texOffs(0, 17).addBox(-4.5F, -0.5F, 0.2F, 9.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 13).addBox(-5.0F, -0.7F, 0.19F, 10.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 13).addBox(-5.0F, -0.7F, 0.69F, 10.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.0F, 4.0F, 0.2618F, 0.0F, 0.0F));

        PartDefinition Chestplate = body.addOrReplaceChild("Chestplate", CubeListBuilder.create().texOffs(0, 31).addBox(-4.0F, 0.0F, -2.5F, 8.0F, 12.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(26, 36).addBox(2.0F, -1.0F, -3.4F, 3.0F, 11.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(26, 36).mirror().addBox(-5.0F, -1.0F, -3.4F, 3.0F, 11.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition swordSheath_r1 = Chestplate.addOrReplaceChild("swordSheath_r1", CubeListBuilder.create().texOffs(60, 0).addBox(4.8F, -14.1F, -3.9F, 1.0F, 15.0F, 2.0F, new CubeDeformation(0.2F))
                .texOffs(66, 0).addBox(4.8F, -21.1F, -3.9F, 1.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(66, 8).addBox(3.8F, -15.1F, -4.9F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4589F, 17.4835F, 8.9689F, 0.9637F, -0.1006F, -0.2859F));
        PartDefinition frontRightCoat2_r1 = Chestplate.addOrReplaceChild("frontRightCoat2_r1", CubeListBuilder.create().texOffs(26, 30).mirror().addBox(-5.0F, -1.0F, -0.4F, 3.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(26, 30).addBox(2.0F, -1.0F, -0.4F, 3.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 11.0F, -3.0F, -0.1309F, 0.0F, 0.0F));

        PartDefinition LeftArm = leftArm.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(34, 30).addBox(0.0F, -4.0F, -2.0F, 4.0F, 14.0F, 4.0F, new CubeDeformation(0.3F)), PartPose.offset(-0.99F, -0.175F, 0.0F));
        PartDefinition leftShoulder_r1 = LeftArm.addOrReplaceChild("leftShoulder_r1", CubeListBuilder.create().texOffs(50, 38).addBox(-3.8625F, -0.925F, -3.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.2F))
                .texOffs(50, 30).addBox(0.5375F, -0.925F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(4.5618F, -4.3483F, 0.0F, 0.0F, 0.0F, -0.0873F));

        PartDefinition RightArm = rightArm.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(34, 30).mirror().addBox(-4.0F, -4.0F, -2.0F, 4.0F, 14.0F, 4.0F, new CubeDeformation(0.3F)).mirror(false), PartPose.offset(0.99F, -0.175F, 0.0F));
        PartDefinition rigthShoulder_r1 = RightArm.addOrReplaceChild("rigthShoulder_r1", CubeListBuilder.create().texOffs(50, 38).mirror().addBox(0.0625F, -0.925F, -3.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.2F)).mirror(false)
                .texOffs(50, 30).mirror().addBox(-2.3375F, -0.925F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.2F)).mirror(false), PartPose.offsetAndRotation(-4.5618F, -4.3483F, 0.0F, 0.0F, 0.0F, 0.0873F));

        PartDefinition LeftLeg = leftLeg.addOrReplaceChild("LeftLeg", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition leftLegPlate_r1 = LeftLeg.addOrReplaceChild("leftLegPlate_r1", CubeListBuilder.create().texOffs(0, 51).mirror().addBox(1.0F, -12.0F, -2.5F, 5.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.9427F, 12.2158F, 0.0F, 0.0F, 0.0F, -0.0873F));
        PartDefinition leftLegFrontPlate_r1 = LeftLeg.addOrReplaceChild("leftLegFrontPlate_r1", CubeListBuilder.create().texOffs(20, 54).mirror().addBox(1.8F, -1.0F, -1.0F, 3.0F, 8.0F, 2.0F, new CubeDeformation(0.25F)).mirror(false), PartPose.offsetAndRotation(-1.8F, 0.0F, -2.0F, -0.1745F, 0.0F, 0.0F));
        PartDefinition leftLegBackPlate2_r1 = LeftLeg.addOrReplaceChild("leftLegBackPlate2_r1", CubeListBuilder.create().texOffs(30, 50).mirror().addBox(1.0F, 7.0F, -0.5F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.001F)).mirror(false)
                .texOffs(30, 55).mirror().addBox(0.0F, 0.0F, -0.5F, 4.0F, 7.0F, 2.0F, new CubeDeformation(-0.001F)).mirror(false), PartPose.offsetAndRotation(-2.0F, -1.0F, 1.0F, 0.1745F, 0.0F, 0.0F));

        PartDefinition RightLeg = rightLeg.addOrReplaceChild("RightLeg", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition rightLegPlate_r1 = RightLeg.addOrReplaceChild("rightLegPlate_r1", CubeListBuilder.create().texOffs(0, 51).addBox(-6.0F, -12.0F, -2.5F, 5.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.9427F, 12.2158F, 0.0F, 0.0F, 0.0F, 0.0873F));
        PartDefinition rightLegFrontPlate_r1 = RightLeg.addOrReplaceChild("rightLegFrontPlate_r1", CubeListBuilder.create().texOffs(20, 54).addBox(-4.8F, -1.0F, -1.0F, 3.0F, 8.0F, 2.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(1.8F, 0.0F, -2.0F, -0.1745F, 0.0F, 0.0F));
        PartDefinition rightLegBackPlate2_r1 = RightLeg.addOrReplaceChild("rightLegBackPlate2_r1", CubeListBuilder.create().texOffs(30, 50).addBox(-4.0F, 7.0F, -0.5F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.001F))
                .texOffs(30, 55).addBox(-4.0F, 0.0F, -0.5F, 4.0F, 7.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(2.0F, -1.0F, 1.0F, 0.1745F, 0.0F, 0.0F));

        PartDefinition LeftBoot = leftLeg.addOrReplaceChild("LeftBoot", CubeListBuilder.create().texOffs(16, 77).mirror().addBox(-2.0F, 10.0F, -3.4F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.21F)).mirror(false)
                .texOffs(0, 64).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.21F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition RightBoot = rightLeg.addOrReplaceChild("RightBoot", CubeListBuilder.create().texOffs(16, 77).addBox(-2.0F, 10.0F, -3.4F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.21F))
                .texOffs(0, 64).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.21F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 80, 80);
    }
}
