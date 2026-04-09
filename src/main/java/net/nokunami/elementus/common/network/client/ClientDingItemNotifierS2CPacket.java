package net.nokunami.elementus.common.network.client;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.nokunami.elementus.client.ItemNotifierClient;

import java.util.function.Supplier;

public class ClientDingItemNotifierS2CPacket {
    float r;

    public ClientDingItemNotifierS2CPacket(float f) { r = f; }

    public ClientDingItemNotifierS2CPacket(FriendlyByteBuf buf) { r = buf.readFloat(); }

    public void toBytes(FriendlyByteBuf buf) { buf.writeFloat(r); }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            var player = ctx.get().getSender();
            if (player != null) {
                ItemNotifierClient.ding(player, r);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
