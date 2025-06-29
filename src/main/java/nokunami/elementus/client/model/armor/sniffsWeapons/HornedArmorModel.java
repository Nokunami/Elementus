package nokunami.elementus.client.model.armor.sniffsWeapons;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.DyeableArmorItem;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class HornedArmorModel extends HumanoidModel<LivingEntity> {
    private ItemStack stack;
    public final ModelPart feathers;
    public final ModelPart feather_holder;
    private final ModelPart skirt;

    public HornedArmorModel(ModelPart root) {
        super(root);
        this.feather_holder = root.getChild("feather_holder");
        this.feathers = this.feather_holder.getChild("feathers");
        this.skirt = root.getChild("skirt");
    }

    public static LayerDefinition createArmorLayer(CubeDeformation deformation) {
        MeshDefinition meshdefinition = HumanoidArmorModel.createMesh(deformation, 0.0F);
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition feather_holder = partdefinition.addOrReplaceChild("feather_holder", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition feathers = feather_holder.addOrReplaceChild("feathers", CubeListBuilder.create(), PartPose.offset(0.0F, -11.0F, 0.0F));
        feathers.addOrReplaceChild("Feathers_Left_r1", CubeListBuilder.create().texOffs(0, 41).addBox(0.0F, -7.5F, -1.0F, 0.0F, 11.0F, 12.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(0.0F, -1.0F, -1.0F, 0.0F, 0.3491F, 0.0F));
        feathers.addOrReplaceChild("Feathers_Right_r1", CubeListBuilder.create().texOffs(0, 41).addBox(0.0F, -7.5F, -1.0F, 0.0F, 11.0F, 12.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(0.0F, -1.0F, -1.0F, 0.0F, -0.3491F, 0.0F));
        partdefinition.addOrReplaceChild("skirt", CubeListBuilder.create().texOffs(32, 50).addBox(-5.0F, 10.0F, -3.0F, 10.0F, 8.0F, 6.0F, new CubeDeformation(0.75F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition hat = head.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(1.0F)).texOffs(48, 6).addBox(4.0F, -12.0F, 0.0F, 8.0F, 9.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(48, 6).mirror().addBox(-12.0F, -12.0F, 0.0F, 8.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false).texOffs(32, 6).addBox(-2.0F, -11.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.3F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        hat.addOrReplaceChild("visor", CubeListBuilder.create().texOffs(32, 0).addBox(-4.5F, -2.0F, -1.0F, 9.0F, 4.0F, 2.0F, new CubeDeformation(0.6F)), PartPose.offsetAndRotation(0.0F, -4.0F, -5.0F, -0.1309F, 0.0F, 0.0F));
        partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(1.01F)).texOffs(0, 32).addBox(-5.0F, 7.0F, -3.0F, 10.0F, 9.0F, 6.0F, new CubeDeformation(1.01F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition rightArm = partdefinition.getChild("right_arm");
        PartDefinition leftArm = partdefinition.getChild("left_arm");

        rightArm.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(0, 16).addBox(2.0F, -4.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.601F)), PartPose.offset(-5.0F, 2.0F, 0.0F));
        rightArm.addOrReplaceChild("cap", CubeListBuilder.create().texOffs(32, 32).addBox(-6.0F, -2.0F, -3.5F, 6.0F, 2.0F, 7.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(1.0F, -2.0F, 0.0F, 0.0F, 0.0F, -0.2182F));

        leftArm.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-6.0F, -4.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.601F)).mirror(false), PartPose.offset(5.0F, 2.0F, 0.0F));
        leftArm.addOrReplaceChild("cap2", CubeListBuilder.create().texOffs(32, 32).mirror().addBox(0.0F, -2.0F, -3.5F, 6.0F, 2.0F, 7.0F, new CubeDeformation(0.1F)).mirror(false), PartPose.offsetAndRotation(-1.0F, -2.0F, 0.0F, 0.0F, 0.0F, 0.2182F));


        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public HornedArmorModel animate(LivingEntity entity, ItemStack stack, EquipmentSlot slot) {
        float weatherf = entity.level().isRaining() ? 40.0F : 5.0F;
        float f = (float)entity.tickCount * weatherf * 0.017453292F;
        this.feathers.xRot = Mth.cos(f) / 8.0F * 3.1415927F * 0.15F;
        this.feathers.yRot = Mth.cos(f) / 4.0F * 3.1415927F * 0.15F;
        this.stack = stack;
        this.setVisible(slot);
        return this;
    }

    public void renderToBuffer(PoseStack poseStack, @NotNull VertexConsumer vertices, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        int decimal = ((DyeableArmorItem)this.stack.getItem()).getColor(this.stack);
        float r = (float)(decimal >> 16 & 255) / 255.0F;
        float g = (float)(decimal >> 8 & 255) / 255.0F;
        float b = (float)(decimal & 255) / 255.0F;
        poseStack.pushPose();
        if (this.young) {
            poseStack.scale(0.5F, 0.5F, 0.5F);
            poseStack.translate(0.0F, 1.5F, 0.0F);
        }

        this.skirt.copyFrom(this.body);
        this.feather_holder.copyFrom(this.head);
        this.feather_holder.render(poseStack, vertices, packedLight, packedOverlay, r, g, b, 1.0F);
        this.skirt.render(poseStack, vertices, packedLight, packedOverlay, r, g, b, 1.0F);
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
                this.skirt.visible = false;
                this.feather_holder.visible = true;
                this.head.visible = true;
                break;
            case CHEST:
                this.feather_holder.visible = false;
                this.skirt.visible = true;
                this.body.visible = true;
        }
    }
}