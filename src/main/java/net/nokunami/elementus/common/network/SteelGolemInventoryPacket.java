package net.nokunami.elementus.common.network;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkEvent;
import net.nokunami.elementus.client.gui.screens.inventory.SteelGolemInventoryScreen;
import net.nokunami.elementus.common.entity.living.AstaliteGolem;
import net.nokunami.elementus.common.inventory.AstaliteGolemInventoryMenu;

import java.util.function.Supplier;

// Code from Cataclysm Netherite Ministrosity
public record SteelGolemInventoryPacket(int id, int size, int entityId) {

    public static SteelGolemInventoryPacket decode(FriendlyByteBuf buf) {
        return new SteelGolemInventoryPacket(buf.readUnsignedByte(), buf.readVarInt(), buf.readInt());
    }

    public static void encode(SteelGolemInventoryPacket message, FriendlyByteBuf buf) {
        buf.writeByte(message.id);
        buf.writeVarInt(message.size);
        buf.writeInt(message.entityId);
    }

    public static void consume(SteelGolemInventoryPacket msg, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> openInventory(msg));
        context.get().setPacketHandled(true);
    }


    @OnlyIn(Dist.CLIENT)
    public static void openInventory(SteelGolemInventoryPacket packet) {
        Player player = Minecraft.getInstance().player;
        if (player != null) {
            Entity entity = player.level().getEntity(packet.entityId());
            if (entity instanceof AstaliteGolem steelGolem) {
                LocalPlayer localPlayer = Minecraft.getInstance().player;
                AstaliteGolemInventoryMenu container = new AstaliteGolemInventoryMenu(packet.id(), player.getInventory(), steelGolem.inventory, steelGolem);
                localPlayer.containerMenu = container;
                Minecraft.getInstance().setScreen(new SteelGolemInventoryScreen(container, player.getInventory(), steelGolem));
            }
        }
    }
}
