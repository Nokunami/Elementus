package net.nokunami.elementus.client;// Made with Blockbench 4.9.2

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class ArmorModelLayered<T extends LivingEntity> extends HumanoidArmorModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "testarmormodel"), "main");
	private final EquipmentSlot slot;
	private final ModelPart Head;
	private final ModelPart Chestplate;
	private final ModelPart RightArm;
	private final ModelPart LeftArm;
	private final ModelPart LeggingsPart;
	private final ModelPart RightLeg;
	private final ModelPart LeftLeg;
	private final ModelPart RightBoot;
	private final ModelPart LeftBoot;

	public ArmorModelLayered(ModelPart root, EquipmentSlot slot) {
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

		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(1.0F))
		.texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.6F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Chestplate = partdefinition.addOrReplaceChild("Chestplate", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(1.0F))
		.texOffs(24, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.6F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition RightArm = partdefinition.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(64, 0).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(1.0F))
		.texOffs(80, 0).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.6F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition LeftArm = partdefinition.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(48, 16).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(1.0F))
		.texOffs(64, 16).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.6F)), PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition LeggingsPart = partdefinition.addOrReplaceChild("LeggingsPart", CubeListBuilder.create().texOffs(0, 32).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition RightLeg = partdefinition.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(24, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.4F))
		.texOffs(40, 32).addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.27F)), PartPose.offset(-2.0F, 12.0F, 0.0F));

		PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(56, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.4F))
		.texOffs(72, 32).addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.27F)), PartPose.offset(2.0F, 12.0F, 0.0F));

		PartDefinition RightBoot = partdefinition.addOrReplaceChild("RightBoot", CubeListBuilder.create().texOffs(0, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.9F))
		.texOffs(16, 48).addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.75F)), PartPose.offset(-2.0F, 12.0F, 0.0F));

		PartDefinition LeftBoot = partdefinition.addOrReplaceChild("LeftBoot", CubeListBuilder.create().texOffs(32, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.9F))
		.texOffs(48, 48).addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.75F)), PartPose.offset(2.0F, 12.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 96, 64);
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
			this.RightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay);
			this.LeftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay);
			this.Chestplate.render(poseStack, vertexConsumer, packedLight, packedOverlay);
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