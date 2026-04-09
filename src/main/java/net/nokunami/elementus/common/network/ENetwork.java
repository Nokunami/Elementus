package net.nokunami.elementus.common.network;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import net.nokunami.elementus.common.network.client.*;
import net.nokunami.elementus.common.network.client.ability.SyncCastTickS2CPacket;
import net.nokunami.elementus.common.network.server.*;
import net.nokunami.elementus.common.network.server.ability.CancelCastC2SPacket;
import net.nokunami.elementus.common.network.server.ability.CastStateC2SPacket;
import net.nokunami.elementus.common.network.server.ability.CheckCastTickC2SPacket;

import java.util.Optional;

import static net.nokunami.elementus.Elementus.modLoc;

// Code from Goety mod
public class ENetwork {
    private static SimpleChannel INSTANCE;
    private static int id = 0;

    public static int netId() {
        return id++;
    }

    public static void setup() {
        INSTANCE = NetworkRegistry.newSimpleChannel(modLoc("network"), () -> "1.0", s -> true, s -> true);

        INSTANCE.registerMessage(netId(), TestC2SPacket.class, TestC2SPacket::toBytes, TestC2SPacket::new, TestC2SPacket::handle, Optional.of(NetworkDirection.PLAY_TO_SERVER));
        INSTANCE.registerMessage(netId(), BladeOfSurgingWindSlashC2SPacket.class, BladeOfSurgingWindSlashC2SPacket::toBytes, BladeOfSurgingWindSlashC2SPacket::new, BladeOfSurgingWindSlashC2SPacket::handle, Optional.of(NetworkDirection.PLAY_TO_SERVER));
        INSTANCE.registerMessage(netId(), ChargeBladeAbilityC2SPacket.class, ChargeBladeAbilityC2SPacket::toBytes, ChargeBladeAbilityC2SPacket::new, ChargeBladeAbilityC2SPacket::handle, Optional.of(NetworkDirection.PLAY_TO_SERVER));
        INSTANCE.registerMessage(netId(), GolemInventoryS2CPacket.class, GolemInventoryS2CPacket::toBytes, GolemInventoryS2CPacket::new, GolemInventoryS2CPacket::handle, Optional.of(NetworkDirection.PLAY_TO_CLIENT));
        INSTANCE.registerMessage(netId(), CatalystItemDisplayS2CPacket.class, CatalystItemDisplayS2CPacket::toBytes, CatalystItemDisplayS2CPacket::new, CatalystItemDisplayS2CPacket::handle, Optional.of(NetworkDirection.PLAY_TO_CLIENT));
        INSTANCE.registerMessage(netId(), CatalystDeathParticleS2CPacket.class, CatalystDeathParticleS2CPacket::toBytes, CatalystDeathParticleS2CPacket::new, CatalystDeathParticleS2CPacket::handle, Optional.of(NetworkDirection.PLAY_TO_CLIENT));
        INSTANCE.registerMessage(netId(), CastStateC2SPacket.class, CastStateC2SPacket::toBytes, CastStateC2SPacket::new, CastStateC2SPacket::handle, Optional.of(NetworkDirection.PLAY_TO_SERVER));
        INSTANCE.registerMessage(netId(), CatalystSlotC2SPacket.class, CatalystSlotC2SPacket::toBytes, CatalystSlotC2SPacket::new, CatalystSlotC2SPacket::handle, Optional.of(NetworkDirection.PLAY_TO_SERVER));
        INSTANCE.registerMessage(netId(), ItemNotifierDingC2SPacket.class, ItemNotifierDingC2SPacket::toBytes, ItemNotifierDingC2SPacket::new, ItemNotifierDingC2SPacket::handle, Optional.of(NetworkDirection.PLAY_TO_SERVER));
        INSTANCE.registerMessage(netId(), ItemNotifierResetC2SPacket.class, ItemNotifierResetC2SPacket::toBytes, ItemNotifierResetC2SPacket::new, ItemNotifierResetC2SPacket::handle, Optional.of(NetworkDirection.PLAY_TO_SERVER));
        INSTANCE.registerMessage(netId(), CACooldownsSyncS2CPacket.class, CACooldownsSyncS2CPacket::toBytes, CACooldownsSyncS2CPacket::new, CACooldownsSyncS2CPacket::handle, Optional.of(NetworkDirection.PLAY_TO_CLIENT));
        INSTANCE.registerMessage(netId(), CACooldownSyncS2CPacket.class, CACooldownSyncS2CPacket::toBytes, CACooldownSyncS2CPacket::new, CACooldownSyncS2CPacket::handle, Optional.of(NetworkDirection.PLAY_TO_CLIENT));
        INSTANCE.registerMessage(netId(), CatalystAbilitySelectionPacket.class, CatalystAbilitySelectionPacket::toBytes, CatalystAbilitySelectionPacket::new, CatalystAbilitySelectionPacket::handle, Optional.of(NetworkDirection.PLAY_TO_SERVER));
        INSTANCE.registerMessage(netId(), CancelCastC2SPacket.class, CancelCastC2SPacket::toBytes, CancelCastC2SPacket::new, CancelCastC2SPacket::handle, Optional.of(NetworkDirection.PLAY_TO_SERVER));
        INSTANCE.registerMessage(netId(), SyncAbilitySelectionS2CPacket.class, SyncAbilitySelectionS2CPacket::toBytes, SyncAbilitySelectionS2CPacket::new, SyncAbilitySelectionS2CPacket::handle, Optional.of(NetworkDirection.PLAY_TO_CLIENT));
        INSTANCE.registerMessage(netId(), SyncItemNotifierS2CPacket.class, SyncItemNotifierS2CPacket::toBytes, SyncItemNotifierS2CPacket::new, SyncItemNotifierS2CPacket::handle, Optional.of(NetworkDirection.PLAY_TO_CLIENT));
        INSTANCE.registerMessage(netId(), ClientDingItemNotifierS2CPacket.class, ClientDingItemNotifierS2CPacket::toBytes, ClientDingItemNotifierS2CPacket::new, ClientDingItemNotifierS2CPacket::handle, Optional.of(NetworkDirection.PLAY_TO_CLIENT));
        INSTANCE.registerMessage(netId(), SyncCastStateS2CPacket.class, SyncCastStateS2CPacket::toBytes, SyncCastStateS2CPacket::new, SyncCastStateS2CPacket::handle, Optional.of(NetworkDirection.PLAY_TO_CLIENT));
        INSTANCE.registerMessage(netId(), CheckCastTickC2SPacket.class, CheckCastTickC2SPacket::toBytes, CheckCastTickC2SPacket::new, CheckCastTickC2SPacket::handle, Optional.of(NetworkDirection.PLAY_TO_SERVER));
        INSTANCE.registerMessage(netId(), SyncCastTickS2CPacket.class, SyncCastTickS2CPacket::toBytes, SyncCastTickS2CPacket::new, SyncCastTickS2CPacket::handle, Optional.of(NetworkDirection.PLAY_TO_CLIENT));
    }

    public static <MSG> void sendTo(ServerPlayer player, MSG msg) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), msg);
    }

    public static <MSG> void sendToServer(MSG msg) {
        INSTANCE.sendToServer(msg);
    }

//    public static <MSG> void sentToTrackingChunk(LevelChunk chunk, MSG msg) {
//        ENetwork.INSTANCE.send(PacketDistributor.TRACKING_CHUNK.with(() -> chunk), msg);
//    }

    public static <MSG> void sentToTrackingEntity(Entity entity, MSG msg) {
        ENetwork.INSTANCE.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), msg);
    }

    public static <MSG> void sentToTrackingAndPlayer(Entity entity, MSG msg) {
        ENetwork.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> entity), msg);
    }

    public static <MSG> void sendToALL(MSG msg) {
        ENetwork.INSTANCE.send(PacketDistributor.ALL.noArg(), msg);
    }

//    public static <MSG> void sendToClient(ServerPlayer player, MSG msg) {
//        ENetwork.INSTANCE.sendTo(msg, player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
//    }
}
