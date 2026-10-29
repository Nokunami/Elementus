package net.nokunami.elementus.common.capability.slashStack;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.entity.Entity;

import java.util.*;

public class SlashStackInstanceManager {
    private final Map<UUID, SlashStackInstance> stackInstances = new HashMap<>();

    public void tick() {
        var cooldowns = stackInstances.entrySet().stream().filter(i -> i.getValue().tickOrDiscard()).toList();
        cooldowns.forEach(cooldown -> stackInstances.remove(cooldown.getKey()));
    }

    public boolean isEmpty() { return stackInstances.isEmpty(); }
    public void addStack(Entity entity, int duration) { addStack(entity, duration, 1); }
    public void addStack(Entity entity, int duration, int stack) {
        if (entity != null) {
            var uuid = entity.getUUID();
            if (stackInstances.containsKey(uuid)) {
                var instance = stackInstances.get(uuid);
                instance.add(duration, stack);
            } else stackInstances.put(uuid, new SlashStackInstance(duration, stack));
        }
    }

    public int getSlashStacks(Entity entity) {
        var inst = stackInstances.get(entity.getUUID());
        return inst != null ? inst.getStack() : 0;
    }

    public ListTag save() {
        var list = new ListTag();
        stackInstances.forEach((uuid, instance) -> {
            CompoundTag tag = new CompoundTag();
            if (!instance.tickOrDiscard()) {
                tag.putUUID("Source", uuid);
                tag.putInt("Duration", instance.getDuration());
                tag.putInt("Stack", instance.getStack());
            }
            list.add(tag);
        });
        return list;
    }

    public void load(ListTag list) {
        if (list != null) {
            list.forEach(tagInList -> {
                var tag = (CompoundTag) tagInList;
                UUID source = tag.getUUID("Source");
                int duration = tag.getInt("Duration");
                int stack = tag.getInt("Stack");
                stackInstances.put(source, new SlashStackInstance(duration, stack));
            });
        }
    }
}
