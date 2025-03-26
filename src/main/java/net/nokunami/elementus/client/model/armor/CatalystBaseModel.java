package net.nokunami.elementus.client.model.armor;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class CatalystBaseModel<T extends LivingEntity> extends HumanoidModel<T> {
    public final ModelPart Body;
    public final ModelPart BodyEmissive;
    public final ModelPart RightArm;
    public final ModelPart LeftArm;
    public final ModelPart rightArmEmissive;
    public final ModelPart leftArmEmissive;
    public final ModelPart rightTassetHolder;
    public final ModelPart leftTassetHolder;
    public final ModelPart rightTassetEmissive;
    public final ModelPart leftTassetEmissive;
    private final ModelPart rightWing;
    private final ModelPart leftWing;

    public CatalystBaseModel(ModelPart root) {
        super(root);
        this.Body = root.getChild("body");
        this.BodyEmissive = root.getChild("body_emissive");
        this.RightArm = root.getChild("right_arm");
        this.LeftArm = root.getChild("left_arm");
        this.rightArmEmissive = root.getChild("right_arm_emissive");
        this.leftArmEmissive = root.getChild("left_arm_emissive");
        this.rightTassetHolder = root.getChild("right_tasset_holder");
        this.leftTassetHolder = root.getChild("left_tasset_holder");
        this.rightTassetEmissive = root.getChild("right_tasset_emissive");
        this.leftTassetEmissive = root.getChild("left_tasset_emissive");
        this.leftWing = root.getChild("left_wing");
        this.rightWing = root.getChild("right_wing");
    }

    public static LayerDefinition createArmorLayer(CubeDeformation deformation) {
        MeshDefinition meshdefinition = HumanoidModel.createMesh(deformation, 0.0F);
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Body = partdefinition.getChild("body");
        Body.addOrReplaceChild("plate", CubeListBuilder.create().texOffs(16, 9).addBox(-5.0F, 0.5F, -3.85F, 10.0F, 6.0F, 1.0F, new CubeDeformation(0.025F))
                .texOffs(17, 0).addBox(-4.0F, 0.775F, -3.5F, 8.0F, 8.0F, 1.0F, new CubeDeformation(0.225F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0349F, 0.0F, 0.0F));

        partdefinition.addOrReplaceChild("body_emissive", CubeListBuilder.create(), PartPose.ZERO);
        PartDefinition BodyEmissive = partdefinition.getChild("body_emissive");
        BodyEmissive.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 48).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(1.0F)), PartPose.ZERO);
        BodyEmissive.addOrReplaceChild("plate", CubeListBuilder.create().texOffs(16, 41).addBox(-5.0F, 0.5F, -3.85F, 10.0F, 6.0F, 1.0F, new CubeDeformation(0.025F))
                .texOffs(17, 32).addBox(-4.0F, 0.775F, -3.5F, 8.0F, 8.0F, 1.0F, new CubeDeformation(0.225F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0349F, 0.0F, 0.0F));
        BodyEmissive.addOrReplaceChild("core", CubeListBuilder.create().texOffs(38, 14).addBox(-4.0F, -1.0F, -3.5F, 8.0F, 8.0F, 2.0F, new CubeDeformation(-1.0F))
                .texOffs(38, 22).addBox(-4.0F, -1.0F, -3.15F, 8.0F, 8.0F, 2.0F, new CubeDeformation(-1.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.075F, 0.0349F, 0.0F, 0.0F));

        partdefinition.addOrReplaceChild("right_tasset_holder", CubeListBuilder.create(), PartPose.offset(-1.9F, 12.0F, 0.0F));
        partdefinition.addOrReplaceChild("left_tasset_holder", CubeListBuilder.create(), PartPose.offset(1.9F, 12.0F, 0.0F));
        PartDefinition rightTassetHolder = partdefinition.getChild("right_tasset_holder");
        PartDefinition leftTassetHolder = partdefinition.getChild("left_tasset_holder");
        rightTassetHolder.addOrReplaceChild("right_tasset", CubeListBuilder.create().texOffs(40, 6).addBox(-3.25F, -15.625F, -2.0F, 5.0F, 6.0F, 4.0F, new CubeDeformation(1.2F)), PartPose.offsetAndRotation(0.0F, 12.0F, 0.0F, 0.0F, 0.0F, 0.0873F));
        leftTassetHolder.addOrReplaceChild("left_tasset", CubeListBuilder.create().texOffs(40, 6).mirror().addBox(-1.75F, -15.625F, -2.0F, 5.0F, 6.0F, 4.0F, new CubeDeformation(1.2F)).mirror(false), PartPose.offsetAndRotation(0.0F, 12.0F, 0.0F, 0.0F, 0.0F, -0.0873F));

        partdefinition.addOrReplaceChild("right_tasset_emissive", CubeListBuilder.create(), PartPose.offset(-1.9F, 12.0F, 0.0F));
        partdefinition.addOrReplaceChild("left_tasset_emissive", CubeListBuilder.create(), PartPose.offset(1.9F, 12.0F, 0.0F));
        PartDefinition rightTassetEmissive = partdefinition.getChild("right_tasset_emissive");
        PartDefinition leftTassetEmissive = partdefinition.getChild("left_tasset_emissive");
        rightTassetEmissive.addOrReplaceChild("right_tasset1", CubeListBuilder.create().texOffs(40, 38).addBox(-3.25F, -15.625F, -2.0F, 5.0F, 6.0F, 4.0F, new CubeDeformation(1.2F)), PartPose.offsetAndRotation(0.0F, 12.0F, 0.0F, 0.0F, 0.0F, 0.0873F));
        leftTassetEmissive.addOrReplaceChild("left_tasset1", CubeListBuilder.create().texOffs(40, 38).mirror().addBox(-1.75F, -15.625F, -2.0F, 5.0F, 6.0F, 4.0F, new CubeDeformation(1.2F)).mirror(false), PartPose.offsetAndRotation(0.0F, 12.0F, 0.0F, 0.0F, 0.0F, -0.0873F));

        PartDefinition rightArm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.offset(-3.0F, -2.0F, -2.0F));
        rightArm.addOrReplaceChild("rightArm", CubeListBuilder.create().texOffs(0, 16).addBox(-3.5F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(1.1F))
                .texOffs(0, 0).addBox(-3.25F, -1.8F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.65F)), PartPose.ZERO);
        PartDefinition leftArm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.offset(-1.0F, -2.0F, -2.0F));
        leftArm.addOrReplaceChild("leftArm", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-0.5F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(1.1F)).mirror(false)
                .texOffs(0, 0).mirror().addBox(-0.75F, -1.8F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.65F)).mirror(false), PartPose.ZERO);

        PartDefinition rightArmEmissive = partdefinition.addOrReplaceChild("right_arm_emissive", CubeListBuilder.create(), PartPose.offset(-3.0F, -2.0F, -2.0F));
        rightArmEmissive.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(0, 48).addBox(-3.5F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(1.1F))
                .texOffs(0, 32).addBox(-3.25F, -1.8F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.65F)), PartPose.ZERO);
        rightArmEmissive.addOrReplaceChild("RightBigSpike", CubeListBuilder.create().texOffs(48, 48).addBox(-2.05F, -6.25F, 0.0F, 4.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -1.75F, 0.0F, 0.0F, -0.1745F, -0.5236F));
        rightArmEmissive.addOrReplaceChild("RightSmallSpike", CubeListBuilder.create().texOffs(48, 55).addBox(0.0F, -5.0F, 0.0F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 1.0F, 0.0F, 0.0F, 0.0F, -1.3963F));
        PartDefinition leftArmEmissive = partdefinition.addOrReplaceChild("left_arm_emissive", CubeListBuilder.create(), PartPose.offset(-1.0F, -2.0F, -2.0F));
        leftArmEmissive.addOrReplaceChild("leftArm1", CubeListBuilder.create().texOffs(0, 48).mirror().addBox(-0.5F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(1.1F)).mirror(false)
                .texOffs(0, 32).mirror().addBox(-0.75F, -1.8F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.65F)).mirror(false), PartPose.ZERO);
        leftArmEmissive.addOrReplaceChild("LeftBigSpike", CubeListBuilder.create().texOffs(48, 48).mirror().addBox(-1.95F, -6.25F, 0.0F, 4.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, -1.75F, 0.0F, 0.0F, -0.1745F, 0.5236F));
        leftArmEmissive.addOrReplaceChild("LeftSmallSpike", CubeListBuilder.create().texOffs(48, 55).addBox(-3.0F, -5.0F, 0.0F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 1.0F, 0.0F, 0.0F, 0.0F, 1.3963F));

        partdefinition.addOrReplaceChild("left_wing", CubeListBuilder.create().texOffs(0, 64).addBox(-10.0F, 0.0F, 0.0F, 10.0F, 20.0F, 2.0F, deformation), PartPose.offsetAndRotation(5.0F, 0.0F, 0.0F, 0.2617994F, 0.0F, -0.2617994F));
        partdefinition.addOrReplaceChild("right_wing", CubeListBuilder.create().texOffs(24, 64).mirror().addBox(0.0F, 0.0F, 0.0F, 10.0F, 20.0F, 2.0F, deformation), PartPose.offsetAndRotation(-5.0F, 0.0F, 0.0F, 0.2617994F, 0.0F, 0.2617994F));

        partdefinition.addOrReplaceChild("left_wing", CubeListBuilder.create().texOffs(0, 64).addBox(-10.0F, 0.0F, 0.0F, 10.0F, 20.0F, 2.0F, deformation), PartPose.offsetAndRotation(5.0F, 0.0F, 0.0F, 0.2617994F, 0.0F, -0.2617994F));
        partdefinition.addOrReplaceChild("right_wing", CubeListBuilder.create().texOffs(24, 64).mirror().addBox(0.0F, 0.0F, 0.0F, 10.0F, 20.0F, 2.0F, deformation), PartPose.offsetAndRotation(-5.0F, 0.0F, 0.0F, 0.2617994F, 0.0F, 0.2617994F));

        return LayerDefinition.create(meshdefinition, 64, 96);
    }

    @Override
    public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        int fullBright = 15728880;
        poseStack.pushPose();
        if (this.young) {
            poseStack.scale(0.5F, 0.5F, 0.5F);
            poseStack.translate(0.0F, 1.5F, 0.0F);
        }

        this.rightLeg.visible = true;
        this.leftLeg.visible = true;
        this.Body.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.BodyEmissive.copyFrom(Body);
        this.BodyEmissive.render(poseStack, buffer, fullBright, packedOverlay, red, green, blue, 1.0F);
        this.RightArm.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, 1.0F);
        this.LeftArm.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, 1.0F);
        this.rightArmEmissive.copyFrom(rightArm);
        this.rightArmEmissive.render(poseStack, buffer, fullBright, packedOverlay, red, green, blue, 1.0F);
        this.leftArmEmissive.copyFrom(leftArm);
        this.leftArmEmissive.render(poseStack, buffer, fullBright, packedOverlay, red, green, blue, 1.0F);
        this.rightTassetHolder.copyFrom(rightLeg);
        this.rightTassetHolder.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, 1.0F);
        this.leftTassetHolder.copyFrom(leftLeg);
        this.leftTassetHolder.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, 1.0F);
        this.rightTassetEmissive.copyFrom(rightLeg);
        this.rightTassetEmissive.render(poseStack, buffer, fullBright, packedOverlay, red, green, blue, 1.0F);
        this.leftTassetEmissive.copyFrom(leftLeg);
        this.leftTassetEmissive.render(poseStack, buffer, fullBright, packedOverlay, red, green, blue, 1.0F);

        poseStack.popPose();
    }

    public void renderElytra(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.rightWing.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftWing.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = 0.2617994F;
        float f1 = -0.2617994F;
        float f2 = 0.0F;
        float f3 = 0.0F;
        if (entity.isFallFlying()) {
            float f4 = 1.0F;
            Vec3 vec3 = entity.getDeltaMovement();
            if (vec3.y < 0.0D) {
                Vec3 vec31 = vec3.normalize();
                f4 = 1.0F - (float)Math.pow(-vec31.y, 1.5D);
            }

            f = f4 * 0.34906584F + (1.0F - f4) * f;
            f1 = f4 * (-(float)Math.PI / 2F) + (1.0F - f4) * f1;
        } else if (entity.isCrouching()) {
            f = 0.6981317F;
            f1 = (-(float)Math.PI / 4F);
            f2 = 3.0F;
            f3 = 0.08726646F;
        }

        this.leftWing.y = f2;
        if (entity instanceof AbstractClientPlayer abstractclientplayer) {
            abstractclientplayer.elytraRotX += (f - abstractclientplayer.elytraRotX) * 0.1F;
            abstractclientplayer.elytraRotY += (f3 - abstractclientplayer.elytraRotY) * 0.1F;
            abstractclientplayer.elytraRotZ += (f1 - abstractclientplayer.elytraRotZ) * 0.1F;
            this.leftWing.xRot = abstractclientplayer.elytraRotX;
            this.leftWing.yRot = abstractclientplayer.elytraRotY;
            this.leftWing.zRot = abstractclientplayer.elytraRotZ;
        } else {
            this.leftWing.xRot = f;
            this.leftWing.zRot = f1;
            this.leftWing.yRot = f3;
        }

        this.rightWing.yRot = -this.leftWing.yRot;
        this.rightWing.y = this.leftWing.y;
        this.rightWing.xRot = this.leftWing.xRot;
        this.rightWing.zRot = -this.leftWing.zRot;
    }
}