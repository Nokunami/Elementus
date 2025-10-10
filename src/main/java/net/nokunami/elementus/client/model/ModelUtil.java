package net.nokunami.elementus.client.model;

import net.minecraft.client.model.geom.ModelPart;

public class ModelUtil {
    public static void setScale(ModelPart part, float xS, float yS, float zS) {
        part.xScale = xS;
        part.yScale = yS;
        part.zScale = zS;
    }

    public static void resetScale(ModelPart part) {
        setScale(part, 1, 1, 1);
    }

    public static void setDefaults(ModelPart part, float pX, float pY, float pZ, float rX, float rY, float rZ) {
        part.xScale = 1;
        part.yScale = 1;
        part.zScale = 1;
        part.setPos(pX, pY, pZ);
        part.setRotation(rX, rY, rZ);
    }

    public static void setDefaults(ModelPart part, double pX, double pY, double pZ, double rX, double rY, double rZ) {
        part.xScale = 1;
        part.yScale = 1;
        part.zScale = 1;
        part.setPos((float) pX, (float) pY, (float) pZ);
        part.setRotation((float) rX, (float) rY, (float) rZ);
    }

    public static void setDefaults(ModelPart part, double pX, double pY, double pZ) {
        part.xScale = 1;
        part.yScale = 1;
        part.zScale = 1;
        part.setPos((float) pX, (float) pY, (float) pZ);
        part.setRotation(0, 0, 0);
    }

    public static void setDefaults(ModelPart part) {
        part.xScale = 1;
        part.yScale = 1;
        part.zScale = 1;
        part.setPos(0, 0, 0);
        part.setRotation(0, 0, 0);
    }
}