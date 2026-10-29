package net.nokunami.elementus.common.network.client.ability;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.nokunami.elementus.client.AbilityDataClient;
import net.nokunami.elementus.common.catalystCore.ability.AbilityStorage;
import net.nokunami.elementus.common.catalystCore.ability.AbilityStorageInstance;

import java.util.List;
import java.util.function.Supplier;

public class AbilityListSyncS2CPacket {
    List<AbilityStorageInstance> abilities;

    public AbilityListSyncS2CPacket(AbilityStorage abilityStorage) { abilities = abilityStorage.getAbilitiesInStorage(); }

    public AbilityListSyncS2CPacket(FriendlyByteBuf buf) { abilities = buf.readList(this::fromNetwork); }
    public void toBytes(FriendlyByteBuf buf) { buf.writeCollection(abilities, this::toNetwork); }

    private AbilityStorageInstance fromNetwork(FriendlyByteBuf buf) { return new AbilityStorageInstance(buf); }
    private void toNetwork(FriendlyByteBuf buf, AbilityStorageInstance instance) { instance.toNetwork(buf); }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            abilities.removeIf(inst -> !inst.addToGUI());
            AbilityDataClient.addAbilities(abilities);
        });
        ctx.get().setPacketHandled(true);
    }
}
