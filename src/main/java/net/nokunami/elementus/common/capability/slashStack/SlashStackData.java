package net.nokunami.elementus.common.capability.slashStack;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingEvent;

public class SlashStackData {
    private final SlashStackInstanceManager stackManager = new SlashStackInstanceManager();
    Entity entity;

    public SlashStackData(Entity victim) {
        entity = victim;
    }

    public static void tick(LivingEvent.LivingTickEvent event) {
        var entity = event.getEntity();
        var level = entity.level();
        if (!level.isClientSide()) {
            slashData(entity).stackManager.tick();
        }
    }

    public static SlashStackData slashData(LivingEntity entity) {
        var cap = entity.getCapability(SlashStackCap.CAP);
        var opt = cap.resolve();
        if (cap.isPresent()) {
            return opt.orElseGet(() -> new SlashStackData(entity));
        }
        return new SlashStackData(entity);
    }

    public void addStack(Entity source) { addStack(source, 1); }
    public void addStack(Entity source, int stack) { addStack(source, 200, stack); }
    public void addStack(Entity source, int duration, int stack) {
        stackManager.addStack(source, duration, stack);
    }

    public int getStacks(Entity attacker) { return stackManager.getSlashStacks(attacker); }

    public void save(CompoundTag tag) {
        if (!stackManager.isEmpty()) tag.put("StackInstances", stackManager.save());
    }

    public void load(CompoundTag tag) {
        ListTag listTag = (ListTag) tag.get("StackInstances");
        if (listTag != null && !listTag.isEmpty()) stackManager.load(listTag);
    }
}
