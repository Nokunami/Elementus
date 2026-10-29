package net.nokunami.elementus.common.capability.slashStack;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static net.nokunami.elementus.Elementus.modLoc;

public class SlashStackCap implements ICapabilityProvider, ICapabilitySerializable<CompoundTag> {
    public static ResourceLocation ID = modLoc("slash_stacks");
    public static Capability<SlashStackData> CAP = CapabilityManager.get(new CapabilityToken<>() { });
    private SlashStackData stackData = null;
    private final LazyOptional<SlashStackData> opt = LazyOptional.of(this::create);
    private final LivingEntity livingEntity;

    public SlashStackCap(LivingEntity living) {
        livingEntity = living;
    }

    private SlashStackData create() {
        return stackData == null ? stackData = new SlashStackData(livingEntity) : stackData;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        return cap == CAP ? opt.cast() : LazyOptional.empty();
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return getCapability(cap);
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        create().save(tag);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) { create().load(nbt); }

    public static void addCap(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof LivingEntity entity) {
            if (!event.getObject().getCapability(CAP).isPresent()) {
                event.addCapability(ID, new SlashStackCap(entity));
            }
        }
    }
}
