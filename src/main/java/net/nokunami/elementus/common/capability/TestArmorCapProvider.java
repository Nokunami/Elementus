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

public class TestArmorCapProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<TestArmorCap> TEST_ARMOR_CAP = CapabilityManager.get(new CapabilityToken<TestArmorCap>() {});
    private TestArmorCap cap;
    private final LazyOptional<TestArmorCap> optional = LazyOptional.of(this::create);

    private TestArmorCap create() {
        if (cap == null) cap = new TestArmorCap();
        return cap;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return cap != TEST_ARMOR_CAP ? LazyOptional.empty() : optional.cast();
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
