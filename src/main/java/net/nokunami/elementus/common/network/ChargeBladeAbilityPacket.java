package net.nokunami.elementus.common.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;
import net.nokunami.elementus.common.item.AnthektiteChargeBlade;
import net.nokunami.elementus.common.item.ChargeSwordItem;
import net.nokunami.elementus.common.item.DiarkriteChargeBlade;
import net.nokunami.elementus.common.registry.ModDamageTypes;
import net.nokunami.elementus.common.registry.ModMobEffects.ElementusEffects;

import java.util.function.Supplier;

import static net.nokunami.elementus.common.config.UniqueItemConfig.diarkriteChargeBladeSelfSacrificeDamage;
import static net.nokunami.elementus.common.item.DiarkriteChargeBlade.*;
import static net.nokunami.elementus.common.registry.ModEnchantments.SACRIFICE_CURSE;

/// Code from SpartanObliviousSpartan's SpartanShields mod
public class ChargeBladeAbilityPacket {
    protected InteractionHand hand;

    public ChargeBladeAbilityPacket(InteractionHand handIn) {
        hand = handIn;
    }

    public static void encode(ChargeBladeAbilityPacket packet, FriendlyByteBuf buf) {
        buf.writeEnum(packet.hand);
    }

    public static ChargeBladeAbilityPacket decode(FriendlyByteBuf buf) {
        return new ChargeBladeAbilityPacket(buf.readEnum(InteractionHand.class));
    }

    public static void consume(final ChargeBladeAbilityPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();

            assert player != null;
            Item item = player.getItemInHand(packet.hand).getItem();
            ItemStack itemStack = player.getItemInHand(packet.hand);

            if (item instanceof ChargeSwordItem) {
                if (!itemStack.isEmpty() && player.isUsingItem() && !player.getCooldowns().isOnCooldown(itemStack.getItem())) {
                    ability(player, packet.hand);
                }
            }
        });
    }

    public static void ability(Player player, InteractionHand hand) {
        Level level = player.level();
        ItemStack stack = player.getUseItem();
        if (!level.isClientSide && !player.isSpectator() && player.isUsingItem()) {
            int cooldown = 0;
            if (stack.getItem() instanceof DiarkriteChargeBlade && (getCharge(stack) > 2 || isEnchantedWith(stack, SACRIFICE_CURSE) || player.isCreative())) {
                createBoom(level, player, stack);
                if (isEnchantedWith(stack, SACRIFICE_CURSE)) player.hurt(level.damageSources().source(ModDamageTypes.SACRIFICIAL), player.getMaxHealth() * (float) diarkriteChargeBladeSelfSacrificeDamage);
                cooldown = 10;
                player.swing(hand, true);
            }
            if (stack.getItem() instanceof AnthektiteChargeBlade) {
                if (player.hasEffect(ElementusEffects.ANTHEKTITE_SWORD_DANCE.get())) {
                    AnthektiteChargeBlade.swordDanceSlash(player, hand);
                    cooldown = 240;
                    player.swing(hand, true);
                } else if (!player.hasEffect(ElementusEffects.ANTHEKTITE_SWORD_DANCE.get()) && (getCharge(stack) >= getMaxCharge(stack) || player.isCreative())) {
                    player.addEffect(new MobEffectInstance(ElementusEffects.ANTHEKTITE_SWORD_DANCE.get(), 600));
                    setCharge(stack, -Math.min(getCharge(stack), getChargeStack(stack)));
                    cooldown = 10;
                }
            }
            if (!player.isCreative()) player.getCooldowns().addCooldown(stack.getItem(), cooldown);
        }
    }
}
