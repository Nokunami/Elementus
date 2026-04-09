package net.nokunami.elementus.common.network.server;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;
import net.nokunami.elementus.common.item.unique.ChargeBladeItem;

import java.util.function.Supplier;

import static net.nokunami.elementus.common.item.unique.BladeOfResonance.*;

/// Code from SpartanObliviousSpartan's SpartanShields mod
public class ChargeBladeAbilityC2SPacket {

    protected InteractionHand hand;

    public ChargeBladeAbilityC2SPacket(InteractionHand handIn) {
        hand = handIn;
    }

    public ChargeBladeAbilityC2SPacket(FriendlyByteBuf buf) { hand = buf.readEnum(InteractionHand.class); }

    public void toBytes(FriendlyByteBuf buf) { buf.writeEnum(hand); }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();

            assert player != null;
            Item item = player.getItemInHand(hand).getItem();
            ItemStack itemStack = player.getItemInHand(hand);

            if (item instanceof ChargeBladeItem) {
                if (!itemStack.isEmpty() && player.isUsingItem() && !player.getCooldowns().isOnCooldown(itemStack.getItem())) {
                    ability(player, hand);
                }
            }
        });
    }

    static void ability(Player player, InteractionHand hand) {
        Level level = player.level();
        ItemStack stack = player.getUseItem();
        if (!level.isClientSide && !player.isSpectator() && player.isUsingItem()) {
            int cooldown = 0;
            boolean shouldStopUsing = false;
            if (stack.getItem() instanceof ChargeBladeItem chargeBladeItem) {
                ChargeBladeAbility ability = chargeBladeItem.castAbility(player, level, stack, hand);
                cooldown = ability.getCooldown();
                shouldStopUsing = ability.stopUsingItem();
                player.resetAttackStrengthTicker();
            }
            if (!player.isCreative()) {
                player.getCooldowns().addCooldown(stack.getItem(), cooldown);
                if (shouldStopUsing) player.stopUsingItem();
            }
        }
    }
}
