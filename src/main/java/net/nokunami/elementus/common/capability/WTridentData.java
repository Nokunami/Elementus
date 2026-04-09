package net.nokunami.elementus.common.capability;

import net.minecraft.nbt.CompoundTag;

import java.util.UUID;

public class WTridentData implements ITridentOwner {
    public UUID owner;

    @Override
    public void setOwner(UUID uuid) {
        owner = uuid;
    }

    @Override
    public UUID getOwner() {
        return owner;
    }

    @Override
    public boolean canRecall() {
        return owner != null;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putUUID("RecallOwner", getOwner());
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        setOwner(nbt.getUUID("RecallOwner"));
    }
}
