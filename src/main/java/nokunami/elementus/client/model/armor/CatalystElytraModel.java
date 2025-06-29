package nokunami.elementus.client.model.armor;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class CatalystElytraModel<T extends LivingEntity> extends AgeableListModel<T> {
    private final ModelPart rightWing;
    private final ModelPart leftWing;
    private final ModelPart rightWingExtra;
    private final ModelPart leftWingExtra;
    private final ModelPart rightWingEmissive;
    private final ModelPart leftWingEmissive;

    public CatalystElytraModel(ModelPart pRoot) {
        this.leftWing = pRoot.getChild("left_wing");
        this.rightWing = pRoot.getChild("right_wing");
        this.leftWingExtra = pRoot.getChild("left_wing_extra");
        this.rightWingExtra = pRoot.getChild("right_wing_extra");
        this.leftWingEmissive = pRoot.getChild("left_wing_emissive");
        this.rightWingEmissive = pRoot.getChild("right_wing_emissive");
    }

    public static LayerDefinition createBaseLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        CubeDeformation cubedeformation = new CubeDeformation(1.0F);

        partdefinition.addOrReplaceChild("left_wing", CubeListBuilder.create().texOffs(22, 0).addBox(-10.0F, 0.0F, 0.0F, 10.0F, 20.0F, 2.0F, cubedeformation), PartPose.offsetAndRotation(5.0F, 0.0F, 0.0F, 0.2617994F, 0.0F, -0.2617994F));
        partdefinition.addOrReplaceChild("right_wing", CubeListBuilder.create().texOffs(22, 0).mirror().addBox(0.0F, 0.0F, 0.0F, 10.0F, 20.0F, 2.0F, cubedeformation), PartPose.offsetAndRotation(-5.0F, 0.0F, 0.0F, 0.2617994F, 0.0F, 0.2617994F));

        partdefinition.addOrReplaceChild("left_wing_extra", CubeListBuilder.create(), PartPose.ZERO);
        partdefinition.addOrReplaceChild("right_wing_extra", CubeListBuilder.create(), PartPose.ZERO);

        partdefinition.addOrReplaceChild("left_wing_emissive", CubeListBuilder.create(), PartPose.ZERO);
        partdefinition.addOrReplaceChild("right_wing_emissive", CubeListBuilder.create(), PartPose.ZERO);

        return LayerDefinition.create(meshdefinition, 64, 32);
    }

    public static LayerDefinition createCatalystLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        CubeDeformation cubedeformation = new CubeDeformation(1.0F);

        partdefinition.addOrReplaceChild("left_wing", CubeListBuilder.create().texOffs(0, 0).addBox(-10.0F, 0.0F, 0.0F, 10.0F, 20.0F, 2.0F, cubedeformation), PartPose.offsetAndRotation(5.0F, 0.0F, 0.0F, 0.2617994F, 0.0F, -0.2617994F));
        partdefinition.addOrReplaceChild("right_wing", CubeListBuilder.create().texOffs(24, 0).mirror().addBox(0.0F, 0.0F, 0.0F, 10.0F, 20.0F, 2.0F, cubedeformation), PartPose.offsetAndRotation(-5.0F, 0.0F, 0.0F, 0.2617994F, 0.0F, 0.2617994F));

        partdefinition.addOrReplaceChild("left_wing_extra", CubeListBuilder.create().texOffs(0, 22).addBox(-10.0F, 0.0F, 0.0F, 10.0F, 20.0F, 2.0F, cubedeformation), PartPose.offsetAndRotation(5.0F, 0.0F, 0.0F, 0.2617994F, 0.0F, -0.2617994F));
        partdefinition.addOrReplaceChild("right_wing_extra", CubeListBuilder.create().texOffs(24, 22).mirror().addBox(0.0F, 0.0F, 0.0F, 10.0F, 20.0F, 2.0F, cubedeformation), PartPose.offsetAndRotation(-5.0F, 0.0F, 0.0F, 0.2617994F, 0.0F, 0.2617994F));

        partdefinition.addOrReplaceChild("left_wing_emissive", CubeListBuilder.create().texOffs(0, 44).addBox(-10.0F, 0.0F, 0.0F, 10.0F, 20.0F, 2.0F, cubedeformation), PartPose.offsetAndRotation(5.0F, 0.0F, 0.0F, 0.2617994F, 0.0F, -0.2617994F));
        partdefinition.addOrReplaceChild("right_wing_emissive", CubeListBuilder.create().texOffs(24, 44).mirror().addBox(0.0F, 0.0F, 0.0F, 10.0F, 20.0F, 2.0F, cubedeformation), PartPose.offsetAndRotation(-5.0F, 0.0F, 0.0F, 0.2617994F, 0.0F, 0.2617994F));

        return LayerDefinition.create(meshdefinition, 80, 80);
    }

    public static LayerDefinition createCatalystOverlayLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        CubeDeformation cubedeformation = new CubeDeformation(1.0F);

        partdefinition.addOrReplaceChild("left_wing", CubeListBuilder.create().texOffs(0, 64).addBox(-5.0F, 0.0F, 0.0F, 10.0F, 20.0F, 2.0F, cubedeformation), PartPose.offsetAndRotation(5.0F, 0.0F, 0.0F, 0.2617994F, 0.0F, -0.2617994F));
        partdefinition.addOrReplaceChild("right_wing", CubeListBuilder.create().texOffs(24, 64).mirror().addBox(0.0F, 0.0F, 0.0F, 10.0F, 20.0F, 2.0F, cubedeformation), PartPose.offsetAndRotation(-5.0F, 0.0F, 0.0F, 0.2617994F, 0.0F, 0.2617994F));

        partdefinition.addOrReplaceChild("left_wing_extra", CubeListBuilder.create(), PartPose.ZERO);
        partdefinition.addOrReplaceChild("right_wing_extra", CubeListBuilder.create(), PartPose.ZERO);

        partdefinition.addOrReplaceChild("left_wing_emissive", CubeListBuilder.create(), PartPose.ZERO);
        partdefinition.addOrReplaceChild("right_wing_emissive", CubeListBuilder.create(), PartPose.ZERO);


        return LayerDefinition.create(meshdefinition, 64, 96);
    }

    @Override
    public void renderToBuffer(@NotNull PoseStack pPoseStack, @NotNull VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, int color) {
        super.renderToBuffer(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, color);
        int fullBright = 15728880;
        this.leftWing.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, color);
        this.rightWing.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, color);
        this.leftWingExtra.copyFrom(this.leftWing);
        this.leftWingExtra.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, color);
        this.rightWingExtra.copyFrom(this.rightWing);
        this.rightWingExtra.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, color);
        this.leftWingEmissive.copyFrom(this.leftWing);
        this.leftWingEmissive.render(pPoseStack, pBuffer, fullBright, pPackedOverlay, color);
        this.rightWingEmissive.copyFrom(this.rightWing);
        this.rightWingEmissive.render(pPoseStack, pBuffer, fullBright, pPackedOverlay, color);
    }
    
    public void renderBase(PoseStack pPoseStack, VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, int color) {
        this.leftWing.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, color);
        this.rightWing.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, color);
    }
    
    public void renderCatalyst(PoseStack pPoseStack, VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, int color) {
        int fullBright = 15728880;
        this.leftWing.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, color);
        this.rightWing.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, color);
        this.leftWingExtra.copyFrom(this.leftWing);
        this.leftWingExtra.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, color);
        this.rightWingExtra.copyFrom(this.rightWing);
        this.rightWingExtra.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, color);
        this.leftWingEmissive.copyFrom(this.leftWing);
        this.leftWingEmissive.render(pPoseStack, pBuffer, fullBright, pPackedOverlay, color);
        this.rightWingEmissive.copyFrom(this.rightWing);
        this.rightWingEmissive.render(pPoseStack, pBuffer, fullBright, pPackedOverlay, color);
    }

    public void renderCatalystOverlay(PoseStack pPoseStack, VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, int color) {
        this.leftWing.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, color);
        this.rightWing.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, color);
    }

    protected @NotNull Iterable<ModelPart> headParts() {
        return ImmutableList.of();
    }

    protected @NotNull Iterable<ModelPart> bodyParts() {
        return ImmutableList.of(this.leftWing, this.rightWing);
    }

    public void setupAnim(LivingEntity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        float f = 0.2617994F;
        float f1 = -0.2617994F;
        float f2 = 0.0F;
        float f3 = 0.0F;
        if (pEntity.isFallFlying()) {
            float f4 = 1.0F;
            Vec3 vec3 = pEntity.getDeltaMovement();
            if (vec3.y < 0.0D) {
                Vec3 vec31 = vec3.normalize();
                f4 = 1.0F - (float)Math.pow(-vec31.y, 1.5D);
            }

            f = f4 * 0.34906584F + (1.0F - f4) * f;
            f1 = f4 * (-(float)Math.PI / 2F) + (1.0F - f4) * f1;
        } else if (pEntity.isCrouching()) {
            f = 0.6981317F;
            f1 = (-(float)Math.PI / 4F);
            f2 = 3.0F;
            f3 = 0.08726646F;
        }

        this.leftWing.y = f2;
        if (pEntity instanceof AbstractClientPlayer abstractclientplayer) {
            abstractclientplayer.elytraRotX += (f - abstractclientplayer.elytraRotX) * 0.1F;
            abstractclientplayer.elytraRotY += (f3 - abstractclientplayer.elytraRotY) * 0.1F;
            abstractclientplayer.elytraRotZ += (f1 - abstractclientplayer.elytraRotZ) * 0.1F;
            this.leftWing.xRot = abstractclientplayer.elytraRotX;
            this.leftWing.yRot = abstractclientplayer.elytraRotY;
            this.leftWing.zRot = abstractclientplayer.elytraRotZ;
        } else {
            this.leftWing.xRot = f;
            this.leftWing.zRot = f1;
            this.leftWing.yRot = f3;
        }

        this.rightWing.yRot = -this.leftWing.yRot;
        this.rightWing.y = this.leftWing.y;
        this.rightWing.xRot = this.leftWing.xRot;
        this.rightWing.zRot = -this.leftWing.zRot;
    }
}