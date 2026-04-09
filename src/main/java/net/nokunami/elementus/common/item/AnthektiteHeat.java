package net.nokunami.elementus.common.item;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;

public class AnthektiteHeat {
    ItemStack stack;
    static String heat = "AnthektiteHeat";
    static String heatTick = "AnthektiteHeatTick";
    static int heatLimit = 30;

//    public static void setHeat(ItemStack stack, int i) {
//        CompoundTag tag = stack.getOrCreateTag();
//        if (i <= 0) tag.remove(heat);
//        else tag.putInt(heat, i);
//    }
//    public static int getHeat(ItemStack stack) {
//        return stack.getOrCreateTag().getInt(heat);
//    }
//
//    public static void addHeat(ItemStack stack, int i) {
//        setHeat(stack, getHeat(stack) + i);
//    }
//
//    public static void setHeatTick(ItemStack stack, int i) {
//        CompoundTag tag = stack.getOrCreateTag();
//        if (i <= 0) tag.remove(heatTick);
//        else tag.putInt(heatTick, i);
//    }
//    public static int getHeatTick(ItemStack stack) {
//        return stack.getOrCreateTag().getInt(heatTick);
//    }
//
//    public static void addHeatTick(ItemStack stack, int i) { setHeatTick(stack, getHeatTick(stack) + i); }
//    public static void decrementHeatTick(ItemStack stack) { setHeatTick(stack, getHeatTick(stack) - 1); }
//    public static void resetHeatTick(ItemStack stack) { setHeatTick(stack, 0); }

    public AnthektiteHeat(ItemStack itemStack) {
        stack = itemStack;
    }

    public static AnthektiteHeat anthektiteHeat(ItemStack stack) {
        return new AnthektiteHeat(stack);
    }

    public void setHeat(int i) {
        CompoundTag tag = stack.getOrCreateTag();
        if (i <= 0) tag.remove(heat);
        else tag.putInt(heat, Mth.clamp(i, 0, heatLimit));
    }
    public int getHeat() {
        return stack.getOrCreateTag().getInt(heat);
    }

    public boolean hasHeat() {
        return getHeat() > 0;
    }

    public void addHeat(int i) { setHeat(getHeat() + i); }
    private void decrementHeat() { setHeat(getHeat() - 1); }
    public void resetHeat() { setHeat(0); }

    public void setHeatTick(int i) {
        CompoundTag tag = stack.getOrCreateTag();
        if (i <= 0 && getHeat() <= 0) tag.remove(heatTick);
        else tag.putInt(heatTick, i);
    }
    public int getHeatTick() {
        return stack.getOrCreateTag().getInt(heatTick);
    }

    public void addHeatTick(int i) { setHeatTick(getHeatTick() + i); }
    private void decrementHeatTick() { setHeatTick(getHeatTick() - 1); }
    public void resetHeatTick() { setHeatTick(0); }

    public void decrement() {
        if (hasHeat()) {
            decrementHeatTick();
            if (getHeatTick() <= 0) {
                addHeat(-1);
                addHeatTick(60);
            }
        }
    }
}