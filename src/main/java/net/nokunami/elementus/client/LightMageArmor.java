package net.nokunami.elementus.client;// Made with Blockbench 4.9.4

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LightMageArmor extends HumanoidModel<LivingEntity> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	private final EquipmentSlot slot;
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "wizardarmor2"), "main");
	private final ModelPart Head;
	private final ModelPart Chestplate;
	private final ModelPart RightArm;
	private final ModelPart LeftArm;
	private final ModelPart LeggingsPart;
	private final ModelPart RightLeg;
	private final ModelPart LeftLeg;
	private final ModelPart RightBoot;
	private final ModelPart LeftBoot;

	public LightMageArmor(ModelPart root, EquipmentSlot slot) {
		super(root);
		this.slot = slot;
		this.Head = root.getChild("Head");
		this.Chestplate = root.getChild("Chestplate");
		this.RightArm = root.getChild("RightArm");
		this.LeftArm = root.getChild("LeftArm");
		this.LeggingsPart = root.getChild("LeggingsPart");
		this.RightLeg = root.getChild("RightLeg");
		this.LeftLeg = root.getChild("LeftLeg");
		this.RightBoot = root.getChild("RightBoot");
		this.LeftBoot = root.getChild("LeftBoot");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = HumanoidArmorModel.createMesh(CubeDeformation.NONE, 0.0F);
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.9F))
				.texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0F, 0.0F));

		PartDefinition FlatHat_r1 = Head.addOrReplaceChild("FlatHat_r1", CubeListBuilder.create().texOffs(64, 48).addBox(-8.0F, -7.5F, -7.3F, 16.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0F, 0.0873F, 0.0F, 0.0F));
		PartDefinition Base = Head.addOrReplaceChild("Base_r1", CubeListBuilder.create().texOffs(88, 65).addBox(-5.0F, -12.0F, -5.0F, 10.0F, 5.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0F, 0.0F, 0.0F));
		PartDefinition SmallTip_r1 = Head.addOrReplaceChild("SmallTip_r1", CubeListBuilder.create().texOffs(68, 67).addBox(-2.5F, -18.0F, -5.35F, 5.0F, 8.0F, 5.0F,  new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.3054F, 0.0F, 0.0F));

		PartDefinition Chestplate = partdefinition.addOrReplaceChild("Chestplate", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.85F))
				.texOffs(24, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.7F))
				.texOffs(48, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition RightArm = partdefinition.addOrReplaceChild("RightArm", CubeListBuilder.create(),PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		PartDefinition RightArm_r1 = RightArm.addOrReplaceChild("RightArm_r1", CubeListBuilder.create().texOffs(64, 0).addBox(-3.9F, -2.36F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.875F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.0873F));
		PartDefinition UnderArmLayer_r1 = RightArm.addOrReplaceChild("UnderArmLayer_r1", CubeListBuilder.create().texOffs(80, 0).addBox(-3.4F, -1.8F, -2F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.75F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		PartDefinition ExtraArmLayer_r1 = RightArm.addOrReplaceChild("ExtraArmLayer_r1", CubeListBuilder.create().texOffs(96, 0).addBox(-3.0F, -1.8F, -2F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.45F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));

		PartDefinition LeftArm = partdefinition.addOrReplaceChild("LeftArm", CubeListBuilder.create(),PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		PartDefinition LeftArm_r1 = LeftArm.addOrReplaceChild("LeftArm_r1", CubeListBuilder.create().texOffs(72, 16).addBox(-0.15F, -2.36F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.875F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0873F));
		PartDefinition UnderArmLayer_r2 = LeftArm.addOrReplaceChild("UnderArmLayer_r2", CubeListBuilder.create().texOffs(88, 16).addBox(-0.55F, -1.8F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.75F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0F));
		PartDefinition ExtraArmLayer_r2 = LeftArm.addOrReplaceChild("ExtraArmLayer_r2", CubeListBuilder.create().texOffs(104, 16).addBox(-1.05F, -1.8F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.45F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0));

		PartDefinition LeggingsPart = partdefinition.addOrReplaceChild("LeggingsPart", CubeListBuilder.create().texOffs(0, 32).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.45F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition RightLeg = partdefinition.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(24, 32).addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.4F))
				.texOffs(40, 32).addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.27F)), PartPose.offset(-2.0F, 12.0F, 0.0F));

		PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(56, 32).addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.4F))
				.texOffs(72, 32).addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.27F)), PartPose.offset(2.0F, 12.0F, 0.0F));

		PartDefinition RightBoot = partdefinition.addOrReplaceChild("RightBoot", CubeListBuilder.create().texOffs(0, 48).addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.9F))
				.texOffs(16, 48).addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.75F)), PartPose.offset(-2.0F, 12.0F, 0.0F));

		PartDefinition LeftBoot = partdefinition.addOrReplaceChild("LeftBoot", CubeListBuilder.create().texOffs(32, 48).addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.9F))
				.texOffs(48, 48).addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.75F)), PartPose.offset(2.0F, 12.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 80);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		if (slot == EquipmentSlot.HEAD) {
			poseStack.pushPose();
			this.Head.copyFrom(this.head);
			if (this.young) {
				poseStack.scale(0.75F, 0.75F, 0.75F);
				this.Head.setPos(0.0F, 15.0F, 0.0F);
			}
			this.Head.render(poseStack, vertexConsumer, packedLight, packedOverlay);
			poseStack.popPose();
		}
		if (slot == EquipmentSlot.CHEST) {
			poseStack.pushPose();
			this.Chestplate.copyFrom(this.body);
			this.RightArm.copyFrom(this.rightArm);
			this.LeftArm.copyFrom(this.leftArm);
			if (this.young) {
				poseStack.scale(0.5F, 0.5F, 0.5F);
				this.Chestplate.setPos(0.0F, 24.0F, 0.0F);
				this.RightArm.setPos(-5.0F, 24.0F, 0.0F);
				this.LeftArm.setPos(5.0F, 24.0F, 0.0F);
			}
			this.Chestplate.render(poseStack, vertexConsumer, packedLight, packedOverlay);
			this.RightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay);
			this.LeftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay);
			poseStack.popPose();
		}
		if (slot == EquipmentSlot.LEGS) {
			poseStack.pushPose();
			this.LeggingsPart.copyFrom(this.body);
			this.RightLeg.copyFrom(this.rightLeg);
			this.LeftLeg.copyFrom(this.leftLeg);
			if (this.young) {
				poseStack.scale(0.5F, 0.5F, 0.5F);
				this.LeggingsPart.setPos(0.0F,24.0F,0.0F);
				this.LeftLeg.setPos(2.0F, 36.0F, 0.0F);
				this.RightLeg.setPos(-2.0F, 36.0F, 0.0F);
			}
			this.LeggingsPart.render(poseStack, vertexConsumer, packedLight, packedOverlay);
			this.RightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay);
			this.LeftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay);
			poseStack.popPose();
		}
		if (slot == EquipmentSlot.FEET) {
			poseStack.pushPose();
			this.RightBoot.copyFrom(this.rightLeg);
			this.LeftBoot.copyFrom(this.leftLeg);
			if (this.young) {
				poseStack.scale(0.5F, 0.5F, 0.5F);
				this.LeftBoot.setPos(2.0F, 37.0F, 0.0F);
				this.RightBoot.setPos(-2.0F, 37.0F, 0.0F);
			}
			this.RightBoot.render(poseStack, vertexConsumer, packedLight, packedOverlay);
			this.LeftBoot.render(poseStack, vertexConsumer, packedLight, packedOverlay);
			poseStack.popPose();

		}
	}
}