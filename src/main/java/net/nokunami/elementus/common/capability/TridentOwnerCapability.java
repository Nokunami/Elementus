package net.nokunami.elementus.common.capability;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.nokunami.elementus.common.registry.ECapabilities;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static net.nokunami.elementus.Elementus.modLoc;

public class TridentOwnerCapability implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<WTridentData> ABILITY = CapabilityManager.get(new CapabilityToken<>() { });
    public static ResourceLocation ID = modLoc("trident_data");
    private final LazyOptional<ITridentOwner> instance = LazyOptional.of(WTridentData::new);

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return ECapabilities.TRIDENT_OWNER_CAPABILITY.orEmpty(cap, instance.cast());
    }

    @Override
    public CompoundTag serializeNBT() {
        return instance.orElseThrow(NullPointerException::new).serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        instance.orElseThrow(NullPointerException::new).deserializeNBT(nbt);
    }
}
