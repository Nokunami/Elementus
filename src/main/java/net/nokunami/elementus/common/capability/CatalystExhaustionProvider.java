package net.nokunami.elementus.common.capability;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CatalystExhaustionProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<CatalystExhaustion> CAP = CapabilityManager.get(new CapabilityToken<>() {});
    private CatalystExhaustion cap;
    private final LazyOptional<CatalystExhaustion> optional = LazyOptional.of(this::create);

    private CatalystExhaustion create() {
        if (cap == null) cap = new CatalystExhaustion();
        return cap;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return cap != CAP ? LazyOptional.empty() : optional.cast();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        create().save(tag);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        create().load(nbt);
    }
}
