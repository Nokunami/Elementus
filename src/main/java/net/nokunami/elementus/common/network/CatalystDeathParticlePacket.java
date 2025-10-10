package net.nokunami.elementus.common.network;

import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class CatalystDeathParticlePacket {
    protected int type;

    public CatalystDeathParticlePacket(int type) {
        this.type = type;
    }

    public static void encode(CatalystDeathParticlePacket packet, FriendlyByteBuf buf) {
        buf.writeInt(packet.type);
    }

    public static CatalystDeathParticlePacket decode(FriendlyByteBuf buf) {
        return new CatalystDeathParticlePacket(buf.readInt());
    }

    public static void consume(CatalystDeathParticlePacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player != null) {
                if (player.level().isClientSide) {
                    if (packet.type == 0)
                        Minecraft.getInstance().particleEngine.createTrackingEmitter(player, ParticleTypes.TOTEM_OF_UNDYING);
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
