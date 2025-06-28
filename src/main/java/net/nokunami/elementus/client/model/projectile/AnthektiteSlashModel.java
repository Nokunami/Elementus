package net.nokunami.elementus.client.model.projectile;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class AnthektiteSlashModel extends Model {
    public static final ResourceLocation TEXTURE = new ResourceLocation("textures/entity/trident.png");
    private final ModelPart root;

    public AnthektiteSlashModel(ModelPart pRoot) {
        super(RenderType::entitySolid);
        this.root = pRoot;
    }

    public static LayerDefinition createLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
//        bb_main.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 17).addBox(-8.0F, -8.0F, -0.5F, 16.0F, 16.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.5708F, 3.1416F, 0.0F));
        bb_main.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(34, 17).addBox(-8.0F, -8.0F, -0.5F, 16.0F, 16.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.5708F, 0.7854F, -3.1416F));

        return LayerDefinition.create(meshdefinition, 80, 80);
    }

    public void renderToBuffer(@NotNull PoseStack pPoseStack, @NotNull VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, float pRed, float pGreen, float pBlue, float pAlpha) {
        this.root.render(pPoseStack, pBuffer, 15728880, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
    }
}
