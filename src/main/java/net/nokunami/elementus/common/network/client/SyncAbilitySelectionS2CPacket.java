package net.nokunami.elementus.common.network.client;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.nokunami.elementus.client.CAbilityClient;

import java.util.function.Supplier;

public class SyncAbilitySelectionS2CPacket {
    private final int selection;

    public SyncAbilitySelectionS2CPacket(int selection) { this.selection = selection; }

    public SyncAbilitySelectionS2CPacket(FriendlyByteBuf buf) { selection = buf.readInt(); }

    public void toBytes(FriendlyByteBuf buf) { buf.writeInt(selection); }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> CAbilityClient.setSelected(selection));
        return true;
    }
}