package net.nokunami.elementus.common.network.server;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.nokunami.elementus.common.capability.itemNotifier.ItemNotifier;
import net.nokunami.elementus.common.capability.itemNotifier.ItemNotifierCap;
import net.nokunami.elementus.common.network.ENetwork;
import net.nokunami.elementus.common.network.client.SyncItemNotifierS2CPacket;

import java.util.function.Supplier;

public class ItemNotifierResetC2SPacket {

    public ItemNotifierResetC2SPacket() { }

    public ItemNotifierResetC2SPacket(FriendlyByteBuf buf) { }

    public void toBytes(FriendlyByteBuf buf) { }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player != null) {
                var inst = ItemNotifier.instance(player);
                inst.setDing(0);
                ENetwork.sendTo(player, new SyncItemNotifierS2CPacket(inst.getDing()));
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
