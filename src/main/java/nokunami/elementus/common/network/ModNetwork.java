package nokunami.elementus.common.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

import static nokunami.elementus.Elementus.MODID;

// Code from Goety mod
public class ModNetwork {
    public static SimpleChannel INSTANCE;
    private static int id = 0;

    public static int id() {
        return id++;
    }

    public static void setup() {
        INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(MODID, "channel"), () -> "1.0", s -> true, s -> true);
        INSTANCE.registerMessage(id(), AnthektiteChargeBladeSlashPacket.class, AnthektiteChargeBladeSlashPacket::encode, AnthektiteChargeBladeSlashPacket::decode, AnthektiteChargeBladeSlashPacket::consume);
        INSTANCE.registerMessage(id(), ChargeBladeAbilityPacket.class, ChargeBladeAbilityPacket::encode, ChargeBladeAbilityPacket::decode, ChargeBladeAbilityPacket::consume);
        INSTANCE.registerMessage(id(), SteelGolemInventoryPacket.class, SteelGolemInventoryPacket::encode, SteelGolemInventoryPacket::decode, SteelGolemInventoryPacket::consume);
    }

    public static <MSG> void sendTo(Player player, MSG msg) {
        ModNetwork.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) player), msg);
    }

    public static <MSG> void sendToServer(MSG msg) {
        ModNetwork.INSTANCE.sendToServer(msg);
    }

    public static <MSG> void sentToTrackingChunk(LevelChunk chunk, MSG msg) {
        ModNetwork.INSTANCE.send(PacketDistributor.TRACKING_CHUNK.with(() -> chunk), msg);
    }

    public static <MSG> void sentToTrackingEntity(Entity entity, MSG msg) {
        ModNetwork.INSTANCE.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), msg);
    }

    public static <MSG> void sentToTrackingEntityAndPlayer(Entity entity, MSG msg) {
        ModNetwork.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> entity), msg);
    }

    public static <MSG> void sendToALL(MSG msg) {
        ModNetwork.INSTANCE.send(PacketDistributor.ALL.noArg(), msg);
    }

    public static <MSG> void sendToClient(ServerPlayer player, MSG msg) {
        ModNetwork.INSTANCE.sendTo(msg, player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
    }

}
