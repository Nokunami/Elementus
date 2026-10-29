package net.nokunami.elementus.common.network.server.ability;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.nokunami.elementus.common.capability.abilityData.AbilityData;
import net.nokunami.elementus.common.network.ENet;
import net.nokunami.elementus.common.network.client.ability.SyncCastTickS2CPacket;

import java.util.function.Supplier;

public class CheckCastTickC2SPacket {
    int tick;

    public CheckCastTickC2SPacket(int i) { tick = i; }
    public CheckCastTickC2SPacket(FriendlyByteBuf buf) { tick = buf.readInt(); }
    public void toBytes(FriendlyByteBuf buf) { buf.writeInt(tick); }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            var player = ctx.get().getSender();
            if (player != null) {
                var ca = AbilityData.instance(player);
                if (ca.getCastTick() != tick) {
                    ENet.sendTo(player, new SyncCastTickS2CPacket(ca.getCastTick()));
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
