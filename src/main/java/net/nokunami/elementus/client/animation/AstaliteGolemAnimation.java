package net.nokunami.elementus.client.animation;// Save this class in your mod and generate all required imports

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationChannel.*;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

import static net.minecraft.client.animation.KeyframeAnimations.degreeVec;
import static net.minecraft.client.animation.KeyframeAnimations.posVec;
import static net.nokunami.elementus.client.model.CustomModelProperties.frame;

/**
 * Made with Blockbench 5.0.7
 * Exported for Minecraft version 1.19 or later with Mojang mappings
 * @author Author
 */
public class AstaliteGolemAnimation {
	public static final AnimationDefinition latchOpen = AnimationDefinition.Builder.withLength(0.5F)
			.addAnimation("latch", new AnimationChannel(Targets.ROTATION,
					frame(0.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
					frame(0.25F, degreeVec(80.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
					frame(0.5F, degreeVec(80.0F, 0.0F, 0.0F), Interpolations.CATMULLROM)
			))
			.build();

	public static final AnimationDefinition latchClose = AnimationDefinition.Builder.withLength(0.5F)
			.addAnimation("latch", new AnimationChannel(Targets.ROTATION,
					frame(0.0F, degreeVec(80.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
					frame(0.125F, degreeVec(5.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
					frame(0.5F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM)
			))
			.build();

	public static final AnimationDefinition sit = AnimationDefinition.Builder.withLength(0.5F)
			.addAnimation("bone", new AnimationChannel(Targets.POSITION,
					frame(0.0F, posVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
					frame(0.25F, posVec(0.0F, -6.0F, 0.0F), Interpolations.CATMULLROM),
					frame(0.5F, posVec(0.0F, -6.0F, 0.0F), Interpolations.CATMULLROM)
			))
			.addAnimation("leftLeg", new AnimationChannel(Targets.ROTATION,
					frame(0.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
					frame(0.25F, degreeVec(70.5F, 0.0F, 0.0F), Interpolations.CATMULLROM),
					frame(0.5F, degreeVec(70.5F, 0.0F, 0.0F), Interpolations.CATMULLROM)
			))
			.addAnimation("leftLeg", new AnimationChannel(Targets.POSITION,
					frame(0.0F, posVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
					frame(0.25F, posVec(0.0F, -1.5F, -8.5F), Interpolations.CATMULLROM),
					frame(0.5F, posVec(0.0F, -1.5F, -8.5F), Interpolations.CATMULLROM)
			))
			.addAnimation("rightLeg", new AnimationChannel(Targets.ROTATION,
					frame(0.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
					frame(0.25F, degreeVec(10.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
					frame(0.5F, degreeVec(10.0F, 0.0F, 0.0F), Interpolations.CATMULLROM)
			))
			.addAnimation("rightLeg", new AnimationChannel(Targets.POSITION,
					frame(0.0F, posVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
					frame(0.25F, posVec(0.0F, 5.0F, -9.5F), Interpolations.CATMULLROM),
					frame(0.5F, posVec(0.0F, 5.0F, -9.5F), Interpolations.CATMULLROM)
			))
			.addAnimation("body", new AnimationChannel(Targets.ROTATION,
					frame(0.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
					frame(0.25F, degreeVec(10.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
					frame(0.5F, degreeVec(10.0F, 0.0F, 0.0F), Interpolations.CATMULLROM)
			))
			.addAnimation("leftArm", new AnimationChannel(Targets.ROTATION,
					frame(0.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
					frame(0.25F, degreeVec(-25.0F, 0.0F, 0.0F), Interpolations.CATMULLROM)
			))
			.addAnimation("leftForeArm", new AnimationChannel(Targets.POSITION,
					frame(0.125F, posVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
					frame(0.25F, posVec(0.0F, 1.0F, 0.0F), Interpolations.CATMULLROM),
					frame(0.5F, posVec(0.0F, 1.0F, 0.0F), Interpolations.CATMULLROM)
			))
			.addAnimation("rightArm", new AnimationChannel(Targets.ROTATION,
					frame(0.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
					frame(0.25F, degreeVec(-25.0F, 0.0F, 0.0F), Interpolations.CATMULLROM)
			))
			.addAnimation("rightForeArm", new AnimationChannel(Targets.POSITION,
					frame(0.125F, posVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
					frame(0.25F, posVec(0.0F, 1.0F, 0.0F), Interpolations.CATMULLROM),
					frame(0.5F, posVec(0.0F, 1.0F, 0.0F), Interpolations.CATMULLROM)
			))
			.build();

	public static final AnimationDefinition stand = AnimationDefinition.Builder.withLength(0.5F)
			.addAnimation("bone", new AnimationChannel(Targets.POSITION,
					frame(0.0F, posVec(0.0F, -6.0F, 0.0F), Interpolations.CATMULLROM),
					frame(0.2083F, posVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
					frame(0.5F, posVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM)
			))
			.addAnimation("leftLeg", new AnimationChannel(Targets.ROTATION,
					frame(0.0F, degreeVec(70.5F, 0.0F, 0.0F), Interpolations.CATMULLROM),
					frame(0.25F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
					frame(0.5F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM)
			))
			.addAnimation("leftLeg", new AnimationChannel(Targets.POSITION,
					frame(0.0F, posVec(0.0F, -1.5F, -8.5F), Interpolations.CATMULLROM),
					frame(0.25F, posVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
					frame(0.5F, posVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM)
			))
			.addAnimation("rightLeg", new AnimationChannel(Targets.ROTATION,
					frame(0.0F, degreeVec(10.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
					frame(0.25F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
					frame(0.5F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM)
			))
			.addAnimation("rightLeg", new AnimationChannel(Targets.POSITION,
					frame(0.0F, posVec(0.0F, 5.0F, -9.5F), Interpolations.CATMULLROM),
					frame(0.25F, posVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
					frame(0.5F, posVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM)
			))
			.addAnimation("body", new AnimationChannel(Targets.ROTATION,
					frame(0.0F, degreeVec(10.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
					frame(0.25F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
					frame(0.5F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM)
			))
			.addAnimation("leftArm", new AnimationChannel(Targets.ROTATION,
					frame(0.0F, degreeVec(-25.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
					frame(0.25F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
					frame(0.5F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM)
			))
			.addAnimation("leftForeArm", new AnimationChannel(Targets.POSITION,
					frame(0.0F, posVec(0.0F, 1.0F, 0.0F), Interpolations.CATMULLROM),
					frame(0.25F, posVec(0.0F, 1.0F, 0.0F), Interpolations.CATMULLROM),
					frame(0.375F, posVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM)
			))
			.addAnimation("rightArm", new AnimationChannel(Targets.ROTATION,
					frame(0.0F, degreeVec(-25.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
					frame(0.25F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
					frame(0.5F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM)
			))
			.addAnimation("rightForeArm", new AnimationChannel(Targets.POSITION,
					frame(0.0F, posVec(0.0F, 1.0F, 0.0F), Interpolations.CATMULLROM),
					frame(0.25F, posVec(0.0F, 1.0F, 0.0F), Interpolations.CATMULLROM),
					frame(0.375F, posVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM)
			))
			.build();

	public static final AnimationDefinition crossPunchLeft = AnimationDefinition.Builder.withLength(1.0F)
			.addAnimation("body", new AnimationChannel(Targets.ROTATION,
					frame(0.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
					frame(0.125F, degreeVec(0.0F, -10.0F, 0.0F), Interpolations.CATMULLROM),
					frame(0.25F, degreeVec(0.0F, -10.0F, 0.0F), Interpolations.CATMULLROM),
					frame(0.3333F, degreeVec(0.0F, 10.0F, 0.0F), Interpolations.CATMULLROM),
					frame(0.5833F, degreeVec(0.0F, 10.0F, 0.0F), Interpolations.CATMULLROM),
					frame(0.75F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
					frame(1.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM)
			))
			.addAnimation("upperBody", new AnimationChannel(Targets.ROTATION,
					frame(0.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
					frame(0.125F, degreeVec(0.0F, -10.0F, 0.0F), Interpolations.CATMULLROM),
					frame(0.25F, degreeVec(0.0F, -10.0F, 0.0F), Interpolations.CATMULLROM),
					frame(0.3333F, degreeVec(0.0F, 10.0F, 0.0F), Interpolations.CATMULLROM),
					frame(0.5833F, degreeVec(0.0F, 10.0F, 0.0F), Interpolations.CATMULLROM),
					frame(0.75F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
					frame(1.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM)
			))
			.addAnimation("leftArm", new AnimationChannel(Targets.ROTATION,
					frame(0.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
					frame(0.125F, degreeVec(27.5F, 0.0F, 0.0F), Interpolations.CATMULLROM),
					frame(0.25F, degreeVec(27.5F, 0.0F, 0.0F), Interpolations.CATMULLROM),
					frame(0.3333F, degreeVec(-52.5F, 0.0F, 0.0F), Interpolations.CATMULLROM),
					frame(0.5833F, degreeVec(-52.5F, 0.0F, 0.0F), Interpolations.CATMULLROM),
					frame(0.75F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
					frame(1.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM)
			))
			.addAnimation("leftForeArm", new AnimationChannel(Targets.ROTATION,
					frame(0.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
					frame(0.125F, degreeVec(-90.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
					frame(0.25F, degreeVec(-90.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
					frame(0.3333F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
					frame(0.5833F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
					frame(0.75F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
					frame(1.0F, degreeVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM)
			))
			.addAnimation("leftForeArm", new AnimationChannel(Targets.POSITION,
					frame(0.0F, posVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
					frame(0.125F, posVec(0.0F, -4.0F, 0.0F), Interpolations.CATMULLROM),
					frame(0.25F, posVec(0.0F, -4.0F, 0.0F), Interpolations.CATMULLROM),
					frame(0.3333F, posVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
					frame(0.5833F, posVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
					frame(0.75F, posVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM),
					frame(1.0F, posVec(0.0F, 0.0F, 0.0F), Interpolations.CATMULLROM)
			))
			.build();
}