package net.nokunami.elementus.common.network.server;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraftforge.network.NetworkEvent;
import net.nokunami.elementus.common.item.unique.BladeOfSurgingWinds;

import java.util.function.Supplier;

public class BladeOfSurgingWindSlashC2SPacket {
    protected InteractionHand interactionHand;

    public BladeOfSurgingWindSlashC2SPacket(InteractionHand hand) {
        interactionHand = hand;
    }

    public BladeOfSurgingWindSlashC2SPacket(FriendlyByteBuf buf) {
        interactionHand = buf.readEnum(InteractionHand.class);
    }

    public void toBytes(FriendlyByteBuf buf) { buf.writeEnum(interactionHand); }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();

            if (player != null && !player.isSpectator()) {
                BladeOfSurgingWinds.spawnSlashProjectile(player, interactionHand);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
