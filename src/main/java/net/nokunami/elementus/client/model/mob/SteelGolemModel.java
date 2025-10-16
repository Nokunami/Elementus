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
import net.nokunami.elementus.client.model.ModelUtil;
import net.nokunami.elementus.common.entity.living.AstaliteGolem;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class SteelGolemModel<T extends AstaliteGolem> extends HierarchicalModel<T> {
    public final float walkMaxAnimSpeed = 4.5F;
    public final float walkAnimScaleFactor = 100;
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

        PartDefinition body = bone.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-9.0F, -20.0F, -7.0F, 18.0F, 14.0F, 14.0F, new CubeDeformation(0.0F))
                .texOffs(0, 28).addBox(-9.0F, -6.0F, -7.0F, 18.0F, 24.0F, 14.0F, new CubeDeformation(0.0F))
                .texOffs(128, 0).addBox(-6.0F, -6.0F, -5.0F, 12.0F, 6.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 5.5F, 0.0F));

        PartDefinition jacket = body.addOrReplaceChild("jacket", CubeListBuilder.create().texOffs(128, 16).addBox(-6.0F, -6.0F, -5.0F, 12.0F, 6.0F, 10.0F, new CubeDeformation(0.25F))
                .texOffs(64, 0).addBox(-9.0F, -20.0F, -7.0F, 18.0F, 14.0F, 14.0F, new CubeDeformation(0.25F))
                .texOffs(64, 28).addBox(-9.0F, -5.55F, -7.0F, 18.0F, 24.0F, 14.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(128, 32).addBox(-5.0F, -9.0F, -5.0F, 10.0F, 9.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(128, 51).addBox(-5.0F, -9.0F, -5.0F, 10.0F, 9.0F, 10.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, -20.0F, -3.5F));

        PartDefinition left_arm = body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(64, 66).addBox(0.0F, -6.0F, -4.0F, 7.0F, 36.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(96, 66).addBox(0.0F, -6.0F, -4.0F, 7.0F, 36.0F, 8.0F, new CubeDeformation(0.25F)), PartPose.offset(9.0F, -13.0F, 0.0F));

        PartDefinition right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(0, 66).addBox(-7.0F, -6.0F, -4.0F, 7.0F, 36.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(32, 66).addBox(-7.0F, -6.0F, -4.0F, 7.0F, 36.0F, 8.0F, new CubeDeformation(0.25F)), PartPose.offset(-9.0F, -13.0F, 0.0F));

        PartDefinition left_leg = bone.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(64, 110).addBox(-3.5F, 0.0F, -4.0F, 7.0F, 18.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(96, 110).addBox(-3.5F, 0.0F, -4.0F, 7.0F, 18.0F, 8.0F, new CubeDeformation(0.25F)), PartPose.offset(4.5F, 5.5F, 0.0F));

        PartDefinition right_leg = bone.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 110).addBox(-3.5F, 0.0F, -4.0F, 7.0F, 18.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(32, 110).addBox(-3.5F, 0.0F, -4.0F, 7.0F, 18.0F, 8.0F, new CubeDeformation(0.25F)), PartPose.offset(-4.5F, 5.5F, 0.0F));

        return LayerDefinition.create(meshdefinition, 176, 176);
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
        resetPartPosRot();

        this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
        this.head.xRot = headPitch * ((float)Math.PI / 180F);

        AnimationDefinition loopAttackAnim = entity.getAttackType() == 0 ? SteelGolemAttackAnimation.leftAttackLoop : entity.getAttackType() == 1 ? SteelGolemAttackAnimation.rightAttackLoop : SteelGolemAttackAnimation.upswingAttackLoop4;
//        AnimationDefinition endAttackAnim = entity.getAttackType() == 0 ? SteelGolemAttackAnimation.leftAttackEnd : entity.getAttackType() == 1 ? SteelGolemAttackAnimation.rightAttackEnd : SteelGolemAttackAnimation.upswingAttackEnd3;

        this.animate(entity.attackLoopAnimationState, loopAttackAnim, ageInTicks);
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

    private void resetPartPosRot() {
        ModelUtil.setDefaults(root, 0, 0.5, 0);
        ModelUtil.setDefaults(body, 0, 5.5, 0);
        ModelUtil.setDefaults(head, 0, -20, -3.5);
        ModelUtil.setDefaults(left_arm, 9, -13, 0);
        ModelUtil.setDefaults(right_arm, -9, -13, 0);
        ModelUtil.setDefaults(left_leg, -4.5, 5.5, 0);
        ModelUtil.setDefaults(right_leg, 4.5, 5.5, 0);
    }

    @Override
    public @NotNull ModelPart root() {
        return this.root;
    }
}
