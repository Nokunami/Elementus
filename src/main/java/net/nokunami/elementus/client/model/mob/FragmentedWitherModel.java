package net.nokunami.elementus.client.model.mob;// Made with Blockbench 5.0.7
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.nokunami.elementus.client.model.CustomModelProperties;
import net.nokunami.elementus.client.model.EHierarchicalModel;
import net.nokunami.elementus.common.entity.boss.FragmentedWither;
import org.jetbrains.annotations.NotNull;

public class FragmentedWitherModel<T extends FragmentedWither> extends EHierarchicalModel<T> {
	private final ModelPart bone;
	private final ModelPart clavicle;
	private final ModelPart leftSkull;
	private final ModelPart mainSkull;
	private final ModelPart rightSkull;
	private final ModelPart ribcage;
	private final ModelPart rib1;
	private final ModelPart rib2;
	private final ModelPart rabSeg;
	private final ModelPart rib3;

	public FragmentedWitherModel(ModelPart root) {
		super(root);
		bone = root.getChild("bone");
		clavicle = bone.getChild("clavicle");
		leftSkull = clavicle.getChild("leftSkull");
		mainSkull = clavicle.getChild("mainSkull");
		rightSkull = clavicle.getChild("rightSkull");
		ribcage = bone.getChild("ribcage");
		rib1 = ribcage.getChild("rib1");
		rib2 = ribcage.getChild("rib2");
		rabSeg = ribcage.getChild("rabSeg");
		rib3 = rabSeg.getChild("rib3");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition clavicle = bone.addOrReplaceChild("clavicle", CubeListBuilder.create().texOffs(0, 32).addBox(-12.0F, -4.0F, -2.0F, 24.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -16.0F, 0.0F));

		PartDefinition leftSkull = clavicle.addOrReplaceChild("leftSkull", CubeListBuilder.create().texOffs(72, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(11.0F, -2.0F, 0.0F));

		PartDefinition mainSkull = clavicle.addOrReplaceChild("mainSkull", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -10.0F, -5.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, 0.0F));

		PartDefinition rightSkull = clavicle.addOrReplaceChild("rightSkull", CubeListBuilder.create().texOffs(40, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-11.0F, -2.0F, 0.0F));

		PartDefinition ribcage = bone.addOrReplaceChild("ribcage", CubeListBuilder.create().texOffs(0, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -16.0F, 0.0F));

		PartDefinition rib1 = ribcage.addOrReplaceChild("rib1", CubeListBuilder.create().texOffs(32, 48).addBox(-7.0F, -3.0F, -2.0F, 14.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, 0.5F));

		PartDefinition rib2 = ribcage.addOrReplaceChild("rib2", CubeListBuilder.create().texOffs(32, 54).addBox(-7.0F, -3.0F, -2.0F, 14.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 8.0F, 0.5F));

		PartDefinition rabSeg = ribcage.addOrReplaceChild("rabSeg", CubeListBuilder.create().texOffs(0, 61).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 9.0F, 0.0F));

		PartDefinition rib3 = rabSeg.addOrReplaceChild("rib3", CubeListBuilder.create().texOffs(32, 60).addBox(-7.0F, -3.0F, -2.0F, 14.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.0F, 0.5F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	public void prepareMobModel(@NotNull T entity, float limbSwing, float limbSwingAmount, float partialTick) {
		setupHeadRotation(entity, rightSkull, 0, partialTick);
		setupHeadRotation(entity, leftSkull, 1, partialTick);
	}

	private void setupHeadRotation(T entity, ModelPart pPart, int head, float partialTick) {
		pPart.yRot = (entity.getHeadYRot(head, partialTick) - entity.yBodyRot) * ((float) Math.PI / 180F);
		pPart.xRot = entity.getHeadXRot(head, partialTick) * ((float) Math.PI / 180F);
	}

	@Override
	public void postSetupAnim(T entity, CustomModelProperties modelProperties) {

	}
}