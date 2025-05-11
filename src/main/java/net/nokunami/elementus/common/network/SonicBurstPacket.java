package net.nokunami.elementus.common.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;
import net.nokunami.elementus.common.item.DiarkriteChargeBlade;
import net.nokunami.elementus.common.registry.ModDamageTypes;

import java.util.function.Supplier;

import static net.nokunami.elementus.common.config.UniqueItemConfig.diarkriteChargeBladeSelfSacrificeDamage;
import static net.nokunami.elementus.common.item.DiarkriteChargeBlade.*;
import static net.nokunami.elementus.common.registry.ModEnchantments.SACRIFICE_CURSE;

/// Code from SpartanObliviousSpartan's SpartanShields mod
public class SonicBurstPacket {
    protected InteractionHand hand;

    public SonicBurstPacket(InteractionHand handIn) {
        hand = handIn;
    }

    public static void encode(SonicBurstPacket packet, FriendlyByteBuf buf) {
        buf.writeEnum(packet.hand);
    }

    public static SonicBurstPacket decode(FriendlyByteBuf buf) {
        return new SonicBurstPacket(buf.readEnum(InteractionHand.class));
    }

    public static void consume(final SonicBurstPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();

            assert player != null;
            if (player.getItemInHand(packet.hand).getItem() instanceof DiarkriteChargeBlade) {
                if (player.isUsingItem()) {
                    ItemStack itemStack = player.getItemInHand(packet.hand);

                    if (!itemStack.isEmpty() && !player.getCooldowns().isOnCooldown(itemStack.getItem())) {
                        boom(player);
                    }
                }
            }
        });
    }

    public static void boom(Player player) {
        Level level = player.level();
        ItemStack stack = player.getUseItem();
        if (!level.isClientSide && !player.isSpectator() && player.isUsingItem() && (getCharge(stack) > 2 || isEnchantedWith(stack, SACRIFICE_CURSE) || player.isCreative())) {
            createBoom(level, player, stack);
            if (isEnchantedWith(stack, SACRIFICE_CURSE)) player.hurt(level.damageSources().source(ModDamageTypes.SACRIFICIAL), player.getMaxHealth() * (float) diarkriteChargeBladeSelfSacrificeDamage);
            if (!player.isCreative()) player.getCooldowns().addCooldown(stack.getItem(), 10);
        }
    }
}
