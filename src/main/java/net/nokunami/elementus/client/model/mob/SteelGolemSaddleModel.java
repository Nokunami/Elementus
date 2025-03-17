package net.nokunami.elementus.client.model.mob;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.nokunami.elementus.common.entity.living.SteelGolem;

public class SteelGolemSaddleModel<T extends SteelGolem> extends SteelGolemModel<T> {
    private final ModelPart root;
    private final ModelPart bone;
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart left_arm;
    private final ModelPart right_arm;
    private final ModelPart saddleSeat;
    private final ModelPart left_leg;
    private final ModelPart right_leg;

    public SteelGolemSaddleModel(ModelPart pRoot) {
        super(pRoot);
        this.root = pRoot;
        this.bone = root.getChild("bone");
        this.body = this.bone.getChild("body");
        this.head = this.body.getChild("head");
        this.left_arm = this.body.getChild("left_arm");
        this.right_arm = this.body.getChild("right_arm");
        this.saddleSeat = this.body.getChild("saddle_seat");
        this.left_leg = this.bone.getChild("left_leg");
        this.right_leg = this.bone.getChild("right_leg");
    }

    public static LayerDefinition createSaddleLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 0.5F, 0.0F));
        PartDefinition body = bone.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-9.0F, -20.0F, -7.0F, 18.0F, 14.0F, 14.0F, new CubeDeformation(0.25F))
                .texOffs(64, 0).addBox(-6.0F, -6.0F, -5.0F, 12.0F, 6.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 5.5F, 0.0F));
        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(64, 16).addBox(-5.0F, -9.0F, -5.0F, 10.0F, 9.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(64, 35).addBox(-5.5F, -5.0F, -5.5F, 10.0F, 2.0F, 10.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, -20.0F, -3.5F));
        PartDefinition left_arm = body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(30, 28).addBox(0.0F, -6.0F, -4.0F, 7.0F, 36.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(9.0F, -13.0F, 0.0F));
        PartDefinition right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(0, 28).addBox(-7.0F, -6.0F, -4.0F, 7.0F, 36.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-9.0F, -13.0F, 0.0F));
        PartDefinition saddleSeat = body.addOrReplaceChild("saddle_seat", CubeListBuilder.create().texOffs(64, 47).addBox(-6.0F, -21.0F, 0.0F, 12.0F, 14.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(64, 69).addBox(-5.75F, -28.0F, 1.0F, 11.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(64, 79).addBox(-4.0F, -30.0F, 1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(64, 83).addBox(-4.0F, -18.0F, 7.5F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(64, 87).addBox(-4.0F, -14.0F, 7.5F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(64, 91).addBox(-4.0F, -10.0F, 7.5F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition left_leg = bone.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(30, 72).addBox(-3.5F, 0.0F, -4.0F, 7.0F, 18.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(4.5F, 5.5F, 0.0F));
        PartDefinition right_leg = bone.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 72).addBox(-3.5F, 0.0F, -4.0F, 7.0F, 18.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.5F, 5.5F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }
}
