package net.nokunami.elementus.common.item.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.DamageEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.nokunami.elementus.common.Etags;
import net.nokunami.elementus.common.config.EConfig;
import org.jetbrains.annotations.NotNull;

public class ArcaneSharpnessEnchantment extends Enchantment {

    public ArcaneSharpnessEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }

    @Override
    public float getDamageBonus(int level, MobType mobType, ItemStack enchantedItem) {
        return (float) (enchantedItem.getEnchantmentValue()* EConfig.COMMON.arcaneSharpnessPercent.get());
    }

    public boolean isTreasureOnly() {
        return EConfig.COMMON.arcaneSharpnessTreasure.get();
    }

    public boolean checkCompatibility(@NotNull Enchantment pEnch) {
        if (EConfig.COMMON.arcaneSharpnessIncompatibility.get()) {
            return !(pEnch instanceof DamageEnchantment);
        } return false;
    }

    public boolean canEnchant(@NotNull ItemStack stack) {
        Item item = stack.getItem();
        return item instanceof AxeItem || stack.is(Etags.Items.ARCANE_SHARPNESS_COMPATIBLE) || super.canEnchant(stack);
    }
}