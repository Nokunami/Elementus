package net.nokunami.elementus.enchantment;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.enchantment.DamageEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;
import org.jetbrains.annotations.NotNull;

public class ArcaneSharpnessEnchantment extends ArcaneDamage {
    float damage = 0.25f;

    private Enchantment self()
    {
        return (Enchantment) this;
    }


    public ArcaneSharpnessEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }

    //public float getDamageBonus(int pLevel, MobType pCreatureType) {
    //    if (this.type == 0) {
    //        return 1.0F + (float)Math.max(0, pLevel - 1) * 0.5F;
    //    } else if (this.type == 1 && pCreatureType == MobType.UNDEAD) {
    //        return (float)pLevel * 2.5F;
    //    } else {
    //        return this.type == 2 && pCreatureType == MobType.ARTHROPOD ? (float)pLevel * 2.5F : 0.0F;
    //    }
    //}

    //public float getArcaneDamageBonus(ItemStack stack, ArcaneDamage arcaneDamage) {
    //    return this.damage * ArcaneDamage.GetEnchantmentValueFromItem(stack);
    //}
    @Override
    public float getArcaneDamageBonus(ItemStack stack, ArcaneDamage arcaneDamage) {
        return self().getArcaneDamageBonus(stack, arcaneDamage);
    }

    public boolean isTreasureOnly() {
        return true;
    }

    public boolean checkCompatibility(@NotNull Enchantment pEnch) {
        return !(pEnch instanceof DamageEnchantment);
    }

    public boolean canEnchant(ItemStack pStack) {
        return pStack.getItem() instanceof AxeItem || super.canEnchant(pStack);
    }

    @Override
    public float getDamageBonus(int level, MobType mobType, ItemStack enchantedItem) {
        return super.getDamageBonus(level, mobType, enchantedItem);
    }
}
