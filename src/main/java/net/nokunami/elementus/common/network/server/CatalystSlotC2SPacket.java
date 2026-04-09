package net.nokunami.elementus.common.network.server;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;
import net.nokunami.elementus.common.item.IScrollableItem;
import net.nokunami.elementus.common.item.unique.CatalystItemUtil;
import net.nokunami.elementus.common.item.unique.TestCatalystArmorItem;
import net.nokunami.elementus.common.registry.EGameRules;

import java.util.function.Supplier;

public class CatalystSlotC2SPacket {

    protected int containerId;
    protected int stateId;
    protected int slotId;
    protected int scroll;

    public CatalystSlotC2SPacket(int container, int state, int slot, int i) {
        containerId = container;
        stateId = state;
        slotId = slot;
        scroll = i;
    }

    public CatalystSlotC2SPacket(FriendlyByteBuf buf) {
        containerId = buf.readInt();
        stateId = buf.readInt();
        slotId = buf.readInt();
        scroll = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(containerId);
        buf.writeInt(stateId);
        buf.writeInt(slotId);
        buf.writeInt(scroll);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player != null) {
                AbstractContainerMenu menu = player.containerMenu;
                if (EGameRules.isDebugModeOn(player.level())) player.sendSystemMessage(Component.literal(String.valueOf(slotId)));
                player.resetLastActionTime();
                if (menu.containerId != containerId) return;
                if (player.isSpectator()) {
                    menu.sendAllDataToRemote();
                    return;
                }
                if (!menu.stillValid(player) || !menu.isValidSlotIndex(slotId)) return;

                boolean flag = stateId == menu.getStateId();
                menu.suppressRemoteUpdates();
                Slot slot = menu.getSlot(slotId);
                ItemStack stack = slot.getItem();
                if (stack.getItem() instanceof IScrollableItem scrollableItem) scrollableItem.onItemScroll(stack, scroll);
                menu.resumeRemoteUpdates();

                if (flag) menu.broadcastFullState();
                else menu.broadcastChanges();
            }

        });
    }
}
