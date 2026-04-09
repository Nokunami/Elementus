package net.nokunami.elementus.common.network.server;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraftforge.network.NetworkEvent;
import net.nokunami.elementus.common.capability.cAbility.CAbility;
import net.nokunami.elementus.common.network.ENetwork;
import net.nokunami.elementus.common.network.client.SyncAbilitySelectionS2CPacket;
import net.nokunami.elementus.common.registry.EGameRules;

import java.util.function.Supplier;

import static net.nokunami.elementus.common.registry.CustomRegistries.CatalystCoreHelper.CatalystCoreUtil;

public class CatalystAbilitySelectionPacket {
    protected int scrollAmount;

    public CatalystAbilitySelectionPacket(int amount) { scrollAmount = amount; }

    public CatalystAbilitySelectionPacket(FriendlyByteBuf buf) { scrollAmount = buf.readInt(); }

    public void toBytes(FriendlyByteBuf buf) { buf.writeInt(scrollAmount); }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player != null) {
                CAbility ca = CAbility.instance(player);
                ca.setAbilityAmount(Math.max(CatalystCoreUtil(player).getCore().getActiveAbilityList().size() - 1, 0));
                int selection = Mth.clamp(ca.getSelection() + scrollAmount, 0, ca.getAbilityAmount());

                ca.setSelection(selection);
                ENetwork.sendTo(player, new SyncAbilitySelectionS2CPacket(selection));
                if (EGameRules.isDebugModeOn(player.level())) {
                    player.sendSystemMessage(Component.literal(String.valueOf(ca.getSelection())));
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}