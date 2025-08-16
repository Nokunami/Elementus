package net.nokunami.elementus.common.capability;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.nokunami.elementus.common.registry.ModCapabilities;

import javax.annotation.Nonnull;
import java.util.UUID;

import static net.nokunami.elementus.Elementus.MODID;

public class TridentOwnerCapability {
    public static ResourceLocation ID = new ResourceLocation(MODID, "parry_cap");

    public static class TridentOwnerCap implements ITridentOwner {
        public UUID owner;

        public void setOwner(UUID owner) {
            this.owner = owner;
        }

        public UUID getOwner() {
            return this.owner;
        }

        public boolean canRecall() {
            return this.owner != null;
        }

        public CompoundTag serializeNBT() {
            CompoundTag tag = new CompoundTag();
            tag.putUUID("TrueOwner", this.getOwner());
            return tag;
        }

        public void deserializeNBT(CompoundTag nbt) {
            this.setOwner(nbt.getUUID("TrueOwner"));
        }

        public static class OwnerProvider implements ICapabilityProvider, ICapabilitySerializable<CompoundTag> {
            private final LazyOptional<ITridentOwner> instance = LazyOptional.of(TridentOwnerCap::new);

            public CompoundTag serializeNBT() {
                return this.instance.orElseThrow(NullPointerException::new).serializeNBT();
            }

            public void deserializeNBT(CompoundTag nbt) {
                this.instance.orElseThrow(NullPointerException::new).deserializeNBT(nbt);
            }

            @Nonnull
            public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, Direction side) {
                return ModCapabilities.TRIDENT_OWNER_CAPABILITY.orEmpty(cap, this.instance.cast());
            }
        }
    }

    public interface ITridentOwner extends INBTSerializable<CompoundTag> {
        void setOwner(UUID owner);

        UUID getOwner();

        boolean canRecall();
    }
}
