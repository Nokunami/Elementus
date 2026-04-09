package net.nokunami.elementus.common.network.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkEvent;
import net.nokunami.elementus.client.gui.screens.inventory.AstaliteGolemInventoryScreen;
import net.nokunami.elementus.client.gui.screens.inventory.SteelGolemInventoryScreen;
import net.nokunami.elementus.common.entity.living.AstaliteGolem;
import net.nokunami.elementus.common.entity.living.SteelGolem;
import net.nokunami.elementus.common.inventory.AstaliteGolemInventoryMenu;
import net.nokunami.elementus.common.inventory.SteelGolemInventoryMenu;

import java.util.function.Supplier;

// Code from Cataclysm Netherite Ministrosity
public class GolemInventoryS2CPacket {
    int id;
    int size;
    int entityId;

    public GolemInventoryS2CPacket(int id, int size, int entityId) {
        this.id = id;
        this.size = size;
        this.entityId = entityId;
    }

    public GolemInventoryS2CPacket(FriendlyByteBuf buf) {
        id = buf.readUnsignedByte();
        size = buf.readVarInt();
        entityId = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeByte(id);
        buf.writeVarInt(size);
        buf.writeInt(entityId);
    }

    public boolean handle(Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> openInventory(id, entityId));
        return true;
    }

    @OnlyIn(Dist.CLIENT)
    public static void openInventory(int id, int entityId) {
        Player player = Minecraft.getInstance().player;
        if (player != null) {
            Entity entity = player.level().getEntity(entityId);
            if (entity instanceof SteelGolem steelGolem) {
                LocalPlayer localPlayer = Minecraft.getInstance().player;
                SteelGolemInventoryMenu container = new SteelGolemInventoryMenu(id, player.getInventory(), steelGolem.inventory, steelGolem);
                localPlayer.containerMenu = container;
                Minecraft.getInstance().setScreen(new SteelGolemInventoryScreen(container, player.getInventory(), steelGolem));
            }
            if (entity instanceof AstaliteGolem steelGolem) {
                LocalPlayer localPlayer = Minecraft.getInstance().player;
                AstaliteGolemInventoryMenu container = new AstaliteGolemInventoryMenu(id, player.getInventory(), steelGolem.inventory, steelGolem);
                localPlayer.containerMenu = container;
                Minecraft.getInstance().setScreen(new AstaliteGolemInventoryScreen(container, player.getInventory(), steelGolem));
            }
        }
    }
}
