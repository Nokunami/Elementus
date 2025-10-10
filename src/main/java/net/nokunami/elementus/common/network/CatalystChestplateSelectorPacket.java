package net.nokunami.elementus.common.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class CatalystChestplateSelectorPacket {
    private int scroll;

    public CatalystChestplateSelectorPacket(int scroll) {
        this.scroll = scroll;
    }

    public static void encode(CatalystChestplateSelectorPacket packet, FriendlyByteBuf buf) {
        buf.writeInt(packet.scroll);
    }

    public static CatalystChestplateSelectorPacket decode(FriendlyByteBuf buf) {
        return new CatalystChestplateSelectorPacket(buf.readInt());
    }

    public static void consume(final CatalystChestplateSelectorPacket packet, Supplier<NetworkEvent.Context> context) {
//        contex
    }
}
