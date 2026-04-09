package net.nokunami.elementus.common.network.server;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;
import net.nokunami.elementus.client.gui.overlay.CatalystAbilityOverlay;

import java.util.function.Supplier;

public class CatalystCoreSyncPacket {
    ItemStack stack;

//    public static void encode(CatalystCoreSyncPacket packet, FriendlyByteBuf buf) {
//        buf.writeItem(packet.stack);
//    }
//
//    public static CatalystCoreSyncPacket decode(FriendlyByteBuf buf) {
//        return new CatalystCoreSyncPacket(buf.readItem());
//    }
//
//    public static void consume(CatalystCoreSyncPacket packet, Supplier<NetworkEvent.Context> ctx) {
//        ctx.get().enqueueWork(() -> CatalystActiveAbilityOverlay.SyncCoreToGui.setStack(packet.stack));
//        ctx.get().setPacketHandled(true);
//    }
    public CatalystCoreSyncPacket(ItemStack itemStack) { stack = itemStack; }

    public CatalystCoreSyncPacket(FriendlyByteBuf buf) { stack = buf.readItem(); }

    public void toBytes(FriendlyByteBuf buf) { buf.writeItem(stack); }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> CatalystAbilityOverlay.SyncCoreToGui.setStack(stack));
        return true;
    }
}