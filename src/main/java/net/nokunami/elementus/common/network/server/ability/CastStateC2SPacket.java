package net.nokunami.elementus.common.network.server.ability;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;
import net.nokunami.elementus.common.capability.cAbility.CAbility;
import net.nokunami.elementus.common.catalystCore.ability.AbilityCastManager;
import net.nokunami.elementus.common.catalystCore.ability.CastType;
import net.nokunami.elementus.common.registry.EGameRules;

import java.util.Optional;
import java.util.function.Supplier;

import static net.nokunami.elementus.common.item.unique.CatalystItemUtil.getEquippedCore;

public class CastStateC2SPacket {
    CastType type;

    public CastStateC2SPacket(CastType type) { this.type = type; }

    public CastStateC2SPacket(FriendlyByteBuf buf) {
        type = buf.readEnum(CastType.class);
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeEnum(type);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();

            if (player != null && !player.isSpectator()) {
                Level level = player.level();
                AbilityCastManager.onServerCastStart(player);
                if (EGameRules.isDebugModeOn(level)) player.sendSystemMessage(Component.literal(String.valueOf(type)));
            }
        });
        return true;
    }
}