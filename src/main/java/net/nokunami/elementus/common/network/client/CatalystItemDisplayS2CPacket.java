package net.nokunami.elementus.common.network.client;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkEvent;
import net.nokunami.elementus.common.registry.CustomRegistries;

import java.util.function.Supplier;

import static net.nokunami.elementus.common.item.unique.CatalystItemUtil.getEquippedCore;
import static net.nokunami.elementus.common.registry.CustomRegistries.CatalystCoreHelper.CatalystCoreUtil;

public class CatalystItemDisplayS2CPacket {

    public CatalystItemDisplayS2CPacket() { }

    public CatalystItemDisplayS2CPacket(FriendlyByteBuf buf) { }

    public void toBytes(FriendlyByteBuf buf) { }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(CatalystItemDisplayS2CPacket::sendItemDisplay);
        return true;
    }

    @OnlyIn(Dist.CLIENT)
    public static void sendItemDisplay() {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (player != null) {
//            ItemStack chestplate = player.getItemBySlot(slot);
//            if (!chestplate.isEmpty()) {
//                if (getEquippedCore(chestplate).isPresent())
//                    Minecraft.getInstance().gameRenderer.displayItemActivation(CustomRegistries.getCatalystCore(getEquippedCore(chestplate).get()).getCoreStack());
//            }
            var core = CatalystCoreUtil(player);
            if (core.hasCore()) {
                Minecraft.getInstance().gameRenderer.displayItemActivation(core.getCoreStack());
            }
        }
    }
}
