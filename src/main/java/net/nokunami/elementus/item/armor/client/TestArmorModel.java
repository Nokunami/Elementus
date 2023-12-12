package net.nokunami.elementus.item.armor.client;// Made with Blockbench 4.9.1
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.HashMap;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class TestArmorModel<T extends LivingEntity> extends HumanoidModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("elementus", "testarmormodel"), "main");
	private final ModelPart Head;
	private final ModelPart UnderHat;


	private final ModelPart Chestplate;
	private final ModelPart UnderShirt;

	private final ModelPart RightArm;
	private final ModelPart RightUnderArm;

	private final ModelPart LeftArm;
	private final ModelPart LeftUnderArm;


	private final ModelPart LeggingsPart;

	private final ModelPart RightLeg;
	private final ModelPart RightUnderPant;

	private final ModelPart LeftLeg;
	private final ModelPart LeftUnderPant;


	private final ModelPart RightBoot;
	private final ModelPart RIghtSock;

	private final ModelPart LeftBoot;
	private final ModelPart LeftSock;

	private final EquipmentSlot slot;

	public TestArmorModel(ModelPart root, EquipmentSlot slot) {
		super(root);
		this.slot = slot;
		this.Head = root.getChild("Head");
		this.UnderHat = root.getChild("UnderHat");
		this.Chestplate = root.getChild("Chestplate");
		this.UnderShirt = root.getChild("UnderShirt");
		this.RightArm = root.getChild("RightArm");
		this.RightUnderArm = root.getChild("RightUnderArm");
		this.LeftArm = root.getChild("LeftArm");
		this.LeftUnderArm = root.getChild("LeftUnderArm");
		this.LeggingsPart = root.getChild("LeggingsPart");
		this.RightLeg = root.getChild("RightLeg");
		this.RightUnderPant = root.getChild("RightUnderPant");
		this.LeftLeg = root.getChild("LeftLeg");
		this.LeftUnderPant = root.getChild("LeftUnderPant");
		this.RightBoot = root.getChild("RightBoot");
		this.RIghtSock = root.getChild("RIghtSock");
		this.LeftBoot = root.getChild("LeftBoot");
		this.LeftSock = root.getChild("LeftSock");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(1.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));

		PartDefinition UnderHat = partdefinition.addOrReplaceChild("UnderHat", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.6F)), PartPose.offset(0.0F, -1.0F, 0.0F));

		PartDefinition Chestplate = partdefinition.addOrReplaceChild("Chestplate", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(1.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition UnderShirt = partdefinition.addOrReplaceChild("UnderShirt", CubeListBuilder.create().texOffs(24, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.6F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition RightArm = partdefinition.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(64, 0).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(1.0F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition RightUnderArm = partdefinition.addOrReplaceChild("RightUnderArm", CubeListBuilder.create().texOffs(80, 0).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.6F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition LeftArm = partdefinition.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(48, 16).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(1.0F)), PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition LeftUnderArm = partdefinition.addOrReplaceChild("LeftUnderArm", CubeListBuilder.create().texOffs(64, 16).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.6F)), PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition LeggingsPart = partdefinition.addOrReplaceChild("LeggingsPart", CubeListBuilder.create().texOffs(0, 32).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.55F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition RightLeg = partdefinition.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(24, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.65F)), PartPose.offset(-2.0F, 12.0F, 0.0F));

		PartDefinition RightUnderPant = partdefinition.addOrReplaceChild("RightUnderPant", CubeListBuilder.create().texOffs(40, 32).addBox(-2.0F, -0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offset(-2.0F, 12.0F, 0.0F));

		PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(56, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.65F)), PartPose.offset(2.0F, 12.0F, 0.0F));

		PartDefinition LeftUnderPant = partdefinition.addOrReplaceChild("LeftUnderPant", CubeListBuilder.create().texOffs(72, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offset(2.0F, 12.0F, 0.0F));

		PartDefinition RightBoot = partdefinition.addOrReplaceChild("RightBoot", CubeListBuilder.create().texOffs(0, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(1.0F)), PartPose.offset(-2.0F, 12.0F, 0.0F));

		PartDefinition RIghtSock = partdefinition.addOrReplaceChild("RIghtSock", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.75F)), PartPose.offset(-2.0F, 12.0F, 0.0F));

		PartDefinition LeftBoot = partdefinition.addOrReplaceChild("LeftBoot", CubeListBuilder.create().texOffs(32, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(1.0F)), PartPose.offset(2.0F, 12.0F, 0.0F));

		PartDefinition LeftSock = partdefinition.addOrReplaceChild("LeftSock", CubeListBuilder.create().texOffs(48, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.75F)), PartPose.offset(2.0F, 12.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 96, 64);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		if (slot == EquipmentSlot.HEAD) {
			poseStack.pushPose();
			this.Head.copyFrom(this.head);
			this.UnderHat.copyFrom(this.head);
			if (this.young) {
				poseStack.scale(0.75F, 0.75F, 0.75F);
				this.Head.setPos(0.0F, 15.0F, 0.0F);
				this.UnderHat.setPos(0.0F, 15.0F, 0.0F);
			}
			this.Head.render(poseStack, vertexConsumer, packedLight, packedOverlay);
			this.UnderHat.render(poseStack, vertexConsumer, packedLight, packedOverlay);
			poseStack.popPose();
		}
		if (slot == EquipmentSlot.CHEST) {
			poseStack.pushPose();
			this.Chestplate.copyFrom(this.body);
			this.UnderShirt.copyFrom(this.body);
			this.RightArm.copyFrom(this.rightArm);
			this.RightUnderArm.copyFrom(this.rightArm);
			this.LeftArm.copyFrom(this.leftArm);
			this.LeftUnderArm.copyFrom(this.leftArm);
			if (this.young) {
				poseStack.scale(0.5F, 0.5F, 0.5F);
				this.Chestplate.setPos(0.0F, 24.0F, 0.0F);
				this.UnderShirt.setPos(0.0F, 24.0F, 0.0F);
				this.RightArm.setPos(-5.0F, 24.0F, 0.0F);
				this.LeftArm.setPos(5.0F, 24.0F, 0.0F);
			}
			this.RightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay);
			this.RightUnderArm.render(poseStack, vertexConsumer, packedLight, packedOverlay);
			this.LeftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay);
			this.LeftUnderArm.render(poseStack, vertexConsumer, packedLight, packedOverlay);
			this.Chestplate.render(poseStack, vertexConsumer, packedLight, packedOverlay);
			this.UnderShirt.render(poseStack, vertexConsumer, packedLight, packedOverlay);
			poseStack.popPose();
		}
		if (slot == EquipmentSlot.LEGS) {
			poseStack.pushPose();
			this.LeggingsPart.copyFrom(this.body);
			this.RightLeg.copyFrom(this.rightLeg);
			this.RightUnderPant.copyFrom(this.rightLeg);
			this.LeftLeg.copyFrom(this.leftLeg);
			this.LeftUnderPant.copyFrom(this.leftLeg);
			if (this.young) {
				poseStack.scale(0.5F, 0.5F, 0.5F);
				this.LeggingsPart.setPos(0.0F,24.0F,0.0F);
				this.LeftLeg.setPos(2.0F, 36.0F, 0.0F);
				this.LeftUnderPant.setPos(2.0F, 36.0F, 0.0F);
				this.RightLeg.setPos(-2.0F, 36.0F, 0.0F);
				this.RightUnderPant.setPos(-2.0F, 36.0F, 0.0F);
			}
			this.LeggingsPart.render(poseStack, vertexConsumer, packedLight, packedOverlay);
			this.RightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay);
			this.RightUnderPant.render(poseStack, vertexConsumer, packedLight, packedOverlay);
			this.LeftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay);
			this.LeftUnderPant.render(poseStack, vertexConsumer, packedLight, packedOverlay);
			poseStack.popPose();
		}
		if (slot == EquipmentSlot.FEET) {
			poseStack.pushPose();
			this.RightBoot.copyFrom(this.rightLeg);
			this.RIghtSock.copyFrom(this.rightLeg);
			this.LeftBoot.copyFrom(this.leftLeg);
			this.LeftSock.copyFrom(this.leftLeg);
			if (this.young) {
				poseStack.scale(0.5F, 0.5F, 0.5F);
				this.LeftBoot.setPos(2.0F, 37.0F, 0.0F);
				this.LeftSock.setPos(2.0F, 37.0F, 0.0F);
				this.RightBoot.setPos(-2.0F, 37.0F, 0.0F);
				this.RIghtSock.setPos(-2.0F, 37.0F, 0.0F);
			}
			this.RightBoot.render(poseStack, vertexConsumer, packedLight, packedOverlay);
			this.RIghtSock.render(poseStack, vertexConsumer, packedLight, packedOverlay);
			this.LeftBoot.render(poseStack, vertexConsumer, packedLight, packedOverlay);
			this.LeftSock.render(poseStack, vertexConsumer, packedLight, packedOverlay);
			poseStack.popPose();

		}
	}
}