package nokunami.elementus.common.network;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkEvent;
import nokunami.elementus.client.SteelGolemInventoryScreen;
import nokunami.elementus.common.entity.living.SteelGolem;
import nokunami.elementus.common.inventory.SteelGolemInventoryMenu;

import java.util.function.Supplier;

// Code from Cataclysm Netherite Ministrosity
public class SteelGolemInventoryPacket {
    private final int id;
    private final int size;
    private final int entityId;

    public SteelGolemInventoryPacket(int id, int size, int entityId) {
        this.id = id;
        this.size = size;
        this.entityId = entityId;
    }

    public static SteelGolemInventoryPacket decode(FriendlyByteBuf buf) {
        return new SteelGolemInventoryPacket(buf.readUnsignedByte(), buf.readVarInt(), buf.readInt());
    }

    public static void encode(SteelGolemInventoryPacket message, FriendlyByteBuf buf) {
        buf.writeByte(message.id);
        buf.writeVarInt(message.size);
        buf.writeInt(message.entityId);
    }


    public int getId() {
        return this.id;
    }

    public int getSize() {
        return this.size;
    }

    public int getEntityId() {
        return this.entityId;
    }

    public static void consume(SteelGolemInventoryPacket msg, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> openInventory(msg));
        context.get().setPacketHandled(true);
    }


    @OnlyIn(Dist.CLIENT)
    public static void openInventory(SteelGolemInventoryPacket packet) {
        Player player = Minecraft.getInstance().player;
        if (player != null) {
            Entity entity = player.level().getEntity(packet.getEntityId());
            if (entity instanceof SteelGolem steelGolem) {
                LocalPlayer localPlayer = Minecraft.getInstance().player;
                SteelGolemInventoryMenu container = new SteelGolemInventoryMenu(packet.getId(), player.getInventory(), steelGolem.inventory, steelGolem);
                localPlayer.containerMenu = container;
                Minecraft.getInstance().setScreen(new SteelGolemInventoryScreen(container, player.getInventory(), steelGolem));
            }
        }
    }
}
