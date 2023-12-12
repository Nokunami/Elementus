package net.nokunami.elementus.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class ArcaneDamage extends Enchantment {

    float damage = 0.25f;
    protected ArcaneDamage(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot[] pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }

    public static int GetEnchantmentValueFromItem(ItemStack stack) {
        return stack.getEnchantmentValue();
    }




    public float getArcaneDamageBonus(ItemStack stack, ArcaneDamage arcaneDamage) {
        return 0.0F;
    }
}
