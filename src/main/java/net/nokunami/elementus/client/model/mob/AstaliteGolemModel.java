package net.nokunami.elementus.client.model.mob;// Made with Blockbench 5.0.1
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.github.alexthe666.iceandfire.client.model.ModelUtils;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.nokunami.elementus.client.model.ModelUtil;
import net.nokunami.elementus.common.entity.living.AstaliteGolem;
import org.jetbrains.annotations.NotNull;

public class AstaliteGolemModel<T extends AstaliteGolem> extends HierarchicalModel<T> {
	private final ModelPart bone;
	private final ModelPart bodyBase;
	private final ModelPart bodySeg;
	private final ModelPart head;
	private final ModelPart left_arm;
	private final ModelPart right_arm;
	private final ModelPart left_leg;
	private final ModelPart right_leg;

	public AstaliteGolemModel(ModelPart root) {
		this.bone = root.getChild("bone");
		this.bodyBase = this.bone.getChild("bodyBase");
		this.bodySeg = this.bodyBase.getChild("bodySeg");
		this.head = this.bodySeg.getChild("head");
		this.left_arm = this.bodySeg.getChild("left_arm");
		this.right_arm = this.bodySeg.getChild("right_arm");
		this.left_leg = this.bone.getChild("left_leg");
		this.right_leg = this.bone.getChild("right_leg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition bodyBase = bone.addOrReplaceChild("bodyBase", CubeListBuilder.create()
                .texOffs(66, 18).addBox(-5.0F, -8.0F, -4.0F, 10.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, -20.0F, 0.0F));
        PartDefinition bodySeg = bodyBase.addOrReplaceChild("bodySeg", CubeListBuilder.create()
                .texOffs(0, 0).addBox(-9.0F, -14.0F, -6.0F, 18.0F, 14.0F, 14.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, -6.0F, 0.0F));
        bodySeg.addOrReplaceChild("head", CubeListBuilder.create()
                .texOffs(64, 0).addBox(-4.5F, -9.0F, -4.5F, 9.0F, 9.0F, 9.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, -12.0F, -4.5F));

        bodySeg.addOrReplaceChild("left_arm", CubeListBuilder.create()
                .texOffs(0, 50).addBox(1.0F, -2.0F, -4.0F, 7.0F, 30.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(0, 28).addBox(0.0F, -7.0F, -5.0F, 9.0F, 12.0F, 10.0F, new CubeDeformation(0.0F)),
                PartPose.offset(9.0F, -8.0F, 0.0F));
        bodySeg.addOrReplaceChild("right_arm", CubeListBuilder.create()
                .texOffs(38, 28).addBox(-9.0F, -7.0F, -5.0F, 9.0F, 12.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(30, 50).addBox(-8.0F, -2.0F, -4.0F, 7.0F, 30.0F, 8.0F, new CubeDeformation(0.0F)),
                PartPose.offset(-9.0F, -8.0F, 0.0F));

        bone.addOrReplaceChild("left_leg", CubeListBuilder.create()
                .texOffs(0, 88).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 20.0F, 6.0F, new CubeDeformation(0.0F)),
                PartPose.offset(5.0F, -20.0F, 0.0F));
        bone.addOrReplaceChild("right_leg", CubeListBuilder.create()
                .texOffs(24, 88).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 20.0F, 6.0F, new CubeDeformation(0.0F)),
                PartPose.offset(-5.0F, -20.0F, 0.0F));


        return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(@NotNull T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        ModelUtil.setDefaults(bone, 0, 24, 0);
        ModelUtil.setDefaults(bodyBase, 0, -20, 0);
        ModelUtil.setDefaults(bodySeg, 0, -6, 0);
        ModelUtil.setDefaults(head, 0, -12, -4.5);
        ModelUtil.setDefaults(left_arm, 9, -8, 0);
        ModelUtil.setDefaults(right_arm, -9, -8, 0);
        ModelUtil.setDefaults(left_leg, 5, -20, 0);
        ModelUtil.setDefaults(right_leg, -5, -20, 0);
	}

	@Override
	public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bone.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

    @Override
    public @NotNull ModelPart root() {
        return bone;
    }
}