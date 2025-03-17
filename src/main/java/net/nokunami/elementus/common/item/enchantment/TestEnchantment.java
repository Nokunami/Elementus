package net.nokunami.elementus.common.item.enchantment;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.nokunami.elementus.common.registry.ModItems;
import org.jetbrains.annotations.NotNull;

public class TestEnchantment extends Enchantment {
    public TestEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }

    @Override
    public int getDamageProtection(int pLevel, @NotNull DamageSource pSource) {
        return super.getDamageProtection(pLevel, pSource);
    }

    @Override
    public float getDamageBonus(int level, MobType mobType, ItemStack enchantedItem) {
        return super.getDamageBonus(level, mobType, enchantedItem);
    }

    @Override
    public boolean canEnchant(@NotNull ItemStack pStack) {
        return pStack.is(ModItems.ElementusItems.DIARKRITE_CHARGE_BLADE.get());
    }
}