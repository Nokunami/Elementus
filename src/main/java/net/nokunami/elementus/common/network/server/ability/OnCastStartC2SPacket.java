package net.nokunami.elementus.common.network.server.ability;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.nokunami.elementus.common.catalystCore.ability.AbilityManager;

import java.util.function.Supplier;

public class OnCastStartC2SPacket {

    public OnCastStartC2SPacket() { }

    public OnCastStartC2SPacket(FriendlyByteBuf buf) { }
    public void toBytes(FriendlyByteBuf buf) { }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player != null && !player.isSpectator()) AbilityManager.onKeyPress(player);
        });
        ctx.get().setPacketHandled(true);
    }
}