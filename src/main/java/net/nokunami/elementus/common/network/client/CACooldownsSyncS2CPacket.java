package net.nokunami.elementus.common.network.client;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.nokunami.elementus.client.CAbilityClient;
import net.nokunami.elementus.common.capability.cAbility.CACooldown;

import java.util.Map;
import java.util.function.Supplier;

public class CACooldownsSyncS2CPacket {
    private final Map<String, CACooldown> map;

    public static String readID(FriendlyByteBuf buffer) { return buffer.readUtf(); }
    public static void writeId(FriendlyByteBuf buf, String id) { buf.writeUtf(id); }

    public static CACooldown readCoolDownInstance(FriendlyByteBuf buffer) {
        int cooldown = buffer.readInt();
        int cooldownTicks = buffer.readInt();
        int chargeAmount = buffer.readInt();
        return new CACooldown(cooldown, cooldownTicks, chargeAmount);
    }
    public static void writeCoolDownInstance(FriendlyByteBuf buf, CACooldown cooldown) {
        buf.writeInt(cooldown.getCooldown());
        buf.writeInt(cooldown.getTicks());
        buf.writeInt(cooldown.getChargeAmount());
    }

    public CACooldownsSyncS2CPacket(Map<String, CACooldown> cooldownMap) { map = cooldownMap; }

    public CACooldownsSyncS2CPacket(FriendlyByteBuf buf) {
        map = buf.readMap(CACooldownsSyncS2CPacket::readID, CACooldownsSyncS2CPacket::readCoolDownInstance);
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeMap(map, CACooldownsSyncS2CPacket::writeId, CACooldownsSyncS2CPacket::writeCoolDownInstance);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context ctx = supplier.get();
        ctx.enqueueWork(() -> {
            var cooldowns = CAbilityClient.getCooldowns();
            cooldowns.clearCooldowns();
            map.forEach((k, v) -> cooldowns.addCooldown(k, v.getCooldown(), v.getChargeAmount()));
        });
        return true;
    }
}
