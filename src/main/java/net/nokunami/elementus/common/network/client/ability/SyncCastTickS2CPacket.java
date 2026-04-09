package net.nokunami.elementus.common.network.client.ability;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.nokunami.elementus.client.CAbilityClient;

import java.util.function.Supplier;

public class SyncCastTickS2CPacket {
    int tick;

    public SyncCastTickS2CPacket(int i) { tick = i; }
    public SyncCastTickS2CPacket(FriendlyByteBuf buf) { tick = buf.readInt(); }
    public void toBytes(FriendlyByteBuf buf) { buf.writeInt(tick); }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            if (tick != CAbilityClient.getCastTick()) {
                CAbilityClient.setClientCastTick(tick);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
