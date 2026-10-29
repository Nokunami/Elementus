package net.nokunami.elementus.api.util;

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
import net.nokunami.elementus.config.EConfig;
import net.nokunami.elementus.common.registry.ETiers;
import net.nokunami.elementus.common.tags.EBlockTags;
import net.nokunami.elementus.common.tags.EItemTags;

import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

import static net.nokunami.elementus.common.registry.EEnchantments.*;
import static net.nokunami.elementus.common.registry.ETiers.*;

public class EItemUtil {
    public static void tooltip(ItemStack stack, List<Component> tooltip, Tier tier) {
        if (isDiarkrite(tier) && stack.getItem() instanceof PickaxeItem) {
            tooltip.add(Component.translatable("item.elementus.diarkrite_pickaxe.desc").withStyle(ChatFormatting.DARK_AQUA));
        }
    }

    public static float toolMiningSpeed(float originalSpeed, ItemStack stack, BlockState state) {
        float newSpeed = originalSpeed;
        Item item = stack.getItem();
        if (item instanceof DiggerItem diggerItem) {
            Tier tier = diggerItem.getTier();
            if (diggerItem.isCorrectToolForDrops(stack, state)) {
                if (stack.getItem() instanceof PickaxeItem && tier instanceof ETiers eTiers) {
                    if (matchBlockState(state, eTiers)) {
                        newSpeed *= (float) EConfig.getMaterialSpeed(tier);
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

    public static boolean matchBlockState(BlockState state, ETiers tier) {
        return switch (tier) {
            case ASTALITE -> state.is(EBlockTags.ASTALITE_EFFICIENT);
            case DIARKRITE -> state.is(EBlockTags.DIARKRITE_EFFICIENT);
            case ANTHEKTITE -> state.is(EBlockTags.ANTHEKTITE_EFFICIENT);
            case MOVCADIA -> state.is(EBlockTags.MOVCADIA_EFFICIENT);
        };
    }
//    public static boolean isCorrectTierForDrops(Tier tier, BlockState state)
//    {
//        if (!isTierSorted(tier))
//            return isCorrectTierVanilla(tier, state);
//
//        for (int x = sortedTiers.indexOf(tier) + 1; x < sortedTiers.size(); x++) {
//            TagKey<Block> tag = sortedTiers.get(x).getTag();
//            if (tag != null && state.is(tag))
//                return false;
//        }
//        return true;
//    }

    //code from aether mod
    public static float getDurablityBasedSpeed(ItemStack stack, boolean reverse) {
        if (reverse) return (float) ((-2.0 * ((double) stack.getDamageValue()) / ((double) stack.getMaxDamage()) + 0.5));
        return (float) ((2.0 * ((double) stack.getDamageValue()) / ((double) stack.getMaxDamage()) + 0.5));
    }

    public static boolean isEquipment(LivingEntity entity, Item item) {
        return entity.getItemBySlot(EquipmentSlot.CHEST).is(item);
    }

    public static boolean isDiarkrite(Tier tier) { return tier.equals(DIARKRITE); }
    public static boolean isAnthektite(Tier tier) { return tier.equals(ANTHEKTITE); }
    public static boolean isMovcadia(Tier tier) { return tier.equals(MOVCADIA); }

    public static boolean getFriendlyFire(ItemStack stack) { return stack.getOrCreateTag().getBoolean("FriendlyFire"); }
    public static void setFriendlyFire(ItemStack stack, boolean b) { stack.getOrCreateTag().putBoolean("FriendlyFire", b); }

    public static int getMovcadiaEssence(ItemStack stack) {
        CompoundTag tag = stack.getTag();
        return tag != null ? tag.getInt("MovcadiaEssence") : 0;
    }

    public static void setMovcadiaEssence(ItemStack stack) { setMovcadiaEssence(stack, 32); }
    public static void setMovcadiaEssence(ItemStack stack, int b) { stack.getOrCreateTag().putInt("MovcadiaEssence", b); }
    public static int getEssenceBarWidth(ItemStack stack) { return Math.round(13.0F - (float)(32 - getMovcadiaEssence(stack)) * 13.0F / (float)32); }

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
                : isDiarkrite(tier) ? stack.is(EItemTags.REPAIRS_DIARKRITE_EQUIPMENT)
                : isAnthektite(tier) ? stack.is(EItemTags.REPAIRS_ANTHEKTITE_EQUIPMENT)
                : isMovcadia(tier) && stack.is(EItemTags.REPAIRS_MOVCADIA_EQUIPMENT);
    }

//    @Nullable
//    public static Map.Entry<EquipmentSlot, ItemStack> getRandomItemWith(Enchantment pEnchantment, LivingEntity pLivingEntity, Predicate<ItemStack> pStackCondition) {
//        Map<EquipmentSlot, ItemStack> map = pEnchantment.getSlotItems(pLivingEntity);
//        Map<EquipmentSlot, ItemStack> map = pLivingEntity.getAllSlots().;
//
//        if (map.isEmpty()) {
//            return null;
//        } else {
//            List<Map.Entry<EquipmentSlot, ItemStack>> list = Lists.newArrayList();
//
//            for(Map.Entry<EquipmentSlot, ItemStack> entry : map.entrySet()) {
//                ItemStack itemstack = entry.getValue();
//                if (!itemstack.isEmpty() && itemstack.is(EItemTags.REPAIR_ON_KILL) && pStackCondition.test(itemstack)) {
//                    list.add(entry);
//                }
//            }
//
//            return list.isEmpty() ? null : list.get(pLivingEntity.getRandom().nextInt(list.size()));
//        }
//    }
//
//    public Map<EquipmentSlot, ItemStack> getSlotItems(LivingEntity pEntity) {
//        Map<EquipmentSlot, ItemStack> map = Maps.newEnumMap(EquipmentSlot.class);
//
//        for(ItemStack itemstack : pEntity.getAllSlots()) {
//            if (!itemstack.isEmpty()) {
//                map.put(equipmentslot, itemstack);
//            }
//        }
//
//        return map;
//    }

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