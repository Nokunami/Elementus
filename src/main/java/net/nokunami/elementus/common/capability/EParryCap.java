package net.nokunami.elementus.common.capability;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.nokunami.elementus.common.registry.ECapabilities;

import javax.annotation.Nonnull;

import static net.nokunami.elementus.Elementus.EID;

/// Credits: L_Ender's Cataclysm ParryCapability
public class EParryCap {
    public static ResourceLocation ID = new ResourceLocation(EID, "parry_cap");

    public static class ParryCapImp implements IParryCap {
        public int tick;

        public void setParryTick(int timer) { tick = timer; }
        public int getParryTick() { return tick; }

        public CompoundTag serializeNBT() {
            CompoundTag tag = new CompoundTag();
            tag.putInt("tick", getParryTick());
            return tag;
        }

        public void deserializeNBT(CompoundTag nbt) { setParryTick(nbt.getInt("tick")); }

        public static class ParryProvider implements ICapabilityProvider, ICapabilitySerializable<CompoundTag> {
            private final LazyOptional<IParryCap> instance = LazyOptional.of(ParryCapImp::new);

            public CompoundTag serializeNBT() {
                return instance.orElseThrow(NullPointerException::new).serializeNBT();
            }

            public void deserializeNBT(CompoundTag nbt) {
                instance.orElseThrow(NullPointerException::new).deserializeNBT(nbt);
            }

            @Nonnull
            public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, Direction side) {
                return ECapabilities.PARRY_CAPABILITY.orEmpty(cap, this.instance.cast());
            }
        }
    }

    public interface IParryCap extends INBTSerializable<CompoundTag> {
        void setParryTick(int var1);

        int getParryTick();
    }
}
