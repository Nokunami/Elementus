package net.nokunami.elementus.common.network.server.ability;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.nokunami.elementus.common.catalystCore.ability.AbilityManager;

import java.util.function.Supplier;

public class AbilityListSyncC2SPacket {

    public AbilityListSyncC2SPacket() { }

    public AbilityListSyncC2SPacket(FriendlyByteBuf buf) { }
    public void toBytes(FriendlyByteBuf buf) { }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            var player = ctx.get().getSender();
            if (player != null) AbilityManager.sync(player, player.level());
        });
        ctx.get().setPacketHandled(true);
    }
}
