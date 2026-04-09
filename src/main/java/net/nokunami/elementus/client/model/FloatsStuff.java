package net.nokunami.elementus.client.model;

import net.minecraft.util.Mth;

public class FloatsStuff {
    float swing;
    float swingAmount;
    float additional = 1;

    public static FloatsStuff inst(float swing, float swingAmount) {
        return new FloatsStuff(swing, swingAmount);
    }

    public FloatsStuff(float swing, float swingAmount) {
        this.swing = swing;
        this.swingAmount = swingAmount;
    }

    public float getSwing() { return swing; }
    public float getAmount() { return swingAmount; }
    public float getAdditional() { return additional; }

    public FloatsStuff offSetSwing(float offset) {
        swing += offset;
        return this;
    }
    public FloatsStuff multiplySwing(float offset) {
        swing *= offset;
        return this;
    }
    public FloatsStuff setSwing(float offset) {
        swing = offset;
        return this;
    }

    public FloatsStuff offSetAmount(float offset) {
        swingAmount += offset;
        return this;
    }
    public FloatsStuff multiplyAmount(float offset) {
        swingAmount *= offset;
        return this;
    }
    public FloatsStuff setAmount(float offset) {
        swingAmount = offset;
        return this;
    }

    public FloatsStuff setAdd(float add) {
        additional = add;
        return this;
    }

    public float cos() { return cos(0); }
    public float cosPI() { return cos(Mth.PI); }

    public float maxCos(float limit) { return Math.max(limit, cos(0)); }
    public float maxCosPI(float limit) { return Math.max(limit, cos(Mth.PI)); }
    public float maxCos(float limit, float offset) { return Math.max(limit, cos(offset)); }

    public float minCos(float limit) { return Math.min(limit, cos(0)); }
    public float minCosPI(float limit) { return Math.min(limit, cos(Mth.PI)); }
    public float minCos(float limit, float offset) { return Math.min(limit, cos(offset)); }

    public float cos(float offset) { return Mth.cos(swing + offset) * swingAmount * additional; }


    public float sin() { return sin(0); }
    public float sinPI() { return sin(Mth.PI); }

    public float maxSin(float limit) { return Math.max(limit, sin(0)); }
    public float maxSinPI(float limit) { return Math.max(limit, sin(Mth.PI)); }
    public float maxSin(float limit, float offset) { return Math.max(limit, sin(offset)); }

    public float minSin(float limit) { return Math.min(limit, sin(0)); }
    public float minSinPI(float limit) { return Math.min(limit, sin(Mth.PI)); }
    public float minSin(float limit, float offset) { return Math.min(limit, sin(offset)); }

    public float sin(float offset) { return Mth.sin(swing + offset) * swingAmount * additional; }
}
