package net.nokunami.elementus.common.network.server;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraftforge.network.NetworkEvent;
import net.nokunami.elementus.common.capability.abilityData.AbilityData;
import net.nokunami.elementus.common.network.ENet;
import net.nokunami.elementus.common.network.client.SyncAbilitySelectionS2CPacket;
import net.nokunami.elementus.common.registry.EGameRules;

import java.util.function.Supplier;

import static net.nokunami.elementus.common.registry.ERegistries.CatalystCoreHelper.CatalystCoreUtil;

public class AbilitySelectionPacket {
    protected int scrollAmount;

    public AbilitySelectionPacket(int amount) { scrollAmount = amount; }

    public AbilitySelectionPacket(FriendlyByteBuf buf) { scrollAmount = buf.readInt(); }

    public void toBytes(FriendlyByteBuf buf) { buf.writeInt(scrollAmount); }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player != null) {
                AbilityData data = AbilityData.instance(player);
                int prev = data.getSelection();
                int selection = Mth.clamp(data.getSelection() + scrollAmount, 0, data.getAbilityStorage().activeCount());

                data.scroll(prev, selection);
                ENet.sendTo(player, new SyncAbilitySelectionS2CPacket(data));
                if (EGameRules.isDebugModeOn(player.level())) {
                    player.sendSystemMessage(Component.literal(String.valueOf(data.getSelection())));
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}