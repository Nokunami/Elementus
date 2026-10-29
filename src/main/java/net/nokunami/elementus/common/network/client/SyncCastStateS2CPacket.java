package net.nokunami.elementus.common.network.client;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.nokunami.elementus.client.AbilityDataClient;
import net.nokunami.elementus.common.capability.abilityData.AbilityData;

import java.util.function.Supplier;

public class SyncCastStateS2CPacket {
    boolean key;
    int castDuration;
    int preCastDuration;

    public SyncCastStateS2CPacket(AbilityData data) {
        key = data.isCastKeyDown();
        preCastDuration = data.getPreCastDuration();
        castDuration = data.getCastDuration();
    }

    public SyncCastStateS2CPacket(FriendlyByteBuf buf) {
        key = buf.readBoolean();
        preCastDuration = buf.readInt();
        castDuration = buf.readInt();
    }
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBoolean(key);
        buf.writeInt(preCastDuration);
        buf.writeInt(castDuration);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> AbilityDataClient.updateCastState(key, preCastDuration, castDuration));
        ctx.get().setPacketHandled(true);
    }
}
