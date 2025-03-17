package net.nokunami.elementus.client.model.mob;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.nokunami.elementus.common.entity.living.SteelGolem;

public class SteelGolemExtraLayer2<T extends SteelGolem> extends SteelGolemModel<T> {

    public SteelGolemExtraLayer2(ModelPart pRoot) {
        super(pRoot);
        ModelPart bone = pRoot.getChild("bone");
        ModelPart body = bone.getChild("body");
        ModelPart head = body.getChild("head");
        ModelPart left_arm = body.getChild("left_arm");
        ModelPart right_arm = body.getChild("right_arm");
        ModelPart left_leg = bone.getChild("left_leg");
        ModelPart right_leg = bone.getChild("right_leg");
    }

    public static LayerDefinition createBodyLayer(CubeDeformation deformation) {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 0.5F, 0.0F));
        PartDefinition body = bone.addOrReplaceChild("body", CubeListBuilder.create().texOffs(64, 0).addBox(-9.0F, -20.0F, -7.0F, 18.0F, 14.0F, 14.0F, deformation)
                .texOffs(0, 114).addBox(-6.0F, -6.0F, -5.0F, 12.0F, 6.0F, 10.0F, deformation), PartPose.offset(0.0F, 5.5F, 0.0F));
        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(84, 117).addBox(-5.0F, -9.0F, -5.0F, 10.0F, 9.0F, 10.0F, deformation)
                /*.texOffs(44, 121).addBox(-5.0F, -5.0F, -5.0F, 10.0F, 2.0F, 10.0F, deformation.extend(0.25F))*/, PartPose.offset(0.0F, -20.0F, -3.5F));
        PartDefinition left_arm = body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(120, 28).addBox(0.0F, -6.0F, -4.0F, 7.0F, 36.0F, 8.0F, deformation), PartPose.offset(9.0F, -13.0F, 0.0F));
        PartDefinition right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(30, 28).addBox(-7.0F, -6.0F, -4.0F, 7.0F, 36.0F, 8.0F, deformation), PartPose.offset(-9.0F, -13.0F, 0.0F));
        PartDefinition left_leg = bone.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(120, 72).addBox(-3.5F, 0.0F, -4.0F, 7.0F, 18.0F, 8.0F, deformation), PartPose.offset(4.5F, 5.5F, 0.0F));
        PartDefinition right_leg = bone.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(30, 72).addBox(-3.5F, 0.0F, -4.0F, 7.0F, 18.0F, 8.0F, deformation), PartPose.offset(-4.5F, 5.5F, 0.0F));

        return LayerDefinition.create(meshdefinition, 192, 192);
    }
}
