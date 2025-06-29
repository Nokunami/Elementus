package nokunami.elementus.client.model.armor;

import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;

public class ExtendedArmorModel<T extends LivingEntity> extends HumanoidArmorModel<T> {

    public ExtendedArmorModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createArmorLayer(CubeDeformation deformation) {
        MeshDefinition meshdefinition = HumanoidArmorModel.createMesh(deformation, 0.0F);
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition rightArm = partdefinition.getChild("right_arm");
        PartDefinition leftArm = partdefinition.getChild("left_arm");

        rightArm.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(40, 0).addBox(2.0F, -4.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.601F)), PartPose.offset(-5.0F, 2.0F, 0.0F));
        leftArm.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(40, 0).mirror().addBox(-6.0F, -4.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.601F)).mirror(false), PartPose.offset(5.0F, 2.0F, 0.0F));

        partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, deformation.extend(-0.1F)), PartPose.offset(-1.9F, 12.0F, 0.0F));
        partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, deformation.extend(-0.1F)), PartPose.offset(1.9F, 12.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 32);
    }
}