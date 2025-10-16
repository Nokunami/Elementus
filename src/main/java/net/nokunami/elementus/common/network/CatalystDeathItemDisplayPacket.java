package net.nokunami.elementus.common.network;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;
import net.nokunami.elementus.common.registry.CustomRegistries;

import java.util.function.Supplier;

import static net.nokunami.elementus.common.item.unique.CatalystItemUtil.getEquippedCore;

public class CatalystDeathItemDisplayPacket {
    protected EquipmentSlot slot;

    public CatalystDeathItemDisplayPacket(EquipmentSlot equipmentSlot) {
        slot = equipmentSlot;
    }

    public static void encode(CatalystDeathItemDisplayPacket packet, FriendlyByteBuf buf) {
        buf.writeEnum(packet.slot);
    }

    public static CatalystDeathItemDisplayPacket decode(FriendlyByteBuf buf) {
        return new CatalystDeathItemDisplayPacket(buf.readEnum(EquipmentSlot.class));
    }

    public static void consume(CatalystDeathItemDisplayPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player != null) {
                ItemStack chestplate = player.getItemBySlot(packet.slot);
                if (!chestplate.isEmpty()) {
//                    Minecraft.getInstance().gameRenderer.displayItemActivation(CatalystCore.getInstance(chestplate).getCoreStack());
                    if (getEquippedCore(chestplate).isPresent())
                        Minecraft.getInstance().gameRenderer.displayItemActivation(CustomRegistries.getCatalystCore(getEquippedCore(chestplate).get()).getCoreStack());
//                    if (player.level().isClientSide) {
//                    }
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
