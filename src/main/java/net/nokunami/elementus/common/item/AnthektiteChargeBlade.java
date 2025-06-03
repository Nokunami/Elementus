package net.nokunami.elementus.common.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;
import net.nokunami.elementus.common.entity.projectile.AnthektiteSlash;
import net.nokunami.elementus.common.entity.projectile.AnthektiteSlash2;
import net.nokunami.elementus.common.entity.projectile.AnthektiteSlash3;
import net.nokunami.elementus.common.network.AnthektiteSlashPacket;
import net.nokunami.elementus.common.network.ModNetwork;
import net.nokunami.elementus.common.registry.ModEnchantments;
import net.nokunami.elementus.common.registry.ModTiers;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Locale;

import static net.nokunami.elementus.Elementus.MODID;
import static net.nokunami.elementus.common.config.UniqueItemConfig.*;
import static net.nokunami.elementus.common.registry.ModEnchantments.CONDENSED_BURST;
import static net.nokunami.elementus.common.registry.ModEnchantments.SACRIFICE_CURSE;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class AnthektiteChargeBlade extends ChargeSwordItem {

    public AnthektiteChargeBlade() {
        super(ModTiers.ANTHEKTITE, anthektiteChargeBladeDamage, (float) anthektiteChargeBladeAttackSpeed, (float) anthektiteChargeBladeAttackReach, new Properties().fireResistant().rarity(Rarity.EPIC));
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        String chargeText = isEnchantedWith(stack, SACRIFICE_CURSE) ? "item.elementus.diarkrite_charge_blade.cursed_charge_desc" : "item.elementus.diarkrite_charge_blade.charge_desc";
        ChatFormatting chargeTextColor = isEnchantedWith(stack, SACRIFICE_CURSE) ? ChatFormatting.DARK_RED : ChatFormatting.GRAY;
        ChatFormatting underlineText = isEnchantedWith(stack, SACRIFICE_CURSE) ? ChatFormatting.UNDERLINE : ChatFormatting.GRAY;
        String damageModifierText = isEnchantedWith(stack, SACRIFICE_CURSE) && isEnchantedWith(stack, CONDENSED_BURST) ? " (+25%) (-40%)" : isEnchantedWith(stack, SACRIFICE_CURSE) ? " (+25%)" : isEnchantedWith(stack, CONDENSED_BURST) ? " (-40%)" : "";
        ChatFormatting damageModifierColor = isEnchantedWith(stack, SACRIFICE_CURSE) || isEnchantedWith(stack, CONDENSED_BURST) ? ChatFormatting.GOLD : ChatFormatting.DARK_AQUA;
        String friendlyFireStr = String.valueOf(getFriendlyFire(stack));
        super.appendHoverText(stack, level, tooltip, flag);

        tooltip.add(Component.translatable("item.elementus.charge_item.friendly_fire_desc",
                friendlyFireStr.substring(0, 1).toUpperCase(Locale.ROOT) + friendlyFireStr.substring(1)).withStyle(ChatFormatting.GRAY));
        if (stack.isEnchanted()) tooltip.add(CommonComponents.EMPTY);
    }

    @Override
    public boolean canDisableShield(ItemStack stack, ItemStack shield, LivingEntity entity, LivingEntity attacker) {
        return false;
    }

    @Override
    public boolean canBeHurtBy(DamageSource pDamageSource) {
        return pDamageSource.is(DamageTypeTags.BYPASSES_INVULNERABILITY);
    }

    @Override
    public int getBarColor(@NotNull ItemStack pStack) {
        return 7924965;
    }

    public static void emptyClick(ItemStack stack) {
        if (!stack.isEmpty() && stack.getItem() instanceof AnthektiteChargeBlade){
            ModNetwork.INSTANCE.send(PacketDistributor.SERVER.noArg(), new AnthektiteSlashPacket());
        }
    }

    /// Credits: Goety Mod Death Scythe
    public static void spawnSlash(Player player) {
        Level level = player.level();
        if (player.getAttackStrengthScale(1.0F) >= 0.99F) {
            level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENDER_EYE_DEATH, SoundSource.NEUTRAL, 1.0F, 1.4F / (level.random.nextFloat() * 0.4F + 0.8F));
            if (!level.isClientSide) {
                AnthektiteSlash slash = new AnthektiteSlash(level, player);
                slash.setOwnerId(player.getUUID());
                slash.setBlockPos(player.blockPosition());
                slash.setDamage(8);
                slash.setDiscardDistance(16);
                slash.setChargeable(true);
                slash.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.25F, 1.0F);
                level.addFreshEntity(slash);
            }
        }
    }

    @SubscribeEvent
    public static void EmptyClickEvents(PlayerInteractEvent.LeftClickEmpty event){
        AnthektiteChargeBlade.emptyClick(event.getItemStack());
    }
}
