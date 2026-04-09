package net.nokunami.elementus.common.capability.catalystQuickSwap;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static net.nokunami.elementus.Elementus.modLoc;

public class CatalystQuickSwapCap implements ICapabilityProvider, ICapabilitySerializable<CompoundTag> {
    public static ResourceLocation ID = modLoc("catalyst_ability");
    public static Capability<CatalystQuickSwap> CAP = CapabilityManager.get(new CapabilityToken<>() { });
    private CatalystQuickSwap quickSwap = null;
    private final LazyOptional<CatalystQuickSwap> opt = LazyOptional.of(this::create);
    private final Player livingEntity;

    public CatalystQuickSwapCap(Player living) {
        livingEntity = living;
    }

    private CatalystQuickSwap create() {
        return quickSwap == null ? quickSwap = new CatalystQuickSwap(livingEntity) : quickSwap;
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
        if (event.getObject() instanceof Player entity) {
            if (!event.getObject().getCapability(CatalystQuickSwapCap.CAP).isPresent()) {
                event.addCapability(ID, new CatalystQuickSwapCap(entity));
            }
        }
    }
}
