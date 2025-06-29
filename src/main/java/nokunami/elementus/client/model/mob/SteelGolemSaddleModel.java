package nokunami.elementus.client.model.mob;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import nokunami.elementus.common.entity.living.SteelGolem;

public class SteelGolemSaddleModel<T extends SteelGolem> extends SteelGolemModel<T> {

    public SteelGolemSaddleModel(ModelPart pRoot) {
        super(pRoot);
        ModelPart bone = pRoot.getChild("bone");
        ModelPart body = bone.getChild("body");
        ModelPart head = body.getChild("head");
        ModelPart left_arm = body.getChild("left_arm");
        ModelPart right_arm = body.getChild("right_arm");
        ModelPart saddleSeat = body.getChild("saddle_seat");
        ModelPart left_leg = bone.getChild("left_leg");
        ModelPart right_leg = bone.getChild("right_leg");
    }

    public static LayerDefinition createSaddleLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 0.5F, 0.0F));

        PartDefinition body = bone.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-9.0F, -20.0F, -7.0F, 18.0F, 14.0F, 14.0F, new CubeDeformation(0.0F))
                .texOffs(64, 0).addBox(-6.0F, -6.0F, -5.0F, 12.0F, 6.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 5.5F, 0.0F));

        body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(64, 16).addBox(-5.0F, -9.0F, -5.0F, 10.0F, 9.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(64, 35).addBox(-5.0F, -5.0F, -5.0F, 10.0F, 2.0F, 10.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, -20.0F, -3.5F));

        body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(30, 28).addBox(0.0F, -6.0F, -4.0F, 7.0F, 36.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(9.0F, -13.0F, 0.0F));

        body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(0, 28).addBox(-7.0F, -6.0F, -4.0F, 7.0F, 36.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-9.0F, -13.0F, 0.0F));

        body.addOrReplaceChild("saddle_seat", CubeListBuilder.create().texOffs(64, 47).addBox(-6.0F, -21.25F, 0.25F, 12.0F, 14.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(64, 69).addBox(-5.75F, -28.0F, 1.0F, 11.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(90, 69).addBox(-4.0F, -30.0F, 1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(104, 47).addBox(-4.0F, -18.25F, 8.25F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(104, 51).addBox(-4.0F, -14.25F, 8.25F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(104, 55).addBox(-4.0F, -10.25F, 8.25F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        bone.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(30, 72).addBox(-3.5F, 0.0F, -4.0F, 7.0F, 18.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(4.5F, 5.5F, 0.0F));

        bone.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 72).addBox(-3.5F, 0.0F, -4.0F, 7.0F, 18.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.5F, 5.5F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }
}
