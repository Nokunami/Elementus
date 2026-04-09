package net.nokunami.elementus.common.network.client;

import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class CatalystDeathParticleS2CPacket {
    protected int type;
    protected int ticks;
    protected int entityId;
    protected boolean isPlayer;

    public CatalystDeathParticleS2CPacket(int type, int ticks, int entityId, boolean isPlayer) {
        this.type = type;
        this.ticks = ticks;
        this.entityId = entityId;
        this.isPlayer = isPlayer;
    }

    public CatalystDeathParticleS2CPacket(FriendlyByteBuf buf) {
        type = buf.readUnsignedByte();
        ticks = buf.readVarInt();
        entityId = buf.readInt();
        isPlayer = buf.readBoolean();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeByte(type);
        buf.writeVarInt(ticks);
        buf.writeInt(entityId);
        buf.writeBoolean(isPlayer);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            Player player = ctx.get().getSender();
            if (player != null) {
                Level level = player.level();
                Entity entity = level.getEntity(entityId);
                if (entity != null) level.broadcastEntityEvent(entity, (byte) 35);
//                sendTrackingParticle(entity);
            }
        });
        return true;
    }

    @OnlyIn(Dist.CLIENT)
    public void sendTrackingParticle(Entity entity) {
        Player player = Minecraft.getInstance().player;
        if (player != null && entity != null) {
//            Entity entity = player;
//            Entity entityId = player.level().getEntity(packet.entityId);
//            boolean shouldParticle = false;
//            if (entityId != null && !packet.isPlayer) {
//                entity = entityId;
//                shouldParticle = true;
//            } else if (packet.isPlayer) {
//                shouldParticle = true;
//            }
//            if (shouldParticle) {
//                if (packet.type == 0) {
//                    Minecraft.getInstance().particleEngine.createTrackingEmitter(entity, ParticleTypes.TOTEM_OF_UNDYING, packet.ticks);
//                }
//            }
            Minecraft.getInstance().particleEngine.createTrackingEmitter(entity, ParticleTypes.TOTEM_OF_UNDYING, ticks);
        }
    }
}
