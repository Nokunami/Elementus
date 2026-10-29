package net.nokunami.elementus.common.network.client;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.nokunami.elementus.client.AbilityDataClient;
import net.nokunami.elementus.common.capability.abilityData.AbilityCooldown;

import java.util.Map;
import java.util.function.Supplier;

public class AbilityCooldownsSyncS2CPacket {
    private final Map<String, AbilityCooldown> map;

    public AbilityCooldownsSyncS2CPacket(Map<String, AbilityCooldown> cooldownMap) { map = cooldownMap; }

    public AbilityCooldownsSyncS2CPacket(FriendlyByteBuf buf) { map = buf.readMap(this::readID, this::readCoolDownInstance); }
    public void toBytes(FriendlyByteBuf buf) { buf.writeMap(map, this::writeId, this::writeCoolDownInstance); }

    public String readID(FriendlyByteBuf buffer) { return buffer.readUtf(); }
    public void writeId(FriendlyByteBuf buf, String id) { buf.writeUtf(id); }

    public AbilityCooldown readCoolDownInstance(FriendlyByteBuf buffer) {
        int cooldown = buffer.readInt();
        int cooldownTicks = buffer.readInt();
        int chargeAmount = buffer.readInt();
        return new AbilityCooldown(cooldown, cooldownTicks, chargeAmount);
    }
    public void writeCoolDownInstance(FriendlyByteBuf buf, AbilityCooldown cooldown) {
        buf.writeInt(cooldown.getTotalTicks());
        buf.writeInt(cooldown.getTicks());
        buf.writeInt(cooldown.getChargeAmount());
    }

    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context ctx = supplier.get();
        ctx.enqueueWork(() -> {
            var cooldowns = AbilityDataClient.getCooldowns();
            cooldowns.clearCooldowns();
            map.forEach((id, data) -> cooldowns.addFreshCooldown(id, data.getTotalTicks(), data.getTicks(), data.getChargeAmount()));
        });
        ctx.setPacketHandled(true);
    }
}
