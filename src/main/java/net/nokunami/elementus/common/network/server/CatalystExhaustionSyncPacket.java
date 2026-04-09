package net.nokunami.elementus.common.network.server;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.nokunami.elementus.client.gui.overlay.CatalystExhaustionClient;

import java.util.function.Supplier;

public record CatalystExhaustionSyncPacket(int exhaustion) {

    public static void encode(CatalystExhaustionSyncPacket packet, FriendlyByteBuf buf) {
        buf.writeInt(packet.exhaustion);
    }

    public static CatalystExhaustionSyncPacket decode(FriendlyByteBuf buf) {
        return new CatalystExhaustionSyncPacket(buf.readInt());
    }

    public static void consume(CatalystExhaustionSyncPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> CatalystExhaustionClient.setExhaustion(packet.exhaustion));
        ctx.get().setPacketHandled(true);
    }
}
