package net.nokunami.elementus.common.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraftforge.network.NetworkEvent;
import net.nokunami.elementus.common.item.AnthektiteChargeBlade;

import java.util.function.Supplier;

public class AnthektiteChargeBladeSlashPacket {
    protected InteractionHand hand;

    public AnthektiteChargeBladeSlashPacket(InteractionHand hand) {
        this.hand = hand;
    }

    public static void encode(AnthektiteChargeBladeSlashPacket packet, FriendlyByteBuf buf) {
        buf.writeEnum(packet.hand);
    }

    public static AnthektiteChargeBladeSlashPacket decode(FriendlyByteBuf buf) {
        return new AnthektiteChargeBladeSlashPacket(buf.readEnum(InteractionHand.class));
    }

    public static void consume(AnthektiteChargeBladeSlashPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();

            if (player != null) {
                AnthektiteChargeBlade.spawnSlash(player, packet.hand);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
