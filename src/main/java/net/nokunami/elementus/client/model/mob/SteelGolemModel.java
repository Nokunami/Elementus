package net.nokunami.elementus.client.model.mob;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.nokunami.elementus.client.animation.definitions.SteelGolemAnimation;
import net.nokunami.elementus.common.entity.living.SteelGolem;
import org.jetbrains.annotations.NotNull;

public class SteelGolemModel<T extends SteelGolem> extends HierarchicalModel<T> {
    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart left_arm;
    private final ModelPart right_arm;
    private final ModelPart left_leg;
    private final ModelPart right_leg;

    public SteelGolemModel(ModelPart pRoot) {
        this.root = pRoot;
        ModelPart bone = root.getChild("bone");
        this.body = bone.getChild("body");
        this.head = this.body.getChild("head");
        this.left_arm = this.body.getChild("left_arm");
        this.right_arm = this.body.getChild("right_arm");
        this.left_leg = bone.getChild("left_leg");
        this.right_leg = bone.getChild("right_leg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 0.5F, 0.0F));
        PartDefinition body = bone.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-9.0F, -20.0F, -7.0F, 18.0F, 14.0F, 14.0F, new CubeDeformation(0.0F))
                .texOffs(64, 0).addBox(-6.0F, -6.0F, -5.0F, 12.0F, 6.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 5.5F, 0.0F));
        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(64, 16).addBox(-5.0F, -9.0F, -5.0F, 10.0F, 9.0F, 10.0F, new CubeDeformation(0.0F))
                /*.texOffs(64, 35).addBox(-5.0F, -5.0F, -5.0F, 10.0F, 2.0F, 10.0F, new CubeDeformation(0.249F))*/, PartPose.offset(0.0F, -20.0F, -3.5F));
        PartDefinition left_arm = body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(30, 28).addBox(0.0F, -6.0F, -4.0F, 7.0F, 36.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(9.0F, -13.0F, 0.0F));
        PartDefinition right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(0, 28).addBox(-7.0F, -6.0F, -4.0F, 7.0F, 36.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-9.0F, -13.0F, 0.0F));
        PartDefinition left_leg = bone.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(30, 72).addBox(-3.5F, 0.0F, -4.0F, 7.0F, 18.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(4.5F, 5.5F, 0.0F));
        PartDefinition right_leg = bone.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 72).addBox(-3.5F, 0.0F, -4.0F, 7.0F, 18.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.5F, 5.5F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    public void setupAnim(@NotNull T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        resetBodyPos();
        resetArmPos();
        resetLegPos();

        this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
        this.head.xRot = headPitch * ((float)Math.PI / 180F);

        this.right_arm.xRot = (-0.2F + 1.5F * Mth.triangleWave(limbSwing, 13.0F)) * limbSwingAmount;
        this.left_arm.xRot = (-0.2F - 1.5F * Mth.triangleWave(limbSwing, 13.0F)) * limbSwingAmount;

        this.right_leg.xRot = -1.5F * Mth.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
        this.left_leg.xRot = 1.5F * Mth.triangleWave(limbSwing, 13.0F) * limbSwingAmount;

        this.animate(entity.leftAttackAnimationState, SteelGolemAnimation.leftArmAttackLoop, ageInTicks);
        this.animate(entity.rightAttackAnimationState, SteelGolemAnimation.rightArmAttackLoop, ageInTicks);
        this.animate(entity.attackStartAnimState, SteelGolemAnimation.attackStart, ageInTicks);
        this.animate(entity.upswingAttackAnimationState, SteelGolemAnimation.upswingAttackLoop, ageInTicks);
        this.animate(entity.leftAttackEndAnimState, SteelGolemAnimation.leftArmAttackEnd, ageInTicks);
        this.animate(entity.rightAttackEndAnimState, SteelGolemAnimation.rightArmAttackEnd, ageInTicks);
        this.animate(entity.upswingAttackEndAnimationState, SteelGolemAnimation.upswingAttackEnd, ageInTicks);
        this.animate(entity.sitAnimState, SteelGolemAnimation.sitAnim, ageInTicks);
        this.animate(entity.standFromSitAnimState, SteelGolemAnimation.standFromSitAnim, ageInTicks);
    }

//    private void resetBonePos() {
//        this.body.x = 0.0F;
//        this.body.y = 5.0F;
//        this.body.z = 0.0F;
//        this.body.xRot = 0.0F;
//        this.body.yRot = 0.0F;
//        this.body.zRot = 0.0F;
//    }

    private void resetBodyPos() {
        this.body.x = 0.0F;
        this.body.y = 5.0F;
        this.body.z = 0.0F;
        this.body.xRot = 0.0F;
        this.body.yRot = 0.0F;
        this.body.zRot = 0.0F;
    }
    private void resetArmPos() {
        this.left_arm.x = 9.0F;
        this.left_arm.y = -13.0F;
        this.left_arm.z = 0.0F;
        this.left_arm.xRot = 0.0F;
        this.left_arm.yRot = 0.0F;
        this.left_arm.zRot = 0.0F;
        this.right_arm.x = -left_arm.x;
        this.right_arm.y = left_arm.y;
        this.right_arm.z = left_arm.z;
        this.right_arm.xRot = left_arm.xRot;
        this.right_arm.yRot = left_arm.yRot;
        this.right_arm.zRot = left_arm.zRot;
    }
    private void resetLegPos() {
        this.left_leg.x = 4.5F;
        this.left_leg.y = 5.0F;
        this.left_leg.z = 0.0F;
        this.left_leg.xRot = 0.0F;
        this.left_leg.yRot = 0.0F;
        this.left_leg.zRot = 0.0F;
        this.right_leg.x = -left_leg.x;
        this.right_leg.y = left_leg.y;
        this.right_leg.z = left_leg.z;
        this.right_leg.xRot = left_leg.xRot;
        this.right_leg.yRot = left_leg.yRot;
        this.right_leg.zRot = left_leg.zRot;
    }

    @Override
    public @NotNull ModelPart root() {
        return this.root;
    }
}
