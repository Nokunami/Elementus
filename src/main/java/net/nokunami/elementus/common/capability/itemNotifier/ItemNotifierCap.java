package net.nokunami.elementus.common.capability.itemNotifier;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static net.nokunami.elementus.Elementus.modLoc;

public class ItemNotifierCap implements ICapabilityProvider, ICapabilitySerializable<CompoundTag> {
    public static ResourceLocation ID = modLoc("item_notifier");
    public static Capability<ItemNotifier> CAP = CapabilityManager.get(new CapabilityToken<>() { });
    private ItemNotifier itemNotifier = null;
    private final LazyOptional<ItemNotifier> opt = LazyOptional.of(this::create);

    private ItemNotifier create() {
        return itemNotifier == null ? itemNotifier = new ItemNotifier() : itemNotifier;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return cap == CAP ? opt.cast() : LazyOptional.empty();
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

    public static void addCap(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            if (!event.getObject().getCapability(ItemNotifierCap.CAP).isPresent()) {
                event.addCapability(ID, new ItemNotifierCap());
            }
        }
    }
}
