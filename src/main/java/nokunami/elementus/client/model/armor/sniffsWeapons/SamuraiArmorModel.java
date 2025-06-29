package nokunami.elementus.client.model.armor.sniffsWeapons;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.DyeableArmorItem;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class SamuraiArmorModel extends HumanoidModel<LivingEntity> {
    private ItemStack stack;
    public final ModelPart mask;
    private final ModelPart skirt;
    private EquipmentSlot slot;

    public SamuraiArmorModel(ModelPart root) {
        super(root);
        this.mask = root.getChild("mask");
        this.skirt = root.getChild("skirt");
    }

    public static LayerDefinition createArmorLayer(CubeDeformation deformation) {
        MeshDefinition meshdefinition = HumanoidArmorModel.createMesh(deformation, 0.0F);
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("skirt", CubeListBuilder.create().texOffs(0, 25).addBox(-5.0F, 13.0F, -3.0F, 10.0F, 7.0F, 6.0F, new CubeDeformation(0.75F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        partdefinition.addOrReplaceChild("mask", CubeListBuilder.create().texOffs(33, 13).addBox(-4.0F, -6.0F, -5.0F, 8.0F, 6.0F, 2.0F, new CubeDeformation(0.2F)).texOffs(48, 16).addBox(-1.0F, -4.0F, -10.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        head.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(0, 0).addBox(-4.6F, -9.0F, -4.0F, 9.0F, 4.0F, 8.0F, new CubeDeformation(1.0F)).texOffs(42, 0).addBox(-5.6F, -19.0F, -5.4F, 11.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(0, 12).addBox(-6.0F, -4.0F, -3.0F, 12.0F, 4.0F, 9.0F, new CubeDeformation(1.0F)).texOffs(26, 0).addBox(-9.5F, -4.0F, -5.0F, 5.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(26, 0).mirror().addBox(4.5F, -4.0F, -5.0F, 5.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));
        partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 48).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(1.01F)).texOffs(28, 32).addBox(-5.0F, 8.0F, -3.0F, 10.0F, 8.0F, 6.0F, new CubeDeformation(1.01F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(24, 48).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(1.0F)), PartPose.offset(-5.0F, 2.0F, 0.0F));
        right_arm.addOrReplaceChild("cap2", CubeListBuilder.create().texOffs(0, 38).addBox(0.0F, -2.0F, -3.5F, 10.0F, 2.0F, 8.0F, new CubeDeformation(0.1F)).texOffs(40, 53).addBox(1.0F, 0.0F, -2.5F, 4.0F, 5.0F, 6.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(-2.0F, -6.0F, 0.5F, 3.1416F, 0.0F, 1.9199F));
        PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(24, 48).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(1.0F)).mirror(false), PartPose.offset(5.0F, 2.0F, 0.0F));
        left_arm.addOrReplaceChild("cap", CubeListBuilder.create().texOffs(0, 38).mirror().addBox(0.0F, -2.0F, -4.5F, 10.0F, 2.0F, 8.0F, new CubeDeformation(0.1F)).texOffs(40, 53).addBox(1.0F, 0.0F, -3.5F, 4.0F, 5.0F, 6.0F, new CubeDeformation(0.1F)).mirror(false), PartPose.offsetAndRotation(2.0F, -6.0F, 0.5F, 0.0F, 0.0F, 1.2217F));
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public SamuraiArmorModel animate(ItemStack stack, EquipmentSlot slot) {
        this.stack = stack;
        this.setVisible(slot);
        this.slot = slot;
        return this;
    }

    public void renderToBuffer(PoseStack poseStack, @NotNull VertexConsumer vertices, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        int decimal = ((DyeableArmorItem)this.stack.getItem()).getColor(this.stack);
        float r = (float)(decimal >> 16 & 255) / 255.0F;
        float g = (float)(decimal >> 8 & 255) / 255.0F;
        float b = (float)(decimal & 255) / 255.0F;
        poseStack.pushPose();
        if (this.young) {
            if (this.slot == EquipmentSlot.HEAD) {
                poseStack.scale(0.75F, 0.75F, 0.75F);
                poseStack.translate(0.0F, 1.0F, 0.0F);
            } else {
                poseStack.scale(0.5F, 0.5F, 0.5F);
                poseStack.translate(0.0F, 1.5F, 0.0F);
            }
        }

        this.skirt.copyFrom(this.body);
        this.mask.copyFrom(this.head);
        this.mask.render(poseStack, vertices, packedLight, packedOverlay, r, g, b, 1.0F);
        this.skirt.render(poseStack, vertices, packedLight, packedOverlay, r, g, b, 1.0F);
        this.head.render(poseStack, vertices, packedLight, packedOverlay, r, g, b, 1.0F);
        this.body.render(poseStack, vertices, packedLight, packedOverlay, red, green, blue, 1.0F);
        this.leftArm.render(poseStack, vertices, packedLight, packedOverlay, red, green, blue, 1.0F);
        this.rightArm.render(poseStack, vertices, packedLight, packedOverlay, red, green, blue, 1.0F);
        this.head.render(poseStack, vertices, packedLight, packedOverlay, red, green, blue, 1.0F);
        poseStack.popPose();
    }

    public void setVisible(EquipmentSlot slot) {
        this.setAllVisible(false);
        switch (slot) {
            case HEAD:
                this.mask.visible = true;
                this.skirt.visible = false;
                this.head.visible = true;
                break;
            case CHEST:
                this.mask.visible = false;
                this.skirt.visible = true;
                this.body.visible = true;
        }
    }
}