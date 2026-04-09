package net.nokunami.elementus.common.network.client;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.nokunami.elementus.common.capability.cAbility.CAbility;

import java.util.function.Supplier;

public class CAClientSyncS2cPacket {
    CAbility ability;

    public CAClientSyncS2cPacket(CAbility ca) { ability = ca; }
    public CAClientSyncS2cPacket(FriendlyByteBuf buf) { }
    public void toBytes(FriendlyByteBuf buf) { }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
        });
        ctx.get().setPacketHandled(true);
    }
}
