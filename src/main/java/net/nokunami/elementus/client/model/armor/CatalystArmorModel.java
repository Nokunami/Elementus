package net.nokunami.elementus.client.model.armor;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public class CatalystArmorModel<T extends LivingEntity> extends HumanoidArmorModel<T> {
    public final ModelPart bodyEmissive;
    public final ModelPart core;
    public final ModelPart rightArmEmissive;
    public final ModelPart leftArmEmissive;

	public CatalystArmorModel(ModelPart root) {
        super(root);
        bodyEmissive = root.getChild("body_emissive");
        core = root.getChild("core");
        rightArmEmissive = root.getChild("right_arm_emissive");
        leftArmEmissive = root.getChild("left_arm_emissive");
	}

	public static LayerDefinition create() {
		MeshDefinition meshdefinition = HumanoidArmorModel.createMesh(new CubeDeformation(1), 0.0F);
		PartDefinition partdefinition = meshdefinition.getRoot();

        partdefinition.addOrReplaceChild("core", CubeListBuilder.create()
                .texOffs(0, 0).addBox(-3.0F, 0.25F, -3.5F, 6.0F, 6.0F, 1.0F, new CubeDeformation(-0.25F))
                .texOffs(0, 7).addBox(-3.0F, 0.25F, -4.0F, 6.0F, 6.0F, 1.0F, new CubeDeformation(-0.25F)),
                PartPose.offset(0.0F, 0.0F, 0.0F));
        partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create()
                .texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(1.1F))
                .texOffs(40, 0).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.65F)),
                PartPose.offset(-5.0F, 2.0F, 0.0F));
        partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create()
                .texOffs(40, 16).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(1.1F)).mirror(false)
                .texOffs(40, 0).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.65F)).mirror(false),
                PartPose.offset(5.0F, 2.0F, 0.0F));

//		PartDefinition RightLeg = partdefinition.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(0, 16).addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.4F)), PartPose.offset(-2.0F, 12.0F, 0.0F));
//		PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.4F)).mirror(false), PartPose.offset(2.0F, 12.0F, 0.0F));

		partdefinition.addOrReplaceChild("body_emissive", CubeListBuilder.create()
                .texOffs(16, 48).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(1.0F)),
                PartPose.offset(0.0F, 0.0F, 0.0F));
        partdefinition.addOrReplaceChild("right_arm_emissive", CubeListBuilder.create()
                .texOffs(40, 48).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(1.1F))
                .texOffs(40, 32).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.65F)),
                PartPose.offset(-5.0F, 2.0F, 0.0F));
        partdefinition.addOrReplaceChild("left_arm_emissive", CubeListBuilder.create()
                .texOffs(40, 48).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(1.1F)).mirror(false)
                .texOffs(40, 32).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.65F)).mirror(false),
                PartPose.offset(5.0F, 2.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

    @Override
    public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        super.renderToBuffer(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        bodyEmissive.copyFrom(body);
        bodyEmissive.render(poseStack, buffer, LightTexture.FULL_BRIGHT, packedOverlay);
        core.copyFrom(body);
        core.render(poseStack, buffer, LightTexture.FULL_BRIGHT, packedOverlay);
        rightArmEmissive.copyFrom(rightArm);
        rightArmEmissive.render(poseStack, buffer, LightTexture.FULL_BRIGHT, packedOverlay);
        leftArmEmissive.copyFrom(leftArm);
        leftArmEmissive.render(poseStack, buffer, LightTexture.FULL_BRIGHT, packedOverlay);
    }
}