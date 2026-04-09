package net.nokunami.elementus.client;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.nokunami.elementus.common.capability.itemNotifier.ItemNotifier;

public class ItemNotifierClient {
    private static final ItemNotifier clientInst = new ItemNotifier();

    public static void sync(int ding) {
        clientInst.setDing(ding);
    }

    public static void ding(LivingEntity entity, float f) {
        clientInst.addDing(1);
        entity.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 1, 1 / (entity.getRandom().nextFloat() * 0.1F + 0.8F) + f * 0.5F);
    }
}
