package net.nokunami.elementus.common.network;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraftforge.network.NetworkEvent;
import net.nokunami.elementus.common.item.unique.AnthektiteChargeBlade;

import java.util.function.Supplier;

public class CatalystItemsSelectionPacket {

    protected int syncId;
    protected int revision;
    protected int i;
    protected int amount;

    public CatalystItemsSelectionPacket(int syncId, int revision, int i, int amount) {
        this.syncId = syncId;
        this.revision = revision;
        this.i = i;
        this.amount = amount;
    }

    public static void encode(CatalystItemsSelectionPacket packet, FriendlyByteBuf buf) {
        buf.writeInt(packet.syncId);
        buf.writeInt(packet.revision);
        buf.writeInt(packet.i);
        buf.writeInt(packet.amount);
    }

    public static CatalystItemsSelectionPacket decode(FriendlyByteBuf buf) {
        return new CatalystItemsSelectionPacket(buf.readInt(), buf.readInt(), buf.readInt(), buf.readInt());
    }

    public static void consume(CatalystItemsSelectionPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            Minecraft minecraft = Minecraft.getInstance();
//            Screen screen = minecraft.player.;

        });
        ctx.get().setPacketHandled(true);
    }
}