package net.nokunami.elementus.common.network.server;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class TestC2SPacket {
    public TestC2SPacket() { }

    public TestC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
//            if (player != null) player.displayClientMessage(Component.literal("testC2Spacket"), true);
            if (player != null) {
                Level level = player.level();
                EntityType.COW.spawn((ServerLevel) level, (ItemStack) null, null, player.blockPosition(), MobSpawnType.COMMAND, true, false);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
