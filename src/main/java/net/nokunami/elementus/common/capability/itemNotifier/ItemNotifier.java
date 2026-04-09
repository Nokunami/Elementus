package net.nokunami.elementus.common.capability.itemNotifier;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.nokunami.elementus.common.capability.cAbility.CAbility;
import net.nokunami.elementus.common.capability.cAbility.CAbilityCap;

public class ItemNotifier {
    private int ding;

    public int getDing() { return ding; }
    public void setDing(int i) { ding = i; }

    public void addDing(int i) { ding += i; }

    public static ItemNotifier instance(LivingEntity entity) {
        if (entity instanceof ServerPlayer player) {
            var cap = player.getCapability(ItemNotifierCap.CAP);
            var opt = cap.resolve();
            if (cap.isPresent()) {
                return opt.orElseGet(ItemNotifier::new);
            }
        }
        return new ItemNotifier();
    }

    public void save(CompoundTag tag) { tag.putInt("ding", getDing()); }
    public void load(CompoundTag tag) { tag.getInt("ding"); }
}