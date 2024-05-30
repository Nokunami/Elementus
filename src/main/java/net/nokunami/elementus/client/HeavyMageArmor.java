package net.nokunami.elementus.client;// Made with Blockbench 4.9.4

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
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HeavyMageArmor<T extends LivingEntity> extends HumanoidArmorModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	private final EquipmentSlot slot;
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "wizardarmor2"), "main");
	private final ModelPart FlatHat;
	private final ModelPart Base;
	private final ModelPart MidSect;
	private final ModelPart Tip1;
	private final ModelPart Tip2;
	private final ModelPart Head;
	private final ModelPart Chestplate;
	private final ModelPart WizardChestRobe;
	private final ModelPart ChestplateFaulds;
	private final ModelPart RightArm;
	private final ModelPart LeftArm;
	private final ModelPart LeggingsPart;
	private final ModelPart RightLeggingsFauld;
	private final ModelPart LeftLeggingsFauld;
	private final ModelPart RightLeg;
	private final ModelPart LeftLeg;
	private final ModelPart RightBoot;
	private final ModelPart LeftBoot;

	public HeavyMageArmor(ModelPart root, EquipmentSlot slot) {
		super(root);
		this.slot = slot;
		this.FlatHat = root.getChild("FlatHat");
		this.Base = root.getChild("Base");
		this.MidSect = root.getChild("MidSect");
		this.Tip1 = root.getChild("Tip1");
		this.Tip2 = root.getChild("Tip2");
		this.Head = root.getChild("Head");
		this.Chestplate = root.getChild("Chestplate");
		this.WizardChestRobe = root.getChild("WizardChestRobe");
		this.ChestplateFaulds = root.getChild("ChestplateFaulds");
		this.RightArm = root.getChild("RightArm");
		this.LeftArm = root.getChild("LeftArm");
		this.LeggingsPart = root.getChild("LeggingsPart");
		this.RightLeggingsFauld = root.getChild("RightLeggingsFauld");
		this.LeftLeggingsFauld = root.getChild("LeftLeggingsFauld");
		this.RightLeg = root.getChild("RightLeg");
		this.LeftLeg = root.getChild("LeftLeg");
		this.RightBoot = root.getChild("RightBoot");
		this.LeftBoot = root.getChild("LeftBoot");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = HumanoidArmorModel.createMesh(CubeDeformation.NONE, 0.0F);
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition FlatHat = partdefinition.addOrReplaceChild("FlatHat", CubeListBuilder.create(),
				PartPose.offsetAndRotation(0,0,-0.5F,-0.1F,0,0));
		PartDefinition FlatHat_ = FlatHat.addOrReplaceChild("FlatHat_",
				CubeListBuilder.create().texOffs(0,64)
						.addBox(-8,-7,-8,
								16,1,16,
						new CubeDeformation(0.0F)), PartPose.offsetAndRotation(
								0,0,-0.77F,-0.1F,0,0));

		PartDefinition Base = partdefinition.addOrReplaceChild("Base", CubeListBuilder.create(),
						PartPose.offsetAndRotation(0,0,-0.5F,-0.1F,0,0));
		PartDefinition Base_ = Base.addOrReplaceChild("Base_",
				CubeListBuilder.create().texOffs(49,66)
						.addBox(-5,-10,-5,
								10,3,10,
						new CubeDeformation(0.0F)), PartPose.offsetAndRotation(
						0,0,-0.77F,-0.1F,0,0));

		PartDefinition MidSect = partdefinition.addOrReplaceChild("MidSect", CubeListBuilder.create(),
				PartPose.offsetAndRotation(0,32,0,5,0,0));
		PartDefinition MidSect_ = MidSect.addOrReplaceChild("MidSect_",
				CubeListBuilder.create().texOffs(0,83)
						.addBox(-4,-13F,-3.25F,
								8,3,8,
						new CubeDeformation(0.0F)), PartPose.offsetAndRotation(
								0,0,-0.77F, -0.1F,0,0));

		PartDefinition Tip1 = partdefinition.addOrReplaceChild("Tip1", CubeListBuilder.create(),
				PartPose.offsetAndRotation(0,32,0,5,0,0));
		PartDefinition Tip1_ = Tip1.addOrReplaceChild("Tip1_", CubeListBuilder.create()
						.texOffs(32,84)
						.addBox(-3,-13,-3,
								6,6,6,
						new CubeDeformation(0.0F)), PartPose.offsetAndRotation(
								0,-1.9F,-4F, -0.5F,0,0));

		PartDefinition Tip2 = partdefinition.addOrReplaceChild("Tip2", CubeListBuilder.create(),
				PartPose.offsetAndRotation(0,32,0,5,0,0));
		PartDefinition Tip2_ = Tip2.addOrReplaceChild("Tip2_",
				CubeListBuilder.create().texOffs(56,88)
						.addBox(-1.5F,-17,-5,
								3,5,3,
						new CubeDeformation(0.0F)), PartPose.offsetAndRotation(
								0,-3.1F,-6.1F, -1F,0,0));

		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.9F))
				.texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Chestplate = partdefinition.addOrReplaceChild("Chestplate", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.7F))
				.texOffs(24, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.51F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition WizardChestRobe = partdefinition.addOrReplaceChild("WizardChestRobe", CubeListBuilder.create().texOffs(0, 96).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.89F))
				.texOffs(24, 96).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.85F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition ChestplateFaulds = partdefinition.addOrReplaceChild("ChestplateFaulds", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition RightCFauld_r1 = ChestplateFaulds.addOrReplaceChild("RightCFauld_r1", CubeListBuilder.create().texOffs(0, 112).addBox(-3.8F, 2.4F, -2.0F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.75F)), PartPose.offsetAndRotation(-0.2F, 7.6F, 0.0F, 0.0F, 0.0F, 0.0873F));

		PartDefinition LeftCFauld_r1 = ChestplateFaulds.addOrReplaceChild("LeftCFauld_r1", CubeListBuilder.create().texOffs(14, 112).addBox(1.2F, 2.4F, -2.0F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.75F)), PartPose.offsetAndRotation(-0.2F, 7.6F, 0.0F, 0.0F, 0.0F, -0.0873F));

		PartDefinition RightArm = partdefinition.addOrReplaceChild("RightArm", CubeListBuilder.create(), PartPose.offset(-5.0F, 2.0F, 0.0F));
		PartDefinition RightArm_r1 = RightArm.addOrReplaceChild("RightArm_r1", CubeListBuilder.create().texOffs(64, 0).addBox(4.65F, -19.7F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.875F)), PartPose.offsetAndRotation(-7.0F, 18.0F, 0.0F, 0.0F, 0.0F, -0.0873F));
		PartDefinition RightExtraArm = RightArm.addOrReplaceChild("RightExtraArm", CubeListBuilder.create().texOffs(80, 0).addBox(1.25F, -3F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.65F)), PartPose.offsetAndRotation(-5.0F, 1F, 0.0F,0,0,0));
		PartDefinition UnderRightArmLayer_r1 = RightArm.addOrReplaceChild("UnderRightArmLayer_r1", CubeListBuilder.create().texOffs(96, 0).addBox(-10.75F, -7.7F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.61F)), PartPose.offsetAndRotation(7.0F, 6.7F, 0.0F, 0.0F, 0.0F, 0.0873F));

		PartDefinition LeftArm = partdefinition.addOrReplaceChild("LeftArm", CubeListBuilder.create(), PartPose.offset(5.0F, 2.0F, 0.0F));
		PartDefinition LeftArm_r1 = LeftArm.addOrReplaceChild("LeftArm_r1", CubeListBuilder.create().texOffs(48, 16).addBox(-8.65F, -19.7F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.875F)), PartPose.offsetAndRotation(7.0F, 18.0F, 0.0F, 0.0F, 0.0F, 0.0873F));
		PartDefinition LeftExtraArm = LeftArm.addOrReplaceChild("LeftExtraArm", CubeListBuilder.create().texOffs(64, 16).addBox(-5.25F, -3.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.65F)), PartPose.offsetAndRotation(5.0F, 1.0F, 0.0F,0,0,0));
		PartDefinition LeftUnderArmLayer_r1 = LeftArm.addOrReplaceChild("LeftUnderArmLayer_r1", CubeListBuilder.create().texOffs(80, 16).addBox(6.75F, -7.7F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.61F)), PartPose.offsetAndRotation(-7.0F, 6.7F, 0.0F, 0.0F, 0.0F, -0.0873F));

		PartDefinition LeggingsPart = partdefinition.addOrReplaceChild("LeggingsPart", CubeListBuilder.create().texOffs(0, 32).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition RightLeggingsFauld = partdefinition.addOrReplaceChild("RightLeggingsFauld", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition RightLFauld_r1 = RightLeggingsFauld.addOrReplaceChild("RightLFauld_r1", CubeListBuilder.create().texOffs(28, 112).addBox(-3.8F, 2.4F, -2.0F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.75F)), PartPose.offsetAndRotation(-0.2F, 7.6F, 0.0F, 0.0F, 0.0F, 0.0873F));

		PartDefinition LeftLeggingsFauld = partdefinition.addOrReplaceChild("LeftLeggingsFauld", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition LeftLFauld_r1 = LeftLeggingsFauld.addOrReplaceChild("LeftLFauld_r1", CubeListBuilder.create().texOffs(42, 112).addBox(1.2F, 2.4F, -2.0F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.75F)), PartPose.offsetAndRotation(-0.2F, 7.6F, 0.0F, 0.0F, 0.0F, -0.0873F));

		PartDefinition RightLeg = partdefinition.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(24, 32).addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.4F))
				.texOffs(40, 32).addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.27F)), PartPose.offset(-2.0F, 12.0F, 0.0F));

		PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(56, 32).addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.4F))
				.texOffs(72, 32).addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.27F)), PartPose.offset(2.0F, 12.0F, 0.0F));

		PartDefinition RightBoot = partdefinition.addOrReplaceChild("RightBoot", CubeListBuilder.create().texOffs(0, 48).addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.9F))
				.texOffs(16, 48).addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.75F)), PartPose.offset(-2.0F, 12.0F, 0.0F));

		PartDefinition LeftBoot = partdefinition.addOrReplaceChild("LeftBoot", CubeListBuilder.create().texOffs(32, 48).addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.9F))
				.texOffs(48, 48).addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.75F)), PartPose.offset(2.0F, 12.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		if (slot == EquipmentSlot.HEAD) {
			poseStack.pushPose();
			this.Head.copyFrom(this.head);
			this.FlatHat.copyFrom(this.head);
			this.Base.copyFrom(this.head);
			this.MidSect.copyFrom(this.head);
			this.Tip1.copyFrom(this.head);
			this.Tip2.copyFrom(this.head);
			if (this.young) {
				poseStack.scale(0.75F, 0.75F, 0.75F);
				this.Head.setPos(0.0F, 15.0F, 0.0F);
				this.FlatHat.setPos(0.0F, 15.0F, 0.0F);
				this.Base.setPos(0.0F, 15.0F, 0.0F);
				this.MidSect.setPos(0.0F, 15.0F, 0.0F);
				this.Tip1.setPos(0.0F, 15.0F, 0.0F);
				this.Tip2.setPos(0.0F, 15.0F, 0.0F);
			}
			this.Head.render(poseStack, vertexConsumer, packedLight, packedOverlay);
			this.FlatHat.render(poseStack, vertexConsumer, packedLight, packedOverlay);
			this.Base.render(poseStack, vertexConsumer, packedLight, packedOverlay);
			this.MidSect.render(poseStack, vertexConsumer, packedLight, packedOverlay);
			this.Tip1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
			this.Tip2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
			poseStack.popPose();
		}
		if (slot == EquipmentSlot.CHEST) {
			poseStack.pushPose();
			this.Chestplate.copyFrom(this.body);
			this.WizardChestRobe.copyFrom(this.body);
			this.ChestplateFaulds.copyFrom(this.body);
			this.RightArm.copyFrom(this.rightArm);
			this.LeftArm.copyFrom(this.leftArm);
			if (this.young) {
				poseStack.scale(0.5F, 0.5F, 0.5F);
				this.Chestplate.setPos(0.0F, 24.0F, 0.0F);
				this.WizardChestRobe.setPos(0.0F, 24.0F, 0.0F);
				this.ChestplateFaulds.setPos(0.0F, 24.0F, 0.0F);
				this.RightArm.setPos(-5.0F, 24.0F, 0.0F);
				this.LeftArm.setPos(5.0F, 24.0F, 0.0F);
			}
			this.Chestplate.render(poseStack, vertexConsumer, packedLight, packedOverlay);
			this.WizardChestRobe.render(poseStack, vertexConsumer, packedLight, packedOverlay);
			this.ChestplateFaulds.render(poseStack, vertexConsumer, packedLight, packedOverlay);
			this.RightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay);
			this.LeftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay);
			poseStack.popPose();
		}
		if (slot == EquipmentSlot.LEGS) {
			poseStack.pushPose();
			this.LeggingsPart.copyFrom(this.body);
			this.RightLeggingsFauld.copyFrom(this.rightLeg);
			this.LeftLeggingsFauld.copyFrom(this.leftLeg);
			this.RightLeg.copyFrom(this.rightLeg);
			this.LeftLeg.copyFrom(this.leftLeg);
			if (this.young) {
				poseStack.scale(0.5F, 0.5F, 0.5F);
				this.LeggingsPart.setPos(0.0F,24.0F,0.0F);
				this.RightLeggingsFauld.setPos(0.0F,24.0F,0.0F);
				this.LeftLeggingsFauld.setPos(0.0F,24.0F,0.0F);
				this.LeftLeg.setPos(2.0F, 36.0F, 0.0F);
				this.RightLeg.setPos(-2.0F, 36.0F, 0.0F);
			}
			this.LeggingsPart.render(poseStack, vertexConsumer, packedLight, packedOverlay);
			this.RightLeggingsFauld.render(poseStack, vertexConsumer, packedLight, packedOverlay);
			this.LeftLeggingsFauld.render(poseStack, vertexConsumer, packedLight, packedOverlay);
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