package net.nokunami.elementus.common.network.server;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.nokunami.elementus.common.capability.itemNotifier.ItemNotifier;
import net.nokunami.elementus.common.capability.itemNotifier.ItemNotifierCap;
import net.nokunami.elementus.common.network.ENetwork;
import net.nokunami.elementus.common.network.client.ClientDingItemNotifierS2CPacket;
import net.nokunami.elementus.common.network.client.SyncItemNotifierS2CPacket;

import java.util.function.Supplier;

public class ItemNotifierDingC2SPacket {
    float f;

    public ItemNotifierDingC2SPacket(float f) { this.f = f; }

    public ItemNotifierDingC2SPacket(FriendlyByteBuf buf) { f = buf.readFloat(); }

    public void toBytes(FriendlyByteBuf buf) { buf.writeFloat(f); }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player != null) {
                var inst = ItemNotifier.instance(player);
                inst.setDing(1);
                ENetwork.sendTo(player, new ClientDingItemNotifierS2CPacket(f));
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
