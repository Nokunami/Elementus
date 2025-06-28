package net.nokunami.elementus.client.model.mob;

import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.nokunami.elementus.client.animation.definitions.SteelGolemAnimation;
import net.nokunami.elementus.client.animation.definitions.SteelGolemAttackAnimation;
import net.nokunami.elementus.client.animation.definitions.SteelGolemChestAnimation;
import net.nokunami.elementus.common.entity.living.SteelGolem;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class SteelGolemModel<T extends SteelGolem> extends HierarchicalModel<T> {
    public final float walkMaxAnimSpeed = 4.45F;
    public final float walkAnimScaleFactor = 500;
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
        body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(30, 28).addBox(0.0F, -6.0F, -4.0F, 7.0F, 36.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(9.0F, -13.0F, 0.0F));
        body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(0, 28).addBox(-7.0F, -6.0F, -4.0F, 7.0F, 36.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-9.0F, -13.0F, 0.0F));
        bone.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(30, 72).addBox(-3.5F, 0.0F, -4.0F, 7.0F, 18.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(4.5F, 5.5F, 0.0F));
        bone.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 72).addBox(-3.5F, 0.0F, -4.0F, 7.0F, 18.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.5F, 5.5F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    public static LayerDefinition createBodyCarpetLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 0.5F, 0.0F));
        PartDefinition body = bone.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-9.0F, -20.0F, -7.0F, 18.0F, 38.0F, 14.0F, new CubeDeformation(0.0F))
                .texOffs(64, 0).addBox(-6.0F, -6.0F, -5.0F, 12.0F, 6.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 5.5F, 0.0F));
        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(64, 16).addBox(-5.0F, -9.0F, -5.0F, 10.0F, 9.0F, 10.0F, new CubeDeformation(0.0F))
                /*.texOffs(64, 35).addBox(-5.0F, -5.0F, -5.0F, 10.0F, 2.0F, 10.0F, new CubeDeformation(0.249F))*/, PartPose.offset(0.0F, -20.0F, -3.5F));
        PartDefinition left_arm = body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(30, 52).addBox(0.0F, -6.0F, -4.0F, 7.0F, 36.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(9.0F, -13.0F, 0.0F));
        PartDefinition right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(0, 52).addBox(-7.0F, -6.0F, -4.0F, 7.0F, 36.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-9.0F, -13.0F, 0.0F));
        PartDefinition left_leg = bone.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(30, 96).addBox(-3.5F, 0.0F, -4.0F, 7.0F, 18.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(4.5F, 5.5F, 0.0F));
        PartDefinition right_leg = bone.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 96).addBox(-3.5F, 0.0F, -4.0F, 7.0F, 18.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.5F, 5.5F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    public static LayerDefinition createExtraLayer1(CubeDeformation deformation) {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 0.5F, 0.0F));
        PartDefinition body = bone.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-9.0F, -20.0F, -7.0F, 18.0F, 14.0F, 14.0F, deformation)
                .texOffs(0, 98).addBox(-6.0F, -6.0F, -5.0F, 12.0F, 6.0F, 10.0F, deformation), PartPose.offset(0.0F, 5.5F, 0.0F));
        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(84, 98).addBox(-5.0F, -9.0F, -5.0F, 10.0F, 9.0F, 10.0F, deformation)
                /*.texOffs(44, 102).addBox(-5.0F, -5.0F, -5.0F, 10.0F, 2.0F, 10.0F, deformation.extend(0.25F))*/, PartPose.offset(0.0F, -20.0F, -3.5F));
        PartDefinition left_arm = body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(90, 28).addBox(0.0F, -6.0F, -4.0F, 7.0F, 36.0F, 8.0F, deformation), PartPose.offset(9.0F, -13.0F, 0.0F));
        PartDefinition right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(0, 28).addBox(-7.0F, -6.0F, -4.0F, 7.0F, 36.0F, 8.0F, deformation), PartPose.offset(-9.0F, -13.0F, 0.0F));
        PartDefinition left_leg = bone.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(90, 72).addBox(-3.5F, 0.0F, -4.0F, 7.0F, 18.0F, 8.0F, deformation), PartPose.offset(4.5F, 5.5F, 0.0F));
        PartDefinition right_leg = bone.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 72).addBox(-3.5F, 0.0F, -4.0F, 7.0F, 18.0F, 8.0F, deformation), PartPose.offset(-4.5F, 5.5F, 0.0F));

        return LayerDefinition.create(meshdefinition, 192, 192);
    }

    public static LayerDefinition createExtraLayer2(CubeDeformation deformation) {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 0.5F, 0.0F));
        PartDefinition body = bone.addOrReplaceChild("body", CubeListBuilder.create().texOffs(64, 0).addBox(-9.0F, -20.0F, -7.0F, 18.0F, 14.0F, 14.0F, deformation)
                .texOffs(0, 114).addBox(-6.0F, -6.0F, -5.0F, 12.0F, 6.0F, 10.0F, deformation), PartPose.offset(0.0F, 5.5F, 0.0F));
        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(84, 117).addBox(-5.0F, -9.0F, -5.0F, 10.0F, 9.0F, 10.0F, deformation)
                /*.texOffs(44, 121).addBox(-5.0F, -5.0F, -5.0F, 10.0F, 2.0F, 10.0F, deformation.extend(0.25F))*/, PartPose.offset(0.0F, -20.0F, -3.5F));
        PartDefinition left_arm = body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(120, 28).addBox(0.0F, -6.0F, -4.0F, 7.0F, 36.0F, 8.0F, deformation), PartPose.offset(9.0F, -13.0F, 0.0F));
        PartDefinition right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(30, 28).addBox(-7.0F, -6.0F, -4.0F, 7.0F, 36.0F, 8.0F, deformation), PartPose.offset(-9.0F, -13.0F, 0.0F));
        PartDefinition left_leg = bone.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(120, 72).addBox(-3.5F, 0.0F, -4.0F, 7.0F, 18.0F, 8.0F, deformation), PartPose.offset(4.5F, 5.5F, 0.0F));
        PartDefinition right_leg = bone.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(30, 72).addBox(-3.5F, 0.0F, -4.0F, 7.0F, 18.0F, 8.0F, deformation), PartPose.offset(-4.5F, 5.5F, 0.0F));

        return LayerDefinition.create(meshdefinition, 192, 192);
    }

    public static LayerDefinition createExtraLayer3(CubeDeformation deformation) {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 0.5F, 0.0F));
        PartDefinition body = bone.addOrReplaceChild("body", CubeListBuilder.create().texOffs(128, 0).addBox(-9.0F, -20.0F, -7.0F, 18.0F, 14.0F, 14.0F, deformation)
                .texOffs(0, 130).addBox(-6.0F, -6.0F, -5.0F, 12.0F, 6.0F, 10.0F, deformation), PartPose.offset(0.0F, 5.5F, 0.0F));
        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(84, 136).addBox(-5.0F, -9.0F, -5.0F, 10.0F, 9.0F, 10.0F, deformation)
                /*.texOffs(44, 140).addBox(-5.0F, -5.0F, -5.0F, 10.0F, 2.0F, 10.0F, deformation.extend(0.25F))*/, PartPose.offset(0.0F, -20.0F, -3.5F));
        PartDefinition left_arm = body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(150, 28).addBox(0.0F, -6.0F, -4.0F, 7.0F, 36.0F, 8.0F, deformation), PartPose.offset(9.0F, -13.0F, 0.0F));
        PartDefinition right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(60, 28).addBox(-7.0F, -6.0F, -4.0F, 7.0F, 36.0F, 8.0F, deformation), PartPose.offset(-9.0F, -13.0F, 0.0F));
        PartDefinition left_leg = bone.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(150, 72).addBox(-3.5F, 0.0F, -4.0F, 7.0F, 18.0F, 8.0F, deformation), PartPose.offset(4.5F, 5.5F, 0.0F));
        PartDefinition right_leg = bone.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(60, 72).addBox(-3.5F, 0.0F, -4.0F, 7.0F, 18.0F, 8.0F, deformation), PartPose.offset(-4.5F, 5.5F, 0.0F));

        return LayerDefinition.create(meshdefinition, 192, 192);
    }

    public void setupAnim(@NotNull T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        resetBodyPos();
        resetHeadPos();
        resetArmPos();
        resetLegPos();

        this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
        this.head.xRot = headPitch * ((float)Math.PI / 180F);

        AnimationDefinition loopAttackAnim = entity.getAttackType() == 0 ? SteelGolemAttackAnimation.leftAttackLoop : entity.getAttackType() == 1 ? SteelGolemAttackAnimation.rightAttackLoop : SteelGolemAttackAnimation.upswingAttackLoop4;
        AnimationDefinition endAttackAnim = entity.getAttackType() == 0 ? SteelGolemAttackAnimation.leftAttackEnd : entity.getAttackType() == 1 ? SteelGolemAttackAnimation.rightAttackEnd : SteelGolemAttackAnimation.upswingAttackEnd3;

        this.animate(entity.attackLoopAnimationState, loopAttackAnim, ageInTicks);
        this.animate(entity.attackEndAnimationState, endAttackAnim, ageInTicks);
        this.animate(entity.upswingAttackAnimationState, SteelGolemAttackAnimation.upswingAttackLoop4, ageInTicks);

        if (entity.isCrouching()) {
            this.applyStatic(SteelGolemAnimation.crouch);
        }

        this.animate(entity.sitFromStandAnimState, SteelGolemAnimation.sitFromStand, ageInTicks);
        this.animate(entity.standFromSitAnimState, SteelGolemAnimation.standFromSit, ageInTicks);
        this.animate(entity.brokenAnim, SteelGolemAnimation.brokenDown, ageInTicks);
        this.animate(entity.repairedAnim, SteelGolemAnimation.repairUp, ageInTicks);
        this.animate(entity.ridden, SteelGolemAnimation.ridden, ageInTicks);
        this.animate(entity.unRide, SteelGolemAnimation.unRide, ageInTicks);
        if (!entity.isChassisBroken()) {
            if (entity.isVehicle() || entity.isSprinting()) {
                this.animateWalk(SteelGolemAnimation.walkCycleWhileRidden, limbSwing, limbSwingAmount, walkMaxAnimSpeed, walkAnimScaleFactor);
            } else {
                this.animateWalk(SteelGolemAnimation.walkCycle, limbSwing, limbSwingAmount, walkMaxAnimSpeed, walkAnimScaleFactor);
            }
        }
        this.animate(entity.chestOpened, SteelGolemChestAnimation.chestOpen, ageInTicks);
        this.animate(entity.chestClosed, SteelGolemChestAnimation.chestClosed, ageInTicks);
    }

    private void resetBodyPos() {
        this.body.x = 0.0F;
        this.body.y = 5.0F;
        this.body.z = 0.0F;
        this.body.xRot = 0.0F;
        this.body.yRot = 0.0F;
        this.body.zRot = 0.0F;
    }
    private void resetHeadPos() {
        this.head.x = 0.0F;
        this.head.y = -20.0F;
        this.head.z = -3.5F;
        this.head.xRot = 0.0F;
        this.head.yRot = 0.0F;
        this.head.zRot = 0.0F;
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
