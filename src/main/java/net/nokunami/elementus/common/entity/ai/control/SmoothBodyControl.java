package net.nokunami.elementus.common.entity.ai.control;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.control.BodyRotationControl;

/**
 * Based on @lgh877's codes:<a href="https://github.com/lgh877/CrimsonStevesMoreMobs/blob/57b2dda1f29392d516fcdd1deaec3a2e77b76369/src/main/java/net/mcreator/crimson_steves_mobs/SmartBodyHelper2.java">link</a>
 */
public class SmoothBodyControl extends BodyRotationControl {
    private final Mob mob;
    private static final float MAX_ROTATE = 75;
    private int rotationTickCounter;
    private static final int HISTORY_SIZE = 10;
    private float prevRenderYawHead;
    private final double[] histPosX = new double[HISTORY_SIZE];
    private final double[] histPosZ = new double[HISTORY_SIZE];

    public SmoothBodyControl(Mob mob) {
        super(mob);
        this.mob = mob;
    }

    @Override
    public void clientTick() {
        for (int i = histPosX.length - 1; i > 0; i--) {
            histPosX[i] = histPosX[i - 1];
            histPosZ[i] = histPosZ[i - 1];
        }
        if (this.hasMoved()) {
            this.mob.yBodyRot = this.mob.getYRot();
            this.rotateHeadIfNecessary();
            this.prevRenderYawHead = this.mob.yHeadRot;
            this.rotationTickCounter = 0;
        } else {
            if (this.noMobPassengers()) {
                if (Math.abs(this.mob.yHeadRot - this.prevRenderYawHead) > 15.0F) {
                    this.rotationTickCounter = 0;
                    this.prevRenderYawHead = this.mob.yHeadRot;
                    this.rotateBodyIfNecessary();
                } else {
                    float limit = MAX_ROTATE;
                    ++this.rotationTickCounter;
                    final int speed = 10;
                    if (this.rotationTickCounter > speed) {
                        limit = Math.max(1 - (rotationTickCounter - speed) / (float) speed, 0) * MAX_ROTATE;
                    }
                    mob.yBodyRot = approach(mob.yHeadRot, mob.yBodyRot, limit);// https://gist.github.com/TheGreyGhost/b5ea2acd1c651a2d6350#file-gistfile1-txt-L60
                }
            }
        }
    }

    private void rotateBodyIfNecessary() {
        this.mob.yBodyRot = Mth.rotateIfNecessary(this.mob.yBodyRot, this.mob.yHeadRot, (float) this.mob.getMaxHeadYRot());
    }

    private void rotateHeadIfNecessary() {
        this.mob.yHeadRot = Mth.rotateIfNecessary(this.mob.yHeadRot, this.mob.yBodyRot, (float) this.mob.getMaxHeadYRot());
    }

    private boolean noMobPassengers() {
        return this.mob.getPassengers().isEmpty() || !(this.mob.getPassengers().get(0) instanceof Mob);
    }

    private boolean hasMoved() {
        double d0 = this.mob.getX() - this.mob.xo;
        double d1 = this.mob.getZ() - this.mob.zo;
        return d0 * d0 + d1 * d1 > (double) 2.5000003E-7F;
    }

    public static float approach(float target, float current, float limit) {
        float delta = Mth.wrapDegrees(current - target);
        if (delta < -limit) {
            delta = -limit;
        } else if (delta >= limit) {
            delta = limit;
        }
        return target + delta * 0.55F;
    }
}
