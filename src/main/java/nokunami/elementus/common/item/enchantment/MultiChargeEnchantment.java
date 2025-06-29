package nokunami.elementus.common.item.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import nokunami.elementus.common.registry.ModItems;
import org.jetbrains.annotations.NotNull;

public class MultiChargeEnchantment extends Enchantment {
    public MultiChargeEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public boolean canEnchant(@NotNull ItemStack pStack) {
        return pStack.is(ModItems.ElementusItems.DIARKRITE_CHARGE_BLADE.get());
    }
}