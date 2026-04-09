package net.nokunami.elementus.common.item;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.nokunami.elementus.common.config.EConfig;
import net.nokunami.elementus.common.item.basic.IConfiguredItem;
import net.nokunami.elementus.common.tags.EBlockTags;
import net.nokunami.elementus.common.tags.EItemTags;

import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

import static net.nokunami.elementus.common.registry.EEnchantments.*;
import static net.nokunami.elementus.common.registry.ETier.EnumTiers.*;

public class EItemUtil {
    public static void tooltip(ItemStack stack, List<Component> tooltip, Tier tier) {
        if (isDiarkrite(tier) && stack.getItem() instanceof PickaxeItem) {
            tooltip.add(Component.translatable("item.elementus.diarkrite_pickaxe.desc").withStyle(ChatFormatting.DARK_AQUA));
        }
    }

    public static float toolMiningSpeed(float originalSpeed, ItemStack stack, BlockState state) {
        float newSpeed = originalSpeed;
        Item item = stack.getItem();
        if (item instanceof DiggerItem diggerItem && item instanceof IConfiguredItem configuredItem) {
            Tier tier = diggerItem.getTier();
            if (diggerItem.isCorrectToolForDrops(stack, state)) {
                if (isDiarkrite(tier) && stack.getItem() instanceof PickaxeItem) {
                    if (matchBlockState(state)) {
                        newSpeed *= Math.max(EConfig.COMMON.DIARKRITE.bonusEfficiency.get().floatValue(), 1);
                    }
                }
//                if (isAnthektite(tier)) {
//                    AnthektiteHeat heat = AnthektiteHeat.anthektiteHeat(stack);
//                    newSpeed *= Math.max(1, 1 + (heat.getHeat() * 10F));
//                }
                if (isMovcadia(tier)) {
                    newSpeed *= (float) (1 - (((double) stack.getDamageValue()) / ((double) stack.getMaxDamage())));
                    if (getMovcadiaEssence(stack) > 0) newSpeed *= 2;
                }
            }
        }
        return newSpeed;
    }

    public static boolean matchBlockState(BlockState state) {
        return state.is(EBlockTags.DIARKRITE_EFFICIENT);
    }

    //code from aether mod
    public static float getDurablityBasedSpeed(ItemStack stack, boolean reverse) {
        if (reverse) return (float) ((-2.0 * ((double) stack.getDamageValue()) / ((double) stack.getMaxDamage()) + 0.5));
        return (float) ((2.0 * ((double) stack.getDamageValue()) / ((double) stack.getMaxDamage()) + 0.5));
    }

    public static boolean isEquipment(LivingEntity entity, Item item) {
        return entity.getItemBySlot(EquipmentSlot.CHEST).is(item);
    }

    public static boolean isDiarkrite(Tier tier) { return tier.equals(DIARKRITE)/* || tier.equals(ETier.DIARKRITE)*/; }
    public static boolean isAnthektite(Tier tier) { return tier.equals(ANTHEKTITE)/* || tier.equals(ETier.ANTHEKTITE)*/; }
    public static boolean isMovcadia(Tier tier) { return tier.equals(MOVCADIA)/* || tier.equals(ETier.MOVCADIA)*/; }

    public static boolean getFriendlyFire(ItemStack stack) {
        return stack.getOrCreateTag().getBoolean("FriendlyFire");
    }

    public static void setFriendlyFire(ItemStack stack, boolean b) {
        stack.getOrCreateTag().putBoolean("FriendlyFire", b);
    }

    public static int getMovcadiaEssence(ItemStack stack) {
        CompoundTag tag = stack.getTag();
        return tag != null ? tag.getInt("MovcadiaEssence") : 0;
    }

    public static void setMovcadiaEssence(ItemStack stack) {
        setMovcadiaEssence(stack, 32);
    }

    public static void setMovcadiaEssence(ItemStack stack, int b) {
        stack.getOrCreateTag().putInt("MovcadiaEssence", b);
    }

    public static int getEssenceBarWidth(ItemStack stack) {
        return Math.round(13.0F - (float)(32 - getMovcadiaEssence(stack)) * 13.0F / (float)32);
    }

//    public static boolean canBeHurtBy(DamageSource source, Tier tier) {
//        return !tier.equals(STEEL_CMD) && !tier.equals(DIARKRITE_CMD) && !tier.equals(ANTHEKTITE_CMD);
//    }

    public static void movcadiaClickAction(ItemStack stack, ItemStack otherStack, Player player) {
        player.playSound(SoundEvents.ENDER_EYE_DEATH);
        otherStack.shrink(1);
        setMovcadiaEssence(stack);
    }

    public static boolean getRepairItem(ItemStack stack, Tier tier) {
        return tier.equals(ASTALITE) ? stack.is(EItemTags.REPAIRS_ASTALITE_EQUIPMENT)
                : tier.equals(DIARKRITE) ? stack.is(EItemTags.REPAIRS_DIARKRITE_EQUIPMENT)
                : tier.equals(ANTHEKTITE) ? stack.is(EItemTags.REPAIRS_ANTHEKTITE_EQUIPMENT)
                : tier.equals(MOVCADIA) && stack.is(EItemTags.REPAIRS_MOVCADIA_EQUIPMENT);
    }

    // Credits: Cataclysm
    public static void replaceModifier(ItemAttributeModifierEvent event, Attribute attribute, UUID uuid, double amount) {
        event.getModifiers().get(attribute).stream().filter(mod -> mod.getId().equals(uuid))
                .findFirst().ifPresent(mod -> event.removeModifier(attribute, mod));
        event.addModifier(attribute, new AttributeModifier(uuid, "Weapon modifier", amount, AttributeModifier.Operation.ADDITION));
    }

    public static boolean enchantedWith(ItemStack stack, Supplier<? extends Enchantment> enchantment) {
        return EnchantmentHelper.getTagEnchantmentLevel(enchantment.get(), stack) > 0;
    }

    public static boolean sacrifice(ItemStack stack) {
        return enchantedWith(stack, SACRIFICE_CURSE);
    }
    public static boolean condensedBurst(ItemStack stack) {
        return enchantedWith(stack, CONDENSED_BURST);
    }
    public static boolean burstPulse(ItemStack stack) {
        return enchantedWith(stack, PULSE_BURST);
    }
    public static boolean rush(ItemStack stack) {
        return enchantedWith(stack, RUSH);
    }
    public static boolean chargeStacking(ItemStack stack) {
        return enchantedWith(stack, CHARGE_STACKING);
    }
}