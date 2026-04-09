package net.nokunami.elementus.common.network.client;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.nokunami.elementus.client.ItemNotifierClient;
import net.nokunami.elementus.common.capability.itemNotifier.ItemNotifier;

import java.util.function.Supplier;

public class SyncItemNotifierS2CPacket {
    int ding;

    public SyncItemNotifierS2CPacket(int i) { ding = i; }

    public SyncItemNotifierS2CPacket(FriendlyByteBuf buf) { ding = buf.readInt(); }

    public void toBytes(FriendlyByteBuf buf) { buf.writeInt(ding); }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> ItemNotifierClient.sync(ding));
        ctx.get().setPacketHandled(true);
    }
}
