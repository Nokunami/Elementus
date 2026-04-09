package net.nokunami.elementus.common.capability;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.UUID;

public interface ITridentOwner extends INBTSerializable<CompoundTag> {
    void setOwner(UUID owner);

    UUID getOwner();

    boolean canRecall();
}
