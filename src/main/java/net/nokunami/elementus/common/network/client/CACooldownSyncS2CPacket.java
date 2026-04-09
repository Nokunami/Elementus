package net.nokunami.elementus.common.network.client;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.nokunami.elementus.client.CAbilityClient;
import net.nokunami.elementus.common.capability.cAbility.CACooldown;

import java.util.Map;
import java.util.function.Supplier;

public class CACooldownSyncS2CPacket {
    private final String id;
    private final int cooldown;
//    private final int charge;
    private final int chargeAmount;

    public CACooldownSyncS2CPacket(String abilityId, int abilityCooldown, int abilityChargeAmount) {
        id = abilityId;
        cooldown = abilityCooldown;
//        charge = abilityCharge;
        chargeAmount = abilityChargeAmount;
    }

    public CACooldownSyncS2CPacket(FriendlyByteBuf buf) {
        id = buf.readUtf();
        cooldown = buf.readInt();
//        charge = buf.readInt();
        chargeAmount = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUtf(id);
        buf.writeInt(cooldown);
//        buf.writeInt(charge);
        buf.writeInt(chargeAmount);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context ctx = supplier.get();
        ctx.enqueueWork(() -> CAbilityClient.getCooldowns().addCooldown(id, cooldown, chargeAmount));
        return true;
    }
}
