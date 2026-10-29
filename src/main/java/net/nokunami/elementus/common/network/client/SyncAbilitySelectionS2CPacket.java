package net.nokunami.elementus.common.network.client;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.nokunami.elementus.client.AbilityDataClient;
import net.nokunami.elementus.common.capability.abilityData.AbilityData;

import java.util.function.Supplier;

public class SyncAbilitySelectionS2CPacket {
    private final int prev;
    private final int selection;

    public SyncAbilitySelectionS2CPacket(AbilityData data) {
        prev = data.getPrevSelection();
        selection = data.getSelection();
    }

    public SyncAbilitySelectionS2CPacket(FriendlyByteBuf buf) {
        prev = buf.readInt();
        selection = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(prev);
        buf.writeInt(selection);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> AbilityDataClient.scroll(prev, selection));
        ctx.get().setPacketHandled(true);
    }
}