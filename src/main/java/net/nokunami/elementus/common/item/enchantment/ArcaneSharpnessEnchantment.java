package net.nokunami.elementus.common.item.enchantment;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.DamageEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.nokunami.elementus.common.config.ModConfig;
import net.nokunami.elementus.common.registry.ModEnchantments;
import nl.sniffiandros.sniffsweapons.item.GreatPickaxeItem;
import nl.sniffiandros.sniffsweapons.item.NaginataItem;
import org.jetbrains.annotations.NotNull;

import static net.nokunami.elementus.ModChecker.*;

public class ArcaneSharpnessEnchantment extends Enchantment {

    public ArcaneSharpnessEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }

    @Override
    public float getDamageBonus(int level, MobType mobType, ItemStack enchantedItem) {
        return (float) (enchantedItem.getEnchantmentValue()* ModConfig.COMMON.arcaneSharpnessPercent.get());
    }

    public float getArcaneDamage(ItemStack enchantedItem) {
        return (float) (enchantedItem.getEnchantmentValue()* ModConfig.COMMON.arcaneSharpnessPercent.get());
    }

    @Override
    public void doPostAttack(LivingEntity pAttacker, @NotNull Entity pTarget, int pLevel) {
        ItemStack enchantedItem = pAttacker.getItemInHand(InteractionHand.MAIN_HAND);
        int i = EnchantmentHelper.getTagEnchantmentLevel(ModEnchantments.ARCANE_SHARPNESS.get(), enchantedItem);
        if (i > 0) {
            pTarget.hurt(pAttacker.damageSources().source(DamageTypes.SONIC_BOOM), getArcaneDamage(enchantedItem));
        } else super.doPostAttack(pAttacker, pTarget, pLevel);
    }

    public boolean isTreasureOnly() {
        return ModConfig.COMMON.arcaneSharpnessTreasure.get();
    }

    public boolean checkCompatibility(@NotNull Enchantment pEnch) {
        if (ModConfig.COMMON.arcaneSharpnessIncompatibility.get()) {
            return !(pEnch instanceof DamageEnchantment);
        } return false;
    }

    public boolean canEnchant(@NotNull ItemStack stack) {
        Item item = stack.getItem();
        if (sniffsWeapons) return item instanceof AxeItem || super.canEnchant(stack)|| item instanceof GreatPickaxeItem || item instanceof NaginataItem;
        return item instanceof AxeItem || super.canEnchant(stack);
    }
}