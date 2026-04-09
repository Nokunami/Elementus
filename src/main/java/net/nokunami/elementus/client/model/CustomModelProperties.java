package net.nokunami.elementus.client.model;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.world.entity.WalkAnimationState;
import org.joml.Vector3f;

public class CustomModelProperties {
    float limbSwing, limbSwingAmount;
    float partialTick, ageInTicks;

    public CustomModelProperties limbSwing(float position, float speed) {
        limbSwing = position;
        limbSwingAmount = speed;
        return this;
    }
    public CustomModelProperties ticks(float pTick, float ageTicks) {
        partialTick = pTick;
        ageInTicks = ageTicks;
        return this;
    }

    public float limbSwing() { return limbSwing; }
    public float limbSwingAmount() { return limbSwingAmount; }
    public float partialTick() { return partialTick; }
    public float ageInTicks() { return ageInTicks; }

    public static Keyframe frame(float timestamp, Vector3f target, AnimationChannel.Interpolation interpolation) {
        return new Keyframe(timestamp, target, interpolation);
    }
}
