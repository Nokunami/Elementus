package nokunami.elementus.client.model.armor.sniffsWeapons;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public class ClothedCuirassModel extends HumanoidModel<LivingEntity> {

    public ClothedCuirassModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createArmorLayer(CubeDeformation deformation) {
        MeshDefinition meshdefinition = HumanoidArmorModel.createMesh(deformation, 0.0F);
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 54).addBox(-1.9F, 0.0F, -2.5F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.5F)), PartPose.offset(1.9F, 12.0F, 0.0F));
        partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 54).addBox(-3.1F, 0.0F, -2.5F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.5F)), PartPose.offset(-1.9F, 12.0F, 0.0F));
        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition hat = head.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(1.01F)).texOffs(40, 51).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 9.0F, 4.0F, new CubeDeformation(1.3F)).texOffs(13, 32).addBox(-5.0F, 10.0F, -3.0F, 10.0F, 5.0F, 6.0F, new CubeDeformation(1.01F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        body.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(0, 5).addBox(-4.5F, -0.3F, -4.5F, 9.0F, 2.0F, 9.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, -0.7F, 0.0F, 0.1309F, 0.0F, 0.0F));
        partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.8F)).texOffs(0, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(1.2F)).texOffs(44, 38).addBox(-3.9F, -3.0F, -2.5F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.8F)), PartPose.offset(-5.0F, 2.0F, 0.0F));
        partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.8F)).mirror(false).texOffs(0, 16).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(1.2F)).mirror(false).texOffs(44, 38).mirror().addBox(-1.1F, -3.0F, -2.5F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.8F)).mirror(false), PartPose.offset(5.0F, 2.0F, 0.0F));
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public void renderToBuffer(PoseStack poseStack, @NotNull VertexConsumer vertices, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        poseStack.pushPose();
        if (this.young) {
            poseStack.scale(0.5F, 0.5F, 0.5F);
            poseStack.translate(0.0F, 1.5F, 0.0F);
        }

        this.rightLeg.visible = true;
        this.leftLeg.visible = true;
        this.head.render(poseStack, vertices, packedLight, packedOverlay, red, green, blue, alpha);
        this.body.render(poseStack, vertices, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightArm.render(poseStack, vertices, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftArm.render(poseStack, vertices, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightLeg.render(poseStack, vertices, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftLeg.render(poseStack, vertices, packedLight, packedOverlay, red, green, blue, alpha);
        poseStack.popPose();
    }
}