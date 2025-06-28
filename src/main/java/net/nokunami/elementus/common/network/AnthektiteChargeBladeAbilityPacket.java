package net.nokunami.elementus.common.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.nokunami.elementus.common.item.AnthektiteChargeBlade;

import java.util.function.Supplier;

public class AnthektiteChargeBladeAbilityPacket {

    public static void encode(AnthektiteChargeBladeAbilityPacket packet, FriendlyByteBuf buffer) {
    }

    public static AnthektiteChargeBladeAbilityPacket decode(FriendlyByteBuf buffer) {
        return new AnthektiteChargeBladeAbilityPacket();
    }

    public static void consume(AnthektiteChargeBladeAbilityPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();

            if (player != null) {
//                AnthektiteChargeBlade.spawnSlash(player);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
