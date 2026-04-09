package net.nokunami.elementus.client.model.mob;// Made with Blockbench 5.0.1
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.nokunami.elementus.client.animation.AstaliteGolemAnimation;
import net.nokunami.elementus.client.model.CustomModelProperties;
import net.nokunami.elementus.client.model.EHierarchicalModel;
import net.nokunami.elementus.common.entity.living.AstaliteGolemCarrier;
import org.jetbrains.annotations.NotNull;

public class AstaliteGolemCarrierModel<T extends AstaliteGolemCarrier> extends EHierarchicalModel<T> {
	private final ModelPart bone;
	private final ModelPart body;
	private final ModelPart upperBody;
	private final ModelPart leftArm;
	private final ModelPart leftForeArm;
	private final ModelPart rightArm;
	private final ModelPart rightForeArm;
	private final ModelPart latch;
	private final ModelPart leftLeg;
	private final ModelPart rightLeg;
	private float sprintPercent;
	private float sprintPercentO;

	public AstaliteGolemCarrierModel(ModelPart root) {
		super(root);
		bone = root.getChild("bone");
		body = bone.getChild("body");
		upperBody = body.getChild("upperBody");
		leftArm = upperBody.getChild("leftArm");
		leftForeArm = leftArm.getChild("leftForeArm");
		rightArm = upperBody.getChild("rightArm");
		rightForeArm = rightArm.getChild("rightForeArm");
		latch = upperBody.getChild("latch");
		leftLeg = bone.getChild("leftLeg");
		rightLeg = bone.getChild("rightLeg");
	}

	public static LayerDefinition createBodyLayer() {
		return LayerDefinition.create(createMesh(false), 128, 128);
	}

	public static MeshDefinition createMesh(boolean type) {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		CubeDeformation zero = new CubeDeformation(0);

		PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0, 24, 0));

		PartDefinition body = bone.addOrReplaceChild("body", type ? CubeListBuilder.create() : CubeListBuilder.create().texOffs(72, 0).addBox(-7, -9, -5, 14, 9, 10, zero), PartPose.offset(0, -15, 0));

		PartDefinition upperBody = body.addOrReplaceChild("upperBody", type ? CubeListBuilder.create() : CubeListBuilder.create().texOffs(0, 0).addBox(-11, -15, -7, 22, 15, 14, new CubeDeformation(0.01F)), PartPose.offset(0, -7, 0));

		PartDefinition leftArm = upperBody.addOrReplaceChild("leftArm", type ? CubeListBuilder.create() : CubeListBuilder.create().texOffs(44, 29).addBox(0, -6, -5.5F, 11, 12, 11, zero), PartPose.offset(11, -10, 0));
		leftArm.addOrReplaceChild("leftForeArm", type ? CubeListBuilder.create() : CubeListBuilder.create().texOffs(44, 52).addBox(-3.5F, -2, -4, 7, 24, 8, zero), PartPose.offset(4.5F, 6, 0));

		PartDefinition rightArm = upperBody.addOrReplaceChild("rightArm", type ? CubeListBuilder.create() : CubeListBuilder.create().texOffs(0, 29).addBox(-11, -6, -5.5F, 11, 12, 11, zero), PartPose.offset(-11, -10, 0));
		rightArm.addOrReplaceChild("rightForeArm", type ? CubeListBuilder.create() : CubeListBuilder.create().texOffs(0, 52).addBox(-3.5F, -2, -4, 7, 24, 8, zero), PartPose.offset(-4.5F, 6, 0));

		upperBody.addOrReplaceChild("latch", type ? CubeListBuilder.create() : CubeListBuilder.create().texOffs(72, 19).addBox(-5, -8, -0.5F, 10, 8, 1, new CubeDeformation(0.01F)), PartPose.offset(0, 0, -7));
		bone.addOrReplaceChild("leftLeg", type ? CubeListBuilder.create() : CubeListBuilder.create().texOffs(44, 84).addBox(-4, 0, -4, 8, 15, 8, zero), PartPose.offset(5, -15, 0));
		bone.addOrReplaceChild("rightLeg", type ? CubeListBuilder.create() : CubeListBuilder.create().texOffs(0, 84).addBox(-4, 0, -4, 8, 15, 8, zero), PartPose.offset(-5, -15, 0));

		return meshdefinition;
	}

	@Override
	public void setupAnim(@NotNull T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		if (!entity.isRenderedOnClient) animateHeadLookTarget(entity, netHeadYaw, headPitch);
//		animateWalk(entity, limbSwing, limbSwingAmount);
		float f = 1.0F;
		if (entity.getFallFlyingTicks() > 4) {
			f = (float) entity.getDeltaMovement().lengthSqr();
			f /= 0.2F;
			f *= f * f;
		}
		if (f < 1.0F) {
			f = 1.0F;
		}
		float speed = 1.4F; //1.4f

//		bone.y += limbSwingAmount * 2;

//		bone.y += Mth.cos(limbSwing * (0.6662F * 2)) * speed * limbSwingAmount / f;
//		bone.zRot += Mth.cos(limbSwing * (0.6662F * 2)) * speed * (limbSwingAmount * 0.025F) / f;

//		leftLeg.xRot += Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * speed * limbSwingAmount / f;
//		leftLeg.y += Math.max(0, Mth.sin(limbSwing * 0.6662F) * speed * (limbSwingAmount * 2) / f);
//		leftLeg.z += Mth.sin(limbSwing * 0.6662F + (float) Math.PI) * speed * (limbSwingAmount * 4) / f;

//		rightLeg.xRot += Mth.cos(limbSwing * 0.6662F) * speed * limbSwingAmount / f;
//		rightLeg.y += Mth.sin(limbSwing * 0.6662F) * speed * limbSwingAmount / f;
//		rightLeg.z += Mth.sin(limbSwing * 0.6662F + (float) Math.PI) * speed * (limbSwingAmount * 4) / f;

		animate(entity.sitAnim, AstaliteGolemAnimation.sit, ageInTicks);
		animate(entity.standAnim, AstaliteGolemAnimation.stand, ageInTicks);
		animate(entity.chestOpened, AstaliteGolemAnimation.latchOpen, ageInTicks);
		animate(entity.chestClosed, AstaliteGolemAnimation.latchClose, ageInTicks);
		animate(entity.golemAttackAnim, AstaliteGolemAnimation.crossPunchLeft, ageInTicks);
	}

	@Override
	public void postSetupAnim(T entity, CustomModelProperties modelProperties) {
		root().getAllParts().forEach(ModelPart::resetPose);
		animateWalk(entity, modelProperties.limbSwing(), modelProperties.limbSwingAmount(), modelProperties.partialTick());
	}

	private void animateHeadLookTarget(T entity, float yaw, float pitch) {
//		upperBody.yRot += yaw * ((float)Math.PI / 180F) * 0.8F;
//		upperBody.xRot += pitch * ((float)Math.PI / 180F) * 0.1F;
//		body.yRot += yaw * ((float)Math.PI / 180F) * 0.2F;
//		body.xRot += pitch * ((float)Math.PI / 180F) * 0.4F;
		float yawLimit = 17.5F;
		float pitchLimit = 20;
		upperBody.xRot += Mth.clamp(pitch, -pitchLimit, pitchLimit) * ((float) Math.PI / 180F);
		upperBody.yRot += Mth.clamp(yaw, -yawLimit, yawLimit) * ((float) Math.PI / 180F);
		float yaw1 = yaw > 0 ? Math.max(0, yaw - yawLimit) : Math.min(0, yaw + yawLimit);
		float pitch1 = pitch > 0 ? Math.max(0, pitch - pitchLimit) : Math.min(0, pitch + pitchLimit);
//		if (!entity.isRenderedOnClient) {
//		}
		body.yRot += yaw1 * ((float) Math.PI / 180F);
		body.yRot += pitch1 * ((float) Math.PI / 180F);

		leftLeg.yRot += yaw * ((float) Math.PI / 180F) * 0.1F;
		rightLeg.yRot += yaw * ((float) Math.PI / 180F) * 0.1F;
	}

	private void animateWalk(T entity, float limbSwing, float limbSwingAmount, float partialTick) {
        float p = entity.getMovementType().isRunning() ? 1 : -2;
        sprintPercentO = sprintPercent;
        sprintPercent = Mth.clamp(sprintPercent + (p / 5), 0, 1);
//		float sprint = entity.getMovementType().equals(MovementType.RUN) ? 3 : 1;

		float alpha = 1 + Mth.lerp(partialTick, entity.getSprintOPercent(), entity.getSprintPercent());
//		float alpha1 = Mth.lerp(partialTick, sprintPercentO, sprintPercent);
//		float sprint = 1 + alpha;
//		float sprint = entity.getMovementType().equals(MovementType.RUN) ? Math.max(1, 1 + (alpha * 2)) : 1;
//		float sprint = (float) Math.max(1, 1 + ((Math.round(alpha * 10.0) / 10.0) * 2));
//		float sprint = Math.max(1, 1 + (alpha1 * 2));

		float f = Math.min(0.5F, 3.0F * limbSwingAmount);
		float f1 = (limbSwing * 0.4662F);
		float f2 = Mth.cos(f1) * f * alpha;
		float f2p = Mth.cos(f1 + Mth.PI) * f * alpha;
		float f3 = Mth.sin(f1) * f * alpha;
		float f3p = Mth.sin(f1 + Mth.PI) * f * alpha;
		float f4 = Math.min(0.35F, f);
//		float sprintArm = Math.min(sprint, 1.25F);

		float pos = Math.min(0.25F, limbSwing);
        float bobbing = 0.1F * f2 * 1F * f;
//		this.head.zRot += 0.3F * f3 * f;
//		this.head.xRot += 1.2F * Mth.cos(f1 + ((float)Math.PI / 2F)) * f4;
//		this.body.zRot = 0.1F * f3 * f;
//		this.body.xRot = 1.0F * f2 * f4;
//		body.y += bobbing;

//		FloatsStuff fs = inst();

		body.zRot += bobbing;
		body.yRot += bobbing;

//		leftArm.xRot += -(0.8F * f2 * f);
//		leftArm.zRot += 0.0F;
//		rightArm.xRot += -(0.8F * f3 * f);
//		rightArm.zRot += 0.0F;

		leftArm.y += f2p;
		leftArm.z += 3.0F * f2p;
		leftArm.xRot += f2p;

		rightArm.y += f2;
		rightArm.z += 3.0F * f3;
		rightArm.xRot += f2;

		leftLeg.y += f2;
		leftLeg.z += 3.0F * f3;
		leftLeg.xRot += f2;

		rightLeg.y += f2p;
		rightLeg.z += 3.0F * f2p;
		rightLeg.xRot += f2p;
	}
}