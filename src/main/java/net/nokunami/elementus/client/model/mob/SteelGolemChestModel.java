package net.nokunami.elementus.client.model.mob;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.nokunami.elementus.client.animation.definitions.SteelGolemChestAnimation;
import net.nokunami.elementus.common.entity.living.SteelGolem;
import org.jetbrains.annotations.NotNull;

public class SteelGolemChestModel<T extends SteelGolem> extends SteelGolemModel<T> {
    private final ModelPart chestTop;
    private final ModelPart chestBottom;
    private final ModelPart chestLatch;

    public SteelGolemChestModel(ModelPart root) {
        super(root);
        ModelPart bone = root.getChild("bone");
        ModelPart body = bone.getChild("body");
        ModelPart head = body.getChild("head");
        ModelPart left_arm = body.getChild("left_arm");
        ModelPart right_arm = body.getChild("right_arm");
        this.chestTop = body.getChild("chest_top");
        this.chestBottom = this.chestTop.getChild("chest_bottom");
        this.chestLatch = this.chestTop.getChild("chest_latch");
        ModelPart left_leg = bone.getChild("left_leg");
        ModelPart right_leg = bone.getChild("right_leg");
    }

    public static LayerDefinition createChestLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 0.5F, 0.0F));

        PartDefinition body = bone.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-9.0F, -20.0F, -7.0F, 18.0F, 14.0F, 14.0F, new CubeDeformation(0.0F))
                .texOffs(64, 0).addBox(-6.0F, -6.0F, -5.0F, 12.0F, 6.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 5.5F, 0.0F));

        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(64, 16).addBox(-5.0F, -9.0F, -5.0F, 10.0F, 9.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(64, 35).addBox(-5.0F, -5.0F, -5.0F, 10.0F, 2.0F, 10.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, -20.0F, -3.5F));

        PartDefinition left_arm = body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(30, 28).addBox(0.0F, -6.0F, -4.0F, 7.0F, 36.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(9.0F, -13.0F, 0.0F));

        PartDefinition right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(0, 28).addBox(-7.0F, -6.0F, -4.0F, 7.0F, 36.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-9.0F, -13.0F, 0.0F));

        PartDefinition chest_top = body.addOrReplaceChild("chest_top", CubeListBuilder.create().texOffs(64, 48).addBox(-7.0F, -2.5F, 0.0F, 14.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.5F, 5.0F));

        PartDefinition chest_bottom = chest_top.addOrReplaceChild("chest_bottom", CubeListBuilder.create().texOffs(64, 55).addBox(-7.0F, 0.0F, 0.0F, 14.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, 0.0F));

        PartDefinition chest_latch = chest_top.addOrReplaceChild("chest_latch", CubeListBuilder.create().texOffs(64, 64).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, 4.0F));

        PartDefinition left_leg = bone.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(30, 72).addBox(-3.5F, 0.0F, -4.0F, 7.0F, 18.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(4.5F, 5.5F, 0.0F));

        PartDefinition right_leg = bone.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 72).addBox(-3.5F, 0.0F, -4.0F, 7.0F, 18.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.5F, 5.5F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    public void setupAnim(@NotNull T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks,netHeadYaw, headPitch);
        resetChestTopPos();
        resetChestBottomPos();
        resetChestLatchPos();

        this.animate(entity.chestOpened, SteelGolemChestAnimation.chestOpen, ageInTicks);
        this.animate(entity.chestClosed, SteelGolemChestAnimation.chestClosed, ageInTicks);
        this.animateWalk(SteelGolemChestAnimation.walkCycle, limbSwing, limbSwingAmount, walkMaxAnimSpeed, walkAnimScaleFactor);
    }

    private void resetChestTopPos() {
        this.chestTop.x = 0.0F;
        this.chestTop.y = -3.5F;
        this.chestTop.z = 5.0F;
        this.chestTop.xRot = 0.0F;
        this.chestTop.yRot = 0.0F;
        this.chestTop.zRot = 0.0F;
    }

    private void resetChestBottomPos() {
        this.chestBottom.x = 0.0F;
        this.chestBottom.y = -0.5F;
        this.chestBottom.z = 0.0F;
        this.chestBottom.xRot = 0.0F;
        this.chestBottom.yRot = 0.0F;
        this.chestBottom.zRot = 0.0F;
    }

    private void resetChestLatchPos() {
        this.chestLatch.x = 0.0F;
        this.chestLatch.y = -0.5F;
        this.chestLatch.z = 4.0F;
        this.chestLatch.xRot = 0.0F;
        this.chestLatch.yRot = 0.0F;
        this.chestLatch.zRot = 0.0F;
    }
}
