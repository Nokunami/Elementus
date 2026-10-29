package net.nokunami.elementus.api.util;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.util.Mth;
import org.joml.Vector3f;

public class ModelPartUtils {
    ModelPart modelPart;
    float partialTickPos;
    float partialTickRot;
    final float pX, pY, pZ, rX, rY, rZ;

    public static CubeDeformation deformCube(double value) { return deformCube(value, value, value); }
    public static CubeDeformation deformCube(double valueX, double valueY, double valueZ) {
        return new CubeDeformation((float) valueX, (float) valueY, (float) valueZ);
    }

    public static ModelPartUtils setPart(ModelPart part) { return setPart(part, 1, 1); }
    public static ModelPartUtils setPart(ModelPart part, double pT) { return setPart(part, pT, pT); }
    public static ModelPartUtils setPart(ModelPart part, double pTP, double pTR) { return new ModelPartUtils(part, pTP, pTR); }

    public static void setBone(ModelPart part) { setBone(part, 1, 1); }
    public static void setBone(ModelPart part, double pTP, double pTR) { new ModelPartUtils(part, pTP, pTR).bone(); }

    public static void setZero(ModelPart part) { setZero(part, 1, 1); }
    public static void setZero(ModelPart part, double pTP, double pTR) { new ModelPartUtils(part, pTP, pTR).zero(); }

    public ModelPartUtils(ModelPart part) {
        this(part, 1, 1);
    }

    public ModelPartUtils(ModelPart part, double pTP, double pTR) {
        modelPart = part;
        partialTickPos = (float) pTP;
        partialTickRot = (float) pTR;
        pX = part.getInitialPose().x;
        pY = part.getInitialPose().y;
        pZ = part.getInitialPose().z;
        rX = part.getInitialPose().xRot;
        rY = part.getInitialPose().yRot;
        rZ = part.getInitialPose().zRot;
    }

    public void scalePosRot(double sX, double sY, double sZ, double pX, double pY, double pZ, double rX, double rY, double rZ) {
        modelPart.xScale = (float) sX;
        modelPart.yScale = (float) sY;
        modelPart.zScale = (float) sZ;
        modelPart.setPos(Mth.rotLerp(partialTickPos, modelPart.x, (float) pX), Mth.rotLerp(partialTickPos, modelPart.y, (float) pY), Mth.rotLerp(partialTickPos, modelPart.z, (float) pZ));
        modelPart.setRotation(Mth.rotLerp(partialTickRot, modelPart.xRot, (float) rX), Mth.rotLerp(partialTickRot, modelPart.yRot, (float) rY), Mth.rotLerp(partialTickRot, modelPart.zRot, (float) rZ));
    }

    public void posRot(double pX, double pY, double pZ, double rX, double rY, double rZ) {
        scalePosRot(1, 1, 1, pX, pY, pZ, rX, rY, rZ);
    }
    public void posRot(double pTP, double pTR) {
        partialTickPos = (float) pTP;
        partialTickRot = (float) pTR;
        scalePosRot(1, 1, 1, pX, pY, pZ, rX, rY, rZ);
//        modelPart.resetPose();
    }
    public void posRot() {
        scalePosRot(1, 1, 1, pX, pY, pZ, rX, rY, rZ);
    }

    public void pos(double pX, double pY, double pZ) { posRot(pX, pY, pZ, 0, 0, 0); }
    public void pos(double pTP) { partialTickPos = (float) pTP; posRot(pX, pY, pZ, 0, 0, 0); }
    public void pos() { posRot(pX, pY, pZ, 0, 0, 0); }

    public void rot(double rX, double rY, double rZ) { posRot(0, 0, 0, rX, rY, rZ); }
    public void rot(double pTR) { partialTickRot = (float) pTR; posRot(0, 0, 0, rX, rY, rZ); }

    public void posRotOS(double pX, double pY, double pZ, double rX, double rY, double rZ) {
        modelPart.xScale = 1;
        modelPart.yScale = 1;
        modelPart.zScale = 1;
        modelPart.offsetPos(new Vector3f((float) pX, (float) pY, (float) pZ));
        modelPart.offsetRotation(new Vector3f((float) rX, (float) rY, (float) rZ));
    }

    public void posOS(double pX, double pY, double pZ) {
        posRotOS(pX, pY, pZ, 0, 0 ,0);
    }

    public void rotOS(double rX, double rY, double rZ) {
        posRotOS(0, 0, 0, rX, rY, rZ);
    }

    public void bone() {
        posRot(0, 24, 0, 0, 0, 0);
    }

    public void zero() {
        posRot(0, 0, 0, 0, 0, 0);
    }
}
