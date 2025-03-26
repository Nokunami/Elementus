package net.nokunami.elementus.common.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.nokunami.elementus.common.item.AnthektiteChargeBlade;

import java.util.function.Supplier;

public class AnthektiteSlashPacket {
    public static void encode(AnthektiteSlashPacket packet, FriendlyByteBuf buffer) {
    }

    public static AnthektiteSlashPacket decode(FriendlyByteBuf buffer) {
        return new AnthektiteSlashPacket();
    }

    public static void consume(AnthektiteSlashPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer playerEntity = ctx.get().getSender();

            if (playerEntity != null) {
                AnthektiteChargeBlade.spawnSlash(playerEntity);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
