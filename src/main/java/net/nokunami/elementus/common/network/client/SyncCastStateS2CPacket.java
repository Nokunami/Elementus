package net.nokunami.elementus.common.network.client;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.nokunami.elementus.client.CAbilityClient;

import java.util.function.Supplier;

public class SyncCastStateS2CPacket {
    boolean key;
    int castDuration;

    public SyncCastStateS2CPacket(boolean b, int i) { key = b; castDuration = i; }
    public SyncCastStateS2CPacket(FriendlyByteBuf buf) { key = buf.readBoolean(); castDuration = buf.readInt(); }
    public void toBytes(FriendlyByteBuf buf) { buf.writeBoolean(key); buf.writeInt(castDuration); }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> CAbilityClient.updateCastState(key, castDuration));
        ctx.get().setPacketHandled(true);
    }
}
