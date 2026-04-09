package net.nokunami.elementus.client.model.mob;// Made with Blockbench 5.0.7
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.nokunami.elementus.client.animation.definitions.SteelGolemAnimation;
import net.nokunami.elementus.client.animation.definitions.SteelGolemAttackAnimation;
import net.nokunami.elementus.client.animation.definitions.SteelGolemChestAnimation;
import net.nokunami.elementus.client.model.CustomModelProperties;
import net.nokunami.elementus.client.model.EHierarchicalModel;
import net.nokunami.elementus.client.model.FloatsStuff;
import net.nokunami.elementus.common.entity.living.AstaliteGolemLongarm;
import org.jetbrains.annotations.NotNull;

import static net.nokunami.elementus.client.model.FloatsStuff.inst;

public class AstaliteGolemLongarmModel<T extends AstaliteGolemLongarm> extends EHierarchicalModel<T> {
	private final ModelPart bone;
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart left_arm;
	private final ModelPart right_arm;
	private final ModelPart left_leg;
	private final ModelPart right_leg;

	public AstaliteGolemLongarmModel(ModelPart root) {
		super(root);
		bone = root.getChild("bone");
		body = bone.getChild("body");
		head = body.getChild("head");
		left_arm = body.getChild("left_arm");
		right_arm = body.getChild("right_arm");
		left_leg = bone.getChild("left_leg");
		right_leg = bone.getChild("right_leg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0, 0.5F, 0));

		PartDefinition body = bone.addOrReplaceChild("body", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-9, -20, -7, 18, 14, 14)
				.texOffs(64, 0).addBox(-6, -6, -5, 12, 6, 10), PartPose.offset(0, 5.5F, 0));
		body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(64, 16).addBox(-5, -9, -5, 10, 9, 10), PartPose.offset(0, -20, -3.5F));

		body.addOrReplaceChild("left_arm", CubeListBuilder.create()
				.texOffs(36, 28).addBox(0, -6, -5, 8, 14, 10, new CubeDeformation(0.1F))
				.texOffs(36, 52).addBox(0, -5.99F, -4, 7, 36, 8), PartPose.offset(9, -13, 0));

		body.addOrReplaceChild("right_arm", CubeListBuilder.create()
				.texOffs(0, 28).addBox(-8, -6, -5, 8, 14, 10, new CubeDeformation(0.1F))
				.texOffs(0, 52).addBox(-7, -5.99F, -4, 7, 36, 8), PartPose.offset(-9, -13, 0));
		
		bone.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(36, 96).addBox(-3.5F, 0, -4, 7, 18, 8), PartPose.offset(4.5F, 5.5F, 0));
		bone.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 96).addBox(-3.5F, 0, -4, 7, 18, 8), PartPose.offset(-4.5F, 5.5F, 0));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(@NotNull T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

		this.head.yRot += netHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot += headPitch * ((float)Math.PI / 180F);

		AnimationDefinition loopAttackAnim = entity.getAttackType() == 0 ? SteelGolemAttackAnimation.leftAttackLoop : entity.getAttackType() == 1 ? SteelGolemAttackAnimation.rightAttackLoop : SteelGolemAttackAnimation.upswingAttackLoop4;
//        AnimationDefinition endAttackAnim = entity.getAttackType() == 0 ? SteelGolemAttackAnimation.leftAttackEnd : entity.getAttackType() == 1 ? SteelGolemAttackAnimation.rightAttackEnd : SteelGolemAttackAnimation.upswingAttackEnd3;

		this.animate(entity.golemAttackAnim, loopAttackAnim, ageInTicks);
		this.animate(entity.upswingAttackAnimationState, SteelGolemAttackAnimation.upswingAttackLoop4, ageInTicks);

		if (entity.isCrouching()) {
			this.applyStatic(SteelGolemAnimation.crouch);
		}

		this.animate(entity.sitAnim, SteelGolemAnimation.sitFromStand, ageInTicks);
		this.animate(entity.standAnim, SteelGolemAnimation.standFromSit, ageInTicks);
		this.animate(entity.brokenAnim, SteelGolemAnimation.brokenDown, ageInTicks);
		this.animate(entity.repairedAnim, SteelGolemAnimation.repairUp, ageInTicks);
		this.animate(entity.riddenAnim, SteelGolemAnimation.ridden, ageInTicks);
		this.animate(entity.unRideAnim, SteelGolemAnimation.unRide, ageInTicks);
//		if (!entity.isChassisBroken()) {
//			if (entity.isVehicle() || entity.isSprinting()) {
//				this.animateWalk(SteelGolemAnimation.walkCycleWhileRidden, limbSwing, limbSwingAmount, 4.5F, 4.5F);
//			} else {
//				this.animateWalk(SteelGolemAnimation.walkCycle, limbSwing, limbSwingAmount, 4.5F, 4.5F);
//			}
//		}
		this.animate(entity.chestOpened, SteelGolemChestAnimation.chestOpen, ageInTicks);
		this.animate(entity.chestClosed, SteelGolemChestAnimation.chestClosed, ageInTicks);
	}

	@Override
	public void postSetupAnim(T entity, CustomModelProperties modelProperties) {
		root().getAllParts().forEach(ModelPart::resetPose);

		float limbSwing = modelProperties.limbSwing();
		float limbSwingAmount = modelProperties.limbSwingAmount();
		float partialTick = modelProperties.partialTick();
		float ageInTick = modelProperties.ageInTicks();

//		float lfsm = Math.min(0.5F, 3 * limbSwingAmount);
//		float armSwing = limbSwing * 0.5F;
//		float bodySwing = limbSwing * 0.5F;
//
//		float s1 = Mth.cos(armSwing) * lfsm;
//		float s11 = Math.min(0, s1);
//		float s1p = Mth.cos(armSwing + Mth.PI) * lfsm;
//		float s11p = Math.min(0, s1p);
//
//		float s2 = Mth.sin(limbSwing) * lfsm;
//		float s21 = Math.min(0, s2);
//		float s2p = Mth.sin(limbSwing + Mth.PI) * lfsm;
//		float s21p = Math.min(0, s2p);

		float sprint = 1 + Mth.lerp(partialTick, entity.getSprintOPercent(), entity.getSprintPercent());
		FloatsStuff fs = inst(limbSwing * 0.525F, Math.min(0.5F, 3 * limbSwingAmount));
		FloatsStuff fs1 = inst((limbSwing * 0.525F) * 2, Math.min(0.5F, 3 * limbSwingAmount));

//		head.xRot += (fs.minSin(0) + fs.minSinPI(0)) * 0.25F;
		head.xRot += -fs.getAmount() * sprint * 0.25F;
		head.yRot += (fs.cosPI()) * sprint * 0.15F;

//		body.xRot += (fs.minSin(0) + fs.minSinPI(0)) * 0.25F;
		body.xRot += fs.getAmount() * sprint * 0.25F;
		body.yRot += (fs.cos()) * sprint * 0.15F;
//		body.y += (fs1.minCos(0) + fs1.minCosPI(0)) * sprint * 1;
		body.y += fs1.cos(0) * sprint * 2;

		left_arm.xRot += fs.cos() * sprint * 0.5F;
		left_arm.yRot += fs.cos() * 0.25F;

		right_arm.xRot += fs.cosPI() * sprint * 0.5F;
		right_arm.yRot += fs.cos() * 0.25F;

		left_leg.xRot += fs.cosPI() * sprint * 0.9F;
		left_leg.y += fs.minSin(0) * sprint * 6F;
		left_leg.z += fs.minSinPI(0) * sprint * 6F;

		right_leg.xRot += fs.cos() * sprint * 0.9F;
		right_leg.y += fs.minSinPI(0) * sprint * 6F;
		right_leg.z += fs.minSin(0) * sprint * 6F;
	}
}